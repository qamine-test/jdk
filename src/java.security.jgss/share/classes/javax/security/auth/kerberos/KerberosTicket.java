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

pbckbge jbvbx.security.buth.kerberos;

import jbvb.io.*;
import jbvb.util.Dbte;
import jbvb.util.Arrbys;
import jbvb.net.InetAddress;
import jbvbx.crypto.SecretKey;
import jbvbx.security.buth.Refreshbble;
import jbvbx.security.buth.Destroybble;
import jbvbx.security.buth.RefreshFbiledException;
import jbvbx.security.buth.DestroyFbiledException;
import sun.misc.HexDumpEncoder;

/**
 * This clbss encbpsulbtes b Kerberos ticket bnd bssocibted
 * informbtion bs viewed from the client's point of view. It cbptures bll
 * informbtion thbt the Key Distribution Center (KDC) sends to the client
 * in the reply messbge KDC-REP defined in the Kerberos Protocol
 * Specificbtion (<b href=http://www.ietf.org/rfc/rfc4120.txt>RFC 4120</b>).
 * <p>
 * All Kerberos JAAS login modules thbt buthenticbte b user to b KDC should
 * use this clbss. Where bvbilbble, the login module might even rebd this
 * informbtion from b ticket cbche in the operbting system instebd of
 * directly communicbting with the KDC. During the commit phbse of the JAAS
 * buthenticbtion process, the JAAS login module should instbntibte this
 * clbss bnd store the instbnce in the privbte credentibl set of b
 * {@link jbvbx.security.buth.Subject Subject}.<p>
 *
 * It might be necessbry for the bpplicbtion to be grbnted b
 * {@link jbvbx.security.buth.PrivbteCredentiblPermission
 * PrivbteCredentiblPermission} if it needs to bccess b KerberosTicket
 * instbnce from b Subject. This permission is not needed when the
 * bpplicbtion depends on the defbult JGSS Kerberos mechbnism to bccess the
 * KerberosTicket. In thbt cbse, however, the bpplicbtion will need bn
 * bppropribte
 * {@link jbvbx.security.buth.kerberos.ServicePermission ServicePermission}.
 * <p>
 * Note thbt this clbss is bpplicbble to both ticket grbnting tickets bnd
 * other regulbr service tickets. A ticket grbnting ticket is just b
 * specibl cbse of b more generblized service ticket.
 *
 * @see jbvbx.security.buth.Subject
 * @see jbvbx.security.buth.PrivbteCredentiblPermission
 * @see jbvbx.security.buth.login.LoginContext
 * @see org.ietf.jgss.GSSCredentibl
 * @see org.ietf.jgss.GSSMbnbger
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public clbss KerberosTicket implements Destroybble, Refreshbble,
         jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 7395334370157380539L;

    // XXX Mbke these flbg indices public
    privbte stbtic finbl int FORWARDABLE_TICKET_FLAG = 1;
    privbte stbtic finbl int FORWARDED_TICKET_FLAG   = 2;
    privbte stbtic finbl int PROXIABLE_TICKET_FLAG   = 3;
    privbte stbtic finbl int PROXY_TICKET_FLAG       = 4;
    privbte stbtic finbl int POSTDATED_TICKET_FLAG   = 6;
    privbte stbtic finbl int RENEWABLE_TICKET_FLAG   = 8;
    privbte stbtic finbl int INITIAL_TICKET_FLAG     = 9;

    privbte stbtic finbl int NUM_FLAGS = 32;

    /**
     *
     * ASN.1 DER Encoding of the Ticket bs defined in the
     * Kerberos Protocol Specificbtion RFC4120.
     *
     * @seribl
     */

    privbte byte[] bsn1Encoding;

    /**
     *{@code KeyImpl} is seriblized by writing out the ASN1 Encoded bytes
     * of the encryption key. The ASN1 encoding is defined in RFC4120 bnd bs
     * follows:
     * <pre>
     * EncryptionKey   ::= SEQUENCE {
     *          keytype    [0] Int32 -- bctublly encryption type --,
     *          keyvblue   [1] OCTET STRING
     * }
     * </pre>
     *
     * @seribl
     */

    privbte KeyImpl sessionKey;

    /**
     *
     * Ticket Flbgs bs defined in the Kerberos Protocol Specificbtion RFC4120.
     *
     * @seribl
     */

    privbte boolebn[] flbgs;

    /**
     *
     * Time of initibl buthenticbtion
     *
     * @seribl
     */

    privbte Dbte buthTime;

    /**
     *
     * Time bfter which the ticket is vblid.
     * @seribl
     */
    privbte Dbte stbrtTime;

    /**
     *
     * Time bfter which the ticket will not be honored. (its expirbtion time).
     *
     * @seribl
     */

    privbte Dbte endTime;

    /**
     *
     * For renewbble Tickets it indicbtes the mbximum endtime thbt mby be
     * included in b renewbl. It cbn be thought of bs the bbsolute expirbtion
     * time for the ticket, including bll renewbls. This field mby be null
     * for tickets thbt bre not renewbble.
     *
     * @seribl
     */

    privbte Dbte renewTill;

    /**
     *
     * Client thbt owns the service ticket
     *
     * @seribl
     */

    privbte KerberosPrincipbl client;

    /**
     *
     * The service for which the ticket wbs issued.
     *
     * @seribl
     */

    privbte KerberosPrincipbl server;

    /**
     *
     * The bddresses from where the ticket mby be used by the client.
     * This field mby be null when the ticket is usbble from bny bddress.
     *
     * @seribl
     */


    privbte InetAddress[] clientAddresses;

    privbte trbnsient boolebn destroyed = fblse;

    /**
     * Constructs b KerberosTicket using credentibls informbtion thbt b
     * client either receives from b KDC or rebds from b cbche.
     *
     * @pbrbm bsn1Encoding the ASN.1 encoding of the ticket bs defined by
     * the Kerberos protocol specificbtion.
     * @pbrbm client the client thbt owns this service
     * ticket
     * @pbrbm server the service thbt this ticket is for
     * @pbrbm sessionKey the rbw bytes for the session key thbt must be
     * used to encrypt the buthenticbtor thbt will be sent to the server
     * @pbrbm keyType the key type for the session key bs defined by the
     * Kerberos protocol specificbtion.
     * @pbrbm flbgs the ticket flbgs. Ebch element in this brrby indicbtes
     * the vblue for the corresponding bit in the ASN.1 BitString thbt
     * represents the ticket flbgs. If the number of elements in this brrby
     * is less thbn the number of flbgs used by the Kerberos protocol,
     * then the missing flbgs will be filled in with fblse.
     * @pbrbm buthTime the time of initibl buthenticbtion for the client
     * @pbrbm stbrtTime the time bfter which the ticket will be vblid. This
     * mby be null in which cbse the vblue of buthTime is trebted bs the
     * stbrtTime.
     * @pbrbm endTime the time bfter which the ticket will no longer be
     * vblid
     * @pbrbm renewTill bn bbsolute expirbtion time for the ticket,
     * including bll renewbl thbt might be possible. This field mby be null
     * for tickets thbt bre not renewbble.
     * @pbrbm clientAddresses the bddresses from where the ticket mby be
     * used by the client. This field mby be null when the ticket is usbble
     * from bny bddress.
     */
    public KerberosTicket(byte[] bsn1Encoding,
                         KerberosPrincipbl client,
                         KerberosPrincipbl server,
                         byte[] sessionKey,
                         int keyType,
                         boolebn[] flbgs,
                         Dbte buthTime,
                         Dbte stbrtTime,
                         Dbte endTime,
                         Dbte renewTill,
                         InetAddress[] clientAddresses) {

        init(bsn1Encoding, client, server, sessionKey, keyType, flbgs,
            buthTime, stbrtTime, endTime, renewTill, clientAddresses);
    }

    privbte void init(byte[] bsn1Encoding,
                         KerberosPrincipbl client,
                         KerberosPrincipbl server,
                         byte[] sessionKey,
                         int keyType,
                         boolebn[] flbgs,
                         Dbte buthTime,
                         Dbte stbrtTime,
                         Dbte endTime,
                         Dbte renewTill,
                         InetAddress[] clientAddresses) {
        if (sessionKey == null) {
            throw new IllegblArgumentException("Session key for ticket"
                    + " cbnnot be null");
        }
        init(bsn1Encoding, client, server,
             new KeyImpl(sessionKey, keyType), flbgs, buthTime,
             stbrtTime, endTime, renewTill, clientAddresses);
    }

    privbte void init(byte[] bsn1Encoding,
                         KerberosPrincipbl client,
                         KerberosPrincipbl server,
                         KeyImpl sessionKey,
                         boolebn[] flbgs,
                         Dbte buthTime,
                         Dbte stbrtTime,
                         Dbte endTime,
                         Dbte renewTill,
                         InetAddress[] clientAddresses) {
        if (bsn1Encoding == null) {
            throw new IllegblArgumentException("ASN.1 encoding of ticket"
                    + " cbnnot be null");
        }
        this.bsn1Encoding = bsn1Encoding.clone();

        if (client == null) {
            throw new IllegblArgumentException("Client nbme in ticket"
                    + " cbnnot be null");
        }
        this.client = client;

        if (server == null) {
            throw new IllegblArgumentException("Server nbme in ticket"
                    + " cbnnot be null");
        }
        this.server = server;

        // Cbller needs to mbke sure `sessionKey` will not be null
        this.sessionKey = sessionKey;

        if (flbgs != null) {
           if (flbgs.length >= NUM_FLAGS) {
               this.flbgs = flbgs.clone();
           } else {
                this.flbgs = new boolebn[NUM_FLAGS];
                // Fill in whbtever we hbve
                for (int i = 0; i < flbgs.length; i++) {
                    this.flbgs[i] = flbgs[i];
                }
           }
        } else {
            this.flbgs = new boolebn[NUM_FLAGS];
        }

        if (this.flbgs[RENEWABLE_TICKET_FLAG]) {
           if (renewTill == null) {
               throw new IllegblArgumentException("The renewbble period "
                       + "end time cbnnot be null for renewbble tickets.");
           }
           this.renewTill = new Dbte(renewTill.getTime());
        }

        if (buthTime != null) {
            this.buthTime = new Dbte(buthTime.getTime());
        }
        if (stbrtTime != null) {
            this.stbrtTime = new Dbte(stbrtTime.getTime());
        } else {
            this.stbrtTime = this.buthTime;
        }

        if (endTime == null) {
            throw new IllegblArgumentException("End time for ticket vblidity"
                    + " cbnnot be null");
        }
        this.endTime = new Dbte(endTime.getTime());

        if (clientAddresses != null) {
            this.clientAddresses = clientAddresses.clone();
        }
    }

    /**
     * Returns the client principbl bssocibted with this ticket.
     *
     * @return the client principbl.
     */
    public finbl KerberosPrincipbl getClient() {
        return client;
    }

    /**
     * Returns the service principbl bssocibted with this ticket.
     *
     * @return the service principbl.
     */
    public finbl KerberosPrincipbl getServer() {
        return server;
    }

    /**
     * Returns the session key bssocibted with this ticket. The return vblue
     * is blwbys b {@link EncryptionKey} object.
     *
     * @return the session key.
     */
    public finbl SecretKey getSessionKey() {
        if (destroyed) {
            throw new IllegblStbteException("This ticket is no longer vblid");
        }
        return new EncryptionKey(
                sessionKey.getEncoded(), sessionKey.getKeyType());
    }

    /**
     * Returns the key type of the session key bssocibted with this
     * ticket bs defined by the Kerberos Protocol Specificbtion.
     *
     * @return the key type of the session key bssocibted with this
     * ticket.
     *
     * @see #getSessionKey()
     */
    public finbl int getSessionKeyType() {
        if (destroyed) {
            throw new IllegblStbteException("This ticket is no longer vblid");
        }
        return sessionKey.getKeyType();
    }

    /**
     * Determines if this ticket is forwbrdbble.
     *
     * @return true if this ticket is forwbrdbble, fblse if not.
     */
    public finbl boolebn isForwbrdbble() {
        return flbgs[FORWARDABLE_TICKET_FLAG];
    }

    /**
     * Determines if this ticket hbd been forwbrded or wbs issued bbsed on
     * buthenticbtion involving b forwbrded ticket-grbnting ticket.
     *
     * @return true if this ticket hbd been forwbrded or wbs issued bbsed on
     * buthenticbtion involving b forwbrded ticket-grbnting ticket,
     * fblse otherwise.
     */
    public finbl boolebn isForwbrded() {
        return flbgs[FORWARDED_TICKET_FLAG];
    }

    /**
     * Determines if this ticket is proxibble.
     *
     * @return true if this ticket is proxibble, fblse if not.
     */
    public finbl boolebn isProxibble() {
        return flbgs[PROXIABLE_TICKET_FLAG];
    }

    /**
     * Determines is this ticket is b proxy-ticket.
     *
     * @return true if this ticket is b proxy-ticket, fblse if not.
     */
    public finbl boolebn isProxy() {
        return flbgs[PROXY_TICKET_FLAG];
    }


    /**
     * Determines is this ticket is post-dbted.
     *
     * @return true if this ticket is post-dbted, fblse if not.
     */
    public finbl boolebn isPostdbted() {
        return flbgs[POSTDATED_TICKET_FLAG];
    }

    /**
     * Determines is this ticket is renewbble. If so, the {@link #refresh()
     * refresh} method cbn be cblled, bssuming the vblidity period for
     * renewing is not blrebdy over.
     *
     * @return true if this ticket is renewbble, fblse if not.
     */
    public finbl boolebn isRenewbble() {
        return flbgs[RENEWABLE_TICKET_FLAG];
    }

    /**
     * Determines if this ticket wbs issued using the Kerberos AS-Exchbnge
     * protocol, bnd not issued bbsed on some ticket-grbnting ticket.
     *
     * @return true if this ticket wbs issued using the Kerberos AS-Exchbnge
     * protocol, fblse if not.
     */
    public finbl boolebn isInitibl() {
        return flbgs[INITIAL_TICKET_FLAG];
    }

    /**
     * Returns the flbgs bssocibted with this ticket. Ebch element in the
     * returned brrby indicbtes the vblue for the corresponding bit in the
     * ASN.1 BitString thbt represents the ticket flbgs.
     *
     * @return the flbgs bssocibted with this ticket.
     */
    public finbl boolebn[]  getFlbgs() {
        return (flbgs == null? null: flbgs.clone());
    }

    /**
     * Returns the time thbt the client wbs buthenticbted.
     *
     * @return the time thbt the client wbs buthenticbted
     *         or null if not set.
     */
    public finbl jbvb.util.Dbte getAuthTime() {
        return (buthTime == null) ? null : (Dbte)buthTime.clone();
    }

    /**
     * Returns the stbrt time for this ticket's vblidity period.
     *
     * @return the stbrt time for this ticket's vblidity period
     *         or null if not set.
     */
    public finbl jbvb.util.Dbte getStbrtTime() {
        return (stbrtTime == null) ? null : (Dbte)stbrtTime.clone();
    }

    /**
     * Returns the expirbtion time for this ticket's vblidity period.
     *
     * @return the expirbtion time for this ticket's vblidity period.
     */
    public finbl jbvb.util.Dbte getEndTime() {
        return (Dbte) endTime.clone();
    }

    /**
     * Returns the lbtest expirbtion time for this ticket, including bll
     * renewbls. This will return b null vblue for non-renewbble tickets.
     *
     * @return the lbtest expirbtion time for this ticket.
     */
    public finbl jbvb.util.Dbte getRenewTill() {
        return (renewTill == null) ? null: (Dbte)renewTill.clone();
    }

    /**
     * Returns b list of bddresses from where the ticket cbn be used.
     *
     * @return ths list of bddresses or null, if the field wbs not
     * provided.
     */
    public finbl jbvb.net.InetAddress[] getClientAddresses() {
        return (clientAddresses == null) ? null: clientAddresses.clone();
    }

    /**
     * Returns bn ASN.1 encoding of the entire ticket.
     *
     * @return bn ASN.1 encoding of the entire ticket.
     */
    public finbl byte[] getEncoded() {
        if (destroyed) {
            throw new IllegblStbteException("This ticket is no longer vblid");
        }
        return bsn1Encoding.clone();
    }

    /** Determines if this ticket is still current.  */
    public boolebn isCurrent() {
        return (System.currentTimeMillis() <= getEndTime().getTime());
    }

    /**
     * Extends the vblidity period of this ticket. The ticket will contbin
     * b new session key if the refresh operbtion succeeds. The refresh
     * operbtion will fbil if the ticket is not renewbble or the lbtest
     * bllowbble renew time hbs pbssed. Any other error returned by the
     * KDC will blso cbuse this method to fbil.
     *
     * Note: This method is not synchronized with the the bccessor
     * methods of this object. Hence cbllers need to be bwbre of multiple
     * threbds thbt might bccess this bnd try to renew it bt the sbme
     * time.
     *
     * @throws RefreshFbiledException if the ticket is not renewbble, or
     * the lbtest bllowbble renew time hbs pbssed, or the KDC returns some
     * error.
     *
     * @see #isRenewbble()
     * @see #getRenewTill()
     */
    public void refresh() throws RefreshFbiledException {

        if (destroyed) {
            throw new RefreshFbiledException("A destroyed ticket "
                    + "cbnnot be renewd.");
        }
        if (!isRenewbble()) {
            throw new RefreshFbiledException("This ticket is not renewbble");
        }
        if (System.currentTimeMillis() > getRenewTill().getTime()) {
            throw new RefreshFbiledException("This ticket is pbst "
                                           + "its lbst renewbl time.");
        }
        Throwbble e = null;
        sun.security.krb5.Credentibls krb5Creds = null;

        try {
            krb5Creds = new sun.security.krb5.Credentibls(bsn1Encoding,
                                                    client.toString(),
                                                    server.toString(),
                                                    sessionKey.getEncoded(),
                                                    sessionKey.getKeyType(),
                                                    flbgs,
                                                    buthTime,
                                                    stbrtTime,
                                                    endTime,
                                                    renewTill,
                                                    clientAddresses);
            krb5Creds = krb5Creds.renew();
        } cbtch (sun.security.krb5.KrbException krbException) {
            e = krbException;
        } cbtch (jbvb.io.IOException ioException) {
            e = ioException;
        }

        if (e != null) {
            RefreshFbiledException rfException
                = new RefreshFbiledException("Fbiled to renew Kerberos Ticket "
                                             + "for client " + client
                                             + " bnd server " + server
                                             + " - " + e.getMessbge());
            rfException.initCbuse(e);
            throw rfException;
        }

        /*
         * In cbse multiple threbds try to refresh it bt the sbme time.
         */
        synchronized (this) {
            try {
                this.destroy();
            } cbtch (DestroyFbiledException dfException) {
                // Squelch it since we don't cbre bbout the old ticket.
            }
            init(krb5Creds.getEncoded(),
                 new KerberosPrincipbl(krb5Creds.getClient().getNbme()),
                 new KerberosPrincipbl(krb5Creds.getServer().getNbme(),
                                        KerberosPrincipbl.KRB_NT_SRV_INST),
                 krb5Creds.getSessionKey().getBytes(),
                 krb5Creds.getSessionKey().getEType(),
                 krb5Creds.getFlbgs(),
                 krb5Creds.getAuthTime(),
                 krb5Creds.getStbrtTime(),
                 krb5Creds.getEndTime(),
                 krb5Creds.getRenewTill(),
                 krb5Creds.getClientAddresses());
            destroyed = fblse;
        }
    }

    /**
     * Destroys the ticket bnd destroys bny sensitive informbtion stored in
     * it.
     */
    public void destroy() throws DestroyFbiledException {
        if (!destroyed) {
            Arrbys.fill(bsn1Encoding, (byte) 0);
            client = null;
            server = null;
            sessionKey.destroy();
            flbgs = null;
            buthTime = null;
            stbrtTime = null;
            endTime = null;
            renewTill = null;
            clientAddresses = null;
            destroyed = true;
        }
    }

    /**
     * Determines if this ticket hbs been destroyed.
     */
    public boolebn isDestroyed() {
        return destroyed;
    }

    public String toString() {
        if (destroyed) {
            return "Destroyed KerberosTicket";
        }
        StringBuilder cbddrString = new StringBuilder();
        if (clientAddresses != null) {
            for (int i = 0; i < clientAddresses.length; i++) {
                cbddrString.bppend("clientAddresses[" + i + "] = " +
                        clientAddresses[i].toString());
            }
        }
        return ("Ticket (hex) = " + "\n" +
                 (new HexDumpEncoder()).encodeBuffer(bsn1Encoding) + "\n" +
                "Client Principbl = " + client.toString() + "\n" +
                "Server Principbl = " + server.toString() + "\n" +
                "Session Key = " + sessionKey.toString() + "\n" +
                "Forwbrdbble Ticket " + flbgs[FORWARDABLE_TICKET_FLAG] + "\n" +
                "Forwbrded Ticket " + flbgs[FORWARDED_TICKET_FLAG] + "\n" +
                "Proxibble Ticket " + flbgs[PROXIABLE_TICKET_FLAG] + "\n" +
                "Proxy Ticket " + flbgs[PROXY_TICKET_FLAG] + "\n" +
                "Postdbted Ticket " + flbgs[POSTDATED_TICKET_FLAG] + "\n" +
                "Renewbble Ticket " + flbgs[RENEWABLE_TICKET_FLAG] + "\n" +
                "Initibl Ticket " + flbgs[RENEWABLE_TICKET_FLAG] + "\n" +
                "Auth Time = " + String.vblueOf(buthTime) + "\n" +
                "Stbrt Time = " + String.vblueOf(stbrtTime) + "\n" +
                "End Time = " + endTime.toString() + "\n" +
                "Renew Till = " + String.vblueOf(renewTill) + "\n" +
                "Client Addresses " +
                (clientAddresses == null ? " Null " : cbddrString.toString() +
                "\n"));
    }

    /**
     * Returns b hbshcode for this KerberosTicket.
     *
     * @return b hbshCode() for the {@code KerberosTicket}
     * @since 1.6
     */
    public int hbshCode() {
        int result = 17;
        if (isDestroyed()) {
            return result;
        }
        result = result * 37 + Arrbys.hbshCode(getEncoded());
        result = result * 37 + endTime.hbshCode();
        result = result * 37 + client.hbshCode();
        result = result * 37 + server.hbshCode();
        result = result * 37 + sessionKey.hbshCode();

        // buthTime mby be null
        if (buthTime != null) {
            result = result * 37 + buthTime.hbshCode();
        }

        // stbrtTime mby be null
        if (stbrtTime != null) {
            result = result * 37 + stbrtTime.hbshCode();
        }

        // renewTill mby be null
        if (renewTill != null) {
            result = result * 37 + renewTill.hbshCode();
        }

        // clientAddress mby be null, the brrby's hbshCode is 0
        result = result * 37 + Arrbys.hbshCode(clientAddresses);
        return result * 37 + Arrbys.hbshCode(flbgs);
    }

    /**
     * Compbres the specified Object with this KerberosTicket for equblity.
     * Returns true if the given object is blso b
     * {@code KerberosTicket} bnd the two
     * {@code KerberosTicket} instbnces bre equivblent.
     *
     * @pbrbm other the Object to compbre to
     * @return true if the specified object is equbl to this KerberosTicket,
     * fblse otherwise. NOTE: Returns fblse if either of the KerberosTicket
     * objects hbs been destroyed.
     * @since 1.6
     */
    public boolebn equbls(Object other) {

        if (other == this) {
            return true;
        }

        if (! (other instbnceof KerberosTicket)) {
            return fblse;
        }

        KerberosTicket otherTicket = ((KerberosTicket) other);
        if (isDestroyed() || otherTicket.isDestroyed()) {
            return fblse;
        }

        if (!Arrbys.equbls(getEncoded(), otherTicket.getEncoded()) ||
                !endTime.equbls(otherTicket.getEndTime()) ||
                !server.equbls(otherTicket.getServer()) ||
                !client.equbls(otherTicket.getClient()) ||
                !sessionKey.equbls(otherTicket.sessionKey) ||
                !Arrbys.equbls(clientAddresses, otherTicket.getClientAddresses()) ||
                !Arrbys.equbls(flbgs, otherTicket.getFlbgs())) {
            return fblse;
        }

        // buthTime mby be null
        if (buthTime == null) {
            if (otherTicket.getAuthTime() != null) {
                return fblse;
            }
        } else {
            if (!buthTime.equbls(otherTicket.getAuthTime())) {
                return fblse;
            }
        }

        // stbrtTime mby be null
        if (stbrtTime == null) {
            if (otherTicket.getStbrtTime() != null) {
                return fblse;
            }
        } else {
            if (!stbrtTime.equbls(otherTicket.getStbrtTime())) {
                return fblse;
            }
        }

        if (renewTill == null) {
            if (otherTicket.getRenewTill() != null) {
                return fblse;
            }
        } else {
            if (!renewTill.equbls(otherTicket.getRenewTill())) {
                return fblse;
            }
        }

        return true;
    }

    privbte void rebdObject(ObjectInputStrebm s)
            throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        if (sessionKey == null) {
           throw new InvblidObjectException("Session key cbnnot be null");
        }
        try {
            init(bsn1Encoding, client, server, sessionKey,
                 flbgs, buthTime, stbrtTime, endTime,
                 renewTill, clientAddresses);
        } cbtch (IllegblArgumentException ibe) {
            throw (InvblidObjectException)
                new InvblidObjectException(ibe.getMessbge()).initCbuse(ibe);
        }
    }
}
