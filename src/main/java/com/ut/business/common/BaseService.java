package com.ut.business.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Collection;
import java.util.List;

/**
 * 公共service
 */
public interface BaseService<T extends AbstractPersistable>  {

    T get(String id) throws Exception;

    T save(T t) throws Exception;

    Collection<T> saveAll(Collection<T> es) throws Exception;

    void delete(String id) throws Exception;

    void deleteAll(Collection<String> ids) throws Exception;

    void deleteAll(String[] ids) throws Exception;

    T update(T t) throws Exception;

    Collection<T> updateAll(Collection<T> ts) throws Exception;

    List<T> findAll() throws Exception;

    List<T> findAll(Collection<String> ids) throws Exception;

    List<T> findAll(Sort sort) throws Exception;

    BackResult<Page<T>> pageAll(PageParam page) throws Exception;

    BackResult<Page<T>> pageAll(PageParam page, Sort sort) throws Exception;
}
