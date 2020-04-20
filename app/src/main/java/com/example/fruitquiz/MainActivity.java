package com.example.fruitquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txt_right, txt_right_answer, txt_question;
    EditText user_input;
    ImageView img_check, img_show, img_next;
    ImageView img_correct, img_incorrect;
    String[] Fruits = new String[]
            {"Apple","Banana","Mango","Orange",
                    "Peach","Pear","Pomegranate",
                    "Strawberry","Pineapple","Melon","Water Melon"};
    int[] reward_images = new int[]{R.drawable.award11, R.drawable.award33,
                                    R.drawable.award44, R.drawable.award55,
                                    R.drawable.award66};
    int reward_image;

    String fruit;
    Random random;
    Random random2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = findViewById(R.id.layout1);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade);
        relativeLayout.startAnimation(animation);
        //Reward Image
        random2 = new Random();

        //////////

        txt_right = findViewById(R.id.txt_right);
        txt_right_answer = findViewById(R.id.txt_right_ans);
        txt_question = findViewById(R.id.question_txt);
        img_correct = findViewById(R.id.correct_answer);
        img_incorrect = findViewById(R.id.wrong_answer);

        user_input = findViewById(R.id.answer_edt);

        img_check = findViewById(R.id.img_check);
        img_next  = findViewById(R.id.img_change);
        img_show  = findViewById(R.id.img_show);

        txt_right_answer.setVisibility(View.INVISIBLE);
        txt_right.setVisibility(View.INVISIBLE);
        img_correct.setVisibility(View.INVISIBLE);
        img_incorrect.setVisibility(View.INVISIBLE);

        random = new Random();
        StartQuiz();


        
        img_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_input.getText().toString().equalsIgnoreCase(fruit)){
                    img_correct.setVisibility(View.VISIBLE);
                    img_incorrect.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Good Job! You Found It.", Toast.LENGTH_SHORT).show();

                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.show();
                    ImageView imgdialog = dialog.findViewById(R.id.imgdialog);
                    reward_image = reward_images[random2.nextInt(reward_images.length)];
                    imgdialog.setImageResource(reward_image);

                    Button btnContinue = dialog.findViewById(R.id.btncontinue);
                    btnContinue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            user_input.setText("");
                            img_correct.setVisibility(View.INVISIBLE);
                        }
                    });
                    StartQuiz();



                }else if(user_input.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Field is Empty, Type something!", Toast.LENGTH_SHORT).show();

                } else{
                    img_incorrect.setVisibility(View.VISIBLE);
                    img_correct.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Wrong Answer! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_right.setVisibility(View.VISIBLE);
                txt_right_answer.setVisibility(View.VISIBLE);
                txt_right_answer.setText(fruit);
                img_incorrect.setVisibility(View.INVISIBLE);
            }
        });

        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartQuiz();
                user_input.setText("");
                img_correct.setVisibility(View.INVISIBLE);
                img_incorrect.setVisibility(View.INVISIBLE);
                txt_right_answer.setVisibility(View.INVISIBLE);
                txt_right.setVisibility(View.INVISIBLE);
            }
        });


    }

    //Function to Mix words
    private String MixWords(String word){
        List<String> fruit = Arrays.asList(word.split(""));
        Collections.shuffle(fruit);

        String mixed_fruit = "";
        for(String i : fruit){
            mixed_fruit += i;
        }
        return mixed_fruit;
    }

    public void StartQuiz(){
        fruit  = Fruits[random.nextInt(Fruits.length)];
        txt_question.setText(MixWords(fruit));
    }



}
