package demo.service;

import demo.domain.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {
    void deleteByItemName(String itemName);

    MenuItem findByItemName(String itemName);

    Page<MenuItem> findByItemType(String itemType, Pageable pageable);

    Page<MenuItem> findByItemPriceBetween(double low, double high, Pageable pageable);
}
