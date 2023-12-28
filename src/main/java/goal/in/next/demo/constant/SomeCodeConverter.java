package goal.in.next.demo.constant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class SomeCodeConverter implements AttributeConverter<SomeCode, String> {
    @Override
    public String convertToDatabaseColumn(SomeCode attribute) {
        // DB에 저장 시 Enum -> 코드 변환
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public SomeCode convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Arrays.stream(SomeCode.values())
                // 프로그램에서 조회 시 코드 -> Enum 변환
                .filter(someCode -> someCode.getCode().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}
