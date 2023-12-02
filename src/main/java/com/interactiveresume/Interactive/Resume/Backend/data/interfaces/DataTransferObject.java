package com.interactiveresume.Interactive.Resume.Backend.data.interfaces;

import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelCollection;
import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelField;

import java.lang.reflect.Field;
import java.util.*;

public interface DataTransferObject<Model> {

    default Model getModelFromDTO() {
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

                    if (dtoField.isAnnotationPresent(ModelCollection.class)) {
                        // Handle fields annotated with ModelCollection
                        handleCollectionField(dtoField, modelField, model);
                    } else {
                        // Transfer the value from DTO to Model for non-collection fields
                        modelField.set(model, dtoField.get(this));
                    }
                }
            }

            return model;
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace(); // Handle exceptions based on your application's requirements
            return null;
        }
    }

    // Helper method to handle population of Collection fields
    default void handleCollectionField(Field dtoField, Field modelField, Model model) throws IllegalAccessException {
        ModelCollection modelCollectionAnnotation = dtoField.getAnnotation(ModelCollection.class);
        String fieldName = modelCollectionAnnotation.name();

        Collection<?> dtoCollection = (Collection<?>) dtoField.get(this);
        Collection<Object> modelCollection = (Collection<Object>) modelField.get(model);

        if (modelCollection == null) {
            // Initialize the modelCollection if it's null
            modelCollection = instantiateCollection(dtoField.getType());
            modelField.set(model, modelCollection);
        }

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

    // Helper method to instantiate a collection of the same type as the DTO collection
    default Collection<Object> instantiateCollection(Class<?> collectionType) {
        try {
            if (List.class.isAssignableFrom(collectionType)) {
                return new ArrayList<>();
            } else if (Set.class.isAssignableFrom(collectionType)) {
                return new HashSet<>();
            } else {
                // Add more collection types as needed
                throw new UnsupportedOperationException("Unsupported collection type: " + collectionType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    Class<Model> getModelClass();
}