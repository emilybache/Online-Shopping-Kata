Online Shopping Kata
====================

Your client has this online shopping application. This is
a snippet of the code.

There is a new delivery method being trialled at 
certain stores - delivery by Drone. You are working
on adding support for this delivery type to the ShoppingCart
class.

When switching stores, if both current and new store support
drone delivery, there is no change to the delivery information.
If "DRONE" delivery is selected in the delivery information type
and the new store does not support it, change the delivery 
type to "PICKUP". If the old store does not support Drone 
delivery, but the new one does, and the delivery information
is "HOME_DELIVERY", and the weight of the items is under 10kg,
then change the delivery information type to "DRONE".

Assume all the classes that implement "ModelObject" are
also stored in a database.
