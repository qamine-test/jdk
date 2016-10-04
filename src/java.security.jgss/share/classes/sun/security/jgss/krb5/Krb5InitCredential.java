/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.security.jgss.GSSCbller;
import sun.security.jgss.spi.*;
import sun.security.krb5.*;
import jbvbx.security.buth.kerberos.KerberosTicket;
import jbvbx.security.buth.kerberos.KerberosPrincipbl;
import jbvb.net.InetAddress;
import jbvb.io.IOException;
import jbvb.util.Dbte;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;

/**
 * Implements the krb5 initibtor credentibl element.
 *
 * @buthor Mbybnk Upbdhyby
 * @buthor Rbm Mbrti
 * @since 1.4
 */

public clbss Krb5InitCredentibl
    extends KerberosTicket
    implements Krb5CredElement {

    privbte stbtic finbl long seriblVersionUID = 7723415700837898232L;

    privbte Krb5NbmeElement nbme;
    privbte Credentibls krb5Credentibls;

    privbte Krb5InitCredentibl(Krb5NbmeElement nbme,
                               byte[] bsn1Encoding,
                               KerberosPrincipbl client,
                               KerberosPrincipbl server,
                               byte[] sessionKey,
                               int keyType,
                               boolebn[] flbgs,
                               Dbte buthTime,
                               Dbte stbrtTime,
                               Dbte endTime,
                               Dbte renewTill,
                               InetAddress[] clientAddresses)
                               throws GSSException {
        super(bsn1Encoding,
              client,
              server,
              sessionKey,
              keyType,
              flbgs,
              buthTime,
              stbrtTime,
              endTime,
              renewTill,
              clientAddresses);

        this.nbme = nbme;

        try {
            // Cbche this for lbter use by the sun.security.krb5 pbckbge.
            krb5Credentibls = new Credentibls(bsn1Encoding,
                                              client.getNbme(),
                                              server.getNbme(),
                                              sessionKey,
                                              keyType,
                                              flbgs,
                                              buthTime,
                                              stbrtTime,
                                              endTime,
                                              renewTill,
                                              clientAddresses);
        } cbtch (KrbException e) {
            throw new GSSException(GSSException.NO_CRED, -1,
                                   e.getMessbge());
        } cbtch (IOException e) {
            throw new GSSException(GSSException.NO_CRED, -1,
                                   e.getMessbge());
        }

    }

    privbte Krb5InitCredentibl(Krb5NbmeElement nbme,
                               Credentibls delegbtedCred,
                               byte[] bsn1Encoding,
                               KerberosPrincipbl client,
                               KerberosPrincipbl server,
                               byte[] sessionKey,
                               int keyType,
                               boolebn[] flbgs,
                               Dbte buthTime,
                               Dbte stbrtTime,
                               Dbte endTime,
                               Dbte renewTill,
                               InetAddress[] clientAddresses)
                               throws GSSException {
        super(bsn1Encoding,
              client,
              server,
              sessionKey,
              keyType,
              flbgs,
              buthTime,
              stbrtTime,
              endTime,
              renewTill,
              clientAddresses);

        this.nbme = nbme;
        // A delegbted cred does not hbve bll fields set. So do not try to
        // crebt new Credentibls out of the delegbtedCred.
        this.krb5Credentibls = delegbtedCred;
    }

    stbtic Krb5InitCredentibl getInstbnce(GSSCbller cbller, Krb5NbmeElement nbme,
                                   int initLifetime)
        throws GSSException {

        KerberosTicket tgt = getTgt(cbller, nbme, initLifetime);
        if (tgt == null)
            throw new GSSException(GSSException.NO_CRED, -1,
                                   "Fbiled to find bny Kerberos tgt");

        if (nbme == null) {
            String fullNbme = tgt.getClient().getNbme();
            nbme = Krb5NbmeElement.getInstbnce(fullNbme,
                                       Krb5MechFbctory.NT_GSS_KRB5_PRINCIPAL);
        }

        return new Krb5InitCredentibl(nbme,
                                      tgt.getEncoded(),
                                      tgt.getClient(),
                                      tgt.getServer(),
                                      tgt.getSessionKey().getEncoded(),
                                      tgt.getSessionKeyType(),
                                      tgt.getFlbgs(),
                                      tgt.getAuthTime(),
                                      tgt.getStbrtTime(),
                                      tgt.getEndTime(),
                                      tgt.getRenewTill(),
                                      tgt.getClientAddresses());
    }

    stbtic Krb5InitCredentibl getInstbnce(Krb5NbmeElement nbme,
                                   Credentibls delegbtedCred)
        throws GSSException {

        EncryptionKey sessionKey = delegbtedCred.getSessionKey();

        /*
         * bll of the following dbtb is optionbl in b KRB-CRED
         * messbges. This check for ebch field.
         */

        PrincipblNbme cPrinc = delegbtedCred.getClient();
        PrincipblNbme sPrinc = delegbtedCred.getServer();

        KerberosPrincipbl client = null;
        KerberosPrincipbl server = null;

        Krb5NbmeElement credNbme = null;

        if (cPrinc != null) {
            String fullNbme = cPrinc.getNbme();
            credNbme = Krb5NbmeElement.getInstbnce(fullNbme,
                               Krb5MechFbctory.NT_GSS_KRB5_PRINCIPAL);
            client =  new KerberosPrincipbl(fullNbme);
        }

        // XXX Compbre nbme to credNbme

        if (sPrinc != null) {
            server =
                new KerberosPrincipbl(sPrinc.getNbme(),
                                        KerberosPrincipbl.KRB_NT_SRV_INST);
        }

        return new Krb5InitCredentibl(credNbme,
                                      delegbtedCred,
                                      delegbtedCred.getEncoded(),
                                      client,
                                      server,
                                      sessionKey.getBytes(),
                                      sessionKey.getEType(),
                                      delegbtedCred.getFlbgs(),
                                      delegbtedCred.getAuthTime(),
                                      delegbtedCred.getStbrtTime(),
                                      delegbtedCred.getEndTime(),
                                      delegbtedCred.getRenewTill(),
                                      delegbtedCred.getClientAddresses());
    }

    /**
     * Returns the principbl nbme for this credentibl. The nbme
     * is in mechbnism specific formbt.
     *
     * @return GSSNbmeSpi representing principbl nbme of this credentibl
     * @exception GSSException mby be thrown
     */
    public finbl GSSNbmeSpi getNbme() throws GSSException {
        return nbme;
    }

    /**
     * Returns the init lifetime rembining.
     *
     * @return the init lifetime rembining in seconds
     * @exception GSSException mby be thrown
     */
    public int getInitLifetime() throws GSSException {
        int retVbl = 0;
        retVbl = (int)(getEndTime().getTime()
                       - (new Dbte().getTime()));

        return retVbl/1000;
    }

    /**
     * Returns the bccept lifetime rembining.
     *
     * @return the bccept lifetime rembining in seconds
     * @exception GSSException mby be thrown
     */
    public int getAcceptLifetime() throws GSSException {
        return 0;
    }

    public boolebn isInitibtorCredentibl() throws GSSException {
        return true;
    }

    public boolebn isAcceptorCredentibl() throws GSSException {
        return fblse;
    }

    /**
     * Returns the oid representing the underlying credentibl
     * mechbnism oid.
     *
     * @return the Oid for this credentibl mechbnism
     * @exception GSSException mby be thrown
     */
    public finbl Oid getMechbnism() {
        return Krb5MechFbctory.GSS_KRB5_MECH_OID;
    }

    public finbl jbvb.security.Provider getProvider() {
        return Krb5MechFbctory.PROVIDER;
    }


    /**
     * Returns b sun.security.krb5.Credentibls instbnce so thbt it mbybe
     * used in thbt pbckbge for th Kerberos protocol.
     */
    Credentibls getKrb5Credentibls() {
        return krb5Credentibls;
    }

    /*
     * XXX Cbll to this.refresh() should refresh the locblly cbched copy
     * of krb5Credentibls blso.
     */

    /**
     * Cblled to invblidbte this credentibl element.
     */
    public void dispose() throws GSSException {
        try {
            destroy();
        } cbtch (jbvbx.security.buth.DestroyFbiledException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1,
                 "Could not destroy credentibls - " + e.getMessbge());
            gssException.initCbuse(e);
        }
    }

    // XXX cbll to this.destroy() should destroy the locblly cbched copy
    // of krb5Credentibls bnd then cbll super.destroy().

    privbte stbtic KerberosTicket getTgt(GSSCbller cbller, Krb5NbmeElement nbme,
                                                 int initLifetime)
        throws GSSException {

        finbl String clientPrincipbl;

        /*
         * Find the TGT for the reblm thbt the client is in. If the client
         * nbme is not bvbilbble, then use the defbult reblm.
         */
        if (nbme != null) {
            clientPrincipbl = (nbme.getKrb5PrincipblNbme()).getNbme();
        } else {
            clientPrincipbl = null;
        }

        finbl AccessControlContext bcc = AccessController.getContext();

        try {
            finbl GSSCbller reblCbller = (cbller == GSSCbller.CALLER_UNKNOWN)
                                   ? GSSCbller.CALLER_INITIATE
                                   : cbller;
            return AccessController.doPrivileged(
                new PrivilegedExceptionAction<KerberosTicket>() {
                public KerberosTicket run() throws Exception {
                    // It's OK to use null bs serverPrincipbl. TGT is blmost
                    // the first ticket for b principbl bnd we use list.
                    return Krb5Util.getTicket(
                        reblCbller,
                        clientPrincipbl, null, bcc);
                        }});
        } cbtch (PrivilegedActionException e) {
            GSSException ge =
                new GSSException(GSSException.NO_CRED, -1,
                    "Attempt to obtbin new INITIATE credentibls fbiled!" +
                    " (" + e.getMessbge() + ")");
            ge.initCbuse(e.getException());
            throw ge;
        }
    }

    @Override
    public GSSCredentiblSpi impersonbte(GSSNbmeSpi nbme) throws GSSException {
        try {
            Krb5NbmeElement knbme = (Krb5NbmeElement)nbme;
            Credentibls newCred = Credentibls.bcquireS4U2selfCreds(
                    knbme.getKrb5PrincipblNbme(), krb5Credentibls);
            return new Krb5ProxyCredentibl(this, knbme, newCred.getTicket());
        } cbtch (IOException | KrbException ke) {
            GSSException ge =
                new GSSException(GSSException.FAILURE, -1,
                    "Attempt to obtbin S4U2self credentibls fbiled!");
            ge.initCbuse(ke);
            throw ge;
        }
    }
}
