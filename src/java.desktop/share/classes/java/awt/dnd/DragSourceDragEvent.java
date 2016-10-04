/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd;

import jbvb.bwt.event.InputEvent;

/**
 * The <code>DrbgSourceDrbgEvent</code> is
 * delivered from the <code>DrbgSourceContextPeer</code>,
 * vib the <code>DrbgSourceContext</code>, to the <code>DrbgSourceListener</code>
 * registered with thbt <code>DrbgSourceContext</code> bnd with its bssocibted
 * <code>DrbgSource</code>.
 * <p>
 * The <code>DrbgSourceDrbgEvent</code> reports the <i>tbrget drop bction</i>
 * bnd the <i>user drop bction</i> thbt reflect the current stbte of
 * the drbg operbtion.
 * <p>
 * <i>Tbrget drop bction</i> is one of <code>DnDConstbnts</code> thbt represents
 * the drop bction selected by the current drop tbrget if this drop bction is
 * supported by the drbg source or <code>DnDConstbnts.ACTION_NONE</code> if this
 * drop bction is not supported by the drbg source.
 * <p>
 * <i>User drop bction</i> depends on the drop bctions supported by the drbg
 * source bnd the drop bction selected by the user. The user cbn select b drop
 * bction by pressing modifier keys during the drbg operbtion:
 * <pre>
 *   Ctrl + Shift -&gt; ACTION_LINK
 *   Ctrl         -&gt; ACTION_COPY
 *   Shift        -&gt; ACTION_MOVE
 * </pre>
 * If the user selects b drop bction, the <i>user drop bction</i> is one of
 * <code>DnDConstbnts</code> thbt represents the selected drop bction if this
 * drop bction is supported by the drbg source or
 * <code>DnDConstbnts.ACTION_NONE</code> if this drop bction is not supported
 * by the drbg source.
 * <p>
 * If the user doesn't select b drop bction, the set of
 * <code>DnDConstbnts</code> thbt represents the set of drop bctions supported
 * by the drbg source is sebrched for <code>DnDConstbnts.ACTION_MOVE</code>,
 * then for <code>DnDConstbnts.ACTION_COPY</code>, then for
 * <code>DnDConstbnts.ACTION_LINK</code> bnd the <i>user drop bction</i> is the
 * first constbnt found. If no constbnt is found the <i>user drop bction</i>
 * is <code>DnDConstbnts.ACTION_NONE</code>.
 *
 * @since 1.2
 *
 */

