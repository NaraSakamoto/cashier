package com.cashier.model.vo;

import javax.validation.constraints.NotNull;

public class ClientVO {

    @NotNull(message = "Client id can't be null")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
