package com.wk.p3.greenmall.modules.info.utils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.info.dao.GcategoryDao;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhuyanqing on 2015/12/15.
 * 供求相关工具类
 */
public class InfoUtils {
	private static GcategoryDao gcategoryDao = SpringContextHolder.getBean(GcategoryDao.class);
	private static GcategoryService gcategoryService = SpringContextHolder.getBean(GcategoryService.class);

	public static final String CACHE_GCATEGORY_LIST = "gcategoryList";
	public static final String CACHE_GCATEGORY_ALL_LIST = "gcategoryAllList";


	/**
	 * 查询所有商品类别 created by zhuyanqing
	 *
	 * @return
	 */
	public static List<Gcategory> getGcategoryAllList() {
		List<Gcategory> gcategoryList = (List<Gcategory>) getCache(CACHE_GCATEGORY_ALL_LIST);
		if (gcategoryList == null) {
			gcategoryList = gcategoryDao.findAllList(new Gcategory());
			putCache(CACHE_GCATEGORY_ALL_LIST, gcategoryList);
		}
		return gcategoryList;
	}

	/**
	 * 获取当前用户有权限访问的商品类别(暂时不执行限制)
	 * created by zhuyanqing
	 *
	 * @return public static List<Gcategory> getGcategoryList(){
	 * @SuppressWarnings("unchecked") List<Gcategory> gcategoryList = (List<Gcategory>)getCache(CACHE_GCATEGORY_LIST);
	 * if (gcategoryList == null){
	 * User user = getUser();
	 * if (user.isAdmin()){
	 * gcategoryList = gcategoryDao.findAllList(new Gcategory());
	 * }else{
	 * Gcategory gcategory = new Gcategory();
	 * gcategory.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
	 * gcategoryList = gcategoryDao.findList(gcategory);
	 * }
	 * putCache(CACHE_GCATEGORY_LIST, gcategoryList);
	 * }
	 * return gcategoryList;
	 * }
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}

	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
			//			subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	/**
	 * 得到当前时间和参数时间的时间差
	 *
	 * @param d
	 * @return
	 */
	public static String getTimeDiffer(Date d) {

		long l = new Date().getTime() - d.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		//System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		StringBuffer sb = new StringBuffer("");
		//不展示天
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (min > 0) {
			sb.append(min + "分钟");
		}
		if (sb.toString().equals("")) {
			sb.append("1分钟");
		}
		return sb.toString();
	}

	//    public static void main(String[] args) {
	//
	//        System.out.println(getTimeDiffer(new Date()));
	//    }

	/**
	 * 将字符串中的中文转化为拼音,其他字符不变
	 *
	 * @param inputString
	 * @return
	 */
	public static String getPingYin(String inputString) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);

		char[] input = inputString.trim().toCharArray();
		String output = "";

		try {
			for (int i = 0; i < input.length; i++) {
				if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
					output += temp[0];
				} else
					output += java.lang.Character.toString(input[i]);
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 *
	 * @param chinese 汉字串
	 * @return 汉语拼音首字母
	 */
	public static String getFirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}


	public static boolean ifHasStr(List<String> list, String str) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if (str.equals(list.get(i))) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/*
	 * 获取主营品种工具类
	 *
	 * */
	public static String[] getMainGoods(String[] arr) {
		List<String> ids = new ArrayList<String>();
		int size = 0;
		if (arr.length == 0) {
			return null;
		} else {
			for (int i = 0; i < arr.length; i++) {
				ids.add(arr[i]);
				String[] pids = gcategoryService.get(Integer.parseInt(arr[i])).getParentIds().split(",");
				for (int j = 0; j < pids.length; j++) {
					if (!ifHasStr(ids, pids[j])) {
						ids.add(pids[j]);
					}
				}
			}
			size = ids.size();
		}
		return (String[]) ids.toArray(new String[size]);
	}


	public static JSONObject responseSuccess(JSONObject jsonObject, String msg) {
		jsonObject.put("code", 200);
		jsonObject.put("msg", msg);
		return jsonObject;
	}

	public static JSONObject responseFail(JSONObject jsonObject, String msg, int code) {
		jsonObject.put("code", code);
		jsonObject.put("msg", msg);
		return jsonObject;
	}


	/**
	 * 保存单个文件
	 *
	 * @param request
	 * @param keyName    文件的key
	 * @param toFileName 目标文件名
	 * @return
	 */
	public static void saveFile(HttpServletRequest request, String keyName, String toFileName) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String uploadPath = multipartRequest.getRealPath(toFileName);
		//String uploadPath = request.getSession().getServletContext().getRealPath(toFileName);

		File file = new File(uploadPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();
		List<MultipartFile> files = multiValueMap.get(keyName);
		if (files != null) {
			for (int i = 0; i < files.size(); i++) {
				files.get(i).transferTo(file);
			}
		}
	}

	/*
	 * 删除单个文件
	 * */
	public static void delFile(String fileName,HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath(fileName);
		File file = new File(path);
		if (file.exists()){
			file.delete();
		}
	}
}
