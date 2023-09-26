package com.company.shifo24.service.workplace;

import com.company.shifo24.domains.dtos.request.WorkplaceDTO;
import com.company.shifo24.domains.entity.Workplace;
import com.company.shifo24.exception.DuplicateValueException;
import com.company.shifo24.exception.ItemNotFoundException;
import com.company.shifo24.repository.WorkplaceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkplaceServiceImpl implements WorkplaceService{
    private final WorkplaceRepository workplaceRepository;
    private final ModelMapper modelMapper;

    @Override
    public WorkplaceDTO create(WorkplaceDTO workplaceDTO) {
        checkWorkplaceExists(workplaceDTO.getName());
        Workplace mappedWorkplace = modelMapper.map(workplaceDTO, Workplace.class);
        workplaceRepository.save(mappedWorkplace);
        return modelMapper.map(mappedWorkplace, WorkplaceDTO.class);
    }

    @Override
    public Workplace getByID(Long workplaceID) {
        return getWorkplaceByID(workplaceID);
    }

    @Override
    public List<WorkplaceDTO> getAllWorkplace() {
        return workplaceRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<WorkplaceDTO> search(String name) {
        return workplaceRepository.searchByName(name)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public WorkplaceDTO update(Long workplaceID, WorkplaceDTO workplaceDTO) {
        checkWorkplaceExists(workplaceDTO.getName());
        Workplace workplace = getWorkplaceByID(workplaceID);
        modelMapper.map(workplaceDTO, workplace);
        Workplace savedWorkplace = workplaceRepository.save(workplace);
        return modelMapper.map(savedWorkplace, WorkplaceDTO.class);
    }

    @Override
    public void delete(Long workplaceID) {
        if (!workplaceRepository.existsById(workplaceID))
            throw new ItemNotFoundException("Workplace not found with ID: " + workplaceID);
        workplaceRepository.deleteById(workplaceID);
    }

    private WorkplaceDTO toDTO(Workplace workplace) {
        return modelMapper.map(workplace, WorkplaceDTO.class);
    }

    private void checkWorkplaceExists(String name) {
        if (workplaceRepository.existsByName(name))
            throw new DuplicateValueException("Workplace already exists with Name: " + name);
    }

    private Workplace getWorkplaceByID(Long workplaceID) {
        return workplaceRepository.findById(workplaceID).orElseThrow(
                () -> new ItemNotFoundException("Workplace not found with ID: " + workplaceID)
        );
    }
}