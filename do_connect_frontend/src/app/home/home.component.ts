import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from '../service/search.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  data: any;
   searchedQuestion=''
  
  constructor(private _userService: UserService, private roter: Router,private _searchService:SearchService) {}

  ngOnInit(): void {
    this._userService.getApprovedQuestions().subscribe((res) => {
      this.data = res;
      console.log(res);
    });
  }
  
  openQuestion(id: any) {
    this.roter.navigate([`/question/${id}`]);
  }

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
