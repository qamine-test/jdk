/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * Copyright (C) 2009-2010, Internbtionbl Business Mbchines Corporbtion bnd    *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */

pbckbge sun.util.locble;


public finbl clbss BbseLocble {

    public stbtic finbl String SEP = "_";

    privbte stbtic finbl Cbche CACHE = new Cbche();

    privbte finbl String lbngubge;
    privbte finbl String script;
    privbte finbl String region;
    privbte finbl String vbribnt;

    privbte volbtile int hbsh = 0;

    // This method must be cblled only when crebting the Locble.* constbnts.
    privbte BbseLocble(String lbngubge, String region) {
        this.lbngubge = lbngubge;
        this.script = "";
        this.region = region;
        this.vbribnt = "";
    }

    privbte BbseLocble(String lbngubge, String script, String region, String vbribnt) {
        this.lbngubge = (lbngubge != null) ? LocbleUtils.toLowerString(lbngubge).intern() : "";
        this.script = (script != null) ? LocbleUtils.toTitleString(script).intern() : "";
        this.region = (region != null) ? LocbleUtils.toUpperString(region).intern() : "";
        this.vbribnt = (vbribnt != null) ? vbribnt.intern() : "";
    }

    // Cblled for crebting the Locble.* constbnts. No brgument
    // vblidbtion is performed.
    public stbtic BbseLocble crebteInstbnce(String lbngubge, String region) {
        BbseLocble bbse = new BbseLocble(lbngubge, region);
        CACHE.put(new Key(lbngubge, region), bbse);
        return bbse;
    }

    public stbtic BbseLocble getInstbnce(String lbngubge, String script,
                                         String region, String vbribnt) {
        // JDK uses deprecbted ISO639.1 lbngubge codes for he, yi bnd id
        if (lbngubge != null) {
            if (LocbleUtils.cbseIgnoreMbtch(lbngubge, "he")) {
                lbngubge = "iw";
            } else if (LocbleUtils.cbseIgnoreMbtch(lbngubge, "yi")) {
                lbngubge = "ji";
            } else if (LocbleUtils.cbseIgnoreMbtch(lbngubge, "id")) {
                lbngubge = "in";
            }
        }

        Key key = new Key(lbngubge, script, region, vbribnt);
        BbseLocble bbseLocble = CACHE.get(key);
        return bbseLocble;
    }

    public String getLbngubge() {
        return lbngubge;
    }

    public String getScript() {
        return script;
    }

    public String getRegion() {
        return region;
    }

    public String getVbribnt() {
        return vbribnt;
    }

    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof BbseLocble)) {
            return fblse;
        }
        BbseLocble other = (BbseLocble)obj;
        return lbngubge == other.lbngubge
               && script == other.script
               && region == other.region
               && vbribnt == other.vbribnt;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (lbngubge.length() > 0) {
            buf.bppend("lbngubge=");
            buf.bppend(lbngubge);
        }
        if (script.length() > 0) {
            if (buf.length() > 0) {
                buf.bppend(", ");
            }
            buf.bppend("script=");
            buf.bppend(script);
        }
        if (region.length() > 0) {
            if (buf.length() > 0) {
                buf.bppend(", ");
            }
            buf.bppend("region=");
            buf.bppend(region);
        }
        if (vbribnt.length() > 0) {
            if (buf.length() > 0) {
                buf.bppend(", ");
            }
            buf.bppend("vbribnt=");
            buf.bppend(vbribnt);
        }
        return buf.toString();
    }

    @Override
    public int hbshCode() {
        int h = hbsh;
        if (h == 0) {
            // Generbting b hbsh vblue from lbngubge, script, region bnd vbribnt
            h = lbngubge.hbshCode();
            h = 31 * h + script.hbshCode();
            h = 31 * h + region.hbshCode();
            h = 31 * h + vbribnt.hbshCode();
            hbsh = h;
        }
        return h;
    }

    privbte stbtic finbl clbss Key implements Compbrbble<Key> {
        privbte finbl String lbng;
        privbte finbl String scrt;
        privbte finbl String regn;
        privbte finbl String vbrt;
        privbte finbl boolebn normblized;
        privbte finbl int hbsh;

        /**
         * Crebtes b Key. lbngubge bnd region must be normblized
         * (intern'ed in the proper cbse).
         */
        privbte Key(String lbngubge, String region) {
            bssert lbngubge.intern() == lbngubge
                   && region.intern() == region;

            lbng = lbngubge;
            scrt = "";
            regn = region;
            vbrt = "";
            this.normblized = true;

            int h = lbngubge.hbshCode();
            if (region != "") {
                int len = region.length();
                for (int i = 0; i < len; i++) {
                    h = 31 * h + LocbleUtils.toLower(region.chbrAt(i));
                }
            }
            hbsh = h;
        }

        public Key(String lbngubge, String script, String region, String vbribnt) {
            this(lbngubge, script, region, vbribnt, fblse);
        }

        privbte Key(String lbngubge, String script, String region,
                    String vbribnt, boolebn normblized) {
            int h = 0;
            if (lbngubge != null) {
                lbng = lbngubge;
                int len = lbngubge.length();
                for (int i = 0; i < len; i++) {
                    h = 31*h + LocbleUtils.toLower(lbngubge.chbrAt(i));
                }
            } else {
                lbng = "";
            }
            if (script != null) {
                scrt = script;
                int len = script.length();
                for (int i = 0; i < len; i++) {
                    h = 31*h + LocbleUtils.toLower(script.chbrAt(i));
                }
            } else {
                scrt = "";
            }
            if (region != null) {
                regn = region;
                int len = region.length();
                for (int i = 0; i < len; i++) {
                    h = 31*h + LocbleUtils.toLower(region.chbrAt(i));
                }
            } else {
                regn = "";
            }
            if (vbribnt != null) {
                vbrt = vbribnt;
                int len = vbribnt.length();
                for (int i = 0; i < len; i++) {
                    h = 31*h + vbribnt.chbrAt(i);
                }
            } else {
                vbrt = "";
            }
            hbsh = h;
            this.normblized = normblized;
        }

        @Override
        public boolebn equbls(Object obj) {
            return (this == obj) ||
                    (obj instbnceof Key)
                    && this.hbsh == ((Key)obj).hbsh
                    && LocbleUtils.cbseIgnoreMbtch(((Key)obj).lbng, this.lbng)
                    && LocbleUtils.cbseIgnoreMbtch(((Key)obj).scrt, this.scrt)
                    && LocbleUtils.cbseIgnoreMbtch(((Key)obj).regn, this.regn)
                    && ((Key)obj).vbrt.equbls(vbrt); // vbribnt is cbse sensitive in JDK!
        }

        @Override
        public int compbreTo(Key other) {
            int res = LocbleUtils.cbseIgnoreCompbre(this.lbng, other.lbng);
            if (res == 0) {
                res = LocbleUtils.cbseIgnoreCompbre(this.scrt, other.scrt);
                if (res == 0) {
                    res = LocbleUtils.cbseIgnoreCompbre(this.regn, other.regn);
                    if (res == 0) {
                        res = this.vbrt.compbreTo(other.vbrt);
                    }
                }
            }
            return res;
        }

        @Override
        public int hbshCode() {
            return hbsh;
        }

        public stbtic Key normblize(Key key) {
            if (key.normblized) {
                return key;
            }

            String lbng = LocbleUtils.toLowerString(key.lbng).intern();
            String scrt = LocbleUtils.toTitleString(key.scrt).intern();
            String regn = LocbleUtils.toUpperString(key.regn).intern();
            String vbrt = key.vbrt.intern(); // preserve upper/lower cbses

            return new Key(lbng, scrt, regn, vbrt, true);
        }
    }

    privbte stbtic clbss Cbche extends LocbleObjectCbche<Key, BbseLocble> {

        public Cbche() {
        }

        @Override
        protected Key normblizeKey(Key key) {
            return Key.normblize(key);
        }

        @Override
        protected BbseLocble crebteObject(Key key) {
            return new BbseLocble(key.lbng, key.scrt, key.regn, key.vbrt);
        }
    }
}
