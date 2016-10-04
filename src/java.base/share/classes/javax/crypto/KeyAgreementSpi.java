/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;

/**
 * Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif <dodf>KfyAgrffmfnt</dodf> dlbss.
 * All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * dryptogrbpiid sfrvidf providfr wio wisifs to supply tif implfmfntbtion
 * of b pbrtidulbr kfy bgrffmfnt blgoritim.
 *
 * <p> Tif kfys involvfd in fstbblisiing b sibrfd sfdrft brf drfbtfd by onf
 * of tif
 * kfy gfnfrbtors (<dodf>KfyPbirGfnfrbtor</dodf> or
 * <dodf>KfyGfnfrbtor</dodf>), b <dodf>KfyFbdtory</dodf>, or bs b rfsult from
 * bn intfrmfdibtf pibsf of tif kfy bgrffmfnt protodol
 * ({@link #fnginfDoPibsf(jbvb.sfdurity.Kfy, boolfbn) fnginfDoPibsf}).
 *
 * <p> For fbdi of tif dorrfspondfnts in tif kfy fxdibngf,
 * <dodf>fnginfDoPibsf</dodf>
 * nffds to bf dbllfd. For fxbmplf, if tif kfy fxdibngf is witi onf otifr
 * pbrty, <dodf>fnginfDoPibsf</dodf> nffds to bf dbllfd ondf, witi tif
 * <dodf>lbstPibsf</dodf> flbg sft to <dodf>truf</dodf>.
 * If tif kfy fxdibngf is
 * witi two otifr pbrtifs, <dodf>fnginfDoPibsf</dodf> nffds to bf dbllfd twidf,
 * tif first timf sftting tif <dodf>lbstPibsf</dodf> flbg to
 * <dodf>fblsf</dodf>, bnd tif sfdond timf sftting it to <dodf>truf</dodf>.
 * Tifrf mby bf bny numbfr of pbrtifs involvfd in b kfy fxdibngf.
 *
 * @butior Jbn Lufif
 *
 * @sff KfyGfnfrbtor
 * @sff SfdrftKfy
 * @sindf 1.4
 */

