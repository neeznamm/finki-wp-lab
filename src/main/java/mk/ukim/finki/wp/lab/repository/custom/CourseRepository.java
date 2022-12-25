//package mk.ukim.finki.wp.lab.repository.custom;
//
//import lombok.NoArgsConstructor;
//import mk.ukim.finki.wp.lab.exception.AlreadyExistsException;
//import mk.ukim.finki.wp.lab.exception.NameConflictException;
//import mk.ukim.finki.wp.lab.exception.NotFoundException;
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Student;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@NoArgsConstructor
//@Repository
//public class CourseRepository {
//    private final StudentRepository studentRepository = new StudentRepository();
//    private final TeacherRepository teacherRepository = new TeacherRepository();
//
//    public final List<Course> COURSES = new ArrayList<>(Arrays.asList(
//            new Course("Веб програмирање", "Вовед во веб програмирањето.", studentRepository.findAllByNameOrSurname("a"), teacherRepository.findAll().get((int)(Math.random()*5))),
//            new Course( "Оперативни системи", "Вовед во оперативните системи.", studentRepository.findAllByNameOrSurname("b"), teacherRepository.findAll().get((int)(Math.random()*5))),
//            new Course( "Електронска и мобилна трговија", "Вовед во електронската и мобилна трговија.", studentRepository.findAllByNameOrSurname("c"), teacherRepository.findAll().get((int)(Math.random()*5))),
//            new Course( "Компјутерски мрежи", "Вовед во компјутерските мрежи.", studentRepository.findAllByNameOrSurname("d"), teacherRepository.findAll().get((int)(Math.random()*5))),
//            new Course( "Бази на податоци", "Вовед во базите на податоци.", studentRepository.findAllByNameOrSurname("e"), teacherRepository.findAll().get((int)(Math.random()*5))))
//    );
//
//    public List<Course> findAllCourses() {
//        return this.COURSES.stream().sorted().toList();
//    }
//
//    public Course findById(Long courseId) throws NotFoundException{
//        for(Course c : this.COURSES) {
//            if(c.getCourseId().equals(courseId)) return c;
//        }
//        throw new NotFoundException("Course with provided identifier does not exist");
//    }
//
//    public List<Student> findAllStudentsByCourse(Long courseId) {
//        for(Course c : this.COURSES) {
//            if(c.getCourseId().equals(courseId)) return c.getStudents();
//        }
//        return null;
//    }
//
//    public String addStudentToCourse(Student student, Long courseId) throws AlreadyExistsException{
//        Course course;
//        try {
//            course = findById(courseId);
//        } catch (NotFoundException e) {
//            return e.getMessage();
//        }
//
//        List<Student> updatedStudents = new ArrayList<>(course.getStudents());
//        for(Student s : updatedStudents) {
//            if(s.getUsername().equals(student.getUsername())) throw new AlreadyExistsException("Student to be added already exists");
//        }
//        updatedStudents.add(student);
//        course.setStudents(updatedStudents);
//        return null;
//    }
//
//    public void saveCourse(String name, String description, Long teacherId, Boolean edit) throws NameConflictException{
//        for (Course c : COURSES) {
//            if (c.getName().equals(name)) {
//                if(edit) {
//                    c.setDescription(description);
//                    c.setTeacher(teacherRepository.findById(teacherId));
//                    return;
//                }
//                throw new NameConflictException("Course with provided name already exists");
//            }
//        }
//        Course courseToAdd = new Course(name, description, new ArrayList<>(), teacherRepository.findById(teacherId));
//        COURSES.add(courseToAdd);
//    }
//
//    public void deleteCourse(Long id) throws NotFoundException{
//        for (Course c : COURSES) {
//            if (c.getCourseId().equals(id)) {
//                COURSES.remove(c);
//                return;
//            }
//        }
//        throw new NotFoundException("Course with provided identifier does not exist");
//    }
//
//}
