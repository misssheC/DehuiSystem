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
import com.dehui.cle.domain.Tax;
import com.dehui.cle.service.ITaxService;
import com.dehui.common.utils.poi.ExcelUtil;
import com.dehui.common.core.page.TableDataInfo;

/**
 * 税率查询Controller
 * 
 * @author ruoyi
 * @date 2026-11-02
 */
@RestController
@RequestMapping("/cle/tax")
public class TaxController extends BaseController
{
    @Autowired
    private ITaxService taxService;

    /**
     * 查询税率查询列表
     */
    @PreAuthorize("@ss.hasPermi('cle:tax:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tax tax)
    {
        startPage();
        List<Tax> list = taxService.selectTaxList(tax);
        return getDataTable(list);
    }

    /**
     * 导出税率查询列表
     */
    @PreAuthorize("@ss.hasPermi('cle:tax:export')")
    @Log(title = "税率查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tax tax)
    {
        List<Tax> list = taxService.selectTaxList(tax);
        ExcelUtil<Tax> util = new ExcelUtil<Tax>(Tax.class);
        util.exportExcel(response, list, "税率查询数据");
    }

    /**
     * 获取税率查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('cle:tax:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taxService.selectTaxById(id));
    }

    /**
     * 新增税率查询
     */
    @PreAuthorize("@ss.hasPermi('cle:tax:add')")
    @Log(title = "税率查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tax tax)
    {
        return toAjax(taxService.insertTax(tax));
    }

    /**
     * 修改税率查询
     */
    @PreAuthorize("@ss.hasPermi('cle:tax:edit')")
    @Log(title = "税率查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tax tax)
    {
        return toAjax(taxService.updateTax(tax));
    }

    /**
     * 删除税率查询
     */
    @PreAuthorize("@ss.hasPermi('cle:tax:remove')")
    @Log(title = "税率查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taxService.deleteTaxByIds(ids));
    }
}
