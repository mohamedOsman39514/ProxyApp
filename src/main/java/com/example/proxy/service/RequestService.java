package com.example.proxy.service;

import com.example.proxy.model.Request;
import com.example.proxy.repository.RequestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestService {

//    @Autowired
    private RequestRepository requestRepository;


    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public Optional<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    public List<Request> findAll()
    {
        return requestRepository.findAll();
    }

    public List<Request> getAll(Integer page, Integer limit) {
//        List<Request> userDTOList = new ArrayList<>();

        if(page>0)
        {
            page = page-1;
        }

        Pageable pageable= PageRequest.of(page, limit);
        Page<Request> usersPage = requestRepository.findAll(pageable);

        List<Request> userEntityList = usersPage.getContent();

        for (Request userEntity : userEntityList)
        {
            Request userDTO = new Request();
            BeanUtils.copyProperties(userEntity, userDTO);
            userEntityList.add(userDTO);
        }

        return userEntityList;
    }

    public Page<Request> getAllRequests(Integer page, Integer size) {
        return requestRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteById(Long id)
    {
        requestRepository.deleteById(id);
    }

}
