import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { CommentsReq } from '../api/Request';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private http: HttpClient) { }

  createCommentsUrl = "http://localhost:8080/api/v1/hello/add_comments"



  createComments(req: CommentsReq, id: any){
    const header = new HttpHeaders({
      "id": id
    })
    return this.http.post(this.createCommentsUrl, req, {headers: header})
  }
}
