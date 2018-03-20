Online Shopping Kata
====================

Your client has this online shopping application. This is
a snippet of the code.

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

Assume all the classes that implement "ModelObject" are
also stored in a database.
