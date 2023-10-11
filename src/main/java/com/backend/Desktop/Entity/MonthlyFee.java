package com.backend.Desktop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "monthly_fee")
public class MonthlyFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fee_id")
    private Fee fee;

    private Month month;
    private Boolean paid = false;

    public MonthlyFee(Fee fee, Month month, Boolean paid) {
        this.fee = fee;
        this.month = month;
        this.paid = paid;
    }
}
