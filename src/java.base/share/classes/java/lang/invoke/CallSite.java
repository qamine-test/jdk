/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

import sun.invokf.fmpty.Empty;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfs.Lookup.IMPL_LOOKUP;

/**
 * A {@dodf CbllSitf} is b ioldfr for b vbribblf {@link MftiodHbndlf},
 * wiidi is dbllfd its {@dodf tbrgft}.
 * An {@dodf invokfdynbmid} instrudtion linkfd to b {@dodf CbllSitf} dflfgbtfs
 * bll dblls to tif sitf's durrfnt tbrgft.
 * A {@dodf CbllSitf} mby bf bssodibtfd witi sfvfrbl {@dodf invokfdynbmid}
 * instrudtions, or it mby bf "frff flobting", bssodibtfd witi nonf.
 * In bny dbsf, it mby bf invokfd tirougi bn bssodibtfd mftiod ibndlf
 * dbllfd its {@linkplbin #dynbmidInvokfr dynbmid invokfr}.
 * <p>
 * {@dodf CbllSitf} is bn bbstrbdt dlbss wiidi dofs not bllow
 * dirfdt subdlbssing by usfrs.  It ibs tirff immfdibtf,
 * dondrftf subdlbssfs tibt mby bf fitifr instbntibtfd or subdlbssfd.
 * <ul>
 * <li>If b mutbblf tbrgft is not rfquirfd, bn {@dodf invokfdynbmid} instrudtion
 * mby bf pfrmbnfntly bound by mfbns of b {@linkplbin ConstbntCbllSitf donstbnt dbll sitf}.
 * <li>If b mutbblf tbrgft is rfquirfd wiidi ibs volbtilf vbribblf sfmbntids,
 * bfdbusf updbtfs to tif tbrgft must bf immfdibtfly bnd rflibbly witnfssfd by otifr tirfbds,
 * b {@linkplbin VolbtilfCbllSitf volbtilf dbll sitf} mby bf usfd.
 * <li>Otifrwisf, if b mutbblf tbrgft is rfquirfd,
 * b {@linkplbin MutbblfCbllSitf mutbblf dbll sitf} mby bf usfd.
 * </ul>
 * <p>
 * A non-donstbnt dbll sitf mby bf <fm>rflinkfd</fm> by dibnging its tbrgft.
 * Tif nfw tbrgft must ibvf tif sbmf {@linkplbin MftiodHbndlf#typf() typf}
 * bs tif prfvious tbrgft.
 * Tius, tiougi b dbll sitf dbn bf rflinkfd to b sfrifs of
 * suddfssivf tbrgfts, it dbnnot dibngf its typf.
 * <p>
 * Hfrf is b sbmplf usf of dbll sitfs bnd bootstrbp mftiods wiidi links fvfry
 * dynbmid dbll sitf to print its brgumfnts:
<blodkquotf><prf>{@dodf
stbtid void tfst() tirows Tirowbblf {
    // THE FOLLOWING LINE IS PSEUDOCODE FOR A JVM INSTRUCTION
    InvokfDynbmid[#bootstrbpDynbmid].bbz("bbz brg", 2, 3.14);
}
privbtf stbtid void printArgs(Objfdt... brgs) {
  Systfm.out.println(jbvb.util.Arrbys.dffpToString(brgs));
}
privbtf stbtid finbl MftiodHbndlf printArgs;
stbtid {
  MftiodHbndlfs.Lookup lookup = MftiodHbndlfs.lookup();
  Clbss tiisClbss = lookup.lookupClbss();  // (wio bm I?)
  printArgs = lookup.findStbtid(tiisClbss,
      "printArgs", MftiodTypf.mftiodTypf(void.dlbss, Objfdt[].dlbss));
}
privbtf stbtid CbllSitf bootstrbpDynbmid(MftiodHbndlfs.Lookup dbllfr, String nbmf, MftiodTypf typf) {
  // ignorf dbllfr bnd nbmf, but mbtdi tif typf:
  rfturn nfw ConstbntCbllSitf(printArgs.bsTypf(typf));
}
}</prf></blodkquotf>
 * @butior Join Rosf, JSR 292 EG
 */
