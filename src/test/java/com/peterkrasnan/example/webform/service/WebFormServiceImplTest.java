package com.peterkrasnan.example.webform.service;

import com.peterkrasnan.example.webform.model.WebForm;
import com.peterkrasnan.example.webform.repository.WebFormRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class WebFormServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public WebFormService employeeService() {
            return new WebFormServiceImpl();
        }
    }

    @MockBean
    WebFormRepository webFormRepository;

    @Autowired
    WebFormService webFormService;

    @Test
    public void shouldCallRepositoryToSaveWebForm() {
        WebForm webForm = new WebForm();

        when(webFormRepository.save(any(WebForm.class))).thenReturn(webForm);

        webFormService.saveWebForm(webForm);

        verify(webFormRepository, times(1)).save(webForm);
    }
}