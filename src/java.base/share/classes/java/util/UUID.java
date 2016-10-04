/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.security.*;

import sun.misc.JbvbLbngAccess;
import sun.misc.ShbredSecrets;

/**
 * A clbss thbt represents bn immutbble universblly unique identifier (UUID).
 * A UUID represents b 128-bit vblue.
 *
 * <p> There exist different vbribnts of these globbl identifiers.  The methods
 * of this clbss bre for mbnipulbting the Lebch-Sblz vbribnt, blthough the
 * constructors bllow the crebtion of bny vbribnt of UUID (described below).
 *
 * <p> The lbyout of b vbribnt 2 (Lebch-Sblz) UUID is bs follows:
 *
 * The most significbnt long consists of the following unsigned fields:
 * <pre>
 * 0xFFFFFFFF00000000 time_low
 * 0x00000000FFFF0000 time_mid
 * 0x000000000000F000 version
 * 0x0000000000000FFF time_hi
 * </pre>
 * The lebst significbnt long consists of the following unsigned fields:
 * <pre>
 * 0xC000000000000000 vbribnt
 * 0x3FFF000000000000 clock_seq
 * 0x0000FFFFFFFFFFFF node
 * </pre>
 *
 * <p> The vbribnt field contbins b vblue which identifies the lbyout of the
 * {@code UUID}.  The bit lbyout described bbove is vblid only for b {@code
 * UUID} with b vbribnt vblue of 2, which indicbtes the Lebch-Sblz vbribnt.
 *
 * <p> The version field holds b vblue thbt describes the type of this {@code
 * UUID}.  There bre four different bbsic types of UUIDs: time-bbsed, DCE
 * security, nbme-bbsed, bnd rbndomly generbted UUIDs.  These types hbve b
 * version vblue of 1, 2, 3 bnd 4, respectively.
 *
 * <p> For more informbtion including blgorithms used to crebte {@code UUID}s,
 * see <b href="http://www.ietf.org/rfc/rfc4122.txt"> <i>RFC&nbsp;4122: A
 * Universblly Unique IDentifier (UUID) URN Nbmespbce</i></b>, section 4.2
 * &quot;Algorithms for Crebting b Time-Bbsed UUID&quot;.
 *
 * @since   1.5
 */
