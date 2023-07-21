# WealthNavigator-server
Wealth Navigator is an expense tracker and budgeting application. This repository holds server code.

Main components of the application are:
* [Monthly expense planner](#monthly-expense-planner)
* [Expense tracker](#expense-tracker)
* [Budget tracker](#budget-tracker)
* [Reports](#reports)

## Log-in
The user is required to be registered before he/she can access the account. (Registration is a manual process as of now. There isn't any UI page to register yourself. If you want to get access to the system, reach out to the developer directly.)

The log-in process is straightforward. The user enters an email address and password and gets into the system.


## Monthly expense planner
A monthly expense planner generates expenses that need to be paid every month. eg, mortgage, phone bill, internet bill, utilities, etc.

This is helpful to keep track of all payments that need to be made and the user never misses a single payment.

This expense list contains both manual and automated payments. Automated payments are also tracked to make sure funds are available when the amount will be deducted from your account and thus avoid any low balance penalty.

The user will be notified inside the application to validate the expense transaction and take required action like moving funds, making sure enough funds are available for the transaction.

The following information is required while creating any recurring expense transactions:

| Field                                                                                                                                                                                                                                                                                                                                                          | Description                                                                                                                    | Example       |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|---------------|
| Expense description                                                                                                                                                                                                                                                                                                                                            | Expense Description                                                                                                            | Internet Bill |
| Recurring payment Frequency
|   <ul>
|       <li>Weekly (Provide : day of the week (eg, Mondays))</li>
|       <li>Bi-weekly (Provide: start date (eg, 01/12/2023))</li>
|       <li>Semi-monthly (Usually on 15th and last days of the month)</li>
|       <li>Monthly (Provide: day of the month (eg, 5th))</li>
|       <li>Quarterly (Provide: start date (eg, 01/12/2023))</li>
|       <li>yearly (Provide: exact date (eg, 06/06/2023))</li>
|   </ul>
| This field describes how often this expense need to be paid                                                                    |               |
| Is transaction allowed on Weekend?                                                                                                                                                                                                                                                                                                                             | This flag is used to indicate if the transaction is allowed on weekend                                                         | True/False    |
| If the transaction is on the weekend does it need to be paid before weekend?                                                                                                                                                                                                                                                                                   | This flag is used to decide if the transaction is not allowed on weekend then should we pay it before weekend or after weekend | True/False    |
| Amount                                                                                                                                                                                                                                                                                                                                                         | If amount is fixed for the expense provide else keep it as '0'                                                                 | $123.45        |
| Account                                                                                                                                                                                                                                                                                                                                                        | From which account the payment is going to be deducted                                                                         | TD            |
| Is expense enable ?                                                                                                                                                                                                                                                                                                                                                    | Flag to indicate if the expense is enabled or disabled. Useful when you want to temporarily stop the expense                   | True/False    |

## Expense tracker

> **TODO:** Details need to be added

## Budget tracker

> **TODO:** Details need to be added

## Reports

> **TODO:** Details need to be added