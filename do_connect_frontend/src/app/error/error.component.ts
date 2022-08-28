import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css'],
})
export class ErrorComponent implements OnInit {
  statusCode: any;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    let urlParams = this.activatedRoute.snapshot.params;
    this.statusCode = urlParams['status'];
  }
}
