package com.wk.p3.greenmall.modules.info.web;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.advise.service.RecommendService;
import com.wk.p3.greenmall.modules.info.entity.InfoRelation;
import com.wk.p3.greenmall.modules.info.service.InfoRelationService;
import com.wk.p3.greenmall.modules.info.service.InfoUnitCategoryService;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import com.wk.p3.greenmall.modules.match.service.impl.MatchServiceImpl;
import com.wk.p3.greenmall.modules.sys.service.DictService;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhuyanqing on 2016/1/28.
 */

@Controller
@RequestMapping(value = "${adminPath}/infoRelation")
public class InfoRelationController extends BaseController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    InfoServiceImp infoServiceImp;
    @Autowired
    MatchServiceImpl matchServiceImpl;
    @Autowired
    RecommendService recommendService;
    @Autowired
    InfoUnitCategoryService infoUnitCategoryService;
    @Autowired
    SystemService systemService;
    @Autowired
    DictService dictService;
    @Autowired
    InfoRelationService infoRelationService;

    @ModelAttribute
    public InfoRelation get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return infoRelationService.get(id);
        }else{
            return new InfoRelation();
        }
    }


    /*********************************************************************************************************************************************/
    /********************************************** TODO 供求信息历史记录、供求信息关系历史明细表  **************************************************/
    /**********************************************************************************************************************************************/
      /*
    * 后台页面，供求关系列表
    * */
    @RequestMapping(value="relationBetweenInfoes")
    public String relationBetweenInfoes(InfoRelation infoRelation,Model model, HttpServletRequest request, HttpServletResponse response){
        int i=0;
        Date d = null;
        if(infoRelation.getEndDate()!=null){
            d=infoRelation.getEndDate();
            try {
                i=1;
                Calendar cd = Calendar.getInstance();
                cd.setTime(infoRelation.getEndDate());
                cd.add(Calendar.DATE, 1);//增加一天
                infoRelation.setEndDate(cd.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Page<InfoRelation> page = infoRelationService.findPage(new Page<InfoRelation>(request, response), infoRelation);
        if(i==1){
            infoRelation.setEndDate(d);
        }
        model.addAttribute("page",page);
        return "modules/info/infoRelationList";
    }



}

