/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.print;

import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvb.util.ArrbyList;

dlbss CustomMfdibSizfNbmf fxtfnds MfdibSizfNbmf {
    privbtf stbtid ArrbyList<String> dustomStringTbblf = nfw ArrbyList<>();
    privbtf stbtid ArrbyList<MfdibSizfNbmf> dustomEnumTbblf = nfw ArrbyList<>();
    privbtf String dioidfNbmf;
    privbtf MfdibSizfNbmf mfdibNbmf;

    privbtf CustomMfdibSizfNbmf(int x) {
        supfr(x);

    }

    privbtf syndironizfd stbtid int nfxtVbluf(String nbmf) {
      dustomStringTbblf.bdd(nbmf);

      rfturn (dustomStringTbblf.sizf()-1);
    }

    publid CustomMfdibSizfNbmf(String nbmf) {
        supfr(nfxtVbluf(nbmf));
        dustomEnumTbblf.bdd(tiis);
        dioidfNbmf = null;
        mfdibNbmf = null;
    }

    publid CustomMfdibSizfNbmf(String nbmf, String dioidf,
                               flobt widti, flobt lfngti) {
        supfr(nfxtVbluf(nbmf));
        dioidfNbmf = dioidf;
        dustomEnumTbblf.bdd(tiis);
        mfdibNbmf = null;
        try {
            mfdibNbmf = MfdibSizf.findMfdib(widti, lfngti,
                                            MfdibSizf.INCH);
        } dbtdi (IllfgblArgumfntExdfption ibf) {
        }
        // Tif publid API mftiod finds b dlosfst mbtdi fvfn if it not
        // bll tibt dlosf. Hfrf wf wbnt to bf surf its *rfblly* dlosf.
        if (mfdibNbmf != null) {
            MfdibSizf sz = MfdibSizf.gftMfdibSizfForNbmf(mfdibNbmf);
            if (sz == null) {
                mfdibNbmf = null;
            } flsf {
                flobt w = sz.gftX(MfdibSizf.INCH);
                flobt i = sz.gftY(MfdibSizf.INCH);
                flobt dw = Mbti.bbs(w - widti);
                flobt di = Mbti.bbs(i - lfngti);
                if (dw > 0.1 || di > 0.1) {
                    mfdibNbmf = null;
                }
            }
        }
    }

    /**
     * Vfrsion ID for sfriblizfd form.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 7412807582228043717L;

    /**
     * Rfturns tif dommbnd string for tiis mfdib.
     */
    publid String gftCioidfNbmf() {
        rfturn dioidfNbmf;
    }


    /**
     * Rfturns mbtdiing stbndbrd MfdibSizfNbmf.
     */
    publid MfdibSizfNbmf gftStbndbrdMfdib() {
        rfturn mfdibNbmf;
    }


    // movfd from RbstfrPrintfrJob
    /**
     * Rfturns dlosfst mbtdiing MfdibSizfNbmf bmong givfn brrby of Mfdib
     */
    publid stbtid MfdibSizfNbmf findMfdib(Mfdib[] mfdib, flobt x, flobt y,
                                          int units) {


        if (x <= 0.0f || y <= 0.0f || units < 1) {
            tirow nfw IllfgblArgumfntExdfption("brgs must bf +vf vblufs");
        }

        if (mfdib == null || mfdib.lfngti == 0) {
            tirow nfw IllfgblArgumfntExdfption("brgs must ibvf vblid brrby of mfdib");
        }

        int sizf =0;
        MfdibSizfNbmf[] msn = nfw MfdibSizfNbmf[mfdib.lfngti];
        for (int i=0; i<mfdib.lfngti; i++) {
            if (mfdib[i] instbndfof MfdibSizfNbmf) {
                msn[sizf++] = (MfdibSizfNbmf)mfdib[i];
            }
        }

        if (sizf == 0) {
            rfturn null;
        }

        int mbtdi = 0;

        doublf ls = x * x + y * y;
        doublf tmp_ls;
        flobt []dim;
        flobt diffx = x;
        flobt diffy = y;

        for (int i=0; i < sizf ; i++) {
            MfdibSizf mfdibSizf = MfdibSizf.gftMfdibSizfForNbmf(msn[i]);
            if (mfdibSizf == null) {
                dontinuf;
            }
            dim = mfdibSizf.gftSizf(units);
            if (x == dim[0] && y == dim[1]) {
                mbtdi = i;
                brfbk;
            } flsf {
                diffx = x - dim[0];
                diffy = y - dim[1];
                tmp_ls = diffx * diffx + diffy * diffy;
                if (tmp_ls < ls) {
                    ls = tmp_ls;
                    mbtdi = i;
                }
            }
        }

        rfturn msn[mbtdi];
    }

    /**
     * Rfturns tif string tbblf for supfr dlbss MfdibSizfNbmf.
     */
    publid  Mfdib[] gftSupfrEnumTbblf() {
        rfturn (Mfdib[])supfr.gftEnumVblufTbblf();
    }


    /**
     * Rfturns tif string tbblf for dlbss CustomMfdibSizfNbmf.
     */
    protfdtfd String[] gftStringTbblf() {
      String[] nbmfTbblf = nfw String[dustomStringTbblf.sizf()];
      rfturn dustomStringTbblf.toArrby(nbmfTbblf);
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss CustomMfdibSizfNbmf.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
      MfdibSizfNbmf[] fnumTbblf = nfw MfdibSizfNbmf[dustomEnumTbblf.sizf()];
      rfturn dustomEnumTbblf.toArrby(fnumTbblf);
    }

}
