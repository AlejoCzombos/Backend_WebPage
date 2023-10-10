package com.backend.Desktop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fee")
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

    @JoinColumn(name = "parent_id")
    @ManyToOne
    private Parent parent;

    @OneToMany(mappedBy = "fee")
    private List<MonthlyFee> monthlyFees;

}
