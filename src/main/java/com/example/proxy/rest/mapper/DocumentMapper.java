package com.example.proxy.rest.mapper;

import com.example.proxy.model.Document;
import com.example.proxy.rest.dto.DocumentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    List<DocumentDto> toDocumentDtos(List<Document> documents);

    Document toDocument(DocumentDto documentDto);

    DocumentDto toDocumentDto(Document document);

}
