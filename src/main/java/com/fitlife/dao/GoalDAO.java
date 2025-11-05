package com.fitlife.dao;

import com.fitlife.Goal; // Import our Goal model
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GoalDAO {

  
    public boolean addGoal(Goal goal) {
        String sql = "INSERT INTO Goals (user_id, goal_type, target_value, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, goal.getUserId());
            pstmt.setString(2, goal.getGoalType());
            pstmt.setDouble(3, goal.getTargetValue());
            pstmt.setString(4, goal.getStartDate());
            pstmt.setString(5, goal.getEndDate());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding goal: " + e.getMessage());
            return false;
        }
    }

 
    public List<Goal> getGoalsByUserId(int userId) {
        List<Goal> goals = new ArrayList<>();
        String sql = "SELECT * FROM Goals WHERE user_id = ? ORDER BY end_date DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Goal goal = new Goal();
                    goal.setGoalId(rs.getInt("goal_id"));
                    goal.setUserId(rs.getInt("user_id"));
                    goal.setGoalType(rs.getString("goal_type"));
                    goal.setTargetValue(rs.getDouble("target_value"));
                    goal.setStartDate(rs.getString("start_date"));
                    goal.setEndDate(rs.getString("end_date"));
                    goals.add(goal);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting goals: " + e.getMessage());
        }
        return goals;
    }

   
    public boolean deleteGoal(int goalId) {
        String sql = "DELETE FROM Goals WHERE goal_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, goalId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting goal: " + e.getMessage());
            return false;
        }
    }
}