package com.e_commerce.UserService.UserDto;



import com.e_commerce.UserService.Entity.User;

public class UserDto {
	
	private User user;
	private String orderStatus;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	

}
