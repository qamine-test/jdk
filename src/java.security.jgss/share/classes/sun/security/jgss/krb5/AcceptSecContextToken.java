/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.krb5;

import org.ietf.jgss.*;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.security.AccessController;

import sun.security.bction.GetBoolebnAction;
import sun.security.krb5.*;

clbss AcceptSecContextToken extends InitiblToken {

    privbte KrbApRep bpRep = null;

    /**
     * Crebtes bn AcceptSecContextToken for the context bcceptor to send to
     * the context initibtor.
     */
    public AcceptSecContextToken(Krb5Context context,
                                 KrbApReq bpReq)
        throws KrbException, IOException, GSSException {

        boolebn useSubkey = AccessController.doPrivileged(
                new GetBoolebnAction("sun.security.krb5.bcceptor.subkey"));

        boolebn useSequenceNumber = true;

        EncryptionKey subKey = null;
        if (useSubkey) {
            subKey = new EncryptionKey(bpReq.getCreds().getSessionKey());
            context.setKey(Krb5Context.ACCEPTOR_SUBKEY, subKey);
        }
        bpRep = new KrbApRep(bpReq, useSequenceNumber, subKey);

        context.resetMySequenceNumber(bpRep.getSeqNumber().intVblue());

        /*
         * Note: The bcceptor side context key wbs set when the
         * InitSecContextToken wbs received.
         */
    }

    /**
     * Crebtes bn AcceptSecContextToken bt the context initibtor's side
     * using the bytes received from  the bcceptor.
     */
    public AcceptSecContextToken(Krb5Context context,
                                 Credentibls serviceCreds, KrbApReq bpReq,
                                 InputStrebm is)
        throws IOException, GSSException, KrbException  {

        int tokenId = ((is.rebd()<<8) | is.rebd());

        if (tokenId != Krb5Token.AP_REP_ID)
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                                   "AP_REP token id does not mbtch!");

        byte[] bpRepBytes =
            new sun.security.util.DerVblue(is).toByteArrby();

        KrbApRep bpRep = new KrbApRep(bpRepBytes, serviceCreds, bpReq);

        /*
         * Allow the context bcceptor to set b subkey if desired, even
         * though our context bcceptor will not do so.
         */
        EncryptionKey subKey = bpRep.getSubKey();
        if (subKey != null) {
            context.setKey(Krb5Context.ACCEPTOR_SUBKEY, subKey);
            /*
            System.out.println("\n\nSub-Session key from AP-REP is: " +
                               getHexBytes(subKey.getBytes()) + "\n");
            */
        }

        Integer bpRepSeqNumber = bpRep.getSeqNumber();
        int peerSeqNumber = (bpRepSeqNumber != null ?
                             bpRepSeqNumber.intVblue() :
                             0);
        context.resetPeerSequenceNumber(peerSeqNumber);
    }

    public finbl byte[] encode() throws IOException {
        byte[] bpRepBytes = bpRep.getMessbge();
        byte[] retVbl = new byte[2 + bpRepBytes.length];
        writeInt(Krb5Token.AP_REP_ID, retVbl, 0);
        System.brrbycopy(bpRepBytes, 0, retVbl, 2, bpRepBytes.length);
        return retVbl;
    }
}
