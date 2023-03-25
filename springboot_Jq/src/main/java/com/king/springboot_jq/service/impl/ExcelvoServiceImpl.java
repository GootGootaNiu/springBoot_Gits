package com.king.springboot_jq.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.king.springboot_jq.pojo.ExcelVo;
import com.king.springboot_jq.service.ExcelvoService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelvoServiceImpl implements ExcelvoService {

    /**
     * 用来解析数据的
     * @param inputStream
     * @return
     */
    // 实现数据
    @Override
    public List<ExcelVo> list(InputStream inputStream) {
        List<ExcelVo> lists = new ArrayList<>();
        EasyExcel.read(inputStream)
                .head(ExcelVo.class)
                .sheet()
                .registerReadListener(new AnalysisEventListener<ExcelVo>() {
                    // 没解析一行数据就调用这个方法
                    @Override
                    public void invoke(ExcelVo excelVo , AnalysisContext analysisContext) {
                        lists.add(excelVo);
                    }

                    //全部解析玩之后会回调
                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        System.out.println("解析完成");
                    }
                }).doRead();
        return lists;
    }
}
