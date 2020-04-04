import { ModelObject } from "./ModelObject";
import LocationService from "./LocationService";
import Cart from "./Cart";

/**
 * Allows the OnlineShopping to access data classes
 * and store them in the database
 */
export default class Session {
    private readonly session: Map<String, ModelObject> = new Map();

    public Session() {
        this.session.set("CART", new Cart());
        this.session.set("LOCATION_SERVICE", new LocationService());
    }

    get(key: string) {
        return this.session.get(key);
    }

    put(key: string, value: ModelObject) {
        this.session.set(key, value);
    }

    saveAll() {
        for (let entity of this.session.values()) {
            if (entity != null) {
                entity.saveToDatabase();
            }
        }
    }

    toString() {
        let sessionContents = '\n';
        for (let [key, modelObject] of this.session) {
            sessionContents += key;
            sessionContents += "=";
            sessionContents += modelObject;
            sessionContents += "\n";
        }

        return "Session{" +
                sessionContents +
                "}";
    }
}
