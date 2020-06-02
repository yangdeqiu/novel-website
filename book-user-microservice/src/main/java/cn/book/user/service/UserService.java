package cn.book.user.service;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.book.comm.constVar.Const;
import cn.book.comm.pojo.User;
import cn.book.comm.util.MD5Util;
import cn.book.comm.util.MapperUtil;
import cn.book.user.mapper.UserMapper;
import redis.clients.jedis.JedisCluster;

@Service
public class UserService {
	@Autowired
	private JedisCluster jedis;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 登录或顶替登录
	 * 
	 * @param user
	 * @return Cookie- ticket
	 */
	public String doLogin(User user) {
		String ticket = null;
		try {
			user.setPassword(MD5Util.md5(Const.MD5_SALT_PWD + user.getPassword()));
			User exist = userMapper.queryOne(user);
			if (exist == null) {
				return "";
			} else {
				String oTicket = jedis.get(Const.LOGIN_ + user.getUsername());
				if (StringUtils.isNotEmpty(oTicket)) {
					jedis.del(oTicket);
				}
				ticket = Const.EM_TICKET + System.currentTimeMillis() + user.getUsername();
				String userJson = MapperUtil.MP.writeValueAsString(exist);
				jedis.setex(ticket, 60 * 60 * 2, userJson);
				jedis.setex(Const.LOGIN_ + user.getUsername(), 60 * 60 * 2, ticket);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return ticket;
	}

	/**
	 * 页面刷新验证，超时续租
	 * 
	 * @param ticket
	 * @return userJson
	 */
	public String queryTicket(String ticket) {
		try {
			Long expireTime = jedis.pttl(ticket);
			if (expireTime < 1000 * 60 * 30) 
				jedis.pexpire(ticket, expireTime + 1000 * 60 * 30);
			return jedis.get(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 注册
	 * @param user
	 */
	public void save(User user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setPassword(MD5Util.md5(Const.MD5_SALT_PWD + user.getPassword()));
		user.setType(Const.Type_User);
		userMapper.save(user);
	}

	/**
	 * 验证用户可用
	 * @param userName
	 * @return 
	 */
	public int checkUserName(String userName) {
		return userMapper.queryExistUser(userName);
	}
}
