
from dataclasses import dataclass, field


class ModelObject:
    def save_to_database(self):
        raise Exception("You shouldn't call save_to_database from a unit test")


class Cart(ModelObject):
    def __init__(self):
        self.items = []
        self.unavailable_items = []

    def add(self, item):
        self.items.append(item)

    def mark_as_unavailable(self, item):
        self.unavailable_items.append(item)

    def __repr__(self):
        return f"""Cart(items={self.items}, unavailable={self.unavailable_items})"""


@dataclass
class Item(ModelObject):
    name: str
    item_type: str
    weight: int


@dataclass
class Store(ModelObject):
    name: str
    has_drone_delivery: bool
    stocked_items: dict = field(default_factory=dict)

    def add_stocked_items(self, *items):
        item_tuples = [(item.name, item) for item in items]
        self.stocked_items.update(dict(item_tuples))

    def add_event(self, event):
        self.stocked_items[event.name] = event

    def has_item(self, item):
        return item.name in self.stocked_items.keys()

    def find_item(self, item_name):
        return self.stocked_items[item_name]

    def __repr__(self):
        return f"""Store(name={self.name}, drone_delivery={self.has_drone_delivery})"""


class StoreEvent(Item):
    def __init__(self, name, store):
        Item.__init__(self, name, "EVENT", 0)
        self.store = store
        store.add_event(self)

    def __repr__(self):
        return f"StoreEvent({self.name}, {self.weight})"


@dataclass
class DeliveryInformation(ModelObject):
    delivery_type: str
    store: Store
    weight: int
    delivery_address: str


class LocationServices(ModelObject):
    def is_within_delivery_range(self, store, address):
        return "NEARBY" == address

    def __repr__(self):
        return "LocationServices"

