package bloggy.validator;

import org.nocturne.validation.ValidationException;

import java.util.regex.Pattern;

public class OptionalValidator extends BaseValidator {

    private final Pattern pattern;
    private Long minLength;
    private Long maxLength;
    private String patternMessage;
    private static final String DEFAULT_MESSAGE = "Unexpected characters were found.";


    public OptionalValidator(String regex) {
        pattern = Pattern.compile(regex);
    }

    public OptionalValidator(String regex, Long minLength, Long maxLength) {
        pattern = Pattern.compile(regex);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public OptionalValidator(String regex, String message) {
        pattern = Pattern.compile(regex);
        this.patternMessage = message;
    }

    public OptionalValidator(String regex, Long minLength, Long maxLength, String message) {
        pattern = Pattern.compile(regex);
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.patternMessage = message;
    }

    @Override
    public void run(String value) throws ValidationException {
        super.run(value);
        validateLength(value);
        validateRegex(value);
    }

    private void validateRegex(String value) throws ValidationException {
        if (!pattern.matcher(value).matches()) {
            throw new ValidationException($(patternMessage == null ? DEFAULT_MESSAGE : patternMessage));
        }
    }

    private void validateLength(String value) throws ValidationException {
        if ((minLength != null && value.length() < minLength) || (maxLength != null && value.length() > maxLength)) {
            throw new ValidationException($(getMessageForLength()));
        }
    }

    private String getMessageForLength() {
        StringBuilder stringBuilder = new StringBuilder("Field must contain ");
        if (minLength == null) {
            stringBuilder.append(String.format("at most %d", maxLength));
        } else if (maxLength == null) {
            stringBuilder.append(String.format("at least %d", minLength));
        } else {
            stringBuilder.append(String.format("from %d to %d", minLength, maxLength));
        }
        stringBuilder.append(" symbols");
        return stringBuilder.toString();
    }

}
