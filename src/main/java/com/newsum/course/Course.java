package com.newsum.course;

import com.newsum.core.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Course extends BaseEntity {
  private String title;
  private String url;

  // zero parameter constructor required by JPA.
  protected Course(){
    super();
  }

  public Course(String title, String url) {
    this();
    this.title = title;
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
