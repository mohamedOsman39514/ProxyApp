package com.example.proxy.rest.mapper;

import com.example.proxy.model.Governorate;
import com.example.proxy.rest.dto.GovernorateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GovernorateMapper {


    List<GovernorateDto> toGovernorateDtos(List<Governorate> governorates);

    Governorate toGovernorate(GovernorateDto governorateDto);

    GovernorateDto toGovernorateDto(Governorate governorate);

}
