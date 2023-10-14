package src.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Input {

    private static Input instance;

    private Scanner scanner;

    public static Input getInstance() {
        if (instance == null) {
            synchronized (Input.class) {
                if (instance == null) {
                    instance = new Input();
                }
            }
        }

        return instance;
    }

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public Integer getInt(String prompt, int min, int max, boolean validate) throws Exception {
        Integer value;
        while (true) {
            try {
                System.out.print(prompt);
                value = Integer.parseInt(scanner.nextLine().trim());

                if (value < min || value > max) {
                    throw new Exception("value is invalid");
                }

                break; // Input is valid, exit the loop
            } catch (NumberFormatException e) {
                if (!validate) {
                    value = null;
                    break;
                }
                throw new Exception("Invalid input. Please enter a valid integer.");
            } catch (Exception e) {
                throw e;
            }
        }
        return value;
    }

    public Double getDouble(String prompt, int min, int max, boolean validate) throws Exception {
        Double value;
        while (true) {
            try {
                System.out.print(prompt);
                value = Double.parseDouble(scanner.nextLine().trim());

                if (value < min || value > max) {
                    throw new Exception("value is invalid");
                }

                break; // Input is valid, exit the loop
            } catch (NumberFormatException e) {
                if (!validate) {
                    value = null;
                    break;
                }
                throw new Exception("Invalid input. Please enter a valid double.");
            } catch (Exception e) {
                throw e;
            }
        }
        return value;
    }

    public String getString(String prompt, int min, int max, boolean validate) throws Exception {
        String value;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextLine().trim();
    
                if (value.isBlank() || value.length() < min || value.length() > max) {
                    throw new Exception("value is invalid");
                }

                break;
            } catch (Exception e) {
                if (!validate) {
                    value = null;
                    break;
                }
                throw e;
            }
        }

        return value;
    }

    public LocalDate getDate(String prompt, LocalDate min, LocalDate max, String pattern, boolean validate) throws Exception {
        LocalDate result;
        while (true) {
            try {
                System.out.print(prompt + "(" + pattern + "): ");
                String value = scanner.nextLine().trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

                result = LocalDate.parse(value, formatter);
    
                if (result.isAfter(min) || result.isBefore(max)) {
                    throw new Exception("value is invalid");
                }

                break;
            } catch (Exception e) {
                if (!validate) {
                    result = null;
                    break;
                }
                throw e;
            }
        }

        return result;
    }

    public void closeScanner() {
        this.scanner.close();
    }
}
