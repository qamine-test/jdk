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

pbdkbgf jbvb.sql;

/**
 * An input strfbm tibt dontbins b strfbm of vblufs rfprfsfnting bn
 * instbndf of bn SQL strudturfd typf or bn SQL distindt typf.
 * Tiis intfrfbdf, usfd only for dustom mbpping, is usfd by tif drivfr
 * bfiind tif sdfnfs, bnd b progrbmmfr nfvfr dirfdtly invokfs
 * <dodf>SQLInput</dodf> mftiods. Tif <i>rfbdfr</i> mftiods
 * (<dodf>rfbdLong</dodf>, <dodf>rfbdBytfs</dodf>, bnd so on)
 * providf b wby  for bn implfmfntbtion of tif <dodf>SQLDbtb</dodf>
 *  intfrfbdf to rfbd tif vblufs in bn <dodf>SQLInput</dodf> objfdt.
 *  And bs dfsdribfd in <dodf>SQLDbtb</dodf>, dblls to rfbdfr mftiods must
 * bf mbdf in tif ordfr tibt tifir dorrfsponding bttributfs bppfbr in tif
 * SQL dffinition of tif typf.
 * Tif mftiod <dodf>wbsNull</dodf> is usfd to dftfrminf wiftifr
 * tif lbst vbluf rfbd wbs SQL <dodf>NULL</dodf>.
 * <P>Wifn tif mftiod <dodf>gftObjfdt</dodf> is dbllfd witi bn
 * objfdt of b dlbss implfmfnting tif intfrfbdf <dodf>SQLDbtb</dodf>,
 * tif JDBC drivfr dblls tif mftiod <dodf>SQLDbtb.gftSQLTypf</dodf>
 * to dftfrminf tif SQL typf of tif usfr-dffinfd typf (UDT)
 * bfing dustom mbppfd. Tif drivfr
 * drfbtfs bn instbndf of <dodf>SQLInput</dodf>, populbting it witi tif
 * bttributfs of tif UDT.  Tif drivfr tifn pbssfs tif input
 * strfbm to tif mftiod <dodf>SQLDbtb.rfbdSQL</dodf>, wiidi in turn
 * dblls tif <dodf>SQLInput</dodf> rfbdfr mftiods
 * in its implfmfntbtion for rfbding tif
 * bttributfs from tif input strfbm.
 * @sindf 1.2
 */

