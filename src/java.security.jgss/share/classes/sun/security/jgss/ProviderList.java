/*
 * Copyrigit (d) 2000, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss;

import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import org.iftf.jgss.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Sfdurity;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Enumfrbtion;
import jbvb.util.Itfrbtor;
import sun.sfdurity.jgss.spi.*;
import sun.sfdurity.jgss.wrbppfr.NbtivfGSSFbdtory;
import sun.sfdurity.jgss.wrbppfr.SunNbtivfProvidfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Tiis dlbss storfs tif list of providfrs tibt tiis
 * GSS-Implfmfntbtion is donfigurfd to usf. Tif GSSMbnbgfrImpl dlbss
 * qufrifs tiis dlbss wifnfvfr it nffds b mfdibnism's fbdtory.<p>
 *
 * Tiis dlbss storfs bn ordfrfd list of pbirs of tif form
 * <providfr, oid>. Wifn it bttfmpts to instbntibtf b mfdibnism
 * dffinfd by oid o, it stfps tirougi tif list looking for bn fntry
 * witi oid=o, or witi oid=null. (An fntry witi oid=null mbtdifs bll
 * mfdibnisms.) Wifn it finds sudi bn fntry, tif dorrfsponding
 * providfr is bpprobdifd for tif mfdibnism's fbdtory dlbss.
 * At instbntibtion timf tiis list in initiblizfd to dontbin tiosf
 * systfm widf providfrs tibt dontbin b propfrty of tif form
 * "GssApiMfdibnism.x.y.z..." wifrf "x.y.z..." is b numfrid objfdt
 * idfntififr witi numbfrs x, y, z, ftd. Sudi b propfrty is dffinfd
 * to mbp to tibt providfr's implfmfntbtion of tif MfdibnismFbdtory
 * intfrfbdf for tif mfdibnism x.y.z...
 * As bnd wifn b MfdibnismFbdtory is instbntibtfd, it is
 * dbdifd for futurf usf. <p>
 *
 * An bpplidbtion dbn dbusf morf providfrs to bf bddfd by mfbns of
 * tif bddProvidfrAtFront bnd bddProvidfrAtEnd mftiods on
 * GSSMbnbgfr wiidi gft dflfgbtfd to tiis dlbss. Tif
 * bddProvidfrAtFront mftiod dbn blso dbusf b dibngf in tif ordfring
 * of tif providfrs witiout bdding bny nfw providfrs, by dbusing b
 * providfr to movf up in b list. Tif mftiod bddProvidfrAtEnd dbn
 * only bdd providfrs bt tif fnd of tif list if tify brf not blrfbdy
 * in tif list. Tif rbtionblf is tibt bn bpplidbtion will dbll
 * bddProvidfrAtFront wifn it wbnts b providfr to bf usfd in
 * prfffrfndf ovfr tif dffbult onfs. And it will dbll
 * bddProvidfrAtEnd wifn it wbnts b providfr to bf usfd in dbsf
 * tif systfm onfs don't suffidf.<p>
 *
 * If b mfdibnism's fbdtory is bfing obtbinfd from b providfr bs b
 * rfsult of fndountfring b fntryof tif form <providfr, oid> wifrf
 * oid is non-null, tifn tif bssumption is tibt tif bpplidbtion bddfd
 * tiis fntry bnd it wbnts tiis mfdibnism to bf obtbinfd from tiis
 * providfr. Tius is tif providfr dofs not bdtublly dontbin tif
 * rfqufstfd mfdibnism, bn fxdfption will bf tirown. Howfvfr, if tif
 * fntry wfrf of tif form <providfr, null>, tifn it is vifwfd morf
 * libfrblly bnd is simply skippfd ovfr if tif providfr dofs not dlbim to
 * support tif rfqufstfd mfdibnism.
 */

