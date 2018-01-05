package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.redis.client.JedisClient;
import com.taotao.result.TaotaoResult;
import com.taotao.sso.service.UserService;
import com.taotao.utils.JsonUtils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${USER_INFO}")
	private String USER_INFO;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	@Override
	public TaotaoResult checkData(String param, int type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if(type == 2){
			criteria.andPhoneEqualTo(param);
		}else if(type == 3){
			criteria.andEmailEqualTo(param);
		}else{
			return TaotaoResult.build(400, "非法的参数");
		}
		List<TbUser> list = userMapper.selectByExample(example);
		
		if (list == null || list.size() == 0 ) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult createUser(TbUser user) {
		if (StringUtils.isBlank(user.getUsername())) {
			return TaotaoResult.build(400, "用户名不能为空！");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			return TaotaoResult.build(400, "密码不能为空！");
		}
		
		TaotaoResult result = checkData(user.getUsername(), 1);
		if (!(boolean) result.getData()) {
			return TaotaoResult.build(400, "此用户名已经被使用！");
		}
		if (StringUtils.isNotEmpty(user.getPhone())) {
			TaotaoResult result1 = checkData(user.getPhone(), 2);
			if (!(boolean) result1.getData()) {
				return TaotaoResult.build(400, "此手机号已经被使用！");
			}
		}
		if (StringUtils.isNotEmpty(user.getEmail())) {
			TaotaoResult result1 = checkData(user.getEmail(), 2);
			if (!(boolean) result1.getData()) {
				return TaotaoResult.build(400, "此邮件地址已经被使用！");
			}
		}
		
		//补全其他属性
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//密码使用MD5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		userMapper.insert(user);
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult login(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.build(400, "用户名或密码错误！");
		}	
		TbUser user = list.get(0);
		String passw = user.getPassword();
		if (!passw.equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			return TaotaoResult.build(400, "用户名或密码错误！");
		}
		String token = UUID.randomUUID().toString();
		user.setPassword(null);
//		System.out.println("token1"+token);
		jedisClient.set(USER_INFO+":"+token, JsonUtils.objectToJson(user));
		jedisClient.expire(USER_INFO+":"+token, SESSION_EXPIRE);
//		System.out.println("token2"+token);
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		String json = jedisClient.get(USER_INFO+":"+token);
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "用户登录已到期，请重新登录！");
				
		}
		jedisClient.expire(USER_INFO+":"+token, SESSION_EXPIRE);
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		
		return TaotaoResult.ok(user);
		
	}

}
