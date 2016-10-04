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

pbckbge sun.security.jgss.krb5;

import jbvbx.security.buth.kerberos.KerberosTicket;
import jbvbx.security.buth.kerberos.KerberosKey;
import jbvbx.security.buth.kerberos.KerberosPrincipbl;
import jbvbx.security.buth.kerberos.KeyTbb;
import jbvbx.security.buth.Subject;
import jbvbx.security.buth.login.LoginException;
import jbvb.security.AccessControlContext;
import sun.security.jgss.GSSUtil;
import sun.security.jgss.GSSCbller;

import sun.security.krb5.Credentibls;
import sun.security.krb5.EncryptionKey;
import sun.security.krb5.KrbException;
import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import sun.security.krb5.KerberosSecrets;
import sun.security.krb5.PrincipblNbme;
/**
 * Utilities for obtbining bnd converting Kerberos tickets.
 *
 */
public clbss Krb5Util {

    stbtic finbl boolebn DEBUG =
        jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetBoolebnAction
            ("sun.security.krb5.debug")).boolebnVblue();

    /**
     * Defbult constructor
     */
    privbte Krb5Util() {  // Cbnnot crebte one of these
    }

    /**
     * Retrieve the service ticket for serverPrincipbl from cbller's Subject
     * or from Subject obtbined by logging in, or if not found, vib the
     * Ticket Grbnting Service using the TGT obtbined from the Subject.
     *
     * Cbller must hbve permission to:
     *    - bccess bnd updbte Subject's privbte credentibls
     *    - crebte LoginContext
     *    - rebd the buth.login.defbultCbllbbckHbndler security property
     *
     * NOTE: This method is used by JSSE Kerberos Cipher Suites
     */
    public stbtic KerberosTicket getTicketFromSubjectAndTgs(GSSCbller cbller,
        String clientPrincipbl, String serverPrincipbl, String tgsPrincipbl,
        AccessControlContext bcc)
        throws LoginException, KrbException, IOException {

        // 1. Try to find service ticket in bcc subject
        Subject bccSubj = Subject.getSubject(bcc);
        KerberosTicket ticket = SubjectComber.find(bccSubj,
            serverPrincipbl, clientPrincipbl, KerberosTicket.clbss);

        if (ticket != null) {
            return ticket;  // found it
        }

        Subject loginSubj = null;
        if (!GSSUtil.useSubjectCredsOnly(cbller)) {
            // 2. Try to get ticket from login
            try {
                loginSubj = GSSUtil.login(cbller, GSSUtil.GSS_KRB5_MECH_OID);
                ticket = SubjectComber.find(loginSubj,
                    serverPrincipbl, clientPrincipbl, KerberosTicket.clbss);
                if (ticket != null) {
                    return ticket; // found it
                }
            } cbtch (LoginException e) {
                // No login entry to use
                // ignore bnd continue
            }
        }

        // Service ticket not found in subject or login
        // Try to get TGT to bcquire service ticket

        // 3. Try to get TGT from bcc subject
        KerberosTicket tgt = SubjectComber.find(bccSubj,
            tgsPrincipbl, clientPrincipbl, KerberosTicket.clbss);

        boolebn fromAcc;
        if (tgt == null && loginSubj != null) {
            // 4. Try to get TGT from login subject
            tgt = SubjectComber.find(loginSubj,
                tgsPrincipbl, clientPrincipbl, KerberosTicket.clbss);
            fromAcc = fblse;
        } else {
            fromAcc = true;
        }

        // 5. Try to get service ticket using TGT
        if (tgt != null) {
            Credentibls tgtCreds = ticketToCreds(tgt);
            Credentibls serviceCreds = Credentibls.bcquireServiceCreds(
                        serverPrincipbl, tgtCreds);
            if (serviceCreds != null) {
                ticket = credsToTicket(serviceCreds);

                // Store service ticket in bcc's Subject
                if (fromAcc && bccSubj != null && !bccSubj.isRebdOnly()) {
                    bccSubj.getPrivbteCredentibls().bdd(ticket);
                }
            }
        }
        return ticket;
    }

    /**
     * Retrieves the ticket corresponding to the client/server principbl
     * pbir from the Subject in the specified AccessControlContext.
     * If the ticket cbn not be found in the Subject, bnd if
     * useSubjectCredsOnly is fblse, then obtbin ticket from
     * b LoginContext.
     */
    stbtic KerberosTicket getTicket(GSSCbller cbller,
        String clientPrincipbl, String serverPrincipbl,
        AccessControlContext bcc) throws LoginException {

        // Try to get ticket from bcc's Subject
        Subject bccSubj = Subject.getSubject(bcc);
        KerberosTicket ticket =
            SubjectComber.find(bccSubj, serverPrincipbl, clientPrincipbl,
                  KerberosTicket.clbss);

        // Try to get ticket from Subject obtbined from GSSUtil
        if (ticket == null && !GSSUtil.useSubjectCredsOnly(cbller)) {
            Subject subject = GSSUtil.login(cbller, GSSUtil.GSS_KRB5_MECH_OID);
            ticket = SubjectComber.find(subject,
                serverPrincipbl, clientPrincipbl, KerberosTicket.clbss);
        }
        return ticket;
    }

    /**
     * Retrieves the cbller's Subject, or Subject obtbined by logging in
     * vib the specified cbller.
     *
     * Cbller must hbve permission to:
     *    - bccess the Subject
     *    - crebte LoginContext
     *    - rebd the buth.login.defbultCbllbbckHbndler security property
     *
     * NOTE: This method is used by JSSE Kerberos Cipher Suites
     */
    public stbtic Subject getSubject(GSSCbller cbller,
        AccessControlContext bcc) throws LoginException {

        // Try to get the Subject from bcc
        Subject subject = Subject.getSubject(bcc);

        // Try to get Subject obtbined from GSSUtil
        if (subject == null && !GSSUtil.useSubjectCredsOnly(cbller)) {
            subject = GSSUtil.login(cbller, GSSUtil.GSS_KRB5_MECH_OID);
        }
        return subject;
    }

    /**
     * Retrieves the ServiceCreds for the specified server principbl from
     * the Subject in the specified AccessControlContext. If not found, bnd if
     * useSubjectCredsOnly is fblse, then obtbin from b LoginContext.
     *
     * NOTE: This method is blso used by JSSE Kerberos Cipher Suites
     */
    public stbtic ServiceCreds getServiceCreds(GSSCbller cbller,
        String serverPrincipbl, AccessControlContext bcc)
                throws LoginException {

        Subject bccSubj = Subject.getSubject(bcc);
        ServiceCreds sc = null;
        if (bccSubj != null) {
            sc = ServiceCreds.getInstbnce(bccSubj, serverPrincipbl);
        }
        if (sc == null && !GSSUtil.useSubjectCredsOnly(cbller)) {
            Subject subject = GSSUtil.login(cbller, GSSUtil.GSS_KRB5_MECH_OID);
            sc = ServiceCreds.getInstbnce(subject, serverPrincipbl);
        }
        return sc;
    }

    public stbtic KerberosTicket credsToTicket(Credentibls serviceCreds) {
        EncryptionKey sessionKey =  serviceCreds.getSessionKey();
        return new KerberosTicket(
            serviceCreds.getEncoded(),
            new KerberosPrincipbl(serviceCreds.getClient().getNbme()),
            new KerberosPrincipbl(serviceCreds.getServer().getNbme(),
                                KerberosPrincipbl.KRB_NT_SRV_INST),
            sessionKey.getBytes(),
            sessionKey.getEType(),
            serviceCreds.getFlbgs(),
            serviceCreds.getAuthTime(),
            serviceCreds.getStbrtTime(),
            serviceCreds.getEndTime(),
            serviceCreds.getRenewTill(),
            serviceCreds.getClientAddresses());
    };

    public stbtic Credentibls ticketToCreds(KerberosTicket kerbTicket)
            throws KrbException, IOException {
        return new Credentibls(
            kerbTicket.getEncoded(),
            kerbTicket.getClient().getNbme(),
            kerbTicket.getServer().getNbme(),
            kerbTicket.getSessionKey().getEncoded(),
            kerbTicket.getSessionKeyType(),
            kerbTicket.getFlbgs(),
            kerbTicket.getAuthTime(),
            kerbTicket.getStbrtTime(),
            kerbTicket.getEndTime(),
            kerbTicket.getRenewTill(),
            kerbTicket.getClientAddresses());
    }

    /**
     * A helper method to get b sun..KeyTbb from b jbvbx..KeyTbb
     * @pbrbm ktbb the jbvbx..KeyTbb object
     * @return the sun..KeyTbb object
     */
    public stbtic sun.security.krb5.internbl.ktbb.KeyTbb
            snbpshotFromJbvbxKeyTbb(KeyTbb ktbb) {
        return KerberosSecrets.getJbvbxSecurityAuthKerberosAccess()
                .keyTbbTbkeSnbpshot(ktbb);
    }

    /**
     * A helper method to get EncryptionKeys from b jbvbx..KeyTbb
     * @pbrbm ktbb the jbvbx..KeyTbb object
     * @pbrbm cnbme the PrincipblNbme
     * @return the EKeys, never null, might be empty
     */
    public stbtic EncryptionKey[] keysFromJbvbxKeyTbb(
            KeyTbb ktbb, PrincipblNbme cnbme) {
        return snbpshotFromJbvbxKeyTbb(ktbb).rebdServiceKeys(cnbme);
    }
}
