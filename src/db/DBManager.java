package db;

import java.util.ArrayList;
import java.util.List;
import models.Task;

public class DBManager {
  private static List<Task> tasks = new ArrayList<>();

  private static Long id = 5L;

  static {
    tasks.add(new Task(1L, "Создать Веб приложение на Java EE", "дополнить crud операции", "20.07.2023", false));
    tasks.add(new Task(2L, "Убраться дома", "максимлальная чистота", "20.07.2023", false));
    tasks.add(new Task(3L, "Выполнить sprint task", "дополнить crud операции", "20.07.2023", false));
    tasks.add(new Task(4L, "Изучить английский язык", "разговорный", "20.12.2023", false));
  }

  public static List<Task> getTasks() {
    return tasks;
  }

  public static void addTask(String name, String description, String deadlineDate) {
    Task task = new Task();
    task.setId(id);
    id++;
    task.setName(name);
    task.setDescription(description);
    task.setDeadlineDate(deadlineDate);
    tasks.add(task);
  }
}
