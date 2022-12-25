package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="grade_id")
    private Long gradeId;
    @Column
    private Character grade;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    @Transient
    @OneToMany(mappedBy = "grade")
    private List<StudentEnrollment> enrollmentsGraded;

    public Grade(Character grade, LocalDateTime timestamp) {
        this.grade = grade;
        this.timestamp = timestamp;
    }
}
