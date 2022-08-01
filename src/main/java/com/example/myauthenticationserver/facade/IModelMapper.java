package com.example.myauthenticationserver.facade;

import lombok.NonNull;

public interface IModelMapper {

    <D> D map(@NonNull Object source, @NonNull Class<D> destinationType);

}
