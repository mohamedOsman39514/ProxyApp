package com.example.proxy.model.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class JPAModel extends JPA{

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime updateAt;
}
