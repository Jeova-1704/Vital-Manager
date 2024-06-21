package com.vitalManager.vitalManager.model.Enum;

public enum TipoTelefoneEnum {
    RES("RES"),
    COM("COM"),
    CEL("CEL");

    private final String codigo;

    TipoTelefoneEnum(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoTelefoneEnum fromCodigo(String codigo) {
        for (TipoTelefoneEnum tipo : TipoTelefoneEnum.values()) {
            if (tipo.codigo.equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido para TipoTelefoneEnum: " + codigo);
    }
}
