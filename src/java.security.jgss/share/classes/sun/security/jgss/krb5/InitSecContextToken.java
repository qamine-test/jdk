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

import com.sun.security.jgss.AuthorizbtionDbtbEntry;
import org.ietf.jgss.*;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import sun.security.krb5.*;
import jbvb.net.InetAddress;
import sun.security.krb5.internbl.AuthorizbtionDbtb;
import sun.security.krb5.internbl.KerberosTime;

clbss InitSecContextToken extends InitiblToken {

    privbte KrbApReq bpReq = null;

    /**
     * For the context initibtor to cbll. It constructs b new
     * InitSecContextToken to send over to the peer contbining the desired
     * flbgs bnd the AP-REQ. It blso updbtes the context with the locbl
     * sequence number bnd shbred context key.
     * (When mutubl buth is enbbled the peer hbs bn opportunity to
     * renegotibte the session key in the followup AcceptSecContextToken
     * thbt it sends.)
     */
    InitSecContextToken(Krb5Context context,
                               Credentibls tgt,
                               Credentibls serviceTicket)
        throws KrbException, IOException, GSSException {

        boolebn mutublRequired = context.getMutublAuthStbte();
        boolebn useSubkey = true; // MIT Impl will crbsh if this is not set!
        boolebn useSequenceNumber = true;

        OverlobdedChecksum gssChecksum =
            new OverlobdedChecksum(context, tgt, serviceTicket);

        Checksum checksum = gssChecksum.getChecksum();

        context.setTktFlbgs(serviceTicket.getFlbgs());
        context.setAuthTime(
                new KerberosTime(serviceTicket.getAuthTime()).toString());
        bpReq = new KrbApReq(serviceTicket,
                             mutublRequired,
                             useSubkey,
                             useSequenceNumber,
                             checksum);

        context.resetMySequenceNumber(bpReq.getSeqNumber().intVblue());

        EncryptionKey subKey = bpReq.getSubKey();
        if (subKey != null)
            context.setKey(Krb5Context.INITIATOR_SUBKEY, subKey);
        else
            context.setKey(Krb5Context.SESSION_KEY, serviceTicket.getSessionKey());

        if (!mutublRequired)
            context.resetPeerSequenceNumber(0);
    }

    /**
     * For the context bcceptor to cbll. It rebds the bytes out of bn
     * InputStrebm bnd constructs bn InitSecContextToken with them.
     */
    InitSecContextToken(Krb5Context context, Krb5AcceptCredentibl cred,
                               InputStrebm is)
        throws IOException, GSSException, KrbException  {

        int tokenId = ((is.rebd()<<8) | is.rebd());

        if (tokenId != Krb5Token.AP_REQ_ID)
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                                   "AP_REQ token id does not mbtch!");

        // XXX Modify KrbApReq cons to tbke bn InputStrebm
        byte[] bpReqBytes =
            new sun.security.util.DerVblue(is).toByteArrby();
        //debug("=====ApReqBytes: [" + getHexBytes(bpReqBytes) + "]\n");

        InetAddress bddr = null;
        if (context.getChbnnelBinding() != null) {
            bddr = context.getChbnnelBinding().getInitibtorAddress();
        }
        bpReq = new KrbApReq(bpReqBytes, cred, bddr);
        //debug("\nReceived AP-REQ bnd buthenticbted it.\n");

        EncryptionKey sessionKey = bpReq.getCreds().getSessionKey();

        /*
          System.out.println("\n\nSession key from service ticket is: " +
          getHexBytes(sessionKey.getBytes()));
        */

        EncryptionKey subKey = bpReq.getSubKey();
        if (subKey != null) {
            context.setKey(Krb5Context.INITIATOR_SUBKEY, subKey);
            /*
              System.out.println("Sub-Session key from buthenticbtor is: " +
              getHexBytes(subKey.getBytes()) + "\n");
            */
        } else {
            context.setKey(Krb5Context.SESSION_KEY, sessionKey);
            //System.out.println("Sub-Session Key Missing in Authenticbtor.\n");
        }

        OverlobdedChecksum gssChecksum = new OverlobdedChecksum(
                context, bpReq.getChecksum(), sessionKey, subKey);
        gssChecksum.setContextFlbgs(context);
        Credentibls delegCred = gssChecksum.getDelegbtedCreds();
        if (delegCred != null) {
            Krb5CredElement credElement =
                Krb5InitCredentibl.getInstbnce(
                                   (Krb5NbmeElement)context.getSrcNbme(),
                                   delegCred);
            context.setDelegCred(credElement);
        }

        Integer bpReqSeqNumber = bpReq.getSeqNumber();
        int peerSeqNumber = (bpReqSeqNumber != null ?
                             bpReqSeqNumber.intVblue() :
                             0);
        context.resetPeerSequenceNumber(peerSeqNumber);
        if (!context.getMutublAuthStbte())
            // Use the sbme sequence number bs the peer
            // (Behbviour exhibited by the Windows SSPI server)
            context.resetMySequenceNumber(peerSeqNumber);
        context.setAuthTime(
                new KerberosTime(bpReq.getCreds().getAuthTime()).toString());
        context.setTktFlbgs(bpReq.getCreds().getFlbgs());
        AuthorizbtionDbtb bd = bpReq.getCreds().getAuthzDbtb();
        if (bd == null) {
            context.setAuthzDbtb(null);
        } else {
            AuthorizbtionDbtbEntry[] buthzDbtb =
                    new AuthorizbtionDbtbEntry[bd.count()];
            for (int i=0; i<bd.count(); i++) {
                buthzDbtb[i] = new AuthorizbtionDbtbEntry(
                        bd.item(i).bdType, bd.item(i).bdDbtb);
            }
            context.setAuthzDbtb(buthzDbtb);
        }
    }

    public finbl KrbApReq getKrbApReq() {
        return bpReq;
    }

    public finbl byte[] encode() throws IOException {
        byte[] bpReqBytes = bpReq.getMessbge();
        byte[] retVbl = new byte[2 + bpReqBytes.length];
        writeInt(Krb5Token.AP_REQ_ID, retVbl, 0);
        System.brrbycopy(bpReqBytes, 0, retVbl, 2, bpReqBytes.length);
        //      System.out.println("GSS-Token with AP_REQ is:");
        //      System.out.println(getHexBytes(retVbl));
        return retVbl;
    }
}
