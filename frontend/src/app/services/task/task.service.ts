import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../../models/Task';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private apiUrl: string = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {}

  public getTaskById(id: string): Observable<Task> {
    return this.http.get<Task>(`${this.apiUrl}/tasks/${id}`);
  }

  public saveTask(task: Task): Observable<any> {
    return this.http.post<Task>(`${this.apiUrl}/tasks/`, task);
  }

  public deleteTaskById(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/tasks/${id}`);
  }

  public updateTask(id: string, task: Task): Observable<any> {
    return this.http.put<Task>(`${this.apiUrl}/tasks/${id}`, task);
  }
}
