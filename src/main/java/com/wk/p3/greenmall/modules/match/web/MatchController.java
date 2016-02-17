package com.wk.p3.greenmall.modules.match.web;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import com.wk.p3.greenmall.modules.match.entity.MatchingAttr;
import com.wk.p3.greenmall.modules.match.entity.SupplyDemandRelationMatching;
import com.wk.p3.greenmall.modules.match.service.MatchService;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.user.entity.OrganizationType;
import com.wk.p3.greenmall.modules.user.service.OrganizationService;

/**
 * @author zhaomeng
 * @description 匹配属性controller
 * 2015年12月15日
 */
@Controller
@RequestMapping(value = "${adminPath}/match")
public class MatchController extends BaseController {

	@Autowired
	MatchService matchService;
	@Autowired
	InfoServiceImp infoService;

	//*****************************************************************************//
	/**
	 * 测试根据供求信息查询商机方法
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"ceshi"})
	public String ceshi() {
		Info info=infoService.get("066a17790662415e91eec4e2d6cc0624");
		Map<Double, String> findMatchingBySupplyAndDemand = matchService.findMatchingBySupplyAndDemand(info);
		for (Entry<Double, String> entry : findMatchingBySupplyAndDemand.entrySet()) {
			System.out.println("测试供求商机匹配======================key= " + entry.getKey() + " and value= " + entry.getValue()+"=========================");
		}
		return null;
	}
	//*****************************************************************************//
	
	//*****************************************************************************//
	/**
	 * 测试根据供求信息ID查询商机方法
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"ceshiid"})
	public String ceshiid() {
		Map<Double, String> findMatchingBySupplyAndDemand = matchService.findMatchingBySupplyAndDemandId("066a17790662415e91eec4e2d6cc0624");
		for (Entry<Double, String> entry : findMatchingBySupplyAndDemand.entrySet()) {
			System.out.println("测试供求商机匹配======================key= " + entry.getKey() + " and value= " + entry.getValue()+"=========================");
		}
		return null;
	}
	//*****************************************************************************//

	//*****************************************************************************//
	/**
	 * 测试根据用户信息查询商机方法
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"ceshi2"})
	public String ceshi2() {
		Map<Double, String> findMatchingByUser = matchService.findMatchingByUser("111");
		for (Entry<Double, String> entry : findMatchingByUser.entrySet()) {
			System.out.println("测试用户商机匹配======================key= " + entry.getKey() + " and value= " + entry.getValue()+"=========================");
		}
		return null;
	}
	//*****************************************************************************//



	/**
	 * 去添加匹配规范页面
	 * @param model
	 * @return
	 */
	@RequestMapping("toAddMathingRelation")
	public String toAddMathingRelation(Model model) {
		List<MatchingAttr> matchingAttrList = matchService.findMatchingAttrList();
		model.addAttribute("matchingAttrList",matchingAttrList);
		return "modules/match/editMatchingRelation";
	}
	/**
	 * 保存匹配规范
	 * @param supplyDemandRelationMatching
	 * @param relationWeight
	 * @param checkmatch
	 * @return
	 */
	@ResponseBody
	@RequestMapping("saveMatchingRelation")
	public String saveMatchingRelation(@RequestParam(required=false,value="ifUpdate") String ifUpdate,String ifCover,SupplyDemandRelationMatching supplyDemandRelationMatching, @RequestParam("relationWeight") String[] relationWeight,@RequestParam("checkmatch") String[] checkmatch ) {
		if(!StringUtils.isNoneBlank(relationWeight)){
			return "relationError1";
		}else{
			Double sum=0.0;
			for (int i = 0; i < relationWeight.length; i++) {
				sum=sum+Double.parseDouble(relationWeight[i]);
			}
			if(sum.equals(100)){
				return "relationError2";
			}
		}
		String result=matchService.saveMatchingRelation(supplyDemandRelationMatching,relationWeight,checkmatch,ifUpdate,ifCover);
		return result;
	}

	/**
	 * 显示匹配规范列表 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"matchRelationList"})
	public String matchRelationList(SupplyDemandRelationMatching supplyDemandRelationMatching, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SupplyDemandRelationMatching> page = matchService.findMatchRelationList(new Page<SupplyDemandRelationMatching>(request, response), supplyDemandRelationMatching);
		model.addAttribute("page", page);
		return "modules/match/matchRelationList";
	}

	/**
	 * 修改匹配规范
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"toUpdateMatchRelation"})
	public String toUpdateMatchRelation(@RequestParam("id") String id,Model model) {
		SupplyDemandRelationMatching demandRelationMatching=matchService.findRelationById(id);
		model.addAttribute("demandRelationMatching",demandRelationMatching);
		List<MatchingAttr> matchingAttrList = matchService.findMatchingAttrList();
		model.addAttribute("matchingAttrList",matchingAttrList);
		List<MatchingAttr> relationMatchList=matchService.findMatchRelationByRealtionId(id);
		model.addAttribute("relationMatchList", relationMatchList);
		model.addAttribute("ifUpdate", "1");
		return "modules/match/editMatchingRelation";
	}

	/**
	 * 删除匹配规范
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"deleteMatchRelation"})
	public String deleteMatchRelation(@RequestParam("id") String id) {
		matchService.deleteMatchRelation(id);
		return "redirect:" + adminPath + "/match/matchRelationList";
	}
}
