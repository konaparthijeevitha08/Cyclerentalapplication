import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { CommonModule } from '@angular/common';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-update-cycle',
  standalone: true,
  imports: [
    NzSpinModule,
    NzButtonModule,
    NzInputModule,
    NzFormModule,
    ReactiveFormsModule,
    NzSelectModule,
    NzDatePickerModule,
    CommonModule
  ],
  templateUrl: './update-cycle.component.html',
  styleUrls: ['./update-cycle.component.scss']
})
export class UpdateCycleComponent {
  isSpinning = false;
  cycleId!: number;
  imgChanged: boolean = false;
  selectedFile: any;
  imagePreview: string | ArrayBuffer | null = null;
  existingImage: string | null = null;
  updateForm!: FormGroup;
  listOfBrands = ["Trek", "Giant", "Specialized", "Cannondale", "Bianchi", "Cervelo", "Santa Cruz", "Yeti"];
  listOfType = ["Road Bike", "Mountain Bike", "Hybrid Bike", "Electric Bike", "BMX", "Gravel Bike"];
  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Geared", "Non-Geared"];

  constructor(
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.cycleId = this.activatedRoute.snapshot.params['id'];
    this.updateForm = this.fb.group({
      name: [null, Validators.required],
      brand: [null, Validators.required],
      type: [null, Validators.required],
      color: [null, Validators.required],
      transmission: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      year: [null, Validators.required],
      image: [null, Validators.required]
    });
    this.getCycleById();
  }

  getCycleById() {
    this.isSpinning = true;
    this.adminService.getCycleById(this.cycleId).subscribe((res) => {
      this.isSpinning = false;
      const cycleDto = res;
      this.existingImage = 'data:image/jpeg;base64,' + res.returnedImage;
      this.updateForm.patchValue(cycleDto);
    });
  }

  updateCycle() {
    this.isSpinning = true;

    const formData: FormData = new FormData();
    if (this.imgChanged && this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    formData.append('brand', this.updateForm.get('brand')!.value);
    formData.append('name', this.updateForm.get('name')!.value);
    formData.append('type', this.updateForm.get('type')!.value);
    formData.append('color', this.updateForm.get('color')!.value);
    formData.append('year', this.updateForm.get('year')!.value);
    formData.append('transmission', this.updateForm.get('transmission')!.value);
    formData.append('description', this.updateForm.get('description')!.value);
    formData.append('price', this.updateForm.get('price')!.value);

    this.adminService.updateCycle(this.cycleId, formData).subscribe(
      (res) => {
        this.isSpinning = false;
        this.message.success('Cycle updated successfully', { nzDuration: 5000 });
        this.router.navigateByUrl('/admin/dashboard');
      },
      (error) => {
        this.isSpinning = false;
        this.message.error('Error while updating cycle', { nzDuration: 5000 });
      }
    );
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.imgChanged = true;
    this.existingImage = null;
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();

    reader.onload = () => {
      this.imagePreview = reader.result;
    };

    reader.readAsDataURL(this.selectedFile);
  }
}
