package kz.segizbay.spring_web.controllers;

import kz.segizbay.spring_web.exceptions.ResourceNotFoundException;
import kz.segizbay.spring_web.model.Student;
import kz.segizbay.spring_web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        studentService.deleteById(id);
    }

    @GetMapping
    public Page<Student> findAll(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_score", required = false) Integer minScore,
            @RequestParam(name = "max_score", required = false) Integer maxScore,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if (page<1){
            page = 1;
        }
        return studentService.finaAll(minScore, maxScore, namePart, page);
    }

    @GetMapping("/change_score")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta){
        studentService.changeScoreById(studentId, delta);
    }

    @GetMapping("/score_between")
    public List<Student> demo(@RequestParam(defaultValue = "0") Integer min,
                              @RequestParam(defaultValue = "100") Integer max){
        return studentService.findByScoreBetween(min, max);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id){
        return studentService.findtById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found"));
    }

    @PostMapping
    public Student save(@RequestBody Student student){
        return studentService.save(student);
    }

    @PutMapping
    public Student update(@RequestBody Student student){
        return studentService.save(student);
    }
}
