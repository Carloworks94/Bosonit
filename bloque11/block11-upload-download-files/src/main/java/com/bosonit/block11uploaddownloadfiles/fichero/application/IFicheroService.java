package com.bosonit.block11uploaddownloadfiles.fichero.application;

import com.bosonit.block11uploaddownloadfiles.fichero.controller.dto.FicheroOutputDTO;
import com.bosonit.block11uploaddownloadfiles.fichero.domain.Fichero;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface IFicheroService {
    void init();

    boolean validacionParamDownload(Integer id, String nombre);

    Optional<FicheroOutputDTO> upload(MultipartFile file, String categoria) throws IOException;

    Resource download(Fichero fichero) throws Exception;

    Resource downloadByNombre(String nombre) throws Exception;

    Resource downloadById(Integer id) throws Exception;

    void setPath(String ruta);


//    Stream<Path> loadAll();
//
//    Path load(String filename);
//
//    Resource loadAsResource(String filename);
//
//    void deleteAll();

}
