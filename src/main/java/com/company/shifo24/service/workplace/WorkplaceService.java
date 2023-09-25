package com.company.shifo24.service.workplace;

import com.company.shifo24.domains.dtos.request.WorkplaceDTO;

import java.util.List;

public interface WorkplaceService {
    WorkplaceDTO create(WorkplaceDTO workplaceDTO);

    WorkplaceDTO getByID(Long workplaceID);

    List<WorkplaceDTO> getAllWorkplace();

    List<WorkplaceDTO> search(String name);

    WorkplaceDTO update(Long workplaceID, WorkplaceDTO workplaceDTO);

    void delete(Long workplaceID);
}
