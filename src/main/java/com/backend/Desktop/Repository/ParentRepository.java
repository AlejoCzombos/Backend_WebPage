package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository  extends JpaRepository<Parent, Integer> {
}
