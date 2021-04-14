package com.zjc.hustoj.privilege.controller;

import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.privilege.constant.Role;
import com.zjc.hustoj.privilege.service.PrivilegeService;
import com.zjc.hustoj.privilege.vo.PrivilegeReqVo;
import com.zjc.hustoj.privilege.vo.PrivilegePageReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author David Hsiang
 * @date 2021/04/07/5:17 下午
 */
@RestController
@RequestMapping("/privilege")
@Slf4j
public class PrivilegeController extends BaseController {

    @Resource
    private PrivilegeService privilegeService;

    @PostMapping("/page")
    public ResponseEntity page(@RequestBody @Valid PrivilegePageReqVo privilegePageReqVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            PageUtils page = privilegeService.queryPage(privilegePageReqVo);
            return ServerResponse.ok(page);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody @Valid PrivilegeReqVo privilegeReqVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            privilegeService.delete(privilegeReqVo);
            return ServerResponse.okMsg("删除成功");
        } catch (Exception e) {
            log.error("删除失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @GetMapping("/roleList")
    public ResponseEntity roleList(){
        return ServerResponse.ok(Role.getRoleNameList());
    }

    @PostMapping("/addRolePrivilege")
    public ResponseEntity addRolePrivilege(@RequestBody @Valid PrivilegeReqVo privilegeReqVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            privilegeService.addRolePrivilege(privilegeReqVo);
            return ServerResponse.okMsg("新增成功");
        } catch (Exception e) {
            log.error("新增失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/addContestPrivilege")
    public ResponseEntity addContestPrivilege(@RequestBody @Valid PrivilegeReqVo privilegeReqVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            privilegeService.addContestPrivilege(privilegeReqVo);
            return ServerResponse.okMsg("新增成功");
        } catch (Exception e) {
            log.error("新增失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/addProblemPrivilege")
    public ResponseEntity addProblemPrivilege(@RequestBody @Valid PrivilegeReqVo privilegeReqVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            privilegeService.addProblemPrivilege(privilegeReqVo);
            return ServerResponse.okMsg("新增成功");
        } catch (Exception e) {
            log.error("新增失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }
}
