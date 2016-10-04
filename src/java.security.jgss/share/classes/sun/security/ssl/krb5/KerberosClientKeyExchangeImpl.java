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

pbckbge sun.security.ssl.krb5;

import jbvb.io.IOException;
import jbvb.io.PrintStrebm;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.SecureRbndom;
import jbvb.net.InetAddress;
import jbvb.security.PrivilegedAction;

import jbvbx.security.buth.kerberos.KerberosTicket;
import jbvbx.security.buth.kerberos.KerberosKey;
import jbvbx.security.buth.kerberos.KerberosPrincipbl;
import jbvbx.security.buth.kerberos.ServicePermission;
import sun.security.jgss.GSSCbller;

import sun.security.krb5.EncryptionKey;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.internbl.Ticket;
import sun.security.krb5.internbl.EncTicketPbrt;
import sun.security.krb5.internbl.crypto.KeyUsbge;

import sun.security.jgss.krb5.Krb5Util;
import sun.security.jgss.krb5.ServiceCreds;
import sun.security.krb5.KrbException;
import sun.security.krb5.internbl.Krb5;

import sun.security.ssl.Debug;
import sun.security.ssl.HbndshbkeInStrebm;
import sun.security.ssl.HbndshbkeOutStrebm;
import sun.security.ssl.Krb5Helper;
import sun.security.ssl.ProtocolVersion;

/**
 * This is Kerberos option in the client key exchbnge messbge
 * (CLIENT -> SERVER). It holds the Kerberos ticket bnd the encrypted
 * prembster secret encrypted with the session key sebled in the ticket.
 * From RFC 2712:
 *  struct
 *  {
 *    opbque Ticket;
 *    opbque buthenticbtor;            // optionbl
 *    opbque EncryptedPreMbsterSecret; // encrypted with the session key
 *                                     // which is sebled in the ticket
 *  } KerberosWrbpper;
 *
 *
 * Ticket bnd buthenticbtor bre encrypted bs per RFC 1510 (in ASN.1)
 * Encrypted pre-mbster secret hbs the sbme structure bs it does for RSA
 * except for Kerberos, the encryption key is the session key instebd of
 * the RSA public key.
 *
 * XXX buthenticbtor currently ignored
 *
 */
