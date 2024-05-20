import { Routes } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import { TaskComponent } from './components/task/task.component';
import { DeletedUsersComponent } from './components/deleted-users/deleted-users.component';
import { DeletedTasksComponent } from './components/deleted-tasks/deleted-tasks.component';

export const routes: Routes = [
  { path: 'user', component: UserComponent },
  { path: 'deleted-users', component: DeletedUsersComponent },
  { path: 'task', component: TaskComponent },
  { path: 'deleted-tasks', component: DeletedTasksComponent },
];
