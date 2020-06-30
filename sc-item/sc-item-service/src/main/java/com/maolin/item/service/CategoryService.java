package com.maolin.item.service;

import com.maolin.item.pojo.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @Author: wangmaolin
 * @Date: 2020/6/28 16:44
 */
public interface CategoryService {
    public List<Category> queryCategoriesByPid(Long pid);
}
