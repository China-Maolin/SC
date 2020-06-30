package com.maolin.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maolin.common.pojo.PageResult;
import com.maolin.item.mapper.BrandMapper;
import com.maolin.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: wangmaolin
 * @Date: 2020/6/29 17:00
 */
@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;
    public PageResult<Brand> queryBrandByPage(String key,Integer page,Integer rows,String sortBy,boolean desc){
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //根据name或首字母模糊查询
         /*`id`  '品牌id',
         `name` '品牌名称',
         `image`  '品牌图片地址',
         `letter`  '品牌的首字母',*/
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        //根据page、rows添加分页条件
        PageHelper.startPage(page,rows);
        //根据sortBy、desc添加排序条件
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(desc?"desc":"asc"));
        }
        //选择合适的通用Mapper
        List<Brand> brands = this.brandMapper.selectByExample(example);

        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList()) ;
    }
}