public clbss DrbgSourceDrbgEvent extends DrbgSourceEvent {

    privbte stbtic finbl long seriblVersionUID = 481346297933902471L;

    /**
     * Constructs b <code>DrbgSourceDrbgEvent</code>.
     * This clbss is typicblly
     * instbntibted by the <code>DrbgSourceContextPeer</code>
     * rbther thbn directly
     * by client code.
     * The coordinbtes for this <code>DrbgSourceDrbgEvent</code>
     * bre not specified, so <code>getLocbtion</code> will return
     * <code>null</code> for this event.
     * <p>
     * The brguments <code>dropAction</code> bnd <code>bction</code> should
     * be one of <code>DnDConstbnts</code> thbt represents b single bction.
     * The brgument <code>modifiers</code> should be either b bitwise mbsk
     * of old <code>jbvb.bwt.event.InputEvent.*_MASK</code> constbnts or b
     * bitwise mbsk of extended <code>jbvb.bwt.event.InputEvent.*_DOWN_MASK</code>
     * constbnts.
     * This constructor does not throw bny exception for invblid <code>dropAction</code>,
     * <code>bction</code> bnd <code>modifiers</code>.
     *
     * @pbrbm dsc the <code>DrbgSourceContext</code> thbt is to mbnbge
     *            notificbtions for this event.
     * @pbrbm dropAction the user drop bction.
     * @pbrbm bction the tbrget drop bction.
     * @pbrbm modifiers the modifier keys down during event (shift, ctrl,
     *        blt, metb)
     *        Either extended _DOWN_MASK or old _MASK modifiers
     *        should be used, but both models should not be mixed
     *        in one event. Use of the extended modifiers is
     *        preferred.
     *
     * @throws IllegblArgumentException if <code>dsc</code> is <code>null</code>.
     *
     * @see jbvb.bwt.event.InputEvent
     * @see DrbgSourceEvent#getLocbtion
     */

    public DrbgSourceDrbgEvent(DrbgSourceContext dsc, int dropAction,
                               int bction, int modifiers) {
        super(dsc);

        tbrgetActions    = bction;
        gestureModifiers = modifiers;
        this.dropAction  = dropAction;
        if ((modifiers & ~(JDK_1_3_MODIFIERS | JDK_1_4_MODIFIERS)) != 0) {
            invblidModifiers = true;
        } else if ((getGestureModifiers() != 0) && (getGestureModifiersEx() == 0)) {
            setNewModifiers();
        } else if ((getGestureModifiers() == 0) && (getGestureModifiersEx() != 0)) {
            setOldModifiers();
        } else {
            invblidModifiers = true;
        }
    }

    /**
     * Constructs b <code>DrbgSourceDrbgEvent</code> given the specified
     * <code>DrbgSourceContext</code>, user drop bction, tbrget drop bction,
     * modifiers bnd coordinbtes.
     * <p>
     * The brguments <code>dropAction</code> bnd <code>bction</code> should
     * be one of <code>DnDConstbnts</code> thbt represents b single bction.
     * The brgument <code>modifiers</code> should be either b bitwise mbsk
     * of old <code>jbvb.bwt.event.InputEvent.*_MASK</code> constbnts or b
     * bitwise mbsk of extended <code>jbvb.bwt.event.InputEvent.*_DOWN_MASK</code>
     * constbnts.
     * This constructor does not throw bny exception for invblid <code>dropAction</code>,
     * <code>bction</code> bnd <code>modifiers</code>.
     *
     * @pbrbm dsc the <code>DrbgSourceContext</code> bssocibted with this
     *        event.
     * @pbrbm dropAction the user drop bction.
     * @pbrbm bction the tbrget drop bction.
     * @pbrbm modifiers the modifier keys down during event (shift, ctrl,
     *        blt, metb)
     *        Either extended _DOWN_MASK or old _MASK modifiers
     *        should be used, but both models should not be mixed
     *        in one event. Use of the extended modifiers is
     *        preferred.
     * @pbrbm x   the horizontbl coordinbte for the cursor locbtion
     * @pbrbm y   the verticbl coordinbte for the cursor locbtion
     *
     * @throws IllegblArgumentException if <code>dsc</code> is <code>null</code>.
     *
     * @see jbvb.bwt.event.InputEvent
     * @since 1.4
     */
    public DrbgSourceDrbgEvent(DrbgSourceContext dsc, int dropAction,
                               int bction, int modifiers, int x, int y) {
        super(dsc, x, y);

        tbrgetActions    = bction;
        gestureModifiers = modifiers;
        this.dropAction  = dropAction;
        if ((modifiers & ~(JDK_1_3_MODIFIERS | JDK_1_4_MODIFIERS)) != 0) {
            invblidModifiers = true;
        } else if ((getGestureModifiers() != 0) && (getGestureModifiersEx() == 0)) {
            setNewModifiers();
        } else if ((getGestureModifiers() == 0) && (getGestureModifiersEx() != 0)) {
            setOldModifiers();
        } else {
            invblidModifiers = true;
        }
    }

    /**
     * This method returns the tbrget drop bction.
     *
     * @return the tbrget drop bction.
     */
    public int getTbrgetActions() {
        return tbrgetActions;
    }


    privbte stbtic finbl int JDK_1_3_MODIFIERS = InputEvent.SHIFT_DOWN_MASK - 1;
    privbte stbtic finbl int JDK_1_4_MODIFIERS =
            ((InputEvent.ALT_GRAPH_DOWN_MASK << 1) - 1) & ~JDK_1_3_MODIFIERS;

    /**
     * This method returns bn <code>int</code> representing
     * the current stbte of the input device modifiers
     * bssocibted with the user's gesture. Typicblly these
     * would be mouse buttons or keybobrd modifiers.
     * <P>
     * If the <code>modifiers</code> pbssed to the constructor
     * bre invblid, this method returns them unchbnged.
     *
     * @return the current stbte of the input device modifiers
     */

    public int getGestureModifiers() {
        return invblidModifiers ? gestureModifiers : gestureModifiers & JDK_1_3_MODIFIERS;
    }

    /**
     * This method returns bn <code>int</code> representing
     * the current stbte of the input device extended modifiers
     * bssocibted with the user's gesture.
     * See {@link InputEvent#getModifiersEx}
     * <P>
     * If the <code>modifiers</code> pbssed to the constructor
     * bre invblid, this method returns them unchbnged.
     *
     * @return the current stbte of the input device extended modifiers
     * @since 1.4
     */

    public int getGestureModifiersEx() {
        return invblidModifiers ? gestureModifiers : gestureModifiers & JDK_1_4_MODIFIERS;
    }

    /**
     * This method returns the user drop bction.
     *
     * @return the user drop bction.
     */
    public int getUserAction() { return dropAction; }

    /**
     * This method returns the logicbl intersection of
     * the tbrget drop bction bnd the set of drop bctions supported by
     * the drbg source.
     *
     * @return the logicbl intersection of the tbrget drop bction bnd
     *         the set of drop bctions supported by the drbg source.
     */
    public int getDropAction() {
        return tbrgetActions & getDrbgSourceContext().getSourceActions();
    }

    /*
     * fields
     */

    /**
     * The tbrget drop bction.
     *
     * @seribl
     */
    privbte int     tbrgetActions    = DnDConstbnts.ACTION_NONE;

    /**
     * The user drop bction.
     *
     * @seribl
     */
    privbte int     dropAction       = DnDConstbnts.ACTION_NONE;

    /**
     * The stbte of the input device modifiers bssocibted with the user
     * gesture.
     *
     * @seribl
     */
    privbte int     gestureModifiers = 0;

    /**
     * Indicbtes whether the <code>gestureModifiers</code> bre invblid.
     *
     * @seribl
     */
    privbte boolebn invblidModifiers;

    /**
     * Sets new modifiers by the old ones.
     * The mouse modifiers hbve higher priority thbn overlbying key
     * modifiers.
     */
    privbte void setNewModifiers() {
        if ((gestureModifiers & InputEvent.BUTTON1_MASK) != 0) {
            gestureModifiers |= InputEvent.BUTTON1_DOWN_MASK;
        }
        if ((gestureModifiers & InputEvent.BUTTON2_MASK) != 0) {
            gestureModifiers |= InputEvent.BUTTON2_DOWN_MASK;
        }
        if ((gestureModifiers & InputEvent.BUTTON3_MASK) != 0) {
            gestureModifiers |= InputEvent.BUTTON3_DOWN_MASK;
        }
        if ((gestureModifiers & InputEvent.SHIFT_MASK) != 0) {
            gestureModifiers |= InputEvent.SHIFT_DOWN_MASK;
        }
        if ((gestureModifiers & InputEvent.CTRL_MASK) != 0) {
            gestureModifiers |= InputEvent.CTRL_DOWN_MASK;
        }
        if ((gestureModifiers & InputEvent.ALT_GRAPH_MASK) != 0) {
            gestureModifiers |= InputEvent.ALT_GRAPH_DOWN_MASK;
        }
    }

    /**
     * Sets old modifiers by the new ones.
     */
    privbte void setOldModifiers() {
        if ((gestureModifiers & InputEvent.BUTTON1_DOWN_MASK) != 0) {
            gestureModifiers |= InputEvent.BUTTON1_MASK;
        }
        if ((gestureModifiers & InputEvent.BUTTON2_DOWN_MASK) != 0) {
            gestureModifiers |= InputEvent.BUTTON2_MASK;
        }
        if ((gestureModifiers & InputEvent.BUTTON3_DOWN_MASK) != 0) {
            gestureModifiers |= InputEvent.BUTTON3_MASK;
        }
        if ((gestureModifiers & InputEvent.SHIFT_DOWN_MASK) != 0) {
            gestureModifiers |= InputEvent.SHIFT_MASK;
        }
        if ((gestureModifiers & InputEvent.CTRL_DOWN_MASK) != 0) {
            gestureModifiers |= InputEvent.CTRL_MASK;
        }
        if ((gestureModifiers & InputEvent.ALT_GRAPH_DOWN_MASK) != 0) {
            gestureModifiers |= InputEvent.ALT_GRAPH_MASK;
        }
    }
}
