package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;
import java.util.Arrays;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int bound = -273;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature : temperatureSeries) {
            if (temperature < bound) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double summa() {
        double sumTemperatureSeries = 0;
        for (double temperature : temperatureSeries) {
            sumTemperatureSeries += temperature;
        }
        return sumTemperatureSeries;
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double sumTemperatureSeries = summa();
        double averageTemperature = sumTemperatureSeries / temperatureSeries.length;
        return averageTemperature;
    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        double averageTemperature = average();
        double variable;

        for (int i = 0; i < temperatureSeries.length; i++) {
            variable = (temperatureSeries[i] - averageTemperature) * (temperatureSeries[i] - averageTemperature);
            sum += variable;
        }
        double deviationValue = Math.sqrt(sum/temperatureSeries.length);
        return deviationValue;
    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double minValue = Double.POSITIVE_INFINITY;
        for (double temperature: temperatureSeries) {
            if (minValue > temperature) {
                minValue = temperature;
            }
        }
        return minValue;
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double maxValue = Double.NEGATIVE_INFINITY;
        for (double temperature: temperatureSeries) {
            if (maxValue < temperature) {
                maxValue = temperature;
            }
        }
        return maxValue;
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double closest = Double.POSITIVE_INFINITY;
        for (double temperature: temperatureSeries) {
            if (closest > Math.abs(temperature)) {
                closest = Math.abs(temperature);
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double finClosest = Double.POSITIVE_INFINITY;
        double closest = Double.POSITIVE_INFINITY;
        for (double temperature: temperatureSeries) {
            if (closest > Math.abs(tempValue - temperature)) {
                closest = Math.abs(tempValue - temperature);
                finClosest = temperature;
            }
        }
        return finClosest;
    }

    public double[] findTempsLessThen(double tempValue) {
        int size = 0;

        for (double temperature: temperatureSeries) {
            if (temperature < tempValue) {
                size++;
            }
        }

        double[] valuesLess = new double[size];
        int ind = 0;

        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < tempValue) {
                valuesLess[i] = temperatureSeries[i];
            }
        }

        return valuesLess;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int size = 0;

        for (double temperature: temperatureSeries) {
            if (temperature > tempValue) {
                size++;
            }
        }

        double[] valuesLess = new double[size];
        int ind = 0;

        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > tempValue) {
                valuesLess[i] = temperatureSeries[i];
            }
        }
        return valuesLess;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp: temps) {
            if (temp < bound) {
                throw new InputMismatchException();
            }
        }
        int var = temperatureSeries.length;
        while (var < temps.length) {
            var = var * 2;
        }

        double[] biggerArray = new double[var];

        biggerArray = Arrays.copyOf(temperatureSeries, temperatureSeries.length);

        int ind = temperatureSeries.length;
        for (double tmp: temps) {
            biggerArray[ind] = tmp;
            ind++;
        }

        double sumTemperatureSeries = summa();
        return (int) sumTemperatureSeries;
    }
}
