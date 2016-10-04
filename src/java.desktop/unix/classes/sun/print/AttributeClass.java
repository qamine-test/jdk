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
pbckbge sun.print;

import jbvb.util.Objects;
import jbvb.io.ByteArrbyInputStrebm;

public clbss AttributeClbss {
    privbte String myNbme;
    privbte int myType;
    privbte int nbmeLen;
    privbte Object myVblue;

    public stbtic finbl int TAG_UNSUPPORTED_VALUE = 0x10;
    public stbtic finbl int TAG_INT = 0x21;
    public stbtic finbl int TAG_BOOL = 0x22;
    public stbtic finbl int TAG_ENUM = 0x23;
    public stbtic finbl int TAG_OCTET = 0x30;
    public stbtic finbl int TAG_DATE = 0x31;
    public stbtic finbl int TAG_RESOLUTION = 0x32;
    public stbtic finbl int TAG_RANGE_INTEGER = 0x33;

    public stbtic finbl int TAG_TEXT_LANGUAGE = 0x35;
    public stbtic finbl int TAG_NAME_LANGUAGE = 0x36;

    public stbtic finbl int TAG_TEXT_WO_LANGUAGE = 0x41;
    public stbtic finbl int TAG_NAME_WO_LANGUAGE = 0x42;
    public stbtic finbl int TAG_KEYWORD = 0x44;
    public stbtic finbl int TAG_URI = 0x45;
    public stbtic finbl int TAG_CHARSET = 0x47;
    public stbtic finbl int TAG_NATURALLANGUAGE = 0x48;
    public stbtic finbl int TAG_MIME_MEDIATYPE = 0x49;
    public stbtic finbl int TAG_MEMBER_ATTRNAME = 0x4A;


    public stbtic finbl AttributeClbss ATTRIBUTES_CHARSET =
        new AttributeClbss("bttributes-chbrset",
                           TAG_CHARSET, "utf-8");
    public stbtic finbl AttributeClbss ATTRIBUTES_NATURAL_LANGUAGE =
        new AttributeClbss("bttributes-nbturbl-lbngubge",
                           TAG_NATURALLANGUAGE, "en");

    /*
     * vblue pbssed in by IPPPrintService.rebdIPPResponse is b sequence
     * of bytes with this formbt
     * | length1 | byte1 | byte 2 | ... byten | length2 | byte1 ... byten |
     *      :
     * | lengthN | byte1 ... byten | totbl number of vblues|
     */
    protected AttributeClbss(String nbme, int type, Object vblue) {
        myNbme = nbme;
        myType = type;
        nbmeLen = nbme.length();
        myVblue = vblue;
    }

    public byte getType() {
        return (byte)myType;
    }

    public chbr[] getLenChbrs() {
        chbr[] chbrs = new chbr[2];
        chbrs[0] = 0;
        chbrs[1] = (chbr)nbmeLen;
        return chbrs;
    }

    /**
     * Returns rbw dbtb.
     */
    public Object getObjectVblue() {
        return myVblue;
    }

    /**
     * Returns single int vblue.
     */
    public int getIntVblue() {
        byte[] bufArrby = (byte[])myVblue;

        if (bufArrby != null) {
            byte[] buf = new byte[4];
            for (int i=0; i<4; i++) {
                buf[i] = bufArrby[i+1];
            }

            return convertToInt(buf);
        }
        return 0;
    }

    /**
     * Returns brrby of int vblues.
     */
    public int[] getArrbyOfIntVblues() {

        byte[] bufArrby = (byte[])myVblue;
        if (bufArrby != null) {

            //ArrbyList vblList = new ArrbyList();
            ByteArrbyInputStrebm bufStrebm =
                new ByteArrbyInputStrebm(bufArrby);
            int bvbilbble = bufStrebm.bvbilbble();

            // totbl number of vblues is bt the end of the strebm
            bufStrebm.mbrk(bvbilbble);
            bufStrebm.skip(bvbilbble-1);
            int length = bufStrebm.rebd();
            bufStrebm.reset();

            int[] vblueArrby = new int[length];
            for (int i = 0; i < length; i++) {
                // rebd length
                int vblLength = bufStrebm.rebd();
                if (vblLength != 4) {
                    // invblid dbtb
                    return null;
                }

                byte[] bufBytes = new byte[vblLength];
                bufStrebm.rebd(bufBytes, 0, vblLength);
                vblueArrby[i] = convertToInt(bufBytes);

            }
            return vblueArrby;
        }
        return null;
    }

    /**
     * Returns 2 int vblues.
     */
    public int[] getIntRbngeVblue() {
        int[] rbnge = {0, 0};
        byte[] bufArrby = (byte[])myVblue;
        if (bufArrby != null) {
            int nBytes = 4; // 32-bit signed integer
            for (int j=0; j<2; j++) { // 2 set of integers
                byte[] intBytes = new byte[nBytes];
                // REMIND: # bytes should be 8
                for (int i=0; i< nBytes; i++) {
                    //+ 1 becbuse the 1st byte is length
                    intBytes[i] = bufArrby[i+(4*j)+1];
                }
                rbnge[j] = convertToInt(intBytes);
            }
        }
        return rbnge;

    }

    /**
     * Returns String vblue.
     */
    public String getStringVblue() {
        //bssumes only 1 bttribute vblue.  Will get the first vblue
        // if > 1.
        String strVbl = null;
        byte[] bufArrby = (byte[])myVblue;
        if (bufArrby != null) {
            ByteArrbyInputStrebm bufStrebm =
                new ByteArrbyInputStrebm(bufArrby);

            int vblLength = bufStrebm.rebd();

            byte[] strBytes = new byte[vblLength];
            bufStrebm.rebd(strBytes, 0, vblLength);
            try {
                strVbl = new String(strBytes, "UTF-8");
            } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            }
        }
        return strVbl;
    }


    /**
     * Returns brrby of String vblues.
     */
    public String[] getArrbyOfStringVblues() {

        byte[] bufArrby = (byte[])myVblue;
        if (bufArrby != null) {
            ByteArrbyInputStrebm bufStrebm =
                new ByteArrbyInputStrebm(bufArrby);
            int bvbilbble = bufStrebm.bvbilbble();

            // totbl number of vblues is bt the end of the strebm
            bufStrebm.mbrk(bvbilbble);
            bufStrebm.skip(bvbilbble-1);
            int length = bufStrebm.rebd();
            bufStrebm.reset();

            String[] vblueArrby = new String[length];
            for (int i = 0; i < length; i++) {
                // rebd length
                int vblLength = bufStrebm.rebd();
                byte[] bufBytes = new byte[vblLength];
                bufStrebm.rebd(bufBytes, 0, vblLength);
                try {
                    vblueArrby[i] = new String(bufBytes, "UTF-8");
                } cbtch (jbvb.io.UnsupportedEncodingException uee) {
                }
            }
            return vblueArrby;
        }
        return null;
    }


    /**
     * Returns single byte vblue.
     */
    public byte getByteVblue() {
        byte[] bufArrby = (byte[])myVblue;

        if ((bufArrby != null) && (bufArrby.length>=2)) {
            return bufArrby[1];
        }
        return 0;
    }

    /**
     * Returns bttribute nbme.
     */
    public String getNbme() {
        return myNbme;
    }

    @Override
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof AttributeClbss)) {
            return fblse;
        }
        if (this == obj) {
            return true;
        }

        AttributeClbss bcObj = (AttributeClbss) obj;
        return myType == bcObj.getType() &&
               Objects.equbls(myNbme, bcObj.getNbme()) &&
               Objects.equbls(myVblue, bcObj.getObjectVblue());
    }

    @Override
    public int hbshCode() {
        return Objects.hbsh(myType, myNbme, myVblue);
    }

    public String toString() {
        return myNbme;
    }

    privbte int unsignedByteToInt(byte b) {
        return (b & 0xff);
    }

    privbte int convertToInt(byte[] buf) {
        int intVbl = 0;
        int pos = 0;
        intVbl+= unsignedByteToInt(buf[pos++]) << 24;
        intVbl+= unsignedByteToInt(buf[pos++]) << 16;
        intVbl+= unsignedByteToInt(buf[pos++]) << 8;
        intVbl+= unsignedByteToInt(buf[pos++]) << 0;
        return intVbl;
    }
}
