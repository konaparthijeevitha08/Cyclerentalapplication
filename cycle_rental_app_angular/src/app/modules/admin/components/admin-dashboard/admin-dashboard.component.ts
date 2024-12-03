import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent {
  constructor(private adminService: AdminService,
    private message : NzMessageService
  ) {}

  cycles : any = []

  ngOnInit() {
    this.getAllCycles();
  }

  getAllCycles() {
    this.adminService.getAllCycles().subscribe(res => {
      console.log(res);
  
      res.forEach((element: { processedImg: string; returnedImage: string; }) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cycles.push(element);
      });
    });
  }

  // deleteCycle(id: number) {
  //   console.log(id);
  
  //   this.adminService.deleteCycle(id).subscribe(res => {
  //     this.getAllCycles();
  //     this.message.success("Car deleted successfully", { nzDuration: 5000 });
  //   });
  // }
  deleteCycle(id: number) {
    console.log(id);
  
    this.adminService.deleteCycle(id).subscribe(res => {
      this.cycles = this.cycles.filter((cycle: any) => cycle.id !== id);
      this.message.success("Cycle deleted successfully", { nzDuration: 5000 });
    });
  }
}
