package com.backend.Desktop.Repository;

import com.backend.Desktop.DTO.MonthlyFeeDTO;
import com.backend.Desktop.DTO.StudentDTO;
import com.backend.Desktop.Entity.MonthlyFee;
import com.backend.Desktop.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomStudentRepository {

    @PersistenceContext
    private EntityManager em;

    public List<StudentDTO> findStudentsWithUnpaidFees() {
        String jpql = "SELECT DISTINCT s, mf FROM Student s " +
                "JOIN s.fees f " +
                "JOIN f.monthlyFees mf " +
                "WHERE mf.paid = false";

        Query query = em.createQuery(jpql);

        List<StudentDTO> studentDTOs = new ArrayList<>();

        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            Student student = (Student) result[0];
            MonthlyFee monthlyFee = (MonthlyFee) result[1];

            List<MonthlyFeeDTO> unpaidMonthlyFees = new ArrayList<>();
            unpaidMonthlyFees.add(new MonthlyFeeDTO(monthlyFee.getMonth(), false)); // Add unpaid fee

            StudentDTO studentDTO = new StudentDTO(student.getFirstname(), student.getLastname(), student.getFile_number(), unpaidMonthlyFees);
            studentDTOs.add(studentDTO);
        }

        return studentDTOs;
    }
}
