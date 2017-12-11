import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { SignupService } from '../../services/signup.service';
import { SignUp } from '../../models/sign-up';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signup: SignUp;

  constructor(private signupService: SignupService) { }

  ngOnInit() {
    this.signup = new SignUp();
  }

  onSubmit() {
    console.log(this.signup);
    console.log(this.signup.birthday);
    this.signupService.signup(this.signup);
  }

}
