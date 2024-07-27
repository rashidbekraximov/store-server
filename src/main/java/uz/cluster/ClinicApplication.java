package uz.cluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.cluster.bot.TelegramBot;
import uz.cluster.configuration.OpenApiProperties;
import uz.cluster.handlers.impl.UpdateHandler;


@EnableConfigurationProperties({
        OpenApiProperties.class,
})
@EnableJpaRepositories(value = {"uz.cluster.*"})
@ComponentScan(value = {"uz.cluster.*"})
@SpringBootApplication()
public class ClinicApplication  {

    private static final Logger logger = LoggerFactory.getLogger(ClinicApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = SpringApplication.run(ClinicApplication.class, args);
        UpdateHandler up = appContext.getBean(UpdateHandler.class);
        TelegramBot telegramBot = new TelegramBot(up);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
            System.out.println("Bot ishga tushdi");
        }catch (TelegramApiException e){
            System.out.println("Bot ishga tushishda xatolik yuzaga keldi !");
        }
    }
}
