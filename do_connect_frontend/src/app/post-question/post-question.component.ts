import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { UploadFilesService } from '../service/upload-files.service';

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
    private uploadService: UploadFilesService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {}
  onChange(event: any) {
    this.file = event.target.files[0];
  }
  sanitizeImageUrl(imageUrl: string): SafeUrl {
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }
  onUploadImage() {
    console.log(this.file);
    this.uploadService.upload(this.file).subscribe(
      (res: any) => {
        console.warn(res);
      },
      (error) => {
        console.warn(error);
        this.uploadedImages.push(error.error.text);
      }
    );
  }
  onSubmit() {
    if (this.question === '') {
      this.warning = 'All fields are required!';
    }
  }
}
