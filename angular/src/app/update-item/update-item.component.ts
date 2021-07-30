import { Component, OnInit } from '@angular/core';
import { Item } from '../models/item';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../_services/items.service';


@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.css']
})
export class UpdateItemComponent implements OnInit {

  id: number;
  item: Item;

  constructor(private route: ActivatedRoute,private router: Router,
    private itemService: ItemService) { }

  ngOnInit() {
    this.item = new Item();

    this.id = this.route.snapshot.params['id'];
    
    this.itemService.getItem(this.id)
      .subscribe(data => {
        console.log(data)
        this.item = data;
      }, error => console.log(error));
  }

  updateItem() {
    this.itemService.updateItem(this.id, this.item)
      .subscribe(data => {
        console.log(data);
        this.item = new Item();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateItem();    
  }

  gotoList() {
    this.router.navigate(['items']);
  }
}
