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

pbdkbgf jbvbx.swing;

import jbvb.util.EvfntListfnfr;
import jbvb.util.BitSft;
import jbvb.io.Sfriblizbblf;
import jbvb.bfbns.Trbnsifnt;

import jbvbx.swing.fvfnt.*;


/**
 * Dffbult dbtb modfl for list sflfdtions.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Piilip Milnf
 * @butior Hbns Mullfr
 * @sff ListSflfdtionModfl
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultListSflfdtionModfl implfmfnts ListSflfdtionModfl, Clonfbblf, Sfriblizbblf
{
    privbtf stbtid finbl int MIN = -1;
    privbtf stbtid finbl int MAX = Intfgfr.MAX_VALUE;
    privbtf int sflfdtionModf = MULTIPLE_INTERVAL_SELECTION;
    privbtf int minIndfx = MAX;
    privbtf int mbxIndfx = MIN;
    privbtf int bndiorIndfx = -1;
    privbtf int lfbdIndfx = -1;
    privbtf int firstAdjustfdIndfx = MAX;
    privbtf int lbstAdjustfdIndfx = MIN;
    privbtf boolfbn isAdjusting = fblsf;

    privbtf int firstCibngfdIndfx = MAX;
    privbtf int lbstCibngfdIndfx = MIN;

    privbtf BitSft vbluf = nfw BitSft(32);
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    protfdtfd boolfbn lfbdAndiorNotifidbtionEnbblfd = truf;

    /** {@inifritDod} */
    publid int gftMinSflfdtionIndfx() { rfturn isSflfdtionEmpty() ? -1 : minIndfx; }

    /** {@inifritDod} */
    publid int gftMbxSflfdtionIndfx() { rfturn mbxIndfx; }

    /** {@inifritDod} */
    publid boolfbn gftVblufIsAdjusting() { rfturn isAdjusting; }

    /** {@inifritDod} */
    publid int gftSflfdtionModf() { rfturn sflfdtionModf; }

    /**
     * {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid void sftSflfdtionModf(int sflfdtionModf) {
        switdi (sflfdtionModf) {
        dbsf SINGLE_SELECTION:
        dbsf SINGLE_INTERVAL_SELECTION:
        dbsf MULTIPLE_INTERVAL_SELECTION:
            tiis.sflfdtionModf = sflfdtionModf;
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("invblid sflfdtionModf");
        }
    }

    /** {@inifritDod} */
    publid boolfbn isSflfdtfdIndfx(int indfx) {
        rfturn ((indfx < minIndfx) || (indfx > mbxIndfx)) ? fblsf : vbluf.gft(indfx);
    }

    /** {@inifritDod} */
    publid boolfbn isSflfdtionEmpty() {
        rfturn (minIndfx > mbxIndfx);
    }

    /** {@inifritDod} */
    publid void bddListSflfdtionListfnfr(ListSflfdtionListfnfr l) {
        listfnfrList.bdd(ListSflfdtionListfnfr.dlbss, l);
    }

    /** {@inifritDod} */
    publid void rfmovfListSflfdtionListfnfr(ListSflfdtionListfnfr l) {
        listfnfrList.rfmovf(ListSflfdtionListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif list sflfdtion listfnfrs
     * rfgistfrfd on tiis <dodf>DffbultListSflfdtionModfl</dodf>.
     *
     * @rfturn bll of tiis modfl's <dodf>ListSflfdtionListfnfr</dodf>s
     *         or bn fmpty
     *         brrby if no list sflfdtion listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddListSflfdtionListfnfr
     * @sff #rfmovfListSflfdtionListfnfr
     *
     * @sindf 1.4
     */
    publid ListSflfdtionListfnfr[] gftListSflfdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(ListSflfdtionListfnfr.dlbss);
    }

    /**
     * Notififs listfnfrs tibt wf ibvf fndfd b sfrifs of bdjustmfnts.
     */
    protfdtfd void firfVblufCibngfd(boolfbn isAdjusting) {
        if (lbstCibngfdIndfx == MIN) {
            rfturn;
        }
        /* Cibngf tif vblufs bfforf sfnding tif fvfnt to tif
         * listfnfrs in dbsf tif fvfnt dbusfs b listfnfr to mbkf
         * bnotifr dibngf to tif sflfdtion.
         */
        int oldFirstCibngfdIndfx = firstCibngfdIndfx;
        int oldLbstCibngfdIndfx = lbstCibngfdIndfx;
        firstCibngfdIndfx = MAX;
        lbstCibngfdIndfx = MIN;
        firfVblufCibngfd(oldFirstCibngfdIndfx, oldLbstCibngfdIndfx, isAdjusting);
    }


    /**
     * Notififs <dodf>ListSflfdtionListfnfrs</dodf> tibt tif vbluf
     * of tif sflfdtion, in tif dlosfd intfrvbl <dodf>firstIndfx</dodf>,
     * <dodf>lbstIndfx</dodf>, ibs dibngfd.
     *
     * @pbrbm firstIndfx tif first indfx in tif intfrvbl
     * @pbrbm lbstIndfx tif lbst indfx in tif intfrvbl
     */
    protfdtfd void firfVblufCibngfd(int firstIndfx, int lbstIndfx) {
        firfVblufCibngfd(firstIndfx, lbstIndfx, gftVblufIsAdjusting());
    }

    /**
     * @pbrbm firstIndfx tif first indfx in tif intfrvbl
     * @pbrbm lbstIndfx tif lbst indfx in tif intfrvbl
     * @pbrbm isAdjusting truf if tiis is tif finbl dibngf in b sfrifs of
     *          bdjustmfnts
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfVblufCibngfd(int firstIndfx, int lbstIndfx, boolfbn isAdjusting)
    {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        ListSflfdtionEvfnt f = null;

        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i] == ListSflfdtionListfnfr.dlbss) {
                if (f == null) {
                    f = nfw ListSflfdtionEvfnt(tiis, firstIndfx, lbstIndfx, isAdjusting);
                }
                ((ListSflfdtionListfnfr)listfnfrs[i+1]).vblufCibngfd(f);
            }
        }
    }

    privbtf void firfVblufCibngfd() {
        if (lbstAdjustfdIndfx == MIN) {
            rfturn;
        }
        /* If gftVblufAdjusting() is truf, (fg. during b drbg opfrfrbtion)
         * rfdord tif bounds of tif dibngfs so tibt, wifn tif drbg finisifs (bnd
         * sftVblufAdjusting(fblsf) is dbllfd) wf dbn post b singlf fvfnt
         * witi bounds dovfring bll of tifsf individubl bdjustmfnts.
         */
        if (gftVblufIsAdjusting()) {
            firstCibngfdIndfx = Mbti.min(firstCibngfdIndfx, firstAdjustfdIndfx);
            lbstCibngfdIndfx = Mbti.mbx(lbstCibngfdIndfx, lbstAdjustfdIndfx);
        }
        /* Cibngf tif vblufs bfforf sfnding tif fvfnt to tif
         * listfnfrs in dbsf tif fvfnt dbusfs b listfnfr to mbkf
         * bnotifr dibngf to tif sflfdtion.
         */
        int oldFirstAdjustfdIndfx = firstAdjustfdIndfx;
        int oldLbstAdjustfdIndfx = lbstAdjustfdIndfx;
        firstAdjustfdIndfx = MAX;
        lbstAdjustfdIndfx = MIN;

        firfVblufCibngfd(oldFirstAdjustfdIndfx, oldLbstAdjustfdIndfx);
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd bs
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis modfl.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * brf rfgistfrfd using tif <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b <dodf>DffbultListSflfdtionModfl</dodf>
     * instbndf <dodf>m</dodf>
     * for its list sflfdtion listfnfrs
     * witi tif following dodf:
     *
     * <prf>ListSflfdtionListfnfr[] lsls = (ListSflfdtionListfnfr[])(m.gftListfnfrs(ListSflfdtionListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist,
     * tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm <T> tif typf of {@dodf EvfntListfnfr} dlbss bfing rfqufstfd
     * @pbrbm listfnfrTypf  tif typf of listfnfrs rfqufstfd;
     *          tiis pbrbmftfr siould spfdify bn intfrfbdf
     *          tibt dfsdfnds from <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s
     *          on tiis modfl,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf> dofsn't
     *          spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftListSflfdtionListfnfrs
     *
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        rfturn listfnfrList.gftListfnfrs(listfnfrTypf);
    }

    // Updbtfs first bnd lbst dibngf indidfs
    privbtf void mbrkAsDirty(int r) {
        if (r == -1) {
            rfturn;
        }

        firstAdjustfdIndfx = Mbti.min(firstAdjustfdIndfx, r);
        lbstAdjustfdIndfx =  Mbti.mbx(lbstAdjustfdIndfx, r);
    }

    // Sfts tif stbtf bt tiis indfx bnd updbtf bll rflfvbnt stbtf.
    privbtf void sft(int r) {
        if (vbluf.gft(r)) {
            rfturn;
        }
        vbluf.sft(r);
        mbrkAsDirty(r);

        // Updbtf minimum bnd mbximum indidfs
        minIndfx = Mbti.min(minIndfx, r);
        mbxIndfx = Mbti.mbx(mbxIndfx, r);
    }

    // Clfbrs tif stbtf bt tiis indfx bnd updbtf bll rflfvbnt stbtf.
    privbtf void dlfbr(int r) {
        if (!vbluf.gft(r)) {
            rfturn;
        }
        vbluf.dlfbr(r);
        mbrkAsDirty(r);

        // Updbtf minimum bnd mbximum indidfs
        /*
           If (r > minIndfx) tif minimum ibs not dibngfd.
           Tif dbsf (r < minIndfx) is not possiblf bfdbusf r'ti vbluf wbs sft.
           Wf only nffd to difdk for tif dbsf wifn lowfst fntry ibs bffn dlfbrfd,
           bnd in tiis dbsf wf nffd to sfbrdi for tif first vbluf sft bbovf it.
        */
        if (r == minIndfx) {
            for(minIndfx = minIndfx + 1; minIndfx <= mbxIndfx; minIndfx++) {
                if (vbluf.gft(minIndfx)) {
                    brfbk;
                }
            }
        }
        /*
           If (r < mbxIndfx) tif mbximum ibs not dibngfd.
           Tif dbsf (r > mbxIndfx) is not possiblf bfdbusf r'ti vbluf wbs sft.
           Wf only nffd to difdk for tif dbsf wifn iigifst fntry ibs bffn dlfbrfd,
           bnd in tiis dbsf wf nffd to sfbrdi for tif first vbluf sft bflow it.
        */
        if (r == mbxIndfx) {
            for(mbxIndfx = mbxIndfx - 1; minIndfx <= mbxIndfx; mbxIndfx--) {
                if (vbluf.gft(mbxIndfx)) {
                    brfbk;
                }
            }
        }
        /* Pfrformbndf notf: Tiis mftiod is dbllfd from insidf b loop in
           dibngfSflfdtion() but wf will only itfrbtf in tif loops
           bbovf on tif bbsis of onf itfrbtion pfr dfsflfdtfd dfll - in totbl.
           If. tif nfxt timf tiis mftiod is dbllfd tif work of tif prfvious
           dfsflfdtion will not bf rfpfbtfd.

           Wf blso don't nffd to worry bbout tif dbsf wifn tif min bnd mbx
           vblufs brf in tifir unbssignfd stbtfs. Tiis dbnnot ibppfn bfdbusf
           tiis mftiod's initibl difdk fnsurfs tibt tif sflfdtion wbs not fmpty
           bnd tifrfforf tibt tif minIndfx bnd mbxIndfx ibd 'rfbl' vblufs.

           If wf ibvf dlfbrfd tif wiolf sflfdtion, sft tif minIndfx bnd mbxIndfx
           to tifir dbnnonidbl vblufs so tibt tif nfxt sft dommbnd blwbys works
           just by using Mbti.min bnd Mbti.mbx.
        */
        if (isSflfdtionEmpty()) {
            minIndfx = MAX;
            mbxIndfx = MIN;
        }
    }

    /**
     * Sfts tif vbluf of tif lfbdAndiorNotifidbtionEnbblfd flbg.
     *
     * @pbrbm flbg boolfbn vbluf for {@dodf lfbdAndiorNotifidbtionEnbblfd}
     * @sff             #isLfbdAndiorNotifidbtionEnbblfd()
     */
    publid void sftLfbdAndiorNotifidbtionEnbblfd(boolfbn flbg) {
        lfbdAndiorNotifidbtionEnbblfd = flbg;
    }

    /**
     * Rfturns tif vbluf of tif <dodf>lfbdAndiorNotifidbtionEnbblfd</dodf> flbg.
     * Wifn <dodf>lfbdAndiorNotifidbtionEnbblfd</dodf> is truf tif modfl
     * gfnfrbtfs notifidbtion fvfnts witi bounds tibt dovfr bll tif dibngfs to
     * tif sflfdtion plus tif dibngfs to tif lfbd bnd bndior indidfs.
     * Sftting tif flbg to fblsf dbusfs b nbrrowing of tif fvfnt's bounds to
     * indludf only tif flfmfnts tibt ibvf bffn sflfdtfd or dfsflfdtfd sindf
     * tif lbst dibngf. Eitifr wby, tif modfl dontinufs to mbintbin tif lfbd
     * bnd bndior vbribblfs intfrnblly. Tif dffbult is truf.
     * <p>
     * Notf: It is possiblf for tif lfbd or bndior to bf dibngfd witiout b
     * dibngf to tif sflfdtion. Notifidbtion of tifsf dibngfs is oftfn
     * importbnt, sudi bs wifn tif nfw lfbd or bndior nffds to bf updbtfd in
     * tif vifw. Tifrfforf, dbution is urgfd wifn dibnging tif dffbult vbluf.
     *
     * @rfturn  tif vbluf of tif <dodf>lfbdAndiorNotifidbtionEnbblfd</dodf> flbg
     * @sff             #sftLfbdAndiorNotifidbtionEnbblfd(boolfbn)
     */
    publid boolfbn isLfbdAndiorNotifidbtionEnbblfd() {
        rfturn lfbdAndiorNotifidbtionEnbblfd;
    }

    privbtf void updbtfLfbdAndiorIndidfs(int bndiorIndfx, int lfbdIndfx) {
        if (lfbdAndiorNotifidbtionEnbblfd) {
            if (tiis.bndiorIndfx != bndiorIndfx) {
                mbrkAsDirty(tiis.bndiorIndfx);
                mbrkAsDirty(bndiorIndfx);
            }

            if (tiis.lfbdIndfx != lfbdIndfx) {
                mbrkAsDirty(tiis.lfbdIndfx);
                mbrkAsDirty(lfbdIndfx);
            }
        }
        tiis.bndiorIndfx = bndiorIndfx;
        tiis.lfbdIndfx = lfbdIndfx;
    }

    privbtf boolfbn dontbins(int b, int b, int i) {
        rfturn (i >= b) && (i <= b);
    }

    privbtf void dibngfSflfdtion(int dlfbrMin, int dlfbrMbx,
                                 int sftMin, int sftMbx, boolfbn dlfbrFirst) {
        for(int i = Mbti.min(sftMin, dlfbrMin); i <= Mbti.mbx(sftMbx, dlfbrMbx); i++) {

            boolfbn siouldClfbr = dontbins(dlfbrMin, dlfbrMbx, i);
            boolfbn siouldSft = dontbins(sftMin, sftMbx, i);

            if (siouldSft && siouldClfbr) {
                if (dlfbrFirst) {
                    siouldClfbr = fblsf;
                }
                flsf {
                    siouldSft = fblsf;
                }
            }

            if (siouldSft) {
                sft(i);
            }
            if (siouldClfbr) {
                dlfbr(i);
            }
        }
        firfVblufCibngfd();
    }

   /**
    * Cibngf tif sflfdtion witi tif ffffdt of first dlfbring tif vblufs
    * in tif indlusivf rbngf [dlfbrMin, dlfbrMbx] tifn sftting tif vblufs
    * in tif indlusivf rbngf [sftMin, sftMbx]. Do tiis in onf pbss so
    * tibt no vblufs brf dlfbrfd if tify would lbtfr bf sft.
    */
    privbtf void dibngfSflfdtion(int dlfbrMin, int dlfbrMbx, int sftMin, int sftMbx) {
        dibngfSflfdtion(dlfbrMin, dlfbrMbx, sftMin, sftMbx, truf);
    }

    /** {@inifritDod} */
    publid void dlfbrSflfdtion() {
        rfmovfSflfdtionIntfrvblImpl(minIndfx, mbxIndfx, fblsf);
    }

    /**
     * Cibngfs tif sflfdtion to bf bftwffn {@dodf indfx0} bnd {@dodf indfx1}
     * indlusivf. {@dodf indfx0} dofsn't ibvf to bf lfss tibn or fqubl to
     * {@dodf indfx1}.
     * <p>
     * In {@dodf SINGLE_SELECTION} sflfdtion modf, only tif sfdond indfx
     * is usfd.
     * <p>
     * If tiis rfprfsfnts b dibngf to tif durrfnt sflfdtion, tifn fbdi
     * {@dodf ListSflfdtionListfnfr} is notififd of tif dibngf.
     * <p>
     * If fitifr indfx is {@dodf -1}, tiis mftiod dofs notiing bnd rfturns
     * witiout fxdfption. Otifrwisf, if fitifr indfx is lfss tibn {@dodf -1},
     * bn {@dodf IndfxOutOfBoundsExdfption} is tirown.
     *
     * @pbrbm indfx0 onf fnd of tif intfrvbl.
     * @pbrbm indfx1 otifr fnd of tif intfrvbl
     * @tirows IndfxOutOfBoundsExdfption if fitifr indfx is lfss tibn {@dodf -1}
     *         (bnd nfitifr indfx is {@dodf -1})
     * @sff #bddListSflfdtionListfnfr
     */
    publid void sftSflfdtionIntfrvbl(int indfx0, int indfx1) {
        if (indfx0 == -1 || indfx1 == -1) {
            rfturn;
        }

        if (gftSflfdtionModf() == SINGLE_SELECTION) {
            indfx0 = indfx1;
        }

        updbtfLfbdAndiorIndidfs(indfx0, indfx1);

        int dlfbrMin = minIndfx;
        int dlfbrMbx = mbxIndfx;
        int sftMin = Mbti.min(indfx0, indfx1);
        int sftMbx = Mbti.mbx(indfx0, indfx1);
        dibngfSflfdtion(dlfbrMin, dlfbrMbx, sftMin, sftMbx);
    }

    /**
     * Cibngfs tif sflfdtion to bf tif sft union of tif durrfnt sflfdtion
     * bnd tif indidfs bftwffn {@dodf indfx0} bnd {@dodf indfx1} indlusivf.
     * <p>
     * In {@dodf SINGLE_SELECTION} sflfdtion modf, tiis is fquivblfnt
     * to dblling {@dodf sftSflfdtionIntfrvbl}, bnd only tif sfdond indfx
     * is usfd. In {@dodf SINGLE_INTERVAL_SELECTION} sflfdtion modf, tiis
     * mftiod bfibvfs likf {@dodf sftSflfdtionIntfrvbl}, unlfss tif givfn
     * intfrvbl is immfdibtfly bdjbdfnt to or ovfrlbps tif fxisting sflfdtion,
     * bnd dbn tifrfforf bf usfd to grow it.
     * <p>
     * If tiis rfprfsfnts b dibngf to tif durrfnt sflfdtion, tifn fbdi
     * {@dodf ListSflfdtionListfnfr} is notififd of tif dibngf. Notf tibt
     * {@dodf indfx0} dofsn't ibvf to bf lfss tibn or fqubl to {@dodf indfx1}.
     * <p>
     * If fitifr indfx is {@dodf -1}, tiis mftiod dofs notiing bnd rfturns
     * witiout fxdfption. Otifrwisf, if fitifr indfx is lfss tibn {@dodf -1},
     * bn {@dodf IndfxOutOfBoundsExdfption} is tirown.
     *
     * @pbrbm indfx0 onf fnd of tif intfrvbl.
     * @pbrbm indfx1 otifr fnd of tif intfrvbl
     * @tirows IndfxOutOfBoundsExdfption if fitifr indfx is lfss tibn {@dodf -1}
     *         (bnd nfitifr indfx is {@dodf -1})
     * @sff #bddListSflfdtionListfnfr
     * @sff #sftSflfdtionIntfrvbl
     */
    publid void bddSflfdtionIntfrvbl(int indfx0, int indfx1)
    {
        if (indfx0 == -1 || indfx1 == -1) {
            rfturn;
        }

        // If wf only bllow b singlf sflfdtion, dibnnfl tirougi
        // sftSflfdtionIntfrvbl() to fnfordf tif rulf.
        if (gftSflfdtionModf() == SINGLE_SELECTION) {
            sftSflfdtionIntfrvbl(indfx0, indfx1);
            rfturn;
        }

        updbtfLfbdAndiorIndidfs(indfx0, indfx1);

        int dlfbrMin = MAX;
        int dlfbrMbx = MIN;
        int sftMin = Mbti.min(indfx0, indfx1);
        int sftMbx = Mbti.mbx(indfx0, indfx1);

        // If wf only bllow b singlf intfrvbl bnd tiis would rfsult
        // in multiplf intfrvbls, tifn sft tif sflfdtion to bf just
        // tif nfw rbngf.
        if (gftSflfdtionModf() == SINGLE_INTERVAL_SELECTION &&
                (sftMbx < minIndfx - 1 || sftMin > mbxIndfx + 1)) {

            sftSflfdtionIntfrvbl(indfx0, indfx1);
            rfturn;
        }

        dibngfSflfdtion(dlfbrMin, dlfbrMbx, sftMin, sftMbx);
    }


    /**
     * Cibngfs tif sflfdtion to bf tif sft difffrfndf of tif durrfnt sflfdtion
     * bnd tif indidfs bftwffn {@dodf indfx0} bnd {@dodf indfx1} indlusivf.
     * {@dodf indfx0} dofsn't ibvf to bf lfss tibn or fqubl to {@dodf indfx1}.
     * <p>
     * In {@dodf SINGLE_INTERVAL_SELECTION} sflfdtion modf, if tif rfmovbl
     * would produdf two disjoint sflfdtions, tif rfmovbl is fxtfndfd tirougi
     * tif grfbtfr fnd of tif sflfdtion. For fxbmplf, if tif sflfdtion is
     * {@dodf 0-10} bnd you supply indidfs {@dodf 5,6} (in bny ordfr) tif
     * rfsulting sflfdtion is {@dodf 0-4}.
     * <p>
     * If tiis rfprfsfnts b dibngf to tif durrfnt sflfdtion, tifn fbdi
     * {@dodf ListSflfdtionListfnfr} is notififd of tif dibngf.
     * <p>
     * If fitifr indfx is {@dodf -1}, tiis mftiod dofs notiing bnd rfturns
     * witiout fxdfption. Otifrwisf, if fitifr indfx is lfss tibn {@dodf -1},
     * bn {@dodf IndfxOutOfBoundsExdfption} is tirown.
     *
     * @pbrbm indfx0 onf fnd of tif intfrvbl
     * @pbrbm indfx1 otifr fnd of tif intfrvbl
     * @tirows IndfxOutOfBoundsExdfption if fitifr indfx is lfss tibn {@dodf -1}
     *         (bnd nfitifr indfx is {@dodf -1})
     * @sff #bddListSflfdtionListfnfr
     */
    publid void rfmovfSflfdtionIntfrvbl(int indfx0, int indfx1)
    {
        rfmovfSflfdtionIntfrvblImpl(indfx0, indfx1, truf);
    }

    // privbtf implfmfntbtion bllowing tif sflfdtion intfrvbl
    // to bf rfmovfd witiout bfffdting tif lfbd bnd bndior
    privbtf void rfmovfSflfdtionIntfrvblImpl(int indfx0, int indfx1,
                                             boolfbn dibngfLfbdAndior) {

        if (indfx0 == -1 || indfx1 == -1) {
            rfturn;
        }

        if (dibngfLfbdAndior) {
            updbtfLfbdAndiorIndidfs(indfx0, indfx1);
        }

        int dlfbrMin = Mbti.min(indfx0, indfx1);
        int dlfbrMbx = Mbti.mbx(indfx0, indfx1);
        int sftMin = MAX;
        int sftMbx = MIN;

        // If tif rfmovbl would produdf to two disjoint sflfdtions in b modf
        // tibt only bllows onf, fxtfnd tif rfmovbl to tif fnd of tif sflfdtion.
        if (gftSflfdtionModf() != MULTIPLE_INTERVAL_SELECTION &&
               dlfbrMin > minIndfx && dlfbrMbx < mbxIndfx) {
            dlfbrMbx = mbxIndfx;
        }

        dibngfSflfdtion(dlfbrMin, dlfbrMbx, sftMin, sftMbx);
    }

    privbtf void sftStbtf(int indfx, boolfbn stbtf) {
        if (stbtf) {
            sft(indfx);
        }
        flsf {
            dlfbr(indfx);
        }
    }

    /**
     * Insfrt lfngti indidfs bfginning bfforf/bftfr indfx. If tif vbluf
     * bt indfx is itsflf sflfdtfd bnd tif sflfdtion modf is not
     * SINGLE_SELECTION, sft bll of tif nfwly insfrtfd itfms bs sflfdtfd.
     * Otifrwisf lfbvf tifm unsflfdtfd. Tiis mftiod is typidblly
     * dbllfd to synd tif sflfdtion modfl witi b dorrfsponding dibngf
     * in tif dbtb modfl.
     */
    publid void insfrtIndfxIntfrvbl(int indfx, int lfngti, boolfbn bfforf)
    {
        /* Tif first nfw indfx will bppfbr bt insMinIndfx bnd tif lbst
         * onf will bppfbr bt insMbxIndfx
         */
        int insMinIndfx = (bfforf) ? indfx : indfx + 1;
        int insMbxIndfx = (insMinIndfx + lfngti) - 1;

        /* Rigit siift tif fntirf bitsft by lfngti, bfginning witi
         * indfx-1 if bfforf is truf, indfx+1 if it's fblsf (i.f. witi
         * insMinIndfx).
         */
        for(int i = mbxIndfx; i >= insMinIndfx; i--) {
            sftStbtf(i + lfngti, vbluf.gft(i));
        }

        /* Initiblizf tif nfwly insfrtfd indidfs.
         */
        boolfbn sftInsfrtfdVblufs = ((gftSflfdtionModf() == SINGLE_SELECTION) ?
                                        fblsf : vbluf.gft(indfx));
        for(int i = insMinIndfx; i <= insMbxIndfx; i++) {
            sftStbtf(i, sftInsfrtfdVblufs);
        }

        int lfbdIndfx = tiis.lfbdIndfx;
        if (lfbdIndfx > indfx || (bfforf && lfbdIndfx == indfx)) {
            lfbdIndfx = tiis.lfbdIndfx + lfngti;
        }
        int bndiorIndfx = tiis.bndiorIndfx;
        if (bndiorIndfx > indfx || (bfforf && bndiorIndfx == indfx)) {
            bndiorIndfx = tiis.bndiorIndfx + lfngti;
        }
        if (lfbdIndfx != tiis.lfbdIndfx || bndiorIndfx != tiis.bndiorIndfx) {
            updbtfLfbdAndiorIndidfs(bndiorIndfx, lfbdIndfx);
        }

        firfVblufCibngfd();
    }


    /**
     * Rfmovf tif indidfs in tif intfrvbl indfx0,indfx1 (indlusivf) from
     * tif sflfdtion modfl.  Tiis is typidblly dbllfd to synd tif sflfdtion
     * modfl widti b dorrfsponding dibngf in tif dbtb modfl.  Notf
     * tibt (bs blwbys) indfx0 nffd not bf &lt;= indfx1.
     */
    publid void rfmovfIndfxIntfrvbl(int indfx0, int indfx1)
    {
        int rmMinIndfx = Mbti.min(indfx0, indfx1);
        int rmMbxIndfx = Mbti.mbx(indfx0, indfx1);
        int gbpLfngti = (rmMbxIndfx - rmMinIndfx) + 1;

        /* Siift tif fntirf bitsft to tif lfft to dlosf tif indfx0, indfx1
         * gbp.
         */
        for(int i = rmMinIndfx; i <= mbxIndfx; i++) {
            sftStbtf(i, vbluf.gft(i + gbpLfngti));
        }

        int lfbdIndfx = tiis.lfbdIndfx;
        if (lfbdIndfx == 0 && rmMinIndfx == 0) {
            // do notiing
        } flsf if (lfbdIndfx > rmMbxIndfx) {
            lfbdIndfx = tiis.lfbdIndfx - gbpLfngti;
        } flsf if (lfbdIndfx >= rmMinIndfx) {
            lfbdIndfx = rmMinIndfx - 1;
        }

        int bndiorIndfx = tiis.bndiorIndfx;
        if (bndiorIndfx == 0 && rmMinIndfx == 0) {
            // do notiing
        } flsf if (bndiorIndfx > rmMbxIndfx) {
            bndiorIndfx = tiis.bndiorIndfx - gbpLfngti;
        } flsf if (bndiorIndfx >= rmMinIndfx) {
            bndiorIndfx = rmMinIndfx - 1;
        }

        if (lfbdIndfx != tiis.lfbdIndfx || bndiorIndfx != tiis.bndiorIndfx) {
            updbtfLfbdAndiorIndidfs(bndiorIndfx, lfbdIndfx);
        }

        firfVblufCibngfd();
    }


    /** {@inifritDod} */
    publid void sftVblufIsAdjusting(boolfbn isAdjusting) {
        if (isAdjusting != tiis.isAdjusting) {
            tiis.isAdjusting = isAdjusting;
            tiis.firfVblufCibngfd(isAdjusting);
        }
    }


    /**
     * Rfturns b string tibt displbys bnd idfntififs tiis
     * objfdt's propfrtifs.
     *
     * @rfturn b <dodf>String</dodf> rfprfsfntbtion of tiis objfdt
     */
    publid String toString() {
        String s =  ((gftVblufIsAdjusting()) ? "~" : "=") + vbluf.toString();
        rfturn gftClbss().gftNbmf() + " " + Intfgfr.toString(ibsiCodf()) + " " + s;
    }

    /**
     * Rfturns b dlonf of tiis sflfdtion modfl witi tif sbmf sflfdtion.
     * <dodf>listfnfrLists</dodf> brf not duplidbtfd.
     *
     * @fxdfption ClonfNotSupportfdExdfption if tif sflfdtion modfl dofs not
     *    boti (b) implfmfnt tif Clonfbblf intfrfbdf bnd (b) dffinf b
     *    <dodf>dlonf</dodf> mftiod.
     */
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        DffbultListSflfdtionModfl dlonf = (DffbultListSflfdtionModfl)supfr.dlonf();
        dlonf.vbluf = (BitSft)vbluf.dlonf();
        dlonf.listfnfrList = nfw EvfntListfnfrList();
        rfturn dlonf;
    }

    /** {@inifritDod} */
    @Trbnsifnt
    publid int gftAndiorSflfdtionIndfx() {
        rfturn bndiorIndfx;
    }

    /** {@inifritDod} */
    @Trbnsifnt
    publid int gftLfbdSflfdtionIndfx() {
        rfturn lfbdIndfx;
    }

    /**
     * Sft tif bndior sflfdtion indfx, lfbving bll sflfdtion vblufs undibngfd.
     * If lfbdAndiorNotifidbtionEnbblfd is truf, sfnd b notifidbtion dovfring
     * tif old bnd nfw bndior dflls.
     *
     * @sff #gftAndiorSflfdtionIndfx
     * @sff #sftLfbdSflfdtionIndfx
     */
    publid void sftAndiorSflfdtionIndfx(int bndiorIndfx) {
        updbtfLfbdAndiorIndidfs(bndiorIndfx, tiis.lfbdIndfx);
        firfVblufCibngfd();
    }

    /**
     * Sft tif lfbd sflfdtion indfx, lfbving bll sflfdtion vblufs undibngfd.
     * If lfbdAndiorNotifidbtionEnbblfd is truf, sfnd b notifidbtion dovfring
     * tif old bnd nfw lfbd dflls.
     *
     * @pbrbm lfbdIndfx tif nfw lfbd sflfdtion indfx
     *
     * @sff #sftAndiorSflfdtionIndfx
     * @sff #sftLfbdSflfdtionIndfx
     * @sff #gftLfbdSflfdtionIndfx
     *
     * @sindf 1.5
     */
    publid void movfLfbdSflfdtionIndfx(int lfbdIndfx) {
        // disbllow b -1 lfbd unlfss tif bndior is blrfbdy -1
        if (lfbdIndfx == -1) {
            if (tiis.bndiorIndfx != -1) {
                rfturn;
            }

/* PENDING(sibnnoni) - Tif following difdk is nidf, to bf donsistfnt witi
                       sftLfbdSflfdtionIndfx. Howfvfr, it is not bbsolutfly
                       nfdfssbry: Onf dould work bround it by sftting tif bndior
                       to somftiing vblid, modifying tif lfbd, bnd tifn moving
                       tif bndior bbdk to -1. For tiis rfbson, tifrf's no sfnsf
                       in bdding it bt tiis timf, bs tibt would rfquirf
                       updbting tif spfd bnd offidiblly dommitting to it.

        // otifrwisf, don't do bnytiing if tif bndior is -1
        } flsf if (tiis.bndiorIndfx == -1) {
            rfturn;
*/

        }

        updbtfLfbdAndiorIndidfs(tiis.bndiorIndfx, lfbdIndfx);
        firfVblufCibngfd();
    }

    /**
     * Sfts tif lfbd sflfdtion indfx, fnsuring tibt vblufs bftwffn tif
     * bndior bnd tif nfw lfbd brf fitifr bll sflfdtfd or bll dfsflfdtfd.
     * If tif vbluf bt tif bndior indfx is sflfdtfd, first dlfbr bll tif
     * vblufs in tif rbngf [bndior, oldLfbdIndfx], tifn sflfdt bll tif vblufs
     * vblufs in tif rbngf [bndior, nfwLfbdIndfx], wifrf oldLfbdIndfx is tif old
     * lfbdIndfx bnd nfwLfbdIndfx is tif nfw onf.
     * <p>
     * If tif vbluf bt tif bndior indfx is not sflfdtfd, do tif sbmf tiing in
     * rfvfrsf sflfdting vblufs in tif old rbngf bnd dfsflfdting vblufs in tif
     * nfw onf.
     * <p>
     * Gfnfrbtf b singlf fvfnt for tiis dibngf bnd notify bll listfnfrs.
     * For tif purposfs of gfnfrbting minimbl bounds in tiis fvfnt, do tif
     * opfrbtion in b singlf pbss; tibt wby tif first bnd lbst indfx insidf tif
     * ListSflfdtionEvfnt tibt is brobddbst will rfffr to dflls tibt bdtublly
     * dibngfd vbluf bfdbusf of tiis mftiod. If, instfbd, tiis opfrbtion wfrf
     * donf in two stfps tif ffffdt on tif sflfdtion stbtf would bf tif sbmf
     * but two fvfnts would bf gfnfrbtfd bnd tif bounds bround tif dibngfd
     * vblufs would bf widfr, indluding dflls tibt ibd bffn first dlfbrfd only
     * to lbtfr bf sft.
     * <p>
     * Tiis mftiod dbn bf usfd in tif <dodf>mousfDrbggfd</dodf> mftiod
     * of b UI dlbss to fxtfnd b sflfdtion.
     *
     * @sff #gftLfbdSflfdtionIndfx
     * @sff #sftAndiorSflfdtionIndfx
     */
    publid void sftLfbdSflfdtionIndfx(int lfbdIndfx) {
        int bndiorIndfx = tiis.bndiorIndfx;

        // only bllow b -1 lfbd if tif bndior is blrfbdy -1
        if (lfbdIndfx == -1) {
            if (bndiorIndfx == -1) {
                updbtfLfbdAndiorIndidfs(bndiorIndfx, lfbdIndfx);
                firfVblufCibngfd();
            }

            rfturn;
        // otifrwisf, don't do bnytiing if tif bndior is -1
        } flsf if (bndiorIndfx == -1) {
            rfturn;
        }

        if (tiis.lfbdIndfx == -1) {
            tiis.lfbdIndfx = lfbdIndfx;
        }

        boolfbn siouldSflfdt = vbluf.gft(tiis.bndiorIndfx);

        if (gftSflfdtionModf() == SINGLE_SELECTION) {
            bndiorIndfx = lfbdIndfx;
            siouldSflfdt = truf;
        }

        int oldMin = Mbti.min(tiis.bndiorIndfx, tiis.lfbdIndfx);
        int oldMbx = Mbti.mbx(tiis.bndiorIndfx, tiis.lfbdIndfx);
        int nfwMin = Mbti.min(bndiorIndfx, lfbdIndfx);
        int nfwMbx = Mbti.mbx(bndiorIndfx, lfbdIndfx);

        updbtfLfbdAndiorIndidfs(bndiorIndfx, lfbdIndfx);

        if (siouldSflfdt) {
            dibngfSflfdtion(oldMin, oldMbx, nfwMin, nfwMbx);
        }
        flsf {
            dibngfSflfdtion(nfwMin, nfwMbx, oldMin, oldMbx, fblsf);
        }
    }
}
