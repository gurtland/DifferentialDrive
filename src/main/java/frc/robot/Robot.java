// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {
    XboxController xbox = new XboxController(Constants.DifferentialDrive.joystickPort);

    SparkMax left1 = new SparkMax(Constants.DifferentialDrive.leftMotor1, MotorType.kBrushless);
    SparkMax left2 = new SparkMax(Constants.DifferentialDrive.leftMotor2, MotorType.kBrushless);
    SparkMax right1 = new SparkMax(Constants.DifferentialDrive.rightMotor1, MotorType.kBrushless);
    SparkMax right2 = new SparkMax(Constants.DifferentialDrive.rightMotor2, MotorType.kBrushless);

    DifferentialDrive drive1 = new DifferentialDrive(left1, right1);

    SparkMaxConfig left1Config = new SparkMaxConfig();
    SparkMaxConfig left2Config = new SparkMaxConfig();

    SparkMaxConfig right1Config = new SparkMaxConfig();
    SparkMaxConfig right2Config = new SparkMaxConfig();

    public Robot() {
        left1.configure(left1Config, ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        left2.configure(left2Config, ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        left2Config.follow(left1);
        left1Config.inverted(true);

        right1.configure(right1Config, ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        right2.configure(right2Config, ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);

        right2Config.follow(right1);
        right1Config.inverted(false);
    }

    /** This function is run once each time the robot enters autonomous mode. */
    @Override
    public void autonomousInit() {}

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}

    /** This function is called once each time the robot enters teleoperated mode. */
    @Override
    public void teleopInit() {}

    /** This function is called periodically during teleoperated mode. */
    @Override
    public void teleopPeriodic() {
        drive1.tankDrive(xbox.getLeftY(), xbox.getRightY());
    }

    /** This function is called once each time the robot enters test mode. */
    @Override
    public void testInit() {}

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
