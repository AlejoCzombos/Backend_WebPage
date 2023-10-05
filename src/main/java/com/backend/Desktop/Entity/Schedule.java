package com.backend.Desktop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;

    private LocalTime startingTime;
    private LocalTime endTime;

    private DayOfWeek day;

}
