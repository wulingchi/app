package com.repay.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.repay.app.dao.entity.AppUser;
import com.repay.app.service.AppUserService;
import com.repay.app.service.SessionService;
import com.repay.app.util.MD5;
import com.repay.app.util.ResultObject;
import com.repay.app.vo.LogUser;
import com.repay.app.vo.LoginInfoVo;

@Api(value = "会话操作",description="会话操作")
@RestController
@RequestMapping("/v1/sessions")
public class UserLoginController{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
    private SessionService sessionService;// 会话服务(TOKEN及用户登录状态管理)


	@ApiOperation(value = "创建会话，用户登录", httpMethod = "POST", response = LoginInfoVo.class, notes = "创建会话，即用户登录")
	@RequestMapping(value="login" ,method = RequestMethod.POST)
	public ResultObject login(HttpServletRequest request, @RequestBody LoginInfoVo loginInfo){
		ResultObject resp = ResultObject.getSuccessResult("登录成功");
		try {
			final String account = loginInfo.getAccount();
			if(StringUtils.isEmpty(account)){
				return ResultObject.getFailResult("账号不能为空");
			}
			final String password = loginInfo.getPassword();
			if(StringUtils.isEmpty(password)){
				return ResultObject.getFailResult("密码不能为空");
			}
			AppUser user = appUserService.getUserByAccount(account);
			if (null == user) {
				LOGGER.error("can't find user by account={}", account);
				return ResultObject.getFailResult("账号不存在");
			}else{
				if(!MD5.isEquals(loginInfo.getPassword(),user.getPassword())){
					return ResultObject.getFailResult("用户密码不正确");
				}
				//生成token
				String token = sessionService.saveToken(user);
				LogUser l = new LogUser();
				l.setAccount(account);
				l.setToken(token);
				l.setName(user.getName());
				resp.setData(l);
				return resp;
			}
		} catch (Exception e) {
			LOGGER.error("服务器网络故障", e);
			return ResultObject.getFailResult("服务器网络故障");
		}
	}
	
	@ApiOperation(value = "用户注册", httpMethod = "POST", response = AppUser.class, notes = "用户注册")
	@RequestMapping(value="add" ,method = RequestMethod.POST)
	public ResultObject addUser(@RequestBody AppUser user){
		ResultObject ro = ResultObject.getSuccessResult("注册成功");
		try{
			ro = appUserService.addUserInfo(user);
		}catch(Exception e){
			LOGGER.error("用户注册异常", e);
			return ResultObject.getFailResult("用户注册失败");
		}
		return ro;
		
	}
	
	
	@ApiOperation(value = "用户注销", httpMethod = "POST", response = AppUser.class, notes = "用户注销")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "token", value = "token值", required = true, dataType = "String", paramType = "header")
	   })
	@RequestMapping(value="destroy" ,method = RequestMethod.POST)
	public ResultObject destroyUser(HttpServletRequest request){
		ResultObject ro = ResultObject.getSuccessResult("注销成功");
		try{
			String token = request.getHeader("token");
			sessionService.destoryToken(token);
		}catch(Exception e){
			LOGGER.error("用户注销异常", e);
			return ResultObject.getFailResult("用户注销失败");
		}
		return ro;
		
	}


/*	@ApiOperation(value = "删除会话，用户注销",
			httpMethod = "DELETE",
			response = ApiResponse.class,
			notes = "根据会话ID删除会话，即用户注销")
	@RequestMapping(value = "logout/{token}", method = RequestMethod.DELETE)
	public Resp logout(
			@ApiParam(required = true, name = "token", value = "会话ID")
			@PathVariable String token) {
		try {
			final boolean validate = sessionService.isTokenValid(token);
			if(!validate){
				LOGGER.error("token is invalidate, token={}", token);
				return Resp.failed("logout failed");
			}
			sessionService.destroyToken(token);
			return Resp.success();
		} catch (Exception e) {
			String respMsg = "logout failed";
			LOGGER.error(respMsg, e);
			return Resp.failed(respMsg);
		}
	}
	
	@ApiOperation(value = "根据工号清除token",httpMethod = "DELETE",response = ApiResponse.class,notes = "根据工号清除token")
	@RequestMapping(value = "logoutToken/{workNo}", method = RequestMethod.DELETE)
	public Resp logoutByWorkNo(@ApiParam(required = true, name = "token", value = "会话ID")@PathVariable String workNo){
		try{
			sessionService.logoutByWorkNo(workNo);
			return Resp.success();
		}catch(Exception e){
			String respMsg = "logoutToken failed";
			LOGGER.error(respMsg, e);
			return Resp.failed(respMsg);
		}
	}*/


