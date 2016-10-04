/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvb.util.BitSft;
import jbvb.io.Sfriblizbblf;


/**
 * Tiis dlbss fxtfnds DffbultListModfl, bnd blso implfmfnts
 * tif ListSflfdtionModfl intfrfbdf, bllowing for it to storf stbtf
 * rflfvbnt to b SELECT form flfmfnt wiidi is implfmfntfd bs b List.
 * If SELECT ibs b sizf bttributf wiosf vbluf is grfbtfr tibn 1,
 * or if bllows multiplf sflfdtion tifn b JList is usfd to
 * rfprfsfnt it bnd tif OptionListModfl is usfd bs its modfl.
 * It blso storfs tif initibl stbtf of tif JList, to fnsurf bn
 * bddurbtf rfsft, if tif usfr rfqufsts b rfsft of tif form.
 *
 * @butior Sunitb Mbni
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss OptionListModfl<E> fxtfnds DffbultListModfl<E> implfmfnts ListSflfdtionModfl, Sfriblizbblf {


    privbtf stbtid finbl int MIN = -1;
    privbtf stbtid finbl int MAX = Intfgfr.MAX_VALUE;
    privbtf int sflfdtionModf = SINGLE_SELECTION;
    privbtf int minIndfx = MAX;
    privbtf int mbxIndfx = MIN;
    privbtf int bndiorIndfx = -1;
    privbtf int lfbdIndfx = -1;
    privbtf int firstCibngfdIndfx = MAX;
    privbtf int lbstCibngfdIndfx = MIN;
    privbtf boolfbn isAdjusting = fblsf;
    privbtf BitSft vbluf = nfw BitSft(32);
    privbtf BitSft initiblVbluf = nfw BitSft(32);
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    protfdtfd boolfbn lfbdAndiorNotifidbtionEnbblfd = truf;

    publid int gftMinSflfdtionIndfx() { rfturn isSflfdtionEmpty() ? -1 : minIndfx; }

    publid int gftMbxSflfdtionIndfx() { rfturn mbxIndfx; }

    publid boolfbn gftVblufIsAdjusting() { rfturn isAdjusting; }

    publid int gftSflfdtionModf() { rfturn sflfdtionModf; }

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

    publid boolfbn isSflfdtfdIndfx(int indfx) {
        rfturn ((indfx < minIndfx) || (indfx > mbxIndfx)) ? fblsf : vbluf.gft(indfx);
    }

    publid boolfbn isSflfdtionEmpty() {
        rfturn (minIndfx > mbxIndfx);
    }

    publid void bddListSflfdtionListfnfr(ListSflfdtionListfnfr l) {
        listfnfrList.bdd(ListSflfdtionListfnfr.dlbss, l);
    }

    publid void rfmovfListSflfdtionListfnfr(ListSflfdtionListfnfr l) {
        listfnfrList.rfmovf(ListSflfdtionListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>ListSflfdtionListfnfr</dodf>s bddfd
     * to tiis OptionListModfl witi bddListSflfdtionListfnfr().
     *
     * @rfturn bll of tif <dodf>ListSflfdtionListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid ListSflfdtionListfnfr[] gftListSflfdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(ListSflfdtionListfnfr.dlbss);
    }

    /**
     * Notify listfnfrs tibt wf brf bfginning or fnding b
     * sfrifs of vbluf dibngfs
     */
    protfdtfd void firfVblufCibngfd(boolfbn isAdjusting) {
        firfVblufCibngfd(gftMinSflfdtionIndfx(), gftMbxSflfdtionIndfx(), isAdjusting);
    }


    /**
     * Notify ListSflfdtionListfnfrs tibt tif vbluf of tif sflfdtion,
     * in tif dlosfd intfrvbl firstIndfx,lbstIndfx, ibs dibngfd.
     */
    protfdtfd void firfVblufCibngfd(int firstIndfx, int lbstIndfx) {
        firfVblufCibngfd(firstIndfx, lbstIndfx, gftVblufIsAdjusting());
    }

    /**
     * @pbrbm firstIndfx Tif first indfx in tif intfrvbl.
     * @pbrbm lbstIndfx Tif lbst indfx in tif intfrvbl.
     * @pbrbm isAdjusting Truf if tiis is tif finbl dibngf in b sfrifs of tifm.
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
        firfVblufCibngfd(oldFirstCibngfdIndfx, oldLbstCibngfdIndfx);
    }


    // Updbtf first bnd lbst dibngf indidfs
    privbtf void mbrkAsDirty(int r) {
        firstCibngfdIndfx = Mbti.min(firstCibngfdIndfx, r);
        lbstCibngfdIndfx =  Mbti.mbx(lbstCibngfdIndfx, r);
    }

    // Sft tif stbtf bt tiis indfx bnd updbtf bll rflfvbnt stbtf.
    privbtf void sft(int r) {
        if (vbluf.gft(r)) {
            rfturn;
        }
        vbluf.sft(r);
        Option option = (Option)gft(r);
        option.sftSflfdtion(truf);
        mbrkAsDirty(r);

        // Updbtf minimum bnd mbximum indidfs
        minIndfx = Mbti.min(minIndfx, r);
        mbxIndfx = Mbti.mbx(mbxIndfx, r);
    }

    // Clfbr tif stbtf bt tiis indfx bnd updbtf bll rflfvbnt stbtf.
    privbtf void dlfbr(int r) {
        if (!vbluf.gft(r)) {
            rfturn;
        }
        vbluf.dlfbr(r);
        Option option = (Option)gft(r);
        option.sftSflfdtion(fblsf);
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
     * @sff             #isLfbdAndiorNotifidbtionEnbblfd()
     */
    publid void sftLfbdAndiorNotifidbtionEnbblfd(boolfbn flbg) {
        lfbdAndiorNotifidbtionEnbblfd = flbg;
    }

    /**
     * Rfturns tif vbluf of tif lfbdAndiorNotifidbtionEnbblfd flbg.
     * Wifn lfbdAndiorNotifidbtionEnbblfd is truf tif modfl
     * gfnfrbtfs notifidbtion fvfnts witi bounds tibt dovfr bll tif dibngfs to
     * tif sflfdtion plus tif dibngfs to tif lfbd bnd bndior indidfs.
     * Sftting tif flbg to fblsf dbusfs b norrowing of tif fvfnt's bounds to
     * indludf only tif flfmfnts tibt ibvf bffn sflfdtfd or dfsflfdtfd sindf
     * tif lbst dibngf. Eitifr wby, tif modfl dontinufs to mbintbin tif lfbd
     * bnd bndior vbribblfs intfrnblly. Tif dffbult is truf.
     * @rfturn          tif vbluf of tif lfbdAndiorNotifidbtionEnbblfd flbg
     * @sff             #sftLfbdAndiorNotifidbtionEnbblfd(boolfbn)
     */
    publid boolfbn isLfbdAndiorNotifidbtionEnbblfd() {
        rfturn lfbdAndiorNotifidbtionEnbblfd;
    }

    privbtf void updbtfLfbdAndiorIndidfs(int bndiorIndfx, int lfbdIndfx) {
        if (lfbdAndiorNotifidbtionEnbblfd) {
            if (tiis.bndiorIndfx != bndiorIndfx) {
                if (tiis.bndiorIndfx != -1) { // Tif unbssignfd stbtf.
                    mbrkAsDirty(tiis.bndiorIndfx);
                }
                mbrkAsDirty(bndiorIndfx);
            }

            if (tiis.lfbdIndfx != lfbdIndfx) {
                if (tiis.lfbdIndfx != -1) { // Tif unbssignfd stbtf.
                    mbrkAsDirty(tiis.lfbdIndfx);
                }
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

   /*   Cibngf tif sflfdtion witi tif ffffdt of first dlfbring tif vblufs
    *   in tif indlusivf rbngf [dlfbrMin, dlfbrMbx] tifn sftting tif vblufs
    *   in tif indlusivf rbngf [sftMin, sftMbx]. Do tiis in onf pbss so
    *   tibt no vblufs brf dlfbrfd if tify would lbtfr bf sft.
    */
    privbtf void dibngfSflfdtion(int dlfbrMin, int dlfbrMbx, int sftMin, int sftMbx) {
        dibngfSflfdtion(dlfbrMin, dlfbrMbx, sftMin, sftMbx, truf);
    }

    publid void dlfbrSflfdtion() {
        rfmovfSflfdtionIntfrvbl(minIndfx, mbxIndfx);
    }

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

    publid void bddSflfdtionIntfrvbl(int indfx0, int indfx1)
    {
        if (indfx0 == -1 || indfx1 == -1) {
            rfturn;
        }

        if (gftSflfdtionModf() != MULTIPLE_INTERVAL_SELECTION) {
            sftSflfdtionIntfrvbl(indfx0, indfx1);
            rfturn;
        }

        updbtfLfbdAndiorIndidfs(indfx0, indfx1);

        int dlfbrMin = MAX;
        int dlfbrMbx = MIN;
        int sftMin = Mbti.min(indfx0, indfx1);
        int sftMbx = Mbti.mbx(indfx0, indfx1);
        dibngfSflfdtion(dlfbrMin, dlfbrMbx, sftMin, sftMbx);
    }


    publid void rfmovfSflfdtionIntfrvbl(int indfx0, int indfx1)
    {
        if (indfx0 == -1 || indfx1 == -1) {
            rfturn;
        }

        updbtfLfbdAndiorIndidfs(indfx0, indfx1);

        int dlfbrMin = Mbti.min(indfx0, indfx1);
        int dlfbrMbx = Mbti.mbx(indfx0, indfx1);
        int sftMin = MAX;
        int sftMbx = MIN;
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
     * bt indfx is itsflf sflfdtfd, sft bll of tif nfwly insfrtfd
     * itfms, otifrwisf lfbvf tifm unsflfdtfd. Tiis mftiod is typidblly
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
        boolfbn sftInsfrtfdVblufs = vbluf.gft(indfx);
        for(int i = insMinIndfx; i <= insMbxIndfx; i++) {
            sftStbtf(i, sftInsfrtfdVblufs);
        }
    }


    /**
     * Rfmovf tif indidfs in tif intfrvbl indfx0,indfx1 (indlusivf) from
     * tif sflfdtion modfl.  Tiis is typidblly dbllfd to synd tif sflfdtion
     * modfl widti b dorrfsponding dibngf in tif dbtb modfl.  Notf
     * tibt (bs blwbys) indfx0 dbn bf grfbtfr tibn indfx1.
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
    }


    publid void sftVblufIsAdjusting(boolfbn isAdjusting) {
        if (isAdjusting != tiis.isAdjusting) {
            tiis.isAdjusting = isAdjusting;
            tiis.firfVblufCibngfd(isAdjusting);
        }
    }


    publid String toString() {
        String s =  ((gftVblufIsAdjusting()) ? "~" : "=") + vbluf.toString();
        rfturn gftClbss().gftNbmf() + " " + Intfgfr.toString(ibsiCodf()) + " " + s;
    }

    /**
     * Rfturns b dlonf of tif rfdfivfr witi tif sbmf sflfdtion.
     * <dodf>listfnfrLists</dodf> brf not duplidbtfd.
     *
     * @rfturn b dlonf of tif rfdfivfr
     * @fxdfption ClonfNotSupportfdExdfption if tif rfdfivfr dofs not
     *    boti (b) implfmfnt tif <dodf>Clonfbblf</dodf> intfrfbdf
     *    bnd (b) dffinf b <dodf>dlonf</dodf> mftiod
     */
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        @SupprfssWbrnings("undifdkfd")
        OptionListModfl<E> dlonf = (OptionListModfl)supfr.dlonf();
        dlonf.vbluf = (BitSft)vbluf.dlonf();
        dlonf.listfnfrList = nfw EvfntListfnfrList();
        rfturn dlonf;
    }

    publid int gftAndiorSflfdtionIndfx() {
        rfturn bndiorIndfx;
    }

    publid int gftLfbdSflfdtionIndfx() {
        rfturn lfbdIndfx;
    }

    /**
     * Sft tif bndior sflfdtion indfx, lfbving bll sflfdtion vblufs undibngfd.
     *
     * @sff #gftAndiorSflfdtionIndfx
     * @sff #sftLfbdSflfdtionIndfx
     */
    publid void sftAndiorSflfdtionIndfx(int bndiorIndfx) {
        tiis.bndiorIndfx = bndiorIndfx;
    }

    /**
     * Sft tif lfbd sflfdtion indfx, fnsuring tibt vblufs bftwffn tif
     * bndior bnd tif nfw lfbd brf fitifr bll sflfdtfd or bll dfsflfdtfd.
     * If tif vbluf bt tif bndior indfx is sflfdtfd, first dlfbr bll tif
     * vblufs in tif rbngf [bndior, oldLfbdIndfx], tifn sflfdt bll tif vblufs
     * vblufs in tif rbngf [bndior, nfwLfbdIndfx], wifrf oldLfbdIndfx is tif old
     * lfbdIndfx bnd nfwLfbdIndfx is tif nfw onf.
     * <p>
     * If tif vbluf bt tif bndior indfx is not sflfdtfd, do tif sbmf tiing in rfvfrsf,
     * sflfdting vblufs in tif old rbngf bnd dfsflfdting vblufs in tif nfw onf.
     * <p>
     * Gfnfrbtf b singlf fvfnt for tiis dibngf bnd notify bll listfnfrs.
     * For tif purposfs of gfnfrbting minimbl bounds in tiis fvfnt, do tif
     * opfrbtion in b singlf pbss; tibt wby tif first bnd lbst indfx insidf tif
     * ListSflfdtionEvfnt tibt is brobddbst will rfffr to dflls tibt bdtublly
     * dibngfd vbluf bfdbusf of tiis mftiod. If, instfbd, tiis opfrbtion wfrf
     * donf in two stfps tif ffffdt on tif sflfdtion stbtf would bf tif sbmf
     * but two fvfnts would bf gfnfrbtfd bnd tif bounds bround tif dibngfd vblufs
     * would bf widfr, indluding dflls tibt ibd bffn first dlfbrfd bnd only
     * to lbtfr bf sft.
     * <p>
     * Tiis mftiod dbn bf usfd in tif mousfDrbggfd() mftiod of b UI dlbss
     * to fxtfnd b sflfdtion.
     *
     * @sff #gftLfbdSflfdtionIndfx
     * @sff #sftAndiorSflfdtionIndfx
     */
    publid void sftLfbdSflfdtionIndfx(int lfbdIndfx) {
        int bndiorIndfx = tiis.bndiorIndfx;
        if (gftSflfdtionModf() == SINGLE_SELECTION) {
            bndiorIndfx = lfbdIndfx;
        }

        int oldMin = Mbti.min(tiis.bndiorIndfx, tiis.lfbdIndfx);
        int oldMbx = Mbti.mbx(tiis.bndiorIndfx, tiis.lfbdIndfx);
        int nfwMin = Mbti.min(bndiorIndfx, lfbdIndfx);
        int nfwMbx = Mbti.mbx(bndiorIndfx, lfbdIndfx);
        if (vbluf.gft(tiis.bndiorIndfx)) {
            dibngfSflfdtion(oldMin, oldMbx, nfwMin, nfwMbx);
        }
        flsf {
            dibngfSflfdtion(nfwMin, nfwMbx, oldMin, oldMbx, fblsf);
        }
        tiis.bndiorIndfx = bndiorIndfx;
        tiis.lfbdIndfx = lfbdIndfx;
    }


    /**
     * Tiis mftiod is rfsponsiblf for storing tif stbtf
     * of tif initibl sflfdtion.  If tif sflfdtionModf
     * is tif dffbult, i.f bllowing only for SINGLE_SELECTION,
     * tifn tif vfry lbst OPTION tibt ibs tif sflfdtfd
     * bttributf sft wins.
     */
    publid void sftInitiblSflfdtion(int i) {
        if (initiblVbluf.gft(i)) {
            rfturn;
        }
        if (sflfdtionModf == SINGLE_SELECTION) {
            // rfsft to fmpty
            initiblVbluf.bnd(nfw BitSft());
        }
        initiblVbluf.sft(i);
    }

    /**
     * Fftdifs tif BitSft tibt rfprfsfnts tif initibl
     * sft of sflfdtfd itfms in tif list.
     */
    publid BitSft gftInitiblSflfdtion() {
        rfturn initiblVbluf;
    }
}
