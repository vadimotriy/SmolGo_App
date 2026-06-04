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

public class NearDneprQuest2Activity extends AppCompatActivity {        int num;
    SharedManager manager;
    TextView label, question;
    ScrollView scrollView;
    ImageView image31, image32, image33, image34;

    String[] labelText = {
            "Добро пожаловать!",
            "Князь Владимир",
            "Пятницкие водяные ворота",
            "Двигайтесь прямо",
            "Главная святыня Смоленска",
            ""
    };

    String[] questionText = {
            "",
            "Сколько флагов можно увидеть рядом с памятником?",
            "Какой год высечен на храме?",
            "",
            "Выберите храм, который считается самым главным во всей Смоленской области."
    };

    int[][] many_images = {
            {},
            {},
            {},
            {},
            {R.drawable.img_dnepr_3_1, R.drawable.img_dnepr_3_2, R.drawable.img_dnepr_3_3, R.drawable.img_dnepr_3_4},
            {}
    };

    int[] right_answers_index = {
            0,
            0,
            0,
            0,
            1,
            0
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_near_dnepr_quest_many_images);

        manager = SharedManager.getInstance(this);
        num = manager.getDnepr();

        label = findViewById(R.id.info);
        question = findViewById(R.id.question);
        scrollView = findViewById(R.id.scrollView);

        image31 = findViewById(R.id.imageView31);
        image32 = findViewById(R.id.imageView32);
        image33 = findViewById(R.id.imageView33);
        image34 = findViewById(R.id.imageView34);

        image31.setOnClickListener(v -> checkAnswer(0));
        image32.setOnClickListener(v -> checkAnswer(1));
        image33.setOnClickListener(v -> checkAnswer(2));
        image34.setOnClickListener(v -> checkAnswer(3));

        nextWay();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void checkAnswer(int selectedIndex) {
        if (selectedIndex == right_answers_index[num]) {
            ++num;
            manager.setDnepr(num);

            nextWay();
        } else {
            Toast.makeText(this, "Неправильно", LENGTH_SHORT).show();
        }
    }


    public void nextWay() {
        switch (num) {
            case 0: case 1: case 2: case 3: case 5:
                startActivity(new Intent(this, NearDneprQuestActivity.class));
                finish();
                break;
            case 4:
                label.setText(labelText[num]);
                question.setText(questionText[num]);

                image31.setImageResource(many_images[num][0]);
                image32.setImageResource(many_images[num][1]);
                image33.setImageResource(many_images[num][2]);
                image34.setImageResource(many_images[num][3]);
        }
    }

    public void backActivity(View view) {
        finish();
    }
}