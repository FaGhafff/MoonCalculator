package com.example.mooncalculator.Fragments.Converter;


import com.digidemic.unitof.UnitOf;

public class Core {

    private double input;
    private double middleValue;
    private double finalValue;
    private String expression;


    Core setInput(double input) {
        this.input = input;
        return this;
    }

    Core setInput(String expression) {
        this.expression = expression;
        return this;
    }


    Core from(Area area) {
        switch (area) {
            case Acres:
                middleValue = new UnitOf.Area().fromAcres(input).toSquareMeters();
                break;
            case Ares:
                middleValue = new UnitOf.Area().fromAres(input).toSquareMeters();
                break;
            case Hectares:
                middleValue = new UnitOf.Area().fromHectares(input).toSquareMeters();
                break;
            case SquareFeet:
                middleValue = new UnitOf.Area().fromSquareFeet(input).toSquareMeters();
                break;
            case SquareMiles:
                middleValue = new UnitOf.Area().fromSquareMiles(input).toSquareMeters();
                break;
            case SquareInches:
                middleValue = new UnitOf.Area().fromSquareInches(input).toSquareMeters();
                break;
            case SquareMeters:
                middleValue = new UnitOf.Area().fromSquareMeters(input).toSquareMeters();
                break;
            case SquareKilometers:
                middleValue = new UnitOf.Area().fromSquareKilometers(input).toSquareMeters();
                break;
            case SquareCentimeters:
                middleValue = new UnitOf.Area().fromSquareCentimeters(input).toSquareMeters();
                break;
        }
        return this;
    }

    Core to(Area area) {
        switch (area) {
            case Acres:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toAcres();
                break;
            case Ares:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toAres();
                break;
            case Hectares:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toHectares();
                break;
            case SquareFeet:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toSquareFeet();
                break;
            case SquareMiles:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toSquareMiles();
                break;
            case SquareInches:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toSquareInches();
                break;
            case SquareMeters:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toSquareMeters();
                break;
            case SquareKilometers:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toSquareKilometers();
                break;
            case SquareCentimeters:
                finalValue = new UnitOf.Area().fromSquareMeters(middleValue).toSquareCentimeters();
                break;
        }
        return this;
    }


    Core from(Length length) {
        switch (length) {
            case Centimetres:
                middleValue = new UnitOf.Length().fromCentimeters(input).toMeters();
                break;
            case Feet:
                middleValue = new UnitOf.Length().fromFeet(input).toMeters();
                break;
            case Mils:
                //todo not found
                middleValue = 0.0;
                break;
            case Yard:
                middleValue = new UnitOf.Length().fromYards(input).toMeters();
                break;
            case Miles:
                middleValue = new UnitOf.Length().fromMiles(input).toMeters();
                break;
            case Inches:
                middleValue = new UnitOf.Length().fromInches(input).toMeters();
                break;
            case Metres:
                middleValue = new UnitOf.Length().fromMeters(input).toMeters();
                break;
            case Kilometres:
                middleValue = new UnitOf.Length().fromKilometers(input).toMeters();
                break;
            case Millimeters:
                middleValue = new UnitOf.Length().fromMillimeters(input).toMeters();
                break;
            case Nauticalmiles:
                middleValue = new UnitOf.Length().fromNauticalMilesInternational(input).toMeters();
                break;
        }
        return this;
    }

    Core to(Length length) {
        switch (length) {
            case Nauticalmiles:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toNauticalMilesInternational();
                break;
            case Millimeters:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toMillimeters();
                break;
            case Kilometres:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toKilometers();
                break;
            case Metres:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toMeters();
                break;
            case Inches:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toInches();
                break;
            case Miles:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toMiles();
                break;
            case Yard:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toYards();
                break;
            case Mils:
                //todo not found
                finalValue = 0.0;
                break;
            case Feet:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toFeet();
                break;
            case Centimetres:
                finalValue = new UnitOf.Length().fromMeters(middleValue).toCentimeters();
                break;
        }
        return this;
    }


    Core from(Temperature temperature) {

        switch (temperature) {
            case Kelvin:
                middleValue = new UnitOf.Temperature().fromKelvin(input).toCelsius();
                break;
            case Celsius:
                middleValue = new UnitOf.Temperature().fromCelsius(input).toCelsius();
                break;
            case Fahrenheit:
                middleValue = new UnitOf.Temperature().fromFahrenheit(input).toCelsius();
                break;
        }
        return this;
    }

