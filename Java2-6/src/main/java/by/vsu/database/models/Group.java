package by.vsu.database.models;

public class Group {
    public static final Integer MAX_AMOUNT_STUDENTS_PART_TIME = 30;
    public static final Integer MAX_AMOUNT_STUDENTS_FULL_TIME = 20;
    public static final Integer MAX_COURSE = 6;
    public static final Integer MAX_NAME_LENGTH = 50;
    public static final Integer MAX_SPECIALIZATION_LENGTH = 255;

    private Integer id;
    private String name;
    private Integer amount_students;
    private String specialization;
    private Integer course;
    private String studyForm;

    public Group(Integer id, String name, Integer amount_students, String specialization, Integer course,
            String studyForm) {
        setId(id);
        setName(name);
        setStudyForm(studyForm);
        setAmountStudents(amount_students);
        setSpecialization(specialization);
        setCourse(course);
    }

    public Group(String name, Integer amount_students, String specialization, Integer course, String studyForm) {
        setName(name);
        setStudyForm(studyForm);
        setAmountStudents(amount_students);
        setSpecialization(specialization);
        setCourse(course);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название группы не может быть пустым!");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Максимальная длина названия группы: " + MAX_NAME_LENGTH);
        }

        this.name = name;
    }

    public Integer getAmountStudents() {
        return amount_students;
    }

    public void setAmountStudents(Integer amount_students) {
        if (amount_students == null || amount_students < 0) {
            throw new IllegalArgumentException("Количество студентов не может быть отрицательным!");
        }

        if (studyForm.equals("очная") && amount_students > MAX_AMOUNT_STUDENTS_FULL_TIME) {

            throw new IllegalArgumentException(
                    "На очной форме может быть максимум " + MAX_AMOUNT_STUDENTS_FULL_TIME + " студентов!");
        }

        if (studyForm.equals("заочная") && amount_students > MAX_AMOUNT_STUDENTS_PART_TIME) {

            throw new IllegalArgumentException(
                    "На заочной форме может быть максимум " + MAX_AMOUNT_STUDENTS_PART_TIME + " студентов!");
        }

        this.amount_students = amount_students;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Специальность не может быть пустой!");
        }

        if (specialization.length() > MAX_SPECIALIZATION_LENGTH) {
            throw new IllegalArgumentException("Максимальная длина специальности: " + MAX_SPECIALIZATION_LENGTH);
        }

        this.specialization = specialization;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        if (course == null || course < 1 || course > MAX_COURSE) {
            throw new IllegalArgumentException("Курс должен быть от 1 до " + MAX_COURSE);
        }

        this.course = course;
    }

    public String getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(String studyForm) {
        if (studyForm == null || studyForm.isBlank()) {
            throw new IllegalArgumentException("Форма обучения не может быть пустой!");
        }

        if (!studyForm.equals("очная") && !studyForm.equals("заочная")) {
            throw new IllegalArgumentException("Форма должна быть 'очная' или 'заочная'");
        }

        this.studyForm = studyForm;
    }
}