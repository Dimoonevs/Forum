import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MessageReq } from '../api/Request';
import { AdminUser, MessageResp } from '../api/Response';

@Injectable({
  providedIn: 'root'
})
export class AdminService {



  constructor(private http: HttpClient) { }

  getAllUserUrl = "http://localhost:8080/api/v1/admin/get_users"
  enableToggleUserUrl = "http://localhost:8080/api/v1/admin/user_active"
  messageSendUrl = "http://localhost:8080/api/v1/message/add"
  messageGetAllUrl = "http://localhost:8080/api/v1/message/get"
  processedMessageUrl= "http://localhost:8080/api/v1/message/done"

  getAllUser(): Observable<AdminUser>{
    return this.http.get<AdminUser>(this.getAllUserUrl)
  }

  enableToggleUser(id: string){
    const header = new HttpHeaders({
      "id": String(id)
    })
    return this.http.get<any>(this.enableToggleUserUrl, {headers: header})
  }
  sengMessage(message: MessageReq){
    return this.http.post(this.messageSendUrl, message)
  }

  getAllMesssage():Observable<MessageResp>{
    return this.http.get<MessageResp>(this.messageGetAllUrl)
  }
  processedMessage(id: any){
    const headerId = new HttpHeaders({
      "id": String(id)
    })
    return this.http.get<any>(this.processedMessageUrl, {headers: headerId})
  }
}
