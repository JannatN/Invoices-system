import { User } from "./user";

export interface UserListResponse {
    content: User[];
    totalElements: number;
}