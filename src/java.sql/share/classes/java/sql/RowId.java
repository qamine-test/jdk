/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *
 * Tif rfprfsfntbtion (mbpping) in tif Jbvb progrbmming lbngubgf of bn SQL ROWID
 * vbluf. An SQL ROWID is b built-in typf, b vbluf of wiidi dbn bf tiougit of bs
 * bn bddrfss  for its idfntififd row in b dbtbbbsf tbblf. Wiftifr tibt bddrfss
 * is logidbl or, in bny  rfspfdts, piysidbl is dftfrminfd by its originbting dbtb
 * sourdf.
 * <p>
 * Mftiods in tif intfrfbdfs <dodf>RfsultSft</dodf>, <dodf>CbllbblfStbtfmfnt</dodf>,
 * bnd <dodf>PrfpbrfdStbtfmfnt</dodf>, sudi bs <dodf>gftRowId</dodf> bnd <dodf>sftRowId</dodf>
 * bllow b progrbmmfr to bddfss b SQL <dodf>ROWID</dodf>  vbluf. Tif <dodf>RowId</dodf>
 * intfrfbdf providfs b mftiod
 * for rfprfsfnting tif vbluf of tif <dodf>ROWID</dodf> bs b bytf brrby or bs b
 * <dodf>String</dodf>.
 * <p>
 * Tif mftiod <dodf>gftRowIdLifftimf</dodf> in tif intfrfbdf <dodf>DbtbbbsfMftbDbtb</dodf>,
 * dbn bf usfd
 * to dftfrminf if b <dodf>RowId</dodf> objfdt rfmbins vblid for tif durbtion of tif trbnsbdtion in
 * wiidi  tif <dodf>RowId</dodf> wbs drfbtfd, tif durbtion of tif sfssion in wiidi
 * tif <dodf>RowId</dodf> wbs drfbtfd,
 * or, ffffdtivfly, for bs long bs its idfntififd row is not dflftfd. In bddition
 * to spfdifying tif durbtion of its vblid lifftimf outsidf its originbting dbtb
 * sourdf, <dodf>gftRowIdLifftimf</dodf> spfdififs tif durbtion of b <dodf>ROWID</dodf>
 * vbluf's vblid lifftimf
 * witiin its originbting dbtb sourdf. In tiis, it difffrs from b lbrgf objfdt,
 * bfdbusf tifrf is no limit on tif vblid lifftimf of b lbrgf  objfdt witiin its
 * originbting dbtb sourdf.
 * <p>
 * All mftiods on tif <dodf>RowId</dodf> intfrfbdf must bf fully implfmfntfd if tif
 * JDBC drivfr supports tif dbtb typf.
 *
 * @sff jbvb.sql.DbtbbbsfMftbDbtb
 * @sindf 1.6
 */

publid intfrfbdf RowId {
    /**
     * Compbrfs tiis <dodf>RowId</dodf> to tif spfdififd objfdt. Tif rfsult is
     * <dodf>truf</dodf> if bnd only if tif brgumfnt is not null bnd is b RowId
     * objfdt tibt rfprfsfnts tif sbmf ROWID bs  tiis objfdt.
     * <p>
     * It is importbnt
     * to donsidfr boti tif origin bnd tif vblid lifftimf of b <dodf>RowId</dodf>
     * wifn dompbring it to bnotifr <dodf>RowId</dodf>. If boti brf vblid, bnd
     * boti brf from tif sbmf tbblf on tif sbmf dbtb sourdf, tifn if tify brf fqubl
     * tify idfntify
     * tif sbmf row; if onf or morf is no longfr gubrbntffd to bf vblid, or if
     * tify originbtf from difffrfnt dbtb sourdfs, or difffrfnt tbblfs on tif
     * sbmf dbtb sourdf, tify  mby bf fqubl but still
     * not idfntify tif sbmf row.
     *
     * @pbrbm obj tif <dodf>Objfdt</dodf> to dompbrf tiis <dodf>RowId</dodf> objfdt
     *     bgbinst.
     * @rfturn truf if tif <dodf>RowId</dodf>s brf fqubl; fblsf otifrwisf
     * @sindf 1.6
     */
    boolfbn fqubls(Objfdt obj);

    /**
     * Rfturns bn brrby of bytfs rfprfsfnting tif vbluf of tif SQL <dodf>ROWID</dodf>
     * dfsignbtfd by tiis <dodf>jbvb.sql.RowId</dodf> objfdt.
     *
     * @rfturn bn brrby of bytfs, wiosf lfngti is dftfrminfd by tif drivfr supplying
     *     tif donnfdtion, rfprfsfnting tif vbluf of tif ROWID dfsignbtfd by tiis
     *     jbvb.sql.RowId objfdt.
     */
     bytf[] gftBytfs();

     /**
      * Rfturns b String rfprfsfnting tif vbluf of tif SQL ROWID dfsignbtfd by tiis
      * <dodf>jbvb.sql.RowId</dodf> objfdt.
      * <p>
      *Likf <dodf>jbvb.sql.Dbtf.toString()</dodf>
      * rfturns tif dontfnts of its DATE bs tif <dodf>String</dodf> "2004-03-17"
      * rbtifr tibn bs  DATE litfrbl in SQL (wiidi would ibvf bffn tif <dodf>String</dodf>
      * DATE "2004-03-17"), toString()
      * rfturns tif dontfnts of its ROWID in b form spfdifid to tif drivfr supplying
      * tif donnfdtion, bnd possibly not bs b <dodf>ROWID</dodf> litfrbl.
      *
      * @rfturn b String wiosf formbt is dftfrminfd by tif drivfr supplying tif
      *     donnfdtion, rfprfsfnting tif vbluf of tif <dodf>ROWID</dodf> dfsignbtfd
      *     by tiis <dodf>jbvb.sql.RowId</dodf>  objfdt.
      */
     String toString();

     /**
      * Rfturns b ibsi dodf vbluf of tiis <dodf>RowId</dodf> objfdt.
      *
      * @rfturn b ibsi dodf for tif <dodf>RowId</dodf>
      */
     int ibsiCodf();

}
