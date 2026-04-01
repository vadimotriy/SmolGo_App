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

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.textfield.TextInputEditText;

// Активность с квестом "Памятники"
public class MonumentQuestActivity extends AppCompatActivity {
    // Массив с названиями памятников
    String[] labelsText = {
            "Памятник Михаилу Глинке",
            "Памятник Александру Твардовскому",
            "Памятник Владимиру Ленину",
            "Памятник воинам, защитникам и освободителям Смоленска",
            "Бюст Михаилу Егорову"
    };

    // Массив с примерным место положением памятников
    String[] whereText = {
            "Сад Блонье",
            "Напротив арбитражного суда",
            "Площадь Ленина",
            "Рядом с кинотеатром Октябрь",
            "У башни Донец"
    };

    // Массив с обозначением мест для карты
    String[] objectNameText = {
            "Сад Блонье",
            "Арбитражный суд",
            "Площадь Ленина",
            "Кинотеатр Октябрь",
            "Башня Донец"
    };

    // Массив с описанием памятников
    String[] aboutText = {
            "Памятник великому русскому композитору Михаилу Ивановичу Глинке, уроженцу Смоленской земли, установлен в парке его имени. Глинка родился в 1804 году в селе Новоспасское Смоленской губернии и по праву считается основоположником русской классической музыки. Автор опер «Иван Сусанин» и «Руслан и Людмила», симфонических произведений. Памятник был открыт в 1906 году по проекту скульптора Р. Р. Баха. Композитор изображён сидящим в задумчивой позе, с нотным листом в руке. Вокруг памятника разбит живописный парк с липовыми аллеями. Рядом находится Смоленское музыкальное училище, носящее имя Глинки. Это место паломничества музыкантов и всех любителей классической музыки.",
            "Памятник выдающемуся советскому поэту Александру Трифоновичу Твардовскому установлен в Городском саду на улице Теньшевой. Твардовский (1910–1971) родился в Смоленской области, в деревне Загорье. Автор знаменитых поэм «Василий Тёркин», «За далью — даль», «Тёркин на том свете». В годы Великой Отечественной войны был военным корреспондентом, его стихи поднимали боевой дух солдат. Памятник был открыт в 1980 году. Поэт изображён сидящим на скамье, с блокнотом в руке, в характерной позе задумчивого творца. Вокруг разбит сквер с именными табличками, на которых высечены строки из его произведений. Это излюбленное место отдыха смолян и литературных экскурсий.",
            "Памятник был сооружён в 1967 году, во время празднования 50−летней годовщины Октябрьской революции. Скульптором памятника стал Лев Кербель, архитектором − Борис Тхор. Памятник культурного наследия России. Общая высота памятника − 9 метров, высота скульптуры − 6,5 метров. Скульптура Ленина в полный рост установлена на невысоком постаменте со ступенчатым стилобатом. Ленин представлен идущим навстречу ветру − у него распахнуто пальто и поднят воротник.",
            "Воздвигнут памятник воинам, защитникам и освободителям города Смоленска: русско − польской войны ... годов, Отечественной войны 1812 года и Великой Отечественной войны 1941 − 1945 года на площади Победы. Он представляет собой гранитную стелу, украшенную бронзовыми фигурами воинов−освободителей. Скульптор − Игорь Чумаков. Общая высота памятника составляет 13 метров, высота фигур − 2,7 метра. Торжественное открытие памятника состоялось 8 мая 2015 года.",
            "В 1943 году, после освобождения Смоленска от фашистских захватчиков, в сквере у крепостной стены начались захоронения погибших советских воинов – стал формироваться смоленский некрополь, а в 1968 году, в дни празднования 25-летия освобождения Смоленщины, был зажжен Вечный огонь, доставленный из Москвы от Могилы неизвестного солдата. Зажег огонь Герой Советского Союза, смолянин Михаил Егоров, водрузивший в 1945 году Знамя Победы над поверженным рейхстагом. Здесь же у подножия крепостной стены Михаил Егоров был похоронен в 1975 году."
    };

    // Массив с вопросами
    String[] questionText = {
            "В каком году построили памятник?",
            "Кто сидит рядом с Твардовским (только фамилия)?",
            "Что написано на памятнике?",
            "Какие года самые ранние на памятнике?",
            "Сколько наград на груди у Егорова?"
    };

    // Массив с ответами
    String[] answersText = {
            "1885",
            "теркин",
            "в. и. ленин",
            "1609 - 1611",
            "14"
    };

    // Массив с кординатами
    double[][] cords = {
            {54.781514, 32.046154},
            {54.779645, 32.051305},
            {54.782465, 32.046125},
            {54.777708, 32.053300},
            {54.779637, 32.045737}
    };

    // Массив с изображениями
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
    TextInputEditText answer;
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
        answer = findViewById(R.id.answer);
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

    // Выход из активности
    public void backActivity(View view) {
        finish();
    }

    // Переход на следующий квест
    public void nextClick(View view) {
        String userAnswer = answer.getText().toString().strip();

        if (userAnswer.toLowerCase().equals(answersText[num])) {
            ++num;
        } else {
            Toast.makeText(this, "Не правильно!", LENGTH_SHORT).show(); return;
        }

        // Если все памятники пройдены, то переход на активность финиша
        if (num == 5) {
            manager.setMonument(0); manager.setMonumentStatus(2);
            startActivity(new Intent(this, FinalActivity.class));
        }
        else {
            label.setText(labelsText[num]);
            where.setText(whereText[num]);
            about.setText(aboutText[num]);
            question.setText(questionText[num]);
            image.setImageResource(monumentsImages[num]);
            answer.setText("");
            manager.setMonument(num);
        }
    }

    // Переход к расположению башен на карте
    public void showMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("cord_first", cords[num][0]);
        intent.putExtra("cord_second", cords[num][1]);
        intent.putExtra("title", "Памятники");
        intent.putExtra("object", objectNameText[num]);
        startActivity(intent);
    }
}