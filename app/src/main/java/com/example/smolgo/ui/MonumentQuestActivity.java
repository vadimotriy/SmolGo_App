package com.example.smolgo.ui;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.FinalActivity;
import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.textfield.TextInputEditText;

public class MonumentQuestActivity extends AppCompatActivity {

    String[] labelsText = {
            "Памятник Михаилу Глинке",
            "Памятник Александру Твардовскому",
            "Памятник Владимиру Ленину",
            "Памятник воинам, защитникам и освободителям Смоленска",
            "Памятник Михаилу Егорову"
    };

    String[] whereText = {
            "Парк Глинки, рядом с музыкальным училищем",
            "Городской сад, улица Теньшевой",
            "Площадь Ленина, административный центр",
            "Кремлёвская стена, площадь Победы",
            "Некрополь у Кремлёвской стены, Коммунистическая улица"
    };


    String[] aboutText = {
            "Памятник великому русскому композитору Михаилу Ивановичу Глинке, уроженцу Смоленской земли, установлен в парке его имени. Глинка родился в 1804 году в селе Новоспасское Смоленской губернии и по праву считается основоположником русской классической музыки. Автор опер «Иван Сусанин» и «Руслан и Людмила», симфонических произведений. Памятник был открыт в 1906 году по проекту скульптора Р. Р. Баха. Композитор изображён сидящим в задумчивой позе, с нотным листом в руке. Вокруг памятника разбит живописный парк с липовыми аллеями. Рядом находится Смоленское музыкальное училище, носящее имя Глинки. Это место паломничества музыкантов и всех любителей классической музыки.",
            "Памятник выдающемуся советскому поэту Александру Трифоновичу Твардовскому установлен в Городском саду на улице Теньшевой. Твардовский (1910–1971) родился в Смоленской области, в деревне Загорье. Автор знаменитых поэм «Василий Тёркин», «За далью — даль», «Тёркин на том свете». В годы Великой Отечественной войны был военным корреспондентом, его стихи поднимали боевой дух солдат. Памятник был открыт в 1980 году. Поэт изображён сидящим на скамье, с блокнотом в руке, в характерной позе задумчивого творца. Вокруг разбит сквер с именными табличками, на которых высечены строки из его произведений. Это излюбленное место отдыха смолян и литературных экскурсий.",
            "Памятник Владимиру Ильичу Ленину на площади Ленина — один из главных монументов советской эпохи в Смоленске. Установлен в 1939 году на месте снесённого кафедрального собора. Площадь стала административным центром города, здесь расположены областная администрация и другие важные учреждения. Скульптура вождя революции выполнена в характерном для того времени монументальном стиле: Ленин изображён в динамичной позе, с протянутой рукой, призывающим к действию. Высота памятника с постаментом составляет около 7 метров. Несмотря на изменение политического строя, памятник остаётся частью исторического облика города и свидетелем сложной истории России XX века.",
            "Памятник воинам, защитникам и освободителям Смоленска — величественный монумент, открытый 8 мая 2015 года к 70-летию Великой Победы. Скульптор — Игорь Чумаков. Общая высота памятника составляет 13 метров, высота фигур — 2,7 метра. Памятник представляет собой трёхгранный обелиск с фигурами трёх воинов-защитников Смоленска разных эпох: с одной стороны — защитник эпохи русско-польской войны 1609–1611 годов, с другой — воин Отечественной войны 1812 года, с третьей — солдат Великой Отечественной войны 1941–1945 годов. На каждой из трёх сторон пьедестала установлены информационные таблички, рассказывающие о героических страницах истории Смоленска. Монумент символизирует преемственность поколений защитников Отечества и напоминает о победоносных боях под Смоленском, где были проявлены истинные стойкость и мужество.",
            "Памятник Герою Советского Союза Михаилу Алексеевичу Егорову установлен на его могиле в некрополе у Смоленской крепостной стены. Михаил Егоров (1923–1975) — уроженец Смоленской области, сержант, который вместе с Мелитоном Кантария и Алексеем Берестом 30 апреля 1945 года водрузил Красное знамя над Рейхстагом в Берлине. В 21 час 30 минут Красное знамя было установлено на втором этаже рейхстага, что стало символом Победы советского народа над фашистской Германией. За этот подвиг Михаилу Егорову было присвоено звание Героя Советского Союза. После войны он жил в Смоленске, погиб 20 июня 1975 года в автомобильной катастрофе. Похоронен у стены Смоленского кремля. На могиле установлен бюст героя. Его имя носят улица в Смоленске и различные учреждения. Это место поклонения подвигу простого русского солдата, внёсшего неоценимый вклад в Великую Победу."
    };

    String[] questionText = {
            "В каком году построили памятник?",
            "Кто сидит рядом с Твардовским (только фамилия)?",
            "Что написано на памятнике?",
            "Какие года самые ранние на памятнике?",
            "Сколько наград на груди у Егорова?"
    };

    String[] yearsText = {
            "1855",
            "Теркин",
            "В. И. Ленин",
            "1609 - 1611",
            "14"
    };

    String[] yearsText2 = {
            "1906",
            "теркин",
            "В. И. ЛЕНИН",
            "1609-1611",
            "14"
    };

    int[] monumentsImages = {
            R.drawable.img_monument_quest_1,
            R.drawable.img_monument_quest_2,
            R.drawable.img_monument_quest_3,
            R.drawable.img_monument_quest_4,
            R.drawable.img_monument_quest_5
    };

    int num;
    SharedManager manager;
    TextView label, about, where, question;
    TextInputEditText year;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monument_quest);

        manager = SharedManager.getInstance(this);
        num = manager.getMonument();

        label = findViewById(R.id.info);
        about = findViewById(R.id.about);
        where = findViewById(R.id.where);
        question = findViewById(R.id.question);
        year = findViewById(R.id.year);
        image = findViewById(R.id.imageWall);

        label.setText(labelsText[num]);
        where.setText(whereText[num]);
        about.setText(aboutText[num]);
        question.setText(questionText[num]);
        image.setImageResource(monumentsImages[num]);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void backActivity(View view) {
        finish();
    }

    public void nextClick(View view) {
        String userYear = year.getText().toString().strip();

        if (userYear.equals(yearsText[num]) || userYear.equals(yearsText2[num])) {
            ++num;
        } else {
            Toast.makeText(this, "Не правильно!", LENGTH_SHORT).show();
            return;
        }

        if (num == 5) { manager.setMonument(0); manager.setMonumentStatus(2); startActivity(new Intent(this, FinalActivity.class)); }
        else {
            label.setText(labelsText[num]);
            where.setText(whereText[num]);
            about.setText(aboutText[num]);
            question.setText(questionText[num]);
            image.setImageResource(monumentsImages[num]);
            year.setText("");
            manager.setMonument(num);
        }
    }
}