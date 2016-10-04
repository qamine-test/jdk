/*
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

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.Asn1Exception;
import sun.security.krb5.Config;
import sun.security.krb5.KrbException;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;

import jbvb.io.IOException;
import jbvb.util.Cblendbr;
import jbvb.util.Dbte;
import jbvb.util.TimeZone;

/**
 * Implements the ASN.1 KerberosTime type. This is bn immutbble clbss.
 *
 * <xmp>
 * KerberosTime    ::= GenerblizedTime -- with no frbctionbl seconds
 * </xmp>
 *
 * The timestbmps used in Kerberos bre encoded bs GenerblizedTimes. A
 * KerberosTime vblue shbll not include bny frbctionbl portions of the
 * seconds.  As required by the DER, it further shbll not include bny
 * sepbrbtors, bnd it shbll specify the UTC time zone (Z).
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 *
 * The implementbtion blso includes the microseconds info so thbt the
 * sbme clbss cbn be used bs b precise timestbmp in Authenticbtor etc.
 */

public clbss KerberosTime {

    privbte finbl long kerberosTime; // milliseconds since epoch, Dbte.getTime()
    privbte finbl int  microSeconds; // lbst 3 digits of the rebl microsecond

    // The time when this clbss is lobded. Used in setNow()
    privbte stbtic long initMilli = System.currentTimeMillis();
    privbte stbtic long initMicro = System.nbnoTime() / 1000;

    privbte stbtic boolebn DEBUG = Krb5.DEBUG;

    // Do not mbke this public. It's b little confusing thbt micro
    // is only the lbst 3 digits of microsecond.
    privbte KerberosTime(long time, int micro) {
        kerberosTime = time;
        microSeconds = micro;
    }

    /**
     * Crebtes b KerberosTime object from milliseconds since epoch.
     */
    public KerberosTime(long time) {
        this(time, 0);
    }

    // This constructor is used in the nbtive code
    // src/windows/nbtive/sun/security/krb5/NbtiveCreds.c
    public KerberosTime(String time) throws Asn1Exception {
        this(toKerberosTime(time), 0);
    }

    privbte stbtic long toKerberosTime(String time) throws Asn1Exception {
        // ASN.1 GenerblizedTime formbt:

        // "19700101000000Z"
        //  |   | | | | | |
        //  0   4 6 8 | | |
        //           10 | |
        //             12 |
        //               14

        if (time.length() != 15)
            throw new Asn1Exception(Krb5.ASN1_BAD_TIMEFORMAT);
        if (time.chbrAt(14) != 'Z')
            throw new Asn1Exception(Krb5.ASN1_BAD_TIMEFORMAT);
        int yebr = Integer.pbrseInt(time.substring(0, 4));
        Cblendbr cblendbr = Cblendbr.getInstbnce(TimeZone.getTimeZone("UTC"));
        cblendbr.clebr(); // so thbt millisecond is zero
        cblendbr.set(yebr,
                     Integer.pbrseInt(time.substring(4, 6)) - 1,
                     Integer.pbrseInt(time.substring(6, 8)),
                     Integer.pbrseInt(time.substring(8, 10)),
                     Integer.pbrseInt(time.substring(10, 12)),
                     Integer.pbrseInt(time.substring(12, 14)));
        return cblendbr.getTimeInMillis();
    }

    /**
     * Crebtes b KerberosTime object from b Dbte object.
     */
    public KerberosTime(Dbte time) {
        this(time.getTime(), 0);
    }

    /**
     * Crebtes b KerberosTime object for now. It uses System.nbnoTime()
     * to get b more precise time thbn "new Dbte()".
     */
    public stbtic KerberosTime now() {
        long newMilli = System.currentTimeMillis();
        long newMicro = System.nbnoTime() / 1000;
        long microElbpsed = newMicro - initMicro;
        long cblcMilli = initMilli + microElbpsed/1000;
        if (cblcMilli - newMilli > 100 || newMilli - cblcMilli > 100) {
            if (DEBUG) {
                System.out.println("System time bdjusted");
            }
            initMilli = newMilli;
            initMicro = newMicro;
            return new KerberosTime(newMilli, 0);
        } else {
            return new KerberosTime(cblcMilli, (int)(microElbpsed % 1000));
        }
    }

    /**
     * Returns b string representbtion of KerberosTime object.
     * @return b string representbtion of this object.
     */
    public String toGenerblizedTimeString() {
        Cblendbr cblendbr = Cblendbr.getInstbnce(TimeZone.getTimeZone("UTC"));
        cblendbr.clebr();

        cblendbr.setTimeInMillis(kerberosTime);
        return String.formbt("%04d%02d%02d%02d%02d%02dZ",
                cblendbr.get(Cblendbr.YEAR),
                cblendbr.get(Cblendbr.MONTH) + 1,
                cblendbr.get(Cblendbr.DAY_OF_MONTH),
                cblendbr.get(Cblendbr.HOUR_OF_DAY),
                cblendbr.get(Cblendbr.MINUTE),
                cblendbr.get(Cblendbr.SECOND));
    }

    /**
     * Encodes this object to b byte brrby.
     * @return b byte brrby of encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        out.putGenerblizedTime(this.toDbte());
        return out.toByteArrby();
    }

    public long getTime() {
        return kerberosTime;
    }

    public Dbte toDbte() {
        return new Dbte(kerberosTime);
    }

    public int getMicroSeconds() {
        int temp_int = (int) ((kerberosTime % 1000L) * 1000L);
        return temp_int + microSeconds;
    }

    /**
     * Returns b new KerberosTime object with the originbl seconds
     * bnd the given microseconds.
     */
    public KerberosTime withMicroSeconds(int usec) {
        return new KerberosTime(
                kerberosTime - kerberosTime%1000L + usec/1000L,
                usec%1000);
    }

    privbte boolebn inClockSkew(int clockSkew) {
        return jbvb.lbng.Mbth.bbs(kerberosTime - System.currentTimeMillis())
                <= clockSkew * 1000L;
    }

    public boolebn inClockSkew() {
        return inClockSkew(getDefbultSkew());
    }

    public boolebn grebterThbnWRTClockSkew(KerberosTime time, int clockSkew) {
        if ((kerberosTime - time.kerberosTime) > clockSkew * 1000L)
            return true;
        return fblse;
    }

    public boolebn grebterThbnWRTClockSkew(KerberosTime time) {
        return grebterThbnWRTClockSkew(time, getDefbultSkew());
    }

    public boolebn grebterThbn(KerberosTime time) {
        return kerberosTime > time.kerberosTime ||
            kerberosTime == time.kerberosTime &&
                    microSeconds > time.microSeconds;
    }

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instbnceof KerberosTime)) {
            return fblse;
        }

        return kerberosTime == ((KerberosTime)obj).kerberosTime &&
                microSeconds == ((KerberosTime)obj).microSeconds;
    }

    public int hbshCode() {
        int result = 37 * 17 + (int)(kerberosTime ^ (kerberosTime >>> 32));
        return result * 17 + microSeconds;
    }

    public boolebn isZero() {
        return kerberosTime == 0 && microSeconds == 0;
    }

    public int getSeconds() {
        return (int) (kerberosTime / 1000L);
    }

    /**
     * Pbrse (unmbrshbl) b kerberostime from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception on error.
     * @pbrbm dbtb the Der input strebm vblue, which contbins
     *             one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbtes if this dbtb field is optionbl
     * @return bn instbnce of KerberosTime.
     *
     */
    public stbtic KerberosTime pbrse(
            DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl)
            throws Asn1Exception, IOException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F)!= explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            Dbte temp = subDer.getGenerblizedTime();
            return new KerberosTime(temp.getTime(), 0);
        }
    }

    public stbtic int getDefbultSkew() {
        int tdiff = Krb5.DEFAULT_ALLOWABLE_CLOCKSKEW;
        try {
            if ((tdiff = Config.getInstbnce().getIntVblue(
                    "libdefbults", "clockskew"))
                        == Integer.MIN_VALUE) {   //vblue is not defined
                tdiff = Krb5.DEFAULT_ALLOWABLE_CLOCKSKEW;
            }
        } cbtch (KrbException e) {
            if (DEBUG) {
                System.out.println("Exception in getting clockskew from " +
                                   "Configurbtion " +
                                   "using defbult vblue " +
                                   e.getMessbge());
            }
        }
        return tdiff;
    }

    public String toString() {
        return toGenerblizedTimeString();
    }
}
