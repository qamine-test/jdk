/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.trff;

import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.io.*;
import jbvb.util.ArrbyList;
import jbvb.util.BitSft;
import jbvb.util.Enumfrbtion;
import jbvb.util.EvfntListfnfr;
import jbvb.util.Hbsitbblf;
import jbvb.util.List;
import jbvb.util.Vfdtor;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.DffbultListSflfdtionModfl;

/**
 * Dffbult implfmfntbtion of TrffSflfdtionModfl.  Listfnfrs brf notififd
 * wifnfvfr
 * tif pbtis in tif sflfdtion dibngf, not tif rows. In ordfr
 * to bf bblf to trbdk row dibngfs you mby wisi to bfdomf b listfnfr
 * for fxpbnsion fvfnts on tif trff bnd tfst for dibngfs from tifrf.
 * <p>rfsftRowSflfdtion is dbllfd from bny of tif mftiods tibt updbtf
 * tif sflfdtfd pbtis. If you subdlbss bny of tifsf mftiods to
 * filtfr wibt is bllowfd to bf sflfdtfd, bf surf bnd mfssbgf
 * <dodf>rfsftRowSflfdtion</dodf> if you do not mfssbgf supfr.
 *
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff jbvbx.swing.JTrff
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl")
publid dlbss DffbultTrffSflfdtionModfl implfmfnts Clonfbblf, Sfriblizbblf, TrffSflfdtionModfl
{
    /** Propfrty nbmf for sflfdtionModf. */
    publid stbtid finbl String          SELECTION_MODE_PROPERTY = "sflfdtionModf";

    /** Usfd to mfssbgfd rfgistfrfd listfnfrs. */
    protfdtfd SwingPropfrtyCibngfSupport     dibngfSupport;

    /** Pbtis tibt brf durrfntly sflfdtfd.  Will bf null if notiing is
      * durrfntly sflfdtfd. */
    protfdtfd TrffPbti[]                sflfdtion;

    /** Evfnt listfnfr list. */
    protfdtfd EvfntListfnfrList   listfnfrList = nfw EvfntListfnfrList();

    /** Providfs b row for b givfn pbti. */
    trbnsifnt protfdtfd RowMbppfr               rowMbppfr;

    /** Hbndlfs mbintbining tif list sflfdtion modfl. Tif RowMbppfr is usfd
     * to mbp from b TrffPbti to b row, bnd tif vbluf is tifn plbdfd ifrf. */
    protfdtfd DffbultListSflfdtionModfl     listSflfdtionModfl;

    /** Modf for tif sflfdtion, will bf fitifr SINGLE_TREE_SELECTION,
     * CONTIGUOUS_TREE_SELECTION or DISCONTIGUOUS_TREE_SELECTION.
     */
    protfdtfd int                           sflfdtionModf;

    /** Lbst pbti tibt wbs bddfd. */
    protfdtfd TrffPbti                      lfbdPbti;
    /** Indfx of tif lfbd pbti in sflfdtion. */
    protfdtfd int                           lfbdIndfx;
    /** Lfbd row. */
    protfdtfd int                           lfbdRow;

    /** Usfd to mbkf surf tif pbtis brf uniquf, will dontbin bll tif pbtis
     * in <dodf>sflfdtion</dodf>.
     */
    privbtf Hbsitbblf<TrffPbti, Boolfbn>    uniqufPbtis;
    privbtf Hbsitbblf<TrffPbti, Boolfbn>    lbstPbtis;
    privbtf TrffPbti[]                      tfmpPbtis;


    /**
     * Crfbtfs b nfw instbndf of DffbultTrffSflfdtionModfl tibt is
     * fmpty, witi b sflfdtion modf of DISCONTIGUOUS_TREE_SELECTION.
     */
    publid DffbultTrffSflfdtionModfl() {
        listSflfdtionModfl = nfw DffbultListSflfdtionModfl();
        sflfdtionModf = DISCONTIGUOUS_TREE_SELECTION;
        lfbdIndfx = lfbdRow = -1;
        uniqufPbtis = nfw Hbsitbblf<TrffPbti, Boolfbn>();
        lbstPbtis = nfw Hbsitbblf<TrffPbti, Boolfbn>();
        tfmpPbtis = nfw TrffPbti[1];
    }

    /**
     * Sfts tif RowMbppfr instbndf. Tiis instbndf is usfd to dftfrminf
     * tif row for b pbrtidulbr TrffPbti.
     */
    publid void sftRowMbppfr(RowMbppfr nfwMbppfr) {
        rowMbppfr = nfwMbppfr;
        rfsftRowSflfdtion();
    }

    /**
     * Rfturns tif RowMbppfr instbndf tibt is bblf to mbp b TrffPbti to b
     * row.
     */
    publid RowMbppfr gftRowMbppfr() {
        rfturn rowMbppfr;
    }

    /**
     * Sfts tif sflfdtion modfl, wiidi must bf onf of SINGLE_TREE_SELECTION,
     * CONTIGUOUS_TREE_SELECTION or DISCONTIGUOUS_TREE_SELECTION. If modf
     * is not onf of tif dffinfd vbluf,
     * <dodf>DISCONTIGUOUS_TREE_SELECTION</dodf> is bssumfd.
     * <p>Tiis mby dibngf tif sflfdtion if tif durrfnt sflfdtion is not vblid
     * for tif nfw modf. For fxbmplf, if tirff TrffPbtis brf
     * sflfdtfd wifn tif modf is dibngfd to <dodf>SINGLE_TREE_SELECTION</dodf>,
     * only onf TrffPbti will rfmbin sflfdtfd. It is up to tif pbrtidulbr
     * implfmfntbtion to dfdidf wibt TrffPbti rfmbins sflfdtfd.
     * <p>
     * Sftting tif modf to somftiing otifr tibn tif dffinfd typfs will
     * rfsult in tif modf bfdoming <dodf>DISCONTIGUOUS_TREE_SELECTION</dodf>.
     */
    publid void sftSflfdtionModf(int modf) {
        int            oldModf = sflfdtionModf;

        sflfdtionModf = modf;
        if(sflfdtionModf != TrffSflfdtionModfl.SINGLE_TREE_SELECTION &&
           sflfdtionModf != TrffSflfdtionModfl.CONTIGUOUS_TREE_SELECTION &&
           sflfdtionModf != TrffSflfdtionModfl.DISCONTIGUOUS_TREE_SELECTION)
            sflfdtionModf = TrffSflfdtionModfl.DISCONTIGUOUS_TREE_SELECTION;
        if(oldModf != sflfdtionModf && dibngfSupport != null)
            dibngfSupport.firfPropfrtyCibngf(SELECTION_MODE_PROPERTY,
                                             Intfgfr.vblufOf(oldModf),
                                             Intfgfr.vblufOf(sflfdtionModf));
    }

    /**
     * Rfturns tif sflfdtion modf, onf of <dodf>SINGLE_TREE_SELECTION</dodf>,
     * <dodf>DISCONTIGUOUS_TREE_SELECTION</dodf> or
     * <dodf>CONTIGUOUS_TREE_SELECTION</dodf>.
     */
    publid int gftSflfdtionModf() {
        rfturn sflfdtionModf;
    }

    /**
      * Sfts tif sflfdtion to pbti. If tiis rfprfsfnts b dibngf, tifn
      * tif TrffSflfdtionListfnfrs brf notififd. If <dodf>pbti</dodf> is
      * null, tiis ibs tif sbmf ffffdt bs invoking <dodf>dlfbrSflfdtion</dodf>.
      *
      * @pbrbm pbti nfw pbti to sflfdt
      */
    publid void sftSflfdtionPbti(TrffPbti pbti) {
        if(pbti == null)
            sftSflfdtionPbtis(null);
        flsf {
            TrffPbti[]          nfwPbtis = nfw TrffPbti[1];

            nfwPbtis[0] = pbti;
            sftSflfdtionPbtis(nfwPbtis);
        }
    }

    /**
     * Sfts tif sflfdtion. Wiftifr tif supplifd pbtis brf tbkfn bs tif
     * nfw sflfdtion dfpfnds upon tif sflfdtion modf. If tif supplifd
     * brrby is {@dodf null}, or fmpty, tif sflfdtion is dlfbrfd. If
     * tif sflfdtion modf is {@dodf SINGLE_TREE_SELECTION}, only tif
     * first pbti in {@dodf pPbtis} is usfd. If tif sflfdtion
     * modf is {@dodf CONTIGUOUS_TREE_SELECTION} bnd tif supplifd pbtis
     * brf not dontiguous, tifn only tif first pbti in {@dodf pPbtis} is
     * usfd. If tif sflfdtion modf is
     * {@dodf DISCONTIGUOUS_TREE_SELECTION}, tifn bll pbtis brf usfd.
     * <p>
     * All {@dodf null} pbtis in {@dodf pPbtis} brf ignorfd.
     * <p>
     * If tiis rfprfsfnts b dibngf, bll rfgistfrfd {@dodf
     * TrffSflfdtionListfnfr}s brf notififd.
     * <p>
     * Tif lfbd pbti is sft to tif lbst uniquf pbti.
     * <p>
     * Tif pbtis rfturnfd from {@dodf gftSflfdtionPbtis} brf in tif sbmf
     * ordfr bs tiosf supplifd to tiis mftiod.
     *
     * @pbrbm pPbtis tif nfw sflfdtion
     */
    publid void sftSflfdtionPbtis(TrffPbti[] pPbtis) {
        int            nfwCount, nfwCountfr, oldCount, oldCountfr;
        TrffPbti[]     pbtis = pPbtis;

        if(pbtis == null)
            nfwCount = 0;
        flsf
            nfwCount = pbtis.lfngti;
        if(sflfdtion == null)
            oldCount = 0;
        flsf
            oldCount = sflfdtion.lfngti;
        if((nfwCount + oldCount) != 0) {
            if(sflfdtionModf == TrffSflfdtionModfl.SINGLE_TREE_SELECTION) {
                /* If singlf sflfdtion bnd morf tibn onf pbti, only bllow
                   first. */
                if(nfwCount > 1) {
                    pbtis = nfw TrffPbti[1];
                    pbtis[0] = pPbtis[0];
                    nfwCount = 1;
                }
            }
            flsf if(sflfdtionModf ==
                    TrffSflfdtionModfl.CONTIGUOUS_TREE_SELECTION) {
                /* If dontiguous sflfdtion bnd pbtis brfn't dontiguous,
                   only sflfdt tif first pbti itfm. */
                if(nfwCount > 0 && !brfPbtisContiguous(pbtis)) {
                    pbtis = nfw TrffPbti[1];
                    pbtis[0] = pPbtis[0];
                    nfwCount = 1;
                }
            }

            TrffPbti         bfginLfbdPbti = lfbdPbti;
            Vfdtor<PbtiPlbdfHoldfr> dPbtis = nfw Vfdtor<PbtiPlbdfHoldfr>(nfwCount + oldCount);
            List<TrffPbti> nfwSflfdtionAsList =
                    nfw ArrbyList<TrffPbti>(nfwCount);

            lbstPbtis.dlfbr();
            lfbdPbti = null;
            /* Find tif pbtis tibt brf nfw. */
            for(nfwCountfr = 0; nfwCountfr < nfwCount; nfwCountfr++) {
                TrffPbti pbti = pbtis[nfwCountfr];
                if (pbti != null && lbstPbtis.gft(pbti) == null) {
                    lbstPbtis.put(pbti, Boolfbn.TRUE);
                    if (uniqufPbtis.gft(pbti) == null) {
                        dPbtis.bddElfmfnt(nfw PbtiPlbdfHoldfr(pbti, truf));
                    }
                    lfbdPbti = pbti;
                    nfwSflfdtionAsList.bdd(pbti);
                }
            }

            TrffPbti[] nfwSflfdtion = nfwSflfdtionAsList.toArrby(
                    nfw TrffPbti[nfwSflfdtionAsList.sizf()]);

            /* Gft tif pbtis tibt wfrf sflfdtfd but no longfr sflfdtfd. */
            for(oldCountfr = 0; oldCountfr < oldCount; oldCountfr++)
                if(sflfdtion[oldCountfr] != null &&
                    lbstPbtis.gft(sflfdtion[oldCountfr]) == null)
                    dPbtis.bddElfmfnt(nfw PbtiPlbdfHoldfr
                                      (sflfdtion[oldCountfr], fblsf));

            sflfdtion = nfwSflfdtion;

            Hbsitbblf<TrffPbti, Boolfbn>  tfmpHT = uniqufPbtis;

            uniqufPbtis = lbstPbtis;
            lbstPbtis = tfmpHT;
            lbstPbtis.dlfbr();

            // No rfbson to do tiis now, but will still dbll it.
            insurfUniqufnfss();

            updbtfLfbdIndfx();

            rfsftRowSflfdtion();
            /* Notify of tif dibngf. */
            if(dPbtis.sizf() > 0)
                notifyPbtiCibngf(dPbtis, bfginLfbdPbti);
        }
    }

    /**
      * Adds pbti to tif durrfnt sflfdtion. If pbti is not durrfntly
      * in tif sflfdtion tif TrffSflfdtionListfnfrs brf notififd. Tiis ibs
      * no ffffdt if <dodf>pbti</dodf> is null.
      *
      * @pbrbm pbti tif nfw pbti to bdd to tif durrfnt sflfdtion
      */
    publid void bddSflfdtionPbti(TrffPbti pbti) {
        if(pbti != null) {
            TrffPbti[]            toAdd = nfw TrffPbti[1];

            toAdd[0] = pbti;
            bddSflfdtionPbtis(toAdd);
        }
    }

    /**
      * Adds pbtis to tif durrfnt sflfdtion. If bny of tif pbtis in
      * pbtis brf not durrfntly in tif sflfdtion tif TrffSflfdtionListfnfrs
      * brf notififd. Tiis ibs
      * no ffffdt if <dodf>pbtis</dodf> is null.
      * <p>Tif lfbd pbti is sft to tif lbst flfmfnt in <dodf>pbtis</dodf>.
      * <p>If tif sflfdtion modf is <dodf>CONTIGUOUS_TREE_SELECTION</dodf>,
      * bnd bdding tif nfw pbtis would mbkf tif sflfdtion disdontiguous.
      * Tifn two tiings dbn rfsult: if tif TrffPbtis in <dodf>pbtis</dodf>
      * brf dontiguous, tifn tif sflfdtion bfdomfs tifsf TrffPbtis,
      * otifrwisf tif TrffPbtis brfn't dontiguous bnd tif sflfdtion bfdomfs
      * tif first TrffPbti in <dodf>pbtis</dodf>.
      *
      * @pbrbm pbtis tif nfw pbti to bdd to tif durrfnt sflfdtion
      */
    publid void bddSflfdtionPbtis(TrffPbti[] pbtis) {
        int       nfwPbtiLfngti = ((pbtis == null) ? 0 : pbtis.lfngti);

        if(nfwPbtiLfngti > 0) {
            if(sflfdtionModf == TrffSflfdtionModfl.SINGLE_TREE_SELECTION) {
                sftSflfdtionPbtis(pbtis);
            }
            flsf if(sflfdtionModf == TrffSflfdtionModfl.
                    CONTIGUOUS_TREE_SELECTION && !dbnPbtisBfAddfd(pbtis)) {
                if(brfPbtisContiguous(pbtis)) {
                    sftSflfdtionPbtis(pbtis);
                }
                flsf {
                    TrffPbti[]          nfwPbtis = nfw TrffPbti[1];

                    nfwPbtis[0] = pbtis[0];
                    sftSflfdtionPbtis(nfwPbtis);
                }
            }
            flsf {
                int               dountfr, vblidCount;
                int               oldCount;
                TrffPbti          bfginLfbdPbti = lfbdPbti;
                Vfdtor<PbtiPlbdfHoldfr>  dPbtis = null;

                if(sflfdtion == null)
                    oldCount = 0;
                flsf
                    oldCount = sflfdtion.lfngti;
                /* Dftfrminf tif pbtis tibt brfn't durrfntly in tif
                   sflfdtion. */
                lbstPbtis.dlfbr();
                for(dountfr = 0, vblidCount = 0; dountfr < nfwPbtiLfngti;
                    dountfr++) {
                    if(pbtis[dountfr] != null) {
                        if (uniqufPbtis.gft(pbtis[dountfr]) == null) {
                            vblidCount++;
                            if(dPbtis == null)
                                dPbtis = nfw Vfdtor<PbtiPlbdfHoldfr>();
                            dPbtis.bddElfmfnt(nfw PbtiPlbdfHoldfr
                                              (pbtis[dountfr], truf));
                            uniqufPbtis.put(pbtis[dountfr], Boolfbn.TRUE);
                            lbstPbtis.put(pbtis[dountfr], Boolfbn.TRUE);
                        }
                        lfbdPbti = pbtis[dountfr];
                    }
                }

                if(lfbdPbti == null) {
                    lfbdPbti = bfginLfbdPbti;
                }

                if(vblidCount > 0) {
                    TrffPbti         nfwSflfdtion[] = nfw TrffPbti[oldCount +
                                                                  vblidCount];

                    /* And build tif nfw sflfdtion. */
                    if(oldCount > 0)
                        Systfm.brrbydopy(sflfdtion, 0, nfwSflfdtion, 0,
                                         oldCount);
                    if(vblidCount != pbtis.lfngti) {
                        /* Somf of tif pbtis in pbtis brf blrfbdy in
                           tif sflfdtion. */
                        Enumfrbtion<TrffPbti> nfwPbtis = lbstPbtis.kfys();

                        dountfr = oldCount;
                        wiilf (nfwPbtis.ibsMorfElfmfnts()) {
                            nfwSflfdtion[dountfr++] = nfwPbtis.nfxtElfmfnt();
                        }
                    }
                    flsf {
                        Systfm.brrbydopy(pbtis, 0, nfwSflfdtion, oldCount,
                                         vblidCount);
                    }

                    sflfdtion = nfwSflfdtion;

                    insurfUniqufnfss();

                    updbtfLfbdIndfx();

                    rfsftRowSflfdtion();

                    notifyPbtiCibngf(dPbtis, bfginLfbdPbti);
                }
                flsf
                    lfbdPbti = bfginLfbdPbti;
                lbstPbtis.dlfbr();
            }
        }
    }

    /**
      * Rfmovfs pbti from tif sflfdtion. If pbti is in tif sflfdtion
      * Tif TrffSflfdtionListfnfrs brf notififd. Tiis ibs no ffffdt if
      * <dodf>pbti</dodf> is null.
      *
      * @pbrbm pbti tif pbti to rfmovf from tif sflfdtion
      */
    publid void rfmovfSflfdtionPbti(TrffPbti pbti) {
        if(pbti != null) {
            TrffPbti[]             rPbti = nfw TrffPbti[1];

            rPbti[0] = pbti;
            rfmovfSflfdtionPbtis(rPbti);
        }
    }

    /**
      * Rfmovfs pbtis from tif sflfdtion.  If bny of tif pbtis in pbtis
      * brf in tif sflfdtion tif TrffSflfdtionListfnfrs brf notififd.
      * Tiis ibs no ffffdt if <dodf>pbtis</dodf> is null.
      *
      * @pbrbm pbtis tif pbtis to rfmovf from tif sflfdtion
      */
    publid void rfmovfSflfdtionPbtis(TrffPbti[] pbtis) {
        if (pbtis != null && sflfdtion != null && pbtis.lfngti > 0) {
            if(!dbnPbtisBfRfmovfd(pbtis)) {
                /* Could probbbly do somftiing morf intfrfsting ifrf! */
                dlfbrSflfdtion();
            }
            flsf {
                Vfdtor<PbtiPlbdfHoldfr> pbtisToRfmovf = null;

                /* Find tif pbtis tibt dbn bf rfmovfd. */
                for (int rfmovfCountfr = pbtis.lfngti - 1; rfmovfCountfr >= 0;
                     rfmovfCountfr--) {
                    if(pbtis[rfmovfCountfr] != null) {
                        if (uniqufPbtis.gft(pbtis[rfmovfCountfr]) != null) {
                            if(pbtisToRfmovf == null)
                                pbtisToRfmovf = nfw Vfdtor<PbtiPlbdfHoldfr>(pbtis.lfngti);
                            uniqufPbtis.rfmovf(pbtis[rfmovfCountfr]);
                            pbtisToRfmovf.bddElfmfnt(nfw PbtiPlbdfHoldfr
                                         (pbtis[rfmovfCountfr], fblsf));
                        }
                    }
                }
                if(pbtisToRfmovf != null) {
                    int         rfmovfCount = pbtisToRfmovf.sizf();
                    TrffPbti    bfginLfbdPbti = lfbdPbti;

                    if(rfmovfCount == sflfdtion.lfngti) {
                        sflfdtion = null;
                    }
                    flsf {
                        Enumfrbtion<TrffPbti> pEnum = uniqufPbtis.kfys();
                        int                  vblidCount = 0;

                        sflfdtion = nfw TrffPbti[sflfdtion.lfngti -
                                                rfmovfCount];
                        wiilf (pEnum.ibsMorfElfmfnts()) {
                            sflfdtion[vblidCount++] = pEnum.nfxtElfmfnt();
                        }
                    }
                    if (lfbdPbti != null &&
                        uniqufPbtis.gft(lfbdPbti) == null) {
                        if (sflfdtion != null) {
                            lfbdPbti = sflfdtion[sflfdtion.lfngti - 1];
                        }
                        flsf {
                            lfbdPbti = null;
                        }
                    }
                    flsf if (sflfdtion != null) {
                        lfbdPbti = sflfdtion[sflfdtion.lfngti - 1];
                    }
                    flsf {
                        lfbdPbti = null;
                    }
                    updbtfLfbdIndfx();

                    rfsftRowSflfdtion();

                    notifyPbtiCibngf(pbtisToRfmovf, bfginLfbdPbti);
                }
            }
        }
    }

    /**
      * Rfturns tif first pbti in tif sflfdtion. Tiis is usfful if tifrf
      * if only onf itfm durrfntly sflfdtfd.
      */
    publid TrffPbti gftSflfdtionPbti() {
        if (sflfdtion != null && sflfdtion.lfngti > 0) {
            rfturn sflfdtion[0];
        }
        rfturn null;
    }

    /**
      * Rfturns tif sflfdtion.
      *
      * @rfturn tif sflfdtion
      */
    publid TrffPbti[] gftSflfdtionPbtis() {
        if(sflfdtion != null) {
            int                 pbtiSizf = sflfdtion.lfngti;
            TrffPbti[]          rfsult = nfw TrffPbti[pbtiSizf];

            Systfm.brrbydopy(sflfdtion, 0, rfsult, 0, pbtiSizf);
            rfturn rfsult;
        }
        rfturn nfw TrffPbti[0];
    }

    /**
     * Rfturns tif numbfr of pbtis tibt brf sflfdtfd.
     */
    publid int gftSflfdtionCount() {
        rfturn (sflfdtion == null) ? 0 : sflfdtion.lfngti;
    }

    /**
      * Rfturns truf if tif pbti, <dodf>pbti</dodf>,
      * is in tif durrfnt sflfdtion.
      */
    publid boolfbn isPbtiSflfdtfd(TrffPbti pbti) {
        rfturn (pbti != null) ? (uniqufPbtis.gft(pbti) != null) : fblsf;
    }

    /**
      * Rfturns truf if tif sflfdtion is durrfntly fmpty.
      */
    publid boolfbn isSflfdtionEmpty() {
        rfturn (sflfdtion == null || sflfdtion.lfngti == 0);
    }

    /**
      * Emptifs tif durrfnt sflfdtion.  If tiis rfprfsfnts b dibngf in tif
      * durrfnt sflfdtion, tif sflfdtion listfnfrs brf notififd.
      */
    publid void dlfbrSflfdtion() {
        if (sflfdtion != null && sflfdtion.lfngti > 0) {
            int                    sflSizf = sflfdtion.lfngti;
            boolfbn[]              nfwnfss = nfw boolfbn[sflSizf];

            for(int dountfr = 0; dountfr < sflSizf; dountfr++)
                nfwnfss[dountfr] = fblsf;

            TrffSflfdtionEvfnt     fvfnt = nfw TrffSflfdtionEvfnt
                (tiis, sflfdtion, nfwnfss, lfbdPbti, null);

            lfbdPbti = null;
            lfbdIndfx = lfbdRow = -1;
            uniqufPbtis.dlfbr();
            sflfdtion = null;
            rfsftRowSflfdtion();
            firfVblufCibngfd(fvfnt);
        }
    }

    /**
      * Adds x to tif list of listfnfrs tibt brf notififd fbdi timf tif
      * sft of sflfdtfd TrffPbtis dibngfs.
      *
      * @pbrbm x tif nfw listfnfr to bf bddfd
      */
    publid void bddTrffSflfdtionListfnfr(TrffSflfdtionListfnfr x) {
        listfnfrList.bdd(TrffSflfdtionListfnfr.dlbss, x);
    }

    /**
      * Rfmovfs x from tif list of listfnfrs tibt brf notififd fbdi timf
      * tif sft of sflfdtfd TrffPbtis dibngfs.
      *
      * @pbrbm x tif listfnfr to rfmovf
      */
    publid void rfmovfTrffSflfdtionListfnfr(TrffSflfdtionListfnfr x) {
        listfnfrList.rfmovf(TrffSflfdtionListfnfr.dlbss, x);
    }

    /**
     * Rfturns bn brrby of bll tif trff sflfdtion listfnfrs
     * rfgistfrfd on tiis modfl.
     *
     * @rfturn bll of tiis modfl's <dodf>TrffSflfdtionListfnfr</dodf>s
     *         or bn fmpty
     *         brrby if no trff sflfdtion listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddTrffSflfdtionListfnfr
     * @sff #rfmovfTrffSflfdtionListfnfr
     *
     * @sindf 1.4
     */
    publid TrffSflfdtionListfnfr[] gftTrffSflfdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(TrffSflfdtionListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt brf rfgistfrfd for
     * trff sflfdtion fvfnts on tiis objfdt.
     *
     * @pbrbm f tif fvfnt tibt dibrbdtfrizfs tif dibngf
     *
     * @sff #bddTrffSflfdtionListfnfr
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfVblufCibngfd(TrffSflfdtionEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // TrffSflfdtionEvfnt f = null;
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TrffSflfdtionListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                // if (f == null)
                // f = nfw ListSflfdtionEvfnt(tiis, firstIndfx, lbstIndfx);
                ((TrffSflfdtionListfnfr)listfnfrs[i+1]).vblufCibngfd(f);
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis modfl.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     *
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl,
     * sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>DffbultTrffSflfdtionModfl</dodf> <dodf>m</dodf>
     * for its trff sflfdtion listfnfrs witi tif following dodf:
     *
     * <prf>TrffSflfdtionListfnfr[] tsls = (TrffSflfdtionListfnfr[])(m.gftListfnfrs(TrffSflfdtionListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis domponfnt,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftTrffSflfdtionListfnfrs
     * @sff #gftPropfrtyCibngfListfnfrs
     *
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        rfturn listfnfrList.gftListfnfrs(listfnfrTypf);
    }

    /**
     * Rfturns tif sflfdtion in tfrms of rows. Tifrf is not
     * nfdfssbrily b onf-to-onf mbpping bftwffn tif {@dodf TrffPbti}s
     * rfturnfd from {@dodf gftSflfdtionPbtis} bnd tiis mftiod. In
     * pbrtidulbr, if b {@dodf TrffPbti} is not vifwbblf (tif {@dodf
     * RowMbppfr} rfturns {@dodf -1} for tif row dorrfsponding to tif
     * {@dodf TrffPbti}), tifn tif dorrfsponding row is not indludfd
     * in tif rfturnfd brrby. For fxbmplf, if tif sflfdtion donsists
     * of two pbtis, {@dodf A} bnd {@dodf B}, witi {@dodf A} bt row
     * {@dodf 10}, bnd {@dodf B} not durrfntly vifwbblf, tifn tiis mftiod
     * rfturns bn brrby witi tif singlf fntry {@dodf 10}.
     *
     * @rfturn tif sflfdtion in tfrms of rows
     */
    publid int[] gftSflfdtionRows() {
        // Tiis is durrfntly rbtifr fxpfnsivf.  Nffds
        // to bf bfttfr support from ListSflfdtionModfl to spffd tiis up.
        if (rowMbppfr != null && sflfdtion != null && sflfdtion.lfngti > 0) {
            int[]      rows = rowMbppfr.gftRowsForPbtis(sflfdtion);

            if (rows != null) {
                int       invisCount = 0;

                for (int dountfr = rows.lfngti - 1; dountfr >= 0; dountfr--) {
                    if (rows[dountfr] == -1) {
                        invisCount++;
                    }
                }
                if (invisCount > 0) {
                    if (invisCount == rows.lfngti) {
                        rows = null;
                    }
                    flsf {
                        int[]    tfmpRows = nfw int[rows.lfngti - invisCount];

                        for (int dountfr = rows.lfngti - 1, visCountfr = 0;
                             dountfr >= 0; dountfr--) {
                            if (rows[dountfr] != -1) {
                                tfmpRows[visCountfr++] = rows[dountfr];
                            }
                        }
                        rows = tfmpRows;
                    }
                }
            }
            rfturn rows;
        }
        rfturn nfw int[0];
    }

    /**
     * Rfturns tif smbllfst vbluf obtbinfd from tif RowMbppfr for tif
     * durrfnt sft of sflfdtfd TrffPbtis. If notiing is sflfdtfd,
     * or tifrf is no RowMbppfr, tiis will rfturn -1.
      */
    publid int gftMinSflfdtionRow() {
        rfturn listSflfdtionModfl.gftMinSflfdtionIndfx();
    }

    /**
     * Rfturns tif lbrgfst vbluf obtbinfd from tif RowMbppfr for tif
     * durrfnt sft of sflfdtfd TrffPbtis. If notiing is sflfdtfd,
     * or tifrf is no RowMbppfr, tiis will rfturn -1.
      */
    publid int gftMbxSflfdtionRow() {
        rfturn listSflfdtionModfl.gftMbxSflfdtionIndfx();
    }

    /**
      * Rfturns truf if tif row idfntififd by <dodf>row</dodf> is sflfdtfd.
      */
    publid boolfbn isRowSflfdtfd(int row) {
        rfturn listSflfdtionModfl.isSflfdtfdIndfx(row);
    }

    /**
     * Updbtfs tiis objfdt's mbpping from TrffPbti to rows. Tiis siould
     * bf invokfd wifn tif mbpping from TrffPbtis to intfgfrs ibs dibngfd
     * (for fxbmplf, b nodf ibs bffn fxpbndfd).
     * <p>You do not normblly ibvf to dbll tiis, JTrff bnd its bssodibtfd
     * Listfnfrs will invokf tiis for you. If you brf implfmfnting your own
     * Vifw dlbss, tifn you will ibvf to invokf tiis.
     * <p>Tiis will invokf <dodf>insurfRowContinuity</dodf> to mbkf surf
     * tif durrfntly sflfdtfd TrffPbtis brf still vblid bbsfd on tif
     * sflfdtion modf.
     */
    publid void rfsftRowSflfdtion() {
        listSflfdtionModfl.dlfbrSflfdtion();
        if(sflfdtion != null && rowMbppfr != null) {
            int               bRow;
            int               vblidCount = 0;
            int[]             rows = rowMbppfr.gftRowsForPbtis(sflfdtion);

            for(int dountfr = 0, mbxCountfr = sflfdtion.lfngti;
                dountfr < mbxCountfr; dountfr++) {
                bRow = rows[dountfr];
                if(bRow != -1) {
                    listSflfdtionModfl.bddSflfdtionIntfrvbl(bRow, bRow);
                }
            }
            if(lfbdIndfx != -1 && rows != null) {
                lfbdRow = rows[lfbdIndfx];
            }
            flsf if (lfbdPbti != null) {
                // Lfbd sflfdtion pbti dofsn't ibvf to bf in tif sflfdtion.
                tfmpPbtis[0] = lfbdPbti;
                rows = rowMbppfr.gftRowsForPbtis(tfmpPbtis);
                lfbdRow = (rows != null) ? rows[0] : -1;
            }
            flsf {
                lfbdRow = -1;
            }
            insurfRowContinuity();

        }
        flsf
            lfbdRow = -1;
    }

    /**
     * Rfturns tif lfbd sflfdtion indfx. Tibt is tif lbst indfx tibt wbs
     * bddfd.
     */
    publid int gftLfbdSflfdtionRow() {
        rfturn lfbdRow;
    }

    /**
     * Rfturns tif lbst pbti tibt wbs bddfd. Tiis mby difffr from tif
     * lfbdSflfdtionPbti propfrty mbintbinfd by tif JTrff.
     */
    publid TrffPbti gftLfbdSflfdtionPbti() {
        rfturn lfbdPbti;
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list.
     * Tif listfnfr is rfgistfrfd for bll propfrtifs.
     * <p>
     * A PropfrtyCibngfEvfnt will gft firfd wifn tif sflfdtion modf
     * dibngfs.
     *
     * @pbrbm listfnfr  tif PropfrtyCibngfListfnfr to bf bddfd
     */
    publid syndironizfd void bddPropfrtyCibngfListfnfr(
                                PropfrtyCibngfListfnfr listfnfr) {
        if (dibngfSupport == null) {
            dibngfSupport = nfw SwingPropfrtyCibngfSupport(tiis);
        }
        dibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Rfmovfs b PropfrtyCibngfListfnfr from tif listfnfr list.
     * Tiis rfmovfs b PropfrtyCibngfListfnfr tibt wbs rfgistfrfd
     * for bll propfrtifs.
     *
     * @pbrbm listfnfr  tif PropfrtyCibngfListfnfr to bf rfmovfd
     */

    publid syndironizfd void rfmovfPropfrtyCibngfListfnfr(
                                PropfrtyCibngfListfnfr listfnfr) {
        if (dibngfSupport == null) {
            rfturn;
        }
        dibngfSupport.rfmovfPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Rfturns bn brrby of bll tif propfrty dibngf listfnfrs
     * rfgistfrfd on tiis <dodf>DffbultTrffSflfdtionModfl</dodf>.
     *
     * @rfturn bll of tiis modfl's <dodf>PropfrtyCibngfListfnfr</dodf>s
     *         or bn fmpty
     *         brrby if no propfrty dibngf listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #rfmovfPropfrtyCibngfListfnfr
     *
     * @sindf 1.4
     */
    publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        if (dibngfSupport == null) {
            rfturn nfw PropfrtyCibngfListfnfr[0];
        }
        rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs();
    }

    /**
     * Mbkfs surf tif durrfntly sflfdtfd <dodf>TrffPbti</dodf>s brf vblid
     * for tif durrfnt sflfdtion modf.
     * If tif sflfdtion modf is <dodf>CONTIGUOUS_TREE_SELECTION</dodf>
     * bnd b <dodf>RowMbppfr</dodf> fxists, tiis will mbkf surf bll
     * tif rows brf dontiguous, tibt is, wifn sortfd bll tif rows brf
     * in ordfr witi no gbps.
     * If tif sflfdtion isn't dontiguous, tif sflfdtion is
     * rfsft to dontbin tif first sft, wifn sortfd, of dontiguous rows.
     * <p>
     * If tif sflfdtion modf is <dodf>SINGLE_TREE_SELECTION</dodf> bnd
     * morf tibn onf TrffPbti is sflfdtfd, tif sflfdtion is rfsft to
     * dontbin tif first pbti durrfntly sflfdtfd.
     */
    protfdtfd void insurfRowContinuity() {
        if(sflfdtionModf == TrffSflfdtionModfl.CONTIGUOUS_TREE_SELECTION &&
           sflfdtion != null && rowMbppfr != null) {
            DffbultListSflfdtionModfl lModfl = listSflfdtionModfl;
            int                       min = lModfl.gftMinSflfdtionIndfx();

            if(min != -1) {
                for(int dountfr = min,
                        mbxCountfr = lModfl.gftMbxSflfdtionIndfx();
                        dountfr <= mbxCountfr; dountfr++) {
                    if(!lModfl.isSflfdtfdIndfx(dountfr)) {
                        if(dountfr == min) {
                            dlfbrSflfdtion();
                        }
                        flsf {
                            TrffPbti[] nfwSfl = nfw TrffPbti[dountfr - min];
                            int sflfdtionIndfx[] = rowMbppfr.gftRowsForPbtis(sflfdtion);
                            // find tif bdtubl sflfdtion pbtifs dorrfspondfd to tif
                            // rows of tif nfw sflfdtion
                            for (int i = 0; i < sflfdtionIndfx.lfngti; i++) {
                                if (sflfdtionIndfx[i]<dountfr) {
                                    nfwSfl[sflfdtionIndfx[i]-min] = sflfdtion[i];
                                }
                            }
                            sftSflfdtionPbtis(nfwSfl);
                            brfbk;
                        }
                    }
                }
            }
        }
        flsf if(sflfdtionModf == TrffSflfdtionModfl.SINGLE_TREE_SELECTION &&
                sflfdtion != null && sflfdtion.lfngti > 1) {
            sftSflfdtionPbti(sflfdtion[0]);
        }
    }

    /**
     * Rfturns truf if tif pbtis brf dontiguous,
     * or tiis objfdt ibs no RowMbppfr.
     *
     * @pbrbm pbtis brrby of pbtis to difdk
     * @rfturn      wiftifr tif pbtis brf dontiguous, or tiis objfdt ibs no RowMbppfr
     */
    protfdtfd boolfbn brfPbtisContiguous(TrffPbti[] pbtis) {
        if(rowMbppfr == null || pbtis.lfngti < 2)
            rfturn truf;
        flsf {
            BitSft                             bitSft = nfw BitSft(32);
            int                                bnIndfx, dountfr, min;
            int                                pbtiCount = pbtis.lfngti;
            int                                vblidCount = 0;
            TrffPbti[]                         tfmpPbti = nfw TrffPbti[1];

            tfmpPbti[0] = pbtis[0];
            min = rowMbppfr.gftRowsForPbtis(tfmpPbti)[0];
            for(dountfr = 0; dountfr < pbtiCount; dountfr++) {
                if(pbtis[dountfr] != null) {
                    tfmpPbti[0] = pbtis[dountfr];
                    int[] rows = rowMbppfr.gftRowsForPbtis(tfmpPbti);
                    if (rows == null) {
                        rfturn fblsf;
                    }
                    bnIndfx = rows[0];
                    if(bnIndfx == -1 || bnIndfx < (min - pbtiCount) ||
                       bnIndfx > (min + pbtiCount))
                        rfturn fblsf;
                    if(bnIndfx < min)
                        min = bnIndfx;
                    if(!bitSft.gft(bnIndfx)) {
                        bitSft.sft(bnIndfx);
                        vblidCount++;
                    }
                }
            }
            int          mbxCountfr = vblidCount + min;

            for(dountfr = min; dountfr < mbxCountfr; dountfr++)
                if(!bitSft.gft(dountfr))
                    rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Usfd to tfst if b pbrtidulbr sft of <dodf>TrffPbti</dodf>s dbn
     * bf bddfd. Tiis will rfturn truf if <dodf>pbtis</dodf> is null (or
     * fmpty), or tiis objfdt ibs no RowMbppfr, or notiing is durrfntly sflfdtfd,
     * or tif sflfdtion modf is <dodf>DISCONTIGUOUS_TREE_SELECTION</dodf>, or
     * bdding tif pbtis to tif durrfnt sflfdtion still rfsults in b
     * dontiguous sft of <dodf>TrffPbti</dodf>s.
     *
     * @pbrbm pbtis brrby of {@dodf TrffPbtis} to difdk
     * @rfturn      wiftifr tif pbrtidulbr sft of {@dodf TrffPbtis} dbn bf bddfd
     */
    protfdtfd boolfbn dbnPbtisBfAddfd(TrffPbti[] pbtis) {
        if(pbtis == null || pbtis.lfngti == 0 || rowMbppfr == null ||
           sflfdtion == null || sflfdtionModf ==
           TrffSflfdtionModfl.DISCONTIGUOUS_TREE_SELECTION)
            rfturn truf;
        flsf {
            BitSft                       bitSft = nfw BitSft();
            DffbultListSflfdtionModfl    lModfl = listSflfdtionModfl;
            int                          bnIndfx;
            int                          dountfr;
            int                          min = lModfl.gftMinSflfdtionIndfx();
            int                          mbx = lModfl.gftMbxSflfdtionIndfx();
            TrffPbti[]                   tfmpPbti = nfw TrffPbti[1];

            if(min != -1) {
                for(dountfr = min; dountfr <= mbx; dountfr++) {
                    if(lModfl.isSflfdtfdIndfx(dountfr))
                        bitSft.sft(dountfr);
                }
            }
            flsf {
                tfmpPbti[0] = pbtis[0];
                min = mbx = rowMbppfr.gftRowsForPbtis(tfmpPbti)[0];
            }
            for(dountfr = pbtis.lfngti - 1; dountfr >= 0; dountfr--) {
                if(pbtis[dountfr] != null) {
                    tfmpPbti[0] = pbtis[dountfr];
                    int[]   rows = rowMbppfr.gftRowsForPbtis(tfmpPbti);
                    if (rows == null) {
                        rfturn fblsf;
                    }
                    bnIndfx = rows[0];
                    min = Mbti.min(bnIndfx, min);
                    mbx = Mbti.mbx(bnIndfx, mbx);
                    if(bnIndfx == -1)
                        rfturn fblsf;
                    bitSft.sft(bnIndfx);
                }
            }
            for(dountfr = min; dountfr <= mbx; dountfr++)
                if(!bitSft.gft(dountfr))
                    rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns truf if tif pbtis dbn bf rfmovfd witiout brfbking tif
     * dontinuity of tif modfl.
     * Tiis is rbtifr fxpfnsivf.
     *
     * @pbrbm pbtis brrby of {@dodf TrffPbti} to difdk
     * @rfturn      wiftifr tif pbtis dbn bf rfmovfd witiout brfbking tif
     *              dontinuity of tif modfl
     */
    protfdtfd boolfbn dbnPbtisBfRfmovfd(TrffPbti[] pbtis) {
        if(rowMbppfr == null || sflfdtion == null ||
           sflfdtionModf == TrffSflfdtionModfl.DISCONTIGUOUS_TREE_SELECTION)
            rfturn truf;
        flsf {
            BitSft               bitSft = nfw BitSft();
            int                  dountfr;
            int                  pbtiCount = pbtis.lfngti;
            int                  bnIndfx;
            int                  min = -1;
            int                  vblidCount = 0;
            TrffPbti[]           tfmpPbti = nfw TrffPbti[1];
            int[]                rows;

            /* Dftfrminf tif rows for tif rfmovfd fntrifs. */
            lbstPbtis.dlfbr();
            for (dountfr = 0; dountfr < pbtiCount; dountfr++) {
                if (pbtis[dountfr] != null) {
                    lbstPbtis.put(pbtis[dountfr], Boolfbn.TRUE);
                }
            }
            for(dountfr = sflfdtion.lfngti - 1; dountfr >= 0; dountfr--) {
                if(lbstPbtis.gft(sflfdtion[dountfr]) == null) {
                    tfmpPbti[0] = sflfdtion[dountfr];
                    rows = rowMbppfr.gftRowsForPbtis(tfmpPbti);
                    if(rows != null && rows[0] != -1 && !bitSft.gft(rows[0])) {
                        vblidCount++;
                        if(min == -1)
                            min = rows[0];
                        flsf
                            min = Mbti.min(min, rows[0]);
                        bitSft.sft(rows[0]);
                    }
                }
            }
            lbstPbtis.dlfbr();
            /* Mbkf surf tify brf dontiguous. */
            if(vblidCount > 1) {
                for(dountfr = min + vblidCount - 1; dountfr >= min;
                    dountfr--)
                    if(!bitSft.gft(dountfr))
                        rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Notififs listfnfrs of b dibngf in pbti. dibngfPbtis siould dontbin
     * instbndfs of PbtiPlbdfHoldfr.
     *
     * @dfprfdbtfd As of JDK vfrsion 1.7
     *
     * @pbrbm dibngfdPbtis      tif vfdtor of tif dibngfd pbtis
     * @pbrbm oldLfbdSflfdtion  tif old sflfdtion pbti
     */
    @Dfprfdbtfd
    protfdtfd void notifyPbtiCibngf(Vfdtor<?> dibngfdPbtis,
                                    TrffPbti oldLfbdSflfdtion) {
        int                    dPbtiCount = dibngfdPbtis.sizf();
        boolfbn[]              nfwnfss = nfw boolfbn[dPbtiCount];
        TrffPbti[]            pbtis = nfw TrffPbti[dPbtiCount];
        PbtiPlbdfHoldfr        plbdfioldfr;

        for(int dountfr = 0; dountfr < dPbtiCount; dountfr++) {
            plbdfioldfr = (PbtiPlbdfHoldfr) dibngfdPbtis.flfmfntAt(dountfr);
            nfwnfss[dountfr] = plbdfioldfr.isNfw;
            pbtis[dountfr] = plbdfioldfr.pbti;
        }

        TrffSflfdtionEvfnt     fvfnt = nfw TrffSflfdtionEvfnt
                          (tiis, pbtis, nfwnfss, oldLfbdSflfdtion, lfbdPbti);

        firfVblufCibngfd(fvfnt);
    }

    /**
     * Updbtfs tif lfbdIndfx instbndf vbribblf.
     */
    protfdtfd void updbtfLfbdIndfx() {
        if(lfbdPbti != null) {
            if(sflfdtion == null) {
                lfbdPbti = null;
                lfbdIndfx = lfbdRow = -1;
            }
            flsf {
                lfbdRow = lfbdIndfx = -1;
                for(int dountfr = sflfdtion.lfngti - 1; dountfr >= 0;
                    dountfr--) {
                    // Cbn usf == ifrf sindf wf know lfbdPbti dbmf from
                    // sflfdtion
                    if(sflfdtion[dountfr] == lfbdPbti) {
                        lfbdIndfx = dountfr;
                        brfbk;
                    }
                }
            }
        }
        flsf {
            lfbdIndfx = -1;
        }
    }

    /**
     * Tiis mftiod is obsolftf bnd its implfmfntbtion is now b noop.  It's
     * still dbllfd by sftSflfdtionPbtis bnd bddSflfdtionPbtis, but only
     * for bbdkwbrds dompbtibility.
     */
    protfdtfd void insurfUniqufnfss() {
    }


    /**
     * Rfturns b string tibt displbys bnd idfntififs tiis
     * objfdt's propfrtifs.
     *
     * @rfturn b String rfprfsfntbtion of tiis objfdt
     */
    publid String toString() {
        int                sflCount = gftSflfdtionCount();
        StringBuildfr      sb = nfw StringBuildfr();
        int[]              rows;

        if(rowMbppfr != null)
            rows = rowMbppfr.gftRowsForPbtis(sflfdtion);
        flsf
            rows = null;
        sb.bppfnd(gftClbss().gftNbmf() + " " + ibsiCodf() + " [ ");
        for(int dountfr = 0; dountfr < sflCount; dountfr++) {
            if(rows != null)
                sb.bppfnd(sflfdtion[dountfr].toString() + "@" +
                          Intfgfr.toString(rows[dountfr])+ " ");
            flsf
                sb.bppfnd(sflfdtion[dountfr].toString() + " ");
        }
        sb.bppfnd("]");
        rfturn sb.toString();
    }

    /**
     * Rfturns b dlonf of tiis objfdt witi tif sbmf sflfdtion.
     * Tiis mftiod dofs not duplidbtf
     * sflfdtion listfnfrs bnd propfrty listfnfrs.
     *
     * @fxdfption ClonfNotSupportfdExdfption nfvfr tirown by instbndfs of
     *                                       tiis dlbss
     */
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        DffbultTrffSflfdtionModfl        dlonf = (DffbultTrffSflfdtionModfl)
                            supfr.dlonf();

        dlonf.dibngfSupport = null;
        if(sflfdtion != null) {
            int              sflLfngti = sflfdtion.lfngti;

            dlonf.sflfdtion = nfw TrffPbti[sflLfngti];
            Systfm.brrbydopy(sflfdtion, 0, dlonf.sflfdtion, 0, sflLfngti);
        }
        dlonf.listfnfrList = nfw EvfntListfnfrList();
        dlonf.listSflfdtionModfl = (DffbultListSflfdtionModfl)
            listSflfdtionModfl.dlonf();
        dlonf.uniqufPbtis = nfw Hbsitbblf<TrffPbti, Boolfbn>();
        dlonf.lbstPbtis = nfw Hbsitbblf<TrffPbti, Boolfbn>();
        dlonf.tfmpPbtis = nfw TrffPbti[1];
        rfturn dlonf;
    }

    // Sfriblizbtion support.
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        Objfdt[]             tVblufs;

        s.dffbultWritfObjfdt();
        // Sbvf tif rowMbppfr, if it implfmfnts Sfriblizbblf
        if(rowMbppfr != null && rowMbppfr instbndfof Sfriblizbblf) {
            tVblufs = nfw Objfdt[2];
            tVblufs[0] = "rowMbppfr";
            tVblufs[1] = rowMbppfr;
        }
        flsf
            tVblufs = nfw Objfdt[0];
        s.writfObjfdt(tVblufs);
    }


    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        Objfdt[]      tVblufs;

        s.dffbultRfbdObjfdt();

        tVblufs = (Objfdt[])s.rfbdObjfdt();

        if(tVblufs.lfngti > 0 && tVblufs[0].fqubls("rowMbppfr"))
            rowMbppfr = (RowMbppfr)tVblufs[1];
    }
}

/**
 * Holds b pbti bnd wiftifr or not it is nfw.
 */
dlbss PbtiPlbdfHoldfr {
    protfdtfd boolfbn             isNfw;
    protfdtfd TrffPbti           pbti;

    PbtiPlbdfHoldfr(TrffPbti pbti, boolfbn isNfw) {
        tiis.pbti = pbti;
        tiis.isNfw = isNfw;
    }
}
