package cn.book.comm.constVar;

/**
 * 系统常量类
 * @author tarena
 *
 */
public class Const {
	/**
	 *redis usercookie常量 
	 */
	public static final String EM_TICKET = "EM_TICKET";
	/**
	 * 用户前缀
	 */
	public static final String USER_ = "USER_"; 
	/**
	 * MD5加盐串
	 */
	public static final String MD5_SALT_PWD = "PWD";
	/**
	 * 缓存中的登录验证前缀
	 */
	public static final String LOGIN_ = "LOGIN_";
	/**
	 * 200-成功回显
	 */
	public static final Integer Success = 200;
	/**
	 * 201-失败回显
	 */
	public static final Integer Failed = 201; 
	/**
	 * 用户类别：0表示用户
	 */
	public static final Integer Type_User = 0;
	/**
	 * 用户类别：1表示作家
	 */
	public static final Integer Type_Auth = 1;
	/**
	 * 用户类别：2表示超级管理员
	 */
	public static final Integer Type_Dev = 2;
	/**
	 * 性别:1男2女3保密
	 */
	public static final Integer Type_StatusOver = 0;
	public static final Integer Type_StatusSerialize = 1;
	/**
	 * 小说的状态：0 完本,1连载
	 */
}
