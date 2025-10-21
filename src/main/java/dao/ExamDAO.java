package dao;

import model.Examcard;
import model.ExamInput;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {

    private Connection getConnection() throws SQLException {
        return DBUtil.getConnection();
    }

    public int insertExam(ExamInput input, String filePath, String fileType, String fileName, String status) throws SQLException {
        String sql = "INSERT INTO exams (exam_name, subject, exam_date, start_time, end_time, duration_minutes, total_marks, created_by, file_path, file_type, original_name, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, input.getExamName());
            stmt.setString(2, input.getSubject());
            stmt.setDate(3, Date.valueOf(input.getExamDate()));
            stmt.setTime(4, Time.valueOf(input.getStartTime()));
            stmt.setTime(5, Time.valueOf(input.getEndTime()));
            stmt.setInt(6, input.getDurationMinutes());
            stmt.setInt(7, input.getTotalMarks());
            stmt.setInt(8, input.getCreatedByTeacherId());
            stmt.setString(9, filePath);
            stmt.setString(10, fileType);
            stmt.setString(11, fileName);
            stmt.setString(12, status);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) return -1;

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                return generatedKeys.next() ? generatedKeys.getInt(1) : -1;
            }
        }
    }

    public List<Examcard> getAllExams() throws SQLException {
        String sql = "SELECT * FROM exams";
        List<Examcard> exams = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Examcard e = new Examcard();
                e.setExamId(rs.getInt("exam_id"));
                e.setExamName(rs.getString("exam_name"));
                e.setSubject(rs.getString("subject"));
                e.setExamDate(rs.getDate("exam_date").toLocalDate());
                e.setStartTime(rs.getTime("start_time").toLocalTime());
                e.setEndTime(rs.getTime("end_time").toLocalTime());
                e.setDurationMinutes(rs.getInt("duration_minutes"));
                e.setTotalMarks(rs.getInt("total_marks"));
                e.setStatus(rs.getString("status"));
                e.setContentType(rs.getString("content_type"));
                e.setCreatedAt(rs.getTimestamp("created_at"));
                e.setCreatedBy(rs.getInt("created_by"));
                e.setFilePath(rs.getString("file_path"));
                e.setOriginalName(rs.getString("original_name"));
                e.setIsPublished(rs.getBoolean("is_published"));
                e.setFileType(rs.getString("file_type"));
                exams.add(e);
            }
        }
        return exams;
    }

    public Examcard getExamById(int examId) throws SQLException {
        String sql = "SELECT * FROM exams WHERE exam_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, examId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Examcard e = new Examcard();
                    e.setExamId(rs.getInt("exam_id"));
                    e.setExamName(rs.getString("exam_name"));
                    e.setSubject(rs.getString("subject"));
                    e.setExamDate(rs.getDate("exam_date").toLocalDate());
                    e.setStartTime(rs.getTime("start_time").toLocalTime());
                    e.setEndTime(rs.getTime("end_time").toLocalTime());
                    e.setDurationMinutes(rs.getInt("duration_minutes"));
                    e.setTotalMarks(rs.getInt("total_marks"));
                    e.setStatus(rs.getString("status"));
                    e.setContentType(rs.getString("content_type"));
                    e.setCreatedAt(rs.getTimestamp("created_at"));
                    e.setCreatedBy(rs.getInt("created_by"));
                    e.setFilePath(rs.getString("file_path"));
                    e.setOriginalName(rs.getString("original_name"));
                    e.setIsPublished(rs.getBoolean("is_published"));
                    e.setFileType(rs.getString("file_type"));
                    return e;
                }
            }
        }
        return null;
    }

    public boolean updateExam(int examId, ExamInput input) throws SQLException {
        String sql = "UPDATE exams SET exam_name=?, subject=?, exam_date=?, start_time=?, end_time=?, duration_minutes=?, total_marks=? WHERE exam_id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, input.getExamName());
            stmt.setString(2, input.getSubject());
            stmt.setDate(3, Date.valueOf(input.getExamDate()));
            stmt.setTime(4, Time.valueOf(input.getStartTime()));
            stmt.setTime(5, Time.valueOf(input.getEndTime()));
            stmt.setInt(6, input.getDurationMinutes());
            stmt.setInt(7, input.getTotalMarks());
            stmt.setInt(8, examId);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteExam(int examId) throws SQLException {
        String sql = "DELETE FROM exams WHERE exam_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, examId);
            return stmt.executeUpdate() > 0;
        }
    }


    public void setExamPublished(int examId) throws SQLException {
        String sql = "UPDATE exams SET is_published=1, status='PUBLISHED' WHERE exam_id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ps.executeUpdate();
        }
    }

    public List<Examcard> getPublishedExams() throws SQLException {
        String sql = "SELECT * FROM exams WHERE is_published = 1";
        List<Examcard> exams = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Examcard e = new Examcard();
                e.setExamId(rs.getInt("exam_id"));
                e.setExamName(rs.getString("exam_name"));
                e.setSubject(rs.getString("subject"));
                e.setExamDate(rs.getDate("exam_date").toLocalDate());
                e.setStartTime(rs.getTime("start_time").toLocalTime());
                e.setEndTime(rs.getTime("end_time").toLocalTime());
                e.setDurationMinutes(rs.getInt("duration_minutes"));
                e.setTotalMarks(rs.getInt("total_marks"));
                e.setStatus(rs.getString("status"));
                e.setContentType(rs.getString("content_type"));
                e.setCreatedAt(rs.getTimestamp("created_at"));
                e.setCreatedBy(rs.getInt("created_by"));
                e.setFilePath(rs.getString("file_path"));
                e.setOriginalName(rs.getString("original_name"));
                e.setIsPublished(rs.getBoolean("is_published"));
                e.setFileType(rs.getString("file_type"));
                exams.add(e);
            }
        }
        return exams;
    }

}
