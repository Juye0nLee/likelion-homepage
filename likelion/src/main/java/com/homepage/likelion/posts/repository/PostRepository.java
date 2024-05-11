package com.homepage.likelion.posts.repository;

import com.homepage.likelion.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
