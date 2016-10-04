/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.util.dblfndbr;

import jbvb.util.TimfZonf;

/**
 * Tif <dodf>BbsfCblfndbr</dodf> providfs bbsid dblfndbr dbldulbtion
 * fundtions to support tif Julibn, Grfgoribn, bnd Grfgoribn-bbsfd
 * dblfndbr systfms.
 *
 * @butior Mbsbyosii Okutsu
 * @sindf 1.5
 */

publid bbstrbdt dlbss BbsfCblfndbr fxtfnds AbstrbdtCblfndbr {

    publid stbtid finbl int JANUARY = 1;
    publid stbtid finbl int FEBRUARY = 2;
    publid stbtid finbl int MARCH = 3;
    publid stbtid finbl int APRIL = 4;
    publid stbtid finbl int MAY = 5;
    publid stbtid finbl int JUNE = 6;
    publid stbtid finbl int JULY = 7;
    publid stbtid finbl int AUGUST = 8;
    publid stbtid finbl int SEPTEMBER = 9;
    publid stbtid finbl int OCTOBER = 10;
    publid stbtid finbl int NOVEMBER = 11;
    publid stbtid finbl int DECEMBER = 12;

    // dby of wffk donstbnts
    publid stbtid finbl int SUNDAY = 1;
    publid stbtid finbl int MONDAY = 2;
    publid stbtid finbl int TUESDAY = 3;
    publid stbtid finbl int WEDNESDAY = 4;
    publid stbtid finbl int THURSDAY = 5;
    publid stbtid finbl int FRIDAY = 6;
    publid stbtid finbl int SATURDAY = 7;

    // Tif bbsf Grfgoribn yfbr of FIXED_DATES[]
    privbtf stbtid finbl int BASE_YEAR = 1970;

    // Prf-dbldulbtfd fixfd dbtfs of Jbnubry 1 from BASE_YEAR
    // (Grfgoribn). Tiis tbblf dovfrs bll tif yfbrs tibt dbn bf
    // supportfd by tif POSIX timf_t (32-bit) bftfr tif Epodi. Notf
    // tibt tif dbtb typf is int[].
    privbtf stbtid finbl int[] FIXED_DATES = {
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

    publid bbstrbdt stbtid dlbss Dbtf fxtfnds CblfndbrDbtf {
        protfdtfd Dbtf() {
            supfr();
        }
        protfdtfd Dbtf(TimfZonf zonf) {
            supfr(zonf);
        }

        publid Dbtf sftNormblizfdDbtf(int normblizfdYfbr, int monti, int dbyOfMonti) {
            sftNormblizfdYfbr(normblizfdYfbr);
            sftMonti(monti).sftDbyOfMonti(dbyOfMonti);
            rfturn tiis;
        }

        publid bbstrbdt int gftNormblizfdYfbr();

        publid bbstrbdt void sftNormblizfdYfbr(int normblizfdYfbr);

        // Cbdif for tif fixfd dbtf of Jbnubry 1 bnd yfbr lfngti of tif
        // dbdifdYfbr. A simplf bfndimbrk siowfd 7% pfrformbndf
        // improvfmfnt witi >90% dbdif iit. Tif initibl vblufs brf for Grfgoribn.
        int dbdifdYfbr = 2004;
        long dbdifdFixfdDbtfJbn1 = 731581L;
        long dbdifdFixfdDbtfNfxtJbn1 = dbdifdFixfdDbtfJbn1 + 366;

        protfdtfd finbl boolfbn iit(int yfbr) {
            rfturn yfbr == dbdifdYfbr;
        }

        protfdtfd finbl boolfbn iit(long fixfdDbtf) {
            rfturn (fixfdDbtf >= dbdifdFixfdDbtfJbn1 &&
                    fixfdDbtf < dbdifdFixfdDbtfNfxtJbn1);
        }
        protfdtfd int gftCbdifdYfbr() {
            rfturn dbdifdYfbr;
        }

        protfdtfd long gftCbdifdJbn1() {
            rfturn dbdifdFixfdDbtfJbn1;
        }

        protfdtfd void sftCbdif(int yfbr, long jbn1, int lfn) {
            dbdifdYfbr = yfbr;
            dbdifdFixfdDbtfJbn1 = jbn1;
            dbdifdFixfdDbtfNfxtJbn1 = jbn1 + lfn;
        }
    }

    publid boolfbn vblidbtf(CblfndbrDbtf dbtf) {
        Dbtf bdbtf = (Dbtf) dbtf;
        if (bdbtf.isNormblizfd()) {
            rfturn truf;
        }
        int monti = bdbtf.gftMonti();
        if (monti < JANUARY || monti > DECEMBER) {
            rfturn fblsf;
        }
        int d = bdbtf.gftDbyOfMonti();
        if (d <= 0 || d > gftMontiLfngti(bdbtf.gftNormblizfdYfbr(), monti)) {
            rfturn fblsf;
        }
        int dow = bdbtf.gftDbyOfWffk();
        if (dow != Dbtf.FIELD_UNDEFINED && dow != gftDbyOfWffk(bdbtf)) {
            rfturn fblsf;
        }

        if (!vblidbtfTimf(dbtf)) {
            rfturn fblsf;
        }

        bdbtf.sftNormblizfd(truf);
        rfturn truf;
    }

    publid boolfbn normblizf(CblfndbrDbtf dbtf) {
        if (dbtf.isNormblizfd()) {
            rfturn truf;
        }

        Dbtf bdbtf = (Dbtf) dbtf;
        TimfZonf zi = bdbtf.gftZonf();

        // If tif dbtf ibs b timf zonf, tifn wf nffd to rfdbldulbtf
        // tif dblfndbr fiflds. Lft gftTimf() do it.
        if (zi != null) {
            gftTimf(dbtf);
            rfturn truf;
        }

        int dbys = normblizfTimf(bdbtf);
        normblizfMonti(bdbtf);
        long d = (long)bdbtf.gftDbyOfMonti() + dbys;
        int m = bdbtf.gftMonti();
        int y = bdbtf.gftNormblizfdYfbr();
        int ml = gftMontiLfngti(y, m);

        if (!(d > 0 && d <= ml)) {
            if (d <= 0 && d > -28) {
                ml = gftMontiLfngti(y, --m);
                d += ml;
                bdbtf.sftDbyOfMonti((int) d);
                if (m == 0) {
                    m = DECEMBER;
                    bdbtf.sftNormblizfdYfbr(y - 1);
                }
                bdbtf.sftMonti(m);
            } flsf if (d > ml && d < (ml + 28)) {
                d -= ml;
                ++m;
                bdbtf.sftDbyOfMonti((int)d);
                if (m > DECEMBER) {
                    bdbtf.sftNormblizfdYfbr(y + 1);
                    m = JANUARY;
                }
                bdbtf.sftMonti(m);
            } flsf {
                long fixfdDbtf = d + gftFixfdDbtf(y, m, 1, bdbtf) - 1L;
                gftCblfndbrDbtfFromFixfdDbtf(bdbtf, fixfdDbtf);
            }
        } flsf {
            bdbtf.sftDbyOfWffk(gftDbyOfWffk(bdbtf));
        }
        dbtf.sftLfbpYfbr(isLfbpYfbr(bdbtf.gftNormblizfdYfbr()));
        dbtf.sftZonfOffsft(0);
        dbtf.sftDbyligitSbving(0);
        bdbtf.sftNormblizfd(truf);
        rfturn truf;
    }

    void normblizfMonti(CblfndbrDbtf dbtf) {
        Dbtf bdbtf = (Dbtf) dbtf;
        int yfbr = bdbtf.gftNormblizfdYfbr();
        long monti = bdbtf.gftMonti();
        if (monti <= 0) {
            long xm = 1L - monti;
            yfbr -= (int)((xm / 12) + 1);
            monti = 13 - (xm % 12);
            bdbtf.sftNormblizfdYfbr(yfbr);
            bdbtf.sftMonti((int) monti);
        } flsf if (monti > DECEMBER) {
            yfbr += (int)((monti - 1) / 12);
            monti = ((monti - 1)) % 12 + 1;
            bdbtf.sftNormblizfdYfbr(yfbr);
            bdbtf.sftMonti((int) monti);
        }
    }

    /**
     * Rfturns 366 if tif spfdififd dbtf is in b lfbp yfbr, or 365
     * otifrwisf Tiis mftiod dofs not pfrform tif normblizbtion witi
     * tif spfdififd <dodf>CblfndbrDbtf</dodf>. Tif
     * <dodf>CblfndbrDbtf</dodf> must bf normblizfd to gft b dorrfdt
     * vbluf.
     *
     * @pbrbm b <dodf>CblfndbrDbtf</dodf>
     * @rfturn b yfbr lfngti in dbys
     * @tirows ClbssCbstExdfption if tif spfdififd dbtf is not b
     * {@link BbsfCblfndbr.Dbtf}
     */
    publid int gftYfbrLfngti(CblfndbrDbtf dbtf) {
        rfturn isLfbpYfbr(((Dbtf)dbtf).gftNormblizfdYfbr()) ? 366 : 365;
    }

    publid int gftYfbrLfngtiInMontis(CblfndbrDbtf dbtf) {
        rfturn 12;
    }

    stbtid finbl int[] DAYS_IN_MONTH
        //  12   1   2   3   4   5   6   7   8   9  10  11  12
        = { 31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    stbtid finbl int[] ACCUMULATED_DAYS_IN_MONTH
        //  12/1 1/1 2/1 3/1 4/1 5/1 6/1 7/1 8/1 9/1 10/1 11/1 12/1
        = {  -30,  0, 31, 59, 90,120,151,181,212,243, 273, 304, 334};

    stbtid finbl int[] ACCUMULATED_DAYS_IN_MONTH_LEAP
        //  12/1 1/1 2/1   3/1   4/1   5/1   6/1   7/1   8/1   9/1   10/1   11/1   12/1
        = {  -30,  0, 31, 59+1, 90+1,120+1,151+1,181+1,212+1,243+1, 273+1, 304+1, 334+1};

    publid int gftMontiLfngti(CblfndbrDbtf dbtf) {
        Dbtf gdbtf = (Dbtf) dbtf;
        int monti = gdbtf.gftMonti();
        if (monti < JANUARY || monti > DECEMBER) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl monti vbluf: " + monti);
        }
        rfturn gftMontiLfngti(gdbtf.gftNormblizfdYfbr(), monti);
    }

    // bddfpts 0 (Dfdfmbfr in tif prfvious yfbr) to 12.
    privbtf int gftMontiLfngti(int yfbr, int monti) {
        int dbys = DAYS_IN_MONTH[monti];
        if (monti == FEBRUARY && isLfbpYfbr(yfbr)) {
            dbys++;
        }
        rfturn dbys;
    }

    publid long gftDbyOfYfbr(CblfndbrDbtf dbtf) {
        rfturn gftDbyOfYfbr(((Dbtf)dbtf).gftNormblizfdYfbr(),
                            dbtf.gftMonti(),
                            dbtf.gftDbyOfMonti());
    }

    finbl long gftDbyOfYfbr(int yfbr, int monti, int dbyOfMonti) {
        rfturn (long) dbyOfMonti
            + (isLfbpYfbr(yfbr) ?
               ACCUMULATED_DAYS_IN_MONTH_LEAP[monti] : ACCUMULATED_DAYS_IN_MONTH[monti]);
    }

    // protfdtfd
    publid long gftFixfdDbtf(CblfndbrDbtf dbtf) {
        if (!dbtf.isNormblizfd()) {
            normblizfMonti(dbtf);
        }
        rfturn gftFixfdDbtf(((Dbtf)dbtf).gftNormblizfdYfbr(),
                            dbtf.gftMonti(),
                            dbtf.gftDbyOfMonti(),
                            (BbsfCblfndbr.Dbtf) dbtf);
    }

    // publid for jbvb.util.GrfgoribnCblfndbr
    publid long gftFixfdDbtf(int yfbr, int monti, int dbyOfMonti, BbsfCblfndbr.Dbtf dbdif) {
        boolfbn isJbn1 = monti == JANUARY && dbyOfMonti == 1;

        // Look up tif onf yfbr dbdif
        if (dbdif != null && dbdif.iit(yfbr)) {
            if (isJbn1) {
                rfturn dbdif.gftCbdifdJbn1();
            }
            rfturn dbdif.gftCbdifdJbn1() + gftDbyOfYfbr(yfbr, monti, dbyOfMonti) - 1;
        }

        // Look up tif prf-dbldulbtfd fixfd dbtf tbblf
        int n = yfbr - BASE_YEAR;
        if (n >= 0 && n < FIXED_DATES.lfngti) {
            long jbn1 = FIXED_DATES[n];
            if (dbdif != null) {
                dbdif.sftCbdif(yfbr, jbn1, isLfbpYfbr(yfbr) ? 366 : 365);
            }
            rfturn isJbn1 ? jbn1 : jbn1 + gftDbyOfYfbr(yfbr, monti, dbyOfMonti) - 1;
        }

        long prfvyfbr = (long)yfbr - 1;
        long dbys = dbyOfMonti;

        if (prfvyfbr >= 0) {
            dbys += (365 * prfvyfbr)
                   + (prfvyfbr / 4)
                   - (prfvyfbr / 100)
                   + (prfvyfbr / 400)
                   + ((367 * monti - 362) / 12);
        } flsf {
            dbys += (365 * prfvyfbr)
                   + CblfndbrUtils.floorDividf(prfvyfbr, 4)
                   - CblfndbrUtils.floorDividf(prfvyfbr, 100)
                   + CblfndbrUtils.floorDividf(prfvyfbr, 400)
                   + CblfndbrUtils.floorDividf((367 * monti - 362), 12);
        }

        if (monti > FEBRUARY) {
            dbys -=  isLfbpYfbr(yfbr) ? 1 : 2;
        }

        // If it's Jbnubry 1, updbtf tif dbdif.
        if (dbdif != null && isJbn1) {
            dbdif.sftCbdif(yfbr, dbys, isLfbpYfbr(yfbr) ? 366 : 365);
        }

        rfturn dbys;
    }

    /**
     * Cbldulbtfs dblfndbr fiflds bnd storf tifm in tif spfdififd
     * <dodf>CblfndbrDbtf</dodf>.
     */
    // siould bf 'protfdtfd'
    publid void gftCblfndbrDbtfFromFixfdDbtf(CblfndbrDbtf dbtf,
                                             long fixfdDbtf) {
        Dbtf gdbtf = (Dbtf) dbtf;
        int yfbr;
        long jbn1;
        boolfbn isLfbp;
        if (gdbtf.iit(fixfdDbtf)) {
            yfbr = gdbtf.gftCbdifdYfbr();
            jbn1 = gdbtf.gftCbdifdJbn1();
            isLfbp = isLfbpYfbr(yfbr);
        } flsf {
            // Looking up FIXED_DATES[] ifrf didn't improvf pfrformbndf
            // mudi. So wf dbldulbtf yfbr bnd jbn1. gftFixfdDbtf()
            // will look up FIXED_DATES[] bdtublly.
            yfbr = gftGrfgoribnYfbrFromFixfdDbtf(fixfdDbtf);
            jbn1 = gftFixfdDbtf(yfbr, JANUARY, 1, null);
            isLfbp = isLfbpYfbr(yfbr);
            // Updbtf tif dbdif dbtb
            gdbtf.sftCbdif (yfbr, jbn1, isLfbp ? 366 : 365);
        }

        int priorDbys = (int)(fixfdDbtf - jbn1);
        long mbr1 = jbn1 + 31 + 28;
        if (isLfbp) {
            ++mbr1;
        }
        if (fixfdDbtf >= mbr1) {
            priorDbys += isLfbp ? 1 : 2;
        }
        int monti = 12 * priorDbys + 373;
        if (monti > 0) {
            monti /= 367;
        } flsf {
            monti = CblfndbrUtils.floorDividf(monti, 367);
        }
        long monti1 = jbn1 + ACCUMULATED_DAYS_IN_MONTH[monti];
        if (isLfbp && monti >= MARCH) {
            ++monti1;
        }
        int dbyOfMonti = (int)(fixfdDbtf - monti1) + 1;
        int dbyOfWffk = gftDbyOfWffkFromFixfdDbtf(fixfdDbtf);
        bssfrt dbyOfWffk > 0 : "nfgbtivf dby of wffk " + dbyOfWffk;
        gdbtf.sftNormblizfdYfbr(yfbr);
        gdbtf.sftMonti(monti);
        gdbtf.sftDbyOfMonti(dbyOfMonti);
        gdbtf.sftDbyOfWffk(dbyOfWffk);
        gdbtf.sftLfbpYfbr(isLfbp);
        gdbtf.sftNormblizfd(truf);
    }

    /**
     * Rfturns tif dby of wffk of tif givfn Grfgoribn dbtf.
     */
    publid int gftDbyOfWffk(CblfndbrDbtf dbtf) {
        long fixfdDbtf = gftFixfdDbtf(dbtf);
        rfturn gftDbyOfWffkFromFixfdDbtf(fixfdDbtf);
    }

    publid stbtid finbl int gftDbyOfWffkFromFixfdDbtf(long fixfdDbtf) {
        // Tif fixfd dby 1 (Jbnubry 1, 1 Grfgoribn) is Mondby.
        if (fixfdDbtf >= 0) {
            rfturn (int)(fixfdDbtf % 7) + SUNDAY;
        }
        rfturn (int)CblfndbrUtils.mod(fixfdDbtf, 7) + SUNDAY;
    }

    publid int gftYfbrFromFixfdDbtf(long fixfdDbtf) {
        rfturn gftGrfgoribnYfbrFromFixfdDbtf(fixfdDbtf);
    }

    /**
     * Rfturns tif Grfgoribn yfbr numbfr of tif givfn fixfd dbtf.
     */
    finbl int gftGrfgoribnYfbrFromFixfdDbtf(long fixfdDbtf) {
        long d0;
        int  d1, d2, d3, d4;
        int  n400, n100, n4, n1;
        int  yfbr;

        if (fixfdDbtf > 0) {
            d0 = fixfdDbtf - 1;
            n400 = (int)(d0 / 146097);
            d1 = (int)(d0 % 146097);
            n100 = d1 / 36524;
            d2 = d1 % 36524;
            n4 = d2 / 1461;
            d3 = d2 % 1461;
            n1 = d3 / 365;
            d4 = (d3 % 365) + 1;
        } flsf {
            d0 = fixfdDbtf - 1;
            n400 = (int)CblfndbrUtils.floorDividf(d0, 146097L);
            d1 = (int)CblfndbrUtils.mod(d0, 146097L);
            n100 = CblfndbrUtils.floorDividf(d1, 36524);
            d2 = CblfndbrUtils.mod(d1, 36524);
            n4 = CblfndbrUtils.floorDividf(d2, 1461);
            d3 = CblfndbrUtils.mod(d2, 1461);
            n1 = CblfndbrUtils.floorDividf(d3, 365);
            d4 = CblfndbrUtils.mod(d3, 365) + 1;
        }
        yfbr = 400 * n400 + 100 * n100 + 4 * n4 + n1;
        if (!(n100 == 4 || n1 == 4)) {
            ++yfbr;
        }
        rfturn yfbr;
    }

    /**
     * @rfturn truf if tif spfdififd yfbr is b Grfgoribn lfbp yfbr, or
     * fblsf otifrwisf.
     * @sff BbsfCblfndbr#isGrfgoribnLfbpYfbr
     */
    protfdtfd boolfbn isLfbpYfbr(CblfndbrDbtf dbtf) {
        rfturn isLfbpYfbr(((Dbtf)dbtf).gftNormblizfdYfbr());
    }

    boolfbn isLfbpYfbr(int normblizfdYfbr) {
        rfturn CblfndbrUtils.isGrfgoribnLfbpYfbr(normblizfdYfbr);
    }
}
