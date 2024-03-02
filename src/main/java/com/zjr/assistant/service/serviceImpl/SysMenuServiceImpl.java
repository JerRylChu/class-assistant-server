package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.entities.Menu;
import com.zjr.assistant.mapper.SysMenuMapper;
import com.zjr.assistant.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取菜单
     * @param isAdmin
     * @return
     */
    @Override
    public List<Menu> getSysMenu() {
        return sysMenuMapper.getSysMenu();
    }
}
