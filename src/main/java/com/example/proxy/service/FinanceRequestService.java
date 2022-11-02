//package com.example.proxy.service;
//
//import com.example.proxy.entity.FinanceRequest;
//import com.example.proxy.repository.FinanceRequestRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class FinanceRequestService {
//
////    @Autowired
//    private FinanceRequestRepository financeRequestRepository;
//
//
//    public FinanceRequest save(FinanceRequest financeRequest) {
//        return financeRequestRepository.save(financeRequest);
//    }
//
//    public Optional<FinanceRequest> findById(Long id) {
//        return financeRequestRepository.findById(id);
//    }
//
//    public Page<FinanceRequest> getAll(Integer page, Integer size) {
//        return financeRequestRepository.findAll(PageRequest.of(page, size));
//    }
//    public void deleteById(Long id)
//    {
//        financeRequestRepository.deleteById(id);
//    }
//
//}
