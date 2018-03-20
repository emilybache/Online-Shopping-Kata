Online Shopping Kata
====================

You work for a company called "Skin Deep" on their
 online beauty product shopping application. The company
 has a chain of physical Stores around the country, and
 also has this online application, so that customers
 can order products to collect in store, or for home delivery.
 
You are working on a part of the code,
 that handles updating the online shopping Cart, 
 when a customer switches the Store they are shopping at. 

You have just joined the team, and your first task is
 to add a new kind of delivery - by Drone. 
 
There is more information about the business rules for the
existing features and new feature in the text below.

There is more information about what each piece of the code
 does, in javadoc comments. Note, all the classes that 
 implement "ModelObject" are generated from a database
 schema and you are not allowed to edit them.


Business Rules
--------------

Drone Delivery Rules
--------------------

There is a new delivery method being trialled at 
certain stores - delivery by Drone. You are working
on adding support for this delivery type to the OnlineShopping
class, in the 'switchStore' method.

When switching stores, if both current and new store support
drone delivery, there is no change to the delivery information.
If "DRONE" delivery is selected in the delivery information type
and the new store does not support it, change the delivery 
type to "PICKUP". If the old store does not support Drone 
delivery, but the new one does, and the delivery information
is "HOME_DELIVERY", and the weight of the items is under 5kg,
then change the delivery information type to "DRONE".

If the new store is null (ie we have selected the central warehouse)
then Drone delivery is not available. The delivery information 
type should be changed to "SHIPPING".

