package com.example.myauthenticationserver.facade;

import com.example.myauthenticationserver.error.UnprocessableEntityException;
import lombok.NonNull;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ModelMapperService implements IModelMapper{

    private ModelMapper modelMapper;

    @PostConstruct
    protected void initialize() {
        modelMapper = new ModelMapper();
    }

    public <D> D map(@NonNull Object source, @NonNull Class<D> destinationType) {
        try {
            return this.modelMapper.map(source, destinationType);
        } catch (IllegalArgumentException | ConfigurationException | MappingException e) {
            throw new UnprocessableEntityException("Unprocessable entity. Cannot convert this entity.");
        }
    }

}
