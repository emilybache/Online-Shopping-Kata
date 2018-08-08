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
 to add a new kind of delivery - by Drone. One of the other
 developers has started working on a unit test for the new feature,
 and helpfully left you a "TODO" to finish it off.
 
There is more information about the business rules for the
existing features and new feature in the text below. See also the
comments in the code.


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
and delivery will by by shipping.

Drone Delivery Rules
--------------------

There is a new delivery method being trialled at 
certain stores - delivery by drone. You are working
on adding support for this delivery type to the _OnlineShopping_
class, in the 'switchStore' method.

If Drone delivery is selected then:
- the weight must be under 500g,
- the Pickup Location must be a Store,
- the delivery address must be specified,
- the location services must confirm that the pickup location is near to the delivery address.

When switching stores, if both current and new store support
Drone delivery, then if possible, keep the delivery type unchanged.
Just update the _pickupLocation_ to the new store.

If you have chosen drone delivery from your current store
but the new store does not support it, change the delivery
type to HOME_DELIVERY, or PICKUP if that's not possible.

If the current store does not support Drone
delivery, but the one you are switching to does, change to DRONE_DELIVERY if possible.

The central warehouse does not support Drone delivery.
