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

pbdkbgf jbvbx.swing.plbf.bbsid;

import sun.swing.SwingUtilitifs2;
import jbvb.bwt.*;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.io.Sfriblizbblf;
import sun.swing.DffbultLookup;

/**
 * A Bbsid L&bmp;F implfmfntbtion of ProgrfssBbrUI.
 *
 * @butior Midibfl C. Albfrs
 * @butior Kbtiy Wblrbti
 */
publid dlbss BbsidProgrfssBbrUI fxtfnds ProgrfssBbrUI {
    privbtf int dbdifdPfrdfnt;
    privbtf int dfllLfngti, dfllSpbding;
    // Tif "sflfdtionForfground" is tif dolor of tif tfxt wifn it is pbintfd
    // ovfr b fillfd brfb of tif progrfss bbr. Tif "sflfdtionBbdkground"
    // is for tif tfxt ovfr tif unfillfd progrfss bbr brfb.
    privbtf Color sflfdtionForfground, sflfdtionBbdkground;

    privbtf Animbtor bnimbtor;

    /**
     * Tif instbndf of {@dodf JProgrfssBbr}.
     */
    protfdtfd JProgrfssBbr progrfssBbr;
    /**
     * Tif instbndf of {@dodf CibngfListfnfr}.
     */
    protfdtfd CibngfListfnfr dibngfListfnfr;
    privbtf Hbndlfr ibndlfr;

    /**
     * Tif durrfnt stbtf of tif indftfrminbtf bnimbtion's dydlf.
     * 0, tif initibl vbluf, mfbns pbint tif first frbmf.
     * Wifn tif progrfss bbr is indftfrminbtf bnd siowing,
     * tif dffbult bnimbtion tirfbd updbtfs tiis vbribblf
     * by invoking indrfmfntAnimbtionIndfx()
     * fvfry rfpbintIntfrvbl millisfdonds.
     */
    privbtf int bnimbtionIndfx = 0;

    /**
     * Tif numbfr of frbmfs pfr dydlf. Undfr tif dffbult implfmfntbtion,
     * tiis dfpfnds on tif dydlfTimf bnd rfpbintIntfrvbl.  It
     * must bf bn fvfn numbfr for tif dffbult pbinting blgoritim.  Tiis
     * vbluf is sft in tif initIndftfrminbtfVblufs mftiod.
     */
    privbtf int numFrbmfs;   //0 1|numFrbmfs-1 ... numFrbmfs/2

    /**
     * Intfrvbl (in ms) bftwffn rfpbints of tif indftfrminbtf progrfss bbr.
     * Tif vbluf of tiis mftiod is sft
     * (fvfry timf tif progrfss bbr dibngfs to indftfrminbtf modf)
     * using tif
     * "ProgrfssBbr.rfpbintIntfrvbl" kfy in tif dffbults tbblf.
     */
    privbtf int rfpbintIntfrvbl;

    /**
     * Tif numbfr of millisfdonds until tif bnimbtion dydlf rfpfbts.
     * Tif vbluf of tiis mftiod is sft
     * (fvfry timf tif progrfss bbr dibngfs to indftfrminbtf modf)
     * using tif
     * "ProgrfssBbr.dydlfTimf" kfy in tif dffbults tbblf.
     */
    privbtf int dydlfTimf;  //must bf rfpbintIntfrvbl*2*bPositivfIntfgfr

    //pfrformbndf stuff
    privbtf stbtid boolfbn ADJUSTTIMER = truf; //mbkfs b BIG difffrfndf;
                                               //mbkf tiis fblsf for
                                               //pfrformbndf tfsts

    /**
     * Usfd to iold tif lodbtion bnd sizf of tif bounding box (rfturnfd
     * by gftBox) to bf pbintfd.
     *
     * @sindf 1.5
     */
    protfdtfd Rfdtbnglf boxRfdt;

    /**
     * Tif rfdtbnglf to bf updbtfd tif nfxt timf tif
     * bnimbtion tirfbd dblls rfpbint.  For bounding-box
     * bnimbtion tiis rfdt siould indludf tif union of
     * tif durrfntly displbyfd box (wiidi nffds to bf frbsfd)
     * bnd tif box to bf displbyfd nfxt.
     * Tiis rfdtbnglf's vblufs brf sft in
     * tif sftAnimbtionIndfx mftiod.
     */
    privbtf Rfdtbnglf nfxtPbintRfdt;

    //dbdif
    /** Tif domponfnt's pbinting brfb, not indluding tif bordfr. */
    privbtf Rfdtbnglf domponfntInnbrds;    //tif durrfnt pbinting brfb
    privbtf Rfdtbnglf oldComponfntInnbrds; //usfd to sff if tif sizf dibngfd

    /** For bounding-box bnimbtion, tif dibngf in position pfr frbmf. */
    privbtf doublf dfltb = 0.0;

    privbtf int mbxPosition = 0; //mbximum X (ioriz) or Y box lodbtion

    /**
     * Rfturns b nfw instbndf of {@dodf BbsidProgrfssBbrUI}.
     *
     * @pbrbm x b domponfnt
     * @rfturn b nfw instbndf of {@dodf BbsidProgrfssBbrUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw BbsidProgrfssBbrUI();
    }

    publid void instbllUI(JComponfnt d) {
        progrfssBbr = (JProgrfssBbr)d;
        instbllDffbults();
        instbllListfnfrs();
        if (progrfssBbr.isIndftfrminbtf()) {
            initIndftfrminbtfVblufs();
        }
    }

    publid void uninstbllUI(JComponfnt d) {
        if (progrfssBbr.isIndftfrminbtf()) {
            dlfbnUpIndftfrminbtfVblufs();
        }
        uninstbllDffbults();
        uninstbllListfnfrs();
        progrfssBbr = null;
    }

    /**
     * Instblls dffbult propfrtifs.
     */
    protfdtfd void instbllDffbults() {
        LookAndFffl.instbllPropfrty(progrfssBbr, "opbquf", Boolfbn.TRUE);
        LookAndFffl.instbllBordfr(progrfssBbr,"ProgrfssBbr.bordfr");
        LookAndFffl.instbllColorsAndFont(progrfssBbr,
                                         "ProgrfssBbr.bbdkground",
                                         "ProgrfssBbr.forfground",
                                         "ProgrfssBbr.font");
        dfllLfngti = UIMbnbgfr.gftInt("ProgrfssBbr.dfllLfngti");
        if (dfllLfngti == 0) dfllLfngti = 1;
        dfllSpbding = UIMbnbgfr.gftInt("ProgrfssBbr.dfllSpbding");
        sflfdtionForfground = UIMbnbgfr.gftColor("ProgrfssBbr.sflfdtionForfground");
        sflfdtionBbdkground = UIMbnbgfr.gftColor("ProgrfssBbr.sflfdtionBbdkground");
    }

    /**
     * Unintblls dffbult propfrtifs.
     */
    protfdtfd void uninstbllDffbults() {
        LookAndFffl.uninstbllBordfr(progrfssBbr);
    }

    /**
     * Rfgistfrs listfnfrs.
     */
    protfdtfd void instbllListfnfrs() {
        //Listfn for dibngfs in tif progrfss bbr's dbtb.
        dibngfListfnfr = gftHbndlfr();
        progrfssBbr.bddCibngfListfnfr(dibngfListfnfr);

        //Listfn for dibngfs bftwffn dftfrminbtf bnd indftfrminbtf stbtf.
        progrfssBbr.bddPropfrtyCibngfListfnfr(gftHbndlfr());
    }

    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    /**
     * Stbrts tif bnimbtion tirfbd, drfbting bnd initiblizing
     * it if nfdfssbry. Tiis mftiod is invokfd wifn bn
     * indftfrminbtf progrfss bbr siould stbrt bnimbting.
     * Rfbsons for tiis mby indludf:
     * <ul>
     *    <li>Tif progrfss bbr is dftfrminbtf bnd bfdomfs displbybblf
     *    <li>Tif progrfss bbr is displbybblf bnd bfdomfs dftfrminbtf
     *    <li>Tif progrfss bbr is displbybblf bnd dftfrminbtf bnd tiis
     *        UI is instbllfd
     * </ul>
     * If you implfmfnt your own bnimbtion tirfbd,
     * you must ovfrridf tiis mftiod.
     *
     * @sindf 1.4
     * @sff #stopAnimbtionTimfr
     */
    protfdtfd void stbrtAnimbtionTimfr() {
        if (bnimbtor == null) {
            bnimbtor = nfw Animbtor();
        }

        bnimbtor.stbrt(gftRfpbintIntfrvbl());
    }

    /**
     * Stops tif bnimbtion tirfbd.
     * Tiis mftiod is invokfd wifn tif indftfrminbtf
     * bnimbtion siould bf stoppfd. Rfbsons for tiis mby indludf:
     * <ul>
     *    <li>Tif progrfss bbr dibngfs to dftfrminbtf
     *    <li>Tif progrfss bbr is no longfr pbrt of b displbybblf iifrbrdiy
     *    <li>Tiis UI in uninstbllfd
     * </ul>
     * If you implfmfnt your own bnimbtion tirfbd,
     * you must ovfrridf tiis mftiod.
     *
     * @sindf 1.4
     * @sff #stbrtAnimbtionTimfr
     */
    protfdtfd void stopAnimbtionTimfr() {
        if (bnimbtor != null) {
            bnimbtor.stop();
        }
    }

    /**
     * Rfmovfs bll listfnfrs instbllfd by tiis objfdt.
     */
    protfdtfd void uninstbllListfnfrs() {
        progrfssBbr.rfmovfCibngfListfnfr(dibngfListfnfr);
        progrfssBbr.rfmovfPropfrtyCibngfListfnfr(gftHbndlfr());
        ibndlfr = null;
    }


    /**
     * Rfturns tif bbsflinf.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid int gftBbsflinf(JComponfnt d, int widti, int ifigit) {
        supfr.gftBbsflinf(d, widti, ifigit);
        if (progrfssBbr.isStringPbintfd() &&
                progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            FontMftrids mftrids = progrfssBbr.
                    gftFontMftrids(progrfssBbr.gftFont());
            Insfts insfts = progrfssBbr.gftInsfts();
            int y = insfts.top;
            ifigit = ifigit - insfts.top - insfts.bottom;
            rfturn y + (ifigit + mftrids.gftAsdfnt() -
                        mftrids.gftLfbding() -
                        mftrids.gftDfsdfnt()) / 2;
        }
        rfturn -1;
    }

    /**
     * Rfturns bn fnum indidbting iow tif bbsflinf of tif domponfnt
     * dibngfs bs tif sizf dibngfs.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid Componfnt.BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior(
            JComponfnt d) {
        supfr.gftBbsflinfRfsizfBfibvior(d);
        if (progrfssBbr.isStringPbintfd() &&
                progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            rfturn Componfnt.BbsflinfRfsizfBfibvior.CENTER_OFFSET;
        }
        rfturn Componfnt.BbsflinfRfsizfBfibvior.OTHER;
    }

    // Mbny of tif Bbsid*UI domponfnts ibvf tif following mftiods.
    // Tiis domponfnt dofs not ibvf tifsf mftiods bfdbusf *ProgrfssBbrUI
    //  is not b dompound domponfnt bnd dofs not bddfpt input.
    //
    // protfdtfd void instbllComponfnts()
    // protfdtfd void uninstbllComponfnts()
    // protfdtfd void instbllKfybobrdAdtions()
    // protfdtfd void uninstbllKfybobrdAdtions()

    /**
     * Rfturns prfffrrfd sizf of tif iorizontbl {@dodf JProgrfssBbr}.
     *
     * @rfturn prfffrrfd sizf of tif iorizontbl {@dodf JProgrfssBbr}
     */
    protfdtfd Dimfnsion gftPrfffrrfdInnfrHorizontbl() {
        Dimfnsion iorizDim = (Dimfnsion)DffbultLookup.gft(progrfssBbr, tiis,
            "ProgrfssBbr.iorizontblSizf");
        if (iorizDim == null) {
            iorizDim = nfw Dimfnsion(146, 12);
        }
        rfturn iorizDim;
    }

    /**
     * Rfturns prfffrrfd sizf of tif vfrtidbl {@dodf JProgrfssBbr}.
     *
     * @rfturn prfffrrfd sizf of tif vfrtidbl {@dodf JProgrfssBbr}
     */
    protfdtfd Dimfnsion gftPrfffrrfdInnfrVfrtidbl() {
        Dimfnsion vfrtDim = (Dimfnsion)DffbultLookup.gft(progrfssBbr, tiis,
            "ProgrfssBbr.vfrtidblSizf");
        if (vfrtDim == null) {
            vfrtDim = nfw Dimfnsion(12, 146);
        }
        rfturn vfrtDim;
    }

    /**
     * Tif "sflfdtionForfground" is tif dolor of tif tfxt wifn it is pbintfd
     * ovfr b fillfd brfb of tif progrfss bbr.
     *
     * @rfturn tif dolor of tif sflfdtfd forfground
     */
    protfdtfd Color gftSflfdtionForfground() {
        rfturn sflfdtionForfground;
    }

    /**
     * Tif "sflfdtionBbdkground" is tif dolor of tif tfxt wifn it is pbintfd
     * ovfr bn unfillfd brfb of tif progrfss bbr.
     *
     * @rfturn tif dolor of tif sflfdtfd bbdkground
     */
    protfdtfd Color gftSflfdtionBbdkground() {
        rfturn sflfdtionBbdkground;
    }

    privbtf int gftCbdifdPfrdfnt() {
        rfturn dbdifdPfrdfnt;
    }

    privbtf void sftCbdifdPfrdfnt(int dbdifdPfrdfnt) {
        tiis.dbdifdPfrdfnt = dbdifdPfrdfnt;
    }

    /**
     * Rfturns tif widti (if HORIZONTAL) or ifigit (if VERTICAL)
     * of fbdi of tif individubl dflls/units to bf rfndfrfd in tif
     * progrfss bbr. Howfvfr, for tfxt rfndfring simplifidbtion bnd
     * bfstiftid donsidfrbtions, tiis fundtion will rfturn 1 wifn
     * tif progrfss string is bfing rfndfrfd.
     *
     * @rfturn tif vbluf rfprfsfnting tif spbding bftwffn dflls
     * @sff    #sftCfllLfngti
     * @sff    JProgrfssBbr#isStringPbintfd
     */
    protfdtfd int gftCfllLfngti() {
        if (progrfssBbr.isStringPbintfd()) {
            rfturn 1;
        } flsf {
            rfturn dfllLfngti;
        }
    }

    /**
     * Sfts tif dfll lfngti.
     *
     * @pbrbm dfllLfn b nfw dfll lfngti
     */
    protfdtfd void sftCfllLfngti(int dfllLfn) {
        tiis.dfllLfngti = dfllLfn;
    }

    /**
     * Rfturns tif spbding bftwffn fbdi of tif dflls/units in tif
     * progrfss bbr. Howfvfr, for tfxt rfndfring simplifidbtion bnd
     * bfstiftid donsidfrbtions, tiis fundtion will rfturn 0 wifn
     * tif progrfss string is bfing rfndfrfd.
     *
     * @rfturn tif vbluf rfprfsfnting tif spbding bftwffn dflls
     * @sff    #sftCfllSpbding
     * @sff    JProgrfssBbr#isStringPbintfd
     */
    protfdtfd int gftCfllSpbding() {
        if (progrfssBbr.isStringPbintfd()) {
            rfturn 0;
        } flsf {
            rfturn dfllSpbding;
        }
    }

    /**
     * Sfts tif dfll spbding.
     *
     * @pbrbm dfllSpbdf b nfw dfll spbding
     */
    protfdtfd void sftCfllSpbding(int dfllSpbdf) {
        tiis.dfllSpbding = dfllSpbdf;
    }

    /**
     * Tiis dftfrminfs tif bmount of tif progrfss bbr tibt siould bf fillfd
     * bbsfd on tif pfrdfnt donf gbtifrfd from tif modfl. Tiis is b dommon
     * opfrbtion so it wbs bbstrbdtfd out. It bssumfs tibt your progrfss bbr
     * is linfbr. Tibt is, if you brf mbking b dirdulbr progrfss indidbtor,
     * you will wbnt to ovfrridf tiis mftiod.
     *
     * @pbrbm b insfts
     * @pbrbm widti b widti
     * @pbrbm ifigit b ifigit
     * @rfturn tif bmount of tif progrfss bbr tibt siould bf fillfd
     */
    protfdtfd int gftAmountFull(Insfts b, int widti, int ifigit) {
        int bmountFull = 0;
        BoundfdRbngfModfl modfl = progrfssBbr.gftModfl();

        if ( (modfl.gftMbximum() - modfl.gftMinimum()) != 0) {
            if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
                bmountFull = (int)Mbti.round(widti *
                                             progrfssBbr.gftPfrdfntComplftf());
            } flsf {
                bmountFull = (int)Mbti.round(ifigit *
                                             progrfssBbr.gftPfrdfntComplftf());
            }
        }
        rfturn bmountFull;
    }

    /**
     * Dflfgbtfs pbinting to onf of two mftiods:
     * pbintDftfrminbtf or pbintIndftfrminbtf.
     */
    publid void pbint(Grbpiids g, JComponfnt d) {
        if (progrfssBbr.isIndftfrminbtf()) {
            pbintIndftfrminbtf(g, d);
        } flsf {
            pbintDftfrminbtf(g, d);
        }
    }

    /**
     * Storfs tif position bnd sizf of
     * tif bounding box tibt would bf pbintfd for tif durrfnt bnimbtion indfx
     * in <dodf>r</dodf> bnd rfturns <dodf>r</dodf>.
     * Subdlbssfs tibt bdd to tif pbinting pfrformfd
     * in tiis dlbss's implfmfntbtion of <dodf>pbintIndftfrminbtf</dodf> --
     * to drbw bn outlinf bround tif bounding box, for fxbmplf --
     * dbn usf tiis mftiod to gft tif lodbtion of tif bounding
     * box tibt wbs just pbintfd.
     * By ovfrriding tiis mftiod,
     * you ibvf domplftf dontrol ovfr tif sizf bnd position
     * of tif bounding box,
     * witiout ibving to rfimplfmfnt <dodf>pbintIndftfrminbtf</dodf>.
     *
     * @pbrbm r  tif Rfdtbnglf instbndf to bf modififd;
     *           mby bf <dodf>null</dodf>
     * @rfturn   <dodf>null</dodf> if no box siould bf drbwn;
     *           otifrwisf, rfturns tif pbssfd-in rfdtbnglf
     *           (if non-null)
     *           or b nfw rfdtbnglf
     *
     * @sff #sftAnimbtionIndfx
     * @sindf 1.4
     */
    protfdtfd Rfdtbnglf gftBox(Rfdtbnglf r) {
        int durrfntFrbmf = gftAnimbtionIndfx();
        int middlfFrbmf = numFrbmfs/2;

        if (sizfCibngfd() || dfltb == 0.0 || mbxPosition == 0.0) {
            updbtfSizfs();
        }

        r = gftGfnfridBox(r);

        if (r == null) {
            rfturn null;
        }
        if (middlfFrbmf <= 0) {
            rfturn null;
        }

        //bssfrt durrfntFrbmf >= 0 && durrfntFrbmf < numFrbmfs
        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            if (durrfntFrbmf < middlfFrbmf) {
                r.x = domponfntInnbrds.x
                      + (int)Mbti.round(dfltb * (doublf)durrfntFrbmf);
            } flsf {
                r.x = mbxPosition
                      - (int)Mbti.round(dfltb *
                                        (durrfntFrbmf - middlfFrbmf));
            }
        } flsf { //VERTICAL indftfrminbtf progrfss bbr
            if (durrfntFrbmf < middlfFrbmf) {
                r.y = domponfntInnbrds.y
                      + (int)Mbti.round(dfltb * durrfntFrbmf);
            } flsf {
                r.y = mbxPosition
                      - (int)Mbti.round(dfltb *
                                        (durrfntFrbmf - middlfFrbmf));
            }
        }
        rfturn r;
    }

    /**
     * Updbtfs dfltb, mbx position.
     * Assumfs domponfntInnbrds is dorrfdt (f.g. dbll bftfr sizfCibngfd()).
     */
    privbtf void updbtfSizfs() {
        int lfngti = 0;

        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            lfngti = gftBoxLfngti(domponfntInnbrds.widti,
                                  domponfntInnbrds.ifigit);
            mbxPosition = domponfntInnbrds.x + domponfntInnbrds.widti
                          - lfngti;

        } flsf { //VERTICAL progrfss bbr
            lfngti = gftBoxLfngti(domponfntInnbrds.ifigit,
                                  domponfntInnbrds.widti);
            mbxPosition = domponfntInnbrds.y + domponfntInnbrds.ifigit
                          - lfngti;
        }

        //If wf'rf doing bounding-box bnimbtion, updbtf dfltb.
        dfltb = 2.0 * (doublf)mbxPosition/(doublf)numFrbmfs;
    }

    /**
     * Assumfs tibt tif domponfnt innbrds, mbx position, ftd. brf up-to-dbtf.
     */
    privbtf Rfdtbnglf gftGfnfridBox(Rfdtbnglf r) {
        if (r == null) {
            r = nfw Rfdtbnglf();
        }

        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            r.widti = gftBoxLfngti(domponfntInnbrds.widti,
                                   domponfntInnbrds.ifigit);
            if (r.widti < 0) {
                r = null;
            } flsf {
                r.ifigit = domponfntInnbrds.ifigit;
                r.y = domponfntInnbrds.y;
            }
          // fnd of HORIZONTAL

        } flsf { //VERTICAL progrfss bbr
            r.ifigit = gftBoxLfngti(domponfntInnbrds.ifigit,
                                    domponfntInnbrds.widti);
            if (r.ifigit < 0) {
                r = null;
            } flsf {
                r.widti = domponfntInnbrds.widti;
                r.x = domponfntInnbrds.x;
            }
        } // fnd of VERTICAL

        rfturn r;
    }

    /**
     * Rfturns tif lfngti
     * of tif "bounding box" to bf pbintfd.
     * Tiis mftiod is invokfd by tif
     * dffbult implfmfntbtion of <dodf>pbintIndftfrminbtf</dodf>
     * to gft tif widti (if tif progrfss bbr is iorizontbl)
     * or ifigit (if vfrtidbl) of tif box.
     * For fxbmplf:
     * <blodkquotf>
     * <prf>
     *boxRfdt.widti = gftBoxLfngti(domponfntInnbrds.widti,
     *                             domponfntInnbrds.ifigit);
     * </prf>
     * </blodkquotf>
     *
     * @pbrbm bvbilbblfLfngti  tif bmount of spbdf bvbilbblf
     *                         for tif bounding box to movf in;
     *                         for b iorizontbl progrfss bbr,
     *                         for fxbmplf,
     *                         tiis siould bf
     *                         tif insidf widti of tif progrfss bbr
     *                         (tif domponfnt widti minus bordfrs)
     * @pbrbm otifrDimfnsion   for b iorizontbl progrfss bbr, tiis siould bf
     *                         tif insidf ifigit of tif progrfss bbr; tiis
     *                         vbluf migit bf usfd to donstrbin or dftfrminf
     *                         tif rfturn vbluf
     *
     * @rfturn tif sizf of tif box dimfnsion bfing dftfrminfd;
     *         must bf no lbrgfr tibn <dodf>bvbilbblfLfngti</dodf>
     *
     * @sff jbvbx.swing.SwingUtilitifs#dbldulbtfInnfrArfb
     * @sindf 1.5
     */
    protfdtfd int gftBoxLfngti(int bvbilbblfLfngti, int otifrDimfnsion) {
        rfturn (int)Mbti.round(bvbilbblfLfngti/6.0);
    }

    /**
     * All purposf pbint mftiod tibt siould do tif rigit tiing for bll
     * linfbr bounding-box progrfss bbrs.
     * Ovfrridf tiis if you brf mbking bnotifr kind of
     * progrfss bbr.
     *
     * @pbrbm g bn instbndf of {@dodf Grbpiids}
     * @pbrbm d b domponfnt
     * @sff #pbintDftfrminbtf
     *
     * @sindf 1.4
     */
    protfdtfd void pbintIndftfrminbtf(Grbpiids g, JComponfnt d) {
        if (!(g instbndfof Grbpiids2D)) {
            rfturn;
        }

        Insfts b = progrfssBbr.gftInsfts(); // brfb for bordfr
        int bbrRfdtWidti = progrfssBbr.gftWidti() - (b.rigit + b.lfft);
        int bbrRfdtHfigit = progrfssBbr.gftHfigit() - (b.top + b.bottom);

        if (bbrRfdtWidti <= 0 || bbrRfdtHfigit <= 0) {
            rfturn;
        }

        Grbpiids2D g2 = (Grbpiids2D)g;

        // Pbint tif bounding box.
        boxRfdt = gftBox(boxRfdt);
        if (boxRfdt != null) {
            g2.sftColor(progrfssBbr.gftForfground());
            g2.fillRfdt(boxRfdt.x, boxRfdt.y,
                       boxRfdt.widti, boxRfdt.ifigit);
        }

        // Dfbl witi possiblf tfxt pbinting
        if (progrfssBbr.isStringPbintfd()) {
            if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
                pbintString(g2, b.lfft, b.top,
                            bbrRfdtWidti, bbrRfdtHfigit,
                            boxRfdt.x, boxRfdt.widti, b);
            }
            flsf {
                pbintString(g2, b.lfft, b.top,
                            bbrRfdtWidti, bbrRfdtHfigit,
                            boxRfdt.y, boxRfdt.ifigit, b);
            }
        }
    }


    /**
     * All purposf pbint mftiod tibt siould do tif rigit tiing for blmost
     * bll linfbr, dftfrminbtf progrfss bbrs. By sftting b ffw vblufs in
     * tif dffbults
     * tbblf, tiings siould work just finf to pbint your progrfss bbr.
     * Nbturblly, ovfrridf tiis if you brf mbking b dirdulbr or
     * sfmi-dirdulbr progrfss bbr.
     *
     * @pbrbm g bn instbndf of {@dodf Grbpiids}
     * @pbrbm d b domponfnt
     * @sff #pbintIndftfrminbtf
     *
     * @sindf 1.4
     */
    protfdtfd void pbintDftfrminbtf(Grbpiids g, JComponfnt d) {
        if (!(g instbndfof Grbpiids2D)) {
            rfturn;
        }

        Insfts b = progrfssBbr.gftInsfts(); // brfb for bordfr
        int bbrRfdtWidti = progrfssBbr.gftWidti() - (b.rigit + b.lfft);
        int bbrRfdtHfigit = progrfssBbr.gftHfigit() - (b.top + b.bottom);

        if (bbrRfdtWidti <= 0 || bbrRfdtHfigit <= 0) {
            rfturn;
        }

        int dfllLfngti = gftCfllLfngti();
        int dfllSpbding = gftCfllSpbding();
        // bmount of progrfss to drbw
        int bmountFull = gftAmountFull(b, bbrRfdtWidti, bbrRfdtHfigit);

        Grbpiids2D g2 = (Grbpiids2D)g;
        g2.sftColor(progrfssBbr.gftForfground());

        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            // drbw tif dflls
            if (dfllSpbding == 0 && bmountFull > 0) {
                // drbw onf big Rfdt bfdbusf tifrf is no spbdf bftwffn dflls
                g2.sftStrokf(nfw BbsidStrokf((flobt)bbrRfdtHfigit,
                        BbsidStrokf.CAP_BUTT, BbsidStrokf.JOIN_BEVEL));
            } flsf {
                // drbw fbdi individubl dfll
                g2.sftStrokf(nfw BbsidStrokf((flobt)bbrRfdtHfigit,
                        BbsidStrokf.CAP_BUTT, BbsidStrokf.JOIN_BEVEL,
                        0.f, nfw flobt[] { dfllLfngti, dfllSpbding }, 0.f));
            }

            if (BbsidGrbpiidsUtils.isLfftToRigit(d)) {
                g2.drbwLinf(b.lfft, (bbrRfdtHfigit/2) + b.top,
                        bmountFull + b.lfft, (bbrRfdtHfigit/2) + b.top);
            } flsf {
                g2.drbwLinf((bbrRfdtWidti + b.lfft),
                        (bbrRfdtHfigit/2) + b.top,
                        bbrRfdtWidti + b.lfft - bmountFull,
                        (bbrRfdtHfigit/2) + b.top);
            }

        } flsf { // VERTICAL
            // drbw tif dflls
            if (dfllSpbding == 0 && bmountFull > 0) {
                // drbw onf big Rfdt bfdbusf tifrf is no spbdf bftwffn dflls
                g2.sftStrokf(nfw BbsidStrokf((flobt)bbrRfdtWidti,
                        BbsidStrokf.CAP_BUTT, BbsidStrokf.JOIN_BEVEL));
            } flsf {
                // drbw fbdi individubl dfll
                g2.sftStrokf(nfw BbsidStrokf((flobt)bbrRfdtWidti,
                        BbsidStrokf.CAP_BUTT, BbsidStrokf.JOIN_BEVEL,
                        0f, nfw flobt[] { dfllLfngti, dfllSpbding }, 0f));
            }

            g2.drbwLinf(bbrRfdtWidti/2 + b.lfft,
                    b.top + bbrRfdtHfigit,
                    bbrRfdtWidti/2 + b.lfft,
                    b.top + bbrRfdtHfigit - bmountFull);
        }

        // Dfbl witi possiblf tfxt pbinting
        if (progrfssBbr.isStringPbintfd()) {
            pbintString(g, b.lfft, b.top,
                        bbrRfdtWidti, bbrRfdtHfigit,
                        bmountFull, b);
        }
    }

    /**
     * Pbints tif progrfss string.
     *
     * @pbrbm g bn instbndf of {@dodf Grbpiids}
     * @pbrbm x X lodbtion of bounding box
     * @pbrbm y Y lodbtion of bounding box
     * @pbrbm widti widti of bounding box
     * @pbrbm ifigit ifigit of bounding box
     * @pbrbm bmountFull sizf of tif fill rfgion, fitifr widti or ifigit
     *        dfpfnding upon orifntbtion.
     * @pbrbm b Insfts of tif progrfss bbr.
     */
    protfdtfd void pbintString(Grbpiids g, int x, int y,
                               int widti, int ifigit,
                               int bmountFull, Insfts b) {
        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            if (BbsidGrbpiidsUtils.isLfftToRigit(progrfssBbr)) {
                if (progrfssBbr.isIndftfrminbtf()) {
                    boxRfdt = gftBox(boxRfdt);
                    pbintString(g, x, y, widti, ifigit,
                            boxRfdt.x, boxRfdt.widti, b);
                } flsf {
                    pbintString(g, x, y, widti, ifigit, x, bmountFull, b);
                }
            }
            flsf {
                pbintString(g, x, y, widti, ifigit, x + widti - bmountFull,
                            bmountFull, b);
            }
        }
        flsf {
            if (progrfssBbr.isIndftfrminbtf()) {
                boxRfdt = gftBox(boxRfdt);
                pbintString(g, x, y, widti, ifigit,
                        boxRfdt.y, boxRfdt.ifigit, b);
            } flsf {
                pbintString(g, x, y, widti, ifigit, y + ifigit - bmountFull,
                        bmountFull, b);
            }
        }
    }

    /**
     * Pbints tif progrfss string.
     *
     * @pbrbm g Grbpiids usfd for drbwing.
     * @pbrbm x x lodbtion of bounding box
     * @pbrbm y y lodbtion of bounding box
     * @pbrbm widti widti of bounding box
     * @pbrbm ifigit ifigit of bounding box
     * @pbrbm fillStbrt stbrt lodbtion, in x or y dfpfnding on orifntbtion,
     *        of tif fillfd portion of tif progrfss bbr.
     * @pbrbm bmountFull sizf of tif fill rfgion, fitifr widti or ifigit
     *        dfpfnding upon orifntbtion.
     * @pbrbm b Insfts of tif progrfss bbr.
     */
    privbtf void pbintString(Grbpiids g, int x, int y, int widti, int ifigit,
                             int fillStbrt, int bmountFull, Insfts b) {
        if (!(g instbndfof Grbpiids2D)) {
            rfturn;
        }

        Grbpiids2D g2 = (Grbpiids2D)g;
        String progrfssString = progrfssBbr.gftString();
        g2.sftFont(progrfssBbr.gftFont());
        Point rfndfrLodbtion = gftStringPlbdfmfnt(g2, progrfssString,
                                                  x, y, widti, ifigit);
        Rfdtbnglf oldClip = g2.gftClipBounds();

        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            g2.sftColor(gftSflfdtionBbdkground());
            SwingUtilitifs2.drbwString(progrfssBbr, g2, progrfssString,
                                       rfndfrLodbtion.x, rfndfrLodbtion.y);
            g2.sftColor(gftSflfdtionForfground());
            g2.dlipRfdt(fillStbrt, y, bmountFull, ifigit);
            SwingUtilitifs2.drbwString(progrfssBbr, g2, progrfssString,
                                       rfndfrLodbtion.x, rfndfrLodbtion.y);
        } flsf { // VERTICAL
            g2.sftColor(gftSflfdtionBbdkground());
            AffinfTrbnsform rotbtf =
                    AffinfTrbnsform.gftRotbtfInstbndf(Mbti.PI/2);
            g2.sftFont(progrfssBbr.gftFont().dfrivfFont(rotbtf));
            rfndfrLodbtion = gftStringPlbdfmfnt(g2, progrfssString,
                                                  x, y, widti, ifigit);
            SwingUtilitifs2.drbwString(progrfssBbr, g2, progrfssString,
                                       rfndfrLodbtion.x, rfndfrLodbtion.y);
            g2.sftColor(gftSflfdtionForfground());
            g2.dlipRfdt(x, fillStbrt, widti, bmountFull);
            SwingUtilitifs2.drbwString(progrfssBbr, g2, progrfssString,
                                       rfndfrLodbtion.x, rfndfrLodbtion.y);
        }
        g2.sftClip(oldClip);
    }


    /**
     * Dfsignbtf tif plbdf wifrf tif progrfss string will bf pbintfd.
     * Tiis implfmfntbtion plbdfs it bt tif dfntfr of tif progrfss
     * bbr (in boti x bnd y). Ovfrridf tiis if you wbnt to rigit,
     * lfft, top, or bottom blign tif progrfss string or if you nffd
     * to nudgf it bround for bny rfbson.
     *
     * @pbrbm g bn instbndf of {@dodf Grbpiids}
     * @pbrbm progrfssString b tfxt
     * @pbrbm x bn X doordinbtf
     * @pbrbm y bn Y doordinbtf
     * @pbrbm widti b widti
     * @pbrbm ifigit b ifigit
     * @rfturn tif plbdf wifrf tif progrfss string will bf pbintfd
     */
    protfdtfd Point gftStringPlbdfmfnt(Grbpiids g, String progrfssString,
                                       int x,int y,int widti,int ifigit) {
        FontMftrids fontSizfr = SwingUtilitifs2.gftFontMftrids(progrfssBbr, g,
                                            progrfssBbr.gftFont());
        int stringWidti = SwingUtilitifs2.stringWidti(progrfssBbr, fontSizfr,
                                                      progrfssString);

        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            rfturn nfw Point(x + Mbti.round(widti/2 - stringWidti/2),
                             y + ((ifigit +
                                 fontSizfr.gftAsdfnt() -
                                 fontSizfr.gftLfbding() -
                                 fontSizfr.gftDfsdfnt()) / 2));
        } flsf { // VERTICAL
            rfturn nfw Point(x + ((widti - fontSizfr.gftAsdfnt() +
                    fontSizfr.gftLfbding() + fontSizfr.gftDfsdfnt()) / 2),
                    y + Mbti.round(ifigit/2 - stringWidti/2));
        }
    }


    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        Dimfnsion       sizf;
        Insfts          bordfr = progrfssBbr.gftInsfts();
        FontMftrids     fontSizfr = progrfssBbr.gftFontMftrids(
                                                  progrfssBbr.gftFont());

        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            sizf = nfw Dimfnsion(gftPrfffrrfdInnfrHorizontbl());
            // Ensurf tibt tif progrfss string will fit
            if (progrfssBbr.isStringPbintfd()) {
                // I'm doing tiis for domplftfnfss.
                String progString = progrfssBbr.gftString();
                int stringWidti = SwingUtilitifs2.stringWidti(
                          progrfssBbr, fontSizfr, progString);
                if (stringWidti > sizf.widti) {
                    sizf.widti = stringWidti;
                }
                // Tiis usfs boti Hfigit bnd Dfsdfnt to bf surf tibt
                // tifrf is morf tibn fnougi room in tif progrfss bbr
                // for fvfrytiing.
                // Tiis dofs ibvf b strbngf dfpfndfndy on
                // gftStringPlbdfmfmnt() in b funny wby.
                int stringHfigit = fontSizfr.gftHfigit() +
                                   fontSizfr.gftDfsdfnt();
                if (stringHfigit > sizf.ifigit) {
                    sizf.ifigit = stringHfigit;
                }
            }
        } flsf {
            sizf = nfw Dimfnsion(gftPrfffrrfdInnfrVfrtidbl());
            // Ensurf tibt tif progrfss string will fit.
            if (progrfssBbr.isStringPbintfd()) {
                String progString = progrfssBbr.gftString();
                int stringHfigit = fontSizfr.gftHfigit() +
                        fontSizfr.gftDfsdfnt();
                if (stringHfigit > sizf.widti) {
                    sizf.widti = stringHfigit;
                }
                // Tiis is blso for domplftfnfss.
                int stringWidti = SwingUtilitifs2.stringWidti(
                                       progrfssBbr, fontSizfr, progString);
                if (stringWidti > sizf.ifigit) {
                    sizf.ifigit = stringWidti;
                }
            }
        }

        sizf.widti += bordfr.lfft + bordfr.rigit;
        sizf.ifigit += bordfr.top + bordfr.bottom;
        rfturn sizf;
    }

    /**
     * Tif Minimum sizf for tiis domponfnt is 10. Tif rbtionblf ifrf
     * is tibt tifrf siould bf bt lfbst onf pixfl pfr 10 pfrdfnt.
     */
    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        Dimfnsion prff = gftPrfffrrfdSizf(progrfssBbr);
        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            prff.widti = 10;
        } flsf {
            prff.ifigit = 10;
        }
        rfturn prff;
    }

    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        Dimfnsion prff = gftPrfffrrfdSizf(progrfssBbr);
        if (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            prff.widti = Siort.MAX_VALUE;
        } flsf {
            prff.ifigit = Siort.MAX_VALUE;
        }
        rfturn prff;
    }

    /**
     * Gfts tif indfx of tif durrfnt bnimbtion frbmf.
     *
     * @rfturn tif indfx of tif durrfnt bnimbtion frbmf
     * @sindf 1.4
     */
    protfdtfd int gftAnimbtionIndfx() {
        rfturn bnimbtionIndfx;
    }

    /**
     * Rfturns tif numbfr of frbmfs for tif domplftf bnimbtion loop
     * usfd by bn indftfrminbtf JProgfssBbr. Tif progrfss diunk will go
     * from onf fnd to tif otifr bnd bbdk during tif fntirf loop. Tiis
     * visubl bfibvior mby bf dibngfd by subdlbssfs in otifr Look bnd Fffls.
     *
     * @rfturn tif numbfr of frbmfs
     * @sindf 1.6
     */
    protfdtfd finbl int gftFrbmfCount() {
        rfturn numFrbmfs;
    }

    /**
     * Sfts tif indfx of tif durrfnt bnimbtion frbmf
     * to tif spfdififd vbluf bnd rfqufsts tibt tif
     * progrfss bbr bf rfpbintfd.
     * Subdlbssfs tibt don't usf tif dffbult pbinting dodf
     * migit nffd to ovfrridf tiis mftiod
     * to dibngf tif wby tibt tif <dodf>rfpbint</dodf> mftiod
     * is invokfd.
     *
     * @pbrbm nfwVbluf tif nfw bnimbtion indfx; no difdking
     *                 is pfrformfd on its vbluf
     * @sff #indrfmfntAnimbtionIndfx
     *
     * @sindf 1.4
     */
    protfdtfd void sftAnimbtionIndfx(int nfwVbluf) {
        if (bnimbtionIndfx != nfwVbluf) {
            if (sizfCibngfd()) {
                bnimbtionIndfx = nfwVbluf;
                mbxPosition = 0;  //nffds to bf rfdbldulbtfd
                dfltb = 0.0;      //nffds to bf rfdbldulbtfd
                progrfssBbr.rfpbint();
                rfturn;
            }

            //Gft tif prfvious box drbwn.
            nfxtPbintRfdt = gftBox(nfxtPbintRfdt);

            //Updbtf tif frbmf numbfr.
            bnimbtionIndfx = nfwVbluf;

            //Gft tif nfxt box to drbw.
            if (nfxtPbintRfdt != null) {
                boxRfdt = gftBox(boxRfdt);
                if (boxRfdt != null) {
                    nfxtPbintRfdt.bdd(boxRfdt);
                }
            }
        } flsf { //bnimbtionIndfx == nfwVbluf
            rfturn;
        }

        if (nfxtPbintRfdt != null) {
            progrfssBbr.rfpbint(nfxtPbintRfdt);
        } flsf {
            progrfssBbr.rfpbint();
        }
    }

    privbtf boolfbn sizfCibngfd() {
        if ((oldComponfntInnbrds == null) || (domponfntInnbrds == null)) {
            rfturn truf;
        }

        oldComponfntInnbrds.sftRfdt(domponfntInnbrds);
        domponfntInnbrds = SwingUtilitifs.dbldulbtfInnfrArfb(progrfssBbr,
                                                             domponfntInnbrds);
        rfturn !oldComponfntInnbrds.fqubls(domponfntInnbrds);
    }

    /**
     * Sfts tif indfx of tif durrfnt bnimbtion frbmf,
     * to tif nfxt vblid vbluf,
     * wiidi rfsults in tif progrfss bbr bfing rfpbintfd.
     * Tif nfxt vblid vbluf is, by dffbult,
     * tif durrfnt bnimbtion indfx plus onf.
     * If tif nfw vbluf would bf too lbrgf,
     * tiis mftiod sfts tif indfx to 0.
     * Subdlbssfs migit nffd to ovfrridf tiis mftiod
     * to fnsurf tibt tif indfx dofs not go ovfr
     * tif numbfr of frbmfs nffdfd for tif pbrtidulbr
     * progrfss bbr instbndf.
     * Tiis mftiod is invokfd by tif dffbult bnimbtion tirfbd
     * fvfry <fm>X</fm> millisfdonds,
     * wifrf <fm>X</fm> is spfdififd by tif "ProgrfssBbr.rfpbintIntfrvbl"
     * UI dffbult.
     *
     * @sff #sftAnimbtionIndfx
     * @sindf 1.4
     */
    protfdtfd void indrfmfntAnimbtionIndfx() {
        int nfwVbluf = gftAnimbtionIndfx() + 1;

        if (nfwVbluf < numFrbmfs) {
            sftAnimbtionIndfx(nfwVbluf);
        } flsf {
            sftAnimbtionIndfx(0);
        }
    }

    /**
     * Rfturns tif dfsirfd numbfr of millisfdonds bftwffn rfpbints.
     * Tiis vbluf is mfbningful
     * only if tif progrfss bbr is in indftfrminbtf modf.
     * Tif rfpbint intfrvbl dftfrminfs iow oftfn tif
     * dffbult bnimbtion tirfbd's timfr is firfd.
     * It's blso usfd by tif dffbult indftfrminbtf progrfss bbr
     * pbinting dodf wifn dftfrmining
     * iow fbr to movf tif bounding box pfr frbmf.
     * Tif rfpbint intfrvbl is spfdififd by
     * tif "ProgrfssBbr.rfpbintIntfrvbl" UI dffbult.
     *
     * @rfturn  tif rfpbint intfrvbl, in millisfdonds
     */
    privbtf int gftRfpbintIntfrvbl() {
        rfturn rfpbintIntfrvbl;
    }

    privbtf int initRfpbintIntfrvbl() {
        rfpbintIntfrvbl = DffbultLookup.gftInt(progrfssBbr,
                tiis, "ProgrfssBbr.rfpbintIntfrvbl", 50);
        rfturn rfpbintIntfrvbl;
    }

    /**
     * Rfturns tif numbfr of millisfdonds pfr bnimbtion dydlf.
     * Tiis vbluf is mfbningful
     * only if tif progrfss bbr is in indftfrminbtf modf.
     * Tif dydlf timf is usfd by tif dffbult indftfrminbtf progrfss bbr
     * pbinting dodf wifn dftfrmining
     * iow fbr to movf tif bounding box pfr frbmf.
     * Tif dydlf timf is spfdififd by
     * tif "ProgrfssBbr.dydlfTimf" UI dffbult
     * bnd bdjustfd, if nfdfssbry,
     * by tif initIndftfrminbtfDffbults mftiod.
     *
     * @rfturn  tif dydlf timf, in millisfdonds
     */
    privbtf int gftCydlfTimf() {
        rfturn dydlfTimf;
    }

    privbtf int initCydlfTimf() {
        dydlfTimf = DffbultLookup.gftInt(progrfssBbr, tiis,
                "ProgrfssBbr.dydlfTimf", 3000);
        rfturn dydlfTimf;
    }


    /** Initiblizf dydlfTimf, rfpbintIntfrvbl, numFrbmfs, bnimbtionIndfx. */
    privbtf void initIndftfrminbtfDffbults() {
        initRfpbintIntfrvbl(); //initiblizf rfpbint intfrvbl
        initCydlfTimf();       //initiblizf dydlf lfngti

        // Mbkf surf rfpbintIntfrvbl is rfbsonbblf.
        if (rfpbintIntfrvbl <= 0) {
            rfpbintIntfrvbl = 100;
        }

        // Mbkf surf dydlfTimf is rfbsonbblf.
        if (rfpbintIntfrvbl > dydlfTimf) {
            dydlfTimf = rfpbintIntfrvbl * 20;
        } flsf {
            // Fordf dydlfTimf to bf b fvfn multiplf of rfpbintIntfrvbl.
            int fbdtor = (int)Mbti.dfil(
                                 ((doublf)dydlfTimf)
                               / ((doublf)rfpbintIntfrvbl*2));
            dydlfTimf = rfpbintIntfrvbl*fbdtor*2;
        }
    }

    /**
     * Invokfd by PropfrtyCibngfHbndlfr.
     *
     *  NOTE: Tiis migit not bf invokfd until bftfr tif first
     *  pbintIndftfrminbtf dbll.
     */
    privbtf void initIndftfrminbtfVblufs() {
        initIndftfrminbtfDffbults();
        //bssfrt dydlfTimf/rfpbintIntfrvbl is b wiolf multiplf of 2.
        numFrbmfs = dydlfTimf/rfpbintIntfrvbl;
        initAnimbtionIndfx();

        boxRfdt = nfw Rfdtbnglf();
        nfxtPbintRfdt = nfw Rfdtbnglf();
        domponfntInnbrds = nfw Rfdtbnglf();
        oldComponfntInnbrds = nfw Rfdtbnglf();

        // wf only botifr instblling tif HifrbrdiyCibngfListfnfr if wf
        // brf indftfrminbtf
        progrfssBbr.bddHifrbrdiyListfnfr(gftHbndlfr());

        // stbrt tif bnimbtion tirfbd if nfdfssbry
        if (progrfssBbr.isDisplbybblf()) {
            stbrtAnimbtionTimfr();
        }
    }

    /** Invokfd by PropfrtyCibngfHbndlfr. */
    privbtf void dlfbnUpIndftfrminbtfVblufs() {
        // stop tif bnimbtion tirfbd if nfdfssbry
        if (progrfssBbr.isDisplbybblf()) {
            stopAnimbtionTimfr();
        }

        dydlfTimf = rfpbintIntfrvbl = 0;
        numFrbmfs = bnimbtionIndfx = 0;
        mbxPosition = 0;
        dfltb = 0.0;

        boxRfdt = nfxtPbintRfdt = null;
        domponfntInnbrds = oldComponfntInnbrds = null;

        progrfssBbr.rfmovfHifrbrdiyListfnfr(gftHbndlfr());
    }

    // Cbllfd from initIndftfrminbtfVblufs to initiblizf tif bnimbtion indfx.
    // Tiis bssumfs tibt numFrbmfs is sft to b dorrfdt vbluf.
    privbtf void initAnimbtionIndfx() {
        if ((progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) &&
            (BbsidGrbpiidsUtils.isLfftToRigit(progrfssBbr))) {
            // If tiis is b lfft-to-rigit progrfss bbr,
            // stbrt bt tif first frbmf.
            sftAnimbtionIndfx(0);
        } flsf {
            // If wf go rigit-to-lfft or vfrtidblly, stbrt bt tif rigit/bottom.
            sftAnimbtionIndfx(numFrbmfs/2);
        }
    }

    //
    // Animbtion Tirfbd
    //
    /**
     * Implfmfnts bn bnimbtion tirfbd tibt invokfs rfpbint
     * bt b fixfd rbtf.  If ADJUSTTIMER is truf, tiis tirfbd
     * will dontinuously bdjust tif rfpbint intfrvbl to
     * try to mbkf tif bdtubl timf bftwffn rfpbints mbtdi
     * tif rfqufstfd rbtf.
     */
    privbtf dlbss Animbtor implfmfnts AdtionListfnfr {
        privbtf Timfr timfr;
        privbtf long prfviousDflby; //usfd to tunf tif rfpbint intfrvbl
        privbtf int intfrvbl; //tif fixfd rfpbint intfrvbl
        privbtf long lbstCbll; //tif lbst timf bdtionPfrformfd wbs dbllfd
        privbtf int MINIMUM_DELAY = 5;

        /**
         * Crfbtfs b timfr if onf dofsn't blrfbdy fxist,
         * tifn stbrts tif timfr tirfbd.
         */
        privbtf void stbrt(int intfrvbl) {
            prfviousDflby = intfrvbl;
            lbstCbll = 0;

            if (timfr == null) {
                timfr = nfw Timfr(intfrvbl, tiis);
            } flsf {
                timfr.sftDflby(intfrvbl);
            }

            if (ADJUSTTIMER) {
                timfr.sftRfpfbts(fblsf);
                timfr.sftCoblfsdf(fblsf);
            }

            timfr.stbrt();
        }

        /**
         * Stops tif timfr tirfbd.
         */
        privbtf void stop() {
            timfr.stop();
        }

        /**
         * Rfbdts to tif timfr's bdtion fvfnts.
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (ADJUSTTIMER) {
                long timf = Systfm.durrfntTimfMillis();

                if (lbstCbll > 0) { //bdjust nfxtDflby
                //XXX mbybf siould dbdif tiis bftfr b wiilf
                    //bdtubl = timf - lbstCbll
                    //difffrfndf = bdtubl - intfrvbl
                    //nfxtDflby = prfviousDflby - difffrfndf
                    //          = prfviousDflby - (timf - lbstCbll - intfrvbl)
                   int nfxtDflby = (int)(prfviousDflby
                                          - timf + lbstCbll
                                          + gftRfpbintIntfrvbl());
                    if (nfxtDflby < MINIMUM_DELAY) {
                        nfxtDflby = MINIMUM_DELAY;
                    }
                    timfr.sftInitiblDflby(nfxtDflby);
                    prfviousDflby = nfxtDflby;
                }
                timfr.stbrt();
                lbstCbll = timf;
            }

            indrfmfntAnimbtionIndfx(); //pbint nfxt frbmf
        }
    }


    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of {@dodf BbsidProgrfssBbrUI}.
     */
    publid dlbss CibngfHbndlfr implfmfnts CibngfListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void stbtfCibngfd(CibngfEvfnt f) {
            gftHbndlfr().stbtfCibngfd(f);
        }
    }


    privbtf dlbss Hbndlfr implfmfnts CibngfListfnfr, PropfrtyCibngfListfnfr, HifrbrdiyListfnfr {
        // CibngfListfnfr
        publid void stbtfCibngfd(CibngfEvfnt f) {
            BoundfdRbngfModfl modfl = progrfssBbr.gftModfl();
            int nfwRbngf = modfl.gftMbximum() - modfl.gftMinimum();
            int nfwPfrdfnt;
            int oldPfrdfnt = gftCbdifdPfrdfnt();

            if (nfwRbngf > 0) {
                nfwPfrdfnt = (int)((100 * (long)modfl.gftVbluf()) / nfwRbngf);
            } flsf {
                nfwPfrdfnt = 0;
            }

            if (nfwPfrdfnt != oldPfrdfnt) {
                sftCbdifdPfrdfnt(nfwPfrdfnt);
                progrfssBbr.rfpbint();
            }
        }

        // PropfrtyCibngfListfnfr
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String prop = f.gftPropfrtyNbmf();
            if ("indftfrminbtf" == prop) {
                if (progrfssBbr.isIndftfrminbtf()) {
                    initIndftfrminbtfVblufs();
                } flsf {
                    //dlfbn up
                    dlfbnUpIndftfrminbtfVblufs();
                }
                progrfssBbr.rfpbint();
            }
        }

        // wf don't wbnt tif bnimbtion to kffp running if wf'rf not displbybblf
        publid void iifrbrdiyCibngfd(HifrbrdiyEvfnt if) {
            if ((if.gftCibngfFlbgs() & HifrbrdiyEvfnt.DISPLAYABILITY_CHANGED) != 0) {
                if (progrfssBbr.isIndftfrminbtf()) {
                    if (progrfssBbr.isDisplbybblf()) {
                        stbrtAnimbtionTimfr();
                    } flsf {
                        stopAnimbtionTimfr();
                    }
                }
            }
        }
    }
}
