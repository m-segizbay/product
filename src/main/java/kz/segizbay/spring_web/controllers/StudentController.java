package kz.segizbay.spring_web.controllers;

import kz.segizbay.spring_web.exceptions.ResourceNotFoundException;
import kz.segizbay.spring_web.model.Student;
import kz.segizbay.spring_web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/delete/{id}")
    public void deleteById(@PathVariable Long id){
        studentService.deleteById(id);
    }

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.finaAll();
    }

    @GetMapping("/students/change_score")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta){
        studentService.changeScoreById(studentId, delta);
    }

    @GetMapping("/students/score_between")
    public List<Student> demo(@RequestParam(defaultValue = "0") Integer min,
                              @RequestParam(defaultValue = "100") Integer max){
        return studentService.findByScoreBetween(min, max);
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.findtById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found"));
    }

    @PostMapping("/students")
    public Student save(@RequestBody Student student){
        return studentService.save(student);
    }
}
