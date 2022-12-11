package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface CourseService {
    List<Course> findAll(); //ne se bara
    Course findCourseById(Long courseId);
    List<Student> listStudentsByCourse(Long courseId);
    String addStudentInCourse(String username, Long courseId);

    String saveCourse(String name, String description, Long teacherId, Boolean edit);

    String deleteCourse(Long id);
}
