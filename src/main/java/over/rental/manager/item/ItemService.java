package over.rental.manager.item;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Page<Item> findItems(String keyword, Pageable pageable){
        if (keyword.isBlank()) {
            return itemRepository.findAll(pageable);
        }
        return itemRepository.findByNameContaining(keyword, pageable);
    }

    public Item findItem(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("물건을 찾을 수 없습니다."));
    }

    public void updateItem(Long id, ItemForm form) {
        Item item = findItem(id);
        item.update(form.getName(), form.getDescription());
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public void rentItem(Long id) {
        Item item = findItem(id);
        item.rent();
        itemRepository.save(item);
    }

    public void returnItem(Long id) {
        Item item = findItem(id);
        item.returnItem();
        itemRepository.save(item);
    }

    public Item createItem(ItemForm form) {
        Item item = new Item(form.getName(), form.getDescription());
        itemRepository.save(item);
        return item;
    }
}