    Core to(Temperature temperature) {

        switch (temperature) {
            case Kelvin:
                finalValue = new UnitOf.Temperature().fromCelsius(middleValue).toKelvin();
                break;
            case Celsius:
                finalValue = new UnitOf.Temperature().fromCelsius(middleValue).toCelsius();
                break;
            case Fahrenheit:
                finalValue = new UnitOf.Temperature().fromCelsius(middleValue).toFahrenheit();
                break;
        }
        return this;
    }


    Core from(Volume volume) {
        switch (volume) {
            case Litres:
                middleValue = new UnitOf.Volume().fromLiters(input).toLiters();
                break;
            case Cubicfeet:
                middleValue = new UnitOf.Volume().fromCubicFeet(input).toLiters();
                break;
            case UKgallons:
                middleValue = new UnitOf.Volume().fromGallonsUK(input).toLiters();
                break;
            case USgallons:
                middleValue = new UnitOf.Volume().fromGallonsUS(input).toLiters();
                break;
            case Millilitres:
                middleValue = new UnitOf.Volume().fromMilliliters(input).toLiters();
                break;
            case Cubicinches:
                middleValue = new UnitOf.Volume().fromCubicInches(input).toLiters();
                break;
            case Cubicmetres:
                middleValue = new UnitOf.Volume().fromCubicMeters(input).toLiters();
                break;
            case Cubiccentimetres:
                middleValue = new UnitOf.Volume().fromCubicCentimeters(input).toLiters();
                break;
        }
        return this;
    }

    Core to(Volume volume) {
        switch (volume) {
            case Litres:
                finalValue = new UnitOf.Volume().fromLiters(middleValue).toLiters();
                break;
            case Cubicfeet:
                finalValue = new UnitOf.Volume().fromLiters(middleValue).toCubicFeet();
                break;
            case UKgallons:
                finalValue = new UnitOf.Volume().fromLiters(middleValue).toGallonsUK();
                break;
            case USgallons:
                finalValue = new UnitOf.Volume().fromLiters(middleValue).toGallonsUS();
                break;
            case Millilitres:
                finalValue = new UnitOf.Volume().fromLiters(middleValue).toMilliliters();
                break;
            case Cubicinches:
                finalValue = new UnitOf.Volume().fromLiters(middleValue).toCubicInches();
                break;
            case Cubicmetres:
                finalValue = new UnitOf.Volume().fromLiters(middleValue).toCubicMeters();
                break;
            case Cubiccentimetres:
                finalValue = new UnitOf.Volume().fromLiters(middleValue).toCubicCentimeters();
                break;
        }
        return this;
    }


    Core from(Mass mass) {
        switch (mass) {
            case Tons:
                middleValue = new UnitOf.Mass().fromTonsMetric(input).toKilograms();
                break;
            case Grams:
                middleValue = new UnitOf.Mass().fromGrams(input).toKilograms();
                break;
            case Ounces:
                middleValue = new UnitOf.Mass().fromOuncesMetric(input).toKilograms();
                break;
            case Pounds:
                middleValue = new UnitOf.Mass().fromPounds(input).toKilograms();
                break;
            case UKtons:
                middleValue = new UnitOf.Mass().fromTonsImperial(input).toKilograms();
                break;
            case UStons:
                middleValue = new UnitOf.Mass().fromTonsUS(input).toKilograms();
                break;
            case Kilograms:
                middleValue = new UnitOf.Mass().fromKilograms(input).toKilograms();
                break;
        }
        return this;
    }

    Core to(Mass mass) {
        switch (mass) {
            case Tons:
                finalValue = new UnitOf.Mass().fromKilograms(middleValue).toTonsMetric();
                break;
            case Grams:
                finalValue = new UnitOf.Mass().fromKilograms(middleValue).toGrams();
                break;
            case Ounces:
                finalValue = new UnitOf.Mass().fromKilograms(middleValue).toOuncesMetric();
                break;
            case Pounds:
                finalValue = new UnitOf.Mass().fromKilograms(middleValue).toPounds();
                break;
            case UKtons:
                finalValue = new UnitOf.Mass().fromKilograms(middleValue).toTonsImperial();
                break;
            case UStons:
                finalValue = new UnitOf.Mass().fromKilograms(middleValue).toTonsUS();
                break;
            case Kilograms:
                finalValue = new UnitOf.Mass().fromKilograms(middleValue).toKilograms();
                break;
        }
        return this;
    }


