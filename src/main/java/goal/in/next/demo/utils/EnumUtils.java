package goal.in.next.demo.utils;

import java.util.List;

public interface EnumUtils {

    static <E extends Enum<E> & EnumUtils> boolean hasCode(List<E> codeList, E code) {
        return codeList.stream()
                .anyMatch(x -> x.getCode().equals(code.getCode()));

    }

    static <E extends Enum<E> & EnumUtils> boolean isEqual(E firstCode, E secondCode) {
        return firstCode.getCode().equals(secondCode.getCode());
    }

    String getCode();
}
