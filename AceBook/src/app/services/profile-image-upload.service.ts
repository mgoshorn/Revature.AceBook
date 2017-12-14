import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { StorageService } from './storage.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ProfileImageUploadService {
  form: FormGroup;
  private resolutionSubject = new Subject<boolean>();
  resolution$ = this.resolutionSubject.asObservable();

  constructor(private storageService: StorageService, private fb: FormBuilder, private http: HttpClient) {
    this.createForm();
  }

  createForm() {
    this.form = this.fb.group({
      credentials: this.storageService.getCredentials(),
      fileType: String,
      image: String
    });
  }

  upload(event) {
    const reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {

        this.form.get('fileType').setValue(file.type);
        this.form.get('image').setValue(reader.result.split(',')[1]);

        console.log(this.form.value);

        this.http.post(environment.context + 'wall/profilePhoto/update', this.form.value, {withCredentials: true})
          .subscribe(
            succ => this.resolutionSubject.next(true),
            err => this.resolutionSubject.next(false)
          );
      };
    }
  }

}
