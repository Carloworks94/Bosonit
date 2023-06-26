package com.bosonit.block5properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "my")
public class Variables implements CommandLineRunner {
    @Value("${greeting}")
    private String greeting;
    @Value("${my.number}")
    private int number;
    @Value("${new.property}")
    private String newProperty;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNewProperty() {
        return newProperty;
    }

    public void setNewProperty(String newProperty) {
        this.newProperty = newProperty;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(getGreeting());
        System.out.println(getNumber());
        System.out.println("El valor de new.property es: " +
                (newProperty.isEmpty() ? "new.property no tiene valor" : getNewProperty()));
    }
}
