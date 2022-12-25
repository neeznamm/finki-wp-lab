package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.StudentEnrollment;

import java.util.List;

public interface ICourseService {
    List<Course> findAll();
    Course findCourseById(Long courseId);
    List<StudentEnrollment> listStudentsByCourse(Long courseId);
    void addStudentInCourse(String username, Long courseId);

    void saveCourse(String name, String description, Long teacherId, Boolean edit);

    void deleteCourse(Long id);
}
