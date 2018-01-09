package com.ut.business.course.service;

import com.ut.business.course.controller.CourseVo;
import com.ut.business.course.domain.Course;
import com.ut.business.course.domain.UserToCourse;
import com.ut.business.course.repository.CourseRepository;
import com.ut.business.course.repository.UserToCourseRepository;
import com.ut.business.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserToCourseRepository userToCourseRepository;

    @Transactional
    public String selCourse(String id, User user) {
        Course course = courseRepository.findOne(id);
        if (course.getHasCount() < course.getCount()) {
            List<UserToCourse> utcList = userToCourseRepository.findByCourseIdAndUserId(id,user.getId());
            if (!utcList.isEmpty()) {
                return "你已经选择了该课程";
            }
            course.setHasCount(course.getHasCount() + 1);
            UserToCourse userToCourse = new UserToCourse();
            userToCourse.setUser(user);
            userToCourse.setCourse(course);
            userToCourseRepository.save(userToCourse);
            courseRepository.save(course);
            return "";
        }
        return "该课程选人已满";
    }

    @Transactional
    public String save(Course course, List<String> userIds) throws Exception{
        String id = courseRepository.save(course).getId();
        if (userIds != null) {
            for (String userId: userIds) {
                UserToCourse userToCourse = new UserToCourse();
                course.setId(id);
                userToCourse.setCourse(course);
                User user = new User();
                user.setId(userId);
                userToCourse.setUser(user);
                userToCourseRepository.save(userToCourse);
            }
        }

        return id;
    }

    @Transactional
    public String update(Course course, List<String> userIds) {
        String id = courseRepository.save(course).getId();
        List<UserToCourse> list = userToCourseRepository.findByCourseId(id);
        if (!list.isEmpty()){
            for (UserToCourse userToCourse:list) {
                userToCourseRepository.delete(userToCourse);
            }
        }
        if (userIds != null) {
            for (String userId:userIds) {
                UserToCourse userToCourse = new UserToCourse();
                course.setId(id);
                userToCourse.setCourse(course);
                User user = new User();
                user.setId(userId);
                userToCourse.setUser(user);
                userToCourseRepository.save(userToCourse);
            }
        }
        return id;
    }

    @Transactional
    public void del(String id) throws Exception {
        courseRepository.delete(id);
//        List<UserToCourse> list = userToCourseRepository.findByCourseId(id);
//        for (UserToCourse userToCourse : list) {
//            userToCourseRepository.delete(userToCourse);
//        }
    }

    @Transactional
    public void delUser(String id,String userId) throws Exception {
        List<UserToCourse> list = userToCourseRepository.findByCourseIdAndUserId(id,userId);
        if (list != null) {
            for (UserToCourse entity:list) {
                userToCourseRepository.delete(entity);
            }
            Course course = courseRepository.findOne(id);
            course.setHasCount(course.getHasCount() - 1);
            courseRepository.save(course);
        }
    }

    public Course findById(String id) {
        return courseRepository.findOne(id);
    }

    public Page<Course> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("name");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return courseRepository.findAll(request);
    }

    public Page<Course> search(final CourseVo courseVo, final int pageNumber, final int pageSize) {
        Specification<Course> spec = new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (courseVo.getUserId() != null && !courseVo.getUserId().equals("")) {
                    if (courseVo.getIsTeacher() != null && courseVo.getIsTeacher() == 1) {
                        Predicate p1 = cb.equal(root.get("teacherId").as(String.class), courseVo.getUserId());
                        query.where(cb.and(p1));
                    } else {
                        Join<Course, UserToCourse> join = root.join("uTocList", JoinType.INNER);
                        Predicate p1 = cb.equal(join.get("user").get("id").as(String.class), courseVo.getUserId());
                        query.where(cb.and(p1));
                    }
                }
                if (!courseVo.getIds().isEmpty()) {
                    CriteriaBuilder.In<String> in = cb.in(root.<String>get("id"));
                    for (String id:courseVo.getIds()) {
                        in.value(id);
                    }
                    query.where(cb.not(in));
                }
                if (courseVo.getName() != null && !courseVo.getName().equals("")) {
                    Predicate p1 = cb.like(root.<String>get("name"), courseVo.getName() + "%");
                    query.where(cb.and(p1));
                }
                return query.getRestriction();
            }
        };
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.DESC,"name");
        return courseRepository.findAll(spec,pageable);
    }

    public List<Course> findByUserId(final String userId) {
        Specification<Course> spec = new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                Join<Course, UserToCourse> join = root.join("uTocList", JoinType.INNER);
                predicate.getExpressions().add(cb.equal(join.get("user").get("id"),userId));
                return predicate;
            }
        };
        return courseRepository.findAll(spec);
    }
}
