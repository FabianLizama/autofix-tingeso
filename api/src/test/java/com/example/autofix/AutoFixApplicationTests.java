package com.example.autofix;

import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Asegúrate de usar un perfil adecuado que no afecte el entorno de producción o desarrollo.
public class AutoFixApplicationTests {

    @Test
    void main() {
        assertThatCode(() -> AutoFixApplication.main(new String[]{}))
            .doesNotThrowAnyException();
    }
}
