# Building Tawny with the Testable Architecture

## Stock and Options Portfolio Tracker

1. "Table stakes": track purchases and sales of equities

    a. Stock Purchase info:
        symbol (AAPL)
        date/time (2020-09-10 11:33AM)
        qty of shares
        purchase price (cost basis)
        --> current value, (virtual) gain/loss
    b. Stock Sale info:

2. Track purchase/sale of options ("intermediate")

   Opening a Position Examples:

    a. Call option purchase:
        Symbol: AAPL
        Position: BTO
        Type: Call (vs. Put)
        Option Expiration Date: Oct 16 2020
        Strike Price: 125
        Cost: 5
        Qty: 1
        Date/Time
        --> cost basis: qty * cost * 100
        --> break even: 125 + 5 = 130

        Scenarios
        ---------

        Open Position: Buy Call Option

        Given a portfolio with
            BTO 1 AAPL 2020-10-16 125 C @ 6.40 on 2020-09-15-15-50
        When I click view portfolio
        Then we can see:

        Symbol | C/P | Qty | Expiration | Strike | Cost Each | Total Cost | Current Price | Value  | Gain/Loss
        -------+-----+-----+------------+--------+-----------+------------+---------------+--------+----------
        AAPL   |  C  |   1 | Oct 16 20  | 125.00 |      6.40 |     640.00 |         6.35  | 635.00 |   ($5.00)

    b. Put option sale:
        Symbol: AMD
        Position: STO (sell to open)
        Type: Put
        Expiration: Oct 2 2020
        Strike Price: 77
        Cost: 2.19
        Qty: 10
        Date/Time
        --> cost basis: -qty * cost * 100 -> 2.19 * 100 * 10 = 2,190

    c. Bull Call Spread
        Symbol: AMD
        Position:       BTO           STO
        Strike Price:   75            90
        Type:           Call          Call
        Expiration:     Oct 16        Oct 16
        Cost:           7.00          1.60
        Qty:            10            10
        Net Cost:          7.00 - 1.60 = 5.40
        --> cost basis: 5.40 * 100 = $5,400

        10 * AMD 2020-10-16 75 C/2020-10-16 90 C

3. Track rollover of options ("advanced")

   Given: -1 AAPL 2020-09-18 115 C (STO) @ 4.20

   Roll:
      +1 AAPL 2020-09-18 115 C (BTC) @ 2.40
      -1 AAPL 2020-10-16 115 C (STO) @ 6.40
      Net:                            +4.00 (income)

   Overall Net: 4.00 + 4.20

   -1 AAPL 2020-10-16 115 C (STO) @ 6.40 --> 4.00

#### Infrastructure:

* Database - relational
* Pricing API - for equities: IEX, options: Tradier
* Broker API (optional)
* Importing portfolio from spreadsheet/CSV
* Mobile app/front-end (iOS) SCARY!!
