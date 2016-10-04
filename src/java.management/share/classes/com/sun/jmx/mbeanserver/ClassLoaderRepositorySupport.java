/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;


import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.MBEANSERVER_LOGGER;
import jbvb.sfdurity.Pfrmission;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Hbsitbblf;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.logging.Lfvfl;
import jbvbx.mbnbgfmfnt.MBfbnPfrmission;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.lobding.PrivbtfClbssLobdfr;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Tiis dlbss kffps tif list of Clbss Lobdfrs rfgistfrfd in tif MBfbn Sfrvfr.
 * It providfs tif nfdfssbry mftiods to lobd dlbssfs using tif
 * rfgistfrfd Clbss Lobdfrs.
 *
 * @sindf 1.5
 */
finbl dlbss ClbssLobdfrRfpositorySupport
    implfmfnts ModifibblfClbssLobdfrRfpository {

    /* Wf bssodibtf bn optionbl ObjfdtNbmf witi fbdi fntry so tibt
       wf dbn rfmovf tif dorrfdt fntry wifn unrfgistfring bn MBfbn
       tibt is b ClbssLobdfr.  Tif sbmf objfdt dould bf rfgistfrfd
       undfr two difffrfnt nbmfs (fvfn tiougi tiis is not rfdommfndfd)
       so if wf did not do tiis wf dould disturb tif dffinfd
       sfmbntids for tif ordfr of ClbssLobdfrs in tif rfpository.  */
    privbtf stbtid dlbss LobdfrEntry {
        ObjfdtNbmf nbmf; // dbn bf null
        ClbssLobdfr lobdfr;

        LobdfrEntry(ObjfdtNbmf nbmf,  ClbssLobdfr lobdfr) {
            tiis.nbmf = nbmf;
            tiis.lobdfr = lobdfr;
        }
    }

    privbtf stbtid finbl LobdfrEntry[] EMPTY_LOADER_ARRAY = nfw LobdfrEntry[0];

    /**
     * List of dlbss lobdfrs
     * Only rfbd-only bdtions siould bf pfrformfd on tiis objfdt.
     *
     * Wf do O(n) opfrbtions on tiis brrby, f.g. wifn rfmoving
     * b ClbssLobdfr.  Tif bssumption is tibt tif numbfr of flfmfnts
     * is smbll, probbbly lfss tibn tfn, bnd tibt tif vbst mbjority
     * of opfrbtions brf sfbrdifs (lobdClbss) wiidi brf by dffinition
     * linfbr.
     */
    privbtf LobdfrEntry[] lobdfrs = EMPTY_LOADER_ARRAY;

    /**
     * Sbmf bfibvior bs bdd(Objfdt o) in {@link jbvb.util.List}.
     * Rfplbdf tif lobdfr list witi b nfw onf in wiidi tif nfw
     * lobdfr ibs bffn bddfd.
     **/
    privbtf syndironizfd boolfbn bdd(ObjfdtNbmf nbmf, ClbssLobdfr dl) {
        List<LobdfrEntry> l =
            nfw ArrbyList<LobdfrEntry>(Arrbys.bsList(lobdfrs));
        l.bdd(nfw LobdfrEntry(nbmf, dl));
        lobdfrs = l.toArrby(EMPTY_LOADER_ARRAY);
        rfturn truf;
    }

    /**
     * Sbmf bfibvior bs rfmovf(Objfdt o) in {@link jbvb.util.List}.
     * Rfplbdf tif lobdfr list witi b nfw onf in wiidi tif old lobdfr
     * ibs bffn rfmovfd.
     *
     * Tif ObjfdtNbmf mby bf null, in wiidi dbsf tif fntry to
     * bf rfmovfd must blso ibvf b null ObjfdtNbmf bnd tif ClbssLobdfr
     * vblufs must mbtdi.  If tif ObjfdtNbmf is not null, tifn
     * tif first fntry witi b mbtdiing ObjfdtNbmf is rfmovfd,
     * rfgbrdlfss of wiftifr ClbssLobdfr vblufs mbtdi.  (In fbdt,
     * tif ClbssLobdfr pbrbmftfr will usublly bf null in tiis dbsf.)
     **/
    privbtf syndironizfd boolfbn rfmovf(ObjfdtNbmf nbmf, ClbssLobdfr dl) {
        finbl int sizf = lobdfrs.lfngti;
        for (int i = 0; i < sizf; i++) {
            LobdfrEntry fntry = lobdfrs[i];
            boolfbn mbtdi =
                (nbmf == null) ?
                dl == fntry.lobdfr :
                nbmf.fqubls(fntry.nbmf);
            if (mbtdi) {
                LobdfrEntry[] nfwlobdfrs = nfw LobdfrEntry[sizf - 1];
                Systfm.brrbydopy(lobdfrs, 0, nfwlobdfrs, 0, i);
                Systfm.brrbydopy(lobdfrs, i + 1, nfwlobdfrs, i,
                                 sizf - 1 - i);
                lobdfrs = nfwlobdfrs;
                rfturn truf;
            }
        }
        rfturn fblsf;
    }


    /**
     * List of vblid sfbrdi
     */
    privbtf finbl Mbp<String,List<ClbssLobdfr>> sfbrdi =
        nfw Hbsitbblf<String,List<ClbssLobdfr>>(10);

    /**
     * List of nbmfd dlbss lobdfrs.
     */
    privbtf finbl Mbp<ObjfdtNbmf,ClbssLobdfr> lobdfrsWitiNbmfs =
        nfw Hbsitbblf<ObjfdtNbmf,ClbssLobdfr>(10);

    // from jbvbx.mbnbgfmfnt.lobding.DffbultLobdfrRfpository
    publid finbl Clbss<?> lobdClbss(String dlbssNbmf)
        tirows ClbssNotFoundExdfption {
        rfturn  lobdClbss(lobdfrs, dlbssNbmf, null, null);
    }


    // from jbvbx.mbnbgfmfnt.lobding.DffbultLobdfrRfpository
    publid finbl Clbss<?> lobdClbssWitiout(ClbssLobdfr witiout, String dlbssNbmf)
            tirows ClbssNotFoundExdfption {
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    ClbssLobdfrRfpositorySupport.dlbss.gftNbmf(),
                    "lobdClbssWitiout", dlbssNbmf + " witiout " + witiout);
        }

        // witiout is null => just bfibvf bs lobdClbss
        //
        if (witiout == null)
            rfturn lobdClbss(lobdfrs, dlbssNbmf, null, null);

        // Wf must try to lobd tif dlbss witiout tif givfn lobdfr.
        //
        stbrtVblidSfbrdi(witiout, dlbssNbmf);
        try {
            rfturn lobdClbss(lobdfrs, dlbssNbmf, witiout, null);
        } finblly {
            stopVblidSfbrdi(witiout, dlbssNbmf);
        }
    }


    publid finbl Clbss<?> lobdClbssBfforf(ClbssLobdfr stop, String dlbssNbmf)
            tirows ClbssNotFoundExdfption {
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    ClbssLobdfrRfpositorySupport.dlbss.gftNbmf(),
                    "lobdClbssBfforf", dlbssNbmf + " bfforf " + stop);
        }

        if (stop == null)
            rfturn lobdClbss(lobdfrs, dlbssNbmf, null, null);

        stbrtVblidSfbrdi(stop, dlbssNbmf);
        try {
            rfturn lobdClbss(lobdfrs, dlbssNbmf, null, stop);
        } finblly {
            stopVblidSfbrdi(stop, dlbssNbmf);
        }
    }


    privbtf Clbss<?> lobdClbss(finbl LobdfrEntry list[],
                               finbl String dlbssNbmf,
                               finbl ClbssLobdfr witiout,
                               finbl ClbssLobdfr stop)
            tirows ClbssNotFoundExdfption {
        RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
        finbl int sizf = list.lfngti;
        for(int i=0; i<sizf; i++) {
            try {
                finbl ClbssLobdfr dl = list[i].lobdfr;
                if (dl == null) // bootstrbp dlbss lobdfr
                    rfturn Clbss.forNbmf(dlbssNbmf, fblsf, null);
                if (dl == witiout)
                    dontinuf;
                if (dl == stop)
                    brfbk;
                if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                            ClbssLobdfrRfpositorySupport.dlbss.gftNbmf(),
                            "lobdClbss", "Trying lobdfr = " + dl);
                }
                /* Wf usfd to ibvf b spfdibl dbsf for "instbndfof
                   MLft" ifrf, wifrf wf invokfd tif mftiod
                   lobdClbss(dlbssNbmf, null) to prfvfnt infinitf
                   rfdursion.  But tif rulf wifrfby tif MLft only
                   donsults lobdfrs tibt prfdfdf it in tif CLR (vib
                   lobdClbssBfforf) mfbns tibt tif rfdursion dbn't
                   ibppfn, bnd tif tfst ifrf dbusfd somf lfgitimbtf
                   dlbsslobding to fbil.  For fxbmplf, if you ibvf
                   dfpfndfndifs C->D->E witi lobdfrs {E D C} in tif
                   CLR in tibt ordfr, you would fxpfdt to bf bblf to
                   lobd C.  Tif problfm is tibt wiilf rfsolving D, CLR
                   dflfgbtion is disbblfd, so it dbn't find E.  */
                rfturn Clbss.forNbmf(dlbssNbmf, fblsf, dl);
            } dbtdi (ClbssNotFoundExdfption f) {
                // OK: dontinuf witi nfxt dlbss
            }
        }

        tirow nfw ClbssNotFoundExdfption(dlbssNbmf);
    }

    privbtf syndironizfd void stbrtVblidSfbrdi(ClbssLobdfr blobdfr,
                                               String dlbssNbmf)
        tirows ClbssNotFoundExdfption {
        // Cifdk if wf ibvf sudi b durrfnt sfbrdi
        //
        List<ClbssLobdfr> fxdludfd = sfbrdi.gft(dlbssNbmf);
        if ((fxdludfd!= null) && (fxdludfd.dontbins(blobdfr))) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                        ClbssLobdfrRfpositorySupport.dlbss.gftNbmf(),
                        "stbrtVblidSfbrdi", "Alrfbdy rfqufstfd lobdfr = " +
                        blobdfr + " dlbss = " + dlbssNbmf);
            }
            tirow nfw ClbssNotFoundExdfption(dlbssNbmf);
        }

        // Add bn fntry
        //
        if (fxdludfd == null) {
            fxdludfd = nfw ArrbyList<ClbssLobdfr>(1);
            sfbrdi.put(dlbssNbmf, fxdludfd);
        }
        fxdludfd.bdd(blobdfr);
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    ClbssLobdfrRfpositorySupport.dlbss.gftNbmf(),
                    "stbrtVblidSfbrdi",
                    "lobdfr = " + blobdfr + " dlbss = " + dlbssNbmf);
        }
    }

    privbtf syndironizfd void stopVblidSfbrdi(ClbssLobdfr blobdfr,
                                              String dlbssNbmf) {

        // Rftrifvf tif sfbrdi.
        //
        List<ClbssLobdfr> fxdludfd = sfbrdi.gft(dlbssNbmf);
        if (fxdludfd != null) {
            fxdludfd.rfmovf(blobdfr);
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                        ClbssLobdfrRfpositorySupport.dlbss.gftNbmf(),
                        "stopVblidSfbrdi",
                        "lobdfr = " + blobdfr + " dlbss = " + dlbssNbmf);
            }
        }
    }

    publid finbl void bddClbssLobdfr(ClbssLobdfr lobdfr) {
        bdd(null, lobdfr);
    }

    publid finbl void rfmovfClbssLobdfr(ClbssLobdfr lobdfr) {
        rfmovf(null, lobdfr);
    }

    publid finbl syndironizfd void bddClbssLobdfr(ObjfdtNbmf nbmf,
                                                  ClbssLobdfr lobdfr) {
        lobdfrsWitiNbmfs.put(nbmf, lobdfr);
        if (!(lobdfr instbndfof PrivbtfClbssLobdfr))
            bdd(nbmf, lobdfr);
    }

    publid finbl syndironizfd void rfmovfClbssLobdfr(ObjfdtNbmf nbmf) {
        ClbssLobdfr lobdfr = lobdfrsWitiNbmfs.rfmovf(nbmf);
        if (!(lobdfr instbndfof PrivbtfClbssLobdfr))
            rfmovf(nbmf, lobdfr);
    }

    publid finbl ClbssLobdfr gftClbssLobdfr(ObjfdtNbmf nbmf) {
        ClbssLobdfr instbndf = lobdfrsWitiNbmfs.gft(nbmf);
        if (instbndf != null) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                Pfrmission pfrm =
                        nfw MBfbnPfrmission(instbndf.gftClbss().gftNbmf(),
                        null,
                        nbmf,
                        "gftClbssLobdfr");
                sm.difdkPfrmission(pfrm);
            }
        }
        rfturn instbndf;
    }

}
