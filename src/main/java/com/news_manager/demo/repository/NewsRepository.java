package com.news_manager.demo.repository;

import com.news_manager.demo.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmitriy on 28.05.2018.
 */
public interface NewsRepository extends JpaRepository<News, Integer> {
}
