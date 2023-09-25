package com.company.shifo24.controller;

import com.company.shifo24.domains.dtos.request.WorkplaceDTO;
import com.company.shifo24.service.workplace.WorkplaceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/workplace")
public class WorkplaceController {
    private final WorkplaceService workplaceService;

    @Operation(
            description = "POST endpoint to create new workplace",
            summary = "create workplace"
    )
    @PostMapping
    public ResponseEntity<WorkplaceDTO> create(
            @Valid @RequestBody WorkplaceDTO workplaceDTO
    ) {
        WorkplaceDTO workplace = workplaceService.create(workplaceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(workplace);
    }

    @Operation(
            description = "GET endpoint to get workplace by ID",
            summary = "get by ID"
    )
    @GetMapping("/{ID}")
    public ResponseEntity<WorkplaceDTO> getByID(
            @PathVariable Long ID
    ) {
        WorkplaceDTO workplaceDTO = workplaceService.getByID(ID);
        return ResponseEntity.ok(workplaceDTO);
    }

    @Operation(
            description = "GET endpoint to get all workplaces",
            summary = "get all"
    )
    @GetMapping
    public ResponseEntity<List<WorkplaceDTO>> getAll() {
        List<WorkplaceDTO> allWorkplace = workplaceService.getAllWorkplace();
        return ResponseEntity.ok(allWorkplace);
    }

    @Operation(
            description = "GET endpoint to search workplace by name, " +
                          "you can search with part of name and will return List of workplaces",
            summary = "search"
    )
    @GetMapping("/search")
    public ResponseEntity<List<WorkplaceDTO>> search(
            @RequestParam String name
    ) {
        List<WorkplaceDTO> workplaces = workplaceService.search(name);
        return ResponseEntity.ok(workplaces);
    }

    @Operation(
            description = "PUT endpoint to update workplace by ID",
            summary = "update"
    )
    @PutMapping("/{ID}")
    public ResponseEntity<WorkplaceDTO> update(
            @PathVariable Long ID,
            @Valid @RequestBody WorkplaceDTO workplaceDTO
    ) {
        WorkplaceDTO updatedWorkplace = workplaceService.update(ID, workplaceDTO);
        return ResponseEntity.ok(updatedWorkplace);
    }

    @Operation(
            description = "DELETE endpoint to delete workplace by ID",
            summary = "delete by ID"
    )
    @DeleteMapping("/{ID}")
    public ResponseEntity<String> delete(
            @PathVariable Long ID
    ) {
        workplaceService.delete(ID);
        return ResponseEntity.ok("Successfully deleted!");
    }
}