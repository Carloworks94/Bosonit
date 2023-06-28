package com.bosonit.block11uploaddownloadfiles.fichero.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class RutaArchivo implements IRutaArchivo{

    @Value("${rutaArchivos}")
    private String ruta;

//C:/Users/carlos.garrido/FicherosPrueba
    @Override
    public String setPath (String nuevoPath){
        this.ruta = nuevoPath;
        return nuevoPath;
    }

    @Override
    public Path getPath(){
        return Paths.get(ruta);
    }
}
