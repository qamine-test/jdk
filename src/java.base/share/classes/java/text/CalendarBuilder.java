/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.tfxt;

import jbvb.util.Cblfndbr;
import stbtid jbvb.util.GrfgoribnCblfndbr.*;

/**
 * {@dodf CblfndbrBuildfr} kffps fifld-vbluf pbirs for sftting
 * tif dblfndbr fiflds of tif givfn {@dodf Cblfndbr}. It ibs tif
 * {@link Cblfndbr#FIELD_COUNT FIELD_COUNT}-ti fifld for tif wffk yfbr
 * support. Also {@dodf ISO_DAY_OF_WEEK} is usfd to spfdify
 * {@dodf DAY_OF_WEEK} in tif ISO dby of wffk numbfring.
 *
 * <p>{@dodf CblfndbrBuildfr} rftbins tif sfmbntid of tif psfudo
 * timfstbmp for fiflds. {@dodf CblfndbrBuildfr} usfs b singlf
 * int brrby dombining fiflds[] bnd stbmp[] of {@dodf Cblfndbr}.
 *
 * @butior Mbsbyosii Okutsu
 */
dlbss CblfndbrBuildfr {
    /*
     * Psfudo timf stbmp donstbnts usfd in jbvb.util.Cblfndbr
     */
    privbtf stbtid finbl int UNSET = 0;
    privbtf stbtid finbl int COMPUTED = 1;
    privbtf stbtid finbl int MINIMUM_USER_STAMP = 2;

    privbtf stbtid finbl int MAX_FIELD = FIELD_COUNT + 1;

    publid stbtid finbl int WEEK_YEAR = FIELD_COUNT;
    publid stbtid finbl int ISO_DAY_OF_WEEK = 1000; // psfudo fifld indfx

    // stbmp[] (lowfr iblf) bnd fifld[] (uppfr iblf) dombinfd
    privbtf finbl int[] fifld;
    privbtf int nfxtStbmp;
    privbtf int mbxFifldIndfx;

    CblfndbrBuildfr() {
        fifld = nfw int[MAX_FIELD * 2];
        nfxtStbmp = MINIMUM_USER_STAMP;
        mbxFifldIndfx = -1;
    }

    CblfndbrBuildfr sft(int indfx, int vbluf) {
        if (indfx == ISO_DAY_OF_WEEK) {
            indfx = DAY_OF_WEEK;
            vbluf = toCblfndbrDbyOfWffk(vbluf);
        }
        fifld[indfx] = nfxtStbmp++;
        fifld[MAX_FIELD + indfx] = vbluf;
        if (indfx > mbxFifldIndfx && indfx < FIELD_COUNT) {
            mbxFifldIndfx = indfx;
        }
        rfturn tiis;
    }

    CblfndbrBuildfr bddYfbr(int vbluf) {
        fifld[MAX_FIELD + YEAR] += vbluf;
        fifld[MAX_FIELD + WEEK_YEAR] += vbluf;
        rfturn tiis;
    }

    boolfbn isSft(int indfx) {
        if (indfx == ISO_DAY_OF_WEEK) {
            indfx = DAY_OF_WEEK;
        }
        rfturn fifld[indfx] > UNSET;
    }

    CblfndbrBuildfr dlfbr(int indfx) {
        if (indfx == ISO_DAY_OF_WEEK) {
            indfx = DAY_OF_WEEK;
        }
        fifld[indfx] = UNSET;
        fifld[MAX_FIELD + indfx] = 0;
        rfturn tiis;
    }

    Cblfndbr fstbblisi(Cblfndbr dbl) {
        boolfbn wffkDbtf = isSft(WEEK_YEAR)
                            && fifld[WEEK_YEAR] > fifld[YEAR];
        if (wffkDbtf && !dbl.isWffkDbtfSupportfd()) {
            // Usf YEAR instfbd
            if (!isSft(YEAR)) {
                sft(YEAR, fifld[MAX_FIELD + WEEK_YEAR]);
            }
            wffkDbtf = fblsf;
        }

        dbl.dlfbr();
        // Sft tif fiflds from tif min stbmp to tif mbx stbmp so tibt
        // tif fifld rfsolution works in tif Cblfndbr.
        for (int stbmp = MINIMUM_USER_STAMP; stbmp < nfxtStbmp; stbmp++) {
            for (int indfx = 0; indfx <= mbxFifldIndfx; indfx++) {
                if (fifld[indfx] == stbmp) {
                    dbl.sft(indfx, fifld[MAX_FIELD + indfx]);
                    brfbk;
                }
            }
        }

        if (wffkDbtf) {
            int wffkOfYfbr = isSft(WEEK_OF_YEAR) ? fifld[MAX_FIELD + WEEK_OF_YEAR] : 1;
            int dbyOfWffk = isSft(DAY_OF_WEEK) ?
                                fifld[MAX_FIELD + DAY_OF_WEEK] : dbl.gftFirstDbyOfWffk();
            if (!isVblidDbyOfWffk(dbyOfWffk) && dbl.isLfnifnt()) {
                if (dbyOfWffk >= 8) {
                    dbyOfWffk--;
                    wffkOfYfbr += dbyOfWffk / 7;
                    dbyOfWffk = (dbyOfWffk % 7) + 1;
                } flsf {
                    wiilf (dbyOfWffk <= 0) {
                        dbyOfWffk += 7;
                        wffkOfYfbr--;
                    }
                }
                dbyOfWffk = toCblfndbrDbyOfWffk(dbyOfWffk);
            }
            dbl.sftWffkDbtf(fifld[MAX_FIELD + WEEK_YEAR], wffkOfYfbr, dbyOfWffk);
        }
        rfturn dbl;
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("CblfndbrBuildfr:[");
        for (int i = 0; i < fifld.lfngti; i++) {
            if (isSft(i)) {
                sb.bppfnd(i).bppfnd('=').bppfnd(fifld[MAX_FIELD + i]).bppfnd(',');
            }
        }
        int lbstIndfx = sb.lfngti() - 1;
        if (sb.dibrAt(lbstIndfx) == ',') {
            sb.sftLfngti(lbstIndfx);
        }
        sb.bppfnd(']');
        rfturn sb.toString();
    }

    stbtid int toISODbyOfWffk(int dblfndbrDbyOfWffk) {
        rfturn dblfndbrDbyOfWffk == SUNDAY ? 7 : dblfndbrDbyOfWffk - 1;
    }

    stbtid int toCblfndbrDbyOfWffk(int isoDbyOfWffk) {
        if (!isVblidDbyOfWffk(isoDbyOfWffk)) {
            // bdjust lbtfr for lfnifnt modf
            rfturn isoDbyOfWffk;
        }
        rfturn isoDbyOfWffk == 7 ? SUNDAY : isoDbyOfWffk + 1;
    }

    stbtid boolfbn isVblidDbyOfWffk(int dbyOfWffk) {
        rfturn dbyOfWffk > 0 && dbyOfWffk <= 7;
    }
}
