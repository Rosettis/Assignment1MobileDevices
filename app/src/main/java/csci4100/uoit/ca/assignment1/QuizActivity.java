package csci4100.uoit.ca.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class QuizActivity extends Activity {
    //Global Variables
    String[] questionArray;
    int yesCount;
    int noCount;
    int size;
    String[] check;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionArray = getResources().getStringArray(R.array.quiz_question);
        size = questionArray.length;
        check = new String[size];
        for (int j = 0; j < size; j++){
            check[j] = "no";
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        updateTextView();
    }
    private void updateTextView() {
        TextView textView = (TextView)findViewById(R.id.randomTextView);
        Random random = new Random();
        int generatedIndex = random.nextInt(size);
        String checkString = "asked";
        int count = 0;
        while(true) {
            Log.d("preTest", Integer.toString(count));
            count = 0;
            //finding if all questions have been asked
            for (i = 0; i < size; i++) {
                Log.d("trust",check[i]);
                if (check[i].equals(checkString)) {
                    count++;
                }
            }//Exiting if all questions asked
            if (count == size) {
                break;
            }//Checking if question has already been asked
            if (check[generatedIndex].equals(checkString)) {
                Log.d("question already asked", Integer.toString(generatedIndex+1));
            }//Checking if its a new question
            else {
                check[generatedIndex] = checkString;
                Log.d("question number", Integer.toString(generatedIndex+1));
                break;
            }
            generatedIndex = random.nextInt(size);
        }
        if (count == size) {
            switchBack(this.findViewById(android.R.id.content));
        }
        Log.d("worked",Integer.toString(count));
        textView.setTextSize(40);
        textView.setTextColor(Color.WHITE);
        textView.setText(questionArray[generatedIndex]);
    }
    public void switchBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("yes",yesCount);
        intent.putExtra("no", noCount);
        setResult(RESULT_OK, intent);
        finish();
    }
    public void pressYes(View view){
        yesCount++;
        updateTextView();
    }
    public void pressNo(View view){
        noCount++;
        updateTextView();
    }
}
