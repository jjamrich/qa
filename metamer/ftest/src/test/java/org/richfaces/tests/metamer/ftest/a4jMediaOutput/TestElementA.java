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
package org.richfaces.tests.metamer.ftest.a4jMediaOutput;

import static org.jboss.arquillian.ajocado.utils.URLUtils.buildUrl;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;

import org.jboss.arquillian.ajocado.dom.Attribute;
import org.richfaces.tests.metamer.bean.a4j.A4JMediaOutputBean;
import org.testng.annotations.Test;


/**
 * Test case for page /faces/components/a4jMediaOutput/elementA.xhtml
 *
 * @author <a href="mailto:jpapouse@redhat.com">Jan Papousek</a>
 */
public class TestElementA extends AbstractMediaOutputTest {

    @Override
    public URL getTestUrl() {
        return buildUrl(contextPath, "faces/components/a4jMediaOutput/elementA.xhtml");
    }

    @Override
    public MetamerNavigation getComponentExampleNavigation() {
        return new MetamerNavigation("A4J", "A4J Media Output", "Element <a></a>");
    }

    @Test
    public void init() throws IOException {
        assertEquals(selenium.getText(MEDIA_OUTPUT), "This is a link", "The link text doesn't match.");
        assertTrue(
            getTextContentByUrlAttribute(MEDIA_OUTPUT.getAttribute(Attribute.HREF)).contains(A4JMediaOutputBean.HTML_TEXT),
            "Target HTML page doesn't match."
        );
    }


}
