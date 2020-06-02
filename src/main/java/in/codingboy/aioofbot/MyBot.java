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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    OkHttpClient okHttpClient;
    Request request;
    Response response=null;
    JSONObject jsonObject;
    String sendCoronaDataTitle = "Country|total_cases|total_recovered|total_deaths\n";
    String sendCoronaDataNumbers="";
    static String welcomemessage =
            "Thank you for using ArrayIndexOutOfBound Bot \uD83D\uDE09.\n\n" +
            "This bot will show you Programming quotes,jokes,random\n" +
            "jokes and Covid19 Global data,more features are coming soon \uD83D\uDD25.\n" +
            "\n" +
            "List of commands.\n\n" +
            "/programmingjoke - Programming Joke\n" +
            "/joke - Random Joke\n" +
            "/quote - Programming Quote\n\n"+
            "/covid\n\n"+
            "Developer @viralvaghela";
    JSONParser parser =  new JSONParser();
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();

        if(update.getMessage().getText().equals("/start"))
        {
            sendMessage.setText("Hii "+ update.getMessage().getFrom().getUserName() + " \uD83D\uDE4B\u200D♂️,\n\n" +welcomemessage);
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
       else if (update.getMessage().getText().equals("/programmingjoke") || update.getMessage().getText().equals("/programmingjoke@arrayindexoutofboundbot")  )
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

        else if (update.getMessage().getText().equals("/joke") || update.getMessage().getText().equals("/joke@arrayindexoutofboundbot"))
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
        else if (update.getMessage().getText().equals("/quote") || update.getMessage().getText().equals("/quote@arrayindexoutofboundbot"))
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


        //This Command is NOT WORKING ,i will fix it soon :)
        else if(update.getMessage().getText().equals("/covid") || update.getMessage().getText().equals("/covid@arrayindexoutofboundbot"))
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
        else
        {
            sendMessage.setText("Hii "+ update.getMessage().getFrom().getUserName() + " \uD83D\uDE4B\u200D♂️,\n\n" +welcomemessage);
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
        return "BOT TOKEN";//generate yout token from Bot Father
    }
}
