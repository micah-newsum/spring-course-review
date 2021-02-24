package com.newsum.core;

import com.newsum.course.Course;
import com.newsum.course.CourseRepository;
import com.newsum.review.Review;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
  private final CourseRepository courses;

  public DatabaseLoader(CourseRepository courses){
    this.courses = courses;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Course course = new Course("Java Basics","https://teamtreehouse.com/library/java-basics");
    course.addReview(new Review(5,"Great course!"));

    String[] templates = {
      "Up and running with %s",
      "%s Basics",
      "%s for Beginners",
        "%s for Neckbeards",
        "Under the hood: %s"
    };

    String[] buzzwords = {
      "Spring REST Data",
        "Java 15",
        "Scala",
        "Groovy",
        "Hibernate"
    };

    List<Course> mockCourses = new ArrayList<>();

    IntStream.range(0,100)
              .forEach(i ->
                  {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course mockCourse = new Course(title, "http://example.com");
                    mockCourse.addReview(new Review(5,"Great course!"));
                    mockCourses.add(mockCourse);
                  }
              );

    courses.saveAll(mockCourses);
    courses.save(course);
  }
}
