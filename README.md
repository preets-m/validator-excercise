# validator-excercise

Acceptance Criteria
Invalid Amount
GIVEN any Frequency
WHEN a non-numeric or blank Amount is provided
THEN no validation error


Null Frequency
GIVEN any Amount
WHEN a null Frequency is provided
THEN no validation error


Weekly
GIVEN a WEEK Frequency
WHEN any Amount is provided
THEN no validation error


Monthly
GIVEN a MONTH Frequency
WHEN any Amount is provided
THEN no validation error
Validated as valid

GIVEN a Frequency is in the set TWO_WEEK, FOUR_WEEK, QUARTER, YEAR
AND an associated Number of Weeks is 2, 4, 13, 52 respectively
WHEN a Amount that divides by the Number of Weeks to a whole number of pence is provided
THEN no validation error
Comments by Preeti - I had confusion in this test still so added set of frequncy in test and for few it passed and for few it failed
Comments by preeti - Doubt was in amount to be considered till how many digits, i have considered as 2 decimal point


Validated as invalid
GIVEN a Frequency is in the set TWO_WEEK, FOUR_WEEK, QUARTER, YEAR
AND an associated Number of Weeks is 2, 4, 13, 52 respectively
WHEN a Amount that does not divide by the Number of Weeks to a whole number of pence is provided
THEN a validation error is produced
Comments by Preeti - I had confusion in this test still so added set of frequncy in test and for few it passed and for few it failed
Comments by preeti - Doubt was in amount to be considered till how many digits, i have considered as 2 decimal point
