import { Item } from '../models/item';
import { Component, OnInit, Input } from '@angular/core';
import { ItemService } from "../_services/items.service";
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent implements OnInit {

  id: number;
  item: Item;

  constructor(private route: ActivatedRoute,private router: Router,
    private itemService:ItemService) { }

  ngOnInit() {
    this.item = new Item();

    this.id = this.route.snapshot.params['id'];
    
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

