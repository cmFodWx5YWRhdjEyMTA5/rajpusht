package extras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import in.co.rajpusht.rajpusht.Login;

/**
 * Created by Narendra on 6/8/2017.
 */

public class SessionManager {

    SharedPreferences sharedprefernce;
    SharedPreferences.Editor editor;
    SharedPreferences sharedprefernceCoupon;
    SharedPreferences.Editor editorCoupon;
    Context context;
    int PRIVATE_MODE=0;

    private static final String PREF_NAME="sharedcheckLogin";
    private static final String PREF_NAME2="sharedcheckLogin2";
    private static final String User_Id="userid";
    private static final String UserName ="uname";
    private static final String FirstName="fname";
    private static final String ProfilePic="img";
    private static final String LastName="lname";
    private static final String Email="email";
    private static final String Phone="phone";
    private static final String LogKeyExp="lkexp";
    private static final String LogKey="lkey";
    private static final String DisplayName ="dname";
    private static final String UserRechargeMode="userrechargemode";
    private static final String EmailVerifyStatus="emailverifystatus";
    private static final String Otp="otp";
    private static final String OtpKey="otpkey";
    private static final String IS_LOGIN="islogin";
    private static final String shippingFirstANme="shippingFirstANme";
    private static final String shippinglastANme="shippinglastANme";
    private static final String shippingAddress1="shippingAddress1";
    private static final String shippingAddres2="shippingAddres2";
    private static final String shippingCity="shippingCity";
    private static final String shippingState="shippingState";
    private static final String shippingPostCode="shippingPostCode";
    private static final String Shippingcountry="Shippingcountry";
    private static final String shiipingEmail="shiipingEmail";
    private static final String shippingPhone="shippingPhone";
    private static final String billFirstANme="billFirstANme";
    private static final String billlastANme="billlastANme";
    private static final String billAddress1="billAddress1";
    private static final String billAddres2="billAddres2";
    private static final String billCity="billCity";
    private static final String billState="billState";
    private static final String billPostCode="billPostCode";
    private static final String billcountry="billcountry";
    private static final String billEmail="billEmail";
    private static final String billPhone="billPhone";
    private static final String CouponCode="CouponCode";
    private static final String CouponPrice="CouponPrice";


    public SessionManager(Context context){

        this.context =  context;
        sharedprefernce = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor=sharedprefernce.edit();

        sharedprefernceCoupon=context.getSharedPreferences(PREF_NAME2,PRIVATE_MODE);
        editorCoupon=sharedprefernceCoupon.edit();

    }

    public Boolean isLogin(){
        return sharedprefernce.getBoolean(IS_LOGIN, false);

    }
    public void setLogin(){

        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }

    public void setUserID(String id ){

     editor.putString(User_Id,id);
     editor.commit();


    }

    public String getUserID(){

        return  sharedprefernce.getString(User_Id,"DEFAULT");
    }



    public void setFirstName(String fname){

        editor.putString(FirstName,fname);
        editor.commit();
    }

    public String getFirstName(){

        return sharedprefernce.getString(FirstName,"Default");
    }

    public void setUserName(String uname){

        editor.putString(UserName,uname);
        editor.commit();
    }

    public String getUserName(){

        return sharedprefernce.getString(UserName,"Default");
    }

    public void setImgUrl(String img){

        editor.putString(ProfilePic,img);
        editor.commit();
    }

    public String getImgUrl(){

        return sharedprefernce.getString(ProfilePic,"Default");
    }


    public void setLastName(String lname){

        editor.putString(LastName,lname);
        editor.commit();
    }

    public String getLastName(){

        return sharedprefernce.getString(LastName,"Default");
    }


    public void setEmail(String email){

        editor.putString(Email,email);
        editor.commit();
    }

    public String getEmail(){

        return sharedprefernce.getString(Email,"Default");
    }


    public void setPhone(String phone){

        editor.putString(Phone,phone);
        editor.commit();
    }

    public String getPhone(){

        return sharedprefernce.getString(Phone,"Default");
    }


    public void setLogKeyExp(String lkexp){

        editor.putString(LogKeyExp,lkexp);
        editor.commit();
    }

    public String getLogKeyExp(){

        return sharedprefernce.getString(LogKeyExp,"Default");
    }


    public void setLogKey(String lkey){

        editor.putString(LogKey,lkey);
        editor.commit();
    }

    public String getLogKey(){

        return sharedprefernce.getString(LogKey,"Default");
    }


    public void setDisplayName(String dname){

        editor.putString(DisplayName,dname);
        editor.commit();
    }

    public String getDisplayName(){

        return sharedprefernce.getString(DisplayName,"Default");
    }


    public void setUserRechargeMode(String userRechargeMode){

        editor.putString(UserRechargeMode,userRechargeMode);
        editor.commit();
    }


    public String getUserRechargeMode(){

        return sharedprefernce.getString(UserRechargeMode,"Default");
    }


    public void setEmailVerifyStatus(String emailVerifyStatus){

        editor.putString(EmailVerifyStatus,emailVerifyStatus);
        editor.commit();
    }


    public String getEmailVerifyStatus(){

        return sharedprefernce.getString(EmailVerifyStatus,"Default");
    }


