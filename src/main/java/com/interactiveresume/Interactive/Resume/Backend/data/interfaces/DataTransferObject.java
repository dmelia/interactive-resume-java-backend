package com.interactiveresume.Interactive.Resume.Backend.data.interfaces;

import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelField;

import java.lang.reflect.Field;
import java.util.Collection;

public interface DataTransferObject<Model> {

    default Model getModelFromDTO() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        Field[] dtoFields = this.getClass().getDeclaredFields();

        try {
            Model model = getModelClass().newInstance();

            for (Field dtoField : dtoFields) {
                if (dtoField.isAnnotationPresent(ModelField.class)) {
                    ModelField modelFieldAnnotation = dtoField.getAnnotation(ModelField.class);
                    String fieldName = modelFieldAnnotation.name();
                    Field modelField = getModelClass().getDeclaredField(fieldName);

                    dtoField.setAccessible(true);
                    modelField.setAccessible(true);

                    // Check if the field implements DataTransferObject and is a Collection
                    if (isCollectionField(dtoField) && DataTransferObject.class.isAssignableFrom(getGenericType(dtoField))) {
                        // Handle collection fields
                        handleCollectionField(dtoField, modelField, model);
                    } else {
                        // Transfer the value from DTO to Model for non-collection fields
                        modelField.set(model, dtoField.get(this));
                    }
                }
            }

            return model;
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw e;
        }
    }

    Class<Model> getModelClass();

    // Helper method to check if a field is a Collection
    default boolean isCollectionField(Field field) {
        return Collection.class.isAssignableFrom(field.getType());
    }

    // Helper method to get the generic type of a Collection field
    default Class<?> getGenericType(Field field) {
        try {
            java.lang.reflect.ParameterizedType genericType = (java.lang.reflect.ParameterizedType) field.getGenericType();
            return (Class<?>) genericType.getActualTypeArguments()[0];
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method to handle population of Collection fields
    default void handleCollectionField(Field dtoField, Field modelField, Model model) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Collection<?> dtoCollection = (Collection<?>) dtoField.get(this);
        Collection<Object> modelCollection = (Collection<Object>) modelField.get(model);

        for (Object dtoItem : dtoCollection) {
            if (dtoItem instanceof DataTransferObject) {
                // If the item in the collection implements DataTransferObject, call getModelFromDTO
                modelCollection.add(((DataTransferObject<?>) dtoItem).getModelFromDTO());
            } else {
                // Handle other types as needed
                modelCollection.add(dtoItem);
            }
        }
    }
}