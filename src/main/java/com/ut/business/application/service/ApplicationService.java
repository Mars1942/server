package com.ut.business.application.service;

import com.ut.business.application.domain.Application;
import com.ut.business.application.repository.ApplicationPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationPagingAndSortingRepository applicationRepository;

    @Transactional
    public String save(Application application) throws Exception {
        return applicationRepository.save(application).getId();
    }

    @Transactional
    public void del(String id) throws Exception {
        applicationRepository.delete(id);
    }

    public Application findById(String id) {
        return applicationRepository.findOne(id);
    }

    public Page<Application> findAll(int pageNumber, int pageSize) throws Exception {
        Sort sort = new Sort("sort");
        PageRequest request = new PageRequest(pageNumber, pageSize, sort);
        return applicationRepository.findAll(request);
    }
}
