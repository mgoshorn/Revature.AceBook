import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { Credentials } from '../../models/credentials';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: Credentials;
  title = 'Acebook';

  constructor(private router: Router, private loginService: LoginService) { }

  ngOnInit() {
    this.credentials = new Credentials();
  }

  login() {
    this.loginService.login(this.credentials);
  }

}
