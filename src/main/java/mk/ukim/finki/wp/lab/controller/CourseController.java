package mk.ukim.finki.wp.lab.controller;

import mk.ukim.finki.wp.lab.exception.NameConflictException;
import mk.ukim.finki.wp.lab.exception.NotFoundException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.StudentEnrollment;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GradeService gradeService;
    private final StudentService studentService;

    public CourseController(CourseService courseService, TeacherService teacherService, GradeService gradeService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("error",error);
        return "listCourses";
    }

    @RequestMapping(value = "/manageStudents", method = RequestMethod.GET)
    public String getStudentsPage(@RequestParam Long courseChoice, Model model, HttpSession session) {
        session.setAttribute("courseChoice", courseChoice);
        List<Student> students = studentService.listAll();
        model.addAttribute("students", students);
        return "listStudents";
    }

    @RequestMapping(value = "/enrollmentsForCourse", method = RequestMethod.GET)
    public String getEnrollmentsForCourse(@RequestParam String username, Model model, @SessionAttribute Long courseChoice) {
        Course course;
        try {
            course = courseService.findCourseById(courseChoice);
        } catch (NotFoundException e) {
            return "redirect:/courses?error="+e.getMessage();
        }

        String error = null;
        try {
            courseService.addStudentInCourse(username, courseChoice);
        } catch (NotFoundException e) {
            return "redirect:/courses?error="+e.getMessage();
        }
        List<StudentEnrollment> studentsInCourse = courseService.listStudentsByCourse(courseChoice);
        model.addAttribute("courseEnrollments", studentsInCourse);
        model.addAttribute("course", course);
        return "studentsInCourse";
    }

    @RequestMapping(value = "createStudent", method = RequestMethod.POST)
    public String saveStudent(@RequestParam String username, @RequestParam String password,
                              @RequestParam String name, @RequestParam String surname, Model model) {
        studentService.save(username,password,name,surname);
        model.addAttribute("students", studentService.listAll());
        return "listStudents";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long teacherId,
                             @RequestParam Boolean edit) {
        String error = null;
        try {
            courseService.saveCourse(name, description, teacherId, edit);
        } catch (NotFoundException | NameConflictException e) {
            error = e.getMessage();
        }
        if (error != null) return "redirect:/courses?error="+error;
        return "redirect:/courses";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable Long id) {
        String error = null;
        try{
            courseService.deleteCourse(id);
        } catch (NotFoundException e) {
            error = e.getMessage();
        }
        if (error != null) return "redirect:/courses?error="+error;
        return "redirect:/courses";
    }

    @RequestMapping(value = "/edit-form/{id}", method = RequestMethod.GET)
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        Course courseToEdit = null;
        String error = null;
        try {
            courseToEdit = courseService.findCourseById(id);
        } catch (NotFoundException e) {
            error = e.getMessage();
        }
        model.addAttribute("courseToEdit", courseToEdit);
        model.addAttribute("teachers", teacherService.findAll());
        if (error != null) return "redirect:/courses?error="+error;
        return "add-course";
    }

    @RequestMapping(value = "/add-form", method = RequestMethod.GET)
    public String getAddCoursePage(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "add-course";
    }

    @RequestMapping(value="/updateGrade", method = RequestMethod.POST)
    public String updateGrade(@RequestParam Long gradeId, @RequestParam Character newGrade,
                            @RequestParam("newTimestamp") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime newTimestamp) {
        try {
            gradeService.updateGrade(gradeId, newGrade, newTimestamp);
        } catch (NotFoundException e) {
            return "redirect:/courses?error="+e.getMessage();
        }
        return "gradeUpdateSuccess";
    }
}