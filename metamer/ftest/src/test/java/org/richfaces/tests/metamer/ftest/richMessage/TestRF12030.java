/**
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and individual contributors
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
 **/
package org.richfaces.tests.metamer.ftest.richMessage;

import static org.jboss.arquillian.ajocado.utils.URLUtils.buildUrl;

import java.net.URL;

import org.jboss.arquillian.graphene.Graphene;
import org.richfaces.tests.metamer.ftest.AbstractWebDriverTest;
import org.richfaces.tests.metamer.ftest.annotations.IssueTracking;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:jhuska@redhat.com">Juraj Huska</a>
 */
public class TestRF12030 extends AbstractWebDriverTest<RF12030Page> {

    @Override
    public URL getTestUrl() {
        return buildUrl(contextPath, "faces/components/richMessage/RF-12030.xhtml");
    }

    @Override
    public MetamerNavigation getComponentExampleNavigation() {
        return new MetamerNavigation("Rich", "Rich Message", "RF-12030");
    }

    @Override
    protected RF12030Page createPage() {
        return new RF12030Page();
    }

    @Test(groups = "4.3")
    @IssueTracking("https://issues.jboss.org/browse/RFPL-12030")
    public void testDataScrollerWorksAfterCollapseOfPanel() {
        String valueFromFstRowBeforeCollapse = page.firstTableRow.getText().trim();

        page.collapsePanel.click();
        page.nextButton.click();

        Graphene.waitModel().withMessage("The value from the first row of table"
                + " should be different as the table was navigated to the second page. "
                + "DataScroller does not work as expected!").until(Graphene.
                element(page.firstTableRow).not().textContains(valueFromFstRowBeforeCollapse));
    }
}
