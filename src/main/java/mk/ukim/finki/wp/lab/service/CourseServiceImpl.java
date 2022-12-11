package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.exception.AlreadyExistsException;
import mk.ukim.finki.wp.lab.exception.NameConflictException;
import mk.ukim.finki.wp.lab.exception.NotFoundException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService) { //constructor based dependency injection
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course findCourseById(Long courseId) throws NotFoundException{
        try {
            return courseRepository.findById(courseId);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public String addStudentInCourse(String username, Long courseId) {
        for(Student s : studentService.listAll()) {
            if(s.getUsername().equals(username)) {
                try {
                    return courseRepository.addStudentToCourse(s, courseId);
                } catch (AlreadyExistsException e) {
                    return e.getMessage();
                }
            }
        }
        return null;
    }

    @Override
    public String saveCourse(String name, String description, Long teacherId, Boolean edit) {
        try {
            courseRepository.saveCourse(name, description, teacherId, edit);
        } catch (NameConflictException e) {
            return e.getMessage();
        }
        return null;
    }

    @Override
    public String deleteCourse(Long id) {
        try {
            courseRepository.deleteCourse(id);
        } catch (NotFoundException e) {
            return e.getMessage();
        }
        return null;
    }
}
