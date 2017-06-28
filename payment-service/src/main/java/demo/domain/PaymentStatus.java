package demo.domain;

public enum PaymentStatus {
    CREATED {
        @Override
        public PaymentStatus nextStatus(PaymentEvent paymentEvent) {
            if (paymentEvent.getPaymentEventType().equals(PaymentEventType.AUTHORIZED)) return AUTHORIZED;
            else if (paymentEvent.getPaymentEventType().equals(PaymentEventType.DECLINED)) return DECLINED;
            throw new IllegalArgumentException("Illegal payment event type " + paymentEvent + ". only accept " + PaymentEventType.AUTHORIZED + " and " + PaymentEventType.DECLINED);
        }
    },

    AUTHORIZED {
        @Override
        public PaymentStatus nextStatus(PaymentEvent paymentEvent) {
            if (paymentEvent.getPaymentEventType().equals(PaymentEventType.AUTHORIZED)) return APPROVED;
            else if (paymentEvent.getPaymentEventType().equals(PaymentEventType.DECLINED)) return DECLINED;
            throw new IllegalArgumentException("Illegal payment event type " + paymentEvent + ". only accept " + PaymentEventType.APPROVED + " and " + PaymentEventType.DECLINED);
        }
    },

    APPROVED {
        @Override
        public PaymentStatus nextStatus(PaymentEvent paymentEvent) {
            throw new IllegalArgumentException("This payment is either completed or terminated");
        }
    },

    DECLINED {
        @Override
        public PaymentStatus nextStatus(PaymentEvent paymentEvent) {
            throw new IllegalArgumentException("This payment is either completed or terminated");
        }
    };

    public abstract PaymentStatus nextStatus(PaymentEvent paymentEvent);
}
