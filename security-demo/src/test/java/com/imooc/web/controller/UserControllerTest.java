package com.imooc.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * lcd  2019/12/29
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        mockMvc.perform(fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk());
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("asdf","tt")
                        .param("username", "zhangsan")
                        .param("age", "22")
                        .param("ageTo", "33")
                        .param("telphone", "44")
//                .param("page","10")
//                .param("size","2")
//                .param("sort","username,desc")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date data = new Date();
        long time = data.getTime();
        System.out.println("前端time" + time);
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":" + time + "}";
        String res = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("返回的" + res);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        Date data = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        long time = data.getTime();
        System.out.println("前端time" + time);
        String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":" + time + "}";
        String res = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("返回的" + res);
    }
}
