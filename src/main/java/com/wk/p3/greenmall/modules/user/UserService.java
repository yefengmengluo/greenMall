package com.wk.p3.greenmall.modules.user;

import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;

import java.util.List;

/**
 * Created by cc on 15-12-8.
 */
public interface UserService {

    /**
     * 获得与给定用户相关的用户
     *
     * @param relation  用户关系类型，在数据字典中获得
     *                  比如：委托人类型为
     *                  relation = DictUtils.getDictValue("middle_user", "user_relation_type", "middle")
     * @param id        用户id
     * @return
     *
     * 比如获得委托人id列表:
     *                  String relation = DictUtils.getDictValue("middle_user", "user_relation_type", "middle")
     *                  List<String> userIds =  relationUser(relation, "userId");
     *
     *
     */
    public List<String> relationUser(String relation, String id);

    /**
     * 通过personId获取前台用户
     *
     * @param personId
     * @return
     */
    public FrontUser getUserByPersonId(String personId);

    /**
     * 添加前台用户
     *
     * @param user
     */
    void registerFrontUser(FrontUser user);

    /**
     * 获取前台用户
     *
     * @param userId
     * @return
     */
    FrontUser getUserByUserId(String userId);

    /**
     * 获取当前登录用户
     * @return
     */
    public FrontUser getFrontUser();

    /**
     * 获取用户手机号个数（判断是否存在此用户）
     * @param mobile
     * @return
     */
    Integer getUserByMobile(String mobile);

    /**
     * 通过组织机构id获取前台用户
     *
     * @param organizationId
     * @return
     */
    FrontUser getFrontUserByOrganizationId(String organizationId);

    /**
     * 通过前台用户id获取交易员信息
     *
     * @param frontUserId
     * @return
     */
    User findBussinessUserByFrontUser(String frontUserId);

    /**
     * 通过条件查询用户信息个数
     *
     * @param frontUser
     * @return
     */
    Integer getCountUser(FrontUser frontUser);
}


