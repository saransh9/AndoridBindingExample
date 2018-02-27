package demo.databindingexample;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by saransh on 20/02/18.
 */

public class MyHandler {

   public void onClickButton(View view) {

        if(view instanceof Button){
            int times = Integer.parseInt(view.getTag().toString());
            times += 1;
            ((Button) view).setText("Clicked " + times + " times");
            view.setTag(times);
        }

    }
    public void longClick(View view){}

}
