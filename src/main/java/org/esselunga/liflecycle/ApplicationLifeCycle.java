package org.esselunga.liflecycle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.esselunga.depots.api.DepotController;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.products.api.ProductController;
import org.esselunga.products.dto.ProductDTO;
import org.esselunga.utils.exception.ApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.mongodb.assertions.Assertions.assertTrue;

@ApplicationScoped
public class ApplicationLifeCycle {

    @Inject
    DepotController depotController;

    @Inject
    ProductController productController;

    void onStart(@Observes StartupEvent event) throws ApplicationException, IOException {
        this.initDepot();
        this.initProducts();
    }

    void onStop(@Observes ShutdownEvent event) throws ApplicationException {
        try (Response response = depotController.deleteAll()) {
            assertTrue(response.getStatus() == 200);
        }

        try (Response response = productController.deleteAll()) {
            assertTrue(response.getStatus() == 200);
        }
    }


    void initDepot() throws ApplicationException, IOException {
        DepotDTO depot = readJsonFromClasspath("Depot.json", new TypeReference<DepotDTO>() {
        });
        // Try-with-resources needed to prevent potential resource leaks.
        try (Response response = depotController.insertDepot(depot)) {
            assertTrue(response.getStatus() == 200);
        }
    }

    void initProducts() throws ApplicationException, IOException {
        List<ProductDTO> products = (readJsonFromClasspath("Products.json", new TypeReference<List<ProductDTO>>() {}));

        // Insert each product using the controller method
        for (ProductDTO product : products) {
            // Try-with-resources needed to prevent potential resource leaks.
            try (Response response = productController.insertProduct(product)) {
                assertTrue(response.getStatus() == 200);
            }
        }
    }

    /**
     * Read a json file and return the relative Object to instantiate.
     *
     * @param filename is the Canonical Name of the resource and must be located in the /resources folder
     *                 In order to point to different locations, add the relative path before "filename".
     **/
    private static <T> T readJsonFromClasspath(String filename, TypeReference<T> typeReference) throws IOException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            if (inputStream != null) {
                byte[] bytes = inputStream.readAllBytes();
                String jsonContent = new String(bytes, StandardCharsets.UTF_8);
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(jsonContent, typeReference);

            } else {
                throw new IOException("File not found: " + "Products.json");
            }
        }
    }
}
