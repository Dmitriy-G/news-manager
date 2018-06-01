package com.news_manager.demo.controller;

import com.news_manager.demo.model.News;
import com.news_manager.demo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitriy on 24.05.2018.
 */
@RestController
public class NewsController {

    @Autowired
    NewsRepository newsRepository;



    @PostMapping(value = "/list")
    @ResponseBody
    public News addNewsPage(@RequestBody News news) {
        news.setDatePublished(new Date());
        return newsRepository.save(news);
    }


    @GetMapping(value = "/list")
    @ResponseBody
    public List<News> getNews() {
        return newsRepository.findAll();
    }


    @PutMapping(value = "/list/{id}")
    @ResponseBody
    public News editNewsPage(@RequestBody News newsDetails, @PathVariable("id") Integer id) {
        News news = newsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Incorrect Id"));
        news.setName(newsDetails.getName());
        news.setContent(newsDetails.getContent());
        news.setDatePublished(new Date());
        news.setCategory(newsDetails.getCategory());
        return newsRepository.save(news);
    }

    @DeleteMapping(value = "/list/{id}")
    @ResponseBody
    public News deleteNews(@PathVariable("id") Integer id) {
        News news = newsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Incorrect Id"));
        newsRepository.delete(news);
        return news;
    }

    @GetMapping(value = "/find/{name}{content}{category}")
    @ResponseBody
    public List<News> findNews(@PathVariable("name") String name,@PathVariable("content") String content, @PathVariable("category") String category) {
        if (name!=null&&!name.isEmpty()){
            return newsRepository.findByName(name);
        }
        if (content!=null&&!content.isEmpty()){
            return newsRepository.findByContent(content);
        }
        if (category!=null&&!category.isEmpty()){
            return newsRepository.findByCategory(category);
        }
        return Collections.emptyList();
    }


}
