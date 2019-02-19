package com.squapl.sa.web.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.squapl.sa.domain.Category;
import com.squapl.sa.domain.Organiser;
import com.squapl.sa.domain.Participant;
import com.squapl.sa.domain.Participantsrepo;
import com.squapl.sa.domain.Region;
import com.squapl.sa.domain.Tournament;
import com.squapl.sa.jparepository.RoleDao;
import com.squapl.sa.service.RegistrationService;
import com.squapl.sa.util.APIResponse;
import com.squapl.sa.util.Helper;
import com.squapl.sa.util.MailClient;



	@RestController
	@RequestMapping("/api")
	public class RegistrationRestController {
		private static final Logger logger = LoggerFactory.getLogger(RegistrationRestController.class);
		
		private static final String SALT = "salt"; // Salt should be protected carefully
		
		@Autowired
		RegistrationService registrationservice;
		
		@Autowired
		 private Helper helper;
		
		@Autowired
	    private RoleDao roleDao;
		
		@Autowired
		private MailClient mailclient;
		
		@GetMapping(value="/getcategories/all",
				produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Iterable<Category>> findall() {
			Iterable<Category> categories = registrationservice.getAllCategories();
			return new ResponseEntity<Iterable<Category>>(categories,HttpStatus.OK);
		}
		
		@GetMapping(value="/getallactivetournaments/all",
				produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Iterable<Tournament>> getAllActiveTournaments() {
			Iterable<Tournament> tournaments = registrationservice.getAllActiveTournaments();
			return new ResponseEntity<Iterable<Tournament>>(tournaments,HttpStatus.OK);
		}
		
		
		 
		@GetMapping(value="/getcategoriesbyname/{groupname}",
				produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Iterable<Category>> getCategoriesforGroup(@PathVariable("groupname") String groupname) {
			Iterable<Category> categories = registrationservice.getCategoriesforGroup(groupname);
			return new ResponseEntity<Iterable<Category>>(categories,HttpStatus.OK);
		}
		
		
		
		@GetMapping(value="/getcategoriesbytournament/{tournamentid}",
		produces=MediaType.APPLICATION_JSON_VALUE)

public ResponseEntity<Iterable<Category>> getCategoriesforTournament(@PathVariable("tournamentid") String tournamentid) {
	Iterable<Category> categories = registrationservice.getCategoriesforTournament(tournamentid);
	return new ResponseEntity<Iterable<Category>>(categories,HttpStatus.OK);
}
		
		
		
		
		@GetMapping(value="/getallareasbyregion/{regionname}",
				produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Region> getallareasbyregion(@PathVariable("regionname") String regionname) {
			Region region = registrationservice.getallareasbyregion(regionname);
			return new ResponseEntity<Region>(region,HttpStatus.OK);
		}
		
		@GetMapping(value="/getregions/all",
				produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Iterable<Region>> getallregions() {
			Iterable<Region> categories = registrationservice.getAllRegions();
			return new ResponseEntity<Iterable<Region>>(categories,HttpStatus.OK);
		}
		
		@GetMapping(value="/getorganisers/all",
				produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Iterable<Organiser>> getallorganisers() {
			Iterable<Organiser> organisers = registrationservice.getAllOrganisers();
			return new ResponseEntity<Iterable<Organiser>>(organisers,HttpStatus.OK);
		}
		
		@GetMapping(value="/getallparticipantsbytournamentid/{tournamentid}",
				produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Iterable<Participant>> getallParticipantsbyTournamentID(@PathVariable("tournamentid") String tournamentid) {
			Iterable<Participant> participants = registrationservice.getallParticipantsbyTournamentID(tournamentid);
			return new ResponseEntity<Iterable<Participant>>(participants,HttpStatus.OK);
		}
		
		@GetMapping(value="/gettournaments/all",
				produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Iterable<Tournament>> getalltournaments() {
			Iterable<Tournament> tournaments = registrationservice.getAllTournaments();
			return new ResponseEntity<Iterable<Tournament>>(tournaments,HttpStatus.OK);
		}
		
		
		
		@GetMapping(value="/gettournamentbyid/{tournamentid}",
		produces=MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<Tournament> getTournamentByid(@PathVariable("tournamentid") String tournamentid) {
			Tournament tournament = registrationservice.getTournamentByID(tournamentid);
			return new ResponseEntity<Tournament>(tournament,HttpStatus.OK);
		}
		
		  @Transactional
			 @RequestMapping(value = "/participants/register", method = RequestMethod.POST)
			    public ResponseEntity  participantAdd(  @RequestBody String stringToParse, BindingResult bindingResult) throws IOException
			    {
			 
		        logger.info("IN >> participants/register");
				JSONObject result = helper.getJsonObj(stringToParse);
				logger.info("PARAMS >> " + result.toJSONString());
				logger.debug("PARAMS >> " + result.toJSONString());
				
				System.out.println("INPUT .. " + stringToParse);
				System.out.println("RESULT .. " + result.toJSONString());
				
				String refrencecode = "";
				
				 try {
				// String region = (String)result.get("region");
				 
				 JSONObject jsonCatArray = (JSONObject) result.get("category");
				 String category = (String)jsonCatArray.get("name");
				 
				// String category = (String)result.get("category");
				 
//				  JSONArray jsonCatArray = null;
//				  jsonCatArray = (JSONArray) result.get("category");
//				  JSONObject tempcategory = (JSONObject)jsonCatArray.get(0);
//	        		 String category = (String)tempcategory.get("name");
				 
		    //     String category = (String)result.get("category");
		         
		     //    String area = (String)result.get("area");
		         String regfirstname = (String)result.get("firstname");
	        		 String reglastname = (String)result.get("lastname");
	        		 String reguseremail = (String)result.get("emailid");
		       
		         String tournamentid = (String)result.get("tournamentid");
		         
		     //    String teamname = (String)result.get("teamname");
		         String companyname = (String)result.get("companyname");
		         String regcontactno = (String)result.get("regphno");
		         
		       //  registrationservice.get
		         
		         Tournament tournament = registrationservice.getTournamentByID(tournamentid);
		         String tournamentshortcode = tournament.getShortcode();
		         
		          refrencecode = tournamentshortcode + Helper.getRandomAlphaNumericString(5);
		         
		         
		         
		         JSONArray jsonArray = null;
		       //  JSONParser parser = new JSONParser();
		         
		         jsonArray = (JSONArray) result.get("participants");
		            
		         int participantscount = jsonArray.size();
		         
		        	 for(int i= 0; i< participantscount; i++) {
		        		 Participant tempparticipant = new Participant();
		        	//	 tempparticipant.setArea(area);
		        	//	 tempparticipant.setRegion(region);
		        		
		        		 
		        		 tempparticipant.setCategory(category);
		        		 tempparticipant.setTournamentid(Long.parseLong(tournamentid));
		        		 tempparticipant.setRegfirstname(regfirstname);
		        		 tempparticipant.setReglastname(reglastname);
		        		 tempparticipant.setReguseremail(reguseremail);
		        		 tempparticipant.setRefrencecode(refrencecode);
		        		 
		        	//	 tempparticipant.setTeamname(teamname);
		        		 tempparticipant.setCompanyname(companyname);
		        		 tempparticipant.setRegcontactno(regcontactno);
		        		 
		        		 
		        		 JSONObject tempparticipantinfo = (JSONObject)jsonArray.get(i);
		        		 String gender = (String)tempparticipantinfo.get("sex");
		        		 tempparticipant.setGender(gender);
		        		 
		        		 String nricname = (String)tempparticipantinfo.get("nricname");
		        		 tempparticipant.setNricname(nricname);
		        		 
		        		 String nric = (String)tempparticipantinfo.get("nric");
		        		 tempparticipant.setNricno(nric);

		        		 if(category!= null && category.equalsIgnoreCase("Directors")) {

							 String designation = (String) tempparticipantinfo.get("designation");
							 tempparticipant.setDesignation(designation);


							 String dob = (String) tempparticipantinfo.get("dob");

							 tempparticipant.setDob(Helper.dateConversion(dob));
//							 String phno = (String) tempparticipantinfo.get("phno");
//							 tempparticipant.setContactno(phno);
//
//							 String email = (String) tempparticipantinfo.get("email");
//							 tempparticipant.setEmailid(email);
						 }
		        		 
		        		 String tshirtsize = (String)tempparticipantinfo.get("tshirtsize");
		        		 tempparticipant.setTshirtsize(tshirtsize);
		        		 
		        		 
		        		 tempparticipant.setRegdatetime(new Date());
		        	
		        		 
		        		 
		        		 Participant participant = registrationservice.saveParticipant(tempparticipant);
		        		 
		        		 if(participant != null) {
		        			 logger.debug("1..");
		        			 int participantflag = registrationservice.checkifparticipantexist(participant.getNricno());
		        			 logger.debug("2.." + participantflag);
		        			 
		        			 if(participantflag == 0 ) {
		        				 Participantsrepo participantsrepo = registrationservice.addparticipanttorepo(participant);
		        			 }
		        			 
		        		 }
		        		 
		        		 
		        		 logger.info("PARTICIPANT OBJECT >>"+ participant.toString());
		        	 }
		        	 
		        	 
		        	 
//		        	 String emailHTML = "Thankyou for Registring. We have received your registration details for : DNV GL Mariners Cup" + 
//		        			 "Your reference code is : " + refrencecode ;
		        	 		String emailHTML = formatRegistrationEmail(regfirstname, category, companyname, refrencecode);
		        	 
		        	 mailclient.prepareAndSendWithFormat(reguseremail, "DNV GL Mariners Cup 2019 - Registration", emailHTML);
		         

				 } catch (Exception e) {
				 	e.printStackTrace();
					 logger.debug("ABCD>>>>>" + e.getMessage());
					 return new ResponseEntity<>(new APIResponse("ERR_CREATE_PARTICIPANT",HttpStatus.OK, ""), HttpStatus.OK);	
				 }
				 
				
				 
				 logger.debug(">>>>>>>>>"+ refrencecode);
				 return new ResponseEntity<>(new APIResponse("SUCCESS",HttpStatus.OK, refrencecode), HttpStatus.OK);	 
			    }
		  
		  private String formatRegistrationEmail(String name, String categoryname, String companyname, String refrencecode) {
			  StringBuffer stb = new StringBuffer();
			  stb.append("Dear <b>"+ StringUtils.capitalize(name) + "</b>,");
			  stb.append("\n<br><br>");
			  stb.append("Thankyou for registering with <b>DNV GL Mariners Cup 2019 </b> for Category - <b>"+ categoryname + "</b>.");
			  stb.append("\n<br><br>");
	          stb.append("Your company <b>" + companyname + "</b> will be accepted upon completion of payment formalities latest by <b>5th March 2019</b>.");
	          stb.append("\n<br><br>");
	          stb.append("Please use the following reference code <b>" + refrencecode + "</b> while making payment.");
	          stb.append("\n<br><br>");
	          
	          stb.append("Thanks and Regards,");
	          stb.append("\n<br>");
	          stb.append("Organizing Committee.");
	          return stb.toString();
					  
		  }
		  
		  
			@GetMapping(value="/getregisteredstatusbyreferencecode/{referencecode}",
					produces=MediaType.APPLICATION_JSON_VALUE)

			public ResponseEntity<Iterable<Participant>> getRegisteredStatusbyReferenceCode(@PathVariable("referencecode") String referencecode) {
				Iterable<Participant> tempparticipant = registrationservice.getRegisteredStatusbyReferenceCode(referencecode);
				return new ResponseEntity<Iterable<Participant>>(tempparticipant,HttpStatus.OK);
			}
			
			
//			 @GetMapping(path = "/download/allregistrations", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//			    public ResponseEntity getFile() {
//			        String exportedContent = "Hello, World!";
//			        
//			        HSSFWorkbook workbook = new HSSFWorkbook();
//				    HSSFSheet sheet = workbook.createSheet("lawix10");
//				    HSSFRow rowhead = sheet.createRow((short) 0);
//				    rowhead.createCell((short) 0).setCellValue("CellHeadName1");
//				    rowhead.createCell((short) 1).setCellValue("CellHeadName2");
//				    rowhead.createCell((short) 2).setCellValue("CellHeadName3");
//			        
//			       // registrationservice.getAllCategories();
//			        String filename = "my-file.xlsx";
//			        HttpHeaders headers = new HttpHeaders();
//			        headers.setAccessControlExposeHeaders(Collections.singletonList("Content-Disposition"));
//			        headers.set("Content-Disposition", "attachment; filename=" + filename);
//			        return new ResponseEntity<>(workbook, headers, HttpStatus.OK);
//			    }
//			 
			 
			 @GetMapping(path = "/download/allregistrations", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
			    public ResponseEntity getFile() {
			        String exportedContent = "Hello, World!";
			        
			        HSSFWorkbook workbook = new HSSFWorkbook();
				    HSSFSheet sheet = workbook.createSheet("lawix10");
				    HSSFRow rowhead = sheet.createRow((short) 0);
				    rowhead.createCell((short) 0).setCellValue("CellHeadName1");
				    rowhead.createCell((short) 1).setCellValue("CellHeadName2");
				    rowhead.createCell((short) 2).setCellValue("CellHeadName3");
			        
			        
				    HSSFRow row = sheet.createRow((short) 1);
			        row.createCell((short) 0).setCellValue("test");
			        row.createCell((short) 1).setCellValue("col2 ");
			        row.createCell((short) 2).setCellValue("col 3");
			        
			        
			        ByteArrayOutputStream bos = new ByteArrayOutputStream();
			        try {
			            workbook.write(bos);
			            bos.close();
			        } catch(Exception e) {
			        	
			        }
			        
			        byte[] bytes = bos.toByteArray();
				    
			        String filename = "my-file.txt";

			        
			        ResponseEntity.ok().header("Content-Disposition","attachment; filename=" + filename );
			        
			        
			        return ResponseEntity.ok()
			                .header(HttpHeaders.CONTENT_DISPOSITION,
			                      "attachment;filename=" + filename).body(bytes);
			        
			    }
			
			
//			public ResponseEntity<Iterable<Organiser>> getallorganisers() {
//				Iterable<Organiser> organisers = registrationservice.getAllOrganisers();
//				return new ResponseEntity<Iterable<Organiser>>(organisers,HttpStatus.OK);
//			}
			
			
//			 try {
//				    Class.forName("driverName");
//				    Connection con = DriverManager.getConnection("url", "user", "pass");
//				    Statement st = con.createStatement();
//				    ResultSet rs = st.executeQuery("Select * from tablename");
//				    HSSFWorkbook workbook = new HSSFWorkbook();
//				    HSSFSheet sheet = workbook.createSheet("lawix10");
//				    HSSFRow rowhead = sheet.createRow((short) 0);
//				    rowhead.createCell((short) 0).setCellValue("CellHeadName1");
//				    rowhead.createCell((short) 1).setCellValue("CellHeadName2");
//				    rowhead.createCell((short) 2).setCellValue("CellHeadName3");
//				    int i = 1;
//				    while (rs.next()){
//				        HSSFRow row = sheet.createRow((short) i);
//				        row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("column1")));
//				        row.createCell((short) 1).setCellValue(rs.getString("column2"));
//				        row.createCell((short) 2).setCellValue(rs.getString("column3"));
//				        i++;
//				    }
//				    String yemi = "g:/test.xls";
//				    FileOutputStream fileOut = new FileOutputStream(yemi);
//				    workbook.write(fileOut);
//				    fileOut.close();
//				    } catch (ClassNotFoundException e1) {
//				       e1.printStackTrace();
//				    } catch (SQLException e1) {
//				        e1.printStackTrace();
//				    } catch (FileNotFoundException e1) {
//				        e1.printStackTrace();
//				    } catch (IOException e1) {
//				        e1.printStackTrace();
//				    }
	}
