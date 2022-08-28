import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
    private _adminService: AdminService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getUnapprovedQuestions();
  }

  getUnapprovedQuestions() {
    this.mode = 'Questions';
    this._adminService.getUnapprovedQuestions().subscribe({
      next: (res) => (this.data = res),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }

  getUnapprovedAnswers() {
    this.mode = 'Answers';
    this._adminService.getUnapprovedAnswers().subscribe({
      next: (res) => (this.data = res),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }

  getUsers() {
    this.mode = 'Users';
    this._adminService.getUsers().subscribe({
      next: (res) => (this.data = res),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }

  onDelete(id: any) {
    if (confirm('Confirm to delete the user')) {
      this._adminService.deleteUser(id).subscribe({
        next: (res) => this.getUsers(),
        error: (err) => {
          if (err.status === 200) this.getUsers();
          else this.router.navigate(['/error/' + err.status]);
        },
      });
    }
  }

  onApproveQuestion(id: any) {
    if (confirm('Confirm to approve')) {
      this._adminService.approveQuestion(id).subscribe({
        next: (res) => this.getUnapprovedQuestions(),
        error: (err) => this.router.navigate(['/error/' + err.status]),
      });
    }
  }

  onRejectQuestion(id: any) {
    if (confirm('Confirm to reject')) {
      this._adminService.deleteQuestion(id).subscribe({
        next: (res) => this.getUnapprovedQuestions(),
        error: (err) => {
          if (err.status === 200) this.getUnapprovedQuestions();
          else this.router.navigate(['/error/' + err.status]);
        },
      });
    }
  }

  onApproveAnswer(answer: any) {
    if (confirm('Confirm to approve')) {
      this._adminService.approveAnswer(answer).subscribe({
        next: (res) => this.getUnapprovedAnswers(),
        error: (err) => {
          if (err.status === 200) this.getUnapprovedAnswers();
          else this.router.navigate(['/error/' + err.status]);
        },
      });
    }
  }

  onRejectAnswer(answer: any) {
    if (confirm('Confirm to reject')) {
      this._adminService.deleteAnswer(answer).subscribe({
        next: (res) => this.getUnapprovedAnswers(),
        error: (err) => {
          if (err.status === 200) this.getUnapprovedAnswers();
          else this.router.navigate(['/error/' + err.status]);
        },
      });
    }
  }
}
