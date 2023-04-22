package lk.ijse.super_cargo.util;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.controlsfx.control.WorldMapView.Country.LK;

public class ValidationController {


//
//
//
//
//
//
//
//
//
//
//    public static boolean contactCheck(String contact) {
//        String contactRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}$";
//        Pattern pattern = Pattern.compile(contactRegex);
//        Matcher matcher = pattern.matcher(contact);
//        return matcher.matches();
//    }

    public static boolean emailCheck(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean BuyercontactCheck(String contact) {
        String contactRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,12}$";
        Pattern pattern = Pattern.compile(contactRegex);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }

    public static boolean buyerIdCheck(String custId) {
        String customerRegex = "^(BUY-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }
    public static boolean employeeIdCheck(String custId) {
        String customerRegex = "^(EMPY-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }
    public static boolean supplierIdCheck(String custId){
        String customerRegex = "^(SUP-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }
    public static boolean salaryIdCheck(String custId) {
        String customerRegex = "^(SLRY-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }

    public static boolean itemIdCheck(String custId){
        String customerRegex = "^(ITEM-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }
    public static boolean matirialIdCheck(String custId){
        String customerRegex = "^(MAT-)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }


    public static boolean customerNameValidate(String custName) {
        String customerRegex = "^[A-z\\s]{4,15}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custName);
        return matcher.matches();
}

    public  static boolean salary(String salary) {
        Pattern idPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        boolean matches = idPattern.matcher(salary).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

        public static boolean Nic (String nic){
            Pattern idPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");

            boolean matches = idPattern.matcher(nic).matches();
            if (matches) {
                return true;
            } else {
                return false;
            }
        }

    public static boolean DateOfBirth (String nic){
        Pattern idPattern = Pattern.compile("^([0-9]{4})-([0-9]{2})-([0-9]{2})$");

        boolean matches = idPattern.matcher(nic).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean Password (String password){
        Pattern idPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

        boolean matches = idPattern.matcher(password).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }



}
