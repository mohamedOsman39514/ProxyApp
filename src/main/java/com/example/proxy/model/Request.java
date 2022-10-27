package com.example.proxy.model;

import com.example.proxy.model.common.JPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request extends JPA {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    private List<Request> requests;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
