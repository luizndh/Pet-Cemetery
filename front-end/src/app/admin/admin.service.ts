import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReuniaoAdmin } from './reuniao/model/reuniao-admin.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private prefixoApi: string = 'http://localhost:8080/api/admin';

  constructor(private http: HttpClient) { }


}
