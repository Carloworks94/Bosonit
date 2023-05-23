package com.bosonit;


import com.sun.jdi.InvalidLineNumberException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    int numDatos = 3; // meter en una interfaz de constante

    private static List<Person> conversionCSV (String ruta) throws IOException{
        List<Person> personas = new ArrayList<>();
        Path path = Paths.get(ruta);
        int numLinea = 0;
        try {
            List<String> lineas = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            for (String linea : lineas){
                numLinea++;
                String[] aux = linea.split(":", -1);  //-1 para contar el último campo aunque esté vacío
                Person person = new Person(aux[0]);
                if (aux.length==3) {
                    if (aux[0].length()<1)
                        throw new InvalidLineFormatException("Formato de linea incorrecto en la linea " + numLinea);
                    if (aux[1]!="")
                        person.setTown(aux[1]);
                    if (aux[2]!="")
                        person.setAge(Integer.parseInt(aux[2]));
                }else{
                    throw new InvalidLineFormatException("Formato de linea incorrecto en la linea " + numLinea);
                }
                personas.add(person);
            }
        } catch (InvalidLineFormatException e) {
            System.out.println(e.getMessage());
        }
        return personas;
    }

   public static List<Person> filtraPersonas (List<Person> personas){
        List<Person> filtro = personas.stream()
                .filter(person -> person.getAge()< 25)
                .filter(person -> person.getAge()> 0)
                .collect(Collectors.toList());
        return filtro;
    }


    public static void main(String[] args) throws IOException {
        String ruta = "C:\\Users\\carlos.garrido\\IdeaProjects\\block1-process-file-and-streams\\Personas.csv";
        List<Person> personas = conversionCSV(ruta);
        personas = filtraPersonas(personas);

        for (Person persona : personas) {
            System.out.println(persona.toString());
        }
    }
}