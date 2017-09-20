package com.raspberry;

import org.apache.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class MotorShield {

	Logger logger = Logger.getLogger(MotorShield.class);

	/* Shift register */
	private GpioPinDigitalOutput DIR_LATCH = null;
	private GpioPinDigitalOutput DIR_CLK = null;
	private GpioPinDigitalOutput DIR_SER = null;

	/* constants used in dictionary */
	private static Integer PIN = 1;
	private static Integer DIRECTION = 2;
	private static Integer IS_RUNNING = 3;
	private static Integer RUNNING_DIRECTION = 4;
	private static Integer PWM = 5;
	private static Integer PWM_FREQUENCY = 6;
	private static Integer PWM_DUTY_CYCLE = 7;

	private Integer clockwise = 0;
	private Integer counterclockwise = 1;
	private Integer stop = 2;

	int[] motor1 = { 4, 8, 4 + 8 };
	int[] motor2 = { 2, 16, 2 + 16 };
	int[] motor3 = { 32, 128, 32 + 128 };
	int[] motor4 = { 1, 64, 1 + 64 };

	private Motor[] motors = { new Motor(1, motor1, Boolean.FALSE, 10, 100), new Motor(2, motor2, Boolean.FALSE, 10, 100),
			new Motor(3, motor3, Boolean.FALSE, 10, 100), new Motor(4, motor4, Boolean.FALSE, 10, 100) };

	private GpioController gpio;

	public MotorShield() {

		gpio = GpioFactory.getInstance();

	}

	public Boolean testShiftPins() {
		if (DIR_LATCH == null)
			return false;
		if (DIR_CLK == null)
			return false;
		if (DIR_SER == null)
			return false;
		return true;
	}

	public void exit() {
		if (testShiftPins()) {
			shiftWrite(0);
			stopMotors(Motor.DC_MOTOR1, Motor.DC_MOTOR2, Motor.DC_MOTOR3, Motor.DC_MOTOR4);
		}
		gpio.shutdown();
	}

	public void shiftWrite(int value) {
		if (!testShiftPins()) {
			logger.error("PINs for shift register were not set properly.");
		}

		DIR_LATCH.setState(PinState.LOW);

		for (int j = 0; j < 8; j++) {
			int temp = value & 0x80;
			DIR_CLK.setState(PinState.LOW);
			if (temp == 0x80) {
				DIR_SER.setState(PinState.HIGH);
			} else {
				DIR_SER.setState(PinState.LOW);
			}
			DIR_CLK.setState(PinState.HIGH);
			value <<= 0x01;
		}

		DIR_LATCH.setState(PinState.HIGH);

	}

	private void stopMotors(int... motors) {

	}

	public void setL293Dpins(int pin1, int pin2, int pin3, int pin4) {
		motors[0].initPin(pin1, gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(pin1)));
		motors[1].initPin(pin2, gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(pin2)));
		motors[2].initPin(pin3, gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(pin3)));
		motors[3].initPin(pin4, gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(pin4)));
	}

	public void set74HC595pins(int latch, int clk, int ser) {
		DIR_LATCH = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(latch), "latch");
		DIR_CLK = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(clk), "clk");
		DIR_SER = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(ser), "ser");
	}

	public Direction getMotorDirection(int motor, int directionIndex) {
		int directionValue = motors[motor].getDirection()[directionIndex];

		int allMotorsDirection = directionValue;

		for (Motor motorTemp : motors) {
			if (motorTemp.getId() == motor) {
				continue;
			}
			if (motors[motorTemp.getId()].getDirection() != null) {
				allMotorsDirection += motors[motorTemp.getId()].getRunningDirection();
			}
		}

		return new Direction(directionValue, allMotorsDirection);
	}

	public void setPwmFrequency(int[] motorFreq) {

		for (int i = 0; i < motors.length; i++) {
			Motor motor = motors[i];
			motor.setPwmFrequency(motorFreq[i]);
		}

	}

	public void getPwmFrequency() {

		int[] freqs = new int[4];
		for (int i = 0; i < motors.length; i++) {
			Motor motor = motors[i];
			freqs[i] = motor.getPwmFrequency();
		}
	}

	public void runMotor(int motor){
		runMotor(motor, false, null);
	}
	
	public void runMotor(int motor, boolean clockWise, Integer speed) {
		
		
		
	}

}
