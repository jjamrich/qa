/**
 * *****************************************************************************
 * JBoss, Home of Professional Open Source Copyright 2010-2012, Red Hat, Inc.
 * and individual contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 * *****************************************************************************
 */
package org.richfaces.tests.metamer.ftest.richPopupPanel;

import static org.jboss.arquillian.ajocado.Graphene.elementVisible;
import static org.jboss.arquillian.ajocado.Graphene.waitGui;
import static org.jboss.arquillian.ajocado.utils.URLUtils.buildUrl;
import static org.jboss.test.selenium.locator.utils.LocatorEscaping.jq;
import static org.richfaces.tests.metamer.ftest.BasicAttributes.controlsClass;
import static org.richfaces.tests.metamer.ftest.BasicAttributes.headerClass;
import static org.richfaces.tests.metamer.ftest.attributes.AttributeList.popupPanelAttributes;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.ajocado.css.CssProperty;
import org.jboss.arquillian.ajocado.dom.Event;
import org.jboss.arquillian.ajocado.geometry.Offset;
import org.jboss.arquillian.ajocado.javascript.JavaScript;
import org.jboss.arquillian.ajocado.locator.JQueryLocator;
import org.jboss.test.selenium.waiting.EventFiredCondition;
import org.richfaces.tests.metamer.ftest.AbstractGrapheneTest;
import org.richfaces.tests.metamer.ftest.annotations.IssueTracking;
import org.richfaces.tests.metamer.ftest.annotations.RegressionTest;
import org.testng.annotations.Test;

/**
 * Test case for page /faces/components/richPopupPanel/simple.xhtml
 *
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision: 22733 $
 */
public class TestRichPopupPanel extends AbstractGrapheneTest {

    private JQueryLocator openButton = pjq("input[id$=openPanelButton]");
    private JQueryLocator panel = pjq("div[id$=popupPanel]");
    private JQueryLocator panelContainer = jq("div[id$=popupPanel_container].rf-pp-cntr");
    private JQueryLocator content = jq("div[id$=popupPanel_container] div.rf-pp-cnt");
    private JQueryLocator header = jq("div[id$=popupPanel_container] div.rf-pp-hdr");
    private JQueryLocator controls = jq("div[id$=popupPanel_container] div.rf-pp-hdr-cntrls a");
    private JQueryLocator scroller = jq("div[id$=popupPanel_container] div.rf-pp-cnt-scrlr");
    private JQueryLocator shadow = jq("div[id$=popupPanel_container] div.rf-pp-shdw");
    private JQueryLocator resizerW = jq("div[id$=popupPanel_container] div.rf-pp-hndlr-l");
    private JQueryLocator resizerN = jq("div[id$=popupPanel_container] div.rf-pp-hndlr-t");
    private JQueryLocator resizerE = jq("div[id$=popupPanel_container] div.rf-pp-hndlr-r");
    private JQueryLocator resizerS = jq("div[id$=popupPanel_container] div.rf-pp-hndlr-b");
    private JQueryLocator resizerNW = jq("div[id$=popupPanel_container] div.rf-pp-hndlr-tl");
    private JQueryLocator resizerNE = jq("div[id$=popupPanel_container] div.rf-pp-hndlr-tr");
    private JQueryLocator resizerSW = jq("div[id$=popupPanel_container] div.rf-pp-hndlr-bl");
    private JQueryLocator resizerSE = jq("div[id$=popupPanel_container] div.rf-pp-hndlr-br");
    private JQueryLocator mask = jq("div[id$=popupPanel_shade].rf-pp-shade");

    @Override
    public URL getTestUrl() {
        return buildUrl(contextPath, "faces/components/richPopupPanel/simple.xhtml");
    }

    @Override
    public MetamerNavigation getComponentExampleNavigation() {
        return new MetamerNavigation("Rich", "Rich Popup Panel", "Simple");
    }

