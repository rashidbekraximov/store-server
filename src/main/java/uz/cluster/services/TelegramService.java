package uz.cluster.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.cluster.bot.Data;
import uz.cluster.dao.MessageSend;
import uz.cluster.exceptions.FailedSendMessageException;

@Service
public class TelegramService extends DefaultAbsSender {

    protected TelegramService() {
        super(new DefaultBotOptions(), Data.token);
    }

    public Message sendMessage(MessageSend message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(message.getText());
        sendMessage.setParseMode("HTML");
        if (message.getKeyboard() != null) {
            sendMessage.setReplyMarkup(message.getKeyboard());
        }
        try {
            return execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new FailedSendMessageException(String.format("Failed send text message %s", message), e);
        }
    }
}

