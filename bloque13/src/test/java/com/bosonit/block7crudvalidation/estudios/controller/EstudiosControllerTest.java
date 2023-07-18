package com.bosonit.block7crudvalidation.estudios.controller;

import com.bosonit.block7crudvalidation.estudios.application.EstudiosServiceImpl;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosSimpleOutputDTO;
import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.student.domain.Student;
import com.bosonit.block7crudvalidation.utils.FileHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class EstudiosControllerTest {
    @MockBean
    private EstudiosServiceImpl estudiosServiceMock;

    @InjectMocks
    private EstudiosController estudiosController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addEstudiosTest() throws Exception {
        int idProfesor = 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        EstudiosInputDTO estudiosInputDTO = new EstudiosInputDTO(idProfesor, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Persona persona = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Profesor profesor = new Profesor(idProfesor, persona, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios = new Estudios(estudiosInputDTO);
        estudios.setProfesor(profesor);
        EstudiosOutputDTO estudiosOutputDTO = estudios.estudiosToEstudiosOutputDTO();
        when(this.estudiosServiceMock.addEstudios(any())).thenReturn(estudiosOutputDTO);

        //el json tiene que tener el mismo valor que el personaInputDTO
        //comprobamos que llamamos al metodo del controlador y devuelve ese content
        this.mockMvc.perform(MockMvcRequestBuilders.post("/estudios")
                        .content(FileHelper.getContentFromFile("estudios.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(estudios.estudiosToEstudiosOutputDTO())));
    }

    @Test
    public void getEstudiosTest() throws Exception {
        int id = 6;
        List<Student> lStudents = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Profesor profesor = new Profesor(3, null, "Buen profesor", "Ingenieria Informatica");
        Student student1 = new Student(1, null, 12, "No estudia nada", profesor, "Ingenieria del software",
                null);
        Student student2 = new Student(2, null, 12, "No estudia nada", profesor, "Ingenieria del software",
                null);
        lStudents.add(student1);
        lStudents.add(student2);
        Estudios estudios = new Estudios(id, profesor, lStudents, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        EstudiosOutputDTO estudiosOutputDTO = estudios.estudiosToEstudiosOutputDTO();

        when(this.estudiosServiceMock.getEstudios(anyInt())).thenReturn(estudiosOutputDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/estudios/{id}", id)
                        .param("outputType", "full"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(estudios.estudiosToEstudiosOutputDTO())));
    }

    @Test
    public void getEstudiosSimpleTest() throws Exception {
        int id = 6;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Profesor profesor = new Profesor(3, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios = new Estudios(id, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        EstudiosSimpleOutputDTO estudiosSimpleOutputDTO = estudios.estudiosToEstudiosSimpleOutputDTO();

        when(this.estudiosServiceMock.getSimpleEstudios(anyInt())).thenReturn(estudiosSimpleOutputDTO);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/estudios/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(estudios.estudiosToEstudiosSimpleOutputDTO())));
    }

    @Test
    public void getAll() throws Exception {
        List<Estudios> lEstudios = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Profesor profesor = new Profesor(3, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios1 = new Estudios(6, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Estudios estudios2 = new Estudios(7, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        lEstudios.add(estudios1);
        lEstudios.add(estudios2);
        when(this.estudiosServiceMock.getAllEstudios()).thenReturn(lEstudios.stream().map(estudios -> estudios.estudiosToEstudiosOutputDTO()).toList());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/estudios"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(lEstudios.stream().map(estudios -> estudios.estudiosToEstudiosOutputDTO()).toList())));
    }

    @Test
    public void updateEstudios() throws Exception {
        int id = 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Profesor profesor = new Profesor(3, null, "Buen profesor", "Ingenieria Informatica");
        EstudiosInputDTO estudiosInputDTO = new EstudiosInputDTO(3, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Estudios estudios1 = new Estudios(id, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Estudios estudios = estudiosInputDTO.estudiosInputDTOToEstudios(estudios1);

        when(this.estudiosServiceMock.updateEstudios(anyInt(), any())).thenReturn(estudios.estudiosToEstudiosOutputDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.put("/estudios/{id}", id)
                        .content(FileHelper.getContentFromFile("estudios.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(estudios.estudiosToEstudiosOutputDTO())));
    }

    @Test
    public void deleteEstudios() throws Exception {
        int id = 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Profesor profesor = new Profesor(3, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios = new Estudios(id, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));

        when(this.estudiosServiceMock.deleteEstudios(anyInt())).thenReturn(estudios.estudiosToEstudiosOutputDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/estudios/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(estudios.estudiosToEstudiosOutputDTO())));
    }


}
