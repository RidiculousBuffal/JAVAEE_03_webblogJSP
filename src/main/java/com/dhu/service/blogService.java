package com.dhu.service;

import java.util.List;
import java.util.Map;
import com.dhu.pojo.lytable;
public interface blogService {
    List<Map<String,Object>>getAllblogs();
    int submitBlog(lytable blog,int userId);
    List<Map<String,Object>>getUserBlog(int userId);
}
