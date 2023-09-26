package com.company.shifo24.controller;


import com.company.shifo24.domains.dtos.request.ProfessionDTO;
import com.company.shifo24.domains.entity.Profession;
import com.company.shifo24.service.profession.ProfessionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profession")
public class ProfessionController {

    private final ProfessionService professionService;
    private final ModelMapper modelMapper;

    @Operation(
            description = "POST endpoint to create new profession",
            summary = "create profession"
    )
    @PostMapping
    public ResponseEntity<ProfessionDTO> create(
            @Valid @RequestBody ProfessionDTO professionDTO
    ) {
        ProfessionDTO profession = professionService.create(professionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(profession);
    }

    @Operation(
            description = "GET endpoint to get profession by ID",
            summary = "get by ID"
    )
    @GetMapping("/{ID}")
    public ResponseEntity<ProfessionDTO> getByID(
            @PathVariable Long ID
    ) {
        Profession profession = professionService.getByID(ID);
        return ResponseEntity.ok(modelMapper.map(profession, ProfessionDTO.class));
    }

    @Operation(
            description = "GET endpoint to get all professions",
            summary = "get all"
    )
    @GetMapping
    public ResponseEntity<List<ProfessionDTO>> getAll() {
        List<ProfessionDTO> allProfession = professionService.getAllProfession();
        return ResponseEntity.ok(allProfession);
    }

    @Operation(
            description = "GET endpoint to search profession by name," +
                          " you can search with part of name and will return List of professions",
            summary = "search"
    )
    @GetMapping("/search")
    public ResponseEntity<List<ProfessionDTO>> search(
            @RequestParam String name
    ) {
        List<ProfessionDTO> professions = professionService.search(name);
        return ResponseEntity.ok(professions);
    }

    @Operation(
            description = "PUT endpoint to update profession by ID",
            summary = "update"
    )
    @PutMapping("/{ID}")
    public ResponseEntity<ProfessionDTO> update(
            @PathVariable Long ID,
            @Valid @RequestBody ProfessionDTO professionDTO
    ) {
        ProfessionDTO updatedProfession = professionService.update(ID, professionDTO);
        return ResponseEntity.ok(updatedProfession);
    }

    @Operation(
            description = "DELETE endpoint to delete profession by ID",
            summary = "delete by ID"
    )
    @DeleteMapping("/{ID}")
    public ResponseEntity<String> delete(
            @PathVariable Long ID
    ) {
        professionService.delete(ID);
        return ResponseEntity.ok("Successfully deleted!");
    }

}