/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.amf.search.results.service.impl;

import com.liferay.amf.search.results.service.base.DataEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the data entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.search.results.service.DataEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DataEntryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.amf.search.results.model.DataEntry",
	service = AopService.class
)
public class DataEntryLocalServiceImpl extends DataEntryLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.amf.search.results.service.DataEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.amf.search.results.service.DataEntryLocalServiceUtil</code>.
	 */
	
	public void getResults(EventRequest request, EventResponse response) 
			throws SearchException {

		// get zip from request
		Event event = request.getEvent();
		String zipCode = (String)event.getValue();
		response.setRenderParameter("zip", zipCode);
		
//		SearchContainer<User> searchContainer = new SearchContainer<User>(request, response.createRenderURL(), null, "");
//		searchContainer.setDelta(5);
//		searchContainer.setResults(null);
		
		try {
			List<User> results =  getUsers( zipCode );//, searchContainer.getStart(), searchContainer.getEnd());
			request.setAttribute("usersSize", results.size());
			request.setAttribute("users", results);
			request.setAttribute("zip", zipCode);		
			System.out.println("Got users");
			
			//searchContainer.setResults(results);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			SessionErrors.add(request, "systemFailure");
		}

	}
	
	public List<User> getUsers(String zip) throws PortalException {
		
		List<User> results = new ArrayList();
		if (!zip.isEmpty()) {
			List<Address> addresses = addressLocalService.getAddresses();
			for (int i = 0; i < addresses.size(); i++) {
				Address temp = addresses.get(i);
				// get address if it is primary, and if it is in the same ZIP
				if (temp.getZip().contentEquals(zip) && temp.getPrimary()) {
					results.add(userLocalService.getUser(temp.getUserId()));
				}
			}
		}
		//userId in Address and Address.zip = zip;

		return results;
	}
	
	// get users with a range
	public List<User> getUsers(String zip, int start, int end) throws PortalException {
		
		List<User> results = new ArrayList();
		
		if (!zip.isEmpty()) {
			// get a list of all the addresses in db - might want to change?
			List<Address> addresses = addressLocalService.getAddresses();
			int count = start; // counter variable for page
			int i = start; // this won't work always - it will start at 0, 5, 10 and look through the addresses...
			
			while (count < end) {
				if (i < addresses.size()) { // to ensure there are no bounds errors
					Address temp = addresses.get(i);
					// get address if it is primary, and if it is in the same ZIP
					if (temp.getZip().contentEquals(zip) && temp.getPrimary()) {
						results.add(userLocalService.getUser(temp.getUserId()));
						count++;
					}
					i++;
				}
				else
					break; // breaks while loop when out of bounds
			}
		}
		
		return results;
	}
	
	
}