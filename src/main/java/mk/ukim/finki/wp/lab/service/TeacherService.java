package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.exception.NotFoundException;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.interfaces.ITeacherService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeacherService implements ITeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Teacher with provided identifier does not exist"
        ));
    }
}
