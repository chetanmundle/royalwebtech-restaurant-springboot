package com.api.resturentapplication.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.resturentapplication.dao.RestRepos;
import com.api.resturentapplication.dao.TableofResturentRepository;
import com.api.resturentapplication.entities.Order_menus;
import com.api.resturentapplication.entities.Resturant;
import com.api.resturentapplication.entities.TablesOfResturant;

@RestController
@RequestMapping("/tablesofrestaurant")
@CrossOrigin(origins = "*")
public class TableController
{
	@Autowired
	private TableofResturentRepository tableofResturentRepository;
	
	@Autowired
	private RestRepos restRepos;
	
	@PostMapping("/savetable/{restid}")
	public ResponseEntity<TablesOfResturant> saveTable( @PathVariable("restid") int restid){
		Optional<Resturant> optionalResturant = restRepos.findById(restid);
		
		if(optionalResturant.isPresent()) {
			TablesOfResturant tablesOfResturant=new TablesOfResturant();
			Resturant resturant = optionalResturant.get();
			tablesOfResturant.setResturant(resturant);
			tableofResturentRepository.save(tablesOfResturant);
			return ResponseEntity.ok(tablesOfResturant);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/getorderbytable/{restid}/{tableid}")
	public ResponseEntity<TablesOfResturant> getOrderedMenusByTable(@PathVariable("restid") int restid, @PathVariable("tableid") int tableid)
	{
		 Optional<TablesOfResturant> findByIdAndResturant_id = tableofResturentRepository.findByIdAndResturant_id(tableid,restid);
		
		if(findByIdAndResturant_id.isPresent())
		{
			TablesOfResturant tablesOfResturant = findByIdAndResturant_id.get();
			return ResponseEntity.ok(tablesOfResturant);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
//	update the status zero(empty) to one(Booked)
	@PutMapping("/updatestatuszerotoone/withcustomer/{restid}/{tableid}")
	public ResponseEntity<HttpStatus> updateStatusandcustomer(@RequestBody TablesOfResturant tablesOfResturant)
	{
		System.out.println("Tableid "+tablesOfResturant.getId());
		System.out.println("Restid : "+tablesOfResturant.getResturant());
		
		return ResponseEntity.ok().build();
//		Optional<TablesOfResturant> findByIdAndResturant_id = tableofResturentRepository.findByIdAndResturant_id(tablesOfResturant.getId(), tablesOfResturant.getResturant());
//		
//		if(findByIdAndResturant_id.isPresent())
//		{
//			TablesOfResturant tablesOfResturant = findByIdAndResturant_id.get();
//			tablesOfResturant.setCname(null)
//		}else {
//			return ResponseEntity.notFound().build();
//		}
	}
	
	
	
	
	
}
