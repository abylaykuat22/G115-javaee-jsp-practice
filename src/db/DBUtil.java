package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Performer;
import models.Task;
import models.User;

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
          "select t.id, t.name, t.description, t.deadline_date, t.category_id, t.performer_id, " +
                  "c.name as category_name, c.status, p.name as performer_name, p.position " +
                  "from tasks t " +
                  "inner join categories c on t.category_id = c.id " +
                  "inner join performers p on t.performer_id = p.id " +
                  "order by id");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getLong("id"));
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        task.setDeadlineDate(resultSet.getString("deadline_date"));

        Category category = new Category();
        category.setId(resultSet.getLong("category_id"));
        category.setName(resultSet.getString("category_name"));
        category.setStatus(resultSet.getBoolean("status"));
        task.setCategory(category);

        Performer performer = new Performer();
        performer.setId(resultSet.getLong("performer_id"));
        performer.setName(resultSet.getString("performer_name"));
        performer.setPosition(resultSet.getString("position"));
        task.setPerformer(performer);

        tasks.add(task);
      }
      statement.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return tasks;
  }

  public static List<Category> getCategories() {
    List<Category> categories = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
              "select * from categories");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        category.setStatus(resultSet.getBoolean("status"));
        categories.add(category);
      }
      statement.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return categories;
  }

  public static List<Performer> getPerformers() {
    List<Performer> performers = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
              "select * from performers");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Performer performer = new Performer();
        performer.setId(resultSet.getLong("id"));
        performer.setName(resultSet.getString("name"));
        performer.setPosition(resultSet.getString("position"));
        performers.add(performer);
      }
      statement.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return performers;
  }

  public static Task getTaskById(Long id) {
    Task task = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
          "select t.id, t.category_id, t.performer_id, t.name, t.description, t.deadline_date, " +
                  "c.name as category_name, p.name as performer_name " +
                  "from tasks t " +
                  "inner join categories c on t.category_id = c.id " +
                  "inner join performers p on t.performer_id = p.id " +
                  "where t.id=?");
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        task = new Task();
        task.setId(id);
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        task.setDeadlineDate(resultSet.getString("deadline_date"));

        Category category = new Category();
        category.setId(resultSet.getLong("category_id"));
        category.setName(resultSet.getString("category_name"));
        task.setCategory(category);

        Performer performer = new Performer();
        performer.setId(resultSet.getLong("performer_id"));
        performer.setName(resultSet.getString("performer_name"));
        task.setPerformer(performer);
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
    return task;
  }

  public static Category getCategoryById(Long id) {
    Category category = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
              "select * from categories where id = ?");
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        category.setStatus(resultSet.getBoolean("status"));
      }
      statement.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return category;
  }

  public static Performer getPerformerById(Long id) {
    Performer performer = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
              "select * from performers where id = ?");
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        performer = new Performer();
        performer.setId(resultSet.getLong("id"));
        performer.setName(resultSet.getString("name"));
        performer.setPosition(resultSet.getString("position"));
      }
      statement.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return performer;
  }

  public static void addTask(String name, String description, String deadlineDate,
                             Long categoryId, Long performerId) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "insert into tasks(name, deadline_date, description, category_id, performer_id) "
              + "values (?, ?, ?, ?, ?)");
      statement.setString(1, name);
      statement.setString(2, deadlineDate);
      statement.setString(3, description);
      statement.setLong(4, categoryId);
      statement.setLong(5, performerId);
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

  public static User getUserByEmailAndPass(String email, String password) {
    User user = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
              "select * from users where email = ? and password = ?");
      statement.setString(1, email);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(resultSet.getString("full_name"));
        user.setAge(resultSet.getInt("age"));
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }
}
