package org.spring.DTO;

import org.springframework.hateoas.RepresentationModel;

/**
 * DTO для сущности пользователя
 */
public class UserDTO extends RepresentationModel<UserDTO> {
    /** Уникальный идентификатор пользователя */
    private Integer id;

    /** Имя пользователя */
    private String name;

    /** Адрес электронной почты */
    private String email;

    /** Возраст пользователя */
    private Integer age;

    /** Конструктор создания объекта DTO */
    public UserDTO(Integer id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserDTO() {
    }
    /**
     * Возвращает идентификатор пользователя.
     * @return id пользователя
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает значение переменной идентификатора пользователя
     * @param id новое значение идентификатора пользователя
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Возвращает имя пользователя.
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает значение переменной имени пользователя
     * @param name новое значение имени пользователя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает адрес электронной почты.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает значение переменной электронной почты
     * @param email новое значение электронной почты
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Возвращает возраст пользователя.
     * @return возраст
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Устанавливает значение переменной возраст
     * @param age новое значение возраста
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}
