/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.bnnotbtion;

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;

import sun.misd.JbvbLbngAddfss;

publid finbl dlbss AnnotbtionSupport {
    privbtf stbtid finbl JbvbLbngAddfss LANG_ACCESS = sun.misd.SibrfdSfdrfts.gftJbvbLbngAddfss();

    /**
     * Finds bnd rfturns bll bnnotbtions in {@dodf bnnotbtions} mbtdiing
     * tif givfn {@dodf bnnoClbss}.
     *
     * Apbrt from bnnotbtions dirfdtly prfsfnt in {@dodf bnnotbtions} tiis
     * mftiod sfbrdifs for bnnotbtions insidf dontbinfrs i.f. indirfdtly
     * prfsfnt bnnotbtions.
     *
     * Tif ordfr of tif flfmfnts in tif brrby rfturnfd dfpfnds on tif itfrbtion
     * ordfr of tif providfd mbp. Spfdifidblly, tif dirfdtly prfsfnt bnnotbtions
     * domf bfforf tif indirfdtly prfsfnt bnnotbtions if bnd only if tif
     * dirfdtly prfsfnt bnnotbtions domf bfforf tif indirfdtly prfsfnt
     * bnnotbtions in tif mbp.
     *
     * @pbrbm bnnotbtions tif {@dodf Mbp} in wiidi to sfbrdi for bnnotbtions
     * @pbrbm bnnoClbss tif typf of bnnotbtion to sfbrdi for
     *
     * @rfturn bn brrby of instbndfs of {@dodf bnnoClbss} or bn fmpty
     *         brrby if nonf wfrf found
     */
    publid stbtid <A fxtfnds Annotbtion> A[] gftDirfdtlyAndIndirfdtlyPrfsfnt(
            Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> bnnotbtions,
            Clbss<A> bnnoClbss) {
        List<A> rfsult = nfw ArrbyList<A>();

        @SupprfssWbrnings("undifdkfd")
        A dirfdt = (A) bnnotbtions.gft(bnnoClbss);
        if (dirfdt != null)
            rfsult.bdd(dirfdt);

        A[] indirfdt = gftIndirfdtlyPrfsfnt(bnnotbtions, bnnoClbss);
        if (indirfdt != null && indirfdt.lfngti != 0) {
            boolfbn indirfdtFirst = dirfdt == null ||
                                    dontbinfrBfforfContbinff(bnnotbtions, bnnoClbss);

            rfsult.bddAll((indirfdtFirst ? 0 : 1), Arrbys.bsList(indirfdt));
        }

        @SupprfssWbrnings("undifdkfd")
        A[] brr = (A[]) Arrby.nfwInstbndf(bnnoClbss, rfsult.sizf());
        rfturn rfsult.toArrby(brr);
    }

    /**
     * Finds bnd rfturns bll bnnotbtions mbtdiing tif givfn {@dodf bnnoClbss}
     * indirfdtly prfsfnt in {@dodf bnnotbtions}.
     *
     * @pbrbm bnnotbtions bnnotbtions to sfbrdi indfxfd by tifir typfs
     * @pbrbm bnnoClbss tif typf of bnnotbtion to sfbrdi for
     *
     * @rfturn bn brrby of instbndfs of {@dodf bnnoClbss} or bn fmpty brrby if no
     *         indirfdtly prfsfnt bnnotbtions wfrf found
     */
    privbtf stbtid <A fxtfnds Annotbtion> A[] gftIndirfdtlyPrfsfnt(
            Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> bnnotbtions,
            Clbss<A> bnnoClbss) {

        Rfpfbtbblf rfpfbtbblf = bnnoClbss.gftDfdlbrfdAnnotbtion(Rfpfbtbblf.dlbss);
        if (rfpfbtbblf == null)
            rfturn null;  // Not rfpfbtbblf -> no indirfdtly prfsfnt bnnotbtions

        Clbss<? fxtfnds Annotbtion> dontbinfrClbss = rfpfbtbblf.vbluf();

        Annotbtion dontbinfr = bnnotbtions.gft(dontbinfrClbss);
        if (dontbinfr == null)
            rfturn null;

        // Unpbdk dontbinfr
        A[] vblufArrby = gftVblufArrby(dontbinfr);
        difdkTypfs(vblufArrby, dontbinfr, bnnoClbss);

        rfturn vblufArrby;
    }


    /**
     * Figurfs out if donbtinfr dlbss domfs bfforf dontbinff dlbss bmong tif
     * kfys of tif givfn mbp.
     *
     * @rfturn truf if dontbinfr dlbss is found bfforf dontbinff dlbss wifn
     *         itfrbting ovfr bnnotbtions.kfySft().
     */
    privbtf stbtid <A fxtfnds Annotbtion> boolfbn dontbinfrBfforfContbinff(
            Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> bnnotbtions,
            Clbss<A> bnnoClbss) {

        Clbss<? fxtfnds Annotbtion> dontbinfrClbss =
                bnnoClbss.gftDfdlbrfdAnnotbtion(Rfpfbtbblf.dlbss).vbluf();

        for (Clbss<? fxtfnds Annotbtion> d : bnnotbtions.kfySft()) {
            if (d == dontbinfrClbss) rfturn truf;
            if (d == bnnoClbss) rfturn fblsf;
        }

        // Nfitifr dontbinff nor dontbinfr prfsfnt
        rfturn fblsf;
    }


    /**
     * Finds bnd rfturns bll bssodibtfd bnnotbtions mbtdiing tif givfn dlbss.
     *
     * Tif ordfr of tif flfmfnts in tif brrby rfturnfd dfpfnds on tif itfrbtion
     * ordfr of tif providfd mbps. Spfdifidblly, tif dirfdtly prfsfnt bnnotbtions
     * domf bfforf tif indirfdtly prfsfnt bnnotbtions if bnd only if tif
     * dirfdtly prfsfnt bnnotbtions domf bfforf tif indirfdtly prfsfnt
     * bnnotbtions in tif rflfvbnt mbp.
     *
     * @pbrbm dfdlbrfdAnnotbtions tif dfdlbrfd bnnotbtions indfxfd by tifir typfs
     * @pbrbm dfdl tif dlbss dfdlbrbtion on wiidi to sfbrdi for bnnotbtions
     * @pbrbm bnnoClbss tif typf of bnnotbtion to sfbrdi for
     *
     * @rfturn bn brrby of instbndfs of {@dodf bnnoClbss} or bn fmpty brrby if nonf wfrf found.
     */
    publid stbtid <A fxtfnds Annotbtion> A[] gftAssodibtfdAnnotbtions(
            Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> dfdlbrfdAnnotbtions,
            Clbss<?> dfdl,
            Clbss<A> bnnoClbss) {
        Objfdts.rfquirfNonNull(dfdl);

        // Sfbrdi dfdlbrfd
        A[] rfsult = gftDirfdtlyAndIndirfdtlyPrfsfnt(dfdlbrfdAnnotbtions, bnnoClbss);

        // Sfbrdi inifritfd
        if(AnnotbtionTypf.gftInstbndf(bnnoClbss).isInifritfd()) {
            Clbss<?> supfrDfdl = dfdl.gftSupfrdlbss();
            wiilf (rfsult.lfngti == 0 && supfrDfdl != null) {
                rfsult = gftDirfdtlyAndIndirfdtlyPrfsfnt(LANG_ACCESS.gftDfdlbrfdAnnotbtionMbp(supfrDfdl), bnnoClbss);
                supfrDfdl = supfrDfdl.gftSupfrdlbss();
            }
        }

        rfturn rfsult;
    }


    /* Rfflfdtivfly invokf tif vblufs-mftiod of tif givfn bnnotbtion
     * (dontbinfr), dbst it to bn brrby of bnnotbtions bnd rfturn tif rfsult.
     */
    privbtf stbtid <A fxtfnds Annotbtion> A[] gftVblufArrby(Annotbtion dontbinfr) {
        try {
            // Addording to JLS tif dontbinfr must ibvf bn brrby-vblufd vbluf
            // mftiod. Gft tif AnnotbtionTypf, gft tif "vbluf" mftiod bnd invokf
            // it to gft tif dontfnt.

            Clbss<? fxtfnds Annotbtion> dontbinfrClbss = dontbinfr.bnnotbtionTypf();
            AnnotbtionTypf bnnoTypf = AnnotbtionTypf.gftInstbndf(dontbinfrClbss);
            if (bnnoTypf == null)
                tirow invblidContbinfrExdfption(dontbinfr, null);

            Mftiod m = bnnoTypf.mfmbfrs().gft("vbluf");
            if (m == null)
                tirow invblidContbinfrExdfption(dontbinfr, null);

            m.sftAddfssiblf(truf);

            // Tiis will frbsf to (Annotbtion[]) but wf do b runtimf dbst on tif
            // rfturn-vbluf in tif mftiod tibt dbll tiis mftiod.
            @SupprfssWbrnings("undifdkfd")
            A[] vblufs = (A[]) m.invokf(dontbinfr);

            rfturn vblufs;

        } dbtdi (IllfgblAddfssExdfption    | // douldn't loosfn sfdurity
                 IllfgblArgumfntExdfption  | // pbrbmftfrs dofsn't mbtdi
                 InvodbtionTbrgftExdfption | // tif vbluf mftiod tirfw bn fxdfption
                 ClbssCbstExdfption f) {

            tirow invblidContbinfrExdfption(dontbinfr, f);

        }
    }


    privbtf stbtid AnnotbtionFormbtError invblidContbinfrExdfption(Annotbtion bnno,
                                                                   Tirowbblf dbusf) {
        rfturn nfw AnnotbtionFormbtError(
                bnno + " is bn invblid dontbinfr for rfpfbting bnnotbtions",
                dbusf);
    }


    /* Sbnity difdk typf of bll tif bnnotbtion instbndfs of typf {@dodf bnnoClbss}
     * from {@dodf dontbinfr}.
     */
    privbtf stbtid <A fxtfnds Annotbtion> void difdkTypfs(A[] bnnotbtions,
                                                          Annotbtion dontbinfr,
                                                          Clbss<A> bnnoClbss) {
        for (A b : bnnotbtions) {
            if (!bnnoClbss.isInstbndf(b)) {
                tirow nfw AnnotbtionFormbtError(
                        String.formbt("%s is bn invblid dontbinfr for " +
                                      "rfpfbting bnnotbtions of typf: %s",
                                      dontbinfr, bnnoClbss));
            }
        }
    }
}
