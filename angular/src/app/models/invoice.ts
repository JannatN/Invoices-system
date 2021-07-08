import { Item } from "./item";

export class Invoice {
    id: number;
    dateCreated: Date;
    dueDate: Date;
    userID: number;
    company:string;
    type:string;
    file_id:number;
items: Item[]
}
