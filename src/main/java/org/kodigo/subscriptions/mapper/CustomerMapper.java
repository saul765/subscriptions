package org.kodigo.subscriptions.mapper;


import org.kodigo.subscriptions.dto.CustomerDTO;
import org.kodigo.subscriptions.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    CustomerDTO entityToDto(CustomerEntity customerEntity);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    CustomerEntity dtoToEntity(CustomerDTO customerDTO);

}
