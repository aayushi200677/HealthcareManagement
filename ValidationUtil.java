package hospital;


import java.util.ArrayList;
import java.util.List;

public final class ValidationUtil {
    private ValidationUtil() {}

    public static List<String> validateDoctor(String name, String specialization, String experienceStr) {
        List<String> errors = new ArrayList<>();

        if (isBlank(name)) errors.add("Doctor name is required.");
        else if (!name.matches("^[A-Za-z. ]{3,50}$")) errors.add("Doctor name must be 3–50 letters.");

        if (isBlank(specialization)) errors.add("Specialization is required.");
        else if (!specialization.matches("^[A-Za-z &-]{2,50}$")) errors.add("Specialization must be 2–50 characters.");

        if (isBlank(experienceStr)) errors.add("Experience is required.");
        else {
            try {
                int exp = Integer.parseInt(experienceStr);
                if (exp < 0 || exp > 60) errors.add("Experience must be between 0 and 60.");
            } catch (NumberFormatException e) {
                errors.add("Experience must be a number.");
            }
        }

        return errors;
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
