package com.bosonit.block10cors.persona.controller.feign;

import com.bosonit.block10cors.profesor.controller.dto.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081/profesor", name = "personaFeign")
public interface PersonaFeign {
    @GetMapping("/{id}")
    ProfesorOutputDTO getProfesor (@PathVariable String id);

}
