package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findAll();
    Optional<Course> findByCourseId(Long courseId);
    void deleteByCourseId(Long courseId);
    Optional<Course> findByName(String name);
}
