/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql.rowsft.spi;

import jbvbx.sql.RowSft;
import jbvb.sql.SQLExdfption;

/**
 * Dffinfs b frbmfwork tibt bllows bpplidbtions to usf b mbnubl dfdision trff
 * to dfdidf wibt siould bf donf wifn b syndironizbtion donflidt oddurs.
 * Altiougi it is not mbndbtory for
 * bpplidbtions to rfsolvf syndironizbtion donflidts mbnublly, tiis
 * frbmfwork providfs tif mfbns to dflfgbtf to tif bpplidbtion wifn donflidts
 * brisf.
 * <p>
 * Notf tibt b donflidt is b situbtion wifrf tif <dodf>RowSft</dodf> objfdt's originbl
 * vblufs for b row do not mbtdi tif vblufs in tif dbtb sourdf, wiidi indidbtfs tibt
 * tif dbtb sourdf row ibs bffn modififd sindf tif lbst syndironizbtion. Notf blso tibt
 * b <dodf>RowSft</dodf> objfdt's originbl vblufs brf tif vblufs it ibd just prior to tif
 * tif lbst syndironizbtion, wiidi brf not nfdfssbrily its initibl vblufs.
 *
 *
 * <H2>Dfsdription of b <dodf>SyndRfsolvfr</dodf> Objfdt</H2>
 *
 * A <dodf>SyndRfsolvfr</dodf> objfdt is b spfdiblizfd <dodf>RowSft</dodf> objfdt
 * tibt implfmfnts tif <dodf>SyndRfsolvfr</dodf> intfrfbdf.
 * It <b>mby</b> opfrbtf bs fitifr b donnfdtfd <dodf>RowSft</dodf> objfdt (bn
 * implfmfntbtion of tif <dodf>JdbdRowSft</dodf> intfrfbdf) or b donnfdtfd
 * <dodf>RowSft</dodf> objfdt (bn implfmfntbtion of tif
 * <dodf>CbdifdRowSft</dodf> intfrfbdf or onf of its subintfrfbdfs). For informbtion
 * on tif subintfrfbdfs, sff tif
 * <b irff="../pbdkbgf-summbry.itml"><dodf>jbvbx.sql.rowsft</dodf></b> pbdkbgf
 * dfsdription. Tif rfffrfndf implfmfntbtion for <dodf>SyndRfsolvfr</dodf> implfmfnts
 * tif <dodf>CbdifdRowSft</dodf> intfrfbdf, but otifr implfmfntbtions
 * mby dioosf to implfmfnt tif <dodf>JdbdRowSft</dodf> intfrfbdf to sbtisfy
 * pbrtidulbr nffds.
 * <P>
 * Aftfr bn bpplidbtion ibs bttfmptfd to syndironizf b <dodf>RowSft</dodf> objfdt witi
 * tif dbtb sourdf (by dblling tif <dodf>CbdifdRowSft</dodf>
 * mftiod <dodf>bddfptCibngfs</dodf>), bnd onf or morf donflidts ibvf bffn found,
 * b rowsft's <dodf>SyndProvidfr</dodf> objfdt drfbtfs bn instbndf of
 * <dodf>SyndRfsolvfr</dodf>. Tiis nfw <dodf>SyndRfsolvfr</dodf> objfdt ibs
 * tif sbmf numbfr of rows bnd dolumns bs tif
 * <dodf>RowSft</dodf> objfdt tibt wbs bttfmpting tif syndironizbtion. Tif
 * <dodf>SyndRfsolvfr</dodf> objfdt dontbins tif vblufs from tif dbtb sourdf tibt dbusfd
 * tif donflidt(s) bnd <dodf>null</dodf> for bll otifr vblufs.
 * In bddition, it dontbins informbtion bbout fbdi donflidt.
 *
 *
 * <H2>Gftting bnd Using b <dodf>SyndRfsolvfr</dodf> Objfdt</H2>
 *
 * Wifn tif mftiod <dodf>bddfptCibngfs</dodf> fndountfrs donflidts, tif
 * <dodf>SyndProvidfr</dodf> objfdt drfbtfs b <dodf>SyndProvidfrExdfption</dodf>
 * objfdt bnd sfts it witi tif nfw <dodf>SyndRfsolvfr</dodf> objfdt. Tif mftiod
 * <dodf>bddfptCibngfs</dodf> will tirow tiis fxdfption, wiidi
 * tif bpplidbtion dbn tifn dbtdi bnd usf to rftrifvf tif
 * <dodf>SyndRfsolvfr</dodf> objfdt it dontbins. Tif following dodf snippft usfs tif
 * <dodf>SyndProvidfrExdfption</dodf> mftiod <dodf>gftSyndRfsolvfr</dodf> to gft
 * tif <dodf>SyndRfsolvfr</dodf> objfdt <i>rfsolvfr</i>.
 * <PRE>
 * {@dodf
 *     } dbtdi (SyndProvidfrExdfption spf) {
 *         SyndRfsolvfr rfsolvfr = spf.gftSyndRfsolvfr();
 *     ...
 *     }
 *
 * }
 * </PRE>
 * <P>
 * Witi <i>rfsolvfr</i> in ibnd, bn bpplidbtion dbn usf it to gft tif informbtion
 * it dontbins bbout tif donflidt or donflidts.  A <dodf>SyndRfsolvfr</dodf> objfdt
 * sudi bs <i>rfsolvfr</i> kffps
 * trbdk of tif donflidts for fbdi row in wiidi tifrf is b donflidt.  It blso plbdfs b
 * lodk on tif tbblf or tbblfs bfffdtfd by tif rowsft's dommbnd so tibt no morf
 * donflidts dbn oddur wiilf tif durrfnt donflidts brf bfing rfsolvfd.
 * <P>
 * Tif following kinds of informbtion dbn bf obtbinfd from b <dodf>SyndRfsolvfr</dodf>
 * objfdt:
 *
 *    <i3>Wibt opfrbtion wbs bfing bttfmptfd wifn b donflidt oddurrfd</i3>
 * Tif <dodf>SyndProvidfr</dodf> intfrfbdf dffinfs four donstbnts
 * dfsdribing stbtfs tibt mby oddur. Tirff
 * donstbnts dfsdribf tif typf of opfrbtion (updbtf, dflftf, or insfrt) tibt b
 * <dodf>RowSft</dodf> objfdt wbs bttfmpting to pfrform wifn b donflidt wbs disdovfrfd,
 * bnd tif fourti indidbtfs tibt tifrf is no donflidt.
 * Tifsf donstbnts brf tif possiblf rfturn vblufs wifn b <dodf>SyndRfsolvfr</dodf> objfdt
 * dblls tif mftiod <dodf>gftStbtus</dodf>.
 * <PRE>
 *     {@dodf int opfrbtion = rfsolvfr.gftStbtus(); }
 * </PRE>
 *
 *    <i3>Tif vbluf in tif dbtb sourdf tibt dbusfd b donflidt</i3>
 * A donflidt fxists wifn b vbluf tibt b <dodf>RowSft</dodf> objfdt ibs dibngfd
 * bnd is bttfmpting to writf to tif dbtb sourdf
 * ibs blso bffn dibngfd in tif dbtb sourdf sindf tif lbst syndironizbtion.  An
 * bpplidbtion dbn dbll tif <dodf>SyndRfsolvfr</dodf> mftiod
 * <dodf>gftConflidtVbluf</dodf > to rftrifvf tif
 * vbluf in tif dbtb sourdf tibt is tif dbusf of tif donflidt bfdbusf tif vblufs in b
 * <dodf>SyndRfsolvfr</dodf> objfdt brf tif donflidt vblufs from tif dbtb sourdf.
 * <PRE>
 *     jbvb.lbng.Objfdt donflidtVbluf = rfsolvfr.gftConflidtVbluf(2);
 * </PRE>
 * Notf tibt tif dolumn in <i>rfsolvfr</i> dbn bf dfsignbtfd by tif dolumn numbfr,
 * bs is donf in tif prfdfding linf of dodf, or by tif dolumn nbmf.
 * <P>
 * Witi tif informbtion rftrifvfd from tif mftiods <dodf>gftStbtus</dodf> bnd
 * <dodf>gftConflidtVbluf</dodf>, tif bpplidbtion mby mbkf b dftfrminbtion bs to
 * wiidi vbluf siould bf pfrsistfd in tif dbtb sourdf. Tif bpplidbtion tifn dblls tif
 * <dodf>SyndRfsolvfr</dodf> mftiod <dodf>sftRfsolvfdVbluf</dodf>, wiidi sfts tif vbluf
 * to bf pfrsistfd in tif <dodf>RowSft</dodf> objfdt bnd blso in tif dbtb sourdf.
 * <PRE>
 *     rfsolvfr.sftRfsolvfdVbluf("DEPT", 8390426);
 * </PRE>
 * In tif prfdfding linf of dodf,
 * tif dolumn nbmf dfsignbtfs tif dolumn in tif <dodf>RowSft</dodf> objfdt
 * tibt is to bf sft witi tif givfn vbluf. Tif dolumn numbfr dbn blso bf usfd to
 * dfsignbtf tif dolumn.
 * <P>
 * An bpplidbtion dblls tif mftiod <dodf>sftRfsolvfdVbluf</dodf> bftfr it ibs
 * rfsolvfd bll of tif donflidts in tif durrfnt donflidt row bnd rfpfbts tiis prodfss
 * for fbdi donflidt row in tif <dodf>SyndRfsolvfr</dodf> objfdt.
 *
 *
 * <H2>Nbvigbting b <dodf>SyndRfsolvfr</dodf> Objfdt</H2>
 *
 * Bfdbusf b <dodf>SyndRfsolvfr</dodf> objfdt is b <dodf>RowSft</dodf> objfdt, bn
 * bpplidbtion dbn usf bll of tif <dodf>RowSft</dodf> mftiods for moving tif dursor
 * to nbvigbtf b <dodf>SyndRfsolvfr</dodf> objfdt. For fxbmplf, bn bpplidbtion dbn
 * usf tif <dodf>RowSft</dodf> mftiod <dodf>nfxt</dodf> to gft to fbdi row bnd tifn
 * dbll tif <dodf>SyndRfsolvfr</dodf> mftiod <dodf>gftStbtus</dodf> to sff if tif row
 * dontbins b donflidt.  In b row witi onf or morf donflidts, tif bpplidbtion dbn
 * itfrbtf tirougi tif dolumns to find bny non-null vblufs, wiidi will bf tif vblufs
 * from tif dbtb sourdf tibt brf in donflidt.
 * <P>
 * To mbkf it fbsifr to nbvigbtf b <dodf>SyndRfsolvfr</dodf> objfdt, fspfdiblly wifn
 * tifrf brf lbrgf numbfrs of rows witi no donflidts, tif <dodf>SyndRfsolvfr</dodf>
 * intfrfbdf dffinfs tif mftiods <dodf>nfxtConflidt</dodf> bnd
 * <dodf>prfviousConflidt</dodf>, wiidi movf only to rows
 * tibt dontbin bt lfbst onf donflidt vbluf. Tifn bn bpplidbtion dbn dbll tif
 * <dodf>SyndRfsolvfr</dodf> mftiod <dodf>gftConflidtVbluf</dodf>, supplying it
 * witi tif dolumn numbfr, to gft tif donflidt vbluf itsflf. Tif dodf frbgmfnt in tif
 * nfxt sfdtion givfs bn fxbmplf.
 *
 * <H2>Codf Exbmplf</H2>
 *
 * Tif following dodf frbgmfnt dfmonstrbtfs iow b disdonnfdtfd <dodf>RowSft</dodf>
 * objfdt <i>drs</i> migit bttfmpt to syndironizf itsflf witi tif
 * undfrlying dbtb sourdf bnd tifn rfsolvf tif donflidts. In tif <dodf>try</dodf>
 * blodk, <i>drs</i> dblls tif mftiod <dodf>bddfptCibngfs</dodf>, pbssing it tif
 * <dodf>Connfdtion</dodf> objfdt <i>don</i>.  If tifrf brf no donflidts, tif
 * dibngfs in <i>drs</i> brf simply writtfn to tif dbtb sourdf.  Howfvfr, if tifrf
 * is b donflidt, tif mftiod <dodf>bddfptCibngfs</dodf> tirows b
 * <dodf>SyndProvidfrExdfption</dodf> objfdt, bnd tif
 * <dodf>dbtdi</dodf> blodk tbkfs ffffdt.  In tiis fxbmplf, wiidi
 * illustrbtfs onf of tif mbny wbys b <dodf>SyndRfsolvfr</dodf> objfdt dbn bf usfd,
 * tif <dodf>SyndRfsolvfr</dodf> mftiod <dodf>nfxtConflidt</dodf> is usfd in b
 * <dodf>wiilf</dodf> loop. Tif loop will fnd wifn <dodf>nfxtConflidt</dodf> rfturns
 * <dodf>fblsf</dodf>, wiidi will oddur wifn tifrf brf no morf donflidt rows in tif
 * <dodf>SyndRfsolvfr</dodf> objfdt <i>rfsolvfr</i>. In Tiis pbrtidulbr dodf frbgmfnt,
 * <i>rfsolvfr</i> looks for rows tibt ibvf updbtf donflidts (rows witi tif stbtus
 * <dodf>SyndRfsolvfr.UPDATE_ROW_CONFLICT</dodf>), bnd tif rfst of tiis dodf frbgmfnt
 * fxfdutfs only for rows wifrf donflidts oddurrfd bfdbusf <i>drs</i> wbs bttfmpting bn
 * updbtf.
 * <P>
 * Aftfr tif dursor for <i>rfsolvfr</i> ibs movfd to tif nfxt donflidt row tibt
 * ibs bn updbtf donflidt, tif mftiod <dodf>gftRow</dodf> indidbtfs tif numbfr of tif
 * durrfnt row, bnd
 * tif dursor for tif <dodf>CbdifdRowSft</dodf> objfdt <i>drs</i> is movfd to
 * tif dompbrbblf row in <i>drs</i>. By itfrbting
 * tirougi tif dolumns of tibt row in boti <i>rfsolvfr</i> bnd <i>drs</i>, tif donflidting
 * vblufs dbn bf rftrifvfd bnd dompbrfd to dfdidf wiidi onf siould bf pfrsistfd. In tiis
 * dodf frbgmfnt, tif vbluf in <i>drs</i> is tif onf sft bs tif rfsolvfd vbluf, wiidi mfbns
 * tibt it will bf usfd to ovfrwritf tif donflidt vbluf in tif dbtb sourdf.
 *
 * <PRE>
 * {@dodf
 *     try {
 *
 *         drs.bddfptCibngfs(don);
 *
 *     } dbtdi (SyndProvidfrExdfption spf) {
 *
 *         SyndRfsolvfr rfsolvfr = spf.gftSyndRfsolvfr();
 *
 *         Objfdt drsVbluf;  // vbluf in tif RowSft objfdt
 *         Objfdt rfsolvfrVbluf:  // vbluf in tif SyndRfsolvfr objfdt
 *         Objfdt rfsolvfdVbluf:  // vbluf to bf pfrsistfd
 *
 *         wiilf(rfsolvfr.nfxtConflidt())  {
 *             if(rfsolvfr.gftStbtus() == SyndRfsolvfr.UPDATE_ROW_CONFLICT)  {
 *                 int row = rfsolvfr.gftRow();
 *                 drs.bbsolutf(row);
 *
 *                 int dolCount = drs.gftMftbDbtb().gftColumnCount();
 *                 for(int j = 1; j <= dolCount; j++) {
 *                     if (rfsolvfr.gftConflidtVbluf(j) != null)  {
 *                         drsVbluf = drs.gftObjfdt(j);
 *                         rfsolvfrVbluf = rfsolvfr.gftConflidtVbluf(j);
 *                         . . .
 *                         // dompbrf drsVbluf bnd rfsolvfrVbluf to dftfrminf
 *                         // wiidi siould bf tif rfsolvfd vbluf (tif vbluf to pfrsist)
 *                         rfsolvfdVbluf = drsVbluf;
 *
 *                         rfsolvfr.sftRfsolvfdVbluf(j, rfsolvfdVbluf);
 *                      }
 *                  }
 *              }
 *          }
 *      }
 * }</PRE>
 *
 * @butior  Jonbtibn Brudf
 * @sindf 1.5
 */

