import { Item } from "./item";

export class Invoice {
    id: number;
    dateCreated: Date;
    dueDate: Date;
    userID: number;
    company:string;
    type:string;
    items: Item[];
    file_id:number;
}
