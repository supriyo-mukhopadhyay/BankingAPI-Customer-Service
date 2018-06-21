package com.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.exception.InvalidUserException;
import com.service.layer.BankingService;

@Path("/userService")
public class RestAPI {
	
	private BankingService service = new BankingService();
	static
	{
		System.out.println("Hello from rest controller");
	}
		
//	@GET
//	@Path("/details")
//	public void testMethod()
//	{
//		System.out.println("Hello from test method");
//	}
	
//	@GET
//	@Path("/details/{id}/{ifsc}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public UserBankDetails getDetails(@PathParam("id") int empId,
//			@PathParam("ifsc") int ifsc) throws Exception
//	{
//		return service.getUserBankDetails(empId, ifsc);
//	}
	
	
	@GET
	@Path("/details/{ifsc}/{accNo}/{balance}/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public String registration(@PathParam("ifsc") int ifsc,@PathParam("accNo") int accNo,
			@PathParam("balance") int bal,@PathParam("uid") int uid) throws Exception
	{
		String s = "success";
		//System.out.println("helloknsd  kn");
		service.userBankRegistration(ifsc,accNo, bal, uid);
		return s;
		
	}
	
//	@POST
//	@Path("/details")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public UserBankDetails registration() throws Exception
//	{
//		System.out.println("Heloo from");
//		UserBankDetails bankDetails = new UserBankDetails();
//		int ifsc = service.bankIfsc(bankDetails.getBankName());
//		int uid = service.getUid(bankDetails.getName());
//		service.userBankRegistration(ifsc,bankDetails.getAccountNumber(), bankDetails.getBalance(), uid);
//		
//		return null;
//	}

	@GET
	@Path("/details/{name}/{bal}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateDetails(
			@PathParam("name") String name,@PathParam("bal") int bal) throws Exception
	{
		int uid =0;
		String s ="sucess";
		uid = service.getUid(name);
		if(uid != 0)
		{
			service.updateAccountDetails(name, bal);
			return s;
		}
		else
		{
			throw new InvalidUserException("User does not exist!! Please register");
		}
	}
}