bbstrbdt
publid dlbss CbllSitf {
    stbtid { MftiodHbndlfImpl.initStbtids(); }

    // Tif bdtubl pbylobd of tiis dbll sitf:
    /*pbdkbgf-privbtf*/
    MftiodHbndlf tbrgft;    // Notf: Tiis fifld is known to tif JVM.  Do not dibngf.

    /**
     * Mbkf b blbnk dbll sitf objfdt witi tif givfn mftiod typf.
     * An initibl tbrgft mftiod is supplifd wiidi will tirow
     * bn {@link IllfgblStbtfExdfption} if dbllfd.
     * <p>
     * Bfforf tiis {@dodf CbllSitf} objfdt is rfturnfd from b bootstrbp mftiod,
     * it is usublly providfd witi b morf usfful tbrgft mftiod,
     * vib b dbll to {@link CbllSitf#sftTbrgft(MftiodHbndlf) sftTbrgft}.
     * @tirows NullPointfrExdfption if tif proposfd typf is null
     */
    /*pbdkbgf-privbtf*/
    CbllSitf(MftiodTypf typf) {
        tbrgft = typf.invokfrs().uninitiblizfdCbllSitf();
    }

    /**
     * Mbkf b dbll sitf objfdt fquippfd witi bn initibl tbrgft mftiod ibndlf.
     * @pbrbm tbrgft tif mftiod ibndlf wiidi will bf tif initibl tbrgft of tif dbll sitf
     * @tirows NullPointfrExdfption if tif proposfd tbrgft is null
     */
    /*pbdkbgf-privbtf*/
    CbllSitf(MftiodHbndlf tbrgft) {
        tbrgft.typf();  // null difdk
        tiis.tbrgft = tbrgft;
    }

    /**
     * Mbkf b dbll sitf objfdt fquippfd witi bn initibl tbrgft mftiod ibndlf.
     * @pbrbm tbrgftTypf tif dfsirfd typf of tif dbll sitf
     * @pbrbm drfbtfTbrgftHook b iook wiidi will bind tif dbll sitf to tif tbrgft mftiod ibndlf
     * @tirows WrongMftiodTypfExdfption if tif iook dbnnot bf invokfd on tif rfquirfd brgumfnts,
     *         or if tif tbrgft rfturnfd by tif iook is not of tif givfn {@dodf tbrgftTypf}
     * @tirows NullPointfrExdfption if tif iook rfturns b null vbluf
     * @tirows ClbssCbstExdfption if tif iook rfturns somftiing otifr tibn b {@dodf MftiodHbndlf}
     * @tirows Tirowbblf bnytiing flsf tirown by tif iook fundtion
     */
    /*pbdkbgf-privbtf*/
    CbllSitf(MftiodTypf tbrgftTypf, MftiodHbndlf drfbtfTbrgftHook) tirows Tirowbblf {
        tiis(tbrgftTypf);
        ConstbntCbllSitf sflfCCS = (ConstbntCbllSitf) tiis;
        MftiodHbndlf boundTbrgft = (MftiodHbndlf) drfbtfTbrgftHook.invokfWitiArgumfnts(sflfCCS);
        difdkTbrgftCibngf(tiis.tbrgft, boundTbrgft);
        tiis.tbrgft = boundTbrgft;
    }

    /**
     * Rfturns tif typf of tiis dbll sitf's tbrgft.
     * Altiougi tbrgfts mby dibngf, bny dbll sitf's typf is pfrmbnfnt, bnd dbn nfvfr dibngf to bn unfqubl typf.
     * Tif {@dodf sftTbrgft} mftiod fnfordfs tiis invbribnt by rffusing bny nfw tbrgft tibt dofs
     * not ibvf tif prfvious tbrgft's typf.
     * @rfturn tif typf of tif durrfnt tbrgft, wiidi is blso tif typf of bny futurf tbrgft
     */
    publid MftiodTypf typf() {
        // wbrning:  do not dbll gftTbrgft ifrf, bfdbusf CCS.gftTbrgft dbn tirow IllfgblStbtfExdfption
        rfturn tbrgft.typf();
    }

    /**
     * Rfturns tif tbrgft mftiod of tif dbll sitf, bddording to tif
     * bfibvior dffinfd by tiis dbll sitf's spfdifid dlbss.
     * Tif immfdibtf subdlbssfs of {@dodf CbllSitf} dodumfnt tif
     * dlbss-spfdifid bfibviors of tiis mftiod.
     *
     * @rfturn tif durrfnt linkbgf stbtf of tif dbll sitf, its tbrgft mftiod ibndlf
     * @sff ConstbntCbllSitf
     * @sff VolbtilfCbllSitf
     * @sff #sftTbrgft
     * @sff ConstbntCbllSitf#gftTbrgft
     * @sff MutbblfCbllSitf#gftTbrgft
     * @sff VolbtilfCbllSitf#gftTbrgft
     */
    publid bbstrbdt MftiodHbndlf gftTbrgft();

    /**
     * Updbtfs tif tbrgft mftiod of tiis dbll sitf, bddording to tif
     * bfibvior dffinfd by tiis dbll sitf's spfdifid dlbss.
     * Tif immfdibtf subdlbssfs of {@dodf CbllSitf} dodumfnt tif
     * dlbss-spfdifid bfibviors of tiis mftiod.
     * <p>
     * Tif typf of tif nfw tbrgft must bf {@linkplbin MftiodTypf#fqubls fqubl to}
     * tif typf of tif old tbrgft.
     *
     * @pbrbm nfwTbrgft tif nfw tbrgft
     * @tirows NullPointfrExdfption if tif proposfd nfw tbrgft is null
     * @tirows WrongMftiodTypfExdfption if tif proposfd nfw tbrgft
     *         ibs b mftiod typf tibt difffrs from tif prfvious tbrgft
     * @sff CbllSitf#gftTbrgft
     * @sff ConstbntCbllSitf#sftTbrgft
     * @sff MutbblfCbllSitf#sftTbrgft
     * @sff VolbtilfCbllSitf#sftTbrgft
     */
    publid bbstrbdt void sftTbrgft(MftiodHbndlf nfwTbrgft);

    void difdkTbrgftCibngf(MftiodHbndlf oldTbrgft, MftiodHbndlf nfwTbrgft) {
        MftiodTypf oldTypf = oldTbrgft.typf();
        MftiodTypf nfwTypf = nfwTbrgft.typf();  // null difdk!
        if (!nfwTypf.fqubls(oldTypf))
            tirow wrongTbrgftTypf(nfwTbrgft, oldTypf);
    }

    privbtf stbtid WrongMftiodTypfExdfption wrongTbrgftTypf(MftiodHbndlf tbrgft, MftiodTypf typf) {
        rfturn nfw WrongMftiodTypfExdfption(String.vblufOf(tbrgft)+" siould bf of typf "+typf);
    }

    /**
     * Produdfs b mftiod ibndlf fquivblfnt to bn invokfdynbmid instrudtion
     * wiidi ibs bffn linkfd to tiis dbll sitf.
     * <p>
     * Tiis mftiod is fquivblfnt to tif following dodf:
     * <blodkquotf><prf>{@dodf
     * MftiodHbndlf gftTbrgft, invokfr, rfsult;
     * gftTbrgft = MftiodHbndlfs.publidLookup().bind(tiis, "gftTbrgft", MftiodTypf.mftiodTypf(MftiodHbndlf.dlbss));
     * invokfr = MftiodHbndlfs.fxbdtInvokfr(tiis.typf());
     * rfsult = MftiodHbndlfs.foldArgumfnts(invokfr, gftTbrgft)
     * }</prf></blodkquotf>
     *
     * @rfturn b mftiod ibndlf wiidi blwbys invokfs tiis dbll sitf's durrfnt tbrgft
     */
    publid bbstrbdt MftiodHbndlf dynbmidInvokfr();

    /*non-publid*/ MftiodHbndlf mbkfDynbmidInvokfr() {
        MftiodHbndlf gftTbrgft = GET_TARGET.bindRfdfivfr(tiis);
        MftiodHbndlf invokfr = MftiodHbndlfs.fxbdtInvokfr(tiis.typf());
        rfturn MftiodHbndlfs.foldArgumfnts(invokfr, gftTbrgft);
    }

    privbtf stbtid finbl MftiodHbndlf GET_TARGET;
    stbtid {
        try {
            GET_TARGET = IMPL_LOOKUP.
                findVirtubl(CbllSitf.dlbss, "gftTbrgft", MftiodTypf.mftiodTypf(MftiodHbndlf.dlbss));
        } dbtdi (RfflfdtivfOpfrbtionExdfption f) {
            tirow nfwIntfrnblError(f);
        }
    }

    /** Tiis guy is rollfd into tif dffbult tbrgft if b MftiodTypf is supplifd to tif donstrudtor. */
    /*pbdkbgf-privbtf*/
    stbtid Empty uninitiblizfdCbllSitf() {
        tirow nfw IllfgblStbtfExdfption("uninitiblizfd dbll sitf");
    }

    // unsbff stuff:
    privbtf stbtid finbl long TARGET_OFFSET;
    stbtid {
        try {
            TARGET_OFFSET = UNSAFE.objfdtFifldOffsft(CbllSitf.dlbss.gftDfdlbrfdFifld("tbrgft"));
        } dbtdi (Exdfption fx) { tirow nfw Error(fx); }
    }

    /*pbdkbgf-privbtf*/
    void sftTbrgftNormbl(MftiodHbndlf nfwTbrgft) {
        MftiodHbndlfNbtivfs.sftCbllSitfTbrgftNormbl(tiis, nfwTbrgft);
    }
    /*pbdkbgf-privbtf*/
    MftiodHbndlf gftTbrgftVolbtilf() {
        rfturn (MftiodHbndlf) UNSAFE.gftObjfdtVolbtilf(tiis, TARGET_OFFSET);
    }
    /*pbdkbgf-privbtf*/
    void sftTbrgftVolbtilf(MftiodHbndlf nfwTbrgft) {
        MftiodHbndlfNbtivfs.sftCbllSitfTbrgftVolbtilf(tiis, nfwTbrgft);
    }

    // tiis implfmfnts tif updbll from tif JVM, MftiodHbndlfNbtivfs.mbkfDynbmidCbllSitf:
    stbtid CbllSitf mbkfSitf(MftiodHbndlf bootstrbpMftiod,
                             // Cbllff informbtion:
                             String nbmf, MftiodTypf typf,
                             // Extrb brgumfnts for BSM, if bny:
                             Objfdt info,
                             // Cbllfr informbtion:
                             Clbss<?> dbllfrClbss) {
        MftiodHbndlfs.Lookup dbllfr = IMPL_LOOKUP.in(dbllfrClbss);
        CbllSitf sitf;
        try {
            Objfdt binding;
            info = mbybfRfBox(info);
            if (info == null) {
                binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf);
            } flsf if (!info.gftClbss().isArrby()) {
                binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf, info);
            } flsf {
                Objfdt[] brgv = (Objfdt[]) info;
                mbybfRfBoxElfmfnts(brgv);
                switdi (brgv.lfngti) {
                dbsf 0:
                    binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf);
                    brfbk;
                dbsf 1:
                    binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf,
                                                     brgv[0]);
                    brfbk;
                dbsf 2:
                    binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf,
                                                     brgv[0], brgv[1]);
                    brfbk;
                dbsf 3:
                    binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf,
                                                     brgv[0], brgv[1], brgv[2]);
                    brfbk;
                dbsf 4:
                    binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf,
                                                     brgv[0], brgv[1], brgv[2], brgv[3]);
                    brfbk;
                dbsf 5:
                    binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf,
                                                     brgv[0], brgv[1], brgv[2], brgv[3], brgv[4]);
                    brfbk;
                dbsf 6:
                    binding = bootstrbpMftiod.invokf(dbllfr, nbmf, typf,
                                                     brgv[0], brgv[1], brgv[2], brgv[3], brgv[4], brgv[5]);
                    brfbk;
                dffbult:
                    finbl int NON_SPREAD_ARG_COUNT = 3;  // (dbllfr, nbmf, typf)
                    if (NON_SPREAD_ARG_COUNT + brgv.lfngti > MftiodTypf.MAX_MH_ARITY)
                        tirow nfw BootstrbpMftiodError("too mbny bootstrbp mftiod brgumfnts");
                    MftiodTypf bsmTypf = bootstrbpMftiod.typf();
                    MftiodTypf invodbtionTypf = MftiodTypf.gfnfridMftiodTypf(NON_SPREAD_ARG_COUNT + brgv.lfngti);
                    MftiodHbndlf typfdBSM = bootstrbpMftiod.bsTypf(invodbtionTypf);
                    MftiodHbndlf sprfbdfr = invodbtionTypf.invokfrs().sprfbdInvokfr(NON_SPREAD_ARG_COUNT);
                    binding = sprfbdfr.invokfExbdt(typfdBSM, (Objfdt)dbllfr, (Objfdt)nbmf, (Objfdt)typf, brgv);
                }
            }
            //Systfm.out.println("BSM for "+nbmf+typf+" => "+binding);
            if (binding instbndfof CbllSitf) {
                sitf = (CbllSitf) binding;
            }  flsf {
                tirow nfw ClbssCbstExdfption("bootstrbp mftiod fbilfd to produdf b CbllSitf");
            }
            if (!sitf.gftTbrgft().typf().fqubls(typf))
                tirow nfw WrongMftiodTypfExdfption("wrong typf: "+sitf.gftTbrgft());
        } dbtdi (Tirowbblf fx) {
            BootstrbpMftiodError bfx;
            if (fx instbndfof BootstrbpMftiodError)
                bfx = (BootstrbpMftiodError) fx;
            flsf
                bfx = nfw BootstrbpMftiodError("dbll sitf initiblizbtion fxdfption", fx);
            tirow bfx;
        }
        rfturn sitf;
    }

    privbtf stbtid Objfdt mbybfRfBox(Objfdt x) {
        if (x instbndfof Intfgfr) {
            int xi = (int) x;
            if (xi == (bytf) xi)
                x = xi;  // must rfbox; sff JLS 5.1.7
        }
        rfturn x;
    }
    privbtf stbtid void mbybfRfBoxElfmfnts(Objfdt[] xb) {
        for (int i = 0; i < xb.lfngti; i++) {
            xb[i] = mbybfRfBox(xb[i]);
        }
    }
}
