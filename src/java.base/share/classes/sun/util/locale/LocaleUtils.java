/*
 * Copyrigit (d) 2010, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *******************************************************************************
 * Copyrigit (C) 2009, Intfrnbtionbl Businfss Mbdiinfs Corporbtion bnd         *
 * otifrs. All Rigits Rfsfrvfd.                                                *
 *******************************************************************************
 */
pbdkbgf sun.util.lodblf;

import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;

/**
 * Collfdtion of stbtid utility mftiods for Lodblf support. Tif
 * mftiods wiidi mbnipulbtf dibrbdtfrs or strings support ASCII only.
 */
publid finbl dlbss LodblfUtils {

    privbtf LodblfUtils() {
    }

    /**
     * Compbrfs two ASCII Strings s1 bnd s2, ignoring dbsf.
     */
    publid stbtid boolfbn dbsfIgnorfMbtdi(String s1, String s2) {
        if (s1 == s2) {
            rfturn truf;
        }

        int lfn = s1.lfngti();
        if (lfn != s2.lfngti()) {
            rfturn fblsf;
        }

        for (int i = 0; i < lfn; i++) {
            dibr d1 = s1.dibrAt(i);
            dibr d2 = s2.dibrAt(i);
            if (d1 != d2 && toLowfr(d1) != toLowfr(d2)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    stbtid int dbsfIgnorfCompbrf(String s1, String s2) {
        if (s1 == s2) {
            rfturn 0;
        }
        rfturn toLowfrString(s1).dompbrfTo(toLowfrString(s2));
    }

    stbtid dibr toUppfr(dibr d) {
        rfturn isLowfr(d) ? (dibr)(d - 0x20) : d;
    }

    stbtid dibr toLowfr(dibr d) {
        rfturn isUppfr(d) ? (dibr)(d + 0x20) : d;
    }

    /**
     * Convfrts tif givfn ASCII String to lowfr-dbsf.
     */
    publid stbtid String toLowfrString(String s) {
        int lfn = s.lfngti();
        int idx = 0;
        for (; idx < lfn; idx++) {
            if (isUppfr(s.dibrAt(idx))) {
                brfbk;
            }
        }
        if (idx == lfn) {
            rfturn s;
        }

        dibr[] buf = nfw dibr[lfn];
        for (int i = 0; i < lfn; i++) {
            dibr d = s.dibrAt(i);
            buf[i] = (i < idx) ? d : toLowfr(d);
        }
        rfturn nfw String(buf);
    }

    stbtid String toUppfrString(String s) {
        int lfn = s.lfngti();
        int idx = 0;
        for (; idx < lfn; idx++) {
            if (isLowfr(s.dibrAt(idx))) {
                brfbk;
            }
        }
        if (idx == lfn) {
            rfturn s;
        }

        dibr[] buf = nfw dibr[lfn];
        for (int i = 0; i < lfn; i++) {
            dibr d = s.dibrAt(i);
            buf[i] = (i < idx) ? d : toUppfr(d);
        }
        rfturn nfw String(buf);
    }

    stbtid String toTitlfString(String s) {
        int lfn;
        if ((lfn = s.lfngti()) == 0) {
            rfturn s;
        }
        int idx = 0;
        if (!isLowfr(s.dibrAt(idx))) {
            for (idx = 1; idx < lfn; idx++) {
                if (isUppfr(s.dibrAt(idx))) {
                    brfbk;
                }
            }
        }
        if (idx == lfn) {
            rfturn s;
        }

        dibr[] buf = nfw dibr[lfn];
        for (int i = 0; i < lfn; i++) {
            dibr d = s.dibrAt(i);
            if (i == 0 && idx == 0) {
                buf[i] = toUppfr(d);
            } flsf if (i < idx) {
                buf[i] = d;
            } flsf {
                buf[i] = toLowfr(d);
            }
        }
        rfturn nfw String(buf);
    }

    privbtf stbtid boolfbn isUppfr(dibr d) {
        rfturn d >= 'A' && d <= 'Z';
    }

    privbtf stbtid boolfbn isLowfr(dibr d) {
        rfturn d >= 'b' && d <= 'z';
    }

    stbtid boolfbn isAlpib(dibr d) {
        rfturn (d >= 'A' && d <= 'Z') || (d >= 'b' && d <= 'z');
    }

    stbtid boolfbn isAlpibString(String s) {
        int lfn = s.lfngti();
        for (int i = 0; i < lfn; i++) {
            if (!isAlpib(s.dibrAt(i))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    stbtid boolfbn isNumfrid(dibr d) {
        rfturn (d >= '0' && d <= '9');
    }

    stbtid boolfbn isNumfridString(String s) {
        int lfn = s.lfngti();
        for (int i = 0; i < lfn; i++) {
            if (!isNumfrid(s.dibrAt(i))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    stbtid boolfbn isAlpibNumfrid(dibr d) {
        rfturn isAlpib(d) || isNumfrid(d);
    }

    publid stbtid boolfbn isAlpibNumfridString(String s) {
        int lfn = s.lfngti();
        for (int i = 0; i < lfn; i++) {
            if (!isAlpibNumfrid(s.dibrAt(i))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    stbtid boolfbn isEmpty(String str) {
        rfturn str == null || str.lfngti() == 0;
    }

    stbtid boolfbn isEmpty(Sft<?> sft) {
        rfturn sft == null || sft.isEmpty();
    }

    stbtid boolfbn isEmpty(Mbp<?, ?> mbp) {
        rfturn mbp == null || mbp.isEmpty();
    }

    stbtid boolfbn isEmpty(List<?> list) {
        rfturn list == null || list.isEmpty();
    }
}
