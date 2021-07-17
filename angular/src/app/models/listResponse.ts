import { Invoice } from "./invoice";

export interface ListResponse {
    content: Invoice[];
    totalElements: number;
}