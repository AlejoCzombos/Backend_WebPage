package com.backend.Desktop.DTO;

import com.backend.Desktop.Entity.MonthlyFee;
import com.backend.Desktop.Entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class FeeDTO {

    Student student;

    List<MonthlyFee> monthlyFees;

}
