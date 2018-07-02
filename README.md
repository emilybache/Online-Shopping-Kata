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

A shopping cart contains a mix of items of different types. When you switch stores, 
if that item is available at the new store, then you keep it in your shopping cart. 
If it is not available there, then it is removed. If the item is a "Store Event"
and the new store has a similar event, you will need to switch to a similar item 
configured for the new store.

A StoreEvent always has a location.

There are only three types of DeliveryInformation: Pickup, Home Delivery and Shipping. 
No others are supported (yet).
A DeliveryInformation must include a delivery address, unless it is of type Pickup, in which 
case it is optional.
If a DeliveryInformation has type Pickup or Home Delivery then the "Pickup Location" 
must be a Store.

Drone Delivery Rules
--------------------

There is a new delivery method being trialled at 
certain stores - delivery by Drone. You are working
on adding support for this delivery type to the OnlineShopping
class, in the 'switchStore' method.

When switching stores, if both current and new store support
drone delivery, then the pickup store is switched to the new one.
If "DRONE" delivery is selected in the delivery information type
and the new store does not support it, change the delivery 
type to "PICKUP". If the old store does not support Drone 
delivery, but the new one does, and the delivery information
is "HOME_DELIVERY", and the weight of the items is under 5kg,
then change the delivery information type to "DRONE".

If the new store is null (ie we have selected the central warehouse)
then Drone delivery is not available. The delivery information 
type should be changed to "SHIPPING".

If Drone delivery is selected then the Pickup Location must be a store, and 
the delivery address must be specified, and the location services must confirm
the pickup location is near to the delivery address.

Test Cases
----------
This starting position for the exercise comes with test cases. There are
several ignored test cases which you should enable when you are ready
to implement the Drone Delivery feature.

