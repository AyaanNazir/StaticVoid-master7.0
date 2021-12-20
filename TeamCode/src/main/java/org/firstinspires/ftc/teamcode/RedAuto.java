package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class RedAuto extends LinearOpMode{
    private DcMotorEx leftFront, leftBack, rightFront, rightBack, intake, outtake, delivery1, delivery2;
    private Servo duck, pully;
    private DcMotorEx[] motors;
    private final double TPI = 33.5625;
    private ElapsedTime timer;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        drive(.8, 100);
        strafe(.6, 1350);
        duck.setPosition(-.7);
        intakeBox();
        duck.setPosition(0.5);
        strafe(.8, 100);
        drive(.8, 350);

    }
    //sebby is stronk
    public void initialize() {
        leftFront = (DcMotorEx) hardwareMap.dcMotor.get("FL");
        leftBack = (DcMotorEx) hardwareMap.dcMotor.get("BL");
        rightFront = (DcMotorEx) hardwareMap.dcMotor.get("FR");
        rightBack = (DcMotorEx) hardwareMap.dcMotor.get("BR");
        intake = (DcMotorEx) hardwareMap.dcMotor.get("intake");
        delivery1 = (DcMotorEx) hardwareMap.dcMotor.get("delivery1");
        delivery2 = (DcMotorEx) hardwareMap.dcMotor.get("delivery2");
        outtake = (DcMotorEx) hardwareMap.dcMotor.get("outtake");
        duck = (Servo) hardwareMap.get("duck");
        pully = (Servo) hardwareMap.get("pully");

        motors = new DcMotorEx[]{leftFront, leftBack, rightFront, rightBack};

        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftBack.setDirection(DcMotorEx.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //pully.setPosition(0);

        waitForStart();
    }

    public void drive(double power, int time) throws InterruptedException {
        timer = new ElapsedTime();
        for (DcMotorEx motor : motors) {
            motor.setPower(power);
        }
        while (timer.milliseconds() <= time) {
            if (!opModeIsActive()) {
                throw new InterruptedException();
            }
        }
        for (DcMotorEx motor : motors) {
            motor.setPower(0);
        }
    }

    public void strafe(double direction, int time) throws InterruptedException {
        timer = new ElapsedTime();
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        for (DcMotorEx motor : motors) {
            motor.setPower(1*direction);
        }
        while (timer.milliseconds() <= time) {
            if (!opModeIsActive()) {
                throw new InterruptedException();
            }
        }
        for (DcMotorEx motor : motors) {
            motor.setPower(0);
        }
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void turn(int power, int time) throws InterruptedException {
        timer = new ElapsedTime();
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        for (DcMotorEx motor : motors) {
            motor.setPower(power);
        }
        while (timer.milliseconds() <= time) {
            if (!opModeIsActive()) {
                throw new InterruptedException();
            }
        }
        for (DcMotorEx motor : motors) {
            motor.setPower(0);
        }
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftBack.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void intakeBox() throws InterruptedException {
        timer = new ElapsedTime();
        /*
        intake.setPower(-1);
        delivery1.setPower(1);
        delivery2.setPower(-1);
        */
        while(timer.milliseconds() <= 5000)
            if (!opModeIsActive()) {
                throw new InterruptedException();
            }
        /*
        intake.setPower(0);
        delivery1.setPower(0);
        delivery2.setPower(0);
        */
    }

    public void dropDuck() throws InterruptedException {
        duck.setPosition(1);
    }

    public void raisePully() throws InterruptedException {
        timer = new ElapsedTime();
        outtake.setPower(1);
        while(timer.milliseconds() <= 1500)
        {
            if (!opModeIsActive()) {
                throw new InterruptedException();
            }
        }
        outtake.setPower(0);
        pully.setPosition(0);
        while(timer.milliseconds() <= 2500)
        {
            if (!opModeIsActive()) {
                throw new InterruptedException();
            }
        }
    }

    public void dropPully() throws InterruptedException {
        timer = new ElapsedTime();
        pully.setPosition(1);
        while(timer.milliseconds() <= 1000)
            if (!opModeIsActive()) {
                throw new InterruptedException();
            }
        outtake.setPower(-1);
        while(timer.milliseconds() <= 2000)
        {
            if (!opModeIsActive()) {
                throw new InterruptedException();
            }
        }
        outtake.setPower(0);
    }
}
