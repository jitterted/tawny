## To Do

[ ] Make Portfolio the Aggregate Root 

   [ ] Replace new Position() with portfolio.openPosition()

   [X] Move "roll" method over to Portfolio, so it can keep track of the new position as well

   [X] Move factory creation method of Position to Portfolio

[ ] For view: show underlying transactions leading to the current cost of a rolled position 

[ ] Real persistence!

[ ] Show gain(loss) based on the current price

[ ] Line up/right-align numbers on the view page (and use the number font as well)

[ ] Close position from UI: needs link with ID, controller handler, etc.

[ ] Note that after rolling, we'll see the original position in the view, 
    whereas it really needs to be a Composite structure, i.e., tree-like,
    so it can be expanded when I want to see the details, but collapsed by default 

[ ] Start trying to use https://github.com/xmolecules/jmolecules

### Features

[X] Close Position - domain-level is done

[X] Close+Open Position: new position is linked to old position, 
    cost is calculated based on difference

[ ] Tracking rolled positions:

   [ ] simple (single) position 
   
       1. STO (sell to open) AMD 2021-01-15 75 Call @ 8.50
       2. Roll:
           BTC (buy to close) AMD 2021-01-15 75 Call @ 17.21
           STO AMD 2021-03-19 80 Call @ 16.98
       8.50 + (16.98 - 17.21) = 8.50 + (-0.23) = 8.27

        Transactions:
            STO AMD-C-75-jan15 @ -  8.50  \__ must be the same
            BTC AMD-C-75-jan15 @ + 17.21  /
            STO AMD-C-80-mar19 @ - 16.98  \
            BTC AMD-C-80-mar19 @ + 15.23  /
            STO AMD-C-85-jun16 @ - 15.35  \
       
       "BDD" (UI) Level description:
           1. Select position to roll (must be OPEN)
           2. Enter BTC cost (we know the contract info)
           3. Enter new Open position (we know the underlying symbol and contract type)
       
       Roll does:
       1. Close original position - requires closing price/cost
       2. Create OpenPosition with Link to Closed position
   
   [ ] multi-legged positions

[ ] Handle grouping of positions into single position (composite)

[ ] Buy/Sell for Open Position (enum)

[ ] Calculate Gain $/%

### Refactorings

[ ] Replace LocalDate for expiration with a Value Object called Expiration

[ ] Replace Call/Put option type with an enum

[ ] Add ArchUnit to enforce proper separation/dependencies for Hexagonal Architecture

## Completed

[X] Integration Test (mockmvc) to check for the endpoint 
[X] Test the view method in the controller for the data in the model
[X] Create Thymeleaf template to display the view
[X] Button to take us to Open Position page/form
[X] POST handler for open position accepting form input
    [X] PostMapping
    [X] Store received form input
    [X] View needs to use stored information
[X] Form for input of Open Position

[X] Add ID to Position
[X] Provide findByPositionId() method in Portfolio for use by the PortfolioController

[X] Add ID for each position to view page as part of the button to roll link

[X] The roll position GET mapped method will take in that ID to use for the roll 


