package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) { //constructor based dependency injection
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty() || name==null || name.isEmpty() || surname==null || surname.isEmpty()) return null;
        Student s = new Student(username, password, name, surname);
        studentRepository.findAllStudents().removeIf(r->r.getUsername().equals(username));
        studentRepository.findAllStudents().add(s);

        return s;
    }
}
