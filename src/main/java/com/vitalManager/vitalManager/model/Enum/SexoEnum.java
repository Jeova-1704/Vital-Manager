package com.vitalManager.vitalManager.model.Enum;

public enum SexoEnum {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");

    private final String descricao;

    SexoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SexoEnum fromDescricao(String descricao) {
        for (SexoEnum sexo : SexoEnum.values()) {
            if (sexo.descricao.equalsIgnoreCase(descricao)) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Descrição inválida para SexoEnum: " + descricao);
    }
}
