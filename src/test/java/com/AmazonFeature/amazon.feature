Feature: order Dog Food Product 

Scenario Outline: HomePage 
	Given user Launch the Url 
	When user Select "<dropDown>" option from Dropdown 
	And user Enter "<search>" Product Name in Searchbox 
	Then select the searched product 
	
	Examples:
	
	|dropDown|search|
	|Music|cd|
	|Baby|toys|
	|Pet Supplies|pedigree|

Scenario: Search Result Page 
	When User get first product Name 
	And User get first product Original Price 
	And Click the first Product 
	And driver switch to the next window 
	
Scenario: Selected Product Page 

	When Compare selected product Name 
	And  Compare selected product Original Price 
	Then Click the Add to Cart 
	
Scenario: Add to Cart Page 
	When Take snap of AddToCart Page 
	Then Click the GoToCart 
	
Scenario: Product buy page 
	When compare shipping cart product Name 
	And Take a ScreenShot of Shipping Cart Page 
	And Click the Proceed to Buy 
	Then Close the current window