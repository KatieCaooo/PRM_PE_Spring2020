package thu.ct.practicalprm_thuctse63589;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import thu.ct.practicalprm_thuctse63589.dao.DrinkDAO;
import thu.ct.practicalprm_thuctse63589.dto.DrinkDTO;

public class DrinkCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_detail);
        ((EditText) findViewById(R.id.edtTime)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.txtTime)).setVisibility(View.GONE);
    }

    public void clickToCreate(View view) {
        List<DrinkDTO> result = new ArrayList<>();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CANADA);
        try {
            FileInputStream fileInputStream = openFileInput("tbl_drink.txt");
            DrinkDAO drinkDAO = new DrinkDAO();
            result = drinkDAO.loadFromInternal(fileInputStream);
            DrinkDTO newDto = new DrinkDTO(
                    ((EditText) findViewById(R.id.edtID)).getText().toString(),
                    ((EditText) findViewById(R.id.edtName)).getText().toString(),
                    Float.parseFloat(((EditText) findViewById(R.id.edtPrice)).getText().toString()),
                    ((Spinner) findViewById(R.id.spinStatus)).getSelectedItem().toString().equals("Finished"),
                    df.format(Date.from(Instant.now())));
            fileInputStream.close();
            fileInputStream = openFileInput("tbl_drink.txt");
            if (newDto.getIdDrink().isEmpty()){
                Toast.makeText(this, "Input ID please!", Toast.LENGTH_LONG).show();
                return;
            }
            if (newDto.getNameDrink().isEmpty()){
                Toast.makeText(this, "Input name please!", Toast.LENGTH_LONG).show();
                return;
            }
            if (!drinkDAO.isIdExist(fileInputStream, newDto.getIdDrink())) {
                result.add(newDto);
                fileInputStream.close();
                FileOutputStream fileOutputStream = openFileOutput("tbl_drink.txt", MODE_PRIVATE);
                drinkDAO.saveToInternal(fileOutputStream, result);
                fileOutputStream.close();
            } else {
                Toast.makeText(this, "ID already existed!", Toast.LENGTH_LONG).show();
                fileInputStream.close();
                return;
            }
            Intent returnIntent = new Intent();
            setResult(AppCompatActivity.RESULT_OK, returnIntent);
            finish();

        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                Toast.makeText(this, "Please input price", Toast.LENGTH_SHORT).show();
            }
            e.printStackTrace();

        }
    }
}
