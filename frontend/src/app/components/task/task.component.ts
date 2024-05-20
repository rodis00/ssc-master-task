import { Component } from '@angular/core';
import { TaskService } from '../../services/task/task.service';
import { Task } from '../../models/Task';
import { HttpErrorResponse } from '@angular/common/http';
import { Error } from '../../models/Error';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './task.component.html',
  styleUrl: './task.component.css',
})
export class TaskComponent {
  public constructor(private taskService: TaskService) {}

  task!: Task;
  error!: Error;
  task2: any = {};

  taskForm = new FormGroup({
    taskId: new FormControl(''),
  });

  saveTaskForm = new FormGroup({
    taskId: new FormControl(''),
    title: new FormControl(''),
    numberOfSubtasks: new FormControl(''),
    priority: new FormControl(''),
    creationDate: new FormControl(''),
    allConditionsMustBeSatisfied: new FormControl(''),
    organizationUnit: new FormControl(''),
    team: new FormControl(),
    minimumExperienceLevel: new FormControl(),
    maximumAgeInYears: new FormControl(),
  });

  private getTask(id: string): void {
    this.taskService.getTaskById(id).subscribe(
      (task: Task) => {
        this.task = task;
        console.log(this.task);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.error = error.error;
      }
    );
  }

  private deleteTask(id: string): void {
    this.taskService.deleteTaskById(id).subscribe(
      () => {
        console.log('Task deleted successfully.');
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.error = error.error;
      }
    );
  }

  private saveTask(task: Task): void {
    this.taskService.saveTask(task).subscribe(
      (task: Task) => {
        console.log(this.task);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.error = error.error;
      }
    );
  }

  private updateTask(id: string, task: Task): void {
    this.taskService.updateTask(id, task).subscribe(
      (task: Task) => {
        console.log(this.task);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.error = error.error;
      }
    );
  }

  public handleSubmit(): void {
    let taskId = this.taskForm.value.taskId!;
    console.log(taskId);
    this.getTask(taskId);
  }

  public handleSubmit3(): void {
    let taskId = this.taskForm.value.taskId!;
    this.deleteTask(taskId);
  }

  public handleSubmit2(): void {
    let taskData = this.saveTaskForm.value!;
    console.log(taskData);

    this.createTask(taskData);
    this.saveTask(this.task2);
  }

  private createTask(taskData: any): void {
    this.task2.id = taskData.taskId;
    this.task2.title = taskData.title;
    this.task2.numberOfSubtasks = taskData.numberOfSubtasks;
    this.task2.priority = taskData.priority;
    this.task2.creationDate = taskData.creationDate;
    this.task2.allConditionsMustBeSatisfied =
      taskData.allConditionsMustBeSatisfied;
    this.task2.organizationUnit = taskData.organizationUnit;
    this.task2.team = taskData.team;
    this.task2.minimumExperienceLevel = taskData.minimumExperienceLevel;
    this.task2.maximumAgeInYears = taskData.maximumAgeInYears;
  }

  public handleSubmit4(): void {
    let taskData = this.saveTaskForm.value!;
    this.createTask(taskData);
    this.updateTask(this.task2.id, this.task2);
  }
}