    @Test
    public void testInit() {
        assertTrue(selenium.isElementPresent(openButton), "Button for opening popup should be on the page.");
        assertTrue(selenium.isElementPresent(panel), "Popup panel is not present on the page.");
        assertFalse(selenium.isVisible(panel), "Popup panel is visible.");

        selenium.click(openButton);
        assertTrue(selenium.isVisible(panel), "Popup panel should be visible.");
        assertTrue(selenium.isVisible(panelContainer), "Popup panel's container should be visible.");
        assertTrue(selenium.isVisible(header), "Popup panel's header should be visible.");
        assertTrue(selenium.isVisible(controls), "Popup panel's controls should be visible.");
        assertTrue(selenium.isVisible(scroller), "Popup panel's scroller should be visible.");
        assertTrue(selenium.isVisible(shadow), "Popup panel's shadow should be visible.");
        assertTrue(selenium.isVisible(resizerE), "Popup panel's right resizer should be visible.");
        assertTrue(selenium.isVisible(resizerN), "Popup panel's top resizer should be visible.");
        assertTrue(selenium.isVisible(resizerS), "Popup panel's bottom resizer should be visible.");
        assertTrue(selenium.isVisible(resizerW), "Popup panel's left resizer should be visible.");
        assertTrue(selenium.isVisible(resizerNE), "Popup panel's top-right resizer should be visible.");
        assertTrue(selenium.isVisible(resizerNW), "Popup panel's top-left resizer should be visible.");
        assertTrue(selenium.isVisible(resizerSE), "Popup panel's bottom-right resizer should be visible.");
        assertTrue(selenium.isVisible(resizerSW), "Popup panel's bottom-left resizer should be visible.");
        assertFalse(selenium.isElementPresent(mask), "Mask should not be visible.");

        assertEquals(selenium.getText(header), "popup panel header", "Header of popup panel");
        assertTrue(selenium.getText(content).startsWith("Lorem ipsum"), "Panel's content should start with 'Lorem ipsum'.");
        assertTrue(selenium.getText(content).endsWith("hide this panel"), "Panel's content should end with 'hide this panel'.");
    }

    @Test
    public void testHidePanel() {
        selenium.click(openButton);
        assertTrue(selenium.isVisible(panel), "Popup panel is not visible.");
        selenium.click(controls);
        assertFalse(selenium.isVisible(panel), "Popup panel is visible.");

        selenium.click(openButton);
        assertTrue(selenium.isVisible(panel), "Popup panel is not visible.");
        selenium.click(jq("div.rf-pp-cnt a"));
        assertFalse(selenium.isVisible(panel), "Popup panel is visible.");
    }

    @Test
    public void testMove() {
        selenium.click(openButton);
        int panelLeft = selenium.getElementPositionLeft(panelContainer);
        int panelTop = selenium.getElementPositionTop(panelContainer);
        int shadowLeft = selenium.getElementPositionLeft(shadow);
        int shadowTop = selenium.getElementPositionTop(shadow);

        assertEquals(selenium.getStyle(header, new CssProperty("cursor")), "move", "Cursor used when mouse is over panel's header.");
        selenium.dragAndDrop(header, new Offset(200, -100));

        assertEquals(selenium.getElementPositionLeft(panelContainer), panelLeft + 200, "Panel's position after move to the right (200px).");
        assertEquals(selenium.getElementPositionLeft(shadow), shadowLeft + 200, "Shadow's position after move to the right (200px).");

        assertEquals(selenium.getElementPositionTop(panelContainer), panelTop - 100, "Panel's position after move to the top (100px).");
        assertEquals(selenium.getElementPositionTop(shadow), shadowTop - 100, "Shadow's position after move to the top (100px).");
    }

