package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.exception.NotFoundException;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class GradeService {
    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public void updateGrade(Long gradeId, Character newGrade, LocalDateTime newTimestamp) {
        Grade gradeToUpdate = gradeRepository.findByGradeId(gradeId).orElseThrow(
                ()->new NotFoundException("Grade with specified id not found")
        );
        gradeToUpdate.setGrade(newGrade);
        gradeToUpdate.setTimestamp(newTimestamp);
        gradeRepository.save(gradeToUpdate);
    }
}
