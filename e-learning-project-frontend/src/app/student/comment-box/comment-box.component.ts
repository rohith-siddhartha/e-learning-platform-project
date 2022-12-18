import { CDK_CONNECTED_OVERLAY_SCROLL_STRATEGY_PROVIDER_FACTORY } from '@angular/cdk/overlay/overlay-directives';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-comment-box',
  templateUrl: './comment-box.component.html',
  styleUrls: ['./comment-box.component.css']
})
export class CommentBoxComponent implements OnInit {

  comments:any[] = [];

  commentsData:any[] = [];

  replies:boolean = false;

  comment = {
    content:'',
    commentedOn:new Date(),
    username:this.loginService.getUser().username,
    notice:this.data.noticeEntity,
    parentId:null

  }

  constructor(private loginService:LoginService , private classroomService:ClassroomService, public dialogRef: MatDialogRef<CommentBoxComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
              private snack:MatSnackBar ) { }

  ngOnInit(): void {

    this.getCommentsByNoticeId(this.data.noticeEntity.id);

  }

  viewReplies() {
    this.replies = !this.replies;
  }

  addComment(parentId:any) {
    this.comment.parentId = parentId;
    this.classroomService.addComment(this.comment).subscribe(
      (data:any)=>{
        this.commentsData.unshift(data);
        this.comment.content = '';
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

  getCommentsByNoticeId(noticeId:number) {
    this.classroomService.getCommentsByNoticeId(noticeId).subscribe(
      (data:any)=>{
        this.comments = data;

        for(let comment of data){
          if(comment.parentId === null){
            this.commentsData.push(comment);
          }
        }

        this.commentsData.sort(function(a:any,b:any){
          return new Date(b.commentedOn).valueOf() - new Date(a.commentedOn).valueOf();
        });

        this.snack.open('Comments fetched succesfully', '', {
          duration: 1000
        });
      },
      (error:any)=>{
        this.snack.open('Comments GET failed', '', {
          duration: 1000
        });
      }
    )
  }

}
