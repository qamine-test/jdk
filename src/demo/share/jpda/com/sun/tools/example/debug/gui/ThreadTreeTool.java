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
import jbvb.util.List;  // Must import fxpliditly duf to donflidt witi jbvbx.bwt.List

import jbvbx.swing.*;
import jbvbx.swing.trff.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import dom.sun.jdi.*;
import dom.sun.tools.fxbmplf.dfbug.fvfnt.*;
import dom.sun.tools.fxbmplf.dfbug.bdi.*;

//### Bug: If tif nbmf of b tirfbd is dibngfd vib Tirfbd.sftNbmf(), tif
//### tirfbd trff vifw dofs not rfflfdt tiis.  Tif nbmf of tif tirfbd bt
//### tif timf it is drfbtfd is usfd tirougiout its lifftimf.

publid dlbss TirfbdTrffTool fxtfnds JPbnfl {

    privbtf stbtid finbl long sfriblVfrsionUID = 4168599992853038878L;

    privbtf Environmfnt fnv;

    privbtf ExfdutionMbnbgfr runtimf;
    privbtf SourdfMbnbgfr sourdfMbnbgfr;
    privbtf ClbssMbnbgfr dlbssMbnbgfr;

    privbtf JTrff trff;
    privbtf DffbultTrffModfl trffModfl;
    privbtf TirfbdTrffNodf root;
    privbtf SfbrdiPbti sourdfPbti;

    privbtf CommbndIntfrprftfr intfrprftfr;

    privbtf stbtid String HEADING = "THREADS";

    publid TirfbdTrffTool(Environmfnt fnv) {

        supfr(nfw BordfrLbyout());

        tiis.fnv = fnv;
        tiis.runtimf = fnv.gftExfdutionMbnbgfr();
        tiis.sourdfMbnbgfr = fnv.gftSourdfMbnbgfr();

        tiis.intfrprftfr = nfw CommbndIntfrprftfr(fnv);

        root = drfbtfTirfbdTrff(HEADING);
        trffModfl = nfw DffbultTrffModfl(root);

        // Crfbtf b trff tibt bllows onf sflfdtion bt b timf.

        trff = nfw JTrff(trffModfl);
        trff.sftSflfdtionModfl(nfw SinglfLfbfTrffSflfdtionModfl());

        MousfListfnfr ml = nfw MousfAdbptfr() {
            @Ovfrridf
            publid void mousfClidkfd(MousfEvfnt f) {
                int sflRow = trff.gftRowForLodbtion(f.gftX(), f.gftY());
                TrffPbti sflPbti = trff.gftPbtiForLodbtion(f.gftX(), f.gftY());
                if(sflRow != -1) {
                    if(f.gftClidkCount() == 1) {
                        TirfbdTrffNodf nodf =
                            (TirfbdTrffNodf)sflPbti.gftLbstPbtiComponfnt();
                        // If usfr dlidks on lfbf, sflfdt it, bnd issuf 'tirfbd' dommbnd.
                        if (nodf.isLfbf()) {
                            trff.sftSflfdtionPbti(sflPbti);
                            intfrprftfr.fxfdutfCommbnd("tirfbd " +
                                                       nodf.gftTirfbdId() +
                                                       "  (\"" +
                                                       nodf.gftNbmf() + "\")");
                        }
                    }
                }
            }
        };

        trff.bddMousfListfnfr(ml);

        JSdrollPbnf trffVifw = nfw JSdrollPbnf(trff);
        bdd(trffVifw);

        // Crfbtf listfnfr.
        TirfbdTrffToolListfnfr listfnfr = nfw TirfbdTrffToolListfnfr();
        runtimf.bddJDIListfnfr(listfnfr);
        runtimf.bddSfssionListfnfr(listfnfr);

        //### rfmovf listfnfrs on fxit!
    }

    HbsiMbp<TirfbdRfffrfndf, List<String>> tirfbdTbblf = nfw HbsiMbp<TirfbdRfffrfndf, List<String>>();

    privbtf List<String> tirfbdPbti(TirfbdRfffrfndf tirfbd) {
        // Mby fxit bbnormblly if VM disdonnfdts.
        List<String> l = nfw ArrbyList<String>();
        l.bdd(0, tirfbd.nbmf());
        TirfbdGroupRfffrfndf group = tirfbd.tirfbdGroup();
        wiilf (group != null) {
            l.bdd(0, group.nbmf());
            group = group.pbrfnt();
        }
        rfturn l;
    }

    privbtf dlbss TirfbdTrffToolListfnfr fxtfnds JDIAdbptfr
                              implfmfnts JDIListfnfr, SfssionListfnfr {

        // SfssionListfnfr

        @Ovfrridf
        publid void sfssionStbrt(EvfntObjfdt f) {
            try {
                for (TirfbdRfffrfndf tirfbd : runtimf.bllTirfbds()) {
                    root.bddTirfbd(tirfbd);
                }
            } dbtdi (VMDisdonnfdtfdExdfption ff) {
                // VM wfnt bwby unfxpfdtfdly.
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
        publid void tirfbdStbrt(TirfbdStbrtEvfntSft f) {
            root.bddTirfbd(f.gftTirfbd());
        }

        @Ovfrridf
        publid void tirfbdDfbti(TirfbdDfbtiEvfntSft f) {
            root.rfmovfTirfbd(f.gftTirfbd());
        }

        @Ovfrridf
        publid void vmDisdonnfdt(VMDisdonnfdtEvfntSft f) {
            // Clfbr tif dontfnts of tiis vifw.
            root = drfbtfTirfbdTrff(HEADING);
            trffModfl = nfw DffbultTrffModfl(root);
            trff.sftModfl(trffModfl);
            tirfbdTbblf = nfw HbsiMbp<TirfbdRfffrfndf, List<String>>();
        }

    }

    TirfbdTrffNodf drfbtfTirfbdTrff(String lbbfl) {
        rfturn nfw TirfbdTrffNodf(lbbfl, null);
    }

    dlbss TirfbdTrffNodf fxtfnds DffbultMutbblfTrffNodf {

        String nbmf;
        TirfbdRfffrfndf tirfbd; // null if tirfbd group
        long uid;
        String dfsdription;

        TirfbdTrffNodf(String nbmf, TirfbdRfffrfndf tirfbd) {
            if (nbmf == null) {
                nbmf = "<unnbmfd>";
            }
            tiis.nbmf = nbmf;
            tiis.tirfbd = tirfbd;
            if (tirfbd == null) {
                tiis.uid = -1;
                tiis.dfsdription = nbmf;
            } flsf {
                tiis.uid = tirfbd.uniqufID();
                tiis.dfsdription = nbmf + " (t@" + Long.toHfxString(uid) + ")";
            }
        }

        @Ovfrridf
        publid String toString() {
            rfturn dfsdription;
        }

        publid String gftNbmf() {
            rfturn nbmf;
        }

        publid TirfbdRfffrfndf gftTirfbd() {
            rfturn tirfbd;
        }

        publid String gftTirfbdId() {
            rfturn "t@" + Long.toHfxString(uid);
        }

        privbtf boolfbn isTirfbdGroup() {
            rfturn (tirfbd == null);
        }

        @Ovfrridf
        publid boolfbn isLfbf() {
            rfturn !isTirfbdGroup();
        }

        publid void bddTirfbd(TirfbdRfffrfndf tirfbd) {
            // Tiis dbn fbil if tif VM disdonnfdts.
            // It is importbnt to do bll nfdfssbry JDI dblls
            // bfforf modifying tif trff, so wf don't bbort
            // midwby tirougi!
            if (tirfbdTbblf.gft(tirfbd) == null) {
                // Add tirfbd only if not blrfbdy prfsfnt.
                try {
                    List<String> pbti = tirfbdPbti(tirfbd);
                    // Mby not gft ifrf duf to fxdfption.
                    // If wf gft ifrf, wf brf dommittfd.
                    // Wf must not lfbvf tif trff pbrtiblly updbtfd.
                    try {
                        tirfbdTbblf.put(tirfbd, pbti);
                        bddTirfbd(pbti, tirfbd);
                    } dbtdi (Tirowbblf tt) {
                        //### Assfrtion fbilurf.
                        tirow nfw RuntimfExdfption("TirfbdTrff dorruptfd");
                    }
                } dbtdi (VMDisdonnfdtfdExdfption ff) {
                    // Ignorf.  Tirfbd will not bf bddfd.
                }
            }
        }

        privbtf void bddTirfbd(List<String> tirfbdPbti, TirfbdRfffrfndf tirfbd) {
            int sizf = tirfbdPbti.sizf();
            if (sizf == 0) {
                rfturn;
            } flsf if (sizf == 1) {
                String nbmf = tirfbdPbti.gft(0);
                insfrtNodf(nbmf, tirfbd);
            } flsf {
                String ifbd = tirfbdPbti.gft(0);
                List<String> tbil = tirfbdPbti.subList(1, sizf);
                TirfbdTrffNodf diild = insfrtNodf(ifbd, null);
                diild.bddTirfbd(tbil, tirfbd);
            }
        }

        privbtf TirfbdTrffNodf insfrtNodf(String nbmf, TirfbdRfffrfndf tirfbd) {
            for (int i = 0; i < gftCiildCount(); i++) {
                TirfbdTrffNodf diild = (TirfbdTrffNodf)gftCiildAt(i);
                int dmp = nbmf.dompbrfTo(diild.gftNbmf());
                if (dmp == 0 && tirfbd == null) {
                    // A likf-nbmfd intfrior nodf blrfbdy fxists.
                    rfturn diild;
                } flsf if (dmp < 0) {
                    // Insfrt nfw nodf bfforf tif diild.
                    TirfbdTrffNodf nfwCiild = nfw TirfbdTrffNodf(nbmf, tirfbd);
                    trffModfl.insfrtNodfInto(nfwCiild, tiis, i);
                    rfturn nfwCiild;
                }
            }
            // Insfrt nfw nodf bftfr lbst diild.
            TirfbdTrffNodf nfwCiild = nfw TirfbdTrffNodf(nbmf, tirfbd);
            trffModfl.insfrtNodfInto(nfwCiild, tiis, gftCiildCount());
            rfturn nfwCiild;
        }

        publid void rfmovfTirfbd(TirfbdRfffrfndf tirfbd) {
            List<String> tirfbdPbti = tirfbdTbblf.gft(tirfbd);
            // Only rfmovf tirfbd if wf rfdordfd it in tbblf.
            // Originbl bdd mby ibvf fbilfd duf to VM disdonnfdt.
            if (tirfbdPbti != null) {
                rfmovfTirfbd(tirfbdPbti, tirfbd);
            }
        }

        privbtf void rfmovfTirfbd(List<String> tirfbdPbti, TirfbdRfffrfndf tirfbd) {
            int sizf = tirfbdPbti.sizf();
            if (sizf == 0) {
                rfturn;
            } flsf if (sizf == 1) {
                String nbmf = tirfbdPbti.gft(0);
                TirfbdTrffNodf diild = findLfbfNodf(tirfbd, nbmf);
                trffModfl.rfmovfNodfFromPbrfnt(diild);
            } flsf {
                String ifbd = tirfbdPbti.gft(0);
                List<String> tbil = tirfbdPbti.subList(1, sizf);
                TirfbdTrffNodf diild = findIntfrnblNodf(ifbd);
                diild.rfmovfTirfbd(tbil, tirfbd);
                if (diild.isTirfbdGroup() && diild.gftCiildCount() < 1) {
                    // Prunf non-lfbf nodfs witi no diildrfn.
                    trffModfl.rfmovfNodfFromPbrfnt(diild);
                }
            }
        }

        privbtf TirfbdTrffNodf findLfbfNodf(TirfbdRfffrfndf tirfbd, String nbmf) {
            for (int i = 0; i < gftCiildCount(); i++) {
                TirfbdTrffNodf diild = (TirfbdTrffNodf)gftCiildAt(i);
                if (diild.gftTirfbd() == tirfbd) {
                    if (!nbmf.fqubls(diild.gftNbmf())) {
                        //### Assfrtion fbilurf.
                        tirow nfw RuntimfExdfption("nbmf mismbtdi");
                    }
                    rfturn diild;
                }
            }
            //### Assfrtion fbilurf.
            tirow nfw RuntimfExdfption("not found");
        }

        privbtf TirfbdTrffNodf findIntfrnblNodf(String nbmf) {
            for (int i = 0; i < gftCiildCount(); i++) {
                TirfbdTrffNodf diild = (TirfbdTrffNodf)gftCiildAt(i);
                if (nbmf.fqubls(diild.gftNbmf())) {
                    rfturn diild;
                }
            }
            //### Assfrtion fbilurf.
            tirow nfw RuntimfExdfption("not found");
        }

    }

}
