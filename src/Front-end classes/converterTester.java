public class converterTester {

    public static void main(String args[]){
        TemperatureConverter converter = new TemperatureConverter();
        double currentTempF = 40.0;
        double currentTempC = 4.445;
        double currentTempK = 277.594;


        System.out.println("Fahrenheit to C and K");
        System.out.println("Temp in F = " + currentTempF);
        System.out.println("Temp in C = " + converter.fahrenheitToCelsius(currentTempF));
        System.out.println("Temp in K = " + converter.fahrenheitToKelvin(currentTempF) + "\n");

        System.out.println("Kelvin to C and F");
        System.out.println("Temp in K = " + currentTempK);
        System.out.println("Temp in C = " + converter.kelvinToCelsius(currentTempK));
        System.out.println("Temp in F = " + converter.kelvinToFahrenheit(currentTempK) + "\n");

        System.out.println("Celsius to K and F");
        System.out.println("Temp in C = " + currentTempC);
        System.out.println("Temp in K = " + converter.celsiusToKelvin(currentTempC));
        System.out.println("Temp in F = " + converter.celsiusToFahrenheit(currentTempC) + "\n");

    }
<<<<<<< HEAD
}
=======
}
>>>>>>> yuliia-branch
