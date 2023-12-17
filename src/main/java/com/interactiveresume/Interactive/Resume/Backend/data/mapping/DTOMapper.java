package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

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
