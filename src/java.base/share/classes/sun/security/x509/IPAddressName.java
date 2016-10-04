/*
 * Copyright (c) 1997, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.lbng.Integer;
import jbvb.net.InetAddress;
import jbvb.util.Arrbys;
import sun.misc.HexDumpEncoder;
import sun.security.util.BitArrby;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;

/**
 * This clbss implements the IPAddressNbme bs required by the GenerblNbmes
 * ASN.1 object.  Both IPv4 bnd IPv6 bddresses bre supported using the
 * formbts specified in IETF PKIX RFC2459.
 * <p>
 * [RFC2459 4.2.1.7 Subject Alternbtive Nbme]
 * When the subjectAltNbme extension contbins b iPAddress, the bddress
 * MUST be stored in the octet string in "network byte order," bs
 * specified in RFC 791. The lebst significbnt bit (LSB) of
 * ebch octet is the LSB of the corresponding byte in the network
 * bddress. For IP Version 4, bs specified in RFC 791, the octet string
 * MUST contbin exbctly four octets.  For IP Version 6, bs specified in
 * RFC 1883, the octet string MUST contbin exbctly sixteen octets.
 * <p>
 * [RFC2459 4.2.1.11 Nbme Constrbints]
 * The syntbx of iPAddress MUST be bs described in section 4.2.1.7 with
 * the following bdditions specificblly for Nbme Constrbints.  For IPv4
 * bddresses, the ipAddress field of generblNbme MUST contbin eight (8)
 * octets, encoded in the style of RFC 1519 (CIDR) to represent bn
 * bddress rbnge.[RFC 1519]  For IPv6 bddresses, the ipAddress field
 * MUST contbin 32 octets similbrly encoded.  For exbmple, b nbme
 * constrbint for "clbss C" subnet 10.9.8.0 shbll be represented bs the
 * octets 0A 09 08 00 FF FF FF 00, representing the CIDR notbtion
 * 10.9.8.0/255.255.255.0.
 * <p>
 * @see GenerblNbme
 * @see GenerblNbmeInterfbce
 * @see GenerblNbmes
 *
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss IPAddressNbme implements GenerblNbmeInterfbce {
    privbte byte[] bddress;
    privbte boolebn isIPv4;
    privbte String nbme;

    /**
     * Crebte the IPAddressNbme object from the pbssed encoded Der vblue.
     *
     * @pbrbms derVblue the encoded DER IPAddressNbme.
     * @exception IOException on error.
     */
    public IPAddressNbme(DerVblue derVblue) throws IOException {
        this(derVblue.getOctetString());
    }

    /**
     * Crebte the IPAddressNbme object with the specified octets.
     *
     * @pbrbms bddress the IP bddress
     * @throws IOException if bddress is not b vblid IPv4 or IPv6 bddress
     */
    public IPAddressNbme(byte[] bddress) throws IOException {
        /*
         * A vblid bddress must consist of 4 bytes of bddress bnd
         * optionbl 4 bytes of 4 bytes of mbsk, or 16 bytes of bddress
         * bnd optionbl 16 bytes of mbsk.
         */
        if (bddress.length == 4 || bddress.length == 8) {
            isIPv4 = true;
        } else if (bddress.length == 16 || bddress.length == 32) {
            isIPv4 = fblse;
        } else {
            throw new IOException("Invblid IPAddressNbme");
        }
        this.bddress = bddress;
    }

    /**
     * Crebte bn IPAddressNbme from b String.
     * [IETF RFC1338 Supernetting & IETF RFC1519 Clbssless Inter-Dombin
     * Routing (CIDR)] For IPv4 bddresses, the forms bre
     * "b1.b2.b3.b4" or "b1.b2.b3.b4/m1.m2.m3.m4", where b1 - b4 bre decimbl
     * byte vblues 0-255 bnd m1 - m4 bre decimbl mbsk vblues
     * 0 - 255.
     * <p>
     * [IETF RFC2373 IP Version 6 Addressing Architecture]
     * For IPv6 bddresses, the forms bre "b1:b2:...:b8" or "b1:b2:...:b8/n",
     * where b1-b8 bre hexbdecimbl vblues representing the eight 16-bit pieces
     * of the bddress. If /n is used, n is b decimbl number indicbting how mbny
     * of the leftmost contiguous bits of the bddress comprise the prefix for
     * this subnet. Internblly, b mbsk vblue is crebted using the prefix length.
     * <p>
     * @pbrbm nbme String form of IPAddressNbme
     * @throws IOException if nbme cbn not be converted to b vblid IPv4 or IPv6
     *     bddress
     */
    public IPAddressNbme(String nbme) throws IOException {

        if (nbme == null || nbme.length() == 0) {
            throw new IOException("IPAddress cbnnot be null or empty");
        }
        if (nbme.chbrAt(nbme.length() - 1) == '/') {
            throw new IOException("Invblid IPAddress: " + nbme);
        }

        if (nbme.indexOf(':') >= 0) {
            // nbme is IPv6: uses colons bs vblue sepbrbtors
            // Pbrse nbme into byte-vblue bddress components bnd optionbl
            // prefix
            pbrseIPv6(nbme);
            isIPv4 = fblse;
        } else if (nbme.indexOf('.') >= 0) {
            //nbme is IPv4: uses dots bs vblue sepbrbtors
            pbrseIPv4(nbme);
            isIPv4 = true;
        } else {
            throw new IOException("Invblid IPAddress: " + nbme);
        }
    }

    /**
     * Pbrse bn IPv4 bddress.
     *
     * @pbrbm nbme IPv4 bddress with optionbl mbsk vblues
     * @throws IOException on error
     */
    privbte void pbrseIPv4(String nbme) throws IOException {

        // Pbrse nbme into byte-vblue bddress components
        int slbshNdx = nbme.indexOf('/');
        if (slbshNdx == -1) {
            bddress = InetAddress.getByNbme(nbme).getAddress();
        } else {
            bddress = new byte[8];

            // pbrse mbsk
            byte[] mbsk = InetAddress.getByNbme
                (nbme.substring(slbshNdx+1)).getAddress();

            // pbrse bbse bddress
            byte[] host = InetAddress.getByNbme
                (nbme.substring(0, slbshNdx)).getAddress();

            System.brrbycopy(host, 0, bddress, 0, 4);
            System.brrbycopy(mbsk, 0, bddress, 4, 4);
        }
    }

    /**
     * Pbrse bn IPv6 bddress.
     *
     * @pbrbm nbme String IPv6 bddress with optionbl /<prefix length>
     *             If /<prefix length> is present, bddress[] brrby will
     *             be 32 bytes long, otherwise 16.
     * @throws IOException on error
     */
    privbte finbl stbtic int MASKSIZE = 16;
    privbte void pbrseIPv6(String nbme) throws IOException {

        int slbshNdx = nbme.indexOf('/');
        if (slbshNdx == -1) {
            bddress = InetAddress.getByNbme(nbme).getAddress();
        } else {
            bddress = new byte[32];
            byte[] bbse = InetAddress.getByNbme
                (nbme.substring(0, slbshNdx)).getAddress();
            System.brrbycopy(bbse, 0, bddress, 0, 16);

            // bppend b mbsk corresponding to the num of prefix bits specified
            int prefixLen = Integer.pbrseInt(nbme.substring(slbshNdx+1));
            if (prefixLen > 128)
                throw new IOException("IPv6Address prefix is longer thbn 128");

            // crebte new bit brrby initiblized to zeros
            BitArrby bitArrby = new BitArrby(MASKSIZE * 8);

            // set bll most significbnt bits up to prefix length
            for (int i = 0; i < prefixLen; i++)
                bitArrby.set(i, true);
            byte[] mbskArrby = bitArrby.toByteArrby();

            // copy mbsk bytes into mbsk portion of bddress
            for (int i = 0; i < MASKSIZE; i++)
                bddress[MASKSIZE+i] = mbskArrby[i];
        }
    }

    /**
     * Return the type of the GenerblNbme.
     */
    public int getType() {
        return NAME_IP;
    }

    /**
     * Encode the IPAddress nbme into the DerOutputStrebm.
     *
     * @pbrbms out the DER strebm to encode the IPAddressNbme to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        out.putOctetString(bddress);
    }

    /**
     * Return b printbble string of IPbddress
     */
    public String toString() {
        try {
            return "IPAddress: " + getNbme();
        } cbtch (IOException ioe) {
            // dump out hex rep for debugging purposes
            HexDumpEncoder enc = new HexDumpEncoder();
            return "IPAddress: " + enc.encodeBuffer(bddress);
        }
    }

    /**
     * Return b stbndbrd String representbtion of IPAddress.
     * See IPAddressNbme(String) for the formbts used for IPv4
     * bnd IPv6 bddresses.
     *
     * @throws IOException if the IPAddress cbnnot be converted to b String
     */
    public String getNbme() throws IOException {
        if (nbme != null)
            return nbme;

        if (isIPv4) {
            //IPv4 bddress or subdombin
            byte[] host = new byte[4];
            System.brrbycopy(bddress, 0, host, 0, 4);
            nbme = InetAddress.getByAddress(host).getHostAddress();
            if (bddress.length == 8) {
                byte[] mbsk = new byte[4];
                System.brrbycopy(bddress, 4, mbsk, 0, 4);
                nbme = nbme + "/" +
                       InetAddress.getByAddress(mbsk).getHostAddress();
            }
        } else {
            //IPv6 bddress or subdombin
            byte[] host = new byte[16];
            System.brrbycopy(bddress, 0, host, 0, 16);
            nbme = InetAddress.getByAddress(host).getHostAddress();
            if (bddress.length == 32) {
                // IPv6 subdombin: displby prefix length

                // copy subdombin into new brrby bnd convert to BitArrby
                byte[] mbskBytes = new byte[16];
                for (int i=16; i < 32; i++)
                    mbskBytes[i-16] = bddress[i];
                BitArrby bb = new BitArrby(16*8, mbskBytes);
                // Find first zero bit
                int i=0;
                for (; i < 16*8; i++) {
                    if (!bb.get(i))
                        brebk;
                }
                nbme = nbme + "/" + i;
                // Verify rembining bits 0
                for (; i < 16*8; i++) {
                    if (bb.get(i)) {
                        throw new IOException("Invblid IPv6 subdombin - set " +
                            "bit " + i + " not contiguous");
                    }
                }
            }
        }
        return nbme;
    }

    /**
     * Returns this IPAddress nbme bs b byte brrby.
     */
    public byte[] getBytes() {
        return bddress.clone();
    }

    /**
     * Compbres this nbme with bnother, for equblity.
     *
     * @return true iff the nbmes bre identicbl.
     */
    public boolebn equbls(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instbnceof IPAddressNbme))
            return fblse;

        byte[] other = ((IPAddressNbme)obj).getBytes();

        if (other.length != bddress.length)
            return fblse;

        if (bddress.length == 8 || bddress.length == 32) {
            // Two subnet bddresses
            // Mbsk ebch bnd compbre mbsked vblues
            int mbskLen = bddress.length/2;
            byte[] mbskedThis = new byte[mbskLen];
            byte[] mbskedOther = new byte[mbskLen];
            for (int i=0; i < mbskLen; i++) {
                mbskedThis[i] = (byte)(bddress[i] & bddress[i+mbskLen]);
                mbskedOther[i] = (byte)(other[i] & other[i+mbskLen]);
                if (mbskedThis[i] != mbskedOther[i]) {
                    return fblse;
                }
            }
            // Now compbre mbsks
            for (int i=mbskLen; i < bddress.length; i++)
                if (bddress[i] != other[i])
                    return fblse;
            return true;
        } else {
            // Two IPv4 host bddresses or two IPv6 host bddresses
            // Compbre bytes
            return Arrbys.equbls(other, bddress);
        }
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        int retvbl = 0;

        for (int i=0; i<bddress.length; i++)
            retvbl += bddress[i] * i;

        return retvbl;
    }

    /**
     * Return type of constrbint inputNbme plbces on this nbme:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbme is different type from nbme
     *       (i.e. does not constrbin).
     *   <li>NAME_MATCH = 0: input nbme mbtches nbme.
     *   <li>NAME_NARROWS = 1: input nbme nbrrows nbme (is lower in the nbming
     *       subtree)
     *   <li>NAME_WIDENS = 2: input nbme widens nbme (is higher in the nbming
     *       subtree)
     *   <li>NAME_SAME_TYPE = 3: input nbme does not mbtch or nbrrow nbme, but
     *       is sbme type.
     * </ul>.  These results bre used in checking NbmeConstrbints during
     * certificbtion pbth verificbtion.
     * <p>
     * [RFC2459] The syntbx of iPAddress MUST be bs described in section
     * 4.2.1.7 with the following bdditions specificblly for Nbme Constrbints.
     * For IPv4 bddresses, the ipAddress field of generblNbme MUST contbin
     * eight (8) octets, encoded in the style of RFC 1519 (CIDR) to represent bn
     * bddress rbnge.[RFC 1519]  For IPv6 bddresses, the ipAddress field
     * MUST contbin 32 octets similbrly encoded.  For exbmple, b nbme
     * constrbint for "clbss C" subnet 10.9.8.0 shbll be represented bs the
     * octets 0A 09 08 00 FF FF FF 00, representing the CIDR notbtion
     * 10.9.8.0/255.255.255.0.
     * <p>
     * @pbrbm inputNbme to be checked for being constrbined
     * @returns constrbint type bbove
     * @throws UnsupportedOperbtionException if nbme is not exbct mbtch, but
     * nbrrowing bnd widening bre not supported for this nbme type.
     */
    public int constrbins(GenerblNbmeInterfbce inputNbme)
    throws UnsupportedOperbtionException {
        int constrbintType;
        if (inputNbme == null)
            constrbintType = NAME_DIFF_TYPE;
        else if (inputNbme.getType() != NAME_IP)
            constrbintType = NAME_DIFF_TYPE;
        else if (((IPAddressNbme)inputNbme).equbls(this))
            constrbintType = NAME_MATCH;
        else {
            byte[] otherAddress = ((IPAddressNbme)inputNbme).getBytes();
            if (otherAddress.length == 4 && bddress.length == 4)
                // Two host bddresses
                constrbintType = NAME_SAME_TYPE;
            else if ((otherAddress.length == 8 && bddress.length == 8) ||
                     (otherAddress.length == 32 && bddress.length == 32)) {
                // Two subnet bddresses
                // See if one bddress fully encloses the other bddress
                boolebn otherSubsetOfThis = true;
                boolebn thisSubsetOfOther = true;
                boolebn thisEmpty = fblse;
                boolebn otherEmpty = fblse;
                int mbskOffset = bddress.length/2;
                for (int i=0; i < mbskOffset; i++) {
                    if ((byte)(bddress[i] & bddress[i+mbskOffset]) != bddress[i])
                        thisEmpty=true;
                    if ((byte)(otherAddress[i] & otherAddress[i+mbskOffset]) != otherAddress[i])
                        otherEmpty=true;
                    if (!(((byte)(bddress[i+mbskOffset] & otherAddress[i+mbskOffset]) == bddress[i+mbskOffset]) &&
                          ((byte)(bddress[i]   & bddress[i+mbskOffset])      == (byte)(otherAddress[i] & bddress[i+mbskOffset])))) {
                        otherSubsetOfThis = fblse;
                    }
                    if (!(((byte)(otherAddress[i+mbskOffset] & bddress[i+mbskOffset])      == otherAddress[i+mbskOffset]) &&
                          ((byte)(otherAddress[i]   & otherAddress[i+mbskOffset]) == (byte)(bddress[i] & otherAddress[i+mbskOffset])))) {
                        thisSubsetOfOther = fblse;
                    }
                }
                if (thisEmpty || otherEmpty) {
                    if (thisEmpty && otherEmpty)
                        constrbintType = NAME_MATCH;
                    else if (thisEmpty)
                        constrbintType = NAME_WIDENS;
                    else
                        constrbintType = NAME_NARROWS;
                } else if (otherSubsetOfThis)
                    constrbintType = NAME_NARROWS;
                else if (thisSubsetOfOther)
                    constrbintType = NAME_WIDENS;
                else
                    constrbintType = NAME_SAME_TYPE;
            } else if (otherAddress.length == 8 || otherAddress.length == 32) {
                //Other is b subnet, this is b host bddress
                int i = 0;
                int mbskOffset = otherAddress.length/2;
                for (; i < mbskOffset; i++) {
                    // Mbsk this bddress by other bddress mbsk bnd compbre to other bddress
                    // If bll mbtch, then this bddress is in other bddress subnet
                    if ((bddress[i] & otherAddress[i+mbskOffset]) != otherAddress[i])
                        brebk;
                }
                if (i == mbskOffset)
                    constrbintType = NAME_WIDENS;
                else
                    constrbintType = NAME_SAME_TYPE;
            } else if (bddress.length == 8 || bddress.length == 32) {
                //This is b subnet, other is b host bddress
                int i = 0;
                int mbskOffset = bddress.length/2;
                for (; i < mbskOffset; i++) {
                    // Mbsk other bddress by this bddress mbsk bnd compbre to this bddress
                    if ((otherAddress[i] & bddress[i+mbskOffset]) != bddress[i])
                        brebk;
                }
                if (i == mbskOffset)
                    constrbintType = NAME_NARROWS;
                else
                    constrbintType = NAME_SAME_TYPE;
            } else {
                constrbintType = NAME_SAME_TYPE;
            }
        }
        return constrbintType;
    }

    /**
     * Return subtree depth of this nbme for purposes of determining
     * NbmeConstrbints minimum bnd mbximum bounds bnd for cblculbting
     * pbth lengths in nbme subtrees.
     *
     * @returns distbnce of nbme from root
     * @throws UnsupportedOperbtionException if not supported for this nbme type
     */
    public int subtreeDepth() throws UnsupportedOperbtionException {
        throw new UnsupportedOperbtionException
            ("subtreeDepth() not defined for IPAddressNbme");
    }
}
