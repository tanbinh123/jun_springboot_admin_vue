package com.jun.plugin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.jun.plugin.common.utils.DataResult;

import com.jun.plugin.entity.OaNotesInfoEntity;
import com.jun.plugin.service.OaNotesInfoService;



/**
 * 公告通知
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-09-27 23:08:54
 */
@Controller
@RequestMapping("/")
public class OaNotesInfoController {
    @Autowired
    private OaNotesInfoService oaNotesInfoService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaNotesInfo")
    public String oaNotesInfo() {
        return "oanotesinfo/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("oaNotesInfo/add")
    @RequiresPermissions("oaNotesInfo:add")
    @ResponseBody
    public DataResult add(@RequestBody OaNotesInfoEntity oaNotesInfo){
        oaNotesInfoService.save(oaNotesInfo);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaNotesInfo/delete")
    @RequiresPermissions("oaNotesInfo:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaNotesInfoService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaNotesInfo/update")
    @RequiresPermissions("oaNotesInfo:update")
    @ResponseBody
    public DataResult update(@RequestBody OaNotesInfoEntity oaNotesInfo){
        oaNotesInfoService.updateById(oaNotesInfo);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaNotesInfo/listByPage")
    @RequiresPermissions("oaNotesInfo:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody OaNotesInfoEntity oaNotesInfo){
        Page page = new Page(oaNotesInfo.getPage(), oaNotesInfo.getLimit());
        LambdaQueryWrapper<OaNotesInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(OaNotesInfoEntity::getId, oaNotesInfo.getId());
        IPage<OaNotesInfoEntity> iPage = oaNotesInfoService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
