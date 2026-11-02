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
import com.dehui.cle.domain.CusTaxRate;
import com.dehui.cle.service.ICusTaxRateService;
import com.dehui.common.utils.poi.ExcelUtil;
import com.dehui.common.core.page.TableDataInfo;

/**
 * 反倾销税信息Controller
 * 
 * @author ruoyi
 * @date 2026-11-02
 */
@RestController
@RequestMapping("/cle/AntiDumping")
public class CusTaxRateController extends BaseController
{
    @Autowired
    private ICusTaxRateService cusTaxRateService;

    /**
     * 查询反倾销税信息列表
     */
    @PreAuthorize("@ss.hasPermi('cle:AntiDumping:list')")
    @GetMapping("/list")
    public TableDataInfo list(CusTaxRate cusTaxRate)
    {
        startPage();
        List<CusTaxRate> list = cusTaxRateService.selectCusTaxRateList(cusTaxRate);
        return getDataTable(list);
    }

    /**
     * 导出反倾销税信息列表
     */
    @PreAuthorize("@ss.hasPermi('cle:AntiDumping:export')")
    @Log(title = "反倾销税信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CusTaxRate cusTaxRate)
    {
        List<CusTaxRate> list = cusTaxRateService.selectCusTaxRateList(cusTaxRate);
        ExcelUtil<CusTaxRate> util = new ExcelUtil<CusTaxRate>(CusTaxRate.class);
        util.exportExcel(response, list, "反倾销税信息数据");
    }

    /**
     * 获取反倾销税信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('cle:AntiDumping:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cusTaxRateService.selectCusTaxRateById(id));
    }

    /**
     * 新增反倾销税信息
     */
    @PreAuthorize("@ss.hasPermi('cle:AntiDumping:add')")
    @Log(title = "反倾销税信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CusTaxRate cusTaxRate)
    {
        return toAjax(cusTaxRateService.insertCusTaxRate(cusTaxRate));
    }

    /**
     * 修改反倾销税信息
     */
    @PreAuthorize("@ss.hasPermi('cle:AntiDumping:edit')")
    @Log(title = "反倾销税信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CusTaxRate cusTaxRate)
    {
        return toAjax(cusTaxRateService.updateCusTaxRate(cusTaxRate));
    }

    /**
     * 删除反倾销税信息
     */
    @PreAuthorize("@ss.hasPermi('cle:AntiDumping:remove')")
    @Log(title = "反倾销税信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cusTaxRateService.deleteCusTaxRateByIds(ids));
    }
}
