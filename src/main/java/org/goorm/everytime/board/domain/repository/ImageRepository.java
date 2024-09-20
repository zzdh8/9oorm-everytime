package org.goorm.everytime.board.domain.repository;

import org.goorm.everytime.board.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPostId(Long postId);
    void deleteByPostId(Long postId);
}
