package com.wechat.demo1.wechatdemo.controller;

import com.wechat.demo1.wechatdemo.po.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String add(){
        return "hello security";
    }


    @GetMapping("/index")
    public String index(){
        return "hello index";
    }


    @GetMapping("/update")
    //@Secured({"ROLE_sale","ROLE_manager"})
    @PreAuthorize("hasAnyAuthority('admins')")
    public String update(){
        return "hello update";
    }


    @GetMapping("/delete")
    @PostAuthorize("hasAnyAuthority('admins')")
    public String delete(){
        System.out.println("sfsfsfsfs");
        return "hello delete";
    }

    @GetMapping("/getall")
    @PostAuthorize("hasAnyAuthority('admins')")
    @PostFilter("filterObject.username == 'admin1'")
    public List<Users> getAllUser() {
        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(31,"admin1","6666"));
        list.add(new Users(21,"admin2", "8888"));
        return list;
    }
}
