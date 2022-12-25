package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="CoursesList", value="/listCourses")
public class CoursesListServlet extends HttpServlet
{
    private final CourseService courseService;

    public CoursesListServlet(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        List<Course> courseList = courseService.findAll();

        out.println("""
                <html>
                <head>
                    <meta charset="utf-8">
                    <title>Welcome and Choose a Course</title>
                    <style type="text/css">
                        body {
                            width: 800px;
                            margin: auto;
                        }
                    </style>
                </head>
                <body>
                <header>
                    <h1>Courses List</h1>
                </header>
                <main>
                    <h2>Choose course:</h2>""");
        out.println("<form action=\"AddStudent\" method=\"post\">\n");
        for(int i=0;i<5;++i) {
            out.printf("<input type=\"radio\" name=\"courseChoice\" value=\"%d\"> %s<br/>\n", courseList.get(i).getCourseId(), courseList.get(i).getName());
        }
        out.println("""
                    <br/>
                    <input type='submit' value='Submit'/>
                </main>
                </body>
                </html>""");
    }
}
