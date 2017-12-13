import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../../environments/environment';
import { WallPost } from '../../models/WallPost';
import { WallData } from '../../models/wall-data';
import { HttpClient } from '@angular/common/http';
import { Credentials } from '../../models/credentials';

@Component({
  selector: 'app-profile-wall',
  templateUrl: './profile-wall.component.html',
  styleUrls: ['./profile-wall.component.css']
})
export class ProfileWallComponent implements OnInit {

  id: number;
  private sub: any;
  wallData: WallData = new WallData;

  testData: Credentials;

  constructor(private route: ActivatedRoute, private httpClient: HttpClient) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];

      this.testData = new Credentials();
      this.testData.username = 'user2';
      this.testData.password = 'pass2';

      this.httpClient
      .post<Array<WallPost>>(environment.context + 'wall/read/' + this.id, this.testData, {'withCredentials': true})
      .subscribe(data => {
        this.wallData.data = data;
      });
   });
  }

}
