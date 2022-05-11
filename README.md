# Bill Managment
What happens when you forget to pay your bills and you're studying computer science?
You end up creating simple java application which keeps track of bill payments.

![BMS GUI](/bms.png)

BMS gets data about unpaid/paid bills from local MySQL server and presents data inside a table.
<br>The idea is that when you pay your bills you get the data about those bills from MySQL server and "pay" 
them by executing SQL statement which sets bill type to paid.</br>

My landlord wants to have a proof about paid bills which I will send to his email address.
<br>What now?</br>
Let's import javax.mail library to enable this feature. Once this is done, let's select images
of bills from file system and send them.
<br>This is one of the first projects where I used javax.mail for sending emails.</br>
