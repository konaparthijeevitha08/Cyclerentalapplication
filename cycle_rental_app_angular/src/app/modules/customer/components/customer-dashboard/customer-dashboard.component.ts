import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-customer-dashboard',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.scss']
})
export class CustomerDashboardComponent {
  cycles: any = [];

  constructor(private service: CustomerService) {}


  ngOnInit() {
    this.getAllCycles();
  }

  getAllCycles() {
    this.service.getAllCycles().subscribe(res => {
      console.log(res);
  
      res.forEach((element: { processedImg: string; returnedImage: string; }) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cycles.push(element);
      });
    });
  }

}
