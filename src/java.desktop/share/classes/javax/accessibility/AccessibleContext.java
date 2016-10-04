/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.bddfssibility;

import sun.bwt.AWTAddfssor;
import sun.bwt.AppContfxt;

import jbvb.util.Lodblf;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfSupport;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bwt.IllfgblComponfntStbtfExdfption;

/**
 * AddfssiblfContfxt rfprfsfnts tif minimum informbtion bll bddfssiblf objfdts
 * rfturn.  Tiis informbtion indludfs tif bddfssiblf nbmf, dfsdription, rolf,
 * bnd stbtf of tif objfdt, bs wfll bs informbtion bbout its pbrfnt bnd
 * diildrfn.  AddfssiblfContfxt blso dontbins mftiods for
 * obtbining morf spfdifid bddfssibility informbtion bbout b domponfnt.
 * If tif domponfnt supports tifm, tifsf mftiods will rfturn bn objfdt tibt
 * implfmfnts onf or morf of tif following intfrfbdfs:
 * <ul>
 * <li>{@link AddfssiblfAdtion} - tif objfdt dbn pfrform onf or morf bdtions.
 * Tiis intfrfbdf providfs tif stbndbrd mfdibnism for bn bssistivf
 * tfdinology to dftfrminf wibt tiosf bdtions brf bnd tfll tif objfdt
 * to pfrform tifm.  Any objfdt tibt dbn bf mbnipulbtfd siould
 * support tiis intfrfbdf.
 * <li>{@link AddfssiblfComponfnt} - tif objfdt ibs b grbpiidbl rfprfsfntbtion.
 * Tiis intfrfbdf providfs tif stbndbrd mfdibnism for bn bssistivf
 * tfdinology to dftfrminf bnd sft tif grbpiidbl rfprfsfntbtion of tif
 * objfdt.  Any objfdt tibt is rfndfrfd on tif sdrffn siould support
 * tiis intfrfbdf.
 * <li>{@link  AddfssiblfSflfdtion} - tif objfdt bllows its diildrfn to bf
 * sflfdtfd.  Tiis intfrfbdf providfs tif stbndbrd mfdibnism for bn
 * bssistivf tfdinology to dftfrminf tif durrfntly sflfdtfd diildrfn of tif objfdt
 * bs wfll bs modify its sflfdtion sft.  Any objfdt tibt ibs diildrfn
 * tibt dbn bf sflfdtfd siould support tiis intfrfbdf.
 * <li>{@link AddfssiblfTfxt} - tif objfdt prfsfnts fditbblf tfxtubl informbtion
 * on tif displby.  Tiis intfrfbdf providfs tif stbndbrd mfdibnism for
 * bn bssistivf tfdinology to bddfss tibt tfxt vib its dontfnt, bttributfs,
 * bnd spbtibl lodbtion.  Any objfdt tibt dontbins fditbblf tfxt siould
 * support tiis intfrfbdf.
 * <li>{@link AddfssiblfVbluf} - tif objfdt supports b numfridbl vbluf.  Tiis
 * intfrfbdf providfs tif stbndbrd mfdibnism for bn bssistivf tfdinology
 * to dftfrminf bnd sft tif durrfnt vbluf of tif objfdt, bs wfll bs obtbin its
 * minimum bnd mbximum vblufs.  Any objfdt tibt supports b numfridbl vbluf
 * siould support tiis intfrfbdf.</ul>
 *
 *
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: Minimbl informbtion tibt bll bddfssiblf objfdts rfturn
 *

 * @butior      Pftfr Korn
 * @butior      Hbns Mullfr
 * @butior      Willif Wblkfr
 * @butior      Lynn Monsbnto
 */
