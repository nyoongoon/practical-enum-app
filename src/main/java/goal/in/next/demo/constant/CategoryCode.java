package goal.in.next.demo.constant;

import goal.in.next.demo.utils.EnumUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum CategoryCode implements EnumUtils {
    CUSTOMER_INQUIRY("CT000001"),
    FREE_BOARD("CT000002"),
    REFUND_INQUIRY("CT000003"),
    TECH_SUPPORT("CT000004"),
    PRODUCT_REVIEWS("CT000005"),
    DISCUSSIONS("CT000006"),
    USER_MANUALS("CT000007");

    private String code;

//    public static boolean hasCode(List<CategoryCode> codeList, CategoryCode code){
//        return codeList.stream()
//                .anyMatch(x -> x.getCode().equals(code.getCode()));
//    }
//
//
//    public static boolean isEqual(CategoryCode categoryCode, CategoryCode code){
//        return categoryCode.getCode().equals(code.getCode());
//    }



//    @Converter(autoApply = true)
//    public static class CategoryCodeConverter implements AttributeConverter<CategoryCode, String>{
//        @Override
//        public String convertToDatabaseColumn(CategoryCode attribute) {
//            // CategoryCode 객체를 String으로 변환하여 데이터베이스에 저장하는 로직을 구현하세요.
//            // 예: return attribute.getCode();
//            return attribute != null ? attribute.getCode() : null;
//        }
//
//        @Override
//        public CategoryCode convertToEntityAttribute(String dbData) {
//            // 데이터베이스에서 가져온 String을 CategoryCode 객체로 변환하는 로직을 구현하세요.
//            // 예: return new CategoryCode(dbData);
//            if(dbData == null){
//                return null;
//            }
//
//            return Arrays.stream(CategoryCode.values())
//                    .filter(categoryCode -> categoryCode.getCode().equals(dbData))
//                    .findFirst()
//                    .orElse(null);
//
//        }
//    }
}
