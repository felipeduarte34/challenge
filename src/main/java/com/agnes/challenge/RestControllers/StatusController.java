package com.agnes.challenge.RestControllers;

import com.agnes.challenge.DTO.StatusDTO;
import com.agnes.challenge.Services.StatusService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StatusController {

    final
    StatusService statusService;

    public StatusController(final StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping(value = "/status", produces = "application/json")
    public ResponseEntity<List<StatusDTO>> getAllStatus() {

        List<StatusDTO> allStatusDTO = statusService.getAllStatus();
        return new ResponseEntity<>(allStatusDTO, HttpStatus.OK);
    }
}