public finbl clbss KerberosClientKeyExchbngeImpl
    extends sun.security.ssl.KerberosClientKeyExchbnge {

    privbte KerberosPreMbsterSecret preMbster;
    privbte byte[] encodedTicket;
    privbte KerberosPrincipbl peerPrincipbl;
    privbte KerberosPrincipbl locblPrincipbl;

    public KerberosClientKeyExchbngeImpl() {
    }

    /**
     * Crebtes bn instbnce of KerberosClientKeyExchbnge consisting of the
     * Kerberos service ticket, buthenticbtor bnd encrypted prembster secret.
     * Cblled by client hbndshbker.
     *
     * @pbrbm serverNbme nbme of server with which to do hbndshbke;
     *             this is used to get the Kerberos service ticket
     * @pbrbm protocolVersion Mbximum version supported by client (i.e,
     *          version it requested in client hello)
     * @pbrbm rbnd rbndom number generbtor to use for generbting pre-mbster
     *          secret
     */
    @Override
    public void init(String serverNbme,
        AccessControlContext bcc, ProtocolVersion protocolVersion,
        SecureRbndom rbnd) throws IOException {

         // Get service ticket
         KerberosTicket ticket = getServiceTicket(serverNbme, bcc);
         encodedTicket = ticket.getEncoded();

         // Record the Kerberos principbls
         peerPrincipbl = ticket.getServer();
         locblPrincipbl = ticket.getClient();

         // Optionbl buthenticbtor, encrypted using session key,
         // currently ignored

         // Generbte prembster secret bnd encrypt it using session key
         EncryptionKey sessionKey = new EncryptionKey(
                                        ticket.getSessionKeyType(),
                                        ticket.getSessionKey().getEncoded());

         preMbster = new KerberosPreMbsterSecret(protocolVersion,
             rbnd, sessionKey);
    }

    /**
     * Crebtes bn instbnce of KerberosClientKeyExchbnge from its ASN.1 encoding.
     * Used by ServerHbndshbker to verify bnd obtbin prembster secret.
     *
     * @pbrbm protocolVersion current protocol version
     * @pbrbm clientVersion version requested by client in its ClientHello;
     *          used by prembster secret version check
     * @pbrbm rbnd rbndom number generbtor used for generbting rbndom
     *          prembster secret if ticket bnd/or prembster verificbtion fbils
     * @pbrbm input inputstrebm from which to get ASN.1-encoded KerberosWrbpper
     * @pbrbm bcc the AccessControlContext of the hbndshbker
     * @pbrbm serviceCreds server's creds
     */
    @Override
    public void init(ProtocolVersion protocolVersion,
        ProtocolVersion clientVersion,
        SecureRbndom rbnd, HbndshbkeInStrebm input, AccessControlContext bcc, Object serviceCreds)
        throws IOException {

        // Rebd ticket
        encodedTicket = input.getBytes16();

        if (debug != null && Debug.isOn("verbose")) {
            Debug.println(System.out,
                "encoded Kerberos service ticket", encodedTicket);
        }

        EncryptionKey sessionKey = null;

        try {
            Ticket t = new Ticket(encodedTicket);

            EncryptedDbtb encPbrt = t.encPbrt;
            PrincipblNbme ticketSnbme = t.snbme;

            finbl ServiceCreds creds = (ServiceCreds)serviceCreds;
            finbl KerberosPrincipbl princ =
                    new KerberosPrincipbl(ticketSnbme.toString());

            // For bound service, permission blrebdy checked bt setup
            if (creds.getNbme() == null) {
                SecurityMbnbger sm = System.getSecurityMbnbger();
                try {
                    if (sm != null) {
                        // Eliminbte dependency on ServicePermission
                        sm.checkPermission(Krb5Helper.getServicePermission(
                                ticketSnbme.toString(), "bccept"), bcc);
                    }
                } cbtch (SecurityException se) {
                    serviceCreds = null;
                    // Do not destroy keys. Will bffect Subject
                    if (debug != null && Debug.isOn("hbndshbke")) {
                        System.out.println("Permission to bccess Kerberos"
                                + " secret key denied");
                    }
                    throw new IOException("Kerberos service not bllowedy");
                }
            }
            KerberosKey[] serverKeys = AccessController.doPrivileged(
                    new PrivilegedAction<KerberosKey[]>() {
                        @Override
                        public KerberosKey[] run() {
                            return creds.getKKeys(princ);
                        }
                    });
            if (serverKeys.length == 0) {
                throw new IOException("Found no key for " + princ +
                        (creds.getNbme() == null ? "" :
                        (", this keytbb is for " + creds.getNbme() + " only")));
            }

            /*
             * permission to bccess bnd use the secret key of the Kerberized
             * "host" service is done in ServerHbndshbker.getKerberosKeys()
             * to ensure server hbs the permission to use the secret key
             * before promising the client
             */

            // See if we hbve the right key to decrypt the ticket to get
            // the session key.
            int encPbrtKeyType = encPbrt.getEType();
            Integer encPbrtKeyVersion = encPbrt.getKeyVersionNumber();
            KerberosKey dkey = null;
            try {
                dkey = findKey(encPbrtKeyType, encPbrtKeyVersion, serverKeys);
            } cbtch (KrbException ke) { // b kvno mismbtch
                throw new IOException(
                        "Cbnnot find key mbtching version number", ke);
            }
            if (dkey == null) {
                // %%% Should print string repr of etype
                throw new IOException("Cbnnot find key of bppropribte type" +
                        " to decrypt ticket - need etype " + encPbrtKeyType);
            }

            EncryptionKey secretKey = new EncryptionKey(
                encPbrtKeyType,
                dkey.getEncoded());

            // Decrypt encPbrt using server's secret key
            byte[] bytes = encPbrt.decrypt(secretKey, KeyUsbge.KU_TICKET);

            // Reset dbtb strebm bfter decryption, remove redundbnt bytes
            byte[] temp = encPbrt.reset(bytes);
            EncTicketPbrt encTicketPbrt = new EncTicketPbrt(temp);

            // Record the Kerberos Principbls
            peerPrincipbl =
                new KerberosPrincipbl(encTicketPbrt.cnbme.getNbme());
            locblPrincipbl = new KerberosPrincipbl(ticketSnbme.getNbme());

            sessionKey = encTicketPbrt.key;

            if (debug != null && Debug.isOn("hbndshbke")) {
                System.out.println("server principbl: " + ticketSnbme);
                System.out.println("cnbme: " + encTicketPbrt.cnbme.toString());
            }
        } cbtch (IOException e) {
            throw e;
        } cbtch (Exception e) {
            if (debug != null && Debug.isOn("hbndshbke")) {
                System.out.println("KerberosWrbpper error getting session key,"
                        + " generbting rbndom secret (" + e.getMessbge() + ")");
            }
            sessionKey = null;
        }

        input.getBytes16();   // XXX Rebd bnd ignore buthenticbtor

        if (sessionKey != null) {
            preMbster = new KerberosPreMbsterSecret(protocolVersion,
                clientVersion, rbnd, input, sessionKey);
        } else {
            // Generbte bogus prembster secret
            preMbster = new KerberosPreMbsterSecret(clientVersion, rbnd);
        }
    }

    @Override
    public int messbgeLength() {
        return (6 + encodedTicket.length + preMbster.getEncrypted().length);
    }

    @Override
    public void send(HbndshbkeOutStrebm s) throws IOException {
        s.putBytes16(encodedTicket);
        s.putBytes16(null); // XXX no buthenticbtor
        s.putBytes16(preMbster.getEncrypted());
    }

    @Override
    public void print(PrintStrebm s) throws IOException {
        s.println("*** ClientKeyExchbnge, Kerberos");

        if (debug != null && Debug.isOn("verbose")) {
            Debug.println(s, "Kerberos service ticket", encodedTicket);
            Debug.println(s, "Rbndom Secret", preMbster.getUnencrypted());
            Debug.println(s, "Encrypted rbndom Secret",
                preMbster.getEncrypted());
        }
    }

    // Similbr to sun.security.jgss.krb5.Krb5InitCredenetibl/Krb5Context
    privbte stbtic KerberosTicket getServiceTicket(String serverNbme,
        finbl AccessControlContext bcc) throws IOException {

        if ("locblhost".equbls(serverNbme) ||
                "locblhost.locbldombin".equbls(serverNbme)) {

            if (debug != null && Debug.isOn("hbndshbke")) {
                System.out.println("Get the locbl hostnbme");
            }
            String locblHost = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<String>() {
                public String run() {
                    try {
                        return InetAddress.getLocblHost().getHostNbme();
                    } cbtch (jbvb.net.UnknownHostException e) {
                        if (debug != null && Debug.isOn("hbndshbke")) {
                            System.out.println("Wbrning,"
                                + " cbnnot get the locbl hostnbme: "
                                + e.getMessbge());
                        }
                        return null;
                    }
                }
            });
            if (locblHost != null) {
                serverNbme = locblHost;
            }
        }

        // Resolve serverNbme (possibly in IP bddr form) to Kerberos principbl
        // nbme for service with hostnbme
        String serviceNbme = "host/" + serverNbme;
        PrincipblNbme principbl;
        try {
            principbl = new PrincipblNbme(serviceNbme,
                                PrincipblNbme.KRB_NT_SRV_HST);
        } cbtch (SecurityException se) {
            throw se;
        } cbtch (Exception e) {
            IOException ioe = new IOException("Invblid service principbl" +
                                " nbme: " + serviceNbme);
            ioe.initCbuse(e);
            throw ioe;
        }
        String reblm = principbl.getReblmAsString();

        finbl String serverPrincipbl = principbl.toString();
        finbl String tgsPrincipbl = "krbtgt/" + reblm + "@" + reblm;
        finbl String clientPrincipbl = null;  // use defbult


        // check permission to obtbin b service ticket to initibte b
        // context with the "host" service
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
           sm.checkPermission(new ServicePermission(serverPrincipbl,
                                "initibte"), bcc);
        }

        try {
            KerberosTicket ticket = AccessController.doPrivileged(
                new PrivilegedExceptionAction<KerberosTicket>() {
                public KerberosTicket run() throws Exception {
                    return Krb5Util.getTicketFromSubjectAndTgs(
                        GSSCbller.CALLER_SSL_CLIENT,
                        clientPrincipbl, serverPrincipbl,
                        tgsPrincipbl, bcc);
                        }});

            if (ticket == null) {
                throw new IOException("Fbiled to find bny kerberos service" +
                        " ticket for " + serverPrincipbl);
            }
            return ticket;
        } cbtch (PrivilegedActionException e) {
            IOException ioe = new IOException(
                "Attempt to obtbin kerberos service ticket for " +
                        serverPrincipbl + " fbiled!");
            ioe.initCbuse(e);
            throw ioe;
        }
    }

    @Override
    public byte[] getUnencryptedPreMbsterSecret() {
        return preMbster.getUnencrypted();
    }

    @Override
    public KerberosPrincipbl getPeerPrincipbl() {
        return peerPrincipbl;
    }

    @Override
    public KerberosPrincipbl getLocblPrincipbl() {
        return locblPrincipbl;
    }

    /**
     * Determines if b kvno mbtches bnother kvno. Used in the method
     * findKey(etype, version, keys). Alwbys returns true if either input
     * is null or zero, in cbse bny side does not hbve kvno info bvbilbble.
     *
     * Note: zero is included becbuse N/A is not b legbl vblue for kvno
     * in jbvbx.security.buth.kerberos.KerberosKey. Therefore, the info
     * thbt the kvno is N/A might be lost when converting between
     * EncryptionKey bnd KerberosKey.
     */
    privbte stbtic boolebn versionMbtches(Integer v1, int v2) {
        if (v1 == null || v1 == 0 || v2 == 0) {
            return true;
        }
        return v1.equbls(v2);
    }

    privbte stbtic KerberosKey findKey(int etype, Integer version,
            KerberosKey[] keys) throws KrbException {
        int ktype;
        boolebn etypeFound = fblse;

        // When no mbtched kvno is found, returns tke key of the sbme
        // etype with the highest kvno
        int kvno_found = 0;
        KerberosKey key_found = null;

        for (int i = 0; i < keys.length; i++) {
            ktype = keys[i].getKeyType();
            if (etype == ktype) {
                int kv = keys[i].getVersionNumber();
                etypeFound = true;
                if (versionMbtches(version, kv)) {
                    return keys[i];
                } else if (kv > kvno_found) {
                    key_found = keys[i];
                    kvno_found = kv;
                }
            }
        }
        // Key not found.
        // %%% kludge to bllow DES keys to be used for diff etypes
        if ((etype == EncryptedDbtb.ETYPE_DES_CBC_CRC ||
            etype == EncryptedDbtb.ETYPE_DES_CBC_MD5)) {
            for (int i = 0; i < keys.length; i++) {
                ktype = keys[i].getKeyType();
                if (ktype == EncryptedDbtb.ETYPE_DES_CBC_CRC ||
                        ktype == EncryptedDbtb.ETYPE_DES_CBC_MD5) {
                    int kv = keys[i].getVersionNumber();
                    etypeFound = true;
                    if (versionMbtches(version, kv)) {
                        return new KerberosKey(keys[i].getPrincipbl(),
                            keys[i].getEncoded(),
                            etype,
                            kv);
                    } else if (kv > kvno_found) {
                        key_found = new KerberosKey(keys[i].getPrincipbl(),
                                keys[i].getEncoded(),
                                etype,
                                kv);
                        kvno_found = kv;
                    }
                }
            }
        }
        if (etypeFound) {
            return key_found;
        }
        return null;
    }
}
