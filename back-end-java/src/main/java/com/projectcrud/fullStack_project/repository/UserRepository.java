package com.projectcrud.fullStack_project.repository;

import com.projectcrud.fullStack_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}
