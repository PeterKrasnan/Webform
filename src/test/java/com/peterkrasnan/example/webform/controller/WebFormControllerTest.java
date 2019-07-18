package com.peterkrasnan.example.webform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterkrasnan.example.webform.service.WebFormServiceImpl;
import com.peterkrasnan.example.webform.model.RequestType;
import com.peterkrasnan.example.webform.model.WebForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.peterkrasnan.example.webform.controller.WebFormController.STORE_CONFIRMATION;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WebFormController.class)
public class WebFormControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WebFormServiceImpl dao;

    private WebForm webForm;

    @Before
    public void setUp() {
        webForm = new WebForm() {{
            setName("peter");
            setSurname("Krasnan");
            setPolicyNumber("abc");
            setRequestType(RequestType.DAMAGE_CASE);
            setRequest("request");
        }};

        given(dao.saveWebForm(webForm)).willReturn(webForm);
    }

    @Test
    public void createValidWebForm() throws Exception {
        callCreateApi(objectMapper.writeValueAsString(webForm))
                .andExpect(status().isOk())
                .andExpect(content().string(STORE_CONFIRMATION));
    }

    @Test
    public void tryToCreateWithInvalidName() throws Exception {
        webForm.setName("Peter12");
        invalidCreateCall(objectMapper.writeValueAsString(webForm));
    }

    @Test
    public void tryToCreateWithInvalidSurname() throws Exception {
        webForm.setSurname("Pe12");
        invalidCreateCall(objectMapper.writeValueAsString(webForm));
    }

    @Test
    public void tryToCreateWithInvalidPolicyNumber() throws Exception {
        webForm.setPolicyNumber("@as");
        invalidCreateCall(objectMapper.writeValueAsString(webForm));
    }


    @Test
    public void tryToCreateWithEmptyName() throws Exception {
        webForm.setName(null);
        invalidCreateCall(objectMapper.writeValueAsString(webForm));
    }

    @Test
    public void tryToCreateWithEmptySurname() throws Exception {
        webForm.setSurname(null);
        invalidCreateCall(objectMapper.writeValueAsString(webForm));
    }

    @Test
    public void tryToCreateWithEmptyRequest() throws Exception {
        webForm.setRequest(null);
        invalidCreateCall(objectMapper.writeValueAsString(webForm));
    }

    @Test
    public void tryToCreateWithEmptyRequestType() throws Exception {
        webForm.setRequestType(null);
        invalidCreateCall(objectMapper.writeValueAsString(webForm));
    }

    @Test
    public void tryToCreateWithEmptyPolicyNumber() throws Exception {
        webForm.setPolicyNumber(null);
        invalidCreateCall(objectMapper.writeValueAsString(webForm));
    }

    private ResultActions callCreateApi(String webFormJson) throws Exception {
        return mvc.perform(post("/webform/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(webFormJson));
    }

    private void invalidCreateCall(String s) throws Exception {
        callCreateApi(s).andExpect(status().is(400));
    }


}