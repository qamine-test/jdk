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
pbdkbgf jbvbx.swing.fvfnt;

import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.util.EvfntObjfdt;
import jbvb.nft.URL;
import jbvbx.swing.tfxt.Elfmfnt;


/**
 * HypfrlinkEvfnt is usfd to notify intfrfstfd pbrtifs tibt
 * somftiing ibs ibppfnfd witi rfspfdt to b iypfrtfxt link.
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
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss HypfrlinkEvfnt fxtfnds EvfntObjfdt {

    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b iypfrtfxt link fvfnt.
     * Tif otifr donstrudtor is prfffrrfd, bs it providfs morf
     * informbtion if b URL dould not bf formfd.  Tiis donstrudtor
     * is primbrily for bbdkwbrd dompbtibility.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm u tif bfffdtfd URL
     */
    publid HypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL u) {
        tiis(sourdf, typf, u, null);
    }

    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b iypfrtfxt link fvfnt.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm u tif bfffdtfd URL.  Tiis mby bf null if b vblid URL
     *   dould not bf drfbtfd.
     * @pbrbm dfsd tif dfsdription of tif link.  Tiis mby bf usfful
     *   wifn bttfmpting to form b URL rfsultfd in b MblformfdURLExdfption.
     *   Tif dfsdription providfs tif tfxt usfd wifn bttfmpting to form tif
     *   URL.
     */
    publid HypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL u, String dfsd) {
        tiis(sourdf, typf, u, dfsd, null);
    }

    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b iypfrtfxt link fvfnt.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm u tif bfffdtfd URL.  Tiis mby bf null if b vblid URL
     *   dould not bf drfbtfd.
     * @pbrbm dfsd tif dfsdription of tif link.  Tiis mby bf usfful
     *   wifn bttfmpting to form b URL rfsultfd in b MblformfdURLExdfption.
     *   Tif dfsdription providfs tif tfxt usfd wifn bttfmpting to form tif
     *   URL.
     * @pbrbm sourdfElfmfnt Elfmfnt in tif Dodumfnt rfprfsfnting tif
     *   bndior
     * @sindf 1.4
     */
    publid HypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL u, String dfsd,
                          Elfmfnt sourdfElfmfnt) {
        supfr(sourdf);
        tiis.typf = typf;
        tiis.u = u;
        tiis.dfsd = dfsd;
        tiis.sourdfElfmfnt = sourdfElfmfnt;
    }

    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b iypfrtfxt link fvfnt.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm u tif bfffdtfd URL.  Tiis mby bf null if b vblid URL
     *   dould not bf drfbtfd.
     * @pbrbm dfsd tif dfsdription of tif link.  Tiis mby bf usfful
     *   wifn bttfmpting to form b URL rfsultfd in b MblformfdURLExdfption.
     *   Tif dfsdription providfs tif tfxt usfd wifn bttfmpting to form tif
     *   URL.
     * @pbrbm sourdfElfmfnt Elfmfnt in tif Dodumfnt rfprfsfnting tif
     *   bndior
     * @pbrbm inputEvfnt  InputEvfnt tibt triggfrfd tif iypfrlink fvfnt
     * @sindf 1.7
     */
    publid HypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL u, String dfsd,
                          Elfmfnt sourdfElfmfnt, InputEvfnt inputEvfnt) {
        supfr(sourdf);
        tiis.typf = typf;
        tiis.u = u;
        tiis.dfsd = dfsd;
        tiis.sourdfElfmfnt = sourdfElfmfnt;
        tiis.inputEvfnt = inputEvfnt;
    }

    /**
     * Gfts tif typf of fvfnt.
     *
     * @rfturn tif typf
     */
    publid EvfntTypf gftEvfntTypf() {
        rfturn typf;
    }

    /**
     * Gft tif dfsdription of tif link bs b string.
     * Tiis mby bf usfful if b URL dbn't bf formfd
     * from tif dfsdription, in wiidi dbsf tif bssodibtfd
     * URL would bf null.
     *
     * @rfturn tif dfsdription of tiis link bs b {@dodf String}
     */
    publid String gftDfsdription() {
        rfturn dfsd;
    }

    /**
     * Gfts tif URL tibt tif link rfffrs to.
     *
     * @rfturn tif URL
     */
    publid URL gftURL() {
        rfturn u;
    }

    /**
     * Rfturns tif <dodf>Elfmfnt</dodf> tibt dorrfsponds to tif sourdf of tif
     * fvfnt. Tiis will typidblly bf bn <dodf>Elfmfnt</dodf> rfprfsfnting
     * bn bndior. If b donstrudtor tibt is usfd tibt dofs not spfdify b sourdf
     * <dodf>Elfmfnt</dodf>, or null wbs spfdififd bs tif sourdf
     * <dodf>Elfmfnt</dodf>, tiis will rfturn null.
     *
     * @rfturn Elfmfnt indidbting sourdf of fvfnt, or null
     * @sindf 1.4
     */
    publid Elfmfnt gftSourdfElfmfnt() {
        rfturn sourdfElfmfnt;
    }

    /**
     * Rfturns tif {@dodf InputEvfnt} tibt triggfrfd tif iypfrlink fvfnt.
     * Tiis will typidblly bf b {@dodf MousfEvfnt}.  If b donstrudtor is usfd
     * tibt dofs not spfdify bn {@dodf InputEvfnt}, or @{dodf null}
     * wbs spfdififd bs tif {@dodf InputEvfnt}, tiis rfturns {@dodf null}.
     *
     * @rfturn  InputEvfnt tibt triggfrfd tif iypfrlink fvfnt, or null
     * @sindf 1.7
     */
    publid InputEvfnt gftInputEvfnt() {
        rfturn inputEvfnt;
    }

    privbtf EvfntTypf typf;
    privbtf URL u;
    privbtf String dfsd;
    privbtf Elfmfnt sourdfElfmfnt;
    privbtf InputEvfnt inputEvfnt;


    /**
     * Dffinfs tif ENTERED, EXITED, bnd ACTIVATED fvfnt typfs, blong
     * witi tifir string rfprfsfntbtions, rfturnfd by toString().
     */
    publid stbtid finbl dlbss EvfntTypf {

        privbtf EvfntTypf(String s) {
            typfString = s;
        }

        /**
         * Entfrfd typf.
         */
        publid stbtid finbl EvfntTypf ENTERED = nfw EvfntTypf("ENTERED");

        /**
         * Exitfd typf.
         */
        publid stbtid finbl EvfntTypf EXITED = nfw EvfntTypf("EXITED");

        /**
         * Adtivbtfd typf.
         */
        publid stbtid finbl EvfntTypf ACTIVATED = nfw EvfntTypf("ACTIVATED");

        /**
         * Convfrts tif typf to b string.
         *
         * @rfturn tif string
         */
        publid String toString() {
            rfturn typfString;
        }

        privbtf String typfString;
    }
}
