package com.sammy.userservice.data.repositories;


import com.sammy.userservice.data.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

//    Optional<User> findByEmailIgnoreCaseOrPhoneNumber(String email, String phoneNumber);
//
//    Optional<User> findByEmail(String email);
//
//    Optional<User> findByPhoneNumber(String phoneNumber);
//
   Optional<User> findByIdAndActive(String id, boolean active);

    Optional<User> findByEmailAndActive(String email, boolean active);

    Optional<User> findByPhoneNumberAndActive(String phoneNumber, boolean active);

    Page<User> findAllByActive(boolean active, Pageable pageable);


    @Query("{ '$and': [ { 'active': ?1 }, { '$or': [ { 'firstName': { $regex: ?0, $options: 'i' } }, { 'lastName': { $regex: ?0, $options: 'i' } }, { 'email': { $regex: ?0, $options: 'i' } } ] } ] }")
    Page<User> searchUsers(String query, boolean isActive, Pageable pageable);

}