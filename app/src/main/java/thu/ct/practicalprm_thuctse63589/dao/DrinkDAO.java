package thu.ct.practicalprm_thuctse63589.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import thu.ct.practicalprm_thuctse63589.dto.DrinkDTO;

public class DrinkDAO implements Serializable {
    public List<DrinkDTO> loadFromRaw(InputStream inputStream) {
        List<DrinkDTO> result = new ArrayList<>();
        DrinkDTO dto = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        String s = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((s = bufferedReader.readLine()) != null) {
                String[] tmp = s.split("-");
                dto = new DrinkDTO(tmp[0].trim(), tmp[1].trim(), Float.parseFloat(tmp[2].trim()),
                        tmp[3].trim().equals("1"), tmp[4].trim());
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<DrinkDTO> loadFromInternal(FileInputStream fileInputStream) {
        List<DrinkDTO> result = new ArrayList<>();
        DrinkDTO dto = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        String s = null;
        try {
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((s = bufferedReader.readLine()) != null) {
                String[] tmp = s.split("-");
                dto = new DrinkDTO(tmp[0].trim(), tmp[1].trim(), Float.parseFloat(tmp[2].trim()),
                        tmp[3].trim().equals("1"), tmp[4].trim());
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void saveToInternal(FileOutputStream fileOutputStream, List<DrinkDTO> studentDTO) {
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            String result = "";
            for (DrinkDTO dto : studentDTO) {
                result += dto.toString() + "\n";
            }
            outputStreamWriter.write(result);
            outputStreamWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isIdExist(FileInputStream inputStream, String id) {
        boolean bool = false;
        List<DrinkDTO> result = loadFromInternal(inputStream);
        for (DrinkDTO dto : result) {
            if (dto.getIdDrink().equals(id)) {
                return true;
            }
        }
        return bool;
    }

    public List<DrinkDTO> findByPrice(FileInputStream fileInputStream, float from, float to) {
        List<DrinkDTO> result = new ArrayList<>();
        for (DrinkDTO dto : loadFromInternal(fileInputStream)) {
            if (dto.getPrice() >= from && dto.getPrice() <= to && dto.isStatus() == false) {
                result.add(dto);
            }
        }
        return result;
    }
}
