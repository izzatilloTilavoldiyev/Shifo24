package com.company.shifo24.service.doctor;


import com.company.shifo24.domains.dtos.request.DoctorCreateDTO;
import com.company.shifo24.domains.dtos.response.DoctorResponseDTO;
import com.company.shifo24.domains.entity.*;
import com.company.shifo24.exception.ItemNotFoundException;
import com.company.shifo24.repository.DoctorRepository;
import com.company.shifo24.service.media.MediaService;
import com.company.shifo24.service.profession.ProfessionService;
import com.company.shifo24.service.workplace.WorkplaceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final ProfessionService professionService;
    private final MediaService mediaService;
    private final WorkplaceService workplaceService;

    @Override
    public DoctorResponseDTO create(DoctorCreateDTO doctorCreateDTO) {
        Doctor mappedDoctor = modelMapper.map(doctorCreateDTO, Doctor.class);
        Profession profession = professionService.getByID(doctorCreateDTO.getProfessionID());
        Media media = mediaService.getMediaById(doctorCreateDTO.getMediaID());
        Workplace workplace = workplaceService.getByID(doctorCreateDTO.getWorkplaceID());

        mappedDoctor.setProfession(profession);
        mappedDoctor.setMedia(media);
        mappedDoctor.setWorkplace(workplace);
        Doctor savedDoctor = doctorRepository.save(mappedDoctor);

        return modelMapper.map(savedDoctor, DoctorResponseDTO.class);
    }

    @Override
    public DoctorResponseDTO getByID(Long doctorID) {
        Doctor doctor = getDoctorByID(doctorID);
        return modelMapper.map(doctor, DoctorResponseDTO.class);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctor() {
        return doctorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public DoctorResponseDTO update(Long doctorID, DoctorResponseDTO doctorResponseDTO) {
        Doctor doctor = getDoctorByID(doctorID);
        modelMapper.map(doctorResponseDTO, doctor);
        if (doctorResponseDTO.getProfessionID() != null)
            doctor.setProfession(professionService.getByID(doctorResponseDTO.getProfessionID()));
        if (doctorResponseDTO.getMediaID() != null)
            doctor.setMedia(mediaService.getMediaById(doctorResponseDTO.getMediaID()));
        if (doctorResponseDTO.getWorkplaceID() != null)
            doctor.setWorkplace(workplaceService.getByID(doctorResponseDTO.getWorkplaceID()));
        Doctor savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor, DoctorResponseDTO.class);
    }

    @Override
    public List<DoctorResponseDTO> searchByName(String firsName) {
        return doctorRepository.searchByName(firsName)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void delete(Long doctorID) {
        if (!doctorRepository.existsById(doctorID))
            throw new ItemNotFoundException("Doctor not found with ID: " + doctorID);
        doctorRepository.deleteById(doctorID);
    }

    private DoctorResponseDTO toDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorResponseDTO.class);
    }

    private Doctor getDoctorByID(Long doctorID) {
        return doctorRepository.findById(doctorID).orElseThrow(
                () -> new ItemNotFoundException("Doctor not found with ID: " + doctorID)
        );
    }
}