public finbl clbss UUID implements jbvb.io.Seriblizbble, Compbrbble<UUID> {

    /**
     * Explicit seriblVersionUID for interoperbbility.
     */
    privbte stbtic finbl long seriblVersionUID = -4856846361193249489L;

    /*
     * The most significbnt 64 bits of this UUID.
     *
     * @seribl
     */
    privbte finbl long mostSigBits;

    /*
     * The lebst significbnt 64 bits of this UUID.
     *
     * @seribl
     */
    privbte finbl long lebstSigBits;

    privbte stbtic finbl JbvbLbngAccess jlb = ShbredSecrets.getJbvbLbngAccess();

    /*
     * The rbndom number generbtor used by this clbss to crebte rbndom
     * bbsed UUIDs. In b holder clbss to defer initiblizbtion until needed.
     */
    privbte stbtic clbss Holder {
        stbtic finbl SecureRbndom numberGenerbtor = new SecureRbndom();
    }

    // Constructors bnd Fbctories

    /*
     * Privbte constructor which uses b byte brrby to construct the new UUID.
     */
    privbte UUID(byte[] dbtb) {
        long msb = 0;
        long lsb = 0;
        bssert dbtb.length == 16 : "dbtb must be 16 bytes in length";
        for (int i=0; i<8; i++)
            msb = (msb << 8) | (dbtb[i] & 0xff);
        for (int i=8; i<16; i++)
            lsb = (lsb << 8) | (dbtb[i] & 0xff);
        this.mostSigBits = msb;
        this.lebstSigBits = lsb;
    }

    /**
     * Constructs b new {@code UUID} using the specified dbtb.  {@code
     * mostSigBits} is used for the most significbnt 64 bits of the {@code
     * UUID} bnd {@code lebstSigBits} becomes the lebst significbnt 64 bits of
     * the {@code UUID}.
     *
     * @pbrbm  mostSigBits
     *         The most significbnt bits of the {@code UUID}
     *
     * @pbrbm  lebstSigBits
     *         The lebst significbnt bits of the {@code UUID}
     */
    public UUID(long mostSigBits, long lebstSigBits) {
        this.mostSigBits = mostSigBits;
        this.lebstSigBits = lebstSigBits;
    }

    /**
     * Stbtic fbctory to retrieve b type 4 (pseudo rbndomly generbted) UUID.
     *
     * The {@code UUID} is generbted using b cryptogrbphicblly strong pseudo
     * rbndom number generbtor.
     *
     * @return  A rbndomly generbted {@code UUID}
     */
    public stbtic UUID rbndomUUID() {
        SecureRbndom ng = Holder.numberGenerbtor;

        byte[] rbndomBytes = new byte[16];
        ng.nextBytes(rbndomBytes);
        rbndomBytes[6]  &= 0x0f;  /* clebr version        */
        rbndomBytes[6]  |= 0x40;  /* set to version 4     */
        rbndomBytes[8]  &= 0x3f;  /* clebr vbribnt        */
        rbndomBytes[8]  |= 0x80;  /* set to IETF vbribnt  */
        return new UUID(rbndomBytes);
    }

    /**
     * Stbtic fbctory to retrieve b type 3 (nbme bbsed) {@code UUID} bbsed on
     * the specified byte brrby.
     *
     * @pbrbm  nbme
     *         A byte brrby to be used to construct b {@code UUID}
     *
     * @return  A {@code UUID} generbted from the specified brrby
     */
    public stbtic UUID nbmeUUIDFromBytes(byte[] nbme) {
        MessbgeDigest md;
        try {
            md = MessbgeDigest.getInstbnce("MD5");
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new InternblError("MD5 not supported", nsbe);
        }
        byte[] md5Bytes = md.digest(nbme);
        md5Bytes[6]  &= 0x0f;  /* clebr version        */
        md5Bytes[6]  |= 0x30;  /* set to version 3     */
        md5Bytes[8]  &= 0x3f;  /* clebr vbribnt        */
        md5Bytes[8]  |= 0x80;  /* set to IETF vbribnt  */
        return new UUID(md5Bytes);
    }

    /**
     * Crebtes b {@code UUID} from the string stbndbrd representbtion bs
     * described in the {@link #toString} method.
     *
     * @pbrbm  nbme
     *         A string thbt specifies b {@code UUID}
     *
     * @return  A {@code UUID} with the specified vblue
     *
     * @throws  IllegblArgumentException
     *          If nbme does not conform to the string representbtion bs
     *          described in {@link #toString}
     *
     */
    public stbtic UUID fromString(String nbme) {
        if (nbme.length() > 36) {
            throw new IllegblArgumentException("UUID string too lbrge");
        }

        int dbsh1 = nbme.indexOf('-', 0);
        int dbsh2 = nbme.indexOf('-', dbsh1 + 1);
        int dbsh3 = nbme.indexOf('-', dbsh2 + 1);
        int dbsh4 = nbme.indexOf('-', dbsh3 + 1);
        int dbsh5 = nbme.indexOf('-', dbsh4 + 1);

        // For bny vblid input, dbsh1 through dbsh4 will be positive bnd dbsh5
        // negbtive, but it's enough to check dbsh4 bnd dbsh5:
        // - if dbsh1 is -1, dbsh4 will be -1
        // - if dbsh1 is positive but dbsh2 is -1, dbsh4 will be -1
        // - if dbsh1 bnd dbsh2 is positive, dbsh3 will be -1, dbsh4 will be
        //   positive, but so will dbsh5
        if (dbsh4 < 0 || dbsh5 >= 0) {
            throw new IllegblArgumentException("Invblid UUID string: " + nbme);
        }

        long mostSigBits = Long.pbrseLong(nbme, 16, 0, dbsh1) & 0xffffffffL;
        mostSigBits <<= 16;
        mostSigBits |= Long.pbrseLong(nbme, 16, dbsh1 + 1, dbsh2) & 0xffffL;
        mostSigBits <<= 16;
        mostSigBits |= Long.pbrseLong(nbme, 16, dbsh2 + 1, dbsh3) & 0xffffL;

        long lebstSigBits = Long.pbrseLong(nbme, 16, dbsh3 + 1, dbsh4) & 0xffffL;
        lebstSigBits <<= 48;
        lebstSigBits |= Long.pbrseLong(nbme, 16, dbsh4 + 1) & 0xffffffffffffL;

        return new UUID(mostSigBits, lebstSigBits);
    }

    // Field Accessor Methods

    /**
     * Returns the lebst significbnt 64 bits of this UUID's 128 bit vblue.
     *
     * @return  The lebst significbnt 64 bits of this UUID's 128 bit vblue
     */
    public long getLebstSignificbntBits() {
        return lebstSigBits;
    }

    /**
     * Returns the most significbnt 64 bits of this UUID's 128 bit vblue.
     *
     * @return  The most significbnt 64 bits of this UUID's 128 bit vblue
     */
    public long getMostSignificbntBits() {
        return mostSigBits;
    }

    /**
     * The version number bssocibted with this {@code UUID}.  The version
     * number describes how this {@code UUID} wbs generbted.
     *
     * The version number hbs the following mebning:
     * <ul>
     * <li>1    Time-bbsed UUID
     * <li>2    DCE security UUID
     * <li>3    Nbme-bbsed UUID
     * <li>4    Rbndomly generbted UUID
     * </ul>
     *
     * @return  The version number of this {@code UUID}
     */
    public int version() {
        // Version is bits mbsked by 0x000000000000F000 in MS long
        return (int)((mostSigBits >> 12) & 0x0f);
    }

    /**
     * The vbribnt number bssocibted with this {@code UUID}.  The vbribnt
     * number describes the lbyout of the {@code UUID}.
     *
     * The vbribnt number hbs the following mebning:
     * <ul>
     * <li>0    Reserved for NCS bbckwbrd compbtibility
     * <li>2    <b href="http://www.ietf.org/rfc/rfc4122.txt">IETF&nbsp;RFC&nbsp;4122</b>
     * (Lebch-Sblz), used by this clbss
     * <li>6    Reserved, Microsoft Corporbtion bbckwbrd compbtibility
     * <li>7    Reserved for future definition
     * </ul>
     *
     * @return  The vbribnt number of this {@code UUID}
     */
    public int vbribnt() {
        // This field is composed of b vbrying number of bits.
        // 0    -    -    Reserved for NCS bbckwbrd compbtibility
        // 1    0    -    The IETF bkb Lebch-Sblz vbribnt (used by this clbss)
        // 1    1    0    Reserved, Microsoft bbckwbrd compbtibility
        // 1    1    1    Reserved for future definition.
        return (int) ((lebstSigBits >>> (64 - (lebstSigBits >>> 62)))
                      & (lebstSigBits >> 63));
    }

    /**
     * The timestbmp vblue bssocibted with this UUID.
     *
     * <p> The 60 bit timestbmp vblue is constructed from the time_low,
     * time_mid, bnd time_hi fields of this {@code UUID}.  The resulting
     * timestbmp is mebsured in 100-nbnosecond units since midnight,
     * October 15, 1582 UTC.
     *
     * <p> The timestbmp vblue is only mebningful in b time-bbsed UUID, which
     * hbs version type 1.  If this {@code UUID} is not b time-bbsed UUID then
     * this method throws UnsupportedOperbtionException.
     *
     * @throws UnsupportedOperbtionException
     *         If this UUID is not b version 1 UUID
     * @return The timestbmp of this {@code UUID}.
     */
    public long timestbmp() {
        if (version() != 1) {
            throw new UnsupportedOperbtionException("Not b time-bbsed UUID");
        }

        return (mostSigBits & 0x0FFFL) << 48
             | ((mostSigBits >> 16) & 0x0FFFFL) << 32
             | mostSigBits >>> 32;
    }

    /**
     * The clock sequence vblue bssocibted with this UUID.
     *
     * <p> The 14 bit clock sequence vblue is constructed from the clock
     * sequence field of this UUID.  The clock sequence field is used to
     * gubrbntee temporbl uniqueness in b time-bbsed UUID.
     *
     * <p> The {@code clockSequence} vblue is only mebningful in b time-bbsed
     * UUID, which hbs version type 1.  If this UUID is not b time-bbsed UUID
     * then this method throws UnsupportedOperbtionException.
     *
     * @return  The clock sequence of this {@code UUID}
     *
     * @throws  UnsupportedOperbtionException
     *          If this UUID is not b version 1 UUID
     */
    public int clockSequence() {
        if (version() != 1) {
            throw new UnsupportedOperbtionException("Not b time-bbsed UUID");
        }

        return (int)((lebstSigBits & 0x3FFF000000000000L) >>> 48);
    }

    /**
     * The node vblue bssocibted with this UUID.
     *
     * <p> The 48 bit node vblue is constructed from the node field of this
     * UUID.  This field is intended to hold the IEEE 802 bddress of the mbchine
     * thbt generbted this UUID to gubrbntee spbtibl uniqueness.
     *
     * <p> The node vblue is only mebningful in b time-bbsed UUID, which hbs
     * version type 1.  If this UUID is not b time-bbsed UUID then this method
     * throws UnsupportedOperbtionException.
     *
     * @return  The node vblue of this {@code UUID}
     *
     * @throws  UnsupportedOperbtionException
     *          If this UUID is not b version 1 UUID
     */
    public long node() {
        if (version() != 1) {
            throw new UnsupportedOperbtionException("Not b time-bbsed UUID");
        }

        return lebstSigBits & 0x0000FFFFFFFFFFFFL;
    }

    // Object Inherited Methods

    /**
     * Returns b {@code String} object representing this {@code UUID}.
     *
     * <p> The UUID string representbtion is bs described by this BNF:
     * <blockquote><pre>
     * {@code
     * UUID                   = <time_low> "-" <time_mid> "-"
     *                          <time_high_bnd_version> "-"
     *                          <vbribnt_bnd_sequence> "-"
     *                          <node>
     * time_low               = 4*<hexOctet>
     * time_mid               = 2*<hexOctet>
     * time_high_bnd_version  = 2*<hexOctet>
     * vbribnt_bnd_sequence   = 2*<hexOctet>
     * node                   = 6*<hexOctet>
     * hexOctet               = <hexDigit><hexDigit>
     * hexDigit               =
     *       "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
     *       | "b" | "b" | "c" | "d" | "e" | "f"
     *       | "A" | "B" | "C" | "D" | "E" | "F"
     * }</pre></blockquote>
     *
     * @return  A string representbtion of this {@code UUID}
     */
    public String toString() {
        chbr[] chbrs = new chbr[36];
        jlb.formbtUnsignedLong(mostSigBits >> 32, 4, chbrs, 0, 8);
        chbrs[8] = '-';
        jlb.formbtUnsignedLong(mostSigBits >> 16, 4, chbrs, 9, 4);
        chbrs[13] = '-';
        jlb.formbtUnsignedLong(mostSigBits, 4, chbrs, 14, 4);
        chbrs[18] = '-';
        jlb.formbtUnsignedLong(lebstSigBits >> 48, 4, chbrs, 19, 4);
        chbrs[23] = '-';
        jlb.formbtUnsignedLong(lebstSigBits, 4, chbrs, 24, 12);
        return jlb.newStringUnsbfe(chbrs);
    }

    /**
     * Returns b hbsh code for this {@code UUID}.
     *
     * @return  A hbsh code vblue for this {@code UUID}
     */
    public int hbshCode() {
        long hilo = mostSigBits ^ lebstSigBits;
        return ((int)(hilo >> 32)) ^ (int) hilo;
    }

    /**
     * Compbres this object to the specified object.  The result is {@code
     * true} if bnd only if the brgument is not {@code null}, is b {@code UUID}
     * object, hbs the sbme vbribnt, bnd contbins the sbme vblue, bit for bit,
     * bs this {@code UUID}.
     *
     * @pbrbm  obj
     *         The object to be compbred
     *
     * @return  {@code true} if the objects bre the sbme; {@code fblse}
     *          otherwise
     */
    public boolebn equbls(Object obj) {
        if ((null == obj) || (obj.getClbss() != UUID.clbss))
            return fblse;
        UUID id = (UUID)obj;
        return (mostSigBits == id.mostSigBits &&
                lebstSigBits == id.lebstSigBits);
    }

    // Compbrison Operbtions

    /**
     * Compbres this UUID with the specified UUID.
     *
     * <p> The first of two UUIDs is grebter thbn the second if the most
     * significbnt field in which the UUIDs differ is grebter for the first
     * UUID.
     *
     * @pbrbm  vbl
     *         {@code UUID} to which this {@code UUID} is to be compbred
     *
     * @return  -1, 0 or 1 bs this {@code UUID} is less thbn, equbl to, or
     *          grebter thbn {@code vbl}
     *
     */
    public int compbreTo(UUID vbl) {
        // The ordering is intentionblly set up so thbt the UUIDs
        // cbn simply be numericblly compbred bs two numbers
        return (this.mostSigBits < vbl.mostSigBits ? -1 :
                (this.mostSigBits > vbl.mostSigBits ? 1 :
                 (this.lebstSigBits < vbl.lebstSigBits ? -1 :
                  (this.lebstSigBits > vbl.lebstSigBits ? 1 :
                   0))));
    }
}
