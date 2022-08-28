import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { UploadFilesService } from '../service/upload-files.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.css'],
})
export class PostQuestionComponent implements OnInit {
  question = '';
  topic = 'java';
  warning = '';
  uploadedImages: string[] = [];
  file: File = new File(['init'], 'init.txt');

  constructor(
    private _uploadService: UploadFilesService,
    private _userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {}
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
    if (this.question === '') {
      this.warning = 'All fields are required!';
      return;
    }
    this._userService
      .postQuestion({
        question: this.question,
        topic: this.topic,
        images: this.uploadedImages,
      })
      .subscribe({
        next: (res) => {
          alert('Successfully Submitted');
          this.router.navigate(['/']);
        },
        error: (err) => this.router.navigate(['/error/' + err.status]),
      });
  }
}
