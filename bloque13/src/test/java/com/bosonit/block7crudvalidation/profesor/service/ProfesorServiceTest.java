package com.bosonit.block7crudvalidation.profesor.service;

import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.persona.repository.IPersonaRepository;
import com.bosonit.block7crudvalidation.profesor.application.ProfesorServiceImpl;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorInputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.profesor.repository.IProfesorRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProfesorServiceTest {
    @Autowired
    ProfesorServiceImpl profesorService;

    @MockBean
    IProfesorRepository profesorRepositoryMock;

    @MockBean
    IPersonaRepository personaRepositoryMock;

    @Test
    public void addProfesorTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        ProfesorInputDTO profesorInputDTO = new ProfesorInputDTO(123, null, "Buen profesor", "Ingenieria Informatica");
        Persona persona = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));

        Mockito.when(personaRepositoryMock.findById(anyInt())).thenReturn(Optional.of(persona));
        Profesor profesor = new Profesor(profesorInputDTO);
        profesor.setPersona(persona);

        Mockito.when(profesorRepositoryMock.save(any())).thenReturn(profesor);
        ProfesorOutputDTO profesorOutputDTO = profesorService.addProfesor(profesorInputDTO);

        Assert.assertEquals(profesor.profesorToProfesorOutputDTO(), profesorOutputDTO);

        Mockito.verify(personaRepositoryMock).findById(any());
        Mockito.verify(profesorRepositoryMock).save(any());
    }

    //TODO: hacer el resto de tests (50% cobertura completado)
}
