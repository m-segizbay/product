package kz.segizbay.spring_web.services;

import jakarta.transaction.Transactional;
import kz.segizbay.spring_web.exceptions.ResourceNotFoundException;
import kz.segizbay.spring_web.model.Student;
import kz.segizbay.spring_web.repositories.StudentsRepository;
import kz.segizbay.spring_web.repositories.specifications.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Student> finaAll(Integer minScore, Integer maxScore, String partName, Integer page) {
        Specification<Student> spec = Specification.where(null);
        if (minScore != null) {
            spec = spec.and(StudentSpecification.scoreGraterOrEqualsThan(minScore));
        }
        if (maxScore != null){
            spec = spec.and(StudentSpecification.scoreLessOrEqualsThan(maxScore));
        }
        if (partName != null){
            spec = spec.and(StudentSpecification.nameLike(partName));
        }
        return studentsRepository.findAll(spec, PageRequest.of(page-1, 5));
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
