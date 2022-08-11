package com.infoboukri.timesheet_backend.services;

import com.infoboukri.timesheet_backend.securite.models.User;
import com.infoboukri.timesheet_backend.securite.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service @AllArgsConstructor @NoArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUsersWithPage(int page, int size) {
        Page<User> pageUsers=userRepository.findAll(PageRequest.of(page, size));
        return pageUsers;
    }

    @Override
    public Page<User> getAllUsersByUserNameAndPage(String name, int page, int size) {
        Page<User> pageUsers=userRepository.findByUsernameContaining(name,PageRequest.of(page, size));
        return pageUsers;
    }




}
