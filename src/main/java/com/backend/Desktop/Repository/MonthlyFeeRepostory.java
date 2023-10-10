package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.MonthlyFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyFeeRepostory extends JpaRepository<MonthlyFee, Integer> {
}
