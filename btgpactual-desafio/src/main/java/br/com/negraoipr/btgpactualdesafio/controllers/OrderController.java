package br.com.negraoipr.btgpactualdesafio.controllers;


import br.com.negraoipr.btgpactualdesafio.controllers.dto.ApiResponse;
import br.com.negraoipr.btgpactualdesafio.controllers.dto.PaginationResponse;
import br.com.negraoipr.btgpactualdesafio.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping({"/customer/{customerid}/orders", "/customers/{customerid}/order"})
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerid") Long customerid,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

        var pageResponse = orderService.findAllByCustomerId(customerid, PageRequest.of(page, pageSize));

        return ResponseEntity.ok(new ApiResponse<>(pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)));
    }

}
