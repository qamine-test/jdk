/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sql;

/**
 * An objfdt tibt dbn bf usfd to gft informbtion bbout tif typfs
 * bnd propfrtifs for fbdi pbrbmftfr mbrkfr in b
 * <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt. For somf qufrifs bnd drivfr
 * implfmfntbtions, tif dbtb tibt would bf rfturnfd by b <dodf>PbrbmftfrMftbDbtb</dodf>
 * objfdt mby not bf bvbilbblf until tif <dodf>PrfpbrfdStbtfmfnt</dodf> ibs
 * bffn fxfdutfd.
 *<p>
 *Somf drivfr implfmfntbtions mby not bf bblf to providf informbtion bbout tif
 *typfs bnd propfrtifs for fbdi pbrbmftfr mbrkfr in b <dodf>CbllbblfStbtfmfnt</dodf>
 *objfdt.
 *
 * @sindf 1.4
 */

publid intfrfbdf PbrbmftfrMftbDbtb fxtfnds Wrbppfr {

    /**
     * Rftrifvfs tif numbfr of pbrbmftfrs in tif <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt for wiidi tiis <dodf>PbrbmftfrMftbDbtb</dodf> objfdt dontbins
     * informbtion.
     *
     * @rfturn tif numbfr of pbrbmftfrs
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftPbrbmftfrCount() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr null vblufs brf bllowfd in tif dfsignbtfd pbrbmftfr.
     *
     * @pbrbm pbrbm tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn tif nullbbility stbtus of tif givfn pbrbmftfr; onf of
     *        <dodf>PbrbmftfrMftbDbtb.pbrbmftfrNoNulls</dodf>,
     *        <dodf>PbrbmftfrMftbDbtb.pbrbmftfrNullbblf</dodf>, or
     *        <dodf>PbrbmftfrMftbDbtb.pbrbmftfrNullbblfUnknown</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int isNullbblf(int pbrbm) tirows SQLExdfption;

    /**
     * Tif donstbnt indidbting tibt b
     * pbrbmftfr will not bllow <dodf>NULL</dodf> vblufs.
     */
    int pbrbmftfrNoNulls = 0;

    /**
     * Tif donstbnt indidbting tibt b
     * pbrbmftfr will bllow <dodf>NULL</dodf> vblufs.
     */
    int pbrbmftfrNullbblf = 1;

    /**
     * Tif donstbnt indidbting tibt tif
     * nullbbility of b pbrbmftfr is unknown.
     */
    int pbrbmftfrNullbblfUnknown = 2;

    /**
     * Rftrifvfs wiftifr vblufs for tif dfsignbtfd pbrbmftfr dbn bf signfd numbfrs.
     *
     * @pbrbm pbrbm tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    boolfbn isSignfd(int pbrbm) tirows SQLExdfption;

    /**
     * Rftrifvfs tif dfsignbtfd pbrbmftfr's spfdififd dolumn sizf.
     *
     * <P>Tif rfturnfd vbluf rfprfsfnts tif mbximum dolumn sizf for tif givfn pbrbmftfr.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. 0 is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     *
     * @pbrbm pbrbm tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn prfdision
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftPrfdision(int pbrbm) tirows SQLExdfption;

    /**
     * Rftrifvfs tif dfsignbtfd pbrbmftfr's numbfr of digits to rigit of tif dfdimbl point.
     * 0 is rfturnfd for dbtb typfs wifrf tif sdblf is not bpplidbblf.
     *
     * @pbrbm pbrbm tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn sdblf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftSdblf(int pbrbm) tirows SQLExdfption;

    /**
     * Rftrifvfs tif dfsignbtfd pbrbmftfr's SQL typf.
     *
     * @pbrbm pbrbm tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn SQL typf from <dodf>jbvb.sql.Typfs</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     * @sff Typfs
     */
    int gftPbrbmftfrTypf(int pbrbm) tirows SQLExdfption;

    /**
     * Rftrifvfs tif dfsignbtfd pbrbmftfr's dbtbbbsf-spfdifid typf nbmf.
     *
     * @pbrbm pbrbm tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn typf tif nbmf usfd by tif dbtbbbsf. If tif pbrbmftfr typf is
     * b usfr-dffinfd typf, tifn b fully-qublififd typf nbmf is rfturnfd.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    String gftPbrbmftfrTypfNbmf(int pbrbm) tirows SQLExdfption;


    /**
     * Rftrifvfs tif fully-qublififd nbmf of tif Jbvb dlbss wiosf instbndfs
     * siould bf pbssfd to tif mftiod <dodf>PrfpbrfdStbtfmfnt.sftObjfdt</dodf>.
     *
     * @pbrbm pbrbm tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn tif fully-qublififd nbmf of tif dlbss in tif Jbvb progrbmming
     *         lbngubgf tibt would bf usfd by tif mftiod
     *         <dodf>PrfpbrfdStbtfmfnt.sftObjfdt</dodf> to sft tif vbluf
     *         in tif spfdififd pbrbmftfr. Tiis is tif dlbss nbmf usfd
     *         for dustom mbpping.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    String gftPbrbmftfrClbssNbmf(int pbrbm) tirows SQLExdfption;

    /**
     * Tif donstbnt indidbting tibt tif modf of tif pbrbmftfr is unknown.
     */
    int pbrbmftfrModfUnknown = 0;

    /**
     * Tif donstbnt indidbting tibt tif pbrbmftfr's modf is IN.
     */
    int pbrbmftfrModfIn = 1;

    /**
     * Tif donstbnt indidbting tibt tif pbrbmftfr's modf is INOUT.
     */
    int pbrbmftfrModfInOut = 2;

    /**
     * Tif donstbnt indidbting tibt tif pbrbmftfr's modf is  OUT.
     */
    int pbrbmftfrModfOut = 4;

    /**
     * Rftrifvfs tif dfsignbtfd pbrbmftfr's modf.
     *
     * @pbrbm pbrbm tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn modf of tif pbrbmftfr; onf of
     *        <dodf>PbrbmftfrMftbDbtb.pbrbmftfrModfIn</dodf>,
     *        <dodf>PbrbmftfrMftbDbtb.pbrbmftfrModfOut</dodf>, or
     *        <dodf>PbrbmftfrMftbDbtb.pbrbmftfrModfInOut</dodf>
     *        <dodf>PbrbmftfrMftbDbtb.pbrbmftfrModfUnknown</dodf>.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftPbrbmftfrModf(int pbrbm) tirows SQLExdfption;
}
