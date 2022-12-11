package mk.ukim.finki.wp.lab.repository;

import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Repository
public class StudentRepository {

    private final List<Student> STUDENTS = new ArrayList<>(Arrays.asList(
            new Student("trajkottt", "12345Ab@", "Trajko", "Trajkov"),
            new Student("jankojjj", "12345Ab@", "Janko", "Jankov"),
            new Student("cvetkoccc", "12345Ab@", "Cvetko", "Cvetkov"),
            new Student("milenkommm", "12345Ab@", "Milenko", "Milenkov"),
            new Student("stavresss", "12345Ab@", "Stavre", "Stavrev"))
    );

    public List<Student> findAllStudents() {
        return this.STUDENTS;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        List<Student> matchingStudents = new ArrayList<>();

        for(Student s : this.STUDENTS) {
            if(s.getName().contains(text) || s.getSurname().contains(text)) matchingStudents.add(s);
        }

        return matchingStudents;
    }

}
