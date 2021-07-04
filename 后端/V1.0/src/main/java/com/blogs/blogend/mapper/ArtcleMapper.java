package com.blogs.blogend.mapper;

import com.blogs.blogend.entity.Artcle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArtcleMapper {
    // 添加一条数据
    Integer uploadData(Artcle artcle);
    // 获取最大的ID值
    Integer getMaxID();
    // 获取数据表数据量
    Integer tablesValue();
    // 编辑文章
    Integer updateData(Artcle artcle);
    // 获取限定范围的timeline数据
    List<Artcle> getALlArtcleDataofPage(Integer page);
    // 获取限定范围的timeline数据
    List<Artcle> getALlFrontArtcleDataofPage(Integer page);
    // 根据ID获取数据
    Artcle getDataofID(String id);
    // 修改分类
    Integer updataArtcleClass(String classification);
    // 删除一条数据
    Integer deleteArtcleData(String id);
    // 根据ID修改Isshow值
    Integer updataArtcleIsShow(String id);
}
