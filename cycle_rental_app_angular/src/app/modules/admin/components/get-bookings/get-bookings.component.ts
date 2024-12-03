import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { AdminService } from '../../services/admin.service';
import { NzMessageComponent, NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-get-bookings',
  standalone: true,
  imports: [
    CommonModule,
    NzTableModule,
    NzSpinModule,
    NzButtonModule
  ],
  templateUrl: './get-bookings.component.html',
  styleUrls: ['./get-bookings.component.scss']
})
export class GetBookingsComponent {

  bookings: any;
  isSpinning = false;

  constructor(private adminService: AdminService,
    private message : NzMessageService
  ) {
    this.getBookings();
  }

  getBookings() {
    this.isSpinning = true;
    this.adminService.getCycleBookings().subscribe((res) => {
      this.isSpinning = false;
      console.log(res);
      this.bookings = res;
    });
  }

  changeBookingStatus(bookingId: number, status: string) {
    this.isSpinning = true;
    console.log(bookingId, status);
    this.adminService.changeBookingStatus(bookingId, status).subscribe(
      (res) => {
        this.isSpinning = false;
        console.log(res);
        this.getBookings();
        this.message.success("Booking status changed successfully", { nzDuration: 5000 });
      },
      (error) => {
        this.isSpinning = false;
        this.message.error("Something went wrong", { nzDuration: 5000 });
      }
    );
  }
  
  
}
