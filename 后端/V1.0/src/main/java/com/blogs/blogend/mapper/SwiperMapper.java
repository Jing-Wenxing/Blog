package com.blogs.blogend.mapper;

import com.blogs.blogend.entity.Classes;
import com.blogs.blogend.entity.Swiper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SwiperMapper {
    // 获取全部Swiper数据
    List<Swiper> getALlSwiperData();
    // 获取所有可展示的swiper数据
    List<Swiper> getALlShowSwiperData();
    // 根据ID查询Swquence值
    String getSwiperSwquenceOfID(String id);
    // 根据ID查询Swquence值
    String getSwiperIDOfSwquence(String swquence);
    // 获取最大的ID值
    Integer getMaxID();
    // 获取最大的Swquence值
    Integer getMaxSwquence();
    // 添加一条轮播图
    Integer insertSwiperUnlessImg(Swiper swiper);
    // 删除一条轮播图
    Integer deleteSwiperData(String id);
    // 删除单条数据后Swquence值批量减小
    Integer updataSwiperSwquenceNumerous(String swquence);
    // 根据ID修改Swquence值
    Integer updataSwiperSwquence(String id, String swquence);
    // 根据ID修改Isshow值
    Integer updataSwiperIsshow(String id);
    // 修改参数值
    Integer updataSwiperAttributes(Swiper swiper);
    // 获取限定范围的数据
    List<Swiper> getALlSwiperDataofPage(Integer page);
    // 获取数据表数据量
    Integer tablesValue();
}
