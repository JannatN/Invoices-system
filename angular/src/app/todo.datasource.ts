import { DataSource } from '@angular/cdk/table';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";
import { catchError, finalize } from "rxjs/operators";
import { Invoice } from './models/invoice';
import { InvoiceService } from './_services/invoices.service';
import { ListResponse } from './models/listResponse';

export class InvoiceDataSource implements DataSource<Invoice>{

    private todoSubject = new BehaviorSubject<Invoice[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);
    private countSubject = new BehaviorSubject<number>(0);
    public counter$ = this.countSubject.asObservable();

    constructor(private invoiceService: InvoiceService) { }

    connect(collectionViewer: CollectionViewer): Observable<Invoice[]> {
        return this.todoSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.todoSubject.complete();
        this.loadingSubject.complete();
        this.countSubject.complete();
    }

    loadTodos(pageNumber = 0, pageSize = 10) {
        this.loadingSubject.next(true);
        this.invoiceService.listInv({ page: pageNumber, size: pageSize })
            .pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe((result: ListResponse) => {
                this.todoSubject.next(result.content);
                this.countSubject.next(result.totalElements);
            }
            );
    }

}