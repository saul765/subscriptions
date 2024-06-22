package org.kodigo.subscriptions.mapper;


import org.kodigo.subscriptions.dto.MerchantDTO;
import org.kodigo.subscriptions.entity.MerchantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MerchantMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "contactNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    MerchantDTO entityToDto(MerchantEntity merchantEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "contactNumber")
    @Mapping(source = "address", target = "address")
    MerchantEntity dtoToEntity(MerchantDTO merchantDTO);
}
