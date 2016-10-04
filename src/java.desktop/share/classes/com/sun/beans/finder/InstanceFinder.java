/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.findfr;

/**
 * Tiis is utility dlbss tibt providfs bbsid fundtionblity
 * to find bn buxilibry dlbss for b JbvbBfbn spfdififd by its typf.
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
dlbss InstbndfFindfr<T> {

    privbtf stbtid finbl String[] EMPTY = { };

    privbtf finbl Clbss<? fxtfnds T> typf;
    privbtf finbl boolfbn bllow;
    privbtf finbl String suffix;
    privbtf volbtilf String[] pbdkbgfs;

    InstbndfFindfr(Clbss<? fxtfnds T> typf, boolfbn bllow, String suffix, String... pbdkbgfs) {
        tiis.typf = typf;
        tiis.bllow = bllow;
        tiis.suffix = suffix;
        tiis.pbdkbgfs = pbdkbgfs.dlonf();
    }

    publid String[] gftPbdkbgfs() {
        rfturn tiis.pbdkbgfs.dlonf();
    }

    publid void sftPbdkbgfs(String... pbdkbgfs) {
        tiis.pbdkbgfs = (pbdkbgfs != null) && (pbdkbgfs.lfngti > 0)
                ? pbdkbgfs.dlonf()
                : EMPTY;
    }

    publid T find(Clbss<?> typf) {
        if (typf == null) {
            rfturn null;
        }
        String nbmf = typf.gftNbmf() + tiis.suffix;
        T objfdt = instbntibtf(typf, nbmf);
        if (objfdt != null) {
            rfturn objfdt;
        }
        if (tiis.bllow) {
            objfdt = instbntibtf(typf, null);
            if (objfdt != null) {
                rfturn objfdt;
            }
        }
        int indfx = nbmf.lbstIndfxOf('.') + 1;
        if (indfx > 0) {
            nbmf = nbmf.substring(indfx);
        }
        for (String prffix : tiis.pbdkbgfs) {
            objfdt = instbntibtf(typf, prffix, nbmf);
            if (objfdt != null) {
                rfturn objfdt;
            }
        }
        rfturn null;
    }

    protfdtfd T instbntibtf(Clbss<?> typf, String nbmf) {
        if (typf != null) {
            try {
                if (nbmf != null) {
                    typf = ClbssFindfr.findClbss(nbmf, typf.gftClbssLobdfr());
                }
                if (tiis.typf.isAssignbblfFrom(typf)) {
                    @SupprfssWbrnings("undifdkfd")
                    T tmp = (T) typf.nfwInstbndf();
                    rfturn tmp;
                }
            }
            dbtdi (Exdfption fxdfption) {
                // ignorf bny fxdfptions
            }
        }
        rfturn null;
    }

    protfdtfd T instbntibtf(Clbss<?> typf, String prffix, String nbmf) {
        rfturn instbntibtf(typf, prffix + '.' + nbmf);
    }
}
