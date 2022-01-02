# *Abhi's Project - Trading Log*

### Trading Background

I am very passionate about trading. I have been studying the financial markets for a few years and have been
through many ups and downs. I have learned a lot about myself through the hard work it requires to be a
consistent and profitable trader.

### Project Information

This project will be a Java GUI where I can log the details of my trades as an entry. I can then calculate basic
metrics using my program. This includes calculating winrate and overall profit/loss. I can also view a summary
of my trades that I can use for reflection. Perhaps in the future I would like to implement more features so that
I can filter my trades based off the instrument I was trading, what session I was trading, how long my best trades
lasted, which days should I avoid based off my trading history, etc.

### User Stories

- As a user, I want to be able to add a trade to my log of trades
- As a user, I want to be able to view a summary of my trades
- As a user, I want to see my overall win-rate
- As a user, I want to see my overall profit
- As a user, I want to be able to save my log of trades to file
- As a user, I want to be able to be able to load my log of trades from file

### Phase 4: Task 2

Below I paste the sample of what Event and EventLog print to console. In the preview it does not show
them in separate lines but in the editor you can see that each statement is printed to a new line.

Tue Nov 23 16:28:24 PST 2021
Trade on EU added to TradesList.
Tue Nov 23 16:28:24 PST 2021
Trade on GU added to TradesList.
Tue Nov 23 16:28:24 PST 2021
Trade on 30Year added to TradesList.
Tue Nov 23 16:28:24 PST 2021
Trade on EU added to TradesList.
Tue Nov 23 16:28:26 PST 2021
Profit of TradesList was calculated.
Tue Nov 23 16:28:26 PST 2021
Profit of TradesList was calculated.
Tue Nov 23 16:28:29 PST 2021
Win-rate of TradesList was calculated.
Tue Nov 23 16:28:29 PST 2021
Win-rate of TradesList was calculated.
Tue Nov 23 16:28:31 PST 2021
Summary of TradesList was made.
Tue Nov 23 16:28:31 PST 2021
Summary of TradesList was made.
Tue Nov 23 16:28:31 PST 2021
Summary of TradesList was made.
Tue Nov 23 16:28:31 PST 2021
Summary of TradesList was made.

Process finished with exit code 1

### Phase 4: Task 3

- I would probably put the components of InputFields, ButtonPanel, and Title classes in MainFrame. I didn't think it
was necessary to split them up, and it would probably make it easier to navigate through the project if it was just
the Main class along with the Frame classes.
- There was lots of similarities between CalculateProfitFrame and SeeWinrateFrame. I could have made a general
abstract class for them and extended these two classes to that class.