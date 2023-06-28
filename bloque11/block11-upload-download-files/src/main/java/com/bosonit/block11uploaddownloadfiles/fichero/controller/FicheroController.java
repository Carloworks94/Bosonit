package com.bosonit.block11uploaddownloadfiles.fichero.controller;

import com.bosonit.block11uploaddownloadfiles.fichero.application.FicheroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FicheroController {
    @Autowired
    FicheroServiceImpl ficheroService;

    @PostMapping(value = "/upload/{categoria}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> upload(@RequestParam MultipartFile file,
                                    @PathVariable String categoria) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ficheroService.upload(file, categoria).orElseThrow());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //En caso de que introduzca los 2 parametros y que, en este caso, el id sea erroneo respecto al nombre, devolvemos mensaje de error
    @GetMapping("/download")
    public ResponseEntity<?> download(@RequestParam(name = "nombre", required = false) String nombre,
                                      @RequestParam(name = "id", required = false) Integer id) throws Exception {
        if (nombre != null && id != null) {
            if (ficheroService.validacionParamDownload(id, nombre))
                return ResponseEntity.status(HttpStatus.OK).body(ficheroService.downloadByNombre(nombre));
            return ResponseEntity.status(HttpStatus.OK).body("Parametro incorrecto");
        } else if (nombre != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ficheroService.downloadByNombre(nombre));
        } else if (id != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ficheroService.downloadById(id));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Parametro incorrecto");
        }
    }

    @GetMapping("/path")
    public ResponseEntity<String> setPath(@RequestParam String ruta) {
        ficheroService.setPath(ruta);
        return ResponseEntity.status(HttpStatus.OK).body("Ruta cambiada correctamente");
    }

}
