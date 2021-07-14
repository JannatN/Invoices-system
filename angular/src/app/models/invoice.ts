import { Item } from "./item";
import {File} from "./file"
export class Invoice {
    id: number;
    date_created: Date;
    due_date: Date;
    userid: number;
    company:string;
    type:string;
    items: Item[];
    files: File[];
}
