package com.example.taskManagement.repository;

import com.example.taskManagement.entity.UserInvite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInviteRepository extends JpaRepository<UserInvite,Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM user_invite ui where ui.id=:id")
    void deleteInvite(@Param("id")Long id);
}
