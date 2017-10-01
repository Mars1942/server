package com.ut.business.user.repository;

import com.ut.business.user.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByName(String name);

    // where name like ?% and age <?
    List<User> findByNameStartingWithAndAgeLessThan(String name, Integer age);

    // where name like %? and age <?
    List<User> findByNameEndingWithAndAgeLessThan(String name, Integer age);

    // where name in (?,?....) or age <?
    List<User> findByNameInOrAgeLessThan(List<String> names, Integer age);

    // where name in (?,?....) and age <?
    List<User> findByNameInAndAgeLessThan(List<String> names, Integer age);


    @Query("select o from User o where id=(select max(id) from User t1)")
    User getUserByMaxId();

    @Query("select o from User o where o.name=?1 and o.age=?2")
    List<User> queryParams1(String name, Integer age);

    @Query("select o from User o where o.name=:name and o.age=:age")
    List<User> queryParams2(@Param("name") String name, @Param("age") Integer age);

    @Query("select o from User o where o.name like %?1%")
    List<User> queryLike1(String name);

    @Query("select o from User o where o.name like %:name%")
    List<User> queryLike2(@Param("name") String name);

    @Query(nativeQuery = true, value = "select count(1) from User")
    long getCount();

    @Modifying
    @Query("update User o set o.age = :age where o.id = :id")
    void update(@Param("id") String id, @Param("age") Integer age);

}
