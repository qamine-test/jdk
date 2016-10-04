/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * bnd propfrtifs of tif dolumns in b <dodf>RfsultSft</dodf> objfdt.
 * Tif following dodf frbgmfnt drfbtfs tif <dodf>RfsultSft</dodf> objfdt rs,
 * drfbtfs tif <dodf>RfsultSftMftbDbtb</dodf> objfdt rsmd, bnd usfs rsmd
 * to find out iow mbny dolumns rs ibs bnd wiftifr tif first dolumn in rs
 * dbn bf usfd in b <dodf>WHERE</dodf> dlbusf.
 * <PRE>
 *
 *     RfsultSft rs = stmt.fxfdutfQufry("SELECT b, b, d FROM TABLE2");
 *     RfsultSftMftbDbtb rsmd = rs.gftMftbDbtb();
 *     int numbfrOfColumns = rsmd.gftColumnCount();
 *     boolfbn b = rsmd.isSfbrdibblf(1);
 *
 * </PRE>
 */

publid intfrfbdf RfsultSftMftbDbtb fxtfnds Wrbppfr {

    /**
     * Rfturns tif numbfr of dolumns in tiis <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn tif numbfr of dolumns
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftColumnCount() tirows SQLExdfption;

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn is butombtidblly numbfrfd.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isAutoIndrfmfnt(int dolumn) tirows SQLExdfption;

    /**
     * Indidbtfs wiftifr b dolumn's dbsf mbttfrs.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isCbsfSfnsitivf(int dolumn) tirows SQLExdfption;

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn dbn bf usfd in b wifrf dlbusf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isSfbrdibblf(int dolumn) tirows SQLExdfption;

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn is b dbsi vbluf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isCurrfndy(int dolumn) tirows SQLExdfption;

    /**
     * Indidbtfs tif nullbbility of vblufs in tif dfsignbtfd dolumn.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif nullbbility stbtus of tif givfn dolumn; onf of <dodf>dolumnNoNulls</dodf>,
     *          <dodf>dolumnNullbblf</dodf> or <dodf>dolumnNullbblfUnknown</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int isNullbblf(int dolumn) tirows SQLExdfption;

    /**
     * Tif donstbnt indidbting tibt b
     * dolumn dofs not bllow <dodf>NULL</dodf> vblufs.
     */
    int dolumnNoNulls = 0;

    /**
     * Tif donstbnt indidbting tibt b
     * dolumn bllows <dodf>NULL</dodf> vblufs.
     */
    int dolumnNullbblf = 1;

    /**
     * Tif donstbnt indidbting tibt tif
     * nullbbility of b dolumn's vblufs is unknown.
     */
    int dolumnNullbblfUnknown = 2;

    /**
     * Indidbtfs wiftifr vblufs in tif dfsignbtfd dolumn brf signfd numbfrs.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isSignfd(int dolumn) tirows SQLExdfption;

    /**
     * Indidbtfs tif dfsignbtfd dolumn's normbl mbximum widti in dibrbdtfrs.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif normbl mbximum numbfr of dibrbdtfrs bllowfd bs tif widti
     *          of tif dfsignbtfd dolumn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftColumnDisplbySizf(int dolumn) tirows SQLExdfption;

    /**
     * Gfts tif dfsignbtfd dolumn's suggfstfd titlf for usf in printouts bnd
     * displbys. Tif suggfstfd titlf is usublly spfdififd by tif SQL <dodf>AS</dodf>
     * dlbusf.  If b SQL <dodf>AS</dodf> is not spfdififd, tif vbluf rfturnfd from
     * <dodf>gftColumnLbbfl</dodf> will bf tif sbmf bs tif vbluf rfturnfd by tif
     * <dodf>gftColumnNbmf</dodf> mftiod.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif suggfstfd dolumn titlf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftColumnLbbfl(int dolumn) tirows SQLExdfption;

    /**
     * Gft tif dfsignbtfd dolumn's nbmf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn dolumn nbmf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftColumnNbmf(int dolumn) tirows SQLExdfption;

    /**
     * Gft tif dfsignbtfd dolumn's tbblf's sdifmb.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn sdifmb nbmf or "" if not bpplidbblf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftSdifmbNbmf(int dolumn) tirows SQLExdfption;

    /**
     * Gft tif dfsignbtfd dolumn's spfdififd dolumn sizf.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. 0 is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn prfdision
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftPrfdision(int dolumn) tirows SQLExdfption;

    /**
     * Gfts tif dfsignbtfd dolumn's numbfr of digits to rigit of tif dfdimbl point.
     * 0 is rfturnfd for dbtb typfs wifrf tif sdblf is not bpplidbblf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn sdblf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftSdblf(int dolumn) tirows SQLExdfption;

    /**
     * Gfts tif dfsignbtfd dolumn's tbblf nbmf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tbblf nbmf or "" if not bpplidbblf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftTbblfNbmf(int dolumn) tirows SQLExdfption;

    /**
     * Gfts tif dfsignbtfd dolumn's tbblf's dbtblog nbmf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif nbmf of tif dbtblog for tif tbblf in wiidi tif givfn dolumn
     *          bppfbrs or "" if not bpplidbblf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftCbtblogNbmf(int dolumn) tirows SQLExdfption;

    /**
     * Rftrifvfs tif dfsignbtfd dolumn's SQL typf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn SQL typf from jbvb.sql.Typfs
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff Typfs
     */
    int gftColumnTypf(int dolumn) tirows SQLExdfption;

    /**
     * Rftrifvfs tif dfsignbtfd dolumn's dbtbbbsf-spfdifid typf nbmf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn typf nbmf usfd by tif dbtbbbsf. If tif dolumn typf is
     * b usfr-dffinfd typf, tifn b fully-qublififd typf nbmf is rfturnfd.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftColumnTypfNbmf(int dolumn) tirows SQLExdfption;

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn is dffinitfly not writbblf.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isRfbdOnly(int dolumn) tirows SQLExdfption;

    /**
     * Indidbtfs wiftifr it is possiblf for b writf on tif dfsignbtfd dolumn to suddffd.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isWritbblf(int dolumn) tirows SQLExdfption;

    /**
     * Indidbtfs wiftifr b writf on tif dfsignbtfd dolumn will dffinitfly suddffd.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isDffinitflyWritbblf(int dolumn) tirows SQLExdfption;

    //--------------------------JDBC 2.0-----------------------------------

    /**
     * <p>Rfturns tif fully-qublififd nbmf of tif Jbvb dlbss wiosf instbndfs
     * brf mbnufbdturfd if tif mftiod <dodf>RfsultSft.gftObjfdt</dodf>
     * is dbllfd to rftrifvf b vbluf
     * from tif dolumn.  <dodf>RfsultSft.gftObjfdt</dodf> mby rfturn b subdlbss of tif
     * dlbss rfturnfd by tiis mftiod.
     *
     * @pbrbm dolumn tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif fully-qublififd nbmf of tif dlbss in tif Jbvb progrbmming
     *         lbngubgf tibt would bf usfd by tif mftiod
     * <dodf>RfsultSft.gftObjfdt</dodf> to rftrifvf tif vbluf in tif spfdififd
     * dolumn. Tiis is tif dlbss nbmf usfd for dustom mbpping.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    String gftColumnClbssNbmf(int dolumn) tirows SQLExdfption;
}
