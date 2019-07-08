package com.mongodb.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author bo bo
 * @date 2019/6/15 10:05
 * @desc
 */
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);

}
