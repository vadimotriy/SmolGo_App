package com.example.smolgo.ui;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.textfield.TextInputEditText;

public class NearDneprQuestActivity extends AppCompatActivity {
    int num;
    SharedManager manager;
    TextView label, about, question;
    TextInputEditText answer;
    ImageView image;
    ScrollView scrollView;

    String[] labelText = {
      "Добро пожаловать!",
      "Князь Владимир",
      "Пятницкие водяные ворота",
      "Двигайтесь прямо",
      "Главная святыня Смоленска",
      ""
    };

    String[] aboutText = {
            "Приглашаем вас в увлекательное историческое путешествие по сердцу Смоленска! Ваше приключение начинается у величественного памятника князю Владимиру на городской набережной. Отсюда вы отправитесь в путь, чтобы узнать захватывающие факты об истории великой реки Днепр, на берегах которой зарождался и развивался наш город. Маршрут квеста проведет вас по живописной Смоленской набережной с её потрясающими видами и приведет к главной архитектурной жемчужине – величественному Успенскому собору, хранящему вековые тайны Смоленска.",
            "Летом 2015 года на набережной реки Днепр в Смоленске торжественно открыли памятник князю Владимиру. Событие было приурочено к 1000-летию со дня преставления святого равноапостольного князя Владимира. Проект новой смоленской достопримечательности был утвержден Патриархом Московским и всея Руси Кириллом. Святейший принял участие в церемонии открытия и освящении памятника святому князю Владимиру в августе 2015 года. Скульптура служит напоминанием смолянам и гостям города о крещении Руси. Автором скульптуры является смоленский художник Валерий Гращенков. Он изобразил князя Владимира с крестом в одной руке, а другой указывающего на воду (на Днепр), как бы приглашая людей креститься.",
            "Свое название «Пятницкая водяная» башня получила предположительно в период польской осады 1609-1611 годов, так как через эту башню шел забор воды из Днепра через специально прорытый ров, однако возможно, что уже в те времена в городе имелся водопровод. В ночь на 5 ноября (по новому стилю - 17 ноября) 1812 года Пятницкая башня была взорвана оставляющими Смоленск войсками императора Наполеона I. Приблизительно в 1816 году на месте пролома башни была выстроена новая каменная церковь в стиле ампир, стилизованная под древние смоленские башни годуновской крепостной стены. Храм был освящен сначала во имя святого Николая Чудотворца, затем в **** году - во имя святого Тихона Задонского (эта дата золочеными цифрами выбита на верхнем ярусе башни).",
            "Ваше приключение продолжается! Теперь вам предстоит сделать уверенный шаг вперед: двигайтесь прямо, не сворачивая с пути, до тех пор, пока перед вами не откроется дорога. Именно там, на этом новом рубеже, вас будет ждать следующая подсказка и продолжение удивительной истории Смоленска. Внимательно смотрите по сторонам, держите курс и отправляйтесь навстречу новым открытиям. В добрый путь!",
            "",
            ""
    };

    String[] questionText = {
            "",
            "Сколько флагов можно увидеть рядом с памятником?",
            "Какой год высечен на храме?",
            "",
            "Выберите храм, который считается самым главным во всей Смоленской области."
    };

    String[] answersText = {
            "",
            "3",
            "1865",
            "",
            ""
    };

    int[] images = {
            0,
            R.drawable.img_dnepr_1,
            R.drawable.img_dnepr_2,
            0,
            0

    };

    int[][] many_images = {
            {},
            {},
            {},
            {R.drawable.img_dnepr_3_1, R.drawable.img_dnepr_3_2, R.drawable.img_dnepr_3_3, R.drawable.img_dnepr_3_4},
            {}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_near_dnepr_quest);

        manager = SharedManager.getInstance(this);
        num = manager.getDnepr();

        label = findViewById(R.id.info);
        about = findViewById(R.id.about);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        image = findViewById(R.id.imageWall);
        scrollView = findViewById(R.id.scrollView);

        nextWay();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Выход из активности
    public void backActivity(View view) {
        finish();
    }

    // Переход на следующий памятник
    public void nextClick(View view) {

        switch (num) {
            case 0:
                ++num; break;
            case 1: case 2: case 4:
                String userAnswer = answer.getText().toString().strip();
                answer.setText("");
                if (userAnswer.equals(answersText[num])) ++num;
                break;

        }

        // Если все памятники пройдены, то переход на активность финиша
        if (num == 7) {
            manager.setDnepr(0); manager.setDneprStatus(2);
            startActivity(new Intent(this, FinalActivity.class));  finish();
        } else {
            nextWay();
            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
        }
    }

    public void nextWay() {
        switch (num) {
            case 0:
                label.setText(labelText[num]);
                about.setText(aboutText[num]);
                image.setVisibility(GONE);
                answer.setVisibility(GONE);
                question.setText("");
                break;
            case 1: case 2: case 4:
                answer.setVisibility(VISIBLE);
                image.setVisibility(VISIBLE);
                label.setText(labelText[num]);
                question.setText(questionText[num]);
                about.setText(aboutText[num]);
                image.setImageResource(images[num]);
                break;
            case 3:


        }
    }
}