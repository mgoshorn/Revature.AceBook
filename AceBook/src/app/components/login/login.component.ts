import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {
    username: '',
    password: ''
  };

  constructor(private router: Router) { }

  ngOnInit() {
  }

  login() {
    console.log(this.credentials.username);
    console.log(this.credentials.password);
    this.router.navigateByUrl('home');
  }

}
