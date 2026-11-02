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
import com.dehui.cle.domain.Port;
import com.dehui.cle.service.IPortService;
import com.dehui.common.utils.poi.ExcelUtil;
import com.dehui.common.core.page.TableDataInfo;

/**
 * 码头管理Controller
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/cle/port")
public class PortController extends BaseController
{
    @Autowired
    private IPortService portService;

    /**
     * 查询码头管理列表
     */
    @PreAuthorize("@ss.hasPermi('cle:port:list')")
    @GetMapping("/list")
    public TableDataInfo list(Port port)
    {
        startPage();
        List<Port> list = portService.selectPortList(port);
        return getDataTable(list);
    }

    /**
     * 导出码头管理列表
     */
    @PreAuthorize("@ss.hasPermi('cle:port:export')")
    @Log(title = "码头管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Port port)
    {
        List<Port> list = portService.selectPortList(port);
        ExcelUtil<Port> util = new ExcelUtil<Port>(Port.class);
        util.exportExcel(response, list, "码头管理数据");
    }

    /**
     * 获取码头管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cle:port:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(portService.selectPortById(id));
    }

    /**
     * 新增码头管理
     */
    @PreAuthorize("@ss.hasPermi('cle:port:add')")
    @Log(title = "码头管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Port port)
    {
        return toAjax(portService.insertPort(port));
    }

    /**
     * 修改码头管理
     */
    @PreAuthorize("@ss.hasPermi('cle:port:edit')")
    @Log(title = "码头管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Port port)
    {
        return toAjax(portService.updatePort(port));
    }

    /**
     * 删除码头管理
     */
    @PreAuthorize("@ss.hasPermi('cle:port:remove')")
    @Log(title = "码头管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(portService.deletePortByIds(ids));
    }
}
