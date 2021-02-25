package com.newsum.review;

import com.newsum.user.User;
import com.newsum.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class ReviewEventHandler {
  private final UserRepository userDao;

  @Autowired
  public ReviewEventHandler(UserRepository userDao) {
    this.userDao = userDao;
  }

  @HandleBeforeCreate
  public void addReviewBasedOnLoggedInUser(Review review){
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userDao.findByUsername(username);
    review.setReviewer(user);
  }
}
