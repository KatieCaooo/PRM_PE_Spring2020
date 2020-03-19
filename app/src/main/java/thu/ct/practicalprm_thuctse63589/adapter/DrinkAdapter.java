package thu.ct.practicalprm_thuctse63589.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import thu.ct.practicalprm_thuctse63589.R;
import thu.ct.practicalprm_thuctse63589.dto.DrinkDTO;

public class DrinkAdapter extends BaseAdapter {
    private List<DrinkDTO> drinkList;

    public DrinkAdapter(List<DrinkDTO> drinkList) {
        this.drinkList = drinkList;
    }

    public void setDrinkList(List<DrinkDTO> drinkList) {
        this.drinkList = drinkList;
    }

    @Override
    public int getCount() {
        return drinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return drinkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return drinkList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.drink_item, parent, false);
        }
        DrinkDTO drinkDTO = drinkList.get(position);
        ((TextView) convertView.findViewById(R.id.idDrink)).setText(drinkDTO.getIdDrink());
        ((TextView) convertView.findViewById(R.id.nameDrink)).setText(drinkDTO.getNameDrink());
        ((TextView) convertView.findViewById(R.id.priceDrink)).setText(String.valueOf(drinkDTO.getPrice()));
        return convertView;
    }
}
