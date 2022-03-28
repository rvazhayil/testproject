package com.xyzcorp.testproject.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mvcMock;

    private StringBuilder data;

    private StringBuilder badData;

    private StringBuilder lessThanZeroData;

    @BeforeEach
    public void init() throws JsonProcessingException {
        data = new StringBuilder();
        data.append("NAME,SALARY")
                .append("\n")
                .append("John,100.21")
                .append("\n")
                .append("Sam,1200.1");
        badData = new StringBuilder();
        badData.append("NAME,SALARY")
                .append("\n")
                .append("Peter,RR")
                .append("\n")
                .append("Sam,1300.1");

        lessThanZeroData = new StringBuilder();
        lessThanZeroData.append("NAME,SALARY")
                .append("\n")
                .append("Zac,-10")
                .append("\n")
                .append("Joe,2500.1");

    }

    @Test
    void whenUploadCSV_thenSuccess() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
                "text/plain", data.toString().getBytes());
        this.mvcMock.perform(multipart("/users/upload").file(multipartFile))
                .andExpect(status().is2xxSuccessful())
                .andExpect( content().string(containsString("{\"success\":\"1\"}")));

    }

    @Test
    void whenUploadCorruptedCSV_thenBadRequest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
                "text/plain", badData.toString().getBytes());
        this.mvcMock.perform(multipart("/users/upload").file(multipartFile))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenUploadlessThanZeroCSV_theRecordSkipped() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
                "text/plain", lessThanZeroData.toString().getBytes());
        this.mvcMock.perform(multipart("/users/upload").file(multipartFile))
                .andExpect(status().is2xxSuccessful());
        this.mvcMock.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"results\":[{\"name\":\"John\",\"salary\":100.21}" +
                        ",{\"name\":\"Sam\",\"salary\":1200.1}" +
                        ",{\"name\":\"Joe\",\"salary\":2500.1}]}")));
    }
    @Test
    public void whenLimitIsOneAndOffset_thenOnePerPage() throws Exception {
        this.mvcMock.perform(get("/users?limit=1&offset=0"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"results\":[{\"name\":\"John\",\"salary\":100.21}]}")));

        this.mvcMock.perform(get("/users?limit=1&offset=1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"results\":[{\"name\":\"Sam\",\"salary\":1200.1}]}")));
    }

    //@Test
    public void whenSort_thenSorted() throws Exception {
        this.mvcMock.perform(get("/users?sort=name"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"results\":[{\"name\":\"Joe\",\"salary\":2500.1}," +
                        "{\"name\":\"John\",\"salary\":100.21},{\"name\":\"Sam\",\"salary\":1200.1}]}")));

        this.mvcMock.perform(get("/users?sort=salary"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"results\":[{\"name\":\"John\",\"salary\":100.21}," +
                        "{\"name\":\"Sam\",\"salary\":1200.1},{\"name\":\"Joe\",\"salary\":2500.1}]}")));
    }

    @Test
    public void whenMinAndMax_thenBetweenRecords() throws Exception {
        this.mvcMock.perform(get("/users?min=100&max=2000"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"results\":[{\"name\":\"John\",\"salary\":100.21}" +
                        ",{\"name\":\"Sam\",\"salary\":1200.1}]}")));

        }
}

