package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exam",
       indexes = {
         @Index(name = "idx_exam_creator", columnList = "created_by"),
         @Index(name = "idx_exam_status", columnList = "status"),
         @Index(name = "idx_exam_time", columnList = "start_at,end_at")
       })
public class Exam {

  public enum Status { DRAFT, UPCOMING, ONGOING, COMPLETED, CANCELLED, ARCHIVED }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "exam_id")
  private Long id;

  @Column(name = "exam_name", nullable = false, length = 200)
  private String name;

  @Column(name = "subject", length = 100)
  private String subject;

  @Column(name = "start_at", nullable = false)
  private LocalDateTime startAt;

  @Column(name = "end_at", nullable = false)
  private LocalDateTime endAt;

  @Column(name = "duration_minutes", nullable = false)
  private Integer durationMinutes;

  @Column(name = "total_marks", nullable = false)
  private Integer totalMarks;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 16)
  private Status status = Status.DRAFT;

  @Column(name = "is_published", nullable = false)
  private boolean published = false;

  @Column(name = "publish_at")
  private LocalDateTime publishAt;

  @Column(name = "created_at", nullable = false,
          columnDefinition = "datetime default current_timestamp")
  private LocalDateTime createdAt = LocalDateTime.now();

  // FK to teachers(teacher_id). If there is a Teacher entity mapped to table "teachers", use @ManyToOne.
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by", nullable = false,
              foreignKey = @ForeignKey(name = "fk_exam_teacher"))
  private Teacher createdBy;

  @Column(name = "file_path", length = 500)
  private String filePath;

  // getters and setters omitted for brevity
}
