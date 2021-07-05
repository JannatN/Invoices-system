import { Item } from '../models/item';
import { Component, OnInit, Input } from '@angular/core';
import { ItemService } from "../_services/items.service";
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrls: ['./invoice-details.component.css']
})
export class InvoiceDetailsComponent implements OnInit {

  id: number;
  item: Item;

  constructor(private route: ActivatedRoute,private router: Router,
    private itemService:ItemService) { }

  ngOnInit() {
    this.item = new Item();

    this.id = this.item.id;
    
    this.itemService.getItem(this.id)
      .subscribe(data => {
        console.log(data)
        this.item = data;
        console.log(this.item["invoice"].type)

      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['admin']);
  }
}

