package com.agnes.challenge.EntityConverter;

import com.agnes.challenge.DTO.StatusDTO;
import com.agnes.challenge.Entities.Status;
import org.springframework.stereotype.Service;

@Service
public class StatusEntityConverter implements EntityConverter<Status, StatusDTO> {

    @Override
    public StatusDTO convertToDTO(Status statusEntity) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.statusName = statusEntity.getStatusName();
        statusDTO.statusId = statusEntity.getStatusId();
        return statusDTO;
    }

    @Override
    public Status convertToEntity(StatusDTO statusDTO) {

        Status statusEntity = new Status();
        statusEntity.setStatusName(statusDTO.statusName);
        return statusEntity;
    }
}
