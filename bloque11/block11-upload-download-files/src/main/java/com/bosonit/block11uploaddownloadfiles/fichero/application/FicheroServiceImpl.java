package com.bosonit.block11uploaddownloadfiles.fichero.application;

import com.bosonit.block11uploaddownloadfiles.exceptions.EntityNotFoundException;
import com.bosonit.block11uploaddownloadfiles.exceptions.IllegalArgumentException;
import com.bosonit.block11uploaddownloadfiles.fichero.components.IRutaArchivo;
import com.bosonit.block11uploaddownloadfiles.fichero.controller.dto.FicheroOutputDTO;
import com.bosonit.block11uploaddownloadfiles.fichero.domain.Fichero;
import com.bosonit.block11uploaddownloadfiles.fichero.repository.IFicheroRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FicheroServiceImpl implements IFicheroService{
    @Autowired
    IFicheroRepository ficheroRepository;

    @Autowired
    IRutaArchivo rutaArchivo;


    @Override
    public void init(){

    }

    @Override
    public Optional<FicheroOutputDTO> upload (MultipartFile file, String categoria) throws IOException {
        if (file.isEmpty())
            throw new IllegalArgumentException("Fichero vacio");

        if (!categoria.equals(StringUtils.getFilenameExtension(file.getOriginalFilename())))
            throw new IllegalArgumentException("La extension no coincide");

        String nombre = file.getOriginalFilename();
        String rutaFichero = rutaArchivo.getPath() + File.separator + nombre;

        //Sobreescribimos el fichero en caso de que exista
        Files.copy(file.getInputStream(), Path.of(rutaFichero),
                StandardCopyOption.REPLACE_EXISTING);

        //En caso de que el archivo ya se encuentre en nuestro repositorio hacemos un update sin autoincrementar su id
        Fichero fichero = ficheroRepository.findByNombreExacto(file.getOriginalFilename());
        if ( fichero != null){
            fichero.setIdFichero(fichero.getIdFichero());
            return Optional.of(ficheroRepository.save(fichero).ficheroToFicheroOutputDTO());
        }else{
            fichero = new Fichero();
            fichero.setNombre(nombre);
            fichero.setFecha(new Date());
            fichero.setCategoria(categoria);
            return Optional.of(ficheroRepository.save(fichero).ficheroToFicheroOutputDTO());
        }
    }

    @Override
    public boolean validacionParamDownload(Integer id, String nombre){
        if (id != ficheroRepository.findByNombreExacto(nombre).getIdFichero())
            return false;
        return true;
    }
    @Override
    public Resource download (Fichero fichero) throws Exception {
        String rutaFichero = rutaArchivo.getPath() + File.separator + fichero.getNombre();
        Path path = Path.of(rutaFichero);
        Resource resource = new UrlResource((path.toUri()));
        if (!resource.exists() || !resource.isReadable()){
            throw new Exception("Archivo no encontrado");
        }else{
            return resource;
        }
    }

    @Override
    public Resource downloadByNombre (String nombre) throws Exception {
        Fichero fichero = ficheroRepository.findByNombreExacto(nombre);
        if (fichero == null)
            throw new EntityNotFoundException("404 - Fichero no encontrado");
        return download(fichero);
    }

    @Override
    public Resource downloadById (Integer id) throws Exception {
        Fichero fichero = ficheroRepository.findById(id).orElseThrow();
        return download(fichero);
    }

    //Método que cambia la ruta donde descargar los archivos, requiere de una ruta absoluta
    @Override
    public void setPath(String ruta){
        this.rutaArchivo.setPath(ruta);
    }


//    public Stream<Path> loadAll();
//
//    public Path load(String filename);
//
//    public Resource loadAsResource(String filename);
//
//    public void deleteAll();
}
