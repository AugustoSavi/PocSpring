package com.poc.academia.api.aula.enums;

public enum TipoAula {
    PILATES("Pilates"), FUNCIONAL("Funcional");

    private final String description;

    TipoAula(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
