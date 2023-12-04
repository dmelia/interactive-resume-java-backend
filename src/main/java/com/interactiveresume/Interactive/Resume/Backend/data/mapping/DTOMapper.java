package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

public interface DTOMapper<DTO, Model> {

    /**
     * Maps an instance of a Model class to a new instance of a DTO class
     * @param model the Model which is to be mapped
     * @return the new instance of the DTO class
     */
    DTO mapModel(Model model);

    /**
     * Maps an instance of a DTO class to a new instance of a Model class
     * @param dto the DTO which is to be mapped
     * @return the new instance of the Model class
     */
    Model mapDTO(DTO dto);
}
