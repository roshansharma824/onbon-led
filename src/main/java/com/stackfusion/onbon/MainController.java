package com.stackfusion.onbon;

import com.stackfusion.onbon.data.*;
import onbon.y2.*;
import onbon.y2.common.AlignmentType;
import onbon.y2.common.Y2Font;
import onbon.y2.play.TextArea;
import onbon.y2.play.*;
import org.apache.log4j.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

import static com.stackfusion.onbon.ApiService.getAdvertisementsList;
import static com.stackfusion.onbon.ApiService.getJetsonDataFromServer;
import static com.stackfusion.onbon.Constants.*;
import static com.stackfusion.onbon.Utils.*;


public class MainController {

    static Y2Screen screen;
    private static Font font;



    public static void main(String[] args) {
        //for testing comment Utils.getEthernetMacAddress()
//        jetson_mac = Utils.getEthernetMacAddress();

        getJetsonDataFromServer();

      
    }



    public static void init() {
    	  // Initialize the API, this operation is only executed once when the program starts,
        // and a memory error will occur if executed multiple times

    	try{
            printLog("Initializing...");
    		Y2Env.initial();

    		pz_initial_setup();



            printLog("Start Getting localfirst messages");




            getAdvertisementsList();

    		LocalFirstMessages.start();


            if (Objects.equals(gateType, "entry") && !payment_at_entry) {
                printLog("GateType: "+Constants.gateType);
                MQTTEntry.MQTTClient();
            }
            if (Objects.equals(gateType, "exit")) {
                printLog("GateType: "+Constants.gateType);
                MQTTExit.MQTTClient();
            }if (payment_at_entry){
                printLog("GateType: payment_at_entry: true ");
                MQTTExit.MQTTClient();
            }
        }catch (Y2Exception e) {
            e.printStackTrace();
            exception_count++;
            printLog(Level.ERROR,"Y2Exception in init "+e.getMessage());
        }catch (Exception e){
             e.printStackTrace();
             exception_count++;
             printLog(Level.ERROR,"Exception in init "+e.getMessage());
        }
    }
    
   // update the program display
    public static void sendPrograms() throws Exception{
        // Create a screen object for interacting with the control card
        Y2Screen screen = new Y2Screen(Constants.url);

        // login
        // The login account and password default to guest

        //TODO: This is null if login fails or the device is not available. need to check the session id
        screen.login("guest","guest");


        // create program
        // The program is mainly used to combine the content displayed on the screen, it consists of multiple areas
        // The controller can only play one program at a time, 
        // it is the smallest unit in which the controller's display content can be updated individually
        // Multiple programs are played in turn by default
        // Create program 1 (including a graphic area and a time area)
        // parameter is program number 1
        ProgramPlayFile pf1 = new ProgramPlayFile(1);

        // create a graphic area
        // When creating an area, you need to pay attention to the location and size of the area. It cannot exceed the screen range, and areas can overlap with each other.
        // There are many areas supported by the controller, such as: text area TextArea, time area DateTimeArea, picture area PicArea, video area VideoArea, etc.
        // The area can be set to display special effects, special effects speed and dwell time, etc.
        TextArea tArea = new TextArea(0,0,screen.getWidth(),40);
        tArea.animation(63,10);
        tArea.setStayTime(0);

        // create display content
        // Display content-related settings, set in the page, such as font size, font color, background color, alignment, etc., use line breaks for newlines\n
        TextAreaTextPage page = tArea.addTextSection("Paid ₹50");


        page.setFont(new Y2Font(font));
        page.fgColor(Color.RED);
        page.setBgColor(Color.BLACK);
        page.getFont().bold();
        page.setVerticalAlignment(AlignmentType.NEAR);
        page.setHorizontalAlignment(AlignmentType.NEAR);

        // create time zone
        DateTimeArea dArea = new DateTimeArea(0,0,screen.getWidth(),20);
        dArea.addUnits(DateTimePattern.HH_MM_SS).fgColor(Color.GREEN).getFont().setSize(12);

        // add the region to the program
        pf1.getAreas().add(tArea);

//        pf1.getAreas().add(dArea);

        // create program 2
        // The program contains pictures and videos
        ProgramPlayFile pf2 = new ProgramPlayFile(2);

        // create an image area
        PicArea pArea = new PicArea(0,20,screen.getWidth(), 20);

        // add a picture to the picture area
//        PicAreaPage pPage = pArea.addPage("C:\\onBonLedDisplay-master\\icon.png");

//        C:\Users\91959\IdeaProjects\onBonLedDisplay-master
        // create a video area
//        VideoArea vArea = new VideoArea(0,0,screen.getWidth(), screen.getHeight());

        // add video to video area
//        VideoAreaPage vPage = vArea.addVideo("C:\\Users\\91959\\IdeaProjects\\onBonLedDisplay-master\\video.mp4",100);
        // add the region to the program
        pf2.getAreas().add(pArea);
//        pf2.getAreas().add(vArea);


        // add a picture to the picture area
        ProgramPlayFile pf3 = new ProgramPlayFile(3);
//        PicAreaPage pPage3 = pArea.addPage("C:\\Users\\91959\\IdeaProjects\\onBonLedDisplay-master\\secureparking.jpeg");
        pf3.getAreas().add(pArea);
        
        ProgramPlayFile pf4 = new ProgramPlayFile(4);
//        PicAreaPage pPage4 = pArea.addPage("C:\\Users\\91959\\IdeaProjects\\onBonLedDisplay-master\\seawoods.jpeg");
        pf3.getAreas().add(pArea);
        
        ProgramPlayFile pf5 = new ProgramPlayFile(5);
//        PicAreaPage pPage5 = pArea.addPage("C:\\Users\\91959\\IdeaProjects\\onBonLedDisplay-master\\bank_paytm.jpeg");



        // update the program
//        String list = screen.writePlaylist(/*pf1,pf2,*/pf3,pf4,pf5);
        pf2.getAreas().add(tArea);
        String list = screen.writePlaylist(pf1);
        screen.play(list);

        // Sign out
        screen.logout();
    }

