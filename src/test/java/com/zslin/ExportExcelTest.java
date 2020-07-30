package com.zslin;
import com.zslin.model.WebDto;
import com.zslin.service.EpcOeCarModelImpl;
import com.zslin.service.PoiOe;
import com.zslin.utils.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by 钟述林 393156105@qq.com on 2016/10/29 0:32.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExportExcelTest {

    @Autowired
    EpcOeCarModelImpl epcOeCarModel;
    @Autowired
    PoiOe poiOe;

    @Test
    public void testCarModel() throws IOException {

        poiOe.read();
      //  epcOeCarModel.searchCarModel("6RD615301");
    }
}
