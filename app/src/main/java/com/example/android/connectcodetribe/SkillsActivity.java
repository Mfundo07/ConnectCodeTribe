package com.example.android.connectcodetribe;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 11/1/2017.
 */

public class SkillsActivity extends AppCompatActivity {

    ArrayList<String> selection = new ArrayList<String>();
    TextView final_text;
    Button removal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog);
        final_text=(TextView)findViewById(R.id.final_result);
        final_text.setEnabled(false);
    }

    public void java1(View view){

        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){

            case  R.id.javaS:

                if (checked)
                {selection.add("- JavaScript");}

                else{
                    selection.remove("- Javascript");
                }break;

            case  R.id.java:

                if (checked)
                {selection.add("- Java");}

                else{
                    selection.remove("- Java");
                }break;
            case  R.id.html:

                if (checked)
                {selection.add("- HTML");}

                else{
                    selection.remove("- HTML");
                }break;
            case  R.id.css:

                if (checked)
                {selection.add("- CSS");}

                else{
                    selection.remove("- CSS");
                }break;
            case  R.id.xml:

                if (checked)
                {selection.add("- XML");}

                else{
                    selection.remove("- XML");
                }break;
            case  R.id.photoshop:

                if (checked)
                {selection.add("- Photoshop");}

                else{
                    selection.remove("- Photoshop");
                }break;
            case  R.id.database:

                if (checked)
                {selection.add("- Database");}

                else{
                    selection.remove("- Database");
                }break;
            case  R.id.sql:

                if (checked)
                {selection.add("- SQL");}

                else{
                    selection.remove("- SQL");
                }break;
            case  R.id.php:

                if (checked)
                {selection.add("- PHP");}

                else{
                    selection.remove("- PHP");
                }break;
            case  R.id.json:

                if (checked)
                {selection.add("- Visual Basic");}

                else{
                    selection.remove("- Visual Basic");
                }break;
            case  R.id.csharp:

                if (checked)
                {selection.add("- C#");}

                else{
                    selection.remove("- C#");
                }break;
            case  R.id.cplus:

                if (checked)
                {selection.add("- C++");}

                else{
                    selection.remove("- C++");
                }break;
        }

    }
    public void final_selection(View view){

        String final_javascript_selection = "";

        for (String Selections: selection){
            final_javascript_selection= final_javascript_selection + Selections + "\n" ;
        }
        final_text.setText(final_javascript_selection);
        final_text.setEnabled(false);





    }







}
