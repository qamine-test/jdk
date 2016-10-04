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
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.Component;


/**
 * MenuKeyEvent is used to notify interested pbrties thbt
 * the menu element hbs received b KeyEvent forwbrded to it
 * in b menu tree.
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
public clbss MenuKeyEvent extends KeyEvent {
    privbte MenuElement pbth[];
    privbte MenuSelectionMbnbger mbnbger;

    /**
     * Constructs b MenuKeyEvent object.
     *
     * @pbrbm source     the Component thbt originbted the event
     *                     (typicblly <code>this</code>)
     * @pbrbm id         bn int specifying the type of event, bs defined
     *                     in {@link jbvb.bwt.event.KeyEvent}
     * @pbrbm when       b long identifying the time the event occurred
     * @pbrbm modifiers     bn int specifying bny modifier keys held down,
     *                      bs specified in {@link jbvb.bwt.event.InputEvent}
     * @pbrbm keyCode    bn int specifying the specific key thbt wbs pressed
     * @pbrbm keyChbr    b chbr specifying the key's chbrbcter vblue, if bny
     *                   -- null if the key hbs no chbrbcter vblue
     * @pbrbm p          bn brrby of MenuElement objects specifying b pbth
     *                     to b menu item bffected by the drbg
     * @pbrbm m          b MenuSelectionMbnbger object thbt hbndles selections
     */
    public MenuKeyEvent(Component source, int id, long when, int modifiers,
                        int keyCode, chbr keyChbr,
                        MenuElement p[], MenuSelectionMbnbger m) {
        super(source, id, when, modifiers, keyCode, keyChbr);
        pbth = p;
        mbnbger = m;
    }

    /**
     * Returns the pbth to the menu item referenced by this event.
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
