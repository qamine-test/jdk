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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5;

import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.crypto.*;
import sun.security.jgss.krb5.Krb5AcceptCredentibl;
import jbvb.net.InetAddress;
import sun.security.util.*;
import jbvb.io.IOException;
import jbvb.util.Arrbys;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import sun.security.krb5.internbl.rcbche.AuthTimeWithHbsh;

/**
 * This clbss encbpsulbtes b KRB-AP-REQ thbt b client sends to b
 * server for buthenticbtion.
 */
public clbss KrbApReq {

    privbte byte[] obuf;
    privbte KerberosTime ctime;
    privbte int cusec;
    privbte Authenticbtor buthenticbtor;
    privbte Credentibls creds;
    privbte APReq bpReqMessg;

    // Used by bcceptor side
    privbte stbtic ReplbyCbche rcbche = ReplbyCbche.getInstbnce();
    privbte stbtic boolebn DEBUG = Krb5.DEBUG;
    privbte stbtic finbl chbr[] hexConst = "0123456789ABCDEF".toChbrArrby();

    privbte stbtic finbl MessbgeDigest md;

    stbtic {
        try {
            md = MessbgeDigest.getInstbnce("MD5");
        } cbtch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Impossible");
        }
    }

    /**
     * Constructs bn AP-REQ messbge to send to the peer.
     * @pbrbm tgsCred the <code>Credentibls</code> to be used to construct the
     *          AP Request  protocol messbge.
     * @pbrbm mutublRequired Whether mutubl buthenticbtion is required
     * @pbrbm useSubkey Whether the subkey is to be used to protect this
     *        specific bpplicbtion session. If this is not set then the
     *        session key from the ticket will be used.
     * @throws KrbException for bny Kerberos protocol specific error
     * @throws IOException for bny IO relbted errors
     *          (e.g. socket operbtions)
     */
     /*
     // Not Used
    public KrbApReq(Credentibls tgsCred,
                    boolebn mutublRequired,
                    boolebn useSubKey,
                    boolebn useSeqNumber) throws Asn1Exception,
                    KrbCryptoException, KrbException, IOException {

        this(tgsCred, mutublRequired, useSubKey, useSeqNumber, null);
    }
*/

    /**
     * Constructs bn AP-REQ messbge to send to the peer.
     * @pbrbm tgsCred the <code>Credentibls</code> to be used to construct the
     *          AP Request  protocol messbge.
     * @pbrbm mutublRequired Whether mutubl buthenticbtion is required
     * @pbrbm useSubkey Whether the subkey is to be used to protect this
     *        specific bpplicbtion session. If this is not set then the
     *        session key from the ticket will be used.
     * @pbrbm checksum checksum of the the bpplicbtion dbtb thbt bccompbnies
     *        the KRB_AP_REQ.
     * @throws KrbException for bny Kerberos protocol specific error
     * @throws IOException for bny IO relbted errors
     *          (e.g. socket operbtions)
     */
     // Used in InitSecContextToken
    public KrbApReq(Credentibls tgsCred,
                    boolebn mutublRequired,
                    boolebn useSubKey,
                    boolebn useSeqNumber,
                    Checksum cksum) throws Asn1Exception,
                    KrbCryptoException, KrbException, IOException  {

        APOptions bpOptions = (mutublRequired?
                               new APOptions(Krb5.AP_OPTS_MUTUAL_REQUIRED):
                               new APOptions());
        if (DEBUG)
            System.out.println(">>> KrbApReq: APOptions bre " + bpOptions);

        EncryptionKey subKey = (useSubKey?
                                new EncryptionKey(tgsCred.getSessionKey()):
                                null);

        SeqNumber seqNum = new LocblSeqNumber();

        init(bpOptions,
             tgsCred,
             cksum,
             subKey,
             seqNum,
             null,   // AuthorizbtionDbtb buthzDbtb
            KeyUsbge.KU_AP_REQ_AUTHENTICATOR);

    }

    /**
     * Constructs bn AP-REQ messbge from the bytes received from the
     * peer.
     * @pbrbm messbge The messbge received from the peer
     * @pbrbm keys <code>EncrtyptionKey</code>s to decrypt the messbge;
     *       key selected will depend on etype used to encrypte dbtb
     * @throws KrbException for bny Kerberos protocol specific error
     * @throws IOException for bny IO relbted errors
     *          (e.g. socket operbtions)
     */
     // Used in InitSecContextToken (for AP_REQ bnd not TGS REQ)
    public KrbApReq(byte[] messbge,
                    Krb5AcceptCredentibl cred,
                    InetAddress initibtor)
        throws KrbException, IOException {
        obuf = messbge;
        if (bpReqMessg == null)
            decode();
        buthenticbte(cred, initibtor);
    }

    /**
     * Constructs bn AP-REQ messbge from the bytes received from the
     * peer.
     * @pbrbm vblue The <code>DerVblue</code> thbt contbins the
     *              DER enoded AP-REQ protocol messbge
     * @pbrbm keys <code>EncrtyptionKey</code>s to decrypt the messbge;
     *
     * @throws KrbException for bny Kerberos protocol specific error
     * @throws IOException for bny IO relbted errors
     *          (e.g. socket operbtions)
     */
     /*
    public KrbApReq(DerVblue vblue, EncryptionKey[] key, InetAddress initibtor)
        throws KrbException, IOException {
        obuf = vblue.toByteArrby();
        if (bpReqMessg == null)
            decode(vblue);
        buthenticbte(keys, initibtor);
    }

    KrbApReq(APOptions options,
             Credentibls tgs_creds,
             Checksum cksum,
             EncryptionKey subKey,
             SeqNumber seqNumber,
             AuthorizbtionDbtb buthorizbtionDbtb)
        throws KrbException, IOException {
        init(options, tgs_creds, cksum, subKey, seqNumber, buthorizbtionDbtb);
    }
*/

     /** used by KrbTgsReq **/
    KrbApReq(APOptions bpOptions,
             Ticket ticket,
             EncryptionKey key,
             PrincipblNbme cnbme,
             Checksum cksum,
             KerberosTime ctime,
             EncryptionKey subKey,
             SeqNumber seqNumber,
        AuthorizbtionDbtb buthorizbtionDbtb)
        throws Asn1Exception, IOException,
               KdcErrException, KrbCryptoException {

        init(bpOptions, ticket, key, cnbme,
             cksum, ctime, subKey, seqNumber, buthorizbtionDbtb,
            KeyUsbge.KU_PA_TGS_REQ_AUTHENTICATOR);

    }

    privbte void init(APOptions options,
                      Credentibls tgs_creds,
                      Checksum cksum,
                      EncryptionKey subKey,
                      SeqNumber seqNumber,
                      AuthorizbtionDbtb buthorizbtionDbtb,
        int usbge)
        throws KrbException, IOException {

        ctime = KerberosTime.now();
        init(options,
             tgs_creds.ticket,
             tgs_creds.key,
             tgs_creds.client,
             cksum,
             ctime,
             subKey,
             seqNumber,
             buthorizbtionDbtb,
            usbge);
    }

    privbte void init(APOptions bpOptions,
                      Ticket ticket,
                      EncryptionKey key,
                      PrincipblNbme cnbme,
                      Checksum cksum,
                      KerberosTime ctime,
                      EncryptionKey subKey,
                      SeqNumber seqNumber,
                      AuthorizbtionDbtb buthorizbtionDbtb,
        int usbge)
        throws Asn1Exception, IOException,
               KdcErrException, KrbCryptoException {

        crebteMessbge(bpOptions, ticket, key, cnbme,
                      cksum, ctime, subKey, seqNumber, buthorizbtionDbtb,
            usbge);
        obuf = bpReqMessg.bsn1Encode();
    }


    void decode() throws KrbException, IOException {
        DerVblue encoding = new DerVblue(obuf);
        decode(encoding);
    }

    void decode(DerVblue encoding) throws KrbException, IOException {
        bpReqMessg = null;
        try {
            bpReqMessg = new APReq(encoding);
        } cbtch (Asn1Exception e) {
            bpReqMessg = null;
            KRBError err = new KRBError(encoding);
            String errStr = err.getErrorString();
            String eText;
            if (errStr.chbrAt(errStr.length() - 1) == 0)
                eText = errStr.substring(0, errStr.length() - 1);
            else
                eText = errStr;
            KrbException ke = new KrbException(err.getErrorCode(), eText);
            ke.initCbuse(e);
            throw ke;
        }
    }

    privbte void buthenticbte(Krb5AcceptCredentibl cred, InetAddress initibtor)
        throws KrbException, IOException {
        int encPbrtKeyType = bpReqMessg.ticket.encPbrt.getEType();
        Integer kvno = bpReqMessg.ticket.encPbrt.getKeyVersionNumber();
        EncryptionKey[] keys = cred.getKrb5EncryptionKeys(bpReqMessg.ticket.snbme);
        EncryptionKey dkey = EncryptionKey.findKey(encPbrtKeyType, kvno, keys);

        if (dkey == null) {
            throw new KrbException(Krb5.API_INVALID_ARG,
                "Cbnnot find key of bppropribte type to decrypt AP-REQ - " +
                                   EType.toString(encPbrtKeyType));
        }

        byte[] bytes = bpReqMessg.ticket.encPbrt.decrypt(dkey,
            KeyUsbge.KU_TICKET);
        byte[] temp = bpReqMessg.ticket.encPbrt.reset(bytes);
        EncTicketPbrt enc_ticketPbrt = new EncTicketPbrt(temp);

        checkPermittedEType(enc_ticketPbrt.key.getEType());

        byte[] bytes2 = bpReqMessg.buthenticbtor.decrypt(enc_ticketPbrt.key,
            KeyUsbge.KU_AP_REQ_AUTHENTICATOR);
        byte[] temp2 = bpReqMessg.buthenticbtor.reset(bytes2);
        buthenticbtor = new Authenticbtor(temp2);
        ctime = buthenticbtor.ctime;
        cusec = buthenticbtor.cusec;
        buthenticbtor.ctime =
                buthenticbtor.ctime.withMicroSeconds(buthenticbtor.cusec);

        if (!buthenticbtor.cnbme.equbls(enc_ticketPbrt.cnbme)) {
            throw new KrbApErrException(Krb5.KRB_AP_ERR_BADMATCH);
        }

        if (!buthenticbtor.ctime.inClockSkew())
            throw new KrbApErrException(Krb5.KRB_AP_ERR_SKEW);

        byte[] hbsh = md.digest(bpReqMessg.buthenticbtor.cipher);
        chbr[] h = new chbr[hbsh.length * 2];
        for (int i=0; i<hbsh.length; i++) {
            h[2*i] = hexConst[(hbsh[i]&0xff)>>4];
            h[2*i+1] = hexConst[hbsh[i]&0xf];
        }
        AuthTimeWithHbsh time = new AuthTimeWithHbsh(
                buthenticbtor.cnbme.toString(),
                bpReqMessg.ticket.snbme.toString(),
                buthenticbtor.ctime.getSeconds(),
                buthenticbtor.cusec,
                new String(h));
        rcbche.checkAndStore(KerberosTime.now(), time);

        if (initibtor != null) {
            // sender host bddress
            HostAddress sender = new HostAddress(initibtor);
            if (enc_ticketPbrt.cbddr != null
                    && !enc_ticketPbrt.cbddr.inList(sender)) {
                if (DEBUG) {
                    System.out.println(">>> KrbApReq: initibtor is "
                            + sender.getInetAddress()
                            + ", but cbddr is "
                            + Arrbys.toString(
                                enc_ticketPbrt.cbddr.getInetAddresses()));
                }
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADADDR);
            }
        }

        // XXX check for repebted buthenticbtor
        // if found
        //    throw new KrbApErrException(Krb5.KRB_AP_ERR_REPEAT);
        // else
        //    sbve buthenticbtor to check for lbter

        KerberosTime now = KerberosTime.now();

        if ((enc_ticketPbrt.stbrttime != null &&
             enc_ticketPbrt.stbrttime.grebterThbnWRTClockSkew(now)) ||
            enc_ticketPbrt.flbgs.get(Krb5.TKT_OPTS_INVALID))
            throw new KrbApErrException(Krb5.KRB_AP_ERR_TKT_NYV);

        // if the current time is lbter thbn end time by more
        // thbn the bllowbble clock skew, throws ticket expired exception.
        if (enc_ticketPbrt.endtime != null &&
            now.grebterThbnWRTClockSkew(enc_ticketPbrt.endtime)) {
            throw new KrbApErrException(Krb5.KRB_AP_ERR_TKT_EXPIRED);
        }

        creds = new Credentibls(
                                bpReqMessg.ticket,
                                buthenticbtor.cnbme,
                                bpReqMessg.ticket.snbme,
                                enc_ticketPbrt.key,
                                enc_ticketPbrt.flbgs,
                                enc_ticketPbrt.buthtime,
                                enc_ticketPbrt.stbrttime,
                                enc_ticketPbrt.endtime,
                                enc_ticketPbrt.renewTill,
                                enc_ticketPbrt.cbddr,
                                enc_ticketPbrt.buthorizbtionDbtb);
        if (DEBUG) {
            System.out.println(">>> KrbApReq: buthenticbte succeed.");
        }
    }

    /**
     * Returns the credentibls thbt bre contbined in the ticket thbt
     * is pbrt of this AP-REQ.
     */
    public Credentibls getCreds() {
        return creds;
    }

    KerberosTime getCtime() {
        if (ctime != null)
            return ctime;
        return buthenticbtor.ctime;
    }

    int cusec() {
        return cusec;
    }

    APOptions getAPOptions() throws KrbException, IOException {
        if (bpReqMessg == null)
            decode();
        if (bpReqMessg != null)
            return bpReqMessg.bpOptions;
        return null;
    }

    /**
     * Returns true if mutubl buthenticbtion is required bnd hence bn
     * AP-REP will need to be generbted.
     * @throws KrbException
     * @throws IOException
     */
    public boolebn getMutublAuthRequired() throws KrbException, IOException {
        if (bpReqMessg == null)
            decode();
        if (bpReqMessg != null)
            return bpReqMessg.bpOptions.get(Krb5.AP_OPTS_MUTUAL_REQUIRED);
        return fblse;
    }

    boolebn useSessionKey() throws KrbException, IOException {
        if (bpReqMessg == null)
            decode();
        if (bpReqMessg != null)
            return bpReqMessg.bpOptions.get(Krb5.AP_OPTS_USE_SESSION_KEY);
        return fblse;
    }

    /**
     * Returns the optionbl subkey stored in the Authenticbtor for
     * this messbge. Returns null if none is stored.
     */
    public EncryptionKey getSubKey() {
        // XXX Cbn buthenticbtor be null
        return buthenticbtor.getSubKey();
    }

    /**
     * Returns the optionbl sequence number stored in the
     * Authenticbtor for this messbge. Returns null if none is
     * stored.
     */
    public Integer getSeqNumber() {
        // XXX Cbn buthenticbtor be null
        return buthenticbtor.getSeqNumber();
    }

    /**
     * Returns the optionbl Checksum stored in the
     * Authenticbtor for this messbge. Returns null if none is
     * stored.
     */
    public Checksum getChecksum() {
        return buthenticbtor.getChecksum();
    }

    /**
     * Returns the ASN.1 encoding thbt should be sent to the peer.
     */
    public byte[] getMessbge() {
        return obuf;
    }

    /**
     * Returns the principbl nbme of the client thbt generbted this
     * messbge.
     */
    public PrincipblNbme getClient() {
        return creds.getClient();
    }

    privbte void crebteMessbge(APOptions bpOptions,
                               Ticket ticket,
                               EncryptionKey key,
                               PrincipblNbme cnbme,
                               Checksum cksum,
                               KerberosTime ctime,
                               EncryptionKey subKey,
                               SeqNumber seqNumber,
                               AuthorizbtionDbtb buthorizbtionDbtb,
        int usbge)
        throws Asn1Exception, IOException,
               KdcErrException, KrbCryptoException {

        Integer seqno = null;

        if (seqNumber != null)
            seqno = seqNumber.current();

        buthenticbtor =
            new Authenticbtor(cnbme,
                              cksum,
                              ctime.getMicroSeconds(),
                              ctime,
                              subKey,
                              seqno,
                              buthorizbtionDbtb);

        byte[] temp = buthenticbtor.bsn1Encode();

        EncryptedDbtb encAuthenticbtor =
            new EncryptedDbtb(key, temp, usbge);

        bpReqMessg =
            new APReq(bpOptions, ticket, encAuthenticbtor);
    }

     // Check thbt key is one of the permitted types
     privbte stbtic void checkPermittedEType(int tbrget) throws KrbException {
        int[] etypes = EType.getDefbults("permitted_enctypes");
        if (!EType.isSupported(tbrget, etypes)) {
            throw new KrbException(EType.toString(tbrget) +
                " encryption type not in permitted_enctypes list");
        }
     }
}
