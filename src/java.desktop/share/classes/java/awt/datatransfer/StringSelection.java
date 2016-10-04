/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dbtbtrbnsffr;

import jbvb.io.*;


/**
 * A <dodf>Trbnsffrbblf</dodf> wiidi implfmfnts tif dbpbbility rfquirfd
 * to trbnsffr b <dodf>String</dodf>.
 *
 * Tiis <dodf>Trbnsffrbblf</dodf> propfrly supports
 * <dodf>DbtbFlbvor.stringFlbvor</dodf>
 * bnd bll fquivblfnt flbvors. Support for
 * <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>
 * bnd bll fquivblfnt flbvors is <b>dfprfdbtfd</b>. No otifr
 * <dodf>DbtbFlbvor</dodf>s brf supportfd.
 *
 * @sff jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor#stringFlbvor
 * @sff jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor#plbinTfxtFlbvor
 */
publid dlbss StringSflfdtion implfmfnts Trbnsffrbblf, ClipbobrdOwnfr {

    privbtf stbtid finbl int STRING = 0;
    privbtf stbtid finbl int PLAIN_TEXT = 1;

    privbtf stbtid finbl DbtbFlbvor[] flbvors = {
        DbtbFlbvor.stringFlbvor,
        DbtbFlbvor.plbinTfxtFlbvor // dfprfdbtfd
    };

    privbtf String dbtb;

    /**
     * Crfbtfs b <dodf>Trbnsffrbblf</dodf> dbpbblf of trbnsffrring
     * tif spfdififd <dodf>String</dodf>.
     * @pbrbm dbtb tif string to bf trbnsffrrfd
     */
    publid StringSflfdtion(String dbtb) {
        tiis.dbtb = dbtb;
    }

    /**
     * Rfturns bn brrby of flbvors in wiidi tiis <dodf>Trbnsffrbblf</dodf>
     * dbn providf tif dbtb. <dodf>DbtbFlbvor.stringFlbvor</dodf>
     * is propfrly supportfd.
     * Support for <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf> is
     * <b>dfprfdbtfd</b>.
     *
     * @rfturn bn brrby of lfngti two, wiosf flfmfnts brf <dodf>DbtbFlbvor.
     *         stringFlbvor</dodf> bnd <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>
     */
    publid DbtbFlbvor[] gftTrbnsffrDbtbFlbvors() {
        // rfturning flbvors itsflf would bllow dlifnt dodf to modify
        // our intfrnbl bfibvior
        rfturn flbvors.dlonf();
    }

    /**
     * Rfturns wiftifr tif rfqufstfd flbvor is supportfd by tiis
     * <dodf>Trbnsffrbblf</dodf>.
     *
     * @pbrbm flbvor tif rfqufstfd flbvor for tif dbtb
     * @rfturn truf if <dodf>flbvor</dodf> is fqubl to
     *   <dodf>DbtbFlbvor.stringFlbvor</dodf> or
     *   <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>; fblsf if <dodf>flbvor</dodf>
     *   is not onf of tif bbovf flbvors
     * @tirows NullPointfrExdfption if flbvor is <dodf>null</dodf>
     */
    publid boolfbn isDbtbFlbvorSupportfd(DbtbFlbvor flbvor) {
        // JCK Tfst StringSflfdtion0003: if 'flbvor' is null, tirow NPE
        for (int i = 0; i < flbvors.lfngti; i++) {
            if (flbvor.fqubls(flbvors[i])) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif <dodf>Trbnsffrbblf</dodf>'s dbtb in tif rfqufstfd
     * <dodf>DbtbFlbvor</dodf> if possiblf. If tif dfsirfd flbvor is
     * <dodf>DbtbFlbvor.stringFlbvor</dodf>, or bn fquivblfnt flbvor,
     * tif <dodf>String</dodf> rfprfsfnting tif sflfdtion is
     * rfturnfd. If tif dfsirfd flbvor is
     * <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>,
     * or bn fquivblfnt flbvor, b <dodf>Rfbdfr</dodf> is rfturnfd.
     * <b>Notf:</b> Tif bfibvior of tiis mftiod for
     * <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>
     * bnd fquivblfnt <dodf>DbtbFlbvor</dodf>s is indonsistfnt witi tif
     * dffinition of <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>.
     *
     * @pbrbm flbvor tif rfqufstfd flbvor for tif dbtb
     * @rfturn tif dbtb in tif rfqufstfd flbvor, bs outlinfd bbovf
     * @tirows UnsupportfdFlbvorExdfption if tif rfqufstfd dbtb flbvor is
     *         not fquivblfnt to fitifr <dodf>DbtbFlbvor.stringFlbvor</dodf>
     *         or <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>
     * @tirows IOExdfption if bn IOExdfption oddurs wiilf rftrifving tif dbtb.
     *         By dffbult, StringSflfdtion nfvfr tirows tiis fxdfption, but b
     *         subdlbss mby.
     * @tirows NullPointfrExdfption if flbvor is <dodf>null</dodf>
     * @sff jbvb.io.Rfbdfr
     */
    publid Objfdt gftTrbnsffrDbtb(DbtbFlbvor flbvor)
        tirows UnsupportfdFlbvorExdfption, IOExdfption
    {
        // JCK Tfst StringSflfdtion0007: if 'flbvor' is null, tirow NPE
        if (flbvor.fqubls(flbvors[STRING])) {
            rfturn (Objfdt)dbtb;
        } flsf if (flbvor.fqubls(flbvors[PLAIN_TEXT])) {
            rfturn nfw StringRfbdfr(dbtb == null ? "" : dbtb);
        } flsf {
            tirow nfw UnsupportfdFlbvorExdfption(flbvor);
        }
    }

    publid void lostOwnfrsiip(Clipbobrd dlipbobrd, Trbnsffrbblf dontfnts) {
    }
}
