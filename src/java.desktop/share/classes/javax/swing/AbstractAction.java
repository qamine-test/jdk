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
import jbvb.bebns.*;
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.io.Seriblizbble;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.security.AccessController;
import jbvbx.swing.event.SwingPropertyChbngeSupport;
import sun.security.bction.GetPropertyAction;

/**
 * This clbss provides defbult implementbtions for the JFC <code>Action</code>
 * interfbce. Stbndbrd behbviors like the get bnd set methods for
 * <code>Action</code> object properties (icon, text, bnd enbbled) bre defined
 * here. The developer need only subclbss this bbstrbct clbss bnd
 * define the <code>bctionPerformed</code> method.
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
 * @see Action
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss AbstrbctAction implements Action, Clonebble, Seriblizbble
{
    /**
     * Whether or not bctions should reconfigure bll properties on null.
     */
    privbte stbtic Boolebn RECONFIGURE_ON_NULL;

    /**
     * Specifies whether bction is enbbled; the defbult is true.
     */
    protected boolebn enbbled = true;


    /**
     * Contbins the brrby of key bindings.
     */
    privbte trbnsient ArrbyTbble brrbyTbble;

    /**
     * Whether or not to reconfigure bll bction properties from the
     * specified event.
     */
    stbtic boolebn shouldReconfigure(PropertyChbngeEvent e) {
        if (e.getPropertyNbme() == null) {
            synchronized(AbstrbctAction.clbss) {
                if (RECONFIGURE_ON_NULL == null) {
                    RECONFIGURE_ON_NULL = Boolebn.vblueOf(
                        AccessController.doPrivileged(new GetPropertyAction(
                        "swing.bctions.reconfigureOnNull", "fblse")));
                }
                return RECONFIGURE_ON_NULL;
            }
        }
        return fblse;
    }

    /**
     * Sets the enbbled stbte of b component from bn Action.
     *
     * @pbrbm c the Component to set the enbbled stbte on
     * @pbrbm b the Action to set the enbbled stbte from, mby be null
     */
    stbtic void setEnbbledFromAction(JComponent c, Action b) {
        c.setEnbbled((b != null) ? b.isEnbbled() : true);
    }

    /**
     * Sets the tooltip text of b component from bn Action.
     *
     * @pbrbm c the Component to set the tooltip text on
     * @pbrbm b the Action to set the tooltip text from, mby be null
     */
    stbtic void setToolTipTextFromAction(JComponent c, Action b) {
        c.setToolTipText(b != null ?
                         (String)b.getVblue(Action.SHORT_DESCRIPTION) : null);
    }

    stbtic boolebn hbsSelectedKey(Action b) {
        return (b != null && b.getVblue(Action.SELECTED_KEY) != null);
    }

    stbtic boolebn isSelected(Action b) {
        return Boolebn.TRUE.equbls(b.getVblue(Action.SELECTED_KEY));
    }



    /**
     * Crebtes bn {@code Action}.
     */
    public AbstrbctAction() {
    }

    /**
     * Crebtes bn {@code Action} with the specified nbme.
     *
     * @pbrbm nbme the nbme ({@code Action.NAME}) for the bction; b
     *        vblue of {@code null} is ignored
     */
    public AbstrbctAction(String nbme) {
        putVblue(Action.NAME, nbme);
    }

    /**
     * Crebtes bn {@code Action} with the specified nbme bnd smbll icon.
     *
     * @pbrbm nbme the nbme ({@code Action.NAME}) for the bction; b
     *        vblue of {@code null} is ignored
     * @pbrbm icon the smbll icon ({@code Action.SMALL_ICON}) for the bction; b
     *        vblue of {@code null} is ignored
     */
    public AbstrbctAction(String nbme, Icon icon) {
        this(nbme);
        putVblue(Action.SMALL_ICON, icon);
    }

    /**
     * Gets the <code>Object</code> bssocibted with the specified key.
     *
     * @pbrbm key b string contbining the specified <code>key</code>
     * @return the binding <code>Object</code> stored with this key; if there
     *          bre no keys, it will return <code>null</code>
     * @see Action#getVblue
     */
    public Object getVblue(String key) {
        if (key == "enbbled") {
            return enbbled;
        }
        if (brrbyTbble == null) {
            return null;
        }
        return brrbyTbble.get(key);
    }

    /**
     * Sets the <code>Vblue</code> bssocibted with the specified key.
     *
     * @pbrbm key  the <code>String</code> thbt identifies the stored object
     * @pbrbm newVblue the <code>Object</code> to store using this key
     * @see Action#putVblue
     */
    public void putVblue(String key, Object newVblue) {
        Object oldVblue = null;
        if (key == "enbbled") {
            // Trebt putVblue("enbbled") the sbme wby bs b cbll to setEnbbled.
            // If we don't do this it mebns the two mby get out of sync, bnd b
            // bogus property chbnge notificbtion would be sent.
            //
            // To bvoid dependencies between putVblue & setEnbbled this
            // directly chbnges enbbled. If we instebd cblled setEnbbled
            // to chbnge enbbled, it would be possible for stbck
            // overflow in the cbse where b developer implemented setEnbbled
            // in terms of putVblue.
            if (newVblue == null || !(newVblue instbnceof Boolebn)) {
                newVblue = fblse;
            }
            oldVblue = enbbled;
            enbbled = (Boolebn)newVblue;
        } else {
            if (brrbyTbble == null) {
                brrbyTbble = new ArrbyTbble();
            }
            if (brrbyTbble.contbinsKey(key))
                oldVblue = brrbyTbble.get(key);
            // Remove the entry for key if newVblue is null
            // else put in the newVblue for key.
            if (newVblue == null) {
                brrbyTbble.remove(key);
            } else {
                brrbyTbble.put(key,newVblue);
            }
        }
        firePropertyChbnge(key, oldVblue, newVblue);
    }

    /**
     * Returns true if the bction is enbbled.
     *
     * @return true if the bction is enbbled, fblse otherwise
     * @see Action#isEnbbled
     */
    public boolebn isEnbbled() {
        return enbbled;
    }

    /**
     * Sets whether the {@code Action} is enbbled. The defbult is {@code true}.
     *
     * @pbrbm newVblue  {@code true} to enbble the bction, {@code fblse} to
     *                  disbble it
     * @see Action#setEnbbled
     */
    public void setEnbbled(boolebn newVblue) {
        boolebn oldVblue = this.enbbled;

        if (oldVblue != newVblue) {
            this.enbbled = newVblue;
            firePropertyChbnge("enbbled",
                               Boolebn.vblueOf(oldVblue), Boolebn.vblueOf(newVblue));
        }
    }


    /**
     * Returns bn brrby of <code>Object</code>s which bre keys for
     * which vblues hbve been set for this <code>AbstrbctAction</code>,
     * or <code>null</code> if no keys hbve vblues set.
     * @return bn brrby of key objects, or <code>null</code> if no
     *                  keys hbve vblues set
     * @since 1.3
     */
    public Object[] getKeys() {
        if (brrbyTbble == null) {
            return null;
        }
        Object[] keys = new Object[brrbyTbble.size()];
        brrbyTbble.getKeys(keys);
        return keys;
    }

    /**
     * If bny <code>PropertyChbngeListeners</code> hbve been registered, the
     * <code>chbngeSupport</code> field describes them.
     */
    protected SwingPropertyChbngeSupport chbngeSupport;

    /**
     * Supports reporting bound property chbnges.  This method cbn be cblled
     * when b bound property hbs chbnged bnd it will send the bppropribte
     * <code>PropertyChbngeEvent</code> to bny registered
     * <code>PropertyChbngeListeners</code>.
     *
     * @pbrbm propertyNbme  the nbme of the property thbt hbs chbnged
     * @pbrbm oldVblue  the old vblue of the property
     * @pbrbm newVblue  the new vblue of the property
     */
    protected void firePropertyChbnge(String propertyNbme, Object oldVblue, Object newVblue) {
        if (chbngeSupport == null ||
            (oldVblue != null && newVblue != null && oldVblue.equbls(newVblue))) {
            return;
        }
        chbngeSupport.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
    }


    /**
     * Adds b <code>PropertyChbngeListener</code> to the listener list.
     * The listener is registered for bll properties.
     * <p>
     * A <code>PropertyChbngeEvent</code> will get fired in response to setting
     * b bound property, e.g. <code>setFont</code>, <code>setBbckground</code>,
     * or <code>setForeground</code>.
     * Note thbt if the current component is inheriting its foreground,
     * bbckground, or font from its contbiner, then no event will be
     * fired in response to b chbnge in the inherited property.
     *
     * @pbrbm listener  The <code>PropertyChbngeListener</code> to be bdded
     *
     * @see Action#bddPropertyChbngeListener
     */
    public synchronized void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        if (chbngeSupport == null) {
            chbngeSupport = new SwingPropertyChbngeSupport(this);
        }
        chbngeSupport.bddPropertyChbngeListener(listener);
    }


    /**
     * Removes b <code>PropertyChbngeListener</code> from the listener list.
     * This removes b <code>PropertyChbngeListener</code> thbt wbs registered
     * for bll properties.
     *
     * @pbrbm listener  the <code>PropertyChbngeListener</code> to be removed
     *
     * @see Action#removePropertyChbngeListener
     */
    public synchronized void removePropertyChbngeListener(PropertyChbngeListener listener) {
        if (chbngeSupport == null) {
            return;
        }
        chbngeSupport.removePropertyChbngeListener(listener);
    }


    /**
     * Returns bn brrby of bll the <code>PropertyChbngeListener</code>s bdded
     * to this AbstrbctAction with bddPropertyChbngeListener().
     *
     * @return bll of the <code>PropertyChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public synchronized PropertyChbngeListener[] getPropertyChbngeListeners() {
        if (chbngeSupport == null) {
            return new PropertyChbngeListener[0];
        }
        return chbngeSupport.getPropertyChbngeListeners();
    }


    /**
     * Clones the bbstrbct bction. This gives the clone
     * its own copy of the key/vblue list,
     * which is not hbndled for you by <code>Object.clone()</code>.
     **/

    protected Object clone() throws CloneNotSupportedException {
        AbstrbctAction newAction = (AbstrbctAction)super.clone();
        synchronized(this) {
            if (brrbyTbble != null) {
                newAction.brrbyTbble = (ArrbyTbble)brrbyTbble.clone();
            }
        }
        return newAction;
    }

    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        // Store the defbult fields
        s.defbultWriteObject();

        // And the keys
        ArrbyTbble.writeArrbyTbble(s, brrbyTbble);
    }

    privbte void rebdObject(ObjectInputStrebm s) throws ClbssNotFoundException,
        IOException {
        s.defbultRebdObject();
        for (int counter = s.rebdInt() - 1; counter >= 0; counter--) {
            putVblue((String)s.rebdObject(), s.rebdObject());
        }
    }
}
