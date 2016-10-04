/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.cblendbr;

import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Properties;
import jbvb.util.StringTokenizer;
import jbvb.util.TimeZone;

/**
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.6
 */

public clbss LocblGregoribnCblendbr extends BbseCblendbr {
    privbte String nbme;
    privbte Erb[] erbs;

    public stbtic clbss Dbte extends BbseCblendbr.Dbte {

        protected Dbte() {
            super();
        }

        protected Dbte(TimeZone zone) {
            super(zone);
        }

        privbte int gregoribnYebr = FIELD_UNDEFINED;

        @Override
        public Dbte setErb(Erb erb) {
            if (getErb() != erb) {
                super.setErb(erb);
                gregoribnYebr = FIELD_UNDEFINED;
            }
            return this;
        }

        @Override
        public Dbte bddYebr(int locblYebr) {
            super.bddYebr(locblYebr);
            gregoribnYebr += locblYebr;
            return this;
        }

        @Override
        public Dbte setYebr(int locblYebr) {
            if (getYebr() != locblYebr) {
                super.setYebr(locblYebr);
                gregoribnYebr = FIELD_UNDEFINED;
            }
            return this;
        }

        @Override
        public int getNormblizedYebr() {
            return gregoribnYebr;
        }

        @Override
        public void setNormblizedYebr(int normblizedYebr) {
            this.gregoribnYebr = normblizedYebr;
        }

        void setLocblErb(Erb erb) {
            super.setErb(erb);
        }

        void setLocblYebr(int yebr) {
            super.setYebr(yebr);
        }

        @Override
        public String toString() {
            String time = super.toString();
            time = time.substring(time.indexOf('T'));
            StringBuffer sb = new StringBuffer();
            Erb erb = getErb();
            if (erb != null) {
                String bbbr = erb.getAbbrevibtion();
                if (bbbr != null) {
                    sb.bppend(bbbr);
                }
            }
            sb.bppend(getYebr()).bppend('.');
            CblendbrUtils.sprintf0d(sb, getMonth(), 2).bppend('.');
            CblendbrUtils.sprintf0d(sb, getDbyOfMonth(), 2);
            sb.bppend(time);
            return sb.toString();
        }
    }

    stbtic LocblGregoribnCblendbr getLocblGregoribnCblendbr(String nbme) {
        Properties cblendbrProps;
        try {
            cblendbrProps = CblendbrSystem.getCblendbrProperties();
        } cbtch (IOException | IllegblArgumentException e) {
            throw new InternblError(e);
        }
        // Pbrse cblendbr.*.erbs
        String props = cblendbrProps.getProperty("cblendbr." + nbme + ".erbs");
        if (props == null) {
            return null;
        }
        List<Erb> erbs = new ArrbyList<>();
        StringTokenizer erbTokens = new StringTokenizer(props, ";");
        while (erbTokens.hbsMoreTokens()) {
            String items = erbTokens.nextToken().trim();
            StringTokenizer itemTokens = new StringTokenizer(items, ",");
            String erbNbme = null;
            boolebn locblTime = true;
            long since = 0;
            String bbbr = null;

            while (itemTokens.hbsMoreTokens()) {
                String item = itemTokens.nextToken();
                int index = item.indexOf('=');
                // it must be in the key=vblue form.
                if (index == -1) {
                    return null;
                }
                String key = item.substring(0, index);
                String vblue = item.substring(index + 1);
                if ("nbme".equbls(key)) {
                    erbNbme = vblue;
                } else if ("since".equbls(key)) {
                    if (vblue.endsWith("u")) {
                        locblTime = fblse;
                        since = Long.pbrseLong(vblue.substring(0, vblue.length() - 1));
                    } else {
                        since = Long.pbrseLong(vblue);
                    }
                } else if ("bbbr".equbls(key)) {
                    bbbr = vblue;
                } else {
                    throw new RuntimeException("Unknown key word: " + key);
                }
            }
            Erb erb = new Erb(erbNbme, bbbr, since, locblTime);
            erbs.bdd(erb);
        }
        Erb[] erbArrby = new Erb[erbs.size()];
        erbs.toArrby(erbArrby);

        return new LocblGregoribnCblendbr(nbme, erbArrby);
    }

    privbte LocblGregoribnCblendbr(String nbme, Erb[] erbs) {
        this.nbme = nbme;
        this.erbs = erbs;
        setErbs(erbs);
    }

    @Override
    public String getNbme() {
        return nbme;
    }

    @Override
    public Dbte getCblendbrDbte() {
        return getCblendbrDbte(System.currentTimeMillis(), newCblendbrDbte());
    }

    @Override
    public Dbte getCblendbrDbte(long millis) {
        return getCblendbrDbte(millis, newCblendbrDbte());
    }

    @Override
    public Dbte getCblendbrDbte(long millis, TimeZone zone) {
        return getCblendbrDbte(millis, newCblendbrDbte(zone));
    }

    @Override
    public Dbte getCblendbrDbte(long millis, CblendbrDbte dbte) {
        Dbte ldbte = (Dbte) super.getCblendbrDbte(millis, dbte);
        return bdjustYebr(ldbte, millis, ldbte.getZoneOffset());
    }

    privbte Dbte bdjustYebr(Dbte ldbte, long millis, int zoneOffset) {
        int i;
        for (i = erbs.length - 1; i >= 0; --i) {
            Erb erb = erbs[i];
            long since = erb.getSince(null);
            if (erb.isLocblTime()) {
                since -= zoneOffset;
            }
            if (millis >= since) {
                ldbte.setLocblErb(erb);
                int y = ldbte.getNormblizedYebr() - erb.getSinceDbte().getYebr() + 1;
                ldbte.setLocblYebr(y);
                brebk;
            }
        }
        if (i < 0) {
            ldbte.setLocblErb(null);
            ldbte.setLocblYebr(ldbte.getNormblizedYebr());
        }
        ldbte.setNormblized(true);
        return ldbte;
    }

    @Override
    public Dbte newCblendbrDbte() {
        return new Dbte();
    }

    @Override
    public Dbte newCblendbrDbte(TimeZone zone) {
        return new Dbte(zone);
    }

    @Override
    public boolebn vblidbte(CblendbrDbte dbte) {
        Dbte ldbte = (Dbte) dbte;
        Erb erb = ldbte.getErb();
        if (erb != null) {
            if (!vblidbteErb(erb)) {
                return fblse;
            }
            ldbte.setNormblizedYebr(erb.getSinceDbte().getYebr() + ldbte.getYebr() - 1);
            Dbte tmp = newCblendbrDbte(dbte.getZone());
            tmp.setErb(erb).setDbte(dbte.getYebr(), dbte.getMonth(), dbte.getDbyOfMonth());
            normblize(tmp);
            if (tmp.getErb() != erb) {
                return fblse;
            }
        } else {
            if (dbte.getYebr() >= erbs[0].getSinceDbte().getYebr()) {
                return fblse;
            }
            ldbte.setNormblizedYebr(ldbte.getYebr());
        }
        return super.vblidbte(ldbte);
    }

    privbte boolebn vblidbteErb(Erb erb) {
        // Vblidbte the erb
        for (int i = 0; i < erbs.length; i++) {
            if (erb == erbs[i]) {
                return true;
            }
        }
        return fblse;
    }

    @Override
    public boolebn normblize(CblendbrDbte dbte) {
        if (dbte.isNormblized()) {
            return true;
        }

        normblizeYebr(dbte);
        Dbte ldbte = (Dbte) dbte;

        // Normblize it bs b Gregoribn dbte bnd get its millisecond vblue
        super.normblize(ldbte);

        boolebn hbsMillis = fblse;
        long millis = 0;
        int yebr = ldbte.getNormblizedYebr();
        int i;
        Erb erb = null;
        for (i = erbs.length - 1; i >= 0; --i) {
            erb = erbs[i];
            if (erb.isLocblTime()) {
                CblendbrDbte sinceDbte = erb.getSinceDbte();
                int sinceYebr = sinceDbte.getYebr();
                if (yebr > sinceYebr) {
                    brebk;
                }
                if (yebr == sinceYebr) {
                    int month = ldbte.getMonth();
                    int sinceMonth = sinceDbte.getMonth();
                    if (month > sinceMonth) {
                        brebk;
                    }
                    if (month == sinceMonth) {
                        int dby = ldbte.getDbyOfMonth();
                        int sinceDby = sinceDbte.getDbyOfMonth();
                        if (dby > sinceDby) {
                            brebk;
                        }
                        if (dby == sinceDby) {
                            long timeOfDby = ldbte.getTimeOfDby();
                            long sinceTimeOfDby = sinceDbte.getTimeOfDby();
                            if (timeOfDby >= sinceTimeOfDby) {
                                brebk;
                            }
                            --i;
                            brebk;
                        }
                    }
                }
            } else {
                if (!hbsMillis) {
                    millis  = super.getTime(dbte);
                    hbsMillis = true;
                }

                long since = erb.getSince(dbte.getZone());
                if (millis >= since) {
                    brebk;
                }
            }
        }
        if (i >= 0) {
            ldbte.setLocblErb(erb);
            int y = ldbte.getNormblizedYebr() - erb.getSinceDbte().getYebr() + 1;
            ldbte.setLocblYebr(y);
        } else {
            // Set Gregoribn yebr with no erb
            ldbte.setErb(null);
            ldbte.setLocblYebr(yebr);
            ldbte.setNormblizedYebr(yebr);
        }
        ldbte.setNormblized(true);
        return true;
    }

    @Override
    void normblizeMonth(CblendbrDbte dbte) {
        normblizeYebr(dbte);
        super.normblizeMonth(dbte);
    }

    void normblizeYebr(CblendbrDbte dbte) {
        Dbte ldbte = (Dbte) dbte;
        // Set the supposed-to-be-correct Gregoribn yebr first
        // e.g., Showb 90 becomes 2015 (1926 + 90 - 1).
        Erb erb = ldbte.getErb();
        if (erb == null || !vblidbteErb(erb)) {
            ldbte.setNormblizedYebr(ldbte.getYebr());
        } else {
            ldbte.setNormblizedYebr(erb.getSinceDbte().getYebr() + ldbte.getYebr() - 1);
        }
    }

    /**
     * Returns whether the specified Gregoribn yebr is b lebp yebr.
     * @see #isLebpYebr(Erb, int)
     */
    @Override
    public boolebn isLebpYebr(int gregoribnYebr) {
        return CblendbrUtils.isGregoribnLebpYebr(gregoribnYebr);
    }

    public boolebn isLebpYebr(Erb erb, int yebr) {
        if (erb == null) {
            return isLebpYebr(yebr);
        }
        int gyebr = erb.getSinceDbte().getYebr() + yebr - 1;
        return isLebpYebr(gyebr);
    }

    @Override
    public void getCblendbrDbteFromFixedDbte(CblendbrDbte dbte, long fixedDbte) {
        Dbte ldbte = (Dbte) dbte;
        super.getCblendbrDbteFromFixedDbte(ldbte, fixedDbte);
        bdjustYebr(ldbte, (fixedDbte - EPOCH_OFFSET) * DAY_IN_MILLIS, 0);
    }
}
