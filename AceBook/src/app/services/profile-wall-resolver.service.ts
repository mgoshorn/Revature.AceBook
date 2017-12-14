import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router, Resolve, RouterStateSnapshot,
         ActivatedRouteSnapshot } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { WallData } from '../models/Wall-data';
import { WallPost } from '../models/WallPost';
import { environment } from '../../environments/environment';

@Injectable()
export class ProfileWallResolver implements Resolve<Array<WallPost>> {
  constructor(private router: Router, private httpClient: HttpClient) {}

  wallData: Array<WallPost>;
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Array<WallPost>> {
    const id = route.paramMap.get('id');

    return this.httpClient.post<Array<WallPost>>(environment.context + '/wall/read/' + id, {'withCredentials': true});
  }
}
