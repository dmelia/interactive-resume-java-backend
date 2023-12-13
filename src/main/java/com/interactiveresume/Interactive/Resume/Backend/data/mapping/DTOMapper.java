package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;

import java.util.ArrayList;
import java.util.List;

public interface DTOMapper<DTO, Model> {

    /**
     * Maps an instance of a Model class to a new instance of a DTO class
     *
     * @param model the Model which is to be mapped
     * @return the new instance of the DTO class
     */
    DTO mapModel(Model model);

    /**
     * Maps an instance of a DTO class to a new instance of a Model class
     *
     * @param dto the DTO which is to be mapped
     * @return the new instance of the Model class
     */
    Model mapDTO(DTO dto);

    /**
     * Maps a list of instances of the DTO class to a list of Models
     *
     * @param dtoList the list of DTOs
     * @return the new list of Model instances
     */
    default List<Model> mapDTOList(List<DTO> dtoList) {
        List<Model> models = new ArrayList<>();
        dtoList.forEach(dto -> {
            models.add(mapDTO(dto));
        });
        return models;
    }

    /**
     * Maps a list of instances of the Model class to a list of DTOs
     *
     * @param modelList the list of Models
     * @return the new list of DTO instances
     */
    default List<DTO> mapModelList(List<Model> modelList) {
        List<DTO> dtoList = new ArrayList<>();
        modelList.forEach(model -> {
            dtoList.add(mapModel(model));
        });
        return dtoList;
    }
}
