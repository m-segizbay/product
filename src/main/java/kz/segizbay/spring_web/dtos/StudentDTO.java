package kz.segizbay.spring_web.dtos;

import kz.segizbay.spring_web.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private Integer score;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.score = student.getScore();
    }
}
