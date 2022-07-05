package com.example.arppl4_sprin_demo.repository;

import com.example.arppl4_sprin_demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Bean -> ziarno
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
