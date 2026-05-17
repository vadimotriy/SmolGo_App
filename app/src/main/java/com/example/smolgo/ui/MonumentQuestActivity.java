package com.example.smolgo.ui;

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

// Активность с квестом "Памятники"
public class MonumentQuestActivity extends AppCompatActivity {
    // Массив с названиями памятников
    int[] labelsText = {
            R.string.quest_monument_label_0,
            R.string.quest_monument_label_1,
            R.string.quest_monument_label_2,
            R.string.quest_monument_label_3,
            R.string.quest_monument_label_4
    };

    // Массив с примерным место положением памятников
    int[] whereText = {
            R.string.quest_monument_where_0,
            R.string.quest_monument_where_1,
            R.string.quest_monument_where_2,
            R.string.quest_monument_where_3,
            R.string.quest_monument_where_4
    };

    // Массив с обозначением мест для карты
    int[] objectNameText = {
            R.string.quest_monument_name_object_0,
            R.string.quest_monument_name_object_1,
            R.string.quest_monument_name_object_2,
            R.string.quest_monument_name_object_3,
            R.string.quest_monument_name_object_4
    };

    // Массив с описанием памятников
    int[] aboutText = {
            R.string.quest_monument_about_0,
            R.string.quest_monument_about_1,
            R.string.quest_monument_about_2,
            R.string.quest_monument_about_3,
            R.string.quest_monument_about_4
    };

    // Массив с вопросами
    int[] questionText = {
            R.string.quest_monument_question_0,
            R.string.quest_monument_question_1,
            R.string.quest_monument_question_2,
            R.string.quest_monument_question_3,
            R.string.quest_monument_question_4
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
    ScrollView scrollView;

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
        scrollView = findViewById(R.id.scrollView);

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
            Toast.makeText(this, R.string.not_correct, LENGTH_SHORT).show(); return;
        }

        // Если все памятники пройдены, то переход на активность финиша
        if (num == 5) {
            manager.setMonument(0); manager.setMonumentStatus(2);
            startActivity(new Intent(this, FinalActivity.class)); finish();
        }
        else {
            label.setText(labelsText[num]);
            where.setText(whereText[num]);
            about.setText(aboutText[num]);
            question.setText(questionText[num]);
            image.setImageResource(monumentsImages[num]);
            answer.setText("");
            manager.setMonument(num);

            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
        }
    }

    // Переход к расположению башен на карте
    public void showMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("cord_first", cords[num][0]);
        intent.putExtra("cord_second", cords[num][1]);
        intent.putExtra("title", R.string.monuments);
        intent.putExtra("object", objectNameText[num]);
        startActivity(intent);
    }
}