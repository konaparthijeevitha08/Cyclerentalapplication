import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component';
import { BookCycleComponent } from './components/book-cycle/book-cycle.component';
import { MyBookingsComponent } from './components/my-bookings/my-bookings.component';
import { SearchCycleComponent } from './components/search-cycle/search-cycle.component';

const routes: Routes = [
  { path: "dashboard", component: CustomerDashboardComponent },
  { path: "book/:id", component: BookCycleComponent },
  {path: "my_bookings", component : MyBookingsComponent},
  { path: "cycle/search", component: SearchCycleComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
