package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Task;

public class DBUtil {

  private static Connection connection;

  static {
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/G115-first-db",
          "postgres",
          "postgres"
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static List<Task> getTasks() {
    List<Task> tasks = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "select * from tasks order by id");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getLong("id"));
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        task.setDeadlineDate(resultSet.getString("deadline_date"));
        tasks.add(task);
      }
      statement.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return tasks;
  }

  public static Task getTaskById(Long id) {
    Task task = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
          "select * from tasks where id=?");
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        task = new Task();
        task.setId(id);
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        task.setDeadlineDate(resultSet.getString("deadline_date"));
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
    return task;
  }

  public static void addTask(String name, String description, String deadlineDate) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "insert into tasks(name, deadline_date, description) "
              + "values (?, ?, ?)");
      statement.setString(1, name);
      statement.setString(2, deadlineDate);
      statement.setString(3, description);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void editTask(Task task) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "update tasks "
              + "set name = ?, description = ?, deadline_date = ? "
              + "where id = ?");
      statement.setString(1, task.getName());
      statement.setString(2, task.getDescription());
      statement.setString(3, task.getDeadlineDate());
      statement.setLong(4, task.getId());
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void deleteTaskById(Long id) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "delete from tasks where id=?");
      statement.setLong(1, id);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
