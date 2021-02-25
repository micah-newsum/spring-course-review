package com.newsum.core;

import com.newsum.course.Course;
import com.newsum.course.CourseRepository;
import com.newsum.review.Review;
import com.newsum.user.User;
import com.newsum.user.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
  private final CourseRepository courses;
  private final UserRepository userDao;

  public DatabaseLoader(CourseRepository courses, UserRepository userDao){
    this.courses = courses;
    this.userDao = userDao;
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
        "Hibernate",
        "Spring HATEOS"
    };

    List<User> students = Arrays.asList(
        new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
        new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
        new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
        new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
        new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
        new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
        new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
        new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
        new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
        new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
        new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
        new User("mnewsum", "Newsum",  "Micah", "password", new String[] {"ROLE_USER","ROLE_ADMIN"}),
        new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"})
    );


    userDao.saveAll(students);

    List<Course> mockCourses = new ArrayList<>();

    IntStream.range(0,100)
              .forEach(i ->
                  {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course mockCourse = new Course(title, "http://example.com");
                    Review review = new Review(5, "Great course!");
                    review.setReviewer(students.get(i % students.size()));
                    mockCourse.addReview(review);
                    mockCourses.add(mockCourse);
                  }
              );

    courses.saveAll(mockCourses);
    courses.save(course);
  }
}
