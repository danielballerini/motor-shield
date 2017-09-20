package com.raspberry;

import com.pi4j.io.gpio.GpioPinPwmOutput;

public class Motor {
	/* Motor numbering */
	public static int DC_MOTOR1 = 1;
	public static int DC_MOTOR2 = 2;
	public static int DC_MOTOR3 = 3;
	public static int DC_MOTOR4 = 4;

	private int id;
	private Integer pin;
	private int []direction;
	private Boolean isRunning;
	private Integer runningDirection;
	private Integer pwm;
	private Integer pwmFrequency;
	private Integer pwmDutyCicle;
	
	private GpioPinPwmOutput pinPwmOutput;
	
	public Motor(int id, int[] direction, Boolean isRunning, Integer pwmFrequency, Integer pwmDutyCicle) {
		super();
		this.setId(id);
		this.direction = direction;
		this.isRunning = isRunning;
		this.pwmFrequency = pwmFrequency;
		this.pwmDutyCicle = pwmDutyCicle;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public int[] getDirection() {
		return direction;
	}

	public void setDirection(int[] direction) {
		this.direction = direction;
	}

	public Boolean getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}

	public Integer getRunningDirection() {
		return runningDirection;
	}

	public void setRunningDirection(Integer runningDirection) {
		this.runningDirection = runningDirection;
	}

	public Integer getPwm() {
		return pwm;
	}

	public void setPwm(Integer pwm) {
		this.pwm = pwm;
	}

	public Integer getPwmFrequency() {
		return pwmFrequency;
	}

	public void setPwmFrequency(Integer pwmFrequency) {
		this.pwmFrequency = pwmFrequency;
	}

	public Integer getPwmDutyCicle() {
		return pwmDutyCicle;
	}

	public void setPwmDutyCicle(Integer pwmDutyCicle) {
		this.pwmDutyCicle = pwmDutyCicle;
	}

	public void initPin(int pinOut, GpioPinPwmOutput pinOutput) {
		setPin(pinOut);
		pinOutput = pinOutput;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
