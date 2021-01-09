package in.codingboy.aioofbot;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    OkHttpClient okHttpClient;
    Request request;
    Response response=null;
    JSONObject jsonObject;

    String sendCoronaDataNumbers="";
    static String welcomemessage =
            "Thank you for using ArrayIndexOutOfBound Bot \uD83D\uDE09.\n\n" +
            "This bot will show you Programming quotes,Programming jokes,random\n" +
            "jokes and Covid19 Global data,more features are coming soon \uD83D\uDD25.\n" +
            "\n\n" +
            "Developer \uD83D\uDC68\u200D\uD83D\uDCBB : https://instagram.com/coding_boy_";
    JSONParser parser =  new JSONParser();
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();

        if(update.getMessage().getText().equals("/start") || update.getMessage().getText().equals("Back") || update.getMessage().getText().equals("/start@arrayindexoutofboundbot"))
        {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List <KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow row;

            row=new KeyboardRow();
            row.add("Programming joke \uD83D\uDE01");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Joke");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Quote");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("COVID \uD83E\uDDA0 GLOBAL DATA \uD83D\uDCCA");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Unlimited Courses");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("CS & Programming Books \uD83D\uDCDA");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("About Us");
            keyboardRowList.add(row);

            replyKeyboardMarkup.setKeyboard(keyboardRowList);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMessage.setText("Hii "+ update.getMessage().getFrom().getFirstName() + " \uD83D\uDE4B\u200D♂️,\n\n" +welcomemessage);
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
       else if (update.getMessage().getText().equals("Programming joke \uD83D\uDE01"))
        {
            try
            {
                okHttpClient = new OkHttpClient();
                request=new Request.Builder()
                        .url("https://official-joke-api.appspot.com/jokes/programming/random")
                        .get()
                        .build();
                response = okHttpClient.newCall(request).execute();
                String data = response.body().string();
               // jsonObject = (JSONObject)parser.parse(data);
                JSONArray jsonArray = (JSONArray)parser.parse(data);
                System.out.println(jsonArray.get(0));
                JSONObject jokejsonobject = (JSONObject)jsonArray.get(0);
                String sendJoke = jokejsonobject.get("setup")+"\n\n"+jokejsonobject.get("punchline");

                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText(sendJoke);
                execute(sendMessage);
            }
            catch (Exception e){ e.printStackTrace();}
        }

        else if (update.getMessage().getText().equals("Joke") )
        {
            try
            {
                okHttpClient = new OkHttpClient();
                request=new Request.Builder()
                        .url("https://official-joke-api.appspot.com/jokes/random")
                        .get()
                        .build();
                response = okHttpClient.newCall(request).execute();
                String data = response.body().string();
                // jsonObject = (JSONObject)parser.parse(data);

                JSONObject jokejsonobject = (JSONObject)parser.parse(data);
                String sendJoke = jokejsonobject.get("setup")+"\n\n"+jokejsonobject.get("punchline");

                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText(sendJoke);
                execute(sendMessage);
            }
            catch (Exception e){ e.printStackTrace();}
        }
        else if (update.getMessage().getText().equals("Quote"))
        {
            try
            {
                okHttpClient = new OkHttpClient();
                request=new Request.Builder()
                        .url("https://programming-quotes-api.herokuapp.com/quotes/random")
                        .get()
                        .build();
                response = okHttpClient.newCall(request).execute();
                String data = response.body().string();
                // jsonObject = (JSONObject)parser.parse(data);

                JSONObject jokejsonobject = (JSONObject)parser.parse(data);
                String sendJoke = jokejsonobject.get("en")+"\n\n -" +jokejsonobject.get("author");

                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText(sendJoke);
                execute(sendMessage);
            }
            catch (Exception e){ e.printStackTrace();}
        }

        else if(update.getMessage().getText().equals("COVID \uD83E\uDDA0 GLOBAL DATA \uD83D\uDCCA"))
        {
            try {
                okHttpClient = new OkHttpClient();
                request = new Request.Builder()
                        .url("https://disease.sh/v2/all")
                        .get()
                        .build();
                Response response = okHttpClient.newCall(request).execute();
                String data = response.body().string();
                JSONParser jsonParser=new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(data);

                sendMessage.setText("COVID 19 GLOBAL DATA\n\nTotal cases : "+jsonObject.get("cases")+
                                    "\nRecovered : "+jsonObject.get("recovered")+
                                    "\nCritical : "+jsonObject.get("critical")+
                                    "\nActive : "+jsonObject.get("active")+
                                    "\nToday Cases : "+jsonObject.get("todayCases")+
                                    "\nTotal Deaths : "+jsonObject.get("deaths")+
                                    "\nToday Deaths : "+jsonObject.get("todayDeaths"));
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (update.getMessage().getText().equals("Unlimited Courses"))
        {
            try
            {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Get Unlimited Courses including SEO Course, Linux, some programming languages courses.\n" +
                        "Happy Learning!\n" +
                        "https://drive.google.com/drive/folders/0B3Qd1rlyIyR5TGlyYmlsSHBlcVU?usp=sharing\n");
                execute(sendMessage);
            }
            catch (Exception e){ e.printStackTrace();}
        }

        else if (update.getMessage().getText().equals("CS & Programming Books \uD83D\uDCDA"))
        {
            try
            {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("500+ Free Computer Science and Programming Books \n\n " +
                        "https://drive.google.com/drive/folders/0B9XbEQh3jB9pWVBFX0hqTzA0dUU");
                execute(sendMessage);
            }
            catch (Exception e){ e.printStackTrace();}
        }

        else if (update.getMessage().getText().equals("About Us"))
        {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List <KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow row;

            row=new KeyboardRow();
            row.add("Instagram");
            row.add("Twitter");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Facebook");
            row.add("Youtube");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Our Blog");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Source Code of this Bot");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Back");
            keyboardRowList.add(row);

            replyKeyboardMarkup.setKeyboard(keyboardRowList);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);

            try
            {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Hello "+update.getMessage().getFrom().getFirstName()+" ,Array Index Out Of Bound (AiooB) is Telegram Bot developed by " +
                        "https://instagram.com/coding_boy_, 50K+ programmers community on the instagram" +
                        "and we are providing daily useful Programming,Java,android development,tips-tricks ,projects and tech content." +
                        "\n\n");
                execute(sendMessage);

            }
            catch (Exception e){ e.printStackTrace();}
        }


        //All Social media and Contact Links

        else if(update.getMessage().getText().equals("Instagram"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Follow us On Instagram");
                execute(sendMessage);
                sendMessage.setText("\n https://instagram.com/coding_boy_");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("Twitter"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Follow us On Twitter");
                execute(sendMessage);
                sendMessage.setText("\n https://twitter.com/MrCodingBoy");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("Facebook"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Like our Facebook page");
                execute(sendMessage);
                sendMessage.setText("\n https://www.facebook.com/thecodingboy");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("Youtube"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Subscribe to our Youtube Channel");
                execute(sendMessage);
                sendMessage.setText("\n https://www.youtube.com/channel/UCcS31rdaPf42mS7B12VVfhw?view_as=subscriber");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("Our Blog"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Blog : ");
                execute(sendMessage);
                sendMessage.setText("\n https://codingboy.in");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("Source Code of this Bot"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Source code of this bot");
                execute(sendMessage);
                sendMessage.setText("\n https://github.com/viralvaghela/Telegram-Bot-Array-Index-Out-Of-Bound");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else
        {
            sendMessage.setText("Hii "+ update.getMessage().getFrom().getFirstName() + " \uD83D\uDE4B\u200D♂️,\n\n" +welcomemessage);
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    private void concateNumbers(String country, String total_cases, String total_recovered, String total_deaths) {
        sendCoronaDataNumbers=sendCoronaDataNumbers+country+total_cases+total_recovered+total_deaths+"\n";
        System.out.println(sendCoronaDataNumbers);
    }

    @Override
    public String getBotUsername() {
        return "ArrayIndexOutOfBound";
    }
    @Override
    public String getBotToken() {
        return "";
    }
}
