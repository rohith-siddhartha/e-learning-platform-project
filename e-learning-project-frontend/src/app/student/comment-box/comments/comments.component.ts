import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  @Input() comment:any;
  @Input() notice:any;

  commentData = {
    content:'',
    commentedOn:new Date(),
    username:this.loginService.getUser().username,
    notice:null,
    parentId:null

  }


  replies:boolean  = true;

  replyBox:boolean = true;

  replyText = "view replies";

  viewReplies() {
    this.replies = !this.replies;

    if(this.replyText === "view replies"){
      this.replyText = "close replies"
    }else{
      this.replyText = "view replies";
    }

  }

  reply() {
    this.replyBox = false;
  }

  addComment(parentm:any) {

    this.replyBox = true;

    this.commentData.notice = this.notice;
    this.commentData.parentId = parentm.id;

    this.classroomService.addComment(this.commentData).subscribe(
      (data:any)=>{
        parentm.replies.unshift(data);
        this.commentData.content = '';
        this.snack.open('Comment created succesfully', '', {
          duration: 1000
        });
      },
      (error:any)=>{
        this.snack.open('Comment creating failed', '', {
          duration: 1000
        });
      }
    )
  }

  constructor(private classroomService:ClassroomService, private loginService:LoginService, private snack:MatSnackBar) { }

  ngOnInit(): void {

    this.comment.replies.sort(function(a:any,b:any){
      return new Date(b.commentedOn).valueOf() - new Date(a.commentedOn).valueOf();
    });

  }

}
