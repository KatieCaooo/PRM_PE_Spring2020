package thu.ct.practicalprm_thuctse63589;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import thu.ct.practicalprm_thuctse63589.dto.DrinkDTO;

public class DrinkDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_detail);
        ((Button) findViewById(R.id.btnCreate)).setVisibility(View.GONE);
        Intent intent = this.getIntent();
        DrinkDTO dto = (DrinkDTO) intent.getSerializableExtra("DTO");
        ((EditText) findViewById(R.id.edtID)).setText(dto.getIdDrink());
        ((EditText) findViewById(R.id.edtID)).setEnabled(false);
        ((EditText) findViewById(R.id.edtName)).setText(dto.getNameDrink());
        ((EditText) findViewById(R.id.edtPrice)).setText(String.valueOf(dto.getPrice()));
        ((Spinner) findViewById(R.id.spinStatus)).setSelection(dto.isStatus() ? 1 : 0);
        ((EditText) findViewById(R.id.edtTime)).setText(dto.getTimeOfCreate());
        ((EditText) findViewById(R.id.edtTime)).setEnabled(false);
    }

}
