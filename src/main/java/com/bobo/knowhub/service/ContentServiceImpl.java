package com.bobo.knowhub.service;

import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl {

    @Autowired
    private ContentRepository contentRepository;

    public Content saveContent(Content content){
        return contentRepository.save(content);
    }

    public List<Content> getAllContent(){
        return contentRepository.findAll();
    }

}
