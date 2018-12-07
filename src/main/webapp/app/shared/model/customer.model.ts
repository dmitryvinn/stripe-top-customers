export interface ICustomer {
    id?: string;
    name?: string;
}

export class Customer implements ICustomer {
    constructor(public id?: string, public name?: string) {}
}
