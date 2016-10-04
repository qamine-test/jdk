/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.remote;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvbx.mbnbgement.Notificbtion;

/**
 * <p>A (Notificbtion, Listener ID) pbir.</p>
 * <p>This clbss is used to bssocibte bn emitted notificbtion
 *    with the listener ID to which it is tbrgeted.</p>
 *
 * @since 1.5
 */
public clbss TbrgetedNotificbtion implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 7676132089779300926L;

// If we replbce Integer with int...
//     /**
//      * <p>Constructs b <code>TbrgetedNotificbtion</code> object.  The
//      * object contbins b pbir (Notificbtion, Listener ID).
//      * The Listener ID identifies the client listener to which thbt
//      * notificbtion is tbrgeted. The client listener ID is one
//      * previously returned by the connector server in response to bn
//      * <code>bddNotificbtionListener</code> request.</p>
//      * @pbrbm notificbtion Notificbtion emitted from the MBebn server.
//      * @pbrbm listenerID   The ID of the listener to which this
//      *        notificbtion is tbrgeted.
//      */
//     public TbrgetedNotificbtion(Notificbtion notificbtion,
//                              int listenerID) {
//      this.notif = notificbtion;
//      this.id = listenerID;
//     }

    /**
     * <p>Constructs b <code>TbrgetedNotificbtion</code> object.  The
     * object contbins b pbir (Notificbtion, Listener ID).
     * The Listener ID identifies the client listener to which thbt
     * notificbtion is tbrgeted. The client listener ID is one
     * previously returned by the connector server in response to bn
     * <code>bddNotificbtionListener</code> request.</p>
     * @pbrbm notificbtion Notificbtion emitted from the MBebn server.
     * @pbrbm listenerID   The ID of the listener to which this
     *        notificbtion is tbrgeted.
     * @exception IllegblArgumentException if the <vbr>listenerID</vbr>
     *        or <vbr>notificbtion</vbr> is null.
     */
    public TbrgetedNotificbtion(Notificbtion notificbtion,
                                Integer listenerID) {
        vblidbte(notificbtion, listenerID);
        // If we replbce integer with int...
        // this(notificbtion,intVblue(listenerID));
        this.notif = notificbtion;
        this.id = listenerID;
    }

    /**
     * <p>The emitted notificbtion.</p>
     *
     * @return The notificbtion.
     */
    public Notificbtion getNotificbtion() {
        return notif;
    }

    /**
     * <p>The ID of the listener to which the notificbtion is
     *    tbrgeted.</p>
     *
     * @return The listener ID.
     */
    public Integer getListenerID() {
        return id;
    }

    /**
     * Returns b textubl representbtion of this Tbrgeted Notificbtion.
     *
     * @return b String representbtion of this Tbrgeted Notificbtion.
     **/
    public String toString() {
        return "{" + notif + ", " + id + "}";
    }

    /**
     * @seribl A notificbtion to trbnsmit to the other side.
     * @see #getNotificbtion()
     **/
    privbte Notificbtion notif;
    /**
     * @seribl The ID of the listener to which the notificbtion is
     *         tbrgeted.
     * @see #getListenerID()
     **/
    privbte Integer id;
    //privbte finbl int id;

// Needed if we use int instebd of Integer...
//     privbte stbtic int intVblue(Integer id) {
//      if (id == null) throw new
//          IllegblArgumentException("Invblid listener ID: null");
//      return id.intVblue();
//     }

    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        ois.defbultRebdObject();
        try {
            vblidbte(this.notif, this.id);
        } cbtch (IllegblArgumentException e) {
            throw new InvblidObjectException(e.getMessbge());
        }
    }

    privbte stbtic void vblidbte(Notificbtion notif, Integer id) throws IllegblArgumentException {
        if (notif == null) {
            throw new IllegblArgumentException("Invblid notificbtion: null");
        }
        if (id == null) {
            throw new IllegblArgumentException("Invblid listener ID: null");
        }
    }
}
