package com.bosonit.block7crudvalidation.estudios.service;

import com.bosonit.block7crudvalidation.estudios.application.EstudiosServiceImpl;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosSimpleOutputDTO;
import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import com.bosonit.block7crudvalidation.estudios.repository.IEstudiosRepository;
import com.bosonit.block7crudvalidation.exceptions.EntityNotFoundException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class EstudiosServiceTest {
    @Autowired
    EstudiosServiceImpl estudiosService;

    @MockBean
    IEstudiosRepository estudiosRepositoryMock;

    @MockBean
    IProfesorRepository profesorRepositoryMock;


    @Test
    public void addEstudiosTest() throws ParseException {
        int idProfesor = 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        EstudiosInputDTO estudiosInputDTO = new EstudiosInputDTO(idProfesor, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Profesor profesor = new Profesor(idProfesor, null, "Buen profesor", "Ingenieria Informatica");

        Mockito.when(profesorRepositoryMock.findById(anyInt())).thenReturn(Optional.of(profesor));
        Estudios estudios = new Estudios(estudiosInputDTO);
        estudios.setProfesor(profesor);
        Mockito.when(estudiosRepositoryMock.save(any())).thenReturn(estudios);
        EstudiosOutputDTO estudiosOutputDTO = estudiosService.addEstudios(estudiosInputDTO);

        Assert.assertEquals(estudios.estudiosToEstudiosOutputDTO(), estudiosOutputDTO);

        Mockito.verify(profesorRepositoryMock).findById(any());
        Mockito.verify(estudiosRepositoryMock).save(any());
    }

    @Test
    public void getEstudiosTest() throws ParseException {
        int id = 2;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Profesor profesor = new Profesor(4, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios = new Estudios(id, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));

        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.of(estudios));
        EstudiosOutputDTO estudiosOutputDTO = estudiosService.getEstudios(id);
        Assert.assertEquals(estudios.estudiosToEstudiosOutputDTO(), estudiosOutputDTO);
        Mockito.verify(estudiosRepositoryMock).findById(any());
    }

    @Test
    public void getSimpleEstudios() throws ParseException {
        int id = 2;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Profesor profesor = new Profesor(4, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios = new Estudios(id, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));

        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.of(estudios));
        EstudiosSimpleOutputDTO estudiosSimpleOutputDTO = estudiosService.getSimpleEstudios(id);
        Assert.assertEquals(estudios.estudiosToEstudiosSimpleOutputDTO(), estudiosSimpleOutputDTO);
        Mockito.verify(estudiosRepositoryMock).findById(any());
    }

    @Test
    public void getAllEstudios() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        List<Estudios> lEstudios = new ArrayList<>();
        Profesor profesor = new Profesor(4, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios1 = new Estudios(3, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Estudios estudios2 = new Estudios(8, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        lEstudios.add(estudios1);
        lEstudios.add(estudios2);

        when(estudiosRepositoryMock.findAll()).thenReturn(lEstudios);
        List<EstudiosOutputDTO> estudiosOP = estudiosService.getAllEstudios();
        Assert.assertEquals(lEstudios.stream().map(estudios -> estudios.estudiosToEstudiosOutputDTO()).toList(), estudiosOP);

        Mockito.verify(estudiosRepositoryMock).findAll();
    }

    @Test
    public void updateEstudios() throws ParseException {
        int id = 3;
        int idProfesor = 4;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        EstudiosInputDTO estudiosInputDTO = new EstudiosInputDTO(idProfesor, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Profesor profesor = new Profesor(idProfesor, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios = new Estudios(id, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));


        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.of(estudios));
        when(profesorRepositoryMock.findById(anyInt())).thenReturn(Optional.of(profesor));

        Estudios estudiosActualizados = estudiosInputDTO.estudiosInputDTOToEstudios(estudios);
        when(estudiosRepositoryMock.save(any())).thenReturn(estudiosActualizados);
        EstudiosOutputDTO estudiosOutputDTO = estudiosService.updateEstudios(id, estudiosInputDTO);


        Assert.assertEquals(estudiosActualizados.estudiosToEstudiosOutputDTO(), estudiosOutputDTO);

        Mockito.verify(estudiosRepositoryMock).findById(anyInt());
        Mockito.verify(profesorRepositoryMock).findById(anyInt());
        Mockito.verify(estudiosRepositoryMock).save(any());
    }

    @Test
    public void deleteEstudios() throws ParseException {
        int id = 6;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Profesor profesor = new Profesor(4, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios = new Estudios(id, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));

        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.of(estudios));
        Mockito.doNothing().when(estudiosRepositoryMock).deleteById(anyInt());

        EstudiosOutputDTO estudiosOutputDTO = estudiosService.deleteEstudios(id);

        Assert.assertEquals(estudios.estudiosToEstudiosOutputDTO(), estudiosOutputDTO);
        Mockito.verify(estudiosRepositoryMock).findById(anyInt());
        Mockito.verify(estudiosRepositoryMock).deleteById(anyInt());
    }

    @Test
    public void testAddEstudios_ProfesorNotFound() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        // Arrange
        EstudiosInputDTO estudiosInputDTO = new EstudiosInputDTO(4, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));

        when(profesorRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            estudiosService.addEstudios(estudiosInputDTO);
        });

        String expectedMessage = "404 - El profesor no existe";
        String actualMessage = exception.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testGetEstudios_EstudiosNotFound() {
        int id = 1;

        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            estudiosService.getEstudios(id);
        });

        String expectedMessage = "404 - Los estudios no existen";
        String actualMessage = exception.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testGetSimpleEstudios_EstudiosNotFound() {
        int id = 1;

        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());


        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            estudiosService.getSimpleEstudios(id);
        });


        String expectedMessage = "404 - Los estudios no existen";
        String actualMessage = exception.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testUpdateEstudios_EstudiosNotFound() throws ParseException {
        int id = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        EstudiosInputDTO estudiosInputDTO = new EstudiosInputDTO(4, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));

        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            estudiosService.updateEstudios(id, estudiosInputDTO);
        });

        String expectedMessage = "404 - Estudios no encontrados";
        String actualMessage = exception.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testUpdateEstudios_ProfesorNotFound() throws ParseException {
        int id = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        EstudiosInputDTO estudiosInputDTO = new EstudiosInputDTO(4, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Profesor profesor = new Profesor(4, null, "Buen profesor", "Ingenieria Informatica");
        Estudios estudios = new Estudios(id, profesor, null, "Lengua Castellana", "Muy dificil", dateFormat.parse("1980-12-31T23:00:00.000+00:00"),
                dateFormat.parse("1980-12-31T23:00:00.000+00:00"));

        when(profesorRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());
        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.of(estudios));
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            estudiosService.updateEstudios(id, estudiosInputDTO);
        });


        String expectedMessage = "404 - El profesor no existe";
        String actualMessage = exception.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testDeleteEstudios_EstudiosNotFound() {
        int id = 1;

        when(estudiosRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            estudiosService.deleteEstudios(id);
        });

        String expectedMessage = "404 - Estudios no encontrados";
        String actualMessage = exception.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}