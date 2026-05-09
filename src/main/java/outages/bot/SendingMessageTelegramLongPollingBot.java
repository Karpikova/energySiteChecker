package outages.bot;

import org.telegram.telegrambots.meta.generics.LongPollingBot;
import outages.pojo.Outage;
import outages.pojo.OutageV1;

public interface SendingMessageTelegramLongPollingBot extends LongPollingBot {

    boolean sendMessage(Outage outage, Long chatIds);

    boolean sendMessage(String text, Long chatId);
}
