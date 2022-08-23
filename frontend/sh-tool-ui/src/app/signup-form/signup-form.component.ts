import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../register.service';
import { User } from '../user';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {

  user: User = new User();
  constructor(private registerService: RegisterService) { }

  ngOnInit(): void {
  }

  userRegister() {
    this.registerService.registerUser(this.user).subscribe(data => {
      alert("User successfully registered");
    },
      error => alert("error: Not able to register the User"));
  }

}