/*	@ApiOperation(value = "App端登录", httpMethod = "POST", response = AppResultUserVo.class, notes = "App端登录")
	@RequestMapping(value = "/appLogin", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(required = false, name = "X-appOS", value = "操作系统", paramType = "header", dataType = "string"),
			@ApiImplicitParam(required = false, name = "X-version", value = "版本号", paramType = "header", dataType = "string"),
	})
	public Resp appLogin(HttpServletRequest request, @RequestBody AppLoginVo loginInfo) {
		String appOS = request.getHeader("X-appOS");
		String version = request.getHeader("X-version");

		if(appOS==null || version ==null){
			return Resp.failed("您的App不是最新版本，请升级！ ");
		}

		final String loginId = loginInfo.getLoginId();
		if (StringUtils.isEmpty(loginId)) {
			return Resp.failed("账号不能为空");
		}
		final String password = loginInfo.getPassword();
		if (StringUtils.isEmpty(password)) {
			return Resp.failed("密码不能为空");
		}

		final BaseUserNewVo user = userService.getUserByLoginId(loginId);
		if (null == user) {
			LOGGER.error("can't find user by userName={}", loginId);
			return Resp.failed("账号或密码错误");
		}
		// 用户无效
		if (user.getValidate().equals("1")) {
			LOGGER.error("user invalidate userName={}", loginId);
			return Resp.failed("账号或密码错误");
		}

		// 去 AD 认证
		LoginInfoVo logintoken = new LoginInfoVo();
		logintoken.setPassword(loginInfo.getPassword());
		logintoken.setUserName(loginInfo.getLoginId());
		if (adAuthenticationService.login(logintoken, user)) {


			final AppResultUserVo loginResult = new AppResultUserVo();

			String source = "";
			if (loginInfo.getComponentName() == null || loginInfo.getComponentName().equals("")) {
				source = "sellhouse";
				loginInfo.setComponentName(source);
			} else {
				source = loginInfo.getComponentName();
			}

			// gen token
			final String workNo = user.getWorkNo();
			String token = sessionService.genAndSaveToken(workNo, source);
			loginResult.setToken(token);

			// 对于非管理员，才缓存其功能权限，管理员不需要缓存
			final boolean isAdmin = isAdmin(workNo);
			if (!isAdmin) {
				cacheFunctionPermissionIds(workNo);
			}
			// 缓存其数据权限
			cachePromission(workNo);

			loginResult.setUserId(loginId);
			loginResult.setUserName(user.getUserName());
			loginResult.setHeaderImg(ossClientConfiguration.getBucketDomain()+user.getHeaderImg());
			loginResult.setDeclaration(user.getDeclaration());
			loginResult.setPhone(user.getPhone());
			loginResult.setEmail(user.getEmail());
			loginResult.setWechat(user.getWechat());
			if(workNo != null && !workNo.isEmpty()){
				String userName = user.getUserName();
				if(userName == null || userName.isEmpty()){
					userName = "置业顾问-"+workNo;
				}
				ImToken imToken = imUserService.getToken(user.getWorkNo(), userName);
				loginResult.setImToken(imToken.getToken());
			}
			
			// 查询案场信息
			Integer caseId = getCaseIdFromHeader();

			List<HouseCaseInfoVo> caseInfo = caseService.findCaseInfo(workNo, caseId);
			if(caseInfo.size() !=0){
				for (int i = 0; i < caseInfo.size(); i++) {
					//如果存在多个案场，默认不返回全民营销
					if(caseInfo.size()>1 && caseInfo.get(i).getCaseId() == 40000027){
						continue;
					}
					loginResult.setCaseId(caseInfo.get(i).getCaseId());
					loginResult.setCaseName(caseInfo.get(i).getCaseName());
					loginResult.setCityId(caseInfo.get(i).getCityId());
					loginResult.setCityName(caseInfo.get(i).getCityName());
					loginResult.setIsExclusiveSale(caseInfo.get(i).getIsExclusiveSale());
				}
			}else{
				//如果登录的时候案场ID不对或第一次登录，默认给返回个正确的值
				List<HouseCaseInfoVo> caseInfos = caseService.findCaseInfo(workNo, null);
				if(caseInfos.size() == 0){
					caseService.insertALLSale(workNo, 40000027);
					HouseCaseInfoVo houseCase = caseService.selectInfoByCaseId(40000027);
					loginResult.setCaseId(houseCase.getCaseId());
					loginResult.setCaseName(houseCase.getCaseName());
					loginResult.setCityId(houseCase.getCityId());
					loginResult.setCityName(houseCase.getCityName());
					loginResult.setIsExclusiveSale(houseCase.getIsExclusiveSale());
//					return Resp.failed("没有对应案场，请联系您所在案场的专案");
				}
				for (int i = 0; i < caseInfos.size(); i++) {
					//如果存在多个案场，默认不返回全民营销
					if(caseInfo.size()>1 && caseInfo.get(i).getCaseId() == 40000027){
						continue;
					}
					loginResult.setCaseId(caseInfos.get(i).getCaseId());
					loginResult.setCaseName(caseInfos.get(i).getCaseName());
					loginResult.setCityId(caseInfos.get(i).getCityId());
					loginResult.setCityName(caseInfos.get(i).getCityName());
					loginResult.setIsExclusiveSale(caseInfos.get(i).getIsExclusiveSale());
				}
			}


			final Resp resp = Resp.success();
			resp.setData(loginResult);
			//修改用户最后登录时间
//			executor.execute(new Runnable() {
//				@Override
//				public void run() {
//					userService.updateLastLogin(loginId,loginInfo.getComponentName());
//				}
//			});
			return resp;
		} else {
			return Resp.failed("账号或密码错误");
		}
	}*/


