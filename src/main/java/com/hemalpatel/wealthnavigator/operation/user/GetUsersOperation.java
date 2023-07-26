package com.hemalpatel.wealthnavigator.operation.user;

import com.hemalpatel.balance.flow.model.RestRequest;
import com.hemalpatel.balance.flow.model.RestResponse;
import com.hemalpatel.balance.flow.operation.IOperation;
import com.hemalpatel.wealthnavigator.domain.User;
import com.hemalpatel.wealthnavigator.payload.dto.UserModel;
import com.hemalpatel.wealthnavigator.payload.mapper.UserMapper;
import com.hemalpatel.wealthnavigator.payload.response.UserResponse;
import com.hemalpatel.wealthnavigator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUsersOperation implements IOperation<RestRequest<Void>, RestResponse<UserResponse>> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public RestResponse<UserResponse> handle(RestRequest<Void> restRequest) {
        List<User> userList = userRepository.findAll();
        List<UserModel> userModelList = userMapper.toUserModelList(userList);

        return RestResponse.valueOf(new

                UserResponse().setUsers(userModelList));
    }
}
