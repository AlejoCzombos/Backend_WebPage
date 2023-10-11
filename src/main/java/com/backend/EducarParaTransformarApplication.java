package com.backend;

import com.backend.Desktop.Entity.Fee;
import com.backend.Desktop.Entity.MonthlyFee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class EducarParaTransformarApplication {

	public static void main(String[] args) {

		SpringApplication.run(EducarParaTransformarApplication.class, args);

	}

}
