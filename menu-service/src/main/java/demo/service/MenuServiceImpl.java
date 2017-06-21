package demo.service;

import demo.domain.MenuItem;
import demo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuServiceImpl (MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void deleteByItemName(String itemName) {
        menuItemRepository.deleteByItemName(itemName);
    }

    @Override
    public MenuItem findByItemName(String itemName) {
        return menuItemRepository.findByItemName(itemName);
    }

    @Override
    public Page<MenuItem> findByItemType(String itemType, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MenuItem> findByItemPriceBetween(double low, double high, Pageable pageable) {
        return null;
    }
}
