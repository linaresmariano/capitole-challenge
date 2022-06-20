package com.inditex.pricing.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MessageErrorEnum {
	
    PRICE_NOT_FOUND_EXCEPTION("No se encontraron precios disponibles");

    private String description;

}
