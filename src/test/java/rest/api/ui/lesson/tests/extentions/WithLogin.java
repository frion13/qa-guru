package rest.api.ui.lesson.tests.extentions;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//создание аннотации
@ExtendWith(LoginExtention.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface WithLogin {
}
