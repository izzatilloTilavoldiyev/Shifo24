package com.company.shifo24.controller;


import com.company.shifo24.domains.dtos.request.DoctorCreateDTO;
import com.company.shifo24.domains.dtos.response.DoctorResponseDTO;
import com.company.shifo24.service.doctor.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Operation(
            description = "POST endpoint to create doctor",
            summary = "create doctor"
    )
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(
            @Valid @RequestBody DoctorCreateDTO doctorCreateDTO
    ) {
        DoctorResponseDTO doctorDTO = doctorService.create(doctorCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorDTO);
    }

    @Operation(
            description = "GET endpoint to get doctor by ID",
            summary = "get by ID"
    )
    @GetMapping("/{doctorID}")
    public ResponseEntity<DoctorResponseDTO> getByID(
            @PathVariable Long doctorID
    ) {
        DoctorResponseDTO doctor = doctorService.getByID(doctorID);
        return ResponseEntity.ok(doctor);
    }

    @Operation(
            description = "GET endpoint to get all doctors",
            summary = "get all"
    )
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAll() {
        List<DoctorResponseDTO> allDoctor = doctorService.getAllDoctor();
        return ResponseEntity.ok(allDoctor);
    }

    @Operation(
            description = "PUT endpoint to update doctor",
            summary = "update"
    )
    @PutMapping("/{doctorID}")
    public ResponseEntity<DoctorResponseDTO> update(
            @PathVariable Long doctorID,
            @Valid @RequestBody DoctorResponseDTO doctorDTO
    ) {
        DoctorResponseDTO updatedDoctor = doctorService.update(doctorID, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    @Operation(
            description = "GET endpoint to search doctor by firsName," +
                    " you can search with part of name and will return List of doctors",
            summary = "search"
    )
    @GetMapping("/search")
    public ResponseEntity<List<DoctorResponseDTO>> search(
            @RequestParam String firstName
    ) {
        List<DoctorResponseDTO> doctors = doctorService.searchByName(firstName);
        return ResponseEntity.ok(doctors);
    }

    @Operation(
            description = "DELETE endpoint to delete doctor by ID",
            summary = "delete"
    )
    @DeleteMapping("/{doctorID}")
    public ResponseEntity<String> delete(
            @PathVariable Long doctorID
    ) {
        doctorService.delete(doctorID);
        return ResponseEntity.ok("Successfully deleted!");
    }
}