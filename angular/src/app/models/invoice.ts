<<<<<<< HEAD
import { Item } from "./item";
import { FileUp } from "./file"
import { FormArray } from "@angular/forms";
export class Invoice {
  id: number;
  date_created: Date;
  due_date: Date;
  userid: number;
  company: string;
  type: string;
  items: Item[];
  // items: FormArray;
  files: FileUp[];
  invoice: any;
  createdBy:string
  lastModifiedBy:string
  lastModifiedDate:Date

}
=======
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
>>>>>>> 744ac9204c57a93704ae1980d936861051ec705d
