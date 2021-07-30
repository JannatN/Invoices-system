import { Item } from "./item";
import { File } from "./file"
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
  files: File[];
  invoice: any;
  createdBy:string
  lastModifiedBy:string
  lastModifiedDate:Date

}
