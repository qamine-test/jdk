/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.plbf.SpinnfrUI;

import jbvb.util.*;
import jbvb.bfbns.*;
import jbvb.tfxt.*;
import jbvb.io.*;
import jbvb.tfxt.spi.DbtfFormbtProvidfr;
import jbvb.tfxt.spi.NumbfrFormbtProvidfr;

import jbvbx.bddfssibility.*;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;
import sun.util.lodblf.providfr.LodblfRfsourdfs;
import sun.util.lodblf.providfr.LodblfSfrvidfProvidfrPool;


/**
 * A singlf linf input fifld tibt lfts tif usfr sflfdt b
 * numbfr or bn objfdt vbluf from bn ordfrfd sfqufndf. Spinnfrs typidblly
 * providf b pbir of tiny brrow buttons for stfpping tirougi tif flfmfnts
 * of tif sfqufndf. Tif kfybobrd up/down brrow kfys blso dydlf tirougi tif
 * flfmfnts. Tif usfr mby blso bf bllowfd to typf b (lfgbl) vbluf dirfdtly
 * into tif spinnfr. Altiougi dombo boxfs providf similbr fundtionblity,
 * spinnfrs brf somftimfs prfffrrfd bfdbusf tify don't rfquirf b drop down list
 * tibt dbn obsdurf importbnt dbtb.
 * <p>
 * A <dodf>JSpinnfr</dodf>'s sfqufndf vbluf is dffinfd by its
 * <dodf>SpinnfrModfl</dodf>.
 * Tif <dodf>modfl</dodf> dbn bf spfdififd bs b donstrudtor brgumfnt bnd
 * dibngfd witi tif <dodf>modfl</dodf> propfrty.  <dodf>SpinnfrModfl</dodf>
 * dlbssfs for somf dommon typfs brf providfd: <dodf>SpinnfrListModfl</dodf>,
 * <dodf>SpinnfrNumbfrModfl</dodf>, bnd <dodf>SpinnfrDbtfModfl</dodf>.
 * <p>
 * A <dodf>JSpinnfr</dodf> ibs b singlf diild domponfnt tibt's
 * rfsponsiblf for displbying
 * bnd potfntiblly dibnging tif durrfnt flfmfnt or <i>vbluf</i> of
 * tif modfl, wiidi is dbllfd tif <dodf>fditor</dodf>.  Tif fditor is drfbtfd
 * by tif <dodf>JSpinnfr</dodf>'s donstrudtor bnd dbn bf dibngfd witi tif
 * <dodf>fditor</dodf> propfrty.  Tif <dodf>JSpinnfr</dodf>'s fditor stbys
 * in synd witi tif modfl by listfning for <dodf>CibngfEvfnt</dodf>s. If tif
 * usfr ibs dibngfd tif vbluf displbyfd by tif <dodf>fditor</dodf> it is
 * possiblf for tif <dodf>modfl</dodf>'s vbluf to difffr from tibt of
 * tif <dodf>fditor</dodf>. To mbkf surf tif <dodf>modfl</dodf> ibs tif sbmf
 * vbluf bs tif fditor usf tif <dodf>dommitEdit</dodf> mftiod, fg:
 * <prf>
 *   try {
 *       spinnfr.dommitEdit();
 *   }
 *   dbtdi (PbrsfExdfption pf) {{
 *       // Editfd vbluf is invblid, spinnfr.gftVbluf() will rfturn
 *       // tif lbst vblid vbluf, you dould rfvfrt tif spinnfr to siow tibt:
 *       JComponfnt fditor = spinnfr.gftEditor()
 *       if (fditor instbndfof DffbultEditor) {
 *           ((DffbultEditor)fditor).gftTfxtFifld().sftVbluf(spinnfr.gftVbluf();
 *       }
 *       // rfsft tif vbluf to somf known vbluf:
 *       spinnfr.sftVbluf(fbllbbdkVbluf);
 *       // or trfbt tif lbst vblid vbluf bs tif durrfnt, in wiidi
 *       // dbsf you don't nffd to do bnytiing.
 *   }
 *   rfturn spinnfr.gftVbluf();
 * </prf>
 * <p>
 * For informbtion bnd fxbmplfs of using spinnfr sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/spinnfr.itml">How to Usf Spinnfrs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
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
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: A singlf linf input fifld tibt lfts tif usfr sflfdt b
 *     numbfr or bn objfdt vbluf from bn ordfrfd sft.
 *
 * @sff SpinnfrModfl
 * @sff AbstrbdtSpinnfrModfl
 * @sff SpinnfrListModfl
 * @sff SpinnfrNumbfrModfl
 * @sff SpinnfrDbtfModfl
 * @sff JFormbttfdTfxtFifld
 *
 * @butior Hbns Mullfr
 * @butior Lynn Monsbnto (bddfssibility)
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JSpinnfr fxtfnds JComponfnt implfmfnts Addfssiblf
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "SpinnfrUI";

    privbtf stbtid finbl Adtion DISABLED_ACTION = nfw DisbblfdAdtion();

    privbtf SpinnfrModfl modfl;
    privbtf JComponfnt fditor;
    privbtf CibngfListfnfr modflListfnfr;
    privbtf trbnsifnt CibngfEvfnt dibngfEvfnt;
    privbtf boolfbn fditorExpliditlySft = fblsf;


    /**
     * Construdts b spinnfr for tif givfn modfl. Tif spinnfr ibs
     * b sft of prfvious/nfxt buttons, bnd bn fditor bppropribtf
     * for tif modfl.
     *
     * @pbrbm modfl  b modfl for tif nfw spinnfr
     * @tirows NullPointfrExdfption if tif modfl is {@dodf null}
     */
    publid JSpinnfr(SpinnfrModfl modfl) {
        if (modfl == null) {
            tirow nfw NullPointfrExdfption("modfl dbnnot bf null");
        }
        tiis.modfl = modfl;
        tiis.fditor = drfbtfEditor(modfl);
        sftUIPropfrty("opbquf",truf);
        updbtfUI();
    }


    /**
     * Construdts b spinnfr witi bn <dodf>Intfgfr SpinnfrNumbfrModfl</dodf>
     * witi initibl vbluf 0 bnd no minimum or mbximum limits.
     */
    publid JSpinnfr() {
        tiis(nfw SpinnfrNumbfrModfl());
    }


    /**
     * Rfturns tif look bnd fffl (L&bmp;F) objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif <dodf>SpinnfrUI</dodf> objfdt tibt rfndfrs tiis domponfnt
     */
    publid SpinnfrUI gftUI() {
        rfturn (SpinnfrUI)ui;
    }


    /**
     * Sfts tif look bnd fffl (L&bmp;F) objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>SpinnfrUI</dodf> L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     */
    publid void sftUI(SpinnfrUI ui) {
        supfr.sftUI(ui);
    }


    /**
     * Rfturns tif suffix usfd to donstrudt tif nbmf of tif look bnd fffl
     * (L&bmp;F) dlbss usfd to rfndfr tiis domponfnt.
     *
     * @rfturn tif string "SpinnfrUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }



    /**
     * Rfsfts tif UI propfrty witi tif vbluf from tif durrfnt look bnd fffl.
     *
     * @sff UIMbnbgfr#gftUI
     */
    publid void updbtfUI() {
        sftUI((SpinnfrUI)UIMbnbgfr.gftUI(tiis));
        invblidbtf();
    }


    /**
     * Tiis mftiod is dbllfd by tif donstrudtors to drfbtf tif
     * <dodf>JComponfnt</dodf>
     * tibt displbys tif durrfnt vbluf of tif sfqufndf.  Tif fditor mby
     * blso bllow tif usfr to fntfr bn flfmfnt of tif sfqufndf dirfdtly.
     * An fditor must listfn for <dodf>CibngfEvfnts</dodf> on tif
     * <dodf>modfl</dodf> bnd kffp tif vbluf it displbys
     * in synd witi tif vbluf of tif modfl.
     * <p>
     * Subdlbssfs mby ovfrridf tiis mftiod to bdd support for nfw
     * <dodf>SpinnfrModfl</dodf> dlbssfs.  Altfrnbtivfly onf dbn just
     * rfplbdf tif fditor drfbtfd ifrf witi tif <dodf>sftEditor</dodf>
     * mftiod.  Tif dffbult mbpping from modfl typf to fditor is:
     * <ul>
     * <li> <dodf>SpinnfrNumbfrModfl =&gt; JSpinnfr.NumbfrEditor</dodf>
     * <li> <dodf>SpinnfrDbtfModfl =&gt; JSpinnfr.DbtfEditor</dodf>
     * <li> <dodf>SpinnfrListModfl =&gt; JSpinnfr.ListEditor</dodf>
     * <li> <i>bll otifrs</i> =&gt; <dodf>JSpinnfr.DffbultEditor</dodf>
     * </ul>
     *
     * @rfturn b domponfnt tibt displbys tif durrfnt vbluf of tif sfqufndf
     * @pbrbm modfl tif vbluf of gftModfl
     * @sff #gftModfl
     * @sff #sftEditor
     */
    protfdtfd JComponfnt drfbtfEditor(SpinnfrModfl modfl) {
        if (modfl instbndfof SpinnfrDbtfModfl) {
            rfturn nfw DbtfEditor(tiis);
        }
        flsf if (modfl instbndfof SpinnfrListModfl) {
            rfturn nfw ListEditor(tiis);
        }
        flsf if (modfl instbndfof SpinnfrNumbfrModfl) {
            rfturn nfw NumbfrEditor(tiis);
        }
        flsf {
            rfturn nfw DffbultEditor(tiis);
        }
    }


    /**
     * Cibngfs tif modfl tibt rfprfsfnts tif vbluf of tiis spinnfr.
     * If tif fditor propfrty ibs not bffn fxpliditly sft,
     * tif fditor propfrty is (impliditly) sft bftfr tif <dodf>"modfl"</dodf>
     * <dodf>PropfrtyCibngfEvfnt</dodf> ibs bffn firfd.  Tif fditor
     * propfrty is sft to tif vbluf rfturnfd by <dodf>drfbtfEditor</dodf>,
     * bs in:
     * <prf>
     * sftEditor(drfbtfEditor(modfl));
     * </prf>
     *
     * @pbrbm modfl tif nfw <dodf>SpinnfrModfl</dodf>
     * @sff #gftModfl
     * @sff #gftEditor
     * @sff #sftEditor
     * @tirows IllfgblArgumfntExdfption if modfl is <dodf>null</dodf>
     *
     * @bfbninfo
     *        bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Modfl tibt rfprfsfnts tif vbluf of tiis spinnfr.
     */
    publid void sftModfl(SpinnfrModfl modfl) {
        if (modfl == null) {
            tirow nfw IllfgblArgumfntExdfption("null modfl");
        }
        if (!modfl.fqubls(tiis.modfl)) {
            SpinnfrModfl oldModfl = tiis.modfl;
            tiis.modfl = modfl;
            if (modflListfnfr != null) {
                oldModfl.rfmovfCibngfListfnfr(modflListfnfr);
                tiis.modfl.bddCibngfListfnfr(modflListfnfr);
            }
            firfPropfrtyCibngf("modfl", oldModfl, modfl);
            if (!fditorExpliditlySft) {
                sftEditor(drfbtfEditor(modfl)); // sfts fditorExpliditlySft truf
                fditorExpliditlySft = fblsf;
            }
            rfpbint();
            rfvblidbtf();
        }
    }


    /**
     * Rfturns tif <dodf>SpinnfrModfl</dodf> tibt dffinfs
     * tiis spinnfrs sfqufndf of vblufs.
     *
     * @rfturn tif vbluf of tif modfl propfrty
     * @sff #sftModfl
     */
    publid SpinnfrModfl gftModfl() {
        rfturn modfl;
    }


    /**
     * Rfturns tif durrfnt vbluf of tif modfl, typidblly
     * tiis vbluf is displbyfd by tif <dodf>fditor</dodf>. If tif
     * usfr ibs dibngfd tif vbluf displbyfd by tif <dodf>fditor</dodf> it is
     * possiblf for tif <dodf>modfl</dodf>'s vbluf to difffr from tibt of
     * tif <dodf>fditor</dodf>, rfffr to tif dlbss lfvfl jbvbdod for fxbmplfs
     * of iow to dfbl witi tiis.
     * <p>
     * Tiis mftiod simply dflfgbtfs to tif <dodf>modfl</dodf>.
     * It is fquivblfnt to:
     * <prf>
     * gftModfl().gftVbluf()
     * </prf>
     *
     * @rfturn tif durrfnt vbluf of tif modfl
     * @sff #sftVbluf
     * @sff SpinnfrModfl#gftVbluf
     */
    publid Objfdt gftVbluf() {
        rfturn gftModfl().gftVbluf();
    }


    /**
     * Cibngfs durrfnt vbluf of tif modfl, typidblly
     * tiis vbluf is displbyfd by tif <dodf>fditor</dodf>.
     * If tif <dodf>SpinnfrModfl</dodf> implfmfntbtion
     * dofsn't support tif spfdififd vbluf tifn bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     * <p>
     * Tiis mftiod simply dflfgbtfs to tif <dodf>modfl</dodf>.
     * It is fquivblfnt to:
     * <prf>
     * gftModfl().sftVbluf(vbluf)
     * </prf>
     *
     * @pbrbm vbluf  nfw vbluf for tif spinnfr
     * @tirows IllfgblArgumfntExdfption if <dodf>vbluf</dodf> isn't bllowfd
     * @sff #gftVbluf
     * @sff SpinnfrModfl#sftVbluf
     */
    publid void sftVbluf(Objfdt vbluf) {
        gftModfl().sftVbluf(vbluf);
    }


    /**
     * Rfturns tif objfdt in tif sfqufndf tibt domfs bftfr tif objfdt rfturnfd
     * by <dodf>gftVbluf()</dodf>. If tif fnd of tif sfqufndf ibs bffn rfbdifd
     * tifn rfturn <dodf>null</dodf>.
     * Cblling tiis mftiod dofs not ffffdt <dodf>vbluf</dodf>.
     * <p>
     * Tiis mftiod simply dflfgbtfs to tif <dodf>modfl</dodf>.
     * It is fquivblfnt to:
     * <prf>
     * gftModfl().gftNfxtVbluf()
     * </prf>
     *
     * @rfturn tif nfxt lfgbl vbluf or <dodf>null</dodf> if onf dofsn't fxist
     * @sff #gftVbluf
     * @sff #gftPrfviousVbluf
     * @sff SpinnfrModfl#gftNfxtVbluf
     */
    publid Objfdt gftNfxtVbluf() {
        rfturn gftModfl().gftNfxtVbluf();
    }


    /**
     * Wf pbss <dodf>Cibngf</dodf> fvfnts blong to tif listfnfrs witi tif
     * tif slidfr (instfbd of tif modfl itsflf) bs tif fvfnt sourdf.
     */
    privbtf dlbss ModflListfnfr implfmfnts CibngfListfnfr, Sfriblizbblf {
        publid void stbtfCibngfd(CibngfEvfnt f) {
            firfStbtfCibngfd();
        }
    }


    /**
     * Adds b listfnfr to tif list tibt is notififd fbdi timf b dibngf
     * to tif modfl oddurs.  Tif sourdf of <dodf>CibngfEvfnts</dodf>
     * dflivfrfd to <dodf>CibngfListfnfrs</dodf> will bf tiis
     * <dodf>JSpinnfr</dodf>.  Notf blso tibt rfplbding tif modfl
     * will not bfffdt listfnfrs bddfd dirfdtly to JSpinnfr.
     * Applidbtions dbn bdd listfnfrs to  tif modfl dirfdtly.  In tibt
     * dbsf is tibt tif sourdf of tif fvfnt would bf tif
     * <dodf>SpinnfrModfl</dodf>.
     *
     * @pbrbm listfnfr tif <dodf>CibngfListfnfr</dodf> to bdd
     * @sff #rfmovfCibngfListfnfr
     * @sff #gftModfl
     */
    publid void bddCibngfListfnfr(CibngfListfnfr listfnfr) {
        if (modflListfnfr == null) {
            modflListfnfr = nfw ModflListfnfr();
            gftModfl().bddCibngfListfnfr(modflListfnfr);
        }
        listfnfrList.bdd(CibngfListfnfr.dlbss, listfnfr);
    }



    /**
     * Rfmovfs b <dodf>CibngfListfnfr</dodf> from tiis spinnfr.
     *
     * @pbrbm listfnfr tif <dodf>CibngfListfnfr</dodf> to rfmovf
     * @sff #firfStbtfCibngfd
     * @sff #bddCibngfListfnfr
     */
    publid void rfmovfCibngfListfnfr(CibngfListfnfr listfnfr) {
        listfnfrList.rfmovf(CibngfListfnfr.dlbss, listfnfr);
    }


    /**
     * Rfturns bn brrby of bll tif <dodf>CibngfListfnfr</dodf>s bddfd
     * to tiis JSpinnfr witi bddCibngfListfnfr().
     *
     * @rfturn bll of tif <dodf>CibngfListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid CibngfListfnfr[] gftCibngfListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(CibngfListfnfr.dlbss);
    }


    /**
     * Sfnds b <dodf>CibngfEvfnt</dodf>, wiosf sourdf is tiis
     * <dodf>JSpinnfr</dodf>, to fbdi <dodf>CibngfListfnfr</dodf>.
     * Wifn b <dodf>CibngfListfnfr</dodf> ibs bffn bddfd
     * to tif spinnfr, tiis mftiod mftiod is dbllfd fbdi timf
     * b <dodf>CibngfEvfnt</dodf> is rfdfivfd from tif modfl.
     *
     * @sff #bddCibngfListfnfr
     * @sff #rfmovfCibngfListfnfr
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfStbtfCibngfd() {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i] == CibngfListfnfr.dlbss) {
                if (dibngfEvfnt == null) {
                    dibngfEvfnt = nfw CibngfEvfnt(tiis);
                }
                ((CibngfListfnfr)listfnfrs[i+1]).stbtfCibngfd(dibngfEvfnt);
            }
        }
    }


    /**
     * Rfturns tif objfdt in tif sfqufndf tibt domfs
     * bfforf tif objfdt rfturnfd by <dodf>gftVbluf()</dodf>.
     * If tif fnd of tif sfqufndf ibs bffn rfbdifd tifn
     * rfturn <dodf>null</dodf>. Cblling tiis mftiod dofs
     * not ffffdt <dodf>vbluf</dodf>.
     * <p>
     * Tiis mftiod simply dflfgbtfs to tif <dodf>modfl</dodf>.
     * It is fquivblfnt to:
     * <prf>
     * gftModfl().gftPrfviousVbluf()
     * </prf>
     *
     * @rfturn tif prfvious lfgbl vbluf or <dodf>null</dodf>
     *   if onf dofsn't fxist
     * @sff #gftVbluf
     * @sff #gftNfxtVbluf
     * @sff SpinnfrModfl#gftPrfviousVbluf
     */
    publid Objfdt gftPrfviousVbluf() {
        rfturn gftModfl().gftPrfviousVbluf();
    }


    /**
     * Cibngfs tif <dodf>JComponfnt</dodf> tibt displbys tif durrfnt vbluf
     * of tif <dodf>SpinnfrModfl</dodf>.  It is tif rfsponsibility of tiis
     * mftiod to <i>disdonnfdt</i> tif old fditor from tif modfl bnd to
     * donnfdt tif nfw fditor.  Tiis mby mfbn rfmoving tif
     * old fditors <dodf>CibngfListfnfr</dodf> from tif modfl or tif
     * spinnfr itsflf bnd bdding onf for tif nfw fditor.
     *
     * @pbrbm fditor tif nfw fditor
     * @sff #gftEditor
     * @sff #drfbtfEditor
     * @sff #gftModfl
     * @tirows IllfgblArgumfntExdfption if fditor is <dodf>null</dodf>
     *
     * @bfbninfo
     *        bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: JComponfnt tibt displbys tif durrfnt vbluf of tif modfl
     */
    publid void sftEditor(JComponfnt fditor) {
        if (fditor == null) {
            tirow nfw IllfgblArgumfntExdfption("null fditor");
        }
        if (!fditor.fqubls(tiis.fditor)) {
            JComponfnt oldEditor = tiis.fditor;
            tiis.fditor = fditor;
            if (oldEditor instbndfof DffbultEditor) {
                ((DffbultEditor)oldEditor).dismiss(tiis);
            }
            fditorExpliditlySft = truf;
            firfPropfrtyCibngf("fditor", oldEditor, fditor);
            rfvblidbtf();
            rfpbint();
        }
    }


    /**
     * Rfturns tif domponfnt tibt displbys bnd potfntiblly
     * dibngfs tif modfl's vbluf.
     *
     * @rfturn tif domponfnt tibt displbys bnd potfntiblly
     *    dibngfs tif modfl's vbluf
     * @sff #sftEditor
     * @sff #drfbtfEditor
     */
    publid JComponfnt gftEditor() {
        rfturn fditor;
    }


    /**
     * Commits tif durrfntly fditfd vbluf to tif <dodf>SpinnfrModfl</dodf>.
     * <p>
     * If tif fditor is bn instbndf of <dodf>DffbultEditor</dodf>, tif
     * dbll if forwbrdfd to tif fditor, otifrwisf tiis dofs notiing.
     *
     * @tirows PbrsfExdfption if tif durrfntly fditfd vbluf douldn't
     *         bf dommittfd.
     */
    publid void dommitEdit() tirows PbrsfExdfption {
        JComponfnt fditor = gftEditor();
        if (fditor instbndfof DffbultEditor) {
            ((DffbultEditor)fditor).dommitEdit();
        }
    }


    /*
     * Sff rfbdObjfdt bnd writfObjfdt in JComponfnt for morf
     * informbtion bbout sfriblizbtion in Swing.
     *
     * @pbrbm s Strfbm to writf to
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * A simplf bbsf dlbss for morf spfdiblizfd fditors
     * tibt displbys b rfbd-only vifw of tif modfl's durrfnt
     * vbluf witi b <dodf>JFormbttfdTfxtFifld</dodf>.  Subdlbssfs
     * dbn donfigurf tif <dodf>JFormbttfdTfxtFifld</dodf> to drfbtf
     * bn fditor tibt's bppropribtf for tif typf of modfl tify
     * support bnd tify mby wbnt to ovfrridf
     * tif <dodf>stbtfCibngfd</dodf> bnd <dodf>propfrtyCibngfd</dodf>
     * mftiods, wiidi kffp tif modfl bnd tif tfxt fifld in synd.
     * <p>
     * Tiis dlbss dffinfs b <dodf>dismiss</dodf> mftiod tibt rfmovfs tif
     * fditors <dodf>CibngfListfnfr</dodf> from tif <dodf>JSpinnfr</dodf>
     * tibt it's pbrt of.   Tif <dodf>sftEditor</dodf> mftiod knows bbout
     * <dodf>DffbultEditor.dismiss</dodf>, so if tif dfvflopfr
     * rfplbdfs bn fditor tibt's dfrivfd from <dodf>JSpinnfr.DffbultEditor</dodf>
     * its <dodf>CibngfListfnfr</dodf> donnfdtion bbdk to tif
     * <dodf>JSpinnfr</dodf> will bf rfmovfd.  Howfvfr bftfr tibt,
     * it's up to tif dfvflopfr to mbnbgf tifir fditor listfnfrs.
     * Similbrly, if b subdlbss ovfrridfs <dodf>drfbtfEditor</dodf>,
     * it's up to tif subdlbssfr to dfbl witi tifir fditor
     * subsfqufntly bfing rfplbdfd (witi <dodf>sftEditor</dodf>).
     * Wf fxpfdt tibt in most dbsfs, bnd in fditor instbllfd
     * witi <dodf>sftEditor</dodf> or drfbtfd by b <dodf>drfbtfEditor</dodf>
     * ovfrridf, will not bf rfplbdfd bnywby.
     * <p>
     * Tiis dlbss is tif <dodf>LbyoutMbnbgfr</dodf> for it's singlf
     * <dodf>JFormbttfdTfxtFifld</dodf> diild.   By dffbult tif
     * diild is just dfntfrfd witi tif pbrfnts insfts.
     * @sindf 1.4
     */
    publid stbtid dlbss DffbultEditor fxtfnds JPbnfl
        implfmfnts CibngfListfnfr, PropfrtyCibngfListfnfr, LbyoutMbnbgfr
    {
        /**
         * Construdts bn fditor domponfnt for tif spfdififd <dodf>JSpinnfr</dodf>.
         * Tiis <dodf>DffbultEditor</dodf> is it's own lbyout mbnbgfr bnd
         * it is bddfd to tif spinnfr's <dodf>CibngfListfnfr</dodf> list.
         * Tif donstrudtor drfbtfs b singlf <dodf>JFormbttfdTfxtFifld</dodf> diild,
         * initiblizfs it's vbluf to bf tif spinnfr modfl's durrfnt vbluf
         * bnd bdds it to <dodf>tiis</dodf> <dodf>DffbultEditor</dodf>.
         *
         * @pbrbm spinnfr tif spinnfr wiosf modfl <dodf>tiis</dodf> fditor will monitor
         * @sff #gftTfxtFifld
         * @sff JSpinnfr#bddCibngfListfnfr
         */
        publid DffbultEditor(JSpinnfr spinnfr) {
            supfr(null);

            JFormbttfdTfxtFifld ftf = nfw JFormbttfdTfxtFifld();
            ftf.sftNbmf("Spinnfr.formbttfdTfxtFifld");
            ftf.sftVbluf(spinnfr.gftVbluf());
            ftf.bddPropfrtyCibngfListfnfr(tiis);
            ftf.sftEditbblf(fblsf);
            ftf.sftInifritsPopupMfnu(truf);

            String toolTipTfxt = spinnfr.gftToolTipTfxt();
            if (toolTipTfxt != null) {
                ftf.sftToolTipTfxt(toolTipTfxt);
            }

            bdd(ftf);

            sftLbyout(tiis);
            spinnfr.bddCibngfListfnfr(tiis);

            // Wf wbnt tif spinnfr's indrfmfnt/dfdrfmfnt bdtions to bf
            // bdtivf vs tiosf of tif JFormbttfdTfxtFifld. As sudi wf
            // put disbblfd bdtions in tif JFormbttfdTfxtFifld's bdtionmbp.
            // A binding to b disbblfd bdtion is trfbtfd bs b nonfxistbnt
            // binding.
            AdtionMbp ftfMbp = ftf.gftAdtionMbp();

            if (ftfMbp != null) {
                ftfMbp.put("indrfmfnt", DISABLED_ACTION);
                ftfMbp.put("dfdrfmfnt", DISABLED_ACTION);
            }
        }


        /**
         * Disdonnfdt <dodf>tiis</dodf> fditor from tif spfdififd
         * <dodf>JSpinnfr</dodf>.  By dffbult, tiis mftiod rfmovfs
         * itsflf from tif spinnfrs <dodf>CibngfListfnfr</dodf> list.
         *
         * @pbrbm spinnfr tif <dodf>JSpinnfr</dodf> to disdonnfdt tiis
         *    fditor from; tif sbmf spinnfr bs wbs pbssfd to tif donstrudtor.
         */
        publid void dismiss(JSpinnfr spinnfr) {
            spinnfr.rfmovfCibngfListfnfr(tiis);
        }


        /**
         * Rfturns tif <dodf>JSpinnfr</dodf> bndfstor of tiis fditor or
         * <dodf>null</dodf> if nonf of tif bndfstors brf b
         * <dodf>JSpinnfr</dodf>.
         * Typidblly tif fditor's pbrfnt is b <dodf>JSpinnfr</dodf> iowfvfr
         * subdlbssfs of <dodf>JSpinnfr</dodf> mby ovfrridf tif
         * tif <dodf>drfbtfEditor</dodf> mftiod bnd insfrt onf or morf dontbinfrs
         * bftwffn tif <dodf>JSpinnfr</dodf> bnd it's fditor.
         *
         * @rfturn <dodf>JSpinnfr</dodf> bndfstor; <dodf>null</dodf>
         *         if nonf of tif bndfstors brf b <dodf>JSpinnfr</dodf>
         *
         * @sff JSpinnfr#drfbtfEditor
         */
        publid JSpinnfr gftSpinnfr() {
            for (Componfnt d = tiis; d != null; d = d.gftPbrfnt()) {
                if (d instbndfof JSpinnfr) {
                    rfturn (JSpinnfr)d;
                }
            }
            rfturn null;
        }


        /**
         * Rfturns tif <dodf>JFormbttfdTfxtFifld</dodf> diild of tiis
         * fditor.  By dffbult tif tfxt fifld is tif first bnd only
         * diild of fditor.
         *
         * @rfturn tif <dodf>JFormbttfdTfxtFifld</dodf> tibt givfs tif usfr
         *     bddfss to tif <dodf>SpinnfrDbtfModfl's</dodf> vbluf.
         * @sff #gftSpinnfr
         * @sff #gftModfl
         */
        publid JFormbttfdTfxtFifld gftTfxtFifld() {
            rfturn (JFormbttfdTfxtFifld)gftComponfnt(0);
        }


        /**
         * Tiis mftiod is dbllfd wifn tif spinnfr's modfl's stbtf dibngfs.
         * It sfts tif <dodf>vbluf</dodf> of tif tfxt fifld to tif durrfnt
         * vbluf of tif spinnfrs modfl.
         *
         * @pbrbm f tif <dodf>CibngfEvfnt</dodf> wiosf sourdf is tif
         * <dodf>JSpinnfr</dodf> wiosf modfl ibs dibngfd.
         * @sff #gftTfxtFifld
         * @sff JSpinnfr#gftVbluf
         */
        publid void stbtfCibngfd(CibngfEvfnt f) {
            JSpinnfr spinnfr = (JSpinnfr)(f.gftSourdf());
            gftTfxtFifld().sftVbluf(spinnfr.gftVbluf());
        }


        /**
         * Cbllfd by tif <dodf>JFormbttfdTfxtFifld</dodf>
         * <dodf>PropfrtyCibngfListfnfr</dodf>.  Wifn tif <dodf>"vbluf"</dodf>
         * propfrty dibngfs, wiidi implifs tibt tif usfr ibs typfd b nfw
         * numbfr, wf sft tif vbluf of tif spinnfrs modfl.
         * <p>
         * Tiis dlbss ignorfs <dodf>PropfrtyCibngfEvfnts</dodf> wiosf
         * sourdf is not tif <dodf>JFormbttfdTfxtFifld</dodf>, so subdlbssfs
         * mby sbffly mbkf <dodf>tiis</dodf> <dodf>DffbultEditor</dodf> b
         * <dodf>PropfrtyCibngfListfnfr</dodf> on otifr objfdts.
         *
         * @pbrbm f tif <dodf>PropfrtyCibngfEvfnt</dodf> wiosf sourdf is
         *    tif <dodf>JFormbttfdTfxtFifld</dodf> drfbtfd by tiis dlbss.
         * @sff #gftTfxtFifld
         */
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f)
        {
            JSpinnfr spinnfr = gftSpinnfr();

            if (spinnfr == null) {
                // Indidbtfs wf brfn't instbllfd bnywifrf.
                rfturn;
            }

            Objfdt sourdf = f.gftSourdf();
            String nbmf = f.gftPropfrtyNbmf();
            if ((sourdf instbndfof JFormbttfdTfxtFifld) && "vbluf".fqubls(nbmf)) {
                Objfdt lbstVbluf = spinnfr.gftVbluf();

                // Try to sft tif nfw vbluf
                try {
                    spinnfr.sftVbluf(gftTfxtFifld().gftVbluf());
                } dbtdi (IllfgblArgumfntExdfption ibf) {
                    // SpinnfrModfl didn't likf nfw vbluf, rfsft
                    try {
                        ((JFormbttfdTfxtFifld)sourdf).sftVbluf(lbstVbluf);
                    } dbtdi (IllfgblArgumfntExdfption ibf2) {
                        // Still bogus, notiing flsf wf dbn do, tif
                        // SpinnfrModfl bnd JFormbttfdTfxtFifld brf now out
                        // of synd.
                    }
                }
            }
        }


        /**
         * Tiis <dodf>LbyoutMbnbgfr</dodf> mftiod dofs notiing.  Wf'rf
         * only mbnbging b singlf diild bnd tifrf's no support
         * for lbyout donstrbints.
         *
         * @pbrbm nbmf ignorfd
         * @pbrbm diild ignorfd
         */
        publid void bddLbyoutComponfnt(String nbmf, Componfnt diild) {
        }


        /**
         * Tiis <dodf>LbyoutMbnbgfr</dodf> mftiod dofs notiing.  Tifrf
         * isn't bny pfr-diild stbtf.
         *
         * @pbrbm diild ignorfd
         */
        publid void rfmovfLbyoutComponfnt(Componfnt diild) {
        }


        /**
         * Rfturns tif sizf of tif pbrfnts insfts.
         */
        privbtf Dimfnsion insftSizf(Contbinfr pbrfnt) {
            Insfts insfts = pbrfnt.gftInsfts();
            int w = insfts.lfft + insfts.rigit;
            int i = insfts.top + insfts.bottom;
            rfturn nfw Dimfnsion(w, i);
        }


        /**
         * Rfturns tif prfffrrfd sizf of first (bnd only) diild plus tif
         * sizf of tif pbrfnts insfts.
         *
         * @pbrbm pbrfnt tif Contbinfr tibt's mbnbging tif lbyout
         * @rfturn tif prfffrrfd dimfnsions to lby out tif subdomponfnts
         *          of tif spfdififd dontbinfr.
         */
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
            Dimfnsion prfffrrfdSizf = insftSizf(pbrfnt);
            if (pbrfnt.gftComponfntCount() > 0) {
                Dimfnsion diildSizf = gftComponfnt(0).gftPrfffrrfdSizf();
                prfffrrfdSizf.widti += diildSizf.widti;
                prfffrrfdSizf.ifigit += diildSizf.ifigit;
            }
            rfturn prfffrrfdSizf;
        }


        /**
         * Rfturns tif minimum sizf of first (bnd only) diild plus tif
         * sizf of tif pbrfnts insfts.
         *
         * @pbrbm pbrfnt tif Contbinfr tibt's mbnbging tif lbyout
         * @rfturn  tif minimum dimfnsions nffdfd to lby out tif subdomponfnts
         *          of tif spfdififd dontbinfr.
         */
        publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
            Dimfnsion minimumSizf = insftSizf(pbrfnt);
            if (pbrfnt.gftComponfntCount() > 0) {
                Dimfnsion diildSizf = gftComponfnt(0).gftMinimumSizf();
                minimumSizf.widti += diildSizf.widti;
                minimumSizf.ifigit += diildSizf.ifigit;
            }
            rfturn minimumSizf;
        }


        /**
         * Rfsizf tif onf (bnd only) diild to domplftfly fill tif brfb
         * witiin tif pbrfnts insfts.
         */
        publid void lbyoutContbinfr(Contbinfr pbrfnt) {
            if (pbrfnt.gftComponfntCount() > 0) {
                Insfts insfts = pbrfnt.gftInsfts();
                int w = pbrfnt.gftWidti() - (insfts.lfft + insfts.rigit);
                int i = pbrfnt.gftHfigit() - (insfts.top + insfts.bottom);
                gftComponfnt(0).sftBounds(insfts.lfft, insfts.top, w, i);
            }
        }

        /**
         * Pusifs tif durrfntly fditfd vbluf to tif <dodf>SpinnfrModfl</dodf>.
         * <p>
         * Tif dffbult implfmfntbtion invokfs <dodf>dommitEdit</dodf> on tif
         * <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @tirows PbrsfExdfption if tif fditfd vbluf is not lfgbl
         */
        publid void dommitEdit()  tirows PbrsfExdfption {
            // If tif vbluf in tif JFormbttfdTfxtFifld is lfgbl, tiis will ibvf
            // tif rfsult of pusiing tif vbluf to tif SpinnfrModfl
            // by wby of tif <dodf>propfrtyCibngf</dodf> mftiod.
            JFormbttfdTfxtFifld ftf = gftTfxtFifld();

            ftf.dommitEdit();
        }

        /**
         * Rfturns tif bbsflinf.
         *
         * @tirows IllfgblArgumfntExdfption {@inifritDod}
         * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int,int)
         * @sff jbvbx.swing.JComponfnt#gftBbsflinfRfsizfBfibvior()
         * @sindf 1.6
         */
        publid int gftBbsflinf(int widti, int ifigit) {
            // difdk sizf.
            supfr.gftBbsflinf(widti, ifigit);
            Insfts insfts = gftInsfts();
            widti = widti - insfts.lfft - insfts.rigit;
            ifigit = ifigit - insfts.top - insfts.bottom;
            int bbsflinf = gftComponfnt(0).gftBbsflinf(widti, ifigit);
            if (bbsflinf >= 0) {
                rfturn bbsflinf + insfts.top;
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
        publid BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior() {
            rfturn gftComponfnt(0).gftBbsflinfRfsizfBfibvior();
        }
    }




    /**
     * Tiis subdlbss of jbvbx.swing.DbtfFormbttfr mbps tif minimum/mbximum
     * propfrtifs to tif stbrt/fnd propfrtifs of b SpinnfrDbtfModfl.
     */
    privbtf stbtid dlbss DbtfEditorFormbttfr fxtfnds DbtfFormbttfr {
        privbtf finbl SpinnfrDbtfModfl modfl;

        DbtfEditorFormbttfr(SpinnfrDbtfModfl modfl, DbtfFormbt formbt) {
            supfr(formbt);
            tiis.modfl = modfl;
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid void sftMinimum(Compbrbblf<?> min) {
            modfl.sftStbrt((Compbrbblf<Dbtf>)min);
        }

        @Ovfrridf
        publid Compbrbblf<Dbtf> gftMinimum() {
            rfturn  modfl.gftStbrt();
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid void sftMbximum(Compbrbblf<?> mbx) {
            modfl.sftEnd((Compbrbblf<Dbtf>)mbx);
        }

        @Ovfrridf
        publid Compbrbblf<Dbtf> gftMbximum() {
            rfturn modfl.gftEnd();
        }
    }


    /**
     * An fditor for b <dodf>JSpinnfr</dodf> wiosf modfl is b
     * <dodf>SpinnfrDbtfModfl</dodf>.  Tif vbluf of tif fditor is
     * displbyfd witi b <dodf>JFormbttfdTfxtFifld</dodf> wiosf formbt
     * is dffinfd by b <dodf>DbtfFormbttfr</dodf> instbndf wiosf
     * <dodf>minimum</dodf> bnd <dodf>mbximum</dodf> propfrtifs
     * brf mbppfd to tif <dodf>SpinnfrDbtfModfl</dodf>.
     * @sindf 1.4
     */
    // PENDING(imullfr): morf fxbmplf jbvbdod
    publid stbtid dlbss DbtfEditor fxtfnds DffbultEditor
    {
        // Tiis is ifrf until SimplfDbtfFormbt gfts b donstrudtor tibt
        // tbkfs b Lodblf: 4923525
        privbtf stbtid String gftDffbultPbttfrn(Lodblf lod) {
            LodblfProvidfrAdbptfr bdbptfr = LodblfProvidfrAdbptfr.gftAdbptfr(DbtfFormbtProvidfr.dlbss, lod);
            LodblfRfsourdfs lr = bdbptfr.gftLodblfRfsourdfs(lod);
            if (lr == null) {
                lr = LodblfProvidfrAdbptfr.forJRE().gftLodblfRfsourdfs(lod);
            }
            rfturn lr.gftDbtfTimfPbttfrn(DbtfFormbt.SHORT, DbtfFormbt.SHORT, null);
        }

        /**
         * Construdt b <dodf>JSpinnfr</dodf> fditor tibt supports displbying
         * bnd fditing tif vbluf of b <dodf>SpinnfrDbtfModfl</dodf>
         * witi b <dodf>JFormbttfdTfxtFifld</dodf>.  <dodf>Tiis</dodf>
         * <dodf>DbtfEditor</dodf> bfdomfs boti b <dodf>CibngfListfnfr</dodf>
         * on tif spinnfrs modfl bnd b <dodf>PropfrtyCibngfListfnfr</dodf>
         * on tif nfw <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm spinnfr tif spinnfr wiosf modfl <dodf>tiis</dodf> fditor will monitor
         * @fxdfption IllfgblArgumfntExdfption if tif spinnfrs modfl is not
         *     bn instbndf of <dodf>SpinnfrDbtfModfl</dodf>
         *
         * @sff #gftModfl
         * @sff #gftFormbt
         * @sff SpinnfrDbtfModfl
         */
        publid DbtfEditor(JSpinnfr spinnfr) {
            tiis(spinnfr, gftDffbultPbttfrn(spinnfr.gftLodblf()));
        }


        /**
         * Construdt b <dodf>JSpinnfr</dodf> fditor tibt supports displbying
         * bnd fditing tif vbluf of b <dodf>SpinnfrDbtfModfl</dodf>
         * witi b <dodf>JFormbttfdTfxtFifld</dodf>.  <dodf>Tiis</dodf>
         * <dodf>DbtfEditor</dodf> bfdomfs boti b <dodf>CibngfListfnfr</dodf>
         * on tif spinnfr bnd b <dodf>PropfrtyCibngfListfnfr</dodf>
         * on tif nfw <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm spinnfr tif spinnfr wiosf modfl <dodf>tiis</dodf> fditor will monitor
         * @pbrbm dbtfFormbtPbttfrn tif initibl pbttfrn for tif
         *     <dodf>SimplfDbtfFormbt</dodf> objfdt tibt's usfd to displby
         *     bnd pbrsf tif vbluf of tif tfxt fifld.
         * @fxdfption IllfgblArgumfntExdfption if tif spinnfrs modfl is not
         *     bn instbndf of <dodf>SpinnfrDbtfModfl</dodf>
         *
         * @sff #gftModfl
         * @sff #gftFormbt
         * @sff SpinnfrDbtfModfl
         * @sff jbvb.tfxt.SimplfDbtfFormbt
         */
        publid DbtfEditor(JSpinnfr spinnfr, String dbtfFormbtPbttfrn) {
            tiis(spinnfr, nfw SimplfDbtfFormbt(dbtfFormbtPbttfrn,
                                               spinnfr.gftLodblf()));
        }

        /**
         * Construdt b <dodf>JSpinnfr</dodf> fditor tibt supports displbying
         * bnd fditing tif vbluf of b <dodf>SpinnfrDbtfModfl</dodf>
         * witi b <dodf>JFormbttfdTfxtFifld</dodf>.  <dodf>Tiis</dodf>
         * <dodf>DbtfEditor</dodf> bfdomfs boti b <dodf>CibngfListfnfr</dodf>
         * on tif spinnfr bnd b <dodf>PropfrtyCibngfListfnfr</dodf>
         * on tif nfw <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm spinnfr tif spinnfr wiosf modfl <dodf>tiis</dodf> fditor
         *        will monitor
         * @pbrbm formbt <dodf>DbtfFormbt</dodf> objfdt tibt's usfd to displby
         *     bnd pbrsf tif vbluf of tif tfxt fifld.
         * @fxdfption IllfgblArgumfntExdfption if tif spinnfrs modfl is not
         *     bn instbndf of <dodf>SpinnfrDbtfModfl</dodf>
         *
         * @sff #gftModfl
         * @sff #gftFormbt
         * @sff SpinnfrDbtfModfl
         * @sff jbvb.tfxt.SimplfDbtfFormbt
         */
        privbtf DbtfEditor(JSpinnfr spinnfr, DbtfFormbt formbt) {
            supfr(spinnfr);
            if (!(spinnfr.gftModfl() instbndfof SpinnfrDbtfModfl)) {
                tirow nfw IllfgblArgumfntExdfption(
                                 "modfl not b SpinnfrDbtfModfl");
            }

            SpinnfrDbtfModfl modfl = (SpinnfrDbtfModfl)spinnfr.gftModfl();
            DbtfFormbttfr formbttfr = nfw DbtfEditorFormbttfr(modfl, formbt);
            DffbultFormbttfrFbdtory fbdtory = nfw DffbultFormbttfrFbdtory(
                                                  formbttfr);
            JFormbttfdTfxtFifld ftf = gftTfxtFifld();
            ftf.sftEditbblf(truf);
            ftf.sftFormbttfrFbdtory(fbdtory);

            /* TBD - initiblizing tif dolumn widti of tif tfxt fifld
             * is imprfdisf bnd doing it ifrf is tridky bfdbusf
             * tif dfvflopfr mby donfigurf tif formbttfr lbtfr.
             */
            try {
                String mbxString = formbttfr.vblufToString(modfl.gftStbrt());
                String minString = formbttfr.vblufToString(modfl.gftEnd());
                ftf.sftColumns(Mbti.mbx(mbxString.lfngti(),
                                        minString.lfngti()));
            }
            dbtdi (PbrsfExdfption f) {
                // PENDING: imullfr
            }
        }

        /**
         * Rfturns tif <dodf>jbvb.tfxt.SimplfDbtfFormbt</dodf> objfdt tif
         * <dodf>JFormbttfdTfxtFifld</dodf> usfs to pbrsf bnd formbt
         * numbfrs.
         *
         * @rfturn tif vbluf of <dodf>gftTfxtFifld().gftFormbttfr().gftFormbt()</dodf>.
         * @sff #gftTfxtFifld
         * @sff jbvb.tfxt.SimplfDbtfFormbt
         */
        publid SimplfDbtfFormbt gftFormbt() {
            rfturn (SimplfDbtfFormbt)((DbtfFormbttfr)(gftTfxtFifld().gftFormbttfr())).gftFormbt();
        }


        /**
         * Rfturn our spinnfr bndfstor's <dodf>SpinnfrDbtfModfl</dodf>.
         *
         * @rfturn <dodf>gftSpinnfr().gftModfl()</dodf>
         * @sff #gftSpinnfr
         * @sff #gftTfxtFifld
         */
        publid SpinnfrDbtfModfl gftModfl() {
            rfturn (SpinnfrDbtfModfl)(gftSpinnfr().gftModfl());
        }
    }


    /**
     * Tiis subdlbss of jbvbx.swing.NumbfrFormbttfr mbps tif minimum/mbximum
     * propfrtifs to b SpinnfrNumbfrModfl bnd initiblizfs tif vblufClbss
     * of tif NumbfrFormbttfr to mbtdi tif typf of tif initibl modfls vbluf.
     */
    privbtf stbtid dlbss NumbfrEditorFormbttfr fxtfnds NumbfrFormbttfr {
        privbtf finbl SpinnfrNumbfrModfl modfl;

        NumbfrEditorFormbttfr(SpinnfrNumbfrModfl modfl, NumbfrFormbt formbt) {
            supfr(formbt);
            tiis.modfl = modfl;
            sftVblufClbss(modfl.gftVbluf().gftClbss());
        }

        @Ovfrridf
        publid void sftMinimum(Compbrbblf<?> min) {
            modfl.sftMinimum(min);
        }

        @Ovfrridf
        publid Compbrbblf<?> gftMinimum() {
            rfturn  modfl.gftMinimum();
        }

        @Ovfrridf
        publid void sftMbximum(Compbrbblf<?> mbx) {
            modfl.sftMbximum(mbx);
        }

        @Ovfrridf
        publid Compbrbblf<?> gftMbximum() {
            rfturn modfl.gftMbximum();
        }
    }



    /**
     * An fditor for b <dodf>JSpinnfr</dodf> wiosf modfl is b
     * <dodf>SpinnfrNumbfrModfl</dodf>.  Tif vbluf of tif fditor is
     * displbyfd witi b <dodf>JFormbttfdTfxtFifld</dodf> wiosf formbt
     * is dffinfd by b <dodf>NumbfrFormbttfr</dodf> instbndf wiosf
     * <dodf>minimum</dodf> bnd <dodf>mbximum</dodf> propfrtifs
     * brf mbppfd to tif <dodf>SpinnfrNumbfrModfl</dodf>.
     * @sindf 1.4
     */
    // PENDING(imullfr): morf fxbmplf jbvbdod
    publid stbtid dlbss NumbfrEditor fxtfnds DffbultEditor
    {
        // Tiis is ifrf until DfdimblFormbt gfts b donstrudtor tibt
        // tbkfs b Lodblf: 4923525
        privbtf stbtid String gftDffbultPbttfrn(Lodblf lodblf) {
            // Gft tif pbttfrn for tif dffbult lodblf.
            LodblfProvidfrAdbptfr bdbptfr;
            bdbptfr = LodblfProvidfrAdbptfr.gftAdbptfr(NumbfrFormbtProvidfr.dlbss,
                                                       lodblf);
            LodblfRfsourdfs lr = bdbptfr.gftLodblfRfsourdfs(lodblf);
            if (lr == null) {
                lr = LodblfProvidfrAdbptfr.forJRE().gftLodblfRfsourdfs(lodblf);
            }
            String[] bll = lr.gftNumbfrPbttfrns();
            rfturn bll[0];
        }

        /**
         * Construdt b <dodf>JSpinnfr</dodf> fditor tibt supports displbying
         * bnd fditing tif vbluf of b <dodf>SpinnfrNumbfrModfl</dodf>
         * witi b <dodf>JFormbttfdTfxtFifld</dodf>.  <dodf>Tiis</dodf>
         * <dodf>NumbfrEditor</dodf> bfdomfs boti b <dodf>CibngfListfnfr</dodf>
         * on tif spinnfr bnd b <dodf>PropfrtyCibngfListfnfr</dodf>
         * on tif nfw <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm spinnfr tif spinnfr wiosf modfl <dodf>tiis</dodf> fditor will monitor
         * @fxdfption IllfgblArgumfntExdfption if tif spinnfrs modfl is not
         *     bn instbndf of <dodf>SpinnfrNumbfrModfl</dodf>
         *
         * @sff #gftModfl
         * @sff #gftFormbt
         * @sff SpinnfrNumbfrModfl
         */
        publid NumbfrEditor(JSpinnfr spinnfr) {
            tiis(spinnfr, gftDffbultPbttfrn(spinnfr.gftLodblf()));
        }

        /**
         * Construdt b <dodf>JSpinnfr</dodf> fditor tibt supports displbying
         * bnd fditing tif vbluf of b <dodf>SpinnfrNumbfrModfl</dodf>
         * witi b <dodf>JFormbttfdTfxtFifld</dodf>.  <dodf>Tiis</dodf>
         * <dodf>NumbfrEditor</dodf> bfdomfs boti b <dodf>CibngfListfnfr</dodf>
         * on tif spinnfr bnd b <dodf>PropfrtyCibngfListfnfr</dodf>
         * on tif nfw <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm spinnfr tif spinnfr wiosf modfl <dodf>tiis</dodf> fditor will monitor
         * @pbrbm dfdimblFormbtPbttfrn tif initibl pbttfrn for tif
         *     <dodf>DfdimblFormbt</dodf> objfdt tibt's usfd to displby
         *     bnd pbrsf tif vbluf of tif tfxt fifld.
         * @fxdfption IllfgblArgumfntExdfption if tif spinnfrs modfl is not
         *     bn instbndf of <dodf>SpinnfrNumbfrModfl</dodf> or if
         *     <dodf>dfdimblFormbtPbttfrn</dodf> is not b lfgbl
         *     brgumfnt to <dodf>DfdimblFormbt</dodf>
         *
         * @sff #gftTfxtFifld
         * @sff SpinnfrNumbfrModfl
         * @sff jbvb.tfxt.DfdimblFormbt
         */
        publid NumbfrEditor(JSpinnfr spinnfr, String dfdimblFormbtPbttfrn) {
            tiis(spinnfr, nfw DfdimblFormbt(dfdimblFormbtPbttfrn));
        }


        /**
         * Construdt b <dodf>JSpinnfr</dodf> fditor tibt supports displbying
         * bnd fditing tif vbluf of b <dodf>SpinnfrNumbfrModfl</dodf>
         * witi b <dodf>JFormbttfdTfxtFifld</dodf>.  <dodf>Tiis</dodf>
         * <dodf>NumbfrEditor</dodf> bfdomfs boti b <dodf>CibngfListfnfr</dodf>
         * on tif spinnfr bnd b <dodf>PropfrtyCibngfListfnfr</dodf>
         * on tif nfw <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm spinnfr tif spinnfr wiosf modfl <dodf>tiis</dodf> fditor will monitor
         * @pbrbm dfdimblFormbtPbttfrn tif initibl pbttfrn for tif
         *     <dodf>DfdimblFormbt</dodf> objfdt tibt's usfd to displby
         *     bnd pbrsf tif vbluf of tif tfxt fifld.
         * @fxdfption IllfgblArgumfntExdfption if tif spinnfrs modfl is not
         *     bn instbndf of <dodf>SpinnfrNumbfrModfl</dodf>
         *
         * @sff #gftTfxtFifld
         * @sff SpinnfrNumbfrModfl
         * @sff jbvb.tfxt.DfdimblFormbt
         */
        privbtf NumbfrEditor(JSpinnfr spinnfr, DfdimblFormbt formbt) {
            supfr(spinnfr);
            if (!(spinnfr.gftModfl() instbndfof SpinnfrNumbfrModfl)) {
                tirow nfw IllfgblArgumfntExdfption(
                          "modfl not b SpinnfrNumbfrModfl");
            }

            SpinnfrNumbfrModfl modfl = (SpinnfrNumbfrModfl)spinnfr.gftModfl();
            NumbfrFormbttfr formbttfr = nfw NumbfrEditorFormbttfr(modfl,
                                                                  formbt);
            DffbultFormbttfrFbdtory fbdtory = nfw DffbultFormbttfrFbdtory(
                                                  formbttfr);
            JFormbttfdTfxtFifld ftf = gftTfxtFifld();
            ftf.sftEditbblf(truf);
            ftf.sftFormbttfrFbdtory(fbdtory);
            // Cibngf tif tfxt orifntbtion for tif NumbfrEditor
            ftf.sftHorizontblAlignmfnt(JTfxtFifld.RIGHT);

            /* TBD - initiblizing tif dolumn widti of tif tfxt fifld
             * is imprfdisf bnd doing it ifrf is tridky bfdbusf
             * tif dfvflopfr mby donfigurf tif formbttfr lbtfr.
             */
            try {
                String mbxString = formbttfr.vblufToString(modfl.gftMinimum());
                String minString = formbttfr.vblufToString(modfl.gftMbximum());
                ftf.sftColumns(Mbti.mbx(mbxString.lfngti(),
                                        minString.lfngti()));
            }
            dbtdi (PbrsfExdfption f) {
                // TBD siould tirow b dibinfd frror ifrf
            }

        }


        /**
         * Rfturns tif <dodf>jbvb.tfxt.DfdimblFormbt</dodf> objfdt tif
         * <dodf>JFormbttfdTfxtFifld</dodf> usfs to pbrsf bnd formbt
         * numbfrs.
         *
         * @rfturn tif vbluf of <dodf>gftTfxtFifld().gftFormbttfr().gftFormbt()</dodf>.
         * @sff #gftTfxtFifld
         * @sff jbvb.tfxt.DfdimblFormbt
         */
        publid DfdimblFormbt gftFormbt() {
            rfturn (DfdimblFormbt)((NumbfrFormbttfr)(gftTfxtFifld().gftFormbttfr())).gftFormbt();
        }


        /**
         * Rfturn our spinnfr bndfstor's <dodf>SpinnfrNumbfrModfl</dodf>.
         *
         * @rfturn <dodf>gftSpinnfr().gftModfl()</dodf>
         * @sff #gftSpinnfr
         * @sff #gftTfxtFifld
         */
        publid SpinnfrNumbfrModfl gftModfl() {
            rfturn (SpinnfrNumbfrModfl)(gftSpinnfr().gftModfl());
        }

        /**
         * {@inifritDod}
         */
        @Ovfrridf
        publid void sftComponfntOrifntbtion(ComponfntOrifntbtion o) {
            supfr.sftComponfntOrifntbtion(o);
            gftTfxtFifld().sftHorizontblAlignmfnt(
                    o.isLfftToRigit() ? JTfxtFifld.RIGHT : JTfxtFifld.LEFT);
        }
    }


    /**
     * An fditor for b <dodf>JSpinnfr</dodf> wiosf modfl is b
     * <dodf>SpinnfrListModfl</dodf>.
     * @sindf 1.4
     */
    publid stbtid dlbss ListEditor fxtfnds DffbultEditor
    {
        /**
         * Construdt b <dodf>JSpinnfr</dodf> fditor tibt supports displbying
         * bnd fditing tif vbluf of b <dodf>SpinnfrListModfl</dodf>
         * witi b <dodf>JFormbttfdTfxtFifld</dodf>.  <dodf>Tiis</dodf>
         * <dodf>ListEditor</dodf> bfdomfs boti b <dodf>CibngfListfnfr</dodf>
         * on tif spinnfr bnd b <dodf>PropfrtyCibngfListfnfr</dodf>
         * on tif nfw <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm spinnfr tif spinnfr wiosf modfl <dodf>tiis</dodf> fditor will monitor
         * @fxdfption IllfgblArgumfntExdfption if tif spinnfrs modfl is not
         *     bn instbndf of <dodf>SpinnfrListModfl</dodf>
         *
         * @sff #gftModfl
         * @sff SpinnfrListModfl
         */
        publid ListEditor(JSpinnfr spinnfr) {
            supfr(spinnfr);
            if (!(spinnfr.gftModfl() instbndfof SpinnfrListModfl)) {
                tirow nfw IllfgblArgumfntExdfption("modfl not b SpinnfrListModfl");
            }
            gftTfxtFifld().sftEditbblf(truf);
            gftTfxtFifld().sftFormbttfrFbdtory(nfw
                              DffbultFormbttfrFbdtory(nfw ListFormbttfr()));
        }

        /**
         * Rfturn our spinnfr bndfstor's <dodf>SpinnfrNumbfrModfl</dodf>.
         *
         * @rfturn <dodf>gftSpinnfr().gftModfl()</dodf>
         * @sff #gftSpinnfr
         * @sff #gftTfxtFifld
         */
        publid SpinnfrListModfl gftModfl() {
            rfturn (SpinnfrListModfl)(gftSpinnfr().gftModfl());
        }


        /**
         * ListFormbttfr providfs domplftion wiilf tfxt is bfing input
         * into tif JFormbttfdTfxtFifld. Complftion is only donf if tif
         * usfr is insfrting tfxt bt tif fnd of tif dodumfnt. Complftion
         * is donf by wby of tif SpinnfrListModfl mftiod findNfxtMbtdi.
         */
        privbtf dlbss ListFormbttfr fxtfnds
                          JFormbttfdTfxtFifld.AbstrbdtFormbttfr {
            privbtf DodumfntFiltfr filtfr;

            publid String vblufToString(Objfdt vbluf) tirows PbrsfExdfption {
                if (vbluf == null) {
                    rfturn "";
                }
                rfturn vbluf.toString();
            }

            publid Objfdt stringToVbluf(String string) tirows PbrsfExdfption {
                rfturn string;
            }

            protfdtfd DodumfntFiltfr gftDodumfntFiltfr() {
                if (filtfr == null) {
                    filtfr = nfw Filtfr();
                }
                rfturn filtfr;
            }


            privbtf dlbss Filtfr fxtfnds DodumfntFiltfr {
                publid void rfplbdf(FiltfrBypbss fb, int offsft, int lfngti,
                                    String string, AttributfSft bttrs) tirows
                                           BbdLodbtionExdfption {
                    if (string != null && (offsft + lfngti) ==
                                          fb.gftDodumfnt().gftLfngti()) {
                        Objfdt nfxt = gftModfl().findNfxtMbtdi(
                                         fb.gftDodumfnt().gftTfxt(0, offsft) +
                                         string);
                        String vbluf = (nfxt != null) ? nfxt.toString() : null;

                        if (vbluf != null) {
                            fb.rfmovf(0, offsft + lfngti);
                            fb.insfrtString(0, vbluf, null);
                            gftFormbttfdTfxtFifld().sflfdt(offsft +
                                                           string.lfngti(),
                                                           vbluf.lfngti());
                            rfturn;
                        }
                    }
                    supfr.rfplbdf(fb, offsft, lfngti, string, bttrs);
                }

                publid void insfrtString(FiltfrBypbss fb, int offsft,
                                     String string, AttributfSft bttr)
                       tirows BbdLodbtionExdfption {
                    rfplbdf(fb, offsft, 0, string, bttr);
                }
            }
        }
    }


    /**
     * An Adtion implfmfntbtion tibt is blwbys disbblfd.
     */
    privbtf stbtid dlbss DisbblfdAdtion implfmfnts Adtion {
        publid Objfdt gftVbluf(String kfy) {
            rfturn null;
        }
        publid void putVbluf(String kfy, Objfdt vbluf) {
        }
        publid void sftEnbblfd(boolfbn b) {
        }
        publid boolfbn isEnbblfd() {
            rfturn fblsf;
        }
        publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
        }
        publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
        }
        publid void bdtionPfrformfd(AdtionEvfnt bf) {
        }
    }

    /////////////////
    // Addfssibility support
    ////////////////

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> for tif <dodf>JSpinnfr</dodf>
     *
     * @rfturn tif <dodf>AddfssiblfContfxt</dodf> for tif <dodf>JSpinnfr</dodf>
     * @sindf 1.5
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJSpinnfr();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * <dodf>AddfssiblfJSpinnfr</dodf> implfmfnts bddfssibility
     * support for tif <dodf>JSpinnfr</dodf> dlbss.
     * @sindf 1.5
     */
    protfdtfd dlbss AddfssiblfJSpinnfr fxtfnds AddfssiblfJComponfnt
        implfmfnts AddfssiblfVbluf, AddfssiblfAdtion, AddfssiblfTfxt,
                   AddfssiblfEditbblfTfxt, CibngfListfnfr {

        privbtf Objfdt oldModflVbluf = null;

        /**
         * AddfssiblfJSpinnfr donstrudtor
         */
        protfdtfd AddfssiblfJSpinnfr() {
            // modfl is gubrbntffd to bf non-null
            oldModflVbluf = modfl.gftVbluf();
            JSpinnfr.tiis.bddCibngfListfnfr(tiis);
        }

        /**
         * Invokfd wifn tif tbrgft of tif listfnfr ibs dibngfd its stbtf.
         *
         * @pbrbm f  b <dodf>CibngfEvfnt</dodf> objfdt. Must not bf null.
         * @tirows NullPointfrExdfption if tif pbrbmftfr is null.
         */
        publid void stbtfCibngfd(CibngfEvfnt f) {
            if (f == null) {
                tirow nfw NullPointfrExdfption();
            }
            Objfdt nfwModflVbluf = modfl.gftVbluf();
            firfPropfrtyCibngf(ACCESSIBLE_VALUE_PROPERTY,
                               oldModflVbluf,
                               nfwModflVbluf);
            firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY,
                               null,
                               0); // fntirf tfxt mby ibvf dibngfd

            oldModflVbluf = nfwModflVbluf;
        }

        /* ===== Bfgin AddfssiblfContfxt mftiods ===== */

        /**
         * Gfts tif rolf of tiis objfdt.  Tif rolf of tif objfdt is tif gfnfrid
         * purposf or usf of tif dlbss of tiis objfdt.  For fxbmplf, tif rolf
         * of b pusi button is AddfssiblfRolf.PUSH_BUTTON.  Tif rolfs in
         * AddfssiblfRolf brf providfd so domponfnt dfvflopfrs dbn pidk from
         * b sft of prfdffinfd rolfs.  Tiis fnbblfs bssistivf tfdinologifs to
         * providf b donsistfnt intfrfbdf to vbrious twfbkfd subdlbssfs of
         * domponfnts (f.g., usf AddfssiblfRolf.PUSH_BUTTON for bll domponfnts
         * tibt bdt likf b pusi button) bs wfll bs distinguisi bftwffn subdlbssfs
         * tibt bfibvf difffrfntly (f.g., AddfssiblfRolf.CHECK_BOX for difdk boxfs
         * bnd AddfssiblfRolf.RADIO_BUTTON for rbdio buttons).
         * <p>Notf tibt tif AddfssiblfRolf dlbss is blso fxtfnsiblf, so
         * dustom domponfnt dfvflopfrs dbn dffinf tifir own AddfssiblfRolf's
         * if tif sft of prfdffinfd rolfs is inbdfqubtf.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.SPIN_BOX;
        }

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn of tif objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn of tif objfdt.
         */
        publid int gftAddfssiblfCiildrfnCount() {
            // tif JSpinnfr ibs onf diild, tif fditor
            if (fditor.gftAddfssiblfContfxt() != null) {
                rfturn 1;
            }
            rfturn 0;
        }

        /**
         * Rfturns tif spfdififd Addfssiblf diild of tif objfdt.  Tif Addfssiblf
         * diildrfn of bn Addfssiblf objfdt brf zfro-bbsfd, so tif first diild
         * of bn Addfssiblf diild is bt indfx 0, tif sfdond diild is bt indfx 1,
         * bnd so on.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif Addfssiblf diild of tif objfdt
         * @sff #gftAddfssiblfCiildrfnCount
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            // tif JSpinnfr ibs onf diild, tif fditor
            if (i != 0) {
                rfturn null;
            }
            if (fditor.gftAddfssiblfContfxt() != null) {
                rfturn (Addfssiblf)fditor;
            }
            rfturn null;
        }

        /* ===== End AddfssiblfContfxt mftiods ===== */

        /**
         * Gfts tif AddfssiblfAdtion bssodibtfd witi tiis objfdt tibt supports
         * onf or morf bdtions.
         *
         * @rfturn AddfssiblfAdtion if supportfd by objfdt; flsf rfturn null
         * @sff AddfssiblfAdtion
         */
        publid AddfssiblfAdtion gftAddfssiblfAdtion() {
            rfturn tiis;
        }

        /**
         * Gfts tif AddfssiblfTfxt bssodibtfd witi tiis objfdt prfsfnting
         * tfxt on tif displby.
         *
         * @rfturn AddfssiblfTfxt if supportfd by objfdt; flsf rfturn null
         * @sff AddfssiblfTfxt
         */
        publid AddfssiblfTfxt gftAddfssiblfTfxt() {
            rfturn tiis;
        }

        /*
         * Rfturns tif AddfssiblfContfxt for tif JSpinnfr fditor
         */
        privbtf AddfssiblfContfxt gftEditorAddfssiblfContfxt() {
            if (fditor instbndfof DffbultEditor) {
                JTfxtFifld tfxtFifld = ((DffbultEditor)fditor).gftTfxtFifld();
                if (tfxtFifld != null) {
                    rfturn tfxtFifld.gftAddfssiblfContfxt();
                }
            } flsf if (fditor instbndfof Addfssiblf) {
                rfturn fditor.gftAddfssiblfContfxt();
            }
            rfturn null;
        }

        /*
         * Rfturns tif AddfssiblfTfxt for tif JSpinnfr fditor
         */
        privbtf AddfssiblfTfxt gftEditorAddfssiblfTfxt() {
            AddfssiblfContfxt bd = gftEditorAddfssiblfContfxt();
            if (bd != null) {
                rfturn bd.gftAddfssiblfTfxt();
            }
            rfturn null;
        }

        /*
         * Rfturns tif AddfssiblfEditbblfTfxt for tif JSpinnfr fditor
         */
        privbtf AddfssiblfEditbblfTfxt gftEditorAddfssiblfEditbblfTfxt() {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt instbndfof AddfssiblfEditbblfTfxt) {
                rfturn (AddfssiblfEditbblfTfxt)bt;
            }
            rfturn null;
        }

        /**
         * Gfts tif AddfssiblfVbluf bssodibtfd witi tiis objfdt.
         *
         * @rfturn AddfssiblfVbluf if supportfd by objfdt; flsf rfturn null
         * @sff AddfssiblfVbluf
         *
         */
        publid AddfssiblfVbluf gftAddfssiblfVbluf() {
            rfturn tiis;
        }

        /* ===== Bfgin AddfssiblfVbluf impl ===== */

        /**
         * Gft tif vbluf of tiis objfdt bs b Numbfr.  If tif vbluf ibs not bffn
         * sft, tif rfturn vbluf will bf null.
         *
         * @rfturn vbluf of tif objfdt
         * @sff #sftCurrfntAddfssiblfVbluf
         */
        publid Numbfr gftCurrfntAddfssiblfVbluf() {
            Objfdt o = modfl.gftVbluf();
            if (o instbndfof Numbfr) {
                rfturn (Numbfr)o;
            }
            rfturn null;
        }

        /**
         * Sft tif vbluf of tiis objfdt bs b Numbfr.
         *
         * @pbrbm n tif vbluf to sft for tiis objfdt
         * @rfturn truf if tif vbluf wbs sft; flsf Fblsf
         * @sff #gftCurrfntAddfssiblfVbluf
         */
        publid boolfbn sftCurrfntAddfssiblfVbluf(Numbfr n) {
            // try to sft tif nfw vbluf
            try {
                modfl.sftVbluf(n);
                rfturn truf;
            } dbtdi (IllfgblArgumfntExdfption ibf) {
                // SpinnfrModfl didn't likf nfw vbluf
            }
            rfturn fblsf;
        }

        /**
         * Gft tif minimum vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Minimum vbluf of tif objfdt; null if tiis objfdt dofs not
         * ibvf b minimum vbluf
         * @sff #gftMbximumAddfssiblfVbluf
         */
        publid Numbfr gftMinimumAddfssiblfVbluf() {
            if (modfl instbndfof SpinnfrNumbfrModfl) {
                SpinnfrNumbfrModfl numbfrModfl = (SpinnfrNumbfrModfl)modfl;
                Objfdt o = numbfrModfl.gftMinimum();
                if (o instbndfof Numbfr) {
                    rfturn (Numbfr)o;
                }
            }
            rfturn null;
        }

        /**
         * Gft tif mbximum vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Mbximum vbluf of tif objfdt; null if tiis objfdt dofs not
         * ibvf b mbximum vbluf
         * @sff #gftMinimumAddfssiblfVbluf
         */
        publid Numbfr gftMbximumAddfssiblfVbluf() {
            if (modfl instbndfof SpinnfrNumbfrModfl) {
                SpinnfrNumbfrModfl numbfrModfl = (SpinnfrNumbfrModfl)modfl;
                Objfdt o = numbfrModfl.gftMbximum();
                if (o instbndfof Numbfr) {
                    rfturn (Numbfr)o;
                }
            }
            rfturn null;
        }

        /* ===== End AddfssiblfVbluf impl ===== */

        /* ===== Bfgin AddfssiblfAdtion impl ===== */

        /**
         * Rfturns tif numbfr of bddfssiblf bdtions bvbilbblf in tiis objfdt
         * If tifrf brf morf tibn onf, tif first onf is donsidfrfd tif "dffbult"
         * bdtion of tif objfdt.
         *
         * Two bdtions brf supportfd: AddfssiblfAdtion.INCREMENT wiidi
         * indrfmfnts tif spinnfr vbluf bnd AddfssiblfAdtion.DECREMENT
         * wiidi dfdrfmfnts tif spinnfr vbluf
         *
         * @rfturn tif zfro-bbsfd numbfr of Adtions in tiis objfdt
         */
        publid int gftAddfssiblfAdtionCount() {
            rfturn 2;
        }

        /**
         * Rfturns b dfsdription of tif spfdififd bdtion of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of tif bdtions
         * @rfturn b String dfsdription of tif bdtion
         * @sff #gftAddfssiblfAdtionCount
         */
        publid String gftAddfssiblfAdtionDfsdription(int i) {
            if (i == 0) {
                rfturn AddfssiblfAdtion.INCREMENT;
            } flsf if (i == 1) {
                rfturn AddfssiblfAdtion.DECREMENT;
            }
            rfturn null;
        }

        /**
         * Pfrforms tif spfdififd Adtion on tif objfdt
         *
         * @pbrbm i zfro-bbsfd indfx of bdtions. Tif first bdtion
         * (indfx 0) is AddfssiblfAdtion.INCREMENT bnd tif sfdond
         * bdtion (indfx 1) is AddfssiblfAdtion.DECREMENT.
         * @rfturn truf if tif bdtion wbs pfrformfd; otifrwisf fblsf.
         * @sff #gftAddfssiblfAdtionCount
         */
        publid boolfbn doAddfssiblfAdtion(int i) {
            if (i < 0 || i > 1) {
                rfturn fblsf;
            }
            Objfdt o;
            if (i == 0) {
                o = gftNfxtVbluf(); // AddfssiblfAdtion.INCREMENT
            } flsf {
                o = gftPrfviousVbluf(); // AddfssiblfAdtion.DECREMENT
            }
            // try to sft tif nfw vbluf
            try {
                modfl.sftVbluf(o);
                rfturn truf;
            } dbtdi (IllfgblArgumfntExdfption ibf) {
                // SpinnfrModfl didn't likf nfw vbluf
            }
            rfturn fblsf;
        }

        /* ===== End AddfssiblfAdtion impl ===== */

        /* ===== Bfgin AddfssiblfTfxt impl ===== */

        /*
         * Rfturns wiftifr sourdf bnd dfstinbtion domponfnts ibvf tif
         * sbmf window bndfstor
         */
        privbtf boolfbn sbmfWindowAndfstor(Componfnt srd, Componfnt dfst) {
            if (srd == null || dfst == null) {
                rfturn fblsf;
            }
            rfturn SwingUtilitifs.gftWindowAndfstor(srd) ==
                SwingUtilitifs.gftWindowAndfstor(dfst);
        }

        /**
         * Givfn b point in lodbl doordinbtfs, rfturn tif zfro-bbsfd indfx
         * of tif dibrbdtfr undfr tibt Point.  If tif point is invblid,
         * tiis mftiod rfturns -1.
         *
         * @pbrbm p tif Point in lodbl doordinbtfs
         * @rfturn tif zfro-bbsfd indfx of tif dibrbdtfr undfr Point p; if
         * Point is invblid rfturn -1.
         */
        publid int gftIndfxAtPoint(Point p) {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null && sbmfWindowAndfstor(JSpinnfr.tiis, fditor)) {
                // donvfrt point from tif JSpinnfr bounds (sourdf) to
                // fditor bounds (dfstinbtion)
                Point fditorPoint = SwingUtilitifs.donvfrtPoint(JSpinnfr.tiis,
                                                                p,
                                                                fditor);
                if (fditorPoint != null) {
                    rfturn bt.gftIndfxAtPoint(fditorPoint);
                }
            }
            rfturn -1;
        }

        /**
         * Dftfrminfs tif bounding box of tif dibrbdtfr bt tif givfn
         * indfx into tif string.  Tif bounds brf rfturnfd in lodbl
         * doordinbtfs.  If tif indfx is invblid bn fmpty rfdtbnglf is
         * rfturnfd.
         *
         * @pbrbm i tif indfx into tif String
         * @rfturn tif sdrffn doordinbtfs of tif dibrbdtfr's bounding box,
         * if indfx is invblid rfturn bn fmpty rfdtbnglf.
         */
        publid Rfdtbnglf gftCibrbdtfrBounds(int i) {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null ) {
                Rfdtbnglf fditorRfdt = bt.gftCibrbdtfrBounds(i);
                if (fditorRfdt != null &&
                    sbmfWindowAndfstor(JSpinnfr.tiis, fditor)) {
                    // rfturn rfdtbnglf in tif tif JSpinnfr bounds
                    rfturn SwingUtilitifs.donvfrtRfdtbnglf(fditor,
                                                           fditorRfdt,
                                                           JSpinnfr.tiis);
                }
            }
            rfturn null;
        }

        /**
         * Rfturns tif numbfr of dibrbdtfrs (vblid indidifs)
         *
         * @rfturn tif numbfr of dibrbdtfrs
         */
        publid int gftCibrCount() {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftCibrCount();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif zfro-bbsfd offsft of tif dbrft.
         *
         * Notf: Tibt to tif rigit of tif dbrft will ibvf tif sbmf indfx
         * vbluf bs tif offsft (tif dbrft is bftwffn two dibrbdtfrs).
         * @rfturn tif zfro-bbsfd offsft of tif dbrft.
         */
        publid int gftCbrftPosition() {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftCbrftPosition();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif String bt b givfn indfx.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf
         */
        publid String gftAtIndfx(int pbrt, int indfx) {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftAtIndfx(pbrt, indfx);
            }
            rfturn null;
        }

        /**
         * Rfturns tif String bftfr b givfn indfx.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf
         */
        publid String gftAftfrIndfx(int pbrt, int indfx) {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftAftfrIndfx(pbrt, indfx);
            }
            rfturn null;
        }

        /**
         * Rfturns tif String bfforf b givfn indfx.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf
         */
        publid String gftBfforfIndfx(int pbrt, int indfx) {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftBfforfIndfx(pbrt, indfx);
            }
            rfturn null;
        }

        /**
         * Rfturns tif AttributfSft for b givfn dibrbdtfr bt b givfn indfx
         *
         * @pbrbm i tif zfro-bbsfd indfx into tif tfxt
         * @rfturn tif AttributfSft of tif dibrbdtfr
         */
        publid AttributfSft gftCibrbdtfrAttributf(int i) {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftCibrbdtfrAttributf(i);
            }
            rfturn null;
        }

        /**
         * Rfturns tif stbrt offsft witiin tif sflfdtfd tfxt.
         * If tifrf is no sflfdtion, but tifrf is
         * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
         *
         * @rfturn tif indfx into tif tfxt of tif stbrt of tif sflfdtion
         */
        publid int gftSflfdtionStbrt() {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftSflfdtionStbrt();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif fnd offsft witiin tif sflfdtfd tfxt.
         * If tifrf is no sflfdtion, but tifrf is
         * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
         *
         * @rfturn tif indfx into tif tfxt of tif fnd of tif sflfdtion
         */
        publid int gftSflfdtionEnd() {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftSflfdtionEnd();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif portion of tif tfxt tibt is sflfdtfd.
         *
         * @rfturn tif String portion of tif tfxt tibt is sflfdtfd
         */
        publid String gftSflfdtfdTfxt() {
            AddfssiblfTfxt bt = gftEditorAddfssiblfTfxt();
            if (bt != null) {
                rfturn bt.gftSflfdtfdTfxt();
            }
            rfturn null;
        }

        /* ===== End AddfssiblfTfxt impl ===== */


        /* ===== Bfgin AddfssiblfEditbblfTfxt impl ===== */

        /**
         * Sfts tif tfxt dontfnts to tif spfdififd string.
         *
         * @pbrbm s tif string to sft tif tfxt dontfnts
         */
        publid void sftTfxtContfnts(String s) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                bt.sftTfxtContfnts(s);
            }
        }

        /**
         * Insfrts tif spfdififd string bt tif givfn indfx/
         *
         * @pbrbm indfx tif indfx in tif tfxt wifrf tif string will
         * bf insfrtfd
         * @pbrbm s tif string to insfrt in tif tfxt
         */
        publid void insfrtTfxtAtIndfx(int indfx, String s) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                bt.insfrtTfxtAtIndfx(indfx, s);
            }
        }

        /**
         * Rfturns tif tfxt string bftwffn two indidfs.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @rfturn tif tfxt string bftwffn tif indidfs
         */
        publid String gftTfxtRbngf(int stbrtIndfx, int fndIndfx) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                rfturn bt.gftTfxtRbngf(stbrtIndfx, fndIndfx);
            }
            rfturn null;
        }

        /**
         * Dflftfs tif tfxt bftwffn two indidfs
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         */
        publid void dflftf(int stbrtIndfx, int fndIndfx) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                bt.dflftf(stbrtIndfx, fndIndfx);
            }
        }

        /**
         * Cuts tif tfxt bftwffn two indidfs into tif systfm dlipbobrd.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         */
        publid void dut(int stbrtIndfx, int fndIndfx) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                bt.dut(stbrtIndfx, fndIndfx);
            }
        }

        /**
         * Pbstfs tif tfxt from tif systfm dlipbobrd into tif tfxt
         * stbrting bt tif spfdififd indfx.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         */
        publid void pbstf(int stbrtIndfx) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                bt.pbstf(stbrtIndfx);
            }
        }

        /**
         * Rfplbdfs tif tfxt bftwffn two indidfs witi tif spfdififd
         * string.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @pbrbm s tif string to rfplbdf tif tfxt bftwffn two indidfs
         */
        publid void rfplbdfTfxt(int stbrtIndfx, int fndIndfx, String s) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                bt.rfplbdfTfxt(stbrtIndfx, fndIndfx, s);
            }
        }

        /**
         * Sflfdts tif tfxt bftwffn two indidfs.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         */
        publid void sflfdtTfxt(int stbrtIndfx, int fndIndfx) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                bt.sflfdtTfxt(stbrtIndfx, fndIndfx);
            }
        }

        /**
         * Sfts bttributfs for tif tfxt bftwffn two indidfs.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @pbrbm bs tif bttributf sft
         * @sff AttributfSft
         */
        publid void sftAttributfs(int stbrtIndfx, int fndIndfx, AttributfSft bs) {
            AddfssiblfEditbblfTfxt bt = gftEditorAddfssiblfEditbblfTfxt();
            if (bt != null) {
                bt.sftAttributfs(stbrtIndfx, fndIndfx, bs);
            }
        }
    }  /* End AddfssiblfJSpinnfr */
}