    Core from(Data data) {
        switch (data) {
            case Bits:
                middleValue = new UnitOf.DataStorage().fromBits(input).toMegabytes();
                break;
            case Bytes:
                middleValue = new UnitOf.DataStorage().fromBytes(input).toMegabytes();
                break;
            case Gigabytes:
                middleValue = new UnitOf.DataStorage().fromGigabytes(input).toMegabytes();
                break;
            case Kilobytes:
                middleValue = new UnitOf.DataStorage().fromKilobytes(input).toMegabytes();
                break;
            case Megabytes:
                middleValue = new UnitOf.DataStorage().fromMegabytes(input).toMegabytes();
                break;
            case Terabytes:
                middleValue = new UnitOf.DataStorage().fromTerabytes(input).toMegabytes();
                break;
        }
        return this;
    }

    Core to(Data data) {
        switch (data) {
            case Bits:
                finalValue = new UnitOf.DataStorage().fromMegabytes(middleValue).toBits();
                break;
            case Bytes:
                finalValue = new UnitOf.DataStorage().fromMegabytes(middleValue).toBytes();
                break;
            case Gigabytes:
                finalValue = new UnitOf.DataStorage().fromMegabytes(middleValue).toGigabytes();
                break;
            case Kilobytes:
                finalValue = new UnitOf.DataStorage().fromMegabytes(middleValue).toKilobytes();
                break;
            case Megabytes:
                finalValue = new UnitOf.DataStorage().fromMegabytes(middleValue).toMegabytes();
                break;
            case Terabytes:
                finalValue = new UnitOf.DataStorage().fromMegabytes(middleValue).toTerabytes();
                break;
        }
        return this;
    }

    Core from(Speed speed) {
        switch (speed) {
            case Knots:
                middleValue = new UnitOf.Speed().fromKnots(input).toKilometersPerHour();
                break;
            case Feetperhour:
                middleValue = new UnitOf.Speed().fromFeetPerHour(input).toKilometersPerHour();
                break;
            case Meterperhour:
                middleValue = new UnitOf.Speed().fromMetersPerHour(input).toKilometersPerHour();
                break;
            case Milesperhour:
                middleValue = new UnitOf.Speed().fromMilesPerHour(input).toKilometersPerHour();
                break;
            case Feetpersecond:
                middleValue = new UnitOf.Speed().fromFeetPerSecond(input).toKilometersPerHour();
                break;
            case Inchesperhour:
                middleValue = new UnitOf.Speed().fromInchesPerHour(input).toKilometersPerHour();
                break;
            case Meterpersecond:
                middleValue = new UnitOf.Speed().fromMetersPerSecond(input).toKilometersPerHour();
                break;
            case Milespersecond:
                middleValue = new UnitOf.Speed().fromMilesPerSecond(input).toKilometersPerHour();
                break;
            case Inchespersecond:
                middleValue = new UnitOf.Speed().fromInchesPerSecond(input).toKilometersPerHour();
                break;
            case Kilometresperhour:
                middleValue = new UnitOf.Speed().fromKilometersPerHour(input).toKilometersPerHour();
                break;
            case Kilometrespersecond:
                middleValue = new UnitOf.Speed().fromKilometersPerSecond(input).toKilometersPerHour();
                break;
        }
        return this;
    }

    Core to(Speed speed) {
        switch (speed) {
            case Knots:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toKnots();
                break;
            case Feetperhour:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toFeetPerHour();
                break;
            case Meterperhour:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toMetersPerHour();
                break;
            case Milesperhour:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toMilesPerHour();
                break;
            case Feetpersecond:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toFeetPerSecond();
                break;
            case Inchesperhour:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toInchesPerHour();
                break;
            case Meterpersecond:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toMetersPerSecond();
                break;
            case Milespersecond:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toMilesPerSecond();
                break;
            case Inchespersecond:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toInchesPerSecond();
                break;
            case Kilometresperhour:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toKilometersPerHour();
                break;
            case Kilometrespersecond:
                finalValue = new UnitOf.Speed().fromKilometersPerHour(middleValue).toKilometersPerSecond();
                break;
        }
        return this;
    }

