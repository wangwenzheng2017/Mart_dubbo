package com.taotao.sso.service;

import com.taotao.pojo.TbUser;
import com.taotao.result.TaotaoResult;

public interface UserService {
	
	public TaotaoResult checkData(String param, int type);
	public TaotaoResult createUser(TbUser user);
}
