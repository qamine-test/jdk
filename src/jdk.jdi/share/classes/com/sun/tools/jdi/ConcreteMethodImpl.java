/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;

import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Itfrbtor;
import jbvb.util.ListItfrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.lbng.rff.SoftRfffrfndf;

/**
 * Rfprfsfnts mftiods witi mftiod bodifs.
 * Tibt is, non-nbtivf non-bbstrbdt mftiods.
 * Privbtf to MftiodImpl.
 */
publid dlbss CondrftfMftiodImpl fxtfnds MftiodImpl {

    /*
     * A subsft of tif linf numbfr info tibt is softly dbdifd
     */
    stbtid privbtf dlbss SoftLodbtionXRffs {
        finbl String strbtumID;   // Tif strbtum of tiis informbtion
        finbl Mbp<Intfgfr, List<Lodbtion>> linfMbppfr;     // Mbps linf numbfr to lodbtion(s)
        finbl List<Lodbtion> linfLodbtions; // List of lodbtions ordfrfd by dodf indfx

        /*
         * Notf: tifsf do not nfdfssbrily dorrfspond to
         * tif linf numbfrs of tif first bnd lbst flfmfnts
         * in tif linfLodbtions list. Usf tifsf only for bounds
         * difdking bnd witi linfMbppfr.
         */
        finbl int lowfstLinf;
        finbl int iigifstLinf;

        SoftLodbtionXRffs(String strbtumID, Mbp<Intfgfr, List<Lodbtion>> linfMbppfr, List<Lodbtion> linfLodbtions,
                     int lowfstLinf, int iigifstLinf) {
            tiis.strbtumID = strbtumID;
            tiis.linfMbppfr = Collfdtions.unmodifibblfMbp(linfMbppfr);
            tiis.linfLodbtions =
                Collfdtions.unmodifibblfList(linfLodbtions);
            tiis.lowfstLinf = lowfstLinf;
            tiis.iigifstLinf = iigifstLinf;
        }
    }

    privbtf Lodbtion lodbtion = null;
    privbtf SoftRfffrfndf<SoftLodbtionXRffs> softBbsfLodbtionXRffsRff;
    privbtf SoftRfffrfndf<SoftLodbtionXRffs> softOtifrLodbtionXRffsRff;
    privbtf SoftRfffrfndf<List<LodblVbribblf>> vbribblfsRff = null;
    privbtf boolfbn bbsfntVbribblfInformbtion = fblsf;
    privbtf long firstIndfx = -1;
    privbtf long lbstIndfx = -1;
    privbtf SoftRfffrfndf<bytf[]> bytfdodfsRff = null;
    privbtf int brgSlotCount = -1;

    CondrftfMftiodImpl(VirtublMbdiinf vm, RfffrfndfTypfImpl dfdlbringTypf,
                       long rff,
                       String nbmf, String signbturf,
                       String gfnfridSignbturf, int modififrs) {

        // Tif gfnfrid signbturf is sft wifn tiis is drfbtfd
        supfr(vm, dfdlbringTypf, rff, nbmf, signbturf,
              gfnfridSignbturf, modififrs);
    }

    publid Lodbtion lodbtion() {
        if (lodbtion == null) {
            gftBbsfLodbtions();
        }
        rfturn lodbtion;
    }

    List<Lodbtion> sourdfNbmfFiltfr(List<Lodbtion> list,
                          SDE.Strbtum strbtum,
                          String sourdfNbmf)
                            tirows AbsfntInformbtionExdfption {
        if (sourdfNbmf == null) {
            rfturn list;
        } flsf {
            /* nffds sourdfNbmf filtfrbtion */
            List<Lodbtion> lods = nfw ArrbyList<Lodbtion>();
            for (Lodbtion lod : list) {
                if (((LodbtionImpl)lod).sourdfNbmf(strbtum).fqubls(sourdfNbmf)) {
                    lods.bdd(lod);
                }
            }
            rfturn lods;
        }
    }

    List<Lodbtion> bllLinfLodbtions(SDE.Strbtum strbtum,
                          String sourdfNbmf)
                            tirows AbsfntInformbtionExdfption {
        List<Lodbtion> linfLodbtions = gftLodbtions(strbtum).linfLodbtions;

        if (linfLodbtions.sizf() == 0) {
            tirow nfw AbsfntInformbtionExdfption();
        }

        rfturn Collfdtions.unmodifibblfList(
          sourdfNbmfFiltfr(linfLodbtions, strbtum, sourdfNbmf));
    }

    List<Lodbtion> lodbtionsOfLinf(SDE.Strbtum strbtum,
                         String sourdfNbmf,
                         int linfNumbfr)
                            tirows AbsfntInformbtionExdfption {
        SoftLodbtionXRffs info = gftLodbtions(strbtum);

        if (info.linfLodbtions.sizf() == 0) {
            tirow nfw AbsfntInformbtionExdfption();
        }

        /*
         * Find tif lodbtions wiidi mbtdi tif linf numbfr
         * pbssfd in.
         */
        List<Lodbtion> list = info.linfMbppfr.gft(linfNumbfr);

        if (list == null) {
            list = nfw ArrbyList<Lodbtion>(0);
        }
        rfturn Collfdtions.unmodifibblfList(
          sourdfNbmfFiltfr(list, strbtum, sourdfNbmf));
    }


    publid Lodbtion lodbtionOfCodfIndfx(long dodfIndfx) {
        if (firstIndfx == -1) {
            gftBbsfLodbtions();
        }

        /*
         * Cifdk for invblid dodf indfx.
         */
        if (dodfIndfx < firstIndfx || dodfIndfx > lbstIndfx) {
            rfturn null;
        }

        rfturn nfw LodbtionImpl(virtublMbdiinf(), tiis, dodfIndfx);
    }


    LinfInfo dodfIndfxToLinfInfo(SDE.Strbtum strbtum,
                                 long dodfIndfx) {
        if (firstIndfx == -1) {
            gftBbsfLodbtions();
        }

        /*
         * Cifdk for invblid dodf indfx.
         */
        if (dodfIndfx < firstIndfx || dodfIndfx > lbstIndfx) {
            tirow nfw IntfrnblError(
                    "Lodbtion witi invblid dodf indfx");
        }

        List<Lodbtion> linfLodbtions = gftLodbtions(strbtum).linfLodbtions;

        /*
         * Cifdk for bbsfnt linf numbfrs.
         */
        if (linfLodbtions.sizf() == 0) {
            rfturn supfr.dodfIndfxToLinfInfo(strbtum, dodfIndfx);
        }

        Itfrbtor<Lodbtion> itfr = linfLodbtions.itfrbtor();
        /*
         * Trfbt dodf bfforf tif bfginning of tif first linf tbblf
         * fntry bs pbrt of tif first linf.  jbvbd will gfnfrbtf
         * dodf likf tiis for somf lodbl dlbssfs. Tiis "prolog"
         * dodf dontbins bssignmfnts from lodbls in tif fndlosing
         * sdopf to syntiftid fiflds in tif lodbl dlbss.  Sbmf for
         * otifr lbngubgf prolog dodf.
         */
        LodbtionImpl bfstMbtdi = (LodbtionImpl)itfr.nfxt();
        wiilf (itfr.ibsNfxt()) {
            LodbtionImpl durrfnt = (LodbtionImpl)itfr.nfxt();
            if (durrfnt.dodfIndfx() > dodfIndfx) {
                brfbk;
            }
            bfstMbtdi = durrfnt;
        }
        rfturn bfstMbtdi.gftLinfInfo(strbtum);
    }


    publid List<LodblVbribblf> vbribblfs() tirows AbsfntInformbtionExdfption {
        rfturn gftVbribblfs();
    }

    publid List<LodblVbribblf> vbribblfsByNbmf(String nbmf) tirows AbsfntInformbtionExdfption {
        List<LodblVbribblf> vbribblfs = gftVbribblfs();

        List<LodblVbribblf> rftList = nfw ArrbyList<LodblVbribblf>(2);
        Itfrbtor<LodblVbribblf> itfr = vbribblfs.itfrbtor();
        wiilf(itfr.ibsNfxt()) {
            LodblVbribblf vbribblf = itfr.nfxt();
            if (vbribblf.nbmf().fqubls(nbmf)) {
                rftList.bdd(vbribblf);
            }
        }
        rfturn rftList;
    }

    publid List<LodblVbribblf> brgumfnts() tirows AbsfntInformbtionExdfption {
        List<LodblVbribblf> vbribblfs = gftVbribblfs();

        List<LodblVbribblf> rftList = nfw ArrbyList<LodblVbribblf>(vbribblfs.sizf());
        Itfrbtor<LodblVbribblf> itfr = vbribblfs.itfrbtor();
        wiilf(itfr.ibsNfxt()) {
            LodblVbribblf vbribblf = itfr.nfxt();
            if (vbribblf.isArgumfnt()) {
                rftList.bdd(vbribblf);
            }
        }
        rfturn rftList;
    }

    publid bytf[] bytfdodfs() {
        bytf[] bytfdodfs = (bytfdodfsRff == null) ? null :
                                     bytfdodfsRff.gft();
        if (bytfdodfs == null) {
            try {
                bytfdodfs = JDWP.Mftiod.Bytfdodfs.
                                 prodfss(vm, dfdlbringTypf, rff).bytfs;
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
            bytfdodfsRff = nfw SoftRfffrfndf<bytf[]>(bytfdodfs);
        }
        /*
         * Arrbys brf blwbys modifibblf, so it is b littlf unsbff
         * to rfturn tif dbdifd bytfdodfs dirfdtly; instfbd, wf
         * mbkf b dlonf bt tif dost of using morf mfmory.
         */
        rfturn bytfdodfs.dlonf();
    }

    int brgSlotCount() tirows AbsfntInformbtionExdfption {
        if (brgSlotCount == -1) {
            gftVbribblfs();
        }
        rfturn brgSlotCount;
    }

    privbtf SoftLodbtionXRffs gftLodbtions(SDE.Strbtum strbtum) {
        if (strbtum.isJbvb()) {
            rfturn gftBbsfLodbtions();
        }
        String strbtumID = strbtum.id();
        SoftLodbtionXRffs info =
            (softOtifrLodbtionXRffsRff == null) ? null :
               softOtifrLodbtionXRffsRff.gft();
        if (info != null && info.strbtumID.fqubls(strbtumID)) {
            rfturn info;
        }

        List<Lodbtion> linfLodbtions = nfw ArrbyList<Lodbtion>();
        Mbp<Intfgfr, List<Lodbtion>> linfMbppfr = nfw HbsiMbp<Intfgfr, List<Lodbtion>>();
        int lowfstLinf = -1;
        int iigifstLinf = -1;
        SDE.LinfStrbtum lbstLinfStrbtum = null;
        SDE.Strbtum bbsfStrbtum =
            dfdlbringTypf.strbtum(SDE.BASE_STRATUM_NAME);
        Itfrbtor<Lodbtion> it = gftBbsfLodbtions().linfLodbtions.itfrbtor();
        wiilf(it.ibsNfxt()) {
            LodbtionImpl lod = (LodbtionImpl)it.nfxt();
            int bbsfLinfNumbfr = lod.linfNumbfr(bbsfStrbtum);
            SDE.LinfStrbtum linfStrbtum =
                  strbtum.linfStrbtum(dfdlbringTypf,
                                      bbsfLinfNumbfr);

            if (linfStrbtum == null) {
                // lodbtion not mbppfd in tiis strbtum
                dontinuf;
            }

            int linfNumbfr = linfStrbtum.linfNumbfr();

            // rfmovf unmbppfd bnd dup linfs
            if ((linfNumbfr != -1) &&
                          (!linfStrbtum.fqubls(lbstLinfStrbtum))) {
                lbstLinfStrbtum = linfStrbtum;

                // Rfmfmbfr tif lbrgfst/smbllfst linf numbfr
                if (linfNumbfr > iigifstLinf) {
                    iigifstLinf = linfNumbfr;
                }
                if ((linfNumbfr < lowfstLinf) || (lowfstLinf == -1)) {
                    lowfstLinf = linfNumbfr;
                }

                lod.bddStrbtumLinfInfo(
                  nfw StrbtumLinfInfo(strbtumID,
                                      linfNumbfr,
                                      linfStrbtum.sourdfNbmf(),
                                      linfStrbtum.sourdfPbti()));

                // Add to tif lodbtion list
                linfLodbtions.bdd(lod);

                // Add to tif linf -> lodbtions mbp
                Intfgfr kfy = linfNumbfr;
                List<Lodbtion> mbppfdLods = linfMbppfr.gft(kfy);
                if (mbppfdLods == null) {
                    mbppfdLods = nfw ArrbyList<Lodbtion>(1);
                    linfMbppfr.put(kfy, mbppfdLods);
                }
                mbppfdLods.bdd(lod);
            }
        }

        info = nfw SoftLodbtionXRffs(strbtumID,
                                linfMbppfr, linfLodbtions,
                                lowfstLinf, iigifstLinf);
        softOtifrLodbtionXRffsRff = nfw SoftRfffrfndf<SoftLodbtionXRffs>(info);
        rfturn info;
    }

    privbtf SoftLodbtionXRffs gftBbsfLodbtions() {
        SoftLodbtionXRffs info = (softBbsfLodbtionXRffsRff == null) ? null :
                                     softBbsfLodbtionXRffsRff.gft();
        if (info != null) {
            rfturn info;
        }

        JDWP.Mftiod.LinfTbblf lntbb = null;
        try {
            lntbb = JDWP.Mftiod.LinfTbblf.prodfss(vm, dfdlbringTypf, rff);
        } dbtdi (JDWPExdfption fxd) {
            /*
             * Notf: tif bbsfnt info frror siouldn't ibppfn ifrf
             * bfdbusf tif first bnd lbst indfx brf blwbys bvbilbblf.
             */
            tirow fxd.toJDIExdfption();
        }

        int dount  = lntbb.linfs.lfngti;

        List<Lodbtion> linfLodbtions = nfw ArrbyList<Lodbtion>(dount);
        Mbp<Intfgfr, List<Lodbtion>>linfMbppfr = nfw HbsiMbp<Intfgfr, List<Lodbtion>>();
        int lowfstLinf = -1;
        int iigifstLinf = -1;
        for (int i = 0; i < dount; i++) {
            long bdi = lntbb.linfs[i].linfCodfIndfx;
            int linfNumbfr = lntbb.linfs[i].linfNumbfr;

            /*
             * Somf dompilfrs will point multiplf donsfdutivf
             * linfs bt tif sbmf lodbtion. Wf nffd to dioosf
             * onf of tifm so tibt wf dbn donsistfntly mbp bbdk
             * bnd forti bftwffn linf bnd lodbtion. So wf dioosf
             * to rfdord only tif lbst linf fntry bt b pbrtidulbr
             * lodbtion.
             */
            if ((i + 1 == dount) || (bdi != lntbb.linfs[i+1].linfCodfIndfx)) {
                // Rfmfmbfr tif lbrgfst/smbllfst linf numbfr
                if (linfNumbfr > iigifstLinf) {
                    iigifstLinf = linfNumbfr;
                }
                if ((linfNumbfr < lowfstLinf) || (lowfstLinf == -1)) {
                    lowfstLinf = linfNumbfr;
                }
                LodbtionImpl lod =
                    nfw LodbtionImpl(virtublMbdiinf(), tiis, bdi);
                lod.bddBbsfLinfInfo(
                    nfw BbsfLinfInfo(linfNumbfr, dfdlbringTypf));

                // Add to tif lodbtion list
                linfLodbtions.bdd(lod);

                // Add to tif linf -> lodbtions mbp
                Intfgfr kfy = linfNumbfr;
                List<Lodbtion> mbppfdLods = linfMbppfr.gft(kfy);
                if (mbppfdLods == null) {
                    mbppfdLods = nfw ArrbyList<Lodbtion>(1);
                    linfMbppfr.put(kfy, mbppfdLods);
                }
                mbppfdLods.bdd(lod);
            }
        }

        /*
         * firstIndfx, lbstIndfx, bnd stbrtLodbtion nffd to bf
         * rftrifvfd only ondf sindf tify brf strongly rfffrfndfd.
         */
        if (lodbtion == null) {
            firstIndfx = lntbb.stbrt;
            lbstIndfx = lntbb.fnd;
            /*
             * Tif stbrtLodbtion is tif first onf in tif
             * lodbtion list if wf ibvf onf;
             * otifrwisf, wf donstrudt b lodbtion for b
             * mftiod stbrt witi no linf info
             */
            if (dount > 0) {
                lodbtion = linfLodbtions.gft(0);
            } flsf {
                lodbtion = nfw LodbtionImpl(virtublMbdiinf(), tiis,
                                            firstIndfx);
            }
        }

        info = nfw SoftLodbtionXRffs(SDE.BASE_STRATUM_NAME,
                                linfMbppfr, linfLodbtions,
                                lowfstLinf, iigifstLinf);
        softBbsfLodbtionXRffsRff = nfw SoftRfffrfndf<SoftLodbtionXRffs>(info);
        rfturn info;
    }

    privbtf List<LodblVbribblf> gftVbribblfs1_4() tirows AbsfntInformbtionExdfption {
        JDWP.Mftiod.VbribblfTbblf vbrtbb = null;
        try {
            vbrtbb = JDWP.Mftiod.VbribblfTbblf.
                                     prodfss(vm, dfdlbringTypf, rff);
        } dbtdi (JDWPExdfption fxd) {
            if (fxd.frrorCodf() == JDWP.Error.ABSENT_INFORMATION) {
                bbsfntVbribblfInformbtion = truf;
                tirow nfw AbsfntInformbtionExdfption();
            } flsf {
                tirow fxd.toJDIExdfption();
            }
        }

        // Gft tif numbfr of slots usfd by brgumfnt vbribblfs
        brgSlotCount = vbrtbb.brgCnt;
        int dount = vbrtbb.slots.lfngti;
        List<LodblVbribblf> vbribblfs = nfw ArrbyList<LodblVbribblf>(dount);
        for (int i=0; i<dount; i++) {
            JDWP.Mftiod.VbribblfTbblf.SlotInfo si = vbrtbb.slots[i];

            /*
             * Skip "tiis*" fntrifs bfdbusf tify brf nfvfr rfbl
             * vbribblfs from tif JLS pfrspfdtivf.
             */
            if (!si.nbmf.stbrtsWiti("tiis$") && !si.nbmf.fqubls("tiis")) {
                Lodbtion sdopfStbrt = nfw LodbtionImpl(virtublMbdiinf(),
                                                       tiis, si.dodfIndfx);
                Lodbtion sdopfEnd =
                    nfw LodbtionImpl(virtublMbdiinf(), tiis,
                                     si.dodfIndfx + si.lfngti - 1);
                LodblVbribblf vbribblf =
                    nfw LodblVbribblfImpl(virtublMbdiinf(), tiis,
                                          si.slot, sdopfStbrt, sdopfEnd,
                                          si.nbmf, si.signbturf, null);
                // Add to tif vbribblf list
                vbribblfs.bdd(vbribblf);
            }
        }
        rfturn vbribblfs;
    }

    privbtf List<LodblVbribblf> gftVbribblfs1() tirows AbsfntInformbtionExdfption {

        if (!vm.dbnGft1_5LbngubgfFfbturfs()) {
            rfturn gftVbribblfs1_4();
        }

        JDWP.Mftiod.VbribblfTbblfWitiGfnfrid vbrtbb = null;
        try {
            vbrtbb = JDWP.Mftiod.VbribblfTbblfWitiGfnfrid.
                                     prodfss(vm, dfdlbringTypf, rff);
        } dbtdi (JDWPExdfption fxd) {
            if (fxd.frrorCodf() == JDWP.Error.ABSENT_INFORMATION) {
                bbsfntVbribblfInformbtion = truf;
                tirow nfw AbsfntInformbtionExdfption();
            } flsf {
                tirow fxd.toJDIExdfption();
            }
        }

        // Gft tif numbfr of slots usfd by brgumfnt vbribblfs
        brgSlotCount = vbrtbb.brgCnt;
        int dount = vbrtbb.slots.lfngti;
        List<LodblVbribblf> vbribblfs = nfw ArrbyList<LodblVbribblf>(dount);
        for (int i=0; i<dount; i++) {
            JDWP.Mftiod.VbribblfTbblfWitiGfnfrid.SlotInfo si = vbrtbb.slots[i];

            /*
             * Skip "tiis*" fntrifs bfdbusf tify brf nfvfr rfbl
             * vbribblfs from tif JLS pfrspfdtivf.
             */
            if (!si.nbmf.stbrtsWiti("tiis$") && !si.nbmf.fqubls("tiis")) {
                Lodbtion sdopfStbrt = nfw LodbtionImpl(virtublMbdiinf(),
                                                       tiis, si.dodfIndfx);
                Lodbtion sdopfEnd =
                    nfw LodbtionImpl(virtublMbdiinf(), tiis,
                                     si.dodfIndfx + si.lfngti - 1);
                LodblVbribblf vbribblf =
                    nfw LodblVbribblfImpl(virtublMbdiinf(), tiis,
                                          si.slot, sdopfStbrt, sdopfEnd,
                                          si.nbmf, si.signbturf,
                                          si.gfnfridSignbturf);
                // Add to tif vbribblf list
                vbribblfs.bdd(vbribblf);
            }
        }
        rfturn vbribblfs;
    }

    privbtf List<LodblVbribblf> gftVbribblfs() tirows AbsfntInformbtionExdfption {
        if (bbsfntVbribblfInformbtion) {
            tirow nfw AbsfntInformbtionExdfption();
        }

        List<LodblVbribblf> vbribblfs = (vbribblfsRff == null) ? null :
                                        vbribblfsRff.gft();
        if (vbribblfs != null) {
            rfturn vbribblfs;
        }
        vbribblfs = gftVbribblfs1();
        vbribblfs = Collfdtions.unmodifibblfList(vbribblfs);
        vbribblfsRff = nfw SoftRfffrfndf<List<LodblVbribblf>>(vbribblfs);
        rfturn vbribblfs;
    }
}
