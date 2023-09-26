package com.company.shifo24.service.profession;

import com.company.shifo24.domains.dtos.request.ProfessionDTO;
import com.company.shifo24.domains.dtos.request.WorkplaceDTO;
import com.company.shifo24.domains.entity.Profession;
import com.company.shifo24.exception.DuplicateValueException;
import com.company.shifo24.exception.ItemNotFoundException;
import com.company.shifo24.repository.ProfessionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository professionRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProfessionDTO create(ProfessionDTO professionDTO) {
        checkProfessionExists(professionDTO.getName());
        Profession mappedProfession = modelMapper.map(professionDTO, Profession.class);
        professionRepository.save(mappedProfession);
        return modelMapper.map(mappedProfession, ProfessionDTO.class);
    }

    @Override
    public Profession getByID(Long professionID) {
        return getProfessionByID(professionID);
    }

    @Override
    public List<ProfessionDTO> getAllProfession() {
        return professionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ProfessionDTO> search(String name) {
        return professionRepository.searchByName(name)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ProfessionDTO update(Long professionID, ProfessionDTO professionDTO) {
        checkProfessionExists(professionDTO.getName());
        Profession profession = getProfessionByID(professionID);
        modelMapper.map(professionDTO, profession);
        Profession savedProfession = professionRepository.save(profession);
        return modelMapper.map(savedProfession, ProfessionDTO.class);
    }

    @Override
    public void delete(Long professionID) {
        if (!professionRepository.existsById(professionID))
            throw new ItemNotFoundException("Profession not found with ID: " + professionID);
        professionRepository.deleteById(professionID);
    }

    private ProfessionDTO toDTO(Profession profession) {
        return modelMapper.map(profession, ProfessionDTO.class);
    }

    private void checkProfessionExists(String name) {
        if (professionRepository.existsByName(name))
            throw new DuplicateValueException("Profession already exists with Name: " + name);
    }

    public Profession getProfessionByID(Long professionID) {
        return professionRepository.findById(professionID).orElseThrow(
                () -> new ItemNotFoundException("Profession not found with ID: " + professionID)
        );
    }
}