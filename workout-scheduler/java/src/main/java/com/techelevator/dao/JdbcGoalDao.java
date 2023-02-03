package com.techelevator.dao;

import com.techelevator.model.profile.Goal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class JdbcGoalDao implements GoalDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcGoalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Goal> getAllGoalsById(Long userId) {
        List<Goal> goals = new ArrayList<>();
        String sqlQuery = "SELECT * FROM goal WHERE customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, userId);
        while (results.next()) {
            Goal goal = mapRowToGoal(results);
            goals.add(goal);
        }
        return goals;
    }

    @Override
    public boolean addNewGoal(Long userId, Goal goal) {
        boolean goalCreated = createNewGoal(goal);
        return goalCreated;
    }

    private boolean createNewGoal(Goal goal) {
        String name = goal.getName();
        Long customerId = goal.getCustomerId();
        int exerciseId = goal.getExerciseId();
        Date date = goal.getDate();
        double reps = goal.getReps();
        double weight = goal.getWeight(); //lbs
        double time = goal.getTime(); //min
        double distance = goal.getDistance(); //mi
        int days = goal.getDays();
        String misc = goal.getMisc();
        boolean isCompleted = goal.isCompleted();

        Integer goalId;
        String sqlQuery = "INSERT INTO goal " +
                "(goal_name, customer_id, exercise_id, goal_date, goal_reps, goal_weight_lbs, goal_time_min, goal_distance_mi, goal_days, goal_misc, is_completed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING goal_id;";
        try {
            goalId = jdbcTemplate.queryForObject(sqlQuery, Integer.class,
                    name, customerId, exerciseId, date, reps, weight, time, distance, days, misc, isCompleted);
        } catch (Exception e) {
            return false;
        }
        return (goalId != null);
    }

    @Override
    public boolean updateGoal(Goal goal) {
        int id = goal.getId();
        String name = goal.getName();
        Long customerId = goal.getCustomerId();
        int exerciseId = goal.getExerciseId();
        Date date = goal.getDate();
        double reps = goal.getReps();
        double weight = goal.getWeight(); //lbs
        double time = goal.getTime(); //min
        double distance = goal.getDistance(); //mi
        int days = goal.getDays();
        String misc = goal.getMisc();
        boolean isCompleted = goal.isCompleted();

        String sqlQuery = "UPDATE goal " +
                "SET goal_name = ?, exercise_id = ?, goal_date = ?, goal_reps = ?, goal_weight_lbs = ?, goal_time_min = ?, goal_distance_mi, goal_days = ?, goal_misc = ?, is_completed = ? " +
                "WHERE goal_id = ? AND customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, name, exerciseId, date, reps, weight, time, distance, days, misc, isCompleted, id, customerId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    Goal mapRowToGoal(SqlRowSet row) {
        Goal goal = new Goal();
        goal.setId(row.getInt("goal_id"));
        goal.setName(row.getString("goal_name"));
        goal.setCustomerId(row.getLong("customer_id"));
        goal.setExerciseId(row.getInt("exercise_id"));
        goal.setDate(row.getDate("goal_date"));
        goal.setReps(row.getDouble("goal_reps"));
        goal.setWeight(row.getDouble("goal_weight_lbs"));
        goal.setTime(row.getDouble("goal_time_min"));
        goal.setDistance(row.getDouble("goal_distance_mi"));
        goal.setDays(row.getInt("goal_days"));
        goal.setMisc(row.getString("goal_misc"));
        goal.setCompleted(row.getBoolean("is_completed"));
        return goal;
    }


}
