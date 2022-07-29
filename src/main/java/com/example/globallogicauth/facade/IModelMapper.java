package com.example.globallogicauth.facade;

import lombok.NonNull;

public interface IModelMapper {

    <D> D map(@NonNull Object source, @NonNull Class<D> destinationType);

}
