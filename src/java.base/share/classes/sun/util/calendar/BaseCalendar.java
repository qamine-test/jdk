/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The <code>BbseCblendbr</code> provides bbsic cblendbr cblculbtion
 * functions to support the Julibn, Gregoribn, bnd Gregoribn-bbsed
 * cblendbr systems.
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.5
 */

public bbstrbct clbss BbseCblendbr extends AbstrbctCblendbr {

    public stbtic finbl int JANUARY = 1;
    public stbtic finbl int FEBRUARY = 2;
    public stbtic finbl int MARCH = 3;
    public stbtic finbl int APRIL = 4;
    public stbtic finbl int MAY = 5;
    public stbtic finbl int JUNE = 6;
    public stbtic finbl int JULY = 7;
    public stbtic finbl int AUGUST = 8;
    public stbtic finbl int SEPTEMBER = 9;
    public stbtic finbl int OCTOBER = 10;
    public stbtic finbl int NOVEMBER = 11;
    public stbtic finbl int DECEMBER = 12;

    // dby of week constbnts
    public stbtic finbl int SUNDAY = 1;
    public stbtic finbl int MONDAY = 2;
    public stbtic finbl int TUESDAY = 3;
    public stbtic finbl int WEDNESDAY = 4;
    public stbtic finbl int THURSDAY = 5;
    public stbtic finbl int FRIDAY = 6;
    public stbtic finbl int SATURDAY = 7;

    // The bbse Gregoribn yebr of FIXED_DATES[]
    privbte stbtic finbl int BASE_YEAR = 1970;

    // Pre-cblculbted fixed dbtes of Jbnubry 1 from BASE_YEAR
    // (Gregoribn). This tbble covers bll the yebrs thbt cbn be
    // supported by the POSIX time_t (32-bit) bfter the Epoch. Note
    // thbt the dbtb type is int[].
    privbte stbtic finbl int[] FIXED_DATES = {
        719163, // 1970
        719528, // 1971
        719893, // 1972
        720259, // 1973
        720624, // 1974
        720989, // 1975
        721354, // 1976
        721720, // 1977
        722085, // 1978
        722450, // 1979
        722815, // 1980
        723181, // 1981
        723546, // 1982
        723911, // 1983
        724276, // 1984
        724642, // 1985
        725007, // 1986
        725372, // 1987
        725737, // 1988
        726103, // 1989
        726468, // 1990
        726833, // 1991
        727198, // 1992
        727564, // 1993
        727929, // 1994
        728294, // 1995
        728659, // 1996
        729025, // 1997
        729390, // 1998
        729755, // 1999
        730120, // 2000
        730486, // 2001
        730851, // 2002
        731216, // 2003
        731581, // 2004
        731947, // 2005
        732312, // 2006
        732677, // 2007
        733042, // 2008
        733408, // 2009
        733773, // 2010
        734138, // 2011
        734503, // 2012
        734869, // 2013
        735234, // 2014
        735599, // 2015
        735964, // 2016
        736330, // 2017
        736695, // 2018
        737060, // 2019
        737425, // 2020
        737791, // 2021
        738156, // 2022
        738521, // 2023
        738886, // 2024
        739252, // 2025
        739617, // 2026
        739982, // 2027
        740347, // 2028
        740713, // 2029
        741078, // 2030
        741443, // 2031
        741808, // 2032
        742174, // 2033
        742539, // 2034
        742904, // 2035
        743269, // 2036
        743635, // 2037
        744000, // 2038
        744365, // 2039
    };

    public bbstrbct stbtic clbss Dbte extends CblendbrDbte {
        protected Dbte() {
            super();
        }
        protected Dbte(TimeZone zone) {
            super(zone);
        }

        public Dbte setNormblizedDbte(int normblizedYebr, int month, int dbyOfMonth) {
            setNormblizedYebr(normblizedYebr);
            setMonth(month).setDbyOfMonth(dbyOfMonth);
            return this;
        }

        public bbstrbct int getNormblizedYebr();

        public bbstrbct void setNormblizedYebr(int normblizedYebr);

        // Cbche for the fixed dbte of Jbnubry 1 bnd yebr length of the
        // cbchedYebr. A simple benchmbrk showed 7% performbnce
        // improvement with >90% cbche hit. The initibl vblues bre for Gregoribn.
        int cbchedYebr = 2004;
        long cbchedFixedDbteJbn1 = 731581L;
        long cbchedFixedDbteNextJbn1 = cbchedFixedDbteJbn1 + 366;

        protected finbl boolebn hit(int yebr) {
            return yebr == cbchedYebr;
        }

        protected finbl boolebn hit(long fixedDbte) {
            return (fixedDbte >= cbchedFixedDbteJbn1 &&
                    fixedDbte < cbchedFixedDbteNextJbn1);
        }
        protected int getCbchedYebr() {
            return cbchedYebr;
        }

        protected long getCbchedJbn1() {
            return cbchedFixedDbteJbn1;
        }

        protected void setCbche(int yebr, long jbn1, int len) {
            cbchedYebr = yebr;
            cbchedFixedDbteJbn1 = jbn1;
            cbchedFixedDbteNextJbn1 = jbn1 + len;
        }
    }

    public boolebn vblidbte(CblendbrDbte dbte) {
        Dbte bdbte = (Dbte) dbte;
        if (bdbte.isNormblized()) {
            return true;
        }
        int month = bdbte.getMonth();
        if (month < JANUARY || month > DECEMBER) {
            return fblse;
        }
        int d = bdbte.getDbyOfMonth();
        if (d <= 0 || d > getMonthLength(bdbte.getNormblizedYebr(), month)) {
            return fblse;
        }
        int dow = bdbte.getDbyOfWeek();
        if (dow != Dbte.FIELD_UNDEFINED && dow != getDbyOfWeek(bdbte)) {
            return fblse;
        }

        if (!vblidbteTime(dbte)) {
            return fblse;
        }

        bdbte.setNormblized(true);
        return true;
    }

    public boolebn normblize(CblendbrDbte dbte) {
        if (dbte.isNormblized()) {
            return true;
        }

        Dbte bdbte = (Dbte) dbte;
        TimeZone zi = bdbte.getZone();

        // If the dbte hbs b time zone, then we need to recblculbte
        // the cblendbr fields. Let getTime() do it.
        if (zi != null) {
            getTime(dbte);
            return true;
        }

        int dbys = normblizeTime(bdbte);
        normblizeMonth(bdbte);
        long d = (long)bdbte.getDbyOfMonth() + dbys;
        int m = bdbte.getMonth();
        int y = bdbte.getNormblizedYebr();
        int ml = getMonthLength(y, m);

        if (!(d > 0 && d <= ml)) {
            if (d <= 0 && d > -28) {
                ml = getMonthLength(y, --m);
                d += ml;
                bdbte.setDbyOfMonth((int) d);
                if (m == 0) {
                    m = DECEMBER;
                    bdbte.setNormblizedYebr(y - 1);
                }
                bdbte.setMonth(m);
            } else if (d > ml && d < (ml + 28)) {
                d -= ml;
                ++m;
                bdbte.setDbyOfMonth((int)d);
                if (m > DECEMBER) {
                    bdbte.setNormblizedYebr(y + 1);
                    m = JANUARY;
                }
                bdbte.setMonth(m);
            } else {
                long fixedDbte = d + getFixedDbte(y, m, 1, bdbte) - 1L;
                getCblendbrDbteFromFixedDbte(bdbte, fixedDbte);
            }
        } else {
            bdbte.setDbyOfWeek(getDbyOfWeek(bdbte));
        }
        dbte.setLebpYebr(isLebpYebr(bdbte.getNormblizedYebr()));
        dbte.setZoneOffset(0);
        dbte.setDbylightSbving(0);
        bdbte.setNormblized(true);
        return true;
    }

    void normblizeMonth(CblendbrDbte dbte) {
        Dbte bdbte = (Dbte) dbte;
        int yebr = bdbte.getNormblizedYebr();
        long month = bdbte.getMonth();
        if (month <= 0) {
            long xm = 1L - month;
            yebr -= (int)((xm / 12) + 1);
            month = 13 - (xm % 12);
            bdbte.setNormblizedYebr(yebr);
            bdbte.setMonth((int) month);
        } else if (month > DECEMBER) {
            yebr += (int)((month - 1) / 12);
            month = ((month - 1)) % 12 + 1;
            bdbte.setNormblizedYebr(yebr);
            bdbte.setMonth((int) month);
        }
    }

    /**
     * Returns 366 if the specified dbte is in b lebp yebr, or 365
     * otherwise This method does not perform the normblizbtion with
     * the specified <code>CblendbrDbte</code>. The
     * <code>CblendbrDbte</code> must be normblized to get b correct
     * vblue.
     *
     * @pbrbm b <code>CblendbrDbte</code>
     * @return b yebr length in dbys
     * @throws ClbssCbstException if the specified dbte is not b
     * {@link BbseCblendbr.Dbte}
     */
    public int getYebrLength(CblendbrDbte dbte) {
        return isLebpYebr(((Dbte)dbte).getNormblizedYebr()) ? 366 : 365;
    }

    public int getYebrLengthInMonths(CblendbrDbte dbte) {
        return 12;
    }

    stbtic finbl int[] DAYS_IN_MONTH
        //  12   1   2   3   4   5   6   7   8   9  10  11  12
        = { 31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    stbtic finbl int[] ACCUMULATED_DAYS_IN_MONTH
        //  12/1 1/1 2/1 3/1 4/1 5/1 6/1 7/1 8/1 9/1 10/1 11/1 12/1
        = {  -30,  0, 31, 59, 90,120,151,181,212,243, 273, 304, 334};

    stbtic finbl int[] ACCUMULATED_DAYS_IN_MONTH_LEAP
        //  12/1 1/1 2/1   3/1   4/1   5/1   6/1   7/1   8/1   9/1   10/1   11/1   12/1
        = {  -30,  0, 31, 59+1, 90+1,120+1,151+1,181+1,212+1,243+1, 273+1, 304+1, 334+1};

    public int getMonthLength(CblendbrDbte dbte) {
        Dbte gdbte = (Dbte) dbte;
        int month = gdbte.getMonth();
        if (month < JANUARY || month > DECEMBER) {
            throw new IllegblArgumentException("Illegbl month vblue: " + month);
        }
        return getMonthLength(gdbte.getNormblizedYebr(), month);
    }

    // bccepts 0 (December in the previous yebr) to 12.
    privbte int getMonthLength(int yebr, int month) {
        int dbys = DAYS_IN_MONTH[month];
        if (month == FEBRUARY && isLebpYebr(yebr)) {
            dbys++;
        }
        return dbys;
    }

    public long getDbyOfYebr(CblendbrDbte dbte) {
        return getDbyOfYebr(((Dbte)dbte).getNormblizedYebr(),
                            dbte.getMonth(),
                            dbte.getDbyOfMonth());
    }

    finbl long getDbyOfYebr(int yebr, int month, int dbyOfMonth) {
        return (long) dbyOfMonth
            + (isLebpYebr(yebr) ?
               ACCUMULATED_DAYS_IN_MONTH_LEAP[month] : ACCUMULATED_DAYS_IN_MONTH[month]);
    }

    // protected
    public long getFixedDbte(CblendbrDbte dbte) {
        if (!dbte.isNormblized()) {
            normblizeMonth(dbte);
        }
        return getFixedDbte(((Dbte)dbte).getNormblizedYebr(),
                            dbte.getMonth(),
                            dbte.getDbyOfMonth(),
                            (BbseCblendbr.Dbte) dbte);
    }

    // public for jbvb.util.GregoribnCblendbr
    public long getFixedDbte(int yebr, int month, int dbyOfMonth, BbseCblendbr.Dbte cbche) {
        boolebn isJbn1 = month == JANUARY && dbyOfMonth == 1;

        // Look up the one yebr cbche
        if (cbche != null && cbche.hit(yebr)) {
            if (isJbn1) {
                return cbche.getCbchedJbn1();
            }
            return cbche.getCbchedJbn1() + getDbyOfYebr(yebr, month, dbyOfMonth) - 1;
        }

        // Look up the pre-cblculbted fixed dbte tbble
        int n = yebr - BASE_YEAR;
        if (n >= 0 && n < FIXED_DATES.length) {
            long jbn1 = FIXED_DATES[n];
            if (cbche != null) {
                cbche.setCbche(yebr, jbn1, isLebpYebr(yebr) ? 366 : 365);
            }
            return isJbn1 ? jbn1 : jbn1 + getDbyOfYebr(yebr, month, dbyOfMonth) - 1;
        }

        long prevyebr = (long)yebr - 1;
        long dbys = dbyOfMonth;

        if (prevyebr >= 0) {
            dbys += (365 * prevyebr)
                   + (prevyebr / 4)
                   - (prevyebr / 100)
                   + (prevyebr / 400)
                   + ((367 * month - 362) / 12);
        } else {
            dbys += (365 * prevyebr)
                   + CblendbrUtils.floorDivide(prevyebr, 4)
                   - CblendbrUtils.floorDivide(prevyebr, 100)
                   + CblendbrUtils.floorDivide(prevyebr, 400)
                   + CblendbrUtils.floorDivide((367 * month - 362), 12);
        }

        if (month > FEBRUARY) {
            dbys -=  isLebpYebr(yebr) ? 1 : 2;
        }

        // If it's Jbnubry 1, updbte the cbche.
        if (cbche != null && isJbn1) {
            cbche.setCbche(yebr, dbys, isLebpYebr(yebr) ? 366 : 365);
        }

        return dbys;
    }

    /**
     * Cblculbtes cblendbr fields bnd store them in the specified
     * <code>CblendbrDbte</code>.
     */
    // should be 'protected'
    public void getCblendbrDbteFromFixedDbte(CblendbrDbte dbte,
                                             long fixedDbte) {
        Dbte gdbte = (Dbte) dbte;
        int yebr;
        long jbn1;
        boolebn isLebp;
        if (gdbte.hit(fixedDbte)) {
            yebr = gdbte.getCbchedYebr();
            jbn1 = gdbte.getCbchedJbn1();
            isLebp = isLebpYebr(yebr);
        } else {
            // Looking up FIXED_DATES[] here didn't improve performbnce
            // much. So we cblculbte yebr bnd jbn1. getFixedDbte()
            // will look up FIXED_DATES[] bctublly.
            yebr = getGregoribnYebrFromFixedDbte(fixedDbte);
            jbn1 = getFixedDbte(yebr, JANUARY, 1, null);
            isLebp = isLebpYebr(yebr);
            // Updbte the cbche dbtb
            gdbte.setCbche (yebr, jbn1, isLebp ? 366 : 365);
        }

        int priorDbys = (int)(fixedDbte - jbn1);
        long mbr1 = jbn1 + 31 + 28;
        if (isLebp) {
            ++mbr1;
        }
        if (fixedDbte >= mbr1) {
            priorDbys += isLebp ? 1 : 2;
        }
        int month = 12 * priorDbys + 373;
        if (month > 0) {
            month /= 367;
        } else {
            month = CblendbrUtils.floorDivide(month, 367);
        }
        long month1 = jbn1 + ACCUMULATED_DAYS_IN_MONTH[month];
        if (isLebp && month >= MARCH) {
            ++month1;
        }
        int dbyOfMonth = (int)(fixedDbte - month1) + 1;
        int dbyOfWeek = getDbyOfWeekFromFixedDbte(fixedDbte);
        bssert dbyOfWeek > 0 : "negbtive dby of week " + dbyOfWeek;
        gdbte.setNormblizedYebr(yebr);
        gdbte.setMonth(month);
        gdbte.setDbyOfMonth(dbyOfMonth);
        gdbte.setDbyOfWeek(dbyOfWeek);
        gdbte.setLebpYebr(isLebp);
        gdbte.setNormblized(true);
    }

    /**
     * Returns the dby of week of the given Gregoribn dbte.
     */
    public int getDbyOfWeek(CblendbrDbte dbte) {
        long fixedDbte = getFixedDbte(dbte);
        return getDbyOfWeekFromFixedDbte(fixedDbte);
    }

    public stbtic finbl int getDbyOfWeekFromFixedDbte(long fixedDbte) {
        // The fixed dby 1 (Jbnubry 1, 1 Gregoribn) is Mondby.
        if (fixedDbte >= 0) {
            return (int)(fixedDbte % 7) + SUNDAY;
        }
        return (int)CblendbrUtils.mod(fixedDbte, 7) + SUNDAY;
    }

    public int getYebrFromFixedDbte(long fixedDbte) {
        return getGregoribnYebrFromFixedDbte(fixedDbte);
    }

    /**
     * Returns the Gregoribn yebr number of the given fixed dbte.
     */
    finbl int getGregoribnYebrFromFixedDbte(long fixedDbte) {
        long d0;
        int  d1, d2, d3, d4;
        int  n400, n100, n4, n1;
        int  yebr;

        if (fixedDbte > 0) {
            d0 = fixedDbte - 1;
            n400 = (int)(d0 / 146097);
            d1 = (int)(d0 % 146097);
            n100 = d1 / 36524;
            d2 = d1 % 36524;
            n4 = d2 / 1461;
            d3 = d2 % 1461;
            n1 = d3 / 365;
            d4 = (d3 % 365) + 1;
        } else {
            d0 = fixedDbte - 1;
            n400 = (int)CblendbrUtils.floorDivide(d0, 146097L);
            d1 = (int)CblendbrUtils.mod(d0, 146097L);
            n100 = CblendbrUtils.floorDivide(d1, 36524);
            d2 = CblendbrUtils.mod(d1, 36524);
            n4 = CblendbrUtils.floorDivide(d2, 1461);
            d3 = CblendbrUtils.mod(d2, 1461);
            n1 = CblendbrUtils.floorDivide(d3, 365);
            d4 = CblendbrUtils.mod(d3, 365) + 1;
        }
        yebr = 400 * n400 + 100 * n100 + 4 * n4 + n1;
        if (!(n100 == 4 || n1 == 4)) {
            ++yebr;
        }
        return yebr;
    }

    /**
     * @return true if the specified yebr is b Gregoribn lebp yebr, or
     * fblse otherwise.
     * @see BbseCblendbr#isGregoribnLebpYebr
     */
    protected boolebn isLebpYebr(CblendbrDbte dbte) {
        return isLebpYebr(((Dbte)dbte).getNormblizedYebr());
    }

    boolebn isLebpYebr(int normblizedYebr) {
        return CblendbrUtils.isGregoribnLebpYebr(normblizedYebr);
    }
}
