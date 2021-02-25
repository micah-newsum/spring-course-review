package com.newsum.review;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ReviewRepository extends PagingAndSortingRepository<Review,Long> {
  @Override
  @PreAuthorize("@reviewRepository.findOne(#id)?.reviewer?.username == authentication.name")
  void deleteById(@Param("id") Long id);

  @Override
  @PreAuthorize("#review.reviewer?.username == authentication.name")
  void delete(@Param("review") Review review);
}
