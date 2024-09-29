package kz.segizbay.spring_web.services;

import jakarta.transaction.Transactional;
import kz.segizbay.spring_web.exceptions.ResourceNotFoundException;
import kz.segizbay.spring_web.model.Student;
import kz.segizbay.spring_web.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentsRepository studentsRepository;

    @Autowired
    public StudentService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> finaAll() {
        return studentsRepository.findAll();
    }

    public void deleteById(long id) {
        studentsRepository.deleteById(id);
    }

    public Optional<Student> findtById(long id) {
        return studentsRepository.findById(id);
    }

    @Transactional
    public void changeScoreById(Long studentId, Integer delta) {
        Student student = studentsRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Unable to change students score, student not found by id " + studentId));
        student.setScore(student.getScore() + delta);
        studentsRepository.save(student);
    }

    public List<Student> findByScoreBetween(Integer min, Integer max) {
        return studentsRepository.findAllByScoreBetween(min, max);
    }

    public Student save(Student student) {
        return studentsRepository.save(student);
    }
}
