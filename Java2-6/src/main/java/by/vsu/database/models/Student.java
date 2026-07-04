package by.vsu.database.models;

import java.time.LocalDate;

public class Student {
    public static final Integer MAX_STRING_LENGTH = 255;
    public static final Integer MAX_FAILED_EXAMS = 2;

    private Integer id;
    private Integer group_id;
    private String last_name;
    private String first_name;
    private String middle_name;
    private String sex;
    private LocalDate birthday;
    private String fundingType;
    private Integer failedExams;

    public Student(Integer id, Integer group_id, String last_name, String first_name, String middle_name, String sex,
            LocalDate birthday, String fundingType, Integer failedExams) {
        setId(id);
        setGroupId(group_id);
        setLastName(last_name);
        setFirstName(first_name);
        setMiddleName(middle_name);
        setSex(sex);
        setBirthday(birthday);
        setFundingType(fundingType);
        setFailedExams(failedExams);
    }

    public Student(Integer group_id, String last_name, String first_name, String middle_name, String sex,
            LocalDate birthday, String fundingType, Integer failedExams) {
        setGroupId(group_id);
        setLastName(last_name);
        setFirstName(first_name);
        setMiddleName(middle_name);
        setSex(sex);
        setBirthday(birthday);
        setFundingType(fundingType);
        setFailedExams(failedExams);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id должен быть положительным числом!");
        }

        this.id = id;
    }

    public Integer getGroupId() {
        return group_id;
    }

    public void setGroupId(Integer group_id) {
        if (group_id == null || group_id <= 0) {
            throw new IllegalArgumentException("group_id должен быть положительным числом!");
        }

        this.group_id = group_id;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        if (last_name == null || last_name.isBlank()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой!");
        }

        if (last_name.length() > MAX_STRING_LENGTH) {
            throw new IllegalArgumentException(
                    "Максимальная длина фамилии: "
                            + MAX_STRING_LENGTH);
        }

        this.last_name = last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        if (first_name == null || first_name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым!");
        }

        if (first_name.length() > MAX_STRING_LENGTH) {
            throw new IllegalArgumentException("Максимальная длина имени: " + MAX_STRING_LENGTH);
        }

        this.first_name = first_name;
    }

    public String getMiddleName() {
        return middle_name;
    }

    public void setMiddleName(String middle_name) {
        if (middle_name == null || middle_name.isBlank()) {
            throw new IllegalArgumentException("Отчество не может быть пустым!");
        }

        if (middle_name.length() > MAX_STRING_LENGTH) {
            throw new IllegalArgumentException("Максимальная длина отчества: " + MAX_STRING_LENGTH);
        }

        this.middle_name = middle_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (sex == null || sex.isBlank()) {
            throw new IllegalArgumentException("Пол не может быть пустым!");
        }

        if (sex.length() > 1) {
            throw new IllegalArgumentException("Максимальная длина пола: " + 1);
        }

        if (!sex.equals("м") && !sex.equals("ж")) {
            throw new IllegalArgumentException("Пол должен быть 'м' или 'ж'");
        }

        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException("Дата рождения не может быть пустой!");
        }

        if (birthday.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Дата рождения не может быть в будущем!");
        }

        this.birthday = birthday;
    }

    public String getFundingType() {
        return fundingType;
    }

    public void setFundingType(String fundingType) {
        if (fundingType == null || fundingType.isBlank()) {
            throw new IllegalArgumentException("Тип финансирования не может быть пустым!");
        }

        if (!fundingType.equals("платник") && !fundingType.equals("бюджетник")) {
            throw new IllegalArgumentException("Форма обучения должна быть 'платник' или 'бюджетник'");
        }

        this.fundingType = fundingType;
    }

    public Integer getFailedExams() {
        return failedExams;
    }

    public void setFailedExams(Integer failedExams) {
        if (failedExams == null || failedExams < 0 || failedExams > MAX_FAILED_EXAMS) {
            throw new IllegalArgumentException(
                    "Количество несданных экзаменов должно быть от 0 до " + MAX_FAILED_EXAMS);
        }

        this.failedExams = failedExams;
    }
}