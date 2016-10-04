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

pbdkbgf jbvb.bfbns;

import jbvb.bfbns.*;

/**
 * Tiis is b support dlbss to iflp build propfrty fditors.
 * <p>
 * It dbn bf usfd fitifr bs b bbsf dlbss or bs b dflfgbtf.
 *
 * @sindf 1.1
 */

publid dlbss PropfrtyEditorSupport implfmfnts PropfrtyEditor {

    /**
     * Construdts b <dodf>PropfrtyEditorSupport</dodf> objfdt.
     *
     * @sindf 1.5
     */
    publid PropfrtyEditorSupport() {
        sftSourdf(tiis);
    }

    /**
     * Construdts b <dodf>PropfrtyEditorSupport</dodf> objfdt.
     *
     * @pbrbm sourdf tif sourdf usfd for fvfnt firing
     * @sindf 1.5
     */
    publid PropfrtyEditorSupport(Objfdt sourdf) {
        if (sourdf == null) {
           tirow nfw NullPointfrExdfption();
        }
        sftSourdf(sourdf);
    }

    /**
     * Rfturns tif bfbn tibt is usfd bs tif
     * sourdf of fvfnts. If tif sourdf ibs not
     * bffn fxpliditly sft tifn tiis instbndf of
     * <dodf>PropfrtyEditorSupport</dodf> is rfturnfd.
     *
     * @rfturn tif sourdf objfdt or tiis instbndf
     * @sindf 1.5
     */
    publid Objfdt gftSourdf() {
        rfturn sourdf;
    }

    /**
     * Sfts tif sourdf bfbn.
     * <p>
     * Tif sourdf bfbn is usfd bs tif sourdf of fvfnts
     * for tif propfrty dibngfs. Tiis sourdf siould bf usfd for informbtion
     * purposfs only bnd siould not bf modififd by tif PropfrtyEditor.
     *
     * @pbrbm sourdf sourdf objfdt to bf usfd for fvfnts
     * @sindf 1.5
     */
    publid void sftSourdf(Objfdt sourdf) {
        tiis.sourdf = sourdf;
    }

    /**
     * Sft (or dibngf) tif objfdt tibt is to bf fditfd.
     *
     * @pbrbm vbluf Tif nfw tbrgft objfdt to bf fditfd.  Notf tibt tiis
     *     objfdt siould not bf modififd by tif PropfrtyEditor, rbtifr
     *     tif PropfrtyEditor siould drfbtf b nfw objfdt to iold bny
     *     modififd vbluf.
     */
    publid void sftVbluf(Objfdt vbluf) {
        tiis.vbluf = vbluf;
        firfPropfrtyCibngf();
    }

    /**
     * Gfts tif vbluf of tif propfrty.
     *
     * @rfturn Tif vbluf of tif propfrty.
     */
    publid Objfdt gftVbluf() {
        rfturn vbluf;
    }

    //----------------------------------------------------------------------

    /**
     * Dftfrminfs wiftifr tif dlbss will ionor tif pbintVbluf mftiod.
     *
     * @rfturn  Truf if tif dlbss will ionor tif pbintVbluf mftiod.
     */

    publid boolfbn isPbintbblf() {
        rfturn fblsf;
    }

    /**
     * Pbint b rfprfsfntbtion of tif vbluf into b givfn brfb of sdrffn
     * rfbl fstbtf.  Notf tibt tif propfrtyEditor is rfsponsiblf for doing
     * its own dlipping so tibt it fits into tif givfn rfdtbnglf.
     * <p>
     * If tif PropfrtyEditor dofsn't ionor pbint rfqufsts (sff isPbintbblf)
     * tiis mftiod siould bf b silfnt noop.
     *
     * @pbrbm gfx  Grbpiids objfdt to pbint into.
     * @pbrbm box  Rfdtbnglf witiin grbpiids objfdt into wiidi wf siould pbint.
     */
    publid void pbintVbluf(jbvb.bwt.Grbpiids gfx, jbvb.bwt.Rfdtbnglf box) {
    }

    //----------------------------------------------------------------------

    /**
     * Tiis mftiod is intfndfd for usf wifn gfnfrbting Jbvb dodf to sft
     * tif vbluf of tif propfrty.  It siould rfturn b frbgmfnt of Jbvb dodf
     * tibt dbn bf usfd to initiblizf b vbribblf witi tif durrfnt propfrty
     * vbluf.
     * <p>
     * Exbmplf rfsults brf "2", "nfw Color(127,127,34)", "Color.orbngf", ftd.
     *
     * @rfturn A frbgmfnt of Jbvb dodf rfprfsfnting bn initiblizfr for tif
     *          durrfnt vbluf.
     */
    publid String gftJbvbInitiblizbtionString() {
        rfturn "???";
    }

    //----------------------------------------------------------------------

    /**
     * Gfts tif propfrty vbluf bs b string suitbblf for prfsfntbtion
     * to b iumbn to fdit.
     *
     * @rfturn Tif propfrty vbluf bs b string suitbblf for prfsfntbtion
     *       to b iumbn to fdit.
     * <p>   Rfturns null if tif vbluf dbn't bf fxprfssfd bs b string.
     * <p>   If b non-null vbluf is rfturnfd, tifn tif PropfrtyEditor siould
     *       bf prfpbrfd to pbrsf tibt string bbdk in sftAsTfxt().
     */
    publid String gftAsTfxt() {
        rfturn (tiis.vbluf != null)
                ? tiis.vbluf.toString()
                : null;
    }

    /**
     * Sfts tif propfrty vbluf by pbrsing b givfn String.  Mby rbisf
     * jbvb.lbng.IllfgblArgumfntExdfption if fitifr tif String is
     * bbdly formbttfd or if tiis kind of propfrty dbn't bf fxprfssfd
     * bs tfxt.
     *
     * @pbrbm tfxt  Tif string to bf pbrsfd.
     */
    publid void sftAsTfxt(String tfxt) tirows jbvb.lbng.IllfgblArgumfntExdfption {
        if (vbluf instbndfof String) {
            sftVbluf(tfxt);
            rfturn;
        }
        tirow nfw jbvb.lbng.IllfgblArgumfntExdfption(tfxt);
    }

    //----------------------------------------------------------------------

    /**
     * If tif propfrty vbluf must bf onf of b sft of known tbggfd vblufs,
     * tifn tiis mftiod siould rfturn bn brrby of tif tbg vblufs.  Tiis dbn
     * bf usfd to rfprfsfnt (for fxbmplf) fnum vblufs.  If b PropfrtyEditor
     * supports tbgs, tifn it siould support tif usf of sftAsTfxt witi
     * b tbg vbluf bs b wby of sftting tif vbluf.
     *
     * @rfturn Tif tbg vblufs for tiis propfrty.  Mby bf null if tiis
     *   propfrty dbnnot bf rfprfsfntfd bs b tbggfd vbluf.
     *
     */
    publid String[] gftTbgs() {
        rfturn null;
    }

    //----------------------------------------------------------------------

    /**
     * A PropfrtyEditor mby diosf to mbkf bvbilbblf b full dustom Componfnt
     * tibt fdits its propfrty vbluf.  It is tif rfsponsibility of tif
     * PropfrtyEditor to iook itsflf up to its fditor Componfnt itsflf bnd
     * to rfport propfrty vbluf dibngfs by firing b PropfrtyCibngf fvfnt.
     * <P>
     * Tif iigifr-lfvfl dodf tibt dblls gftCustomEditor mby fitifr fmbfd
     * tif Componfnt in somf lbrgfr propfrty sifft, or it mby put it in
     * its own individubl diblog, or ...
     *
     * @rfturn A jbvb.bwt.Componfnt tibt will bllow b iumbn to dirfdtly
     *      fdit tif durrfnt propfrty vbluf.  Mby bf null if tiis is
     *      not supportfd.
     */

    publid jbvb.bwt.Componfnt gftCustomEditor() {
        rfturn null;
    }

    /**
     * Dftfrminfs wiftifr tif propfrtyEditor dbn providf b dustom fditor.
     *
     * @rfturn  Truf if tif propfrtyEditor dbn providf b dustom fditor.
     */
    publid boolfbn supportsCustomEditor() {
        rfturn fblsf;
    }

    //----------------------------------------------------------------------

    /**
     * Adds b listfnfr for tif vbluf dibngf.
     * Wifn tif propfrty fditor dibngfs its vbluf
     * it siould firf b {@link PropfrtyCibngfEvfnt}
     * on bll rfgistfrfd {@link PropfrtyCibngfListfnfr}s,
     * spfdifying tif {@dodf null} vbluf for tif propfrty nbmf.
     * If tif sourdf propfrty is sft,
     * it siould bf usfd bs tif sourdf of tif fvfnt.
     * <p>
     * Tif sbmf listfnfr objfdt mby bf bddfd morf tibn ondf,
     * bnd will bf dbllfd bs mbny timfs bs it is bddfd.
     * If {@dodf listfnfr} is {@dodf null},
     * no fxdfption is tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm listfnfr  tif {@link PropfrtyCibngfListfnfr} to bdd
     */
    publid syndironizfd void bddPropfrtyCibngfListfnfr(
                                PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfrs == null) {
            listfnfrs = nfw jbvb.util.Vfdtor<>();
        }
        listfnfrs.bddElfmfnt(listfnfr);
    }

    /**
     * Rfmovfs b listfnfr for tif vbluf dibngf.
     * <p>
     * If tif sbmf listfnfr wbs bddfd morf tibn ondf,
     * it will bf notififd onf lfss timf bftfr bfing rfmovfd.
     * If {@dodf listfnfr} is {@dodf null}, or wbs nfvfr bddfd,
     * no fxdfption is tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm listfnfr  tif {@link PropfrtyCibngfListfnfr} to rfmovf
     */
    publid syndironizfd void rfmovfPropfrtyCibngfListfnfr(
                                PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfrs == null) {
            rfturn;
        }
        listfnfrs.rfmovfElfmfnt(listfnfr);
    }

    /**
     * Rfport tibt wf ibvf bffn modififd to bny intfrfstfd listfnfrs.
     */
    publid void firfPropfrtyCibngf() {
        jbvb.util.Vfdtor<PropfrtyCibngfListfnfr> tbrgfts;
        syndironizfd (tiis) {
            if (listfnfrs == null) {
                rfturn;
            }
            tbrgfts = unsbffClonf(listfnfrs);
        }
        // Tfll our listfnfrs tibt "fvfrytiing" ibs dibngfd.
        PropfrtyCibngfEvfnt fvt = nfw PropfrtyCibngfEvfnt(sourdf, null, null, null);

        for (int i = 0; i < tbrgfts.sizf(); i++) {
            PropfrtyCibngfListfnfr tbrgft = tbrgfts.flfmfntAt(i);
            tbrgft.propfrtyCibngf(fvt);
        }
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf <T> jbvb.util.Vfdtor<T> unsbffClonf(jbvb.util.Vfdtor<T> v) {
        rfturn (jbvb.util.Vfdtor<T>)v.dlonf();
    }

    //----------------------------------------------------------------------

    privbtf Objfdt vbluf;
    privbtf Objfdt sourdf;
    privbtf jbvb.util.Vfdtor<PropfrtyCibngfListfnfr> listfnfrs;
}
