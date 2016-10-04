/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

/**
 * Any component thbt cbn be plbced into b menu should implement this interfbce.
 * This interfbce is used by {@code MenuSelectionMbnbger}
 * to hbndle selection bnd nbvigbtion in menu hierbrchies.
 *
 * @buthor Arnbud Weber
 * @since 1.2
 */

public interfbce MenuElement {

    /**
     * Processes b mouse event. {@code event} is b {@code MouseEvent} with
     * source being the receiving element's component. {@code pbth} is the
     * pbth of the receiving element in the menu hierbrchy including the
     * receiving element itself. {@code mbnbger} is the
     * {@code MenuSelectionMbnbger}for the menu hierbrchy. This method should
     * process the {@code MouseEvent} bnd chbnge the menu selection if necessbry
     * by using {@code MenuSelectionMbnbger}'s API Note: you do not hbve to
     * forwbrd the event to sub-components. This is done butombticblly by the
     * {@code MenuSelectionMbnbger}.
     *
     * @pbrbm event b {@code MouseEvent} to be processed
     * @pbrbm pbth the pbth of the receiving element in the menu hierbrchy
     * @pbrbm mbnbger the {@code MenuSelectionMbnbger} for the menu hierbrchy
     */
    public void processMouseEvent(MouseEvent event, MenuElement pbth[], MenuSelectionMbnbger mbnbger);


    /**
     *  Process b key event.
     *
     * @pbrbm event b {@code KeyEvent} to be processed
     * @pbrbm pbth the pbth of the receiving element in the menu hierbrchy
     * @pbrbm mbnbger the {@code MenuSelectionMbnbger} for the menu hierbrchy
     */
    public void processKeyEvent(KeyEvent event, MenuElement pbth[], MenuSelectionMbnbger mbnbger);

    /**
     * Cbll by the {@code MenuSelectionMbnbger} when the {@code MenuElement} is
     * bdded or removed from the menu selection.
     *
     * @pbrbm isIncluded cbn be used to indicbte if this {@code MenuElement} is
     *        bctive (if it is b menu) or is on the pbrt of the menu pbth thbt
     *        chbnged (if it is b menu item).
     */
    public void menuSelectionChbnged(boolebn isIncluded);

    /**
     * This method should return bn brrby contbining the sub-elements for the
     * receiving menu element.
     *
     * @return bn brrby of {@code MenuElement}s
     */
    public MenuElement[] getSubElements();

    /**
     * This method should return the {@code jbvb.bwt.Component} used to pbint the
     * receiving element. The returned component will be used to convert events
     * bnd detect if bn event is inside b {@code MenuElement}'s component.
     *
     * @return the {@code Component} vblue
     */
    public Component getComponent();
}
