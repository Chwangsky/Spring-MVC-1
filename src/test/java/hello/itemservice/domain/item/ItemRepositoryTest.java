package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("bag", 10000, 5);
        // when
        itemRepository.save(item);
        // then
        Item id = itemRepository.findById(item.getId());
        assertThat(id).isEqualTo(item);


    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemA", 10000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> all = itemRepository.findAll();

        // then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1, item2);

    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("item1", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long id = savedItem.getId();

        // when
        Item item2 = new Item("item2", 20000, 30);

        itemRepository.update(id, item2);

        // then
        Item findItem = itemRepository.findById(id);

        assertThat(findItem.getItemName()).isEqualTo(item2.getItemName());
    }
}
