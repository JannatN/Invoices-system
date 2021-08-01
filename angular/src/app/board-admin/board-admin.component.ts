import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { InvoiceService } from "../_services/invoices.service";
import { Invoice } from '../models/invoice';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { Observable, of } from "rxjs";
import { TokenStorageService } from '../_services/token-storage.service';
import { Item } from '../models/item';
import { InvoiceDataSource } from '../datasource/invoices.datasource';
import { catchError, finalize, map, tap } from 'rxjs/operators';
import { UploadFilesService } from '../_services/upload-file.service';
import { InvoiceData } from '../datasource/InvoiceData';


@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  filters = {
    keyword: ''
  }

  filterValue: string = null;

  invoices: Observable<Invoice[]>;
  items: Observable<Item[]>;
  user: User;
  invoice: Invoice;
  invoicesArray: Invoice[];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  public displayedColumns = ['id', 'date_created', 'due_date', 'userid', 'company', 'type', 'details', 'update', 'delete', 'create', 'attachFile'];
  invoiceDatasource: InvoiceDataSource;


  public dataSource = new MatTableDataSource<Invoice>();
  constructor(private invoiceService: InvoiceService,
    private router: Router, private token: TokenStorageService,) { }

  ngOnInit() {
    // this.getList();
    // this.getListBackend();
    this.user = this.token.getUser();
    this.invoiceDatasource = new InvoiceDataSource(this.invoiceService);
    this.invoiceDatasource.loadInvoices();


  }

  // filterInvoice(invoice: Invoice[]) {
  //   console.log('hhh');
  //   // return Object.values(invoice).filter(res => {
  //   //   // for (let i = 0; i < 10; i++) {
  //   //   //   console.log('hhh', res[i].type);
  //   //     return res.type.includes(this.filters.keyword);
  //   //   // }
  //   // })
  // }


  // listInv() {
  //   // console.log('hhh', this.filters);
  //   this.invoiceService.listInv({ invoice: this.filters.keyword }).pipe(
  //     catchError(() => of([])),
  //   )
  // console.log('hhho', this.filters);

  // .subscribe(
  //   data => this.invoicesArray = this.filterInvoice(data)
  // )
  // }
  // public getList = () => {
  //   this.invoiceService.getInvoicesList()
  //     .subscribe(res => {
  //       this.dataSource.data = res as Invoice[];
  //     })
  // }


  // ngAfterViewInit(): void {
  //   this.dataSource.sort = this.sort;
  //   this.dataSource.paginator = this.paginator;
  // }
  ngAfterViewInit() {
    this.invoiceDatasource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      )
      .subscribe();
    if (this.filterValue == null) {
      this.paginator.page
        .pipe(
          tap(() => this.loadInvoices())
        )
        .subscribe();
    } else {
      this.paginator.page
        .pipe(
          tap(() => this.loadInvoicesFilter())
        )
        .subscribe();
    }
  }

  loadInvoices() {
    this.invoiceDatasource.loadInvoices(this.paginator.pageIndex, this.paginator.pageSize);
  }

  loadInvoicesFilter() {
    this.invoiceDatasource.loadInvoicesWithFilter(this.paginator.pageIndex, this.paginator.pageSize, this.invoice.type);
  }

  // findBy(type: string) {
  //   console.log(type);
  //   this.invoiceService.paginate(0, 10, type).pipe(
  //     map((invoiceData: InvoiceData) => this.dataSource = invoiceData)
  //   ).subscribe()
  // }
  
  public doFilter = (value: string) => {
    // this.dataSource.filter = value.trim().toLocaleLowerCase();
    value = value.trim(); // Remove whitespace
    value = value.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = value;
  }

  // public searchInvoice(key: string): void {
  //   console.log(key);
  //   const results: Invoice[] = [];
  //   for (const invoice1 of this.invoicesArray) {
  //     if (invoice1.type.toLowerCase().indexOf(key.toLowerCase()) !== -1
  //       || invoice1.company.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
  //       results.push(invoice1);
  //     }
  //   }
  //   this.invoicesArray = results;
  //   if (results.length === 0 || !key) {
  //     // this.invoiceService.listInv();
  //     console.log("nooo key found")
  //   }
  // }




  public redirectToDetails = (id: number) => {
    this.router.navigate(['detailsInvoice', id]);

  }
  public redirectToUpdate = (id: number) => {
    this.router.navigate(['updateInvoice', id]);

  }
  public redirectToDelete = (id: number) => {
    this.invoiceService.deleteInvoice(id)
      .subscribe(
        data => {
          console.log(data);
          // this.reloadData();
        },
        error => console.log(error));
  }
  redirectToAttachFile(id: number) {
    this.router.navigate(['attachFile', id]);

  }
  createInvoice() {
    this.router.navigate(['addInvoice']);
  }

  redirectToCreate(id: number) {
    this.router.navigate(['addItem', id]);
  }
  // reloadData() {
  //   this.invoices = this.invoiceService.getInvoicesList();
  // }




}

