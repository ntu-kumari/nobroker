package com.nobroker.service.impl;

import com.nobroker.entity.User;
import com.nobroker.payload.UserDto;
import com.nobroker.repository.UserRepository;
import com.nobroker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper ModelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public long  createUser(UserDto UserDto) {
        User user = mapToEntity(UserDto);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    User mapToEntity(UserDto UserDto){
        User User = ModelMapper.map(UserDto, User.class);
        return User;
    }
}
