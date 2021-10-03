package com.example.accountingofsocks.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@DynamicUpdate(value = true) // По умолчанию Hibernate если мы обновляем только одно поле.
// То он все равно обновляет все поля. Он делает это, потому что с нами параллельно может кто то работать с этим обьектом,
// и мы могли получить не то что хотели. По этому Hibernate дает нам возможность вк флажки. Мы говорим. Меняй только те поля,
// которые я обновляю
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String color;

    @Min(0)
    @Max(100)
    @Column
    private Byte cottonPart; //процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);

    @Column
    @NotNull
    @Min(value = 1,message = "Количество пар носков не может быть меньше 0")
    private int quantity; //количество пар носков, целое число больше 0.

    @Override
    public String toString() {
        return "Socks{" +
                "id=" + id +
                ", color=" + color +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
