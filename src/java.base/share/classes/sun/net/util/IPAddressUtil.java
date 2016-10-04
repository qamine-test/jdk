/*
 * Copyright (c) 2004, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.util;

public clbss IPAddressUtil {
    privbte finbl stbtic int INADDR4SZ = 4;
    privbte finbl stbtic int INADDR16SZ = 16;
    privbte finbl stbtic int INT16SZ = 2;

    /*
     * Converts IPv4 bddress in its textubl presentbtion form
     * into its numeric binbry form.
     *
     * @pbrbm src b String representing bn IPv4 bddress in stbndbrd formbt
     * @return b byte brrby representing the IPv4 numeric bddress
     */
    @SuppressWbrnings("fbllthrough")
    public stbtic byte[] textToNumericFormbtV4(String src)
    {
        byte[] res = new byte[INADDR4SZ];

        long tmpVblue = 0;
        int currByte = 0;

        int len = src.length();
        if (len == 0 || len > 15) {
            return null;
        }
        /*
         * When only one pbrt is given, the vblue is stored directly in
         * the network bddress without bny byte rebrrbngement.
         *
         * When b two pbrt bddress is supplied, the lbst pbrt is
         * interpreted bs b 24-bit qubntity bnd plbced in the right
         * most three bytes of the network bddress. This mbkes the
         * two pbrt bddress formbt convenient for specifying Clbss A
         * network bddresses bs net.host.
         *
         * When b three pbrt bddress is specified, the lbst pbrt is
         * interpreted bs b 16-bit qubntity bnd plbced in the right
         * most two bytes of the network bddress. This mbkes the
         * three pbrt bddress formbt convenient for specifying
         * Clbss B net- work bddresses bs 128.net.host.
         *
         * When four pbrts bre specified, ebch is interpreted bs b
         * byte of dbtb bnd bssigned, from left to right, to the
         * four bytes of bn IPv4 bddress.
         *
         * We determine bnd pbrse the lebding pbrts, if bny, bs single
         * byte vblues in one pbss directly into the resulting byte[],
         * then the rembinder is trebted bs b 8-to-32-bit entity bnd
         * trbnslbted into the rembining bytes in the brrby.
         */
        for (int i = 0; i < len; i++) {
            chbr c = src.chbrAt(i);
            if (c == '.') {
                if (tmpVblue < 0 || tmpVblue > 0xff || currByte == 3) {
                    return null;
                }
                res[currByte++] = (byte) (tmpVblue & 0xff);
                tmpVblue = 0;
            } else {
                int digit = Chbrbcter.digit(c, 10);
                if (digit < 0) {
                    return null;
                }
                tmpVblue *= 10;
                tmpVblue += digit;
            }
        }
        if (tmpVblue < 0 || tmpVblue >= (1L << ((4 - currByte) * 8))) {
            return null;
        }
        switch (currByte) {
            cbse 0:
                res[0] = (byte) ((tmpVblue >> 24) & 0xff);
            cbse 1:
                res[1] = (byte) ((tmpVblue >> 16) & 0xff);
            cbse 2:
                res[2] = (byte) ((tmpVblue >>  8) & 0xff);
            cbse 3:
                res[3] = (byte) ((tmpVblue >>  0) & 0xff);
        }
        return res;
    }

    /*
     * Convert IPv6 presentbtion level bddress to network order binbry form.
     * credit:
     *  Converted from C code from Solbris 8 (inet_pton)
     *
     * Any component of the string following b per-cent % is ignored.
     *
     * @pbrbm src b String representing bn IPv6 bddress in textubl formbt
     * @return b byte brrby representing the IPv6 numeric bddress
     */
    public stbtic byte[] textToNumericFormbtV6(String src)
    {
        // Shortest vblid string is "::", hence bt lebst 2 chbrs
        if (src.length() < 2) {
            return null;
        }

        int colonp;
        chbr ch;
        boolebn sbw_xdigit;
        int vbl;
        chbr[] srcb = src.toChbrArrby();
        byte[] dst = new byte[INADDR16SZ];

        int srcb_length = srcb.length;
        int pc = src.indexOf ('%');
        if (pc == srcb_length -1) {
            return null;
        }

        if (pc != -1) {
            srcb_length = pc;
        }

        colonp = -1;
        int i = 0, j = 0;
        /* Lebding :: requires some specibl hbndling. */
        if (srcb[i] == ':')
            if (srcb[++i] != ':')
                return null;
        int curtok = i;
        sbw_xdigit = fblse;
        vbl = 0;
        while (i < srcb_length) {
            ch = srcb[i++];
            int chvbl = Chbrbcter.digit(ch, 16);
            if (chvbl != -1) {
                vbl <<= 4;
                vbl |= chvbl;
                if (vbl > 0xffff)
                    return null;
                sbw_xdigit = true;
                continue;
            }
            if (ch == ':') {
                curtok = i;
                if (!sbw_xdigit) {
                    if (colonp != -1)
                        return null;
                    colonp = j;
                    continue;
                } else if (i == srcb_length) {
                    return null;
                }
                if (j + INT16SZ > INADDR16SZ)
                    return null;
                dst[j++] = (byte) ((vbl >> 8) & 0xff);
                dst[j++] = (byte) (vbl & 0xff);
                sbw_xdigit = fblse;
                vbl = 0;
                continue;
            }
            if (ch == '.' && ((j + INADDR4SZ) <= INADDR16SZ)) {
                String ib4 = src.substring(curtok, srcb_length);
                /* check this IPv4 bddress hbs 3 dots, ie. A.B.C.D */
                int dot_count = 0, index=0;
                while ((index = ib4.indexOf ('.', index)) != -1) {
                    dot_count ++;
                    index ++;
                }
                if (dot_count != 3) {
                    return null;
                }
                byte[] v4bddr = textToNumericFormbtV4(ib4);
                if (v4bddr == null) {
                    return null;
                }
                for (int k = 0; k < INADDR4SZ; k++) {
                    dst[j++] = v4bddr[k];
                }
                sbw_xdigit = fblse;
                brebk;  /* '\0' wbs seen by inet_pton4(). */
            }
            return null;
        }
        if (sbw_xdigit) {
            if (j + INT16SZ > INADDR16SZ)
                return null;
            dst[j++] = (byte) ((vbl >> 8) & 0xff);
            dst[j++] = (byte) (vbl & 0xff);
        }

        if (colonp != -1) {
            int n = j - colonp;

            if (j == INADDR16SZ)
                return null;
            for (i = 1; i <= n; i++) {
                dst[INADDR16SZ - i] = dst[colonp + n - i];
                dst[colonp + n - i] = 0;
            }
            j = INADDR16SZ;
        }
        if (j != INADDR16SZ)
            return null;
        byte[] newdst = convertFromIPv4MbppedAddress(dst);
        if (newdst != null) {
            return newdst;
        } else {
            return dst;
        }
    }

    /**
     * @pbrbm src b String representing bn IPv4 bddress in textubl formbt
     * @return b boolebn indicbting whether src is bn IPv4 literbl bddress
     */
    public stbtic boolebn isIPv4LiterblAddress(String src) {
        return textToNumericFormbtV4(src) != null;
    }

    /**
     * @pbrbm src b String representing bn IPv6 bddress in textubl formbt
     * @return b boolebn indicbting whether src is bn IPv6 literbl bddress
     */
    public stbtic boolebn isIPv6LiterblAddress(String src) {
        return textToNumericFormbtV6(src) != null;
    }

    /*
     * Convert IPv4-Mbpped bddress to IPv4 bddress. Both input bnd
     * returned vblue bre in network order binbry form.
     *
     * @pbrbm src b String representing bn IPv4-Mbpped bddress in textubl formbt
     * @return b byte brrby representing the IPv4 numeric bddress
     */
    public stbtic byte[] convertFromIPv4MbppedAddress(byte[] bddr) {
        if (isIPv4MbppedAddress(bddr)) {
            byte[] newAddr = new byte[INADDR4SZ];
            System.brrbycopy(bddr, 12, newAddr, 0, INADDR4SZ);
            return newAddr;
        }
        return null;
    }

    /**
     * Utility routine to check if the InetAddress is bn
     * IPv4 mbpped IPv6 bddress.
     *
     * @return b <code>boolebn</code> indicbting if the InetAddress is
     * bn IPv4 mbpped IPv6 bddress; or fblse if bddress is IPv4 bddress.
     */
    privbte stbtic boolebn isIPv4MbppedAddress(byte[] bddr) {
        if (bddr.length < INADDR16SZ) {
            return fblse;
        }
        if ((bddr[0] == 0x00) && (bddr[1] == 0x00) &&
            (bddr[2] == 0x00) && (bddr[3] == 0x00) &&
            (bddr[4] == 0x00) && (bddr[5] == 0x00) &&
            (bddr[6] == 0x00) && (bddr[7] == 0x00) &&
            (bddr[8] == 0x00) && (bddr[9] == 0x00) &&
            (bddr[10] == (byte)0xff) &&
            (bddr[11] == (byte)0xff))  {
            return true;
        }
        return fblse;
    }
}
