package com.backend.Desktop.DTO;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {

    String firstname;
    String lastname;
    String file_number;

    List<MonthlyFeeDTO> unpaidMonthlyFees;

    public StudentDTO(String firstname, String lastname, String file_number, List<MonthlyFeeDTO> unpaidMonthlyFees) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.file_number = file_number;
        this.unpaidMonthlyFees = unpaidMonthlyFees;
    }

}
