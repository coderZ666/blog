package com.zwx.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zwx.domain.Type;
import com.zwx.service.TypeService;
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
public class TypeController {

    @Resource
    private TypeService typeService;

    //默认每页展示十条数据
    private Integer size=10;

    //处理进入分类管理页面的操作（默认进入列表页展示分类列表，按id降序——即最新创建在前）
    @GetMapping("/types")
    public String types(Model model,
            //获取请求参数，表示当前是第几页，不能为空，默认为1
            @RequestParam(name="page",defaultValue="1")Integer page){

        List<Type> types = typeService.listType(page, size);
        //分页管理类，封装查询结果
        PageInfo pageInfo = new PageInfo(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //处理进入新增分类页面的请求
    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }

    //进入修改分类页面的请求回显需要修改的信息
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Integer id, Model model){
        model.addAttribute("type",typeService.getTypeById(id));
        return "admin/types-input";
    }

    //处理新增分类信息的请求
    @PostMapping("/types/input")
    public String post(Type type, RedirectAttributes attributes,Model model){
        //首先判断该分类是否重复
        if (typeService.getTypeByName(type.getName()) != null){//如果重复
            model.addAttribute("massage","提示：不能添加重复的分类");
            return "admin/types-input";
        }else {//如果不重复
            //执行添加操作
            int i = typeService.saveType(type);
            if (i == 1){
                attributes.addFlashAttribute("massage","添加成功");
            }else {
                attributes.addFlashAttribute("massage","未知的异常，添加失败");
            }
            return "redirect:/admin/types";
        }
    }

    //处理修改分类信息的请求
    @PostMapping("/types/{id}/input")
    public String editPost(@PathVariable Integer id,Type type, RedirectAttributes attributes,Model model){
        //首先判断该分类是否重复
        if (typeService.getTypeByName(type.getName()) != null){//如果重复,回传一个带着id的type
            type.setId(id);
            model.addAttribute("type",type);
            model.addAttribute("massage","提示：试图修改的分类已存在");
            return "admin/types-input";
        }else {//如果不重复
            type.setId(id);
            //执行修改操作
            int i = typeService.updateType(type);
            if (i == 1){
                attributes.addFlashAttribute("massage","修改成功");
            }else {
                attributes.addFlashAttribute("massage","未知的异常，修改失败");
            }
            return "redirect:/admin/types";
        }
    }

    //处理删除分类信息的请求
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes){
        //查询该分类下博客的数量
        Integer a = typeService.getBlogNum(id);
        if (a!=0){//如果该分类下有博客
            //返回提示，不能删除
            attributes.addFlashAttribute("massage","删除失败，请先修改所有该分类下的博客到其他分类");
            return "redirect:/admin/types";
        }//如果分类下没有博客，执行删除操作
        int i = typeService.deleteType(id);
        if (i == 1){
            attributes.addFlashAttribute("massage","删除成功");
        }else {
            attributes.addFlashAttribute("massage","删除失败，未知的异常");
        }
        return "redirect:/admin/types";
    }

}
