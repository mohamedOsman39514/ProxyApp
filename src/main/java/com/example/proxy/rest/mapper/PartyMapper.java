package com.example.proxy.rest.mapper;

import com.example.proxy.model.Party;
import com.example.proxy.model.Request;
import com.example.proxy.rest.dto.PartyDto;
import com.example.proxy.rest.dto.RequestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartyMapper {


    List<PartyDto> toPartyDtos(List<Party> parties);

    Party toParty(PartyDto partyDto);

    PartyDto toPartyDto(Party party);

}
