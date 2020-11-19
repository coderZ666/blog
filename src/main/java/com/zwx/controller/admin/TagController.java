package com.zwx.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zwx.domain.Tag;
import com.zwx.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-05 18:51
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    //默认每页展示十条数据
    private Integer size=10;

    //处理进入标签管理页面的操作（默认进入列表页展示标签列表，按id降序——即最新创建在前）
    @GetMapping("/tags")
    public String tags(Model model,
                        //获取请求参数，表示当前是第几页，不能为空，默认为1
                        @RequestParam(name="page",defaultValue="1")Integer page){

        List<Tag> tags = tagService.listTag(page, size);
        //分页管理类，封装查询结果
        PageInfo pageInfo = new PageInfo(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //处理进入新增标签页面的请求
    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

    //进入修改标签页面的请求回显需要修改的信息
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Integer id, Model model){
        model.addAttribute("tag",tagService.getTagById(id));
        return "admin/tags-input";
    }

    //处理新增标签信息的请求
    @PostMapping("/tags/input")
    public String post(Tag tag, RedirectAttributes attributes,Model model){
        //首先判断该标签是否重复
        if (tagService.getTagByName(tag.getName()) != null){//如果重复
            model.addAttribute("massage","提示：不能添加重复的标签");
            return "admin/tags-input";
        }else {//如果不重复
            //执行添加操作
            int i = tagService.saveTag(tag);
            if (i == 1){
                attributes.addFlashAttribute("massage","添加成功");
            }else {
                attributes.addFlashAttribute("massage","未知的异常，添加失败");
            }
            return "redirect:/admin/tags";
        }
    }

    //处理修改标签信息的请求
    @PostMapping("/tags/{id}/input")
    public String editPost(@PathVariable Integer id,Tag tag, RedirectAttributes attributes,Model model){
        //首先判断该标签是否重复
        if (tagService.getTagByName(tag.getName()) != null){//如果重复,回传一个带着id的tag
            tag.setId(id);
            model.addAttribute("tag",tag);
            model.addAttribute("massage","提示：试图修改的标签已存在");
            return "admin/tags-input";
        }else {//如果不重复
            tag.setId(id);
            //执行修改操作
            int i = tagService.updateTag(tag);
            if (i == 1){
                attributes.addFlashAttribute("massage","修改成功");
            }else {
                attributes.addFlashAttribute("massage","未知的异常，修改失败");
            }
            return "redirect:/admin/tags";
        }
    }

    //处理删除标签信息的请求
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes){
        int i = tagService.deleteTag(id);
        if (i == 1){
            attributes.addFlashAttribute("massage","删除成功");
        }else {
            attributes.addFlashAttribute("massage","删除失败，请先在所有博客中移除该标签");
        }
        return "redirect:/admin/tags";
    }

}