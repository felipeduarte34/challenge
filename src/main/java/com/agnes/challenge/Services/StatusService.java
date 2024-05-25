package com.agnes.challenge.Services;

import com.agnes.challenge.DTO.StatusDTO;
import com.agnes.challenge.Entities.Status;
import com.agnes.challenge.Repositories.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    final
    StatusRepository statusRepository;

    public StatusService(final StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Transactional
    public Status getStatusByName(String statusName) {

        Optional<Status> statusByName = statusRepository.findByStatusName(statusName);

        if (statusByName.isPresent()) {
            return statusByName.get();
        } else {
            throw new RuntimeException("Could not find Status with name: " + statusName);
            //todo ELSE throw exception
        }
    }

    @Transactional
    public List<StatusDTO> getAllStatus() {

        List<Status> allStatus = statusRepository.findAll();
        List<StatusDTO> statusDTOS = new ArrayList<>();

        for (Status status : allStatus) {
            StatusDTO temp = new StatusDTO();
            temp.statusId = status.getStatusId();
            temp.statusName = status.getStatusName();
            temp.statusOfProject = status.getStatusName();
            statusDTOS.add(temp);
        }
        return statusDTOS;
    }

    @Transactional
    public void saveNewStatus(Status statusName) {

        statusRepository.save(statusName);
    }

    @Transactional
    public void deleteStatusById(Integer id) {

        statusRepository.deleteById(id);
    }
}
