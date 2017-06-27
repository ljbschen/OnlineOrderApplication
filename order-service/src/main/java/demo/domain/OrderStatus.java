package demo.domain;

// STATE:     PURCHASED -> PENDING -> ORDERED -> SHIPPED -> COMPLETED
// EVENT:  CREATED  ->  Pay  ->  App/Dec  ->  SHIP  -> DELIVER

public enum OrderStatus {
    PURCHASED {
        @Override
        public OrderStatus nextStatus(OrderEvent orderEvent) {
            if (orderEvent.getType() == OrderEventType.PAY) return PENDING;
            throw new IllegalArgumentException("Illegal order event type " + orderEvent + ". only accept " + OrderEventType.PAY);
        }
    },

    PENDING {
        @Override
        public OrderStatus nextStatus(OrderEvent orderEvent) {
            if (orderEvent.getType() == OrderEventType.DECLINE) return PURCHASED;
            else if (orderEvent.getType() == OrderEventType.APPROVE) return ORDERED;
            throw new IllegalArgumentException("Illegal order event type " + orderEvent + ". only accept " + OrderEventType.DECLINE + " and " + OrderEventType.APPROVE);
        }
    },

    ORDERED {
        @Override
        public OrderStatus nextStatus(OrderEvent orderEvent) {
            if (orderEvent.getType() == OrderEventType.SHIP) return SHIPPED;
            throw new IllegalArgumentException("Illegal order event type " + orderEvent + ". only accept " + OrderEventType.SHIP);
        }
    },

    SHIPPED {
        @Override
        public OrderStatus nextStatus(OrderEvent orderEvent) {
            if (orderEvent.getType() == OrderEventType.DELIVER) return COMPLETED;
            throw new IllegalArgumentException("Illegal order event type " + orderEvent + ". only accept " + OrderEventType.DELIVER);
        }
    },

    COMPLETED {
        @Override
        public OrderStatus nextStatus(OrderEvent orderEvent) {
            throw new IllegalArgumentException("Illegal order event type " + orderEvent + ". Order is completed");
        }
    };

    public abstract OrderStatus nextStatus(OrderEvent orderEvent);
}
