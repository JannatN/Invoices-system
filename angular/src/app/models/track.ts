import { Invoice } from "./invoice";

export class Track {
    id: number;
    auditorName: string;
    invoiceBefore: string;
    invoiceAfter: Invoice;
    created_at: Date;
    updated_At: Date;
}


