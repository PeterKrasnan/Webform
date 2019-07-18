package com.peterkrasnan.example.webform.service;

import com.peterkrasnan.example.webform.model.WebForm;
import com.peterkrasnan.example.webform.repository.WebFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebFormServiceImpl implements WebFormService {

    @Autowired
    WebFormRepository webFormRepository;

    @Override
    public WebForm saveWebForm(WebForm webForm) {
        return webFormRepository.save(webForm);
    }
}

