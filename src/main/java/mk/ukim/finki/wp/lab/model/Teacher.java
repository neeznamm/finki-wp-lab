package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private Long teacherId;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfEmployment;
    @Transient
    private List<Course> coursesTaught;

    public Teacher(String name, String surname, List<Course> coursesTaught) {
        this.name = name;
        this.surname = surname;
        this.dateOfEmployment = LocalDate.now();
        this.coursesTaught = coursesTaught;
    }
}