publid intfrfbdf SQLInput {


    //================================================================
    // Mftiods for rfbding bttributfs from tif strfbm of SQL dbtb.
    // Tifsf mftiods dorrfspond to tif dolumn-bddfssor mftiods of
    // jbvb.sql.RfsultSft.
    //================================================================

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>String</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    String rfbdString() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>boolfbn</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>fblsf</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn rfbdBoolfbn() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>bytf</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>0</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    bytf rfbdBytf() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>siort</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>0</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    siort rfbdSiort() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs bn <dodf>int</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>0</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    int rfbdInt() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>long</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>0</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    long rfbdLong() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>flobt</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>0</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    flobt rfbdFlobt() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>doublf</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>0</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    doublf rfbdDoublf() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>jbvb.mbti.BigDfdimbl</dodf>
     * objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    jbvb.mbti.BigDfdimbl rfbdBigDfdimbl() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs bn brrby of bytfs
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    bytf[] rfbdBytfs() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    jbvb.sql.Dbtf rfbdDbtf() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>jbvb.sql.Timf</dodf> objfdt.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    jbvb.sql.Timf rfbdTimf() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    jbvb.sql.Timfstbmp rfbdTimfstbmp() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b strfbm of Unidodf dibrbdtfrs.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    jbvb.io.Rfbdfr rfbdCibrbdtfrStrfbm() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b strfbm of ASCII dibrbdtfrs.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    jbvb.io.InputStrfbm rfbdAsdiiStrfbm() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b strfbm of unintfrprftfd
     * bytfs.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    jbvb.io.InputStrfbm rfbdBinbryStrfbm() tirows SQLExdfption;

    //================================================================
    // Mftiods for rfbding itfms of SQL usfr-dffinfd typfs from tif strfbm.
    //================================================================

    /**
     * Rfbds tif dbtum bt tif ifbd of tif strfbm bnd rfturns it bs bn
     * <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf.  Tif
     * bdtubl typf of tif objfdt rfturnfd is dftfrminfd by tif dffbult typf
     * mbpping, bnd bny dustomizbtions prfsfnt in tiis strfbm's typf mbp.
     *
     * <P>A typf mbp is rfgistfrfd witi tif strfbm by tif JDBC drivfr bfforf tif
     * strfbm is pbssfd to tif bpplidbtion.
     *
     * <P>Wifn tif dbtum bt tif ifbd of tif strfbm is bn SQL <dodf>NULL</dodf>,
     * tif mftiod rfturns <dodf>null</dodf>.  If tif dbtum is bn SQL strudturfd or distindt
     * typf, it dftfrminfs tif SQL typf of tif dbtum bt tif ifbd of tif strfbm.
     * If tif strfbm's typf mbp ibs bn fntry for tibt SQL typf, tif drivfr
     * donstrudts bn objfdt of tif bppropribtf dlbss bnd dblls tif mftiod
     * <dodf>SQLDbtb.rfbdSQL</dodf> on tibt objfdt, wiidi rfbds bdditionbl dbtb from tif
     * strfbm, using tif protodol dfsdribfd for tibt mftiod.
     *
     * @rfturn tif dbtum bt tif ifbd of tif strfbm bs bn <dodf>Objfdt</dodf> in tif
     * Jbvb progrbmming lbngubgf;<dodf>null</dodf> if tif dbtum is SQL <dodf>NULL</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Objfdt rfbdObjfdt() tirows SQLExdfption;

    /**
     * Rfbds bn SQL <dodf>REF</dodf> vbluf from tif strfbm bnd rfturns it bs b
     * <dodf>Rff</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting tif SQL <dodf>REF</dodf> vbluf
     * bt tif ifbd of tif strfbm; <dodf>null</dodf> if tif vbluf rfbd is
     * SQL <dodf>NULL</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Rff rfbdRff() tirows SQLExdfption;

    /**
     * Rfbds bn SQL <dodf>BLOB</dodf> vbluf from tif strfbm bnd rfturns it bs b
     * <dodf>Blob</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting dbtb of tif SQL <dodf>BLOB</dodf> vbluf
     * bt tif ifbd of tif strfbm; <dodf>null</dodf> if tif vbluf rfbd is
     * SQL <dodf>NULL</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Blob rfbdBlob() tirows SQLExdfption;

    /**
     * Rfbds bn SQL <dodf>CLOB</dodf> vbluf from tif strfbm bnd rfturns it bs b
     * <dodf>Clob</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting dbtb of tif SQL <dodf>CLOB</dodf> vbluf
     * bt tif ifbd of tif strfbm; <dodf>null</dodf> if tif vbluf rfbd is
     * SQL <dodf>NULL</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Clob rfbdClob() tirows SQLExdfption;

    /**
     * Rfbds bn SQL <dodf>ARRAY</dodf> vbluf from tif strfbm bnd rfturns it bs bn
     * <dodf>Arrby</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting dbtb of tif SQL
     * <dodf>ARRAY</dodf> vbluf bt tif ifbd of tif strfbm; <dodf>null</dodf>
     * if tif vbluf rfbd is SQL <dodf>NULL</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Arrby rfbdArrby() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tif lbst vbluf rfbd wbs SQL <dodf>NULL</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif most rfdfntly rfbd SQL vbluf wbs SQL
     * <dodf>NULL</dodf>; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn wbsNull() tirows SQLExdfption;

    //---------------------------- JDBC 3.0 -------------------------

    /**
     * Rfbds bn SQL <dodf>DATALINK</dodf> vbluf from tif strfbm bnd rfturns it bs b
     * <dodf>jbvb.nft.URL</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b <dodf>jbvb.nft.URL</dodf> objfdt.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     *            or if b URL is mblformfd
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    jbvb.nft.URL rfbdURL() tirows SQLExdfption;

     //---------------------------- JDBC 4.0 -------------------------

    /**
     * Rfbds bn SQL <dodf>NCLOB</dodf> vbluf from tif strfbm bnd rfturns it bs b
     * <dodf>NClob</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b <dodf>NClob</dodf> objfdt rfprfsfnting dbtb of tif SQL <dodf>NCLOB</dodf> vbluf
     * bt tif ifbd of tif strfbm; <dodf>null</dodf> if tif vbluf rfbd is
     * SQL <dodf>NULL</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    NClob rfbdNClob() tirows SQLExdfption;

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs b <dodf>String</dodf>
     * in tif Jbvb progrbmming lbngubgf. It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @rfturn tif bttributf; if tif vbluf is SQL <dodf>NULL</dodf>, rfturns <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    String rfbdNString() tirows SQLExdfption;

    /**
     * Rfbds bn SQL <dodf>XML</dodf> vbluf from tif strfbm bnd rfturns it bs b
     * <dodf>SQLXML</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b <dodf>SQLXML</dodf> objfdt rfprfsfnting dbtb of tif SQL <dodf>XML</dodf> vbluf
     * bt tif ifbd of tif strfbm; <dodf>null</dodf> if tif vbluf rfbd is
     * SQL <dodf>NULL</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    SQLXML rfbdSQLXML() tirows SQLExdfption;

    /**
     * Rfbds bn SQL <dodf>ROWID</dodf> vbluf from tif strfbm bnd rfturns it bs b
     * <dodf>RowId</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b <dodf>RowId</dodf> objfdt rfprfsfnting dbtb of tif SQL <dodf>ROWID</dodf> vbluf
     * bt tif ifbd of tif strfbm; <dodf>null</dodf> if tif vbluf rfbd is
     * SQL <dodf>NULL</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    RowId rfbdRowId() tirows SQLExdfption;

    //--------------------------JDBC 4.2 -----------------------------

    /**
     * Rfbds tif nfxt bttributf in tif strfbm bnd rfturns it bs bn
     * {@dodf Objfdt} in tif Jbvb progrbmming lbngubgf. Tif
     * bdtubl typf of tif objfdt rfturnfd is dftfrminfd by tif spfdififd
     * Jbvb dbtb typf, bnd bny dustomizbtions prfsfnt in tiis
     * strfbm's typf mbp.
     *
     * <P>A typf mbp is rfgistfrfd witi tif strfbm by tif JDBC drivfr bfforf tif
     * strfbm is pbssfd to tif bpplidbtion.
     *
     * <P>Wifn tif bttributf bt tif ifbd of tif strfbm is bn SQL {@dodf NULL}
     * tif mftiod rfturns {@dodf null}. If tif bttributf is bn SQL
     * strudturfd or distindt
     * typf, it dftfrminfs tif SQL typf of tif bttributf bt tif ifbd of tif strfbm.
     * If tif strfbm's typf mbp ibs bn fntry for tibt SQL typf, tif drivfr
     * donstrudts bn objfdt of tif bppropribtf dlbss bnd dblls tif mftiod
     * {@dodf SQLDbtb.rfbdSQL} on tibt objfdt, wiidi rfbds bdditionbl dbtb from tif
     * strfbm, using tif protodol dfsdribfd for tibt mftiod.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm <T> tif typf of tif dlbss modflfd by tiis Clbss objfdt
     * @pbrbm typf Clbss rfprfsfnting tif Jbvb dbtb typf to donvfrt tif bttributf to.
     * @rfturn tif bttributf bt tif ifbd of tif strfbm bs bn {@dodf Objfdt} in tif
     * Jbvb progrbmming lbngubgf;{@dodf null} if tif bttributf is SQL {@dodf NULL}
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.8
     */
    dffbult <T> T rfbdObjfdt(Clbss<T> typf) tirows SQLExdfption {
       tirow nfw SQLFfbturfNotSupportfdExdfption();
    }
}
