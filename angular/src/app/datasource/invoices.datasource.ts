import { DataSource } from '@angular/cdk/table';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";
import { catchError, finalize } from "rxjs/operators";
import { Invoice } from '../core/models/invoice'
import { InvoiceService } from '../core/services/invoices.service';
import { InvoiceListResponse } from '../core/models/invoicesListResponse';

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

    loadInvoices(pageNumber = 0, pageSize = 10) {
        this.loadingSubject.next(true);
        this.invoiceService.listInv({ page: pageNumber, size: pageSize })
            .pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe((result: InvoiceListResponse) => {
                this.todoSubject.next(result.content);
                this.countSubject.next(result.totalElements);
            }
            );
    }
    loadInvoicesWithFilter(pageNumber = 0, pageSize = 10, key ) {
        this.loadingSubject.next(true);
        this.invoiceService.paginate( pageNumber, pageSize, key )
            .pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe((result: InvoiceListResponse) => {
                this.todoSubject.next(result.content);
                this.countSubject.next(result.totalElements);
            }
            );
    }
    
}