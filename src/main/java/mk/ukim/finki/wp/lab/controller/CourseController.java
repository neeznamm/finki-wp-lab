package mk.ukim.finki.wp.lab.controller;

import mk.ukim.finki.wp.lab.exception.NotFoundException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseServiceImpl;
import mk.ukim.finki.wp.lab.service.TeacherServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

    private final CourseServiceImpl courseService;
    private final TeacherServiceImpl teacherService;

    public CourseController(CourseServiceImpl courseService, TeacherServiceImpl teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("error",error);
        return "listCourses";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long teacherId,
                             @RequestParam Boolean edit) {
        String error = courseService.saveCourse(name, description, teacherId, edit);
        if (error != null) return "redirect:/courses?error="+error;
        return "redirect:/courses";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable Long id) {
        String error = courseService.deleteCourse(id);
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
}
