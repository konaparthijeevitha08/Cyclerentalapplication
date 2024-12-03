import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = ["http://localhost:8081"];

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http: HttpClient) {}

  postCycle(cycleDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/admin/cycle", cycleDto, {
      headers: this.createAuthorizationHeader()
    });
  }
  getAllCycles(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/cycles", {
      headers: this.createAuthorizationHeader()
    });
  }

  deleteCycle(id: number): Observable<any> {
    return this.http.delete(BASIC_URL + "/api/admin/cycle/" + id, {
      headers: this.createAuthorizationHeader()
    });
  }

  getCycleById(id: number): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/cycle/" + id, {
        headers: this.createAuthorizationHeader()
    });
  }
  updateCycle(cycleId: number,cycleDto: any): Observable<any> {
    return this.http.put(BASIC_URL + "/api/admin/cycle/" + cycleId , cycleDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  getCycleBookings(): Observable<any> {
    return this.http.get(BASIC_URL+ "/api/admin/cycle/bookings",{
      headers: this.createAuthorizationHeader()
    })
  }

  changeBookingStatus(bookingId: number, status: string): Observable<any> {
    return this.http.get(BASIC_URL+ `/api/admin/cycle/booking/${bookingId}/${status}`, {
      headers: this.createAuthorizationHeader()
    });
  }

  searchCycle(searchCycleDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/admin/cycle/search", searchCycleDto, {
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