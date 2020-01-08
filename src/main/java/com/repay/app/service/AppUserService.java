package com.repay.app.service;

import com.repay.app.dao.entity.AppUser;
import com.repay.app.dto.UserDto;
import com.repay.app.util.ResultObject;

public interface AppUserService {

	AppUser getUserByAccount(String account);

	ResultObject addUserInfo(AppUser user);

	ResultObject getList(UserDto dto);


}
