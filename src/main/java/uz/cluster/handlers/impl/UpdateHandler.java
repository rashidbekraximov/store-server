package uz.cluster.handlers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import uz.cluster.dao.MessageSend;
import uz.cluster.handlers.Handler;
import uz.cluster.services.TelegramService;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UpdateHandler implements Handler<Update> {

    private final TelegramService telegramService;

    @Override
    public void handle(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                telegramService.sendMessage(new MessageSend(message.getChatId(), "Assalomu aleykum\uD83C\uDF39\n" +
                        "\n" +
                        "\"Brand Collection' da  sizni ko’rib turganimizdan hursandmiz\uD83E\uDD70 \n" +
                        "\n" +
                        "Mahsulotlarimiz bilan batafsil tanishishingiz mumkin❤️",createCatalogInlineKeyboard()));
            }
        } else if (update.hasInlineQuery()) {
        } else if (update.hasCallbackQuery()) {
        } else if (update.hasPreCheckoutQuery()) {
        }
    }


    private InlineKeyboardMarkup createCatalogInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\uD83D\uDC4B Ko'rish");
        inlineKeyboardButton1.setWebApp(new WebAppInfo("https://brandcollection0420.netlify.app/"));
        inlineKeyboardMarkup.setKeyboard(List.of(List.of(inlineKeyboardButton1)));
        return inlineKeyboardMarkup;
    }

}

