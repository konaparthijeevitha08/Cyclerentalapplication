import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { PostCycleComponent } from './components/post-cycle/post-cycle.component';
import { UpdateCycleComponent } from './components/update-cycle/update-cycle.component';
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component';
import { SearchCycleComponent } from './components/search-cycle/search-cycle.component';



const routes: Routes = [
  { path: "dashboard", component: AdminDashboardComponent },
  { path: "cycle", component: PostCycleComponent },
  { path: "cycle/:id","component": UpdateCycleComponent},
  {path: "bookings", component: GetBookingsComponent},
  { path: "search", component: SearchCycleComponent },



];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
