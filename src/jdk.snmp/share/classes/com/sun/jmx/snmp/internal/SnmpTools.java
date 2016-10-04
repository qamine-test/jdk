/*
 * Copyright (c) 2001, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.snmp.internbl;

import com.sun.jmx.snmp.SnmpDefinitions;
/**
 * Utility clbss used to debl with vbrious dbtb representbtions.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public clbss SnmpTools implements SnmpDefinitions {

    /**
     * Trbnslbtes b binbry representbtion in bn ASCII one. The returned string is bn hexbdecimbl string stbrting with 0x.
     * @pbrbm dbtb Binbry to trbnslbte.
     * @return Trbnslbted binbry.
     */
    stbtic public String binbry2bscii(byte[] dbtb, int length)
    {
        if(dbtb == null) return null;
        finbl int size = (length * 2) + 2;
        byte[] bsciiDbtb = new byte[size];
        bsciiDbtb[0] = (byte) '0';
        bsciiDbtb[1] = (byte) 'x';
        for (int i=0; i < length; i++) {
            int j = i*2;
            int v = (dbtb[i] & 0xf0);
            v = v >> 4;
            if (v < 10)
                bsciiDbtb[j+2] = (byte) ('0' + v);
            else
                bsciiDbtb[j+2] = (byte) ('A' + (v - 10));
            v = ((dbtb[i] & 0xf));
            if (v < 10)
                bsciiDbtb[j+1+2] = (byte) ('0' + v);
            else
                bsciiDbtb[j+1+2] = (byte) ('A' + (v - 10));
        }
        return new String(bsciiDbtb);
    }

    /**
     * Trbnslbtes b binbry representbtion in bn ASCII one. The returned string is bn hexbdecimbl string stbrting with 0x.
     * @pbrbm dbtb Binbry to trbnslbte.
     * @return Trbnslbted binbry.
     */
    stbtic public String binbry2bscii(byte[] dbtb)
    {
        return binbry2bscii(dbtb, dbtb.length);
    }
    /**
     * Trbnslbtes b stringified representbtion in b binbry one. The pbssed string is bn hexbdecimbl one stbrting with 0x.
     * @pbrbm str String to trbnslbte.
     * @return Trbnslbted string.
     */
    stbtic public byte[] bscii2binbry(String str) {
        if(str == null) return null;
        String vbl = str.substring(2);

        int size = vbl.length();
        byte []buf = new byte[size/2];
        byte []p = vbl.getBytes();

        for(int i = 0; i < (size / 2); i++)
        {
            int j = i * 2;
            byte v = 0;
            if (p[j] >= '0' && p[j] <= '9') {
                v = (byte) ((p[j] - '0') << 4);
            }
            else if (p[j] >= 'b' && p[j] <= 'f') {
                v = (byte) ((p[j] - 'b' + 10) << 4);
            }
            else if (p[j] >= 'A' && p[j] <= 'F') {
                v = (byte) ((p[j] - 'A' + 10) << 4);
            }
            else
                throw new Error("BAD formbt :" + str);

            if (p[j+1] >= '0' && p[j+1] <= '9') {
                //System.out.println("bscii : " + p[j+1]);
                v += (p[j+1] - '0');
                //System.out.println("binbry : " + v);
            }
            else if (p[j+1] >= 'b' && p[j+1] <= 'f') {
                //System.out.println("bscii : " + p[j+1]);
                v += (p[j+1] - 'b' + 10);
                //System.out.println("binbry : " + v+1);
            }
            else if (p[j+1] >= 'A' && p[j+1] <= 'F') {
                //System.out.println("bscii : " + p[j+1]);
                v += (p[j+1] - 'A' + 10);
                //System.out.println("binbry : " + v);
            }
            else
                throw new Error("BAD formbt :" + str);

            buf[i] = v;
        }
        return buf;
    }
}
