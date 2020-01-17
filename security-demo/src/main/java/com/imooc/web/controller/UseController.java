package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * lcd  2019/12/29
 * Description:
 */
@RestController
@RequestMapping(value = "/user")
public class UseController {
    @GetMapping("/me")
    public Object getAuthentication(@AuthenticationPrincipal UserDetails userDetails){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(userDetails);
        return userDetails;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println("后端" + user.getBirthday());
        user.setId(1);
        return user;
    }

    @PutMapping("/{id}")
    public User update(@Valid @RequestBody User user, BindingResult bindingResult, @PathVariable String id) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                System.out.println(((FieldError) e).getField());
                System.out.println(e.getDefaultMessage());
            });
        }
        System.out.println(id);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println("后端" + user.getBirthday());
        user.setId(1);
        return user;
    }

    @JsonView(User.UserSimpleView.class)
    @GetMapping
    public List<User> query(/*@RequestParam(required = false,defaultValue = "abc",name = "asdf") String username*/
            UserQueryCondition condition
            , @PageableDefault(size = 11, page = 2, sort = "age,asc") Pageable pageable) {
        System.out.println(condition);
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @JsonView(User.UserDetailView.class)
    @GetMapping("/{id:\\d+}")
    public User getUserInfo(@PathVariable String id) {
//        throw new RuntimeException("user not exist");
//        System.out.println(id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }

}
