# CliRental User Guide

## Introduction

Clirental is a CLI-based application that allows car rental companies to track their customers, cars, and rental transactions.

Summary of Contents:
- [Quick start](#quick-start)
- [File Saving](#file-saving)
- [Features](#features)
  - [Adding a user: `add-user`](#adding-a-user-to-the-database-add-user)
  - [Adding a car: `add-car`](#adding-a-car-add-car)
  - [Removing a car: `remove-car`](#removing-a-car-remove-car)
  - [Listing all cars: `list-cars`](#listing-all-cars-list-cars)
  - [Listing all rented-out cars: `list-rented`](#listing-all-rented-out-cars-list-rented)
  - [Listing all available cars: `list-available`](#listing-all-available-cars-list-available)
  - [Updating rental status of cars](#updating-rental-status-of-car)
  - [Adding a transaction: `add-tx`](#adding-a-transaction-add-tx)
  - [Removing a transaction: `remove-tx`](#removing-a-transaction-remove-tx)
  - [Removing all transactions: `remove-all-txs`](#removing-all-transactions-remove-all-txs)
  - [Listing all transactions: `list-txs`](#listing-all-transactions-list-txs)
  - [Marking a transaction as complete: `mark-tx`](#marking-a-transaction-as-complete-mark-tx)
  - [Unmarking a transaction as incomplete: `unmark-tx`](#unmarking-a-transaction-as-incomplete-unmark-tx)
  - [Displaying the help page: `help`](#displaying-the-help-page-help)
  - [Exiting the program: `exit`](#exiting-the-program-exit)
- [FAQ](#faq)
- [Command Summary](#command-summary)


## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `CliRental` from [here](https://github.com/AY2425S1-CS2113-T11-3/tp/releases).
3. Copy the jar file into an empty folder.
4. Open your terminal and navigate to the folder the jar file is placed in.
5. Run java -jar tp.jar and you can start using right away.

## Features

---
### File Saving

`Customer`, `Transaction`, and `Car` data will be saved in their respective files under the `data` directory.


#### `IMPORTANT NOTE / DISCLAIMER: `

* The file saving feature does not include the functionality of being able to add/edit data by editing the text files. 
Please add/edit data via the command line using the commands given.
* Users must ensure any additional data follows the correct format.

`If user do not follow instructions, following additional measures are placed.`
* Corrupted data will be flagged upon start of program , highlighting the rows of data which are wrong. Please correct 
them or the corrupted lines will be flushed from the data files upon the `first correct command` given by the user.


**Filenames:**

* `Car data`: `carData.txt`
* `Customer data`: `customerData.txt`
* `Transaction data`: `transactionData.txt`

Format : 
* `Car data` :  `CAR MODEL | LICENSE PLATE | PRICE | RENTED | EXPENSIVE`
* `Customer data` : `NAME | AGE | PHONE NUMBER`
* `Transaction data` : `TRANSACTION ID | LICENSE PLATE | CUSTOMER NAME | RENTAL DURATION(DAYS) | 
RENTAL START DATE | COMPLETED`

Types :
* `Car data` :  `STRING | STRING | DOUBLE | BOOLEAN | BOOLEAN`
* `Customer data` : `STRING | INT | STRING`
* `Transaction data` : `STRING | STRING | STRING | INT | LOCALDATE | BOOLEAN`

**Example:**

* `Car data` :  `Toyota Corolla | SGM4932K | 120.0 | false | false`
* `Customer data` : `John | 22 | +6590907638`
* `Transaction data` : `TX1 | SGM4932K | John | 30 | 17-10-2024 | false`

Others : 

* Any string `not "true"` will be treated as `false` when it is placed in a BOOLEAN section.

---
## Features

### Adding a User to the Database: `add-user`

Adds a customer to the list of customers tracked by the car rental application.

**Format:** `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]`

* `CUSTOMER_NAME` : `STRING`.
* `AGE` : `INT`
  * age should be > 17 and <= 100.
* `CONTACT_NUMBER` : `STRING`
  * `[8 DIGITS AND START WITH 8 or 9]`
  * `No space between + and the first digit`
  * `No space between subsequent digits as well`
  * `E.g. +6595382572`
* `/u` , `/a` , `/c` must be in sequence.

**Example of usage:**
`add-user /u John /a 18 /c 95382572`

**Sample Response:**
```
____________________________________________________________
add-user /u John /a 18 /c +6595382572
Customer added
Customer name : John
Age : 18
Contact Number :+6595382572
____________________________________________________________
What would you like to do?
____________________________________________________________
```
---
### Adding a Car: `add-car`

Adds a car to the car list.

**Format:** `add-car /n [CAR_MODEL] /c [LICENSE_PLATE_NUMBER] /p [PRICE]`

- `/n`, `/c` and `/p` identifiers **must be** in the correct order.
- `LICENSE_PLATE_NUMBER` **must be** in the following format: `SXX####X`, where
    - `X` is any letter from **A to Z**.
    - `####` is any number from **1 to 9999**.
    - Starts with the letter **S**.
- `PRICE` must be a **non-negative, numeric value**.
- `PRICE` cannot exceed **10000**.
- `$` character not required for `PRICE`.

**Example:** 
`add-car /n Honda Civic /c SGE1234X /p 10000`

**Sample output:**
``` 
Car added to list
Car details:
Honda Civic | SGE1234X | $10000.00 | Available | Affordable | Median price: 10000.0
```
---
### Removing a Car: `remove-car`

Removes a car from the fleet based on the car's unique ID.

**Format:** `remove-car /i [LICENSE_PLATE_NUMBER]`

- `/i` identifier specifies the car ID to be removed.
- `LICENSE_PLATE_NUMBER` must match an existing car in the database.

**Example:** 
`remove-car /i SGE1234X`

**Sample output:**
```
Car with license plate SGE1234X removed from list.
```

If the `LICENSE_PLATE_NUMBER` is not found:
```
No car found with license plate [SGE1234X]
```
---

### Listing All Cars: `list-cars`

Lists all the cars owned by the company.

**Format:** `list-cars`

**Sample Output:**
```
Here are the current cars in the company:
1) civic | SGE1234X | $1000.00 | Available | Affordable | Median price: 1000.0
2) vios | SKP890C | $2000.00 | Available | Expensive | Median price: 1000.0
```
If the list is **empty**:

``` 
Oops!! Car list is empty...
Use command <add-car> to add a new car.
```
---
### Listing All Rented Out Cars: `list-rented`

Lists all the cars that are currently rented out.

**Format:** `list-rented`

**Sample output:**
```
Here are all the rented-out cars:
1) Honda Civic | SGE1234X | $100.00
2) Toyota Camry | SKL4567M | $200.00
3) Nissan Latio | SFT1190A | $300.00
```

If the list is **empty**:
```
No cars currently rented out...
```
---
### Listing All Available Cars: `list-available`

Lists all available cars in the company.

**Format:** `list-available`

**Sample output:**
```
Here are all the available cars:
1) Mitsubishi Attrage | SGP7877N | $1500.00
2) Honda Vezel | SLK9945F | $3400.00
```

If the list is **empty**:
```
There are no available cars at the moment...
```
---
### Updating Rental Status of Car

There is no need to manually update the rental status of a car. 
The status will be updated automatically when a transaction record is:

- Added
- Removed
- Marked as completed
- Marked as not completed

### Adding a Transaction: `add-tx`

Adds a new rental transaction to the system.

**Format:** `add-tx /c [LICENSE_PLATE_NUMBER] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE: dd-MM-yyyy]`

**Example:**  
`add-tx /c SZZ1579D /u John /d 15 /s 11-05-2025`

**Sample Response:**
```
Transaction added:
[ ] TX2 | SZZ1579D | John | 15day(s)
Start Date: 11-05-2025
____________________________________________________________
```
---
### Removing a Transaction: `remove-tx`

Removes a specific rental transaction from the system based on the transaction ID.

**Format:** `remove-tx /t [TRANSACTION_ID]`

- `/t` identifier specifies the transaction ID to be removed.
- `TRANSACTION_ID` must match an existing transaction in the system.

**Example:**  
`remove-tx /t TX1`

**Sample output:**

```
Transaction deleted:
[ ] TX1 | SGE1234X | john | 4day(s)
Start Date: 11-12-2024
____________________________________________________________
```


If the `TRANSACTION_ID` is not found:
```
Transaction not found
```
---

### Removing All Transactions: `remove-all-txs`

Removes all transactions from the system.

**Format:** `remove-all-txs`

### Listing All Transactions: `list-txs`

Displays all transactions stored in the system.

**Format:** `list-txs`

**Sample output:**
```
Here are all the transactions:
1) [ ] TX1 | SGE1234X | john | 4day(s)
   Start Date: 11-12-2024
2) [ ] TX2 | SKL4567M | thomas | 6day(s)
   Start Date: 11-12-2024
3) [ ] TX3 | SFT1190A | matthew | 8day(s)
   Start Date: 11-12-2024
```

If the list is **empty**:
```
No transaction available.
```

---
### Marking a Transaction as Complete: `mark-tx`

Marks a rental transaction as completed, indicating that the transaction is finalized.

**Format:** `mark-tx /t [TRANSACTION_ID]`

- `/t` identifier specifies the transaction ID to be marked as completed.
- `TRANSACTION_ID` must match an existing transaction in the system.

**Example:**  
`mark-tx /t TX1`

**Sample output:**
```
Transaction marked as complete:
[X] TX1 | SGE1234X | john | 4day(s)
Start Date: 11-12-2024
____________________________________________________________
```

If the `TRANSACTION_ID` is not found:
```
Transaction with ID TX1 not found.
```
---
### Unmarking a Transaction as Incomplete: `unmark-tx`

Unmarks a rental transaction, indicating it is not yet completed.

**Format:** `unmark-tx /t [TRANSACTION_ID]`

- `/t` identifier specifies the transaction ID to be unmarked.
- `TRANSACTION_ID` must match an existing transaction in the system.

**Example:**  
`unmark-tx /t TX1`

**Sample output:**
```
Transaction marked as incomplete:
[ ] TX1 | SGE1234X | john | 4day(s)
Start Date: 11-12-2024
____________________________________________________________
```

If the `TRANSACTION_ID` is not found:
```
Transaction with ID TX1 not found.
```

### Displaying the help page: `help`

Displays a help page containing all the commands, together with
its respective format and description. 

Format: `help`

### Exiting the program: `exit`

Exits the program and saves all data to the respective data text files.

Format: `exit`

---
## FAQ

`No questions to answer for now!!!`

# Command Summary

**`Customer` related commands:**

|          Action           | Format                                                     |
|:-------------------------:|------------------------------------------------------------|
|     **Add** customer      | `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]` |
|    **Remove** customer    | `remove-user /u [CUSTOMER_NAME]`                           |
| **Remove all** customers  | `remove-all-users`                                         |
|  **List all** customers   | `list-users`                                               |

**`Car` related commands:**

|         Action          | Format                                                         |
|:-----------------------:|----------------------------------------------------------------|
|       **Add** car       | `add-car /n [CAR_MODEL] /c [LICENSE_PLATE_NUMBER] /p [PRICE]`  |
|     **Remove** car      | `remove-car /i [LICENSE_PLATE_NUMBER]`                         |
|   **Remove all** cars   | `remove-all-cars`                                              |
|    **List all** cars    | `list-cars`                                                    |
|  **List rented** cars   | `list-rented`                                                  |
| **List available** cars | `list-available`                                               |

**`Transaction` related commands:**

|                 Action                  | Format                                                                                          |
|:---------------------------------------:|-------------------------------------------------------------------------------------------------|
|           **Add** transaction           | `add-tx /c [LICENSE_PLATE_NUMBER] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE: dd-MM-yyyy]` |
|         **Remove** transaction          | `remove-tx /t [TRANSACTION_ID]`                                                                 |
|       **Remove all** transactions       | `remove-all-txs`                                                                                |
|        **List all** transactions        | `list-txs`                                                                                      |
|  **Mark** transactions as **complete**  | `mark-txs /t [TRANSACTION_ID]`                                                                  |
| **Mark** transactions as **incomplete** | `unmark-txs /t [TRANSACTION_ID]`                                                                |
|     **List completed** transactions     | `list-txs-completed`                                                                            |
|    **List uncompleted** transactions    | `list-txs-uncompleted`                                                                          |
|  **Find** transactions **by customer**  | `find-txs-by-customer /u [CUSTOMER_NAME]`                                                       |

**Other useful commands:**

|       Action       | Format |
|:------------------:|--------|
| Show **help** page | `help` |
|  **Exit** program  | `exit` |
