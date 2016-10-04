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

pbdkbgf dom.sun.rowsft;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvbx.nbming.*;
import jbvb.io.*;
import jbvb.mbti.*;
import jbvb.util.*;

import jbvbx.sql.rowsft.*;

/**
 * Tif stbndbrd implfmfntbtion of tif <dodf>JdbdRowSft</dodf> intfrfbdf. Sff tif intfrfbdf
 * dffinition for full bfibvior bnd implfmfntbtion rfquirfmfnts.
 *
 * @butior Jonbtibn Brudf, Amit Hbndb
 */

publid dlbss JdbdRowSftImpl fxtfnds BbsfRowSft implfmfnts JdbdRowSft, Joinbblf {

    /**
     * Tif <dodf>Connfdtion</dodf> objfdt tibt is tiis rowsft's
     * durrfnt donnfdtion to tif dbtbbbsf.  Tiis fifld is sft
     * intfrnblly wifn tif donnfdtion is fstbblisifd.
     */
    privbtf Connfdtion donn;

    /**
     * Tif <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt is tiis rowsft's
     * durrfnt dommbnd.  Tiis fifld is sft intfrnblly wifn tif mftiod
     * <dodf>fxfdutf</dodf> drfbtfs tif <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt.
     */
    privbtf PrfpbrfdStbtfmfnt ps;

    /**
     * Tif <dodf>RfsultSft</dodf> objfdt tibt is tiis rowsft's
     * durrfnt rfsult sft.  Tiis fifld is sft intfrnblly wifn tif mftiod
     * <dodf>fxfdutf</dodf> fxfdutfs tif rowsft's dommbnd bnd tifrfby
     * drfbtfs tif rowsft's <dodf>RfsultSft</dodf> objfdt.
     */
    privbtf RfsultSft rs;

    /**
     * Tif <dodf>RowSftMftbDbtbImpl</dodf> objfdt tibt is donstrudtfd wifn
     * b <dodf>RfsultSft</dodf> objfdt is pbssfd to tif <dodf>JdbdRowSft</dodf>
     * donstrudtor. Tiis iflps in donstrudting bll mftbdbtb bssodibtfd
     * witi tif <dodf>RfsultSft</dodf> objfdt using tif sfttfr mftiods of
     * <dodf>RowSftMftbDbtbImpl</dodf>.
     */
    privbtf RowSftMftbDbtbImpl rowsMD;

    /**
     * Tif <dodf>RfsultSftMftbDbtb</dodf> objfdt from wiidi tiis
     * <dodf>RowSftMftbDbtbImpl</dodf> is formfd bnd wiidi  iflps in gftting
     * tif mftbdbtb informbtion.
     */
    privbtf RfsultSftMftbDbtb rfsMD;


    /**
     * Tif Vfdtor iolding tif Mbtdi Columns
     */
    privbtf Vfdtor<Intfgfr> iMbtdiColumns;

    /**
     * Tif Vfdtor tibt will iold tif Mbtdi Column nbmfs.
     */
    privbtf Vfdtor<String> strMbtdiColumns;


    protfdtfd trbnsifnt JdbdRowSftRfsourdfBundlf rfsBundlf;

    /**
     * Construdts b dffbult <dodf>JdbdRowSft</dodf> objfdt.
     * Tif nfw instbndf of <dodf>JdbdRowSft</dodf> will sfrvf bs b proxy
     * for tif <dodf>RfsultSft</dodf> objfdt it drfbtfs, bnd by so doing,
     * it will mbkf it possiblf to usf tif rfsult sft bs b JbvbBfbns
     * domponfnt.
     * <P>
     * Tif following is truf of b dffbult <dodf>JdbdRowSft</dodf> instbndf:
     * <UL>
     *   <LI>Dofs not siow dflftfd rows
     *   <LI>Hbs no timf limit for iow long b drivfr mby tbkf to
     *       fxfdutf tif rowsft's dommbnd
     *   <LI>Hbs no limit for tif numbfr of rows it mby dontbin
     *   <LI>Hbs no limit for tif numbfr of bytfs b dolumn mby dontbin
     *   <LI>Hbs b sdrollbblf dursor bnd dofs not siow dibngfs
     *       mbdf by otifrs
     *   <LI>Will not sff undommittfd dbtb (mbkf "dirty" rfbds)
     *   <LI>Hbs fsdbpf prodfssing turnfd on
     *   <LI>Hbs its donnfdtion's typf mbp sft to <dodf>null</dodf>
     *   <LI>Hbs bn fmpty <dodf>Hbsitbblf</dodf> objfdt for storing bny
     *       pbrbmftfrs tibt brf sft
     * </UL>
     * A nfwly drfbtfd <dodf>JdbdRowSft</dodf> objfdt must ibvf its
     * <dodf>fxfdutf</dodf> mftiod invokfd bfforf otifr publid mftiods
     * brf dbllfd on it; otifrwisf, sudi mftiod dblls will dbusf bn
     * fxdfption to bf tirown.
     *
     * @tirows SQLExdfption [1] if bny of its publid mftiods brf dbllfd prior
     * to dblling tif <dodf>fxfdutf</dodf> mftiod; [2] if invblid JDBC drivfr
     * propfrtifs brf sft or [3] if no donnfdtion to b dbtb sourdf fxists.
     */
    publid JdbdRowSftImpl() {
        donn = null;
        ps   = null;
        rs   = null;

        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }


        initPbrbms();

        // sft tif dffbults

        try {
            sftSiowDflftfd(fblsf);
        } dbtdi(SQLExdfption sqlf) {
             Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftsiowdflftfd").toString() +
                                sqlf.gftLodblizfdMfssbgf());
        }

        try {
            sftQufryTimfout(0);
        } dbtdi(SQLExdfption sqlf) {
            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftqufrytimfout").toString() +
                                sqlf.gftLodblizfdMfssbgf());
        }

        try {
            sftMbxRows(0);
        } dbtdi(SQLExdfption sqlf) {
            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftmbxrows").toString() +
                                sqlf.gftLodblizfdMfssbgf());
        }

        try {
            sftMbxFifldSizf(0);
        } dbtdi(SQLExdfption sqlf) {
             Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftmbxfifldsizf").toString() +
                                sqlf.gftLodblizfdMfssbgf());
        }

        try {
            sftEsdbpfProdfssing(truf);
        } dbtdi(SQLExdfption sqlf) {
             Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftfsdbpfprodfssing").toString() +
                                sqlf.gftLodblizfdMfssbgf());
        }

        try {
            sftCondurrfndy(RfsultSft.CONCUR_UPDATABLE);
        } dbtdi (SQLExdfption sqlf) {
            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftdondurrfndy").toString() +
                                sqlf.gftLodblizfdMfssbgf());
        }

        sftTypfMbp(null);

        try {
            sftTypf(RfsultSft.TYPE_SCROLL_INSENSITIVE);
        } dbtdi(SQLExdfption sqlf){
          Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sfttypf").toString() +
                                sqlf.gftLodblizfdMfssbgf());
        }

        sftRfbdOnly(truf);

        try {
            sftTrbnsbdtionIsolbtion(Connfdtion.TRANSACTION_READ_COMMITTED);
        } dbtdi(SQLExdfption sqlf){
            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sfttrbnsbdtionisolbtion").toString() +
                                sqlf.gftLodblizfdMfssbgf());
        }

        //Instbntibting tif vfdtor for MbtdiColumns

        iMbtdiColumns = nfw Vfdtor<Intfgfr>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtdiColumns.bdd(i,Intfgfr.vblufOf(-1));
        }

        strMbtdiColumns = nfw Vfdtor<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtdiColumns.bdd(j,null);
        }
    }

    /**
     * Construdts b dffbult <dodf>JdbdRowSft</dodf> objfdt givfn b
     * vblid <dodf>Connfdtion</dodf> objfdt. Tif nfw
     * instbndf of <dodf>JdbdRowSft</dodf> will sfrvf bs b proxy for
     * tif <dodf>RfsultSft</dodf> objfdt it drfbtfs, bnd by so doing,
     * it will mbkf it possiblf to usf tif rfsult sft bs b JbvbBfbns
     * domponfnt.
     * <P>
     * Tif following is truf of b dffbult <dodf>JdbdRowSft</dodf> instbndf:
     * <UL>
     *   <LI>Dofs not siow dflftfd rows
     *   <LI>Hbs no timf limit for iow long b drivfr mby tbkf to
     *       fxfdutf tif rowsft's dommbnd
     *   <LI>Hbs no limit for tif numbfr of rows it mby dontbin
     *   <LI>Hbs no limit for tif numbfr of bytfs b dolumn mby dontbin
     *   <LI>Hbs b sdrollbblf dursor bnd dofs not siow dibngfs
     *       mbdf by otifrs
     *   <LI>Will not sff undommittfd dbtb (mbkf "dirty" rfbds)
     *   <LI>Hbs fsdbpf prodfssing turnfd on
     *   <LI>Hbs its donnfdtion's typf mbp sft to <dodf>null</dodf>
     *   <LI>Hbs bn fmpty <dodf>Hbsitbblf</dodf> objfdt for storing bny
     *       pbrbmftfrs tibt brf sft
     * </UL>
     * A nfwly drfbtfd <dodf>JdbdRowSft</dodf> objfdt must ibvf its
     * <dodf>fxfdutf</dodf> mftiod invokfd bfforf otifr publid mftiods
     * brf dbllfd on it; otifrwisf, sudi mftiod dblls will dbusf bn
     * fxdfption to bf tirown.
     *
     * @tirows SQLExdfption [1] if bny of its publid mftiods brf dbllfd prior
     * to dblling tif <dodf>fxfdutf</dodf> mftiod, [2] if invblid JDBC drivfr
     * propfrtifs brf sft, or [3] if no donnfdtion to b dbtb sourdf fxists.
     */
    publid JdbdRowSftImpl(Connfdtion don) tirows SQLExdfption {

        donn = don;
        ps = null;
        rs = null;

        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }


        initPbrbms();
        // sft tif dffbults
        sftSiowDflftfd(fblsf);
        sftQufryTimfout(0);
        sftMbxRows(0);
        sftMbxFifldSizf(0);

        sftPbrbms();

        sftRfbdOnly(truf);
        sftTrbnsbdtionIsolbtion(Connfdtion.TRANSACTION_READ_COMMITTED);
        sftEsdbpfProdfssing(truf);
        sftTypfMbp(null);

        //Instbntibting tif vfdtor for MbtdiColumns

        iMbtdiColumns = nfw Vfdtor<Intfgfr>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtdiColumns.bdd(i,Intfgfr.vblufOf(-1));
        }

        strMbtdiColumns = nfw Vfdtor<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtdiColumns.bdd(j,null);
        }
    }

    /**
     * Construdts b dffbult <dodf>JdbdRowSft</dodf> objfdt using tif
     * URL, usfrnbmf, bnd pbssword brgumfnts supplifd. Tif nfw
     * instbndf of <dodf>JdbdRowSft</dodf> will sfrvf bs b proxy for
     * tif <dodf>RfsultSft</dodf> objfdt it drfbtfs, bnd by so doing,
     * it will mbkf it possiblf to usf tif rfsult sft bs b JbvbBfbns
     * domponfnt.
     *
     * <P>
     * Tif following is truf of b dffbult <dodf>JdbdRowSft</dodf> instbndf:
     * <UL>
     *   <LI>Dofs not siow dflftfd rows
     *   <LI>Hbs no timf limit for iow long b drivfr mby tbkf to
     *       fxfdutf tif rowsft's dommbnd
     *   <LI>Hbs no limit for tif numbfr of rows it mby dontbin
     *   <LI>Hbs no limit for tif numbfr of bytfs b dolumn mby dontbin
     *   <LI>Hbs b sdrollbblf dursor bnd dofs not siow dibngfs
     *       mbdf by otifrs
     *   <LI>Will not sff undommittfd dbtb (mbkf "dirty" rfbds)
     *   <LI>Hbs fsdbpf prodfssing turnfd on
     *   <LI>Hbs its donnfdtion's typf mbp sft to <dodf>null</dodf>
     *   <LI>Hbs bn fmpty <dodf>Hbsitbblf</dodf> objfdt for storing bny
     *       pbrbmftfrs tibt brf sft
     * </UL>
     *
     * @pbrbm url - b JDBC URL for tif dbtbbbsf to wiidi tiis <dodf>JdbdRowSft</dodf>
     *        objfdt will bf donnfdtfd. Tif form for b JDBC URL is
     *        <dodf>jdbd:subprotodol:subnbmf</dodf>.
     * @pbrbm usfr - tif dbtbbbsf usfr on wiosf bfiblf tif donnfdtion
     *        is bfing mbdf
     * @pbrbm pbssword - tif usfr's pbssword
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid JdbdRowSftImpl(String url, String usfr, String pbssword) tirows SQLExdfption {
        donn = null;
        ps = null;
        rs = null;

        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }


        initPbrbms();

        // Pbss tif brgumfnts to BbsfRowSft
        // sfttfr mftiods now.

        sftUsfrnbmf(usfr);
        sftPbssword(pbssword);
        sftUrl(url);

        // sft tif dffbults
        sftSiowDflftfd(fblsf);
        sftQufryTimfout(0);
        sftMbxRows(0);
        sftMbxFifldSizf(0);

        sftPbrbms();

        sftRfbdOnly(truf);
        sftTrbnsbdtionIsolbtion(Connfdtion.TRANSACTION_READ_COMMITTED);
        sftEsdbpfProdfssing(truf);
        sftTypfMbp(null);

        //Instbntibting tif vfdtor for MbtdiColumns

        iMbtdiColumns = nfw Vfdtor<Intfgfr>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtdiColumns.bdd(i,Intfgfr.vblufOf(-1));
        }

        strMbtdiColumns = nfw Vfdtor<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtdiColumns.bdd(j,null);
        }
    }


    /**
     * Construdts b <dodf>JdbdRowSft</dodf> objfdt using tif givfn vblid
     * <dodf>RfsultSft</dodf> objfdt. Tif nfw
     * instbndf of <dodf>JdbdRowSft</dodf> will sfrvf bs b proxy for
     * tif <dodf>RfsultSft</dodf> objfdt, bnd by so doing,
     * it will mbkf it possiblf to usf tif rfsult sft bs b JbvbBfbns
     * domponfnt.
     *
     * <P>
     * Tif following is truf of b dffbult <dodf>JdbdRowSft</dodf> instbndf:
     * <UL>
     *   <LI>Dofs not siow dflftfd rows
     *   <LI>Hbs no timf limit for iow long b drivfr mby tbkf to
     *       fxfdutf tif rowsft's dommbnd
     *   <LI>Hbs no limit for tif numbfr of rows it mby dontbin
     *   <LI>Hbs no limit for tif numbfr of bytfs b dolumn mby dontbin
     *   <LI>Hbs b sdrollbblf dursor bnd dofs not siow dibngfs
     *       mbdf by otifrs
     *   <LI>Will not sff undommittfd dbtb (mbkf "dirty" rfbds)
     *   <LI>Hbs fsdbpf prodfssing turnfd on
     *   <LI>Hbs its donnfdtion's typf mbp sft to <dodf>null</dodf>
     *   <LI>Hbs bn fmpty <dodf>Hbsitbblf</dodf> objfdt for storing bny
     *       pbrbmftfrs tibt brf sft
     * </UL>
     *
     * @pbrbm rfs b vblid <dodf>RfsultSft</dodf> objfdt
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss oddurs duf to b non
     * vblid RfsultSft ibndlf.
     */
    publid JdbdRowSftImpl(RfsultSft rfs) tirows SQLExdfption {

        // A RfsultSft ibndlf fndbpsulbtfs b donnfdtion ibndlf.
        // But tifrf is no wby wf dbn rftrifvf b Connfdtion ibndlf
        // from b RfsultSft objfdt.
        // So to bvoid bny bnomblifs wf kffp tif donn = null
        // Tif pbssfd rs ibndlf will bf b wrbppfr bround for
        // "tiis" objfdt's bll opfrbtions.
        donn = null;

        ps = null;

        rs = rfs;

        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }


        initPbrbms();

        // gft tif vblufs from tif rfsultsft ibndlf.
        sftSiowDflftfd(fblsf);
        sftQufryTimfout(0);
        sftMbxRows(0);
        sftMbxFifldSizf(0);

        sftPbrbms();

        sftRfbdOnly(truf);
        sftTrbnsbdtionIsolbtion(Connfdtion.TRANSACTION_READ_COMMITTED);
        sftEsdbpfProdfssing(truf);
        sftTypfMbp(null);

        // Gft b ibndlf to RfsultSftMftbDbtb
        // Construdt RowSftMftbDbtb out of it.

        rfsMD = rs.gftMftbDbtb();

        rowsMD = nfw RowSftMftbDbtbImpl();

        initMftbDbtb(rowsMD, rfsMD);

        //Instbntibting tif vfdtor for MbtdiColumns

        iMbtdiColumns = nfw Vfdtor<Intfgfr>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtdiColumns.bdd(i,Intfgfr.vblufOf(-1));
        }

        strMbtdiColumns = nfw Vfdtor<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtdiColumns.bdd(j,null);
        }
    }

    /**
     * Initiblizfs tif givfn <dodf>RowSftMftbDbtb</dodf> objfdt witi tif vblufs
     * in tif givfn <dodf>RfsultSftMftbDbtb</dodf> objfdt.
     *
     * @pbrbm md tif <dodf>RowSftMftbDbtb</dodf> objfdt for tiis
     *           <dodf>JdbdRowSftImpl</dodf> objfdt, wiidi will bf sft witi
     *           vblufs from rsmd
     * @pbrbm rsmd tif <dodf>RfsultSftMftbDbtb</dodf> objfdt from wiidi nfw
     *             vblufs for md will bf rfbd
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd void initMftbDbtb(RowSftMftbDbtb md, RfsultSftMftbDbtb rsmd) tirows SQLExdfption {
        int numCols = rsmd.gftColumnCount();

        md.sftColumnCount(numCols);
        for (int dol=1; dol <= numCols; dol++) {
            md.sftAutoIndrfmfnt(dol, rsmd.isAutoIndrfmfnt(dol));
            md.sftCbsfSfnsitivf(dol, rsmd.isCbsfSfnsitivf(dol));
            md.sftCurrfndy(dol, rsmd.isCurrfndy(dol));
            md.sftNullbblf(dol, rsmd.isNullbblf(dol));
            md.sftSignfd(dol, rsmd.isSignfd(dol));
            md.sftSfbrdibblf(dol, rsmd.isSfbrdibblf(dol));
            md.sftColumnDisplbySizf(dol, rsmd.gftColumnDisplbySizf(dol));
            md.sftColumnLbbfl(dol, rsmd.gftColumnLbbfl(dol));
            md.sftColumnNbmf(dol, rsmd.gftColumnNbmf(dol));
            md.sftSdifmbNbmf(dol, rsmd.gftSdifmbNbmf(dol));
            md.sftPrfdision(dol, rsmd.gftPrfdision(dol));
            md.sftSdblf(dol, rsmd.gftSdblf(dol));
            md.sftTbblfNbmf(dol, rsmd.gftTbblfNbmf(dol));
            md.sftCbtblogNbmf(dol, rsmd.gftCbtblogNbmf(dol));
            md.sftColumnTypf(dol, rsmd.gftColumnTypf(dol));
            md.sftColumnTypfNbmf(dol, rsmd.gftColumnTypfNbmf(dol));
        }
    }


    protfdtfd void difdkStbtf() tirows SQLExdfption {

        // If bll tif tirff i.f.  donn, ps & rs brf
        // simultbnfously null implifs wf brf not donnfdtfd
        // to tif db, implifs undfsirbblf stbtf so tirow fxdfption

        if (donn == null && ps == null && rs == null ) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.invblstbtf").toString());
        }
    }

    //---------------------------------------------------------------------
    // Rfbding bnd writing dbtb
    //---------------------------------------------------------------------

    /**
     * Crfbtfs tif intfrnbl <dodf>RfsultSft</dodf> objfdt for wiidi tiis
     * <dodf>JdbdRowSft</dodf> objfdt is b wrbppfr, ffffdtivfly
     * mbking tif rfsult sft b JbvbBfbns domponfnt.
     * <P>
     * Cfrtbin propfrtifs must ibvf bffn sft bfforf tiis mftiod is dbllfd
     * so tibt it dbn fstbblisi b donnfdtion to b dbtbbbsf bnd fxfdutf tif
     * qufry tibt will drfbtf tif rfsult sft.  If b <dodf>DbtbSourdf</dodf>
     * objfdt will bf usfd to drfbtf tif donnfdtion, propfrtifs for tif
     * dbtb sourdf nbmf, usfr nbmf, bnd pbssword must bf sft.  If tif
     * <dodf>DrivfrMbnbgfr</dodf> will bf usfd, tif propfrtifs for tif
     * URL, usfr nbmf, bnd pbssword must bf sft.  In fitifr dbsf, tif
     * propfrty for tif dommbnd must bf sft.  If tif dommbnd ibs plbdfioldfr
     * pbrbmftfrs, tiosf must blso bf sft. Tiis mftiod tirows
     * bn fxdfption if tif rfquirfd propfrtifs brf not sft.
     * <P>
     * Otifr propfrtifs ibvf dffbult vblufs tibt mby optionblly bf sft
     * to nfw vblufs. Tif <dodf>fxfdutf</dodf> mftiod will usf tif vbluf
     * for tif dommbnd propfrty to drfbtf b <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt bnd sft its propfrtifs (fsdbpf prodfssing, mbximum fifld
     * sizf, mbximum numbfr of rows, bnd qufry timfout limit) to bf tiosf
     * of tiis rowsft.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     * (2) bny rfquirfd JDBC propfrtifs brf not sft, or (3) if bn
     * invblid donnfdtion fxists.
     */
    publid void fxfdutf() tirows SQLExdfption {
        /*
         * To fxfdutf bbsfd on tif propfrtifs:
         * i) dftfrminf iow to gft b donnfdtion
         * ii) prfpbrf tif stbtfmfnt
         * iii) sft tif propfrtifs of tif stbtfmfnt
         * iv) pbrsf tif pbrbms. bnd sft tifm
         * v) fxfdutf tif stbtfmfnt
         *
         * During bll of tiis try to tolfrbtf bs mbny frrors
         * bs possiblf, mbny drivfrs will not support bll of
         * tif propfrtifs bnd will/siould tirow SQLExdfption
         * bt us...
         *
         */

        prfpbrf();

        // sft tif propfrtifs of our siiny nfw stbtfmfnt
        sftPropfrtifs(ps);


        // sft tif pbrbmftfrs
        dfdodfPbrbms(gftPbrbms(), ps);


        // fxfdutf tif stbtfmfnt
        rs = ps.fxfdutfQufry();


        // notify listfnfrs
        notifyRowSftCibngfd();


    }

    protfdtfd void sftPropfrtifs(PrfpbrfdStbtfmfnt ps) tirows SQLExdfption {

        try {
            ps.sftEsdbpfProdfssing(gftEsdbpfProdfssing());
        } dbtdi (SQLExdfption fx) {
            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftfsdbpfprodfssing").toString() +
                                fx.gftLodblizfdMfssbgf());
        }

        try {
            ps.sftMbxFifldSizf(gftMbxFifldSizf());
        } dbtdi (SQLExdfption fx) {
            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftmbxfifldsizf").toString() +
                                fx.gftLodblizfdMfssbgf());
        }

        try {
            ps.sftMbxRows(gftMbxRows());
        } dbtdi (SQLExdfption fx) {
           Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftmbxrows").toString() +
                                fx.gftLodblizfdMfssbgf());
        }

        try {
            ps.sftQufryTimfout(gftQufryTimfout());
        } dbtdi (SQLExdfption fx) {
           Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftqufrytimfout").toString() +
                                fx.gftLodblizfdMfssbgf());
        }

    }

    privbtf Connfdtion donnfdt() tirows SQLExdfption {

        // Gft b JDBC donnfdtion.

        // First difdk for Connfdtion ibndlf objfdt bs sudi if
        // "tiis" initiblizfd  using donn.

        if(donn != null) {
            rfturn donn;

        } flsf if (gftDbtbSourdfNbmf() != null) {

            // Connfdt using JNDI.
            try {
                Contfxt dtx = nfw InitiblContfxt();
                DbtbSourdf ds = (DbtbSourdf)dtx.lookup
                    (gftDbtbSourdfNbmf());
                //rfturn ds.gftConnfdtion(gftUsfrnbmf(),gftPbssword());

                if(gftUsfrnbmf() != null && !gftUsfrnbmf().fqubls("")) {
                     rfturn ds.gftConnfdtion(gftUsfrnbmf(),gftPbssword());
                } flsf {
                     rfturn ds.gftConnfdtion();
                }
            }
            dbtdi (jbvbx.nbming.NbmingExdfption fx) {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.donnfdt").toString());
            }

        } flsf if (gftUrl() != null) {
            // Cifdk only for gftUrl() != null bfdbusf
            // usfr, pbsswd dbn bf null
            // Connfdt using tif drivfr mbnbgfr.

            rfturn DrivfrMbnbgfr.gftConnfdtion
                    (gftUrl(), gftUsfrnbmf(), gftPbssword());
        }
        flsf {
            rfturn null;
        }

    }


    protfdtfd PrfpbrfdStbtfmfnt prfpbrf() tirows SQLExdfption {
        // gft b donnfdtion
        donn = donnfdt();

        try {

            Mbp<String, Clbss<?>> bMbp = gftTypfMbp();
            if( bMbp != null) {
                donn.sftTypfMbp(bMbp);
            }
            ps = donn.prfpbrfStbtfmfnt(gftCommbnd(),RfsultSft.TYPE_SCROLL_INSENSITIVE,RfsultSft.CONCUR_UPDATABLE);
        } dbtdi (SQLExdfption fx) {
            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.prfpbrf").toString() +
                                fx.gftLodblizfdMfssbgf());

            if (ps != null)
                ps.dlosf();
            if (donn != null)
                donn.dlosf();

            tirow nfw SQLExdfption(fx.gftMfssbgf());
        }

        rfturn ps;
    }

    @SupprfssWbrnings("dfprfdbtion")
    privbtf void dfdodfPbrbms(Objfdt[] pbrbms, PrfpbrfdStbtfmfnt ps)
    tirows SQLExdfption {

    // Tifrf is b dorrfsponding dfdodfPbrbms in JdbdRowSftImpl
    // wiidi dofs tif sbmf bs tiis mftiod. Tiis is b dfsign flbw.
    // Updbtf tif CbdifdRowsftRfbdfr.dfdodfPbrbms wifn you updbtf
    // tiis mftiod.

    // Adding tif sbmf dommfnts to CbdifdRowsftRfbdfr.dfdodfPbrbms.

        int brrbySizf;
        Objfdt[] pbrbm = null;

        for (int i=0; i < pbrbms.lfngti; i++) {
            if (pbrbms[i] instbndfof Objfdt[]) {
                pbrbm = (Objfdt[])pbrbms[i];

                if (pbrbm.lfngti == 2) {
                    if (pbrbm[0] == null) {
                        ps.sftNull(i + 1, ((Intfgfr)pbrbm[1]).intVbluf());
                        dontinuf;
                    }

                    if (pbrbm[0] instbndfof jbvb.sql.Dbtf ||
                        pbrbm[0] instbndfof jbvb.sql.Timf ||
                        pbrbm[0] instbndfof jbvb.sql.Timfstbmp) {
                        Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.dftfdtfddbtf"));
                        if (pbrbm[1] instbndfof jbvb.util.Cblfndbr) {
                            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.dftfdtfddblfndbr"));
                            ps.sftDbtf(i + 1, (jbvb.sql.Dbtf)pbrbm[0],
                                       (jbvb.util.Cblfndbr)pbrbm[1]);
                            dontinuf;
                        }
                        flsf {
                            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.pbrbmtypf").toString());
                        }
                    }

                    if (pbrbm[0] instbndfof Rfbdfr) {
                        ps.sftCibrbdtfrStrfbm(i + 1, (Rfbdfr)pbrbm[0],
                                              ((Intfgfr)pbrbm[1]).intVbluf());
                        dontinuf;
                    }

                    /*
                     * Wibt's lfft siould bf sftObjfdt(int, Objfdt, sdblf)
                     */
                    if (pbrbm[1] instbndfof Intfgfr) {
                        ps.sftObjfdt(i + 1, pbrbm[0], ((Intfgfr)pbrbm[1]).intVbluf());
                        dontinuf;
                    }

                } flsf if (pbrbm.lfngti == 3) {

                    if (pbrbm[0] == null) {
                        ps.sftNull(i + 1, ((Intfgfr)pbrbm[1]).intVbluf(),
                                   (String)pbrbm[2]);
                        dontinuf;
                    }

                    if (pbrbm[0] instbndfof jbvb.io.InputStrfbm) {
                        switdi (((Intfgfr)pbrbm[2]).intVbluf()) {
                        dbsf JdbdRowSftImpl.UNICODE_STREAM_PARAM:
                            ps.sftUnidodfStrfbm(i + 1,
                                                (jbvb.io.InputStrfbm)pbrbm[0],
                                                ((Intfgfr)pbrbm[1]).intVbluf());
                            brfbk;
                        dbsf JdbdRowSftImpl.BINARY_STREAM_PARAM:
                            ps.sftBinbryStrfbm(i + 1,
                                               (jbvb.io.InputStrfbm)pbrbm[0],
                                               ((Intfgfr)pbrbm[1]).intVbluf());
                            brfbk;
                        dbsf JdbdRowSftImpl.ASCII_STREAM_PARAM:
                            ps.sftAsdiiStrfbm(i + 1,
                                              (jbvb.io.InputStrfbm)pbrbm[0],
                                              ((Intfgfr)pbrbm[1]).intVbluf());
                            brfbk;
                        dffbult:
                            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.pbrbmtypf").toString());
                        }
                    }

                    /*
                     * no point bt looking bt tif first flfmfnt now;
                     * wibt's lfft must bf tif sftObjfdt() dbsfs.
                     */
                    if (pbrbm[1] instbndfof Intfgfr && pbrbm[2] instbndfof Intfgfr) {
                        ps.sftObjfdt(i + 1, pbrbm[0], ((Intfgfr)pbrbm[1]).intVbluf(),
                                     ((Intfgfr)pbrbm[2]).intVbluf());
                        dontinuf;
                    }

                    tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.pbrbmtypf").toString());

                } flsf {
                    // dommon dbsf - tiis dbtdifs bll SQL92 typfs
                    ps.sftObjfdt(i + 1, pbrbms[i]);
                    dontinuf;
                }
            }  flsf {
               // Try to gft bll tif pbrbms to bf sft ifrf
               ps.sftObjfdt(i + 1, pbrbms[i]);

            }
        }
    }

    /**
     * Movfs tif dursor for tiis rowsft's <dodf>RfsultSft</dodf>
     * objfdt down onf row from its durrfnt position.
     * A <dodf>RfsultSft</dodf> dursor is initiblly positionfd
     * bfforf tif first row; tif first dbll to tif mftiod
     * <dodf>nfxt</dodf> mbkfs tif first row tif durrfnt row; tif
     * sfdond dbll mbkfs tif sfdond row tif durrfnt row, bnd so on.
     *
     * <P>If bn input strfbm is opfn for tif durrfnt row, b dbll
     * to tif mftiod <dodf>nfxt</dodf> will
     * impliditly dlosf it. A <dodf>RfsultSft</dodf> objfdt's
     * wbrning dibin is dlfbrfd wifn b nfw row is rfbd.
     *
     * @rfturn <dodf>truf</dodf> if tif nfw durrfnt row is vblid;
     *         <dodf>fblsf</dodf> if tifrf brf no morf rows
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn nfxt() tirows SQLExdfption {
        difdkStbtf();

        boolfbn b = rs.nfxt();
        notifyCursorMovfd();
        rfturn b;
    }

    /**
     * Rflfbsfs tiis rowsft's <dodf>RfsultSft</dodf> objfdt's dbtbbbsf bnd
     * JDBC rfsourdfs immfdibtfly instfbd of wbiting for
     * tiis to ibppfn wifn it is butombtidblly dlosfd.
     *
     * <P><B>Notf:</B> A <dodf>RfsultSft</dodf> objfdt
     * is butombtidblly dlosfd by tif
     * <dodf>Stbtfmfnt</dodf> objfdt tibt gfnfrbtfd it wifn
     * tibt <dodf>Stbtfmfnt</dodf> objfdt is dlosfd,
     * rf-fxfdutfd, or is usfd to rftrifvf tif nfxt rfsult from b
     * sfqufndf of multiplf rfsults. A <dodf>RfsultSft</dodf> objfdt
     * is blso butombtidblly dlosfd wifn it is gbrbbgf dollfdtfd.
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid void dlosf() tirows SQLExdfption {
        if (rs != null)
            rs.dlosf();
        if (ps != null)
            ps.dlosf();
        if (donn != null)
            donn.dlosf();
    }

    /**
     * Rfports wiftifr tif lbst dolumn rfbd from tiis rowsft's
     * <dodf>RfsultSft</dodf> objfdt ibd b vbluf of SQL <dodf>NULL</dodf>.
     * Notf tibt you must first dbll onf of tif <dodf>gftXXX</dodf> mftiods
     * on b dolumn to try to rfbd its vbluf bnd tifn dbll
     * tif mftiod <dodf>wbsNull</dodf> to sff if tif vbluf rfbd wbs
     * SQL <dodf>NULL</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif lbst dolumn vbluf rfbd wbs SQL
     *         <dodf>NULL</dodf> bnd <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn wbsNull() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.wbsNull();
    }

    //======================================================================
    // Mftiods for bddfssing rfsults by dolumn indfx
    //======================================================================

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>String</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid String gftString(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftString(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>boolfbn</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>fblsf</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn gftBoolfbn(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftBoolfbn(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>bytf</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid bytf gftBytf(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftBytf(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>siort</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid siort gftSiort(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftSiort(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * bn <dodf>int</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid int gftInt(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftInt(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>long</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid long gftLong(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftLong(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>flobt</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid flobt gftFlobt(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftFlobt(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>doublf</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid doublf gftDoublf(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftDoublf(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.BigDfdimbl</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid BigDfdimbl gftBigDfdimbl(int dolumnIndfx, int sdblf) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftBigDfdimbl(dolumnIndfx, sdblf);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>bytf</dodf> brrby in tif Jbvb progrbmming lbngubgf.
     * Tif bytfs rfprfsfnt tif rbw vblufs rfturnfd by tif drivfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid bytf[] gftBytfs(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftBytfs(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Dbtf</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Dbtf gftDbtf(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftDbtf(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Timf</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Timf gftTimf(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftTimf(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftTimfstbmp(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b strfbm of ASCII dibrbdtfrs. Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.
     * Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into ASCII.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.  Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod
     * <dodf>InputStrfbm.bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of onf-bytf ASCII dibrbdtfrs;
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) dbtbbbsf bddfss frror oddurs
     *            (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.io.InputStrfbm gftAsdiiStrfbm(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftAsdiiStrfbm(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * bs b strfbm of Unidodf dibrbdtfrs.
     * Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf<dodf>LONGVARCHAR</dodf>vblufs.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into Unidodf.
     * Tif bytf formbt of tif Unidodf strfbm must bf Jbvb UTF-8,
     * bs spfdififd in tif Jbvb virtubl mbdiinf spfdifidbtion.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.  Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod
     * <dodf>InputStrfbm.bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm in Jbvb UTF-8 bytf formbt;
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     * @dfprfdbtfd usf <dodf>gftCibrbdtfrStrfbm</dodf> in plbdf of
     *              <dodf>gftUnidodfStrfbm</dodf>
     */
    @Dfprfdbtfd
    publid jbvb.io.InputStrfbm gftUnidodfStrfbm(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftUnidodfStrfbm(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of b dolumn in tif durrfnt row bs b strfbm of
     * tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b binbry strfbm of
     * unintfrprftfd bytfs. Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf> vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.  Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod
     * <dodf>InputStrfbm.bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of unintfrprftfd bytfs;
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftBinbryStrfbm(dolumnIndfx);
    }


    //======================================================================
    // Mftiods for bddfssing rfsults by dolumn nbmf
    //======================================================================

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>String</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid String gftString(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftString(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>boolfbn</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>fblsf</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn gftBoolfbn(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBoolfbn(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>bytf</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid bytf gftBytf(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBytf(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>siort</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid siort gftSiort(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftSiort(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * bn <dodf>int</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid int gftInt(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftInt(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>long</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid long gftLong(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftLong(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>flobt</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid flobt gftFlobt(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftFlobt(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>doublf</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid doublf gftDoublf(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftDoublf(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.mbti.BigDfdimbl</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) bdbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid BigDfdimbl gftBigDfdimbl(String dolumnNbmf, int sdblf) tirows SQLExdfption {
        rfturn gftBigDfdimbl(findColumn(dolumnNbmf), sdblf);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>bytf</dodf> brrby in tif Jbvb progrbmming lbngubgf.
     * Tif bytfs rfprfsfnt tif rbw vblufs rfturnfd by tif drivfr.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid bytf[] gftBytfs(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBytfs(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Dbtf</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Dbtf gftDbtf(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftDbtf(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Timf</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Timf gftTimf(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftTimf(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftTimfstbmp(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b strfbm of
     * ASCII dibrbdtfrs. Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.
     * Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into ASCII.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod <dodf>bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of onf-bytf ASCII dibrbdtfrs.
     * If tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.io.InputStrfbm gftAsdiiStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftAsdiiStrfbm(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b strfbm of
     * Unidodf dibrbdtfrs. Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.
     * Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into Unidodf.
     * Tif bytf formbt of tif Unidodf strfbm must bf Jbvb UTF-8,
     * bs dffinfd in tif Jbvb virtubl mbdiinf spfdifidbtion.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod <dodf>bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of two-bytf Unidodf dibrbdtfrs.
     * If tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid jbvb.io.InputStrfbm gftUnidodfStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftUnidodfStrfbm(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b strfbm of unintfrprftfd
     * <dodf>bytf</dodf>s.
     * Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf>
     * vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod <dodf>bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of unintfrprftfd bytfs;
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBinbryStrfbm(findColumn(dolumnNbmf));
    }


    //=====================================================================
    // Advbndfd ffbturfs:
    //=====================================================================

    /**
     * Rfturns tif first wbrning rfportfd by dblls on tiis rowsft's
     * <dodf>RfsultSft</dodf> objfdt.
     * Subsfqufnt wbrnings on tiis rowsft's <dodf>RfsultSft</dodf> objfdt
     * will bf dibinfd to tif <dodf>SQLWbrning</dodf> objfdt tibt
     * tiis mftiod rfturns.
     *
     * <P>Tif wbrning dibin is butombtidblly dlfbrfd fbdi timf b nfw
     * row is rfbd.
     *
     * <P><B>Notf:</B> Tiis wbrning dibin only dovfrs wbrnings dbusfd
     * by <dodf>RfsultSft</dodf> mftiods.  Any wbrning dbusfd by
     * <dodf>Stbtfmfnt</dodf> mftiods
     * (sudi bs rfbding OUT pbrbmftfrs) will bf dibinfd on tif
     * <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * @rfturn tif first <dodf>SQLWbrning</dodf> objfdt rfportfd or <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid SQLWbrning gftWbrnings() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftWbrnings();
    }

    /**
     * Clfbrs bll wbrnings rfportfd on tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     * Aftfr tiis mftiod is dbllfd, tif mftiod <dodf>gftWbrnings</dodf>
     * rfturns <dodf>null</dodf> until b nfw wbrning is
     * rfportfd for tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid void dlfbrWbrnings() tirows SQLExdfption {
        difdkStbtf();

        rs.dlfbrWbrnings();
    }

    /**
     * Gfts tif nbmf of tif SQL dursor usfd by tiis rowsft's <dodf>RfsultSft</dodf>
     * objfdt.
     *
     * <P>In SQL, b rfsult tbblf is rftrifvfd tirougi b dursor tibt is
     * nbmfd. Tif durrfnt row of b rfsult sft dbn bf updbtfd or dflftfd
     * using b positionfd updbtf/dflftf stbtfmfnt tibt rfffrfndfs tif
     * dursor nbmf. To insurf tibt tif dursor ibs tif propfr isolbtion
     * lfvfl to support updbtf, tif dursor's <dodf>sflfdt</dodf> stbtfmfnt siould bf
     * of tif form 'sflfdt for updbtf'. If tif 'for updbtf' dlbusf is
     * omittfd, tif positionfd updbtfs mby fbil.
     *
     * <P>Tif JDBC API supports tiis SQL ffbturf by providing tif nbmf of tif
     * SQL dursor usfd by b <dodf>RfsultSft</dodf> objfdt.
     * Tif durrfnt row of b <dodf>RfsultSft</dodf> objfdt
     * is blso tif durrfnt row of tiis SQL dursor.
     *
     * <P><B>Notf:</B> If positionfd updbtf is not supportfd, b
     * <dodf>SQLExdfption</dodf> is tirown.
     *
     * @rfturn tif SQL nbmf for tiis rowsft's <dodf>RfsultSft</dodf> objfdt's dursor
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) xtiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid String gftCursorNbmf() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftCursorNbmf();
    }

    /**
     * Rftrifvfs tif  numbfr, typfs bnd propfrtifs of
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt's dolumns.
     *
     * @rfturn tif dfsdription of tiis rowsft's <dodf>RfsultSft</dodf>
     *     objfdt's dolumns
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *     or (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     *     prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid RfsultSftMftbDbtb gftMftbDbtb() tirows SQLExdfption {

        difdkStbtf();

        // It mby bf tif dbsf tibt JdbdRowSft migit not ibvf bffn
        // initiblizfd witi RfsultSft ibndlf bnd mby bf by PrfpbrfdStbtfmfnt
        // intfrnblly wifn wf sft JdbdRowSft.sftCommbnd().
        // Wf mby rfquirf bll tif bbsid propfrtifs of sftEsdbpfProdfssing
        // sftMbxFifldSizf ftd. wiidi bn bpplidbtion dbn usf bfforf wf dbll
        // fxfdutf.
        try {
             difdkStbtf();
        } dbtdi(SQLExdfption sqlf) {
             prfpbrf();
             // will rfturn RfsultSftMftbDbtb
             rfturn ps.gftMftbDbtb();
        }
        rfturn rs.gftMftbDbtb();
    }

    /**
     * <p>Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * bn <dodf>Objfdt</dodf>.
     *
     * <p>Tiis mftiod will rfturn tif vbluf of tif givfn dolumn bs b
     * Jbvb objfdt.  Tif typf of tif Jbvb objfdt will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC
     * spfdifidbtion.
     *
     * <p>Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     *
     * In tif JDBC 3.0 API, tif bfibvior of mftiod
     * <dodf>gftObjfdt</dodf> is fxtfndfd to mbtfriblizf
     * dbtb of SQL usfr-dffinfd typfs.  Wifn b dolumn dontbins
     * b strudturfd or distindt vbluf, tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to: <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Objfdt gftObjfdt(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftObjfdt(dolumnIndfx);
    }

    /**
     * <p>Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs
     * bn <dodf>Objfdt</dodf>.
     *
     * <p>Tiis mftiod will rfturn tif vbluf of tif givfn dolumn bs b
     * Jbvb objfdt.  Tif typf of tif Jbvb objfdt will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC
     * spfdifidbtion.
     *
     * <p>Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     *
     * In tif JDBC 3.0 API, tif bfibvior of tif mftiod
     * <dodf>gftObjfdt</dodf> is fxtfndfd to mbtfriblizf
     * dbtb of SQL usfr-dffinfd typfs.  Wifn b dolumn dontbins
     * b strudturfd or distindt vbluf, tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to: <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Objfdt gftObjfdt(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftObjfdt(findColumn(dolumnNbmf));
    }

    //----------------------------------------------------------------

    /**
     * Mbps tif givfn <dodf>JdbdRowSftImpl</dodf> dolumn nbmf to its
     * <dodf>JdbdRowSftImpl</dodf> dolumn indfx bnd rfflfdts tiis on
     * tif intfrnbl <dodf>RfsultSft</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @rfturn tif dolumn indfx of tif givfn dolumn nbmf
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     * (2) tiis rowsft dofs not ibvf b durrfntly vblid donnfdtion,
     * prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid int findColumn(String dolumnNbmf) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.findColumn(dolumnNbmf);
    }


    //--------------------------JDBC 2.0-----------------------------------

    //---------------------------------------------------------------------
    // Gfttfrs bnd Sfttfrs
    //---------------------------------------------------------------------

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif dolumn
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf>.
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     *
     */
    publid jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftCibrbdtfrStrfbm(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     *
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif dolumn
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf>.
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @rfturn tif vbluf in tif spfdififd dolumn bs b <dodf>jbvb.io.Rfbdfr</dodf>
     *
     */
    publid jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftCibrbdtfrStrfbm(findColumn(dolumnNbmf));
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> witi full prfdision.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn tif dolumn vbluf (full prfdision);
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf>.
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid BigDfdimbl gftBigDfdimbl(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftBigDfdimbl(dolumnIndfx);
    }

    /**
     * Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> witi full prfdision.
     *
     * @pbrbm dolumnNbmf tif dolumn nbmf
     * @rfturn tif dolumn vbluf (full prfdision);
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf>.
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid BigDfdimbl gftBigDfdimbl(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBigDfdimbl(findColumn(dolumnNbmf));
    }

    //---------------------------------------------------------------------
    // Trbvfrsbl/Positioning
    //---------------------------------------------------------------------

    /**
     * Indidbtfs wiftifr tif dursor is bfforf tif first row in
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bfforf tif first row;
     * <dodf>fblsf</dodf> if tif dursor is bt bny otifr position or tif
     * rfsult sft dontbins no rows
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn isBfforfFirst() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.isBfforfFirst();
    }

    /**
     * Indidbtfs wiftifr tif dursor is bftfr tif lbst row in
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bftfr tif lbst row;
     * <dodf>fblsf</dodf> if tif dursor is bt bny otifr position or tif
     * rfsult sft dontbins no rows
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn isAftfrLbst() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.isAftfrLbst();
    }

    /**
     * Indidbtfs wiftifr tif dursor is on tif first row of
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif first row;
     * <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn isFirst() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.isFirst();
    }

    /**
     * Indidbtfs wiftifr tif dursor is on tif lbst row of
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     * Notf: Cblling tif mftiod <dodf>isLbst</dodf> mby bf fxpfnsivf
     * bfdbusf tif JDBC drivfr
     * migit nffd to fftdi bifbd onf row in ordfr to dftfrminf
     * wiftifr tif durrfnt row is tif lbst row in tif rfsult sft.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif lbst row;
     * <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid boolfbn isLbst() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.isLbst();
    }

    /**
     * Movfs tif dursor to tif front of
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt, just bfforf tif
     * first row. Tiis mftiod ibs no ffffdt if tif rfsult sft dontbins no rows.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>,
     *            or (3) tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid void bfforfFirst() tirows SQLExdfption {
        difdkStbtf();

        rs.bfforfFirst();
        notifyCursorMovfd();
    }

    /**
     * Movfs tif dursor to tif fnd of
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt, just bftfr tif
     * lbst row. Tiis mftiod ibs no ffffdt if tif rfsult sft dontbins no rows.
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>,
     *            or (3) tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid void bftfrLbst() tirows SQLExdfption {
        difdkStbtf();

        rs.bftfrLbst();
        notifyCursorMovfd();
    }

    /**
     * Movfs tif dursor to tif first row in
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     * <dodf>fblsf</dodf> if tifrf brf no rows in tif rfsult sft
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>,
     *            or (3) tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn first() tirows SQLExdfption {
        difdkStbtf();

        boolfbn b = rs.first();
        notifyCursorMovfd();
        rfturn b;

    }

    /**
     * Movfs tif dursor to tif lbst row in
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     * <dodf>fblsf</dodf> if tifrf brf no rows in tif rfsult sft
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>,
     *            or (3) tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn lbst() tirows SQLExdfption {
        difdkStbtf();

        boolfbn b = rs.lbst();
        notifyCursorMovfd();
        rfturn b;
    }

    /**
     * Rftrifvfs tif durrfnt row numbfr.  Tif first row is numbfr 1, tif
     * sfdond is numbfr 2, bnd so on.
     *
     * @rfturn tif durrfnt row numbfr; <dodf>0</dodf> if tifrf is no durrfnt row
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid int gftRow() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftRow();
    }

    /**
     * Movfs tif dursor to tif givfn row numbfr in
     * tiis rowsft's intfrnbl <dodf>RfsultSft</dodf> objfdt.
     *
     * <p>If tif row numbfr is positivf, tif dursor movfs to
     * tif givfn row numbfr witi rfspfdt to tif
     * bfginning of tif rfsult sft.  Tif first row is row 1, tif sfdond
     * is row 2, bnd so on.
     *
     * <p>If tif givfn row numbfr is nfgbtivf, tif dursor movfs to
     * bn bbsolutf row position witi rfspfdt to
     * tif fnd of tif rfsult sft.  For fxbmplf, dblling tif mftiod
     * <dodf>bbsolutf(-1)</dodf> positions tif
     * dursor on tif lbst row, dblling tif mftiod <dodf>bbsolutf(-2)</dodf>
     * movfs tif dursor to tif nfxt-to-lbst row, bnd so on.
     *
     * <p>An bttfmpt to position tif dursor bfyond tif first/lbst row in
     * tif rfsult sft lfbvfs tif dursor bfforf tif first row or bftfr
     * tif lbst row.
     *
     * <p><B>Notf:</B> Cblling <dodf>bbsolutf(1)</dodf> is tif sbmf
     * bs dblling <dodf>first()</dodf>. Cblling <dodf>bbsolutf(-1)</dodf>
     * is tif sbmf bs dblling <dodf>lbst()</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif rfsult sft;
     * <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tif row is <dodf>0</dodf>, (3) tif rfsult sft
     *            typf is <dodf>TYPE_FORWARD_ONLY</dodf>, or (4) tiis
     *            rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn bbsolutf(int row) tirows SQLExdfption {
        difdkStbtf();

        boolfbn b = rs.bbsolutf(row);
        notifyCursorMovfd();
        rfturn b;
    }

    /**
     * Movfs tif dursor b rflbtivf numbfr of rows, fitifr positivf or nfgbtivf.
     * Attfmpting to movf bfyond tif first/lbst row in tif
     * rfsult sft positions tif dursor bfforf/bftfr tif
     * tif first/lbst row. Cblling <dodf>rflbtivf(0)</dodf> is vblid, but dofs
     * not dibngf tif dursor position.
     *
     * <p>Notf: Cblling tif mftiod <dodf>rflbtivf(1)</dodf>
     * is difffrfnt from dblling tif mftiod <dodf>nfxt()</dodf>
     * bfdbusf is mbkfs sfnsf to dbll <dodf>nfxt()</dodf> wifn tifrf
     * is no durrfnt row,
     * for fxbmplf, wifn tif dursor is positionfd bfforf tif first row
     * or bftfr tif lbst row of tif rfsult sft.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b row;
     * <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tifrf is no durrfnt row, (3) tif rfsult sft
     *            typf is <dodf>TYPE_FORWARD_ONLY</dodf>, or (4) tiis
     *            rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn rflbtivf(int rows) tirows SQLExdfption {
        difdkStbtf();

        boolfbn b = rs.rflbtivf(rows);
        notifyCursorMovfd();
        rfturn b;
    }

    /**
     * Movfs tif dursor to tif prfvious row in tiis
     * <dodf>RfsultSft</dodf> objfdt.
     *
     * <p><B>Notf:</B> Cblling tif mftiod <dodf>prfvious()</dodf> is not tif sbmf bs
     * dblling tif mftiod <dodf>rflbtivf(-1)</dodf> bfdbusf it
     * mbkfs sfnsf to dbll <dodf>prfvious()</dodf> wifn tifrf is no durrfnt row.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     * <dodf>fblsf</dodf> if it is off tif rfsult sft
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>,
     *            or (3) tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid boolfbn prfvious() tirows SQLExdfption {
        difdkStbtf();

        boolfbn b = rs.prfvious();
        notifyCursorMovfd();
        rfturn b;
    }

    /**
     * Givfs b iint bs to tif dirfdtion in wiidi tif rows in tiis
     * <dodf>RfsultSft</dodf> objfdt will bf prodfssfd.
     * Tif initibl vbluf is dftfrminfd by tif
     * <dodf>Stbtfmfnt</dodf> objfdt
     * tibt produdfd tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     * Tif fftdi dirfdtion mby bf dibngfd bt bny timf.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>
     *            bnd tif fftdi dirfdtion is not <dodf>FETCH_FORWARD</dodf>,
     *            or (3) tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     * @sff jbvb.sql.Stbtfmfnt#sftFftdiDirfdtion
     */
    publid void sftFftdiDirfdtion(int dirfdtion) tirows SQLExdfption {
        difdkStbtf();

        rs.sftFftdiDirfdtion(dirfdtion);
    }

    /**
     * Rfturns tif fftdi dirfdtion for tiis
     * <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn tif durrfnt fftdi dirfdtion for tiis rowsft's
     *         <dodf>RfsultSft</dodf> objfdt
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid int gftFftdiDirfdtion() tirows SQLExdfption {
        try {
             difdkStbtf();
        } dbtdi(SQLExdfption sqlf) {
             supfr.gftFftdiDirfdtion();
        }
        rfturn rs.gftFftdiDirfdtion();
    }

    /**
     * Givfs tif JDBC drivfr b iint bs to tif numbfr of rows tibt siould
     * bf fftdifd from tif dbtbbbsf wifn morf rows brf nffdfd for tiis
     * <dodf>RfsultSft</dodf> objfdt.
     * If tif fftdi sizf spfdififd is zfro, tif JDBC drivfr
     * ignorfs tif vbluf bnd is frff to mbkf its own bfst gufss bs to wibt
     * tif fftdi sizf siould bf.  Tif dffbult vbluf is sft by tif
     * <dodf>Stbtfmfnt</dodf> objfdt
     * tibt drfbtfd tif rfsult sft.  Tif fftdi sizf mby bf dibngfd bt bny timf.
     *
     * @pbrbm rows tif numbfr of rows to fftdi
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs, (2) tif
     *            dondition <dodf>0 <= rows <= tiis.gftMbxRows()</dodf> is not
     *            sbtisfifd, or (3) tiis rowsft dofs not durrfntly ibvf b vblid
     *            donnfdtion, prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void sftFftdiSizf(int rows) tirows SQLExdfption {
        difdkStbtf();

        rs.sftFftdiSizf(rows);
    }

    /**
     *
     * Rfturns tif fftdi sizf for tiis
     * <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn tif durrfnt fftdi sizf for tiis rowsft's <dodf>RfsultSft</dodf> objfdt
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid int gftTypf() tirows SQLExdfption {
        try {
             difdkStbtf();
        } dbtdi(SQLExdfption sqlf) {
            rfturn supfr.gftTypf();
        }

        // If tif RfsultSft ibs not bffn drfbtfd, tifn rfturn tif dffbult typf
        // otifrwisf rfturn tif typf from tif RfsultSft.
        if(rs == null) {
            rfturn supfr.gftTypf();
        } flsf {
           int rstypf = rs.gftTypf();
            rfturn rstypf;
        }


    }

    /**
     * Rfturns tif dondurrfndy modf of tiis rowsft's <dodf>RfsultSft</dodf> objfdt.
     * Tif dondurrfndy usfd is dftfrminfd by tif
     * <dodf>Stbtfmfnt</dodf> objfdt tibt drfbtfd tif rfsult sft.
     *
     * @rfturn tif dondurrfndy typf, fitifr <dodf>CONCUR_READ_ONLY</dodf>
     * or <dodf>CONCUR_UPDATABLE</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid int gftCondurrfndy() tirows SQLExdfption {
        try {
             difdkStbtf();
        } dbtdi(SQLExdfption sqlf) {
             supfr.gftCondurrfndy();
        }
        rfturn rs.gftCondurrfndy();
    }

    //---------------------------------------------------------------------
    // Updbtfs
    //---------------------------------------------------------------------

    /**
     * Indidbtfs wiftifr tif durrfnt row ibs bffn updbtfd.  Tif vbluf rfturnfd
     * dfpfnds on wiftifr or not tif rfsult sft dbn dftfdt updbtfs.
     *
     * @rfturn <dodf>truf</dodf> if tif row ibs bffn visibly updbtfd
     * by tif ownfr or bnotifr, bnd updbtfs brf dftfdtfd
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     * @sff jbvb.sql.DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn rowUpdbtfd() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.rowUpdbtfd();
    }

    /**
     * Indidbtfs wiftifr tif durrfnt row ibs ibd bn insfrtion.
     * Tif vbluf rfturnfd dfpfnds on wiftifr or not tiis
     * <dodf>RfsultSft</dodf> objfdt dbn dftfdt visiblf insfrts.
     *
     * @rfturn <dodf>truf</dodf> if b row ibs ibd bn insfrtion
     * bnd insfrtions brf dftfdtfd; <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     * @sff jbvb.sql.DbtbbbsfMftbDbtb#insfrtsArfDftfdtfd
     *
     */
    publid boolfbn rowInsfrtfd() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.rowInsfrtfd();
    }

    /**
     * Indidbtfs wiftifr b row ibs bffn dflftfd.  A dflftfd row mby lfbvf
     * b visiblf "iolf" in b rfsult sft.  Tiis mftiod dbn bf usfd to
     * dftfdt iolfs in b rfsult sft.  Tif vbluf rfturnfd dfpfnds on wiftifr
     * or not tiis rowsft's <dodf>RfsultSft</dodf> objfdt dbn dftfdt dflftions.
     *
     * @rfturn <dodf>truf</dodf> if b row wbs dflftfd bnd dflftions brf dftfdtfd;
     * <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     * @sff jbvb.sql.DbtbbbsfMftbDbtb#dflftfsArfDftfdtfd
     */
    publid boolfbn rowDflftfd() tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.rowDflftfd();
    }

    /**
     * Givfs b nullbblf dolumn b null vbluf.
     *
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf>
     * or <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid void updbtfNull(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfNull(dolumnIndfx);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>boolfbn</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfBoolfbn(int dolumnIndfx, boolfbn x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfBoolfbn(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>bytf</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfBytf(int dolumnIndfx, bytf x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfBytf(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>siort</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfSiort(int dolumnIndfx, siort x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfSiort(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>int</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid void updbtfInt(int dolumnIndfx, int x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfInt(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>long</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfLong(int dolumnIndfx, long x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfLong(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>flobt</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfFlobt(int dolumnIndfx, flobt x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfFlobt(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>doublf</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfDoublf(int dolumnIndfx, doublf x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfDoublf(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.mbti.BigDfdimbl</dodf>
     * vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfBigDfdimbl(int dolumnIndfx, BigDfdimbl x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfBigDfdimbl(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>String</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfString(int dolumnIndfx, String x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfString(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>bytf</dodf> brrby vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfBytfs(int dolumnIndfx, bytf x[]) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfBytfs(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Dbtf</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfDbtf(int dolumnIndfx, jbvb.sql.Dbtf x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfDbtf(dolumnIndfx, x);
    }


    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Timf</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfTimf(int dolumnIndfx, jbvb.sql.Timf x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfTimf(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Timfstbmp</dodf>
     * vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfTimfstbmp(int dolumnIndfx, jbvb.sql.Timfstbmp x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfTimfstbmp(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            (2) or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfAsdiiStrfbm(int dolumnIndfx, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfAsdiiStrfbm(dolumnIndfx, x, lfngti);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfBinbryStrfbm(int dolumnIndfx, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfBinbryStrfbm(dolumnIndfx, x, lfngti);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfCibrbdtfrStrfbm(int dolumnIndfx, jbvb.io.Rfbdfr x, int lfngti) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfCibrbdtfrStrfbm(dolumnIndfx, x, lfngti);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>Objfdt</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm sdblf for <dodf>jbvb.sql.Typfs.DECIMAl</dodf>
     *  or <dodf>jbvb.sql.Typfs.NUMERIC</dodf> typfs,
     *  tiis is tif numbfr of digits bftfr tif dfdimbl point.  For bll otifr
     *  typfs tiis vbluf will bf ignorfd.
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfObjfdt(int dolumnIndfx, Objfdt x, int sdblf) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfObjfdt(dolumnIndfx, x, sdblf);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>Objfdt</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfObjfdt(int dolumnIndfx, Objfdt x) tirows SQLExdfption {
        difdkStbtf();

        // To difdk tif typf bnd dondurrfndy of tif RfsultSft
        // to vfrify wiftifr updbtfs brf possiblf or not
        difdkTypfCondurrfndy();

        rs.updbtfObjfdt(dolumnIndfx, x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>null</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid void updbtfNull(String dolumnNbmf) tirows SQLExdfption {
        updbtfNull(findColumn(dolumnNbmf));
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>boolfbn</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfBoolfbn(String dolumnNbmf, boolfbn x) tirows SQLExdfption {
        updbtfBoolfbn(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>bytf</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfBytf(String dolumnNbmf, bytf x) tirows SQLExdfption {
        updbtfBytf(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>siort</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfSiort(String dolumnNbmf, siort x) tirows SQLExdfption {
        updbtfSiort(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>int</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfInt(String dolumnNbmf, int x) tirows SQLExdfption {
        updbtfInt(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>long</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfLong(String dolumnNbmf, long x) tirows SQLExdfption {
        updbtfLong(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>flobt </dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfFlobt(String dolumnNbmf, flobt x) tirows SQLExdfption {
        updbtfFlobt(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>doublf</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfDoublf(String dolumnNbmf, doublf x) tirows SQLExdfption {
        updbtfDoublf(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.BigDfdimbl</dodf>
     * vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfBigDfdimbl(String dolumnNbmf, BigDfdimbl x) tirows SQLExdfption {
        updbtfBigDfdimbl(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>String</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfString(String dolumnNbmf, String x) tirows SQLExdfption {
        updbtfString(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>boolfbn</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * JDBC 2.0
     *
     * Updbtfs b dolumn witi b bytf brrby vbluf.
     *
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row, or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf>
     * mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfBytfs(String dolumnNbmf, bytf x[]) tirows SQLExdfption {
        updbtfBytfs(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Dbtf</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfDbtf(String dolumnNbmf, jbvb.sql.Dbtf x) tirows SQLExdfption {
        updbtfDbtf(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Timf</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfTimf(String dolumnNbmf, jbvb.sql.Timf x) tirows SQLExdfption {
        updbtfTimf(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Timfstbmp</dodf>
     * vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfTimfstbmp(String dolumnNbmf, jbvb.sql.Timfstbmp x) tirows SQLExdfption {
        updbtfTimfstbmp(findColumn(dolumnNbmf), x);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfAsdiiStrfbm(String dolumnNbmf, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        updbtfAsdiiStrfbm(findColumn(dolumnNbmf), x, lfngti);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfBinbryStrfbm(String dolumnNbmf, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        updbtfBinbryStrfbm(findColumn(dolumnNbmf), x, lfngti);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs
     * in tif durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf>
     * mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd tif
     * <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif nfw dolumn <dodf>Rfbdfr</dodf> strfbm vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfCibrbdtfrStrfbm(String dolumnNbmf, jbvb.io.Rfbdfr rfbdfr, int lfngti) tirows SQLExdfption {
        updbtfCibrbdtfrStrfbm(findColumn(dolumnNbmf), rfbdfr, lfngti);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>Objfdt</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm sdblf for <dodf>jbvb.sql.Typfs.DECIMAL</dodf>
     *  or <dodf>jbvb.sql.Typfs.NUMERIC</dodf> typfs,
     *  tiis is tif numbfr of digits bftfr tif dfdimbl point.  For bll otifr
     *  typfs tiis vbluf will bf ignorfd.
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfObjfdt(String dolumnNbmf, Objfdt x, int sdblf) tirows SQLExdfption {
        updbtfObjfdt(findColumn(dolumnNbmf), x, sdblf);
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>Objfdt</dodf> vbluf.
     * Tif <dodf>updbtfXXX</dodf> mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif <dodf>updbtfXXX</dodf> mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    publid void updbtfObjfdt(String dolumnNbmf, Objfdt x) tirows SQLExdfption {
        updbtfObjfdt(findColumn(dolumnNbmf), x);
    }

    /**
     * Insfrts tif dontfnts of tif insfrt row into tiis
     * <dodf>RfsultSft</dodf> objfdt bnd into tif dbtbbbsf
     * bnd blso notififs listfnfrs tibt b row ibs dibngfd.
     * Tif dursor must bf on tif insfrt row wifn tiis mftiod is dbllfd.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tiis mftiod is dbllfd wifn tif dursor is not
     *             on tif insfrt row, (3) not bll non-nullbblf dolumns in
     *             tif insfrt row ibvf bffn givfn b vbluf, or (4) tiis
     *             rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *             prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid void insfrtRow() tirows SQLExdfption {
        difdkStbtf();

        rs.insfrtRow();
        notifyRowCibngfd();
    }

    /**
     * Updbtfs tif undfrlying dbtbbbsf witi tif nfw dontfnts of tif
     * durrfnt row of tiis rowsft's <dodf>RfsultSft</dodf> objfdt
     * bnd notififs listfnfrs tibt b row ibs dibngfd.
     * Tiis mftiod dbnnot bf dbllfd wifn tif dursor is on tif insfrt row.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tiis mftiod is dbllfd wifn tif dursor is
     *             on tif insfrt row, (3) tif dondurrfndy of tif rfsult
     *             sft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>, or
     *             (4) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *             prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid void updbtfRow() tirows SQLExdfption {
        difdkStbtf();

        rs.updbtfRow();
        notifyRowCibngfd();
    }

    /**
     * Dflftfs tif durrfnt row from tiis rowsft's <dodf>RfsultSft</dodf> objfdt
     * bnd from tif undfrlying dbtbbbsf bnd blso notififs listfnfrs tibt b row
     * ibs dibngfd.  Tiis mftiod dbnnot bf dbllfd wifn tif dursor is on tif insfrt
     * row.
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or if tiis mftiod is dbllfd wifn tif dursor is on tif insfrt row
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tiis mftiod is dbllfd wifn tif dursor is bfforf tif
     *            first row, bftfr tif lbst row, or on tif insfrt row,
     *            (3) tif dondurrfndy of tiis rowsft's rfsult
     *            sft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>, or
     *            (4) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid void dflftfRow() tirows SQLExdfption {
        difdkStbtf();

        rs.dflftfRow();
        notifyRowCibngfd();
    }

    /**
     * Rffrfsifs tif durrfnt row of tiis rowsft's <dodf>RfsultSft</dodf>
     * objfdt witi its most rfdfnt vbluf in tif dbtbbbsf.  Tiis mftiod
     * dbnnot bf dbllfd wifn tif dursor is on tif insfrt row.
     *
     * <P>Tif <dodf>rffrfsiRow</dodf> mftiod providfs b wby for bn
     * bpplidbtion to fxpliditly tfll tif JDBC drivfr to rffftdi
     * b row(s) from tif dbtbbbsf.  An bpplidbtion mby wbnt to dbll
     * <dodf>rffrfsiRow</dodf> wifn dbdiing or prffftdiing is bfing
     * donf by tif JDBC drivfr to fftdi tif lbtfst vbluf of b row
     * from tif dbtbbbsf.  Tif JDBC drivfr mby bdtublly rffrfsi multiplf
     * rows bt ondf if tif fftdi sizf is grfbtfr tibn onf.
     *
     * <P> All vblufs brf rffftdifd subjfdt to tif trbnsbdtion isolbtion
     * lfvfl bnd dursor sfnsitivity.  If <dodf>rffrfsiRow</dodf> is dbllfd bftfr
     * dblling bn <dodf>updbtfXXX</dodf> mftiod, but bfforf dblling
     * tif mftiod <dodf>updbtfRow</dodf>, tifn tif
     * updbtfs mbdf to tif row brf lost.  Cblling tif mftiod
     * <dodf>rffrfsiRow</dodf> frfqufntly will likfly slow pfrformbndf.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tiis mftiod is dbllfd wifn tif dursor is
     *             on tif insfrt row, or (3) tiis rowsft dofs not
     *             durrfntly ibvf b vblid donnfdtion, prfpbrfd stbtfmfnt,
     *             bnd rfsult sft
     *
     */
    publid void rffrfsiRow() tirows SQLExdfption {
        difdkStbtf();

        rs.rffrfsiRow();
    }

    /**
     * Cbndfls tif updbtfs mbdf to tif durrfnt row in tiis
     * <dodf>RfsultSft</dodf> objfdt bnd notififs listfnfrs tibt b row
     * ibs dibngfd. Tiis mftiod mby bf dbllfd bftfr dblling bn
     * <dodf>updbtfXXX</dodf> mftiod(s) bnd bfforf dblling
     * tif mftiod <dodf>updbtfRow</dodf> to roll bbdk
     * tif updbtfs mbdf to b row.  If no updbtfs ibvf bffn mbdf or
     * <dodf>updbtfRow</dodf> ibs blrfbdy bffn dbllfd, tiis mftiod ibs no
     * ffffdt.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tiis mftiod is dbllfd wifn tif dursor is
     *             on tif insfrt row, or (3) tiis rowsft dofs not
     *             durrfntly ibvf b vblid donnfdtion, prfpbrfd stbtfmfnt,
     *             bnd rfsult sft
     */
    publid void dbndflRowUpdbtfs() tirows SQLExdfption {
        difdkStbtf();

        rs.dbndflRowUpdbtfs();

        notifyRowCibngfd();
    }

    /**
     * Movfs tif dursor to tif insfrt row.  Tif durrfnt dursor position is
     * rfmfmbfrfd wiilf tif dursor is positionfd on tif insfrt row.
     *
     * Tif insfrt row is b spfdibl row bssodibtfd witi bn updbtbblf
     * rfsult sft.  It is fssfntiblly b bufffr wifrf b nfw row mby
     * bf donstrudtfd by dblling tif <dodf>updbtfXXX</dodf> mftiods prior to
     * insfrting tif row into tif rfsult sft.
     *
     * Only tif <dodf>updbtfXXX</dodf>, <dodf>gftXXX</dodf>,
     * bnd <dodf>insfrtRow</dodf> mftiods mby bf
     * dbllfd wifn tif dursor is on tif insfrt row.  All of tif dolumns in
     * b rfsult sft must bf givfn b vbluf fbdi timf tiis mftiod is
     * dbllfd bfforf dblling <dodf>insfrtRow</dodf>.
     * An <dodf>updbtfXXX</dodf> mftiod must bf dbllfd bfforf b
     * <dodf>gftXXX</dodf> mftiod dbn bf dbllfd on b dolumn vbluf.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tiis rowsft's <dodf>RfsultSft</dodf> objfdt is
     *             not updbtbblf, or (3) tiis rowsft dofs not
     *             durrfntly ibvf b vblid donnfdtion, prfpbrfd stbtfmfnt,
     *             bnd rfsult sft
     *
     */
    publid void movfToInsfrtRow() tirows SQLExdfption {
        difdkStbtf();

        rs.movfToInsfrtRow();
    }

    /**
     * Movfs tif dursor to tif rfmfmbfrfd dursor position, usublly tif
     * durrfnt row.  Tiis mftiod ibs no ffffdt if tif dursor is not on
     * tif insfrt row.
     *
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs,
     *            (2) tiis rowsft's <dodf>RfsultSft</dodf> objfdt is
     *             not updbtbblf, or (3) tiis rowsft dofs not
     *             durrfntly ibvf b vblid donnfdtion, prfpbrfd stbtfmfnt,
     *             bnd rfsult sft
     */
    publid void movfToCurrfntRow() tirows SQLExdfption {
        difdkStbtf();

        rs.movfToCurrfntRow();
    }

    /**
     * Rfturns tif <dodf>Stbtfmfnt</dodf> objfdt tibt produdfd tiis
     * <dodf>RfsultSft</dodf> objfdt.
     * If tif rfsult sft wbs gfnfrbtfd somf otifr wby, sudi bs by b
     * <dodf>DbtbbbsfMftbDbtb</dodf> mftiod, tiis mftiod rfturns
     * <dodf>null</dodf>.
     *
     * @rfturn tif <dodf>Stbtfmfnt</dodf> objfdt tibt produdfd
     * tiis rowsft's <dodf>RfsultSft</dodf> objfdt or <dodf>null</dodf>
     * if tif rfsult sft wbs produdfd somf otifr wby
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid jbvb.sql.Stbtfmfnt gftStbtfmfnt() tirows SQLExdfption {

        if(rs != null)
        {
           rfturn rs.gftStbtfmfnt();
        } flsf {
           rfturn null;
        }
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs bn <dodf>Objfdt</dodf>.
     * Tiis mftiod usfs tif givfn <dodf>Mbp</dodf> objfdt
     * for tif dustom mbpping of tif
     * SQL strudturfd or distindt typf tibt is bfing rftrifvfd.
     *
     * @pbrbm i tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt tibt dontbins tif mbpping
     * from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * rfprfsfnting tif SQL vbluf
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Objfdt gftObjfdt(int i, jbvb.util.Mbp<String,Clbss<?>> mbp)
        tirows SQLExdfption
    {
        difdkStbtf();

        rfturn rs.gftObjfdt(i, mbp);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>Rff</dodf> objfdt.
     *
     * @pbrbm i tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting bn SQL <dodf>REF</dodf> vbluf
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Rff gftRff(int i) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftRff(i);
    }


    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>Blob</dodf> objfdt.
     *
     * @pbrbm i tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting tif SQL <dodf>BLOB</dodf>
     *         vbluf in tif spfdififd dolumn
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Blob gftBlob(int i) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftBlob(i);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>Clob</dodf> objfdt.
     *
     * @pbrbm i tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting tif SQL <dodf>CLOB</dodf>
     *         vbluf in tif spfdififd dolumn
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Clob gftClob(int i) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftClob(i);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt.
     *
     * @pbrbm i tif first dolumn is 1, tif sfdond is 2, bnd so on.
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting tif SQL <dodf>ARRAY</dodf>
     *         vbluf in tif spfdififd dolumn
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Arrby gftArrby(int i) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftArrby(i);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs bn <dodf>Objfdt</dodf>.
     * Tiis mftiod usfs tif spfdififd <dodf>Mbp</dodf> objfdt for
     * dustom mbpping if bppropribtf.
     *
     * @pbrbm dolNbmf tif nbmf of tif dolumn from wiidi to rftrifvf tif vbluf
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt tibt dontbins tif mbpping
     * from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting tif SQL
     *         vbluf in tif spfdififd dolumn
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Objfdt gftObjfdt(String dolNbmf, jbvb.util.Mbp<String,Clbss<?>> mbp)
        tirows SQLExdfption
    {
        rfturn gftObjfdt(findColumn(dolNbmf), mbp);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>Rff</dodf> objfdt.
     *
     * @pbrbm dolNbmf tif dolumn nbmf
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting tif SQL <dodf>REF</dodf> vbluf in
     *         tif spfdififd dolumn
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Rff gftRff(String dolNbmf) tirows SQLExdfption {
        rfturn gftRff(findColumn(dolNbmf));
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>Blob</dodf> objfdt.
     *
     * @pbrbm dolNbmf tif nbmf of tif dolumn from wiidi to rftrifvf tif vbluf
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting tif SQL <dodf>BLOB</dodf>
     *         vbluf in tif spfdififd dolumn
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Blob gftBlob(String dolNbmf) tirows SQLExdfption {
        rfturn gftBlob(findColumn(dolNbmf));
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>Clob</dodf> objfdt.
     *
     * @pbrbm dolNbmf tif nbmf of tif dolumn from wiidi to rftrifvf tif vbluf
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting tif SQL <dodf>CLOB</dodf>
     *         vbluf in tif spfdififd dolumn
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Clob gftClob(String dolNbmf) tirows SQLExdfption {
        rfturn gftClob(findColumn(dolNbmf));
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt.
     *
     * @pbrbm dolNbmf tif nbmf of tif dolumn from wiidi to rftrifvf tif vbluf
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting tif SQL <dodf>ARRAY</dodf>
     *         vbluf in tif spfdififd dolumn
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid Arrby gftArrby(String dolNbmf) tirows SQLExdfption {
        rfturn gftArrby(findColumn(dolNbmf));
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf>
     * objfdt. Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf
     * millisfdond vbluf for tif dbtf if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     *        to usf in donstrudting tif dbtf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt;
     *         if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) b dbtbbbsf bddfss frror oddurs
     *            or (2) tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Dbtf gftDbtf(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftDbtf(dolumnIndfx, dbl);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf>
     * objfdt. Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf
     * millisfdond vbluf for tif dbtf if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn from wiidi to rftrifvf tif vbluf
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     *        to usf in donstrudting tif dbtf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt;
     *         if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     *
     */
    publid jbvb.sql.Dbtf gftDbtf(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        rfturn gftDbtf(findColumn(dolumnNbmf), dbl);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf>
     * objfdt. Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf
     * millisfdond vbluf for tif dbtf if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     *        to usf in donstrudting tif timf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Timf</dodf> objfdt;
     *         if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Timf gftTimf(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftTimf(dolumnIndfx, dbl);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf>
     * objfdt. Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf
     * millisfdond vbluf for tif dbtf if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     *        to usf in donstrudting tif timf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Timf</dodf> objfdt;
     *         if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Timf gftTimf(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        rfturn gftTimf(findColumn(dolumnNbmf), dbl);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     * Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf millisfdond
     * vbluf for tif timfstbmp if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, bnd so on
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     *        to usf in donstrudting tif timfstbmp
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt;
     *         if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        difdkStbtf();

        rfturn rs.gftTimfstbmp(dolumnIndfx, dbl);
    }

    /**
     * Rfturns tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis rowsft's <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     * Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf millisfdond
     * vbluf for tif timfstbmp if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     *        to usf in donstrudting tif timfstbmp
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt;
     *         if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *            or tiis rowsft dofs not durrfntly ibvf b vblid donnfdtion,
     *            prfpbrfd stbtfmfnt, bnd rfsult sft
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        rfturn gftTimfstbmp(findColumn(dolumnNbmf), dbl);
    }


    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JdbdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>doublf</dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm rff tif nfw <dodf>Rff</dodf> dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRff(int dolumnIndfx, jbvb.sql.Rff rff)
        tirows SQLExdfption {
        difdkStbtf();
        rs.updbtfRff(dolumnIndfx, rff);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JdbdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>doublf</dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm rff tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRff(String dolumnNbmf, jbvb.sql.Rff rff)
        tirows SQLExdfption {
        updbtfRff(findColumn(dolumnNbmf), rff);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JdbdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>doublf</dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm d tif nfw dolumn <dodf>Clob</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfClob(int dolumnIndfx, Clob d) tirows SQLExdfption {
        difdkStbtf();
        rs.updbtfClob(dolumnIndfx, d);
    }


    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JdbdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>doublf</dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm d tif nfw dolumn <dodf>Clob</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfClob(String dolumnNbmf, Clob d) tirows SQLExdfption {
        updbtfClob(findColumn(dolumnNbmf), d);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JdbdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.sql.Blob</dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm b tif nfw dolumn <dodf>Blob</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBlob(int dolumnIndfx, Blob b) tirows SQLExdfption {
        difdkStbtf();
        rs.updbtfBlob(dolumnIndfx, b);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JdbdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.sql.Blob </dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm b tif nfw dolumn <dodf>Blob</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBlob(String dolumnNbmf, Blob b) tirows SQLExdfption {
        updbtfBlob(findColumn(dolumnNbmf), b);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JdbdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.sql.Arrby</dodf> vblufs.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm b tif nfw dolumn <dodf>Arrby</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfArrby(int dolumnIndfx, Arrby b) tirows SQLExdfption {
        difdkStbtf();
        rs.updbtfArrby(dolumnIndfx, b);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JdbdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.sql.Arrby</dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm b tif nfw dolumn <dodf>Arrby</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfArrby(String dolumnNbmf, Arrby b) tirows SQLExdfption {
        updbtfArrby(findColumn(dolumnNbmf), b);
    }

    /**
     * Providf intfrfbdf dovfrbgf for gftURL(int) in RfsultSft->RowSft
     */
    publid jbvb.nft.URL gftURL(int dolumnIndfx) tirows SQLExdfption {
        difdkStbtf();
        rfturn rs.gftURL(dolumnIndfx);
    }

    /**
     * Providf intfrfbdf dovfrbgf for gftURL(String) in RfsultSft->RowSft
     */
    publid jbvb.nft.URL gftURL(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftURL(findColumn(dolumnNbmf));
    }

    /**
     * Rfturn tif RowSftWbrning objfdt for tif durrfnt row of b
     * <dodf>JdbdRowSftImpl</dodf>
     */
    publid RowSftWbrning gftRowSftWbrnings() tirows SQLExdfption {
       rfturn null;
    }
    /**
     * Unsfts tif dfsignbtfd pbrbmftfr to tif givfn int brrby.
     * Tiis wbs sft using <dodf>sftMbtdiColumn</dodf>
     * bs tif dolumn wiidi will form tif bbsis of tif join.
     * <P>
     * Tif pbrbmftfr vbluf unsft by tiis mftiod siould bf sbmf
     * bs wbs sft.
     *
     * @pbrbm dolumnIdxfs tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds or if tif dolumnIdx is
     *  not tif sbmf bs sft using <dodf>sftMbtdiColumn(int [])</dodf>
     */
    publid void unsftMbtdiColumn(int[] dolumnIdxfs) tirows SQLExdfption {

         int i_vbl;
         for( int j= 0 ;j < dolumnIdxfs.lfngti; j++) {
            i_vbl = (Intfgfr.pbrsfInt(iMbtdiColumns.gft(j).toString()));
            if(dolumnIdxfs[j] != i_vbl) {
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.mbtdidols").toString());
            }
         }

         for( int i = 0;i < dolumnIdxfs.lfngti ;i++) {
            iMbtdiColumns.sft(i,Intfgfr.vblufOf(-1));
         }
    }

   /**
     * Unsfts tif dfsignbtfd pbrbmftfr to tif givfn String brrby.
     * Tiis wbs sft using <dodf>sftMbtdiColumn</dodf>
     * bs tif dolumn wiidi will form tif bbsis of tif join.
     * <P>
     * Tif pbrbmftfr vbluf unsft by tiis mftiod siould bf sbmf
     * bs wbs sft.
     *
     * @pbrbm dolumnIdxfs tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds or if tif dolumnNbmf is
     *  not tif sbmf bs sft using <dodf>sftMbtdiColumn(String [])</dodf>
     */
    publid void unsftMbtdiColumn(String[] dolumnIdxfs) tirows SQLExdfption {

        for(int j = 0 ;j < dolumnIdxfs.lfngti; j++) {
           if( !dolumnIdxfs[j].fqubls(strMbtdiColumns.gft(j)) ){
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.mbtdidols").toString());
           }
        }

        for(int i = 0 ; i < dolumnIdxfs.lfngti; i++) {
           strMbtdiColumns.sft(i,null);
        }
    }

    /**
     * Rftrifvfs tif dolumn nbmf bs <dodf>String</dodf> brrby
     * tibt wbs sft using <dodf>sftMbtdiColumn(String [])</dodf>
     * for tiis rowsft.
     *
     * @rfturn b <dodf>String</dodf> brrby objfdt tibt dontbins tif dolumn nbmfs
     *         for tif rowsft wiidi ibs tiis tif mbtdi dolumns
     *
     * @tirows SQLExdfption if bn frror oddurs or dolumn nbmf is not sft
     */
    publid String[] gftMbtdiColumnNbmfs() tirows SQLExdfption {

        String []str_tfmp = nfw String[strMbtdiColumns.sizf()];

        if( strMbtdiColumns.gft(0) == null) {
           tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftmbtdidols").toString());
        }

        strMbtdiColumns.dopyInto(str_tfmp);
        rfturn str_tfmp;
    }

    /**
     * Rftrifvfs tif dolumn id bs <dodf>int</dodf> brrby tibt wbs sft using
     * <dodf>sftMbtdiColumn(int [])</dodf> for tiis rowsft.
     *
     * @rfturn b <dodf>int</dodf> brrby objfdt tibt dontbins tif dolumn ids
     *         for tif rowsft wiidi ibs tiis bs tif mbtdi dolumns.
     *
     * @tirows SQLExdfption if bn frror oddurs or dolumn indfx is not sft
     */
    publid int[] gftMbtdiColumnIndfxfs() tirows SQLExdfption {

        Intfgfr []int_tfmp = nfw Intfgfr[iMbtdiColumns.sizf()];
        int [] i_tfmp = nfw int[iMbtdiColumns.sizf()];
        int i_vbl;

        i_vbl = iMbtdiColumns.gft(0);

        if( i_vbl == -1 ) {
           tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.sftmbtdidols").toString());
        }


        iMbtdiColumns.dopyInto(int_tfmp);

        for(int i = 0; i < int_tfmp.lfngti; i++) {
           i_tfmp[i] = (int_tfmp[i]).intVbluf();
        }

        rfturn i_tfmp;
    }

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn int brrby.
     * Tiis forms tif bbsis of tif join for tif
     * <dodf>JoinRowSft</dodf> bs tif dolumn wiidi will form tif bbsis of tif
     * join.
     * <P>
     * Tif pbrbmftfr vbluf sft by tiis mftiod is storfd intfrnblly bnd
     * will bf supplifd bs tif bppropribtf pbrbmftfr in tiis rowsft's
     * dommbnd wifn tif mftiod <dodf>gftMbtdiColumnIndfxfs</dodf> is dbllfd.
     *
     * @pbrbm dolumnIdxfs tif indfxfs into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs; tif
     *        first pbrbmftfr is 0, tif sfdond is 1, bnd so on; must bf
     *        <dodf>0</dodf> or grfbtfr
     * @tirows SQLExdfption if bn frror oddurs or tif
     *                         pbrbmftfr indfx is out of bounds
     */
    publid void sftMbtdiColumn(int[] dolumnIdxfs) tirows SQLExdfption {

        for(int j = 0 ; j < dolumnIdxfs.lfngti; j++) {
           if( dolumnIdxfs[j] < 0 ) {
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.mbtdidols1").toString());
           }
        }
        for(int i = 0 ;i < dolumnIdxfs.lfngti; i++) {
           iMbtdiColumns.bdd(i,Intfgfr.vblufOf(dolumnIdxfs[i]));
        }
    }

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn String brrby.
     *  Tiis forms tif bbsis of tif join for tif
     * <dodf>JoinRowSft</dodf> bs tif dolumn wiidi will form tif bbsis of tif
     * join.
     * <P>
     * Tif pbrbmftfr vbluf sft by tiis mftiod is storfd intfrnblly bnd
     * will bf supplifd bs tif bppropribtf pbrbmftfr in tiis rowsft's
     * dommbnd wifn tif mftiod <dodf>gftMbtdiColumn</dodf> is dbllfd.
     *
     * @pbrbm dolumnNbmfs tif nbmf of tif dolumn into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds
     */
    publid void sftMbtdiColumn(String[] dolumnNbmfs) tirows SQLExdfption {

        for(int j = 0; j < dolumnNbmfs.lfngti; j++) {
           if( dolumnNbmfs[j] == null || dolumnNbmfs[j].fqubls("")) {
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.mbtdidols2").toString());
           }
        }
        for( int i = 0; i < dolumnNbmfs.lfngti; i++) {
           strMbtdiColumns.bdd(i,dolumnNbmfs[i]);
        }
    }


        /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>int</dodf>
     * objfdt.  Tiis forms tif bbsis of tif join for tif
     * <dodf>JoinRowSft</dodf> bs tif dolumn wiidi will form tif bbsis of tif
     * join.
     * <P>
     * Tif pbrbmftfr vbluf sft by tiis mftiod is storfd intfrnblly bnd
     * will bf supplifd bs tif bppropribtf pbrbmftfr in tiis rowsft's
     * dommbnd wifn tif mftiod <dodf>gftMbtdiColumn</dodf> is dbllfd.
     *
     * @pbrbm dolumnIdx tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs; tif
     *        first pbrbmftfr is 0, tif sfdond is 1, bnd so on; must bf
     *        <dodf>0</dodf> or grfbtfr
     * @tirows SQLExdfption if bn frror oddurs or tif
     *                         pbrbmftfr indfx is out of bounds
     */
    publid void sftMbtdiColumn(int dolumnIdx) tirows SQLExdfption {
        // vblidbtf, if dol is ok to bf sft
        if(dolumnIdx < 0) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.mbtdidols1").toString());
        } flsf {
            // sft iMbtdiColumn
            iMbtdiColumns.sft(0, Intfgfr.vblufOf(dolumnIdx));
            //strMbtdiColumn = null;
        }
    }

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>String</dodf>
     * objfdt.  Tiis forms tif bbsis of tif join for tif
     * <dodf>JoinRowSft</dodf> bs tif dolumn wiidi will form tif bbsis of tif
     * join.
     * <P>
     * Tif pbrbmftfr vbluf sft by tiis mftiod is storfd intfrnblly bnd
     * will bf supplifd bs tif bppropribtf pbrbmftfr in tiis rowsft's
     * dommbnd wifn tif mftiod <dodf>gftMbtdiColumn</dodf> is dbllfd.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds
     */
    publid void sftMbtdiColumn(String dolumnNbmf) tirows SQLExdfption {
        // vblidbtf, if dol is ok to bf sft
        if(dolumnNbmf == null || (dolumnNbmf= dolumnNbmf.trim()).fqubls("")) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.mbtdidols2").toString());
        } flsf {
            // sft strMbtdiColumn
            strMbtdiColumns.sft(0, dolumnNbmf);
            //iMbtdiColumn = -1;
        }
    }

    /**
     * Unsfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>int</dodf>
     * objfdt.  Tiis wbs sft using <dodf>sftMbtdiColumn</dodf>
     * bs tif dolumn wiidi will form tif bbsis of tif join.
     * <P>
     * Tif pbrbmftfr vbluf unsft by tiis mftiod siould bf sbmf
     * bs wbs sft.
     *
     * @pbrbm dolumnIdx tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds or if tif dolumnIdx is
     *  not tif sbmf bs sft using <dodf>sftMbtdiColumn(int)</dodf>
     */
    publid void unsftMbtdiColumn(int dolumnIdx) tirows SQLExdfption {
        // difdk if wf brf unsftting tif SAME dolumn
        if(! iMbtdiColumns.gft(0).fqubls(Intfgfr.vblufOf(dolumnIdx) )  ) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.unsftmbtdi").toString());
        } flsf if(strMbtdiColumns.gft(0) != null) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.usfdolnbmf").toString());
        } flsf {
                // tibt is, wf brf unsftting it.
               iMbtdiColumns.sft(0, Intfgfr.vblufOf(-1));
        }
    }

    /**
     * Unsfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>String</dodf>
     * objfdt.  Tiis wbs sft using <dodf>sftMbtdiColumn</dodf>
     * bs tif dolumn wiidi will form tif bbsis of tif join.
     * <P>
     * Tif pbrbmftfr vbluf unsft by tiis mftiod siould bf sbmf
     * bs wbs sft.
     *
     * @pbrbm dolumnNbmf tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds or if tif dolumnNbmf is
     *  not tif sbmf bs sft using <dodf>sftMbtdiColumn(String)</dodf>
     *
     */
    publid void unsftMbtdiColumn(String dolumnNbmf) tirows SQLExdfption {
        // difdk if wf brf unsftting tif sbmf dolumn
        dolumnNbmf = dolumnNbmf.trim();

        if(!((strMbtdiColumns.gft(0)).fqubls(dolumnNbmf))) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.unsftmbtdi").toString());
        } flsf if(iMbtdiColumns.gft(0) > 0) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.usfdolid").toString());
        } flsf {
            strMbtdiColumns.sft(0, null);   // tibt is, wf brf unsftting it.
        }
    }

    /**
     * Rftrifvfs tif <dodf>DbtbbbsfMftbDbtb</dodf> bssodibtfd witi
     * tif donnfdtion ibndlf bssodibtfd tiis tiis
     * <dodf>JdbdRowSft</dodf> objfdt.
     *
     * @rfturn tif <dodf>DbtbbbsfMftbdbtb</dodf> bssodibtfd
     *  witi tif rowsft's donnfdtion.
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid DbtbbbsfMftbDbtb gftDbtbbbsfMftbDbtb() tirows SQLExdfption {
        Connfdtion don = donnfdt();
        rfturn don.gftMftbDbtb();
    }

    /**
     * Rftrifvfs tif <dodf>PbrbmftfrMftbDbtb</dodf> bssodibtfd witi
     * tif donnfdtion ibndlf bssodibtfd tiis tiis
     * <dodf>JdbdRowSft</dodf> objfdt.
     *
     * @rfturn tif <dodf>PbrbmftfrMftbdbtb</dodf> bssodibtfd
     *  witi tif rowsft's donnfdtion.
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid PbrbmftfrMftbDbtb gftPbrbmftfrMftbDbtb() tirows SQLExdfption {
        prfpbrf();
        rfturn (ps.gftPbrbmftfrMftbDbtb());
    }

    /**
     * Commits bll updbtfs in tiis <dodf>JdbdRowSft</dodf> objfdt by
     * wrbpping tif intfrnbl <dodf>Connfdtion</dodf> objfdt bnd dblling
     * its <dodf>dommit</dodf> mftiod.
     * Tiis mftiod sfts tiis <dodf>JdbdRowSft</dodf> objfdt's privbtf fifld
     * <dodf>rs</dodf> to <dodf>null</dodf> bftfr sbving its vbluf to bnotifr
     * objfdt, but only if tif <dodf>RfsultSft</dodf>
     * donstbnt <dodf>HOLD_CURSORS_OVER_COMMIT</dodf> ibs not bffn sft.
     * (Tif fifld <dodf>rs</dodf> is tiis <dodf>JdbdRowSft</dodf> objfdt's
     * <dodf>RfsultSft</dodf> objfdt.)
     *
     * @tirows SQLExdfption if butoCommit is sft to truf or if b dbtbbbsf
     * bddfss frror oddurs
     */
    publid void dommit() tirows SQLExdfption {
      donn.dommit();

      // Cifdking tif iolbdbility vbluf bnd mbking tif rfsult sft ibndlf null
      // Addfd bs pfr Rbvf rfquirfmfnts

      if( donn.gftHoldbbility() != HOLD_CURSORS_OVER_COMMIT) {
         rs = null;
      }
    }

    /**
     * Sfts buto-dommit on tif intfrnbl <dodf>Connfdtion</dodf> objfdt witi tiis
     * <dodf>JdbdRowSft</dodf>
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid void sftAutoCommit(boolfbn butoCommit) tirows SQLExdfption {
        // Tif donnfdtion objfdt siould bf tifrf
        // in ordfr to dommit tif donnfdtion ibndlf on or off.

        if(donn != null) {
           donn.sftAutoCommit(butoCommit);
        } flsf {
           // Coming ifrf mfbns tif donnfdtion objfdt is null.
           // So gfnfrbtf b donnfdtion ibndlf intfrnblly, sindf
           // b JdbdRowSft is blwbys donnfdtfd to b db, it is finf
           // to gft b ibndlf to tif donnfdtion.

           // Gft iold of b donnfdtion ibndlf
           // bnd dibngf tif butdommit bs pbssfsd.
           donn = donnfdt();

           // Aftfr sftting tif bflow tif donn.gftAutoCommit()
           // siould rfturn tif sbmf vbluf.
           donn.sftAutoCommit(butoCommit);

        }
    }

    /**
     * Rfturns tif buto-dommit stbtus witi tiis <dodf>JdbdRowSft</dodf>.
     *
     * @rfturn truf if buto dommit is truf; fblsf otifrwisf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid boolfbn gftAutoCommit() tirows SQLExdfption {
        rfturn donn.gftAutoCommit();
    }

    /**
     * Rolls bbdk bll tif updbtfs in tiis <dodf>JdbdRowSft</dodf> objfdt by
     * wrbpping tif intfrnbl <dodf>Connfdtion</dodf> objfdt bnd dblling its
     * <dodf>rollbbdk</dodf> mftiod.
     * Tiis mftiod sfts tiis <dodf>JdbdRowSft</dodf> objfdt's privbtf fifld
     * <dodf>rs</dodf> to <dodf>null</dodf> bftfr sbving its vbluf to bnotifr objfdt.
     * (Tif fifld <dodf>rs</dodf> is tiis <dodf>JdbdRowSft</dodf> objfdt's
     * intfrnbl <dodf>RfsultSft</dodf> objfdt.)
     *
     * @tirows SQLExdfption if butoCommit is sft to truf or b dbtbbbsf
     * bddfss frror oddurs
     */
    publid void rollbbdk() tirows SQLExdfption {
        donn.rollbbdk();

        // Mbkfs tif rfsult stf ibndlf null bftfr rollbbdk
        // Addfd bs pfr Rbvf rfquirfmfnts

        rs = null;
    }


    /**
     * Rollbbdks bll tif updbtfs in tif <dodf>JdbdRowSft</dodf> bbdk to tif
     * lbst <dodf>Sbvfpoint</dodf> trbnsbdtion mbrkfr. Wrbps tif intfrnbl
     * <dodf>Connfdtion</dodf> objfdt bnd dbll it's rollbbdk mftiod
     *
     * @pbrbm s tif <dodf>Sbvfpoint</dodf> trbnsbdtion mbrkfr to roll tif
     * trbnsbdtion to.
     * @tirows SQLExdfption if butoCommit is sft to truf; or ib b dbtbbbsf
     * bddfss frror oddurs
     */
    publid void rollbbdk(Sbvfpoint s) tirows SQLExdfption {
        donn.rollbbdk(s);
    }

    // Sftting tif RfsultSft Typf bnd Condurrfndy
    protfdtfd void sftPbrbms() tirows SQLExdfption {
        if(rs == null) {
           sftTypf(RfsultSft.TYPE_SCROLL_INSENSITIVE);
           sftCondurrfndy(RfsultSft.CONCUR_UPDATABLE);
        }
        flsf {
            sftTypf(rs.gftTypf());
            sftCondurrfndy(rs.gftCondurrfndy());
        }
    }


    // Cifdking RfsultSft Typf bnd Condurrfndy
    privbtf void difdkTypfCondurrfndy() tirows SQLExdfption {
        if(rs.gftTypf() == TYPE_FORWARD_ONLY ||
           rs.gftCondurrfndy() == CONCUR_READ_ONLY) {
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.rfsnotupd").toString());
         }
    }

     // Rfturns b Connfdtion Hbndlf
    //  Addfd bs pfr Rbvf rfquirfmfnts

    /**
     * Gfts tiis <dodf>JdbdRowSft</dodf> objfdt's Connfdtion propfrty
     *
     *
     * @rfturn tif <dodf>Connfdtion</dodf> objfdt bssodibtfd witi tiis rowsft;
     */

    protfdtfd Connfdtion gftConnfdtion() {
       rfturn donn;
    }

    // Sfts tif donnfdtion ibndlf witi tif pbrbmftfr
    // Addfd bs pfr rbvf rfquirfmfnts

    /**
     * Sfts tiis <dodf>JdbdRowSft</dodf> objfdt's donnfdtion propfrty
     * to tif givfn <dodf>Connfdtion</dodf> objfdt.
     *
     * @pbrbm donnfdtion tif <dodf>Connfdtion</dodf> objfdt.
     */

    protfdtfd void sftConnfdtion(Connfdtion donnfdtion) {
       donn = donnfdtion;
    }

    // Rfturns b PrfpbrfdStbtfmfnt Hbndlf
    // Addfd bs pfr Rbvf rfquirfmfnts

    /**
     * Gfts tiis <dodf>JdbdRowSft</dodf> objfdt's PrfpbrfdStbtfmfnt propfrty
     *
     *
     * @rfturn tif <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt bssodibtfd witi tiis rowsft;
     */

    protfdtfd PrfpbrfdStbtfmfnt gftPrfpbrfdStbtfmfnt() {
       rfturn ps;
    }

    //Sfts tif prfpbrfd stbtfmfnt ibndlf to tif pbrbmftfr
    // Addfd bs pfr Rbvf rfquirfmfnts

    /**
     * Sfts tiis <dodf>JdbdRowSft</dodf> objfdt's prfpbrfdtsbtfmfnt propfrty
     * to tif givfn <dodf>PrfpbrfdStbtfmfnnt</dodf> objfdt.
     *
     * @pbrbm prfpbrfdStbtfmfnt tif <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt
     *
     */
    protfdtfd void sftPrfpbrfdStbtfmfnt(PrfpbrfdStbtfmfnt prfpbrfdStbtfmfnt) {
       ps = prfpbrfdStbtfmfnt;
    }

    // Rfturns b RfsultSft ibndlf
    // Addfd bs pfr Rbvf rfquirfmfnts

    /**
     * Gfts tiis <dodf>JdbdRowSft</dodf> objfdt's RfsultSft propfrty
     *
     *
     * @rfturn tif <dodf>RfsultSft</dodf> objfdt bssodibtfd witi tiis rowsft;
     */

    protfdtfd RfsultSft gftRfsultSft() tirows SQLExdfption {

       difdkStbtf();

       rfturn rs;
    }

    // Sfts tif rfsult sft ibndlf to tif pbrbmftfr
    // Addfd bs pfr Rbvf rfquirfmfnts

    /**
     * Sfts tiis <dodf>JdbdRowSft</dodf> objfdt's rfsultsft propfrty
     * to tif givfn <dodf>RfsultSft</dodf> objfdt.
     *
     * @pbrbm rfsultSft tif <dodf>RfsultSft</dodf> objfdt
     *
     */
    protfdtfd void sftRfsultSft(RfsultSft rfsultSft) {
       rs = rfsultSft;
    }

    /**
     * Sfts tiis <dodf>JdbdRowSft</dodf> objfdt's <dodf>dommbnd</dodf> propfrty to
     * tif givfn <dodf>String</dodf> objfdt bnd dlfbrs tif pbrbmftfrs, if bny,
     * tibt wfrf sft for tif prfvious dommbnd. In bddition,
     * if tif <dodf>dommbnd</dodf> propfrty ibs prfviously bffn sft to b
     * non-null vbluf bnd it is
     * difffrfnt from tif <dodf>String</dodf> objfdt supplifd,
     * tiis mftiod sfts tiis <dodf>JdbdRowSft</dodf> objfdt's privbtf fiflds
     * <dodf>ps</dodf> bnd <dodf>rs</dodf> to <dodf>null</dodf>.
     * (Tif fifld <dodf>ps</dodf> is its <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt, bnd
     * tif fifld <dodf>rs</dodf> is its <dodf>RfsultSft</dodf> objfdt.)
     * <P>
     * Tif <dodf>dommbnd</dodf> propfrty mby not bf nffdfd if tif <dodf>RowSft</dodf>
     * objfdt gfts its dbtb from b sourdf tibt dofs not support dommbnds,
     * sudi bs b sprfbdsifft or otifr tbbulbr filf.
     * Tius, tiis propfrty is optionbl bnd mby bf <dodf>null</dodf>.
     *
     * @pbrbm dommbnd b <dodf>String</dodf> objfdt dontbining bn SQL qufry
     *            tibt will bf sft bs tiis <dodf>RowSft</dodf> objfdt's dommbnd
     *            propfrty; mby bf <dodf>null</dodf> but mby not bf bn fmpty string
     * @tirows SQLExdfption if bn fmpty string is providfd bs tif dommbnd vbluf
     * @sff #gftCommbnd
     */
    publid void sftCommbnd(String dommbnd) tirows SQLExdfption {

       if (gftCommbnd() != null) {
          if(!gftCommbnd().fqubls(dommbnd)) {
             supfr.sftCommbnd(dommbnd);
             ps = null;
             rs = null;
          }
       }
       flsf {
          supfr.sftCommbnd(dommbnd);
       }
    }

    /**
     * Sfts tif <dodf>dbtbSourdfNbmf</dodf> propfrty for tiis <dodf>JdbdRowSft</dodf>
     * objfdt to tif givfn logidbl nbmf bnd sfts tiis <dodf>JdbdRowSft</dodf> objfdt's
     * Url propfrty to <dodf>null</dodf>. In bddition, if tif <dodf>dbtbSourdfNbmf</dodf>
     * propfrty ibs prfviously bffn sft bnd is difffrfnt from tif onf supplifd,
     * tiis mftiod sfts tiis <dodf>JdbdRowSft</dodf> objfdt's privbtf fiflds
     * <dodf>ps</dodf>, <dodf>rs</dodf>, bnd <dodf>donn</dodf> to <dodf>null</dodf>.
     * (Tif fifld <dodf>ps</dodf> is its <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt,
     * tif fifld <dodf>rs</dodf> is its <dodf>RfsultSft</dodf> objfdt, bnd
     * tif fifld <dodf>donn</dodf> is its <dodf>Connfdtion</dodf> objfdt.)
     * <P>
     * Tif nbmf supplifd to tiis mftiod must ibvf bffn bound to b
     * <dodf>DbtbSourdf</dodf> objfdt in b JNDI nbming sfrvidf so tibt bn
     * bpplidbtion dbn do b lookup using tibt nbmf to rftrifvf tif
     * <dodf>DbtbSourdf</dodf> objfdt bound to it. Tif <dodf>DbtbSourdf</dodf>
     * objfdt dbn tifn bf usfd to fstbblisi b donnfdtion to tif dbtb sourdf it
     * rfprfsfnts.
     * <P>
     * Usfrs siould sft fitifr tif Url propfrty or tif dbtbSourdfNbmf propfrty.
     * If boti propfrtifs brf sft, tif drivfr will usf tif propfrty sft most rfdfntly.
     *
     * @pbrbm dsNbmf b <dodf>String</dodf> objfdt witi tif nbmf tibt dbn bf supplifd
     *        to b nbming sfrvidf bbsfd on JNDI tfdinology to rftrifvf tif
     *        <dodf>DbtbSourdf</dodf> objfdt tibt dbn bf usfd to gft b donnfdtion;
     *        mby bf <dodf>null</dodf>
     * @tirows SQLExdfption if tifrf is b problfm sftting tif
     *          <dodf>dbtbSourdfNbmf</dodf> propfrty
     * @sff #gftDbtbSourdfNbmf
     */
    publid void sftDbtbSourdfNbmf(String dsNbmf) tirows SQLExdfption{

       if(gftDbtbSourdfNbmf() != null) {
          if(!gftDbtbSourdfNbmf().fqubls(dsNbmf)) {
             supfr.sftDbtbSourdfNbmf(dsNbmf);
             donn = null;
             ps = null;
             rs = null;
          }
       }
       flsf {
          supfr.sftDbtbSourdfNbmf(dsNbmf);
       }
    }


    /**
     * Sfts tif Url propfrty for tiis <dodf>JdbdRowSft</dodf> objfdt
     * to tif givfn <dodf>String</dodf> objfdt bnd sfts tif dbtbSourdf nbmf
     * propfrty to <dodf>null</dodf>. In bddition, if tif Url propfrty ibs
     * prfviously bffn sft to b non <dodf>null</dodf> vbluf bnd its vbluf
     * is difffrfnt from tif vbluf to bf sft,
     * tiis mftiod sfts tiis <dodf>JdbdRowSft</dodf> objfdt's privbtf fiflds
     * <dodf>ps</dodf>, <dodf>rs</dodf>, bnd <dodf>donn</dodf> to <dodf>null</dodf>.
     * (Tif fifld <dodf>ps</dodf> is its <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt,
     * tif fifld <dodf>rs</dodf> is its <dodf>RfsultSft</dodf> objfdt, bnd
     * tif fifld <dodf>donn</dodf> is its <dodf>Connfdtion</dodf> objfdt.)
     * <P>
     * Tif Url propfrty is b JDBC URL tibt is usfd wifn
     * tif donnfdtion is drfbtfd using b JDBC tfdinology-fnbblfd drivfr
     * ("JDBC drivfr") bnd tif <dodf>DrivfrMbnbgfr</dodf>.
     * Tif dorrfdt JDBC URL for tif spfdifid drivfr to bf usfd dbn bf found
     * in tif drivfr dodumfntbtion.  Altiougi tifrf brf guidflinfs for for iow
     * b JDBC URL is formfd,
     * b drivfr vfndor dbn spfdify bny <dodf>String</dodf> objfdt fxdfpt
     * onf witi b lfngti of <dodf>0</dodf> (bn fmpty string).
     * <P>
     * Sftting tif Url propfrty is optionbl if donnfdtions brf fstbblisifd using
     * b <dodf>DbtbSourdf</dodf> objfdt instfbd of tif <dodf>DrivfrMbnbgfr</dodf>.
     * Tif drivfr will usf fitifr tif URL propfrty or tif
     * dbtbSourdfNbmf propfrty to drfbtf b donnfdtion, wiidifvfr wbs
     * spfdififd most rfdfntly. If bn bpplidbtion usfs b JDBC URL, it
     * must lobd b JDBC drivfr tibt bddfpts tif JDBC URL bfforf it usfs tif
     * <dodf>RowSft</dodf> objfdt to donnfdt to b dbtbbbsf.  Tif <dodf>RowSft</dodf>
     * objfdt will usf tif URL intfrnblly to drfbtf b dbtbbbsf donnfdtion in ordfr
     * to rfbd or writf dbtb.
     *
     * @pbrbm url b <dodf>String</dodf> objfdt tibt dontbins tif JDBC URL
     *            tibt will bf usfd to fstbblisi tif donnfdtion to b dbtbbbsf for tiis
     *            <dodf>RowSft</dodf> objfdt; mby bf <dodf>null</dodf> but must not
     *            bf bn fmpty string
     * @tirows SQLExdfption if bn frror oddurs sftting tif Url propfrty or tif
     *         pbrbmftfr supplifd is b string witi b lfngti of <dodf>0</dodf> (bn
     *         fmpty string)
     * @sff #gftUrl
     */

    publid void sftUrl(String url) tirows SQLExdfption {

       if(gftUrl() != null) {
          if(!gftUrl().fqubls(url)) {
             supfr.sftUrl(url);
             donn = null;
             ps = null;
             rs = null;
          }
       }
       flsf {
          supfr.sftUrl(url);
       }
    }

     /**
     * Sfts tif usfrnbmf propfrty for tiis <dodf>JdbdRowSft</dodf> objfdt
     * to tif givfn usfr nbmf. Bfdbusf it
     * is not sfriblizfd, tif usfrnbmf propfrty is sft bt run timf bfforf
     * dblling tif mftiod <dodf>fxfdutf</dodf>. In bddition,
     * if tif <dodf>usfrnbmf</dodf> propfrty is blrfbdy sft witi b
     * non-null vbluf bnd tibt vbluf is difffrfnt from tif <dodf>String</dodf>
     * objfdt to bf sft,
     * tiis mftiod sfts tiis <dodf>JdbdRowSft</dodf> objfdt's privbtf fiflds
     * <dodf>ps</dodf>, <dodf>rs</dodf>, bnd <dodf>donn</dodf> to <dodf>null</dodf>.
     * (Tif fifld <dodf>ps</dodf> is its <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt,
     * <dodf>rs</dodf> is its <dodf>RfsultSft</dodf> objfdt, bnd
     * <dodf>donn</dodf> is its <dodf>Connfdtion</dodf> objfdt.)
     * Sftting tifsf fiflds to <dodf>null</dodf> fnsurfs tibt only durrfnt
     * vblufs will bf usfd.
     *
     * @pbrbm unbmf tif <dodf>String</dodf> objfdt dontbining tif usfr nbmf tibt
     *     is supplifd to tif dbtb sourdf to drfbtf b donnfdtion. It mby bf null.
     * @sff #gftUsfrnbmf
     */
    publid void sftUsfrnbmf(String unbmf) {

       if( gftUsfrnbmf() != null) {
          if(!gftUsfrnbmf().fqubls(unbmf)) {
             supfr.sftUsfrnbmf(unbmf);
             donn = null;
             ps = null;
             rs = null;
          }
       }
       flsf{
          supfr.sftUsfrnbmf(unbmf);
       }
    }

     /**
     * Sfts tif pbssword propfrty for tiis <dodf>JdbdRowSft</dodf> objfdt
     * to tif givfn <dodf>String</dodf> objfdt. Bfdbusf it
     * is not sfriblizfd, tif pbssword propfrty is sft bt run timf bfforf
     * dblling tif mftiod <dodf>fxfdutf</dodf>. Its dffbult vblus is
     * <dodf>null</dodf>. In bddition,
     * if tif <dodf>pbssword</dodf> propfrty is blrfbdy sft witi b
     * non-null vbluf bnd tibt vbluf is difffrfnt from tif onf bfing sft,
     * tiis mftiod sfts tiis <dodf>JdbdRowSft</dodf> objfdt's privbtf fiflds
     * <dodf>ps</dodf>, <dodf>rs</dodf>, bnd <dodf>donn</dodf> to <dodf>null</dodf>.
     * (Tif fifld <dodf>ps</dodf> is its <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt,
     * <dodf>rs</dodf> is its <dodf>RfsultSft</dodf> objfdt, bnd
     * <dodf>donn</dodf> is its <dodf>Connfdtion</dodf> objfdt.)
     * Sftting tifsf fiflds to <dodf>null</dodf> fnsurfs tibt only durrfnt
     * vblufs will bf usfd.
     *
     * @pbrbm pbssword tif <dodf>String</dodf> objfdt tibt rfprfsfnts tif pbssword
     *     tibt must bf supplifd to tif dbtbbbsf to drfbtf b donnfdtion
     */
    publid void sftPbssword(String pbssword) {

       if ( gftPbssword() != null) {
          if(!gftPbssword().fqubls(pbssword)) {
             supfr.sftPbssword(pbssword);
             donn = null;
             ps = null;
             rs = null;
          }
       }
       flsf{
          supfr.sftPbssword(pbssword);
       }
    }

    /**
     * Sfts tif typf for tiis <dodf>RowSft</dodf> objfdt to tif spfdififd typf.
     * Tif dffbult typf is <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>.
     *
     * @pbrbm typf onf of tif following donstbnts:
     *             <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *             <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *             <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @tirows SQLExdfption if tif pbrbmftfr supplifd is not onf of tif
     *         following donstbnts:
     *          <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf> or
     *          <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>
     *          <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @sff #gftCondurrfndy
     * @sff #gftTypf
     */

    publid void sftTypf(int typf) tirows SQLExdfption {

       int oldVbl;

       try {
          oldVbl = gftTypf();
        }dbtdi(SQLExdfption fx) {
           oldVbl = 0;
        }

       if(oldVbl != typf) {
           supfr.sftTypf(typf);
       }

    }

    /**
     * Sfts tif dondurrfndy for tiis <dodf>RowSft</dodf> objfdt to
     * tif spfdififd dondurrfndy. Tif dffbult dondurrfndy for bny <dodf>RowSft</dodf>
     * objfdt (donnfdtfd or disdonnfdtfd) is <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>,
     * but tiis mftiod mby bf dbllfd bt bny timf to dibngf tif dondurrfndy.
     *
     * @pbrbm dondur onf of tif following donstbnts:
     *                    <dodf>RfsultSft.CONCUR_READ_ONLY</dodf> or
     *                    <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @tirows SQLExdfption if tif pbrbmftfr supplifd is not onf of tif
     *         following donstbnts:
     *          <dodf>RfsultSft.CONCUR_UPDATABLE</dodf> or
     *          <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     * @sff #gftCondurrfndy
     * @sff #isRfbdOnly
     */
    publid void sftCondurrfndy(int dondur) tirows SQLExdfption {

       int oldVbl;

       try {
          oldVbl = gftCondurrfndy();
        }dbtdi(NullPointfrExdfption fx) {
           oldVbl = 0;
        }

       if(oldVbl != dondur) {
           supfr.sftCondurrfndy(dondur);
       }

    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd <dodf>SQL XML</dodf> pbrbmftfr bs b
     * <dodf>SQLXML</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b SQLXML objfdt tibt mbps bn SQL XML vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid SQLXML gftSQLXML(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd <dodf>SQL XML</dodf> pbrbmftfr bs b
     * <dodf>SQLXML</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm dolNbmf tif nbmf of tif dolumn from wiidi to rftrifvf tif vbluf
     * @rfturn b SQLXML objfdt tibt mbps bn SQL XML vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid SQLXML gftSQLXML(String dolNbmf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>RfsultSft</dodf> objfdt bs b jbvb.sql.RowId objfdt in tif Jbvb
     * progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @rfturn tif dolumn vbluf if tif vbluf is b SQL <dodf>NULL</dodf> tif
     *     vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid RowId gftRowId(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>RfsultSft</dodf> objfdt bs b jbvb.sql.RowId objfdt in tif Jbvb
     * progrbmming lbngubgf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf if tif vbluf is b SQL <dodf>NULL</dodf> tif
     *     vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid RowId gftRowId(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>RowId</dodf> vbluf. Tif updbtfr
     * mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or tif insfrt
     * row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd
     * tif <dodf>updbtfRow<dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm x tif dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss oddurs
     * @sindf 1.6
     */
    publid void updbtfRowId(int dolumnIndfx, RowId x) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>RowId</dodf> vbluf. Tif updbtfr
     * mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or tif insfrt
     * row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd
     * tif <dodf>updbtfRow<dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm x tif dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss oddurs
     * @sindf 1.6
     */
    publid void updbtfRowId(String dolumnNbmf, RowId x) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Rftrifvfs tif ioldbbility of tiis RfsultSft objfdt
     * @rfturn  fitifr RfsultSft.HOLD_CURSORS_OVER_COMMIT or RfsultSft.CLOSE_CURSORS_AT_COMMIT
     * @tirows SQLExdfption if b dbtbbbsf frror oddurs
     * @sindf 1.6
     */
    publid int gftHoldbbility() tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Rftrifvfs wiftifr tiis RfsultSft objfdt ibs bffn dlosfd. A RfsultSft is dlosfd if tif
     * mftiod dlosf ibs bffn dbllfd on it, or if it is butombtidblly dlosfd.
     * @rfturn truf if tiis RfsultSft objfdt is dlosfd; fblsf if it is still opfn
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid boolfbn isClosfd() tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Tiis mftiod is usfd for updbting dolumns tibt support Nbtionbl Cibrbdtfr sfts.
     * It dbn bf usfd for updbting NCHAR,NVARCHAR bnd LONGNVARCHAR dolumns.
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm nString tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid void updbtfNString(int dolumnIndfx, String nString) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Tiis mftiod is usfd for updbting dolumns tibt support Nbtionbl Cibrbdtfr sfts.
     * It dbn bf usfd for updbting NCHAR,NVARCHAR bnd LONGNVARCHAR dolumns.
     * @pbrbm dolumnNbmf nbmf of tif Column
     * @pbrbm nString tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid void updbtfNString(String dolumnNbmf, String nString) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }


    /*o
     * Tiis mftiod is usfd for updbting SQL <dodf>NCLOB</dodf>  typf tibt mbps
     * to <dodf>jbvb.sql.Typfs.NCLOB</dodf>
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm nClob tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid void updbtfNClob(int dolumnIndfx, NClob nClob) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Tiis mftiod is usfd for updbting SQL <dodf>NCLOB</dodf>  typf tibt mbps
     * to <dodf>jbvb.sql.Typfs.NCLOB</dodf>
     * @pbrbm dolumnNbmf nbmf of tif dolumn
     * @pbrbm nClob tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid void updbtfNClob(String dolumnNbmf, NClob nClob) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>NClob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm i tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>NClob</dodf> objfdt rfprfsfnting tif SQL
     *         <dodf>NCLOB</dodf> vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid NClob gftNClob(int i) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }


  /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>NClob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolNbmf tif nbmf of tif dolumn from wiidi to rftrifvf tif vbluf
     * @rfturn b <dodf>NClob</dodf> objfdt rfprfsfnting tif SQL <dodf>NCLOB</dodf>
     * vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid NClob gftNClob(String dolNbmf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    publid <T> T unwrbp(jbvb.lbng.Clbss<T> ifbdf) tirows jbvb.sql.SQLExdfption{
        rfturn null;
    }

    publid boolfbn isWrbppfrFor(Clbss<?> intfrfbdfs) tirows SQLExdfption {
        rfturn fblsf;
    }

    /**
      * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.SQLXML</dodf> objfdt. Tif drivfr donvfrts tiis to bn
      * SQL <dodf>XML</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
      * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
      * @pbrbm xmlObjfdt b <dodf>SQLXML</dodf> objfdt tibt mbps bn SQL <dodf>XML</dodf> vbluf
      * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
      * @sindf 1.6
      */
     publid void sftSQLXML(int pbrbmftfrIndfx, SQLXML xmlObjfdt) tirows SQLExdfption {
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
     }

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.SQLXML</dodf> objfdt. Tif drivfr donvfrts tiis to bn
     * <dodf>SQL XML</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm xmlObjfdt b <dodf>SQLXML</dodf> objfdt tibt mbps bn <dodf>SQL XML</dodf> vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid void sftSQLXML(String pbrbmftfrNbmf, SQLXML xmlObjfdt) tirows SQLExdfption {
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
     }

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.RowId</dodf> objfdt. Tif
     * drivfr donvfrts tiis to b SQL <dodf>ROWID</dodf> vbluf wifn it sfnds it
     * to tif dbtbbbsf
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @pbrbm x tif pbrbmftfr vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     * @sindf 1.6
     */
    publid void sftRowId(int pbrbmftfrIndfx, RowId x) tirows SQLExdfption {
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
     }

    /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.RowId</dodf> objfdt. Tif
    * drivfr donvfrts tiis to b SQL <dodf>ROWID</dodf> wifn it sfnds it to tif
    * dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
    * @sindf 1.6
    */
   publid void sftRowId(String pbrbmftfrNbmf, RowId x) tirows SQLExdfption {
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
     }


   /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>String</dodf> objfdt.
     * Tif drivfr donvfrts tiis to b SQL <dodf>NCHAR</dodf> or
     * <dodf>NVARCHAR</dodf> or <dodf>LONGNVARCHAR</dodf> vbluf
     * (dfpfnding on tif brgumfnt's
     * sizf rflbtivf to tif drivfr's limits on <dodf>NVARCHAR</dodf> vblufs)
     * wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrIndfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @pbrbm vbluf tif pbrbmftfr vbluf
     * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur ; or if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
     publid void sftNString(int pbrbmftfrIndfx, String vbluf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
     }


   /**
    * Sfts tif dfsignbtfd pbrbmftfr in tiis <dodf>RowSft</dodf> objfdt's dommbnd
    * to b <dodf>Rfbdfr</dodf> objfdt. Tif
    * <dodf>Rfbdfr</dodf> rfbds tif dbtb till fnd-of-filf is rfbdifd. Tif
    * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
    * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.

    * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
    * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
    * stbndbrd intfrfbdf.
    * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
    * it migit bf morf fffidifnt to usf b vfrsion of
    * <dodf>sftNCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
    *
    * @pbrbm pbrbmftfrIndfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
    * @pbrbm vbluf tif pbrbmftfr vbluf
    * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
    *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
    *  frror dould oddur ; if b dbtbbbsf bddfss frror oddurs; or
    * tiis mftiod is dbllfd on b dlosfd <dodf>PrfpbrfdStbtfmfnt</dodf>
    * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
    * @sindf 1.6
    */
    publid void sftNCibrbdtfrStrfbm(int pbrbmftfrIndfx, Rfbdfr vbluf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

  /**
    * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>jbvb.sql.NClob</dodf> objfdt. Tif objfdt
    * implfmfnts tif <dodf>jbvb.sql.NClob</dodf> intfrfbdf. Tiis <dodf>NClob</dodf>
    * objfdt mbps to b SQL <dodf>NCLOB</dodf>.
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif dolumn to bf sft
    * @pbrbm vbluf tif pbrbmftfr vbluf
    * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
    *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
    *  frror dould oddur; or if b dbtbbbsf bddfss frror oddurs
    * @sindf 1.6
    */
    publid void sftNClob(String pbrbmftfrNbmf, NClob vbluf) tirows SQLExdfption {
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
     }


  /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif dolumn
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf.
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid jbvb.io.Rfbdfr gftNCibrbdtfrStrfbm(int dolumnIndfx) tirows SQLExdfption {
       tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
     }


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif dolumn
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid jbvb.io.Rfbdfr gftNCibrbdtfrStrfbm(String dolumnNbmf) tirows SQLExdfption {
       tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
     }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.SQLXML</dodf> vbluf.
     * Tif updbtfr
     * mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or tif insfrt
     * row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd
     * tif <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm xmlObjfdt tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid void updbtfSQLXML(int dolumnIndfx, SQLXML xmlObjfdt) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.SQLXML</dodf> vbluf.
     * Tif updbtfr
     * mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or tif insfrt
     * row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd
     * tif <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn
     * @pbrbm xmlObjfdt tif dolumn vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss oddurs
     * @sindf 1.6
     */
    publid void updbtfSQLXML(String dolumnNbmf, SQLXML xmlObjfdt) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

     /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid String gftNString(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @pbrbm dolumnNbmf tif SQL nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid String gftNString(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

     /**
       * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will
       * ibvf tif spfdififd numbfr of bytfs. Tif drivfr dofs tif nfdfssbry donvfrsion
       * from Jbvb dibrbdtfr formbt to tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
       * It is intfndfd for usf wifn updbting NCHAR,NVARCHAR bnd LONGNVARCHAR dolumns.
       * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or
       * tif insfrt row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf;
       * instfbd tif updbtfRow or insfrtRow mftiods brf dbllfd to updbtf tif dbtbbbsf.
       *
       * @pbrbm dolumnIndfx - tif first dolumn is 1, tif sfdond is 2, ...
       * @pbrbm x - tif nfw dolumn vbluf
       * @pbrbm lfngti - tif lfngti of tif strfbm
       * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
       * @sindf 1.6
       */
       publid void updbtfNCibrbdtfrStrfbm(int dolumnIndfx,
                            jbvb.io.Rfbdfr x,
                            long lfngti)
                            tirows SQLExdfption {
          tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
       }

     /**
       * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will
       * ibvf tif spfdififd numbfr of bytfs. Tif drivfr dofs tif nfdfssbry donvfrsion
       * from Jbvb dibrbdtfr formbt to tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
       * It is intfndfd for usf wifn updbting NCHAR,NVARCHAR bnd LONGNVARCHAR dolumns.
       * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or
       * tif insfrt row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf;
       * instfbd tif updbtfRow or insfrtRow mftiods brf dbllfd to updbtf tif dbtbbbsf.
       *
       * @pbrbm dolumnNbmf - nbmf of tif Column
       * @pbrbm x - tif nfw dolumn vbluf
       * @pbrbm lfngti - tif lfngti of tif strfbm
       * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
       * @sindf 1.6
       */
       publid void updbtfNCibrbdtfrStrfbm(String dolumnNbmf,
                            jbvb.io.Rfbdfr x,
                            long lfngti)
                            tirows SQLExdfption {
          tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
       }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.   Tif
     * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
     * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
     * It is intfndfd for usf wifn
     * updbting  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfNCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf> or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfNCibrbdtfrStrfbm(int dolumnIndfx,
                             jbvb.io.Rfbdfr x) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.  Tif
     * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
     * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
     * It is intfndfd for usf wifn
     * updbting  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfNCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lb
bfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining
     *        tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf> or tiis mftiod is dbllfd on b dlosfd rfsult sft
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfNCibrbdtfrStrfbm(String dolumnLbbfl,
                             jbvb.io.Rfbdfr rfbdfr) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn input strfbm, wiidi
     * will ibvf tif spfdififd numbfr of bytfs.
     * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @pbrbm lfngti tif numbfr of bytfs in tif pbrbmftfr dbtb.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfBlob(int dolumnIndfx, InputStrfbm inputStrfbm, long lfngti) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn input strfbm, wiidi
     * will ibvf tif spfdififd numbfr of bytfs.
     * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @pbrbm lfngti tif numbfr of bytfs in tif pbrbmftfr dbtb.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfBlob(String dolumnLbbfl, InputStrfbm inputStrfbm, long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn input strfbm.
     * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *
     *  <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfBlob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfBlob(int dolumnIndfx, InputStrfbm inputStrfbm) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn input strfbm.
     * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *   <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfBlob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lb
bfl is tif nbmf of tif dolumn
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfBlob(String dolumnLbbfl, InputStrfbm inputStrfbm) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfClob(int dolumnIndfx,  Rfbdfr rfbdfr, long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfClob(String dolumnLbbfl,  Rfbdfr rfbdfr, long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *   <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfClob(int dolumnIndfx,  Rfbdfr rfbdfr) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *  <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lb
bfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfClob(String dolumnLbbfl,  Rfbdfr rfbdfr) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

   /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft,
     * if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfNClob(int dolumnIndfx,  Rfbdfr rfbdfr, long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     *  if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfNClob(String dolumnLbbfl,  Rfbdfr rfbdfr, long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfNClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft,
     * if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfNClob(int dolumnIndfx,  Rfbdfr rfbdfr) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfNClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lb
bfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     *  if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfNClob(String dolumnLbbfl,  Rfbdfr rfbdfr) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }


        /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfAsdiiStrfbm(int dolumnIndfx,
                           jbvb.io.InputStrfbm x,
                           long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfBinbryStrfbm(int dolumnIndfx,
                            jbvb.io.InputStrfbm x,
                            long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfCibrbdtfrStrfbm(int dolumnIndfx,
                             jbvb.io.Rfbdfr x,
                             long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

     /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs..
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfAsdiiStrfbm(String dolumnLbbfl,
                           jbvb.io.InputStrfbm x,
                           long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfAsdiiStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfAsdiiStrfbm(int dolumnIndfx,
                           jbvb.io.InputStrfbm x) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfAsdiiStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lb
bfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfAsdiiStrfbm(String dolumnLbbfl,
                           jbvb.io.InputStrfbm x) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }


    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfBinbryStrfbm(String dolumnLbbfl,
                            jbvb.io.InputStrfbm x,
                            long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfBinbryStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfBinbryStrfbm(int dolumnIndfx,
                            jbvb.io.InputStrfbm x) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }


    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfBinbryStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lb
bfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfBinbryStrfbm(String dolumnLbbfl,
                            jbvb.io.InputStrfbm x) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }


    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining
     *        tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfCibrbdtfrStrfbm(String dolumnLbbfl,
                             jbvb.io.Rfbdfr rfbdfr,
                             long lfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfCibrbdtfrStrfbm(int dolumnIndfx,
                             jbvb.io.Rfbdfr x) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lb
bfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining
     *        tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    publid void updbtfCibrbdtfrStrfbm(String dolumnLbbfl,
                             jbvb.io.Rfbdfr rfbdfr) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }


     /**
  * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.nft.URL</dodf> vbluf.
  * Tif drivfr donvfrts tiis to bn SQL <dodf>DATALINK</dodf> vbluf
  * wifn it sfnds it to tif dbtbbbsf.
  *
  * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, ...
  * @pbrbm x tif <dodf>jbvb.nft.URL</dodf> objfdt to bf sft
  * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
  * tiis mftiod is dbllfd on b dlosfd <dodf>PrfpbrfdStbtfmfnt</dodf>
  * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
  * @sindf 1.4
  */
  publid void sftURL(int pbrbmftfrIndfx, jbvb.nft.URL x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


   /**
  * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.
  * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr)</dodf> mftiod
  * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
  * tif sfrvfr bs b <dodf>NCLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
  * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
  * dbtb siould bf sfnt to tif sfrvfr bs b <dodf>LONGNVARCHAR</dodf> or b <dodf>NCLOB</dodf>
  * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
  * it migit bf morf fffidifnt to usf b vfrsion of
  * <dodf>sftNClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
  *
  * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
  * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
  * @tirows SQLExdfption if pbrbmftfrIndfx dofs not dorrfspond to b pbrbmftfr
  * mbrkfr in tif SQL stbtfmfnt;
  * if tif drivfr dofs not support nbtionbl dibrbdtfr sfts;
  * if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
  *  frror dould oddur;  if b dbtbbbsf bddfss frror oddurs or
  * tiis mftiod is dbllfd on b dlosfd <dodf>PrfpbrfdStbtfmfnt</dodf>
  * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
  *
  * @sindf 1.6
  */
  publid void sftNClob(int pbrbmftfrIndfx, Rfbdfr rfbdfr)
    tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

   /**
  * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.  Tif <dodf>rfbdfr</dodf> must dontbin tif numbfr
             * of dibrbdtfrs spfdififd by lfngti otifrwisf b <dodf>SQLExdfption</dodf> will bf
            * gfnfrbtfd wifn tif <dodf>CbllbblfStbtfmfnt</dodf> is fxfdutfd.
            * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr, int)</dodf> mftiod
            * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
            * tif sfrvfr bs b <dodf>NCLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
            * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
            * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGNVARCHAR</dodf> or b <dodf>NCLOB</dodf>
            *
            * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
            * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
            * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
            * @tirows SQLExdfption if pbrbmftfrIndfx dofs not dorrfspond to b pbrbmftfr
            * mbrkfr in tif SQL stbtfmfnt; if tif lfngti spfdififd is lfss tibn zfro;
            * if tif drivfr dofs not support nbtionbl
            *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
            *  frror dould oddur; if b dbtbbbsf bddfss frror oddurs or
            * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
            * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
            * tiis mftiod
            * @sindf 1.6
            */
            publid void sftNClob(String pbrbmftfrNbmf, Rfbdfr rfbdfr, long lfngti)
    tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
  * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.
  * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr)</dodf> mftiod
  * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
  * tif sfrvfr bs b <dodf>NCLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
  * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
  * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGNVARCHAR</dodf> or b <dodf>NCLOB</dodf>
  * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
  * it migit bf morf fffidifnt to usf b vfrsion of
  * <dodf>sftNClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
  *
  * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
  * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
  * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl dibrbdtfr sfts;
  * if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
  *  frror dould oddur;  if b dbtbbbsf bddfss frror oddurs or
  * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
  * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
  *
  * @sindf 1.6
  */
  publid void sftNClob(String pbrbmftfrNbmf, Rfbdfr rfbdfr)
    tirows SQLExdfption{
             tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


   /**
     ** of dibrbdtfrs spfdififd by lfngti otifrwisf b <dodf>SQLExdfption</dodf> will bfdontbin  tif numbfr
     * gfnfrbtfd wifn tif <dodf>PrfpbrfdStbtfmfnt</dodf> is fxfdutfd.
     * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr, int)</dodf> mftiod
     * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
     * tif sfrvfr bs b <dodf>NCLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
     * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
     * dbtb siould bf sfnt to tif sfrvfr bs b <dodf>LONGNVARCHAR</dodf> or b <dodf>NCLOB</dodf>
     * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption if pbrbmftfrIndfx dofs not dorrfspond to b pbrbmftfr
     * mbrkfr in tif SQL stbtfmfnt; if tif lfngti spfdififd is lfss tibn zfro;
     * if tif drivfr dofs not support nbtionbl dibrbdtfr sfts;
     * if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur;  if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>PrfpbrfdStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     *
     * @sindf 1.6
     */
     publid void sftNClob(int pbrbmftfrIndfx, Rfbdfr rfbdfr, long lfngti)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>jbvb.sql.NClob</dodf> objfdt. Tif drivfr donvfrts tiis to
b
     * SQL <dodf>NCLOB</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     * @pbrbm pbrbmftfrIndfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @pbrbm vbluf tif pbrbmftfr vbluf
     * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur ; or if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
     publid void sftNClob(int pbrbmftfrIndfx, NClob vbluf) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
  * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>String</dodf> objfdt.
  * Tif drivfr donvfrts tiis to b SQL <dodf>NCHAR</dodf> or
  * <dodf>NVARCHAR</dodf> or <dodf>LONGNVARCHAR</dodf>
  * @pbrbm pbrbmftfrNbmf tif nbmf of tif dolumn to bf sft
  * @pbrbm vbluf tif pbrbmftfr vbluf
  * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
  *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
  *  frror dould oddur; or if b dbtbbbsf bddfss frror oddurs
  * @sindf 1.6
  */
 publid void sftNString(String pbrbmftfrNbmf, String vbluf)
         tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
  * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt. Tif
  * <dodf>Rfbdfr</dodf> rfbds tif dbtb till fnd-of-filf is rfbdifd. Tif
  * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
  * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
  * @pbrbm pbrbmftfrIndfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
  * @pbrbm vbluf tif pbrbmftfr vbluf
  * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
  * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
  *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
  *  frror dould oddur ; or if b dbtbbbsf bddfss frror oddurs
  * @sindf 1.6
  */
  publid void sftNCibrbdtfrStrfbm(int pbrbmftfrIndfx, Rfbdfr vbluf, long lfngti) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }



 /**
  * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt. Tif
  * <dodf>Rfbdfr</dodf> rfbds tif dbtb till fnd-of-filf is rfbdifd. Tif
  * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
  * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
  * @pbrbm pbrbmftfrNbmf tif nbmf of tif dolumn to bf sft
  * @pbrbm vbluf tif pbrbmftfr vbluf
  * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
  * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
  *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
  *  frror dould oddur; or if b dbtbbbsf bddfss frror oddurs
  * @sindf 1.6
  */
 publid void sftNCibrbdtfrStrfbm(String pbrbmftfrNbmf, Rfbdfr vbluf, long lfngti)
         tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

  /**
  * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt. Tif
  * <dodf>Rfbdfr</dodf> rfbds tif dbtb till fnd-of-filf is rfbdifd. Tif
  * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
  * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.

  * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
  * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
  * stbndbrd intfrfbdf.
  * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
  * it migit bf morf fffidifnt to usf b vfrsion of
  * <dodf>sftNCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
  *
  * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
  * @pbrbm vbluf tif pbrbmftfr vbluf
  * @tirows SQLExdfption if tif drivfr dofs not support nbtionbl
  *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
  *  frror dould oddur ; if b dbtbbbsf bddfss frror oddurs; or
  * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
  * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
  * @sindf 1.6
  */
  publid void sftNCibrbdtfrStrfbm(String pbrbmftfrNbmf, Rfbdfr vbluf) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

  /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Timfstbmp</dodf> vbluf,
    * using tif givfn <dodf>Cblfndbr</dodf> objfdt.  Tif drivfr usfs
    * tif <dodf>Cblfndbr</dodf> objfdt to donstrudt bn SQL <dodf>TIMESTAMP</dodf> vbluf,
    * wiidi tif drivfr tifn sfnds to tif dbtbbbsf.  Witi b
    * b <dodf>Cblfndbr</dodf> objfdt, tif drivfr dbn dbldulbtf tif timfstbmp
    * tbking into bddount b dustom timfzonf.  If no
    * <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif dffbult
    * timfzonf, wiidi is tibt of tif virtubl mbdiinf running tif bpplidbtion.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
    *            to donstrudt tif timfstbmp
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftTimfstbmp
    * @sindf 1.4
    */
    publid void sftTimfstbmp(String pbrbmftfrNbmf, jbvb.sql.Timfstbmp x, Cblfndbr dbl)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

    /**
    * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.  Tif <dodf>rfbdfr</dodf> must dontbin  tif numbfr
               * of dibrbdtfrs spfdififd by lfngti otifrwisf b <dodf>SQLExdfption</dodf> will bf
               * gfnfrbtfd wifn tif <dodf>CbllbblfStbtfmfnt</dodf> is fxfdutfd.
              * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr, int)</dodf> mftiod
              * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
              * tif sfrvfr bs b <dodf>CLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
               * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
               * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGVARCHAR</dodf> or b <dodf>CLOB</dodf>
               * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
              * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
              * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
              * @tirows SQLExdfption if pbrbmftfrIndfx dofs not dorrfspond to b pbrbmftfr
              * mbrkfr in tif SQL stbtfmfnt; if tif lfngti spfdififd is lfss tibn zfro;
              * b dbtbbbsf bddfss frror oddurs or
              * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
              * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
              * tiis mftiod
              *
              * @sindf 1.6
              */
      publid  void sftClob(String pbrbmftfrNbmf, Rfbdfr rfbdfr, long lfngti)
      tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }



  /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Clob</dodf> objfdt.
    * Tif drivfr donvfrts tiis to bn SQL <dodf>CLOB</dodf> vbluf wifn it
    * sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x b <dodf>Clob</dodf> objfdt tibt mbps bn SQL <dodf>CLOB</dodf> vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sindf 1.6
    */
    publid void sftClob (String pbrbmftfrNbmf, Clob x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.
    * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr)</dodf> mftiod
    * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
    * tif sfrvfr bs b <dodf>CLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
    * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
    * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGVARCHAR</dodf> or b <dodf>CLOB</dodf>
    *
    * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
    * it migit bf morf fffidifnt to usf b vfrsion of
    * <dodf>sftClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is dbllfd on
    * b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    *
    * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
    * @sindf 1.6
    */
    publid void sftClob(String pbrbmftfrNbmf, Rfbdfr rfbdfr)
      tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Dbtf</dodf> vbluf
    * using tif dffbult timf zonf of tif virtubl mbdiinf tibt is running
    * tif bpplidbtion.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>DATE</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftDbtf
    * @sindf 1.4
    */
    publid void sftDbtf(String pbrbmftfrNbmf, jbvb.sql.Dbtf x)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

   /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Dbtf</dodf> vbluf,
    * using tif givfn <dodf>Cblfndbr</dodf> objfdt.  Tif drivfr usfs
    * tif <dodf>Cblfndbr</dodf> objfdt to donstrudt bn SQL <dodf>DATE</dodf> vbluf,
    * wiidi tif drivfr tifn sfnds to tif dbtbbbsf.  Witi b
    * b <dodf>Cblfndbr</dodf> objfdt, tif drivfr dbn dbldulbtf tif dbtf
    * tbking into bddount b dustom timfzonf.  If no
    * <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif dffbult
    * timfzonf, wiidi is tibt of tif virtubl mbdiinf running tif bpplidbtion.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
    *            to donstrudt tif dbtf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftDbtf
    * @sindf 1.4
    */
   publid void sftDbtf(String pbrbmftfrNbmf, jbvb.sql.Dbtf x, Cblfndbr dbl)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Timf</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>TIME</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftTimf
    * @sindf 1.4
    */
   publid void sftTimf(String pbrbmftfrNbmf, jbvb.sql.Timf x)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Timf</dodf> vbluf,
    * using tif givfn <dodf>Cblfndbr</dodf> objfdt.  Tif drivfr usfs
    * tif <dodf>Cblfndbr</dodf> objfdt to donstrudt bn SQL <dodf>TIME</dodf> vbluf,
    * wiidi tif drivfr tifn sfnds to tif dbtbbbsf.  Witi b
    * b <dodf>Cblfndbr</dodf> objfdt, tif drivfr dbn dbldulbtf tif timf
    * tbking into bddount b dustom timfzonf.  If no
    * <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif dffbult
    * timfzonf, wiidi is tibt of tif virtubl mbdiinf running tif bpplidbtion.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
    *            to donstrudt tif timf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftTimf
    * @sindf 1.4
    */
   publid void sftTimf(String pbrbmftfrNbmf, jbvb.sql.Timf x, Cblfndbr dbl)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

   /**
   * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.
   * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr)</dodf> mftiod
   * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
   * tif sfrvfr bs b <dodf>CLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
   * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
   * dbtb siould bf sfnt to tif sfrvfr bs b <dodf>LONGVARCHAR</dodf> or b <dodf>CLOB</dodf>
   *
   * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
   * it migit bf morf fffidifnt to usf b vfrsion of
   * <dodf>sftClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
   *
   * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
   * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
   * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis mftiod is dbllfd on
   * b dlosfd <dodf>PrfpbrfdStbtfmfnt</dodf>or if pbrbmftfrIndfx dofs not dorrfspond to b pbrbmftfr
   * mbrkfr in tif SQL stbtfmfnt
   *
   * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
   * @sindf 1.6
   */
   publid void sftClob(int pbrbmftfrIndfx, Rfbdfr rfbdfr)
     tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


   /**
   * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.  Tif rfbdfr must dontbin  tif numbfr
   * of dibrbdtfrs spfdififd by lfngti otifrwisf b <dodf>SQLExdfption</dodf> will bf
   * gfnfrbtfd wifn tif <dodf>PrfpbrfdStbtfmfnt</dodf> is fxfdutfd.
   *Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr, int)</dodf> mftiod
   * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
   * tif sfrvfr bs b <dodf>CLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
   * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
   * dbtb siould bf sfnt to tif sfrvfr bs b <dodf>LONGVARCHAR</dodf> or b <dodf>CLOB</dodf>
   * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
   * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
   * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
   * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis mftiod is dbllfd on
   * b dlosfd <dodf>PrfpbrfdStbtfmfnt</dodf>, if pbrbmftfrIndfx dofs not dorrfspond to b pbrbmftfr
   * mbrkfr in tif SQL stbtfmfnt, or if tif lfngti spfdififd is lfss tibn zfro.
   *
   * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
   * @sindf 1.6
   */
   publid void sftClob(int pbrbmftfrIndfx, Rfbdfr rfbdfr, long lfngti)
     tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
    * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>InputStrfbm</dodf> objfdt.  Tif inputstrfbm must dontbin  tif numbfr
    * of dibrbdtfrs spfdififd by lfngti otifrwisf b <dodf>SQLExdfption</dodf> will bf
    * gfnfrbtfd wifn tif <dodf>PrfpbrfdStbtfmfnt</dodf> is fxfdutfd.
    * Tiis mftiod difffrs from tif <dodf>sftBinbryStrfbm (int, InputStrfbm, int)</dodf>
    * mftiod bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf
    * sfnt to tif sfrvfr bs b <dodf>BLOB</dodf>.  Wifn tif <dodf>sftBinbryStrfbm</dodf> mftiod is usfd,
    * tif drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
    * dbtb siould bf sfnt to tif sfrvfr bs b <dodf>LONGVARBINARY</dodf> or b <dodf>BLOB</dodf>
    * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1,
    * tif sfdond is 2, ...
    * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
    * vbluf to.
    * @pbrbm lfngti tif numbfr of bytfs in tif pbrbmftfr dbtb.
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs,
    * tiis mftiod is dbllfd on b dlosfd <dodf>PrfpbrfdStbtfmfnt</dodf>,
    * if pbrbmftfrIndfx dofs not dorrfspond
    * to b pbrbmftfr mbrkfr in tif SQL stbtfmfnt,  if tif lfngti spfdififd
    * is lfss tibn zfro or if tif numbfr of bytfs in tif inputstrfbm dofs not mbtdi
    * tif spfdififd lfngti.
    * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
    *
    * @sindf 1.6
    */
    publid void sftBlob(int pbrbmftfrIndfx, InputStrfbm inputStrfbm, long lfngti)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>InputStrfbm</dodf> objfdt.
    * Tiis mftiod difffrs from tif <dodf>sftBinbryStrfbm (int, InputStrfbm)</dodf>
    * Tiis mftiod difffrs from tif <dodf>sftBinbryStrfbm (int, InputStrfbm)</dodf>
    * mftiod bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf
    * sfnt to tif sfrvfr bs b <dodf>BLOB</dodf>.  Wifn tif <dodf>sftBinbryStrfbm</dodf> mftiod is usfd,
    * tif drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
    * dbtb siould bf sfnt to tif sfrvfr bs b <dodf>LONGVARBINARY</dodf> or b <dodf>BLOB</dodf>
    *
    * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
    * it migit bf morf fffidifnt to usf b vfrsion of
    * <dodf>sftBlob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
    *
    * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1,
    * tif sfdond is 2, ...


    * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
    * vbluf to.
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs,
    * tiis mftiod is dbllfd on b dlosfd <dodf>PrfpbrfdStbtfmfnt</dodf> or
    * if pbrbmftfrIndfx dofs not dorrfspond
    * to b pbrbmftfr mbrkfr in tif SQL stbtfmfnt,
    * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
    *
    * @sindf 1.6
    */
    publid void sftBlob(int pbrbmftfrIndfx, InputStrfbm inputStrfbm)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>InputStrfbm</dodf> objfdt.  Tif <dodf>inputstrfbm</dodf> must dontbin  tif numbfr
      * of dibrbdtfrs spfdififd by lfngti, otifrwisf b <dodf>SQLExdfption</dodf> will bf
      * gfnfrbtfd wifn tif <dodf>CbllbblfStbtfmfnt</dodf> is fxfdutfd.
      * Tiis mftiod difffrs from tif <dodf>sftBinbryStrfbm (int, InputStrfbm, int)</dodf>
      * mftiod bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf
      * sfnt to tif sfrvfr bs b <dodf>BLOB</dodf>.  Wifn tif <dodf>sftBinbryStrfbm</dodf> mftiod is usfd,
      * tif drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
      * dbtb siould bf sfnt to tif sfrvfr bs b <dodf>LONGVARBINARY</dodf> or b <dodf>BLOB</dodf>
      *
      * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
      * tif sfdond is 2, ...
      *
      * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
      * vbluf to.
      * @pbrbm lfngti tif numbfr of bytfs in tif pbrbmftfr dbtb.
      * @tirows SQLExdfption  if pbrbmftfrIndfx dofs not dorrfspond
      * to b pbrbmftfr mbrkfr in tif SQL stbtfmfnt,  or if tif lfngti spfdififd
      * is lfss tibn zfro; if tif numbfr of bytfs in tif inputstrfbm dofs not mbtdi
      * tif spfdififd lfngti; if b dbtbbbsf bddfss frror oddurs or
      * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
      * tiis mftiod
      *
      * @sindf 1.6
      */
      publid void sftBlob(String pbrbmftfrNbmf, InputStrfbm inputStrfbm, long lfngti)
         tirows SQLExdfption{
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
    }


 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Blob</dodf> objfdt.
    * Tif drivfr donvfrts tiis to bn SQL <dodf>BLOB</dodf> vbluf wifn it
    * sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x b <dodf>Blob</dodf> objfdt tibt mbps bn SQL <dodf>BLOB</dodf> vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sindf 1.6
    */
   publid void sftBlob (String pbrbmftfrNbmf, Blob x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>InputStrfbm</dodf> objfdt.
    * Tiis mftiod difffrs from tif <dodf>sftBinbryStrfbm (int, InputStrfbm)</dodf>
    * mftiod bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf
    * sfnt to tif sfrvfr bs b <dodf>BLOB</dodf>.  Wifn tif <dodf>sftBinbryStrfbm</dodf> mftiod is usfd,
    * tif drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
    * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGVARBINARY</dodf> or b <dodf>BLOB</dodf>
    *
    * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
    * it migit bf morf fffidifnt to usf b vfrsion of
    * <dodf>sftBlob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
    * vbluf to.
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
    *
    * @sindf 1.6
    */
    publid void sftBlob(String pbrbmftfrNbmf, InputStrfbm inputStrfbm)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

  /**
  * Sfts tif vbluf of tif dfsignbtfd pbrbmftfr witi tif givfn objfdt. Tif sfdond
  * brgumfnt must bf bn objfdt typf; for intfgrbl vblufs, tif
  * <dodf>jbvb.lbng</dodf> fquivblfnt objfdts siould bf usfd.
  *
  * <p>Tif givfn Jbvb objfdt will bf donvfrtfd to tif givfn tbrgftSqlTypf
  * bfforf bfing sfnt to tif dbtbbbsf.
  *
  * If tif objfdt ibs b dustom mbpping (is of b dlbss implfmfnting tif
  * intfrfbdf <dodf>SQLDbtb</dodf>),
  * tif JDBC drivfr siould dbll tif mftiod <dodf>SQLDbtb.writfSQL</dodf> to writf it
  * to tif SQL dbtb strfbm.
  * If, on tif otifr ibnd, tif objfdt is of b dlbss implfmfnting
  * <dodf>Rff</dodf>, <dodf>Blob</dodf>, <dodf>Clob</dodf>,  <dodf>NClob</dodf>,
  *  <dodf>Strudt</dodf>, <dodf>jbvb.nft.URL</dodf>,
  * or <dodf>Arrby</dodf>, tif drivfr siould pbss it to tif dbtbbbsf bs b
  * vbluf of tif dorrfsponding SQL typf.
  * <P>
  * Notf tibt tiis mftiod mby bf usfd to pbss dbtbtbbbsf-
  * spfdifid bbstrbdt dbtb typfs.
  *
  * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
  * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
  * @pbrbm tbrgftSqlTypf tif SQL typf (bs dffinfd in jbvb.sql.Typfs) to bf
  * sfnt to tif dbtbbbsf. Tif sdblf brgumfnt mby furtifr qublify tiis typf.
  * @pbrbm sdblf for jbvb.sql.Typfs.DECIMAL or jbvb.sql.Typfs.NUMERIC typfs,
  *          tiis is tif numbfr of digits bftfr tif dfdimbl point.  For bll otifr
  *          typfs, tiis vbluf will bf ignorfd.
  * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
  * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
  * @fxdfption SQLFfbturfNotSupportfdExdfption if <dodf>tbrgftSqlTypf</dodf> is
  * b <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>, <dodf>CLOB</dodf>,
  * <dodf>DATALINK</dodf>, <dodf>JAVA_OBJECT</dodf>, <dodf>NCHAR</dodf>,
  * <dodf>NCLOB</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>,
  *  <dodf>REF</dodf>, <dodf>ROWID</dodf>, <dodf>SQLXML</dodf>
  * or  <dodf>STRUCT</dodf> dbtb typf bnd tif JDBC drivfr dofs not support
  * tiis dbtb typf
  * @sff Typfs
  * @sff #gftObjfdt
  * @sindf 1.4
  */
  publid void sftObjfdt(String pbrbmftfrNbmf, Objfdt x, int tbrgftSqlTypf, int sdblf)
     tirows SQLExdfption{
      tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
 }

  /**
    * Sfts tif vbluf of tif dfsignbtfd pbrbmftfr witi tif givfn objfdt.
    * Tiis mftiod is likf tif mftiod <dodf>sftObjfdt</dodf>
    * bbovf, fxdfpt tibt it bssumfs b sdblf of zfro.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
    * @pbrbm tbrgftSqlTypf tif SQL typf (bs dffinfd in jbvb.sql.Typfs) to bf
    *                      sfnt to tif dbtbbbsf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if <dodf>tbrgftSqlTypf</dodf> is
    * b <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>, <dodf>CLOB</dodf>,
    * <dodf>DATALINK</dodf>, <dodf>JAVA_OBJECT</dodf>, <dodf>NCHAR</dodf>,
    * <dodf>NCLOB</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>,
    *  <dodf>REF</dodf>, <dodf>ROWID</dodf>, <dodf>SQLXML</dodf>
    * or  <dodf>STRUCT</dodf> dbtb typf bnd tif JDBC drivfr dofs not support
    * tiis dbtb typf
    * @sff #gftObjfdt
    * @sindf 1.4
    */
    publid void sftObjfdt(String pbrbmftfrNbmf, Objfdt x, int tbrgftSqlTypf)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
   * Sfts tif vbluf of tif dfsignbtfd pbrbmftfr witi tif givfn objfdt.
   * Tif sfdond pbrbmftfr must bf of typf <dodf>Objfdt</dodf>; tifrfforf, tif
   * <dodf>jbvb.lbng</dodf> fquivblfnt objfdts siould bf usfd for built-in typfs.
   *
   * <p>Tif JDBC spfdifidbtion spfdififs b stbndbrd mbpping from
   * Jbvb <dodf>Objfdt</dodf> typfs to SQL typfs.  Tif givfn brgumfnt
   * will bf donvfrtfd to tif dorrfsponding SQL typf bfforf bfing
   * sfnt to tif dbtbbbsf.
   *
   * <p>Notf tibt tiis mftiod mby bf usfd to pbss dbtbtbbbsf-
   * spfdifid bbstrbdt dbtb typfs, by using b drivfr-spfdifid Jbvb
   * typf.
   *
   * If tif objfdt is of b dlbss implfmfnting tif intfrfbdf <dodf>SQLDbtb</dodf>,
   * tif JDBC drivfr siould dbll tif mftiod <dodf>SQLDbtb.writfSQL</dodf>
   * to writf it to tif SQL dbtb strfbm.
   * If, on tif otifr ibnd, tif objfdt is of b dlbss implfmfnting
   * <dodf>Rff</dodf>, <dodf>Blob</dodf>, <dodf>Clob</dodf>,  <dodf>NClob</dodf>,
   *  <dodf>Strudt</dodf>, <dodf>jbvb.nft.URL</dodf>,
   * or <dodf>Arrby</dodf>, tif drivfr siould pbss it to tif dbtbbbsf bs b
   * vbluf of tif dorrfsponding SQL typf.
   * <P>
   * Tiis mftiod tirows bn fxdfption if tifrf is bn bmbiguity, for fxbmplf, if tif
   * objfdt is of b dlbss implfmfnting morf tibn onf of tif intfrfbdfs nbmfd bbovf.
   *
   * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
   * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
   * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf> or if tif givfn
   *            <dodf>Objfdt</dodf> pbrbmftfr is bmbiguous
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sff #gftObjfdt
   * @sindf 1.4
   */
   publid void sftObjfdt(String pbrbmftfrNbmf, Objfdt x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

  /**
  * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm, wiidi will ibvf
  * tif spfdififd numbfr of bytfs.
  * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
  * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
  * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
  * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
  * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
  *
  * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
  * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
  * stbndbrd intfrfbdf.
  *
  * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
  * @pbrbm x tif Jbvb input strfbm tibt dontbins tif ASCII pbrbmftfr vbluf
  * @pbrbm lfngti tif numbfr of bytfs in tif strfbm
  * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
  * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
  * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
  * tiis mftiod
  * @sindf 1.4
  */
 publid void sftAsdiiStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x, int lfngti)
     tirows SQLExdfption{
      tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
 }


/**
  * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm, wiidi will ibvf
  * tif spfdififd numbfr of bytfs.
  * Wifn b vfry lbrgf binbry vbluf is input to b <dodf>LONGVARBINARY</dodf>
  * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
  * <dodf>jbvb.io.InputStrfbm</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
  * bs nffdfd until fnd-of-filf is rfbdifd.
  *
  * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
  * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
  * stbndbrd intfrfbdf.
  *
  * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
  * @pbrbm x tif jbvb input strfbm wiidi dontbins tif binbry pbrbmftfr vbluf
  * @pbrbm lfngti tif numbfr of bytfs in tif strfbm
  * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
  * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
  * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
  * tiis mftiod
  * @sindf 1.4
  */
 publid void sftBinbryStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x,
                      int lfngti) tirows SQLExdfption{
      tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
 }

 /**
   * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>Rfbdfr</dodf>
   * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
   * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
   * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
   * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
   * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
   * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
   *
   * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
   * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
   * stbndbrd intfrfbdf.
   *
   * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
   * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt
   *        dontbins tif UNICODE dbtb usfd bs tif dfsignbtfd pbrbmftfr
   * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif strfbm
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
   * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.4
   */
  publid void sftCibrbdtfrStrfbm(String pbrbmftfrNbmf,
                          jbvb.io.Rfbdfr rfbdfr,
                          int lfngti) tirows SQLExdfption{
       tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
  }

  /**
   * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm.
   * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
   * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
   * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
   * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
   * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
   *
   * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
   * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
   * stbndbrd intfrfbdf.
   * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
   * it migit bf morf fffidifnt to usf b vfrsion of
   * <dodf>sftAsdiiStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
   *
   * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
   * @pbrbm x tif Jbvb input strfbm tibt dontbins tif ASCII pbrbmftfr vbluf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
   * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
   * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @sindf 1.6
  */
  publid void sftAsdiiStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x)
          tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm.
    * Wifn b vfry lbrgf binbry vbluf is input to b <dodf>LONGVARBINARY</dodf>
    * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
    * <dodf>jbvb.io.InputStrfbm</dodf> objfdt. Tif dbtb will bf rfbd from tif
    * strfbm bs nffdfd until fnd-of-filf is rfbdifd.
    *
    * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
    * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
    * stbndbrd intfrfbdf.
    * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
    * it migit bf morf fffidifnt to usf b vfrsion of
    * <dodf>sftBinbryStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif jbvb input strfbm wiidi dontbins tif binbry pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
    * @sindf 1.6
    */
   publid void sftBinbryStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x)
   tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>Rfbdfr</dodf>
    * objfdt.
    * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
    * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
    * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
    * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
    * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
    *
    * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
    * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
    * stbndbrd intfrfbdf.
    * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
    * it migit bf morf fffidifnt to usf b vfrsion of
    * <dodf>sftCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif
    *        Unidodf dbtb
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
    * @sindf 1.6
    */
   publid void sftCibrbdtfrStrfbm(String pbrbmftfrNbmf,
                         jbvb.io.Rfbdfr rfbdfr) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

   /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn
    * <dodf>jbvb.mbti.BigDfdimbl</dodf> vbluf.
    * Tif drivfr donvfrts tiis to bn SQL <dodf>NUMERIC</dodf> vbluf wifn
    * it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftBigDfdimbl
    * @sindf 1.4
    */
   publid void sftBigDfdimbl(String pbrbmftfrNbmf, BigDfdimbl x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>String</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf
    * (dfpfnding on tif brgumfnt's
    * sizf rflbtivf to tif drivfr's limits on <dodf>VARCHAR</dodf> vblufs)
    * wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftString
    * @sindf 1.4
    */
   publid void sftString(String pbrbmftfrNbmf, String x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }



 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb brrby of bytfs.
    * Tif drivfr donvfrts tiis to bn SQL <dodf>VARBINARY</dodf> or
    * <dodf>LONGVARBINARY</dodf> (dfpfnding on tif brgumfnt's sizf rflbtivf
    * to tif drivfr's limits on <dodf>VARBINARY</dodf> vblufs) wifn it sfnds
    * it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftBytfs
    * @sindf 1.4
    */
   publid void sftBytfs(String pbrbmftfrNbmf, bytf x[]) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Timfstbmp</dodf> vbluf.
    * Tif drivfr
    * donvfrts tiis to bn SQL <dodf>TIMESTAMP</dodf> vbluf wifn it sfnds it to tif
    * dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftTimfstbmp
    * @sindf 1.4
    */
   publid void sftTimfstbmp(String pbrbmftfrNbmf, jbvb.sql.Timfstbmp x)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

    /**
    * Sfts tif dfsignbtfd pbrbmftfr to SQL <dodf>NULL</dodf>.
    *
    * <P><B>Notf:</B> You must spfdify tif pbrbmftfr's SQL typf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm sqlTypf tif SQL typf dodf dffinfd in <dodf>jbvb.sql.Typfs</dodf>
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sindf 1.4
    */
   publid void sftNull(String pbrbmftfrNbmf, int sqlTypf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to SQL <dodf>NULL</dodf>.
    * Tiis vfrsion of tif mftiod <dodf>sftNull</dodf> siould
    * bf usfd for usfr-dffinfd typfs bnd REF typf pbrbmftfrs.  Exbmplfs
    * of usfr-dffinfd typfs indludf: STRUCT, DISTINCT, JAVA_OBJECT, bnd
    * nbmfd brrby typfs.
    *
    * <P><B>Notf:</B> To bf portbblf, bpplidbtions must givf tif
    * SQL typf dodf bnd tif fully-qublififd SQL typf nbmf wifn spfdifying
    * b NULL usfr-dffinfd or REF pbrbmftfr.  In tif dbsf of b usfr-dffinfd typf
    * tif nbmf is tif typf nbmf of tif pbrbmftfr itsflf.  For b REF
    * pbrbmftfr, tif nbmf is tif typf nbmf of tif rfffrfndfd typf.  If
    * b JDBC drivfr dofs not nffd tif typf dodf or typf nbmf informbtion,
    * it mby ignorf it.
    *
    * Altiougi it is intfndfd for usfr-dffinfd bnd Rff pbrbmftfrs,
    * tiis mftiod mby bf usfd to sft b null pbrbmftfr of bny JDBC typf.
    * If tif pbrbmftfr dofs not ibvf b usfr-dffinfd or REF typf, tif givfn
    * typfNbmf is ignorfd.
    *
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm sqlTypf b vbluf from <dodf>jbvb.sql.Typfs</dodf>
    * @pbrbm typfNbmf tif fully-qublififd nbmf of bn SQL usfr-dffinfd typf;
    *        ignorfd if tif pbrbmftfr is not b usfr-dffinfd typf or
    *        SQL <dodf>REF</dodf> vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sindf 1.4
    */
   publid void sftNull (String pbrbmftfrNbmf, int sqlTypf, String typfNbmf)
       tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>boolfbn</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>BIT</dodf> or <dodf>BOOLEAN</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @sff #gftBoolfbn
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sindf 1.4
    */
   publid void sftBoolfbn(String pbrbmftfrNbmf, boolfbn x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }



 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>bytf</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>TINYINT</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftBytf
    * @sindf 1.4
    */
   publid void sftBytf(String pbrbmftfrNbmf, bytf x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>siort</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>SMALLINT</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftSiort
    * @sindf 1.4
    */
   publid void sftSiort(String pbrbmftfrNbmf, siort x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


   /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>int</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>INTEGER</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftInt
    * @sindf 1.4
    */
   publid void sftInt(String pbrbmftfrNbmf, int x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>long</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>BIGINT</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftLong
    * @sindf 1.4
    */
   publid void sftLong(String pbrbmftfrNbmf, long x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>flobt</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>FLOAT</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftFlobt
    * @sindf 1.4
    */
   publid void sftFlobt(String pbrbmftfrNbmf, flobt x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

 /**
    * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>doublf</dodf> vbluf.
    * Tif drivfr donvfrts tiis
    * to bn SQL <dodf>DOUBLE</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
    *
    * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
    * @pbrbm x tif pbrbmftfr vbluf
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
    * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
    * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
    * tiis mftiod
    * @sff #gftDoublf
    * @sindf 1.4
    */
   publid void sftDoublf(String pbrbmftfrNbmf, doublf x) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("jdbdrowsftimpl.ffbtnotsupp").toString());
   }

    /**
     * Tiis mftiod rf populbtfs tif rfsBundlf
     * during tif dfsfriblizbtion prodfss
     *
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {
        // Dffbult stbtf initiblizbtion ibppfns ifrf
        ois.dffbultRfbdObjfdt();
        // Initiblizbtion of trbnsifnt Rfs Bundlf ibppfns ifrf .
        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {}

    }

   stbtid finbl long sfriblVfrsionUID = -3591946023893483003L;

 //------------------------- JDBC 4.1 -----------------------------------

    publid <T> T gftObjfdt(int dolumnIndfx, Clbss<T> typf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("Not supportfd yft.");
    }

    publid <T> T gftObjfdt(String dolumnLbbfl, Clbss<T> typf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("Not supportfd yft.");
    }
}
