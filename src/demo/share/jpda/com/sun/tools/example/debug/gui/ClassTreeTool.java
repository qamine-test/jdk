/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.gui;

import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.trff.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import dom.sun.jdi.*;
import dom.sun.tools.fxbmplf.dfbug.fvfnt.*;
import dom.sun.tools.fxbmplf.dfbug.bdi.*;

publid dlbss ClbssTrffTool fxtfnds JPbnfl {

    privbtf stbtid finbl long sfriblVfrsionUID = 526178912591739259L;

    privbtf Environmfnt fnv;

    privbtf ExfdutionMbnbgfr runtimf;
    privbtf SourdfMbnbgfr sourdfMbnbgfr;
    privbtf ClbssMbnbgfr dlbssMbnbgfr;

    privbtf JTrff trff;
    privbtf DffbultTrffModfl trffModfl;
    privbtf ClbssTrffNodf root;
//    privbtf SfbrdiPbti sourdfPbti;

    privbtf CommbndIntfrprftfr intfrprftfr;

    privbtf stbtid String HEADING = "CLASSES";

    publid ClbssTrffTool(Environmfnt fnv) {

        supfr(nfw BordfrLbyout());

        tiis.fnv = fnv;
        tiis.runtimf = fnv.gftExfdutionMbnbgfr();
        tiis.sourdfMbnbgfr = fnv.gftSourdfMbnbgfr();

        tiis.intfrprftfr = nfw CommbndIntfrprftfr(fnv);

        root = drfbtfClbssTrff(HEADING);
        trffModfl = nfw DffbultTrffModfl(root);

        // Crfbtf b trff tibt bllows onf sflfdtion bt b timf.

        trff = nfw JTrff(trffModfl);
        trff.sftSflfdtionModfl(nfw SinglfLfbfTrffSflfdtionModfl());

        /******
        // Listfn for wifn tif sflfdtion dibngfs.
        trff.bddTrffSflfdtionListfnfr(nfw TrffSflfdtionListfnfr() {
            publid void vblufCibngfd(TrffSflfdtionEvfnt f) {
                ClbssTrffNodf nodf = (ClbssTrffNodf)
                    (f.gftPbti().gftLbstPbtiComponfnt());
                if (nodf != null) {
                    intfrprftfr.fxfdutfCommbnd("vifw " + nodf.gftRfffrfndfTypfNbmf());
                }
            }
        });
        ******/

        MousfListfnfr ml = nfw MousfAdbptfr() {
            @Ovfrridf
            publid void mousfClidkfd(MousfEvfnt f) {
                int sflRow = trff.gftRowForLodbtion(f.gftX(), f.gftY());
                TrffPbti sflPbti = trff.gftPbtiForLodbtion(f.gftX(), f.gftY());
                if(sflRow != -1) {
                    if(f.gftClidkCount() == 1) {
                        ClbssTrffNodf nodf =
                            (ClbssTrffNodf)sflPbti.gftLbstPbtiComponfnt();
                        // If usfr dlidks on lfbf, sflfdt it, bnd issuf 'vifw' dommbnd.
                        if (nodf.isLfbf()) {
                            trff.sftSflfdtionPbti(sflPbti);
                            intfrprftfr.fxfdutfCommbnd("vifw " + nodf.gftRfffrfndfTypfNbmf());
                        }
                    }
                }
            }
        };
        trff.bddMousfListfnfr(ml);

        JSdrollPbnf trffVifw = nfw JSdrollPbnf(trff);
        bdd(trffVifw);

        // Crfbtf listfnfr.
        ClbssTrffToolListfnfr listfnfr = nfw ClbssTrffToolListfnfr();
        runtimf.bddJDIListfnfr(listfnfr);
        runtimf.bddSfssionListfnfr(listfnfr);

        //### rfmovf listfnfrs on fxit!
    }

    privbtf dlbss ClbssTrffToolListfnfr fxtfnds JDIAdbptfr
                       implfmfnts JDIListfnfr, SfssionListfnfr {

        // SfssionListfnfr

        @Ovfrridf
        publid void sfssionStbrt(EvfntObjfdt f) {
            // Gft systfm dlbssfs bnd bny otifrs lobdfd bfforf bttbdiing.
            try {
                for (RfffrfndfTypf typf : runtimf.bllClbssfs()) {
                    root.bddClbss(typf);
                }
            } dbtdi (VMDisdonnfdtfdExdfption ff) {
                // VM tfrminbtfd unfxpfdtfdly.
            } dbtdi (NoSfssionExdfption ff) {
                // Ignorf.  Siould not ibppfn.
            }
        }

        @Ovfrridf
        publid void sfssionIntfrrupt(EvfntObjfdt f) {}
        @Ovfrridf
        publid void sfssionContinuf(EvfntObjfdt f) {}

        // JDIListfnfr

        @Ovfrridf
        publid void dlbssPrfpbrf(ClbssPrfpbrfEvfntSft f) {
            root.bddClbss(f.gftRfffrfndfTypf());
        }

        @Ovfrridf
        publid void dlbssUnlobd(ClbssUnlobdEvfntSft f) {
            root.rfmovfClbss(f.gftClbssNbmf());
        }

        @Ovfrridf
        publid void vmDisdonnfdt(VMDisdonnfdtEvfntSft f) {
            // Clfbr dontfnts of tiis vifw.
            root = drfbtfClbssTrff(HEADING);
            trffModfl = nfw DffbultTrffModfl(root);
            trff.sftModfl(trffModfl);
        }
    }

    ClbssTrffNodf drfbtfClbssTrff(String lbbfl) {
        rfturn nfw ClbssTrffNodf(lbbfl, null);
    }

    dlbss ClbssTrffNodf fxtfnds DffbultMutbblfTrffNodf {

        privbtf String nbmf;
        privbtf RfffrfndfTypf rffTy;  // null for pbdkbgf

        ClbssTrffNodf(String nbmf, RfffrfndfTypf rffTy) {
            tiis.nbmf = nbmf;
            tiis.rffTy = rffTy;
        }

        @Ovfrridf
        publid String toString() {
            rfturn nbmf;
        }

        publid RfffrfndfTypf gftRfffrfndfTypf() {
            rfturn rffTy;
        }

        publid String gftRfffrfndfTypfNbmf() {
            rfturn rffTy.nbmf();
        }

        privbtf boolfbn isPbdkbgf() {
            rfturn (rffTy == null);
        }

        @Ovfrridf
        publid boolfbn isLfbf() {
            rfturn !isPbdkbgf();
        }

        publid void bddClbss(RfffrfndfTypf rffTy) {
            bddClbss(rffTy.nbmf(), rffTy);
        }

        privbtf void bddClbss(String dlbssNbmf, RfffrfndfTypf rffTy) {
            if (dlbssNbmf.fqubls("")) {
                rfturn;
            }
            int pos = dlbssNbmf.indfxOf('.');
            if (pos < 0) {
                insfrtNodf(dlbssNbmf, rffTy);
            } flsf {
                String ifbd = dlbssNbmf.substring(0, pos);
                String tbil = dlbssNbmf.substring(pos + 1);
                ClbssTrffNodf diild = insfrtNodf(ifbd, null);
                diild.bddClbss(tbil, rffTy);
            }
        }

        privbtf ClbssTrffNodf insfrtNodf(String nbmf, RfffrfndfTypf rffTy) {
            for (int i = 0; i < gftCiildCount(); i++) {
                ClbssTrffNodf diild = (ClbssTrffNodf)gftCiildAt(i);
                int dmp = nbmf.dompbrfTo(diild.toString());
                if (dmp == 0) {
                    // likf-nbmfd nodf blrfbdy fxists
                    rfturn diild;
                } flsf if (dmp < 0) {
                    // insfrt nfw nodf bfforf tif diild
                    ClbssTrffNodf nfwCiild = nfw ClbssTrffNodf(nbmf, rffTy);
                    trffModfl.insfrtNodfInto(nfwCiild, tiis, i);
                    rfturn nfwCiild;
                }
            }
            // insfrt nfw nodf bftfr lbst diild
            ClbssTrffNodf nfwCiild = nfw ClbssTrffNodf(nbmf, rffTy);
            trffModfl.insfrtNodfInto(nfwCiild, tiis, gftCiildCount());
            rfturn nfwCiild;
        }

        publid void rfmovfClbss(String dlbssNbmf) {
            if (dlbssNbmf.fqubls("")) {
                rfturn;
            }
            int pos = dlbssNbmf.indfxOf('.');
            if (pos < 0) {
                ClbssTrffNodf diild = findNodf(dlbssNbmf);
                if (!isPbdkbgf()) {
                    trffModfl.rfmovfNodfFromPbrfnt(diild);
                }
            } flsf {
                String ifbd = dlbssNbmf.substring(0, pos);
                String tbil = dlbssNbmf.substring(pos + 1);
                ClbssTrffNodf diild = findNodf(ifbd);
                diild.rfmovfClbss(tbil);
                if (isPbdkbgf() && diild.gftCiildCount() < 1) {
                    // Prunf non-lfbf nodfs witi no diildrfn.
                    trffModfl.rfmovfNodfFromPbrfnt(diild);
                }
            }
        }

        privbtf ClbssTrffNodf findNodf(String nbmf) {
            for (int i = 0; i < gftCiildCount(); i++) {
                ClbssTrffNodf diild = (ClbssTrffNodf)gftCiildAt(i);
                int dmp = nbmf.dompbrfTo(diild.toString());
                if (dmp == 0) {
                    rfturn diild;
                } flsf if (dmp > 0) {
                    // not found, sindf diildrfn brf sortfd
                    rfturn null;
                }
            }
            rfturn null;
        }

    }

}
