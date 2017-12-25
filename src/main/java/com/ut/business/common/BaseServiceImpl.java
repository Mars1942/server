package com.ut.business.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.util.*;

@Service
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<T extends AbstractPersistable> implements BaseService<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Override
    public T get(String id) throws Exception {
        return getRepository().findOne(id);
    }

    @Transactional
    @Override
    public T save(T t) throws Exception {
        DateFormat df2 = DateFormat.getDateTimeInstance();//可以精确到时分秒
        logger.info("记录操作日志：添加" + t + "--时间：" + new Date().toString());
        return getRepository().save(t);
    }

    @Transactional
    @Override
    public Collection<T> saveAll(Collection<T> es) throws Exception {
        Collection<T> c = new Collection<T>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<T> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T1> T1[] toArray(T1[] a) {
                return null;
            }

            @Override
            public boolean add(T t) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends T> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        for (T t : es) {
            save(t);
            c.add(t);
        }
        return c;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        getRepository().delete(id);
        logger.info("记录操作日志：删除" + id + "--时间：" + new Date().toString());
    }

    @Transactional
    @Override
    public void deleteAll(String[] ids) throws Exception {
        deleteAll(Arrays.asList(ids));
    }

    @Transactional
    @Override
    public void deleteAll(Collection<String> ids) throws Exception {
        for (String id : ids) {
            delete(id);
        }
    }

    @Transactional
    @Override
    public T update(T t) throws Exception {
        logger.info("记录操作日志：修改" + t + "--时间：" + new Date().toString());
        return getRepository().save(t);
    }

    @Transactional
    @Override
    public Collection<T> updateAll(Collection<T> es) throws Exception {
        Collection<T> c = new Collection<T>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<T> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T1> T1[] toArray(T1[] a) {
                return null;
            }

            @Override
            public boolean add(T t) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends T> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        for (T t : es) {
            update(t);
            c.add(t);
        }
        return c;
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) throws Exception {
        return getRepository().findAll(sort);
    }

    @Override
    public List<T> findAll(Collection<String> ids) throws Exception {
        return getRepository().findAll(ids);
    }

    @Override
    public BackResult<Page<T>> pageAll(PageParam pageParam) throws Exception {
        PageRequest pageRequest = new PageRequest(pageParam.getPage() - 1, pageParam.getSize());
        Page<T> page = getRepository().findAll(pageRequest);
        BackResult<Page<T>> backResult = new BackResult<>(page);
        return backResult;
    }

    @Override
    public BackResult<Page<T>> pageAll(PageParam pageParam, Sort sort) throws Exception {
        PageRequest pageRequest = new PageRequest(pageParam.getPage() - 1, pageParam.getSize(), sort);
        Page<T> page = getRepository().findAll(pageRequest);
        BackResult<Page<T>> backResult = new BackResult<>(page);
        return backResult;
    }

    protected List<T> search(Specification<T> spec) throws Exception {
        return getRepository().findAll(spec);
    }

    protected List<T> search(Specification<T> spec, Sort sort) throws Exception {
        return getRepository().findAll(spec, sort);
    }

    protected BackResult<Page<T>> search(PageParam pageParam, Specification<T> spec, Sort sort) throws Exception {
        PageRequest pageRequest = new PageRequest(pageParam.getPage() - 1, pageParam.getSize(), sort);
        Page<T> page = getRepository().findAll(spec, pageRequest);
        BackResult<Page<T>> backResult = new BackResult<>(page);
        return backResult;
    }

    protected long count(Specification<T> spec) throws Exception {
        return getRepository().count(spec);
    }

    protected abstract BaseRepository<T> getRepository();

}
