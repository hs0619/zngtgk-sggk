package com.baosight.sggk.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import java.text.SimpleDateFormat;
import java.util.*;

public class PermissionUtil {
	
	public static String DbSchema = ResourceBundle.getBundle("application").getString("hbSchema");
	
	/**   
	* 项目名称：project_name   
	* 类名称：type_name   
	* 类描述： 根据用户名和用户组查询是否在对应的用户组里
	* 状态为1表示该用户组存在对应的所属用户组，状态为0表示该用户组不存在所属用户组，状态为-1表示报错  
	* 创建人：gaoming   
	* 创建时间：2021年6月24日 下午2:44:49   
	* @version        
	*/
	public static String GetIsUserInGroup(String loginName, String groupName) {

		loginName = String.valueOf(loginName).trim();
		groupName = String.valueOf(groupName).trim();
		EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId,"S_XS_10");
		eiInfo.set("groupEname", groupName);
		eiInfo.set("loginName", loginName);
		
		EiInfo outInfo = XServiceManager.call(eiInfo);
		return String.valueOf(outInfo.getStatus());
	}
	
	/**   
	* 项目名称：project_name   
	* 类名称：type_name   
	* 类描述：   根据loginName判断用户是否为系统管理员
	* 创建人：gaoming   
	* 创建时间：2021年6月25日 下午3:10:26   
	* @version        
	*/
	public static String GetIsAdminGroup(String loginName) {

		loginName = String.valueOf(loginName).trim();
		EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId,"S_XS_32");
		eiInfo.set("loginName", loginName);
		EiInfo outInfo = XServiceManager.call(eiInfo);
		return String.valueOf(outInfo.getStatus());
	}
	
	/**   
	* 项目名称：project_name   
	* 类名称：type_name   
	* 类描述：   判断用户是否是特权用户组，特权可以看所有厂部返回%，分厂只能看自己厂部，报错返回""
	* 创建人：gaoming   
	* 创建时间：2021年6月25日 下午1:51:38   
	* @version        
	*/
	public static String getUserDepart(Dao dao, String loginName) {
		try {
			String adminState = GetIsAdminGroup(loginName);	
			if("1".equals(adminState)){
				return "%";
			}
			String branchuserState = GetIsUserInGroup(loginName,"tqglz");
			if("1".equals(branchuserState)) {
				return "%";
			}else if("0".equals(branchuserState)) {
				String sql = "select LOGIN_NAME,DEPARTMENT_ID,DEPARTMENT_NAME from " + DbSchema + ".VIEW_T_HA_XS_USER_EX where LOGIN_NAME = '" + loginName + "' ";
                Map sqlmap = new HashMap();
                sqlmap.put("sqlMap", sql);
                List list = dao.query("DUHA01.query", sqlmap);
                if(list.size() > 0) {
                	String departid = ((HashMap<String, String>)list.get(0)).get("DEPARTMENT_ID");
                	return departid;
                }else {
                	return "";
                }
			}else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	/** 驳回到个人 (厂区申报)
	 * 管理员发送消息到数字平台
	 * @param sendMan 推送消息人员，
	 * @param receiver 推送到的目的人员
	 * @param sendNum 发送的消息的数量
	 * @param messageContent 消息的内容
	 * @param dao 
	 * auto caojinge
	 * @return
	 */
	public static int addPlatformMessageToUser(String sendMan, String receiver, String messageTitle, String
			messageType, String jumpPage, Dao dao, String onlyId) {
		//厂部ID
		//String departmentId = getDepartmentIdByLoginName(receiver,dao);
		
		//1.将接收人的待办消息，插入到待办信息表里
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, String> pMap = new HashMap<>();
		pMap.put("messageId", StrUtil.getUUID());
		pMap.put("messageType", messageType);
		pMap.put("jumpPage", jumpPage);
		pMap.put("title", messageTitle);
		pMap.put("content", messageTitle);
		pMap.put("pushTime", sdf.format(new Date()));
		pMap.put("status", "1");
		pMap.put("groupName", "");
		//pMap.put("userId", departmentId+"-"+receiver);
		pMap.put("userId",receiver);
		pMap.put("creator", sendMan);
		pMap.put("createdTime", sdf.format(new Date()));
		pMap.put("remarks",onlyId);
		int insertStatus = 0;
		try {
			dao.insert("tduhb99.insertmessage",pMap);
			insertStatus = 1;
		} catch (Exception e) {
			insertStatus = 0;
		}
		return insertStatus;
	}
	/**  提交
	 * 管理员发送消息到数字平台
	 * @param sendMan 推送消息人员，
	 * @param receiver 推送到的目的人员
	 * @param sendNum 发送的消息的数量
	 * @param messageContent 消息的内容
	 *@param groupName  权限组
	 * @param dao
	 * auto caojinge
	 * @return
	 */
	public static void addPlatformMessageToGroup(String sendMan, String groupName, String messageTitle, String messageType,
                                                 String jumpPage, Dao dao, String onlyId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//1.根据用户组，查询该用户组下的用户成员列表
		List<Map<String,String>> userList = PermissionUtil.getGroupUsersByGroupEname(groupName);
		String receiver = new String();
		//2.当用户组内的成员数量大于1时，先将组内人员的消息信息插入到表中
		if (StrUtil.listIsNotNullOrEmpty(userList)) {
			for (int i = 0; i < userList.size(); i++) {
				Map map = userList.get(i);
				 receiver += (String) map.get("loginName");
			}
			Map<String, String> pMap = new HashMap<>();
			pMap.put("messageId", StrUtil.getUUID());
			pMap.put("messageType", messageType);
			pMap.put("jumpPage", jumpPage);
			pMap.put("title", messageTitle);
			pMap.put("content", messageTitle);
			pMap.put("pushTime", sdf.format(new Date()));
			pMap.put("status", "1");//查看
			pMap.put("groupName", groupName);
			pMap.put("userId", receiver);
			pMap.put("creator", sendMan);
			pMap.put("createdTime", sdf.format(new Date()));
			pMap.put("remarks",onlyId);
			int insertStatus = 0;
			try {
				dao.insert("tduhb99.insertmessage",pMap);
				insertStatus = 1;
			} catch (Exception e) {
				insertStatus = 0;
			}
		}

	}
	
	/**  提交(能环申报申报，只针对监察整改组)
	 * 管理员发送消息到数字平台
	 * @param sendMan 推送消息人员，
	 * @param receiver 推送到的目的人员
	 * @param sendNum 发送的消息的数量
	 * @param messageContent 消息的内容
	 *@param groupName  权限组
	 * @param dao
	 * auto caojinge
	 * @return
	 */
	public static void addPlatformMessageToGroup1(String sendMan, String groupName, String messageTitle, String messageType,
                                                  String jumpPage, Dao dao, String onlyId , String account) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//1.根据用户组，查询该用户组下的用户成员列表
		List<Map<String,String>> userList = PermissionUtil.getGroupUsersByGroupEname(groupName);
		String receiver = new String();
		//2.当用户组内的成员数量大于1时，先将组内人员的消息信息插入到表中
		if (StrUtil.listIsNotNullOrEmpty(userList)) {
			for (int i = 0; i < userList.size(); i++) {
				Map map = userList.get(i);
				receiver += (String) map.get("loginName");
			}
			Map<String, String> pMap = new HashMap<>();
			pMap.put("messageId", StrUtil.getUUID());
			pMap.put("messageType", messageType);
			pMap.put("jumpPage", jumpPage);
			pMap.put("title", messageTitle);
			pMap.put("content", messageTitle);
			pMap.put("pushTime", sdf.format(new Date()));
			pMap.put("status", "1");//查看
			pMap.put("groupName", groupName);
			//pMap.put("userId", receiver);
			pMap.put("userId", account);
			pMap.put("creator", sendMan);
			pMap.put("createdTime", sdf.format(new Date()));
			pMap.put("remarks",onlyId);
			int insertStatus = 0;
			try {
				dao.insert("tduhb99.insertmessage",pMap);
				insertStatus = 1;
			} catch (Exception e) {
				insertStatus = 0;
			}
		}
	}

//	/**
//	 *  代办消息处理完成，发送消息到数字平台，进行角标数量的修改
//	 * @param sendMan 消息发送人
//	 * @param receiver  消息接收人（被处理人）
//	 * @param messageType 消息类型
//	 * @param dao
//	 * @param onlyId  拼接的唯一主键
//	 */
//	public static int dealWithMessage(String sendMan,String receiver, String messageType,  String onlyId , Dao dao) {
//		int dealStatus = 1;
//		try {
//			//修改数据表内消息状态
//			if (StrUtil.paramIsNotNullOrEmpty(onlyId)) {
//				Map<String,String> pMap = new HashMap<>();
//				pMap.put("messageType", messageType);
//				pMap.put("solvePerson", sendMan);
//				//pMap.put("userId", receiver);
//				pMap.put("solvedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				pMap.put("remarks", onlyId);
//				pMap.put("status", "1");
//				dao.update("tduhb99.dealWithMessage",pMap);
//			}
//		} catch (Exception e) {
//			dealStatus = -1;
//		}
//		return dealStatus;
//	}


	/** 暂存
	 *  代办消息处理完成，发送消息到数字平台，进行角标数量的修改
	 * @param sendMan 消息发送人
	 * @param receiver  消息接收人（被处理人）
	 * @param messageType 消息类型
	 * @param dao
	 * @param onlyId  拼接的唯一主键
	 */
	public static void dealWithMessageHold(String sendMan, String groupName, String messageTitle, String messageType,
                                           String jumpPage, Dao dao, String onlyId, String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//1.根据用户组，查询该用户组下的用户成员列表
		List<Map<String,String>> userList = PermissionUtil.getGroupUsersByGroupEname(groupName);
		String receiver = new String();
		//2.当用户组内的成员数量大于1时，先将组内人员的消息信息插入到表中
			if (StrUtil.paramIsNotNullOrEmpty(onlyId) && str == "1") {
//				for (int i = 0; i < userList.size(); i++) {
//					Map map = userList.get(i);
//					receiver += (String) map.get("loginName");
//				}
				Map<String, String> pMap = new HashMap<>();
				pMap.put("messageId", StrUtil.getUUID());
				pMap.put("messageType", messageType);
				pMap.put("jumpPage", jumpPage);
				pMap.put("title", messageTitle);
				pMap.put("content", messageTitle);
				pMap.put("pushTime", sdf.format(new Date()));
				pMap.put("status", "0");//待审核
				pMap.put("groupName", groupName);
				pMap.put("userId", sendMan);
				pMap.put("creator", sendMan);
				pMap.put("createdTime", sdf.format(new Date()));
				pMap.put("remarks",onlyId);
				int insertStatus = 0;
				try {
					dao.insert("tduhb99.insertmessage",pMap);
					insertStatus = 1;
				} catch (Exception e) {
					insertStatus = 0;
				}
			}else{
				Map<String,String> pMap = new HashMap<>();
				String date =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String title = date+"你有暂存需执行";
				pMap.put("title", title);
				pMap.put("messageType", messageType);
				pMap.put("solvePerson", sendMan);
				pMap.put("userId", receiver);
				pMap.put("solvedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pMap.put("remarks", onlyId);
				pMap.put("status", "1");
				int insertStatus = 0;
				try {
				dao.update("tduhb99.dealWithMessage2",pMap);
					insertStatus = 1;
			} catch (Exception e) {
					insertStatus = 0;
		}
			}

	}



	/**  审核
	 *  代办消息处理完成，发送消息到数字平台，进行角标数量的修改
	 * @param sendMan 消息发送人
	 * @param receiver  消息接收人（被处理人）
	 * @param messageType 消息类型
	 * @param dao
	 * @param onlyId  拼接的唯一主键
	 */
	public static int dealWithMessage1(String sendMan,String receiver, String messageType,  String onlyId , Dao dao) {
		int dealStatus = 1;
		try {
			//修改数据表内消息状态
			if (StrUtil.paramIsNotNullOrEmpty(onlyId)) {
				Map<String,String> pMap = new HashMap<>();
				String date =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String title = date+"你有申报需执行";
				pMap.put("title", title);
				pMap.put("messageType", messageType);
				pMap.put("solvePerson", sendMan);
				pMap.put("userId", receiver);
				pMap.put("solvedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pMap.put("remarks", onlyId);
				pMap.put("status", "1");
				dao.update("tduhb99.dealWithMessage2",pMap);
			}
		} catch (Exception e) {
			dealStatus = -1;
		}
		return dealStatus;
	}



	/**  驳回
	 *  代办消息处理完成，发送消息到数字平台，进行角标数量的修改
	 * @param sendMan 消息发送人
	 * @param receiver  消息接收人（被处理人）
	 * @param messageType 消息类型
	 * @param dao
	 * @param onlyId  拼接的唯一主键
	 */
	public static int dealWithMessage2(String sendMan,String receiver, String messageType,  String onlyId , Dao dao) {
		int dealStatus = 1;
		try {
			//修改数据表内消息状态
			if (StrUtil.paramIsNotNullOrEmpty(onlyId)) {
				Map<String,String> pMap = new HashMap<>();
				String date =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String title = date+"你有消息被驳回";
				pMap.put("title", title);
				pMap.put("messageType", messageType);
				pMap.put("solvePerson", sendMan);
				pMap.put("userId", receiver);
				pMap.put("solvedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pMap.put("remarks", onlyId);
				pMap.put("status", "2");
				dao.update("tduhb99.dealWithMessage1",pMap);
			}
		} catch (Exception e) {
			dealStatus = -1;
		}
		return dealStatus;
	}


	/**
	 *  接口描述：根据用户组英文名获取用户组下所有成员组信息
	 *	接口服务ID：S_XS_09
	 *  接口服务名：XSUser
	 *	接口方法：getGroupUsersByGroupEname
	 *  auto: caojinge
	 */
	public static List getGroupUsersByGroupEname(String groupName) {
		EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId,"S_XS_10");
		eiInfo.set("groupEname", groupName);
		EiInfo outInfo = XServiceManager.call(eiInfo);
		List resList = new ArrayList<>();

		JSONArray resultArray = JSONArray.fromObject(outInfo.get("result"));
		if (resultArray != null) {
			resList = JSONArray.toList(resultArray,new HashMap<>(),new JsonConfig());
		}

		return resList;
	}


	/**
	 *  根据当前登录人账号，获取当前登录人所在的厂部id
	 * @param loginName
	 * @param dao
	 * auto: caojinge
	 * @return
	 */
	public static String getDepartmentIdByLoginName(String loginName, Dao dao) {
		String departmentId = "";
		if (StrUtil.paramIsNotNullOrEmpty(loginName)) {
			String sql = "select USER_ID,DEPARTMENT_ID from " + DbSchema + ".T_HA_XS_USER_EX where USER_ID = '" + loginName + "' ";
            Map sqlmap = new HashMap();
            sqlmap.put("sqlMap", sql);
            List list = dao.query("DUHA01.query", sqlmap);
            if(list.size() > 0) {
            	departmentId = ((HashMap<String, String>)list.get(0)).get("DEPARTMENT_ID");
            }
		}
		return departmentId;
	}
	
	/**
	 *  修改消息
	 * @param dao
	 * @param onlyId   唯一的ID
	 * @return
	 */
	public static int updatePlatformMessageToUser(Dao dao, String  onlyId){
	   Map<String,String> mapStatus = new HashMap<>();
	   mapStatus.put("remarks",onlyId);
	   mapStatus.put("status","0");
	   int insertStatus = 0;
	   try {
	      dao.update("tduhb99.updateStatus",mapStatus);
	      insertStatus = 1;
	   } catch (Exception e) {
	      insertStatus = -1;
	      e.printStackTrace();
	   }
	      return     insertStatus ;
	}
	
	/**
	 *  修改消息
	 * @param dao
	 * @param onlyId   唯一的ID
	 * @return
	 */
	public static int updatePlatformMessageLikeRemarks(Dao dao, String  onlyId){
	   Map<String,String> mapStatus = new HashMap<>();
	   mapStatus.put("remarks",onlyId);
	   mapStatus.put("status","0");
	   int insertStatus = 0;
	   try {
	      dao.update("tduhb99.updateStatusLikeremarks",mapStatus);
	      insertStatus = 1;
	   } catch (Exception e) {
	      insertStatus = -1;
	      e.printStackTrace();
	   }
	      return     insertStatus ;
	}
	
	/**
	 *   通过厂部ID查询所有人账号
	 * @param departid
	 * @return
	 */
	public static List factoryPerson(String departid , Dao dao){
	   Map<String,String> map = new HashMap<>();
	   map.put("departid",departid);
	   List<Map<String,String>> list = dao.query("DUHF7301.querydepartid",map);
	   return list;
	}
}
