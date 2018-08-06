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
 This code is in production and there are no known bugs in it.

You have just joined the team, and your first task is
 to add a new kind of delivery - by Drone. There are
 several ignored test cases which you should enable when you are ready
 to implement the Drone Delivery feature.
 
There is more information about the business rules for the
existing features and new feature in the text below. See also the
javadoc comments in the code.


Business Rules
--------------

A _Session_ holds all the _ModelObject_ classes which store
their fields in the database. If you change the fields in these classes
then the database schema will need updating, so don't do this unless you have to.

A shopping _Cart_ contains a mix of _Items_ of different types that can be bought from a _Store_. When you switch stores,
if that item is available at the new store, then you keep it in your shopping cart. 
If it is not available there, then it is added to the 'unavailable' list.
If the item is a _StoreEvent_ and the new store has a similar event,
you will need to switch to a similar item configured for the new store.
A _StoreEvent_ always has a location.

There are only three types of _DeliveryInformation_: PICKUP, HOME_DELIVERY and SHIPPING.
No others are supported (yet).
If a _DeliveryInformation_ has type PICKUP or HOME_DELIVERY then the _pickupLocation_
must be a _Store_.

When you are shopping online using the _OnlineShopping_ class, you may choose to
switch which store you are shopping at, and update it in the _Session_.
This will impact the delivery options. If you have
Pickup or Home Delivery then if possible you will keep that option, but
with the new store. If the new store is too far away from your _deliveryAddress_
you have to pickup from the original store.
Otherwise, if the new store is close enough to your delivery address,
the delivery information gets switched to home delivery.

If you switch to a _null_ store, that means you will instead shop at the central warehouse
and delivery will be by shipping.

Drone Delivery Rules
--------------------

There is a new delivery method being trialled at 
certain stores - delivery by drone. You are working
on adding support for this delivery type to the _OnlineShopping_
class, in the 'switchStore' method.

When switching stores, if both current and new store support
drone delivery, then the delivery type is unchanged, just the
_pickupLocation_ is switched to the new store.
If you have chosen drone delivery but the new store does not support it, change the delivery
type to PICKUP. If the old store does not support Drone
delivery, but the new one does, and the delivery information
is HOME_DELIVERY, and the weight of the items is under 500g,
then change the delivery information type to "DRONE".
The central warehouse does not support drone delivery.

If Drone delivery is selected then the Pickup Location must be a store, and 
the delivery address must be specified, and the location services must confirm
the pickup location is near to the delivery address.

