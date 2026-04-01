package com.example.smolgo.ui;

import static android.view.View.VISIBLE;
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

// Активность с квестом "Памятники 2"
public class Monument2QuestActivity extends AppCompatActivity {
    // Массив с названиями памятников
    String[] labelsText = {
            "Инструкция",
            "Памятник «Благодарная Россия — Героям ... года»",
            "Алея героев 1812 года",
            "Утраченные башни Крепостной стены",
            "Опаленный цветок",
            "Мемориал героям СВО",
            "Памятный знак партизанам 1812 года"
    };

    // Массив с описанием памятников
    String[] aboutText = {
            "Все памятники этого квеста расположены в пределах одной зоны. На карте эта территория выделена цветным многоугольником. Пожалуйста, ориентируйтесь на эту границу и не покидайте её пределы во время поиска объектов.",
            "Также известен как «памятник с орлами». Памятник представляет собой большую скалу, на вершине которой два больших орла защищают гнездо, к которому по скале крадётся галл в доспехах и с мечом в руке. Орлы символизируют I-ю и II-ю русские армии, соединившиеся в Смоленске и защищавшие город в августе ... года.",
            "26 августа 1912 года, в 100-летнюю годовщину Бородинского сражения, на средства населения Смоленской губернии был открыт памятник ... Скульптором памятника была выпускница Академии Художеств в Санкт-Петербурге Мария Страховская. Первоначально бюст стоял на центральной аллее Сквера Памяти Героев. Вокруг памятника разбит цветник. Перед памятником находится гранитный монолит с текстом обращения ... к населению Смоленской губернии 20 августа 1812 года:",
            "В Смоленске реализуется проект по установке мемориальных стел на местах утраченных башен крепостной стены (XVI век). Всего из 38 башен сохранилось 14 оригинальных башен и 3 реконструированных.",
            "Памятник детям − узникам фашистских концентрационных лагерей. Автор проекта − Александр Семёнович Парфёнов. Памятник представляет собой несколько хрупких детских тел, слившихся вместе в шар. Под шаром находятся надписи с названиями концлагерей. Памятник установлен в 2005 году (год 60−летия Победы в Великой Отечественной войне) по инициативе Смоленской региональной организации «Бывшие малолетние узники фашистских концлагерей».",
            "Мемориал посвящен защитникам, погибшим в зоне специальной военной операции. В основу концепции мемориала лег девиз, размещенный на гербе Смоленской области: «Несгибаемый дух всё ...» – символ отваги и несокрушимости наших Героев!",
            "Памятный знак выполнен из известняка, в его центре находится бронзовая медаль, обрамлённая дубовыми листьями. На медали упоминается Василиса Кожина. Василиса Кожина − жена старосты одной из деревень Смоленской губернии. О Василисе Кожиной писали как об активной участнице партизанского движения, которая организовала в Сычёвском уезде отряд из подростков и женщин, охранявший селения и наносивший большой урон французам."
    };

    // Массив с вопросами
    String[] questionText = {
            "",
            "Героям какого года посвящен этот памятник?",
            "Какая фамилия у генерала, который написал письмо Смолянам?",
            "Сколько таких башенок находится в пределах этой территории?",
            "Сколько человек из Смоленской области было угнано в немецко-фашистское рабство?",
            "Вставьте пропуск «НЕСГИБАЕМЫЙ ДУХ ВСЁ ...»",
            "Какая фамилия у скульптора?"
    };

    // Массив с ответами
    String[] answersText = {
            "",
            "1812",
            "кутузов",
            "2",
            "164630",
            "превозможет",
            "барановская"
    };

    // Массив с изображениями
    int[] monumentsImages = {
            R.drawable.img_monument2_quest_1,
            R.drawable.img_monument2_quest_2,
            R.drawable.img_monument2_quest_3,
            R.drawable.img_monument2_quest_4,
            R.drawable.img_monument2_quest_5,
            R.drawable.img_monument2_quest_6,
            R.drawable.img_monument2_quest_7
    };

    int num;
    SharedManager manager;
    TextView label, about, question;
    TextInputEditText answer;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monumets2_quest);

        manager = SharedManager.getInstance(this);
        num = manager.getMonument2();

        label = findViewById(R.id.info);
        about = findViewById(R.id.about);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        image = findViewById(R.id.imageWall);

        label.setText(labelsText[num]);
        about.setText(aboutText[num]);
        question.setText(questionText[num]);
        image.setImageResource(monumentsImages[num]);

        if (num != 0) {
            answer.setVisibility(VISIBLE);
        }

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
        String userAnswer = answer.getText().toString().strip();

        if (userAnswer.toLowerCase().equals(answersText[num])) {
            ++num;
        } else {
            Toast.makeText(this, "Не правильно!", LENGTH_SHORT).show();
            return;
        }

        // Если все памятники пройдены, то переход на активность финиша
        if (num == 7) { manager.setMonument2(0); manager.setMonumentStatus2(2); startActivity(new Intent(this, FinalActivity.class)); }
        else {
            label.setText(labelsText[num]);
            about.setText(aboutText[num]);
            question.setText(questionText[num]);
            image.setImageResource(monumentsImages[num]);
            answer.setText("");
            answer.setVisibility(VISIBLE);
            manager.setMonument2(num);
        }
    }
}