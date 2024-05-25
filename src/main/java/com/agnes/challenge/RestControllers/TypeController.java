package com.agnes.challenge.RestControllers;

import com.agnes.challenge.DTO.TypeDTO;
import com.agnes.challenge.Services.TypeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TypeController {

    final
    TypeService typeService;

    public TypeController(final TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping(value = "/types", produces = "application/json")
    public ResponseEntity<List<TypeDTO>> getAllTypes() {

        List<TypeDTO> allTypesDTO = typeService.getAllTypes();
        return new ResponseEntity<>(allTypesDTO, HttpStatus.OK);
    }
}
