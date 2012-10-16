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
package org.richfaces.tests.metamer.ftest.richPanelMenuItem;

import static javax.faces.event.PhaseId.APPLY_REQUEST_VALUES;
import static javax.faces.event.PhaseId.INVOKE_APPLICATION;
import static javax.faces.event.PhaseId.PROCESS_VALIDATIONS;
import static javax.faces.event.PhaseId.RENDER_RESPONSE;
import static javax.faces.event.PhaseId.RESTORE_VIEW;
import static javax.faces.event.PhaseId.UPDATE_MODEL_VALUES;
import static org.jboss.arquillian.ajocado.utils.URLUtils.buildUrl;
import static org.richfaces.tests.metamer.ftest.attributes.AttributeList.panelMenuItemAttributes;

import java.net.URL;
import java.util.LinkedList;

import javax.faces.event.PhaseId;

import org.richfaces.PanelMenuMode;
import org.richfaces.tests.metamer.ftest.AbstractGrapheneTest;
import org.richfaces.tests.metamer.ftest.annotations.Inject;
import org.richfaces.tests.metamer.ftest.annotations.Use;
import org.richfaces.tests.metamer.ftest.model.PanelMenu;
import org.testng.annotations.Test;


/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision: 22751 $
 */
public class TestPanelMenuItemMode extends AbstractGrapheneTest {

    PanelMenu menu = new PanelMenu(pjq("div.rf-pm[id$=panelMenu]"));
    PanelMenu.Item item = menu.getGroup(1).getItem(2);

    @Inject
    @Use(booleans = { true, false })
    Boolean immediate;

    @Inject
    @Use(booleans = { true, false })
    Boolean bypassUpdates;

    @Inject
    @Use(enumeration = true)
    PanelMenuMode mode;

    @Inject
    @Use("listeners")
    String listener;
    String[] listeners = new String[] { "phases", "action invoked", "action listener invoked", "executeChecker",
        "item changed" };

    @Override
    public URL getTestUrl() {
        return buildUrl(contextPath, "faces/components/richPanelMenuItem/simple.xhtml");
    }

    @Override
    public MetamerNavigation getComponentExampleNavigation() {
        return new MetamerNavigation("Rich", "Rich Panel Menu Item", "Simple");
    }

    @Test
    public void testMode() {
        panelMenuItemAttributes.set(PanelMenuItemAttributes.immediate, immediate);
        panelMenuItemAttributes.set(PanelMenuItemAttributes.bypassUpdates, bypassUpdates);
        panelMenuItemAttributes.set(PanelMenuItemAttributes.mode, mode);
        menu.setItemMode(mode);

        panelMenuItemAttributes.set(PanelMenuItemAttributes.execute, "@this executeChecker");

        item.select();

        if (mode != PanelMenuMode.client) {
            if ("phases".equals(listener)) {
                phaseInfo.assertPhases(getExpectedPhases());
            } else {
                PhaseId listenerInvocationPhase = getListenerInvocationPhase();
                if (listenerInvocationPhase == null) {
                    phaseInfo.assertNoListener(listener);
                } else {
                    phaseInfo.assertListener(listenerInvocationPhase, listener);
                }
            }
        }
    }

    private PhaseId[] getExpectedPhases() {
        LinkedList<PhaseId> list = new LinkedList<PhaseId>();
        list.add(RESTORE_VIEW);
        list.add(APPLY_REQUEST_VALUES);
        if (!immediate) {
            list.add(PROCESS_VALIDATIONS);
        }
        if (!immediate && !bypassUpdates) {
            list.add(UPDATE_MODEL_VALUES);
            list.add(INVOKE_APPLICATION);
        }
        list.add(RENDER_RESPONSE);
        return list.toArray(new PhaseId[list.size()]);
    }

    private PhaseId getListenerInvocationPhase() {
        PhaseId[] phases = getExpectedPhases();
        PhaseId phase = phases[phases.length - 2];

        if ("executeChecker".equals(listener)) {
            if (phase.compareTo(UPDATE_MODEL_VALUES) < 0 || mode == PanelMenuMode.server) {
                return null;
            } else {
                return UPDATE_MODEL_VALUES;
            }
        }

        if ("item changed".equals(listener)) {
            if (phases.length == 6) {
                return UPDATE_MODEL_VALUES;
            }
        }

        return phase;
    }
}
