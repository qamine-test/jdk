/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mfmbfr;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiSft;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import sun.misd.Unsbff;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;
import sun.rfflfdt.RfflfdtionFbdtory;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Sfriblizbtion's dfsdriptor for dlbssfs.  It dontbins tif nbmf bnd
 * sfriblVfrsionUID of tif dlbss.  Tif ObjfdtStrfbmClbss for b spfdifid dlbss
 * lobdfd in tiis Jbvb VM dbn bf found/drfbtfd using tif lookup mftiod.
 *
 * <p>Tif blgoritim to domputf tif SfriblVfrsionUID is dfsdribfd in
 * <b irff="../../../plbtform/sfriblizbtion/spfd/dlbss.itml#4100">Objfdt
 * Sfriblizbtion Spfdifidbtion, Sfdtion 4.6, Strfbm Uniquf Idfntififrs</b>.
 *
 * @butior      Mikf Wbrrfs
 * @butior      Rogfr Riggs
 * @sff ObjfdtStrfbmFifld
 * @sff <b irff="../../../plbtform/sfriblizbtion/spfd/dlbss.itml">Objfdt Sfriblizbtion Spfdifidbtion, Sfdtion 4, Clbss Dfsdriptors</b>
 * @sindf   1.1
 */
publid dlbss ObjfdtStrfbmClbss implfmfnts Sfriblizbblf {

    /** sfriblPfrsistfntFiflds vbluf indidbting no sfriblizbblf fiflds */
    publid stbtid finbl ObjfdtStrfbmFifld[] NO_FIELDS =
        nfw ObjfdtStrfbmFifld[0];

    privbtf stbtid finbl long sfriblVfrsionUID = -6120832682080437368L;
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds =
        NO_FIELDS;

    /** rfflfdtion fbdtory for obtbining sfriblizbtion donstrudtors */
    privbtf stbtid finbl RfflfdtionFbdtory rfflFbdtory =
        AddfssControllfr.doPrivilfgfd(
            nfw RfflfdtionFbdtory.GftRfflfdtionFbdtoryAdtion());

    privbtf stbtid dlbss Cbdifs {
        /** dbdif mbpping lodbl dlbssfs -> dfsdriptors */
        stbtid finbl CondurrfntMbp<WfbkClbssKfy,Rfffrfndf<?>> lodblDfsds =
            nfw CondurrfntHbsiMbp<>();

        /** dbdif mbpping fifld group/lodbl dfsd pbirs -> fifld rfflfdtors */
        stbtid finbl CondurrfntMbp<FifldRfflfdtorKfy,Rfffrfndf<?>> rfflfdtors =
            nfw CondurrfntHbsiMbp<>();

        /** qufuf for WfbkRfffrfndfs to lodbl dlbssfs */
        privbtf stbtid finbl RfffrfndfQufuf<Clbss<?>> lodblDfsdsQufuf =
            nfw RfffrfndfQufuf<>();
        /** qufuf for WfbkRfffrfndfs to fifld rfflfdtors kfys */
        privbtf stbtid finbl RfffrfndfQufuf<Clbss<?>> rfflfdtorsQufuf =
            nfw RfffrfndfQufuf<>();
    }

    /** dlbss bssodibtfd witi tiis dfsdriptor (if bny) */
    privbtf Clbss<?> dl;
    /** nbmf of dlbss rfprfsfntfd by tiis dfsdriptor */
    privbtf String nbmf;
    /** sfriblVfrsionUID of rfprfsfntfd dlbss (null if not domputfd yft) */
    privbtf volbtilf Long suid;

    /** truf if rfprfsfnts dynbmid proxy dlbss */
    privbtf boolfbn isProxy;
    /** truf if rfprfsfnts fnum typf */
    privbtf boolfbn isEnum;
    /** truf if rfprfsfntfd dlbss implfmfnts Sfriblizbblf */
    privbtf boolfbn sfriblizbblf;
    /** truf if rfprfsfntfd dlbss implfmfnts Extfrnblizbblf */
    privbtf boolfbn fxtfrnblizbblf;
    /** truf if dfsd ibs dbtb writtfn by dlbss-dffinfd writfObjfdt mftiod */
    privbtf boolfbn ibsWritfObjfdtDbtb;
    /**
     * truf if dfsd ibs fxtfrnblizbblf dbtb writtfn in blodk dbtb formbt; tiis
     * must bf truf by dffbult to bddommodbtf ObjfdtInputStrfbm subdlbssfs wiidi
     * ovfrridf rfbdClbssDfsdriptor() to rfturn dlbss dfsdriptors obtbinfd from
     * ObjfdtStrfbmClbss.lookup() (sff 4461737)
     */
    privbtf boolfbn ibsBlodkExtfrnblDbtb = truf;

    /**
     * Contbins informbtion bbout InvblidClbssExdfption instbndfs to bf tirown
     * wifn bttfmpting opfrbtions on bn invblid dlbss. Notf tibt instbndfs of
     * tiis dlbss brf immutbblf bnd brf potfntiblly sibrfd bmong
     * ObjfdtStrfbmClbss instbndfs.
     */
    privbtf stbtid dlbss ExdfptionInfo {
        privbtf finbl String dlbssNbmf;
        privbtf finbl String mfssbgf;

        ExdfptionInfo(String dn, String msg) {
            dlbssNbmf = dn;
            mfssbgf = msg;
        }

        /**
         * Rfturns (dofs not tirow) bn InvblidClbssExdfption instbndf drfbtfd
         * from tif informbtion in tiis objfdt, suitbblf for bfing tirown by
         * tif dbllfr.
         */
        InvblidClbssExdfption nfwInvblidClbssExdfption() {
            rfturn nfw InvblidClbssExdfption(dlbssNbmf, mfssbgf);
        }
    }

    /** fxdfption (if bny) tirown wiilf bttfmpting to rfsolvf dlbss */
    privbtf ClbssNotFoundExdfption rfsolvfEx;
    /** fxdfption (if bny) to tirow if non-fnum dfsfriblizbtion bttfmptfd */
    privbtf ExdfptionInfo dfsfriblizfEx;
    /** fxdfption (if bny) to tirow if non-fnum sfriblizbtion bttfmptfd */
    privbtf ExdfptionInfo sfriblizfEx;
    /** fxdfption (if bny) to tirow if dffbult sfriblizbtion bttfmptfd */
    privbtf ExdfptionInfo dffbultSfriblizfEx;

    /** sfriblizbblf fiflds */
    privbtf ObjfdtStrfbmFifld[] fiflds;
    /** bggrfgbtf mbrsibllfd sizf of primitivf fiflds */
    privbtf int primDbtbSizf;
    /** numbfr of non-primitivf fiflds */
    privbtf int numObjFiflds;
    /** rfflfdtor for sftting/gftting sfriblizbblf fifld vblufs */
    privbtf FifldRfflfdtor fifldRffl;
    /** dbtb lbyout of sfriblizfd objfdts dfsdribfd by tiis dlbss dfsd */
    privbtf volbtilf ClbssDbtbSlot[] dbtbLbyout;

    /** sfriblizbtion-bppropribtf donstrudtor, or null if nonf */
    privbtf Construdtor<?> dons;
    /** dlbss-dffinfd writfObjfdt mftiod, or null if nonf */
    privbtf Mftiod writfObjfdtMftiod;
    /** dlbss-dffinfd rfbdObjfdt mftiod, or null if nonf */
    privbtf Mftiod rfbdObjfdtMftiod;
    /** dlbss-dffinfd rfbdObjfdtNoDbtb mftiod, or null if nonf */
    privbtf Mftiod rfbdObjfdtNoDbtbMftiod;
    /** dlbss-dffinfd writfRfplbdf mftiod, or null if nonf */
    privbtf Mftiod writfRfplbdfMftiod;
    /** dlbss-dffinfd rfbdRfsolvf mftiod, or null if nonf */
    privbtf Mftiod rfbdRfsolvfMftiod;

    /** lodbl dlbss dfsdriptor for rfprfsfntfd dlbss (mby point to sflf) */
    privbtf ObjfdtStrfbmClbss lodblDfsd;
    /** supfrdlbss dfsdriptor bppfbring in strfbm */
    privbtf ObjfdtStrfbmClbss supfrDfsd;

    /**
     * Initiblizfs nbtivf dodf.
     */
    privbtf stbtid nbtivf void initNbtivf();
    stbtid {
        initNbtivf();
    }

    /**
     * Find tif dfsdriptor for b dlbss tibt dbn bf sfriblizfd.  Crfbtfs bn
     * ObjfdtStrfbmClbss instbndf if onf dofs not fxist yft for dlbss. Null is
     * rfturnfd if tif spfdififd dlbss dofs not implfmfnt jbvb.io.Sfriblizbblf
     * or jbvb.io.Extfrnblizbblf.
     *
     * @pbrbm   dl dlbss for wiidi to gft tif dfsdriptor
     * @rfturn  tif dlbss dfsdriptor for tif spfdififd dlbss
     */
    publid stbtid ObjfdtStrfbmClbss lookup(Clbss<?> dl) {
        rfturn lookup(dl, fblsf);
    }

    /**
     * Rfturns tif dfsdriptor for bny dlbss, rfgbrdlfss of wiftifr it
     * implfmfnts {@link Sfriblizbblf}.
     *
     * @pbrbm        dl dlbss for wiidi to gft tif dfsdriptor
     * @rfturn       tif dlbss dfsdriptor for tif spfdififd dlbss
     * @sindf 1.6
     */
    publid stbtid ObjfdtStrfbmClbss lookupAny(Clbss<?> dl) {
        rfturn lookup(dl, truf);
    }

    /**
     * Rfturns tif nbmf of tif dlbss dfsdribfd by tiis dfsdriptor.
     * Tiis mftiod rfturns tif nbmf of tif dlbss in tif formbt tibt
     * is usfd by tif {@link Clbss#gftNbmf} mftiod.
     *
     * @rfturn b string rfprfsfnting tif nbmf of tif dlbss
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturn tif sfriblVfrsionUID for tiis dlbss.  Tif sfriblVfrsionUID
     * dffinfs b sft of dlbssfs bll witi tif sbmf nbmf tibt ibvf fvolvfd from b
     * dommon root dlbss bnd bgrff to bf sfriblizfd bnd dfsfriblizfd using b
     * dommon formbt.  NonSfriblizbblf dlbssfs ibvf b sfriblVfrsionUID of 0L.
     *
     * @rfturn  tif SUID of tif dlbss dfsdribfd by tiis dfsdriptor
     */
    publid long gftSfriblVfrsionUID() {
        // REMIND: syndironizf instfbd of rflying on volbtilf?
        if (suid == null) {
            suid = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<Long>() {
                    publid Long run() {
                        rfturn domputfDffbultSUID(dl);
                    }
                }
            );
        }
        rfturn suid.longVbluf();
    }

    /**
     * Rfturn tif dlbss in tif lodbl VM tibt tiis vfrsion is mbppfd to.  Null
     * is rfturnfd if tifrf is no dorrfsponding lodbl dlbss.
     *
     * @rfturn  tif <dodf>Clbss</dodf> instbndf tibt tiis dfsdriptor rfprfsfnts
     */
    @CbllfrSfnsitivf
    publid Clbss<?> forClbss() {
        if (dl == null) {
            rfturn null;
        }
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            Clbss<?> dbllfr = Rfflfdtion.gftCbllfrClbss();
            if (RfflfdtUtil.nffdsPbdkbgfAddfssCifdk(dbllfr.gftClbssLobdfr(), dl.gftClbssLobdfr())) {
                RfflfdtUtil.difdkPbdkbgfAddfss(dl);
            }
        }
        rfturn dl;
    }

    /**
     * Rfturn bn brrby of tif fiflds of tiis sfriblizbblf dlbss.
     *
     * @rfturn  bn brrby dontbining bn flfmfnt for fbdi pfrsistfnt fifld of
     *          tiis dlbss. Rfturns bn brrby of lfngti zfro if tifrf brf no
     *          fiflds.
     * @sindf 1.2
     */
    publid ObjfdtStrfbmFifld[] gftFiflds() {
        rfturn gftFiflds(truf);
    }

    /**
     * Gft tif fifld of tiis dlbss by nbmf.
     *
     * @pbrbm   nbmf tif nbmf of tif dbtb fifld to look for
     * @rfturn  Tif ObjfdtStrfbmFifld objfdt of tif nbmfd fifld or null if
     *          tifrf is no sudi nbmfd fifld.
     */
    publid ObjfdtStrfbmFifld gftFifld(String nbmf) {
        rfturn gftFifld(nbmf, null);
    }

    /**
     * Rfturn b string dfsdribing tiis ObjfdtStrfbmClbss.
     */
    publid String toString() {
        rfturn nbmf + ": stbtid finbl long sfriblVfrsionUID = " +
            gftSfriblVfrsionUID() + "L;";
    }

    /**
     * Looks up bnd rfturns dlbss dfsdriptor for givfn dlbss, or null if dlbss
     * is non-sfriblizbblf bnd "bll" is sft to fblsf.
     *
     * @pbrbm   dl dlbss to look up
     * @pbrbm   bll if truf, rfturn dfsdriptors for bll dlbssfs; if fblsf, only
     *          rfturn dfsdriptors for sfriblizbblf dlbssfs
     */
    stbtid ObjfdtStrfbmClbss lookup(Clbss<?> dl, boolfbn bll) {
        if (!(bll || Sfriblizbblf.dlbss.isAssignbblfFrom(dl))) {
            rfturn null;
        }
        prodfssQufuf(Cbdifs.lodblDfsdsQufuf, Cbdifs.lodblDfsds);
        WfbkClbssKfy kfy = nfw WfbkClbssKfy(dl, Cbdifs.lodblDfsdsQufuf);
        Rfffrfndf<?> rff = Cbdifs.lodblDfsds.gft(kfy);
        Objfdt fntry = null;
        if (rff != null) {
            fntry = rff.gft();
        }
        EntryFuturf futurf = null;
        if (fntry == null) {
            EntryFuturf nfwEntry = nfw EntryFuturf();
            Rfffrfndf<?> nfwRff = nfw SoftRfffrfndf<>(nfwEntry);
            do {
                if (rff != null) {
                    Cbdifs.lodblDfsds.rfmovf(kfy, rff);
                }
                rff = Cbdifs.lodblDfsds.putIfAbsfnt(kfy, nfwRff);
                if (rff != null) {
                    fntry = rff.gft();
                }
            } wiilf (rff != null && fntry == null);
            if (fntry == null) {
                futurf = nfwEntry;
            }
        }

        if (fntry instbndfof ObjfdtStrfbmClbss) {  // difdk dommon dbsf first
            rfturn (ObjfdtStrfbmClbss) fntry;
        }
        if (fntry instbndfof EntryFuturf) {
            futurf = (EntryFuturf) fntry;
            if (futurf.gftOwnfr() == Tirfbd.durrfntTirfbd()) {
                /*
                 * Hbndlf nfstfd dbll situbtion dfsdribfd by 4803747: wbiting
                 * for futurf vbluf to bf sft by b lookup() dbll furtifr up tif
                 * stbdk will rfsult in dfbdlodk, so dbldulbtf bnd sft tif
                 * futurf vbluf ifrf instfbd.
                 */
                fntry = null;
            } flsf {
                fntry = futurf.gft();
            }
        }
        if (fntry == null) {
            try {
                fntry = nfw ObjfdtStrfbmClbss(dl);
            } dbtdi (Tirowbblf ti) {
                fntry = ti;
            }
            if (futurf.sft(fntry)) {
                Cbdifs.lodblDfsds.put(kfy, nfw SoftRfffrfndf<Objfdt>(fntry));
            } flsf {
                // nfstfd lookup dbll blrfbdy sft futurf
                fntry = futurf.gft();
            }
        }

        if (fntry instbndfof ObjfdtStrfbmClbss) {
            rfturn (ObjfdtStrfbmClbss) fntry;
        } flsf if (fntry instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption) fntry;
        } flsf if (fntry instbndfof Error) {
            tirow (Error) fntry;
        } flsf {
            tirow nfw IntfrnblError("unfxpfdtfd fntry: " + fntry);
        }
    }

    /**
     * Plbdfioldfr usfd in dlbss dfsdriptor bnd fifld rfflfdtor lookup tbblfs
     * for bn fntry in tif prodfss of bfing initiblizfd.  (Intfrnbl) dbllfrs
     * wiidi rfdfivf bn EntryFuturf bflonging to bnotifr tirfbd bs tif rfsult
     * of b lookup siould dbll tif gft() mftiod of tif EntryFuturf; tiis will
     * rfturn tif bdtubl fntry ondf it is rfbdy for usf bnd ibs bffn sft().  To
     * donsfrvf objfdts, EntryFuturfs syndironizf on tifmsflvfs.
     */
    privbtf stbtid dlbss EntryFuturf {

        privbtf stbtid finbl Objfdt unsft = nfw Objfdt();
        privbtf finbl Tirfbd ownfr = Tirfbd.durrfntTirfbd();
        privbtf Objfdt fntry = unsft;

        /**
         * Attfmpts to sft tif vbluf dontbinfd by tiis EntryFuturf.  If tif
         * EntryFuturf's vbluf ibs not bffn sft blrfbdy, tifn tif vbluf is
         * sbvfd, bny dbllfrs blodkfd in tif gft() mftiod brf notififd, bnd
         * truf is rfturnfd.  If tif vbluf ibs blrfbdy bffn sft, tifn no sbving
         * or notifidbtion oddurs, bnd fblsf is rfturnfd.
         */
        syndironizfd boolfbn sft(Objfdt fntry) {
            if (tiis.fntry != unsft) {
                rfturn fblsf;
            }
            tiis.fntry = fntry;
            notifyAll();
            rfturn truf;
        }

        /**
         * Rfturns tif vbluf dontbinfd by tiis EntryFuturf, blodking if
         * nfdfssbry until b vbluf is sft.
         */
        syndironizfd Objfdt gft() {
            boolfbn intfrruptfd = fblsf;
            wiilf (fntry == unsft) {
                try {
                    wbit();
                } dbtdi (IntfrruptfdExdfption fx) {
                    intfrruptfd = truf;
                }
            }
            if (intfrruptfd) {
                AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                            Tirfbd.durrfntTirfbd().intfrrupt();
                            rfturn null;
                        }
                    }
                );
            }
            rfturn fntry;
        }

        /**
         * Rfturns tif tirfbd tibt drfbtfd tiis EntryFuturf.
         */
        Tirfbd gftOwnfr() {
            rfturn ownfr;
        }
    }

    /**
     * Crfbtfs lodbl dlbss dfsdriptor rfprfsfnting givfn dlbss.
     */
    privbtf ObjfdtStrfbmClbss(finbl Clbss<?> dl) {
        tiis.dl = dl;
        nbmf = dl.gftNbmf();
        isProxy = Proxy.isProxyClbss(dl);
        isEnum = Enum.dlbss.isAssignbblfFrom(dl);
        sfriblizbblf = Sfriblizbblf.dlbss.isAssignbblfFrom(dl);
        fxtfrnblizbblf = Extfrnblizbblf.dlbss.isAssignbblfFrom(dl);

        Clbss<?> supfrCl = dl.gftSupfrdlbss();
        supfrDfsd = (supfrCl != null) ? lookup(supfrCl, fblsf) : null;
        lodblDfsd = tiis;

        if (sfriblizbblf) {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    if (isEnum) {
                        suid = Long.vblufOf(0);
                        fiflds = NO_FIELDS;
                        rfturn null;
                    }
                    if (dl.isArrby()) {
                        fiflds = NO_FIELDS;
                        rfturn null;
                    }

                    suid = gftDfdlbrfdSUID(dl);
                    try {
                        fiflds = gftSfriblFiflds(dl);
                        domputfFifldOffsfts();
                    } dbtdi (InvblidClbssExdfption f) {
                        sfriblizfEx = dfsfriblizfEx =
                            nfw ExdfptionInfo(f.dlbssnbmf, f.gftMfssbgf());
                        fiflds = NO_FIELDS;
                    }

                    if (fxtfrnblizbblf) {
                        dons = gftExtfrnblizbblfConstrudtor(dl);
                    } flsf {
                        dons = gftSfriblizbblfConstrudtor(dl);
                        writfObjfdtMftiod = gftPrivbtfMftiod(dl, "writfObjfdt",
                            nfw Clbss<?>[] { ObjfdtOutputStrfbm.dlbss },
                            Void.TYPE);
                        rfbdObjfdtMftiod = gftPrivbtfMftiod(dl, "rfbdObjfdt",
                            nfw Clbss<?>[] { ObjfdtInputStrfbm.dlbss },
                            Void.TYPE);
                        rfbdObjfdtNoDbtbMftiod = gftPrivbtfMftiod(
                            dl, "rfbdObjfdtNoDbtb", null, Void.TYPE);
                        ibsWritfObjfdtDbtb = (writfObjfdtMftiod != null);
                    }
                    writfRfplbdfMftiod = gftInifritbblfMftiod(
                        dl, "writfRfplbdf", null, Objfdt.dlbss);
                    rfbdRfsolvfMftiod = gftInifritbblfMftiod(
                        dl, "rfbdRfsolvf", null, Objfdt.dlbss);
                    rfturn null;
                }
            });
        } flsf {
            suid = Long.vblufOf(0);
            fiflds = NO_FIELDS;
        }

        try {
            fifldRffl = gftRfflfdtor(fiflds, tiis);
        } dbtdi (InvblidClbssExdfption fx) {
            // fifld mismbtdifs impossiblf wifn mbtdiing lodbl fiflds vs. sflf
            tirow nfw IntfrnblError(fx);
        }

        if (dfsfriblizfEx == null) {
            if (isEnum) {
                dfsfriblizfEx = nfw ExdfptionInfo(nbmf, "fnum typf");
            } flsf if (dons == null) {
                dfsfriblizfEx = nfw ExdfptionInfo(nbmf, "no vblid donstrudtor");
            }
        }
        for (int i = 0; i < fiflds.lfngti; i++) {
            if (fiflds[i].gftFifld() == null) {
                dffbultSfriblizfEx = nfw ExdfptionInfo(
                    nbmf, "unmbtdifd sfriblizbblf fifld(s) dfdlbrfd");
            }
        }
    }

    /**
     * Crfbtfs blbnk dlbss dfsdriptor wiidi siould bf initiblizfd vib b
     * subsfqufnt dbll to initProxy(), initNonProxy() or rfbdNonProxy().
     */
    ObjfdtStrfbmClbss() {
    }

    /**
     * Initiblizfs dlbss dfsdriptor rfprfsfnting b proxy dlbss.
     */
    void initProxy(Clbss<?> dl,
                   ClbssNotFoundExdfption rfsolvfEx,
                   ObjfdtStrfbmClbss supfrDfsd)
        tirows InvblidClbssExdfption
    {
        tiis.dl = dl;
        tiis.rfsolvfEx = rfsolvfEx;
        tiis.supfrDfsd = supfrDfsd;
        isProxy = truf;
        sfriblizbblf = truf;
        suid = Long.vblufOf(0);
        fiflds = NO_FIELDS;

        if (dl != null) {
            lodblDfsd = lookup(dl, truf);
            if (!lodblDfsd.isProxy) {
                tirow nfw InvblidClbssExdfption(
                    "dbnnot bind proxy dfsdriptor to b non-proxy dlbss");
            }
            nbmf = lodblDfsd.nbmf;
            fxtfrnblizbblf = lodblDfsd.fxtfrnblizbblf;
            dons = lodblDfsd.dons;
            writfRfplbdfMftiod = lodblDfsd.writfRfplbdfMftiod;
            rfbdRfsolvfMftiod = lodblDfsd.rfbdRfsolvfMftiod;
            dfsfriblizfEx = lodblDfsd.dfsfriblizfEx;
        }
        fifldRffl = gftRfflfdtor(fiflds, lodblDfsd);
    }

    /**
     * Initiblizfs dlbss dfsdriptor rfprfsfnting b non-proxy dlbss.
     */
    void initNonProxy(ObjfdtStrfbmClbss modfl,
                      Clbss<?> dl,
                      ClbssNotFoundExdfption rfsolvfEx,
                      ObjfdtStrfbmClbss supfrDfsd)
        tirows InvblidClbssExdfption
    {
        tiis.dl = dl;
        tiis.rfsolvfEx = rfsolvfEx;
        tiis.supfrDfsd = supfrDfsd;
        nbmf = modfl.nbmf;
        suid = Long.vblufOf(modfl.gftSfriblVfrsionUID());
        isProxy = fblsf;
        isEnum = modfl.isEnum;
        sfriblizbblf = modfl.sfriblizbblf;
        fxtfrnblizbblf = modfl.fxtfrnblizbblf;
        ibsBlodkExtfrnblDbtb = modfl.ibsBlodkExtfrnblDbtb;
        ibsWritfObjfdtDbtb = modfl.ibsWritfObjfdtDbtb;
        fiflds = modfl.fiflds;
        primDbtbSizf = modfl.primDbtbSizf;
        numObjFiflds = modfl.numObjFiflds;

        if (dl != null) {
            lodblDfsd = lookup(dl, truf);
            if (lodblDfsd.isProxy) {
                tirow nfw InvblidClbssExdfption(
                    "dbnnot bind non-proxy dfsdriptor to b proxy dlbss");
            }
            if (isEnum != lodblDfsd.isEnum) {
                tirow nfw InvblidClbssExdfption(isEnum ?
                    "dbnnot bind fnum dfsdriptor to b non-fnum dlbss" :
                    "dbnnot bind non-fnum dfsdriptor to bn fnum dlbss");
            }

            if (sfriblizbblf == lodblDfsd.sfriblizbblf &&
                !dl.isArrby() &&
                suid.longVbluf() != lodblDfsd.gftSfriblVfrsionUID())
            {
                tirow nfw InvblidClbssExdfption(lodblDfsd.nbmf,
                    "lodbl dlbss indompbtiblf: " +
                    "strfbm dlbssdfsd sfriblVfrsionUID = " + suid +
                    ", lodbl dlbss sfriblVfrsionUID = " +
                    lodblDfsd.gftSfriblVfrsionUID());
            }

            if (!dlbssNbmfsEqubl(nbmf, lodblDfsd.nbmf)) {
                tirow nfw InvblidClbssExdfption(lodblDfsd.nbmf,
                    "lodbl dlbss nbmf indompbtiblf witi strfbm dlbss " +
                    "nbmf \"" + nbmf + "\"");
            }

            if (!isEnum) {
                if ((sfriblizbblf == lodblDfsd.sfriblizbblf) &&
                    (fxtfrnblizbblf != lodblDfsd.fxtfrnblizbblf))
                {
                    tirow nfw InvblidClbssExdfption(lodblDfsd.nbmf,
                        "Sfriblizbblf indompbtiblf witi Extfrnblizbblf");
                }

                if ((sfriblizbblf != lodblDfsd.sfriblizbblf) ||
                    (fxtfrnblizbblf != lodblDfsd.fxtfrnblizbblf) ||
                    !(sfriblizbblf || fxtfrnblizbblf))
                {
                    dfsfriblizfEx = nfw ExdfptionInfo(
                        lodblDfsd.nbmf, "dlbss invblid for dfsfriblizbtion");
                }
            }

            dons = lodblDfsd.dons;
            writfObjfdtMftiod = lodblDfsd.writfObjfdtMftiod;
            rfbdObjfdtMftiod = lodblDfsd.rfbdObjfdtMftiod;
            rfbdObjfdtNoDbtbMftiod = lodblDfsd.rfbdObjfdtNoDbtbMftiod;
            writfRfplbdfMftiod = lodblDfsd.writfRfplbdfMftiod;
            rfbdRfsolvfMftiod = lodblDfsd.rfbdRfsolvfMftiod;
            if (dfsfriblizfEx == null) {
                dfsfriblizfEx = lodblDfsd.dfsfriblizfEx;
            }
        }
        fifldRffl = gftRfflfdtor(fiflds, lodblDfsd);
        // rfbssign to mbtdifd fiflds so bs to rfflfdt lodbl unsibrfd sfttings
        fiflds = fifldRffl.gftFiflds();
    }

    /**
     * Rfbds non-proxy dlbss dfsdriptor informbtion from givfn input strfbm.
     * Tif rfsulting dlbss dfsdriptor is not fully fundtionbl; it dbn only bf
     * usfd bs input to tif ObjfdtInputStrfbm.rfsolvfClbss() bnd
     * ObjfdtStrfbmClbss.initNonProxy() mftiods.
     */
    void rfbdNonProxy(ObjfdtInputStrfbm in)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        nbmf = in.rfbdUTF();
        suid = Long.vblufOf(in.rfbdLong());
        isProxy = fblsf;

        bytf flbgs = in.rfbdBytf();
        ibsWritfObjfdtDbtb =
            ((flbgs & ObjfdtStrfbmConstbnts.SC_WRITE_METHOD) != 0);
        ibsBlodkExtfrnblDbtb =
            ((flbgs & ObjfdtStrfbmConstbnts.SC_BLOCK_DATA) != 0);
        fxtfrnblizbblf =
            ((flbgs & ObjfdtStrfbmConstbnts.SC_EXTERNALIZABLE) != 0);
        boolfbn sflbg =
            ((flbgs & ObjfdtStrfbmConstbnts.SC_SERIALIZABLE) != 0);
        if (fxtfrnblizbblf && sflbg) {
            tirow nfw InvblidClbssExdfption(
                nbmf, "sfriblizbblf bnd fxtfrnblizbblf flbgs donflidt");
        }
        sfriblizbblf = fxtfrnblizbblf || sflbg;
        isEnum = ((flbgs & ObjfdtStrfbmConstbnts.SC_ENUM) != 0);
        if (isEnum && suid.longVbluf() != 0L) {
            tirow nfw InvblidClbssExdfption(nbmf,
                "fnum dfsdriptor ibs non-zfro sfriblVfrsionUID: " + suid);
        }

        int numFiflds = in.rfbdSiort();
        if (isEnum && numFiflds != 0) {
            tirow nfw InvblidClbssExdfption(nbmf,
                "fnum dfsdriptor ibs non-zfro fifld dount: " + numFiflds);
        }
        fiflds = (numFiflds > 0) ?
            nfw ObjfdtStrfbmFifld[numFiflds] : NO_FIELDS;
        for (int i = 0; i < numFiflds; i++) {
            dibr tdodf = (dibr) in.rfbdBytf();
            String fnbmf = in.rfbdUTF();
            String signbturf = ((tdodf == 'L') || (tdodf == '[')) ?
                in.rfbdTypfString() : nfw String(nfw dibr[] { tdodf });
            try {
                fiflds[i] = nfw ObjfdtStrfbmFifld(fnbmf, signbturf, fblsf);
            } dbtdi (RuntimfExdfption f) {
                tirow (IOExdfption) nfw InvblidClbssExdfption(nbmf,
                    "invblid dfsdriptor for fifld " + fnbmf).initCbusf(f);
            }
        }
        domputfFifldOffsfts();
    }

    /**
     * Writfs non-proxy dlbss dfsdriptor informbtion to givfn output strfbm.
     */
    void writfNonProxy(ObjfdtOutputStrfbm out) tirows IOExdfption {
        out.writfUTF(nbmf);
        out.writfLong(gftSfriblVfrsionUID());

        bytf flbgs = 0;
        if (fxtfrnblizbblf) {
            flbgs |= ObjfdtStrfbmConstbnts.SC_EXTERNALIZABLE;
            int protodol = out.gftProtodolVfrsion();
            if (protodol != ObjfdtStrfbmConstbnts.PROTOCOL_VERSION_1) {
                flbgs |= ObjfdtStrfbmConstbnts.SC_BLOCK_DATA;
            }
        } flsf if (sfriblizbblf) {
            flbgs |= ObjfdtStrfbmConstbnts.SC_SERIALIZABLE;
        }
        if (ibsWritfObjfdtDbtb) {
            flbgs |= ObjfdtStrfbmConstbnts.SC_WRITE_METHOD;
        }
        if (isEnum) {
            flbgs |= ObjfdtStrfbmConstbnts.SC_ENUM;
        }
        out.writfBytf(flbgs);

        out.writfSiort(fiflds.lfngti);
        for (int i = 0; i < fiflds.lfngti; i++) {
            ObjfdtStrfbmFifld f = fiflds[i];
            out.writfBytf(f.gftTypfCodf());
            out.writfUTF(f.gftNbmf());
            if (!f.isPrimitivf()) {
                out.writfTypfString(f.gftTypfString());
            }
        }
    }

    /**
     * Rfturns ClbssNotFoundExdfption (if bny) tirown wiilf bttfmpting to
     * rfsolvf lodbl dlbss dorrfsponding to tiis dlbss dfsdriptor.
     */
    ClbssNotFoundExdfption gftRfsolvfExdfption() {
        rfturn rfsolvfEx;
    }

    /**
     * Tirows bn InvblidClbssExdfption if objfdt instbndfs rfffrfnding tiis
     * dlbss dfsdriptor siould not bf bllowfd to dfsfriblizf.  Tiis mftiod dofs
     * not bpply to dfsfriblizbtion of fnum donstbnts.
     */
    void difdkDfsfriblizf() tirows InvblidClbssExdfption {
        if (dfsfriblizfEx != null) {
            tirow dfsfriblizfEx.nfwInvblidClbssExdfption();
        }
    }

    /**
     * Tirows bn InvblidClbssExdfption if objfdts wiosf dlbss is rfprfsfntfd by
     * tiis dfsdriptor siould not bf bllowfd to sfriblizf.  Tiis mftiod dofs
     * not bpply to sfriblizbtion of fnum donstbnts.
     */
    void difdkSfriblizf() tirows InvblidClbssExdfption {
        if (sfriblizfEx != null) {
            tirow sfriblizfEx.nfwInvblidClbssExdfption();
        }
    }

    /**
     * Tirows bn InvblidClbssExdfption if objfdts wiosf dlbss is rfprfsfntfd by
     * tiis dfsdriptor siould not bf pfrmittfd to usf dffbult sfriblizbtion
     * (f.g., if tif dlbss dfdlbrfs sfriblizbblf fiflds tibt do not dorrfspond
     * to bdtubl fiflds, bnd ifndf must usf tif GftFifld API).  Tiis mftiod
     * dofs not bpply to dfsfriblizbtion of fnum donstbnts.
     */
    void difdkDffbultSfriblizf() tirows InvblidClbssExdfption {
        if (dffbultSfriblizfEx != null) {
            tirow dffbultSfriblizfEx.nfwInvblidClbssExdfption();
        }
    }

    /**
     * Rfturns supfrdlbss dfsdriptor.  Notf tibt on tif rfdfiving sidf, tif
     * supfrdlbss dfsdriptor mby bf bound to b dlbss tibt is not b supfrdlbss
     * of tif subdlbss dfsdriptor's bound dlbss.
     */
    ObjfdtStrfbmClbss gftSupfrDfsd() {
        rfturn supfrDfsd;
    }

    /**
     * Rfturns tif "lodbl" dlbss dfsdriptor for tif dlbss bssodibtfd witi tiis
     * dlbss dfsdriptor (i.f., tif rfsult of
     * ObjfdtStrfbmClbss.lookup(tiis.forClbss())) or null if tifrf is no dlbss
     * bssodibtfd witi tiis dfsdriptor.
     */
    ObjfdtStrfbmClbss gftLodblDfsd() {
        rfturn lodblDfsd;
    }

    /**
     * Rfturns brrbys of ObjfdtStrfbmFiflds rfprfsfnting tif sfriblizbblf
     * fiflds of tif rfprfsfntfd dlbss.  If dopy is truf, b dlonf of tiis dlbss
     * dfsdriptor's fifld brrby is rfturnfd, otifrwisf tif brrby itsflf is
     * rfturnfd.
     */
    ObjfdtStrfbmFifld[] gftFiflds(boolfbn dopy) {
        rfturn dopy ? fiflds.dlonf() : fiflds;
    }

    /**
     * Looks up b sfriblizbblf fifld of tif rfprfsfntfd dlbss by nbmf bnd typf.
     * A spfdififd typf of null mbtdifs bll typfs, Objfdt.dlbss mbtdifs bll
     * non-primitivf typfs, bnd bny otifr non-null typf mbtdifs bssignbblf
     * typfs only.  Rfturns mbtdiing fifld, or null if no mbtdi found.
     */
    ObjfdtStrfbmFifld gftFifld(String nbmf, Clbss<?> typf) {
        for (int i = 0; i < fiflds.lfngti; i++) {
            ObjfdtStrfbmFifld f = fiflds[i];
            if (f.gftNbmf().fqubls(nbmf)) {
                if (typf == null ||
                    (typf == Objfdt.dlbss && !f.isPrimitivf()))
                {
                    rfturn f;
                }
                Clbss<?> ftypf = f.gftTypf();
                if (ftypf != null && typf.isAssignbblfFrom(ftypf)) {
                    rfturn f;
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfturns truf if dlbss dfsdriptor rfprfsfnts b dynbmid proxy dlbss, fblsf
     * otifrwisf.
     */
    boolfbn isProxy() {
        rfturn isProxy;
    }

    /**
     * Rfturns truf if dlbss dfsdriptor rfprfsfnts bn fnum typf, fblsf
     * otifrwisf.
     */
    boolfbn isEnum() {
        rfturn isEnum;
    }

    /**
     * Rfturns truf if rfprfsfntfd dlbss implfmfnts Extfrnblizbblf, fblsf
     * otifrwisf.
     */
    boolfbn isExtfrnblizbblf() {
        rfturn fxtfrnblizbblf;
    }

    /**
     * Rfturns truf if rfprfsfntfd dlbss implfmfnts Sfriblizbblf, fblsf
     * otifrwisf.
     */
    boolfbn isSfriblizbblf() {
        rfturn sfriblizbblf;
    }

    /**
     * Rfturns truf if dlbss dfsdriptor rfprfsfnts fxtfrnblizbblf dlbss tibt
     * ibs writtfn its dbtb in 1.2 (blodk dbtb) formbt, fblsf otifrwisf.
     */
    boolfbn ibsBlodkExtfrnblDbtb() {
        rfturn ibsBlodkExtfrnblDbtb;
    }

    /**
     * Rfturns truf if dlbss dfsdriptor rfprfsfnts sfriblizbblf (but not
     * fxtfrnblizbblf) dlbss wiidi ibs writtfn its dbtb vib b dustom
     * writfObjfdt() mftiod, fblsf otifrwisf.
     */
    boolfbn ibsWritfObjfdtDbtb() {
        rfturn ibsWritfObjfdtDbtb;
    }

    /**
     * Rfturns truf if rfprfsfntfd dlbss is sfriblizbblf/fxtfrnblizbblf bnd dbn
     * bf instbntibtfd by tif sfriblizbtion runtimf--i.f., if it is
     * fxtfrnblizbblf bnd dffinfs b publid no-brg donstrudtor, or if it is
     * non-fxtfrnblizbblf bnd its first non-sfriblizbblf supfrdlbss dffinfs bn
     * bddfssiblf no-brg donstrudtor.  Otifrwisf, rfturns fblsf.
     */
    boolfbn isInstbntibblf() {
        rfturn (dons != null);
    }

    /**
     * Rfturns truf if rfprfsfntfd dlbss is sfriblizbblf (but not
     * fxtfrnblizbblf) bnd dffinfs b donformbnt writfObjfdt mftiod.  Otifrwisf,
     * rfturns fblsf.
     */
    boolfbn ibsWritfObjfdtMftiod() {
        rfturn (writfObjfdtMftiod != null);
    }

    /**
     * Rfturns truf if rfprfsfntfd dlbss is sfriblizbblf (but not
     * fxtfrnblizbblf) bnd dffinfs b donformbnt rfbdObjfdt mftiod.  Otifrwisf,
     * rfturns fblsf.
     */
    boolfbn ibsRfbdObjfdtMftiod() {
        rfturn (rfbdObjfdtMftiod != null);
    }

    /**
     * Rfturns truf if rfprfsfntfd dlbss is sfriblizbblf (but not
     * fxtfrnblizbblf) bnd dffinfs b donformbnt rfbdObjfdtNoDbtb mftiod.
     * Otifrwisf, rfturns fblsf.
     */
    boolfbn ibsRfbdObjfdtNoDbtbMftiod() {
        rfturn (rfbdObjfdtNoDbtbMftiod != null);
    }

    /**
     * Rfturns truf if rfprfsfntfd dlbss is sfriblizbblf or fxtfrnblizbblf bnd
     * dffinfs b donformbnt writfRfplbdf mftiod.  Otifrwisf, rfturns fblsf.
     */
    boolfbn ibsWritfRfplbdfMftiod() {
        rfturn (writfRfplbdfMftiod != null);
    }

    /**
     * Rfturns truf if rfprfsfntfd dlbss is sfriblizbblf or fxtfrnblizbblf bnd
     * dffinfs b donformbnt rfbdRfsolvf mftiod.  Otifrwisf, rfturns fblsf.
     */
    boolfbn ibsRfbdRfsolvfMftiod() {
        rfturn (rfbdRfsolvfMftiod != null);
    }

    /**
     * Crfbtfs b nfw instbndf of tif rfprfsfntfd dlbss.  If tif dlbss is
     * fxtfrnblizbblf, invokfs its publid no-brg donstrudtor; otifrwisf, if tif
     * dlbss is sfriblizbblf, invokfs tif no-brg donstrudtor of tif first
     * non-sfriblizbblf supfrdlbss.  Tirows UnsupportfdOpfrbtionExdfption if
     * tiis dlbss dfsdriptor is not bssodibtfd witi b dlbss, if tif bssodibtfd
     * dlbss is non-sfriblizbblf or if tif bppropribtf no-brg donstrudtor is
     * inbddfssiblf/unbvbilbblf.
     */
    Objfdt nfwInstbndf()
        tirows InstbntibtionExdfption, InvodbtionTbrgftExdfption,
               UnsupportfdOpfrbtionExdfption
    {
        if (dons != null) {
            try {
                rfturn dons.nfwInstbndf();
            } dbtdi (IllfgblAddfssExdfption fx) {
                // siould not oddur, bs bddfss difdks ibvf bffn supprfssfd
                tirow nfw IntfrnblError(fx);
            }
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    /**
     * Invokfs tif writfObjfdt mftiod of tif rfprfsfntfd sfriblizbblf dlbss.
     * Tirows UnsupportfdOpfrbtionExdfption if tiis dlbss dfsdriptor is not
     * bssodibtfd witi b dlbss, or if tif dlbss is fxtfrnblizbblf,
     * non-sfriblizbblf or dofs not dffinf writfObjfdt.
     */
    void invokfWritfObjfdt(Objfdt obj, ObjfdtOutputStrfbm out)
        tirows IOExdfption, UnsupportfdOpfrbtionExdfption
    {
        if (writfObjfdtMftiod != null) {
            try {
                writfObjfdtMftiod.invokf(obj, nfw Objfdt[]{ out });
            } dbtdi (InvodbtionTbrgftExdfption fx) {
                Tirowbblf ti = fx.gftTbrgftExdfption();
                if (ti instbndfof IOExdfption) {
                    tirow (IOExdfption) ti;
                } flsf {
                    tirowMisdExdfption(ti);
                }
            } dbtdi (IllfgblAddfssExdfption fx) {
                // siould not oddur, bs bddfss difdks ibvf bffn supprfssfd
                tirow nfw IntfrnblError(fx);
            }
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    /**
     * Invokfs tif rfbdObjfdt mftiod of tif rfprfsfntfd sfriblizbblf dlbss.
     * Tirows UnsupportfdOpfrbtionExdfption if tiis dlbss dfsdriptor is not
     * bssodibtfd witi b dlbss, or if tif dlbss is fxtfrnblizbblf,
     * non-sfriblizbblf or dofs not dffinf rfbdObjfdt.
     */
    void invokfRfbdObjfdt(Objfdt obj, ObjfdtInputStrfbm in)
        tirows ClbssNotFoundExdfption, IOExdfption,
               UnsupportfdOpfrbtionExdfption
    {
        if (rfbdObjfdtMftiod != null) {
            try {
                rfbdObjfdtMftiod.invokf(obj, nfw Objfdt[]{ in });
            } dbtdi (InvodbtionTbrgftExdfption fx) {
                Tirowbblf ti = fx.gftTbrgftExdfption();
                if (ti instbndfof ClbssNotFoundExdfption) {
                    tirow (ClbssNotFoundExdfption) ti;
                } flsf if (ti instbndfof IOExdfption) {
                    tirow (IOExdfption) ti;
                } flsf {
                    tirowMisdExdfption(ti);
                }
            } dbtdi (IllfgblAddfssExdfption fx) {
                // siould not oddur, bs bddfss difdks ibvf bffn supprfssfd
                tirow nfw IntfrnblError(fx);
            }
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    /**
     * Invokfs tif rfbdObjfdtNoDbtb mftiod of tif rfprfsfntfd sfriblizbblf
     * dlbss.  Tirows UnsupportfdOpfrbtionExdfption if tiis dlbss dfsdriptor is
     * not bssodibtfd witi b dlbss, or if tif dlbss is fxtfrnblizbblf,
     * non-sfriblizbblf or dofs not dffinf rfbdObjfdtNoDbtb.
     */
    void invokfRfbdObjfdtNoDbtb(Objfdt obj)
        tirows IOExdfption, UnsupportfdOpfrbtionExdfption
    {
        if (rfbdObjfdtNoDbtbMftiod != null) {
            try {
                rfbdObjfdtNoDbtbMftiod.invokf(obj, (Objfdt[]) null);
            } dbtdi (InvodbtionTbrgftExdfption fx) {
                Tirowbblf ti = fx.gftTbrgftExdfption();
                if (ti instbndfof ObjfdtStrfbmExdfption) {
                    tirow (ObjfdtStrfbmExdfption) ti;
                } flsf {
                    tirowMisdExdfption(ti);
                }
            } dbtdi (IllfgblAddfssExdfption fx) {
                // siould not oddur, bs bddfss difdks ibvf bffn supprfssfd
                tirow nfw IntfrnblError(fx);
            }
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    /**
     * Invokfs tif writfRfplbdf mftiod of tif rfprfsfntfd sfriblizbblf dlbss bnd
     * rfturns tif rfsult.  Tirows UnsupportfdOpfrbtionExdfption if tiis dlbss
     * dfsdriptor is not bssodibtfd witi b dlbss, or if tif dlbss is
     * non-sfriblizbblf or dofs not dffinf writfRfplbdf.
     */
    Objfdt invokfWritfRfplbdf(Objfdt obj)
        tirows IOExdfption, UnsupportfdOpfrbtionExdfption
    {
        if (writfRfplbdfMftiod != null) {
            try {
                rfturn writfRfplbdfMftiod.invokf(obj, (Objfdt[]) null);
            } dbtdi (InvodbtionTbrgftExdfption fx) {
                Tirowbblf ti = fx.gftTbrgftExdfption();
                if (ti instbndfof ObjfdtStrfbmExdfption) {
                    tirow (ObjfdtStrfbmExdfption) ti;
                } flsf {
                    tirowMisdExdfption(ti);
                    tirow nfw IntfrnblError(ti);  // nfvfr rfbdifd
                }
            } dbtdi (IllfgblAddfssExdfption fx) {
                // siould not oddur, bs bddfss difdks ibvf bffn supprfssfd
                tirow nfw IntfrnblError(fx);
            }
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    /**
     * Invokfs tif rfbdRfsolvf mftiod of tif rfprfsfntfd sfriblizbblf dlbss bnd
     * rfturns tif rfsult.  Tirows UnsupportfdOpfrbtionExdfption if tiis dlbss
     * dfsdriptor is not bssodibtfd witi b dlbss, or if tif dlbss is
     * non-sfriblizbblf or dofs not dffinf rfbdRfsolvf.
     */
    Objfdt invokfRfbdRfsolvf(Objfdt obj)
        tirows IOExdfption, UnsupportfdOpfrbtionExdfption
    {
        if (rfbdRfsolvfMftiod != null) {
            try {
                rfturn rfbdRfsolvfMftiod.invokf(obj, (Objfdt[]) null);
            } dbtdi (InvodbtionTbrgftExdfption fx) {
                Tirowbblf ti = fx.gftTbrgftExdfption();
                if (ti instbndfof ObjfdtStrfbmExdfption) {
                    tirow (ObjfdtStrfbmExdfption) ti;
                } flsf {
                    tirowMisdExdfption(ti);
                    tirow nfw IntfrnblError(ti);  // nfvfr rfbdifd
                }
            } dbtdi (IllfgblAddfssExdfption fx) {
                // siould not oddur, bs bddfss difdks ibvf bffn supprfssfd
                tirow nfw IntfrnblError(fx);
            }
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    /**
     * Clbss rfprfsfnting tif portion of bn objfdt's sfriblizfd form bllottfd
     * to dbtb dfsdribfd by b givfn dlbss dfsdriptor.  If "ibsDbtb" is fblsf,
     * tif objfdt's sfriblizfd form dofs not dontbin dbtb bssodibtfd witi tif
     * dlbss dfsdriptor.
     */
    stbtid dlbss ClbssDbtbSlot {

        /** dlbss dfsdriptor "oddupying" tiis slot */
        finbl ObjfdtStrfbmClbss dfsd;
        /** truf if sfriblizfd form indludfs dbtb for tiis slot's dfsdriptor */
        finbl boolfbn ibsDbtb;

        ClbssDbtbSlot(ObjfdtStrfbmClbss dfsd, boolfbn ibsDbtb) {
            tiis.dfsd = dfsd;
            tiis.ibsDbtb = ibsDbtb;
        }
    }

    /**
     * Rfturns brrby of ClbssDbtbSlot instbndfs rfprfsfnting tif dbtb lbyout
     * (indluding supfrdlbss dbtb) for sfriblizfd objfdts dfsdribfd by tiis
     * dlbss dfsdriptor.  ClbssDbtbSlots brf ordfrfd by inifritbndf witi tiosf
     * dontbining "iigifr" supfrdlbssfs bppfbring first.  Tif finbl
     * ClbssDbtbSlot dontbins b rfffrfndf to tiis dfsdriptor.
     */
    ClbssDbtbSlot[] gftClbssDbtbLbyout() tirows InvblidClbssExdfption {
        // REMIND: syndironizf instfbd of rflying on volbtilf?
        if (dbtbLbyout == null) {
            dbtbLbyout = gftClbssDbtbLbyout0();
        }
        rfturn dbtbLbyout;
    }

    privbtf ClbssDbtbSlot[] gftClbssDbtbLbyout0()
        tirows InvblidClbssExdfption
    {
        ArrbyList<ClbssDbtbSlot> slots = nfw ArrbyList<>();
        Clbss<?> stbrt = dl, fnd = dl;

        // lodbtf dlosfst non-sfriblizbblf supfrdlbss
        wiilf (fnd != null && Sfriblizbblf.dlbss.isAssignbblfFrom(fnd)) {
            fnd = fnd.gftSupfrdlbss();
        }

        HbsiSft<String> osdNbmfs = nfw HbsiSft<>(3);

        for (ObjfdtStrfbmClbss d = tiis; d != null; d = d.supfrDfsd) {
            if (osdNbmfs.dontbins(d.nbmf)) {
                tirow nfw InvblidClbssExdfption("Cirdulbr rfffrfndf.");
            } flsf {
                osdNbmfs.bdd(d.nbmf);
            }

            // sfbrdi up inifritbndf iifrbrdiy for dlbss witi mbtdiing nbmf
            String sfbrdiNbmf = (d.dl != null) ? d.dl.gftNbmf() : d.nbmf;
            Clbss<?> mbtdi = null;
            for (Clbss<?> d = stbrt; d != fnd; d = d.gftSupfrdlbss()) {
                if (sfbrdiNbmf.fqubls(d.gftNbmf())) {
                    mbtdi = d;
                    brfbk;
                }
            }

            // bdd "no dbtb" slot for fbdi unmbtdifd dlbss bflow mbtdi
            if (mbtdi != null) {
                for (Clbss<?> d = stbrt; d != mbtdi; d = d.gftSupfrdlbss()) {
                    slots.bdd(nfw ClbssDbtbSlot(
                        ObjfdtStrfbmClbss.lookup(d, truf), fblsf));
                }
                stbrt = mbtdi.gftSupfrdlbss();
            }

            // rfdord dfsdriptor/dlbss pbiring
            slots.bdd(nfw ClbssDbtbSlot(d.gftVbribntFor(mbtdi), truf));
        }

        // bdd "no dbtb" slot for bny lfftovfr unmbtdifd dlbssfs
        for (Clbss<?> d = stbrt; d != fnd; d = d.gftSupfrdlbss()) {
            slots.bdd(nfw ClbssDbtbSlot(
                ObjfdtStrfbmClbss.lookup(d, truf), fblsf));
        }

        // ordfr slots from supfrdlbss -> subdlbss
        Collfdtions.rfvfrsf(slots);
        rfturn slots.toArrby(nfw ClbssDbtbSlot[slots.sizf()]);
    }

    /**
     * Rfturns bggrfgbtf sizf (in bytfs) of mbrsibllfd primitivf fifld vblufs
     * for rfprfsfntfd dlbss.
     */
    int gftPrimDbtbSizf() {
        rfturn primDbtbSizf;
    }

    /**
     * Rfturns numbfr of non-primitivf sfriblizbblf fiflds of rfprfsfntfd
     * dlbss.
     */
    int gftNumObjFiflds() {
        rfturn numObjFiflds;
    }

    /**
     * Fftdifs tif sfriblizbblf primitivf fifld vblufs of objfdt obj bnd
     * mbrsibls tifm into bytf brrby buf stbrting bt offsft 0.  It is tif
     * rfsponsibility of tif dbllfr to fnsurf tibt obj is of tif propfr typf if
     * non-null.
     */
    void gftPrimFifldVblufs(Objfdt obj, bytf[] buf) {
        fifldRffl.gftPrimFifldVblufs(obj, buf);
    }

    /**
     * Sfts tif sfriblizbblf primitivf fiflds of objfdt obj using vblufs
     * unmbrsibllfd from bytf brrby buf stbrting bt offsft 0.  It is tif
     * rfsponsibility of tif dbllfr to fnsurf tibt obj is of tif propfr typf if
     * non-null.
     */
    void sftPrimFifldVblufs(Objfdt obj, bytf[] buf) {
        fifldRffl.sftPrimFifldVblufs(obj, buf);
    }

    /**
     * Fftdifs tif sfriblizbblf objfdt fifld vblufs of objfdt obj bnd storfs
     * tifm in brrby vbls stbrting bt offsft 0.  It is tif rfsponsibility of
     * tif dbllfr to fnsurf tibt obj is of tif propfr typf if non-null.
     */
    void gftObjFifldVblufs(Objfdt obj, Objfdt[] vbls) {
        fifldRffl.gftObjFifldVblufs(obj, vbls);
    }

    /**
     * Sfts tif sfriblizbblf objfdt fiflds of objfdt obj using vblufs from
     * brrby vbls stbrting bt offsft 0.  It is tif rfsponsibility of tif dbllfr
     * to fnsurf tibt obj is of tif propfr typf if non-null.
     */
    void sftObjFifldVblufs(Objfdt obj, Objfdt[] vbls) {
        fifldRffl.sftObjFifldVblufs(obj, vbls);
    }

    /**
     * Cbldulbtfs bnd sfts sfriblizbblf fifld offsfts, bs wfll bs primitivf
     * dbtb sizf bnd objfdt fifld dount totbls.  Tirows InvblidClbssExdfption
     * if fiflds brf illfgblly ordfrfd.
     */
    privbtf void domputfFifldOffsfts() tirows InvblidClbssExdfption {
        primDbtbSizf = 0;
        numObjFiflds = 0;
        int firstObjIndfx = -1;

        for (int i = 0; i < fiflds.lfngti; i++) {
            ObjfdtStrfbmFifld f = fiflds[i];
            switdi (f.gftTypfCodf()) {
                dbsf 'Z':
                dbsf 'B':
                    f.sftOffsft(primDbtbSizf++);
                    brfbk;

                dbsf 'C':
                dbsf 'S':
                    f.sftOffsft(primDbtbSizf);
                    primDbtbSizf += 2;
                    brfbk;

                dbsf 'I':
                dbsf 'F':
                    f.sftOffsft(primDbtbSizf);
                    primDbtbSizf += 4;
                    brfbk;

                dbsf 'J':
                dbsf 'D':
                    f.sftOffsft(primDbtbSizf);
                    primDbtbSizf += 8;
                    brfbk;

                dbsf '[':
                dbsf 'L':
                    f.sftOffsft(numObjFiflds++);
                    if (firstObjIndfx == -1) {
                        firstObjIndfx = i;
                    }
                    brfbk;

                dffbult:
                    tirow nfw IntfrnblError();
            }
        }
        if (firstObjIndfx != -1 &&
            firstObjIndfx + numObjFiflds != fiflds.lfngti)
        {
            tirow nfw InvblidClbssExdfption(nbmf, "illfgbl fifld ordfr");
        }
    }

    /**
     * If givfn dlbss is tif sbmf bs tif dlbss bssodibtfd witi tiis dlbss
     * dfsdriptor, rfturns rfffrfndf to tiis dlbss dfsdriptor.  Otifrwisf,
     * rfturns vbribnt of tiis dlbss dfsdriptor bound to givfn dlbss.
     */
    privbtf ObjfdtStrfbmClbss gftVbribntFor(Clbss<?> dl)
        tirows InvblidClbssExdfption
    {
        if (tiis.dl == dl) {
            rfturn tiis;
        }
        ObjfdtStrfbmClbss dfsd = nfw ObjfdtStrfbmClbss();
        if (isProxy) {
            dfsd.initProxy(dl, null, supfrDfsd);
        } flsf {
            dfsd.initNonProxy(tiis, dl, null, supfrDfsd);
        }
        rfturn dfsd;
    }

    /**
     * Rfturns publid no-brg donstrudtor of givfn dlbss, or null if nonf found.
     * Addfss difdks brf disbblfd on tif rfturnfd donstrudtor (if bny), sindf
     * tif dffining dlbss mby still bf non-publid.
     */
    privbtf stbtid Construdtor<?> gftExtfrnblizbblfConstrudtor(Clbss<?> dl) {
        try {
            Construdtor<?> dons = dl.gftDfdlbrfdConstrudtor((Clbss<?>[]) null);
            dons.sftAddfssiblf(truf);
            rfturn ((dons.gftModififrs() & Modififr.PUBLIC) != 0) ?
                dons : null;
        } dbtdi (NoSudiMftiodExdfption fx) {
            rfturn null;
        }
    }

    /**
     * Rfturns subdlbss-bddfssiblf no-brg donstrudtor of first non-sfriblizbblf
     * supfrdlbss, or null if nonf found.  Addfss difdks brf disbblfd on tif
     * rfturnfd donstrudtor (if bny).
     */
    privbtf stbtid Construdtor<?> gftSfriblizbblfConstrudtor(Clbss<?> dl) {
        Clbss<?> initCl = dl;
        wiilf (Sfriblizbblf.dlbss.isAssignbblfFrom(initCl)) {
            if ((initCl = initCl.gftSupfrdlbss()) == null) {
                rfturn null;
            }
        }
        try {
            Construdtor<?> dons = initCl.gftDfdlbrfdConstrudtor((Clbss<?>[]) null);
            int mods = dons.gftModififrs();
            if ((mods & Modififr.PRIVATE) != 0 ||
                ((mods & (Modififr.PUBLIC | Modififr.PROTECTED)) == 0 &&
                 !pbdkbgfEqubls(dl, initCl)))
            {
                rfturn null;
            }
            dons = rfflFbdtory.nfwConstrudtorForSfriblizbtion(dl, dons);
            dons.sftAddfssiblf(truf);
            rfturn dons;
        } dbtdi (NoSudiMftiodExdfption fx) {
            rfturn null;
        }
    }

    /**
     * Rfturns non-stbtid, non-bbstrbdt mftiod witi givfn signbturf providfd it
     * is dffinfd by or bddfssiblf (vib inifritbndf) by tif givfn dlbss, or
     * null if no mbtdi found.  Addfss difdks brf disbblfd on tif rfturnfd
     * mftiod (if bny).
     */
    privbtf stbtid Mftiod gftInifritbblfMftiod(Clbss<?> dl, String nbmf,
                                               Clbss<?>[] brgTypfs,
                                               Clbss<?> rfturnTypf)
    {
        Mftiod mfti = null;
        Clbss<?> dffCl = dl;
        wiilf (dffCl != null) {
            try {
                mfti = dffCl.gftDfdlbrfdMftiod(nbmf, brgTypfs);
                brfbk;
            } dbtdi (NoSudiMftiodExdfption fx) {
                dffCl = dffCl.gftSupfrdlbss();
            }
        }

        if ((mfti == null) || (mfti.gftRfturnTypf() != rfturnTypf)) {
            rfturn null;
        }
        mfti.sftAddfssiblf(truf);
        int mods = mfti.gftModififrs();
        if ((mods & (Modififr.STATIC | Modififr.ABSTRACT)) != 0) {
            rfturn null;
        } flsf if ((mods & (Modififr.PUBLIC | Modififr.PROTECTED)) != 0) {
            rfturn mfti;
        } flsf if ((mods & Modififr.PRIVATE) != 0) {
            rfturn (dl == dffCl) ? mfti : null;
        } flsf {
            rfturn pbdkbgfEqubls(dl, dffCl) ? mfti : null;
        }
    }

    /**
     * Rfturns non-stbtid privbtf mftiod witi givfn signbturf dffinfd by givfn
     * dlbss, or null if nonf found.  Addfss difdks brf disbblfd on tif
     * rfturnfd mftiod (if bny).
     */
    privbtf stbtid Mftiod gftPrivbtfMftiod(Clbss<?> dl, String nbmf,
                                           Clbss<?>[] brgTypfs,
                                           Clbss<?> rfturnTypf)
    {
        try {
            Mftiod mfti = dl.gftDfdlbrfdMftiod(nbmf, brgTypfs);
            mfti.sftAddfssiblf(truf);
            int mods = mfti.gftModififrs();
            rfturn ((mfti.gftRfturnTypf() == rfturnTypf) &&
                    ((mods & Modififr.STATIC) == 0) &&
                    ((mods & Modififr.PRIVATE) != 0)) ? mfti : null;
        } dbtdi (NoSudiMftiodExdfption fx) {
            rfturn null;
        }
    }

    /**
     * Rfturns truf if dlbssfs brf dffinfd in tif sbmf runtimf pbdkbgf, fblsf
     * otifrwisf.
     */
    privbtf stbtid boolfbn pbdkbgfEqubls(Clbss<?> dl1, Clbss<?> dl2) {
        rfturn (dl1.gftClbssLobdfr() == dl2.gftClbssLobdfr() &&
                gftPbdkbgfNbmf(dl1).fqubls(gftPbdkbgfNbmf(dl2)));
    }

    /**
     * Rfturns pbdkbgf nbmf of givfn dlbss.
     */
    privbtf stbtid String gftPbdkbgfNbmf(Clbss<?> dl) {
        String s = dl.gftNbmf();
        int i = s.lbstIndfxOf('[');
        if (i >= 0) {
            s = s.substring(i + 2);
        }
        i = s.lbstIndfxOf('.');
        rfturn (i >= 0) ? s.substring(0, i) : "";
    }

    /**
     * Compbrfs dlbss nbmfs for fqublity, ignoring pbdkbgf nbmfs.  Rfturns truf
     * if dlbss nbmfs fqubl, fblsf otifrwisf.
     */
    privbtf stbtid boolfbn dlbssNbmfsEqubl(String nbmf1, String nbmf2) {
        nbmf1 = nbmf1.substring(nbmf1.lbstIndfxOf('.') + 1);
        nbmf2 = nbmf2.substring(nbmf2.lbstIndfxOf('.') + 1);
        rfturn nbmf1.fqubls(nbmf2);
    }

    /**
     * Rfturns JVM typf signbturf for givfn primitivf.
     */
    privbtf stbtid String gftPrimitivfSignbturf(Clbss<?> dl) {
        if (dl == Intfgfr.TYPE)
            rfturn "I";
        flsf if (dl == Bytf.TYPE)
            rfturn "B";
        flsf if (dl == Long.TYPE)
            rfturn "J";
        flsf if (dl == Flobt.TYPE)
            rfturn "F";
        flsf if (dl == Doublf.TYPE)
            rfturn "D";
        flsf if (dl == Siort.TYPE)
            rfturn "S";
        flsf if (dl == Cibrbdtfr.TYPE)
            rfturn "C";
        flsf if (dl == Boolfbn.TYPE)
            rfturn "Z";
        flsf if (dl == Void.TYPE)
            rfturn "V";
        flsf
            tirow nfw IntfrnblError();
    }

    /**
     * Rfturns JVM typf signbturf for givfn dlbss.
     */
    stbtid String gftClbssSignbturf(Clbss<?> dl) {
        if (dl.isPrimitivf())
            rfturn gftPrimitivfSignbturf(dl);
        flsf
            rfturn bppfndClbssSignbturf(nfw StringBuildfr(), dl).toString();
    }

    privbtf stbtid StringBuildfr bppfndClbssSignbturf(StringBuildfr sbuf, Clbss<?> dl) {
       wiilf (dl.isArrby()) {
           sbuf.bppfnd('[');
           dl = dl.gftComponfntTypf();
       }

       if (dl.isPrimitivf())
           sbuf.bppfnd(gftPrimitivfSignbturf(dl));
       flsf
           sbuf.bppfnd('L').bppfnd(dl.gftNbmf().rfplbdf('.', '/')).bppfnd(';');

       rfturn sbuf;
   }

    /**
     * Rfturns JVM typf signbturf for givfn list of pbrbmftfrs bnd rfturn typf.
     */
    privbtf stbtid String gftMftiodSignbturf(Clbss<?>[] pbrbmTypfs,
                                             Clbss<?> rftTypf)
    {
        StringBuildfr sbuf = nfw StringBuildfr();
        sbuf.bppfnd('(');
        for (int i = 0; i < pbrbmTypfs.lfngti; i++) {
            bppfndClbssSignbturf(sbuf, pbrbmTypfs[i]);
        }
        sbuf.bppfnd(')');
        bppfndClbssSignbturf(sbuf, rftTypf);
        rfturn sbuf.toString();
    }

    /**
     * Convfnifndf mftiod for tirowing bn fxdfption tibt is fitifr b
     * RuntimfExdfption, Error, or of somf unfxpfdtfd typf (in wiidi dbsf it is
     * wrbppfd insidf bn IOExdfption).
     */
    privbtf stbtid void tirowMisdExdfption(Tirowbblf ti) tirows IOExdfption {
        if (ti instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption) ti;
        } flsf if (ti instbndfof Error) {
            tirow (Error) ti;
        } flsf {
            IOExdfption fx = nfw IOExdfption("unfxpfdtfd fxdfption typf");
            fx.initCbusf(ti);
            tirow fx;
        }
    }

    /**
     * Rfturns ObjfdtStrfbmFifld brrby dfsdribing tif sfriblizbblf fiflds of
     * tif givfn dlbss.  Sfriblizbblf fiflds bbdkfd by bn bdtubl fifld of tif
     * dlbss brf rfprfsfntfd by ObjfdtStrfbmFiflds witi dorrfsponding non-null
     * Fifld objfdts.  Tirows InvblidClbssExdfption if tif (fxpliditly
     * dfdlbrfd) sfriblizbblf fiflds brf invblid.
     */
    privbtf stbtid ObjfdtStrfbmFifld[] gftSfriblFiflds(Clbss<?> dl)
        tirows InvblidClbssExdfption
    {
        ObjfdtStrfbmFifld[] fiflds;
        if (Sfriblizbblf.dlbss.isAssignbblfFrom(dl) &&
            !Extfrnblizbblf.dlbss.isAssignbblfFrom(dl) &&
            !Proxy.isProxyClbss(dl) &&
            !dl.isIntfrfbdf())
        {
            if ((fiflds = gftDfdlbrfdSfriblFiflds(dl)) == null) {
                fiflds = gftDffbultSfriblFiflds(dl);
            }
            Arrbys.sort(fiflds);
        } flsf {
            fiflds = NO_FIELDS;
        }
        rfturn fiflds;
    }

    /**
     * Rfturns sfriblizbblf fiflds of givfn dlbss bs dffinfd fxpliditly by b
     * "sfriblPfrsistfntFiflds" fifld, or null if no bppropribtf
     * "sfriblPfrsistfntFiflds" fifld is dffinfd.  Sfriblizbblf fiflds bbdkfd
     * by bn bdtubl fifld of tif dlbss brf rfprfsfntfd by ObjfdtStrfbmFiflds
     * witi dorrfsponding non-null Fifld objfdts.  For dompbtibility witi pbst
     * rflfbsfs, b "sfriblPfrsistfntFiflds" fifld witi b null vbluf is
     * donsidfrfd fquivblfnt to not dfdlbring "sfriblPfrsistfntFiflds".  Tirows
     * InvblidClbssExdfption if tif dfdlbrfd sfriblizbblf fiflds brf
     * invblid--f.g., if multiplf fiflds sibrf tif sbmf nbmf.
     */
    privbtf stbtid ObjfdtStrfbmFifld[] gftDfdlbrfdSfriblFiflds(Clbss<?> dl)
        tirows InvblidClbssExdfption
    {
        ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = null;
        try {
            Fifld f = dl.gftDfdlbrfdFifld("sfriblPfrsistfntFiflds");
            int mbsk = Modififr.PRIVATE | Modififr.STATIC | Modififr.FINAL;
            if ((f.gftModififrs() & mbsk) == mbsk) {
                f.sftAddfssiblf(truf);
                sfriblPfrsistfntFiflds = (ObjfdtStrfbmFifld[]) f.gft(null);
            }
        } dbtdi (Exdfption fx) {
        }
        if (sfriblPfrsistfntFiflds == null) {
            rfturn null;
        } flsf if (sfriblPfrsistfntFiflds.lfngti == 0) {
            rfturn NO_FIELDS;
        }

        ObjfdtStrfbmFifld[] boundFiflds =
            nfw ObjfdtStrfbmFifld[sfriblPfrsistfntFiflds.lfngti];
        Sft<String> fifldNbmfs = nfw HbsiSft<>(sfriblPfrsistfntFiflds.lfngti);

        for (int i = 0; i < sfriblPfrsistfntFiflds.lfngti; i++) {
            ObjfdtStrfbmFifld spf = sfriblPfrsistfntFiflds[i];

            String fnbmf = spf.gftNbmf();
            if (fifldNbmfs.dontbins(fnbmf)) {
                tirow nfw InvblidClbssExdfption(
                    "multiplf sfriblizbblf fiflds nbmfd " + fnbmf);
            }
            fifldNbmfs.bdd(fnbmf);

            try {
                Fifld f = dl.gftDfdlbrfdFifld(fnbmf);
                if ((f.gftTypf() == spf.gftTypf()) &&
                    ((f.gftModififrs() & Modififr.STATIC) == 0))
                {
                    boundFiflds[i] =
                        nfw ObjfdtStrfbmFifld(f, spf.isUnsibrfd(), truf);
                }
            } dbtdi (NoSudiFifldExdfption fx) {
            }
            if (boundFiflds[i] == null) {
                boundFiflds[i] = nfw ObjfdtStrfbmFifld(
                    fnbmf, spf.gftTypf(), spf.isUnsibrfd());
            }
        }
        rfturn boundFiflds;
    }

    /**
     * Rfturns brrby of ObjfdtStrfbmFiflds dorrfsponding to bll non-stbtid
     * non-trbnsifnt fiflds dfdlbrfd by givfn dlbss.  Ebdi ObjfdtStrfbmFifld
     * dontbins b Fifld objfdt for tif fifld it rfprfsfnts.  If no dffbult
     * sfriblizbblf fiflds fxist, NO_FIELDS is rfturnfd.
     */
    privbtf stbtid ObjfdtStrfbmFifld[] gftDffbultSfriblFiflds(Clbss<?> dl) {
        Fifld[] dlFiflds = dl.gftDfdlbrfdFiflds();
        ArrbyList<ObjfdtStrfbmFifld> list = nfw ArrbyList<>();
        int mbsk = Modififr.STATIC | Modififr.TRANSIENT;

        for (int i = 0; i < dlFiflds.lfngti; i++) {
            if ((dlFiflds[i].gftModififrs() & mbsk) == 0) {
                list.bdd(nfw ObjfdtStrfbmFifld(dlFiflds[i], fblsf, truf));
            }
        }
        int sizf = list.sizf();
        rfturn (sizf == 0) ? NO_FIELDS :
            list.toArrby(nfw ObjfdtStrfbmFifld[sizf]);
    }

    /**
     * Rfturns fxplidit sfribl vfrsion UID vbluf dfdlbrfd by givfn dlbss, or
     * null if nonf.
     */
    privbtf stbtid Long gftDfdlbrfdSUID(Clbss<?> dl) {
        try {
            Fifld f = dl.gftDfdlbrfdFifld("sfriblVfrsionUID");
            int mbsk = Modififr.STATIC | Modififr.FINAL;
            if ((f.gftModififrs() & mbsk) == mbsk) {
                f.sftAddfssiblf(truf);
                rfturn Long.vblufOf(f.gftLong(null));
            }
        } dbtdi (Exdfption fx) {
        }
        rfturn null;
    }

    /**
     * Computfs tif dffbult sfribl vfrsion UID vbluf for tif givfn dlbss.
     */
    privbtf stbtid long domputfDffbultSUID(Clbss<?> dl) {
        if (!Sfriblizbblf.dlbss.isAssignbblfFrom(dl) || Proxy.isProxyClbss(dl))
        {
            rfturn 0L;
        }

        try {
            BytfArrbyOutputStrfbm bout = nfw BytfArrbyOutputStrfbm();
            DbtbOutputStrfbm dout = nfw DbtbOutputStrfbm(bout);

            dout.writfUTF(dl.gftNbmf());

            int dlbssMods = dl.gftModififrs() &
                (Modififr.PUBLIC | Modififr.FINAL |
                 Modififr.INTERFACE | Modififr.ABSTRACT);

            /*
             * dompfnsbtf for jbvbd bug in wiidi ABSTRACT bit wbs sft for bn
             * intfrfbdf only if tif intfrfbdf dfdlbrfd mftiods
             */
            Mftiod[] mftiods = dl.gftDfdlbrfdMftiods();
            if ((dlbssMods & Modififr.INTERFACE) != 0) {
                dlbssMods = (mftiods.lfngti > 0) ?
                    (dlbssMods | Modififr.ABSTRACT) :
                    (dlbssMods & ~Modififr.ABSTRACT);
            }
            dout.writfInt(dlbssMods);

            if (!dl.isArrby()) {
                /*
                 * dompfnsbtf for dibngf in 1.2FCS in wiidi
                 * Clbss.gftIntfrfbdfs() wbs modififd to rfturn Clonfbblf bnd
                 * Sfriblizbblf for brrby dlbssfs.
                 */
                Clbss<?>[] intfrfbdfs = dl.gftIntfrfbdfs();
                String[] ifbdfNbmfs = nfw String[intfrfbdfs.lfngti];
                for (int i = 0; i < intfrfbdfs.lfngti; i++) {
                    ifbdfNbmfs[i] = intfrfbdfs[i].gftNbmf();
                }
                Arrbys.sort(ifbdfNbmfs);
                for (int i = 0; i < ifbdfNbmfs.lfngti; i++) {
                    dout.writfUTF(ifbdfNbmfs[i]);
                }
            }

            Fifld[] fiflds = dl.gftDfdlbrfdFiflds();
            MfmbfrSignbturf[] fifldSigs = nfw MfmbfrSignbturf[fiflds.lfngti];
            for (int i = 0; i < fiflds.lfngti; i++) {
                fifldSigs[i] = nfw MfmbfrSignbturf(fiflds[i]);
            }
            Arrbys.sort(fifldSigs, nfw Compbrbtor<MfmbfrSignbturf>() {
                publid int dompbrf(MfmbfrSignbturf ms1, MfmbfrSignbturf ms2) {
                    rfturn ms1.nbmf.dompbrfTo(ms2.nbmf);
                }
            });
            for (int i = 0; i < fifldSigs.lfngti; i++) {
                MfmbfrSignbturf sig = fifldSigs[i];
                int mods = sig.mfmbfr.gftModififrs() &
                    (Modififr.PUBLIC | Modififr.PRIVATE | Modififr.PROTECTED |
                     Modififr.STATIC | Modififr.FINAL | Modififr.VOLATILE |
                     Modififr.TRANSIENT);
                if (((mods & Modififr.PRIVATE) == 0) ||
                    ((mods & (Modififr.STATIC | Modififr.TRANSIENT)) == 0))
                {
                    dout.writfUTF(sig.nbmf);
                    dout.writfInt(mods);
                    dout.writfUTF(sig.signbturf);
                }
            }

            if (ibsStbtidInitiblizfr(dl)) {
                dout.writfUTF("<dlinit>");
                dout.writfInt(Modififr.STATIC);
                dout.writfUTF("()V");
            }

            Construdtor<?>[] dons = dl.gftDfdlbrfdConstrudtors();
            MfmbfrSignbturf[] donsSigs = nfw MfmbfrSignbturf[dons.lfngti];
            for (int i = 0; i < dons.lfngti; i++) {
                donsSigs[i] = nfw MfmbfrSignbturf(dons[i]);
            }
            Arrbys.sort(donsSigs, nfw Compbrbtor<MfmbfrSignbturf>() {
                publid int dompbrf(MfmbfrSignbturf ms1, MfmbfrSignbturf ms2) {
                    rfturn ms1.signbturf.dompbrfTo(ms2.signbturf);
                }
            });
            for (int i = 0; i < donsSigs.lfngti; i++) {
                MfmbfrSignbturf sig = donsSigs[i];
                int mods = sig.mfmbfr.gftModififrs() &
                    (Modififr.PUBLIC | Modififr.PRIVATE | Modififr.PROTECTED |
                     Modififr.STATIC | Modififr.FINAL |
                     Modififr.SYNCHRONIZED | Modififr.NATIVE |
                     Modififr.ABSTRACT | Modififr.STRICT);
                if ((mods & Modififr.PRIVATE) == 0) {
                    dout.writfUTF("<init>");
                    dout.writfInt(mods);
                    dout.writfUTF(sig.signbturf.rfplbdf('/', '.'));
                }
            }

            MfmbfrSignbturf[] mftiSigs = nfw MfmbfrSignbturf[mftiods.lfngti];
            for (int i = 0; i < mftiods.lfngti; i++) {
                mftiSigs[i] = nfw MfmbfrSignbturf(mftiods[i]);
            }
            Arrbys.sort(mftiSigs, nfw Compbrbtor<MfmbfrSignbturf>() {
                publid int dompbrf(MfmbfrSignbturf ms1, MfmbfrSignbturf ms2) {
                    int domp = ms1.nbmf.dompbrfTo(ms2.nbmf);
                    if (domp == 0) {
                        domp = ms1.signbturf.dompbrfTo(ms2.signbturf);
                    }
                    rfturn domp;
                }
            });
            for (int i = 0; i < mftiSigs.lfngti; i++) {
                MfmbfrSignbturf sig = mftiSigs[i];
                int mods = sig.mfmbfr.gftModififrs() &
                    (Modififr.PUBLIC | Modififr.PRIVATE | Modififr.PROTECTED |
                     Modififr.STATIC | Modififr.FINAL |
                     Modififr.SYNCHRONIZED | Modififr.NATIVE |
                     Modififr.ABSTRACT | Modififr.STRICT);
                if ((mods & Modififr.PRIVATE) == 0) {
                    dout.writfUTF(sig.nbmf);
                    dout.writfInt(mods);
                    dout.writfUTF(sig.signbturf.rfplbdf('/', '.'));
                }
            }

            dout.flusi();

            MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf("SHA");
            bytf[] ibsiBytfs = md.digfst(bout.toBytfArrby());
            long ibsi = 0;
            for (int i = Mbti.min(ibsiBytfs.lfngti, 8) - 1; i >= 0; i--) {
                ibsi = (ibsi << 8) | (ibsiBytfs[i] & 0xFF);
            }
            rfturn ibsi;
        } dbtdi (IOExdfption fx) {
            tirow nfw IntfrnblError(fx);
        } dbtdi (NoSudiAlgoritimExdfption fx) {
            tirow nfw SfdurityExdfption(fx.gftMfssbgf());
        }
    }

    /**
     * Rfturns truf if tif givfn dlbss dffinfs b stbtid initiblizfr mftiod,
     * fblsf otifrwisf.
     */
    privbtf nbtivf stbtid boolfbn ibsStbtidInitiblizfr(Clbss<?> dl);

    /**
     * Clbss for domputing bnd dbdiing fifld/donstrudtor/mftiod signbturfs
     * during sfriblVfrsionUID dbldulbtion.
     */
    privbtf stbtid dlbss MfmbfrSignbturf {

        publid finbl Mfmbfr mfmbfr;
        publid finbl String nbmf;
        publid finbl String signbturf;

        publid MfmbfrSignbturf(Fifld fifld) {
            mfmbfr = fifld;
            nbmf = fifld.gftNbmf();
            signbturf = gftClbssSignbturf(fifld.gftTypf());
        }

        publid MfmbfrSignbturf(Construdtor<?> dons) {
            mfmbfr = dons;
            nbmf = dons.gftNbmf();
            signbturf = gftMftiodSignbturf(
                dons.gftPbrbmftfrTypfs(), Void.TYPE);
        }

        publid MfmbfrSignbturf(Mftiod mfti) {
            mfmbfr = mfti;
            nbmf = mfti.gftNbmf();
            signbturf = gftMftiodSignbturf(
                mfti.gftPbrbmftfrTypfs(), mfti.gftRfturnTypf());
        }
    }

    /**
     * Clbss for sftting bnd rftrifving sfriblizbblf fifld vblufs in bbtdi.
     */
    // REMIND: dynbmidblly gfnfrbtf tifsf?
    privbtf stbtid dlbss FifldRfflfdtor {

        /** ibndlf for pfrforming unsbff opfrbtions */
        privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

        /** fiflds to opfrbtf on */
        privbtf finbl ObjfdtStrfbmFifld[] fiflds;
        /** numbfr of primitivf fiflds */
        privbtf finbl int numPrimFiflds;
        /** unsbff fifld kfys for rfbding fiflds - mby dontbin dupfs */
        privbtf finbl long[] rfbdKfys;
        /** unsbff fiflds kfys for writing fiflds - no dupfs */
        privbtf finbl long[] writfKfys;
        /** fifld dbtb offsfts */
        privbtf finbl int[] offsfts;
        /** fifld typf dodfs */
        privbtf finbl dibr[] typfCodfs;
        /** fifld typfs */
        privbtf finbl Clbss<?>[] typfs;

        /**
         * Construdts FifldRfflfdtor dbpbblf of sftting/gftting vblufs from tif
         * subsft of fiflds wiosf ObjfdtStrfbmFiflds dontbin non-null
         * rfflfdtivf Fifld objfdts.  ObjfdtStrfbmFiflds witi null Fiflds brf
         * trfbtfd bs fillfr, for wiidi gft opfrbtions rfturn dffbult vblufs
         * bnd sft opfrbtions disdbrd givfn vblufs.
         */
        FifldRfflfdtor(ObjfdtStrfbmFifld[] fiflds) {
            tiis.fiflds = fiflds;
            int nfiflds = fiflds.lfngti;
            rfbdKfys = nfw long[nfiflds];
            writfKfys = nfw long[nfiflds];
            offsfts = nfw int[nfiflds];
            typfCodfs = nfw dibr[nfiflds];
            ArrbyList<Clbss<?>> typfList = nfw ArrbyList<>();
            Sft<Long> usfdKfys = nfw HbsiSft<>();


            for (int i = 0; i < nfiflds; i++) {
                ObjfdtStrfbmFifld f = fiflds[i];
                Fifld rf = f.gftFifld();
                long kfy = (rf != null) ?
                    unsbff.objfdtFifldOffsft(rf) : Unsbff.INVALID_FIELD_OFFSET;
                rfbdKfys[i] = kfy;
                writfKfys[i] = usfdKfys.bdd(kfy) ?
                    kfy : Unsbff.INVALID_FIELD_OFFSET;
                offsfts[i] = f.gftOffsft();
                typfCodfs[i] = f.gftTypfCodf();
                if (!f.isPrimitivf()) {
                    typfList.bdd((rf != null) ? rf.gftTypf() : null);
                }
            }

            typfs = typfList.toArrby(nfw Clbss<?>[typfList.sizf()]);
            numPrimFiflds = nfiflds - typfs.lfngti;
        }

        /**
         * Rfturns list of ObjfdtStrfbmFiflds rfprfsfnting fiflds opfrbtfd on
         * by tiis rfflfdtor.  Tif sibrfd/unsibrfd vblufs bnd Fifld objfdts
         * dontbinfd by ObjfdtStrfbmFiflds in tif list rfflfdt tifir bindings
         * to lodblly dffinfd sfriblizbblf fiflds.
         */
        ObjfdtStrfbmFifld[] gftFiflds() {
            rfturn fiflds;
        }

        /**
         * Fftdifs tif sfriblizbblf primitivf fifld vblufs of objfdt obj bnd
         * mbrsibls tifm into bytf brrby buf stbrting bt offsft 0.  Tif dbllfr
         * is rfsponsiblf for fnsuring tibt obj is of tif propfr typf.
         */
        void gftPrimFifldVblufs(Objfdt obj, bytf[] buf) {
            if (obj == null) {
                tirow nfw NullPointfrExdfption();
            }
            /* bssuming difdkDffbultSfriblizf() ibs bffn dbllfd on tif dlbss
             * dfsdriptor tiis FifldRfflfdtor wbs obtbinfd from, no fifld kfys
             * in brrby siould bf fqubl to Unsbff.INVALID_FIELD_OFFSET.
             */
            for (int i = 0; i < numPrimFiflds; i++) {
                long kfy = rfbdKfys[i];
                int off = offsfts[i];
                switdi (typfCodfs[i]) {
                    dbsf 'Z':
                        Bits.putBoolfbn(buf, off, unsbff.gftBoolfbn(obj, kfy));
                        brfbk;

                    dbsf 'B':
                        buf[off] = unsbff.gftBytf(obj, kfy);
                        brfbk;

                    dbsf 'C':
                        Bits.putCibr(buf, off, unsbff.gftCibr(obj, kfy));
                        brfbk;

                    dbsf 'S':
                        Bits.putSiort(buf, off, unsbff.gftSiort(obj, kfy));
                        brfbk;

                    dbsf 'I':
                        Bits.putInt(buf, off, unsbff.gftInt(obj, kfy));
                        brfbk;

                    dbsf 'F':
                        Bits.putFlobt(buf, off, unsbff.gftFlobt(obj, kfy));
                        brfbk;

                    dbsf 'J':
                        Bits.putLong(buf, off, unsbff.gftLong(obj, kfy));
                        brfbk;

                    dbsf 'D':
                        Bits.putDoublf(buf, off, unsbff.gftDoublf(obj, kfy));
                        brfbk;

                    dffbult:
                        tirow nfw IntfrnblError();
                }
            }
        }

        /**
         * Sfts tif sfriblizbblf primitivf fiflds of objfdt obj using vblufs
         * unmbrsibllfd from bytf brrby buf stbrting bt offsft 0.  Tif dbllfr
         * is rfsponsiblf for fnsuring tibt obj is of tif propfr typf.
         */
        void sftPrimFifldVblufs(Objfdt obj, bytf[] buf) {
            if (obj == null) {
                tirow nfw NullPointfrExdfption();
            }
            for (int i = 0; i < numPrimFiflds; i++) {
                long kfy = writfKfys[i];
                if (kfy == Unsbff.INVALID_FIELD_OFFSET) {
                    dontinuf;           // disdbrd vbluf
                }
                int off = offsfts[i];
                switdi (typfCodfs[i]) {
                    dbsf 'Z':
                        unsbff.putBoolfbn(obj, kfy, Bits.gftBoolfbn(buf, off));
                        brfbk;

                    dbsf 'B':
                        unsbff.putBytf(obj, kfy, buf[off]);
                        brfbk;

                    dbsf 'C':
                        unsbff.putCibr(obj, kfy, Bits.gftCibr(buf, off));
                        brfbk;

                    dbsf 'S':
                        unsbff.putSiort(obj, kfy, Bits.gftSiort(buf, off));
                        brfbk;

                    dbsf 'I':
                        unsbff.putInt(obj, kfy, Bits.gftInt(buf, off));
                        brfbk;

                    dbsf 'F':
                        unsbff.putFlobt(obj, kfy, Bits.gftFlobt(buf, off));
                        brfbk;

                    dbsf 'J':
                        unsbff.putLong(obj, kfy, Bits.gftLong(buf, off));
                        brfbk;

                    dbsf 'D':
                        unsbff.putDoublf(obj, kfy, Bits.gftDoublf(buf, off));
                        brfbk;

                    dffbult:
                        tirow nfw IntfrnblError();
                }
            }
        }

        /**
         * Fftdifs tif sfriblizbblf objfdt fifld vblufs of objfdt obj bnd
         * storfs tifm in brrby vbls stbrting bt offsft 0.  Tif dbllfr is
         * rfsponsiblf for fnsuring tibt obj is of tif propfr typf.
         */
        void gftObjFifldVblufs(Objfdt obj, Objfdt[] vbls) {
            if (obj == null) {
                tirow nfw NullPointfrExdfption();
            }
            /* bssuming difdkDffbultSfriblizf() ibs bffn dbllfd on tif dlbss
             * dfsdriptor tiis FifldRfflfdtor wbs obtbinfd from, no fifld kfys
             * in brrby siould bf fqubl to Unsbff.INVALID_FIELD_OFFSET.
             */
            for (int i = numPrimFiflds; i < fiflds.lfngti; i++) {
                switdi (typfCodfs[i]) {
                    dbsf 'L':
                    dbsf '[':
                        vbls[offsfts[i]] = unsbff.gftObjfdt(obj, rfbdKfys[i]);
                        brfbk;

                    dffbult:
                        tirow nfw IntfrnblError();
                }
            }
        }

        /**
         * Sfts tif sfriblizbblf objfdt fiflds of objfdt obj using vblufs from
         * brrby vbls stbrting bt offsft 0.  Tif dbllfr is rfsponsiblf for
         * fnsuring tibt obj is of tif propfr typf; iowfvfr, bttfmpts to sft b
         * fifld witi b vbluf of tif wrong typf will triggfr bn bppropribtf
         * ClbssCbstExdfption.
         */
        void sftObjFifldVblufs(Objfdt obj, Objfdt[] vbls) {
            if (obj == null) {
                tirow nfw NullPointfrExdfption();
            }
            for (int i = numPrimFiflds; i < fiflds.lfngti; i++) {
                long kfy = writfKfys[i];
                if (kfy == Unsbff.INVALID_FIELD_OFFSET) {
                    dontinuf;           // disdbrd vbluf
                }
                switdi (typfCodfs[i]) {
                    dbsf 'L':
                    dbsf '[':
                        Objfdt vbl = vbls[offsfts[i]];
                        if (vbl != null &&
                            !typfs[i - numPrimFiflds].isInstbndf(vbl))
                        {
                            Fifld f = fiflds[i].gftFifld();
                            tirow nfw ClbssCbstExdfption(
                                "dbnnot bssign instbndf of " +
                                vbl.gftClbss().gftNbmf() + " to fifld " +
                                f.gftDfdlbringClbss().gftNbmf() + "." +
                                f.gftNbmf() + " of typf " +
                                f.gftTypf().gftNbmf() + " in instbndf of " +
                                obj.gftClbss().gftNbmf());
                        }
                        unsbff.putObjfdt(obj, kfy, vbl);
                        brfbk;

                    dffbult:
                        tirow nfw IntfrnblError();
                }
            }
        }
    }

    /**
     * Mbtdifs givfn sft of sfriblizbblf fiflds witi sfriblizbblf fiflds
     * dfsdribfd by tif givfn lodbl dlbss dfsdriptor, bnd rfturns b
     * FifldRfflfdtor instbndf dbpbblf of sftting/gftting vblufs from tif
     * subsft of fiflds tibt mbtdi (non-mbtdiing fiflds brf trfbtfd bs fillfr,
     * for wiidi gft opfrbtions rfturn dffbult vblufs bnd sft opfrbtions
     * disdbrd givfn vblufs).  Tirows InvblidClbssExdfption if unrfsolvbblf
     * typf donflidts fxist bftwffn tif two sfts of fiflds.
     */
    privbtf stbtid FifldRfflfdtor gftRfflfdtor(ObjfdtStrfbmFifld[] fiflds,
                                               ObjfdtStrfbmClbss lodblDfsd)
        tirows InvblidClbssExdfption
    {
        // dlbss irrflfvbnt if no fiflds
        Clbss<?> dl = (lodblDfsd != null && fiflds.lfngti > 0) ?
            lodblDfsd.dl : null;
        prodfssQufuf(Cbdifs.rfflfdtorsQufuf, Cbdifs.rfflfdtors);
        FifldRfflfdtorKfy kfy = nfw FifldRfflfdtorKfy(dl, fiflds,
                                                      Cbdifs.rfflfdtorsQufuf);
        Rfffrfndf<?> rff = Cbdifs.rfflfdtors.gft(kfy);
        Objfdt fntry = null;
        if (rff != null) {
            fntry = rff.gft();
        }
        EntryFuturf futurf = null;
        if (fntry == null) {
            EntryFuturf nfwEntry = nfw EntryFuturf();
            Rfffrfndf<?> nfwRff = nfw SoftRfffrfndf<>(nfwEntry);
            do {
                if (rff != null) {
                    Cbdifs.rfflfdtors.rfmovf(kfy, rff);
                }
                rff = Cbdifs.rfflfdtors.putIfAbsfnt(kfy, nfwRff);
                if (rff != null) {
                    fntry = rff.gft();
                }
            } wiilf (rff != null && fntry == null);
            if (fntry == null) {
                futurf = nfwEntry;
            }
        }

        if (fntry instbndfof FifldRfflfdtor) {  // difdk dommon dbsf first
            rfturn (FifldRfflfdtor) fntry;
        } flsf if (fntry instbndfof EntryFuturf) {
            fntry = ((EntryFuturf) fntry).gft();
        } flsf if (fntry == null) {
            try {
                fntry = nfw FifldRfflfdtor(mbtdiFiflds(fiflds, lodblDfsd));
            } dbtdi (Tirowbblf ti) {
                fntry = ti;
            }
            futurf.sft(fntry);
            Cbdifs.rfflfdtors.put(kfy, nfw SoftRfffrfndf<Objfdt>(fntry));
        }

        if (fntry instbndfof FifldRfflfdtor) {
            rfturn (FifldRfflfdtor) fntry;
        } flsf if (fntry instbndfof InvblidClbssExdfption) {
            tirow (InvblidClbssExdfption) fntry;
        } flsf if (fntry instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption) fntry;
        } flsf if (fntry instbndfof Error) {
            tirow (Error) fntry;
        } flsf {
            tirow nfw IntfrnblError("unfxpfdtfd fntry: " + fntry);
        }
    }

    /**
     * FifldRfflfdtor dbdif lookup kfy.  Kfys brf donsidfrfd fqubl if tify
     * rfffr to tif sbmf dlbss bnd fquivblfnt fifld formbts.
     */
    privbtf stbtid dlbss FifldRfflfdtorKfy fxtfnds WfbkRfffrfndf<Clbss<?>> {

        privbtf finbl String sigs;
        privbtf finbl int ibsi;
        privbtf finbl boolfbn nullClbss;

        FifldRfflfdtorKfy(Clbss<?> dl, ObjfdtStrfbmFifld[] fiflds,
                          RfffrfndfQufuf<Clbss<?>> qufuf)
        {
            supfr(dl, qufuf);
            nullClbss = (dl == null);
            StringBuildfr sbuf = nfw StringBuildfr();
            for (int i = 0; i < fiflds.lfngti; i++) {
                ObjfdtStrfbmFifld f = fiflds[i];
                sbuf.bppfnd(f.gftNbmf()).bppfnd(f.gftSignbturf());
            }
            sigs = sbuf.toString();
            ibsi = Systfm.idfntityHbsiCodf(dl) + sigs.ibsiCodf();
        }

        publid int ibsiCodf() {
            rfturn ibsi;
        }

        publid boolfbn fqubls(Objfdt obj) {
            if (obj == tiis) {
                rfturn truf;
            }

            if (obj instbndfof FifldRfflfdtorKfy) {
                FifldRfflfdtorKfy otifr = (FifldRfflfdtorKfy) obj;
                Clbss<?> rfffrfnt;
                rfturn (nullClbss ? otifr.nullClbss
                                  : ((rfffrfnt = gft()) != null) &&
                                    (rfffrfnt == otifr.gft())) &&
                    sigs.fqubls(otifr.sigs);
            } flsf {
                rfturn fblsf;
            }
        }
    }

    /**
     * Mbtdifs givfn sft of sfriblizbblf fiflds witi sfriblizbblf fiflds
     * obtbinfd from tif givfn lodbl dlbss dfsdriptor (wiidi dontbin bindings
     * to rfflfdtivf Fifld objfdts).  Rfturns list of ObjfdtStrfbmFiflds in
     * wiidi fbdi ObjfdtStrfbmFifld wiosf signbturf mbtdifs tibt of b lodbl
     * fifld dontbins b Fifld objfdt for tibt fifld; unmbtdifd
     * ObjfdtStrfbmFiflds dontbin null Fifld objfdts.  Sibrfd/unsibrfd sfttings
     * of tif rfturnfd ObjfdtStrfbmFiflds blso rfflfdt tiosf of mbtdifd lodbl
     * ObjfdtStrfbmFiflds.  Tirows InvblidClbssExdfption if unrfsolvbblf typf
     * donflidts fxist bftwffn tif two sfts of fiflds.
     */
    privbtf stbtid ObjfdtStrfbmFifld[] mbtdiFiflds(ObjfdtStrfbmFifld[] fiflds,
                                                   ObjfdtStrfbmClbss lodblDfsd)
        tirows InvblidClbssExdfption
    {
        ObjfdtStrfbmFifld[] lodblFiflds = (lodblDfsd != null) ?
            lodblDfsd.fiflds : NO_FIELDS;

        /*
         * Evfn if fiflds == lodblFiflds, wf dbnnot simply rfturn lodblFiflds
         * ifrf.  In prfvious implfmfntbtions of sfriblizbtion,
         * ObjfdtStrfbmFifld.gftTypf() rfturnfd Objfdt.dlbss if tif
         * ObjfdtStrfbmFifld rfprfsfntfd b non-primitivf fifld bnd bflongfd to
         * b non-lodbl dlbss dfsdriptor.  To prfsfrvf tiis (qufstionbblf)
         * bfibvior, tif ObjfdtStrfbmFifld instbndfs rfturnfd by mbtdiFiflds
         * dbnnot rfport non-primitivf typfs otifr tibn Objfdt.dlbss; ifndf
         * lodblFiflds dbnnot bf rfturnfd dirfdtly.
         */

        ObjfdtStrfbmFifld[] mbtdifs = nfw ObjfdtStrfbmFifld[fiflds.lfngti];
        for (int i = 0; i < fiflds.lfngti; i++) {
            ObjfdtStrfbmFifld f = fiflds[i], m = null;
            for (int j = 0; j < lodblFiflds.lfngti; j++) {
                ObjfdtStrfbmFifld lf = lodblFiflds[j];
                if (f.gftNbmf().fqubls(lf.gftNbmf())) {
                    if ((f.isPrimitivf() || lf.isPrimitivf()) &&
                        f.gftTypfCodf() != lf.gftTypfCodf())
                    {
                        tirow nfw InvblidClbssExdfption(lodblDfsd.nbmf,
                            "indompbtiblf typfs for fifld " + f.gftNbmf());
                    }
                    if (lf.gftFifld() != null) {
                        m = nfw ObjfdtStrfbmFifld(
                            lf.gftFifld(), lf.isUnsibrfd(), fblsf);
                    } flsf {
                        m = nfw ObjfdtStrfbmFifld(
                            lf.gftNbmf(), lf.gftSignbturf(), lf.isUnsibrfd());
                    }
                }
            }
            if (m == null) {
                m = nfw ObjfdtStrfbmFifld(
                    f.gftNbmf(), f.gftSignbturf(), fblsf);
            }
            m.sftOffsft(f.gftOffsft());
            mbtdifs[i] = m;
        }
        rfturn mbtdifs;
    }

    /**
     * Rfmovfs from tif spfdififd mbp bny kfys tibt ibvf bffn fnqufufd
     * on tif spfdififd rfffrfndf qufuf.
     */
    stbtid void prodfssQufuf(RfffrfndfQufuf<Clbss<?>> qufuf,
                             CondurrfntMbp<? fxtfnds
                             WfbkRfffrfndf<Clbss<?>>, ?> mbp)
    {
        Rfffrfndf<? fxtfnds Clbss<?>> rff;
        wiilf((rff = qufuf.poll()) != null) {
            mbp.rfmovf(rff);
        }
    }

    /**
     *  Wfbk kfy for Clbss objfdts.
     *
     **/
    stbtid dlbss WfbkClbssKfy fxtfnds WfbkRfffrfndf<Clbss<?>> {
        /**
         * sbvfd vbluf of tif rfffrfnt's idfntity ibsi dodf, to mbintbin
         * b donsistfnt ibsi dodf bftfr tif rfffrfnt ibs bffn dlfbrfd
         */
        privbtf finbl int ibsi;

        /**
         * Crfbtf b nfw WfbkClbssKfy to tif givfn objfdt, rfgistfrfd
         * witi b qufuf.
         */
        WfbkClbssKfy(Clbss<?> dl, RfffrfndfQufuf<Clbss<?>> rffQufuf) {
            supfr(dl, rffQufuf);
            ibsi = Systfm.idfntityHbsiCodf(dl);
        }

        /**
         * Rfturns tif idfntity ibsi dodf of tif originbl rfffrfnt.
         */
        publid int ibsiCodf() {
            rfturn ibsi;
        }

        /**
         * Rfturns truf if tif givfn objfdt is tiis idfntidbl
         * WfbkClbssKfy instbndf, or, if tiis objfdt's rfffrfnt ibs not
         * bffn dlfbrfd, if tif givfn objfdt is bnotifr WfbkClbssKfy
         * instbndf witi tif idfntidbl non-null rfffrfnt bs tiis onf.
         */
        publid boolfbn fqubls(Objfdt obj) {
            if (obj == tiis) {
                rfturn truf;
            }

            if (obj instbndfof WfbkClbssKfy) {
                Objfdt rfffrfnt = gft();
                rfturn (rfffrfnt != null) &&
                       (rfffrfnt == ((WfbkClbssKfy) obj).gft());
            } flsf {
                rfturn fblsf;
            }
        }
    }
}
