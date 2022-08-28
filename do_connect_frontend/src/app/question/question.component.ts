import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UploadFilesService } from '../service/upload-files.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css'],
})
export class QuestionComponent implements OnInit {
  data: any;
  answer = '';
  answersList: any;
  questionId: any;
  warning = '';
  uploadedImages: string[] = [];
  file: File = new File(['init'], 'init.txt');
  constructor(
    private _userService: UserService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private _uploadService: UploadFilesService
  ) {}

  ngOnInit(): void {
    this.getData();
  }

  getData() {
    let urlParams = this.activatedRoute.snapshot.params;
    this.questionId = urlParams['id'];
    console.log(this.questionId);
    this.data = this._userService.getQuestion(this.questionId).subscribe({
      next: (res) => (this.data = res),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });

    this._userService.getAnswers(this.questionId).subscribe({
      next: (res) => (this.answersList = res),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }
  onChange(event: any) {
    this.file = event.target.files[0];
  }
  getImageUrl(image: any) {
    const imageUrl = 'http://localhost:4000/' + image;
    return imageUrl;
  }
  onUploadImage() {
    this._uploadService.upload(this.file).subscribe({
      next: (res) => console.warn(res),
      error: (err) => {
        if (err.status === 200) this.uploadedImages.push(err.error.text);
        else this.router.navigate(['/error/' + err.status]);
      },
    });
  }

  onSubmit() {
    if (this.answer === '') {
      this.warning = 'All fields are required!';
      return;
    }
    this._userService
      .postAnswer(this.questionId, {
        answer: this.answer,
        images: this.uploadedImages,
      })
      .subscribe({
        next: (res) => {
          alert('Successfully Submitted');
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.log(err);
          this.router.navigate(['/error/' + err.status]);
        },
      });
  }
}
