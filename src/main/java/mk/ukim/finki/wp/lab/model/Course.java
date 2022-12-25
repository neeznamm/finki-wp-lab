package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor
@Entity
@Table(name="course")
public class Course implements Comparable<Course>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long courseId;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudentEnrollment> studentsEnrolled;

    public Course(String name, String description, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.type = new Random().nextInt() % 3 == 0 ? Type.WINTER : Type.SUMMER;
        studentsEnrolled = new ArrayList<>();
    }

    @Override
    public int compareTo(Course c) {
        return this.name.compareTo(c.getName());
    }
}
