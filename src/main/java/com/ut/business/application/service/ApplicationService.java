package com.ut.business.application.service;

import com.ut.business.application.domain.Application;
import com.ut.business.application.repository.ApplicationPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

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

    public Application findById(String id)  throws Exception  {
        return applicationRepository.findOne(id);
    }

    public Page<Application> findAll(int pageNumber, int pageSize) throws Exception {
        Sort sort = new Sort("sort");
        PageRequest request = new PageRequest(pageNumber, pageSize, sort);
        return applicationRepository.findAll(request);
    }

    public List<Application> listAll()  throws Exception  {
        return (List<Application>) applicationRepository.findAll();
    }

    public Page<Application> search(final Application application, final int pageNumber, final int pageSize)  throws Exception {
        Specification<Application> spec = new Specification<Application>() {
            @Override
            public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (application.getName() != null && !application.getName().equals("")) {
                    Predicate p1 = cb.like(root.<String>get("name"), application.getName() + "%");
                    query.where(cb.and(p1));
                }
                return query.getRestriction();
            }
        };
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.DESC,"sort");
        return applicationRepository.findAll(spec,pageable);
    }
}
