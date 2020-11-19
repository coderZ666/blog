package com.zwx.controller;

import com.github.pagehelper.PageInfo;
import com.zwx.domain.Blog;
import com.zwx.domain.Type;
import com.zwx.service.BlogService;
import com.zwx.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-10 11:55
 */
@Controller
public class TypeShoeController {

    @Resource
    private TypeService typeService;
    @Resource
    private BlogService blogService;

    private Integer size=10;

    //访问分类页的请求
    @GetMapping("/type/{id}")
    public String type(@PathVariable() Integer id, Model model,
                       //获取请求参数，表示当前是第几页，不能为空，默认为1
                       @RequestParam(name="page",defaultValue="1")Integer page){
        //查出所有分类，按分类下博客数量降序排列，传递给页面
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        if (id==-1){//表示通过导航访问，默认选中第一个分类（博客最多的分类）
            id = types.get(0).getId();
        }//否则使用页面传来的id
        //根据分类id查询分类下所有已发布的博客，按更新时间排序，做分页处理，传递给页面
        List<Blog> blogList = blogService.listBlogByTypeId(page,size,id);
        PageInfo pageInfo = new PageInfo(blogList);
        model.addAttribute("pageInfo",pageInfo);
        //再把id传回页面，表示被选中
        model.addAttribute("activeTypeId",id);
        return "type";
    }

}
