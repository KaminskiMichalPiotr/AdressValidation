import com.sun.java.accessibility.util.Translator;

import java.util.regex.Pattern;

public class AddressDataValidator {


    //Assumption - polish postal code has format dd-ddd
    private String polishPostalCode;

    //Assumption - street can start with number e.g. "3 Maja"
    private String street;

    //Assumption - member of city name can be separated only by whitespace or '-'
    private String city;

    //Assumption - building number can be either:
    //- only numeric e.g. 87
    //- numeric with one letter e.g. 87A
    //- numeric with flat number e.g. 87/5
    //- numeric with one letter and flat number(without letter) 87A/5
    private String buildingNumber;

    //Default constructor for test purposes
    public AddressDataValidator() {
        this.polishPostalCode = null;
        this.street = null;
        this.city = null;
        this.buildingNumber = null;
    }

    public AddressDataValidator(String polishPostalCode, String street, String city, String buildingNumber) throws IllegalArgumentException {
        checkIfDataIsNotEmpty(polishPostalCode, street, city, buildingNumber);
        if(validatePostalCode(polishPostalCode))
            this.polishPostalCode = polishPostalCode;
        if(validateStreet(street))
            this.street = street;
        if(validateCity(city))
            this.city = city;
        if(validateBuildingNumber(buildingNumber))
            this.buildingNumber = buildingNumber;
    }

    public void checkIfDataIsNotEmpty(String polishPostalCode, String street, String city, String buildingNumber){
        if(isDataEmptyOrNull(polishPostalCode)){
            throw new IllegalArgumentException("Postal Code cannot be empty or null!");
        }
        if(isDataEmptyOrNull(street)){
            throw new IllegalArgumentException("Street cannot be empty or null!");
        }
        if(isDataEmptyOrNull(city)){
            throw new IllegalArgumentException("City cannot be empty or null!");
        }
        if(isDataEmptyOrNull(buildingNumber)){
            throw new IllegalArgumentException("Building Number cannot be empty or null!");
        }
    }

    public static boolean isDataEmptyOrNull(final String data ) {
        return data == null || data.trim().isEmpty();
    }

    private boolean validateStreet(String street) throws IllegalArgumentException{
        Pattern standard = Pattern.compile("[A-Z][a-z]++");  //e.g. "Zwyciestwa"
        Pattern startingWithSpecialWord = Pattern.compile("([a-z]++.*)( [A-Z][a-z]++)+( [A-Z][a-z]++-[A-Z][a-z]++)*"); //e.g. "most Nowaka", "plac Centralny"
        Pattern streetDualOrMoreName = Pattern.compile("([A-Z][a-z]++ )+[A-Z][a-z]++"); //e.g. "Krolowej Jadwigi"
        Pattern streetDualOrMoreNameEndingWithDashSurname = Pattern.compile("([A-Z][a-z]++ )*([A-Z][a-z]++-[A-Z][a-z]++)"); //e.g. "Generala Boleslawa Wieniawy-Dlugoszowskiego"
        Pattern streetWithNumberWhitespaceName  = Pattern.compile("\\d++ [A-Z][a-z]++"); //e.g. "3 Maja"
        Pattern streetWithNumberDashName = Pattern.compile("\\d++-[a-z]++"); //e.g. "100-lecia"
        if(streetDualOrMoreNameEndingWithDashSurname.matcher(street).matches() || standard.matcher(street).matches() || streetWithNumberWhitespaceName.matcher(street).matches()
            || streetWithNumberDashName.matcher(street).matches() || streetDualOrMoreName.matcher(street).matches() || startingWithSpecialWord.matcher(street).matches())
            return true;
        throw new IllegalArgumentException("Street doesn't match any known format!");
    }

    private boolean validateCity(String city) throws IllegalArgumentException{
        Pattern citySingle = Pattern.compile("[A-Z][a-z]++");
        Pattern cityWithDualNameAndWhitespace= Pattern.compile("[A-Z][a-z]++ [A-Z][a-z]++");
        Pattern cityWithDualNameAndDash = Pattern.compile("[A-Z][a-z]++-[A-Z][a-z]++");
        if(citySingle.matcher(city).matches() || cityWithDualNameAndWhitespace.matcher(city).matches() || cityWithDualNameAndDash.matcher(city).matches())
            return true;
        throw new IllegalArgumentException("City doesn't match any known format! Try e.g. Poznan, Czerwionka-Leszczyny, Nowy Sacz");
    }

    private boolean validatePostalCode(String postalCode) throws IllegalArgumentException{
        Pattern postalCodePattern = Pattern.compile("\\d{2}-\\d{3}");
        if(postalCodePattern.matcher(postalCode).matches())
            return true;
        throw new IllegalArgumentException("Postal Code format doesn't mach polish dd-ddd, or doesn't contain only numeric values!");
    }

    private boolean validateBuildingNumber(String street) throws IllegalArgumentException{
        Pattern buildingWithoutFlatNumber = Pattern.compile("\\d++[a-zA-Z]??");
        Pattern buildingWithFlatNumber = Pattern.compile("\\d++[a-zA-Z]?/\\d++");
        if(buildingWithFlatNumber.matcher(street).matches() || buildingWithoutFlatNumber.matcher(street).matches())
            return true;
        throw new IllegalArgumentException("Building number doesn't match any pattern! Correct e.g. 87, 87A, 87/5, 87A/5");
    }

    public String getPolishPostalCode() {
        return polishPostalCode;
    }

    public void setPolishPostalCode(String polishPostalCode) throws IllegalArgumentException {
        if(validatePostalCode(polishPostalCode))
            this.polishPostalCode = polishPostalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws IllegalArgumentException  {
        if(validateStreet(street))
            this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws IllegalArgumentException  {
        if(validateCity(city))
            this.city = city;
    }

    public String getBuildinigNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) throws IllegalArgumentException {
        if(validateBuildingNumber(buildingNumber))
            this.buildingNumber = buildingNumber;
    }
}
