package com.example.akinator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addCitat();
        //Таймер
        Timer myTimer;
        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            public void run() {
                timerTick();
            }
        }, 1000, 10000); // каждые 5 секунд
    }
    //Таймер
    private void timerTick() {
        this.runOnUiThread(doTask);
    }
    //Таймер
    private Runnable doTask = new Runnable() {
        public void run() {
            Toast toast = Toast.makeText(getApplicationContext(), "Мяу!", Toast.LENGTH_SHORT);
            toast.show();
        }
    };
    HashMap<Integer, String> citats = new HashMap<>();
    public void addCitat(){
        citats.put(1, "ХМ?");
        citats.put(2, "Нет");
        citats.put(3, "Не уверен");
        citats.put(4, "Сейчас не смогу ответить тебе");
        citats.put(5, "Да");
        citats.put(6, "Абсолютно, да");
        citats.put(7, "Не понимаю вопроса");
        citats.put(8, "Это будет сегодня");
        citats.put(9, "Точно не смогу ответить");
    }
    private Random random = new Random();


    public void onClick(View view){
        EditText contetn = findViewById(R.id.edTXT);
        String str = contetn.getText().toString();
        Log.d("DEBUG", "Сообщение: " + str);
        if (str != null && !str.trim().isEmpty()) {
            int randomKey = random.nextInt(citats.size()); // Индекс массива начинается с 0, поэтому не нужно добавлять 1
            String randomQuote = citats.get(randomKey);
            Toast.makeText(getApplicationContext(), randomQuote, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Вы ничего не спросили!!!", Toast.LENGTH_SHORT).show();
        }
    }
}