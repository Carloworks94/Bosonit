package com.bosonit.block7crudvalidation.persona.controller.feign;

import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081/profesor", name = "personaFeign")
public interface PersonaFeign {
    @GetMapping("/{id}")
    ProfesorOutputDTO getProfesor (@PathVariable String id);

}
