import { DataSource } from '@angular/cdk/table';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";
import { catchError, finalize } from "rxjs/operators";
import { UserListResponse } from '../core/models/usersListResponse';
import { User } from '../core/models/user';
import { UserService } from '../core/services/users.service';

export class UserDataSource implements DataSource<User>{

    private todoSubject = new BehaviorSubject<User[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);
    private countSubject = new BehaviorSubject<number>(0);
    public counter$ = this.countSubject.asObservable();

    constructor(private userService: UserService) { }

    connect(collectionViewer: CollectionViewer): Observable<User[]> {
        return this.todoSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.todoSubject.complete();
        this.loadingSubject.complete();
        this.countSubject.complete();
    }

    loadUsers(pageNumber = 0, pageSize = 10) {
        this.loadingSubject.next(true);
        this.userService.listUsers({ page: pageNumber, size: pageSize })
            .pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe((result: UserListResponse) => {
                this.todoSubject.next(result.content);
                this.countSubject.next(result.totalElements);
            }
            );
    }

}