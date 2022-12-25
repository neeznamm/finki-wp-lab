package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.exception.NameConflictException;
import mk.ukim.finki.wp.lab.exception.NotFoundException;
import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.repository.jpa.*;
import mk.ukim.finki.wp.lab.service.interfaces.ICourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService implements ICourseService {
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;


    public CourseService(CourseRepository courseRepository,
                         StudentEnrollmentRepository studentEnrollmentRepository,
                         StudentRepository studentRepository,
                         TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findCourseById(Long courseId) {
       return courseRepository.findByCourseId(courseId).orElseThrow(
               () -> new NotFoundException("Course with provided identifier does not exist")
       );
    }

    public List<StudentEnrollment> listStudentsByCourse(Long courseId) {
        Course c = courseRepository.findByCourseId(courseId).orElseThrow(
                ()->new NotFoundException("Course with provided identifier does not exist")
        );
        List<StudentEnrollment> matchedPairs = studentEnrollmentRepository.findByCourse(c);
        return new ArrayList<>(matchedPairs);
    }

    public void addStudentInCourse(String username, Long courseId) {
        Student s = studentRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("Student with provided username does not exist")
        );
        Course c = courseRepository.findByCourseId(courseId).orElseThrow(
                () -> new NotFoundException("Course with provided identifier does not exist")
        );
        Grade g = new Grade('N', LocalDateTime.now());
        gradeRepository.save(g);
        StudentEnrollment enrollment = new StudentEnrollment(s,c,g);
        studentEnrollmentRepository.save(enrollment);
    }

    public void saveCourse(String name, String description, Long teacherId, Boolean edit) {
        Teacher teacher = teacherRepository.findByTeacherId(teacherId).orElseThrow(
                () -> new NotFoundException("Teacher with provided identifier does not exist"));

        if (edit) {
            Course courseToEdit = courseRepository.findByName(name).orElseThrow(
                    ()->new NotFoundException("Course with provided name does not exist")
            );
            courseToEdit.setDescription(description);
            courseToEdit.setTeacher(teacher);
            courseRepository.save(courseToEdit);
        }
        else {
            if (courseRepository.findByName(name).isPresent()) throw new NameConflictException("Course with " +
                    "provided name already exists");
            courseRepository.save(new Course(name,description,teacher));
        };
    }

    public void deleteCourse(Long id) {
        courseRepository.findByCourseId(id).orElseThrow(
                () -> new NotFoundException("Course with provided identifier does not exist")
        );
        courseRepository.deleteByCourseId(id);
    }
}