    @Test
    public void testResizeHorizontal() {
        selenium.click(openButton);
        int panelWidth = selenium.getElementWidth(panelContainer);
        int panelHeight = selenium.getElementHeight(panelContainer);
        int shadowWidth = selenium.getElementWidth(shadow);
        int shadowHeight = selenium.getElementHeight(shadow);

        assertEquals(selenium.getStyle(resizerW, new CssProperty("cursor")), "w-resize", "Cursor used when mouse is over panel's left resizer.");
        assertEquals(selenium.getStyle(resizerE, new CssProperty("cursor")), "e-resize", "Cursor used when mouse is over panel's right resizer.");

        selenium.dragAndDrop(resizerW, new Offset(100, 0));

        assertEquals(selenium.getElementWidth(panelContainer), panelWidth - 100, "Panel's width after resizing horizontally (-100px).");
        assertEquals(selenium.getElementHeight(panelContainer), panelHeight, "Panel's height after resizing horizontally.");
        assertEquals(selenium.getElementWidth(shadow), shadowWidth - 100, "Shadow's width after resizing horizontally (-100px).");
        assertEquals(selenium.getElementHeight(shadow), shadowHeight, "Shadow's height after resizing horizontally.");

        selenium.dragAndDrop(resizerE, new Offset(100, 0));

        assertEquals(selenium.getElementWidth(panelContainer), panelWidth, "Panel's width after resizing horizontally (100px).");
        assertEquals(selenium.getElementHeight(panelContainer), panelHeight, "Panel's height after resizing horizontally.");
        assertEquals(selenium.getElementWidth(shadow), shadowWidth, "Shadow's width after resizing horizontally (100px).");
        assertEquals(selenium.getElementHeight(shadow), shadowHeight, "Shadow's height after resizing horizontally.");
    }
//    @Test
//    public void testResizeVertical() {
//        selenium.click(openButton);
//        int panelWidth = selenium.getElementWidth(panelContainer);
//        int panelHeight = selenium.getElementHeight(panelContainer);
//        int shadowWidth = selenium.getElementWidth(shadow);
//        int shadowHeight = selenium.getElementHeight(shadow);
//
//        assertEquals(selenium.getStyle(resizerN, new CssProperty("cursor")), "n-resize", "Cursor used when mouse is over panel's top resizer.");
//        assertEquals(selenium.getStyle(resizerS, new CssProperty("cursor")), "s-resize", "Cursor used when mouse is over panel's bottom resizer.");
//
//        selenium.dragAndDrop(resizerN, new Offset(0, 100));
//
//        assertEquals(selenium.getElementWidth(panelContainer), panelWidth, "Panel's width after resizing vertically (-100px).");
//        assertEquals(selenium.getElementHeight(panelContainer), panelHeight - 100, "Panel's height after resizing vertically.");
//        assertEquals(selenium.getElementWidth(shadow), shadowWidth, "Shadow's width after resizing vertically (-100px).");
//        assertEquals(selenium.getElementHeight(shadow), shadowHeight - 100, "Shadow's height after resizing vertically.");
//
//        selenium.dragAndDrop(resizerS, new Offset(0, -100));
//
//        assertEquals(selenium.getElementWidth(panelContainer), panelWidth, "Panel's width after resizing vertically (100px).");
//        assertEquals(selenium.getElementHeight(panelContainer), panelHeight, "Panel's height after resizing vertically.");
//        assertEquals(selenium.getElementWidth(shadow), shadowWidth, "Shadow's width after resizing vertically (100px).");
//        assertEquals(selenium.getElementHeight(shadow), shadowHeight, "Shadow's height after resizing vertically.");
//    }

    @Test
    public void testControlsClass() {
        testStyleClass(pjq("div[id$=popupPanel_header_controls]"), controlsClass);
    }

    @Test
    @IssueTracking("https://issues.jboss.org/browse/RF-10249")
    public void testDomElementAttachment() {
        selenium.click(openButton);
        assertTrue(selenium.isElementPresent(jq("body ").getChild(panelContainer)), "Panel container should be attached to the body element.");

        popupPanelAttributes.set(PopupPanelAttributes.domElementAttachment, "parent");
        selenium.click(openButton);
        assertTrue(selenium.isElementPresent(panel.getChild(panelContainer)), "Panel container should be attached to the parent element.");

        popupPanelAttributes.set(PopupPanelAttributes.domElementAttachment, "");
        selenium.click(openButton);
        assertTrue(selenium.isElementPresent(jq("body").getChild(panelContainer)), "Panel container should be attached to the body element.");

        popupPanelAttributes.set(PopupPanelAttributes.domElementAttachment, "form");
        selenium.click(openButton);
        assertTrue(selenium.isElementPresent(jq("form[id*=form]").getChild(panelContainer)), "Panel container should be attached to the form element.");
    }

    @Test
    public void testHeader() {
        popupPanelAttributes.set(PopupPanelAttributes.header, "new header");
        assertEquals(selenium.getText(header), "new header", "Header of the popup panel.");
    }

    @Test
    public void testHeaderClass() {
        testStyleClass(header, headerClass);
    }

    @Test
    public void testHeight() {
        // TODO rewrite this test so that it will test RF-10251
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getStyle(panelContainer, CssProperty.HEIGHT), "300px", "Height of the panel");

