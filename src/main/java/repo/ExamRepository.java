package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import model.Exam;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    // Find all published exams (for student dashboard)
    List<Exam> findByPublishedTrue();

    // Find all exams created by a specific teacher
    List<Exam> findByCreatedByTeacherId(Long teacherId);
}
