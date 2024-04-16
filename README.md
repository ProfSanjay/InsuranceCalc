# InsuranceCalc 
## Split your insurance bill with any added in discounts with ease!
Quick and easy bill calculator that takes in the make of a car, who owns it, the amount owed per car, and the total billed value to check to see if there are any discounts and how to distribute them equally amongst users

### _Inputs_
* A list of cars, created through the program by providing:
  * The make and/or model of the car
  * A list of the owners of that vehicle
  * The insurance value of that vehicle
* The total bill amount, which may or may not include some discounts to be divided amongst the bill payers

### _Outputs_
* The calculated value of the discount, if there is one
* The percentage difference of the discount (a fun lil stat to see)
* A breakdown of the amount discounted off of each vehicle, (to be further explained in the Calculations section)
* A breakdown of what each vehicle owner has to pay for their share of the insurance bill (more fun in Calculations)

### _Caclulations_
* __Checking to see if there is a discount available:__

First things first, the sum of the individual vehicles' insurance price is added up through a loop (let's call it $vehicleSum$. This value is then subtracted by the total bill value ($balanceDue$), to check if there is a discount or not. All calculations should be $\geq0$, cause if it's not then $vehicleSum$ must be wrong somewhere.
* __Get each vehicle's discount portion:__

The total sum of the discount value ($totalDiscount = vehicleSum - balanceDue$) should, in all fairness, be split proprtionally betwee neach vehicle based on how much their value contributed towards $vehicleSum$. To do this, we take the percentage of each vehicle's insurance price towards the total sum and then multiply that with the total discount value to give us its share of the discount to recieve, like so: $$(vehiclePrice/vehicleSum) * totalDiscount$$ This calculation is looped and saved within the object holding all of the car data, and subsequently printed out in a nice, readable fashion.

* Calculating each owner's share of the total due
Now that the program knows which vehicles get discounted a certain amount, how should it make the calculation to tell how much each person owes towards the total bill? Using a map, it's easy enough to mark who has to pay what, but for the calculation, the program must loop through each car and subtract the discount from the provided price, and then split it amongst each payee, to look like this: $$\left( amount - discount \right) / \text{number of payees}$$
