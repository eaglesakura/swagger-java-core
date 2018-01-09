package com.eaglesakura.swagger;


import java.util.regex.Pattern;

/**
 * 送受信対象のデータを検証する
 */
@SuppressWarnings("WeakerAccess")
public class ParameterValidator {

    private Object mParameter;

    private Boolean mRequired;

    private String mPattern;

    private Integer mMinLength;

    private Integer mMaxLength;

    public ParameterValidator(Object parameter) {
        mParameter = parameter;
    }

    public ParameterValidator required(Boolean required) {
        mRequired = required;
        return this;
    }

    /**
     * 文字列の正規表現パターンが渡される.
     *
     * patternに一致しない文字列はInvalidとして扱う
     */
    public ParameterValidator pattern(String pattern) {
        if (pattern != null) {
            if (pattern.startsWith("/")) {
                pattern = pattern.substring(1, pattern.length() - 1);
            }
        }
        mPattern = pattern;
        return this;
    }

    /**
     * 文字列の最小文字数を規定する
     *
     * mParameter#length() < minLengthの文字列はInvalidとして扱う
     */
    public ParameterValidator minLength(Integer minLength) {
        mMinLength = minLength;
        return this;
    }

    /**
     * 文字列の最大文字数を規定する
     *
     * mParameter#length() > maxLengthの文字列はInvalidとして扱う
     */
    public ParameterValidator maxLength(Integer maxLength) {
        mMaxLength = maxLength;
        return this;
    }

    /**
     * パラメータが正しいことを検証する
     */
    public boolean valid() {
        // パラメータを事前チェックする
        if (mParameter == null) {
            boolean required = (mRequired != null && mRequired);
            if (required) {
                // 必須パラメータが指定されていない
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

        return true;
    }
}
