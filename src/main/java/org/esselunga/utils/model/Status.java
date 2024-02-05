package org.esselunga.utils.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PRESO_IN_CARICO("PRESO IN CARICO"),
    PARTITO("PARTITO"),
    IN_CONSEGNA("IN CONSEGNA"),
    CONSEGNATO("CONSEGNATO"),
    DESTINATARIO_NON_TROVATO("DESTINATARIO NON TROVATO"),
    DESTINAZIONE_SCONOSCIUTA("DESTINAZIONE SCONOSCIUTA"),
    RISPEDITO_AL_MITTENTE("RISPEDITO AL MITTENTE");

    private final String descrizione;


    public static Status fromDescrizione(String descrizione) {
        for (Status status : Status.values()) {
            if (status.descrizione.equals(descrizione)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with descrizione: " + descrizione);
    }
}