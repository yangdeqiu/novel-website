package cn.book.user.mapper;

import cn.book.comm.pojo.User;

public interface UserMapper {

	User queryOne(User user);

	void save(User user);

	int queryExistUser(String userName);

}