    // Update the dynamic area display
    // The dynamic area is independent of the program and can be updated according to the area. The dynamic area will not be saved when power off, and the update of the dynamic area will not affect the program playback.
    // The dynamic area is generally used to update the displayed content in real time
    public static void sendDynamic()throws Exception{
        // Create a screen object for interacting with the control card
        Y2Screen screen = new Y2Screen(Constants.url);

        // login
        // The login account and password are both guest by default
        screen.login("guest","guest");

        // Create dynamic area management
        Y2DynamicManager dyn = screen.dynamic();

        // create dynamic area file
        DynamicPlayFile file = new DynamicPlayFile();

        // Add a dynamic area to the dynamic area file
        DynamicArea dynamicArea = file.createArea(0,0,128,96);


        // Add display content in the dynamic area, which can be a picture or a string
        DynamicAreaTextUnit text = dynamicArea.addText("This is a dynamic area");
//        DynamicAreaPicUnit image = dynamicArea.addImage("C:\\Users\\91959\\IdeaProjects\\onBonLedDisplay-master\\bank_paytm.jpeg");

        // update dynamic area
        dyn.write(file);

        // Set the dynamic area to save when power off
//        dyn.save("0");

        // Sign out
        screen.logout();

    }

    // Update dynamic area material
    // Before updating the dynamic area material, you need to update the dynamic area first, and then keep updating the dynamic area material
    public static void sendDynamicUnits() throws Exception{
        Y2Screen screen = new Y2Screen(Constants.url);
        screen.login("guest","guest");
        Y2DynamicManager dyn = screen.dynamic();
        DynamicPlayFile dynFile = new DynamicPlayFile();
        DynamicArea dArea = dynFile.createArea(0,0,128,96,0);
        DynamicAreaTextUnit unit = dArea.addText("This is the dynamic area");
        dyn.write(dynFile);

        // Update dynamic area material
        DynamicUnitFile unitFile = new DynamicUnitFile();
        DynamicArea dynamicArea = unitFile.defineArea(0,0,128,96,0);
        dynamicArea.addText("This is the dynamic area material");
        // Update dynamic area material
        dyn.writeUnitsOnly(unitFile);

        // Sign out
        screen.logout();
    }

