import { Byte } from "@angular/compiler/src/util";

export class File {
    id: string;
    name: string;
    type: string;
    data: BlobPart[];
    invoiceid: number;
}
