package outages.service;

import java.util.UUID;

public interface SentNotificationService {
    void markAsSent(Long chatId, UUID outageId, Boolean isCancelling);

    boolean existsByIdChatIdAndIdOutageId(Long chatId, UUID outageId, Boolean isCancelling);
}