/*	@ApiOperation(value = "案场App端登录", httpMethod = "POST", response = CaseAppResultUserVo.class, notes = "案场App端登录")
	@RequestMapping(value = "/caseAppLogin", method = RequestMethod.POST)
	public Resp caseAppLogin(HttpServletRequest request, @RequestBody AppLoginVo loginInfo) {
		final String loginId = loginInfo.getLoginId();
		if (StringUtils.isEmpty(loginId)) {
			return Resp.failed("账号不能为空");
		}
		final String password = loginInfo.getPassword();
		if (StringUtils.isEmpty(password)) {
			return Resp.failed("密码不能为空");
		}

		final BaseUserNewVo user = userService.getUserByLoginId(loginId);
		if (null == user) {
			LOGGER.error("can't find user by userName={}", loginId);
			return Resp.failed("账号或密码错误");
		}
		// 用户无效
		if (user.getValidate().equals("1")) {
			LOGGER.error("user invalidate userName={}", loginId);
			return Resp.failed("账号或密码错误");
		}

		// 去 AD 认证
		LoginInfoVo logintoken = new LoginInfoVo();
		logintoken.setPassword(loginInfo.getPassword());
		logintoken.setUserName(loginInfo.getLoginId());
		if (adAuthenticationService.login(logintoken, user)) {

			//需要验证权限
//			boolean flag = roleRelationService.isCaseAppUserAuthorization(user.getLoginId(), 1, 3);
//			if(!flag){
//				LOGGER.error("账号没有权限登录", loginId);
//				return Resp.failed("该账号没有权限使用该系统！");
//			}
			final CaseAppResultUserVo loginResult = new CaseAppResultUserVo();

			// gen token
			final String workNo = user.getWorkNo();
			final String componentName = loginInfo.getComponentName();
			String token = sessionService.genAndSaveToken(workNo, componentName);
			loginResult.setToken(token);

			// 对于非管理员，才缓存其功能权限，管理员不需要缓存
			final boolean isAdmin = isAdmin(workNo);
			if (!isAdmin) {
				cacheFunctionPermissionIds(workNo);
			}
			// 缓存其数据权限
			cachePromission(workNo);

			loginResult.setUserId(loginId);
			loginResult.setUserName(user.getUserName());
			loginResult.setHeaderImg(ossClientConfiguration.getBucketDomain()+user.getHeaderImg());
			loginResult.setDeclaration(user.getDeclaration());
			loginResult.setPhone(user.getPhone());

			final List<Integer> orgIds = dataService.getOrgIdsByloginUser(workNo);

			//获取子公司级别的组织架构
			final List<BaseOrganizational> orgListByLevel = dataService.getOrgListByLevel("30");
			final List<BaseOrganizational> orgListByLevelTO20 = dataService.getOrgListByLevel("20");

			final List<Integer> orgLevelIds = orgListByLevel.stream().map(BaseOrganizational::getOrgId).collect(Collectors.toList());
			final List<Integer> orgLevel20Ids = orgListByLevelTO20.stream().map(BaseOrganizational::getOrgId).collect(Collectors.toList());


			List<Integer> copyOrdIs1 = Lists.newArrayList();
			List<Integer> copyOrdIs2 = Lists.newArrayList();


			copyOrdIs1.addAll(orgIds);
			copyOrdIs2.addAll(orgIds);

			//取交集
			copyOrdIs1.retainAll(orgLevelIds);
			copyOrdIs2.retainAll(orgLevel20Ids);

			if (copyOrdIs1.size() > 0 || copyOrdIs2.size() >0 ){
				loginResult.setSearchAuthority(0);
			}

			List<CaseUserSimpleInfoVo> caseList = caseService.findAppCaseListByUser(workNo, 1, 3, 5);
			if (caseList.size() == 0) {
				List<CityVo> cityList = cityService.findCityListByUser(workNo);
				int index = 0;
				for (int i = 0; i < cityList.size(); i++) {
					CityVo city = cityList.get(i);
					if ("上海".equals(city.getName())) {
						index = i;
						break;
					}
				}
				loginResult.setCityId(cityList.size() >0?cityList.get(index).getId():1);
				loginResult.setCityName(cityList.size() >0?cityList.get(index).getName():"上海");
				loginResult.setAuthority(1);
			} else {
				int index = 0;
				for (int i = 0; i < caseList.size(); i++) {
					CaseUserSimpleInfoVo caseUser = caseList.get(i);
					if ("上海".equals(caseUser.getCityName())) {
						index = i;
						break;
					}
				}
				loginResult.setRoleId(caseList.get(index).getRoleId());
				loginResult.setCityId(caseList.get(index).getCityId());
				loginResult.setCityName(caseList.get(index).getCityName());
			}

			final Resp resp = Resp.success();
			resp.setData(loginResult);
			//修改用户最后登录时间
//			executor.execute(new Runnable() {
//				@Override
//				public void run() {
//					userService.updateLastLogin(loginId,loginInfo.getComponentName());
//				}
//			});
			return resp;
		} else {
			return Resp.failed("账号或密码错误");
		}
	}
	


	private void cacheFunctionPermissionIds(String workNo) {
		final Set<Integer> functionPermissionIds = baseDataService.getFunctionPermissionIds(workNo);
		final String key = FUNCTION_PERMISION_PREFIX + workNo;
		if (!CollectionUtils.isEmpty(functionPermissionIds)) {
			try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
				final Pipeline pipelined = jedis.pipelined();
				pipelined.del(key); // 先删除

				final String[] idStrings = new String[functionPermissionIds.size()];
				int i = 0;
				final Iterator<Integer> iterator = functionPermissionIds.iterator();
				while(iterator.hasNext()) {
					idStrings[i++] = iterator.next().toString();
				}
				pipelined.sadd(key, idStrings);
				pipelined.sync();
			}
		} // 权限为空，也需要删除原来的权限
		else {
			try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
				jedis.del(key);
			}
		}
	}*/
	
