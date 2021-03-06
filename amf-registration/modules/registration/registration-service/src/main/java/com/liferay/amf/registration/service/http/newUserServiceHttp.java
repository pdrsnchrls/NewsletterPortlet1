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

package com.liferay.amf.registration.service.http;

import com.liferay.amf.registration.service.newUserServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the HTTP utility for the
 * <code>newUserServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Charles Pederson
 * @see newUserServiceSoap
 * @generated
 */
@ProviderType
public class newUserServiceHttp {

	public static void registerUser(
			HttpPrincipal httpPrincipal, String firstName, String lastName,
			String email, String username, boolean male, String birthday,
			String password1, String password2, String homePhone,
			String mobilePhone, String street1, String street2, String city,
			String state, String zip, String secQuestion, String secAnswer,
			boolean ToU)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				newUserServiceUtil.class, "registerUser",
				_registerUserParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, firstName, lastName, email, username, male, birthday,
				password1, password2, homePhone, mobilePhone, street1, street2,
				city, state, zip, secQuestion, secAnswer, ToU);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(newUserServiceHttp.class);

	private static final Class<?>[] _registerUserParameterTypes0 = new Class[] {
		String.class, String.class, String.class, String.class, boolean.class,
		String.class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, String.class, String.class,
		String.class, String.class, boolean.class
	};

}