import { Component, OnInit } from '@angular/core';
import { SearchService } from '../service/search.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  constructor(private _userService: UserService,private _searchService:SearchService) {}

  searchedQuestion=''
  data:any;

  ngOnInit(): void {}

  onSearch() {
    if(this.searchedQuestion!=''){
      this._searchService.onOpen(this.searchedQuestion).subscribe(res=>{
        this.data = res;
        console.log(this.data);
      })
    }
    console.log(this.searchedQuestion);
  }
}
