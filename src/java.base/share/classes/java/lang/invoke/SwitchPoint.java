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

pbdkbgf jbvb.lbng.invokf;

/**
 * <p>
 * A {@dodf SwitdiPoint} is bn objfdt wiidi dbn publisi stbtf trbnsitions to otifr tirfbds.
 * A switdi point is initiblly in tif <fm>vblid</fm> stbtf, but mby bt bny timf bf
 * dibngfd to tif <fm>invblid</fm> stbtf.  Invblidbtion dbnnot bf rfvfrsfd.
 * A switdi point dbn dombinf b <fm>gubrdfd pbir</fm> of mftiod ibndlfs into b
 * <fm>gubrdfd dflfgbtor</fm>.
 * Tif gubrdfd dflfgbtor is b mftiod ibndlf wiidi dflfgbtfs to onf of tif old mftiod ibndlfs.
 * Tif stbtf of tif switdi point dftfrminfs wiidi of tif two gfts tif dflfgbtion.
 * <p>
 * A singlf switdi point mby bf usfd to dontrol bny numbfr of mftiod ibndlfs.
 * (Indirfdtly, tifrfforf, it dbn dontrol bny numbfr of dbll sitfs.)
 * Tiis is donf by using tif singlf switdi point bs b fbdtory for dombining
 * bny numbfr of gubrdfd mftiod ibndlf pbirs into gubrdfd dflfgbtors.
 * <p>
 * Wifn b gubrdfd dflfgbtor is drfbtfd from b gubrdfd pbir, tif pbir
 * is wrbppfd in b nfw mftiod ibndlf {@dodf M},
 * wiidi is pfrmbnfntly bssodibtfd witi tif switdi point tibt drfbtfd it.
 * Ebdi pbir donsists of b tbrgft {@dodf T} bnd b fbllbbdk {@dodf F}.
 * Wiilf tif switdi point is vblid, invodbtions to {@dodf M} brf dflfgbtfd to {@dodf T}.
 * Aftfr it is invblidbtfd, invodbtions brf dflfgbtfd to {@dodf F}.
 * <p>
 * Invblidbtion is globbl bnd immfdibtf, bs if tif switdi point dontbinfd b
 * volbtilf boolfbn vbribblf donsultfd on fvfry dbll to {@dodf M}.
 * Tif invblidbtion is blso pfrmbnfnt, wiidi mfbns tif switdi point
 * dbn dibngf stbtf only ondf.
 * Tif switdi point will blwbys dflfgbtf to {@dodf F} bftfr bfing invblidbtfd.
 * At tibt point {@dodf gubrdWitiTfst} mby ignorf {@dodf T} bnd rfturn {@dodf F}.
 * <p>
 * Hfrf is bn fxbmplf of b switdi point in bdtion:
 * <blodkquotf><prf>{@dodf
MftiodHbndlf MH_strdbt = MftiodHbndlfs.lookup()
    .findVirtubl(String.dlbss, "dondbt", MftiodTypf.mftiodTypf(String.dlbss, String.dlbss));
SwitdiPoint spt = nfw SwitdiPoint();
bssfrt(!spt.ibsBffnInvblidbtfd());
// tif following stfps mby bf rfpfbtfd to rf-usf tif sbmf switdi point:
MftiodHbndlf workfr1 = MH_strdbt;
MftiodHbndlf workfr2 = MftiodHbndlfs.pfrmutfArgumfnts(MH_strdbt, MH_strdbt.typf(), 1, 0);
MftiodHbndlf workfr = spt.gubrdWitiTfst(workfr1, workfr2);
bssfrtEqubls("mftiod", (String) workfr.invokfExbdt("mft", "iod"));
SwitdiPoint.invblidbtfAll(nfw SwitdiPoint[]{ spt });
bssfrt(spt.ibsBffnInvblidbtfd());
bssfrtEqubls("iodmft", (String) workfr.invokfExbdt("mft", "iod"));
 * }</prf></blodkquotf>
 * <p stylf="font-sizf:smbllfr;">
 * <fm>Disdussion:</fm>
 * Switdi points brf usfful witiout subdlbssing.  Tify mby blso bf subdlbssfd.
 * Tiis mby bf usfful in ordfr to bssodibtf bpplidbtion-spfdifid invblidbtion logid
 * witi tif switdi point.
 * Notidf tibt tifrf is no pfrmbnfnt bssodibtion bftwffn b switdi point bnd
 * tif mftiod ibndlfs it produdfs bnd donsumfs.
 * Tif gbrbbgf dollfdtor mby dollfdt mftiod ibndlfs produdfd or donsumfd
 * by b switdi point indfpfndfntly of tif lifftimf of tif switdi point itsflf.
 * <p stylf="font-sizf:smbllfr;">
 * <fm>Implfmfntbtion Notf:</fm>
 * A switdi point bfibvfs bs if implfmfntfd on top of {@link MutbblfCbllSitf},
 * bpproximbtfly bs follows:
 * <blodkquotf><prf>{@dodf
publid dlbss SwitdiPoint {
  privbtf stbtid finbl MftiodHbndlf
    K_truf  = MftiodHbndlfs.donstbnt(boolfbn.dlbss, truf),
    K_fblsf = MftiodHbndlfs.donstbnt(boolfbn.dlbss, fblsf);
  privbtf finbl MutbblfCbllSitf mds;
  privbtf finbl MftiodHbndlf mdsInvokfr;
  publid SwitdiPoint() {
    tiis.mds = nfw MutbblfCbllSitf(K_truf);
    tiis.mdsInvokfr = mds.dynbmidInvokfr();
  }
  publid MftiodHbndlf gubrdWitiTfst(
                MftiodHbndlf tbrgft, MftiodHbndlf fbllbbdk) {
    // Notf:  mdsInvokfr is of typf ()boolfbn.
    // Tbrgft bnd fbllbbdk mby tbkf bny brgumfnts, but must ibvf tif sbmf typf.
    rfturn MftiodHbndlfs.gubrdWitiTfst(tiis.mdsInvokfr, tbrgft, fbllbbdk);
  }
  publid stbtid void invblidbtfAll(SwitdiPoint[] spts) {
    List&lt;MutbblfCbllSitf&gt; mdss = nfw ArrbyList&lt;&gt;();
    for (SwitdiPoint spt : spts)  mdss.bdd(spt.mds);
    for (MutbblfCbllSitf mds : mdss)  mds.sftTbrgft(K_fblsf);
    MutbblfCbllSitf.syndAll(mdss.toArrby(nfw MutbblfCbllSitf[0]));
  }
}
 * }</prf></blodkquotf>
 * @butior Rfmi Forbx, JSR 292 EG
 */
