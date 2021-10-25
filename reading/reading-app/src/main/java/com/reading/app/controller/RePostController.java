package com.reading.app.controller;

import com.reading.app.domain.RePost;
import com.reading.app.service.IRePostService;
import com.reading.common.annotation.Log;
import com.reading.common.core.controller.BaseController;
import com.reading.common.core.domain.AjaxResult;
import com.reading.common.core.page.TableDataInfo;
import com.reading.common.enums.BusinessType;
import com.reading.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帖子Controller
 * 
 * @author cj
 * @date 2021-03-24
 */
@RestController
@RequestMapping("/re/repost")
public class RePostController extends BaseController
{
    @Autowired
    private IRePostService rePostService;

    /**
     * 查询帖子列表
     */

    @GetMapping("/list")
    public TableDataInfo list(RePost rePost)
    {
        startPage();
        List<RePost> list = rePostService.selectRePostList(rePost);
        return getDataTable(list);
    }

    /**
     * 导出帖子列表
     */
    @Log(title = "帖子", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RePost rePost)
    {
        List<RePost> list = rePostService.selectRePostList(rePost);
        ExcelUtil<RePost> util = new ExcelUtil<RePost>(RePost.class);
        return util.exportExcel(list, "repost");
    }

    /**
     * 获取帖子详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(rePostService.selectRePostById(id));
    }

    /**
     * 新增帖子
     */
    @Log(title = "帖子", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RePost rePost)
    {
        return toAjax(rePostService.insertRePost(rePost));
    }

    /**
     * 修改帖子
     */
    @Log(title = "帖子", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RePost rePost)
    {
        return toAjax(rePostService.updateRePost(rePost));
    }

    /**
     * 删除帖子
     */
    @Log(title = "帖子", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rePostService.deleteRePostByIds(ids));
    }
}
