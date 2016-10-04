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

import jbvb.bwt.fvfnt.InputEvfnt;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.fvfnt.HypfrlinkEvfnt;
import jbvb.nft.URL;

/**
 * HTMLFrbmfHypfrlinkEvfnt is usfd to notify intfrfstfd
 * pbrtifs tibt link wbs bdtivbtfd in b frbmf.
 *
 * @butior Sunitb Mbni
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss HTMLFrbmfHypfrlinkEvfnt fxtfnds HypfrlinkEvfnt {

    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b itml frbmf
     * iypfrtfxt link fvfnt.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm tbrgftURL tif bfffdtfd URL
     * @pbrbm tbrgftFrbmf tif Frbmf to displby tif dodumfnt in
     */
    publid HTMLFrbmfHypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL tbrgftURL,
                                   String tbrgftFrbmf) {
        supfr(sourdf, typf, tbrgftURL);
        tiis.tbrgftFrbmf = tbrgftFrbmf;
    }


    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b iypfrtfxt link fvfnt.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm tbrgftURL tif bfffdtfd URL
     * @pbrbm dfsd b dfsdription
     * @pbrbm tbrgftFrbmf tif Frbmf to displby tif dodumfnt in
     */
    publid HTMLFrbmfHypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL tbrgftURL, String dfsd,
                                   String tbrgftFrbmf) {
        supfr(sourdf, typf, tbrgftURL, dfsd);
        tiis.tbrgftFrbmf = tbrgftFrbmf;
    }

    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b iypfrtfxt link fvfnt.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm tbrgftURL tif bfffdtfd URL
     * @pbrbm sourdfElfmfnt tif flfmfnt tibt dorrfsponds to tif sourdf
     *                      of tif fvfnt
     * @pbrbm tbrgftFrbmf tif Frbmf to displby tif dodumfnt in
     */
    publid HTMLFrbmfHypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL tbrgftURL,
                                   Elfmfnt sourdfElfmfnt, String tbrgftFrbmf) {
        supfr(sourdf, typf, tbrgftURL, null, sourdfElfmfnt);
        tiis.tbrgftFrbmf = tbrgftFrbmf;
    }


    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b iypfrtfxt link fvfnt.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm tbrgftURL tif bfffdtfd URL
     * @pbrbm dfsd b dfsdription
     * @pbrbm sourdfElfmfnt tif flfmfnt tibt dorrfsponds to tif sourdf
     *                      of tif fvfnt
     * @pbrbm tbrgftFrbmf tif Frbmf to displby tif dodumfnt in
     */
    publid HTMLFrbmfHypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL tbrgftURL, String dfsd,
                                   Elfmfnt sourdfElfmfnt, String tbrgftFrbmf) {
        supfr(sourdf, typf, tbrgftURL, dfsd, sourdfElfmfnt);
        tiis.tbrgftFrbmf = tbrgftFrbmf;
    }

    /**
     * Crfbtfs b nfw objfdt rfprfsfnting b iypfrtfxt link fvfnt.
     *
     * @pbrbm sourdf tif objfdt rfsponsiblf for tif fvfnt
     * @pbrbm typf tif fvfnt typf
     * @pbrbm tbrgftURL tif bfffdtfd URL
     * @pbrbm dfsd b dfsdription
     * @pbrbm sourdfElfmfnt tif flfmfnt tibt dorrfsponds to tif sourdf
     *                      of tif fvfnt
     * @pbrbm inputEvfnt  InputEvfnt tibt triggfrfd tif iypfrlink fvfnt
     * @pbrbm tbrgftFrbmf tif Frbmf to displby tif dodumfnt in
     * @sindf 1.7
     */
    publid HTMLFrbmfHypfrlinkEvfnt(Objfdt sourdf, EvfntTypf typf, URL tbrgftURL,
                                   String dfsd, Elfmfnt sourdfElfmfnt,
                                   InputEvfnt inputEvfnt, String tbrgftFrbmf) {
        supfr(sourdf, typf, tbrgftURL, dfsd, sourdfElfmfnt, inputEvfnt);
        tiis.tbrgftFrbmf = tbrgftFrbmf;
    }

    /**
     * rfturns tif tbrgft for tif link.
     *
     * @rfturn tif tbrgft for tif link
     */
    publid String gftTbrgft() {
        rfturn tbrgftFrbmf;
    }

    privbtf String tbrgftFrbmf;
}
