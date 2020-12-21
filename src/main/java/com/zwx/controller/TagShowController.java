package com.zwx.controller;

import com.github.pagehelper.PageInfo;
import com.zwx.domain.Blog;
import com.zwx.domain.Tag;
import com.zwx.service.BlogService;
import com.zwx.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-10 13:52
 */
@Controller
public class TagShowController {

    @Resource
    private TagService tagService;
    @Resource
    private BlogService blogService;

    private Integer size=10;

    //访问标签页的请求
    @GetMapping("/tag/{id}")
    public String tag(@PathVariable() Integer id, Model model,
                       //获取请求参数，表示当前是第几页，不能为空，默认为1
                       @RequestParam(name="page",defaultValue="1")Integer page){
        //查出所有标签，按标签下博客数量降序排列，传递给页面
        List<Tag> tags = tagService.listTag();
        model.addAttribute("tags",tags);
        if (id==-1){//表示通过导航访问，默认选中第一个标签（博客最多的标签）
            id = tags.get(0).getId();
        }//否则使用页面传来的id
        //根据标签id查询标签下所有已发布的博客，按更新时间排序，做分页处理，传递给页面
        List<Blog> blogList = blogService.listBlogByTagId(page,size,id);
        PageInfo pageInfo = new PageInfo(blogList);
        model.addAttribute("pageInfo",pageInfo);
        //再把id传回页面，表示被选中
        model.addAttribute("activeTagId",id);
        return "tag";
    }

    //处理最新文章列表上下一页的ajax请求
    @PostMapping("/changeTagPage")
    public String changeBlogPage(Integer page,Integer id,Model model){
        System.out.println(id+"\t"+page);
        //根据分类id查询分类下所有已发布的博客，按更新时间排序，做分页处理，传递给页面
        List<Blog> blogList = blogService.listBlogByTagId(page,size,id);
        PageInfo pageInfo = new PageInfo(blogList);
        model.addAttribute("pageInfo",pageInfo);
        //再把id传回页面，表示被选中
        model.addAttribute("activeTagId",id);
        return "tag :: blogList";
    }
}
