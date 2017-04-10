package com.eaglesakura.swagger;


import java.util.regex.Pattern;

/**
 * Param validate
 */
public class ParameterValidator {
    Object mParameter;

    Boolean mRequired;

    String mPattern;

    Integer mMinLength;

    Integer mMaxLength;

    public ParameterValidator(Object parameter) {
        mParameter = parameter;
    }

    public ParameterValidator required(Boolean required) {
        mRequired = required;
        return this;
    }

    public ParameterValidator pattern(String pattern) {
        if (pattern != null) {
            if (pattern.startsWith("/")) {
                pattern = pattern.substring(1, pattern.length() - 1);
            }
        }
        mPattern = pattern;
        return this;
    }

    public ParameterValidator minLength(Integer minLength) {
        mMinLength = minLength;
        return this;
    }

    public ParameterValidator maxLength(Integer maxLength) {
        mMaxLength = maxLength;
        return this;
    }

    public boolean valid() {
        // required param error
        if (mRequired != null && mParameter == null) {
            if (mRequired) {
                // 必須パラメータが設定されていない
                return false;
            } else {
                // パラメータは無いが必須でもないのでこれ以上のチェックは不要
                return true;
            }
        }

        if (mParameter instanceof Validatable) {
            return ((Validatable) mParameter).valid();
        }

        if (mParameter instanceof String) {
            String s = ((String) mParameter);
            if (mMaxLength != null && s.length() > mMaxLength) {
                return false;
            }

            if (mMinLength != null && s.length() < mMinLength) {
                return false;
            }

            if (mPattern != null && !Pattern.compile(mPattern).matcher(((String) mParameter)).find()) {
                return false;
            }
        }

        return mParameter != null;
    }
}
