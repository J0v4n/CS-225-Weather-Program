import java.text.DecimalFormat;

//Class used to convert fahrenheit, celsius, and Kelvin between each other
public class TemperatureConverter {


    //Takes in the temperature as fahrenheit and is converted into celsius
    public double fahrenheitToCelsius(double fahrenheit){
        double finalTemp = fahrenheit;
        finalTemp -= 32;
        finalTemp *= 5;
        finalTemp /= 9;
        finalTemp = roundAnswer(finalTemp);
        return finalTemp;
    }

    //takes in the temperature as fahrenheit and is converted into kelvin
    public double fahrenheitToKelvin(double fahrenheit){
        double finalTemp = fahrenheit;
        finalTemp -= 32;
        finalTemp *= 5;
        finalTemp /= 9;
        finalTemp += 273.15;
        finalTemp = roundAnswer(finalTemp);
        return finalTemp;
    }


    //takes in the temperature as Kelvin and converts it to Celsius
    public double kelvinToCelsius(double kelvin){
        double finalTemp = kelvin;
        finalTemp -= 273.15;
        finalTemp = roundAnswer(finalTemp);
        return finalTemp;
    }

    //takes in the temperature as kelvin and converts it to fahrenheit
    public double kelvinToFahrenheit(double kelvin){
        double finalTemp = kelvin;
        finalTemp -= 273.15;
        finalTemp *= 9;
        finalTemp /= 5;
        finalTemp += 32;
        finalTemp = roundAnswer(finalTemp);
        return finalTemp;
    }

    //takes in the temperature as celsius and converts it to kelvin
    public double celsiusToKelvin(double celsius){
        double finalTemp = celsius;
        finalTemp += 273.15;
        finalTemp = roundAnswer(finalTemp);
        return finalTemp;
    }

    //takes in the temperature as celsius and converts it to fahrenheit
    public double celsiusToFahrenheit(double celsius){
        double finalTemp = celsius;
        finalTemp *= 9;
        finalTemp /= 5;
        finalTemp += 32;
        finalTemp = roundAnswer(finalTemp);
        return finalTemp;
    }

    //rounds a number provided to the nearest hundredth
    private double roundAnswer(double num){
        double finalNum = num;
        DecimalFormat f = new DecimalFormat("##.00");
        finalNum = Double.parseDouble(f.format(finalNum));
        return finalNum;
    }



}
