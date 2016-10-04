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

pbdkbgf jbvb.bfbns.bfbndontfxt;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;

import jbvb.bfbns.Bfbns;
import jbvb.bfbns.ApplftInitiblizfr;

import jbvb.bfbns.DfsignModf;

import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfSupport;

import jbvb.bfbns.VftobblfCibngfListfnfr;
import jbvb.bfbns.VftobblfCibngfSupport;
import jbvb.bfbns.PropfrtyVftoExdfption;

import jbvb.bfbns.Visibility;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;

import jbvb.nft.URL;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;


/**
 * Tiis iflpfr dlbss providfs b utility implfmfntbtion of tif
 * jbvb.bfbns.bfbndontfxt.BfbnContfxt intfrfbdf.
 * <p>
 * Sindf tiis dlbss dirfdtly implfmfnts tif BfbnContfxt intfrfbdf, tif dlbss
 * dbn, bnd is intfndfd to bf usfd fitifr by subdlbssing tiis implfmfntbtion,
 * or vib bd-iod dflfgbtion of bn instbndf of tiis dlbss from bnotifr.
 * </p>
 *
 * @butior Lburfndf P. G. Cbblf
 * @sindf 1.2
 */
publid dlbss      BfbnContfxtSupport fxtfnds BfbnContfxtCiildSupport
       implfmfnts BfbnContfxt,
                  Sfriblizbblf,
                  PropfrtyCibngfListfnfr,
                  VftobblfCibngfListfnfr {

    // Fix for bug 4282900 to pbss JCK rfgrfssion tfst
    stbtid finbl long sfriblVfrsionUID = -4879613978649577204L;

    /**
     *
     * Construdt b BfbnContfxtSupport instbndf
     *
     *
     * @pbrbm pffr      Tif pffr <tt>BfbnContfxt</tt> wf brf
     *                  supplying bn implfmfntbtion for,
     *                  or <tt>null</tt>
     *                  if tiis objfdt is its own pffr
     * @pbrbm ldlf      Tif durrfnt Lodblf for tiis BfbnContfxt. If
     *                  <tt>ldlf</tt> is <tt>null</tt>, tif dffbult lodblf
     *                  is bssignfd to tif <tt>BfbnContfxt</tt> instbndf.
     * @pbrbm dTimf     Tif initibl stbtf,
     *                  <tt>truf</tt> if in dfsign modf,
     *                  <tt>fblsf</tt> if runtimf.
     * @pbrbm visiblf   Tif initibl visibility.
     * @sff jbvb.util.Lodblf#gftDffbult()
     * @sff jbvb.util.Lodblf#sftDffbult(jbvb.util.Lodblf)
     */
    publid BfbnContfxtSupport(BfbnContfxt pffr, Lodblf ldlf, boolfbn dTimf, boolfbn visiblf) {
        supfr(pffr);

        lodblf          = ldlf != null ? ldlf : Lodblf.gftDffbult();
        dfsignTimf      = dTimf;
        okToUsfGui      = visiblf;

        initiblizf();
    }

    /**
     * Crfbtf bn instbndf using tif spfdififd Lodblf bnd dfsign modf.
     *
     * @pbrbm pffr      Tif pffr <tt>BfbnContfxt</tt> wf
     *                  brf supplying bn implfmfntbtion for,
     *                  or <tt>null</tt> if tiis objfdt is its own pffr
     * @pbrbm ldlf      Tif durrfnt Lodblf for tiis <tt>BfbnContfxt</tt>. If
     *                  <tt>ldlf</tt> is <tt>null</tt>, tif dffbult lodblf
     *                  is bssignfd to tif <tt>BfbnContfxt</tt> instbndf.
     * @pbrbm dtimf     Tif initibl stbtf, <tt>truf</tt>
     *                  if in dfsign modf,
     *                  <tt>fblsf</tt> if runtimf.
     * @sff jbvb.util.Lodblf#gftDffbult()
     * @sff jbvb.util.Lodblf#sftDffbult(jbvb.util.Lodblf)
     */
    publid BfbnContfxtSupport(BfbnContfxt pffr, Lodblf ldlf, boolfbn dtimf) {
        tiis (pffr, ldlf, dtimf, truf);
    }

    /**
     * Crfbtf bn instbndf using tif spfdififd lodblf
     *
     * @pbrbm pffr      Tif pffr BfbnContfxt wf brf
     *                  supplying bn implfmfntbtion for,
     *                  or <tt>null</tt> if tiis objfdt
     *                  is its own pffr
     * @pbrbm ldlf      Tif durrfnt Lodblf for tiis
     *                  <tt>BfbnContfxt</tt>. If
     *                  <tt>ldlf</tt> is <tt>null</tt>,
     *                  tif dffbult lodblf
     *                  is bssignfd to tif <tt>BfbnContfxt</tt>
     *                  instbndf.
     * @sff jbvb.util.Lodblf#gftDffbult()
     * @sff jbvb.util.Lodblf#sftDffbult(jbvb.util.Lodblf)
     */
    publid BfbnContfxtSupport(BfbnContfxt pffr, Lodblf ldlf) {
        tiis (pffr, ldlf, fblsf, truf);
    }

    /**
     * Crfbtf bn instbndf using witi b dffbult lodblf
     *
     * @pbrbm pffr      Tif pffr <tt>BfbnContfxt</tt> wf brf
     *                  supplying bn implfmfntbtion for,
     *                  or <tt>null</tt> if tiis objfdt
     *                  is its own pffr
     */
    publid BfbnContfxtSupport(BfbnContfxt pffr) {
        tiis (pffr, null, fblsf, truf);
    }

    /**
     * Crfbtf bn instbndf tibt is not b dflfgbtf of bnotifr objfdt
     */

    publid BfbnContfxtSupport() {
        tiis (null, null, fblsf, truf);
    }

    /**
     * Gfts tif instbndf of <tt>BfbnContfxt</tt> tibt
     * tiis objfdt is providing tif implfmfntbtion for.
     * @rfturn tif BfbnContfxt instbndf
     */
    publid BfbnContfxt gftBfbnContfxtPffr() { rfturn (BfbnContfxt)gftBfbnContfxtCiildPffr(); }

    /**
     * <p>
     * Tif instbntibtfCiild mftiod is b donvfnifndf iook
     * in BfbnContfxt to simplify
     * tif tbsk of instbntibting b Bfbn, nfstfd,
     * into b <tt>BfbnContfxt</tt>.
     * </p>
     * <p>
     * Tif sfmbntids of tif bfbnNbmf pbrbmftfr brf dffinfd by jbvb.bfbns.Bfbns.instbntibtf.
     * </p>
     *
     * @pbrbm bfbnNbmf tif nbmf of tif Bfbn to instbntibtf witiin tiis BfbnContfxt
     * @tirows IOExdfption if tifrf is bn I/O frror wifn tif bfbn is bfing dfsfriblizfd
     * @tirows ClbssNotFoundExdfption if tif dlbss
     * idfntififd by tif bfbnNbmf pbrbmftfr is not found
     * @rfturn tif nfw objfdt
     */
    publid Objfdt instbntibtfCiild(String bfbnNbmf)
           tirows IOExdfption, ClbssNotFoundExdfption {
        BfbnContfxt bd = gftBfbnContfxtPffr();

        rfturn Bfbns.instbntibtf(bd.gftClbss().gftClbssLobdfr(), bfbnNbmf, bd);
    }

    /**
     * Gfts tif numbfr of diildrfn durrfntly nfstfd in
     * tiis BfbnContfxt.
     *
     * @rfturn numbfr of diildrfn
     */
    publid int sizf() {
        syndironizfd(diildrfn) {
            rfturn diildrfn.sizf();
        }
    }

    /**
     * Rfports wiftifr or not tiis
     * <tt>BfbnContfxt</tt> is fmpty.
     * A <tt>BfbnContfxt</tt> is donsidfrfd
     * fmpty wifn it dontbins zfro
     * nfstfd diildrfn.
     * @rfturn if tifrf brf not diildrfn
     */
    publid boolfbn isEmpty() {
        syndironizfd(diildrfn) {
            rfturn diildrfn.isEmpty();
        }
    }

    /**
     * Dftfrminfs wiftifr or not tif spfdififd objfdt
     * is durrfntly b diild of tiis <tt>BfbnContfxt</tt>.
     * @pbrbm o tif Objfdt in qufstion
     * @rfturn if tiis objfdt is b diild
     */
    publid boolfbn dontbins(Objfdt o) {
        syndironizfd(diildrfn) {
            rfturn diildrfn.dontbinsKfy(o);
        }
    }

    /**
     * Dftfrminfs wiftifr or not tif spfdififd objfdt
     * is durrfntly b diild of tiis <tt>BfbnContfxt</tt>.
     * @pbrbm o tif Objfdt in qufstion
     * @rfturn if tiis objfdt is b diild
     */
    publid boolfbn dontbinsKfy(Objfdt o) {
        syndironizfd(diildrfn) {
            rfturn diildrfn.dontbinsKfy(o);
        }
    }

    /**
     * Gfts bll JbvbBfbn or <tt>BfbnContfxt</tt> instbndfs
     * durrfntly nfstfd in tiis <tt>BfbnContfxt</tt>.
     * @rfturn bn <tt>Itfrbtor</tt> of tif nfstfd diildrfn
     */
    publid Itfrbtor<Objfdt> itfrbtor() {
        syndironizfd(diildrfn) {
            rfturn nfw BCSItfrbtor(diildrfn.kfySft().itfrbtor());
        }
    }

    /**
     * Gfts bll JbvbBfbn or <tt>BfbnContfxt</tt>
     * instbndfs durrfntly nfstfd in tiis BfbnContfxt.
     */
    publid Objfdt[] toArrby() {
        syndironizfd(diildrfn) {
            rfturn diildrfn.kfySft().toArrby();
        }
    }

    /**
     * Gfts bn brrby dontbining bll diildrfn of
     * tiis <tt>BfbnContfxt</tt> tibt mbtdi
     * tif typfs dontbinfd in brry.
     * @pbrbm brry Tif brrby of objfdt
     * typfs tibt brf of intfrfst.
     * @rfturn bn brrby of diildrfn
     */
    publid Objfdt[] toArrby(Objfdt[] brry) {
        syndironizfd(diildrfn) {
            rfturn diildrfn.kfySft().toArrby(brry);
        }
    }


    /************************************************************************/

    /**
     * protfdtfd finbl subdlbss tibt fndbpsulbtfs bn itfrbtor but implfmfnts
     * b noop rfmovf() mftiod.
     */

    protfdtfd stbtid finbl dlbss BCSItfrbtor implfmfnts Itfrbtor<Objfdt> {
        BCSItfrbtor(Itfrbtor<?> i) { supfr(); srd = i; }

        publid boolfbn ibsNfxt() { rfturn srd.ibsNfxt(); }
        publid Objfdt       nfxt()    { rfturn srd.nfxt();    }
        publid void    rfmovf()  { /* do notiing */      }

        privbtf Itfrbtor<?> srd;
    }

    /************************************************************************/

    /*
     * protfdtfd nfstfd dlbss dontbining pfr diild informbtion, bn instbndf
     * of wiidi is bssodibtfd witi fbdi diild in tif "diildrfn" ibsitbblf.
     * subdlbssfs dbn fxtfnd tiis dlbss to indludf tifir own pfr-diild stbtf.
     *
     * Notf tibt tiis 'vbluf' is sfriblizfd witi tif dorrfsponding diild 'kfy'
     * wifn tif BfbnContfxtSupport is sfriblizfd.
     */

    protfdtfd dlbss BCSCiild implfmfnts Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -5815286101609939109L;

        BCSCiild(Objfdt bdd, Objfdt pffr) {
            supfr();

            diild     = bdd;
            proxyPffr = pffr;
        }

        Objfdt  gftCiild()                  { rfturn diild; }

        void    sftRfmovfPfnding(boolfbn v) { rfmovfPfnding = v; }

        boolfbn isRfmovfPfnding()           { rfturn rfmovfPfnding; }

        boolfbn isProxyPffr()               { rfturn proxyPffr != null; }

        Objfdt  gftProxyPffr()              { rfturn proxyPffr; }
        /*
         * fiflds
         */


        privbtf           Objfdt   diild;
        privbtf           Objfdt   proxyPffr;

        privbtf trbnsifnt boolfbn  rfmovfPfnding;
    }

    /**
     * <p>
     * Subdlbssfs dbn ovfrridf tiis mftiod to insfrt tifir own subdlbss
     * of Ciild witiout ibving to ovfrridf bdd() or tif otifr Collfdtion
     * mftiods tibt bdd diildrfn to tif sft.
     * </p>
     * @pbrbm tbrgftCiild tif diild to drfbtf tif Ciild on bfiblf of
     * @pbrbm pffr        tif pffr if tif trbgftCiild bnd tif pffr brf rflbtfd by bn implfmfntbtion of BfbnContfxtProxy
     * @rfturn Subtypf-spfdifid subdlbss of Ciild witiout ovfrriding dollfdtion mftiods
     */

    protfdtfd BCSCiild drfbtfBCSCiild(Objfdt tbrgftCiild, Objfdt pffr) {
        rfturn nfw BCSCiild(tbrgftCiild, pffr);
    }

    /************************************************************************/

    /**
     * Adds/nfsts b diild witiin tiis <tt>BfbnContfxt</tt>.
     * <p>
     * Invokfd bs b sidf ffffdt of jbvb.bfbns.Bfbns.instbntibtf().
     * If tif diild objfdt is not vblid for bdding tifn tiis mftiod
     * tirows bn IllfgblStbtfExdfption.
     * </p>
     *
     *
     * @pbrbm tbrgftCiild Tif diild objfdts to nfst
     * witiin tiis <tt>BfbnContfxt</tt>
     * @rfturn truf if tif diild wbs bddfd suddfssfully.
     * @sff #vblidbtfPfndingAdd
     */
    publid boolfbn bdd(Objfdt tbrgftCiild) {

        if (tbrgftCiild == null) tirow nfw IllfgblArgumfntExdfption();

        // Tif spfdifidbtion rfquirfs tibt wf do notiing if tif diild
        // is blrfbdy nfstfd ifrfin.

        if (diildrfn.dontbinsKfy(tbrgftCiild)) rfturn fblsf; // tfst bfforf lodking

        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            if (diildrfn.dontbinsKfy(tbrgftCiild)) rfturn fblsf; // difdk bgbin

            if (!vblidbtfPfndingAdd(tbrgftCiild)) {
                tirow nfw IllfgblStbtfExdfption();
            }


            // Tif spfdifidbtion rfquirfs tibt wf invokf sftBfbnContfxt() on tif
            // nfwly bddfd diild if it implfmfnts tif jbvb.bfbns.bfbndontfxt.BfbnContfxtCiild intfrfbdf

            BfbnContfxtCiild dbdd  = gftCiildBfbnContfxtCiild(tbrgftCiild);
            BfbnContfxtCiild  bddp = null;

            syndironizfd(tbrgftCiild) {

                if (tbrgftCiild instbndfof BfbnContfxtProxy) {
                    bddp = ((BfbnContfxtProxy)tbrgftCiild).gftBfbnContfxtProxy();

                    if (bddp == null) tirow nfw NullPointfrExdfption("BfbnContfxtPffr.gftBfbnContfxtProxy()");
                }

                BCSCiild bdsd  = drfbtfBCSCiild(tbrgftCiild, bddp);
                BCSCiild pbdsd = null;

                syndironizfd (diildrfn) {
                    diildrfn.put(tbrgftCiild, bdsd);

                    if (bddp != null) diildrfn.put(bddp, pbdsd = drfbtfBCSCiild(bddp, tbrgftCiild));
                }

                if (dbdd != null) syndironizfd(dbdd) {
                    try {
                        dbdd.sftBfbnContfxt(gftBfbnContfxtPffr());
                    } dbtdi (PropfrtyVftoExdfption pvf) {

                        syndironizfd (diildrfn) {
                            diildrfn.rfmovf(tbrgftCiild);

                            if (bddp != null) diildrfn.rfmovf(bddp);
                        }

                        tirow nfw IllfgblStbtfExdfption();
                    }

                    dbdd.bddPropfrtyCibngfListfnfr("bfbnContfxt", diildPCL);
                    dbdd.bddVftobblfCibngfListfnfr("bfbnContfxt", diildVCL);
                }

                Visibility v = gftCiildVisibility(tbrgftCiild);

                if (v != null) {
                    if (okToUsfGui)
                        v.okToUsfGui();
                    flsf
                        v.dontUsfGui();
                }

                if (gftCiildSfriblizbblf(tbrgftCiild) != null) sfriblizbblf++;

                diildJustAddfdHook(tbrgftCiild, bdsd);

                if (bddp != null) {
                    v = gftCiildVisibility(bddp);

                    if (v != null) {
                        if (okToUsfGui)
                            v.okToUsfGui();
                        flsf
                            v.dontUsfGui();
                    }

                    if (gftCiildSfriblizbblf(bddp) != null) sfriblizbblf++;

                    diildJustAddfdHook(bddp, pbdsd);
                }


            }

            // Tif spfdifidbtion rfquirfs tibt wf firf b notifidbtion of tif dibngf

            firfCiildrfnAddfd(nfw BfbnContfxtMfmbfrsiipEvfnt(gftBfbnContfxtPffr(), bddp == null ? nfw Objfdt[] { tbrgftCiild } : nfw Objfdt[] { tbrgftCiild, bddp } ));

        }

        rfturn truf;
    }

    /**
     * Rfmovfs b diild from tiis BfbnContfxt.  If tif diild objfdt is not
     * for bdding tifn tiis mftiod tirows bn IllfgblStbtfExdfption.
     * @pbrbm tbrgftCiild Tif diild objfdts to rfmovf
     * @sff #vblidbtfPfndingRfmovf
     */
    publid boolfbn rfmovf(Objfdt tbrgftCiild) {
        rfturn rfmovf(tbrgftCiild, truf);
    }

    /**
     * intfrnbl rfmovf usfd wifn rfmovbl dbusfd by
     * unfxpfdtfd <tt>sftBfbnContfxt</tt> or
     * by <tt>rfmovf()</tt> invodbtion.
     * @pbrbm tbrgftCiild tif JbvbBfbn, BfbnContfxt, or Objfdt to bf rfmovfd
     * @pbrbm dbllCiildSftBC usfd to indidbtf tibt
     * tif diild siould bf notififd tibt it is no
     * longfr nfstfd in tiis <tt>BfbnContfxt</tt>.
     * @rfturn wiftifr or not wbs prfsfnt bfforf bfing rfmovfd
     */
    protfdtfd boolfbn rfmovf(Objfdt tbrgftCiild, boolfbn dbllCiildSftBC) {

        if (tbrgftCiild == null) tirow nfw IllfgblArgumfntExdfption();

        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            if (!dontbinsKfy(tbrgftCiild)) rfturn fblsf;

            if (!vblidbtfPfndingRfmovf(tbrgftCiild)) {
                tirow nfw IllfgblStbtfExdfption();
            }

            BCSCiild bdsd  = diildrfn.gft(tbrgftCiild);
            BCSCiild pbdsd = null;
            Objfdt   pffr  = null;

            // wf brf rfquirfd to notify tif diild tibt it is no longfr nfstfd ifrf if
            // it implfmfnts jbvb.bfbns.bfbndontfxt.BfbnContfxtCiild

            syndironizfd(tbrgftCiild) {
                if (dbllCiildSftBC) {
                    BfbnContfxtCiild dbdd = gftCiildBfbnContfxtCiild(tbrgftCiild);
                    if (dbdd != null) syndironizfd(dbdd) {
                        dbdd.rfmovfPropfrtyCibngfListfnfr("bfbnContfxt", diildPCL);
                        dbdd.rfmovfVftobblfCibngfListfnfr("bfbnContfxt", diildVCL);

                        try {
                            dbdd.sftBfbnContfxt(null);
                        } dbtdi (PropfrtyVftoExdfption pvf1) {
                            dbdd.bddPropfrtyCibngfListfnfr("bfbnContfxt", diildPCL);
                            dbdd.bddVftobblfCibngfListfnfr("bfbnContfxt", diildVCL);
                            tirow nfw IllfgblStbtfExdfption();
                        }

                    }
                }

                syndironizfd (diildrfn) {
                    diildrfn.rfmovf(tbrgftCiild);

                    if (bdsd.isProxyPffr()) {
                        pbdsd = diildrfn.gft(pffr = bdsd.gftProxyPffr());
                        diildrfn.rfmovf(pffr);
                    }
                }

                if (gftCiildSfriblizbblf(tbrgftCiild) != null) sfriblizbblf--;

                diildJustRfmovfdHook(tbrgftCiild, bdsd);

                if (pffr != null) {
                    if (gftCiildSfriblizbblf(pffr) != null) sfriblizbblf--;

                    diildJustRfmovfdHook(pffr, pbdsd);
                }
            }

            firfCiildrfnRfmovfd(nfw BfbnContfxtMfmbfrsiipEvfnt(gftBfbnContfxtPffr(), pffr == null ? nfw Objfdt[] { tbrgftCiild } : nfw Objfdt[] { tbrgftCiild, pffr } ));

        }

        rfturn truf;
    }

    /**
     * Tfsts to sff if bll objfdts in tif
     * spfdififd <tt>Collfdtion</tt> brf diildrfn of
     * tiis <tt>BfbnContfxt</tt>.
     * @pbrbm d tif spfdififd <tt>Collfdtion</tt>
     *
     * @rfturn <tt>truf</tt> if bll objfdts
     * in tif dollfdtion brf diildrfn of
     * tiis <tt>BfbnContfxt</tt>, fblsf if not.
     */
    @SupprfssWbrnings("rbwtypfs")
    publid boolfbn dontbinsAll(Collfdtion d) {
        syndironizfd(diildrfn) {
            Itfrbtor<?> i = d.itfrbtor();
            wiilf (i.ibsNfxt())
                if(!dontbins(i.nfxt()))
                    rfturn fblsf;

            rfturn truf;
        }
    }

    /**
     * bdd Collfdtion to sft of Ciildrfn (Unsupportfd)
     * implfmfntbtions must syndironizfd on tif iifrbrdiy lodk bnd "diildrfn" protfdtfd fifld
     * @tirows UnsupportfdOpfrbtionExdfption tirown undonditionblly by tiis implfmfntbtion
     * @rfturn tiis implfmfntbtion undonditionblly tirows {@dodf UnsupportfdOpfrbtionExdfption}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid boolfbn bddAll(Collfdtion d) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * rfmovf bll spfdififd diildrfn (Unsupportfd)
     * implfmfntbtions must syndironizfd on tif iifrbrdiy lodk bnd "diildrfn" protfdtfd fifld
     * @tirows UnsupportfdOpfrbtionExdfption tirown undonditionblly by tiis implfmfntbtion
     * @rfturn tiis implfmfntbtion undonditionblly tirows {@dodf UnsupportfdOpfrbtionExdfption}

     */
    @SupprfssWbrnings("rbwtypfs")
    publid boolfbn rfmovfAll(Collfdtion d) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * rftbin only spfdififd diildrfn (Unsupportfd)
     * implfmfntbtions must syndironizfd on tif iifrbrdiy lodk bnd "diildrfn" protfdtfd fifld
     * @tirows UnsupportfdOpfrbtionExdfption tirown undonditionblly by tiis implfmfntbtion
     * @rfturn tiis implfmfntbtion undonditionblly tirows {@dodf UnsupportfdOpfrbtionExdfption}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid boolfbn rftbinAll(Collfdtion d) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * dlfbr tif diildrfn (Unsupportfd)
     * implfmfntbtions must syndironizfd on tif iifrbrdiy lodk bnd "diildrfn" protfdtfd fifld
     * @tirows UnsupportfdOpfrbtionExdfption tirown undonditionblly by tiis implfmfntbtion
     */
    publid void dlfbr() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Adds b BfbnContfxtMfmbfrsiipListfnfr
     *
     * @pbrbm  bdml tif BfbnContfxtMfmbfrsiipListfnfr to bdd
     * @tirows NullPointfrExdfption if tif brgumfnt is null
     */

    publid void bddBfbnContfxtMfmbfrsiipListfnfr(BfbnContfxtMfmbfrsiipListfnfr bdml) {
        if (bdml == null) tirow nfw NullPointfrExdfption("listfnfr");

        syndironizfd(bdmListfnfrs) {
            if (bdmListfnfrs.dontbins(bdml))
                rfturn;
            flsf
                bdmListfnfrs.bdd(bdml);
        }
    }

    /**
     * Rfmovfs b BfbnContfxtMfmbfrsiipListfnfr
     *
     * @pbrbm  bdml tif BfbnContfxtMfmbfrsiipListfnfr to rfmovf
     * @tirows NullPointfrExdfption if tif brgumfnt is null
     */

    publid void rfmovfBfbnContfxtMfmbfrsiipListfnfr(BfbnContfxtMfmbfrsiipListfnfr bdml) {
        if (bdml == null) tirow nfw NullPointfrExdfption("listfnfr");

        syndironizfd(bdmListfnfrs) {
            if (!bdmListfnfrs.dontbins(bdml))
                rfturn;
            flsf
                bdmListfnfrs.rfmovf(bdml);
        }
    }

    /**
     * @pbrbm nbmf tif nbmf of tif rfsourdf rfqufstfd.
     * @pbrbm bdd  tif diild objfdt mbking tif rfqufst.
     *
     * @rfturn  tif rfqufstfd rfsourdf bs bn InputStrfbm
     * @tirows  NullPointfrExdfption if tif brgumfnt is null
     */

    publid InputStrfbm gftRfsourdfAsStrfbm(String nbmf, BfbnContfxtCiild bdd) {
        if (nbmf == null) tirow nfw NullPointfrExdfption("nbmf");
        if (bdd  == null) tirow nfw NullPointfrExdfption("bdd");

        if (dontbinsKfy(bdd)) {
            ClbssLobdfr dl = bdd.gftClbss().gftClbssLobdfr();

            rfturn dl != null ? dl.gftRfsourdfAsStrfbm(nbmf)
                              : ClbssLobdfr.gftSystfmRfsourdfAsStrfbm(nbmf);
        } flsf tirow nfw IllfgblArgumfntExdfption("Not b vblid diild");
    }

    /**
     * @pbrbm nbmf tif nbmf of tif rfsourdf rfqufstfd.
     * @pbrbm bdd  tif diild objfdt mbking tif rfqufst.
     *
     * @rfturn tif rfqufstfd rfsourdf bs bn InputStrfbm
     */

    publid URL gftRfsourdf(String nbmf, BfbnContfxtCiild bdd) {
        if (nbmf == null) tirow nfw NullPointfrExdfption("nbmf");
        if (bdd  == null) tirow nfw NullPointfrExdfption("bdd");

        if (dontbinsKfy(bdd)) {
            ClbssLobdfr dl = bdd.gftClbss().gftClbssLobdfr();

            rfturn dl != null ? dl.gftRfsourdf(nbmf)
                              : ClbssLobdfr.gftSystfmRfsourdf(nbmf);
        } flsf tirow nfw IllfgblArgumfntExdfption("Not b vblid diild");
    }

    /**
     * Sfts tif nfw dfsign timf vbluf for tiis <tt>BfbnContfxt</tt>.
     * @pbrbm dTimf tif nfw dfsignTimf vbluf
     */
    publid syndironizfd void sftDfsignTimf(boolfbn dTimf) {
        if (dfsignTimf != dTimf) {
            dfsignTimf = dTimf;

            firfPropfrtyCibngf("dfsignModf", Boolfbn.vblufOf(!dTimf), Boolfbn.vblufOf(dTimf));
        }
    }


    /**
     * Rfports wiftifr or not tiis objfdt is in
     * durrfntly in dfsign timf modf.
     * @rfturn <tt>truf</tt> if in dfsign timf modf,
     * <tt>fblsf</tt> if not
     */
    publid syndironizfd boolfbn isDfsignTimf() { rfturn dfsignTimf; }

    /**
     * Sfts tif lodblf of tiis BfbnContfxt.
     * @pbrbm nfwLodblf tif nfw lodblf. Tiis mftiod dbll will ibvf
     *        no ffffdt if nfwLodblf is <CODE>null</CODE>.
     * @tirows PropfrtyVftoExdfption if tif nfw vbluf is rfjfdtfd
     */
    publid syndironizfd void sftLodblf(Lodblf nfwLodblf) tirows PropfrtyVftoExdfption {

        if ((lodblf != null && !lodblf.fqubls(nfwLodblf)) && nfwLodblf != null) {
            Lodblf old = lodblf;

            firfVftobblfCibngf("lodblf", old, nfwLodblf); // tirows

            lodblf = nfwLodblf;

            firfPropfrtyCibngf("lodblf", old, nfwLodblf);
        }
    }

    /**
     * Gfts tif lodblf for tiis <tt>BfbnContfxt</tt>.
     *
     * @rfturn tif durrfnt Lodblf of tif <tt>BfbnContfxt</tt>
     */
    publid syndironizfd Lodblf gftLodblf() { rfturn lodblf; }

    /**
     * <p>
     * Tiis mftiod is typidblly dbllfd from tif fnvironmfnt in ordfr to dftfrminf
     * if tif implfmfntor "nffds" b GUI.
     * </p>
     * <p>
     * Tif blgoritim usfd ifrfin tfsts tif BfbnContfxtPffr, bnd its durrfnt diildrfn
     * to dftfrminf if tify brf fitifr Contbinfrs, Componfnts, or if tify implfmfnt
     * Visibility bnd rfturn nffdsGui() == truf.
     * </p>
     * @rfturn <tt>truf</tt> if tif implfmfntor nffds b GUI
     */
    publid syndironizfd boolfbn nffdsGui() {
        BfbnContfxt bd = gftBfbnContfxtPffr();

        if (bd != tiis) {
            if (bd instbndfof Visibility) rfturn ((Visibility)bd).nffdsGui();

            if (bd instbndfof Contbinfr || bd instbndfof Componfnt)
                rfturn truf;
        }

        syndironizfd(diildrfn) {
            for (Itfrbtor<Objfdt> i = diildrfn.kfySft().itfrbtor(); i.ibsNfxt();) {
                Objfdt d = i.nfxt();

                try {
                        rfturn ((Visibility)d).nffdsGui();
                    } dbtdi (ClbssCbstExdfption ddf) {
                        // do notiing ...
                    }

                    if (d instbndfof Contbinfr || d instbndfof Componfnt)
                        rfturn truf;
            }
        }

        rfturn fblsf;
    }

    /**
     * notify tiis instbndf tibt it mby no longfr rfndfr b GUI.
     */

    publid syndironizfd void dontUsfGui() {
        if (okToUsfGui) {
            okToUsfGui = fblsf;

            // lfts blso tfll tif Ciildrfn tibt dbn tibt tify mby not usf tifir GUI's
            syndironizfd(diildrfn) {
                for (Itfrbtor<Objfdt> i = diildrfn.kfySft().itfrbtor(); i.ibsNfxt();) {
                    Visibility v = gftCiildVisibility(i.nfxt());

                    if (v != null) v.dontUsfGui();
               }
            }
        }
    }

    /**
     * Notify tiis instbndf tibt it mby now rfndfr b GUI
     */

    publid syndironizfd void okToUsfGui() {
        if (!okToUsfGui) {
            okToUsfGui = truf;

            // lfts blso tfll tif Ciildrfn tibt dbn tibt tify mby usf tifir GUI's
            syndironizfd(diildrfn) {
                for (Itfrbtor<Objfdt> i = diildrfn.kfySft().itfrbtor(); i.ibsNfxt();) {
                    Visibility v = gftCiildVisibility(i.nfxt());

                    if (v != null) v.okToUsfGui();
                }
            }
        }
    }

    /**
     * Usfd to dftfrminf if tif <tt>BfbnContfxt</tt>
     * diild is bvoiding using its GUI.
     * @rfturn is tiis instbndf bvoiding using its GUI?
     * @sff Visibility
     */
    publid boolfbn bvoidingGui() {
        rfturn !okToUsfGui && nffdsGui();
    }

    /**
     * Is tiis <tt>BfbnContfxt</tt> in tif
     * prodfss of bfing sfriblizfd?
     * @rfturn if tiis <tt>BfbnContfxt</tt> is
     * durrfntly bfing sfriblizfd
     */
    publid boolfbn isSfriblizing() { rfturn sfriblizing; }

    /**
     * Rfturns bn itfrbtor of bll diildrfn
     * of tiis <tt>BfbnContfxt</tt>.
     * @rfturn bn itfrbtor for bll tif durrfnt BCSCiild vblufs
     */
    protfdtfd Itfrbtor<BCSCiild> bdsCiildrfn() { syndironizfd(diildrfn) { rfturn diildrfn.vblufs().itfrbtor();  } }

    /**
     * dbllfd by writfObjfdt bftfr dffbultWritfObjfdt() but prior to
     * sfriblizbtion of durrfntly sfriblizbblf diildrfn.
     *
     * Tiis mftiod mby bf ovfrriddfn by subdlbssfs to pfrform dustom
     * sfriblizbtion of tifir stbtf prior to tiis supfrdlbss sfriblizing
     * tif diildrfn.
     *
     * Tiis mftiod siould not iowfvfr bf usfd by subdlbssfs to rfplbdf tifir
     * own implfmfntbtion (if bny) of writfObjfdt().
     * @pbrbm oos tif {@dodf ObjfdtOutputStrfbm} to usf during sfriblizbtion
     * @tirows IOExdfption if sfriblizbtion fbilfd
     */

    protfdtfd void bdsPrfSfriblizbtionHook(ObjfdtOutputStrfbm oos) tirows IOExdfption {
    }

    /**
     * dbllfd by rfbdObjfdt bftfr dffbultRfbdObjfdt() but prior to
     * dfsfriblizbtion of bny diildrfn.
     *
     * Tiis mftiod mby bf ovfrriddfn by subdlbssfs to pfrform dustom
     * dfsfriblizbtion of tifir stbtf prior to tiis supfrdlbss dfsfriblizing
     * tif diildrfn.
     *
     * Tiis mftiod siould not iowfvfr bf usfd by subdlbssfs to rfplbdf tifir
     * own implfmfntbtion (if bny) of rfbdObjfdt().
     * @pbrbm ois tif {@dodf ObjfdtInputStrfbm} to usf during dfsfriblizbtion
     * @tirows IOExdfption if dfsfriblizbtion fbilfd
     * @tirows ClbssNotFoundExdfption if nffdfd dlbssfs brf not found
     */

    protfdtfd void bdsPrfDfsfriblizbtionHook(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {
    }

    /**
     * Cbllfd by rfbdObjfdt witi tif nfwly dfsfriblizfd diild bnd BCSCiild.
     * @pbrbm diild tif nfwly dfsfriblizfd diild
     * @pbrbm bdsd tif nfwly dfsfriblizfd BCSCiild
     */
    protfdtfd void diildDfsfriblizfdHook(Objfdt diild, BCSCiild bdsd) {
        syndironizfd(diildrfn) {
            diildrfn.put(diild, bdsd);
        }
    }

    /**
     * Usfd by writfObjfdt to sfriblizf b Collfdtion.
     * @pbrbm oos tif <tt>ObjfdtOutputStrfbm</tt>
     * to usf during sfriblizbtion
     * @pbrbm doll tif <tt>Collfdtion</tt> to sfriblizf
     * @tirows IOExdfption if sfriblizbtion fbilfd
     */
    protfdtfd finbl void sfriblizf(ObjfdtOutputStrfbm oos, Collfdtion<?> doll) tirows IOExdfption {
        int      dount   = 0;
        Objfdt[] objfdts = doll.toArrby();

        for (int i = 0; i < objfdts.lfngti; i++) {
            if (objfdts[i] instbndfof Sfriblizbblf)
                dount++;
            flsf
                objfdts[i] = null;
        }

        oos.writfInt(dount); // numbfr of subsfqufnt objfdts

        for (int i = 0; dount > 0; i++) {
            Objfdt o = objfdts[i];

            if (o != null) {
                oos.writfObjfdt(o);
                dount--;
            }
        }
    }

    /**
     * usfd by rfbdObjfdt to dfsfriblizf b dollfdtion.
     * @pbrbm ois tif ObjfdtInputStrfbm to usf
     * @pbrbm doll tif Collfdtion
     * @tirows IOExdfption if dfsfriblizbtion fbilfd
     * @tirows ClbssNotFoundExdfption if nffdfd dlbssfs brf not found
     */
    @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
    protfdtfd finbl void dfsfriblizf(ObjfdtInputStrfbm ois, Collfdtion doll) tirows IOExdfption, ClbssNotFoundExdfption {
        int dount = 0;

        dount = ois.rfbdInt();

        wiilf (dount-- > 0) {
            doll.bdd(ois.rfbdObjfdt());
        }
    }

    /**
     * Usfd to sfriblizf bll diildrfn of
     * tiis <tt>BfbnContfxt</tt>.
     * @pbrbm oos tif <tt>ObjfdtOutputStrfbm</tt>
     * to usf during sfriblizbtion
     * @tirows IOExdfption if sfriblizbtion fbilfd
     */
    publid finbl void writfCiildrfn(ObjfdtOutputStrfbm oos) tirows IOExdfption {
        if (sfriblizbblf <= 0) rfturn;

        boolfbn prfv = sfriblizing;

        sfriblizing = truf;

        int dount = 0;

        syndironizfd(diildrfn) {
            Itfrbtor<Mbp.Entry<Objfdt, BCSCiild>> i = diildrfn.fntrySft().itfrbtor();

            wiilf (i.ibsNfxt() && dount < sfriblizbblf) {
                Mbp.Entry<Objfdt, BCSCiild> fntry = i.nfxt();

                if (fntry.gftKfy() instbndfof Sfriblizbblf) {
                    try {
                        oos.writfObjfdt(fntry.gftKfy());   // diild
                        oos.writfObjfdt(fntry.gftVbluf()); // BCSCiild
                    } dbtdi (IOExdfption iof) {
                        sfriblizing = prfv;
                        tirow iof;
                    }
                    dount++;
                }
            }
        }

        sfriblizing = prfv;

        if (dount != sfriblizbblf) {
            tirow nfw IOExdfption("wrotf difffrfnt numbfr of diildrfn tibn fxpfdtfd");
        }

    }

    /**
     * Sfriblizf tif BfbnContfxtSupport, if tiis instbndf ibs b distindt
     * pffr (tibt is tiis objfdt is bdting bs b dflfgbtf for bnotifr) tifn
     * tif diildrfn of tiis instbndf brf not sfriblizfd ifrf duf to b
     * 'diidkfn bnd fgg' problfm tibt oddurs on dfsfriblizbtion of tif
     * diildrfn bt tif sbmf timf bs tiis instbndf.
     *
     * Tifrfforf in situbtions wifrf tifrf is b distindt pffr to tiis instbndf
     * it siould blwbys dbll writfObjfdt() followfd by writfCiildrfn() bnd
     * rfbdObjfdt() followfd by rfbdCiildrfn().
     *
     * @pbrbm oos tif ObjfdtOutputStrfbm
     */

    privbtf syndironizfd void writfObjfdt(ObjfdtOutputStrfbm oos) tirows IOExdfption, ClbssNotFoundExdfption {
        sfriblizing = truf;

        syndironizfd (BfbnContfxt.globblHifrbrdiyLodk) {
            try {
                oos.dffbultWritfObjfdt(); // sfriblizf tif BfbnContfxtSupport objfdt

                bdsPrfSfriblizbtionHook(oos);

                if (sfriblizbblf > 0 && tiis.fqubls(gftBfbnContfxtPffr()))
                    writfCiildrfn(oos);

                sfriblizf(oos, (Collfdtion)bdmListfnfrs);
            } finblly {
                sfriblizing = fblsf;
            }
        }
    }

    /**
     * Wifn bn instbndf of tiis dlbss is usfd bs b dflfgbtf for tif
     * implfmfntbtion of tif BfbnContfxt protodols (bnd its subprotodols)
     * tifrf fxists b 'diidkfn bnd fgg' problfm during dfsfriblizbtion
     * @pbrbm ois tif ObjfdtInputStrfbm to usf
     * @tirows IOExdfption if dfsfriblizbtion fbilfd
     * @tirows ClbssNotFoundExdfption if nffdfd dlbssfs brf not found
     */

    publid finbl void rfbdCiildrfn(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {
        int dount = sfriblizbblf;

        wiilf (dount-- > 0) {
            Objfdt                      diild = null;
            BfbnContfxtSupport.BCSCiild bsdd  = null;

            try {
                diild = ois.rfbdObjfdt();
                bsdd  = (BfbnContfxtSupport.BCSCiild)ois.rfbdObjfdt();
            } dbtdi (IOExdfption iof) {
                dontinuf;
            } dbtdi (ClbssNotFoundExdfption dnff) {
                dontinuf;
            }


            syndironizfd(diild) {
                BfbnContfxtCiild bdd = null;

                try {
                    bdd = (BfbnContfxtCiild)diild;
                } dbtdi (ClbssCbstExdfption ddf) {
                    // do notiing;
                }

                if (bdd != null) {
                    try {
                        bdd.sftBfbnContfxt(gftBfbnContfxtPffr());

                       bdd.bddPropfrtyCibngfListfnfr("bfbnContfxt", diildPCL);
                       bdd.bddVftobblfCibngfListfnfr("bfbnContfxt", diildVCL);

                    } dbtdi (PropfrtyVftoExdfption pvf) {
                        dontinuf;
                    }
                }

                diildDfsfriblizfdHook(diild, bsdd);
            }
        }
    }

    /**
     * dfsfriblizf dontfnts ... if tiis instbndf ibs b distindt pffr tif
     * diildrfn brf *not* sfriblizfd ifrf, tif pffr's rfbdObjfdt() must dbll
     * rfbdCiildrfn() bftfr dfsfriblizing tiis instbndf.
     */

    privbtf syndironizfd void rfbdObjfdt(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {

        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            ois.dffbultRfbdObjfdt();

            initiblizf();

            bdsPrfDfsfriblizbtionHook(ois);

            if (sfriblizbblf > 0 && tiis.fqubls(gftBfbnContfxtPffr()))
                rfbdCiildrfn(ois);

            dfsfriblizf(ois, bdmListfnfrs = nfw ArrbyList<>(1));
        }
    }

    /**
     * subdlbssfs mby fnvflopf to monitor vfto diild propfrty dibngfs.
     */

    publid void vftobblfCibngf(PropfrtyCibngfEvfnt pdf) tirows PropfrtyVftoExdfption {
        String propfrtyNbmf = pdf.gftPropfrtyNbmf();
        Objfdt sourdf       = pdf.gftSourdf();

        syndironizfd(diildrfn) {
            if ("bfbnContfxt".fqubls(propfrtyNbmf) &&
                dontbinsKfy(sourdf)                    &&
                !gftBfbnContfxtPffr().fqubls(pdf.gftNfwVbluf())
            ) {
                if (!vblidbtfPfndingRfmovf(sourdf)) {
                    tirow nfw PropfrtyVftoExdfption("durrfnt BfbnContfxt vftofs sftBfbnContfxt()", pdf);
                } flsf diildrfn.gft(sourdf).sftRfmovfPfnding(truf);
            }
        }
    }

    /**
     * subdlbssfs mby fnvflopf to monitor diild propfrty dibngfs.
     */

    publid void propfrtyCibngf(PropfrtyCibngfEvfnt pdf) {
        String propfrtyNbmf = pdf.gftPropfrtyNbmf();
        Objfdt sourdf       = pdf.gftSourdf();

        syndironizfd(diildrfn) {
            if ("bfbnContfxt".fqubls(propfrtyNbmf) &&
                dontbinsKfy(sourdf)                    &&
                diildrfn.gft(sourdf).isRfmovfPfnding()) {
                BfbnContfxt bd = gftBfbnContfxtPffr();

                if (bd.fqubls(pdf.gftOldVbluf()) && !bd.fqubls(pdf.gftNfwVbluf())) {
                    rfmovf(sourdf, fblsf);
                } flsf {
                    diildrfn.gft(sourdf).sftRfmovfPfnding(fblsf);
                }
            }
        }
    }

    /**
     * <p>
     * Subdlbssfs of tiis dlbss mby ovfrridf, or fnvflopf, tiis mftiod to
     * bdd vblidbtion bfibvior for tif BfbnContfxt to fxbminf diild objfdts
     * immfdibtfly prior to tifir bfing bddfd to tif BfbnContfxt.
     * </p>
     *
     * @pbrbm tbrgftCiild tif diild to drfbtf tif Ciild on bfiblf of
     * @rfturn truf iff tif diild mby bf bddfd to tiis BfbnContfxt, otifrwisf fblsf.
     */

    protfdtfd boolfbn vblidbtfPfndingAdd(Objfdt tbrgftCiild) {
        rfturn truf;
    }

    /**
     * <p>
     * Subdlbssfs of tiis dlbss mby ovfrridf, or fnvflopf, tiis mftiod to
     * bdd vblidbtion bfibvior for tif BfbnContfxt to fxbminf diild objfdts
     * immfdibtfly prior to tifir bfing rfmovfd from tif BfbnContfxt.
     * </p>
     *
     * @pbrbm tbrgftCiild tif diild to drfbtf tif Ciild on bfiblf of
     * @rfturn truf iff tif diild mby bf rfmovfd from tiis BfbnContfxt, otifrwisf fblsf.
     */

    protfdtfd boolfbn vblidbtfPfndingRfmovf(Objfdt tbrgftCiild) {
        rfturn truf;
    }

    /**
     * subdlbssfs mby ovfrridf tiis mftiod to simply fxtfnd bdd() sfmbntids
     * bftfr tif diild ibs bffn bddfd bnd bfforf tif fvfnt notifidbtion ibs
     * oddurrfd. Tif mftiod is dbllfd witi tif diild syndironizfd.
     * @pbrbm diild tif diild
     * @pbrbm bdsd tif BCSCiild
     */

    protfdtfd void diildJustAddfdHook(Objfdt diild, BCSCiild bdsd) {
    }

    /**
     * subdlbssfs mby ovfrridf tiis mftiod to simply fxtfnd rfmovf() sfmbntids
     * bftfr tif diild ibs bffn rfmovfd bnd bfforf tif fvfnt notifidbtion ibs
     * oddurrfd. Tif mftiod is dbllfd witi tif diild syndironizfd.
     * @pbrbm diild tif diild
     * @pbrbm bdsd tif BCSCiild
     */

    protfdtfd void diildJustRfmovfdHook(Objfdt diild, BCSCiild bdsd) {
    }

    /**
     * Gfts tif Componfnt (if bny) bssodibtfd witi tif spfdififd diild.
     * @pbrbm diild tif spfdififd diild
     * @rfturn tif Componfnt (if bny) bssodibtfd witi tif spfdififd diild.
     */
    protfdtfd stbtid finbl Visibility gftCiildVisibility(Objfdt diild) {
        try {
            rfturn (Visibility)diild;
        } dbtdi (ClbssCbstExdfption ddf) {
            rfturn null;
        }
    }

    /**
     * Gfts tif Sfriblizbblf (if bny) bssodibtfd witi tif spfdififd Ciild
     * @pbrbm diild tif spfdififd diild
     * @rfturn tif Sfriblizbblf (if bny) bssodibtfd witi tif spfdififd Ciild
     */
    protfdtfd stbtid finbl Sfriblizbblf gftCiildSfriblizbblf(Objfdt diild) {
        try {
            rfturn (Sfriblizbblf)diild;
        } dbtdi (ClbssCbstExdfption ddf) {
            rfturn null;
        }
    }

    /**
     * Gfts tif PropfrtyCibngfListfnfr
     * (if bny) of tif spfdififd diild
     * @pbrbm diild tif spfdififd diild
     * @rfturn tif PropfrtyCibngfListfnfr (if bny) of tif spfdififd diild
     */
    protfdtfd stbtid finbl PropfrtyCibngfListfnfr gftCiildPropfrtyCibngfListfnfr(Objfdt diild) {
        try {
            rfturn (PropfrtyCibngfListfnfr)diild;
        } dbtdi (ClbssCbstExdfption ddf) {
            rfturn null;
        }
    }

    /**
     * Gfts tif VftobblfCibngfListfnfr
     * (if bny) of tif spfdififd diild
     * @pbrbm diild tif spfdififd diild
     * @rfturn tif VftobblfCibngfListfnfr (if bny) of tif spfdififd diild
     */
    protfdtfd stbtid finbl VftobblfCibngfListfnfr gftCiildVftobblfCibngfListfnfr(Objfdt diild) {
        try {
            rfturn (VftobblfCibngfListfnfr)diild;
        } dbtdi (ClbssCbstExdfption ddf) {
            rfturn null;
        }
    }

    /**
     * Gfts tif BfbnContfxtMfmbfrsiipListfnfr
     * (if bny) of tif spfdififd diild
     * @pbrbm diild tif spfdififd diild
     * @rfturn tif BfbnContfxtMfmbfrsiipListfnfr (if bny) of tif spfdififd diild
     */
    protfdtfd stbtid finbl BfbnContfxtMfmbfrsiipListfnfr gftCiildBfbnContfxtMfmbfrsiipListfnfr(Objfdt diild) {
        try {
            rfturn (BfbnContfxtMfmbfrsiipListfnfr)diild;
        } dbtdi (ClbssCbstExdfption ddf) {
            rfturn null;
        }
    }

    /**
     * Gfts tif BfbnContfxtCiild (if bny) of tif spfdififd diild
     * @pbrbm diild tif spfdififd diild
     * @rfturn  tif BfbnContfxtCiild (if bny) of tif spfdififd diild
     * @tirows  IllfgblArgumfntExdfption if diild implfmfnts boti BfbnContfxtCiild bnd BfbnContfxtProxy
     */
    protfdtfd stbtid finbl BfbnContfxtCiild gftCiildBfbnContfxtCiild(Objfdt diild) {
        try {
            BfbnContfxtCiild bdd = (BfbnContfxtCiild)diild;

            if (diild instbndfof BfbnContfxtCiild && diild instbndfof BfbnContfxtProxy)
                tirow nfw IllfgblArgumfntExdfption("diild dbnnot implfmfnt boti BfbnContfxtCiild bnd BfbnContfxtProxy");
            flsf
                rfturn bdd;
        } dbtdi (ClbssCbstExdfption ddf) {
            try {
                rfturn ((BfbnContfxtProxy)diild).gftBfbnContfxtProxy();
            } dbtdi (ClbssCbstExdfption ddf1) {
                rfturn null;
            }
        }
    }

    /**
     * Firf b BfbnContfxtsiipEvfnt on tif BfbnContfxtMfmbfrsiipListfnfr intfrfbdf
     * @pbrbm bdmf tif fvfnt to firf
     */

    protfdtfd finbl void firfCiildrfnAddfd(BfbnContfxtMfmbfrsiipEvfnt bdmf) {
        Objfdt[] dopy;

        syndironizfd(bdmListfnfrs) { dopy = bdmListfnfrs.toArrby(); }

        for (int i = 0; i < dopy.lfngti; i++)
            ((BfbnContfxtMfmbfrsiipListfnfr)dopy[i]).diildrfnAddfd(bdmf);
    }

    /**
     * Firf b BfbnContfxtsiipEvfnt on tif BfbnContfxtMfmbfrsiipListfnfr intfrfbdf
     * @pbrbm bdmf tif fvfnt to firf
     */

    protfdtfd finbl void firfCiildrfnRfmovfd(BfbnContfxtMfmbfrsiipEvfnt bdmf) {
        Objfdt[] dopy;

        syndironizfd(bdmListfnfrs) { dopy = bdmListfnfrs.toArrby(); }

        for (int i = 0; i < dopy.lfngti; i++)
            ((BfbnContfxtMfmbfrsiipListfnfr)dopy[i]).diildrfnRfmovfd(bdmf);
    }

    /**
     * protfdtfd mftiod dbllfd from donstrudtor bnd rfbdObjfdt to initiblizf
     * trbnsifnt stbtf of BfbnContfxtSupport instbndf.
     *
     * Tiis dlbss usfs tiis mftiod to instbntibtf innfr dlbss listfnfrs usfd
     * to monitor PropfrtyCibngf bnd VftobblfCibngf fvfnts on diildrfn.
     *
     * subdlbssfs mby fnvflopf tiis mftiod to bdd tifir own initiblizbtion
     * bfibvior
     */

    protfdtfd syndironizfd void initiblizf() {
        diildrfn     = nfw HbsiMbp<>(sfriblizbblf + 1);
        bdmListfnfrs = nfw ArrbyList<>(1);

        diildPCL = nfw PropfrtyCibngfListfnfr() {

            /*
             * tiis bdbptor is usfd by tif BfbnContfxtSupport dlbss to forwbrd
             * propfrty dibngfs from b diild to tif BfbnContfxt, bvoiding
             * bddidfntibl sfriblizbtion of tif BfbnContfxt by b bbdly
             * bfibvfd Sfriblizbblf diild.
             */

            publid void propfrtyCibngf(PropfrtyCibngfEvfnt pdf) {
                BfbnContfxtSupport.tiis.propfrtyCibngf(pdf);
            }
        };

        diildVCL = nfw VftobblfCibngfListfnfr() {

            /*
             * tiis bdbptor is usfd by tif BfbnContfxtSupport dlbss to forwbrd
             * vftobblf dibngfs from b diild to tif BfbnContfxt, bvoiding
             * bddidfntibl sfriblizbtion of tif BfbnContfxt by b bbdly
             * bfibvfd Sfriblizbblf diild.
             */

            publid void vftobblfCibngf(PropfrtyCibngfEvfnt pdf) tirows PropfrtyVftoExdfption {
                BfbnContfxtSupport.tiis.vftobblfCibngf(pdf);
             }
        };
    }

    /**
     * Gfts b dopy of tif tiis BfbnContfxt's diildrfn.
     * @rfturn b dopy of tif durrfnt nfstfd diildrfn
     */
    protfdtfd finbl Objfdt[] dopyCiildrfn() {
        syndironizfd(diildrfn) { rfturn diildrfn.kfySft().toArrby(); }
    }

    /**
     * Tfsts to sff if two dlbss objfdts,
     * or tifir nbmfs brf fqubl.
     * @pbrbm first tif first objfdt
     * @pbrbm sfdond tif sfdond objfdt
     * @rfturn truf if fqubl, fblsf if not
     */
    protfdtfd stbtid finbl boolfbn dlbssEqubls(Clbss<?> first, Clbss<?> sfdond) {
        rfturn first.fqubls(sfdond) || first.gftNbmf().fqubls(sfdond.gftNbmf());
    }


    /*
     * fiflds
     */


    /**
     * bll bddfssfs to tif <dodf> protfdtfd HbsiMbp diildrfn </dodf> fifld
     * sibll bf syndironizfd on tibt objfdt.
     */
    protfdtfd trbnsifnt HbsiMbp<Objfdt, BCSCiild>         diildrfn;

    privbtf             int             sfriblizbblf  = 0; // diildrfn sfriblizbblf

    /**
     * bll bddfssfs to tif <dodf> protfdtfd ArrbyList bdmListfnfrs </dodf> fifld
     * sibll bf syndironizfd on tibt objfdt.
     */
    protfdtfd trbnsifnt ArrbyList<BfbnContfxtMfmbfrsiipListfnfr> bdmListfnfrs;

    //

    /**
     * Tif durrfnt lodblf of tiis BfbnContfxt.
     */
    protfdtfd           Lodblf          lodblf;

    /**
     * A <tt>boolfbn</tt> indidbting if tiis
     * instbndf mby now rfndfr b GUI.
     */
    protfdtfd           boolfbn         okToUsfGui;


    /**
     * A <tt>boolfbn</tt> indidbting wiftifr or not
     * tiis objfdt is durrfntly in dfsign timf modf.
     */
    protfdtfd           boolfbn         dfsignTimf;

    /*
     * trbnsifnt
     */

    privbtf trbnsifnt PropfrtyCibngfListfnfr diildPCL;

    privbtf trbnsifnt VftobblfCibngfListfnfr diildVCL;

    privbtf trbnsifnt boolfbn                sfriblizing;
}
