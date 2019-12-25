package com.ford.blog.controller.login;

import com.ford.blog.annotation.PublicInterface;
import com.ford.blog.controller.OperationStatus;
import com.ford.blog.controller.login.dto.DetailLoginDTO;
import com.ford.blog.controller.login.dto.LoginDTO;
import com.ford.blog.encrypt.util.CheckoutUtil;
import com.ford.blog.entity.User;
import com.ford.blog.entity.role.UserRoleRef;
import com.ford.blog.exception.UnauthorizedException;
import com.ford.blog.service.UserService;
import com.ford.blog.util.ErrorCode;
import com.ford.blog.util.JwtToken;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import com.alibaba.fastjson.JSON;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: LoginController
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 6:13
 **/
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @PostMapping(value = "/login", produces = "application/json")
    @ApiOperation(value = "登录")
    @PublicInterface
    public DetailLoginDTO login(@RequestBody LoginDTO dto) {
        logger.info("login info is {}", JSON.toJSONString(dto));
        User user = userService.queryByUsernameAndPassword(dto.getUsername(), CheckoutUtil.md5(dto.getPassword()));
        if (null == user) {
            throw new UnauthorizedException(ErrorCode.UserOrPasswordError);
        }
        // 判断当前用户是否有已弃用的角色
        List<UserRoleRef> refs = user.getUserRoleRef().stream().filter(ref -> ref.getRole().getEnable()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(refs)) {
            throw new UnauthorizedException(ErrorCode.NoRoleEnable);
        }
        String token = JwtToken.createToken(user.getUserId(), dto.getFromMobile());
        DetailLoginDTO result = new DetailLoginDTO().convertFrom(user);
        result.setToken(token);
        return result;
    }
    /**
     * 注册功能
     */
    @PostMapping(value = "/register", produces = "application/json")
    @ApiOperation(value = "注册")
    @PublicInterface
    public ModelAndView register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("password2") String password2){
        ModelAndView success = new ModelAndView();
        //用户或密码为空的条件判断
        if(username.isEmpty()||password.isEmpty()||password2.isEmpty()){
            success.setViewName("register");
            success.addObject("tip1","用户或密码不能为空");
            return success;
        }
        //两次密码不一样的判断条件
        if(!password.equals(password2)){
            success.setViewName("register");
            success.addObject("tip2","两次密码不一样");
            return success;
        }
        //判断是否取到用户，如果没有就保存在数据库中
        User user=userService.queryByUsername(username);
        if(user == null){
            User registers=new User();
            registers.setUserName(username);
            registers.setPassword(password);
            userService.save(registers);
            success.setViewName("success");
        }
        else {
            success.setViewName("404");
        }
        return success;
    }

    @RequestMapping("/login_error")
    public OperationStatus loginError() {
        return new OperationStatus("error", "登录失败!");
    }

    @RequestMapping("/login_success")
    public OperationStatus loginSuccess() {
        return new OperationStatus("success", "登录成功!");
    }

    /**
     * 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     * <p>
     * 如果要支持表单登录，可以在这个方法中判断请求的类型，进而决定返回JSON还是HTML页面
     *
     * @return
     */
    @RequestMapping("/login_page")
    public OperationStatus loginPage() {
        return new OperationStatus("error", "尚未登录，请登录!");
    }

}
