package com.exercisetracker.domain.enums;

public enum UserType {

    USER(1, "ROLE_USER"),
    MODERATOR(2,"ROLE_MOD"),
    ADMINISTRATOR(3,"ROLE_ADMIN");

    private int value;
    private String descricao;
    
    private UserType(int i, String descricao) {
		this.value = i;
		this.descricao = descricao;
	}

	public int getValue() {
		return value;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static UserType toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (UserType x : UserType.values()) {
			if(cod.equals(x.getValue())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
    
}