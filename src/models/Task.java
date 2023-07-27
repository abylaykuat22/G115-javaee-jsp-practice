package models;

public class Task {
  private Long id;
  private String name;
  private String description;
  private String deadlineDate;
  private Category category;

  private Performer performer;
  private boolean status;

  public Task() {

  }

  public Task(Long id, String name, String description, String deadlineDate, boolean status, Category category,
              Performer performer) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.deadlineDate = deadlineDate;
    this.status = status;
    this.category = category;
    this.performer = performer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDeadlineDate() {
    return deadlineDate;
  }

  public void setDeadlineDate(String deadlineDate) {
    this.deadlineDate = deadlineDate;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Performer getPerformer() {
    return performer;
  }

  public void setPerformer(Performer performer) {
    this.performer = performer;
  }
}
