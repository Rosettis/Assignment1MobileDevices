package csci4100.uoit.ca.mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 100490515 on 9/22/2015.
 */
public class MainActivity extends Activity {
    //Global Variables
    int requestCode = 4100;
    int yesCount = 0;
    int noCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // show the correct view (layout)
        setContentView(R.layout.activity_menu);
        // Setting the string values
        setText();
    }
    public void switchScreens(View view) {
        //create intent
        Intent intent = new Intent(this, QuizActivity.class);
        //add yes and no counters
        intent.putExtra("yes", yesCount);
        intent.putExtra("no", noCount);
        //start activity
        startActivityForResult(intent, requestCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 4100){
            if(resultCode == RESULT_OK){
                yesCount = data.getExtras().getInt("yes");
                noCount = data.getExtras().getInt("no");
                setText();
            }
        }
    }
    protected void setText(){
        Resources res = getResources();
        //Setting the yes string
        String yesTextFormat = res.getString(R.string.yes_score);
        String yesTextMsg = String.format(yesTextFormat, yesCount);
        TextView yesTextView = (TextView) findViewById(R.id.yesScore);
        Log.d("ok",yesTextMsg);
        yesTextView.setText(yesTextMsg);
        //Setting the no string
        String noTextFormat = res.getString(R.string.no_score);
        String noTextMsg = String.format(noTextFormat, noCount);
        TextView noTextView = (TextView) findViewById(R.id.noScore);
        Log.d("ok",noTextMsg);
        noTextView.setText(noTextMsg);
    }
}
