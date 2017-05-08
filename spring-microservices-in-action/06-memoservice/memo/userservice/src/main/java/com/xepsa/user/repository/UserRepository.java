package com.xepsa.user.repository;

import com.xepsa.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    public User findById(String id);

    public List<User> findByFamilyName(String familyName);

}
