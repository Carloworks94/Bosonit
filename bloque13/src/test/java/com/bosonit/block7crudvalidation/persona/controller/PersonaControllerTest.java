package com.bosonit.block7crudvalidation.persona.controller;

import com.bosonit.block7crudvalidation.persona.application.PersonaServiceImpl;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.utils.FileHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class PersonaControllerTest {
    //simulamos el servicio, o sea no debería entrar al código de nuestro servicio como tal
    @MockBean
    private PersonaServiceImpl personaServiceMock;

    @InjectMocks
    private PersonaController personaController;

    @Autowired
    private MockMvc mockMvc;

    //Para que se entre a nuestro controlador, si no cuando se le llama no hace nada
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    //addPersonaTest
    public void shouldReturnCreatedWhenValidPersonaRequestIsReceivedTest() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        PersonaInputDTO personaInputDTO = new PersonaInputDTO("usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Persona persona = new Persona(personaInputDTO);
        when(this.personaServiceMock.addPersona(any())).thenReturn(persona.personaToPersonaOutputDTO());
        PersonaOutputDTO personaOutputDTO = persona.personaToPersonaOutputDTO();
        //el json tiene que tener el mismo valor que el personaInputDTO
        //comprobamos que llamamos al metodo del controlador y devuelve ese content
        this.mockMvc.perform(MockMvcRequestBuilders.post("/persona")
                        .content(FileHelper.getContentFromFile("persona.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(personaOutputDTO)));
    }

    @Test
    public void getPersona() throws Exception {
        int id = 2;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Persona persona = new Persona(id, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        when(this.personaServiceMock.getPersona(anyInt())).thenReturn(persona.personaToPersonaOutputDTO());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/persona/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(persona.personaToPersonaOutputDTO())));
    }

    @Test
    public void getAllPersonas() throws Exception {
        List<Persona> personas = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Persona persona1 = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Persona persona2 = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        personas.add(persona1);
        personas.add(persona2);
        when(this.personaServiceMock.getAllPersonas()).thenReturn(personas.stream().map(persona -> persona.personaToPersonaOutputDTO()).toList());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/persona"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(personas.stream().map(
                        persona -> persona.personaToPersonaOutputDTO()
                ).toList())));
    }


    @Test
    public void getPersonaByUser() throws Exception {
        String userr = "usuario";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        List<Persona> personas = new ArrayList<>();
        Persona persona1 = new Persona(123, userr, "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Persona persona2 = new Persona(123, userr, "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        personas.add(persona1);
        personas.add(persona2);

        when(this.personaServiceMock.getPersonas(anyString())).thenReturn(personas);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/persona/user/{user}", userr))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(personas.stream().map(
                        persona -> persona.personaToPersonaOutputDTO()).toList())));
    }

    @Test
    public void updatePersona() throws Exception {
        int id = 2;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        PersonaInputDTO personaInputDTO = new PersonaInputDTO("usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Persona persona1 = new Persona(id, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));

        Persona personaActualizada = personaInputDTO.personaInputDTOtoPersona(persona1);
        when(this.personaServiceMock.updatePersona(anyInt(), any())).thenReturn(personaActualizada.personaToPersonaOutputDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.put("/persona/{id}", id)
                        .content(FileHelper.getContentFromFile("persona.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(personaActualizada.personaToPersonaOutputDTO())));
    }

    @Test
    public void deletePersona() throws Exception {
        int id = 2;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Persona persona = new Persona(id, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));

        when(this.personaServiceMock.deletePersona(anyInt())).thenReturn(persona.personaToPersonaOutputDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/persona/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(persona.personaToPersonaOutputDTO())));
    }

    @Test
    public void testUpdatePersona_Exception() throws Exception {
        int id = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        PersonaInputDTO personaInputDTO = new PersonaInputDTO("usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));

        String errorMessage = "404 - Persona no encontrada";
        when(personaServiceMock.updatePersona(anyInt(), Mockito.any(PersonaInputDTO.class)))
                .thenThrow(new Exception(errorMessage));

        ResponseEntity<?> responseEntity = personaController.updatePersona(id, personaInputDTO);

        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assert.assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    public void testDeletePersona_Exception() throws Exception {
        int id = 1;

        String errorMessage = "404 - Persona no encontrada";
        when(personaServiceMock.deletePersona(anyInt()))
                .thenThrow(new Exception(errorMessage));

        ResponseEntity<?> responseEntity = personaController.deletePersona(id);

        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assert.assertEquals(errorMessage, responseEntity.getBody());
    }

}