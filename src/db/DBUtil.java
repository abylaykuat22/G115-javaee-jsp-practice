package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Blog;
import models.Category;
import models.Comment;
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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

  public static User getUserByEmail(String email) {
    User user = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
          "select * from users where email = ?");
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(email);
        user.setPassword(resultSet.getString("password"));
        user.setAge(resultSet.getInt("age"));
        user.setFullName(resultSet.getString("full_name"));
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }

  public static void addUser(User user) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "insert into users(email, password, full_name, age) "
              + "values (?, ?, ?, ?) ");
      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getFullName());
      statement.setInt(4, user.getAge());
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static List<Blog> getBlogs() {
    List<Blog> blogs = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "select b.id, b.title, b.content, b.post_date, b.user_id, "
              + "u.age, u.full_name from blogs b "
              + "inner join users u on u.id = b.user_id");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Blog blog = new Blog();
        blog.setId(resultSet.getLong("id"));
        blog.setTitle(resultSet.getString("title"));
        blog.setContent(resultSet.getString("content"));
        blog.setPostDate(resultSet.getDate("post_date"));

        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setAge(resultSet.getInt("age"));
        user.setFullName(resultSet.getString("full_name"));
        blog.setUser(user);
        blogs.add(blog);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return blogs;
  }

  public static Blog getBlogById(Long id) {
    Blog blog = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
          "select b.id, b.title, b.content, b.post_date, "
              + "b.user_id, u.full_name, u.age, u.email from blogs b "
              + "inner join users u on b.user_id = u.id "
              + "where b.id = ?");
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        blog = new Blog();
        blog.setId(resultSet.getLong("id"));
        blog.setTitle(resultSet.getString("title"));
        blog.setContent(resultSet.getString("content"));
        blog.setPostDate(resultSet.getDate("post_date"));

        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setFullName(resultSet.getString("full_name"));
        user.setAge(resultSet.getInt("age"));
        blog.setUser(user);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return blog;
  }

  public static List<User> getUsers() {
    List<User> users = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "select * from users");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setAge(resultSet.getInt("age"));
        user.setFullName(resultSet.getString("full_name"));
        user.setEmail(resultSet.getString("email"));
        users.add(user);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }

  public static void deleteBlogById(Long id) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "delete from blogs where id=?");
      statement.setLong(1, id);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void editBlog(Long blogId, String title, String content, Long userId) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "update blogs "
              + "set title = ?, content = ?, user_id = ? "
              + "where id = ?");
      statement.setString(1, title);
      statement.setString(2, content);
      statement.setLong(3, userId);
      statement.setLong(4, blogId);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void addComment(String description, Long blogId, Long userId) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "insert into comments(description, post_date, blog_id, user_id) "
              + "values (?, now(), ?, ?)");
      statement.setString(1, description);
      statement.setLong(2, blogId);
      statement.setLong(3, userId);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static List<Comment> getCommentsByBlogId(Long blogId) {
    List<Comment> comments = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "select c.id, c.description, c.post_date, c.user_id, c.blog_id, "
              + "u.full_name, u.age "
              + " from comments c "
              + "inner join blogs b on b.id = c.blog_id "
              + "inner join users u on u.id = c.user_id "
              + "where b.id = ?");
      statement.setLong(1, blogId);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("id"));
        comment.setDescription(resultSet.getString("description"));
        comment.setPostDate(resultSet.getDate("post_date"));

        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setFullName(resultSet.getString("full_name"));
        user.setAge(resultSet.getInt("age"));
        comment.setUser(user);
        comments.add(comment);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return comments;
  }

  public static void deleteCommentById(Long id) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "delete from comments "
              + "where id = ?");
      statement.setLong(1, id);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
