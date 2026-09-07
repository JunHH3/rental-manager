package over.rental.manager.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public Page<Item> findItems(String keyword, Pageable pageable) {
        if (keyword.isBlank()) {
            return itemRepository.findAll(pageable);
        }
        return itemRepository.findByNameContaining(keyword, pageable);
    }


    @Transactional
    public Item createItem(ItemForm form) {
        Item item = new Item(form.getName(), form.getDescription());
        itemRepository.save(item);
        return item;
    }

    @Transactional(readOnly = true)
    public Item findItem(Long id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("물건을 찾을 수 없습니다.")
        );
    }

    @Transactional
    public void updateItem(Long id, ItemForm form) {
        Item item = findItem(id);
        item.update(form.getName(), form.getDescription());
        itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public void rentItem(Long id) {
        Item item = findItem(id);
        item.rent();
        itemRepository.save(item);
    }

    @Transactional
    public void returnItem(Long id) {
        Item item = findItem(id);
        item.returnRent();
        itemRepository.save(item);
    }

}
