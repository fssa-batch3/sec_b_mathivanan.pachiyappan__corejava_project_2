package in.fssa.evotingsystem.util;

import in.fssa.evotingsystem.exception.ValidationException;

/**
 * Provides utility methods for string validation.
 */

public class StringUtil {

	/**
     * Throws a ValidationException if the input string is invalid.
     *
     * @param input      The input string to validate.
     * @param inputName  The name of the input field being validated.
     * @throws ValidationException If the input string is invalid.
     */
    public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
        if (isInvalidString(input)) {
            throw new ValidationException(inputName.concat(" cannot be Null or Empty"));
        }
    }

    /**
     * Checks if a string is valid (not null and not empty after trimming).
     *
     * @param newString The string to validate.
     * @return True if the string is valid, false otherwise.
     */
    public static boolean isValidString(String newString) {
        return newString != null && !newString.trim().isEmpty();
    }

    /**
     * Checks if a string is invalid (null or empty after trimming).
     *
     * @param newString The string to validate.
     * @return True if the string is invalid, false otherwise.
     */
    public static boolean isInvalidString(String newString) {
        return newString == null || newString.trim().isEmpty();
    }
    
}

