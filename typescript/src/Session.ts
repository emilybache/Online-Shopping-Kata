import Cart from "./Cart";
import LocationService from "./LocationService";
import { IModelObject } from "./ModelObject";

/**
 * Allows the OnlineShopping to access data classes
 * and store them in the database.
 */
export default class Session {
    private readonly session: Map<string, IModelObject> = new Map();

    public Session() {
        this.session.set("CART", new Cart());
        this.session.set("LOCATION_SERVICE", new LocationService());
    }

    public get(key: string) {
        return this.session.get(key);
    }

    public put(key: string, value: IModelObject) {
        this.session.set(key, value);
    }

    public saveAll() {
        for (const entity of this.session.values()) {
            if (entity != null) {
                entity.saveToDatabase();
            }
        }
    }

    public toString() {
        let sessionContents = '\n';
        for (const [key, modelObject] of this.session) {
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
