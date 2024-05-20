import { Component } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { User } from '../../models/User';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-deleted-users',
  standalone: true,
  imports: [],
  templateUrl: './deleted-users.component.html',
  styleUrl: './deleted-users.component.css',
})
export class DeletedUsersComponent {
  public constructor(private userService: UserService) {
    this.getDeletedUsers();
  }

  users: any = {};

  private getDeletedUsers() {
    this.userService.getDeletedUsers().subscribe(
      (users: User[]) => {
        this.users = users;
        console.log(users);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
}
