package kz.segizbay.spring_web.repositories;

import kz.segizbay.spring_web.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    @Query("select s from Student s where s.score between ?1 and ?2")
    List<Student> findAllByScoreBetween(Integer min, Integer max);

    @Query("select s.name from Student s where :name = s.name")
    Optional<Student> findByName(String name);

    @Query("select s from Student s where s.score<20")
    List<Student> finrAllLowRating();

    @Query(value = "select score from students where :name = name", nativeQuery = true)
    Optional<Student> getScoreByNameWithNativeQuery(String name);
}
