package com.news_manager.demo.repository;

import com.news_manager.demo.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Dmitriy on 28.05.2018.
 */
public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("select n from News n where n.name = :name")
    List<News> findByName(@Param("name") String name);

    @Query("select n from News n where n.content = :content")
    List<News> findByContent(@Param("content") String content);

    @Query("select n from News n where n.category = :category")
    List<News> findByCategory(@Param("category") String category);

}
