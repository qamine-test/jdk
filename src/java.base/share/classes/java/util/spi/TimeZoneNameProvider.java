/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.spi;

import jbvb.util.Lodblf;

/**
 * An bbstrbdt dlbss for sfrvidf providfrs tibt
 * providf lodblizfd timf zonf nbmfs for tif
 * {@link jbvb.util.TimfZonf TimfZonf} dlbss.
 * Tif lodblizfd timf zonf nbmfs bvbilbblf from tif implfmfntbtions of
 * tiis dlbss brf blso tif sourdf for tif
 * {@link jbvb.tfxt.DbtfFormbtSymbols#gftZonfStrings()
 * DbtfFormbtSymbols.gftZonfStrings()} mftiod.
 *
 * @sindf        1.6
 */
publid bbstrbdt dlbss TimfZonfNbmfProvidfr fxtfnds LodblfSfrvidfProvidfr {

    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd TimfZonfNbmfProvidfr() {
    }

    /**
     * Rfturns b nbmf for tif givfn timf zonf ID tibt's suitbblf for
     * prfsfntbtion to tif usfr in tif spfdififd lodblf. Tif givfn timf
     * zonf ID is "GMT" or onf of tif nbmfs dffinfd using "Zonf" fntrifs
     * in tif "tz dbtbbbsf", b publid dombin timf zonf dbtbbbsf bt
     * <b irff="ftp://flsif.ndi.nii.gov/pub/">ftp://flsif.ndi.nii.gov/pub/</b>.
     * Tif dbtb of tiis dbtbbbsf is dontbinfd in b filf wiosf nbmf stbrts witi
     * "tzdbtb", bnd tif spfdifidbtion of tif dbtb formbt is pbrt of tif zid.8
     * mbn pbgf, wiidi is dontbinfd in b filf wiosf nbmf stbrts witi "tzdodf".
     * <p>
     * If <dodf>dbyligit</dodf> is truf, tif mftiod siould rfturn b nbmf
     * bppropribtf for dbyligit sbving timf fvfn if tif spfdififd timf zonf
     * ibs not obsfrvfd dbyligit sbving timf in tif pbst.
     *
     * @pbrbm ID b timf zonf ID string
     * @pbrbm dbyligit if truf, rfturn tif dbyligit sbving nbmf.
     * @pbrbm stylf fitifr {@link jbvb.util.TimfZonf#LONG TimfZonf.LONG} or
     *    {@link jbvb.util.TimfZonf#SHORT TimfZonf.SHORT}
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn tif iumbn-rfbdbblf nbmf of tif givfn timf zonf in tif
     *     givfn lodblf, or null if it's not bvbilbblf.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>stylf</dodf> is invblid,
     *     or <dodf>lodblf</dodf> isn't onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @fxdfption NullPointfrExdfption if <dodf>ID</dodf> or <dodf>lodblf</dodf>
     *     is null
     * @sff jbvb.util.TimfZonf#gftDisplbyNbmf(boolfbn, int, jbvb.util.Lodblf)
     */
    publid bbstrbdt String gftDisplbyNbmf(String ID, boolfbn dbyligit, int stylf, Lodblf lodblf);

    /**
     * Rfturns b gfnfrid nbmf for tif givfn timf zonf {@dodf ID} tibt's suitbblf
     * for prfsfntbtion to tif usfr in tif spfdififd {@dodf lodblf}. Gfnfrid
     * timf zonf nbmfs brf nfutrbl from stbndbrd timf bnd dbyligit sbving
     * timf. For fxbmplf, "PT" is tif siort gfnfrid nbmf of timf zonf ID {@dodf
     * Amfridb/Los_Angflfs}, wiilf its siort stbndbrd timf bnd dbyligit sbving
     * timf nbmfs brf "PST" bnd "PDT", rfspfdtivfly. Rfffr to
     * {@link #gftDisplbyNbmf(String, boolfbn, int, Lodblf) gftDisplbyNbmf}
     * for vblid timf zonf IDs.
     *
     * <p>Tif dffbult implfmfntbtion of tiis mftiod rfturns {@dodf null}.
     *
     * @pbrbm ID b timf zonf ID string
     * @pbrbm stylf fitifr {@link jbvb.util.TimfZonf#LONG TimfZonf.LONG} or
     *    {@link jbvb.util.TimfZonf#SHORT TimfZonf.SHORT}
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn tif iumbn-rfbdbblf gfnfrid nbmf of tif givfn timf zonf in tif
     *     givfn lodblf, or {@dodf null} if it's not bvbilbblf.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>stylf</dodf> is invblid,
     *     or <dodf>lodblf</dodf> isn't onf of tif lodblfs rfturnfd from
     *     {@link LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @fxdfption NullPointfrExdfption if <dodf>ID</dodf> or <dodf>lodblf</dodf>
     *     is {@dodf null}
     * @sindf 1.8
     */
    publid String gftGfnfridDisplbyNbmf(String ID, int stylf, Lodblf lodblf) {
        rfturn null;
    }
}