//	private Set<Integer> getCachedFunctionPermssionIds(String workNo) {
//		final Set<Integer> functionPermissionIds = new HashSet<>();
//		final String key = FUNCTION_PERMISION_PREFIX + workNo;
//		try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
//			// 获取用户的功能权限ID并转换
//			final Set<String> smembers = jedis.smembers(key);
//			if (!CollectionUtils.isEmpty(smembers)) {
//				for (String e : smembers) {
//					functionPermissionIds.add(Integer.valueOf(e));
//				}
//			}
//		}
//		return functionPermissionIds;
//	}

/*	//缓存人员相关权限
	private void cachePromission(String workNo){
		Map<String, List<?>> datePromission = baseDataService.getDatePromission(workNo);
		List<?> caseIds = datePromission.get("caseIds");
		List<?> orgIds = datePromission.get("orgIds");
		caseIds.remove(null);
		if(caseIds!= null){
			try (Jedis jedis =JedisUtil.getInstance().getJedis()) {
				//需转换为String数组才能存储,
				String[] caseArray = new String[caseIds.size()];
				for (int i = 0; i < caseIds.size(); i++) {
					if(caseIds.get(i) != null){
						caseArray[i] = caseIds.get(i).toString();
					}
				}
				String[] orgArray = new String[orgIds.size()];
				for (int i = 0; i < orgIds.size(); i++) {
					if(orgIds.get(i) != null){
						orgArray[i] = orgIds.get(i).toString();
					}
				}
				//先清除key
				jedis.del(DATA_CASE+workNo);
				jedis.del(DATA_ORG+workNo);

				//缓存案场权限
				if (caseArray.length > 0) {
					jedis.sadd(DATA_CASE+workNo, caseArray);
					jedis.expire(DATA_CASE+workNo, 3600*24*60);
				}
				if (orgArray.length > 0) {
					jedis.sadd(DATA_ORG+workNo,orgArray);
					jedis.expire(DATA_ORG+workNo, 3600*24*60);
				}
			}catch (Exception e) {
				LOGGER.error("baocuo",e);
			}
		}
	}*/
	
