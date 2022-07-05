package com.example.arppl4_sprin_demo.controller;

import com.example.arppl4_sprin_demo.model.Student;
import com.example.arppl4_sprin_demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/api/student")
@RestController
public class StudentController {
    // REST == HTTP == ZAPYTANIA == REQESTS
    // WYROZNIAMY METODY HTTP:
    // - GET(pobierz)
    // - POST(wtaw, edytuj)
    // - PATCH(edytuj fragment)
    // - DELETE (usuń)
    // -PUT (wstaw lub zastąp)
    private  StudentRepository studentRepository; // AUTOWIRE

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

        @GetMapping
        public List<Student> studenList() {
            log.info("Wywołano metodę StudentList");
            List<Student> studentList = studentRepository.findAll();

            return studentList;
        }
        //http://localhost:8080/api/student/5
        @GetMapping("/{identifier}")
        public Student findStudent(@PathVariable(name = "identifier") Long studentId) {
            log.info("Wywołano metodę findStudent" + studentId);

            Optional<Student> studentOpional = studentRepository.findById(studentId);
            if (studentOpional.isPresent()) {
                Student student = studentOpional.get();
                return student;
            }
            throw  new EntityNotFoundException("Nie znaleziono studenta o id:" + studentId);
        }
    //http://localhost:8080/api/student/5 ?studentId=5
    //Requast Param - parametr zapytania
    @GetMapping("/find")
    public Student findStudenByIdt(@RequestParam(name = "studentId") Long studentId) {
        log.info("Wywołano metodę findStudentById" + studentId);

        Optional<Student> studentOpional = studentRepository.findById(studentId);
        if (studentOpional.isPresent()) {
            Student student = studentOpional.get();
            return student;
        }
        throw  new EntityNotFoundException("Nie znaleziono studenta o id:" + studentId);
    }
    @DeleteMapping("/{identifier}")
    public void deleteStudent(@PathVariable(name = "identifier") Long studentId) {
        log.info("Wywołano metodę deleteStudent" + studentId);

        studentRepository.deleteById(studentId);
    }
    //RequestBody - ciało zapytania
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student){
        log.info("Wywołano metode createStudent:"+ student);

        studentRepository.save(student);

    }
}
// Controller -> zwraca dane
// RestController -> zwraca HTML

//[Controller] -> [->] -> [Repository]
