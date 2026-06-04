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
            "Двигайтесь к собору",
            "Древний колокол",
            "Великий полководец",
            "",
            ""
    };

    String[] aboutText = {
            "Приглашаем вас в увлекательное историческое путешествие по сердцу Смоленска! Ваше приключение начинается у величественного памятника князю Владимиру на городской набережной. Отсюда вы отправитесь в путь, чтобы узнать захватывающие факты об истории великой реки Днепр, на берегах которой зарождался и развивался наш город. Маршрут квеста проведет вас по живописной Смоленской набережной с её потрясающими видами и приведет к главной архитектурной жемчужине – величественному Успенскому собору, хранящему вековые тайны Смоленска.",
            "Летом 2015 года на набережной реки Днепр в Смоленске торжественно открыли памятник князю Владимиру. Событие было приурочено к 1000-летию со дня преставления святого равноапостольного князя Владимира. Проект новой смоленской достопримечательности был утвержден Патриархом Московским и всея Руси Кириллом. Святейший принял участие в церемонии открытия и освящении памятника святому князю Владимиру в августе 2015 года. Скульптура служит напоминанием смолянам и гостям города о крещении Руси. Автором скульптуры является смоленский художник Валерий Гращенков. Он изобразил князя Владимира с крестом в одной руке, а другой указывающего на воду (на Днепр), как бы приглашая людей креститься.",
            "Свое название «Пятницкая водяная» башня получила предположительно в период польской осады 1609-1611 годов, так как через эту башню шел забор воды из Днепра через специально прорытый ров, однако возможно, что уже в те времена в городе имелся водопровод. В ночь на 5 ноября (по новому стилю - 17 ноября) 1812 года Пятницкая башня была взорвана оставляющими Смоленск войсками императора Наполеона I. Приблизительно в 1816 году на месте пролома башни была выстроена новая каменная церковь в стиле ампир, стилизованная под древние смоленские башни годуновской крепостной стены. Храм был освящен сначала во имя святого Николая Чудотворца, затем в **** году - во имя святого Тихона Задонского (эта дата золочеными цифрами выбита на верхнем ярусе башни).",
            "Ваше приключение продолжается! Теперь вам предстоит сделать уверенный шаг вперед: двигайтесь прямо, не сворачивая с пути, до тех пор, пока перед вами не откроется дорога. Именно там, на этом новом рубеже, вас будет ждать следующая подсказка и продолжение удивительной истории Смоленска. Внимательно смотрите по сторонам, держите курс и отправляйтесь навстречу новым открытиям. В добрый путь!",
            "",
            "Двигайтесь в сторону этой прекрасной архитектурной доминанты, держа её в поле зрения. Пусть его силуэт ведет вас вперед, а на подходе к его стенам вас уже будет ждать новая загадка и продолжение удивительной истории Смоленска. Внимательно смотрите по сторонам, держите курс и отправляйтесь навстречу новым открытиям. В добрый путь!",
            "Согласно одной из них, этот католический колокол был преподнесен городу в дар в 1700 году, прибыв сюда из Велиарского монастыря*. По другой, не менее захватывающей легенде, когда-то он оглашал своим звоном окрестности костела Святой Анны. Этот храм когда-то возвышался здесь же, на Соборном холме, ровно на том месте, где сегодня стоит величественный памятник Владимиру Мономаху.",
            "В самом городе полководец ни разу не был, в городах и селах Смоленской губернии он вместе со своей армией отстаивал российские земли, но в Смоленске побывать ему так и не удалось. Однако именно на Смоленской земле он принял назначение стать главнокомандующим российской армии в 1812 году.",
            "",
            ""
    };

    String[] questionText = {
            "",
            "Сколько флагов можно увидеть рядом с памятником?",
            "Какой год высечен на храме?",
            "",
            "Выберите храм, который считается самым главным во всей Смоленской области.",
            "",
            "Каким годом датирован этот колокол?",
            "Кто это?",
            "",
            ""
    };

    String[] answersText = {
            "",
            "3",
            "1865",
            "",
            "",
            "",
            "1636",
            "кутузов",
            "",
            ""
    };

    int[] images = {
            0,
            R.drawable.img_dnepr_1,
            R.drawable.img_dnepr_2,
            0,
            0,
            0,
            R.drawable.img_dnepr_4,
            R.drawable.img_dnepr_5,
            0,
            0
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
            case 0: case 3: case 5:
                ++num; break;
            case 1: case 2: case 4: case 6: case 7:
                String userAnswer = answer.getText().toString().strip();
                answer.setText("");
                if (userAnswer.equals(answersText[num])) ++num;
                else {
                    Toast.makeText(this, "Не правильно", LENGTH_SHORT).show();
                    return;
                }
                break;

        }

        manager.setDnepr(num);

        // Если все памятники пройдены, то переход на активность финиша
        if (num == 9) {
            manager.setDnepr(0); manager.setDneprStatus(2);
            startActivity(new Intent(this, FinalActivity.class));  finish();
        } else {
            nextWay();
        }
    }

    public void nextWay() {
        Toast.makeText(this, Integer.toString(num), LENGTH_SHORT).show();
        switch (num) {
            case 0: case 3: case 5:
                label.setText(labelText[num]);
                about.setText(aboutText[num]);
                image.setVisibility(GONE);
                answer.setVisibility(GONE);
                question.setText("");
                break;
            case 1: case 2: case 6: case 7:
                answer.setVisibility(VISIBLE);
                image.setVisibility(VISIBLE);
                label.setText(labelText[num]);
                question.setText(questionText[num]);
                about.setText(aboutText[num]);
                image.setImageResource(images[num]);
                scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
                break;
            case 4:
                startActivity(new Intent(this, NearDneprQuest2Activity.class));
                finish();
                break;
            case 8:
                startActivity(new Intent(this, NearDneprQuest3Activity.class));
                finish();
                break;
        }
    }
}