/*	@ApiOperation(value = "根据工号获取其操作权限", httpMethod = "GET", response = SessionPermissionVo.class, notes = "根据工号获取其操作权限")
	@RequestMapping(method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(required = true, name = "X-Token", value = "令牌", paramType = "header", dataType = "string") 
			})
	public Resp getOperationsByWorkNo(
			@ApiParam(name = "workNo", value = "工号", required = true) @RequestParam(value = "workNo", required = true) String workNo
			) {
		Resp resp = null;
		try {
			SessionPermissionVo vo = new SessionPermissionVo();
			List<PermissionVo> operations = null;
			
			boolean isAdmin = isAdmin(workNo);
			// 判断是否管理员，如果是管理员则查出所有操作权限返回，否则根据用户工号去查
			if(isAdmin){
				operations = userService.getAllOperations();
			}else{
				operations = userService.getAllOperationByWorkNo(workNo);
			}
			
			// 获取urm案场ID集合
			List<Integer> urmCaseIds = caseService.findURMCaseIdList();
			
			// 查询当前登录工号最大权限的角色
			Integer roleId = 0;
			List<Integer> userRoleList = dataService.findUserRoleList(workNo);
			if(userRoleList != null && !userRoleList.isEmpty()) {
				roleId = Collections.max(userRoleList);
			}
			
			// 封装结果
			vo.setCaseIds(urmCaseIds);
			vo.setOperations(operations);
			vo.setRoleId(roleId);
			
			resp = Resp.success();
			resp.setData(vo);
		} catch (Exception e) {
			LOGGER.error("获取操作权限异常", e);
			resp = Resp.failed("获取操作权限异常");
		}
		return resp;
	}*/
	
	
