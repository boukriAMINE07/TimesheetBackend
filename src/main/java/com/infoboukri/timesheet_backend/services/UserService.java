package com.infoboukri.timesheet_backend.services;


import com.infoboukri.timesheet_backend.exceptions.UserNotFoundException;
import com.infoboukri.timesheet_backend.securite.models.User;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User SaveUser(User user);
    List<User> allUsers();
    Page<User> getAllUsersWithPage(int page, int size);
    Page<User> getAllUsersByUserNameAndPage(String name,int page, int size);
    void deletetUser(Long id);
    User getUser(Long id) throws UserNotFoundException, UserNotFoundException;

}