    Core from(Time time) {
        switch (time) {
            case Days:
                middleValue = new UnitOf.Time().fromDays(input).toHours();
                break;
            case Hours:
                middleValue = new UnitOf.Time().fromHours(input).toHours();
                break;
            case Weeks:
                middleValue = new UnitOf.Time().fromWeeks(input).toHours();
                break;
            case Minutes:
                middleValue = new UnitOf.Time().fromMinutes(input).toHours();
                break;
            case Seconds:
                middleValue = new UnitOf.Time().fromSeconds(input).toHours();
                break;
            case Milliseconds:
                middleValue = new UnitOf.Time().fromMilliseconds(input).toHours();
                break;
        }
        return this;
    }

    Core to(Time time) {
        switch (time) {
            case Days:
                finalValue = new UnitOf.Time().fromHours(middleValue).toDays();
                break;
            case Hours:
                finalValue = new UnitOf.Time().fromHours(middleValue).toHours();
                break;
            case Weeks:
                finalValue = new UnitOf.Time().fromHours(middleValue).toWeeks();
                break;
            case Minutes:
                finalValue = new UnitOf.Time().fromHours(middleValue).toMinutes();
                break;
            case Seconds:
                finalValue = new UnitOf.Time().fromHours(middleValue).toSeconds();
                break;
            case Milliseconds:
                finalValue = new UnitOf.Time().fromHours(middleValue).toMilliseconds();
                break;
        }
        return this;
    }

    Core from(Notation notation) {
        //todo my eyes are destroyed
        return this;
    }

    double getOutput() {

        return finalValue;
    }

    enum Field {
        Area,
        Length,
        Temperature,
        Volume,
        Mass,
        Data,
        Speed,
        Time,
        Notation;
    }

    enum Area {
        Acres,
        Ares,
        Hectares,
        SquareCentimeters,
        SquareFeet,
        SquareInches,
        SquareKilometers,
        SquareMeters,
        SquareMiles;

        static Area getEnum(String string) {
            return Area.valueOf(string);
        }
    }

    enum Length {
        Millimeters,
        Centimetres,
        Metres,
        Kilometres,
        Inches,
        Feet,
        Yard,
        Miles,
        Nauticalmiles,
        Mils;

        static Length getEnum(String string) {
            return Length.valueOf(string);
        }
    }

    enum Temperature {
        Celsius,
        Fahrenheit,
        Kelvin;

        static Temperature getEnum(String string) {
            return Temperature.valueOf(string);
        }
    }

    enum Volume {
        UKgallons,
        USgallons,
        Litres,
        Millilitres,
        Cubiccentimetres,
        Cubicmetres,
        Cubicinches,
        Cubicfeet;

        static Volume getEnum(String string) {
            return Volume.valueOf(string);
        }
    }

    enum Mass {
        Tons,
        UKtons,
        UStons,
        Pounds,
        Ounces,
        Kilograms,
        Grams;

        static Mass getEnum(String string) {
            return Mass.valueOf(string);
        }
    }

    enum Data {
        Bits,
        Bytes,
        Kilobytes,
        Megabytes,
        Gigabytes,
        Terabytes;

        static Data getEnum(String string) {
            return Data.valueOf(string);
        }
    }

    enum Speed {
        Meterpersecond,
        Meterperhour,
        Kilometrespersecond,
        Kilometresperhour,
        Inchespersecond,
        Inchesperhour,
        Feetpersecond,
        Feetperhour,
        Milespersecond,
        Milesperhour,
        Knots;

        static Speed getEnum(String string) {
            return Speed.valueOf(string);
        }
    }

    enum Time {
        Milliseconds,
        Seconds,
        Minutes,
        Hours,
        Days,
        Weeks;

        static Time getEnum(String string) {
            return Time.valueOf(string);
        }
    }

    enum Notation {
        Infix,
        Prefix,
        Postfix;

        static Notation getEnum(String string) {
            return Notation.valueOf(string);
        }
    }
}