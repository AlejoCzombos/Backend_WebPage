package com.backend.Desktop.DTO;

import lombok.Data;

import java.time.Month;

@Data
public class MonthlyFeeDTO {

    private Month month;
    private boolean paid;

    // Constructor, getters y setters

    public MonthlyFeeDTO(Month month, boolean paid) {
        this.month = month;
        this.paid = paid;
    }

}