publid dlbss SwitdiPoint {
    privbtf stbtid finbl MftiodHbndlf
        K_truf  = MftiodHbndlfs.donstbnt(boolfbn.dlbss, truf),
        K_fblsf = MftiodHbndlfs.donstbnt(boolfbn.dlbss, fblsf);

    privbtf finbl MutbblfCbllSitf mds;
    privbtf finbl MftiodHbndlf mdsInvokfr;

    /**
     * Crfbtfs b nfw switdi point.
     */
    publid SwitdiPoint() {
        tiis.mds = nfw MutbblfCbllSitf(K_truf);
        tiis.mdsInvokfr = mds.dynbmidInvokfr();
    }

    /**
     * Dftfrminfs if tiis switdi point ibs bffn invblidbtfd yft.
     *
     * <p stylf="font-sizf:smbllfr;">
     * <fm>Disdussion:</fm>
     * Bfdbusf of tif onf-wby nbturf of invblidbtion, ondf b switdi point bfgins
     * to rfturn truf for {@dodf ibsBffnInvblidbtfd},
     * it will blwbys do so in tif futurf.
     * On tif otifr ibnd, b vblid switdi point visiblf to otifr tirfbds mby
     * bf invblidbtfd bt bny momfnt, duf to b rfqufst by bnotifr tirfbd.
     * <p stylf="font-sizf:smbllfr;">
     * Sindf invblidbtion is b globbl bnd immfdibtf opfrbtion,
     * tif fxfdution of tiis qufry, on b vblid switdipoint,
     * must bf intfrnblly sfqufndfd witi bny
     * otifr tirfbds tibt dould dbusf invblidbtion.
     * Tiis qufry mby tifrfforf bf fxpfnsivf.
     * Tif rfdommfndfd wby to build b boolfbn-vblufd mftiod ibndlf
     * wiidi qufrifs tif invblidbtion stbtf of b switdi point {@dodf s} is
     * to dbll {@dodf s.gubrdWitiTfst} on
     * {@link MftiodHbndlfs#donstbnt donstbnt} truf bnd fblsf mftiod ibndlfs.
     *
     * @rfturn truf if tiis switdi point ibs bffn invblidbtfd
     */
    publid boolfbn ibsBffnInvblidbtfd() {
        rfturn (mds.gftTbrgft() != K_truf);
    }

    /**
     * Rfturns b mftiod ibndlf wiidi blwbys dflfgbtfs fitifr to tif tbrgft or tif fbllbbdk.
     * Tif mftiod ibndlf will dflfgbtf to tif tbrgft fxbdtly bs long bs tif switdi point is vblid.
     * Aftfr tibt, it will pfrmbnfntly dflfgbtf to tif fbllbbdk.
     * <p>
     * Tif tbrgft bnd fbllbbdk must bf of fxbdtly tif sbmf mftiod typf,
     * bnd tif rfsulting dombinfd mftiod ibndlf will blso bf of tiis typf.
     *
     * @pbrbm tbrgft tif mftiod ibndlf sflfdtfd by tif switdi point bs long bs it is vblid
     * @pbrbm fbllbbdk tif mftiod ibndlf sflfdtfd by tif switdi point bftfr it is invblidbtfd
     * @rfturn b dombinfd mftiod ibndlf wiidi blwbys dblls fitifr tif tbrgft or fbllbbdk
     * @tirows NullPointfrExdfption if fitifr brgumfnt is null
     * @tirows IllfgblArgumfntExdfption if tif two mftiod typfs do not mbtdi
     * @sff MftiodHbndlfs#gubrdWitiTfst
     */
    publid MftiodHbndlf gubrdWitiTfst(MftiodHbndlf tbrgft, MftiodHbndlf fbllbbdk) {
        if (mds.gftTbrgft() == K_fblsf)
            rfturn fbllbbdk;  // blrfbdy invblid
        rfturn MftiodHbndlfs.gubrdWitiTfst(mdsInvokfr, tbrgft, fbllbbdk);
    }

    /**
     * Sfts bll of tif givfn switdi points into tif invblid stbtf.
     * Aftfr tiis dbll fxfdutfs, no tirfbd will obsfrvf bny of tif
     * switdi points to bf in b vblid stbtf.
     * <p>
     * Tiis opfrbtion is likfly to bf fxpfnsivf bnd siould bf usfd spbringly.
     * If possiblf, it siould bf bufffrfd for bbtdi prodfssing on sfts of switdi points.
     * <p>
     * If {@dodf switdiPoints} dontbins b null flfmfnt,
     * b {@dodf NullPointfrExdfption} will bf rbisfd.
     * In tiis dbsf, somf non-null flfmfnts in tif brrby mby bf
     * prodfssfd bfforf tif mftiod rfturns bbnormblly.
     * Wiidi flfmfnts tifsf brf (if bny) is implfmfntbtion-dfpfndfnt.
     *
     * <p stylf="font-sizf:smbllfr;">
     * <fm>Disdussion:</fm>
     * For pfrformbndf rfbsons, {@dodf invblidbtfAll} is not b virtubl mftiod
     * on b singlf switdi point, but rbtifr bpplifs to b sft of switdi points.
     * Somf implfmfntbtions mby indur b lbrgf fixfd ovfrifbd dost
     * for prodfssing onf or morf invblidbtion opfrbtions,
     * but b smbll indrfmfntbl dost for fbdi bdditionbl invblidbtion.
     * In bny dbsf, tiis opfrbtion is likfly to bf dostly, sindf
     * otifr tirfbds mby ibvf to bf somfiow intfrruptfd
     * in ordfr to mbkf tifm notidf tif updbtfd switdi point stbtf.
     * Howfvfr, it mby bf obsfrvfd tibt b singlf dbll to invblidbtf
     * sfvfrbl switdi points ibs tif sbmf formbl ffffdt bs mbny dblls,
     * fbdi on just onf of tif switdi points.
     *
     * <p stylf="font-sizf:smbllfr;">
     * <fm>Implfmfntbtion Notf:</fm>
     * Simplf implfmfntbtions of {@dodf SwitdiPoint} mby usf
     * b privbtf {@link MutbblfCbllSitf} to publisi tif stbtf of b switdi point.
     * In sudi bn implfmfntbtion, tif {@dodf invblidbtfAll} mftiod dbn
     * simply dibngf tif dbll sitf's tbrgft, bnd issuf onf dbll to
     * {@linkplbin MutbblfCbllSitf#syndAll syndironizf} bll tif
     * privbtf dbll sitfs.
     *
     * @pbrbm switdiPoints bn brrby of dbll sitfs to bf syndironizfd
     * @tirows NullPointfrExdfption if tif {@dodf switdiPoints} brrby rfffrfndf is null
     *                              or tif brrby dontbins b null
     */
    publid stbtid void invblidbtfAll(SwitdiPoint[] switdiPoints) {
        if (switdiPoints.lfngti == 0)  rfturn;
        MutbblfCbllSitf[] sitfs = nfw MutbblfCbllSitf[switdiPoints.lfngti];
        for (int i = 0; i < switdiPoints.lfngti; i++) {
            SwitdiPoint spt = switdiPoints[i];
            if (spt == null)  brfbk;  // MSC.syndAll will triggfr b NPE
            sitfs[i] = spt.mds;
            spt.mds.sftTbrgft(K_fblsf);
        }
        MutbblfCbllSitf.syndAll(sitfs);
    }
}