    // Announcement area update
    public static void sendBulletin() throws Exception{
        // Create a screen object for interacting with the control card
        Y2Screen screen = new Y2Screen(Constants.url);

        // login
        screen.login("guest","guest");

        // announcement management
        Y2BulletinManager bulletinManager = screen.bulletin();

        // create announcement one
        BulletinArea bArea1 = new BulletinArea(1,"Bulletin 1",0,0,128,16);
        bArea1.content("Sumeet mahaptra");
        bArea1.bgColor(Color.CYAN);
       
        // create announcement two
        BulletinArea bArea2 = new BulletinArea(2,"Bulletin 2",0,17,128,16);
        bArea2.content("Bulletin 2");
        
        // Upload Announcement 1 and Announcement 2
        bulletinManager.write(bArea1);
        bulletinManager.write(bArea2);
        
        bArea1.content("New Text");
        bulletinManager.write(bArea1);
        // play announcement
        bulletinManager.play();

        // delete announcement 2
        //bulletinManager.delete(2);

        // stop
        //bulletinManager.stop();

        // Sign out
        screen.logout();
    }

    // Play live stream display
    public static void sendWebVideo() throws Exception{
        // Create a screen object for interacting with the control card
        Y2Screen screen = new Y2Screen(Constants.url);
        
        // login
        screen.login("guest","guest");


        // create program
        ProgramPlayFile pf = new ProgramPlayFile(0);

        // create video area
        VideoArea vArea = new VideoArea(0,0,128,96);

        // add live stream video
        VideoAreaPage page = vArea.addVideo("C:\\Users\\91959\\IdeaProjects\\onBonLedDisplay-master\\video.mp4",100);

        // set network video properties
        page.setSource("10");

        // add the video area to the program
        pf.getAreas().add(vArea);

        // update the program
        String list = screen.writePlaylist(pf);
        screen.play(list);

        // Sign out
        screen.logout();
    }

    static void pz_initial_setup(){
        //login and check if screen is okay

        printLog("initial setup url..."+url);

        try {
            screen = new Y2Screen(url);

            printLog("Trying Login");

            // login
            screen.login("guest","guest");

            printLog("Login Success...");

            printLog("Trying Synctime");


            //set date time
            screen.syncTime();

            printLog("SyncTime success...");

            printLog("Try to set font and font-size...");


            font = setFont(textSize);

            //set NTP server
            //SetupNTPServersCmd()
            //clear old com.stackfusion.onbon.data
            //Clear playback resources Delete all display content in the controller,
            //after calling this command, the words "Please add a program" will be displayed on the screen.
            printLog("trying clearing play resources...");

            screen.clearPlayResources();
            printLog("Clearing play resources success");

            printLog("trying changing brightness resources...");

            screen.changeBrightness(display_brightness);

            printLog("Changing brightness resources success");
        }catch (Y2Exception e){
            e.printStackTrace();
            exception_count++;
            printLog(Level.ERROR,"Y2Exception in initializing display  "+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            exception_count++;
            printLog(Level.ERROR,"Exception in initializing display  "+e.getMessage());
        }

    }
    // other commands
    public static void otherCmd() throws Exception{
        // screen
        Y2Screen screen = new Y2Screen(Constants.url);

        // login
        screen.login("guest","guest");

        // other CMD
        // Shutdown command to make the screen black, not physically power off the screen or the controller
        screen.turnOff();

        // The boot command turns the screen from a black state to a bright state
        screen.turnOn();

        // school time command
        screen.syncTime();

        // volume adjustment command volume range 0--100
        screen.changeVolume(60);

        // Brightness adjustment command Brightness range 0--255


        //Clear playback resources Delete all display content in the controller, 
        //after calling this command, the words "Please add a program" will be displayed on the screen.
        screen.clearPlayResources();

        // lock the current screen
        screen.lock();

        // Unlock the current screen
        screen.unlock();

        // Lock program When multiple programs are playing, after calling this command, only the locked program will be displayed
        screen.lockProgram(0);

        // Unlock the program
        screen.unlockProgram(0);

        // take screenshot of screen display
        // Return a picture, the picture is the screenshot of the screen display content at the moment
        // used to debug the screen
        BufferedImage image_screenshot = screen.capture();
    }

    // Modify the login password
    public static void changePassword() throws Exception{
        Y2Screen screen = new Y2Screen(Constants.url);

        screen.login("guest","guest");

        // Modify the login password
        screen.changePassword("guest","123456");

        // Sign out
        screen.logout();

    }

    // update firmware
    public static void updateFirmware() throws Exception{
        Y2Screen screen = new Y2Screen(Constants.url);

        screen.login("guest","guest");

        // update firmware
        screen.updateFirmware("C:\\Users\\Judy\\Desktop\\BX-Y08A_upgrade_V21061600.bxf");

        screen.logout();
    }

    public static void entryGate(WelComeMessageResponse welComeMessageResponse) {


        if (Objects.equals(welComeMessageResponse.getStatus(), "0")){
            entryFastag(welComeMessageResponse);
        }else {
            entryWithoutFastag(welComeMessageResponse);
        }


    }

    private static void entryWithoutFastag(WelComeMessageResponse welComeMessageResponse) {
        printLog("Trying to print entryWithoutFastag\n "+welComeMessageResponse);
        try {
            ProgramPlayFile pf1 = new ProgramPlayFile(1);

            //Welcome TEXT
            TextArea tArea = new TextArea(0,0,width(100),height(50));
            tArea.animation(2,10);
            tArea.setStayTime(3);
            TextAreaTextPage page = tArea.addTextSection("Take Ticket");
            page.setFont(new Y2Font(font));
            page.fgColor(Color.GREEN);
            page.setBgColor(Color.BLACK);
            page.getFont().bold();
            page.setVerticalAlignment(AlignmentType.NEAR);
            page.setHorizontalAlignment(AlignmentType.CENTER);

            pf1.getAreas().add(tArea);
            // vehicle number
            vehicleNumber(pf1,welComeMessageResponse.getVehicleNumber());



            String list = screen.writePlaylist(pf1);
            screen.play(list);
            printLog("Success to print entryWithoutFastag\n "+welComeMessageResponse);
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception to print entryWithoutFastag\n "+welComeMessageResponse+"\n "+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to print entryWithoutFastag\n "+welComeMessageResponse+"\n "+e.getMessage());
//            System.exit(0);
        }

    }

