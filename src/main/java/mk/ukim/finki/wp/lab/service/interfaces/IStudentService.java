package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String name,String surname);
    void save(String username, String password, String name, String surname);
}
