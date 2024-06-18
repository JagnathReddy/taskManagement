package com.example.taskManagement.pojos;

import com.example.taskManagement.entity.User;
import com.example.taskManagement.entity.UserInvite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteLinkRegistrationRequest {
    private UserInvite userInvite;
    private User user;
}
