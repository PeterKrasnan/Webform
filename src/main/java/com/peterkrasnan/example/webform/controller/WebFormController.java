package com.peterkrasnan.example.webform.controller;

import com.peterkrasnan.example.webform.model.RequestType;
import com.peterkrasnan.example.webform.model.WebForm;
import com.peterkrasnan.example.webform.service.WebFormServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/webform")
@Slf4j
public class WebFormController {

    static String STORE_CONFIRMATION = "Webform is stored";

    @Autowired
    WebFormServiceImpl service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@Valid @RequestBody WebForm webForm) {
        log.info("Saving: " + webForm);
        service.saveWebForm(webForm);
        return ResponseEntity.ok(STORE_CONFIRMATION);
    }

    @GetMapping("/api/requests")
    public List<String> getEnums() {
        return RequestType.getAllRequestsToString();
    }
}
