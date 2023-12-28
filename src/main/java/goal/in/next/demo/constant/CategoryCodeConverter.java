package goal.in.next.demo.constant;

import goal.in.next.demo.constant.CategoryCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class CategoryCodeConverter implements AttributeConverter<CategoryCode, String> {
    @Override
    public String convertToDatabaseColumn(CategoryCode attribute) {
        // DB에 저장 시 Enum -> 코드 변환
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public CategoryCode convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }

        // 프로그램에서 조회 시 코드 -> Enum 변환
        return Arrays.stream(CategoryCode.values())
                .filter(categoryCode -> categoryCode.getCode().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}