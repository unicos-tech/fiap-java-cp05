@RabbitListener(queues = "stock.update.queue")
public void onMessage(OrderRequest order){ service.apply(order); }
