import { Routes } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import { TaskComponent } from './components/task/task.component';

export const routes: Routes = [
  { path: 'user', component: UserComponent },
  { path: 'task', component: TaskComponent },
];
