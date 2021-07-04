package com.blogs.blogend.mapper;

import com.blogs.blogend.entity.Timeline;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimelineMapper {
    // 添加一条数据
    Integer uploadData(Timeline timeline);
    // 获取最大的ID值
    Integer getMaxID();
    // 查看单个数据
    Timeline selectOFID(String id);
    // 获取数据表数据量
    Integer tablesValue();
    // 获取所有的timeline数据
    List<Timeline> getALlTimelineData();
    // 获取限定范围的timeline数据
    List<Timeline> getALlTimelineDataofPage(Integer page);
    // 删除一条数据
    Integer deleteTimelineData(String id);

}
