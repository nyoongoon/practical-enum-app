package goal.in.next.demo._employee;

import com.querydsl.jpa.impl.JPAQueryFactory;
import goal.in.next.demo._employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static goal.in.next.demo._employee.QEmployee.employee;


@Repository
@RequiredArgsConstructor
public class EmployeeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<Employee> findById(String nickName, String name) {
        return Optional.ofNullable(queryFactory
                .selectFrom(employee)
                .where(employee.nickName.eq(nickName)
                        .and(employee.name.eq(name)))
                .fetchOne()
        );
    }
}