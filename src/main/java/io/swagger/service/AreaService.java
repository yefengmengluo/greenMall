package io.swagger.service;

import io.swagger.model.Area;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by cc on 16-1-29.
 */
public interface AreaService {
    /**
     * 根据pid获得area的list
     * 0为根目录，中国
     * @param pid
     * @return
     */
    public ResponseEntity<List<Area>> getAreaListByPid(String pid);

}
