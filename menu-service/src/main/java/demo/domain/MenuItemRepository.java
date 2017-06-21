package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{
    void deleteByItemName(String itemName);

    MenuItem findByItemName(String itemName);

    Page<MenuItem> findByItemType(String itemType, Pageable pageable);

    Page<MenuItem> findByItemPriceBetween(double low, double high, Pageable pageable);
}
