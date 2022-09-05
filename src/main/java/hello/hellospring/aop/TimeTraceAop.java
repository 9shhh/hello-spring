package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // spring aop를 이용하기 위해 정의 필요.
//@Component bean 등록해도됨.
public class TimeTraceAop {
    // @Around 어노테이션을 통한 적용 대상 지정. (타겟팅)
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    /**
     * ###### 주의 사항 ######
     * # 위 @Around 의 execution 에 정의된 내용을 보면, 해당 패키지 하위에 모든 클래스의 메소드에 다 적용하라는 의미. 단, 현재 TimeTraceAop 자기 자신은 대상에서 제외한다.
     * # => 수동 빈 등록 클래스에 정의된 timeTraceAop() 자신도 aop 대상이 되므로, 자신을 계속 호출하여 순환참조 에러 발생.
     * # @Component 선언을 통한 bean 등록 방식(컴포넌트 스캔)은 aop 의 대상이 되는 이런 코드 자체가 없기 때문에 문제가 발생하지 않았습니다.
     */

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
