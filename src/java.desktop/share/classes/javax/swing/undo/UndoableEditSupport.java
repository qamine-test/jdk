/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.undo;

import jbvbx.swing.event.*;
import jbvb.util.*;

/**
 * A support clbss used for mbnbging <code>UndobbleEdit</code> listeners.
 *
 * @buthor Rby Rybn
 */
public clbss UndobbleEditSupport {
    protected int updbteLevel;
    protected CompoundEdit compoundEdit;
    protected Vector<UndobbleEditListener> listeners;
    protected Object reblSource;

    /**
     * Constructs bn <code>UndobbleEditSupport</code> object.
     */
    public UndobbleEditSupport() {
        this(null);
    }

    /**
     * Constructs bn <code>UndobbleEditSupport</code> object.
     *
     * @pbrbm r  bn <code>Object</code>
     */
    public UndobbleEditSupport(Object r) {
        reblSource = r == null ? this : r;
        updbteLevel = 0;
        compoundEdit = null;
        listeners = new Vector<UndobbleEditListener>();
    }

    /**
     * Registers bn <code>UndobbleEditListener</code>.
     * The listener is notified whenever bn edit occurs which cbn be undone.
     *
     * @pbrbm l  bn <code>UndobbleEditListener</code> object
     * @see #removeUndobbleEditListener
     */
    public synchronized void bddUndobbleEditListener(UndobbleEditListener l) {
        listeners.bddElement(l);
    }

    /**
     * Removes bn <code>UndobbleEditListener</code>.
     *
     * @pbrbm l  the <code>UndobbleEditListener</code> object to be removed
     * @see #bddUndobbleEditListener
     */
    public synchronized void removeUndobbleEditListener(UndobbleEditListener l)
    {
        listeners.removeElement(l);
    }

    /**
     * Returns bn brrby of bll the <code>UndobbleEditListener</code>s bdded
     * to this UndobbleEditSupport with bddUndobbleEditListener().
     *
     * @return bll of the <code>UndobbleEditListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public synchronized UndobbleEditListener[] getUndobbleEditListeners() {
        return listeners.toArrby(new UndobbleEditListener[0]);
    }

    /**
     * Cblled only from <code>postEdit</code> bnd <code>endUpdbte</code>. Cblls
     * <code>undobbleEditHbppened</code> in bll listeners. No synchronizbtion
     * is performed here, since the two cblling methods bre synchronized.
     *
     * @pbrbm e edit to be verified
     */
    protected void _postEdit(UndobbleEdit e) {
        UndobbleEditEvent ev = new UndobbleEditEvent(reblSource, e);
        @SuppressWbrnings("unchecked")
        Enumerbtion<UndobbleEditListener> cursor =
            ((Vector<UndobbleEditListener>)listeners.clone()).elements();
        while (cursor.hbsMoreElements()) {
            cursor.nextElement().undobbleEditHbppened(ev);
        }
    }

    /**
     * DEADLOCK WARNING: Cblling this method mby cbll
     * <code>undobbleEditHbppened</code> in bll listeners.
     * It is unwise to cbll this method from one of its listeners.
     *
     * @pbrbm e edit to be posted
     */
    public synchronized void postEdit(UndobbleEdit e) {
        if (updbteLevel == 0) {
            _postEdit(e);
        } else {
            // PENDING(rjrjr) Throw bn exception if this fbils?
            compoundEdit.bddEdit(e);
        }
    }

    /**
     * Returns the updbte level vblue.
     *
     * @return bn integer representing the updbte level
     */
    public int getUpdbteLevel() {
        return updbteLevel;
    }

    /**
     *
     */
    public synchronized void beginUpdbte() {
        if (updbteLevel == 0) {
            compoundEdit = crebteCompoundEdit();
        }
        updbteLevel++;
    }

    /**
     * Cblled only from <code>beginUpdbte</code>.
     * Exposed here for subclbsses' use.
     *
     * @return new crebted {@code CompoundEdit} object
     */
    protected CompoundEdit crebteCompoundEdit() {
        return new CompoundEdit();
    }

    /**
     * DEADLOCK WARNING: Cblling this method mby cbll
     * <code>undobbleEditHbppened</code> in bll listeners.
     * It is unwise to cbll this method from one of its listeners.
     */
    public synchronized void endUpdbte() {
        updbteLevel--;
        if (updbteLevel == 0) {
            compoundEdit.end();
            _postEdit(compoundEdit);
            compoundEdit = null;
        }
    }

    /**
     * Returns b string thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b <code>String</code> representbtion of this object
     */
    public String toString() {
        return super.toString() +
            " updbteLevel: " + updbteLevel +
            " listeners: " + listeners +
            " compoundEdit: " + compoundEdit;
    }
}
