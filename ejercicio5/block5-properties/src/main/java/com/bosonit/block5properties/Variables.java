package com.bosonit.block5properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Variables implements CommandLineRunner {
    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private int myNumber;

    @Value("${new.property}")
    private String newProperty;

    //agregamos una variable de entorno con el nombre new.property y nos da como salida
    //el valor de la variable de entorno, ya que no tiene valor en application.properties
    //(acordarse de reiniciar el IDE para que efectúen los cambios de las variables de entorno)
    @Override
    public void run(String... args) throws Exception {
        System.out.println(greeting);
        System.out.println(myNumber);
        System.out.println("El valor de new.property es: " +
                (newProperty.isEmpty() ? "new.property no tiene valor" : newProperty));
    }
}
