import { Item } from "./item";

export class Invoice {
    id: number;
    date_created: Date;
    due_date: Date;
    userid: number;
    company:string;
    type:string;
    items: Item[];
    file_id:number;
}
