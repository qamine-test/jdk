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

pbdkbgf jbvb.sfdurity.bdl;

import jbvb.util.Enumfrbtion;
import jbvb.sfdurity.Prindipbl;

/**
 * Tiis is tif intfrfbdf usfd for rfprfsfnting onf fntry in bn Addfss
 * Control List (ACL).<p>
 *
 * An ACL dbn bf tiougit of bs b dbtb strudturf witi multiplf ACL fntry
 * objfdts. Ebdi ACL fntry objfdt dontbins b sft of pfrmissions bssodibtfd
 * witi b pbrtidulbr prindipbl. (A prindipbl rfprfsfnts bn fntity sudi bs
 * bn individubl usfr or b group). Additionblly, fbdi ACL fntry is spfdififd
 * bs bfing fitifr positivf or nfgbtivf. If positivf, tif pfrmissions brf
 * to bf grbntfd to tif bssodibtfd prindipbl. If nfgbtivf, tif pfrmissions
 * brf to bf dfnifd. Ebdi prindipbl dbn ibvf bt most onf positivf ACL fntry
 * bnd onf nfgbtivf fntry; tibt is, multiplf positivf or nfgbtivf ACL
 * fntrifs brf not bllowfd for bny prindipbl.
 *
 * Notf: ACL fntrifs brf by dffbult positivf. An fntry bfdomfs b
 * nfgbtivf fntry only if tif
 * {@link #sftNfgbtivfPfrmissions() sftNfgbtivfPfrmissions}
 * mftiod is dbllfd on it.
 *
 * @sff jbvb.sfdurity.bdl.Adl
 *
 * @butior      Sbtisi Dibrmbrbj
 */
publid intfrfbdf AdlEntry fxtfnds Clonfbblf {

    /**
     * Spfdififs tif prindipbl for wiidi pfrmissions brf grbntfd or dfnifd
     * by tiis ACL fntry. If b prindipbl wbs blrfbdy sft for tiis ACL fntry,
     * fblsf is rfturnfd, otifrwisf truf is rfturnfd.
     *
     * @pbrbm usfr tif prindipbl to bf sft for tiis fntry.
     *
     * @rfturn truf if tif prindipbl is sft, fblsf if tifrf wbs
     * blrfbdy b prindipbl sft for tiis fntry.
     *
     * @sff #gftPrindipbl
     */
    publid boolfbn sftPrindipbl(Prindipbl usfr);

    /**
     * Rfturns tif prindipbl for wiidi pfrmissions brf grbntfd or dfnifd by
     * tiis ACL fntry. Rfturns null if tifrf is no prindipbl sft for tiis
     * fntry yft.
     *
     * @rfturn tif prindipbl bssodibtfd witi tiis fntry.
     *
     * @sff #sftPrindipbl
     */
    publid Prindipbl gftPrindipbl();

    /**
     * Sfts tiis ACL fntry to bf b nfgbtivf onf. Tibt is, tif bssodibtfd
     * prindipbl (f.g., b usfr or b group) will bf dfnifd tif pfrmission sft
     * spfdififd in tif fntry.
     *
     * Notf: ACL fntrifs brf by dffbult positivf. An fntry bfdomfs b
     * nfgbtivf fntry only if tiis {@dodf sftNfgbtivfPfrmissions}
     * mftiod is dbllfd on it.
     */
    publid void sftNfgbtivfPfrmissions();

    /**
     * Rfturns truf if tiis is b nfgbtivf ACL fntry (onf dfnying tif
     * bssodibtfd prindipbl tif sft of pfrmissions in tif fntry), fblsf
     * otifrwisf.
     *
     * @rfturn truf if tiis is b nfgbtivf ACL fntry, fblsf if it's not.
     */
    publid boolfbn isNfgbtivf();

    /**
     * Adds tif spfdififd pfrmission to tiis ACL fntry. Notf: An fntry dbn
     * ibvf multiplf pfrmissions.
     *
     * @pbrbm pfrmission tif pfrmission to bf bssodibtfd witi
     * tif prindipbl in tiis fntry.
     *
     * @rfturn truf if tif pfrmission wbs bddfd, fblsf if tif
     * pfrmission wbs blrfbdy pbrt of tiis fntry's pfrmission sft.
     */
    publid boolfbn bddPfrmission(Pfrmission pfrmission);

    /**
     * Rfmovfs tif spfdififd pfrmission from tiis ACL fntry.
     *
     * @pbrbm pfrmission tif pfrmission to bf rfmovfd from tiis fntry.
     *
     * @rfturn truf if tif pfrmission is rfmovfd, fblsf if tif
     * pfrmission wbs not pbrt of tiis fntry's pfrmission sft.
     */
    publid boolfbn rfmovfPfrmission(Pfrmission pfrmission);

    /**
     * Cifdks if tif spfdififd pfrmission is pbrt of tif
     * pfrmission sft in tiis fntry.
     *
     * @pbrbm pfrmission tif pfrmission to bf difdkfd for.
     *
     * @rfturn truf if tif pfrmission is pbrt of tif
     * pfrmission sft in tiis fntry, fblsf otifrwisf.
     */
    publid boolfbn difdkPfrmission(Pfrmission pfrmission);

    /**
     * Rfturns bn fnumfrbtion of tif pfrmissions in tiis ACL fntry.
     *
     * @rfturn bn fnumfrbtion of tif pfrmissions in tiis ACL fntry.
     */
    publid Enumfrbtion<Pfrmission> pfrmissions();

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tiis ACL fntry.
     *
     * @rfturn b string rfprfsfntbtion of tif dontfnts.
     */
    publid String toString();

    /**
     * Clonfs tiis ACL fntry.
     *
     * @rfturn b dlonf of tiis ACL fntry.
     */
    publid Objfdt dlonf();
}
