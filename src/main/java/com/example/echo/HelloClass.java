package com.example.echo;




import com.googlecode.objectify.Key;




public class HelloClass {
    public Object message = "Hello World";

    public HelloClass () {
    }

    public HelloClass (String name,int rollno,int sem) {
        this.message = "Hello " + name + "!";
    }

    public HelloClass pnr(String no){
        this.message ="you are the fucking "+no+" bro!!!";

//        try {
//
//            System.out.println(new Gson().toJson(new HelloClass().checker()));
//            ESessiondetails s = (ESessiondetails) new HelloClass().checker();
//           // this.message = new pnrDemo().pnrDemo(no, s.sessionid,s.captchaAns);
//            this.message= new pnr_apicall().pnr_apicall(s.captchaAns,s.sessionid,no);
//        }catch (Exception e){
//            this.message ="error:"+e.toString();
//        }

        return this;
    }







}
