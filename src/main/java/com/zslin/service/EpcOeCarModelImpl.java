package com.zslin.service;
import com.zslin.domain.EpcOeCarModleDomain;
import com.zslin.domain.EpcOePartDomain;
import com.zslin.model.EpcOePartVo;
import com.zslin.model.JsModelListStd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangnian23
 * @Date 2020/7/28 14:33
 * @Version 1.0
 * 通过OE号获取适用车型
 */
@Service
public class EpcOeCarModelImpl  {

    @Autowired
    private EpcOeCarModleDomain epcOeCarModleDomain;
    @Autowired
    private EpcOePartDomain epcOePartDomain;

    public  List<JsModelListStd> searchCarModel(String oe) {

        List<EpcOePartVo> epcOePartVos = epcOePartDomain.searchOePart(oe);
        if(epcOePartVos==null)
            return null;
        List<JsModelListStd> list=new ArrayList<>();
        for(int i=0;i<epcOePartVos.size();i++){
            List<JsModelListStd> jsModelListStds= epcOeCarModleDomain.searchCarModel(oe,epcOePartVos.get(i).getGroupId());
            list.addAll(jsModelListStds);
        }
        return list;
    }
}
