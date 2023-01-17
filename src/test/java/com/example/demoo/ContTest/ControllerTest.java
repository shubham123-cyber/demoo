package com.example.demoo.ContTest;

import com.example.demoo.DemoController.DemoCont;
import com.example.demoo.DemoService.DemoServ;
import com.example.demoo.Model.Entitymodel;
import com.example.demoo.Repo.Sql;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper.*;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoCont.class)

public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    Sql sql;
    @InjectMocks
    DemoCont demoCont;


    @MockBean
    private DemoServ demoServ;

    //private DemoCont demoCont;

//    @BeforeEach
//    void setUp() {
//        Entitymodel entitymodel=Entitymodel.builder()
//                .idd(1)
//                .you("hii")
//                .build();
//    }

    @Test
    public void Control_add() throws Exception {

        Entitymodel entitymodel=Entitymodel.builder()
                .idd(2)
                .you("hii")
                .build();
        Mockito.when(demoServ.Save(entitymodel)).thenReturn(entitymodel);
        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idd\":\"1\",\"you\":\"hello shubham\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void Control_get_all() throws Exception {

        Entitymodel entitymodel=Entitymodel.builder()
                .idd(2)
                .you("hii")
                .build();
        List<Entitymodel> e=List.of(entitymodel);
        Mockito.when(demoServ.GetAll()).thenReturn((e));
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(e.size()))
                .andDo(print());


    }
    @Test
    public void Control_update() throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();

        Entitymodel entitymodel=Entitymodel.builder()
                .idd(2)
                .you("hii")
                .build();
        Entitymodel entitymodel1=Entitymodel.builder()
                .idd(2)
                .you("h")
                .build();
        Mockito.when(demoServ.getByIdd(2)).thenReturn(entitymodel);
        Mockito.doNothing().when(demoServ).Update(entitymodel);
        mockMvc.perform(put("/update/{id}",entitymodel.getIdd())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entitymodel1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.you").value(entitymodel1.getYou()))
                .andDo(print());
                //.andExpect(jsonPath("$.description").value(updatedtutorial.getDescription()))


    }
    @Test
    public void Control_delete() throws Exception {



        Entitymodel entitymodel=Entitymodel.builder()
                .idd(2)
                .you("hii")
                .build();
        Mockito.doNothing().when(sql).deleteById(entitymodel.getIdd());
        mockMvc.perform(delete("/delete/{id}",entitymodel.getIdd()))
                .andExpect(status().isOk())
                .andDo(print());
    }





}