/*	@ApiOperation(value = "OA用户登录", httpMethod = "POST", response = LoginResult.class, notes = "OA用户登录")
	@RequestMapping(value="/oaLogin",method=RequestMethod.POST)
	public Resp oaLogin(@RequestBody LoginOA oa){
		Resp resp = null;
		try{
			String loginId =  oa.getLoginId();
			String oaToken = oa.getOaToken();
			if(StringUtils.isEmpty(loginId)){
				return Resp.failed("账号为空");
			}
			if(StringUtils.isEmpty(oaToken)){
				return Resp.failed("oaToken为空");
			}
			//先判断 OAToken 是否有效
			boolean isEff = userService.isEffectOaToken(oaToken,loginId);
			if(!isEff){
				resp = Resp.failed("99", "oaToken无效");
				Map<String,String> data = new HashMap<String,String>();
				data.put("loginId", loginId);
				data.put("oaToken", oaToken);
				resp.setData(data);
				return resp;
			}
			//判断账号是否存在
			//BaseUserNewVo user = userService.getUserByLoginId(loginId);
			BaseUserNewVo user = userService.getOaUserByLoginId(loginId);
			if (null == user) {
				LOGGER.error("can't find user by loginID={}", loginId);
				return Resp.failed("账号不存在");
			}
			// 用户无效
			if (user.getValidate().equals("1")) {
				LOGGER.error("user invalidate loginID={}", loginId);
				return Resp.failed("用户无效");
			}
			
			//案场代理云代码校验
			//userService.oaLogin(loginId);
			if(isEff){
				// 生成 TOKEN
				final String componentName = "agent_cloud";
				final String workNo = user.getWorkNo();
				final String token = sessionService.genAndSaveToken(workNo, componentName);
				
				// 对于非管理员，才缓存其功能权限，管理员不需要缓存
				final boolean isAdmin = isAdmin(workNo);
				if (!isAdmin) {
					cacheFunctionPermissionIds(workNo);
				}
				// 缓存其数据权限
				this.cachePromission(workNo);
				
				List<PermissionVo> operations = null;
				// 判断是否管理员，如果是管理员则查出所有操作权限返回，否则根据用户工号去查
				// Integer isAdmin = userService.judgeUserisAdmin(workNo);
				if(isAdmin){
					operations = userService.getAllOperations();
				}else{
					operations = userService.getAllOperationByWorkNo(workNo);
				}
				
				// 返回结果
				final LoginResultVo loginResult = new LoginResultVo();
				loginResult.setToken(token);
				loginResult.setUserName(loginId);
				loginResult.setDisplayName(user.getUserName());
				loginResult.setOperations(operations);
				List<Integer> urmCaseIdList = caseService.findURMCaseIdList();
				loginResult.setCaseIds(urmCaseIdList);
//				loginResult.setDeptId(user.getDeptId());
				
				//金融系统权限控制begin
				if(("tfin").equals(componentName)){
					boolean flag = false;
					for (PermissionVo permissionVo : operations) {
						if(("tfin_backend").equals(permissionVo.getCode())){
							flag = true;
						}
					}
					if(!flag){
						return  Resp.failed("无金融系统登录权限");
					}
				}
				//金融系统权限控制end
				
				//平台系统登录权限begin
				if(("platform").equals(componentName)){
					boolean flag = false;
					for (PermissionVo permissionVo : operations) {
						if(("plat_form").equals(permissionVo.getCode())){
							flag = true;
						}
					}
					if(!flag){
						return Resp.failed("无平台系统登录权限");
					}
				}
				//平台系统登录权限end
				
				//查询用户拥有的操作权限集合返回给前端
				resp = Resp.success();
				resp.setData(loginResult);
				//修改用户最后登录时间
				return resp;
			}
			
		}catch(Exception e){
			LOGGER.error("获取异常", e);
			resp = Resp.failed("获取异常");
		}
		return resp;
	}*/
	
	
}