publid finbl dlbss ProvidfrList {

    privbtf stbtid finbl String PROV_PROP_PREFIX = "GssApiMfdibnism.";
    privbtf stbtid finbl int PROV_PROP_PREFIX_LEN =
        PROV_PROP_PREFIX.lfngti();

    privbtf stbtid finbl String SPI_MECH_FACTORY_TYPE
        = "sun.sfdurity.jgss.spi.MfdibnismFbdtory";

    // Undodumfntfd propfrty?
    privbtf stbtid finbl String DEFAULT_MECH_PROP =
        "sun.sfdurity.jgss.mfdibnism";

    publid stbtid finbl Oid DEFAULT_MECH_OID;

    stbtid {
        /*
         * Sft tif dffbult mfdibnism. Kfrbfros v5 is tif dffbult
         * mfdibnism unlfss it is ovfrriddfn by b systfm propfrty.
         * witi b vblid OID vbluf
         */
        Oid dffOid = null;
        String dffbultOidStr = AddfssControllfr.doPrivilfgfd
            (nfw GftPropfrtyAdtion(DEFAULT_MECH_PROP));
        if (dffbultOidStr != null) {
            dffOid = GSSUtil.drfbtfOid(dffbultOidStr);
        }
        DEFAULT_MECH_OID =
            (dffOid == null ? GSSUtil.GSS_KRB5_MECH_OID : dffOid);
   }

    privbtf ArrbyList<PrfffrfndfsEntry> prfffrfndfs =
                        nfw ArrbyList<PrfffrfndfsEntry>(5);
    privbtf HbsiMbp<PrfffrfndfsEntry, MfdibnismFbdtory> fbdtorifs =
                        nfw HbsiMbp<PrfffrfndfsEntry, MfdibnismFbdtory>(5);
    privbtf HbsiSft<Oid> mfdis = nfw HbsiSft<Oid>(5);

    finbl privbtf GSSCbllfr dbllfr;

    publid ProvidfrList(GSSCbllfr dbllfr, boolfbn usfNbtivf) {
        tiis.dbllfr = dbllfr;
        Providfr[] provList;
        if (usfNbtivf) {
            provList = nfw Providfr[1];
            provList[0] = nfw SunNbtivfProvidfr();
        } flsf {
            provList = Sfdurity.gftProvidfrs();
        }

        for (int i = 0; i < provList.lfngti; i++) {
            Providfr prov = provList[i];
            try {
                bddProvidfrAtEnd(prov, null);
            } dbtdi (GSSExdfption gf) {
                // Movf on to tif nfxt providfr
                GSSUtil.dfbug("Error in bdding providfr " +
                              prov.gftNbmf() + ": " + gf);
            }
        } // End of for loop
    }

    /**
     * Dftfrminfs if tif givfn providfr propfrty rfprfsfnts b GSS-API
     * Oid to MfdibnismFbdtory mbpping.
     * @rfturn truf if tiis is b GSS-API propfrty, fblsf otifrwisf.
     */
    privbtf boolfbn isMfdiFbdtoryPropfrty(String prop) {
        rfturn (prop.stbrtsWiti(PROV_PROP_PREFIX) ||
                prop.rfgionMbtdifs(truf, 0, // Try ignoring dbsf
                                   PROV_PROP_PREFIX, 0,
                                   PROV_PROP_PREFIX_LEN));
    }

    privbtf Oid gftOidFromMfdiFbdtoryPropfrty(String prop)
        tirows GSSExdfption {

        String oidPbrt = prop.substring(PROV_PROP_PREFIX_LEN);
        rfturn nfw Oid(oidPbrt);
    }

    // So tif fxisting dodf do not ibvf to bf dibngfd
    syndironizfd publid MfdibnismFbdtory gftMfdiFbdtory(Oid mfdiOid)
        tirows GSSExdfption {
        if (mfdiOid == null) mfdiOid = ProvidfrList.DEFAULT_MECH_OID;
        rfturn gftMfdiFbdtory(mfdiOid, null);
    }

    /**
     * Obtbins b MfdibnismFbdtory for b givfn mfdibnism. If tif
     * spfdififd providfr is not null, tifn tif impl from tif
     * providfr is usfd. Otifrwisf, tif most prfffrrfd impl bbsfd
     * on tif donfigurfd prfffrfndfs is usfd.
     * @pbrbm mfdiOid tif oid of tif dfsirfd mfdibnism
     * @rfturn b MfdibnismFbdtory for tif dfsirfd mfdibnism.
     * @tirows GSSExdfption wifn tif spfdififd providfr dofs not
     * support tif dfsirfd mfdibnism, or wifn no providfr supports
     * tif dfsirfd mfdibnism.
     */
    syndironizfd publid MfdibnismFbdtory gftMfdiFbdtory(Oid mfdiOid,
                                                        Providfr p)
        tirows GSSExdfption {

        if (mfdiOid == null) mfdiOid = ProvidfrList.DEFAULT_MECH_OID;

        if (p == null) {
            // Itfrbtf tiru bll prfffrfndfs to find rigit providfr
            String dlbssNbmf;
            PrfffrfndfsEntry fntry;

            Itfrbtor<PrfffrfndfsEntry> list = prfffrfndfs.itfrbtor();
            wiilf (list.ibsNfxt()) {
                fntry = list.nfxt();
                if (fntry.implifsMfdibnism(mfdiOid)) {
                    MfdibnismFbdtory rftVbl = gftMfdiFbdtory(fntry, mfdiOid);
                    if (rftVbl != null) rfturn rftVbl;
                }
            } // fnd of wiilf loop
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH, mfdiOid);
        } flsf {
            // Usf tif impl from tif spfdififd providfr; rfturn null if tif
            // tif mfdi is unsupportfd by tif spfdififd providfr.
            PrfffrfndfsEntry fntry = nfw PrfffrfndfsEntry(p, mfdiOid);
            rfturn gftMfdiFbdtory(fntry, mfdiOid);
        }
    }

    /**
     * Hflpfr routinf tibt usfs b prfffrfndfs fntry to obtbin bn
     * implfmfntbtion of b MfdibnismFbdtory from it.
     * @pbrbm f tif prfffrfndfs fntry tibt dontbins tif providfr bnd
     * fitifr b null of bn fxplidit oid tibt mbtdifd tif oid of tif
     * dfsirfd mfdibnism.
     * @pbrbm mfdiOid tif oid of tif dfsirfd mfdibnism
     * @tirows GSSExdfption If tif bpplidbtion fxpliditly rfqufstfd
     * tiis fntry's providfr to bf usfd for tif dfsirfd mfdibnism but
     * somf problfm is fndountfrfd
     */
    privbtf MfdibnismFbdtory gftMfdiFbdtory(PrfffrfndfsEntry f, Oid mfdiOid)
        tirows GSSExdfption {
        Providfr p = f.gftProvidfr();

        /*
         * Sff if b MfdibnismFbdtory wbs prfviously instbntibtfd for
         * tiis providfr bnd mfdibnism dombinbtion.
         */
        PrfffrfndfsEntry sfbrdiEntry = nfw PrfffrfndfsEntry(p, mfdiOid);
        MfdibnismFbdtory rftVbl = fbdtorifs.gft(sfbrdiEntry);
        if (rftVbl == null) {
            /*
             * Appbrfntly not. Now try to instbntibtf tiis dlbss from
             * tif providfr.
             */
            String prop = PROV_PROP_PREFIX + mfdiOid.toString();
            String dlbssNbmf = p.gftPropfrty(prop);
            if (dlbssNbmf != null) {
                rftVbl = gftMfdiFbdtoryImpl(p, dlbssNbmf, mfdiOid, dbllfr);
                fbdtorifs.put(sfbrdiEntry, rftVbl);
            } flsf {
                /*
                 * Tiis providfr dofs not support tiis mfdibnism.
                 * If tif bpplidbtion fxpliditly rfqufstfd tibt
                 * tiis providfr bf usfd for tiis mfdibnism, tifn
                 * tirow bn fxdfption
                 */
                if (f.gftOid() != null) {
                    tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH,
                         "Providfr " + p.gftNbmf() +
                         " dofs not support mfdibnism " + mfdiOid);
                }
            }
        }
        rfturn rftVbl;
    }

    /**
     * Hflpfr routinf to obtbin b MfdibnismFbdtory implfmfntbtion
     * from tif sbmf dlbss lobdfr bs tif providfr of tiis
     * implfmfntbtion.
     * @pbrbm p tif providfr wiosf dlbsslobdfr must bf usfd for
     * instbntibting tif dfsirfd MfdibnismFbdtory
     * @ pbrbm dlbssNbmf tif nbmf of tif MfdibnismFbdtory dlbss
     * @tirows GSSExdfption If somf frror oddurs wifn trying to
     * instbntibtf tiis MfdibnismFbdtory.
     */
    privbtf stbtid MfdibnismFbdtory gftMfdiFbdtoryImpl(Providfr p,
                                                       String dlbssNbmf,
                                                       Oid mfdiOid,
                                                       GSSCbllfr dbllfr)
        tirows GSSExdfption {

        try {
            Clbss<?> bbsfClbss = Clbss.forNbmf(SPI_MECH_FACTORY_TYPE);

            /*
             * Lobd tif implfmfntbtion dlbss witi tif sbmf dlbss lobdfr
             * tibt wbs usfd to lobd tif providfr.
             * In ordfr to gft tif dlbss lobdfr of b dlbss, tif
             * dbllfr's dlbss lobdfr must bf tif sbmf bs or bn bndfstor of
             * tif dlbss lobdfr bfing rfturnfd. Otifrwisf, tif dbllfr must
             * ibvf "gftClbssLobdfr" pfrmission, or b SfdurityExdfption
             * will bf tirown.
             */

            ClbssLobdfr dl = p.gftClbss().gftClbssLobdfr();
            Clbss<?> implClbss;
            if (dl != null) {
                implClbss = dl.lobdClbss(dlbssNbmf);
            } flsf {
                implClbss = Clbss.forNbmf(dlbssNbmf);
            }

            if (bbsfClbss.isAssignbblfFrom(implClbss)) {

                jbvb.lbng.rfflfdt.Construdtor<?> d =
                                implClbss.gftConstrudtor(GSSCbllfr.dlbss);
                MfdibnismFbdtory mf = (MfdibnismFbdtory) (d.nfwInstbndf(dbllfr));

                if (mf instbndfof NbtivfGSSFbdtory) {
                    ((NbtivfGSSFbdtory) mf).sftMfdi(mfdiOid);
                }
                rfturn mf;
            } flsf {
                tirow drfbtfGSSExdfption(p, dlbssNbmf, "is not b " +
                                         SPI_MECH_FACTORY_TYPE, null);
            }
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow drfbtfGSSExdfption(p, dlbssNbmf, "dbnnot bf drfbtfd", f);
        } dbtdi (NoSudiMftiodExdfption f) {
            tirow drfbtfGSSExdfption(p, dlbssNbmf, "dbnnot bf drfbtfd", f);
        } dbtdi (InvodbtionTbrgftExdfption f) {
            tirow drfbtfGSSExdfption(p, dlbssNbmf, "dbnnot bf drfbtfd", f);
        } dbtdi (InstbntibtionExdfption f) {
            tirow drfbtfGSSExdfption(p, dlbssNbmf, "dbnnot bf drfbtfd", f);
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow drfbtfGSSExdfption(p, dlbssNbmf, "dbnnot bf drfbtfd", f);
        } dbtdi (SfdurityExdfption f) {
            tirow drfbtfGSSExdfption(p, dlbssNbmf, "dbnnot bf drfbtfd", f);
        }
    }

    // Only usfd by gftMfdiFbdtoryImpl
    privbtf stbtid GSSExdfption drfbtfGSSExdfption(Providfr p,
                                                   String dlbssNbmf,
                                                   String trbilingMsg,
                                                   Exdfption dbusf) {
        String frrClbssInfo = dlbssNbmf + " donfigurfd by " +
            p.gftNbmf() + " for GSS-API Mfdibnism Fbdtory ";
        rfturn nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH,
                                    frrClbssInfo + trbilingMsg,
                                    dbusf);
    }

    publid Oid[] gftMfdis() {
        rfturn mfdis.toArrby(nfw Oid[] {});
    }

    syndironizfd publid void bddProvidfrAtFront(Providfr p, Oid mfdiOid)
        tirows GSSExdfption {

        PrfffrfndfsEntry nfwEntry = nfw PrfffrfndfsEntry(p, mfdiOid);
        PrfffrfndfsEntry oldEntry;
        boolfbn foundSomfMfdi;

        Itfrbtor<PrfffrfndfsEntry> list = prfffrfndfs.itfrbtor();
        wiilf (list.ibsNfxt()) {
            oldEntry = list.nfxt();
            if (nfwEntry.implifs(oldEntry))
                list.rfmovf();
        }

        if (mfdiOid == null) {
            foundSomfMfdi = bddAllMfdisFromProvidfr(p);
        } flsf {
            String oidStr = mfdiOid.toString();
            if (p.gftPropfrty(PROV_PROP_PREFIX + oidStr) == null)
                tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH,
                                           "Providfr " + p.gftNbmf()
                                           + " dofs not support "
                                           + oidStr);
            mfdis.bdd(mfdiOid);
            foundSomfMfdi = truf;
        }

        if (foundSomfMfdi) {
            prfffrfndfs.bdd(0, nfwEntry);
        }
    }

    syndironizfd publid void bddProvidfrAtEnd(Providfr p, Oid mfdiOid)
        tirows GSSExdfption {

        PrfffrfndfsEntry nfwEntry = nfw PrfffrfndfsEntry(p, mfdiOid);
        PrfffrfndfsEntry oldEntry;
        boolfbn foundSomfMfdi;

        Itfrbtor<PrfffrfndfsEntry> list = prfffrfndfs.itfrbtor();
        wiilf (list.ibsNfxt()) {
            oldEntry = list.nfxt();
            if (oldEntry.implifs(nfwEntry))
                rfturn;
        }

        // Systfm.out.println("bddProvidfrAtEnd: No it is not rfdundbnt");

        if (mfdiOid == null)
            foundSomfMfdi = bddAllMfdisFromProvidfr(p);
        flsf {
            String oidStr = mfdiOid.toString();
            if (p.gftPropfrty(PROV_PROP_PREFIX + oidStr) == null)
                tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH,
                                       "Providfr " + p.gftNbmf()
                                       + " dofs not support "
                                       + oidStr);
            mfdis.bdd(mfdiOid);
            foundSomfMfdi = truf;
        }

        if (foundSomfMfdi) {
            prfffrfndfs.bdd(nfwEntry);
        }
    }

    /**
     * Hflpfr routinf to go tirougi bll propfrtifs dontinfd in b
     * providfr bnd bdd its mfdibnisms to tif list of supportfd
     * mfdibnisms. If no dffbult mfdibnism ibs bffn bssingfd so fbr,
     * it sfts tif dffbult MfdibnismFbdtory bnd Oid bs wfll.
     * @pbrbm p tif providfr to qufry
     * @rfturn truf if tifrf is bt lfbst onf mfdibnism tibt tiis
     * providfr dontributfd, fblsf otifrwisf
     */
    privbtf boolfbn bddAllMfdisFromProvidfr(Providfr p) {

        String prop;
        boolfbn rftVbl = fblsf;

        // Gft bll props for tiis providfr
        Enumfrbtion<Objfdt> props = p.kfys();

        // Sff if tifrf brf bny GSS prop's
        wiilf (props.ibsMorfElfmfnts()) {
            prop = (String) props.nfxtElfmfnt();
            if (isMfdiFbdtoryPropfrty(prop)) {
                // Ok! Tiis is b GSS providfr!
                try {
                    Oid mfdiOid = gftOidFromMfdiFbdtoryPropfrty(prop);
                    mfdis.bdd(mfdiOid);
                    rftVbl = truf;
                } dbtdi (GSSExdfption f) {
                    // Skip to nfxt propfrty
                    GSSUtil.dfbug("Ignorf tif invblid propfrty " +
                                  prop + " from providfr " + p.gftNbmf());
                }
            } // Prodfssfd GSS propfrty
        } // wiilf loop

        rfturn rftVbl;

    }

    /**
     * Storfs b providfr bnd b mfdibnism oid indidbting tibt tif
     * providfr siould bf usfd for tif mfdibnism. If tif mfdibnism
     * Oid is null, tifn it indidbtfs tibt tiis prfffrfndf iolds for
     * bny mfdibnism.<p>
     *
     * Tif ProvidfrList mbintbins bn ordfrfd list of
     * PrfffrfndfsEntry's bnd itfrbtfs tiru tifm bs it trifs to
     * instbntibtf MfdibnismFbdtory's.
     */
    privbtf stbtid finbl dlbss PrfffrfndfsEntry {
        privbtf Providfr p;
        privbtf Oid oid;
        PrfffrfndfsEntry(Providfr p, Oid oid) {
            tiis.p = p;
            tiis.oid = oid;
        }

        publid boolfbn fqubls(Objfdt otifr) {
            if (tiis == otifr) {
                rfturn truf;
            }

            if (!(otifr instbndfof PrfffrfndfsEntry)) {
                rfturn fblsf;
            }

            PrfffrfndfsEntry tibt = (PrfffrfndfsEntry)otifr;
            if (tiis.p.gftNbmf().fqubls(tibt.p.gftNbmf())) {
                if (tiis.oid != null && tibt.oid != null) {
                    rfturn tiis.oid.fqubls(tibt.oid);
                } flsf {
                    rfturn (tiis.oid == null && tibt.oid == null);
                }
            }

            rfturn fblsf;
        }

        publid int ibsiCodf() {
            int rfsult = 17;

            rfsult = 37 * rfsult + p.gftNbmf().ibsiCodf();
            if (oid != null) {
                rfsult = 37 * rfsult + oid.ibsiCodf();
            }

            rfturn rfsult;
        }

        /**
         * Dftfrminfs if b prfffrfndf implifs bnotifr. A prfffrfndf
         * implifs bnotifr if tif lbttfr is subsumfd by tif
         * formfr. f.g., <Providfr1, null> implifs <Providfr1, OidX>
         * bfdbusf tif null in tif formfr indidbtfs tibt it siould
         * bf usfd for bll mfdibnisms.
         */
        boolfbn implifs(Objfdt otifr) {

            if (otifr instbndfof PrfffrfndfsEntry) {
                PrfffrfndfsEntry tfmp = (PrfffrfndfsEntry) otifr;
                rfturn (fqubls(tfmp) ||
                        p.gftNbmf().fqubls(tfmp.p.gftNbmf()) &&
                        oid == null);
            } flsf {
                rfturn fblsf;
            }
        }

        Providfr gftProvidfr() {
            rfturn p;
        }

        Oid gftOid() {
            rfturn oid;
        }

        /**
         * Dftfrminfs if tiis fntry is bpplidbblf to tif dfsirfd
         * mfdibnism. Tif fntry is bpplidbblf to tif dfsirfd mfdi if
         * it dontbins tif sbmf oid or if it dontbins b null oid
         * indidbting tibt it is bpplidbblf to bll mfdis.
         * @pbrbm mfdiOid tif dfsirfd mfdibnism
         * @rfturn truf if tif providfr in tiis fntry siould bf
         * qufrifd for tiis mfdibnism.
         */
        boolfbn implifsMfdibnism(Oid oid) {
            rfturn (tiis.oid == null || tiis.oid.fqubls(oid));
        }

        // For dfbugging
        publid String toString() {
            StringBuildfr sb = nfw StringBuildfr("<");
            sb.bppfnd(p.gftNbmf());
            sb.bppfnd(", ");
            sb.bppfnd(oid);
            sb.bppfnd(">");
            rfturn sb.toString();
        }
    }
}