        popupPanelAttributes.set(PopupPanelAttributes.height, 400);
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getStyle(panelContainer, CssProperty.HEIGHT), "400px", "Height of the panel");
    }

    @Test
    public void testLeft() {
        double width = Integer.parseInt(selenium.getEval(new JavaScript("window.innerWidth")));
        width = (width - 500) / 2;
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getElementPositionLeft(panelContainer), Math.round(width), "Left margin of the panel");

        popupPanelAttributes.set(PopupPanelAttributes.left, 200);
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getElementPositionLeft(panelContainer), 200, "Left margin of the panel");
    }

    @Test
    public void testModal() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);

        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertTrue(selenium.isVisible(mask), "Mask should be visible.");
    }

    @Test
    public void testMovable() {
        popupPanelAttributes.set(PopupPanelAttributes.moveable, Boolean.FALSE);

        selenium.click(openButton);
        int panelLeft = selenium.getElementPositionLeft(panelContainer);
        int panelTop = selenium.getElementPositionTop(panelContainer);
        int shadowLeft = selenium.getElementPositionLeft(shadow);
        int shadowTop = selenium.getElementPositionTop(shadow);

        assertEquals(selenium.getStyle(header, new CssProperty("cursor")), "default", "Cursor used when mouse is over panel's header.");
        selenium.dragAndDrop(header, new Offset(200, -100));

        assertEquals(selenium.getElementPositionLeft(panelContainer), panelLeft, "Panel's position after move to the right (200px).");
        assertEquals(selenium.getElementPositionLeft(shadow), shadowLeft, "Shadow's position after move to the right (200px).");

        assertEquals(selenium.getElementPositionTop(panelContainer), panelTop, "Panel's position after move to the top (100px).");
        assertEquals(selenium.getElementPositionTop(shadow), shadowTop, "Shadow's position after move to the top (100px).");
    }

    @Test
    public void testOnbeforehideOnhide() {
        popupPanelAttributes.set(PopupPanelAttributes.onbeforehide, "metamerEvents += \"beforehide \"");
        popupPanelAttributes.set(PopupPanelAttributes.onhide, "metamerEvents += \"hide \"");

        selenium.getEval(new JavaScript("window.metamerEvents = \"\";"));

        selenium.click(openButton);
        selenium.click(controls);

        String[] events = selenium.getEval(new JavaScript("window.metamerEvents")).split(" ");

        assertEquals(events.length, 2, "2 events should be fired.");
        assertEquals(events[0], "beforehide", "Attribute onbeforehide doesn't work");
        assertEquals(events[1], "hide", "Attribute onhide doesn't work");
    }

    @Test
    public void testOnbeforeshowOnshow() {
        popupPanelAttributes.set(PopupPanelAttributes.onbeforeshow, "metamerEvents += \"beforeshow \"");
        popupPanelAttributes.set(PopupPanelAttributes.onshow, "metamerEvents += \"show \"");

        selenium.getEval(new JavaScript("window.metamerEvents = \"\";"));

        selenium.click(openButton);

        String[] events = selenium.getEval(new JavaScript("window.metamerEvents")).split(" ");

        assertEquals(events.length, 2, "2 events should be fired.");
        assertEquals(events[0], "beforeshow", "Attribute onbeforeshow doesn't work");
        assertEquals(events[1], "show", "Attribute onshow doesn't work");
    }

    @Test
    public void testOnmaskclick() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);
        testFireEvent(Event.CLICK, mask, "maskclick");
    }

    @Test
    public void testOnmaskcontextmenu() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);
        testFireEvent(new Event("contextmenu"), mask, "maskcontextmenu");
    }

    @Test
    public void testOnmaskdblclick() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);
        testFireEvent(Event.DBLCLICK, mask, "maskdblclick");
    }

    @Test
    public void testOnmaskmousedown() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);
        testFireEvent(Event.MOUSEDOWN, mask, "maskmousedown");
    }

    @Test
    public void testOnmaskmousemove() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);
        testFireEvent(Event.MOUSEMOVE, mask, "maskmousemove");
    }

    @Test
    public void testOnmaskmouseout() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);
        testFireEvent(Event.MOUSEOUT, mask, "maskmouseout");
    }

    @Test
    public void testOnmaskmouseover() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);
        testFireEvent(Event.MOUSEOVER, mask, "maskmouseover");
    }

    @Test
    public void testOnmaskmouseup() {
        popupPanelAttributes.set(PopupPanelAttributes.modal, Boolean.TRUE);
        testFireEvent(Event.MOUSEUP, mask, "maskmouseup");
    }

    @Test
    public void testOnmove() {
        popupPanelAttributes.set(PopupPanelAttributes.onmove, "metamerEvents += \"move \"");
        selenium.dragAndDrop(header, new Offset(1, 0));
        waitGui.failWith("Attribute onmove does not work correctly.").until(new EventFiredCondition(new Event("move")));
    }

    @Test
    public void testOnresize() {
        popupPanelAttributes.set(PopupPanelAttributes.onresize, "metamerEvents += \"resize \"");
        selenium.dragAndDrop(resizerE, new Offset(100, 0));
        waitGui.failWith("Attribute onresize does not work correctly.").until(new EventFiredCondition(new Event("resize")));
    }

    @Test
    public void testRendered() {
        popupPanelAttributes.set(PopupPanelAttributes.rendered, Boolean.FALSE);
        assertFalse(selenium.isElementPresent(panel), "Popup panel should not be rendered when rendered=false.");
    }

    @Test
    public void testResizeable() {
        popupPanelAttributes.set(PopupPanelAttributes.resizeable, Boolean.FALSE);
        assertFalse(selenium.isElementPresent(resizerE), "Popup panel's right resizer should not be present on the page.");
        assertFalse(selenium.isElementPresent(resizerN), "Popup panel's top resizer should not be present on the page.");
        assertFalse(selenium.isElementPresent(resizerS), "Popup panel's bottom resizer should not be present on the page.");
        assertFalse(selenium.isElementPresent(resizerW), "Popup panel's left resizer should not be present on the page.");
        assertFalse(selenium.isElementPresent(resizerNE), "Popup panel's top-right resizer should not be present on the page.");
        assertFalse(selenium.isElementPresent(resizerNW), "Popup panel's top-left resizer should not be present on the page.");
        assertFalse(selenium.isElementPresent(resizerSE), "Popup panel's bottom-right resizer should not be present on the page.");
        assertFalse(selenium.isElementPresent(resizerSW), "Popup panel's bottom-left resizer should not be present on the page.");
    }

    @Test
    @RegressionTest("https://issues.jboss.org/browse/RF-10504")
    public void testShadowDepth() {
        popupPanelAttributes.set(PopupPanelAttributes.shadowDepth, 15);

        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        int panelPos = selenium.getElementPositionLeft(panelContainer);
        int shadowPos = selenium.getElementPositionLeft(shadow);
        assertEquals(shadowPos - panelPos, 15, "Depth of the shadow (left-right).");

        panelPos = selenium.getElementPositionTop(panelContainer);
        shadowPos = selenium.getElementPositionTop(shadow);
        assertEquals(shadowPos - panelPos, 15, "Depth of the shadow (top-bottom).");

        popupPanelAttributes.set(PopupPanelAttributes.shadowDepth, 0);

        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        panelPos = selenium.getElementPositionLeft(panelContainer);
        shadowPos = selenium.getElementPositionLeft(shadow);
        assertEquals(shadowPos - panelPos, 0, "Depth of the shadow (left-right).");

        panelPos = selenium.getElementPositionTop(panelContainer);
        shadowPos = selenium.getElementPositionTop(shadow);
        assertEquals(shadowPos - panelPos, 0, "Depth of the shadow (top-bottom).");
    }

    @Test
    @RegressionTest("https://issues.jboss.org/browse/RF-10504")
    public void testShadowOpacity() {
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getStyle(shadow, new CssProperty("opacity")), "0.1", "Default shadow opacity");

        popupPanelAttributes.set(PopupPanelAttributes.shadowOpacity, 0.7);

        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getStyle(shadow, new CssProperty("opacity")), "0.7", "Shadow opacity");
    }

    @Test
    @RegressionTest("https://issues.jboss.org/browse/RF-10245")
    public void testStyle() {
        testStyle(panelContainer);
    }

    @Test
    @RegressionTest("https://issues.jboss.org/browse/RF-10245")
    public void testStyleClass() {
        testStyleClass(panelContainer);
    }

    @Test
    public void testTop() {
        double top = Integer.parseInt(selenium.getEval(new JavaScript("window.innerHeight")));
        top = (top - 300) / 2;
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getElementPositionTop(panelContainer), Math.round(top), "Top margin of the panel");

        popupPanelAttributes.set(PopupPanelAttributes.top, 200);
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getElementPositionTop(panelContainer), 200, "Top margin of the panel");
    }

    @Test
    public void testWidth() {
        // TODO rewrite this test so that it will test RF-10251
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getStyle(panelContainer, CssProperty.WIDTH), "500px", "Width of the panel");

        popupPanelAttributes.set(PopupPanelAttributes.width, 400);
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getStyle(panelContainer, CssProperty.WIDTH), "400px", "Width of the panel");
    }

    @Test
    public void testZindex() {
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getStyle(panelContainer, new CssProperty("z-index")), "4", "Zindex of the panel");

        popupPanelAttributes.set(PopupPanelAttributes.zindex, 30);
        selenium.click(openButton);
        waitGui.failWith("Panel was not opened.").until(elementVisible.locator(panel));
        assertEquals(selenium.getStyle(panelContainer, new CssProperty("z-index")), "30", "Z-index of the panel");
    }
}
