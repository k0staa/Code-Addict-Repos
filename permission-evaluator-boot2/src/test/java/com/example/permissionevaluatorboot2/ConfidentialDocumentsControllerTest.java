package com.example.permissionevaluatorboot2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConfidentialDocumentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1")
    public void list_withUser1LoggedIn_showHrefForOwnedDocs() throws Exception {
        //testing thymeleaf sec:authorize with hasPermission
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(allOf(
                        containsString("Edit file1.txt"),
                        containsString("Edit file2.txt"),
                        not(containsString("Edit file3.txt")))));
    }

    @Test
    @WithMockUser(username = "user2")
    public void list_withUser2LoggedIn_showHrefForOwnedDocs() throws Exception {
        //testing thymeleaf sec:authorize with hasPermission
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(allOf(
                        not(containsString("Edit file1.txt")),
                        not(containsString("Edit file2.txt")),
                        containsString("Edit file3.txt"))));
    }

    @Test
    @WithMockUser(username = "user1")
    public void findById_withUser1LoggedInAndIdEq1_showdocsDetailsView() throws Exception {
        //test @PreAuthorize annotation on controllers
        this.mockMvc
                .perform(get("/document/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("document"));
    }

    @Test
    @WithMockUser(username = "user1")
    public void findById_withUser1LoggedInAndIdEq2_showDocsDetailsView() throws Exception {
        //test @PreAuthorize annotation on controllers
        this.mockMvc
                .perform(get("/document/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("document"));
    }

    @Test
    @WithMockUser(username = "user1")
    public void findById_withUser1LoggedInAndIdEq3_viewIsForbidden() throws Exception {
        //test @PreAuthorize annotation on controllers
        this.mockMvc
                .perform(get("/document/3"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user2")
    public void findById_withUser2LoggedInAndIdEq1_viewIsForbidden() throws Exception {
        //test @PreAuthorize annotation on controllers
        this.mockMvc
                .perform(get("/document/1"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user2")
    public void findById_withUser2LoggedInAndIdEq2_viewIsForbidden() throws Exception {
        //test @PreAuthorize annotation on controllers
        this.mockMvc
                .perform(get("/document/2"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user2")
    public void findById_withUser2LoggedInAndIdEq3_showDocDetails() throws Exception {
        //test @PreAuthorize annotation on controllers
        this.mockMvc
                .perform(get("/document/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("document"));
    }
}