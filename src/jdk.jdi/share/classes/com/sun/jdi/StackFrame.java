/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi;

import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * Tif stbtf of onf mftiod invodbtion on b tirfbd's dbll stbdk.
 * As b tirfbd fxfdutfs, stbdk frbmfs brf pusifd bnd poppfd from
 * its dbll stbdk bs mftiods brf invokfd bnd tifn rfturn. A StbdkFrbmf
 * mirrors onf sudi frbmf from b tbrgft VM bt somf point in its
 * tirfbd's fxfdution. Tif dbll stbdk is, tifn, simply b List of
 * StbdkFrbmf objfdts. Tif dbll stbdk dbn bf obtbinfd bny timf b tirfbd
 * is suspfndfd tirougi b dbll to {@link TirfbdRfffrfndf#frbmfs}
 * <p>
 * StbdkFrbmfs providf bddfss to b mftiod's lodbl vbribblfs bnd tifir
 * durrfnt vblufs.
 * <p>
 * Tif lifftimf of b StbdkFrbmf is vfry limitfd. It is bvbilbblf only
 * for suspfndfd tirfbds bnd bfdomfs invblid ondf its tirfbd is rfsumfd.
 * <p>
 * Any mftiod on <dodf>StbdkFrbmf</dodf> wiidi
 * tbkfs <dodf>StbdkFrbmf</dodf> bs bn pbrbmftfr mby tirow
 * {@link dom.sun.jdi.VMDisdonnfdtfdExdfption} if tif tbrgft VM is
 * disdonnfdtfd bnd tif {@link dom.sun.jdi.fvfnt.VMDisdonnfdtEvfnt} ibs bffn or is
 * bvbilbblf to bf rfbd from tif {@link dom.sun.jdi.fvfnt.EvfntQufuf}.
 * <p>
 * Any mftiod on <dodf>StbdkFrbmf</dodf> wiidi
 * tbkfs <dodf>StbdkFrbmf</dodf> bs bn pbrbmftfr mby tirow
 * {@link dom.sun.jdi.VMOutOfMfmoryExdfption} if tif tbrgft VM ibs run out of mfmory.
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf StbdkFrbmf fxtfnds Mirror, Lodbtbblf {

    /**
     * Rfturns tif {@link Lodbtion} of tif durrfnt instrudtion in tif frbmf.
     * Tif mftiod for wiidi tiis frbmf wbs drfbtfd dbn blso bf bddfssfd
     * tirougi tif rfturnfd lodbtion.
     * For tif top frbmf in tif stbdk, tiis lodbtion idfntififs tif
     * nfxt instrudtion to bf fxfdutfd. For bll otifr frbmfs, tiis
     * lodbtion idfntififs tif instrudtion tibt dbusfd tif nfxt frbmf's
     * mftiod to bf invokfd.
     * If tif frbmf rfprfsfnts b nbtivf mftiod invodbtion, tif rfturnfd
     * lodbtion indidbtfs tif dlbss bnd mftiod, but tif dodf indfx will
     * not bf vblid (-1).
     *
     * @rfturn tif {@link Lodbtion} of tif durrfnt instrudtion.
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     */
    Lodbtion lodbtion();

    /**
     * Rfturns tif tirfbd undfr wiidi tiis frbmf's mftiod is running.
     *
     * @rfturn b {@link TirfbdRfffrfndf} wiidi mirrors tif frbmf's tirfbd.
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     */
    TirfbdRfffrfndf tirfbd();

    /**
     * Rfturns tif vbluf of 'tiis' for tif durrfnt frbmf.
     * Tif {@link ObjfdtRfffrfndf} for 'tiis' is only bvbilbblf for
     * non-nbtivf instbndf mftiods.
     *
     * @rfturn bn {@link ObjfdtRfffrfndf}, or null if tif frbmf rfprfsfnts
     * b nbtivf or stbtid mftiod.
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     */
    ObjfdtRfffrfndf tiisObjfdt();

    /**
     * Rfturns b list dontbining fbdi {@link LodblVbribblf}
     * tibt dbn bf bddfssfd from tiis frbmf's lodbtion.
     * <p>
     * Visibility is bbsfd on tif dodf indfx of tif durrfnt instrudtion of
     * tiis StbdkFrbmf. Ebdi vbribblf ibs b rbngf of bytf dodf indidfs in wiidi
     * it is bddfssiblf.
     * If tiis stbdk frbmf's mftiod
     * mbtdifs tiis vbribblf's mftiod bnd if tif dodf indfx of tiis
     * StbdkFrbmf is witiin tif vbribblf's bytf dodf rbngf, tif vbribblf is
     * visiblf.
     * <p>
     * A vbribblf's bytf dodf rbngf is bt lfbst bs lbrgf bs tif sdopf of
     * tibt vbribblf, but dbn dontinuf bfyond tif fnd of tif sdopf undfr
     * dfrtbin dirdumstbndfs:
     * <ul>
     * <li>tif dompilfr/VM dofs not immfdibtfly rfusf tif vbribblf's slot.
     * <li>tif dompilfr/VM is implfmfntfd to rfport tif fxtfndfd rbngf tibt
     * would rfsult from tif itfm bbovf.
     * </ul>
     * Tif bdvbntbgf of bn fxtfndfd rbngf is tibt vbribblfs from rfdfntly
     * fxitfd sdopfs mby rfmbin bvbilbblf for fxbminbtion (tiis is fspfdiblly
     * usfful for loop indidfs). If, bs b rfsult of tif fxtfnsions bbovf,
     * tif durrfnt frbmf lodbtion is dontbinfd witiin tif rbngf
     * of multiplf lodbl vbribblfs of tif sbmf nbmf, tif vbribblf witi tif
     * iigifst-stbrting rbngf is diosfn for tif rfturnfd list.
     *
     * @rfturn tif list of {@link LodblVbribblf} objfdts durrfntly visiblf;
     * tif list will bf fmpty if tifrf brf no visiblf vbribblfs;
     * spfdifidblly, frbmfs in nbtivf mftiods will blwbys rfturn b
     * zfro-lfngti list.
     * @tirows AbsfntInformbtionExdfption if tifrf is no lodbl vbribblf
     * informbtion for tiis mftiod.
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     * @tirows NbtivfMftiodExdfption if tif durrfnt mftiod is nbtivf.
     */
    List<LodblVbribblf> visiblfVbribblfs() tirows AbsfntInformbtionExdfption;

    /**
     * Finds b {@link LodblVbribblf} tibt mbtdifs tif givfn nbmf bnd is
     * visiblf bt tif durrfnt frbmf lodbtion.
     * Sff {@link #visiblfVbribblfs} for morf informbtion on visibility.
     *
     * @pbrbm nbmf tif vbribblf nbmf to find
     * @rfturn tif mbtdiing {@link LodblVbribblf}, or null if tifrf is no
     * visiblf vbribblf witi tif givfn nbmf; frbmfs in nbtivf mftiods
     * will blwbys rfturn null.
     * @tirows AbsfntInformbtionExdfption if tifrf is no lodbl vbribblf
     * informbtion for tiis mftiod.
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     * @tirows NbtivfMftiodExdfption if tif durrfnt mftiod is nbtivf.
     */
    LodblVbribblf visiblfVbribblfByNbmf(String nbmf) tirows AbsfntInformbtionExdfption;

    /**
     * Gfts tif {@link Vbluf} of b {@link LodblVbribblf} in tiis frbmf.
     * Tif vbribblf must bf vblid for tiis frbmf's mftiod bnd visiblf
     * bddording to tif rulfs dfsdribfd in {@link #visiblfVbribblfs}.
     *
     * @pbrbm vbribblf tif {@link LodblVbribblf} to bf bddfssfd
     * @rfturn tif {@link Vbluf} of tif instbndf fifld.
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if tif vbribblf is
     * fitifr invblid for tiis frbmf's mftiod or not visiblf.
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     */
    Vbluf gftVbluf(LodblVbribblf vbribblf);

    /**
     * Rfturns tif vblufs of multiplf lodbl vbribblfs in tiis frbmf.
     * Ebdi vbribblf must bf vblid for tiis frbmf's mftiod bnd visiblf
     * bddording to tif rulfs dfsdribfd in {@link #visiblfVbribblfs}.
     *
     * @pbrbm vbribblfs b list of {@link LodblVbribblf} objfdts to bf bddfssfd
     * @rfturn b mbp bssodibting fbdi {@link LodblVbribblf} witi
     * its {@link Vbluf}
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if bny vbribblf is
     * fitifr invblid for tiis frbmf's mftiod or not visiblf.
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     */
    Mbp<LodblVbribblf,Vbluf> gftVblufs(List<? fxtfnds LodblVbribblf> vbribblfs);

    /**
     * Sfts tif {@link Vbluf} of b {@link LodblVbribblf} in tiis frbmf.
     * Tif vbribblf must bf vblid for tiis frbmf's mftiod bnd visiblf
     * bddording to tif rulfs dfsdribfd in {@link #visiblfVbribblfs}.
     * <p>
     * Objfdt vblufs must bf bssignmfnt dompbtiblf witi tif vbribblf typf
     * (Tiis implifs tibt tif vbribblf typf must bf lobdfd tirougi tif
     * fndlosing dlbss's dlbss lobdfr). Primitivf vblufs must bf
     * fitifr bssignmfnt dompbtiblf witi tif vbribblf typf or must bf
     * donvfrtiblf to tif vbribblf typf witiout loss of informbtion.
     * Sff JLS sfdtion 5.2 for morf informbtion on bssignmfnt
     * dompbtibility.
     *
     * @pbrbm vbribblf tif fifld dontbining tif rfqufstfd vbluf
     * @pbrbm vbluf tif nfw vbluf to bssign
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if tif fifld is not vblid for
     * tiis objfdt's dlbss.
     * @tirows InvblidTypfExdfption if tif vbluf's typf dofs not mbtdi
     * tif vbribblf's typf.
     * @tirows ClbssNotLobdfdExdfption if tif vbribblf typf ibs not yft bffn lobdfd
     * tirougi tif bppropribtf dlbss lobdfr.
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void sftVbluf(LodblVbribblf vbribblf, Vbluf vbluf)
        tirows InvblidTypfExdfption, ClbssNotLobdfdExdfption;

    /**
     * Rfturns tif vblufs of bll brgumfnts in tiis frbmf.  Vblufs brf
     * rfturnfd fvfn if no lodbl vbribblf informbtion is prfsfnt.
     *
     * @rfturn b list dontbining b {@link Vbluf} objfdt for fbdi brgumfnt
     * to tiis frbmf, in tif ordfr in wiidi tif brgumfnts wfrf
     * dfdlbrfd.  If tif mftiod dorrfsponding to tiis frbmf ibs
     * no brgumfnts, bn fmpty list is rfturnfd.
     *
     * @tirows InvblidStbdkFrbmfExdfption if tiis stbdk frbmf ibs bfdomf
     * invblid. Ondf tif frbmf's tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.
     * @sindf 1.6
     */
    List<Vbluf> gftArgumfntVblufs();

}
