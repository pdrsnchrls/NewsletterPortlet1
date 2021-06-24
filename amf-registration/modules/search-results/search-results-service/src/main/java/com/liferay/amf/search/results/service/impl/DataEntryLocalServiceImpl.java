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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

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
	
	public void getResults(RenderRequest renderRequest, RenderResponse renderResponse) 
			throws SearchException {
		String zip= ParamUtil.get(renderRequest, "zip", "");
		
//		// create search context and set its attributes
//		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);
//		SearchContext searchContext = SearchContextFactory.getInstance(httpRequest);
//		
//		searchContext.setKeywords(zip);
//		searchContext.setAttribute("paginationType", "more");
//		searchContext.setStart(0);
//		searchContext.setEnd(5);
//		
//		//get Indexer for user class?
//		Indexer indexer = IndexerRegistryUtil.getIndexer(Address.class);
//		
//		Hits hits = indexer.search(searchContext);
//		
//		//get list of addresses
//		List<Address> addressTest = new ArrayList<Address>();
//		
//		for(int i = 0; i < hits.getDocs().length; i++) {
//			Document doc = hits.doc(i);
//			
//			long addressId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));
//			System.out.println("Address ID -->" + addressId);
//			Address address = null;
//			
//			try {
//				address = AddressLocalServiceUtil.getAddress(addressId);
//				System.out.println(address.getStreet1() + "<-- Street 1");
//			} catch (PortalException pe) {
//				System.out.println("Portal Exception:");
//				pe.printStackTrace();
//			} catch (SystemException se) {
//				System.out.println("System Exception:");
//				se.printStackTrace();
//			}
//			
//			addressTest.add(address);
//		}
		
		try {
			List<User> results =  getUsers( zip );
			renderRequest.setAttribute("usersSize", results.size());
			renderRequest.setAttribute("users", results);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			SessionErrors.add(renderRequest, "systemFailure");
		}
		renderRequest.setAttribute("zip", zip);		
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
		
		
		
		return results;
	}
	
	
}