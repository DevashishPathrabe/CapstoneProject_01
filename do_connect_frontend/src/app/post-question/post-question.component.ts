import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BASE_URL } from '../constants/constants';
import { UploadImageService } from '../service/upload-image.service';
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
  imageFile: File = new File(['init'], 'init.png');

  constructor(
    private _uploadService: UploadImageService,
    private _userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  onChange(event: any) {
    this.imageFile = event.target.files[0];
  }

  getImageUrl(imageName: string) {
    return `${BASE_URL}/images/${imageName}`;
  }

  onUploadImage() {
    this._uploadService.uploadImage(this.imageFile).subscribe({
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
