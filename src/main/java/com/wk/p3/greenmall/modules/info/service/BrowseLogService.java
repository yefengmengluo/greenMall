package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.utils.UserAgentUtils;
import com.wk.p3.greenmall.modules.info.dao.BrowseLogDao;
import com.wk.p3.greenmall.modules.info.entity.BrowseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhuyanqing on 2016/1/29.
 */
@Service
@Transactional(readOnly = true)
public class BrowseLogService extends CrudService<BrowseLogDao,BrowseLog> {

    @Autowired
    BrowseLogDao browseLogDao;


    public void beforeSave(HttpServletRequest request,BrowseLog browseLog){
        String host = StringUtils.getRemoteAddr(request);
        browseLog.setIp(host);
        browseLog.setAccessType(UserAgentUtils.getDeviceType(request).getName());
        /* TODO 移动端用户经纬度确定 */
       /* if(UserAgentUtils.isAndroid(request)){
            browseLog.setOs(1);
        }else if(UserAgentUtils.isIOS(request)){
            browseLog.setOs(2);
        }*/
        browseLog.setBrowser(UserAgentUtils.getBrowser(request).getName());
    }
    public Integer count(BrowseLog browseLog){
        return browseLogDao.count(browseLog);
    }



}
