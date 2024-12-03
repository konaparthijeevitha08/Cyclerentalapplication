import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzMessageService } from 'ng-zorro-antd/message';
import { StorageService } from '../../../../auth/services/storage/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-cycle',
  standalone: true,
  imports: [
    RouterModule,
    CommonModule,
    ReactiveFormsModule,
    NzSpinModule,
    NzFormModule,
    NzButtonModule,
    NzDatePickerModule
  ],
  templateUrl: './book-cycle.component.html',
  styleUrls: ['./book-cycle.component.scss']
})
export class BookCycleComponent {
  cycleId!: number;
  cycle: any;
  processedImage: any;
  validateForm!: FormGroup;
  isSpinning = false;
  dateFormat: string = 'DD-MM-YYYY';

  constructor(
    private service: CustomerService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      toDate: [null, Validators.required],
      fromDate: [null, Validators.required]
    });
    this.cycleId = this.activatedRoute.snapshot.params['id'];
    this.getCycleById();
  }

  getCycleById() {
    this.service.getCycleById(this.cycleId).subscribe((res) => {
      console.log(res);
      this.processedImage = 'data:image/jpeg;base64,' + res.returnedImage;
      this.cycle = res;
    });
  }

  bookACycle(data: any) {
    console.log(data);
    this.isSpinning = true;
    
    const bookACycleDto = {
      toDate: data.toDate ? data.toDate.toISOString().split('T')[0] : null,
      fromDate: data.fromDate ? data.fromDate.toISOString().split('T')[0] : null,
      userId: StorageService.getUserId(),
      cycleId: this.cycleId
    };
    
    console.log(bookACycleDto);
  
    this.service.bookACycle(bookACycleDto).subscribe(res => {
      console.log(res);
      this.message.success("Booking request submitted successfully", { nzDuration: 5000 });
      this.router.navigateByUrl("/customer/dashboard");
    }, error => {
      this.message.error("Something went wrong", { nzDuration: 5000 });
      this.isSpinning = false;
    });
  }
  
}
