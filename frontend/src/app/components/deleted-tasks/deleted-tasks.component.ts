import { Component } from '@angular/core';
import { TaskService } from '../../services/task/task.service';
import { Task } from '../../models/Task';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-deleted-tasks',
  standalone: true,
  imports: [],
  templateUrl: './deleted-tasks.component.html',
  styleUrl: './deleted-tasks.component.css',
})
export class DeletedTasksComponent {
  public constructor(private taskService: TaskService) {
    this.getDeletedTasks();
  }

  tasks: any = {};

  private getDeletedTasks() {
    this.taskService.getDeletedTasks().subscribe(
      (tasks: Task[]) => {
        this.tasks = tasks;
        console.log(tasks);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
}
