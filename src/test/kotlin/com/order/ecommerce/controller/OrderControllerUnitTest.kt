package com.order.ecommerce.controller

import com.order.ecommerce.dto.OrderCreateResponse
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.service.OrderService
import com.order.ecommerce.util.OrderUtil
import com.order.ecommerce.util.OrderUtil.Companion.mockOrderId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class OrderControllerUnitTest {


    @Mock
    lateinit var orderService: OrderService

    @InjectMocks
    lateinit var orderController: OrderController

    private val orderDtoRequest: OrderDto = OrderUtil.createTestOrder()
    private val mockOrderCreateResponse: OrderCreateResponse =
        OrderCreateResponse(mockOrderId, "PROCESSING")
    private val mockOrderGetResponse = OrderUtil.createMockOrderResponse()
    private val mockOrderUpdateResponse: OrderCreateResponse =
    OrderCreateResponse(mockOrderId, "PROCESSING")


    @Test
    fun testCreateOrder() {
        Mockito.`when`(orderService.createOrder(orderDtoRequest))
            .thenReturn(mockOrderCreateResponse)
        val actualResponse = orderController.createOrder(orderDtoRequest)
        assertThat(actualResponse).isEqualTo(mockOrderCreateResponse)
    }

    @Test
    fun testGetOrder() {
        Mockito.`when`(orderService.findOrderById(mockOrderId))
            .thenReturn(mockOrderGetResponse)
        val actualResponse = orderController.findOrderById(mockOrderId)
        assertThat(actualResponse).isEqualTo(mockOrderGetResponse)
    }

    @Test
    fun testUpdateOrder(){
        Mockito.`when`(orderService.updateOrderStatus(mockOrderId,"PROCESSING"))
            .thenReturn(mockOrderUpdateResponse)
        val actualResponse = orderController.updateOrderStatus(mockOrderId, "PROCESSING")

        assertThat(actualResponse).isEqualTo(mockOrderUpdateResponse)
    }

}
