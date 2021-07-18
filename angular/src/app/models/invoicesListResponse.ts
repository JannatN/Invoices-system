import { Invoice } from "./invoice";
import { User } from "./user";

export interface InvoiceListResponse {
    content: Invoice[];
    totalElements: number;
}