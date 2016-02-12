package org.usfirst.frc.team904.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String portCullis = "Portcullis";
    final String chevalDeFrise = "Cheval de Frise";
    final String moat = "Moat";
    final String ramparts = "Ramparts";
    final String drawbridge = "Drawbridge";
    final String sallyPort = "Sally Port";
    final String rockWall = "Rock Wall";
    final String roughTerrain = "Rough Terrain";
    final String nullMove = "Don't Go";
    final String one = "1";
    final String two = "2";
    final String three = "3";
    final String four = "4";
    String autoSelected;
    SendableChooser chooser, positionChooser;
 
    Joystick driver, operator;
    CANTalon hawk, eagle;
    //These will probably be talons so that we can have greater control.
    //Relay ostrich,
    //penguin;
    
    double motorLeft, motorRight, 
    yLeft, yRight, zLeft, zRight,
    scaleFactor,
    voltage,
    arm, shoot,
    autonLoop;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Portcullis", portCullis);
        chooser.addObject("Cheval de Frise", chevalDeFrise);
        chooser.addObject("Moat", moat);
        chooser.addObject("Ramparts", ramparts);
        chooser.addObject("Drawbridge", drawbridge);
        chooser.addObject("Sally Port", sallyPort);
        chooser.addObject("Rock Wall", rockWall);
        chooser.addObject("Rough Terrain", roughTerrain);
        SmartDashboard.putData("Auto choices", chooser);
        
        positionChooser = new SendableChooser();
        positionChooser.addDefault("Don't Go", nullMove);
        positionChooser.addObject("1", one);
        positionChooser.addObject("2", two);
        positionChooser.addObject("3", three);
        positionChooser.addObject("4", four);
        SmartDashboard.putData("Position choices", positionChooser);
        
        //Init Joysticks
        driver = new Joystick(0);
        operator = new Joystick (1);
        //Init stuff
        hawk = new CANTalon(2);
        eagle = new CANTalon(3);
        //ostrich = new  Relay(0);
        //penguin = new Relay (1);
        }
    
 /**
  * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
  * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
  * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
  * below the Gyro
  *
  * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
  * If using the SendableChooser make sure to add them to the chooser code above as well.
  */
    public void autonomousInit() {
     autoSelected = (String) chooser.getSelected();
     System.out.println("Auto selected: " + autoSelected);
     autonLoop = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
     switch(autoSelected) {
     case portCullis:
    	//Drive to portcullis
    	//start raising arms
    	//Drive forward while raising arms
    	//Stop raising arms when at the top
    	//Drive forward
    	//Lower arms
      break;
     case chevalDeFrise:
    	//Drive to thing
    	//lower arms to put things to the floor
    	 	//stop this when the current pull is higher?
    	//drive forward with arm lowered
    	//raise arms
    	//keep driving
      break;
     case moat:
    	 if(autonLoop < 250){
    		 autonDrive(.375);
      	   	 autonLoop ++;
      	 }
      	 else{
      	     autonDrive(0.0);
      	 }
      break;
     case ramparts:
    	 if(autonLoop < 250){
      	   	 autonDrive(.375);
      	   	 autonLoop ++;
      	 }
      	 else{
      		 autonDrive(0.0);
      	 }
      break;
     case drawbridge:
    	 //Drive to drawbridge
    	 //start lowering gate
    	 //drive backward and lower gate
    	 //at floor, stop lowering, keep driving
    	 //keep driving
      break;
     case sallyPort:
    	 //Drive to sallyPort
    	 //start lifting gate
    	 //drive forward and lift gate
    	 //at max point, stop lifting, keep driving
    	 //keep driving
      break;
     case rockWall:
    	 if(autonLoop < 250){
   	   	  autonDrive(.375);
   	   	  autonLoop ++;
   	     }
   	     else{
   	   	  autonDrive(0.0);
   	     }
    	 break;
     case roughTerrain:
		 if(autonLoop < 250){
	   	  autonDrive(.375);
	   	  autonLoop ++;
	     }
	     else{
	   	  autonDrive(0.0);
	     }
		 break;
     default:
     //Put default auto code here
      //Do Nothing if none of these are selected
            break;
     }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drivebase();
        //armDrive();
        //shoot();
    }
    /**
     * This function controls the drive during Teleop mode
     */
    public void drivebase(){
    	if((-driver.getY() > -.1) && (-driver.getY() < .1)){
    		yLeft = 0;
    		yRight = 0;
    	}
    	else{
    		yLeft = -1.11*driver.getY()+.11;
    		yRight = -1.11*driver.getY()+.11;
    	}
    	
    	if((driver.getZ() < -.1) || (driver.getZ() > .1)){
    		zLeft = driver.getZ();
    		zRight = -driver.getZ();
		}
    	else{
    		zLeft = 0;
    		zRight = 0;
    	}
    	motorLeft = yLeft + zLeft;
    	motorRight = yRight + zRight;
    	if((Math.max(motorLeft, motorRight) > 1)){
    		scaleFactor = Math.max(motorLeft, motorRight);
    	}
    	else{
    		scaleFactor = 1;
    	}
    	motorLeft = motorLeft/scaleFactor;
    	motorRight = motorRight/scaleFactor;
        SmartDashboard.putNumber("Voltage Right", eagle.getOutputCurrent());
        SmartDashboard.putNumber("Voltage Left", hawk.getOutputCurrent());
        SmartDashboard.putNumber("Left Motor", motorLeft);
        SmartDashboard.putNumber("Right Motor", -motorRight);
        SmartDashboard.putNumber("Scale Factor", scaleFactor);
        
    	hawk.set(motorLeft);
        eagle.set(-motorRight);
    }
    
    /**
     * This Function controls the autonomous drive
     */
    public void autonDrive(double speed){
    	hawk.set(speed);
        eagle.set(-speed);
    }
    /**

     * This Function controls the arms during Teleop
     * 
    public void armDrive(){
     arm = operator.getRawAxis(0);
     if(Math.abs(arm) < .1){
      arm = 0;
     }
     else if(arm > .1){
     ostrich.set(1);
    }
    else{
        ostrich.set(-1);
    }
    **/
    /**
     * This function controls the shooting mechanism
     * 
    public void shoot(){
     shoot = operator.getRawAxis(1);
     if(Math.abs(shoot) < .1){
      shoot = 0;
     }
     else if(shoot > .1){
         shoot = 1;
     }
     else{
         shoot = -1;
     }
     penguin.set(shoot);
    }
    **/
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
