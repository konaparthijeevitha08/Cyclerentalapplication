import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NgStyle } from '@angular/common';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-my-bookings',
  standalone: true,
  imports: [
    CommonModule,
    NzSpinModule,
    NzTableModule,
    NgStyle,
    DatePipe
  ],
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.scss']
})
export class MyBookingsComponent {

  bookings: any
  isSpinning = false;

  constructor(private service: CustomerService) {
      this.getMyBookings();
  }

  getMyBookings() {
    this.isSpinning = true;
    this.service.getBookingsByUserID().subscribe((res) => {
      this.isSpinning = false;
      console.log(res);
      this.bookings = res;
    })
  }
}