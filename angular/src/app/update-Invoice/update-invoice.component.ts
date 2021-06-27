import { Component, OnInit } from '@angular/core';
import { Invoice } from '../invoice';
import { ActivatedRoute, Router } from '@angular/router';
import { InvoiceService } from '../_services/invoices.service';


@Component({
  selector: 'app-update-invoice',
  templateUrl: './update-invoice.component.html',
  styleUrls: ['./update-invoice.component.css']
})
export class UpdateInvoiceComponent implements OnInit {

  id: number;
  invoice: Invoice;

  constructor(private route: ActivatedRoute,private router: Router,
    private invoiceService: InvoiceService) { }

  ngOnInit() {
    this.invoice = new Invoice();

    this.id = this.route.snapshot.params['id'];
    
    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
        console.log(data)
        this.invoice = data;
      }, error => console.log(error));
  }

  updateInvoice() {
    this.invoiceService.updateInvoice(this.id, this.invoice)
      .subscribe(data => {
        console.log(data);
        this.invoice = new Invoice();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateInvoice();    
  }

  gotoList() {
    this.router.navigate(['/invoices']);
  }
}
