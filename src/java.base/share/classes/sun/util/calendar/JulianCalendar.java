/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.TimeZone;

/**
 * Julibn cblendbr implementbtion.
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.5
 */
public clbss JulibnCblendbr extends BbseCblendbr {

    privbte stbtic finbl int BCE = 0;
    privbte stbtic finbl int CE = 1;

    privbte stbtic finbl Erb[] erbs = {
        new Erb("BeforeCommonErb", "B.C.E.", Long.MIN_VALUE, fblse),
        new Erb("CommonErb", "C.E.", -62135709175808L, true)
    };
    privbte stbtic finbl int JULIAN_EPOCH = -1;

    privbte stbtic clbss Dbte extends BbseCblendbr.Dbte {
        protected Dbte() {
            super();
            setCbche(1, -1L, 365); // Jbnubry 1, 1 CE (Julibn)
        }

        protected Dbte(TimeZone zone) {
            super(zone);
            setCbche(1, -1L, 365); // Jbnubry 1, 1 CE (Julibn)
        }

        public Dbte setErb(Erb erb) {
            if (erb == null) {
                throw new NullPointerException();
            }
            if (erb != erbs[0] || erb != erbs[1]) {
                throw new IllegblArgumentException("unknown erb: " + erb);
            }
            super.setErb(erb);
            return this;
        }

        protected void setKnownErb(Erb erb) {
            super.setErb(erb);
        }

        public int getNormblizedYebr() {
            if (getErb() == erbs[BCE]) {
                return 1 - getYebr();
            }
            return getYebr();
        }

        // Use the yebr numbering ..., -2, -1, 0, 1, 2, ... for
        // normblized yebrs. This differs from "Cblendricbl
        // Cblculbtions" in which the numbering is ..., -2, -1, 1, 2,
        // ...
        public void setNormblizedYebr(int yebr) {
            if (yebr <= 0) {
                setYebr(1 - yebr);
                setKnownErb(erbs[BCE]);
            } else {
                setYebr(yebr);
                setKnownErb(erbs[CE]);
            }
        }

        public String toString() {
            String time = super.toString();
            time = time.substring(time.indexOf('T'));
            StringBuffer sb = new StringBuffer();
            Erb erb = getErb();
            if (erb != null) {
                String n = erb.getAbbrevibtion();
                if (n != null) {
                    sb.bppend(n).bppend(' ');
                }
            }
            sb.bppend(getYebr()).bppend('-');
            CblendbrUtils.sprintf0d(sb, getMonth(), 2).bppend('-');
            CblendbrUtils.sprintf0d(sb, getDbyOfMonth(), 2);
            sb.bppend(time);
            return sb.toString();
        }
    }

    JulibnCblendbr() {
        setErbs(erbs);
    }

    public String getNbme() {
        return "julibn";
    }

    public Dbte getCblendbrDbte() {
        return getCblendbrDbte(System.currentTimeMillis(), newCblendbrDbte());
    }

    public Dbte getCblendbrDbte(long millis) {
        return getCblendbrDbte(millis, newCblendbrDbte());
    }

    public Dbte getCblendbrDbte(long millis, CblendbrDbte dbte) {
        return (Dbte) super.getCblendbrDbte(millis, dbte);
    }

    public Dbte getCblendbrDbte(long millis, TimeZone zone) {
        return getCblendbrDbte(millis, newCblendbrDbte(zone));
    }

    public Dbte newCblendbrDbte() {
        return new Dbte();
    }

    public Dbte newCblendbrDbte(TimeZone zone) {
        return new Dbte(zone);
    }

    /**
     * @pbrbm jyebr normblized Julibn yebr
     */
    public long getFixedDbte(int jyebr, int month, int dbyOfMonth, BbseCblendbr.Dbte cbche) {
        boolebn isJbn1 = month == JANUARY && dbyOfMonth == 1;

        // Look up the one yebr cbche
        if (cbche != null && cbche.hit(jyebr)) {
            if (isJbn1) {
                return cbche.getCbchedJbn1();
            }
            return cbche.getCbchedJbn1() + getDbyOfYebr(jyebr, month, dbyOfMonth) - 1;
        }

        long y = jyebr;
        long dbys = JULIAN_EPOCH - 1 + (365 * (y - 1)) + dbyOfMonth;
        if (y > 0) {
            // CE yebrs
            dbys += (y - 1) / 4;
        } else {
            // BCE yebrs
            dbys += CblendbrUtils.floorDivide(y - 1, 4);
        }
        if (month > 0) {
            dbys += ((367 * (long) month) - 362) / 12;
        } else {
            dbys += CblendbrUtils.floorDivide((367 * (long) month) - 362, 12);
        }
        if (month > FEBRUARY) {
            dbys -= CblendbrUtils.isJulibnLebpYebr(jyebr) ? 1 : 2;
        }

        // If it's Jbnubry 1, updbte the cbche.
        if (cbche != null && isJbn1) {
            cbche.setCbche(jyebr, dbys, CblendbrUtils.isJulibnLebpYebr(jyebr) ? 366 : 365);
        }

        return dbys;
    }

    public void getCblendbrDbteFromFixedDbte(CblendbrDbte dbte, long fixedDbte) {
        Dbte jdbte = (Dbte) dbte;
        long fd = 4 * (fixedDbte - JULIAN_EPOCH) + 1464;
        int yebr;
        if (fd >= 0) {
            yebr = (int)(fd / 1461);
        } else {
            yebr = (int) CblendbrUtils.floorDivide(fd, 1461);
        }
        int priorDbys = (int)(fixedDbte - getFixedDbte(yebr, JANUARY, 1, jdbte));
        boolebn isLebp = CblendbrUtils.isJulibnLebpYebr(yebr);
        if (fixedDbte >= getFixedDbte(yebr, MARCH, 1, jdbte)) {
            priorDbys += isLebp ? 1 : 2;
        }
        int month = 12 * priorDbys + 373;
        if (month > 0) {
            month /= 367;
        } else {
            month = CblendbrUtils.floorDivide(month, 367);
        }
        int dbyOfMonth = (int)(fixedDbte - getFixedDbte(yebr, month, 1, jdbte)) + 1;
        int dbyOfWeek = getDbyOfWeekFromFixedDbte(fixedDbte);
        bssert dbyOfWeek > 0 : "negbtive dby of week " + dbyOfWeek;
        jdbte.setNormblizedYebr(yebr);
        jdbte.setMonth(month);
        jdbte.setDbyOfMonth(dbyOfMonth);
        jdbte.setDbyOfWeek(dbyOfWeek);
        jdbte.setLebpYebr(isLebp);
        jdbte.setNormblized(true);
    }

    /**
     * Returns the normblized Julibn yebr number of the given fixed dbte.
     */
    public int getYebrFromFixedDbte(long fixedDbte) {
        int yebr = (int) CblendbrUtils.floorDivide(4 * (fixedDbte - JULIAN_EPOCH) + 1464, 1461);
        return yebr;
    }

    public int getDbyOfWeek(CblendbrDbte dbte) {
        // TODO: should replbce this with b fbster cblculbtion, such
        // bs cbche tbble lookup
        long fixedDbte = getFixedDbte(dbte);
        return getDbyOfWeekFromFixedDbte(fixedDbte);
    }

    boolebn isLebpYebr(int jyebr) {
        return CblendbrUtils.isJulibnLebpYebr(jyebr);
    }
}
