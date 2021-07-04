package com.blogs.blogend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.entity.Classes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    // 获取最大的ID值
    Integer getMaxID();
    // 添加一条数据
    Integer uploadData(Classes classes);
    // 删除文章
    Integer deleteClassData(String classification);
    // 获取限定范围的数据
    List<Classes> getALlArtcleDataofPage(Integer page);
    // 获取限定范围的数据
    List<Classes> getALlClassData();
    // 获取数据表数据量
    Integer tablesValue();
}
