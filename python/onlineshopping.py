
class OnlineShopping:
    """The online shopping company owns a chain of Stores selling makeup and
    beauty products.

    Customers using the online shopping website can choose a Store then
    can put Items available at that store into their Cart.

    If no store is selected, then items are shipped from a central warehouse.
 """

    def __init__(self, session):
        self.session = session

    def switch_store(self, store_to_switch_to):
        """This method is called when the user changes the store they are shopping
        at in the online shopping website."""
        cart = self.session.get("CART")
        delivery_information = self.session.get("DELIVERY_INFO")
        if store_to_switch_to is None:
            if cart is not None:
                for item in cart.get_items():
                    if "EVENT" == item.get_type():
                        cart.mark_as_unavailable(item)

            if delivery_information is not None:
                delivery_information.set_type("SHIPPING")
                delivery_information.set_pickup_location(None)
            
        else:
            if cart is not None:
                new_items = []
                weight = 0
                for item in cart.items:
                    if "EVENT" == item.item_type:
                        if store_to_switch_to.has_item(item):
                            cart.mark_as_unavailable(item)
                            new_items.append(store_to_switch_to.find_item(item.name))
                        else:
                            cart.mark_as_unavailable(item)
                        
                    elif not store_to_switch_to.has_item(item):
                        cart.mark_as_unavailable(item)
                    
                    weight += item.weight
                
                for item in cart.unavailable_items:
                    weight -= item.weight

                current_store = self.session.get("STORE")
                if delivery_information is not None and \
                        delivery_information.delivery_type is not None and \
                        "HOME_DELIVERY" == delivery_information.delivery_type and \
                        delivery_information.delivery_address is not None:
                    if not self.session.get("LOCATION_SERVICE").is_within_delivery_range(store_to_switch_to, delivery_information.delivery_address):
                        delivery_information.delivery_type = "PICKUP"
                        delivery_information.pickup_location = current_store
                    else:
                        delivery_information.weight = weight
                        delivery_information.pickup_location = store_to_switch_to
                    
                else:
                    if delivery_information is not None and \
                            delivery_information.delivery_address is not None:
                        if self.session.get("LOCATION_SERVICE").is_within_delivery_range(store_to_switch_to, delivery_information.delivery_address):
                            delivery_information.delivery_type = "HOME_DELIVERY"
                            delivery_information.weight = weight
                            delivery_information.pickup_location = store_to_switch_to

                for item in new_items:
                    cart.add(item)

        self.session.put("STORE", store_to_switch_to)
        self.session.save_all()

    def __repr__(self):
        return f"""OnlineShopping(session={self.session})"""
