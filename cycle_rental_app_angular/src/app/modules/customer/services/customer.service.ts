import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = ["http://localhost:8081"];

@Injectable({
  providedIn: 'root'
})

export class CustomerService {
  constructor(private http: HttpClient) {}

  getAllCycles(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/customer/cycles", {
      headers: this.createAuthorizationHeader()
    });
  }

  getCycleById(cycleId: number): Observable<any>{
    return this.http.get(BASIC_URL + "/api/customer/cycle/" + cycleId, {
        headers: this.createAuthorizationHeader()
    })
  }

  bookACycle(bookACycleDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/customer/cycle/book", bookACycleDto, {
        headers: this.createAuthorizationHeader()
    });
  }

  getBookingsByUserID(): Observable<any> {
    return this.http.get(BASIC_URL+ "/api/customer/cycle/bookings/" + StorageService.getUserId(),{
    headers: this.createAuthorizationHeader()
  })
  }
  
  searchCycle(searchCycleDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/customer/cycle/search", searchCycleDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    const token = StorageService.getToken();
    console.log("Token:", token);  // For debugging, can remove later
    return authHeaders.set('Authorization', 'Bearer ' + token);
  }
}
