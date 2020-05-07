package mp.cariaso.springboot.model;

import java.util.stream.Stream;

public enum EnumWithValue {

    CODE_A("A"),
    CODE_B("B");

    private String code;

    EnumWithValue(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static EnumWithValue getEnumByCode(String code) {

        return Stream.of(EnumWithValue.class.getEnumConstants()).filter(e -> e.getCode().equals(code)).findFirst().get();
    }
}
