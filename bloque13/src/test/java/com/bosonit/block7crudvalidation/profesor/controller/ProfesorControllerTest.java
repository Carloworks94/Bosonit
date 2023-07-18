package com.bosonit.block7crudvalidation.profesor.controller;

import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.profesor.application.ProfesorServiceImpl;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorInputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorSimpleOutputDTO;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
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
class ProfesorControllerTest {

    @MockBean
    private ProfesorServiceImpl profesorServiceMock;

    @InjectMocks
    private ProfesorController profesorController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addProfesorTest() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        ProfesorInputDTO profesorInputDTO = new ProfesorInputDTO(123, null, "Buen profesor", "Ingenieria Informatica");
        Persona persona = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Profesor profesor = new Profesor(profesorInputDTO);
        profesor.setPersona(persona);

        when(profesorServiceMock.addProfesor(any())).thenReturn(profesor.profesorToProfesorOutputDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/profesor")
                        .content(FileHelper.getContentFromFile("profesor.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(profesor.profesorToProfesorOutputDTO())));
    }

    @Test
    public void getProfesorTest() throws Exception {
        int idProfesor = 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Persona persona = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Profesor profesor = new Profesor(idProfesor, persona, "Buen profesor", "Ingenieria Informatica");
        ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO(idProfesor, persona.personaToPersonaOutputDTO(), "Buen profesor", "Ingenieria Informatica");

        when(profesorServiceMock.getProfesor(anyInt())).thenReturn(profesorOutputDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/profesor/{id}", idProfesor)
                        .param("outputType", "full"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(profesor.profesorToProfesorOutputDTO())));
    }

    @Test
    public void getSimpleProfesorTest() throws Exception {
        int idProfesor = 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Persona persona = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Profesor profesor = new Profesor(idProfesor, persona, "Buen profesor", "Ingenieria Informatica");
        ProfesorSimpleOutputDTO profesorSimpleOutputDTO = new ProfesorSimpleOutputDTO(idProfesor, "Buen profesor", "Ingenieria Informatica");

        when(profesorServiceMock.getSimpleProfesor(anyInt())).thenReturn(profesorSimpleOutputDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/profesor/{id}", idProfesor))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(profesor.profesorToProfesorSimpleOutputDTO())));
    }

    @Test
    public void getAll() throws Exception {
        List<Profesor> lProfesores = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Persona persona1 = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Persona persona2 = new Persona(1234, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Profesor profesor1 = new Profesor(3, persona1, "Buen profesor", "Ingenieria Informatica");
        Profesor profesor2 = new Profesor(2, persona2, "Buen profesor", "Ingenieria Informatica");

        lProfesores.add(profesor1);
        lProfesores.add(profesor2);
        when(this.profesorServiceMock.getAllProfesor()).thenReturn(lProfesores.stream().map(profesor -> profesor.profesorToProfesorOutputDTO()).toList());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/profesor"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(lProfesores.stream().map(profesor -> profesor.profesorToProfesorOutputDTO()).toList())));
    }

    @Test
    public void updateProfesor() throws Exception {
        int id = 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        ProfesorInputDTO profesorInputDTO = new ProfesorInputDTO(123, null, "Buen profesor", "Ingenieria Informatica");
        Persona persona = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Profesor profesor1 = new Profesor(id, persona, "Buen profesor", "Ingenieria Informatica");
        Profesor profesor = profesorInputDTO.profesorInputDTOtoProfesor(profesor1);
        when(this.profesorServiceMock.updateProfesor(anyInt(), any())).thenReturn(profesor.profesorToProfesorOutputDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.put("/profesor/{id}", id)
                        .content(FileHelper.getContentFromFile("profesor.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(profesor.profesorToProfesorOutputDTO())));
    }

    @Test
    public void deleteProfesor() throws Exception {
        int id = 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Persona persona = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Profesor profesor = new Profesor(id, persona, "Buen profesor", "Ingenieria Informatica");

        when(this.profesorServiceMock.deleteProfesor(anyInt())).thenReturn(profesor.profesorToProfesorOutputDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/profesor/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(profesor.profesorToProfesorOutputDTO())));
    }
}
