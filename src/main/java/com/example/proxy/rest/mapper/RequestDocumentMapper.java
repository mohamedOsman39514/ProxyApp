package com.example.proxy.rest.mapper;

import com.example.proxy.model.RequestDocument;
import com.example.proxy.rest.dto.RequestDocumentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestDocumentMapper {

    List<RequestDocumentDto> toDocumentDtos(List<RequestDocument> documents);

    RequestDocument toDocument(RequestDocumentDto requestDocumentDto);

    RequestDocumentDto toDocumentDto(RequestDocument document);

}
