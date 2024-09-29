package kz.segizbay.spring_web.repositories.specifications;

import kz.segizbay.spring_web.model.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<Student> scoreGraterOrEqualsThan(int score){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("score"), score));
    }

    public static Specification<Student> scoreLessOrEqualsThan(int score){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("score"), score);
    }

    public static Specification<Student> nameLike(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", name));
    }
}
