package com.dehui.cle.controller;

import java.util.List;

import com.dehui.common.utils.SecurityUtils;
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
import com.dehui.cle.domain.Waybill;
import com.dehui.cle.service.IWaybillService;
import com.dehui.common.utils.poi.ExcelUtil;
import com.dehui.common.core.page.TableDataInfo;

/**
 * 我的运单Controller
 * 
 * @author ruoyi
 * @date 2026-10-29
 */
@RestController
@RequestMapping("/cle/waybill")
public class WaybillController extends BaseController
{
    @Autowired
    private IWaybillService waybillService;

    /**
     * 查询我的运单列表
     */
    @PreAuthorize("@ss.hasPermi('cle:waybill:list')")
    @GetMapping("/list")
    public TableDataInfo list(Waybill waybill)
    {
        startPage();
        List<Waybill> list = waybillService.selectWaybillList(waybill);
        return getDataTable(list);
    }

    /**
     * 导出我的运单列表
     */
    @PreAuthorize("@ss.hasPermi('cle:waybill:export')")
    @Log(title = "我的运单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Waybill waybill)
    {
        List<Waybill> list = waybillService.selectWaybillList(waybill);
        ExcelUtil<Waybill> util = new ExcelUtil<Waybill>(Waybill.class);
        util.exportExcel(response, list, "我的运单数据");
    }

    @PreAuthorize("@ss.hasPermi('cle:waybill:my:export')")
    @Log(title = "我的运单", businessType = BusinessType.EXPORT)
    @PostMapping("/my/export")
    public void exportMy(HttpServletResponse response, Waybill waybill)
    {
        waybill.setUserId(SecurityUtils.getUserId());
        List<Waybill> list = waybillService.selectMyWaybillList(waybill);
        ExcelUtil<Waybill> util = new ExcelUtil<Waybill>(Waybill.class);
        util.exportExcel(response, list, "我的运单数据");
    }

    /**
     * 获取我的运单详细信息
     */
    @PreAuthorize("@ss.hasPermi('cle:waybill:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(waybillService.selectWaybillById(id));
    }

    /**
     * 新增我的运单
     */
    @PreAuthorize("@ss.hasPermi('cle:waybill:add')")
    @Log(title = "我的运单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Waybill waybill)
    {
        return toAjax(waybillService.insertWaybill(waybill));
    }

    /**
     * 修改我的运单
     */
    @PreAuthorize("@ss.hasPermi('cle:waybill:edit')")
    @Log(title = "我的运单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Waybill waybill)
    {
        return toAjax(waybillService.updateWaybill(waybill));
    }

    /**
     * 删除我的运单
     */
    @PreAuthorize("@ss.hasPermi('cle:waybill:remove')")
    @Log(title = "我的运单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(waybillService.deleteWaybillByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('cle:waybill:my')")
    @GetMapping("/my/list")
    public TableDataInfo myList(Waybill waybill) {
        startPage();
        List<Waybill> list = waybillService.selectMyWaybillList(waybill);
        return getDataTable(list);
    }

    /**
     * 获取我的运单详情
     */
    @PreAuthorize("@ss.hasPermi('cle:waybill:my')")
    @GetMapping("/my/{id}")
    public AjaxResult myGetInfo(@PathVariable("id") Long id) {
        return success(waybillService.selectMyWaybillById(id));
    }
}
