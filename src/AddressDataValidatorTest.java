import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDataValidatorTest {

    private AddressDataValidator addressDataValidator;

    @BeforeEach
    void setUp() {
        addressDataValidator = new AddressDataValidator();
    }

    @Test
    public void checkConstructor(){
        addressDataValidator= new AddressDataValidator("44-100","Jana Nowaka-Jezioranskiego","Gliwice","69B/5");
    }

    @Test
    public void checkConstructorWithWrongData(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator= new AddressDataValidator("",null,null,null));
    }

    @Test
    public void checkCorrectPostalCode(){
        addressDataValidator.setPolishPostalCode("08-100");
    }

    @Test
    public void checkIncorrectPostalCode(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setPolishPostalCode("44-1001"));
    }

    @Test
    public void checkPostalCodeWithoutDash(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setPolishPostalCode("44100"));
    }

    @Test
    public void checkDumbPostalCode(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setPolishPostalCode("dd-100"));
    }

    @Test
    public void checkCorrectBuildingNumber(){
        addressDataValidator.setBuildingNumber("85");
        addressDataValidator.setBuildingNumber("85B");
        addressDataValidator.setBuildingNumber("85/25");
        addressDataValidator.setBuildingNumber("85B/25");
    }

    @Test
    public void checkIncorrectBuildingNumber(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setBuildingNumber("C555/5"));
    }

    @Test
    public void checkBuildingNumberWithoutFlat(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setBuildingNumber("55CC"));
    }

    @Test
    public void checkIncompleteBuildingNumber(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setBuildingNumber("55/"));
    }

    @Test
    public void checkCorrectCity(){
        addressDataValidator.setCity("Czerwionka-Leszczyny");
        addressDataValidator.setCity("Czestochowa");
        addressDataValidator.setCity("Nowy Sacz");
    }

    @Test
    public void checkIncorrectCity(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setCity("CZerwionka-Leszczyny"));
    }

    @Test
    public void checkCityWithNumericData(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setCity("44021-00021"));
    }

    @Test
    public void checkCityWithoutSeparator(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setCity("CzerwionkaLeszczyny"));
    }

    @Test
    public void checkIncompleteCity(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setCity("Czerwionka-"));
    }

    @Test
    public void checkCityWithoutFirstUpperCaseLetter(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setCity("czerwionka-Leszczyny"));
    }

    @Test
    public void checkCorrectStreet(){
        addressDataValidator.setStreet("20-lecia");
        addressDataValidator.setStreet("8 Kwietnia");
        addressDataValidator.setStreet("Zwyciestwa");
        addressDataValidator.setStreet("rondo Jadwigi");
        addressDataValidator.setStreet("plk. Pilota");
        addressDataValidator.setStreet("most Stanislawa Augusta Poniatowskiego");
        addressDataValidator.setStreet("most Stanislawa Augusta-Poniatowskiego");
        addressDataValidator.setStreet("most Stanislawa Jana Augusta-Poniatowskiego");
        addressDataValidator.setStreet("Generala Boleslawa");
        addressDataValidator.setStreet("Generala Boleslawa Nowaka");
        addressDataValidator.setStreet("Generala Boleslawa Kniazia-Sobieskiego");
    }

    @Test
    public void checkIncorrectStreet(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setStreet("CZerwionka-Leszczyny"));
    }

    @Test
    public void checkStreetStartingWithNumberAndUpperCase(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setStreet("75-Lecia"));
    }

    @Test
    public void checkStreetWithoutSeparator(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setStreet("8 kwietnia"));
    }

    @Test
    public void checkIncompleteStreet(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setStreet("most"));
    }

    @Test
    public void checkStreetWithoutWhiteSpace(){
        assertThrows(IllegalArgumentException.class, () -> addressDataValidator.setStreet("GeneralaBoleslawaKniazia-Sobieskiego"));
    }
}