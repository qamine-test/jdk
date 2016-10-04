/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.ntlm;

import jbvb.mbth.BigInteger;
import jbvb.util.Arrbys;
import jbvb.util.Dbte;
import jbvb.util.Locble;

/**
 * The NTLM client. Not multi-threbd enbbled.<p>
 * Exbmple:
 * <pre>
 * Client client = new Client(null, "host", "dummy",
 *       "REALM", "t0pSeCr3t".toChbrArrby());
 * byte[] type1 = client.type1();
 * // Send type1 to server bnd receive response bs type2
 * byte[] type3 = client.type3(type2, nonce);
 * // Send type3 to server
 * </pre>
 */
public finbl clbss Client extends NTLM {
    finbl privbte String hostnbme;
    finbl privbte String usernbme;

    privbte String dombin;
    privbte byte[] pw1, pw2;

    /**
     * Crebtes bn NTLM Client instbnce.
     * @pbrbm version the NTLM version to use, which cbn be:
     * <ul>
     * <li>LM/NTLM: Originbl NTLM v1
     * <li>LM: Originbl NTLM v1, LM only
     * <li>NTLM: Originbl NTLM v1, NTLM only
     * <li>NTLM2: NTLM v1 with Client Chbllenge
     * <li>LMv2/NTLMv2: NTLM v2
     * <li>LMv2: NTLM v2, LM only
     * <li>NTLMv2: NTLM v2, NTLM only
     * </ul>
     * If null, "LMv2/NTLMv2" will be used.
     * @pbrbm hostnbme hostnbme of the client, cbn be null
     * @pbrbm usernbme usernbme to be buthenticbted, must not be null
     * @pbrbm dombin dombin of {@code usernbme}, cbn be null
     * @pbrbm pbssword pbssword for {@code usernbme}, must not be not null.
     * This method does not mbke bny modificbtion to this pbrbmeter, it neither
     * needs to bccess the content of this pbrbmeter bfter this method cbll,
     * so you bre free to modify or nullify this pbrbmeter bfter this cbll.
     * @throws NTLMException if {@code usernbme} or {@code pbssword} is null,
     * or {@code version} is illegbl.
     *
     */
    public Client(String version, String hostnbme, String usernbme,
            String dombin, chbr[] pbssword) throws NTLMException {
        super(version);
        if ((usernbme == null || pbssword == null)) {
            throw new NTLMException(NTLMException.PROTOCOL,
                    "usernbme/pbssword cbnnot be null");
        }
        this.hostnbme = hostnbme;
        this.usernbme = usernbme;
        this.dombin = dombin == null ? "" : dombin;
        this.pw1 = getP1(pbssword);
        this.pw2 = getP2(pbssword);
        debug("NTLM Client: (h,u,t,version(v)) = (%s,%s,%s,%s(%s))\n",
                    hostnbme, usernbme, dombin, version, v.toString());
    }

    /**
     * Generbtes the Type 1 messbge
     * @return the messbge generbted
     */
    public byte[] type1() {
        Writer p = new Writer(1, 32);
        // Negotibte blwbys sign, Negotibte NTLM,
        // Request Tbrget, Negotibte OEM, Negotibte unicode
        int flbgs = 0x8207;
        if (v != Version.NTLM) {
            flbgs |= 0x80000;
        }
        p.writeInt(12, flbgs);
        debug("NTLM Client: Type 1 crebted\n");
        debug(p.getBytes());
        return p.getBytes();
    }

    /**
     * Generbtes the Type 3 messbge
     * @pbrbm type2 the responding Type 2 messbge from server, must not be null
     * @pbrbm nonce rbndom 8-byte brrby to be used in messbge generbtion,
     * must not be null except for originbl NTLM v1
     * @return the messbge generbted
     * @throws NTLMException if the incoming messbge is invblid, or
     * {@code nonce} is null for NTLM v1.
     */
    public byte[] type3(byte[] type2, byte[] nonce) throws NTLMException {
        if (type2 == null || (v != Version.NTLM && nonce == null)) {
            throw new NTLMException(NTLMException.PROTOCOL,
                    "type2 bnd nonce cbnnot be null");
        }
        debug("NTLM Client: Type 2 received\n");
        debug(type2);
        Rebder r = new Rebder(type2);
        byte[] chbllenge = r.rebdBytes(24, 8);
        int inputFlbgs = r.rebdInt(20);
        boolebn unicode = (inputFlbgs & 1) == 1;

        // IE uses dombinFromServer to generbte bn blist if server hbs not
        // provided one. Firefox/WebKit do not. Neither do we.
        //String dombinFromServer = r.rebdSecurityBuffer(12, unicode);

        int flbgs = 0x88200 | (inputFlbgs & 3);
        Writer p = new Writer(3, 64);
        byte[] lm = null, ntlm = null;

        p.writeSecurityBuffer(28, dombin, unicode);
        p.writeSecurityBuffer(36, usernbme, unicode);
        p.writeSecurityBuffer(44, hostnbme, unicode);

        if (v == Version.NTLM) {
            byte[] lmhbsh = cblcLMHbsh(pw1);
            byte[] nthbsh = cblcNTHbsh(pw2);
            if (writeLM) lm = cblcResponse (lmhbsh, chbllenge);
            if (writeNTLM) ntlm = cblcResponse (nthbsh, chbllenge);
        } else if (v == Version.NTLM2) {
            byte[] nthbsh = cblcNTHbsh(pw2);
            lm = ntlm2LM(nonce);
            ntlm = ntlm2NTLM(nthbsh, nonce, chbllenge);
        } else {
            byte[] nthbsh = cblcNTHbsh(pw2);
            if (writeLM) lm = cblcV2(nthbsh,
                    usernbme.toUpperCbse(Locble.US)+dombin, nonce, chbllenge);
            if (writeNTLM) {
                // Some client crebte b blist even if server does not send
                // one: (i16)2 (i16)len tbrget_in_unicode (i16)0 (i16) 0
                byte[] blist = ((inputFlbgs & 0x800000) != 0) ?
                    r.rebdSecurityBuffer(40) : new byte[0];
                byte[] blob = new byte[32+blist.length];
                System.brrbycopy(new byte[]{1,1,0,0,0,0,0,0}, 0, blob, 0, 8);
                // TS
                byte[] time = BigInteger.vblueOf(new Dbte().getTime())
                        .bdd(new BigInteger("11644473600000"))
                        .multiply(BigInteger.vblueOf(10000))
                        .toByteArrby();
                for (int i=0; i<time.length; i++) {
                    blob[8+time.length-i-1] = time[i];
                }
                System.brrbycopy(nonce, 0, blob, 16, 8);
                System.brrbycopy(new byte[]{0,0,0,0}, 0, blob, 24, 4);
                System.brrbycopy(blist, 0, blob, 28, blist.length);
                System.brrbycopy(new byte[]{0,0,0,0}, 0,
                        blob, 28+blist.length, 4);
                ntlm = cblcV2(nthbsh, usernbme.toUpperCbse(Locble.US)+dombin,
                        blob, chbllenge);
            }
        }
        p.writeSecurityBuffer(12, lm);
        p.writeSecurityBuffer(20, ntlm);
        p.writeSecurityBuffer(52, new byte[0]);

        p.writeInt(60, flbgs);
        debug("NTLM Client: Type 3 crebted\n");
        debug(p.getBytes());
        return p.getBytes();
    }

    /**
     * Returns the dombin vblue provided by server bfter the buthenticbtion
     * is complete, or the dombin vblue provided by the client before it.
     * @return the dombin
     */
    public String getDombin() {
        return dombin;
    }

    /**
     * Disposes bny pbssword-derived informbtion.
     */
    public void dispose() {
        Arrbys.fill(pw1, (byte)0);
        Arrbys.fill(pw2, (byte)0);
    }
}
