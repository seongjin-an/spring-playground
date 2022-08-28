package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
//    public Item updateItem(Long itemId, Book param) { //param : 파라미터로 넘어온 준영속 상태의 엔티티
    public void updateItem(Long itemId, String name, int price, int stockQuantity) { //param : 파라미터로 넘어온 준영속 상태의 엔티티
        Item findItem = itemRepository.findOne(itemId); //같은 엔티티함를 조회한다
//        findItem.setPrice(param.getPrice()); //데이터를 수정한다.
//        findItem.setName(param.getName());
//        findItem.setStockQuantity(param.getStockQuantity()); //데이터를 수정한다.
        findItem.setPrice(price); //데이터를 수정한다.
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity); //데이터를 수정한다.
        // commit 이 되면 JPA 는 flush 를 한다. 영속성 컨텍스트에서 변경된 부분이 무엇인지 다 찾아서 반영
        //또한, 단발성으로 setXxx, setXxx 이렇게 하는 것이 아니라, addStock() 과 같은 메서드 처럼 해야
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
