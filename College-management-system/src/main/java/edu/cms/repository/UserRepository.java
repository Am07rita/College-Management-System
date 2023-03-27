package edu.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cms.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
