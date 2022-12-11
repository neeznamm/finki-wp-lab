package mk.ukim.finki.wp.lab.repository;

import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Repository
public class TeacherRepository {

    public final List<Teacher> TEACHERS = new ArrayList<>(Arrays.asList(
            new Teacher(1L, "Сашо", "Граматиков"),
            new Teacher(2L, "Ристе", "Стојанов"),
            new Teacher(3L, "Марјан", "Гушев"),
            new Teacher(4L, "Анастас", "Мишев"),
            new Teacher(5L, "Петре", "Ламески")
    ));

    public List<Teacher> findAll() {
        return this.TEACHERS;
    }

    public Teacher findById(Long id) {
        for(Teacher t : TEACHERS) {
            if(t.getId().equals(id)) return t;
        }
        return null;
    }
}
