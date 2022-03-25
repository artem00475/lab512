package ru.itmo.lab5.file;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.itmo.lab5.exceptions.*;
import ru.itmo.lab5.person.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;

public class FileManager {
    private String[] tags = new String[]{"id", "name", "coordinatesX", "coordinatesY","date", "height", "eyeColor", "hairColor", "nationality", "locationX", "locationY", "locationZ", "locationName" };
    private String scanPath;

    public FileManager(){}


    public void parseFile(Queue<Person> personQueue, Scanner s, String scanPath) {
        this.scanPath=scanPath;
        JSONParser parser = new JSONParser();
        StringBuilder str = new StringBuilder();
        while (s.hasNextLine()) {
            str.append(s.nextLine()).append('\n');
        }
        try {
            int idP;
            Integer coordinatesXP;
            Integer coordinatesYP;
            Date dateP;
            Double heightP;
            ColorE eyeColorP;
            ColorH hairColorP;
            Country nationalityP;
            Integer locationXP;
            Double locationYP;
            Long locationZP;
            JSONObject rootJSONObject = (JSONObject) parser.parse(str.toString());
            JSONArray personJSONArray = (JSONArray) rootJSONObject.get("persons");
            int index = 0;
            for (Object personsObject : personJSONArray) {
                index++;
                try {
                    JSONObject personsJSONObject = (JSONObject) personsObject;
                    if (((String) personsJSONObject.get(tags[0])).equals("")) {
                        throw new IdException("У элемента отсутствует id");
                    }
                    try {
                        idP = Integer.parseInt((String) personsJSONObject.get(tags[0]));
                    } catch (NumberFormatException e) {
                        throw new IdException("В поле id нечисловое значение");
                    }
                    if (((String) personsJSONObject.get(tags[1])).equals("")) {
                        throw new NameException("У элемента отсутсвует поле name");
                    }
                    String nameP = (String) personsJSONObject.get(tags[1]);
                    if (((String) personsJSONObject.get(tags[2])).equals("")) {
                        throw new CoordinatesException("У элемента отсутствует координата Х");
                    }
                    try {
                        coordinatesXP = Integer.parseInt((String) personsJSONObject.get(tags[2]));
                    } catch (NumberFormatException e) {
                        throw new CoordinatesException("В поле координата Х нечисловое значение");
                    }
                    if (((String) personsJSONObject.get(tags[3])).equals("")) {
                        throw new CoordinatesException("У элемента отсутствует координата Y");
                    }
                    try {
                        coordinatesYP = Integer.parseInt((String) personsJSONObject.get(tags[3]));
                    } catch (NumberFormatException e) {
                        throw new CoordinatesException("В поле координата Y нечисловое значение");
                    }
                    if (((String) personsJSONObject.get(tags[4])).equals("")) {
                        throw new DateException("У элемента отсутствует поле date");
                    }
                    try {
                        dateP = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse((String) personsJSONObject.get(tags[4]));
                    } catch (java.text.ParseException e) {
                        throw new DateException("Ошибка в формате даты");
                    }
                    if (((String) personsJSONObject.get(tags[5])).equals("")) {
                        throw new HeightException("У элемента отсутствует поле height");
                    }
                    try {
                        heightP = Double.parseDouble((String) personsJSONObject.get(tags[5]));
                    } catch (NumberFormatException e) {
                        throw new HeightException("В поле height нечисловое значение");
                    }
                    if (((String) personsJSONObject.get(tags[6])).equals("")) {
                        throw new EyeColorException("У элемента отсутствует поле eyeColor");
                    }
                    try {
                        String eyeColorTest = (String) personsJSONObject.get(tags[6]);
                        eyeColorP = ColorE.valueOf(eyeColorTest.toUpperCase(Locale.ROOT));
                    } catch (IllegalArgumentException e) {
                        throw new EyeColorException("Некорректное значение поля eyeColor");
                    }
                    if (((String) personsJSONObject.get(tags[7])).equals("")) {
                        throw new HairColorException("У элемента отсутствует поле hairColor");
                    }
                    try {
                        String hairColorTest = (String) personsJSONObject.get(tags[7]);
                        hairColorP = ColorH.valueOf(hairColorTest.toUpperCase(Locale.ROOT));
                    } catch (IllegalArgumentException e) {
                        throw new HairColorException("Некорректное значение поля hairColor");
                    }
                    if (((String) personsJSONObject.get(tags[8])).equals("")) {
                        throw new NationalityException("У элемента отсутствует поле nationality");
                    }
                    try {
                        String nationalityTest = (String) personsJSONObject.get(tags[8]);
                        nationalityP = Country.valueOf(nationalityTest.toUpperCase(Locale.ROOT));
                    } catch (IllegalArgumentException e) {
                        throw new NationalityException("Некорректное значение поля nationality");
                    }
                    if (((String) personsJSONObject.get(tags[9])).equals("")) {
                        throw new LocationException("У элемента отсутствует поле locationX");
                    }
                    try {
                        locationXP = Integer.parseInt((String) personsJSONObject.get(tags[9]));
                    } catch (NumberFormatException e) {
                        throw new LocationException("В поле locationX нечисловое значение");
                    }
                    if (((String) personsJSONObject.get(tags[10])).equals("")) {
                        throw new LocationException("У элемента отсутствует поле locationY");
                    }
                    try {
                        locationYP = Double.parseDouble((String) personsJSONObject.get(tags[10]));
                    } catch (NumberFormatException e) {
                        throw new LocationException("В поле locationY нечисловое значение");
                    }
                    if (((String) personsJSONObject.get(tags[11])).equals("")) {
                        throw new LocationException("У элемента отсутствует поле locationZ");
                    }
                    try {
                        locationZP = Long.parseLong((String) personsJSONObject.get(tags[11]));
                    } catch (NumberFormatException e) {
                        throw new LocationException("В поле locationZ нечисловое значение");
                    }
                    if (((String) personsJSONObject.get(tags[12])).equals("")) {
                        throw new LocationException("У элемента отсутствует поле locationName");
                    }
                    String locationNameP = (String) personsJSONObject.get(tags[12]);
                    Person newPerson = new Person(idP, nameP, coordinatesXP, coordinatesYP, dateP, heightP, eyeColorP, hairColorP, nationalityP, locationXP, locationYP, locationZP, locationNameP);
                    personQueue.add(newPerson);
                } catch (Exception e) {
                    System.out.println("В " + index + " элементе ошибка, он не будет добавлен в коллекцию");
                }
            }
        }catch (ParseException e) {
            System.out.println("В файле некорректный формат");
        }
    }

    public void saveToFile(Queue<Person> personQueue) {
        StringBuilder str = new StringBuilder("{\"persons\" : [\n");
        for (Person person : personQueue){
            str.append("    {\n");
            for (int i=0;i< tags.length;i++){
                str.append("        \""+tags[i]+"\" : \""+person.getPersonFields().get(i)+"\",\n");
            }
            str.deleteCharAt(str.lastIndexOf(","));
            str.append("    },\n");
        }
        str.deleteCharAt(str.lastIndexOf(","));
        str.append("]\n}");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scanPath))) {
            bufferedWriter.write(String.valueOf(str));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
