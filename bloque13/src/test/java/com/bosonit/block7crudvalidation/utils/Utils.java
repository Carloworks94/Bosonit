package com.bosonit.block7crudvalidation.utils;

import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static void creaPersona(PersonaInputDTO persona, String email) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        persona.setUser("user134");
        persona.setPassword("12345");
        persona.setName("Luis");
        persona.setSurname("Fonsi");
        persona.setCity("Torao");
        persona.setActive(true);
        persona.setPersonalEmail("pabloPersonal@sample.sample");
        persona.setCompanyEmail(email);
        persona.setCreatedDate(dateFormat.parse("1985-12-31"));
        persona.setTerminationDate(dateFormat.parse("1990-12-31"));
        persona.setImagenUrl("www.url.com");
    }

    public static void assertionsPersona(PersonaInputDTO personaInputDto, PersonaOutputDTO personaOutputDto) {
        Assertions.assertEquals(personaInputDto.getUser(), personaOutputDto.getUser());
        Assertions.assertEquals(personaInputDto.getName(), personaOutputDto.getName());
        Assertions.assertEquals(personaInputDto.getSurname(), personaOutputDto.getSurname());
        Assertions.assertEquals(personaInputDto.getCity(), personaOutputDto.getCity());
        Assertions.assertEquals(personaInputDto.getActive(), personaOutputDto.getActive());
        Assertions.assertEquals(personaInputDto.getPersonalEmail(), personaOutputDto.getPersonalEmail());
        Assertions.assertEquals(personaInputDto.getCompanyEmail(), personaOutputDto.getCompanyEmail());
        Assertions.assertEquals(personaInputDto.getImagenUrl(), personaOutputDto.getImagenUrl());
        Assertions.assertEquals(personaInputDto.getCreatedDate(), personaOutputDto.getCreatedDate());
        Assertions.assertEquals(personaInputDto.getTerminationDate(), personaOutputDto.getTerminationDate());
    }

//    public int minimo (int[] v, int i, int j){
//        int mini = Integer.MAX_VALUE;
//        for (int k=i; k<j; k++){
//            if (v[k]<mini)
//        }
//    }

    public static int divideYVenceras(int[] v, int i, int j) {
        if (j - i == 1)
            return Math.abs(v[j] - v[i]);
        else if (j-i == 0)
            return Integer.MAX_VALUE;
        else {
            int medio = (i+j) / 2;
            //8-4-2
            int minIz = divideYVenceras(v, i, medio);
            int minDer = divideYVenceras(v, medio + 1, j);
            int minimo = Math.min(minIz, minDer);

            return minimo;
        }
    }
}
