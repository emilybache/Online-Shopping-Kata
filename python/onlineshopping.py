
class OnlineShopping:
    def __init__(self, session):
        self.session = session

    def switch_store(self, store_to_switch_to):
        cart = self.session.get("CART")
        deliveryInformation = self.session.get("DELIVERY_INFO")
        if store_to_switch_to is None:
            if cart is not None:
                for item in cart.get_items():
                    if "EVENT" == item.get_type():
                        cart.mark_as_unavailable(item)

            if deliveryInformation is not None:
                deliveryInformation.set_type("SHIPPING")
                deliveryInformation.set_pickup_location(None)
            
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
                if deliveryInformation is not None and \
                        deliveryInformation.delivery_type is not None and \
                        "HOME_DELIVERY" == deliveryInformation.delivery_type and \
                        deliveryInformation.delivery_address is not None:
                    if not self.session.get("LOCATION_SERVICE").is_within_delivery_range(store_to_switch_to, deliveryInformation.delivery_address):
                        deliveryInformation.setType("PICKUP")
                        deliveryInformation.pickup_location = current_store
                    else:
                        deliveryInformation.weight = weight
                        deliveryInformation.pickup_location = store_to_switch_to
                    
                else:
                    if deliveryInformation is not None and \
                            deliveryInformation.delivery_address is not None:
                        if self.session.get("LOCATION_SERVICE").is_within_delivery_range(store_to_switch_to, deliveryInformation.delivery_address):
                            deliveryInformation.setType("HOME_DELIVERY")
                            deliveryInformation.weight = weight
                            deliveryInformation.pickup_location = store_to_switch_to

                for item in new_items:
                    cart.add(item)

        self.session.put("STORE", store_to_switch_to)
        self.session.save_all()

    def __repr__(self):
        return f"""OnlineShopping(
session={self.session}
)"""
