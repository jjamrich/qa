/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2010-2012, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/
package org.richfaces.tests.metamer.bean;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Session-scoped managed bean storing user's roles.
 *
 * @author Nick Belaevski
 * @author <a href="https://community.jboss.org/people/ppitonak">Pavol Pitonak</a>
 * @version $Revision: 21194 $
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = -2195858488735486326L;

    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String username) {
        this.rolename = username;
    }

    public Principal getPrincipal() {
        if (rolename != null) {
            return new PrincipalImpl(rolename);
        }

        return null;
    }

    public boolean isUserInRole(String role) {
        Principal principal = getPrincipal();
        if (principal != null) {
            //username & principal's name & role name are considered the same
            return principal.getName().equals(role);
        }

        return false;
    }
}

class PrincipalImpl implements Principal {

    private String name;

    public PrincipalImpl(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
