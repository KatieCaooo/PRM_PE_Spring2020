package thu.ct.practicalprm_thuctse63589;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import thu.ct.practicalprm_thuctse63589.adapter.DrinkAdapter;
import thu.ct.practicalprm_thuctse63589.dao.DrinkDAO;
import thu.ct.practicalprm_thuctse63589.dto.DrinkDTO;

public class MainActivity extends AppCompatActivity {

    private ListView listFood;
    private DrinkAdapter drinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrinkDAO dao = new DrinkDAO();
        try {
            FileOutputStream fileOutputStream = openFileOutput("tbl_drink.txt", MODE_PRIVATE);
            InputStream inputStream = this.getResources().openRawResource(R.raw.tbl_drink);
            List<DrinkDTO> drinkDTOList = dao.loadFromRaw(inputStream);
            dao.saveToInternal(fileOutputStream, drinkDTOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileInputStream = openFileInput("tbl_drink.txt");
            List<DrinkDTO> result = dao.loadFromInternal(fileInputStream);
            drinkAdapter = new DrinkAdapter(result);
            listFood = findViewById(R.id.listDrinks);
            listFood.setAdapter(drinkAdapter);
            listFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DrinkDTO dto = (DrinkDTO) listFood.getItemAtPosition(position);
                    Intent intentDetail = new Intent(MainActivity.this, DrinkDetailActivity.class);
                    intentDetail.putExtra("DTO", dto);
                    startActivityForResult(intentDetail, 1000);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void clickToSearch(View view) {
        float from = 0;
        float to = 0;
        DrinkDAO drinkDAO = new DrinkDAO();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput("tbl_drink.txt");
            try {
                from = Float.parseFloat(((EditText) findViewById(R.id.from)).getText().toString());
                to = Float.parseFloat(((EditText) findViewById(R.id.to)).getText().toString());
            } catch (Exception ex) {
                if (ex instanceof NumberFormatException) {
                    Toast.makeText(this, "Please input price", Toast.LENGTH_SHORT).show();
                }
                ex.printStackTrace();
            }
            List<DrinkDTO> result = drinkDAO.findByPrice(fileInputStream, from, to);
            drinkAdapter.setDrinkList(result);
            drinkAdapter.notifyDataSetChanged();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DrinkDAO DrinkDAO = new DrinkDAO();
        FileInputStream fileInputStream = null;
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            try {
                fileInputStream = openFileInput("tbl_drink.txt");
                List<DrinkDTO> result = DrinkDAO.loadFromInternal(fileInputStream);
                drinkAdapter.setDrinkList(result);
                drinkAdapter.notifyDataSetChanged();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void clickToCreate(View view) {
        Intent intentDetail = new Intent(MainActivity.this, DrinkCreateActivity.class);
        startActivityForResult(intentDetail, 1000);
    }
}
