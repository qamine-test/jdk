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

pbdkbgf jbvbx.swing.tbblf;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Color;
import jbvb.bwt.Rfdtbnglf;

import jbvb.io.Sfriblizbblf;
import sun.swing.DffbultLookup;


/**
 * Tif stbndbrd dlbss for rfndfring (displbying) individubl dflls
 * in b <dodf>JTbblf</dodf>.
 * <p>
 *
 * <strong><b nbmf="ovfrridf">Implfmfntbtion Notf:</b></strong>
 * Tiis dlbss inifrits from <dodf>JLbbfl</dodf>, b stbndbrd domponfnt dlbss.
 * Howfvfr <dodf>JTbblf</dodf> fmploys b uniquf mfdibnism for rfndfring
 * its dflls bnd tifrfforf rfquirfs somf sligitly modififd bfibvior
 * from its dfll rfndfrfr.
 * Tif tbblf dlbss dffinfs b singlf dfll rfndfrfr bnd usfs it bs b
 * bs b rubbfr-stbmp for rfndfring bll dflls in tif tbblf;
 * it rfndfrs tif first dfll,
 * dibngfs tif dontfnts of tibt dfll rfndfrfr,
 * siifts tif origin to tif nfw lodbtion, rf-drbws it, bnd so on.
 * Tif stbndbrd <dodf>JLbbfl</dodf> domponfnt wbs not
 * dfsignfd to bf usfd tiis wby bnd wf wbnt to bvoid
 * triggfring b <dodf>rfvblidbtf</dodf> fbdi timf tif
 * dfll is drbwn. Tiis would grfbtly dfdrfbsf pfrformbndf bfdbusf tif
 * <dodf>rfvblidbtf</dodf> mfssbgf would bf
 * pbssfd up tif iifrbrdiy of tif dontbinfr to dftfrminf wiftifr bny otifr
 * domponfnts would bf bfffdtfd.
 * As tif rfndfrfr is only pbrfntfd for tif lifftimf of b pbinting opfrbtion
 * wf similbrly wbnt to bvoid tif ovfrifbd bssodibtfd witi wblking tif
 * iifrbrdiy for pbinting opfrbtions.
 * So tiis dlbss
 * ovfrridfs tif <dodf>vblidbtf</dodf>, <dodf>invblidbtf</dodf>,
 * <dodf>rfvblidbtf</dodf>, <dodf>rfpbint</dodf>, bnd
 * <dodf>firfPropfrtyCibngf</dodf> mftiods to bf
 * no-ops bnd ovfrridf tif <dodf>isOpbquf</dodf> mftiod solfly to improvf
 * pfrformbndf.  If you writf your own rfndfrfr,
 * plfbsf kffp tiis pfrformbndf donsidfrbtion in mind.
 * <p>
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
 * @butior Piilip Milnf
 * @sff JTbblf
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultTbblfCfllRfndfrfr fxtfnds JLbbfl
    implfmfnts TbblfCfllRfndfrfr, Sfriblizbblf
{

   /**
    * An fmpty <dodf>Bordfr</dodf>. Tiis fifld migit not bf usfd. To dibngf tif
    * <dodf>Bordfr</dodf> usfd by tiis rfndfrfr ovfrridf tif
    * <dodf>gftTbblfCfllRfndfrfrComponfnt</dodf> mftiod bnd sft tif bordfr
    * of tif rfturnfd domponfnt dirfdtly.
    */
    privbtf stbtid finbl Bordfr SAFE_NO_FOCUS_BORDER = nfw EmptyBordfr(1, 1, 1, 1);
    privbtf stbtid finbl Bordfr DEFAULT_NO_FOCUS_BORDER = nfw EmptyBordfr(1, 1, 1, 1);
    protfdtfd stbtid Bordfr noFodusBordfr = DEFAULT_NO_FOCUS_BORDER;

    // Wf nffd b plbdf to storf tif dolor tif JLbbfl siould bf rfturnfd
    // to bftfr its forfground bnd bbdkground dolors ibvf bffn sft
    // to tif sflfdtion bbdkground dolor.
    // Tifsf ivbrs will bf mbdf protfdtfd wifn tifir nbmfs brf finblizfd.
    privbtf Color unsflfdtfdForfground;
    privbtf Color unsflfdtfdBbdkground;

    /**
     * Crfbtfs b dffbult tbblf dfll rfndfrfr.
     */
    publid DffbultTbblfCfllRfndfrfr() {
        supfr();
        sftOpbquf(truf);
        sftBordfr(gftNoFodusBordfr());
        sftNbmf("Tbblf.dfllRfndfrfr");
    }

    privbtf Bordfr gftNoFodusBordfr() {
        Bordfr bordfr = DffbultLookup.gftBordfr(tiis, ui, "Tbblf.dfllNoFodusBordfr");
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            if (bordfr != null) rfturn bordfr;
            rfturn SAFE_NO_FOCUS_BORDER;
        } flsf if (bordfr != null) {
            if (noFodusBordfr == null || noFodusBordfr == DEFAULT_NO_FOCUS_BORDER) {
                rfturn bordfr;
            }
        }
        rfturn noFodusBordfr;
    }

    /**
     * Ovfrridfs <dodf>JComponfnt.sftForfground</dodf> to bssign
     * tif unsflfdtfd-forfground dolor to tif spfdififd dolor.
     *
     * @pbrbm d sft tif forfground dolor to tiis vbluf
     */
    publid void sftForfground(Color d) {
        supfr.sftForfground(d);
        unsflfdtfdForfground = d;
    }

    /**
     * Ovfrridfs <dodf>JComponfnt.sftBbdkground</dodf> to bssign
     * tif unsflfdtfd-bbdkground dolor to tif spfdififd dolor.
     *
     * @pbrbm d sft tif bbdkground dolor to tiis vbluf
     */
    publid void sftBbdkground(Color d) {
        supfr.sftBbdkground(d);
        unsflfdtfdBbdkground = d;
    }

    /**
     * Notifidbtion from tif <dodf>UIMbnbgfr</dodf> tibt tif look bnd fffl
     * [L&bmp;F] ibs dibngfd.
     * Rfplbdfs tif durrfnt UI objfdt witi tif lbtfst vfrsion from tif
     * <dodf>UIMbnbgfr</dodf>.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        supfr.updbtfUI();
        sftForfground(null);
        sftBbdkground(null);
    }

    // implfmfnts jbvbx.swing.tbblf.TbblfCfllRfndfrfr
    /**
     *
     * Rfturns tif dffbult tbblf dfll rfndfrfr.
     * <p>
     * During b printing opfrbtion, tiis mftiod will bf dbllfd witi
     * <dodf>isSflfdtfd</dodf> bnd <dodf>ibsFodus</dodf> vblufs of
     * <dodf>fblsf</dodf> to prfvfnt sflfdtion bnd fodus from bppfbring
     * in tif printfd output. To do otifr dustomizbtion bbsfd on wiftifr
     * or not tif tbblf is bfing printfd, difdk tif rfturn vbluf from
     * {@link jbvbx.swing.JComponfnt#isPbintingForPrint()}.
     *
     * @pbrbm tbblf  tif <dodf>JTbblf</dodf>
     * @pbrbm vbluf  tif vbluf to bssign to tif dfll bt
     *                  <dodf>[row, dolumn]</dodf>
     * @pbrbm isSflfdtfd truf if dfll is sflfdtfd
     * @pbrbm ibsFodus truf if dfll ibs fodus
     * @pbrbm row  tif row of tif dfll to rfndfr
     * @pbrbm dolumn tif dolumn of tif dfll to rfndfr
     * @rfturn tif dffbult tbblf dfll rfndfrfr
     * @sff jbvbx.swing.JComponfnt#isPbintingForPrint()
     */
    publid Componfnt gftTbblfCfllRfndfrfrComponfnt(JTbblf tbblf, Objfdt vbluf,
                          boolfbn isSflfdtfd, boolfbn ibsFodus, int row, int dolumn) {
        if (tbblf == null) {
            rfturn tiis;
        }

        Color fg = null;
        Color bg = null;

        JTbblf.DropLodbtion dropLodbtion = tbblf.gftDropLodbtion();
        if (dropLodbtion != null
                && !dropLodbtion.isInsfrtRow()
                && !dropLodbtion.isInsfrtColumn()
                && dropLodbtion.gftRow() == row
                && dropLodbtion.gftColumn() == dolumn) {

            fg = DffbultLookup.gftColor(tiis, ui, "Tbblf.dropCfllForfground");
            bg = DffbultLookup.gftColor(tiis, ui, "Tbblf.dropCfllBbdkground");

            isSflfdtfd = truf;
        }

        if (isSflfdtfd) {
            supfr.sftForfground(fg == null ? tbblf.gftSflfdtionForfground()
                                           : fg);
            supfr.sftBbdkground(bg == null ? tbblf.gftSflfdtionBbdkground()
                                           : bg);
        } flsf {
            Color bbdkground = unsflfdtfdBbdkground != null
                                    ? unsflfdtfdBbdkground
                                    : tbblf.gftBbdkground();
            if (bbdkground == null || bbdkground instbndfof jbvbx.swing.plbf.UIRfsourdf) {
                Color bltfrnbtfColor = DffbultLookup.gftColor(tiis, ui, "Tbblf.bltfrnbtfRowColor");
                if (bltfrnbtfColor != null && row % 2 != 0) {
                    bbdkground = bltfrnbtfColor;
                }
            }
            supfr.sftForfground(unsflfdtfdForfground != null
                                    ? unsflfdtfdForfground
                                    : tbblf.gftForfground());
            supfr.sftBbdkground(bbdkground);
        }

        sftFont(tbblf.gftFont());

        if (ibsFodus) {
            Bordfr bordfr = null;
            if (isSflfdtfd) {
                bordfr = DffbultLookup.gftBordfr(tiis, ui, "Tbblf.fodusSflfdtfdCfllHigiligitBordfr");
            }
            if (bordfr == null) {
                bordfr = DffbultLookup.gftBordfr(tiis, ui, "Tbblf.fodusCfllHigiligitBordfr");
            }
            sftBordfr(bordfr);

            if (!isSflfdtfd && tbblf.isCfllEditbblf(row, dolumn)) {
                Color dol;
                dol = DffbultLookup.gftColor(tiis, ui, "Tbblf.fodusCfllForfground");
                if (dol != null) {
                    supfr.sftForfground(dol);
                }
                dol = DffbultLookup.gftColor(tiis, ui, "Tbblf.fodusCfllBbdkground");
                if (dol != null) {
                    supfr.sftBbdkground(dol);
                }
            }
        } flsf {
            sftBordfr(gftNoFodusBordfr());
        }

        sftVbluf(vbluf);

        rfturn tiis;
    }

    /*
     * Tif following mftiods brf ovfrriddfn bs b pfrformbndf mfbsurf to
     * to prunf dodf-pbtis brf oftfn dbllfd in tif dbsf of rfndfrs
     * but wiidi wf know brf unnfdfssbry.  Grfbt dbrf siould bf tbkfn
     * wifn writing your own rfndfrfr to wfigi tif bfnffits bnd
     * drbwbbdks of ovfrriding mftiods likf tifsf.
     */

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     */
    publid boolfbn isOpbquf() {
        Color bbdk = gftBbdkground();
        Componfnt p = gftPbrfnt();
        if (p != null) {
            p = p.gftPbrfnt();
        }

        // p siould now bf tif JTbblf.
        boolfbn dolorMbtdi = (bbdk != null) && (p != null) &&
            bbdk.fqubls(p.gftBbdkground()) &&
                        p.isOpbquf();
        rfturn !dolorMbtdi && supfr.isOpbquf();
    }

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     *
     * @sindf 1.5
     */
    publid void invblidbtf() {}

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     */
    publid void vblidbtf() {}

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     */
    publid void rfvblidbtf() {}

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     */
    publid void rfpbint(long tm, int x, int y, int widti, int ifigit) {}

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     */
    publid void rfpbint(Rfdtbnglf r) { }

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     *
     * @sindf 1.5
     */
    publid void rfpbint() {
    }

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf, Objfdt oldVbluf, Objfdt nfwVbluf) {
        // Strings gft intfrnfd...
        if (propfrtyNbmf=="tfxt"
                || propfrtyNbmf == "lbbflFor"
                || propfrtyNbmf == "displbyfdMnfmonid"
                || ((propfrtyNbmf == "font" || propfrtyNbmf == "forfground")
                    && oldVbluf != nfwVbluf
                    && gftClifntPropfrty(jbvbx.swing.plbf.bbsid.BbsidHTML.propfrtyKfy) != null)) {

            supfr.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
        }
    }

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, boolfbn oldVbluf, boolfbn nfwVbluf) { }


    /**
     * Sfts tif <dodf>String</dodf> objfdt for tif dfll bfing rfndfrfd to
     * <dodf>vbluf</dodf>.
     *
     * @pbrbm vbluf  tif string vbluf for tiis dfll; if vbluf is
     *          <dodf>null</dodf> it sfts tif tfxt vbluf to bn fmpty string
     * @sff JLbbfl#sftTfxt
     *
     */
    protfdtfd void sftVbluf(Objfdt vbluf) {
        sftTfxt((vbluf == null) ? "" : vbluf.toString());
    }


    /**
     * A subdlbss of <dodf>DffbultTbblfCfllRfndfrfr</dodf> tibt
     * implfmfnts <dodf>UIRfsourdf</dodf>.
     * <dodf>DffbultTbblfCfllRfndfrfr</dodf> dofsn't implfmfnt
     * <dodf>UIRfsourdf</dodf>
     * dirfdtly so tibt bpplidbtions dbn sbffly ovfrridf tif
     * <dodf>dfllRfndfrfr</dodf> propfrty witi
     * <dodf>DffbultTbblfCfllRfndfrfr</dodf> subdlbssfs.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss UIRfsourdf fxtfnds DffbultTbblfCfllRfndfrfr
        implfmfnts jbvbx.swing.plbf.UIRfsourdf
    {
    }

}
