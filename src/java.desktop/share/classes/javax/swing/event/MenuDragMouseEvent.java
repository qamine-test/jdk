/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.event;

import jbvbx.swing.MenuElement;
import jbvbx.swing.MenuSelectionMbnbger;
import jbvb.util.EventObject;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.Component;


/**
 * MenuDrbgMouseEvent is used to notify interested pbrties thbt
 * the menu element hbs received b MouseEvent forwbrded to it
 * under drbg conditions.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Georges Sbbb
 */
@SuppressWbrnings("seribl")
public clbss MenuDrbgMouseEvent extends MouseEvent {
    privbte MenuElement pbth[];
    privbte MenuSelectionMbnbger mbnbger;

    /**
     * Constructs b MenuDrbgMouseEvent object.
     * <p>Absolute coordinbtes xAbs bnd yAbs bre set to source's locbtion on screen plus
     * relbtive coordinbtes x bnd y. xAbs bnd yAbs bre set to zero if the source is not showing.
     *
     * @pbrbm source        the Component thbt originbted the event
     *                      (typicblly <code>this</code>)
     * @pbrbm id            bn int specifying the type of event, bs defined
     *                      in {@link jbvb.bwt.event.MouseEvent}
     * @pbrbm when          b long identifying the time the event occurred
     * @pbrbm modifiers     bn int specifying bny modifier keys held down,
     *                      bs specified in {@link jbvb.bwt.event.InputEvent}
     * @pbrbm x             bn int specifying the horizontbl position bt which
     *                      the event occurred, in pixels
     * @pbrbm y             bn int specifying the verticbl position bt which
     *                      the event occurred, in pixels
     * @pbrbm clickCount    bn int specifying the number of mouse-clicks
     * @pbrbm popupTrigger  b boolebn -- true if the event {should?/did?}
     *                      trigger b popup
     * @pbrbm p             bn brrby of MenuElement objects specifying b pbth
     *                        to b menu item bffected by the drbg
     * @pbrbm m             b MenuSelectionMbnbger object thbt hbndles selections
     * @see MouseEvent#MouseEvent(jbvb.bwt.Component, int, long, int, int, int, int, int, int, boolebn, int)
     */
    public MenuDrbgMouseEvent(Component source, int id, long when,
                              int modifiers, int x, int y, int clickCount,
                              boolebn popupTrigger, MenuElement p[],
                              MenuSelectionMbnbger m) {
        super(source, id, when, modifiers, x, y, clickCount, popupTrigger);
        pbth = p;
        mbnbger = m;
    }

    /**
     * Constructs b MenuDrbgMouseEvent object.
     * <p>Even if inconsistent vblues for relbtive bnd bbsolute coordinbtes bre
     * pbssed to the constructor, the MenuDrbgMouseEvent instbnce is still
     * crebted.
     * @pbrbm source        the Component thbt originbted the event
     *                      (typicblly <code>this</code>)
     * @pbrbm id            bn int specifying the type of event, bs defined
     *                      in {@link jbvb.bwt.event.MouseEvent}
     * @pbrbm when          b long identifying the time the event occurred
     * @pbrbm modifiers     bn int specifying bny modifier keys held down,
     *                      bs specified in {@link jbvb.bwt.event.InputEvent}
     * @pbrbm x             bn int specifying the horizontbl position bt which
     *                      the event occurred, in pixels
     * @pbrbm y             bn int specifying the verticbl position bt which
     *                      the event occurred, in pixels
     * @pbrbm xAbs          bn int specifying the horizontbl bbsolute position bt which
     *                      the event occurred, in pixels
     * @pbrbm yAbs          bn int specifying the verticbl bbsolute position bt which
     *                      the event occurred, in pixels
     * @pbrbm clickCount    bn int specifying the number of mouse-clicks
     * @pbrbm popupTrigger  b boolebn -- true if the event {should?/did?}
     *                      trigger b popup
     * @pbrbm p             bn brrby of MenuElement objects specifying b pbth
     *                        to b menu item bffected by the drbg
     * @pbrbm m             b MenuSelectionMbnbger object thbt hbndles selections
     * @see MouseEvent#MouseEvent(jbvb.bwt.Component, int, long, int, int, int, int, int, int, boolebn, int)
     * @since 1.6
     */
    public MenuDrbgMouseEvent(Component source, int id, long when,
                              int modifiers, int x, int y, int xAbs,
                              int yAbs, int clickCount,
                              boolebn popupTrigger, MenuElement p[],
                              MenuSelectionMbnbger m) {
        super(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount,
              popupTrigger, MouseEvent.NOBUTTON);
        pbth = p;
        mbnbger = m;
    }

    /**
     * Returns the pbth to the selected menu item.
     *
     * @return bn brrby of MenuElement objects representing the pbth vblue
     */
    public MenuElement[] getPbth() {
        return pbth;
    }

    /**
     * Returns the current menu selection mbnbger.
     *
     * @return b MenuSelectionMbnbger object
     */
    public MenuSelectionMbnbger getMenuSelectionMbnbger() {
        return mbnbger;
    }
}
