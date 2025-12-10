package client;

import app.controller.ClaimController;
import app.controller.CustomerController;
import app.controller.PolicyController;


import java.util.Scanner;

public class Client {
    private static PolicyController policyController;
    private static CustomerController customerController;
    private static ClaimController claimController;
    private static Scanner scanner;

    public static void main(String[] args) {

        try {
            // Создаём объекты контроллеров для взаимодействия с приложением
            policyController = new PolicyController();
            customerController = new CustomerController();
            claimController = new ClaimController();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println();
            System.out.println("==== СТРАХОВОЙ СЕРВИС ====");
            System.out.println("1 - операции с полисами");
            System.out.println("2 - операции с клиентами");
            System.out.println("3 - операции с заявками по страховому случаю");
            System.out.println("0 - выход");
            System.out.print("Ваш выбор: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    policyOperations();
                    break;
                case "2":
                    customerOperations();
                    break;
                case "3":
                    claimOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректный ввод!");
                    break;
            }
        }
    }

    public static void claimOperations() {
        while (true) {
            try {
                System.out.println();
                System.out.println("=== ОПЕРАЦИИ С ЗАЯВКАМИ ===");
                System.out.println("1 - создать заявку");
                System.out.println("2 - показать все активные заявки");
                System.out.println("3 - показать заявку по идентификатору");
                System.out.println("4 - обновить покрытие заявки");
                System.out.println("5 - обновить решение заявки");
                System.out.println("6 - удалить заявку по идентификатору");
                System.out.println("7 - восстановить заявку по идентификатору");
                System.out.println("0 - выход в главное меню");
                System.out.print("Ваш выбор: ");

                String input = scanner.nextLine();
                String id;
                String type;
                String customerId;
                String coverage;
                String decision;
                switch (input) {
                    case "1":
                        System.out.println("Введите тип заявки (AUTO/HEALTH/PROPERTY):");
                        type = scanner.nextLine();
                        System.out.println("Введите идентификатор клиента:");
                        customerId = scanner.nextLine();
                        System.out.println("Введите сумму покрытия:");
                        coverage = scanner.nextLine();
                        System.out.println("Создана заявка: " +
                                claimController.save(type, customerId, coverage));
                        break;
                    case "2":
                        claimController.getAll().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор заявки:");
                        id = scanner.nextLine();
                        System.out.println(claimController.getById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор заявки:");
                        id = scanner.nextLine();
                        System.out.println("Введите новую сумму покрытия:");
                        coverage = scanner.nextLine();
                        claimController.update(id, coverage);
                        System.out.println("Сумма покрытия успешно обновлена.");
                        break;
                    case "5":
                        System.out.println("Введите идентификатор заявки:");
                        id = scanner.nextLine();
                        System.out.println("Введите решение заявки (true/false):");
                        decision = scanner.nextLine();
                        claimController.updateDecision(id, decision);
                        System.out.println("Решение заявки успешно обновлено.");
                        break;
                    case "6":
                        System.out.println("Введите идентификатор заявки:");
                        id = scanner.nextLine();
                        claimController.deleteById(id);
                        System.out.println("Заявка успешно удалена.");
                    case "7":
                        System.out.println("Введите идентификатор заявки:");
                        id = scanner.nextLine();
                        claimController.restoreById(id);
                        System.out.println("Заявка успешно восстановлена.");
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод! Попробуйте еще раз.");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void policyOperations() {
        while (true) {
            try {
                System.out.println();
                System.out.println("=== ОПЕРАЦИИ С ПОЛИСАМИ ===");
                System.out.println("1 - сохранить полис");
                System.out.println("2 - показать все активные полисы");
                System.out.println("3 - показать полис по идентификатору");
                System.out.println("4 - изменить страховую сумму полиса");
                System.out.println("5 - деактивировать полис по идентификатору (soft delete)");
                System.out.println("6 - деактивировать полисы по номеру (soft delete)");
                System.out.println("7 - восстановить полис по идентификатору");
                System.out.println("8 - показать количество активных полисов");
                System.out.println("9 - показать суммарную страховую сумму активных полисов");
                System.out.println("10 - показать среднюю страховую сумму активных полисов");
                System.out.println("0 - выход в главное меню");
                System.out.print("Ваш выбор: ");

                String input = scanner.nextLine();
                String id;
                String number;
                String type;
                String coverage;

                switch (input) {
                    case "1":
                        System.out.println("Введите номер полиса");
                        number = scanner.nextLine();
                        System.out.println("Введите тип полиса (AUTO/HEALTH/PROPERTY):");
                        type = scanner.nextLine();
                        System.out.println("Введите страховую сумму:");
                        coverage = scanner.nextLine();
                        System.out.println("Создан полис: " + policyController.save(number, type, coverage));
                        break;
                    case "2":
                        policyController.getAllActivePolicies().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор полиса");
                        id = scanner.nextLine();
                        System.out.println(policyController.getPolicyById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор полиса");
                        id = scanner.nextLine();
                        System.out.println("Введите новую страховую сумму");
                        coverage = scanner.nextLine();
                        policyController.updateCoverageSum(id, coverage);
                        System.out.println("Страховая сумма полиса успешно обновлена");
                        break;
                    case "5":
                        System.out.println("Введите идентификатор полиса");
                        id = scanner.nextLine();
                        policyController.deleteById(id);
                        System.out.println("Полис успешно удален");
                        break;
                    case "6":
                        System.out.println("Введите номер полиса");
                        number = scanner.nextLine();
                        policyController.deleteByNumber(number);
                        System.out.println("Если полис с номером \"" + number + "\" был активен — он деактивирован.");
                        System.out.println("Если активных полисов с таким номером не было — ничего не изменилось.");
                        break;
                    case "7":
                        System.out.println("Введите идентификатор полиса");
                        id = scanner.nextLine();
                        policyController.restoreById(id);
                        System.out.println("Полис успешно восстановлен");
                        break;
                    case "8":
                        System.out.println("Количество активных полисов - " + policyController.getAllActivePoliciesCount());
                        break;
                    case "9":
                        System.out.println("Суммарная страховая сумма всех активных полисов - " +
                                policyController.getAllActivePoliciesTotalCoverage());
                        break;
                    case "10":
                        System.out.println("Средняя страховая сумма активных полисов - " +
                                policyController.getAllActivePoliciesAverageCoverage());
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод! Попробуйте еще раз.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: ожидалось число. " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }


    public static void customerOperations() {
        while (true) {
            try {
                System.out.println();
                System.out.println("=== ОПЕРАЦИИ С КЛИЕНТАМИ ===");
                System.out.println("1 - сохранить клиента");
                System.out.println("2 - показать всех активных клиентов");
                System.out.println("3 - показать клиента по идентификатору");
                System.out.println("4 - изменить имя клиента");
                System.out.println("5 - изменить рейтинг клиента");
                System.out.println("6 - деактивировать клиента по идентификатору (soft delete)");
                System.out.println("7 - деактивировать клиентов по имени (soft delete)");
                System.out.println("8 - восстановить клиента по идентификатору");
                System.out.println("9 - показать количество активных клиентов");
                System.out.println("10 - показать суммарную страховую сумму по полисам клиента");
                System.out.println("11 - показать среднюю страховую сумму по полисам клиента");
                System.out.println("12 - добавить полис клиенту");
                System.out.println("13 - удалить полис у клиента");
                System.out.println("14 - очистить все полисы клиента");
                System.out.println("0 - выход в главное меню");
                System.out.print("Ваш выбор: ");

                String input = scanner.nextLine();
                String id;
                String name;
                String rating;
                String customerId;
                String policyId;

                switch (input) {
                    case "1":
                        System.out.println("Введите имя клиента");
                        name = scanner.nextLine();
                        System.out.println(customerController.save(name));
                        System.out.println("Создан клиент: " +
                                customerController.save(name));
                        break;
                    case "2":
                        customerController.getAllActiveCustomers().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор клиента");
                        id = scanner.nextLine();
                        System.out.println(customerController.findById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор клиента");
                        id = scanner.nextLine();
                        System.out.println("Введите новое имя клиента");
                        name = scanner.nextLine();
                        customerController.update(id, name);
                        System.out.println("Имя клиента успешно изменено.");
                        break;
                    case "5":
                        System.out.println("Введите идентификатор клиента");
                        id = scanner.nextLine();
                        System.out.print("Введите новый рейтинг клиента: ");
                        rating = scanner.nextLine();
                        customerController.updateRating(id, rating);
                        System.out.println("Рейтинг клиента успешно изменён.");
                        break;
                    case "6":
                        System.out.println("Введите идентификатор клиента");
                        id = scanner.nextLine();
                        customerController.deleteById(id);
                        System.out.println("Клиент с идентификатором" + id + "успешно деактивирован");
                        break;
                    case "7":
                        System.out.println("Введите имя клиента");
                        name = scanner.nextLine();
                        customerController.deleteCustomerByName(name);
                        System.out.println("Клиент с именем" + name + "успешно деактивирован");
                        break;
                    case "8":
                        System.out.println("Введите идентификатор клиента");
                        id = scanner.nextLine();
                        customerController.restoreCustomerById(id);
                        System.out.println("Клиент с идентификатором" + id + "успешно восстановлен");
                        break;
                    case "9":
                        System.out.println("Количество активных клиентов - " + customerController.getActiveCustomersCount());
                    case "10":
                        System.out.println("Введите идентификатор клиента");
                        id = scanner.nextLine();
                        System.out.println("Суммарная страховая сумма по полисам клиента - " +
                                customerController.getCustomerTotalCoverage(id));
                        break;
                    case "11":
                        System.out.println("Введите идентификатор покупателя");
                        id = scanner.nextLine();
                        System.out.println("Средняя страховая сумма по полисам клиента - " +
                                customerController.getCustomerAverageCoverage(id));
                        break;
                    case "12":
                        System.out.println("Введите идентификатор клиента");
                        customerId = scanner.nextLine();
                        System.out.println("Введите идентификатор полиса");
                        policyId = scanner.nextLine();
                        customerController.addPolicyToCustomer(customerId, policyId);
                        System.out.println("Полис привязан к клиенту.");
                        break;
                    case "13":
                        System.out.println("Введите идентификатор клиента");
                        customerId = scanner.nextLine();
                        System.out.println("Введите идентификатор полиса");
                        policyId = scanner.nextLine();
                        customerController.removePolicyFromCustomer(customerId, policyId);
                        System.out.println("Полис удален.");
                        break;
                    case "14":
                        System.out.print("Введите идентификатор клиента: ");
                        id = scanner.nextLine();
                        customerController.clearCustomerPolicies(id);
                        System.out.println("Все полисы клиента удалены.");
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод. Попробуйте ещё раз.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: ожидалось число. " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
