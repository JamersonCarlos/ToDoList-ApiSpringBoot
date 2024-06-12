package api.todo_list_api.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api.todo_list_api.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    
    @Query("SELECT u FROM UserModel u WHERE u.email =:email")
    UserModel findByUsername(@Param("email") String email); 

}