    private static void entryFastag(WelComeMessageResponse welComeMessageResponse)  {

        printLog("Trying to print entry fastag message\n "+welComeMessageResponse);

        try {
            ProgramPlayFile pf1 = new ProgramPlayFile(1);


            // add a picture to the picture area
            PicArea pArea = new PicArea(0,0,width(100), height(50));


            PicAreaPage pPage = pArea.addPage(showImageFromURL("entryFastag", "https://parkzapstatic.s3.ap-south-1.amazonaws.com/banner/Fastag-logo.png"),"png");
            pPage.animation(52,10);
            pPage.setStayTime(0);
            pf1.getAreas().add(pArea);


            // vehicle number
            vehicleNumber(pf1,welComeMessageResponse.getVehicleNumber());



            String list = screen.writePlaylist(pf1);
            screen.play(list);
            printLog("Success to print entry fastag message \n"+welComeMessageResponse);
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception Failed to print entry fastag message\n "+welComeMessageResponse+"\n"+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception Failed to print entry fastag message \n "+welComeMessageResponse+"\n"+e.getMessage());
//            System.exit(0);
        }

    }

    public static void exitGate(DashboardData dashboardData) {


        if (Objects.equals(dashboardData.getAgent(), "fastag")) {
            fastagPayment(dashboardData);
        }else if(Objects.equals(dashboardData.getAgent(), "cash")){
//            cashPaymentWithErrorCode(exitGateDatamessage);
        }else if (Objects.equals(dashboardData.getAgent(), "site")){
            sitePass(dashboardData);
        }


//        screen.logout();
    }
    public static void ticketNotFound(NotFound notFound)  {
        try {
            printLog("Trying to display "+notFound);
            ProgramPlayFile pf1 = new ProgramPlayFile(1);
            TextArea tArea3 = new TextArea(0,0,width(100),height(100));

            tArea3.animation(2,10);
            tArea3.setStayTime(0);
            TextAreaTextPage page3 = tArea3.addTextSection("Show Ticket");

            page3.setFont(new Y2Font(font));
            page3.fgColor(Color.RED);
            page3.setBgColor(Color.BLACK);
            page3.getFont().bold();
            page3.setVerticalAlignment(AlignmentType.CENTER);
            page3.setHorizontalAlignment(AlignmentType.CENTER);

            pf1.getAreas().add(tArea3);



            String list = screen.writePlaylist(pf1);
            screen.play(list);
            printLog("Success to display "+notFound);
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception to display \n"+notFound+"\n"+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to display \n"+notFound+"\n"+e.getMessage());
//            System.exit(0);
        }
    }
    public static void readingMessage(String s) {

        try {
            printLog("Trying to display "+s);
            ProgramPlayFile pf1 = new ProgramPlayFile(1);

            TextArea tArea3 = new TextArea(0,0,width(100),height(100));
//        tArea3.animation(52, 10);
            printLog(s);
            tArea3.animation(63,10);
            tArea3.setStayTime(0);
            TextAreaTextPage page3 = tArea3.addTextSection(s);

            page3.setFont(new Y2Font(font));
            page3.fgColor(Color.YELLOW);
            page3.setBgColor(Color.BLACK);
            page3.getFont().bold();
            page3.setVerticalAlignment(AlignmentType.CENTER);
            page3.setHorizontalAlignment(AlignmentType.CENTER);

            pf1.getAreas().add(tArea3);



            String list = screen.writePlaylist(pf1);
            screen.play(list);
            printLog("Success to display "+s);
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception to display \n"+s+"\n"+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to display \n"+s+"\n"+e.getMessage());
//            System.exit(0);
        }

    }

    public static void loopErrorMessage() {

        try {
            printLog("Trying to display Loop Error ");
            ProgramPlayFile pf1 = new ProgramPlayFile(1);

            TextArea tArea3 = new TextArea(0,0,width(100),height(100));
//        tArea3.animation(52, 10);
            tArea3.animation(63,10);
            tArea3.setStayTime(0);
            TextAreaTextPage page3 = tArea3.addTextSection("Loop Error");

            page3.setFont(new Y2Font(font));
            page3.fgColor(Color.RED);
            page3.setBgColor(Color.BLACK);
            page3.getFont().bold();
            page3.setVerticalAlignment(AlignmentType.CENTER);
            page3.setHorizontalAlignment(AlignmentType.CENTER);

            pf1.getAreas().add(tArea3);



            String list = screen.writePlaylist(pf1);
            screen.play(list);
            printLog("Success to display Loop Error ");
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception to display Loop Error  \n"+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to display Loop Error  \n"+e.getMessage());
//            System.exit(0);
        }

    }

    public static void siteLogo() {
        printLog("Start Loading site logo URL");

        ArrayList<ProgramPlayFile> programPlayFileList = new ArrayList<ProgramPlayFile>();

        try{
            ProgramPlayFile pf1 = new ProgramPlayFile(1);


            // add a picture to the picture area
            PicArea pArea = new PicArea(0,0,width(100),height(100));


            PicAreaPage pPage = pArea.addPage( showImageFromURL("site logo",jetsonData.getSite_logo_url()),"png");
            pPage.animation(2,10);
            pPage.setStayTime(5);
            pf1.getAreas().add(pArea);

            programPlayFileList.add(pf1);

            for(int i=0; i< adsList.size(); i++){
                ProgramPlayFile pf = new ProgramPlayFile(i+2);

                PicAreaPage pPage2 = pArea.addPage( showImageFromURL("ads logo", adsList.get(i).getImage()),"png");
                pPage2.animation(2,10);
                pPage2.setStayTime(5);
                pf.getAreas().add(pArea);

                programPlayFileList.add(pf);
            }





            String list = screen.writePlaylist(programPlayFileList);
            screen.play(list);
            printLog("Site Logo Display successfully");
        }catch (Y2Exception y2Exception){
            exception_count++;
            printLog(Level.ERROR,"y2Exception Site Logo failed to Display ");
//            System.exit(0);
        }catch (Exception exception){
            exception_count++;
            printLog(Level.ERROR,"exception Site Logo failed to Display ");
//            System.exit(0);
        }



    }
    


    public static void fastagPayment(DashboardData dashboardData) {

        try {
            printLog("Trying to display fastagPayment\n"+dashboardData);
            ProgramPlayFile pf1 = new ProgramPlayFile(1);
            ProgramPlayFile pf2 = new ProgramPlayFile(2);

            //PAID TEXT
            TextArea tArea = new TextArea(0,0,width(43.75f),height(37.5f));
            tArea.animation(63,10);
            tArea.setStayTime(0);
            TextAreaTextPage page = tArea.addTextSection("Paid");
            page.setFont(new Y2Font(font));
            page.fgColor(Color.GREEN);
            page.setBgColor(Color.BLACK);
            page.getFont().bold();
            page.setVerticalAlignment(AlignmentType.NEAR);
            page.setHorizontalAlignment(AlignmentType.CENTER);

            pf1.getAreas().add(tArea);
            pf2.getAreas().add(tArea);


            // AMOUNT
            TextArea tArea2 = new TextArea(width(50),0,width(50),height(37.5f));
//        if (dashboardData.getTotal_amount() >=6) {
//            tArea2.animation(52, 15);
//        }else {
//            tArea2.animation(2, 10);
//        }
            tArea2.animation(2,10);
            tArea2.setStayTime(0);
            TextAreaTextPage page2 = tArea2.addTextSection(currencySymbol+String.valueOf(dashboardData.getTotal_amount()));

            page2.setFont(new Y2Font(font));
            page2.fgColor(Color.RED);
            page2.setBgColor(Color.BLACK);
            page2.getFont().bold();
            page2.setVerticalAlignment(AlignmentType.NEAR);
            page2.setHorizontalAlignment(AlignmentType.NEAR);
            pf1.getAreas().add(tArea2);
            pf2.getAreas().add(tArea2);

            PicArea pArea = new PicArea(0,height(50),width(100), height(50));

            // add a picture to the picture area
            PicAreaPage pPage = pArea.addPage(showImageFromURL("fastagPayment", "https://parkzapstatic.s3.ap-south-1.amazonaws.com/banner/Fastag-logo.png"),"png");
            pPage.animation(52,10);
            pPage.setStayTime(0);
            pf1.getAreas().add(pArea);

            // vehicle number
            vehicleNumber(pf2,dashboardData.getVehicle_num());



            String list = screen.writePlaylist(pf1,pf2);
            screen.play(list);
            printLog("Success to display fastagPayment\n"+dashboardData);
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception to display fastagPayment\n"+dashboardData+"\n"+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to display fastagPayment\n"+dashboardData+"\n"+e.getMessage());
//            System.exit(0);
        }

    }

    public static void cashPayment(DashboardData dashboardData) {

        try {
            printLog("Trying to display cashPayment\n"+dashboardData);
            printLog("FASTAG STATUS --------->"+dashboardData.getStatus());

            ProgramPlayFile pf1 = new ProgramPlayFile(1);
            ProgramPlayFile pf2 = new ProgramPlayFile(2);

            //Pay TEXT
            TextArea tArea = new TextArea(0,0,width(43.75f),height(37.5f));
            tArea.animation(2,10);
            tArea.setStayTime(0);
            TextAreaTextPage page = tArea.addTextSection("Pay");
            page.setFont(new Y2Font(font));
            page.fgColor(Color.RED);
            page.setBgColor(Color.BLACK);
            page.getFont().bold();
            page.setVerticalAlignment(AlignmentType.NEAR);
            page.setHorizontalAlignment(AlignmentType.CENTER);

            pf1.getAreas().add(tArea);
            pf2.getAreas().add(tArea);


            // AMOUNT
            TextArea tArea2 = new TextArea(width(50),0,width(50),height(37.5f));
            tArea2.animation(2,10);
            tArea2.setStayTime(0);
            TextAreaTextPage page2 = tArea2.addTextSection(currencySymbol+String.valueOf(dashboardData.getTotal_amount()));

            page2.setFont(new Y2Font(font));
            page2.fgColor(Color.GREEN);
            page2.setBgColor(Color.BLACK);
            page2.getFont().bold();
            page2.setVerticalAlignment(AlignmentType.NEAR);
            page2.setHorizontalAlignment(AlignmentType.NEAR);
            pf1.getAreas().add(tArea2);
            pf2.getAreas().add(tArea2);


            //TODO Reason why fastag not done
            TextArea cashText = new TextArea(0,height(50),width(100),height(50));
            cashText.animation(2,10);
            cashText.setStayTime(1);
            TextAreaTextPage page3 = cashText.addTextSection("CASH");
            page3.setFont(new Y2Font(font));
            page3.fgColor(Color.ORANGE);
            page3.setBgColor(Color.BLACK);
            page3.getFont().bold();
            page3.setVerticalAlignment(AlignmentType.NEAR);
            page3.setHorizontalAlignment(AlignmentType.CENTER);
            pf1.getAreas().add(cashText);

            // vehicle number
            vehicleNumber(pf2,dashboardData.getVehicle_num());

            String list = screen.writePlaylist(pf1,pf2);
            screen.play(list);

            printLog("Success to display cashPayment\n"+dashboardData);
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception to display cashPayment\n"+dashboardData+"\n"+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to display cashPayment\n"+dashboardData+"\n"+e.getMessage());
//            System.exit(0);
        }


    }

    public static void cashPaymentWithErrorCode(ExitGateData exitGateData) {

        try {
            printLog("Trying to display cashPayment\n"+exitGateData);
            printLog("FASTAG STATUS --------->"+exitGateData.getMessage());

            ProgramPlayFile pf1 = new ProgramPlayFile(1);
            ProgramPlayFile pf2 = new ProgramPlayFile(2);

            //CASH TEXT
            TextArea tArea = new TextArea(0,0,width(43.75f),height(37.5f));
            tArea.animation(2,10);
            tArea.setStayTime(0);
            TextAreaTextPage page = tArea.addTextSection("Cash");
            page.setFont(new Y2Font(font));
            page.fgColor(Color.RED);
            page.setBgColor(Color.BLACK);
            page.getFont().bold();
            page.setVerticalAlignment(AlignmentType.NEAR);
            page.setHorizontalAlignment(AlignmentType.CENTER);

            pf1.getAreas().add(tArea);
            pf2.getAreas().add(tArea);


            // AMOUNT
            TextArea tArea2 = new TextArea(width(50),0,width(50),height(37.5f));
            tArea2.animation(2,10);
            tArea2.setStayTime(0);
            TextAreaTextPage page2 = tArea2.addTextSection(currencySymbol+String.valueOf(exitGateData.getAmount()));

            page2.setFont(new Y2Font(font));
            page2.fgColor(Color.GREEN);
            page2.setBgColor(Color.BLACK);
            page2.getFont().bold();
            page2.setVerticalAlignment(AlignmentType.NEAR);
            page2.setHorizontalAlignment(AlignmentType.NEAR);
            pf1.getAreas().add(tArea2);
            pf2.getAreas().add(tArea2);


            //show error code why fastag transaction not done /* Error 1000 */
            Footer footer = exitGateDatamessage.getFooter();
            TextArea errorText = new TextArea(0,height(50),width(100),height(50));
            errorText.animation(52,10);
            errorText.setStayTime(1);

            TextAreaTextPage page3;
            if (!footer.getMessage().isEmpty()){
                page3 = errorText.addTextSection(footer.getMessage());
            }else {
                page3 = errorText.addTextSection(footer.getError_code());
            }
            page3.setFont(new Y2Font(font));
            page3.fgColor(Color.RED);
            page3.setBgColor(Color.BLACK);
            page3.getFont().bold();
            page3.setVerticalAlignment(AlignmentType.CENTER);
            page3.setHorizontalAlignment(AlignmentType.CENTER);
            pf1.getAreas().add(errorText);


            // vehicle number
            vehicleNumber(pf2,exitGateData.getVehicleNumber());

            String list = screen.writePlaylist(pf1,pf2);
            screen.play(list);

            printLog("Success to display cashPayment\n"+exitGateData);
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception to display cashPayment\n"+exitGateData+"\n"+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to display cashPayment\n"+exitGateData+"\n"+e.getMessage());
//            System.exit(0);
        }


    }

    public static void sitePass(DashboardData dashboardData)  {

        try {
            ProgramPlayFile pf1 = new ProgramPlayFile(1);
            ProgramPlayFile pf2 = new ProgramPlayFile(2);
            PicArea pArea = new PicArea(0,0,width(100), height(100));
            // pass text

            TextArea passText = new TextArea(0,0,width(100),height(100));
            PicAreaPage pPage = pArea.addPage(showImageFromURL("sitePass", "https://parkzapstatic.s3.ap-south-1.amazonaws.com/banner/pass_image.png"),"png");
            pPage.animation(52,5);
            pPage.setStayTime(2);
            pf1.getAreas().add(pArea);
            passText.animation(63,10);
            passText.setStayTime(0);

            // vehicle number
            vehicleNumber(pf2,dashboardData.getVehicle_num());



            String list = screen.writePlaylist(pf1,pf2);
            screen.play(list);
        }catch (Y2Exception e){
            exception_count++;
            printLog(Level.ERROR,"Y2Exception to display sitePass\n"+dashboardData+"\n"+e.getMessage());
//            System.exit(0);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to display sitePass\n"+dashboardData+"\n"+e.getMessage());
//            System.exit(0);
        }

    }
    public static void debugLine(boolean l1, boolean l2) throws Y2Exception {


        ProgramPlayFile pf1 = new ProgramPlayFile(1);
        // loop1
        TextArea tArea = new TextArea(0, 35, 40, 5);
        tArea.animation(63, 10);
        tArea.setStayTime(0);
        TextAreaTextPage page = tArea.addTextSection("__");
        page.setFont(new Y2Font(font));
        page.getFont().bold();
        page.setVerticalAlignment(AlignmentType.NEAR);
        page.setHorizontalAlignment(AlignmentType.NEAR);
        if (l1) {
            page.fgColor(Color.GREEN);
            page.setBgColor(Color.GREEN);
        }else {
            page.fgColor(Color.RED);
            page.setBgColor(Color.RED);
        }

        // loop2
        TextArea tArea2 = new TextArea(40,35,40,5);
        tArea2.animation(63,10);
        tArea2.setStayTime(0);
        TextAreaTextPage page2 = tArea2.addTextSection("__");
        page2.setFont(new Y2Font(font));
        page2.getFont().bold();
        page2.setVerticalAlignment(AlignmentType.NEAR);
        page2.setHorizontalAlignment(AlignmentType.NEAR);
        if (l2) {
            page2.fgColor(Color.GREEN);
            page2.setBgColor(Color.GREEN);
        }else {
            page2.fgColor(Color.RED);
            page2.setBgColor(Color.RED);
        }

        pf1.getAreas().add(tArea);
        pf1.getAreas().add(tArea2);

        String list = screen.writePlaylist(pf1);
        screen.play(list);


        screen.logout();
    }
    public static void loopDetector(LocalFisrtModel localFisrtModel) throws Y2Exception {
        ProgramPlayFile pf1 = new ProgramPlayFile(1);

        TextArea tArea3 = new TextArea(0,20,80,20);
        tArea3.animation(52, 10);

//        tArea3.animation(2,10);
        tArea3.setStayTime(0);
        TextAreaTextPage page3 = tArea3.addTextSection("Please Wait");

        page3.setFont(new Y2Font(font));
        page3.fgColor(Color.RED);
        page3.setBgColor(Color.BLACK);
        page3.getFont().bold();
        page3.setVerticalAlignment(AlignmentType.NEAR);
        page3.setHorizontalAlignment(AlignmentType.NEAR);

        pf1.getAreas().add(tArea3);



        String list = screen.writePlaylist(pf1);
        screen.play(list);
    }

    private static void vehicleNumber(ProgramPlayFile pf1,String vehicleNumber){
        // vehicle number
        try {
            Font font1 = setFont(12f);
            String localVehicleNumber = "";

            localVehicleNumber = vehicleNumber;
            TextArea vehicleNumberLast4Digit = new TextArea(50, 20, width(37.f), height(50));
            TextArea textArea1 = new TextArea(0, 20, width(62), height(50));
            vehicleNumberLast4Digit.animation(2, 10);
            if (vehicleNumber.length() <=10){
                textArea1.animation(2, 10);
            }else{
                textArea1.animation(52, 10);
            }



            vehicleNumberLast4Digit.setStayTime(4);
            TextAreaTextPage page4 = vehicleNumberLast4Digit.addTextSection(localVehicleNumber.substring(localVehicleNumber.length() - 4));

            textArea1.setStayTime(3);
            TextAreaTextPage page5 = textArea1.addTextSection(localVehicleNumber.substring(0, localVehicleNumber.length() - 4));
            page4.setFont(new Y2Font(font1));
            page4.fgColor(Color.YELLOW);
            page4.setBgColor(Color.BLACK);
            page4.getFont().bold();
            page4.setVerticalAlignment(AlignmentType.CENTER);
            page4.setHorizontalAlignment(AlignmentType.FAR);

            page5.setFont(new Y2Font(font1));
            page5.fgColor(Color.YELLOW);
            page5.setBgColor(Color.BLACK);
            page5.getFont().bold();
            page5.setVerticalAlignment(AlignmentType.CENTER);
            page5.setHorizontalAlignment(AlignmentType.NEAR);
            pf1.getAreas().add(vehicleNumberLast4Digit);
            pf1.getAreas().add(textArea1);
        }catch (Exception e){
            exception_count++;
            printLog(Level.ERROR,"Exception to display Vehicle Number \n"+vehicleNumber+"\n"+e.getMessage());
        }
    }




}
