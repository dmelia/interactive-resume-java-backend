package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import java.util.Collection;

public interface DTOMapper<DTO, Model> {
    public DTO mapModel(Model model);
    public Model mapDTO(DTO dto);
}
