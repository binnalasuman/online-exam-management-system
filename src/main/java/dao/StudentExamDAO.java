package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ExamInput;
import model.Examcard;

public class StudentExamDAO {
    // This method signature matches the servlet call (5 parameters)
    public int insertExam(ExamInput in, String filePath, String contentType, String originalName, String status)
            throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO exams (exam_name, subject, exam_date, start_time, end_time, duration_minutes, " +
                     "total_marks, status, is_published, created_by, file_path, file_type, original_name) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, in.getExamName());
            ps.setString(2, in.getSubject());
            ps.setDate(3, java.sql.Date.valueOf(in.getExamDate()));
            ps.setTime(4, java.sql.Time.valueOf(in.getStartTime()));
            ps.setTime(5, java.sql.Time.valueOf(in.getEndTime()));
            ps.setInt(6, in.getDurationMinutes());
            ps.setInt(7, in.getTotalMarks());
            ps.setString(8, status);
            ps.setInt(9, 1); // is_published
            ps.setInt(10, in.getCreatedByTeacherId());
            ps.setString(11, filePath);
            ps.setString(12, contentType);
            ps.setString(13, originalName);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to insert exam: no generated key");
    }

    public List<Examcard> getAllExams() throws SQLException, ClassNotFoundException {
        String sql = "SELECT exam_id, exam_name, subject, exam_date, start_time, end_time, total_marks, status FROM exams ORDER BY exam_date DESC";
        List<Examcard> exams = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Examcard exam = new Examcard();
                exam.setExamId(rs.getInt("exam_id"));
                exam.setName(rs.getString("exam_name"));
                exam.setSubject(rs.getString("subject"));
                exam.setExamDate(rs.getDate("exam_date").toLocalDate());
                exam.setStartTime(rs.getTime("start_time").toLocalTime());
                exam.setEndTime(rs.getTime("end_time").toLocalTime());
                exam.setTotalMarks(rs.getInt("total_marks"));
                exam.setStatus(rs.getString("status"));
                exams.add(exam);
            }
        }
        return exams;
    }

    // ... (any other required methods)
}
