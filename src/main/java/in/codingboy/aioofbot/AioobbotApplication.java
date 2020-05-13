package in.codingboy.aioofbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@Controller
@SpringBootApplication
public class AioobbotApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(AioobbotApplication.class, args);
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try
        {
            telegramBotsApi.registerBot(new MyBot());
        }
        catch (Exception e){e.printStackTrace();}
    }
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

}
