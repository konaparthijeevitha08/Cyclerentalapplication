import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AdminService } from '../../../admin/services/admin.service';
import { CustomerService } from '../../services/customer.service';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-cycle',
  standalone: true,
  imports: [NzSpinModule,NzButtonModule, NzFormModule, ReactiveFormsModule, NzSelectModule, NzDatePickerModule, CommonModule],
  templateUrl: './search-cycle.component.html',
  styleUrl: './search-cycle.component.scss'
})
export class SearchCycleComponent {

  cycles: any = [];
  searchCycleForm: FormGroup;
  listOfOptions: Array<{ label: string; value: string }> = [];
  listOfBrands = ["Trek", "Schwinn", "Specialized", "Giant", "Cannondale", "Canyon", "Scott", "Merida", "Focus", "BMC"]; // Sample cycle brands
  listOfType = ["Mountain", "Road", "Hybrid", "Electric", "Cruiser"]; // Cycle types
  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Geared", "Non-Geared"];

  isSpinning = false;

  constructor(private fb: FormBuilder,
    private service: CustomerService
  ) {
    this.searchCycleForm = this.fb.group({
      brand: [null],
      type: [null],
      transmission : [null],
      color: [null],
      
    });
  }

searchCycle() {
  this.isSpinning = true;
  this.service.searchCycle(this.searchCycleForm.value).subscribe((res) => {
    res.cycleDtoList.forEach((element: { processedImg: string; returnedImage: string; }) => {
      element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
      this.cycles.push(element);
    });
    this.isSpinning = false;
  });
}
}