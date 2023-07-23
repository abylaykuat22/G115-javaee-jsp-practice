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
}
