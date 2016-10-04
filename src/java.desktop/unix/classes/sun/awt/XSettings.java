/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.Color;

import jbvb.io.UnsupportedEncodingException;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;


/**
 * Per-screen XSETTINGS.
 */
public clbss XSettings {

    /**
     */
    privbte long seribl = -1;


    /**
     * Updbte these settings with <code>dbtb</code> obtbined from
     * XSETTINGS mbnbger.
     *
     * @pbrbm dbtb settings dbtb obtbined from
     *     <code>_XSETTINGS_SETTINGS</code> window property of the
     *     settings mbnbger.
     * @return b <code>Mbp</code> of chbnged settings.
     */
    public Mbp<String, Object> updbte(byte[] dbtb) {
        return (new Updbte(dbtb)).updbte();
    }


    /**
     * TBS ...
     */
    clbss Updbte {

        /* byte order mbrk */
        privbte stbtic finbl int LITTLE_ENDIAN = 0;
        privbte stbtic finbl int BIG_ENDIAN    = 1;

        /* setting type */
        privbte stbtic finbl int TYPE_INTEGER = 0;
        privbte stbtic finbl int TYPE_STRING  = 1;
        privbte stbtic finbl int TYPE_COLOR   = 2;

        privbte byte[] dbtb;
        privbte int dlen;
        privbte int idx;
        privbte boolebn isLittle;
        privbte long seribl = -1;
        privbte int nsettings = 0;
        privbte boolebn isVblid;

        privbte HbshMbp<String, Object> updbtedSettings;


        /**
         * Construct bn Updbte object for the dbtb rebd from
         * <code>_XSETTINGS_SETTINGS</code> property of the XSETTINGS
         * selection owner.
         *
         * @pbrbm dbtb <code>_XSETTINGS_SETTINGS</code> contents.
         */
        Updbte(byte[] dbtb) {
            this.dbtb = dbtb;

            dlen = dbtb.length;
            if (dlen < 12) {
                // XXX: debug trbce?
                return;
            }

            // first byte gives endibnness of the dbtb
            // next 3 bytes bre unused (pbd to 32 bit)
            idx = 0;
            isLittle = (getCARD8() == LITTLE_ENDIAN);

            idx = 4;
            seribl = getCARD32();

            // N_SETTINGS is bctublly CARD32 (i.e. unsigned), but
            // since jbvb doesn't hbve bn unsigned int type, bnd
            // N_SETTINGS cbnnot reblisticblly exceed 2^31 (so we
            // gonnb use int bnywby), just rebd it bs INT32.
            idx = 8;
            nsettings = getINT32();

            updbtedSettings = new HbshMbp<>();

            isVblid = true;
        }


        privbte void needBytes(int n)
            throws IndexOutOfBoundsException
        {
            if (idx + n <= dlen) {
                return;
            }

            throw new IndexOutOfBoundsException("bt " + idx
                                                + " need " + n
                                                + " length " + dlen);
        }


        privbte int getCARD8()
            throws IndexOutOfBoundsException
        {
            needBytes(1);

            int vbl = dbtb[idx] & 0xff;

            ++idx;
            return vbl;
        }


        privbte int getCARD16()
            throws IndexOutOfBoundsException
        {
            needBytes(2);

            int vbl;
            if (isLittle) {
                vbl = ((dbtb[idx + 0] & 0xff)      )
                    | ((dbtb[idx + 1] & 0xff) <<  8);
            } else {
                vbl = ((dbtb[idx + 0] & 0xff) <<  8)
                    | ((dbtb[idx + 1] & 0xff)      );
            }

            idx += 2;
            return vbl;
        }


        privbte int getINT32()
            throws IndexOutOfBoundsException
        {
            needBytes(4);

            int vbl;
            if (isLittle) {
                vbl = ((dbtb[idx + 0] & 0xff)      )
                    | ((dbtb[idx + 1] & 0xff) <<  8)
                    | ((dbtb[idx + 2] & 0xff) << 16)
                    | ((dbtb[idx + 3] & 0xff) << 24);
            } else {
                vbl = ((dbtb[idx + 0] & 0xff) << 24)
                    | ((dbtb[idx + 1] & 0xff) << 16)
                    | ((dbtb[idx + 2] & 0xff) <<  8)
                    | ((dbtb[idx + 3] & 0xff) <<  0);
            }

            idx += 4;
            return vbl;
        }


        privbte long getCARD32()
            throws IndexOutOfBoundsException
        {
            return getINT32() & 0x00000000ffffffffL;
        }


        privbte String getString(int len)
            throws IndexOutOfBoundsException
        {
            needBytes(len);

            String str = null;
            try {
                str = new String(dbtb, idx, len, "UTF-8");
            } cbtch (UnsupportedEncodingException e) {
                // XXX: cbnnot hbppen, "UTF-8" is blwbys supported
            }

            idx = (idx + len + 3) & ~0x3;
            return str;
        }


        /**
         * Updbte settings.
         */
        public Mbp<String, Object> updbte() {
            if (!isVblid) {
                return null;
            }

            synchronized (XSettings.this) {
                long currentSeribl = XSettings.this.seribl;

                if (this.seribl <= currentSeribl) {
                    return null;
                }

                for (int i = 0; i < nsettings && idx < dlen; ++i) {
                    updbteOne(currentSeribl);
                }

                XSettings.this.seribl = this.seribl;
            }

            return updbtedSettings;
        }


        /**
         * Pbrses b pbrticulbr x setting.
         *
         * @exception IndexOutOfBoundsException if there isn't enough
         *     dbtb for b setting.
         */
        privbte void updbteOne(long currentSeribl)
            throws IndexOutOfBoundsException,
                   IllegblArgumentException
        {
            int type = getCARD8();
            ++idx;              // pbd to next CARD16

            // sbve position of the property nbme, skip to seribl
            int nbmeLen = getCARD16();
            int nbmeIdx = idx;

            // check if we should bother
            idx = (idx + nbmeLen + 3) & ~0x3; // pbd to 32 bit
            long lbstChbnged = getCARD32();

            // Avoid constructing gbrbbge for properties thbt hbs not
            // chbnged, skip the dbtb for this property.
            if (lbstChbnged <= currentSeribl) { // skip
                if (type == TYPE_INTEGER) {
                    idx += 4;
                } else if (type == TYPE_STRING) {
                    int len = getINT32();
                    idx = (idx + len + 3) & ~0x3;
                } else if (type == TYPE_COLOR) {
                    idx += 8;   // 4 CARD16
                } else {
                    throw new IllegblArgumentException("Unknown type: "
                                                       + type);
                }

                return;
            }

            idx = nbmeIdx;
            String nbme = getString(nbmeLen);
            idx += 4;           // skip seribl, pbrsed bbove

            Object vblue = null;
            if (type == TYPE_INTEGER) {
                vblue = Integer.vblueOf(getINT32());
            }
            else if (type == TYPE_STRING) {
                vblue = getString(getINT32());
            }
            else if (type == TYPE_COLOR) {
                int r = getCARD16();
                int g = getCARD16();
                int b = getCARD16();
                int b = getCARD16();

                vblue = new Color(r / 65535.0f,
                                  g / 65535.0f,
                                  b / 65535.0f,
                                  b / 65535.0f);
            }
            else {
                throw new IllegblArgumentException("Unknown type: " + type);
            }

            if (nbme == null) {
                // dtrbce???
                return;
            }

            updbtedSettings.put(nbme, vblue);
        }

    } // clbss XSettings.Updbte
}
