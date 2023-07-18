package com.bosonit.block7crudvalidation.persona.service;

import com.bosonit.block7crudvalidation.persona.application.PersonaServiceImpl;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.persona.repository.IPersonaRepository;
import com.bosonit.block7crudvalidation.profesor.repository.IProfesorRepository;
import com.bosonit.block7crudvalidation.student.repository.IStudentRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//CustomRunner.class < MockitoJUnitRunner.class (responsible for initializing @Mock, @Spy, and @InjectMock annotations)  < SpringRunner.class (same that MockitoJUnitRunner + initializing a Spring Context and @MockBean and @SpyBean annotations)
@RunWith(SpringRunner.class)
@SpringBootTest
class PersonaServiceTest {

    @Autowired
    private PersonaServiceImpl personaService;

    @MockBean
    private IProfesorRepository profesorRepository;

    @MockBean
    private IStudentRepository studentRepository;

    @MockBean
    private IPersonaRepository personaRepositoryMock;


    @Test
    public void testGetPersonaByIdTest() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        int id = 1;
        Persona persona = new Persona(id, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        //cuando llamemos a este metodo, simula devolver la persona que buscamos?
        Mockito.when(personaRepositoryMock.findById(anyInt())).thenReturn(Optional.of(persona));
        //doReturn(Optional.of(persona)).when(this.personaRepositoryMock).findById(any()); //Lo mismo que lo de arriba
        PersonaOutputDTO personaOutputDTO = personaService.getPersona(id);
        Assert.assertEquals(persona.personaToPersonaOutputDTO(), personaOutputDTO);
        Mockito.verify(personaRepositoryMock).findById(id);
    }

    @Test
    public void testAddPersona() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        PersonaInputDTO personaInputDTO = new PersonaInputDTO("usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Mockito.when(personaRepositoryMock.existsByCompanyEmail(personaInputDTO.getCompanyEmail())).thenReturn(false);
        Persona persona = new Persona(personaInputDTO);
        Mockito.when(personaRepositoryMock.save(any())).thenReturn(persona);
        PersonaOutputDTO personaOutputDTO = personaService.addPersona(personaInputDTO);
        Assert.assertEquals(persona.personaToPersonaOutputDTO(), personaOutputDTO);
        Mockito.verify(personaRepositoryMock).existsByCompanyEmail(personaInputDTO.getCompanyEmail());
        Mockito.verify(personaRepositoryMock).save(any());
    }

    @Test
    public void testGetPersonasByUser() throws ParseException {
        String userr = "usuario";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        List<Persona> personas = new ArrayList<>();
        Persona persona1 = new Persona(123, userr, "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Persona persona2 = new Persona(123, userr, "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        personas.add(persona1);
        personas.add(persona2);
        Mockito.when(personaRepositoryMock.findAllByUserPersona(anyString())).thenReturn(personas);
        List<Persona> persons = personaService.getPersonas(userr);
        Assert.assertEquals(personas, persons);
        Mockito.verify(personaRepositoryMock).findAllByUserPersona(userr);
    }

    @Test
    public void testGetAllPersonas() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        List<Persona> personas = new ArrayList<>();
        Persona persona1 = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Persona persona2 = new Persona(123, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        personas.add(persona1);
        personas.add(persona2);
        Mockito.when(personaRepositoryMock.findAll()).thenReturn(personas);
        List<PersonaOutputDTO> persons = personaService.getAllPersonas();
        Assert.assertEquals(personas.stream().map(persona -> persona.personaToPersonaOutputDTO()).toList(), persons);
        Mockito.verify(personaRepositoryMock).findAll();
    }

    @Test
    public void testUpdatePersona() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        int id = 1;
        PersonaInputDTO personaInputDTO = new PersonaInputDTO("usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1989-12-31T23:00:00.000+00:00"));
        Persona persona = new Persona(id, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1980-12-31T23:00:00.000+00:00"));

        Persona personaInput = personaInputDTO.personaInputDTOtoPersona(persona);
        Mockito.when(personaRepositoryMock.findById(anyInt())).thenReturn(Optional.of(persona));
        Mockito.when(personaRepositoryMock.save(any())).thenReturn(personaInput);
        PersonaOutputDTO personaOutputDTO = personaService.updatePersona(id, personaInputDTO);
        Assert.assertEquals(personaInput.personaToPersonaOutputDTO(), personaOutputDTO);
        Mockito.verify(personaRepositoryMock).findById(anyInt());
        Mockito.verify(personaRepositoryMock).save(any());
    }

    @Test
    public void testDeletePersona() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        int id = 1;
        Persona persona = new Persona(id, "usuario", "1234", "nombre", "surmano", "companyEmail@gmail.com", "personalEmail@gmail.com", "ciudad",
                true, dateFormat.parse("1980-12-31T23:00:00.000+00:00"), "www.urlImagen.com", dateFormat.parse("1980-12-31T23:00:00.000+00:00"));
        Mockito.when(personaRepositoryMock.findById(anyInt())).thenReturn(Optional.of(persona));
        Mockito.doNothing().when(personaRepositoryMock).deleteById(anyInt());
        doReturn(null).when(this.studentRepository).findByPersona(any());
        doReturn(null).when(this.profesorRepository).findByPersona(any());

        PersonaOutputDTO personaOutputDTO = personaService.deletePersona(id);
        Assert.assertEquals(persona.personaToPersonaOutputDTO(), personaOutputDTO);
        Mockito.verify(personaRepositoryMock).findById(anyInt());
        Mockito.verify(personaRepositoryMock).deleteById(anyInt());

    }

}
