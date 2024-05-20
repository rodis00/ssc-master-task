import { Component } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { User, User2 } from '../../models/User';
import { Error } from '../../models/Error';
import { HttpErrorResponse } from '@angular/common/http';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {
  public constructor(private userService: UserService) {}

  user!: User;
  error?: Error;
  user2: any = {};

  userForm = new FormGroup({
    userId: new FormControl(''),
  });

  saveUserForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    dateOfBirth: new FormControl(''),
    creationDate: new FormControl(''),
    organizationUnit: new FormControl(''),
    team: new FormControl(''),
    experienceLevel: new FormControl(),
  });

  private getUser(id: string): void {
    this.userService.getUserById(id).subscribe(
      (user: User) => {
        this.user = user;
        console.log(this.user);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.error = error.error;
      }
    );
  }

  private saveUser(user: User2): void {
    this.userService.saveUser(user).subscribe(
      (user: User2) => {
        // this.user = user;
        console.log(this.user);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.error = error.error;
      }
    );
  }

  private deleteUser(id: string): void {
    this.userService.deleteUserById(id).subscribe(
      () => {
        console.log('User deleted successfully.');
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.error = error.error;
      }
    );
  }

  public handleSubmit(): void {
    let userId = this.userForm.value.userId!;
    console.log(userId);
    this.getUser(userId);
  }

  public handleSubmit3(): void {
    let userId = this.userForm.value.userId!;
    this.deleteUser(userId);
  }

  public handleSubmit2(): void {
    let userData = this.saveUserForm.value!;
    console.log(userData);

    this.createUser(userData);
    this.saveUser(this.user2);
  }

  private createUser(userData: any): void {
    this.user2.firstName = userData.firstName;
    this.user2.lastName = userData.lastName;
    this.user2.creationDate = userData.creationDate;
    this.user2.dateOfBirth = userData.dateOfBirth;
    this.user2.organizationUnit = userData.organizationUnit;
    this.user2.team = userData.team;
    this.user2.experienceLevel = userData.experienceLevel;
  }
}
