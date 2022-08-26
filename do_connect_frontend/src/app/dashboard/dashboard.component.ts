import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  mode = 'Questions';
  data: any;
  constructor(
    private _userService: UserService,
    private _adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.getUnapprovedQuestions();
  }

  getUnapprovedQuestions() {
    this.mode = 'Questions';
    this._adminService.getUnapprovedQuestions().subscribe((res) => {
      this.data = res;
    });
  }

  getUnapprovedAnswers() {
    this.mode = 'Answers';
    this._adminService.getUnapprovedAnswers().subscribe((res) => {
      this.data = res;
    });
  }

  getUsers() {
    this.mode = 'Users';
    this._adminService.getUsers().subscribe((res) => {
      this.data = res;
    });
  }

  onDelete(id: any) {
    if (confirm('Confirm to delete the user')) {
      this._adminService.deleteUser(id).subscribe((res) => {
        console.log(res);
        this.getUsers();
      });
    }
  }

  onApproveQuestion(id: any) {
    this._adminService.approveQuestion(id).subscribe();
  }

  onRejectQuestion(id: any) {
    this._adminService.deleteQuestion(id).subscribe();
  }

  onApproveAnswer(id: any) {
    this._adminService.approveAnswer(id).subscribe();
  }

  onRejectAnswer(id: any) {
    this._adminService.deleteAnswer(id).subscribe();
  }
}
