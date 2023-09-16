package br.com.commerce.api.models;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum OrderState {
    ABANDONED(1,"ABANDONED"),
    INCOMPLETE(2,"INCOMPLETE"),
    COMPLETE(3,"COMPLETE");

    private Integer code;
    private String description;

    public static OrderState toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (OrderState x : OrderState.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
