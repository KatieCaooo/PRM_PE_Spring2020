package thu.ct.practicalprm_thuctse63589.dto;

import java.io.Serializable;

public class DrinkDTO implements Serializable {
    private String idDrink, nameDrink, timeOfCreate;
    float price;
    boolean status;

    @Override
    public String toString() {
        return idDrink + " - " + nameDrink + " - " + price + " - " + (status ? "1" : "0") + " - " + timeOfCreate;
    }

    public DrinkDTO() {
    }

    public DrinkDTO(String idDrink, String nameDrink, float price, boolean status, String timeOfCreate) {
        this.idDrink = idDrink;
        this.nameDrink = nameDrink;
        this.price = price;
        this.timeOfCreate = timeOfCreate;
        this.status = status;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getNameDrink() {
        return nameDrink;
    }

    public void setNameDrink(String nameDrink) {
        this.nameDrink = nameDrink;
    }

    public String getTimeOfCreate() {
        return timeOfCreate;
    }

    public void setTimeOfCreate(String timeOfCreate) {
        this.timeOfCreate = timeOfCreate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
