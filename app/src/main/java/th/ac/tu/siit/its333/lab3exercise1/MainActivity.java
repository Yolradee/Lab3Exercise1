package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        calculate();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list

    }

    public void addClicked(View v) {
        Intent i = new Intent(this, CourseActivity.class);
        startActivityForResult(i, 88);

    }


    public void showClicked(View v) {
        Intent i = new Intent(this, CourseListActivity.class);



        String alldata = "List of Courses";
        for (int j=0; j < listCodes.size();j++){
        alldata += String.format("\n%s (%d credits) = %s",listCodes.get(j),listCredits.get(j),listGrades.get(j));
        }

        i.putExtra("list_of_course", alldata);
        startActivity(i);
    }

    public void resetClicked(View v) {

         cr = 0;         // Credits
         gp = 0.0;    // Grade points
         gpa = 0.0;

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        calculate();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Values from child activity
        if (requestCode == 88) {
            if (resultCode == RESULT_OK) {
                int a = data.getIntExtra("Credit", 0);
                listCredits.add(a);
                String b = data.getStringExtra("Course");
                listCodes.add(b);
                String c = data.getStringExtra("Grade");
                listGrades.add(c);
                calculate();
              }
            TextView tvGPA = (TextView)findViewById(R.id.tvGPA);
            tvGPA.setText(0);
            }

        }

public void calculate()
{

    cr = 0;         // Credits
    gp = 0.0;    // Grade points
    gpa = 0.0;

    for(int i=0; i < listGrades.size(); i++) {
        if(listGrades.get(i).equals("A")){
            gp += 4.0*listCredits.get(i);
        }
        else if(listGrades.get(i).equals("B+")){
            gp += 3.5*listCredits.get(i);
        }
        else if(listGrades.get(i).equals("B")){
            gp += 3.0*listCredits.get(i);
        }
        else if(listGrades.get(i).equals("C+")){
            gp += 2.5*listCredits.get(i);
        }
        else if(listGrades.get(i).equals("C")){
            gp += 2.0*listCredits.get(i);
        }
        else if(listGrades.get(i).equals("D+")){
            gp += 1.5*listCredits.get(i);
        }
        else if(listGrades.get(i).equals("D")){
            gp += 1.0*listCredits.get(i);
        }
        else if(listGrades.get(i).equals("F")){
            gp += 0.0*listCredits.get(i);
        }

        cr += listCredits.get(i);

    }

    TextView tvGP = (TextView)findViewById(R.id.tvGP);
    TextView tvCR = (TextView)findViewById(R.id.tvCR);
    TextView tvGPA = (TextView)findViewById(R.id.tvGPA);


    gpa =  gp/cr;

    tvGP.setText(Double.toString(gp));
    tvCR.setText(Double.toString(cr));
    tvGPA.setText(Double.toString(gpa));

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