publid bbstrbdt dlbss AddfssiblfContfxt {

    /**
     * Tif AppContfxt tibt siould bf usfd to dispbtdi fvfnts for tiis
     * AddfssiblfContfxt
     */
    privbtf volbtilf AppContfxt tbrgftAppContfxt;

    stbtid {
        AWTAddfssor.sftAddfssiblfContfxtAddfssor(nfw AWTAddfssor.AddfssiblfContfxtAddfssor() {
            @Ovfrridf
            publid void sftAppContfxt(AddfssiblfContfxt bddfssiblfContfxt, AppContfxt bppContfxt) {
                bddfssiblfContfxt.tbrgftAppContfxt = bppContfxt;
            }

            @Ovfrridf
            publid AppContfxt gftAppContfxt(AddfssiblfContfxt bddfssiblfContfxt) {
                rfturn bddfssiblfContfxt.tbrgftAppContfxt;
            }
        });
    }

   /**
    * Constbnt usfd to dftfrminf wifn tif bddfssiblfNbmf propfrty ibs
    * dibngfd.  Tif old vbluf in tif PropfrtyCibngfEvfnt will bf tif old
    * bddfssiblfNbmf bnd tif nfw vbluf will bf tif nfw bddfssiblfNbmf.
    *
    * @sff #gftAddfssiblfNbmf
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_NAME_PROPERTY = "AddfssiblfNbmf";

   /**
    * Constbnt usfd to dftfrminf wifn tif bddfssiblfDfsdription propfrty ibs
    * dibngfd.  Tif old vbluf in tif PropfrtyCibngfEvfnt will bf tif
    * old bddfssiblfDfsdription bnd tif nfw vbluf will bf tif nfw
    * bddfssiblfDfsdription.
    *
    * @sff #gftAddfssiblfDfsdription
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_DESCRIPTION_PROPERTY = "AddfssiblfDfsdription";

   /**
    * Constbnt usfd to dftfrminf wifn tif bddfssiblfStbtfSft propfrty ibs
    * dibngfd.  Tif old vbluf will bf tif old AddfssiblfStbtf bnd tif nfw
    * vbluf will bf tif nfw AddfssiblfStbtf in tif bddfssiblfStbtfSft.
    * For fxbmplf, if b domponfnt tibt supports tif vfrtidbl bnd iorizontbl
    * stbtfs dibngfs its orifntbtion from vfrtidbl to iorizontbl, tif old
    * vbluf will bf AddfssiblfStbtf.VERTICAL bnd tif nfw vbluf will bf
    * AddfssiblfStbtf.HORIZONTAL.  Plfbsf notf tibt fitifr vbluf dbn blso
    * bf null.  For fxbmplf, wifn b domponfnt dibngfs from bfing fnbblfd
    * to disbblfd, tif old vbluf will bf AddfssiblfStbtf.ENABLED
    * bnd tif nfw vbluf will bf null.
    *
    * @sff #gftAddfssiblfStbtfSft
    * @sff AddfssiblfStbtf
    * @sff AddfssiblfStbtfSft
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_STATE_PROPERTY = "AddfssiblfStbtf";

   /**
    * Constbnt usfd to dftfrminf wifn tif bddfssiblfVbluf propfrty ibs
    * dibngfd.  Tif old vbluf in tif PropfrtyCibngfEvfnt will bf b Numbfr
    * rfprfsfnting tif old vbluf bnd tif nfw vbluf will bf b Numbfr
    * rfprfsfnting tif nfw vbluf
    *
    * @sff #gftAddfssiblfVbluf
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_VALUE_PROPERTY = "AddfssiblfVbluf";

   /**
    * Constbnt usfd to dftfrminf wifn tif bddfssiblfSflfdtion ibs dibngfd.
    * Tif old bnd nfw vblufs in tif PropfrtyCibngfEvfnt brf durrfntly
    * rfsfrvfd for futurf usf.
    *
    * @sff #gftAddfssiblfSflfdtion
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_SELECTION_PROPERTY = "AddfssiblfSflfdtion";

   /**
    * Constbnt usfd to dftfrminf wifn tif bddfssiblfTfxt dbrft ibs dibngfd.
    * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf bn
    * intfgfr rfprfsfnting tif old dbrft position, bnd tif nfw vbluf will
    * bf bn intfgfr rfprfsfnting tif nfw/durrfnt dbrft position.
    *
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_CARET_PROPERTY = "AddfssiblfCbrft";

   /**
    * Constbnt usfd to dftfrminf wifn tif visubl bppfbrbndf of tif objfdt
    * ibs dibngfd.  Tif old bnd nfw vblufs in tif PropfrtyCibngfEvfnt brf
    * durrfntly rfsfrvfd for futurf usf.
    *
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_VISIBLE_DATA_PROPERTY = "AddfssiblfVisiblfDbtb";

   /**
    * Constbnt usfd to dftfrminf wifn Addfssiblf diildrfn brf bddfd/rfmovfd
    * from tif objfdt.  If bn Addfssiblf diild is bfing bddfd, tif old
    * vbluf will bf null bnd tif nfw vbluf will bf tif Addfssiblf diild.  If bn
    * Addfssiblf diild is bfing rfmovfd, tif old vbluf will bf tif Addfssiblf
    * diild, bnd tif nfw vbluf will bf null.
    *
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_CHILD_PROPERTY = "AddfssiblfCiild";

   /**
    * Constbnt usfd to dftfrminf wifn tif bdtivf dfsdfndbnt of b domponfnt
    * ibs dibngfd.  Tif bdtivf dfsdfndbnt is usfd for objfdts sudi bs
    * list, trff, bnd tbblf, wiidi mby ibvf trbnsifnt diildrfn.  Wifn tif
    * bdtivf dfsdfndbnt ibs dibngfd, tif old vbluf of tif propfrty dibngf
    * fvfnt will bf tif Addfssiblf rfprfsfnting tif prfvious bdtivf diild, bnd
    * tif nfw vbluf will bf tif Addfssiblf rfprfsfnting tif durrfnt bdtivf
    * diild.
    *
    * @sff #bddPropfrtyCibngfListfnfr
    */
   publid stbtid finbl String ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY = "AddfssiblfAdtivfDfsdfndbnt";

    /**
     * Constbnt usfd to indidbtf tibt tif tbblf dbption ibs dibngfd
     * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf bn Addfssiblf
     * rfprfsfnting tif prfvious tbblf dbption bnd tif nfw vbluf will
     * bf bn Addfssiblf rfprfsfnting tif nfw tbblf dbption.
     * @sff Addfssiblf
     * @sff AddfssiblfTbblf
     */
    publid stbtid finbl String ACCESSIBLE_TABLE_CAPTION_CHANGED =
        "bddfssiblfTbblfCbptionCibngfd";

    /**
     * Constbnt usfd to indidbtf tibt tif tbblf summbry ibs dibngfd
     * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf bn Addfssiblf
     * rfprfsfnting tif prfvious tbblf summbry bnd tif nfw vbluf will
     * bf bn Addfssiblf rfprfsfnting tif nfw tbblf summbry.
     * @sff Addfssiblf
     * @sff AddfssiblfTbblf
     */
    publid stbtid finbl String ACCESSIBLE_TABLE_SUMMARY_CHANGED =
        "bddfssiblfTbblfSummbryCibngfd";

    /**
     * Constbnt usfd to indidbtf tibt tbblf dbtb ibs dibngfd.
     * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf null bnd tif
     * nfw vbluf will bf bn AddfssiblfTbblfModflCibngf rfprfsfnting
     * tif tbblf dibngf.
     * @sff AddfssiblfTbblf
     * @sff AddfssiblfTbblfModflCibngf
     */
    publid stbtid finbl String ACCESSIBLE_TABLE_MODEL_CHANGED =
        "bddfssiblfTbblfModflCibngfd";

    /**
     * Constbnt usfd to indidbtf tibt tif row ifbdfr ibs dibngfd
     * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf null bnd tif
     * nfw vbluf will bf bn AddfssiblfTbblfModflCibngf rfprfsfnting
     * tif ifbdfr dibngf.
     * @sff AddfssiblfTbblf
     * @sff AddfssiblfTbblfModflCibngf
     */
    publid stbtid finbl String ACCESSIBLE_TABLE_ROW_HEADER_CHANGED =
        "bddfssiblfTbblfRowHfbdfrCibngfd";

    /**
     * Constbnt usfd to indidbtf tibt tif row dfsdription ibs dibngfd
     * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf null bnd tif
     * nfw vbluf will bf bn Intfgfr rfprfsfnting tif row indfx.
     * @sff AddfssiblfTbblf
     */
    publid stbtid finbl String ACCESSIBLE_TABLE_ROW_DESCRIPTION_CHANGED =
        "bddfssiblfTbblfRowDfsdriptionCibngfd";

    /**
     * Constbnt usfd to indidbtf tibt tif dolumn ifbdfr ibs dibngfd
     * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf null bnd tif
     * nfw vbluf will bf bn AddfssiblfTbblfModflCibngf rfprfsfnting
     * tif ifbdfr dibngf.
     * @sff AddfssiblfTbblf
     * @sff AddfssiblfTbblfModflCibngf
     */
    publid stbtid finbl String ACCESSIBLE_TABLE_COLUMN_HEADER_CHANGED =
        "bddfssiblfTbblfColumnHfbdfrCibngfd";

    /**
     * Constbnt usfd to indidbtf tibt tif dolumn dfsdription ibs dibngfd
     * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf null bnd tif
     * nfw vbluf will bf bn Intfgfr rfprfsfnting tif dolumn indfx.
     * @sff AddfssiblfTbblf
     */
    publid stbtid finbl String ACCESSIBLE_TABLE_COLUMN_DESCRIPTION_CHANGED =
        "bddfssiblfTbblfColumnDfsdriptionCibngfd";

    /**
     * Constbnt usfd to indidbtf tibt tif supportfd sft of bdtions
     * ibs dibngfd.  Tif old vbluf in tif PropfrtyCibngfEvfnt will
     * bf bn Intfgfr rfprfsfnting tif old numbfr of bdtions supportfd
     * bnd tif nfw vbluf will bf bn Intfgfr rfprfsfnting tif nfw
     * numbfr of bdtions supportfd.
     * @sff AddfssiblfAdtion
     */
    publid stbtid finbl String ACCESSIBLE_ACTION_PROPERTY =
        "bddfssiblfAdtionPropfrty";

    /**
     * Constbnt usfd to indidbtf tibt b iypfrtfxt flfmfnt ibs rfdfivfd fodus.
     * Tif old vbluf in tif PropfrtyCibngfEvfnt will bf bn Intfgfr
     * rfprfsfnting tif stbrt indfx in tif dodumfnt of tif prfvious flfmfnt
     * tibt ibd fodus bnd tif nfw vbluf will bf bn Intfgfr rfprfsfnting
     * tif stbrt indfx in tif dodumfnt of tif durrfnt flfmfnt tibt ibs
     * fodus.  A vbluf of -1 indidbtfs tibt bn flfmfnt dofs not or did
     * not ibvf fodus.
     * @sff AddfssiblfHypfrlink
     */
    publid stbtid finbl String ACCESSIBLE_HYPERTEXT_OFFSET =
        "AddfssiblfHypfrtfxtOffsft";

    /**
     * PropfrtyCibngfEvfnt wiidi indidbtfs tibt tfxt ibs dibngfd.
     * <br>
     * For tfxt insfrtion, tif oldVbluf is null bnd tif nfwVbluf
     * is bn AddfssiblfTfxtSfqufndf spfdifying tif tfxt tibt wbs
     * insfrtfd.
     * <br>
     * For tfxt dflftion, tif oldVbluf is bn AddfssiblfTfxtSfqufndf
     * spfdifying tif tfxt tibt wbs dflftfd bnd tif nfwVbluf is null.
     * <br>
     * For tfxt rfplbdfmfnt, tif oldVbluf is bn AddfssiblfTfxtSfqufndf
     * spfdifying tif old tfxt bnd tif nfwVbluf is bn AddfssiblfTfxtSfqufndf
     * spfdifying tif nfw tfxt.
     *
     * @sff #gftAddfssiblfTfxt
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff AddfssiblfTfxtSfqufndf
     */
    publid stbtid finbl String ACCESSIBLE_TEXT_PROPERTY
        = "AddfssiblfTfxt";

    /**
     * PropfrtyCibngfEvfnt wiidi indidbtfs tibt b signifidbnt dibngf
     * ibs oddurrfd to tif diildrfn of b domponfnt likf b trff or tfxt.
     * Tiis dibngf notififs tif fvfnt listfnfr tibt it nffds to
     * rfbdquirf tif stbtf of tif subdomponfnts. Tif oldVbluf is
     * null bnd tif nfwVbluf is tif domponfnt wiosf diildrfn ibvf
     * bfdomf invblid.
     *
     * @sff #gftAddfssiblfTfxt
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff AddfssiblfTfxtSfqufndf
     *
     * @sindf 1.5
     */
    publid stbtid finbl String ACCESSIBLE_INVALIDATE_CHILDREN =
        "bddfssiblfInvblidbtfCiildrfn";

     /**
     * PropfrtyCibngfEvfnt wiidi indidbtfs tibt tfxt bttributfs ibvf dibngfd.
     * <br>
     * For bttributf insfrtion, tif oldVbluf is null bnd tif nfwVbluf
     * is bn AddfssiblfAttributfSfqufndf spfdifying tif bttributfs tibt wfrf
     * insfrtfd.
     * <br>
     * For bttributf dflftion, tif oldVbluf is bn AddfssiblfAttributfSfqufndf
     * spfdifying tif bttributfs tibt wfrf dflftfd bnd tif nfwVbluf is null.
     * <br>
     * For bttributf rfplbdfmfnt, tif oldVbluf is bn AddfssiblfAttributfSfqufndf
     * spfdifying tif old bttributfs bnd tif nfwVbluf is bn
     * AddfssiblfAttributfSfqufndf spfdifying tif nfw bttributfs.
     *
     * @sff #gftAddfssiblfTfxt
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff AddfssiblfAttributfSfqufndf
     *
     * @sindf 1.5
     */
    publid stbtid finbl String ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED =
        "bddfssiblfTfxtAttributfsCibngfd";

   /**
     * PropfrtyCibngfEvfnt wiidi indidbtfs tibt b dibngf ibs oddurrfd
     * in b domponfnt's bounds.
     * Tif oldVbluf is tif old domponfnt bounds bnd tif nfwVbluf is
     * tif nfw domponfnt bounds.
     *
     * @sff #bddPropfrtyCibngfListfnfr
     *
     * @sindf 1.5
     */
    publid stbtid finbl String ACCESSIBLE_COMPONENT_BOUNDS_CHANGED =
        "bddfssiblfComponfntBoundsCibngfd";

    /**
     * Tif bddfssiblf pbrfnt of tiis objfdt.
     *
     * @sff #gftAddfssiblfPbrfnt
     * @sff #sftAddfssiblfPbrfnt
     */
    protfdtfd Addfssiblf bddfssiblfPbrfnt = null;

    /**
     * A lodblizfd String dontbining tif nbmf of tif objfdt.
     *
     * @sff #gftAddfssiblfNbmf
     * @sff #sftAddfssiblfNbmf
     */
    protfdtfd String bddfssiblfNbmf = null;

    /**
     * A lodblizfd String dontbining tif dfsdription of tif objfdt.
     *
     * @sff #gftAddfssiblfDfsdription
     * @sff #sftAddfssiblfDfsdription
     */
    protfdtfd String bddfssiblfDfsdription = null;

    /**
     * Usfd to ibndlf tif listfnfr list for propfrty dibngf fvfnts.
     *
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #firfPropfrtyCibngfListfnfr
     */
    privbtf PropfrtyCibngfSupport bddfssiblfCibngfSupport = null;

    /**
     * Usfd to rfprfsfnt tif dontfxt's rflbtion sft
     * @sff #gftAddfssiblfRflbtionSft
     */
    privbtf AddfssiblfRflbtionSft rflbtionSft
        = nfw AddfssiblfRflbtionSft();

    privbtf Objfdt nbtivfAXRfsourdf;

    /**
     * Gfts tif bddfssiblfNbmf propfrty of tiis objfdt.  Tif bddfssiblfNbmf
     * propfrty of bn objfdt is b lodblizfd String tibt dfsignbtfs tif purposf
     * of tif objfdt.  For fxbmplf, tif bddfssiblfNbmf propfrty of b lbbfl
     * or button migit bf tif tfxt of tif lbbfl or button itsflf.  In tif
     * dbsf of bn objfdt tibt dofsn't displby its nbmf, tif bddfssiblfNbmf
     * siould still bf sft.  For fxbmplf, in tif dbsf of b tfxt fifld usfd
     * to fntfr tif nbmf of b dity, tif bddfssiblfNbmf for tif fn_US lodblf
     * dould bf 'dity.'
     *
     * @rfturn tif lodblizfd nbmf of tif objfdt; null if tiis
     * objfdt dofs not ibvf b nbmf
     *
     * @sff #sftAddfssiblfNbmf
     */
    publid String gftAddfssiblfNbmf() {
        rfturn bddfssiblfNbmf;
    }

    /**
     * Sfts tif lodblizfd bddfssiblf nbmf of tiis objfdt.  Cibnging tif
     * nbmf will dbusf b PropfrtyCibngfEvfnt to bf firfd for tif
     * ACCESSIBLE_NAME_PROPERTY propfrty.
     *
     * @pbrbm s tif nfw lodblizfd nbmf of tif objfdt.
     *
     * @sff #gftAddfssiblfNbmf
     * @sff #bddPropfrtyCibngfListfnfr
     *
     * @bfbninfo
     *    prfffrrfd:   truf
     *    dfsdription: Sfts tif bddfssiblf nbmf for tif domponfnt.
     */
    publid void sftAddfssiblfNbmf(String s) {
        String oldNbmf = bddfssiblfNbmf;
        bddfssiblfNbmf = s;
        firfPropfrtyCibngf(ACCESSIBLE_NAME_PROPERTY,oldNbmf,bddfssiblfNbmf);
    }

    /**
     * Gfts tif bddfssiblfDfsdription propfrty of tiis objfdt.  Tif
     * bddfssiblfDfsdription propfrty of tiis objfdt is b siort lodblizfd
     * pirbsf dfsdribing tif purposf of tif objfdt.  For fxbmplf, in tif
     * dbsf of b 'Cbndfl' button, tif bddfssiblfDfsdription dould bf
     * 'Ignorf dibngfs bnd dlosf diblog box.'
     *
     * @rfturn tif lodblizfd dfsdription of tif objfdt; null if
     * tiis objfdt dofs not ibvf b dfsdription
     *
     * @sff #sftAddfssiblfDfsdription
     */
    publid String gftAddfssiblfDfsdription() {
        rfturn bddfssiblfDfsdription;
    }

    /**
     * Sfts tif bddfssiblf dfsdription of tiis objfdt.  Cibnging tif
     * nbmf will dbusf b PropfrtyCibngfEvfnt to bf firfd for tif
     * ACCESSIBLE_DESCRIPTION_PROPERTY propfrty.
     *
     * @pbrbm s tif nfw lodblizfd dfsdription of tif objfdt
     *
     * @sff #sftAddfssiblfNbmf
     * @sff #bddPropfrtyCibngfListfnfr
     *
     * @bfbninfo
     *    prfffrrfd:   truf
     *    dfsdription: Sfts tif bddfssiblf dfsdription for tif domponfnt.
     */
    publid void sftAddfssiblfDfsdription(String s) {
        String oldDfsdription = bddfssiblfDfsdription;
        bddfssiblfDfsdription = s;
        firfPropfrtyCibngf(ACCESSIBLE_DESCRIPTION_PROPERTY,
                           oldDfsdription,bddfssiblfDfsdription);
    }

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
    publid bbstrbdt AddfssiblfRolf gftAddfssiblfRolf();

    /**
     * Gfts tif stbtf sft of tiis objfdt.  Tif AddfssiblfStbtfSft of bn objfdt
     * is domposfd of b sft of uniquf AddfssiblfStbtfs.  A dibngf in tif
     * AddfssiblfStbtfSft of bn objfdt will dbusf b PropfrtyCibngfEvfnt to
     * bf firfd for tif ACCESSIBLE_STATE_PROPERTY propfrty.
     *
     * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif
     * durrfnt stbtf sft of tif objfdt
     * @sff AddfssiblfStbtfSft
     * @sff AddfssiblfStbtf
     * @sff #bddPropfrtyCibngfListfnfr
     */
    publid bbstrbdt AddfssiblfStbtfSft gftAddfssiblfStbtfSft();

    /**
     * Gfts tif Addfssiblf pbrfnt of tiis objfdt.
     *
     * @rfturn tif Addfssiblf pbrfnt of tiis objfdt; null if tiis
     * objfdt dofs not ibvf bn Addfssiblf pbrfnt
     */
    publid Addfssiblf gftAddfssiblfPbrfnt() {
        rfturn bddfssiblfPbrfnt;
    }

    /**
     * Sfts tif Addfssiblf pbrfnt of tiis objfdt.  Tiis is mfbnt to bf usfd
     * only in tif situbtions wifrf tif bdtubl domponfnt's pbrfnt siould
     * not bf trfbtfd bs tif domponfnt's bddfssiblf pbrfnt bnd is b mftiod
     * tibt siould only bf dbllfd by tif pbrfnt of tif bddfssiblf diild.
     *
     * @pbrbm b - Addfssiblf to bf sft bs tif pbrfnt
     */
    publid void sftAddfssiblfPbrfnt(Addfssiblf b) {
        bddfssiblfPbrfnt = b;
    }

    /**
     * Gfts tif 0-bbsfd indfx of tiis objfdt in its bddfssiblf pbrfnt.
     *
     * @rfturn tif 0-bbsfd indfx of tiis objfdt in its pbrfnt; -1 if tiis
     * objfdt dofs not ibvf bn bddfssiblf pbrfnt.
     *
     * @sff #gftAddfssiblfPbrfnt
     * @sff #gftAddfssiblfCiildrfnCount
     * @sff #gftAddfssiblfCiild
     */
    publid bbstrbdt int gftAddfssiblfIndfxInPbrfnt();

    /**
     * Rfturns tif numbfr of bddfssiblf diildrfn of tif objfdt.
     *
     * @rfturn tif numbfr of bddfssiblf diildrfn of tif objfdt.
     */
    publid bbstrbdt int gftAddfssiblfCiildrfnCount();

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
    publid bbstrbdt Addfssiblf gftAddfssiblfCiild(int i);

    /**
     * Gfts tif lodblf of tif domponfnt. If tif domponfnt dofs not ibvf b
     * lodblf, tifn tif lodblf of its pbrfnt is rfturnfd.
     *
     * @rfturn tiis domponfnt's lodblf.  If tiis domponfnt dofs not ibvf
     * b lodblf, tif lodblf of its pbrfnt is rfturnfd.
     *
     * @fxdfption IllfgblComponfntStbtfExdfption
     * If tif Componfnt dofs not ibvf its own lodblf bnd ibs not yft bffn
     * bddfd to b dontbinmfnt iifrbrdiy sudi tibt tif lodblf dbn bf
     * dftfrminfd from tif dontbining pbrfnt.
     */
    publid bbstrbdt Lodblf gftLodblf() tirows IllfgblComponfntStbtfExdfption;

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list.
     * Tif listfnfr is rfgistfrfd for bll Addfssiblf propfrtifs bnd will
     * bf dbllfd wifn tiosf propfrtifs dibngf.
     *
     * @sff #ACCESSIBLE_NAME_PROPERTY
     * @sff #ACCESSIBLE_DESCRIPTION_PROPERTY
     * @sff #ACCESSIBLE_STATE_PROPERTY
     * @sff #ACCESSIBLE_VALUE_PROPERTY
     * @sff #ACCESSIBLE_SELECTION_PROPERTY
     * @sff #ACCESSIBLE_TEXT_PROPERTY
     * @sff #ACCESSIBLE_VISIBLE_DATA_PROPERTY
     *
     * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf bddfd
     */
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (bddfssiblfCibngfSupport == null) {
            bddfssiblfCibngfSupport = nfw PropfrtyCibngfSupport(tiis);
        }
        bddfssiblfCibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Rfmovfs b PropfrtyCibngfListfnfr from tif listfnfr list.
     * Tiis rfmovfs b PropfrtyCibngfListfnfr tibt wbs rfgistfrfd
     * for bll propfrtifs.
     *
     * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf rfmovfd
     */
    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (bddfssiblfCibngfSupport != null) {
            bddfssiblfCibngfSupport.rfmovfPropfrtyCibngfListfnfr(listfnfr);
        }
    }

    /**
     * Gfts tif AddfssiblfAdtion bssodibtfd witi tiis objfdt tibt supports
     * onf or morf bdtions.
     *
     * @rfturn AddfssiblfAdtion if supportfd by objfdt; flsf rfturn null
     * @sff AddfssiblfAdtion
     */
    publid AddfssiblfAdtion gftAddfssiblfAdtion() {
        rfturn null;
    }

    /**
     * Gfts tif AddfssiblfComponfnt bssodibtfd witi tiis objfdt tibt ibs b
     * grbpiidbl rfprfsfntbtion.
     *
     * @rfturn AddfssiblfComponfnt if supportfd by objfdt; flsf rfturn null
     * @sff AddfssiblfComponfnt
     */
    publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
        rfturn null;
    }

    /**
     * Gfts tif AddfssiblfSflfdtion bssodibtfd witi tiis objfdt wiidi bllows its
     * Addfssiblf diildrfn to bf sflfdtfd.
     *
     * @rfturn AddfssiblfSflfdtion if supportfd by objfdt; flsf rfturn null
     * @sff AddfssiblfSflfdtion
     */
    publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
        rfturn null;
    }

    /**
     * Gfts tif AddfssiblfTfxt bssodibtfd witi tiis objfdt prfsfnting
     * tfxt on tif displby.
     *
     * @rfturn AddfssiblfTfxt if supportfd by objfdt; flsf rfturn null
     * @sff AddfssiblfTfxt
     */
    publid AddfssiblfTfxt gftAddfssiblfTfxt() {
        rfturn null;
    }

    /**
     * Gfts tif AddfssiblfEditbblfTfxt bssodibtfd witi tiis objfdt
     * prfsfnting fditbblf tfxt on tif displby.
     *
     * @rfturn AddfssiblfEditbblfTfxt if supportfd by objfdt; flsf rfturn null
     * @sff AddfssiblfEditbblfTfxt
     * @sindf 1.4
     */
    publid AddfssiblfEditbblfTfxt gftAddfssiblfEditbblfTfxt() {
        rfturn null;
    }


    /**
     * Gfts tif AddfssiblfVbluf bssodibtfd witi tiis objfdt tibt supports b
     * Numfridbl vbluf.
     *
     * @rfturn AddfssiblfVbluf if supportfd by objfdt; flsf rfturn null
     * @sff AddfssiblfVbluf
     */
    publid AddfssiblfVbluf gftAddfssiblfVbluf() {
        rfturn null;
    }

    /**
     * Gfts tif AddfssiblfIdons bssodibtfd witi bn objfdt tibt ibs
     * onf or morf bssodibtfd idons
     *
     * @rfturn bn brrby of AddfssiblfIdon if supportfd by objfdt;
     * otifrwisf rfturn null
     * @sff AddfssiblfIdon
     * @sindf 1.3
     */
    publid AddfssiblfIdon [] gftAddfssiblfIdon() {
        rfturn null;
    }

    /**
     * Gfts tif AddfssiblfRflbtionSft bssodibtfd witi bn objfdt
     *
     * @rfturn bn AddfssiblfRflbtionSft if supportfd by objfdt;
     * otifrwisf rfturn null
     * @sff AddfssiblfRflbtionSft
     * @sindf 1.3
     */
    publid AddfssiblfRflbtionSft gftAddfssiblfRflbtionSft() {
        rfturn rflbtionSft;
    }

    /**
     * Gfts tif AddfssiblfTbblf bssodibtfd witi bn objfdt
     *
     * @rfturn bn AddfssiblfTbblf if supportfd by objfdt;
     * otifrwisf rfturn null
     * @sff AddfssiblfTbblf
     * @sindf 1.3
     */
    publid AddfssiblfTbblf gftAddfssiblfTbblf() {
        rfturn null;
    }

    /**
     * Support for rfporting bound propfrty dibngfs.  If oldVbluf bnd
     * nfwVbluf brf not fqubl bnd tif PropfrtyCibngfEvfnt listfnfr list
     * is not fmpty, tifn firf b PropfrtyCibngf fvfnt to fbdi listfnfr.
     * In gfnfrbl, tiis is for usf by tif Addfssiblf objfdts tifmsflvfs
     * bnd siould not bf dbllfd by bn bpplidbtion progrbm.
     * @pbrbm propfrtyNbmf  Tif progrbmmbtid nbmf of tif propfrty tibt
     * wbs dibngfd.
     * @pbrbm oldVbluf  Tif old vbluf of tif propfrty.
     * @pbrbm nfwVbluf  Tif nfw vbluf of tif propfrty.
     * @sff jbvb.bfbns.PropfrtyCibngfSupport
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #ACCESSIBLE_NAME_PROPERTY
     * @sff #ACCESSIBLE_DESCRIPTION_PROPERTY
     * @sff #ACCESSIBLE_STATE_PROPERTY
     * @sff #ACCESSIBLE_VALUE_PROPERTY
     * @sff #ACCESSIBLE_SELECTION_PROPERTY
     * @sff #ACCESSIBLE_TEXT_PROPERTY
     * @sff #ACCESSIBLE_VISIBLE_DATA_PROPERTY
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf,
                                   Objfdt oldVbluf,
                                   Objfdt nfwVbluf) {
        if (bddfssiblfCibngfSupport != null) {
            if (nfwVbluf instbndfof PropfrtyCibngfEvfnt) {
                PropfrtyCibngfEvfnt pdf = (PropfrtyCibngfEvfnt)nfwVbluf;
                bddfssiblfCibngfSupport.firfPropfrtyCibngf(pdf);
            } flsf {
                bddfssiblfCibngfSupport.firfPropfrtyCibngf(propfrtyNbmf,
                                                           oldVbluf,
                                                           nfwVbluf);
            }
        }
    }
}