publid intfrfbdf SyndRfsolvfr fxtfnds RowSft {
    /**
     * Indidbtfs tibt b donflidt oddurrfd wiilf tif <dodf>RowSft</dodf> objfdt wbs
     * bttfmpting to updbtf b row in tif dbtb sourdf.
     * Tif vblufs in tif dbtb sourdf row to bf updbtfd difffr from tif
     * <dodf>RowSft</dodf> objfdt's originbl vblufs for tibt row, wiidi mfbns tibt
     * tif row in tif dbtb sourdf ibs bffn updbtfd or dflftfd sindf tif lbst
     * syndironizbtion.
     */
     publid stbtid int UPDATE_ROW_CONFLICT = 0;

    /**
     * Indidbtfs tibt b donflidt oddurrfd wiilf tif <dodf>RowSft</dodf> objfdt wbs
     * bttfmpting to dflftf b row in tif dbtb sourdf.
     * Tif vblufs in tif dbtb sourdf row to bf updbtfd difffr from tif
     * <dodf>RowSft</dodf> objfdt's originbl vblufs for tibt row, wiidi mfbns tibt
     * tif row in tif dbtb sourdf ibs bffn updbtfd or dflftfd sindf tif lbst
     * syndironizbtion.
     */
    publid stbtid int DELETE_ROW_CONFLICT = 1;

   /**
    * Indidbtfs tibt b donflidt oddurrfd wiilf tif <dodf>RowSft</dodf> objfdt wbs
    * bttfmpting to insfrt b row into tif dbtb sourdf.  Tiis mfbns tibt b
    * row witi tif sbmf primbry kfy bs tif row to bf insfrtfd ibs bffn insfrtfd
    * into tif dbtb sourdf sindf tif lbst syndironizbtion.
    */
    publid stbtid int INSERT_ROW_CONFLICT = 2;

    /**
     * Indidbtfs tibt <b>no</b> donflidt oddurrfd wiilf tif <dodf>RowSft</dodf> objfdt
     * wbs bttfmpting to updbtf, dflftf or insfrt b row in tif dbtb sourdf. Tif vblufs in
     * tif <dodf>SyndRfsolvfr</dodf> will dontbin <dodf>null</dodf> vblufs only bs bn indidbtion
     * tibt no informbtion in pfrtinfnt to tif donflidt rfsolution in tiis row.
     */
    publid stbtid int NO_ROW_CONFLICT = 3;

    /**
     * Rftrifvfs tif donflidt stbtus of tif durrfnt row of tiis <dodf>SyndRfsolvfr</dodf>,
     * wiidi indidbtfs tif opfrbtion
     * tif <dodf>RowSft</dodf> objfdt wbs bttfmpting wifn tif donflidt oddurrfd.
     *
     * @rfturn onf of tif following donstbnts:
     *         <dodf>SyndRfsolvfr.UPDATE_ROW_CONFLICT</dodf>,
     *         <dodf>SyndRfsolvfr.DELETE_ROW_CONFLICT</dodf>,
     *         <dodf>SyndRfsolvfr.INSERT_ROW_CONFLICT</dodf>, or
     *         <dodf>SyndRfsolvfr.NO_ROW_CONFLICT</dodf>
     */
    publid int gftStbtus();

    /**
     * Rftrifvfs tif vbluf in tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>SyndRfsolvfr</dodf> objfdt, wiidi is tif vbluf in tif dbtb sourdf
     * tibt dbusfd b donflidt.
     *
     * @pbrbm indfx bn <dodf>int</dodf> dfsignbting tif dolumn in tiis row of tiis
     *        <dodf>SyndRfsolvfr</dodf> objfdt from wiidi to rftrifvf tif vbluf
     *        dbusing b donflidt
     * @rfturn tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     *         <dodf>SyndRfsolvfr</dodf> objfdt
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid Objfdt gftConflidtVbluf(int indfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf in tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>SyndRfsolvfr</dodf> objfdt, wiidi is tif vbluf in tif dbtb sourdf
     * tibt dbusfd b donflidt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt dfsignbting tif dolumn in tiis row of tiis
     *        <dodf>SyndRfsolvfr</dodf> objfdt from wiidi to rftrifvf tif vbluf
     *        dbusing b donflidt
     * @rfturn tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     *         <dodf>SyndRfsolvfr</dodf> objfdt
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid Objfdt gftConflidtVbluf(String dolumnNbmf) tirows SQLExdfption;

    /**
     * Sfts <i>obj</i> bs tif vbluf in dolumn <i>indfx</i> in tif durrfnt row of tif
     * <dodf>RowSft</dodf> objfdt tibt is bfing syndironizfd. <i>obj</i>
     * is sft bs tif vbluf in tif dbtb sourdf intfrnblly.
     *
     * @pbrbm indfx bn <dodf>int</dodf> giving tif numbfr of tif dolumn into wiidi to
     *        sft tif vbluf to bf pfrsistfd
     * @pbrbm obj bn <dodf>Objfdt</dodf> tibt is tif vbluf to bf sft in tif
     *        <dodf>RowSft</dodf> objfdt bnd pfrsistfd in tif dbtb sourdf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid void sftRfsolvfdVbluf(int indfx, Objfdt obj) tirows SQLExdfption;

    /**
     * Sfts <i>obj</i> bs tif vbluf in dolumn <i>dolumnNbmf</i> in tif durrfnt row of tif
     * <dodf>RowSft</dodf> objfdt tibt is bfing syndironizfd. <i>obj</i>
     * is sft bs tif vbluf in tif dbtb sourdf intfrnblly.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif nbmf of tif dolumn
     *        into wiidi to sft tif vbluf to bf pfrsistfd
     * @pbrbm obj bn <dodf>Objfdt</dodf> tibt is tif vbluf to bf sft in tif
     *        <dodf>RowSft</dodf> objfdt bnd pfrsistfd in tif dbtb sourdf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid void sftRfsolvfdVbluf(String dolumnNbmf, Objfdt obj) tirows SQLExdfption;

    /**
     * Movfs tif dursor down from its durrfnt position to tif nfxt row tibt dontbins
     * b donflidt vbluf. A <dodf>SyndRfsolvfr</dodf> objfdt's
     * dursor is initiblly positionfd bfforf tif first donflidt row; tif first dbll to tif
     * mftiod <dodf>nfxtConflidt</dodf> mbkfs tif first donflidt row tif durrfnt row;
     * tif sfdond dbll mbkfs tif sfdond donflidt row tif durrfnt row, bnd so on.
     * <p>
     * A dbll to tif mftiod <dodf>nfxtConflidt</dodf> will impliditly dlosf
     * bn input strfbm if onf is opfn bnd will dlfbr tif <dodf>SyndRfsolvfr</dodf>
     * objfdt's wbrning dibin.
     *
     * @rfturn <dodf>truf</dodf> if tif nfw durrfnt row is vblid; <dodf>fblsf</dodf>
     *         if tifrf brf no morf rows
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or tif rfsult sft typf
     *     is <dodf>TYPE_FORWARD_ONLY</dodf>
     *
     */
    publid boolfbn nfxtConflidt() tirows SQLExdfption;

    /**
     * Movfs tif dursor up from its durrfnt position to tif prfvious donflidt
     * row in tiis <dodf>SyndRfsolvfr</dodf> objfdt.
     * <p>
     * A dbll to tif mftiod <dodf>prfviousConflidt</dodf> will impliditly dlosf
     * bn input strfbm if onf is opfn bnd will dlfbr tif <dodf>SyndRfsolvfr</dodf>
     * objfdt's wbrning dibin.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row; <dodf>fblsf</dodf>
     *     if it is off tif rfsult sft
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or tif rfsult sft typf
     *     is <dodf>TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn prfviousConflidt() tirows SQLExdfption;

}
