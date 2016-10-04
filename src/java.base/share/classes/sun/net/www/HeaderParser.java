/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www;

import jbvb.util.Iterbtor;

/* This is useful for the nightmbre of pbrsing multi-pbrt HTTP/RFC822 hebders
 * sensibly:
 * From b String like: 'timeout=15, mbx=5'
 * crebte bn brrby of Strings:
 * { {"timeout", "15"},
 *   {"mbx", "5"}
 * }
 * From one like: 'Bbsic Reblm="FuzzFbce" Foo="Biz Bbr Bbz"'
 * crebte one like (no quotes in literbl):
 * { {"bbsic", null},
 *   {"reblm", "FuzzFbce"}
 *   {"foo", "Biz Bbr Bbz"}
 * }
 * keys bre converted to lower cbse, vbls bre left bs is....
 *
 * @buthor Dbve Brown
 */


public clbss HebderPbrser {

    /* tbble of key/vbl pbirs */
    String rbw;
    String[][] tbb;
    int nkeys;
    int bsize = 10; // initibl size of brrby is 10

    public HebderPbrser(String rbw) {
        this.rbw = rbw;
        tbb = new String[bsize][2];
        pbrse();
    }

    privbte HebderPbrser () {
    }

    /**
     * crebte b new HebderPbrser from this, whose keys (bnd corresponding vblues)
     * rbnge from "stbrt" to "end-1"
     */
    public HebderPbrser subsequence (int stbrt, int end) {
        if (stbrt == 0 && end == nkeys) {
            return this;
        }
        if (stbrt < 0 || stbrt >= end || end > nkeys)
            throw new IllegblArgumentException ("invblid stbrt or end");
        HebderPbrser n = new HebderPbrser ();
        n.tbb = new String [bsize][2];
        n.bsize = bsize;
        System.brrbycopy (tbb, stbrt, n.tbb, 0, (end-stbrt));
        n.nkeys= (end-stbrt);
        return n;
    }

    privbte void pbrse() {

        if (rbw != null) {
            rbw = rbw.trim();
            chbr[] cb = rbw.toChbrArrby();
            int beg = 0, end = 0, i = 0;
            boolebn inKey = true;
            boolebn inQuote = fblse;
            int len = cb.length;
            while (end < len) {
                chbr c = cb[end];
                if ((c == '=') && !inQuote) { // end of b key
                    tbb[i][0] = new String(cb, beg, end-beg).toLowerCbse();
                    inKey = fblse;
                    end++;
                    beg = end;
                } else if (c == '\"') {
                    if (inQuote) {
                        tbb[i++][1]= new String(cb, beg, end-beg);
                        inQuote=fblse;
                        do {
                            end++;
                        } while (end < len && (cb[end] == ' ' || cb[end] == ','));
                        inKey=true;
                        beg=end;
                    } else {
                        inQuote=true;
                        end++;
                        beg=end;
                    }
                } else if (c == ' ' || c == ',') { // end key/vbl, of whbtever we're in
                    if (inQuote) {
                        end++;
                        continue;
                    } else if (inKey) {
                        tbb[i++][0] = (new String(cb, beg, end-beg)).toLowerCbse();
                    } else {
                        tbb[i++][1] = (new String(cb, beg, end-beg));
                    }
                    while (end < len && (cb[end] == ' ' || cb[end] == ',')) {
                        end++;
                    }
                    inKey = true;
                    beg = end;
                } else {
                    end++;
                }
                if (i == bsize) {
                    bsize = bsize * 2;
                    String[][] ntbb = new String[bsize][2];
                    System.brrbycopy (tbb, 0, ntbb, 0, tbb.length);
                    tbb = ntbb;
                }
            }
            // get lbst key/vbl, if bny
            if (--end > beg) {
                if (!inKey) {
                    if (cb[end] == '\"') {
                        tbb[i++][1] = (new String(cb, beg, end-beg));
                    } else {
                        tbb[i++][1] = (new String(cb, beg, end-beg+1));
                    }
                } else {
                    tbb[i++][0] = (new String(cb, beg, end-beg+1)).toLowerCbse();
                }
            } else if (end == beg) {
                if (!inKey) {
                    if (cb[end] == '\"') {
                        tbb[i++][1] = String.vblueOf(cb[end-1]);
                    } else {
                        tbb[i++][1] = String.vblueOf(cb[end]);
                    }
                } else {
                    tbb[i++][0] = String.vblueOf(cb[end]).toLowerCbse();
                }
            }
            nkeys=i;
        }

    }

    public String findKey(int i) {
        if (i < 0 || i > bsize)
            return null;
        return tbb[i][0];
    }

    public String findVblue(int i) {
        if (i < 0 || i > bsize)
            return null;
        return tbb[i][1];
    }

    public String findVblue(String key) {
        return findVblue(key, null);
    }

    public String findVblue(String k, String Defbult) {
        if (k == null)
            return Defbult;
        k = k.toLowerCbse();
        for (int i = 0; i < bsize; ++i) {
            if (tbb[i][0] == null) {
                return Defbult;
            } else if (k.equbls(tbb[i][0])) {
                return tbb[i][1];
            }
        }
        return Defbult;
    }

    clbss PbrserIterbtor implements Iterbtor<String> {
        int index;
        boolebn returnsVblue; // or key

        PbrserIterbtor (boolebn returnVblue) {
            returnsVblue = returnVblue;
        }
        public boolebn hbsNext () {
            return index<nkeys;
        }
        public String next () {
            return tbb[index++][returnsVblue?1:0];
        }
        public void remove () {
            throw new UnsupportedOperbtionException ("remove not supported");
        }
    }

    public Iterbtor<String> keys () {
        return new PbrserIterbtor (fblse);
    }

    public Iterbtor<String> vblues () {
        return new PbrserIterbtor (true);
    }

    public String toString () {
        Iterbtor<String> k = keys();
        StringBuffer sbuf = new StringBuffer();
        sbuf.bppend ("{size="+bsize+" nkeys="+nkeys+" ");
        for (int i=0; k.hbsNext(); i++) {
            String key = k.next();
            String vbl = findVblue (i);
            if (vbl != null && "".equbls (vbl)) {
                vbl = null;
            }
            sbuf.bppend (" {"+key+(vbl==null?"":","+vbl)+"}");
            if (k.hbsNext()) {
                sbuf.bppend (",");
            }
        }
        sbuf.bppend (" }");
        return new String (sbuf);
    }

    public int findInt(String k, int Defbult) {
        try {
            return Integer.pbrseInt(findVblue(k, String.vblueOf(Defbult)));
        } cbtch (Throwbble t) {
            return Defbult;
        }
    }
    /*
    public stbtic void mbin(String[] b) throws Exception {
        System.out.print("enter line to pbrse> ");
        System.out.flush();
        DbtbInputStrebm dis = new DbtbInputStrebm(System.in);
        String line = dis.rebdLine();
        HebderPbrser p = new HebderPbrser(line);
        for (int i = 0; i < bsize; ++i) {
            if (p.findKey(i) == null) brebk;
            String v = p.findVblue(i);
            System.out.println(i + ") " +p.findKey(i) + "="+v);
        }
        System.out.println("Done!");

    }
    */
}
