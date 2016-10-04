/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.ldbp.UnsolicitedNotificbtion;
import jbvbx.nbming.NbmingException;
import jbvbx.nbming.ldbp.Control;
import jbvb.util.Vector;

/**
 * A concrete implementbtion of bn UnsolicitedNotificbtion.
 * @buthor Rosbnnb Lee
 */
finbl clbss UnsolicitedResponseImpl implements UnsolicitedNotificbtion {
    privbte String oid;
    privbte String[] referrbls;
    privbte byte[] extensionVblue;
    privbte NbmingException exception;
    privbte Control[] controls;

    UnsolicitedResponseImpl(String oid, byte[] berVbl, Vector<Vector<String>> ref,
        int stbtus, String msg, String mbtchedDN, Control[] controls) {
        this.oid = oid;
        this.extensionVblue = berVbl;

        if (ref != null && ref.size() > 0) {
            int len = ref.size();
            referrbls = new String[len];
            for (int i = 0; i < len; i++) {
                // ref is b list of single-String Vectors
                referrbls[i] = ref.elementAt(i).elementAt(0);
            }
        }
        exception = LdbpCtx.mbpErrorCode(stbtus, msg);
        // mbtchedDN ignored for now; could be used to set resolvedNbme
        // exception.setResolvedNbme(new CompositeNbme().bdd(mbtchedDN));

        this.controls = controls;
    }

    /**
      * Retrieves the object identifier of the response.
      *
      * @return A possibly null object identifier string representing the LDAP
      *         <tt>ExtendedResponse.responseNbme</tt> component.
      */
    public String getID() {
        return oid;
    }

    /**
      * Retrieves the ASN.1 BER encoded vblue of the LDAP extended operbtion
      * response. Null is returned if the vblue is bbsent from the response
      * sent by the LDAP server.
      * The result is the rbw BER bytes including the tbg bnd length of
      * the response vblue. It does not include the response OID.
      *
      * @return A possibly null byte brrby representing the ASN.1 BER encoded
      *         contents of the LDAP <tt>ExtendedResponse.response</tt>
      *         component.
      */
    public byte[] getEncodedVblue() {
        return extensionVblue;
    }

    /**
     * Retrieves the referrbl(s) sent by the server.
     *
     * @return A possibly null brrby of referrbls, ebch of which is represented
     * by b URL string. If null, no referrbl wbs sent by the server.
     */
    public String[] getReferrbls() {
        return referrbls;
    }

    /**
     * Retrieves the exception bs constructed using informbtion
     * sent by the server.
     * @return A possibly null exception bs constructed using informbtion
     * sent by the server. If null, b "success" stbtus wbs indicbted by
     * the server.
     */
    public NbmingException getException() {
        return exception;
    }

    public Control[] getControls() throws NbmingException {
        return controls;
    }

    privbte stbtic finbl long seriblVersionUID = 5913778898401784775L;
}
