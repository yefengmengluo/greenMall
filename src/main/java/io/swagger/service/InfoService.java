package io.swagger.service;

import io.swagger.model.Info;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by cc on 16-1-29.
 */
public interface InfoService {

    /**
     * 添加一条供求信息
     *
     * @param body
     * @return
     */
    public ResponseEntity<Void> addInfo(Info body);

    /**
     * 针对某条供(求)id，发布一条求(供)消息
     * 关联两条信息，发布消息
     *  addlinkedInfo 换个名称 publishInfo
     * @param id
     * @param body
     * @return
     */
    public ResponseEntity<Void> publishInfo(String id, Info body);


    /**
     * 关联两条信息，不发布消息
     * @param id
     * @param body
     * @return
     */
    public ResponseEntity<Void> addClickInfo(String id, Info body);

    /**
     * 根据某条infoId得到与之对应的推荐消息
     * @param infoId
     * @return
     */
    public ResponseEntity<List<Info>> getInfoListBySpecificInfo(Info infoId);

    /**
     * 根据某条infoId得到与之对应的推荐消息
     * @return
     */
    public ResponseEntity<List<Info>> guessList();

    /**
     * 通过某条infoId找到info实体
     * @param id
     * @return
     */
    public ResponseEntity<Info> getInfoById(String id);

    /**
     * 通过某条info的id更新该info
     * @param id
     * @param body
     * @return
     */
    public ResponseEntity<Void> updateInfo(String id, Info body);

}