    public void setOtp(String oTp){

        editor.putString(Otp,oTp);
        editor.commit();
    }
    public String getOtp(){

        return sharedprefernce.getString(Otp,"Default");
    }



    public void setOtpKey(String otpKey){

        editor.putString(OtpKey,otpKey);
        editor.commit();
    }
    public String getOtpKey(){

        return sharedprefernce.getString(OtpKey,"Default");
    }

    public void setShippingFirstANme(String name){
        editor.putString(shippingFirstANme,name);
        editor.commit();

    }
    public String getShippingFirstANme(){
        return  sharedprefernce.getString(shippingFirstANme,"First Name");
    }

    public void setShippinglastANme(String name){
        editor.putString(shippinglastANme,name);
        editor.commit();

    }
    public String getShippinglastANme(){
        return  sharedprefernce.getString(shippinglastANme,"Last Name");
    }

    public void setShippingAddress1(String name){
        editor.putString(shippingAddress1,name);
        editor.commit();

    }
    public String getShippingAddress1(){
        return  sharedprefernce.getString(shippingAddress1,"Address 1");
    }

    public void setShippingAddres2(String name){
        editor.putString(shippingAddres2,name);
        editor.commit();

    }
    public String getShippingAddres2(){
        return  sharedprefernce.getString(shippingAddres2,"Address 2");
    }

    public void setShippingCity(String name){
        editor.putString(shippingCity,name);
        editor.commit();

    }
    public String getShippingCity(){
        return  sharedprefernce.getString(shippingCity,"City");
    }

    public void setShippingState(String name){
        editor.putString(shippingState,name);
        editor.commit();

    }
    public String getShippingState(){
        return  sharedprefernce.getString(shippingState,"ODISHA");
    }

    public void setshippingPostCode(String name){
        editor.putString(shippingPostCode,name);
        editor.commit();

    }
    public String getshippingPostCode(){
        return  sharedprefernce.getString(shippingPostCode,"Pin Code");
    }

    public void setShippingcountry(String name){
        editor.putString(Shippingcountry,name);
        editor.commit();

    }
    public String getShippingcountry(){
        return  sharedprefernce.getString(Shippingcountry,"INDIA");
    }

    public void setshiipingEmaile(String name){
        editor.putString(shiipingEmail,name);
        editor.commit();

    }
    public String getshiipingEmail(){
        return  sharedprefernce.getString(shiipingEmail,"Email");
    }

    public void setshippingPhone(String name){
        editor.putString(shippingPhone,name);
        editor.commit();

    }
    public String getshippingPhone(){
        return  sharedprefernce.getString(shippingPhone,"Phone");
    }


    public void setBillFirstANme(String name){
        editor.putString(billFirstANme,name);
        editor.commit();

    }
    public String getBillFirstANme(){
        return  sharedprefernce.getString(billFirstANme,"First Name");
    }

    public void setBilllastANme(String name){
        editor.putString(billlastANme,name);
        editor.commit();

    }
    public String getBilllastANme(){
        return  sharedprefernce.getString(billlastANme,"Last Name");
    }

    public void setBillAddress1(String name){
        editor.putString(billAddress1,name);
        editor.commit();

    }
    public String getBillAddress1(){
        return  sharedprefernce.getString(billAddress1,"Address 1");
    }

    public void setBillAddres2(String name){
        editor.putString(billAddres2,name);
        editor.commit();

    }
    public String getBillAddres2(){
        return  sharedprefernce.getString(billAddres2,"Address 2");
    }

    public void setBillCity(String name){
        editor.putString(billCity,name);
        editor.commit();

    }
    public String getBillCity(){
        return  sharedprefernce.getString(billCity,"City");
    }

    public void setBillState(String name){
        editor.putString(billState,name);
        editor.commit();

    }
    public String getBillState(){
        return  sharedprefernce.getString(billState,"ODISHA");
    }

    public void setBillPostCode(String name){
        editor.putString(billPostCode,name);
        editor.commit();

    }
    public String getBillPostCode(){
        return  sharedprefernce.getString(billPostCode,"Pin Code");
    }

    public void setBillcountry(String name){
        editor.putString(billcountry,name);
        editor.commit();

    }
    public String getBillcountry(){
        return  sharedprefernce.getString(billcountry,"INDIA");
    }

    public void setBillEmaile(String name){
        editor.putString(billEmail,name);
        editor.commit();

    }
    public String getBillEmail(){
        return  sharedprefernce.getString(billEmail,"Email");
    }

    public void setBillPhone(String name){
        editor.putString(billPhone,name);
        editor.commit();

    }
    public String getBillPhone(){
        return  sharedprefernce.getString(billPhone,"Phone");
    }



    public void setCouponCode(String name){
        editor.putString(CouponCode,name);
        editor.commit();

    }

    public String getCouponCode(){
        return  sharedprefernce.getString(CouponCode,"Phone");
    }



    public void setCouponPrice(String name){
        editor.putString(CouponPrice,name);
        editor.commit();

    }

    public String getCouponPrice(){
        return  sharedprefernce.getString(CouponPrice,"Phone");
    }

    public void clearCoupon(){

        editorCoupon.clear();
        editorCoupon.commit();

    }



    public void clear(){

//        sharedprefernce.

    editor.clear();
        editor.commit();
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

}


