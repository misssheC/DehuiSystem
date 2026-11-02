package com.dehui.cle.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dehui.common.annotation.Log;
import com.dehui.common.core.controller.BaseController;
import com.dehui.common.core.domain.AjaxResult;
import com.dehui.common.enums.BusinessType;
import com.dehui.cle.domain.hs;
import com.dehui.cle.service.IhsService;
import com.dehui.common.utils.poi.ExcelUtil;
import com.dehui.common.core.page.TableDataInfo;

/**
 * HS查询Controller
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/cle/HsSearch")
public class hsController extends BaseController
{
    @Autowired
    private IhsService hsService;

    /**
     * 查询HS查询列表
     */
    @PreAuthorize("@ss.hasPermi('cle:HsSearch:list')")
    @GetMapping("/list")
    public TableDataInfo list(hs hs)
    {
        startPage();
        List<hs> list = hsService.selecthsList(hs);
        return getDataTable(list);
    }

    /**
     * 导出HS查询列表
     */
    @PreAuthorize("@ss.hasPermi('cle:HsSearch:export')")
    @Log(title = "HS查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, hs hs)
    {
        List<hs> list = hsService.selecthsList(hs);
        ExcelUtil<hs> util = new ExcelUtil<hs>(hs.class);
        util.exportExcel(response, list, "HS查询数据");
    }

    /**
     * 获取HS查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('cle:HsSearch:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsService.selecthsById(id));
    }

    /**
     * 新增HS查询
     */
    @PreAuthorize("@ss.hasPermi('cle:HsSearch:add')")
    @Log(title = "HS查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody hs hs)
    {
        return toAjax(hsService.inserths(hs));
    }

    /**
     * 修改HS查询
     */
    @PreAuthorize("@ss.hasPermi('cle:HsSearch:edit')")
    @Log(title = "HS查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody hs hs)
    {
        return toAjax(hsService.updatehs(hs));
    }

    /**
     * 删除HS查询
     */
    @PreAuthorize("@ss.hasPermi('cle:HsSearch:remove')")
    @Log(title = "HS查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsService.deletehsByIds(ids));
    }
}
