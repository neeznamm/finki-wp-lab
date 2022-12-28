package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.interfaces.IStudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) { //constructor based dependency injection
        this.studentRepository = studentRepository;
    }

    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    public List<Student> searchByNameOrSurname(String name, String surname) {
        return studentRepository.findByNameOrSurname(name, surname);
    }

    public void save(String username, String password, String name, String surname) {
        Student s = new Student(username, password, name, surname);
        studentRepository.findAll().removeIf(r->r.getUsername().equals(username));
        studentRepository.save(s);
    }
}
