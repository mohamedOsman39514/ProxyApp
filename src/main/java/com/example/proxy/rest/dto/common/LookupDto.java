package com.example.proxy.rest.dto.common;

import com.example.proxy.rest.validation.InsertValidation;
import com.example.proxy.rest.validation.UpdateValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of = {"name"}, callSuper = true)
@ToString(of = {"name"}, callSuper = true)
public class LookupDto extends RestDto {

    private String name;

}
