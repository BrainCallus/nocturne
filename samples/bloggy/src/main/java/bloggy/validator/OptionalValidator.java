package bloggy.validator;

import org.nocturne.validation.ValidationException;

import java.util.regex.Pattern;

public class OptionalValidator extends BaseValidator {

    private final Pattern pattern;
    private final long length;
    private final String message;
    private static final String DEFAULT_MESSAGE = "Unexpected characters were found.";


    public OptionalValidator(String regex) {
        pattern = Pattern.compile(regex);
        length = -1;
        message = DEFAULT_MESSAGE;
    }

    public OptionalValidator(String regex, long length) {
        pattern = Pattern.compile(regex);
        this.length = length;
        message = DEFAULT_MESSAGE;
    }

    public OptionalValidator(String regex, String message) {
        pattern = Pattern.compile(regex);
        this.message = message;
        length = -1;
    }

    public OptionalValidator(String regex, long length, String message) {
        pattern = Pattern.compile(regex);
        this.length = length;
        this.message = message;
    }

    @Override
    public void run(String value) throws ValidationException {
        super.run(value);
        if ((length > 0 && value.length() < length)) {
            throw new ValidationException($(String.format("Must contain at least %d symbols%n", length)));
        }
        if (pattern != null && !pattern.matcher(value).matches()) {
            throw new ValidationException($(message));
        }
    }

}