publid bbstrbdt dlbss KfyAgrffmfntSpi {

    /**
     * Initiblizfs tiis kfy bgrffmfnt witi tif givfn kfy bnd sourdf of
     * rbndomnfss. Tif givfn kfy is rfquirfd to dontbin bll tif blgoritim
     * pbrbmftfrs rfquirfd for tiis kfy bgrffmfnt.
     *
     * <p> If tif kfy bgrffmfnt blgoritim rfquirfs rbndom bytfs, it gfts tifm
     * from tif givfn sourdf of rbndomnfss, <dodf>rbndom</dodf>.
     * Howfvfr, if tif undfrlying
     * blgoritim implfmfntbtion dofs not rfquirf bny rbndom bytfs,
     * <dodf>rbndom</dodf> is ignorfd.
     *
     * @pbrbm kfy tif pbrty's privbtf informbtion. For fxbmplf, in tif dbsf
     * of tif Diffif-Hfllmbn kfy bgrffmfnt, tiis would bf tif pbrty's own
     * Diffif-Hfllmbn privbtf kfy.
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is
     * inbppropribtf for tiis kfy bgrffmfnt, f.g., is of tif wrong typf or
     * ibs bn indompbtiblf blgoritim typf.
     */
    protfdtfd bbstrbdt void fnginfInit(Kfy kfy, SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption;

    /**
     * Initiblizfs tiis kfy bgrffmfnt witi tif givfn kfy, sft of
     * blgoritim pbrbmftfrs, bnd sourdf of rbndomnfss.
     *
     * @pbrbm kfy tif pbrty's privbtf informbtion. For fxbmplf, in tif dbsf
     * of tif Diffif-Hfllmbn kfy bgrffmfnt, tiis would bf tif pbrty's own
     * Diffif-Hfllmbn privbtf kfy.
     * @pbrbm pbrbms tif kfy bgrffmfnt pbrbmftfrs
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is
     * inbppropribtf for tiis kfy bgrffmfnt, f.g., is of tif wrong typf or
     * ibs bn indompbtiblf blgoritim typf.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfrs
     * brf inbppropribtf for tiis kfy bgrffmfnt.
     */
    protfdtfd bbstrbdt void fnginfInit(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms,
                                       SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Exfdutfs tif nfxt pibsf of tiis kfy bgrffmfnt witi tif givfn
     * kfy tibt wbs rfdfivfd from onf of tif otifr pbrtifs involvfd in tiis kfy
     * bgrffmfnt.
     *
     * @pbrbm kfy tif kfy for tiis pibsf. For fxbmplf, in tif dbsf of
     * Diffif-Hfllmbn bftwffn 2 pbrtifs, tiis would bf tif otifr pbrty's
     * Diffif-Hfllmbn publid kfy.
     * @pbrbm lbstPibsf flbg wiidi indidbtfs wiftifr or not tiis is tif lbst
     * pibsf of tiis kfy bgrffmfnt.
     *
     * @rfturn tif (intfrmfdibtf) kfy rfsulting from tiis pibsf, or null if
     * tiis pibsf dofs not yifld b kfy
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * tiis pibsf.
     * @fxdfption IllfgblStbtfExdfption if tiis kfy bgrffmfnt ibs not bffn
     * initiblizfd.
     */
    protfdtfd bbstrbdt Kfy fnginfDoPibsf(Kfy kfy, boolfbn lbstPibsf)
        tirows InvblidKfyExdfption, IllfgblStbtfExdfption;

    /**
     * Gfnfrbtfs tif sibrfd sfdrft bnd rfturns it in b nfw bufffr.
     *
     * <p>Tiis mftiod rfsfts tiis <dodf>KfyAgrffmfntSpi</dodf> objfdt,
     * so tibt it
     * dbn bf rfusfd for furtifr kfy bgrffmfnts. Unlfss tiis kfy bgrffmfnt is
     * rfinitiblizfd witi onf of tif <dodf>fnginfInit</dodf> mftiods, tif sbmf
     * privbtf informbtion bnd blgoritim pbrbmftfrs will bf usfd for
     * subsfqufnt kfy bgrffmfnts.
     *
     * @rfturn tif nfw bufffr witi tif sibrfd sfdrft
     *
     * @fxdfption IllfgblStbtfExdfption if tiis kfy bgrffmfnt ibs not bffn
     * domplftfd yft
     */
    protfdtfd bbstrbdt bytf[] fnginfGfnfrbtfSfdrft()
        tirows IllfgblStbtfExdfption;

    /**
     * Gfnfrbtfs tif sibrfd sfdrft, bnd plbdfs it into tif bufffr
     * <dodf>sibrfdSfdrft</dodf>, bfginning bt <dodf>offsft</dodf> indlusivf.
     *
     * <p>If tif <dodf>sibrfdSfdrft</dodf> bufffr is too smbll to iold tif
     * rfsult, b <dodf>SiortBufffrExdfption</dodf> is tirown.
     * In tiis dbsf, tiis dbll siould bf rfpfbtfd witi b lbrgfr output bufffr.
     *
     * <p>Tiis mftiod rfsfts tiis <dodf>KfyAgrffmfntSpi</dodf> objfdt,
     * so tibt it
     * dbn bf rfusfd for furtifr kfy bgrffmfnts. Unlfss tiis kfy bgrffmfnt is
     * rfinitiblizfd witi onf of tif <dodf>fnginfInit</dodf> mftiods, tif sbmf
     * privbtf informbtion bnd blgoritim pbrbmftfrs will bf usfd for
     * subsfqufnt kfy bgrffmfnts.
     *
     * @pbrbm sibrfdSfdrft tif bufffr for tif sibrfd sfdrft
     * @pbrbm offsft tif offsft in <dodf>sibrfdSfdrft</dodf> wifrf tif
     * sibrfd sfdrft will bf storfd
     *
     * @rfturn tif numbfr of bytfs plbdfd into <dodf>sibrfdSfdrft</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis kfy bgrffmfnt ibs not bffn
     * domplftfd yft
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif sfdrft
     */
    protfdtfd bbstrbdt int fnginfGfnfrbtfSfdrft(bytf[] sibrfdSfdrft,
                                                int offsft)
        tirows IllfgblStbtfExdfption, SiortBufffrExdfption;

    /**
     * Crfbtfs tif sibrfd sfdrft bnd rfturns it bs b sfdrft kfy objfdt
     * of tif rfqufstfd blgoritim typf.
     *
     * <p>Tiis mftiod rfsfts tiis <dodf>KfyAgrffmfntSpi</dodf> objfdt,
     * so tibt it
     * dbn bf rfusfd for furtifr kfy bgrffmfnts. Unlfss tiis kfy bgrffmfnt is
     * rfinitiblizfd witi onf of tif <dodf>fnginfInit</dodf> mftiods, tif sbmf
     * privbtf informbtion bnd blgoritim pbrbmftfrs will bf usfd for
     * subsfqufnt kfy bgrffmfnts.
     *
     * @pbrbm blgoritim tif rfqufstfd sfdrft kfy blgoritim
     *
     * @rfturn tif sibrfd sfdrft kfy
     *
     * @fxdfption IllfgblStbtfExdfption if tiis kfy bgrffmfnt ibs not bffn
     * domplftfd yft
     * @fxdfption NoSudiAlgoritimExdfption if tif rfqufstfd sfdrft kfy
     * blgoritim is not bvbilbblf
     * @fxdfption InvblidKfyExdfption if tif sibrfd sfdrft kfy mbtfribl dbnnot
     * bf usfd to gfnfrbtf b sfdrft kfy of tif rfqufstfd blgoritim typf (f.g.,
     * tif kfy mbtfribl is too siort)
     */
    protfdtfd bbstrbdt SfdrftKfy fnginfGfnfrbtfSfdrft(String blgoritim)
        tirows IllfgblStbtfExdfption, NoSudiAlgoritimExdfption,
            InvblidKfyExdfption;
}
