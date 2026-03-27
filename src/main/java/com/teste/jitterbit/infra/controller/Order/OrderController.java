package com.teste.jitterbit.infra.controller.Order;

import com.teste.jitterbit.application.usecases.Order.CreateOrder;
import com.teste.jitterbit.application.usecases.Order.ListOrder;
import com.teste.jitterbit.domain.entitties.Items.Item;
import com.teste.jitterbit.domain.entitties.Order.Order;
import com.teste.jitterbit.infra.persistence.items.ItemsEntity;
import com.teste.jitterbit.infra.persistence.order.OrderEntity;
import com.teste.jitterbit.infra.persistence.order.OrderRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final CreateOrder createOrder;
    private final ListOrder listOrder;

    @Autowired
    OrderRepository orderRepository;

    public OrderController (CreateOrder createOrder, ListOrder listOrder) {
        this.createOrder = createOrder;
        this.listOrder = listOrder;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO dto) {
        ZonedDateTime converter = ZonedDateTime.parse(dto.creationDate());
        Order save = createOrder.createNewOrder(new Order(dto.orderId(),dto.value(), converter, dto.items()));
        OrderDTO response = new OrderDTO(
                save.getOrderNumber(),
                save.getValue(),
                save.getCreationDate().toString(),
                save.getItems()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> listOrders() {
        List<OrderDTO> listResponse = listOrder.listOrders().stream()
                .filter(Objects::nonNull)
                .map(u -> {
                    String dateStr = (u.getCreationDate() != null) ? u.getCreationDate().toString() : null;
                    return new OrderDTO(u.getOrderNumber(),u.getValue(), dateStr,u.getItems());
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("{orderid}")
    public ResponseEntity<OrderDTO> findOrder(@PathVariable String orderid) {
        OrderEntity orderEntity = orderRepository.findByOrderNumber(orderid).orElseThrow(() -> new RuntimeException("Pedido com número "
        + orderid + " Não encontrado"));

        List<Item> item = orderEntity.getItems().stream()
                .map(itemEntity -> new Item(
                        itemEntity.getId(),
                        itemEntity.getQuantity(),
                        itemEntity.getValue()
                ))
                .toList();

        OrderDTO response = new OrderDTO(orderEntity.getOrderNumber(), orderEntity.getValue(), orderEntity.getCreationDate().toString(), item);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{orderid}")
    @Transactional
    public ResponseEntity updateOrder(@PathVariable String orderid, @RequestBody OrderDTO dto) {
        OrderEntity orderEntity = orderRepository.findByOrderNumber(orderid)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        orderEntity.setValue(dto.value());
        orderEntity.setCreationDate(ZonedDateTime.parse(dto.creationDate()));
        orderEntity.getItems().clear();

        dto.items().forEach(itemDto -> {
            ItemsEntity novoItem = new ItemsEntity();
            novoItem.setProductId(itemDto.getProductId());
            novoItem.setQuantity(itemDto.getQuantity());
            novoItem.setValue(itemDto.getPrice());
            novoItem.setOrder(orderEntity);
            orderEntity.getItems().add(novoItem);
        });
        return ResponseEntity.ok(new OrderDTO(
                orderEntity.getOrderNumber(),
                orderEntity.getValue(),
                orderEntity.getCreationDate().toString(),
                dto.items()
        ));
    }

    @DeleteMapping("{orderid}")
    @Transactional
    public ResponseEntity deleteOrder(@PathVariable String orderid) {
        OrderEntity orderEntity = orderRepository.findByOrderNumber(orderid)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        orderRepository.delete(orderEntity);

        return ResponseEntity.ok("DELETADO PEDIDO " + orderid);
    }
}
