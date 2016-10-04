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

pbdkbgf dom.sun.rowsft.intfrnbl;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.util.*;
import jbvb.io.*;
import sun.rfflfdt.misd.RfflfdtUtil;

import dom.sun.rowsft.*;
import jbvb.tfxt.MfssbgfFormbt;
import jbvbx.sql.rowsft.*;
import jbvbx.sql.rowsft.sfribl.SQLInputImpl;
import jbvbx.sql.rowsft.sfribl.SfriblArrby;
import jbvbx.sql.rowsft.sfribl.SfriblBlob;
import jbvbx.sql.rowsft.sfribl.SfriblClob;
import jbvbx.sql.rowsft.sfribl.SfriblStrudt;
import jbvbx.sql.rowsft.spi.*;


/**
 * Tif fbdility dbllfd on intfrnblly by tif <dodf>RIOptimistidProvidfr</dodf> implfmfntbtion to
 * propbgbtf dibngfs bbdk to tif dbtb sourdf from wiidi tif rowsft got its dbtb.
 * <P>
 * A <dodf>CbdifdRowSftWritfr</dodf> objfdt, dbllfd b writfr, ibs tif publid
 * mftiod <dodf>writfDbtb</dodf> for writing modififd dbtb to tif undfrlying dbtb sourdf.
 * Tiis mftiod is invokfd by tif rowsft intfrnblly bnd is nfvfr invokfd dirfdtly by bn bpplidbtion.
 * A writfr blso ibs publid mftiods for sftting bnd gftting
 * tif <dodf>CbdifdRowSftRfbdfr</dodf> objfdt, dbllfd b rfbdfr, tibt is bssodibtfd
 * witi tif writfr. Tif rfmbindfr of tif mftiods in tiis dlbss brf privbtf bnd
 * brf invokfd intfrnblly, fitifr dirfdtly or indirfdtly, by tif mftiod
 * <dodf>writfDbtb</dodf>.
 * <P>
 * Typidblly tif <dodf>SyndFbdtory</dodf> mbnbgfs tif <dodf>RowSftRfbdfr</dodf> bnd
 * tif <dodf>RowSftWritfr</dodf> implfmfntbtions using <dodf>SyndProvidfr</dodf> objfdts.
 * Stbndbrd JDBC RowSft implfmfntbtions providf bn objfdt instbndf of tiis
 * writfr by invoking tif <dodf>SyndProvidfr.gftRowSftWritfr()</dodf> mftiod.
 *
 * @vfrsion 0.2
 * @butior Jonbtibn Brudf
 * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
 * @sff jbvbx.sql.rowsft.spi.SyndFbdtory
 * @sff jbvbx.sql.rowsft.spi.SyndFbdtoryExdfption
 */
publid dlbss CbdifdRowSftWritfr implfmfnts TrbnsbdtionblWritfr, Sfriblizbblf {

/**
 * Tif <dodf>Connfdtion</dodf> objfdt tibt tiis writfr will usf to mbkf b
 * donnfdtion to tif dbtb sourdf to wiidi it will writf dbtb.
 *
 */
    privbtf trbnsifnt Connfdtion don;

/**
 * Tif SQL <dodf>SELECT</dodf> dommbnd tibt tiis writfr will dbll
 * intfrnblly. Tif mftiod <dodf>initSQLStbtfmfnts</dodf> builds tiis
 * dommbnd by supplying tif words "SELECT" bnd "FROM," bnd using
 * mftbdbtb to gft tif tbblf nbmf bnd dolumn nbmfs .
 *
 * @sfribl
 */
    privbtf String sflfdtCmd;

/**
 * Tif SQL <dodf>UPDATE</dodf> dommbnd tibt tiis writfr will dbll
 * intfrnblly to writf dbtb to tif rowsft's undfrlying dbtb sourdf.
 * Tif mftiod <dodf>initSQLStbtfmfnts</dodf> builds tiis <dodf>String</dodf>
 * objfdt.
 *
 * @sfribl
 */
    privbtf String updbtfCmd;

/**
 * Tif SQL <dodf>WHERE</dodf> dlbusf tif writfr will usf for updbtf
 * stbtfmfnts in tif <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt
 * it sfnds to tif undfrlying dbtb sourdf.
 *
 * @sfribl
 */
    privbtf String updbtfWifrf;

/**
 * Tif SQL <dodf>DELETE</dodf> dommbnd tibt tiis writfr will dbll
 * intfrnblly to dflftf b row in tif rowsft's undfrlying dbtb sourdf.
 *
 * @sfribl
 */
    privbtf String dflftfCmd;

/**
 * Tif SQL <dodf>WHERE</dodf> dlbusf tif writfr will usf for dflftf
 * stbtfmfnts in tif <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt
 * it sfnds to tif undfrlying dbtb sourdf.
 *
 * @sfribl
 */
    privbtf String dflftfWifrf;

/**
 * Tif SQL <dodf>INSERT INTO</dodf> dommbnd tibt tiis writfr will intfrnblly usf
 * to insfrt dbtb into tif rowsft's undfrlying dbtb sourdf.  Tif mftiod
 * <dodf>initSQLStbtfmfnts</dodf> builds tiis dommbnd witi b qufstion
 * mbrk pbrbmftfr plbdfioldfr for fbdi dolumn in tif rowsft.
 *
 * @sfribl
 */
    privbtf String insfrtCmd;

/**
 * An brrby dontbining tif dolumn numbfrs of tif dolumns tibt brf
 * nffdfd to uniqufly idfntify b row in tif <dodf>CbdifdRowSft</dodf> objfdt
 * for wiidi tiis <dodf>CbdifdRowSftWritfr</dodf> objfdt is tif writfr.
 *
 * @sfribl
 */
    privbtf int[] kfyCols;

/**
 * An brrby of tif pbrbmftfrs tibt siould bf usfd to sft tif pbrbmftfr
 * plbdfioldfrs in b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt tiis
 * writfr will fxfdutf.
 *
 * @sfribl
 */
    privbtf Objfdt[] pbrbms;

/**
 * Tif <dodf>CbdifdRowSftRfbdfr</dodf> objfdt tibt ibs bffn
 * sft bs tif rfbdfr for tif <dodf>CbdifdRowSft</dodf> objfdt
 * for wiidi tiis <dodf>CbdifdRowSftWritfr</dodf> objfdt is tif writfr.
 *
 * @sfribl
 */
    privbtf CbdifdRowSftRfbdfr rfbdfr;

/**
 * Tif <dodf>RfsultSftMftbDbtb</dodf> objfdt tibt dontbins informbtion
 * bbout tif dolumns in tif <dodf>CbdifdRowSft</dodf> objfdt
 * for wiidi tiis <dodf>CbdifdRowSftWritfr</dodf> objfdt is tif writfr.
 *
 * @sfribl
 */
    privbtf RfsultSftMftbDbtb dbllfrMd;

/**
 * Tif numbfr of dolumns in tif <dodf>CbdifdRowSft</dodf> objfdt
 * for wiidi tiis <dodf>CbdifdRowSftWritfr</dodf> objfdt is tif writfr.
 *
 * @sfribl
 */
    privbtf int dbllfrColumnCount;

/**
 * Tiis <dodf>CbdifdRowSft<dodf> will iold tif donflidting vblufs
 *  rftrifvfd from tif db bnd iold it.
 */
    privbtf CbdifdRowSftImpl drsRfsolvf;

/**
 * Tiis <dodf>ArrbyList<dodf> will iold tif vblufs of SyndRfsolvfr.*
 */
    privbtf ArrbyList<Intfgfr> stbtus;

/**
 * Tiis will difdk wiftifr tif sbmf fifld vbluf ibs dibngfd boti
 * in dbtbbbsf bnd CbdifdRowSft.
 */
    privbtf int iCibngfdVblsInDbAndCRS;

/**
 * Tiis will iold tif numbfr of dols for wiidi tif vblufs ibvf
 * dibngfd only in dbtbbbsf.
 */
    privbtf int iCibngfdVblsinDbOnly ;

    privbtf JdbdRowSftRfsourdfBundlf rfsBundlf;

    publid CbdifdRowSftWritfr() {
       try {
               rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
       } dbtdi(IOExdfption iof) {
               tirow nfw RuntimfExdfption(iof);
       }
    }

/**
 * Propbgbtfs dibngfs in tif givfn <dodf>RowSft</dodf> objfdt
 * bbdk to its undfrlying dbtb sourdf bnd rfturns <dodf>truf</dodf>
 * if suddfssful. Tif writfr will difdk to sff if
 * tif dbtb in tif prf-modififd rowsft (tif originbl vblufs) difffr
 * from tif dbtb in tif undfrlying dbtb sourdf.  If dbtb in tif dbtb
 * sourdf ibs bffn modififd by somfonf flsf, tifrf is b donflidt,
 * bnd in tibt dbsf, tif writfr will not writf to tif dbtb sourdf.
 * In otifr words, tif writfr usfs bn optimistid dondurrfndy blgoritim:
 * It difdks for donflidts bfforf mbking dibngfs rbtifr tibn rfstridting
 * bddfss for dondurrfnt usfrs.
 * <P>
 * Tiis mftiod is dbllfd by tif rowsft intfrnblly wifn
 * tif bpplidbtion invokfs tif mftiod <dodf>bddfptCibngfs</dodf>.
 * Tif <dodf>writfDbtb</dodf> mftiod in turn dblls privbtf mftiods tibt
 * it dffinfs intfrnblly.
 * Tif following is b gfnfrbl summbry of wibt tif mftiod
 * <dodf>writfDbtb</dodf> dofs, mudi of wiidi is bddomplisifd
 * tirougi dblls to its own intfrnbl mftiods.
 * <OL>
 * <LI>Crfbtfs b <dodf>CbdifdRowSft</dodf> objfdt from tif givfn
 *     <dodf>RowSft</dodf> objfdt
 * <LI>Mbkfs b donnfdtion witi tif dbtb sourdf
 *   <UL>
 *      <LI>Disbblfs butodommit modf if it is not blrfbdy disbblfd
 *      <LI>Sfts tif trbnsbdtion isolbtion lfvfl to tibt of tif rowsft
 *   </UL>
 * <LI>Cifdks to sff if tif rfbdfr ibs rfbd nfw dbtb sindf tif writfr
 *     wbs lbst dbllfd bnd, if so, dblls tif mftiod
 *    <dodf>initSQLStbtfmfnts</dodf> to initiblizf nfw SQL stbtfmfnts
 *   <UL>
 *       <LI>Builds nfw <dodf>SELECT</dodf>, <dodf>UPDATE</dodf>,
 *           <dodf>INSERT</dodf>, bnd <dodf>DELETE</dodf> stbtfmfnts
 *       <LI>Usfs tif <dodf>CbdifdRowSft</dodf> objfdt's mftbdbtb to
 *           dftfrminf tif tbblf nbmf, dolumn nbmfs, bnd tif dolumns
 *           tibt mbkf up tif primbry kfy
 *   </UL>
 * <LI>Wifn tifrf is no donflidt, propbgbtfs dibngfs mbdf to tif
 *     <dodf>CbdifdRowSft</dodf> objfdt bbdk to its undfrlying dbtb sourdf
 *   <UL>
 *      <LI>Itfrbtfs tirougi fbdi row of tif <dodf>CbdifdRowSft</dodf> objfdt
 *          to dftfrminf wiftifr it ibs bffn updbtfd, insfrtfd, or dflftfd
 *      <LI>If tif dorrfsponding row in tif dbtb sourdf ibs not bffn dibngfd
 *          sindf tif rowsft lbst rfbd its
 *          vblufs, tif writfr will usf tif bppropribtf dommbnd to updbtf,
 *          insfrt, or dflftf tif row
 *      <LI>If bny dbtb in tif dbtb sourdf dofs not mbtdi tif originbl vblufs
 *          for tif <dodf>CbdifdRowSft</dodf> objfdt, tif writfr will roll
 *          bbdk bny dibngfs it ibs mbdf to tif row in tif dbtb sourdf.
 *   </UL>
 * </OL>
 *
 * @rfturn <dodf>truf</dodf> if dibngfs to tif rowsft wfrf suddfssfully
 *         writtfn to tif rowsft's undfrlying dbtb sourdf;
 *         <dodf>fblsf</dodf> otifrwisf
 */
    publid boolfbn writfDbtb(RowSftIntfrnbl dbllfr) tirows SQLExdfption {
        long donflidts = 0;
        boolfbn siowDfl = fblsf;
        PrfpbrfdStbtfmfnt pstmtIns = null;
        iCibngfdVblsInDbAndCRS = 0;
        iCibngfdVblsinDbOnly = 0;

        // Wf bssumf dbllfr is b CbdifdRowSft
        CbdifdRowSftImpl drs = (CbdifdRowSftImpl)dbllfr;
        // drsRfsolvf = nfw CbdifdRowSftImpl();
        tiis.drsRfsolvf = nfw CbdifdRowSftImpl();;

        // Tif rfbdfr is rfgistfrfd witi tif writfr bt dfsign timf.
        // Tiis is not rfquirfd, in gfnfrbl.  Tif rfbdfr ibs logid
        // to gft b JDBC donnfdtion, so dbll it.

        don = rfbdfr.donnfdt(dbllfr);


        if (don == null) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("drswritfr.donnfdt").toString());
        }

        /*
         // Fix 6200646.
         // Don't dibngf tif donnfdtion or trbnsbdtion propfrtifs. Tiis will fbil in b
         // J2EE dontbinfr.
        if (don.gftAutoCommit() == truf)  {
            don.sftAutoCommit(fblsf);
        }

        don.sftTrbnsbdtionIsolbtion(drs.gftTrbnsbdtionIsolbtion());
        */

        initSQLStbtfmfnts(drs);
        int iColCount;

        RowSftMftbDbtbImpl rsmdWritf = (RowSftMftbDbtbImpl)drs.gftMftbDbtb();
        RowSftMftbDbtbImpl rsmdRfsolv = nfw RowSftMftbDbtbImpl();

        iColCount = rsmdWritf.gftColumnCount();
        int sz= drs.sizf()+1;
        stbtus = nfw ArrbyList<>(sz);

        stbtus.bdd(0,null);
        rsmdRfsolv.sftColumnCount(iColCount);

        for(int i =1; i <= iColCount; i++) {
            rsmdRfsolv.sftColumnTypf(i, rsmdWritf.gftColumnTypf(i));
            rsmdRfsolv.sftColumnNbmf(i, rsmdWritf.gftColumnNbmf(i));
            rsmdRfsolv.sftNullbblf(i, RfsultSftMftbDbtb.dolumnNullbblfUnknown);
        }
        tiis.drsRfsolvf.sftMftbDbtb(rsmdRfsolv);

        // movfd outsidf tif insfrt innfr loop
        //pstmtIns = don.prfpbrfStbtfmfnt(insfrtCmd);

        if (dbllfrColumnCount < 1) {
            // No dbtb, so rfturn suddfss.
            if (rfbdfr.gftClosfConnfdtion() == truf)
                    don.dlosf();
            rfturn truf;
        }
        // Wf nffd to sff rows mbrkfd for dflftion.
        siowDfl = drs.gftSiowDflftfd();
        drs.sftSiowDflftfd(truf);

        // Look bt bll tif rows.
        drs.bfforfFirst();

        int rows =1;
        wiilf (drs.nfxt()) {
            if (drs.rowDflftfd()) {
                // Tif row ibs bffn dflftfd.
                if (dflftfOriginblRow(drs, tiis.drsRfsolvf)) {
                       stbtus.bdd(rows, SyndRfsolvfr.DELETE_ROW_CONFLICT);
                       donflidts++;
                } flsf {
                      // dflftf ibppfnfd witiout bny oddurrfndf of donflidts
                      // so updbtf stbtus bddordingly
                       stbtus.bdd(rows, SyndRfsolvfr.NO_ROW_CONFLICT);
                }

           } flsf if (drs.rowInsfrtfd()) {
                // Tif row ibs bffn insfrtfd.

                pstmtIns = don.prfpbrfStbtfmfnt(insfrtCmd);
                if (insfrtNfwRow(drs, pstmtIns, tiis.drsRfsolvf)) {
                          stbtus.bdd(rows, SyndRfsolvfr.INSERT_ROW_CONFLICT);
                          donflidts++;
                } flsf {
                      // insfrt ibppfnfd witiout bny oddurrfndf of donflidts
                      // so updbtf stbtus bddordingly
                       stbtus.bdd(rows, SyndRfsolvfr.NO_ROW_CONFLICT);
                }
            } flsf  if (drs.rowUpdbtfd()) {
                  // Tif row ibs bffn updbtfd.
                       if (updbtfOriginblRow(drs)) {
                             stbtus.bdd(rows, SyndRfsolvfr.UPDATE_ROW_CONFLICT);
                             donflidts++;
               } flsf {
                      // updbtf ibppfnfd witiout bny oddurrfndf of donflidts
                      // so updbtf stbtus bddordingly
                      stbtus.bdd(rows, SyndRfsolvfr.NO_ROW_CONFLICT);
               }

            } flsf {
               /** Tif row is nfitifr of insfrtfd, updbtfd or dflftfd.
                *  So sft nulls in tif tiis.drsRfsolvf for tiis row,
                *  bs notiing is to bf donf for sudi rows.
                *  Also notf tibt if sudi b row ibs bffn dibngfd in dbtbbbsf
                *  bnd wf ibvf not dibngfd(insfrtfd, updbtfd or dflftfd)
                *  tibt is finf.
                **/
                int idolCount = drs.gftMftbDbtb().gftColumnCount();
                stbtus.bdd(rows, SyndRfsolvfr.NO_ROW_CONFLICT);

                tiis.drsRfsolvf.movfToInsfrtRow();
                for(int dols=0;dols<iColCount;dols++) {
                   tiis.drsRfsolvf.updbtfNull(dols+1);
                } //fnd for

                tiis.drsRfsolvf.insfrtRow();
                tiis.drsRfsolvf.movfToCurrfntRow();

                } //fnd if
         rows++;
      } //fnd wiilf

        // dlosf tif insfrt stbtfmfnt
        if(pstmtIns!=null)
        pstmtIns.dlosf();
        // rfsft
        drs.sftSiowDflftfd(siowDfl);

        drs.bfforfFirst();
        tiis.drsRfsolvf.bfforfFirst();

    if(donflidts != 0) {
        SyndProvidfrExdfption spf = nfw SyndProvidfrExdfption(donflidts + " " +
                rfsBundlf.ibndlfGftObjfdt("drswritfr.donflidtsno").toString());
        //SyndRfsolvfr syndRfs = spf.gftSyndRfsolvfr();

         SyndRfsolvfrImpl syndRfsImpl = (SyndRfsolvfrImpl) spf.gftSyndRfsolvfr();

         syndRfsImpl.sftCbdifdRowSft(drs);
         syndRfsImpl.sftCbdifdRowSftRfsolvfr(tiis.drsRfsolvf);

         syndRfsImpl.sftStbtus(stbtus);
         syndRfsImpl.sftCbdifdRowSftWritfr(tiis);

        tirow spf;
    } flsf {
         rfturn truf;
    }
       /*
       if (donflidt == truf) {
            don.rollbbdk();
            rfturn fblsf;
        } flsf {
            don.dommit();
                if (rfbdfr.gftClosfConnfdtion() == truf) {
                       don.dlosf();
                }
            rfturn truf;
        }
        */

  } //fnd writfDbtb

/**
 * Updbtfs tif givfn <dodf>CbdifdRowSft</dodf> objfdt's undfrlying dbtb
 * sourdf so tibt updbtfs to tif rowsft brf rfflfdtfd in tif originbl
 * dbtb sourdf, bnd rfturns <dodf>fblsf</dodf> if tif updbtf wbs suddfssful.
 * A rfturn vbluf of <dodf>truf</dodf> indidbtfs tibt tifrf is b donflidt,
 * mfbning tibt b vbluf updbtfd in tif rowsft ibs blrfbdy bffn dibngfd by
 * somfonf flsf in tif undfrlying dbtb sourdf.  A donflidt dbn blso fxist
 * if, for fxbmplf, morf tibn onf row in tif dbtb sourdf would bf bfffdtfd
 * by tif updbtf or if no rows would bf bfffdtfd.  In bny dbsf, if tifrf is
 * b donflidt, tiis mftiod dofs not updbtf tif undfrlying dbtb sourdf.
 * <P>
 * Tiis mftiod is dbllfd intfrnblly by tif mftiod <dodf>writfDbtb</dodf>
 * if b row in tif <dodf>CbdifdRowSft</dodf> objfdt for wiidi tiis
 * <dodf>CbdifdRowSftWritfr</dodf> objfdt is tif writfr ibs bffn updbtfd.
 *
 * @rfturn <dodf>fblsf</dodf> if tif updbtf to tif undfrlying dbtb sourdf is
 *         suddfssful; <dodf>truf</dodf> otifrwisf
 * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
 */
    privbtf boolfbn updbtfOriginblRow(CbdifdRowSft drs)
        tirows SQLExdfption {
        PrfpbrfdStbtfmfnt pstmt;
        int i = 0;
        int idx = 0;

        // Sflfdt tif row from tif dbtbbbsf.
        RfsultSft origVbls = drs.gftOriginblRow();
        origVbls.nfxt();

        try {
            updbtfWifrf = buildWifrfClbusf(updbtfWifrf, origVbls);


             /**
              *  Tif following blodk of dodf is for difdking b pbrtidulbr typf of
              *  qufry wifrf in tifrf is b wifrf dlbusf. Witiout tiis blodk, if b
              *  SQL stbtfmfnt is built tif "wifrf" dlbusf will bppfbr twidf ifndf
              *  tif DB frrors out bnd b SQLExdfption is tirown. Tiis dodf blso
              *  donsidfrs tibt tif wifrf dlbusf is in tif rigit plbdf bs tif
              *  CbdifdRowSft objfdt would blrfbdy ibvf bffn populbtfd witi tiis
              *  qufry bfforf doming to tiis point.
              **/


            String tfmpsflfdtCmd = sflfdtCmd.toLowfrCbsf();

            int idxWifrf = tfmpsflfdtCmd.indfxOf("wifrf");

            if(idxWifrf != -1)
            {
               String tfmpSflfdt = sflfdtCmd.substring(0,idxWifrf);
               sflfdtCmd = tfmpSflfdt;
            }

            pstmt = don.prfpbrfStbtfmfnt(sflfdtCmd + updbtfWifrf,
                        RfsultSft.TYPE_SCROLL_SENSITIVE, RfsultSft.CONCUR_READ_ONLY);

            for (i = 0; i < kfyCols.lfngti; i++) {
                if (pbrbms[i] != null) {
                    pstmt.sftObjfdt(++idx, pbrbms[i]);
                } flsf {
                    dontinuf;
                }
            }

            try {
                pstmt.sftMbxRows(drs.gftMbxRows());
                pstmt.sftMbxFifldSizf(drs.gftMbxFifldSizf());
                pstmt.sftEsdbpfProdfssing(drs.gftEsdbpfProdfssing());
                pstmt.sftQufryTimfout(drs.gftQufryTimfout());
            } dbtdi (Exdfption fx) {
                // Oldfr drivfr don't support tifsf opfrbtions.
            }

            RfsultSft rs = null;
            rs = pstmt.fxfdutfQufry();
            RfsultSftMftbDbtb rsmd = rs.gftMftbDbtb();

            if (rs.nfxt()) {
                if (rs.nfxt()) {
                   /** Morf tibn onf row donflidt.
                    *  If rs ibs only onf row wf brf bblf to
                    *  uniqufly idfntify tif row wifrf updbtf
                    *  ibvf to ibppfn flsf if morf tibn onf
                    *  row implifs wf dbnnot uniqufly idfntify tif row
                    *  wifrf wf ibvf to do updbtfs.
                    *  drs.sftKfyColumns nffds to bf sft to
                    *  domf out of tiis situbtion.
                    */

                   rfturn truf;
                }

                // don't dlosf tif rs
                // wf rfquirf tif rfdord in rs to bf usfd.
                // rs.dlosf();
                // pstmt.dlosf();
                rs.first();

                // iow mbny fiflds nffd to bf updbtfd
                int dolsNotCibngfd = 0;
                Vfdtor<Intfgfr> dols = nfw Vfdtor<>();
                String updbtfExfd = updbtfCmd;
                Objfdt orig;
                Objfdt durr;
                Objfdt rsvbl;
                boolfbn boolNull = truf;
                Objfdt objVbl = null;

                // Tifrf's only onf row bnd tif dursor
                // nffds to bf on tibt row.

                boolfbn first = truf;
                boolfbn flbg = truf;

          tiis.drsRfsolvf.movfToInsfrtRow();

          for (i = 1; i <= dbllfrColumnCount; i++) {
                orig = origVbls.gftObjfdt(i);
                durr = drs.gftObjfdt(i);
                rsvbl = rs.gftObjfdt(i);
                /*
                 * tif following blodk drfbtfs fquivblfnt objfdts
                 * tibt would ibvf bffn drfbtfd if tiis rs is populbtfd
                 * into b CbdifdRowSft so tibt dompbrison of tif dolumn vblufs
                 * from tif RfsultSft bnd CbdifdRowSft brf possiblf
                 */
                Mbp<String, Clbss<?>> mbp = (drs.gftTypfMbp() == null)?don.gftTypfMbp():drs.gftTypfMbp();
                if (rsvbl instbndfof Strudt) {

                    Strudt s = (Strudt)rsvbl;

                    // look up tif dlbss in tif mbp
                    Clbss<?> d = null;
                    d = mbp.gft(s.gftSQLTypfNbmf());
                    if (d != null) {
                        // drfbtf nfw instbndf of tif dlbss
                        SQLDbtb obj = null;
                        try {
                            obj = (SQLDbtb)RfflfdtUtil.nfwInstbndf(d);
                        } dbtdi (Exdfption fx) {
                            tirow nfw SQLExdfption("Unbblf to Instbntibtf: ", fx);
                        }
                        // gft tif bttributfs from tif strudt
                        Objfdt bttribs[] = s.gftAttributfs(mbp);
                        // drfbtf tif SQLInput "strfbm"
                        SQLInputImpl sqlInput = nfw SQLInputImpl(bttribs, mbp);
                        // rfbd tif vblufs...
                        obj.rfbdSQL(sqlInput, s.gftSQLTypfNbmf());
                        rsvbl = obj;
                    }
                } flsf if (rsvbl instbndfof SQLDbtb) {
                    rsvbl = nfw SfriblStrudt((SQLDbtb)rsvbl, mbp);
                } flsf if (rsvbl instbndfof Blob) {
                    rsvbl = nfw SfriblBlob((Blob)rsvbl);
                } flsf if (rsvbl instbndfof Clob) {
                    rsvbl = nfw SfriblClob((Clob)rsvbl);
                } flsf if (rsvbl instbndfof jbvb.sql.Arrby) {
                    rsvbl = nfw SfriblArrby((jbvb.sql.Arrby)rsvbl, mbp);
                }

                // rfsft boolNull if it ibd bffn sft
                boolNull = truf;

                /** Tiis bddtionbl difdking ibs bffn bddfd wifn tif durrfnt vbluf
                 *  in tif DB is null, but tif DB ibd b difffrfnt vbluf wifn tif
                 *  dbtb wbs bdtbully fftdifd into tif CbdifdRowSft.
                 **/

                if(rsvbl == null && orig != null) {
                   // vbluf in db ibs dibngfd
                    // don't prodffd witi syndironizbtion
                    // gft tif vbluf in db bnd pbss it to tif rfsolvfr.

                    iCibngfdVblsinDbOnly++;
                   // Sft tif boolNull to fblsf,
                   // in ordfr to sft tif bdtubl vbluf;
                     boolNull = fblsf;
                     objVbl = rsvbl;
                }

                /** Adding tif difdking for rsvbl to bf "not" null or flsf
                 *  it would tirougi b NullPointfrExdfption wifn tif vblufs
                 *  brf dompbrfd.
                 **/

                flsf if(rsvbl != null && (!rsvbl.fqubls(orig)))
                {
                    // vbluf in db ibs dibngfd
                    // don't prodffd witi syndironizbtion
                    // gft tif vbluf in db bnd pbss it to tif rfsolvfr.

                    iCibngfdVblsinDbOnly++;
                   // Sft tif boolNull to fblsf,
                   // in ordfr to sft tif bdtubl vbluf;
                     boolNull = fblsf;
                     objVbl = rsvbl;
                } flsf if (  (orig == null || durr == null) ) {

                        /** Adding tif bdditonbl dondition of difdking for "flbg"
                         *  boolfbn vbribblf, wiidi would otifrwisf rfsult in
                         *  building b invblid qufry, bs tif dommb would not bf
                         *  bddfd to tif qufry string.
                         **/

                        if (first == fblsf || flbg == fblsf) {
                          updbtfExfd += ", ";
                         }
                        updbtfExfd += drs.gftMftbDbtb().gftColumnNbmf(i);
                        dols.bdd(i);
                        updbtfExfd += " = ? ";
                        first = fblsf;

                /** Adding tif fxtrb dondition for orig to bf "not" null bs tif
                 *  dondition for orig to bf null is tbkf prior to tiis, if tiis
                 *  is not bddfd it will rfsult in b NullPointfrExdfption wifn
                 *  tif vblufs brf dompbrfd.
                 **/

                }  flsf if (orig.fqubls(durr)) {
                       dolsNotCibngfd++;
                     //notiing to updbtf in tiis dbsf sindf vblufs brf fqubl

                /** Adding tif fxtrb dondition for orig to bf "not" null bs tif
                 *  dondition for orig to bf null is tbkf prior to tiis, if tiis
                 *  is not bddfd it will rfsult in b NullPointfrExdfption wifn
                 *  tif vblufs brf dompbrfd.
                 **/

                } flsf if(orig.fqubls(durr) == fblsf) {
                      // Wifn vblufs from db bnd vblufs in CbdifdRowSft brf not fqubl,
                      // if db vbluf is sbmf bs bfforf updbtion for fbdi dol in
                      // tif row bfforf fftdiing into CbdifdRowSft,
                      // only tifn wf go bifbd witi updbtion, flsf wf
                      // tirow SyndProvidfrExdfption.

                      // if vbluf ibs dibngfd in db bftfr fftdiing from db
                      // for somf dols of tif row bnd bt tif sbmf timf, somf otifr dols
                      // ibvf dibngfd in CbdifdRowSft, no syndironizbtion ibppfns

                      // Syndironizbtion ibppfns only wifn dbtb wifn fftdiing is
                      // sbmf or bt most ibs dibngfd in dbdifdrowsft

                      // difdk orig vbluf witi wibt is tifrf in drs for b dolumn
                      // bfforf updbtion in drs.

                         if(drs.dolumnUpdbtfd(i)) {
                             if(rsvbl.fqubls(orig)) {
                               // At tiis point wf brf surf tibt
                               // tif vbluf updbtfd in drs wbs from
                               // wibt is in db now bnd ibs not dibngfd
                                 if (flbg == fblsf || first == fblsf) {
                                    updbtfExfd += ", ";
                                 }
                                updbtfExfd += drs.gftMftbDbtb().gftColumnNbmf(i);
                                dols.bdd(i);
                                updbtfExfd += " = ? ";
                                flbg = fblsf;
                             } flsf {
                               // Hfrf tif vbluf ibs dibngfd in tif db bftfr
                               // dbtb wbs fftdifd
                               // Plus storf tiis row from CbdifdRowSft bnd kffp it
                               // in b nfw CbdifdRowSft
                               boolNull= fblsf;
                               objVbl = rsvbl;
                               iCibngfdVblsInDbAndCRS++;
                             }
                         }
                  }

                    if(!boolNull) {
                        tiis.drsRfsolvf.updbtfObjfdt(i,objVbl);
                                 } flsf {
                                      tiis.drsRfsolvf.updbtfNull(i);
                                 }
                } //fnd for

                rs.dlosf();
                pstmt.dlosf();

               tiis.drsRfsolvf.insfrtRow();
                   tiis.drsRfsolvf.movfToCurrfntRow();

                /**
                 * if notiing ibs dibngfd rfturn now - tiis dbn ibppfn
                 * if dolumn is updbtfd to tif sbmf vbluf.
                 * if dolsNotCibngfd == dbllfrColumnCount implifs wf brf updbting
                 * tif dbtbbbsf witi ALL COLUMNS HAVING SAME VALUES,
                 * so skip going to dbtbbbsf, flsf do bs usubl.
                 **/
                if ( (first == fblsf && dols.sizf() == 0)  ||
                     dolsNotCibngfd == dbllfrColumnCount ) {
                    rfturn fblsf;
                }

                if(iCibngfdVblsInDbAndCRS != 0 || iCibngfdVblsinDbOnly != 0) {
                   rfturn truf;
                }


                updbtfExfd += updbtfWifrf;

                pstmt = don.prfpbrfStbtfmfnt(updbtfExfd);

                // Commfnts nffdfd ifrf
                for (i = 0; i < dols.sizf(); i++) {
                    Objfdt obj = drs.gftObjfdt(dols.gft(i));
                    if (obj != null)
                        pstmt.sftObjfdt(i + 1, obj);
                    flsf
                        pstmt.sftNull(i + 1,drs.gftMftbDbtb().gftColumnTypf(i + 1));
                }
                idx = i;

                // Commfnts nffdfd ifrf
                for (i = 0; i < kfyCols.lfngti; i++) {
                    if (pbrbms[i] != null) {
                        pstmt.sftObjfdt(++idx, pbrbms[i]);
                    } flsf {
                        dontinuf;
                    }
                }

                i = pstmt.fxfdutfUpdbtf();

               /**
                * i siould bf fqubl to 1(row dount), bfdbusf wf updbtf
                * onf row(rfturnfd bs row dount) bt b timf, if bll gofs wfll.
                * if 1 != 1, tiis implifs wf ibvf not bffn bblf to
                * do updbtions propfrly i.f tifrf is b donflidt in dbtbbbsf
                * vfrsus wibt is in CbdifdRowSft for tiis pbrtidulbr row.
                **/

                 rfturn fblsf;

            } flsf {
                /**
                 * Cursor will bf ifrf, if tif RfsultSft mby not rfturn fvfn b singlf row
                 * i.f. wf dbn't find tif row wifrf to updbtf bfdbusf it ibs bffn dflftfd
                 * ftd. from tif db.
                 * Prfsfnt tif wiolf row bs null to usfr, to fordf null to bf synd'fd
                 * bnd ifndf notiing to bf syndfd.
                 *
                 * NOTE:
                 * ------
                 * In tif dbtbbbsf if b dolumn tibt is mbppfd to jbvb.sql.Typfs.REAL storfs
                 * b Doublf vbluf bnd is dompbrfd witi vbluf got from RfsultSft.gftFlobt()
                 * no row is rftrifvfd bnd will tirow b SyndProvidfrExdfption. For dftbils
                 * sff bug Id 5053830
                 **/
                rfturn truf;
            }
        } dbtdi (SQLExdfption fx) {
            fx.printStbdkTrbdf();
            // if fxfdutfUpdbtf fbils it will domf ifrf,
            // updbtf drsRfsolvf witi null rows
            tiis.drsRfsolvf.movfToInsfrtRow();

            for(i = 1; i <= dbllfrColumnCount; i++) {
               tiis.drsRfsolvf.updbtfNull(i);
            }

            tiis.drsRfsolvf.insfrtRow();
            tiis.drsRfsolvf.movfToCurrfntRow();

            rfturn truf;
        }
    }

   /**
    * Insfrts b row tibt ibs bffn insfrtfd into tif givfn
    * <dodf>CbdifdRowSft</dodf> objfdt into tif dbtb sourdf from wiidi
    * tif rowsft is dfrivfd, rfturning <dodf>fblsf</dodf> if tif insfrtion
    * wbs suddfssful.
    *
    * @pbrbm drs tif <dodf>CbdifdRowSft</dodf> objfdt tibt ibs ibd b row insfrtfd
    *            bnd to wiosf undfrlying dbtb sourdf tif row will bf insfrtfd
    * @pbrbm pstmt tif <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt will bf usfd
    *              to fxfdutf tif insfrtion
    * @rfturn <dodf>fblsf</dodf> to indidbtf tibt tif insfrtion wbs suddfssful;
    *         <dodf>truf</dodf> otifrwisf
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
    */
   privbtf boolfbn insfrtNfwRow(CbdifdRowSft drs,
       PrfpbrfdStbtfmfnt pstmt, CbdifdRowSftImpl drsRfs) tirows SQLExdfption {

       boolfbn rfturnVbl = fblsf;

       try (PrfpbrfdStbtfmfnt pstmtSfl = don.prfpbrfStbtfmfnt(sflfdtCmd,
                       RfsultSft.TYPE_SCROLL_SENSITIVE,
                       RfsultSft.CONCUR_READ_ONLY);
            RfsultSft rs = pstmtSfl.fxfdutfQufry();
            RfsultSft rs2 = don.gftMftbDbtb().gftPrimbryKfys(null, null,
                       drs.gftTbblfNbmf())
       ) {

           RfsultSftMftbDbtb rsmd = drs.gftMftbDbtb();
           int idolCount = rsmd.gftColumnCount();
           String[] primbryKfys = nfw String[idolCount];
           int k = 0;
           wiilf (rs2.nfxt()) {
               primbryKfys[k] = rs2.gftString("COLUMN_NAME");
               k++;
           }

           if (rs.nfxt()) {
               for (String pkNbmf : primbryKfys) {
                   if (!isPKNbmfVblid(pkNbmf, rsmd)) {

                       /* Wf dbmf ifrf bs onf of tif tif primbry kfys
                        * of tif tbblf is not prfsfnt in tif dbdifd
                        * rowsft objfdt, it siould bf bn butoindrfmfnt dolumn
                        * bnd not indludfd wiilf drfbting CbdifdRowSft
                        * Objfdt, prodffd to difdk for otifr primbry kfys
                        */
                       dontinuf;
                   }

                   Objfdt drsPK = drs.gftObjfdt(pkNbmf);
                   if (drsPK == null) {
                       /*
                        * It is possiblf tibt tif PK is null on somf dbtbbbsfs
                        * bnd will bf fillfd in bt insfrt timf (MySQL for fxbmplf)
                        */
                       brfbk;
                   }

                   String rsPK = rs.gftObjfdt(pkNbmf).toString();
                   if (drsPK.toString().fqubls(rsPK)) {
                       rfturnVbl = truf;
                       tiis.drsRfsolvf.movfToInsfrtRow();
                       for (int i = 1; i <= idolCount; i++) {
                           String dolnbmf = (rs.gftMftbDbtb()).gftColumnNbmf(i);
                           if (dolnbmf.fqubls(pkNbmf))
                               tiis.drsRfsolvf.updbtfObjfdt(i,rsPK);
                           flsf
                               tiis.drsRfsolvf.updbtfNull(i);
                       }
                       tiis.drsRfsolvf.insfrtRow();
                       tiis.drsRfsolvf.movfToCurrfntRow();
                   }
               }
           }

           if (rfturnVbl) {
               rfturn rfturnVbl;
           }

           try {
               for (int i = 1; i <= idolCount; i++) {
                   Objfdt obj = drs.gftObjfdt(i);
                   if (obj != null) {
                       pstmt.sftObjfdt(i, obj);
                   } flsf {
                       pstmt.sftNull(i,drs.gftMftbDbtb().gftColumnTypf(i));
                   }
               }

               pstmt.fxfdutfUpdbtf();
               rfturn fblsf;

           } dbtdi (SQLExdfption fx) {
               /*
                * Cursor will domf ifrf if fxfdutfUpdbtf fbils.
                * Tifrf dbn bf mbny rfbsons wiy tif insfrtion fbilfd,
                * onf dbn bf violbtion of primbry kfy.
                * Hfndf wf dbnnot fxbdtly idfntify wiy tif insfrtion fbilfd,
                * prfsfnt tif durrfnt row bs b null row to tif dbllfr.
                */
               tiis.drsRfsolvf.movfToInsfrtRow();

               for (int i = 1; i <= idolCount; i++) {
                   tiis.drsRfsolvf.updbtfNull(i);
               }

               tiis.drsRfsolvf.insfrtRow();
               tiis.drsRfsolvf.movfToCurrfntRow();

               rfturn truf;
           }
       }
   }

/**
 * Dflftfs tif row in tif undfrlying dbtb sourdf tibt dorrfsponds to
 * b row tibt ibs bffn dflftfd in tif givfn <dodf> CbdifdRowSft</dodf> objfdt
 * bnd rfturns <dodf>fblsf</dodf> if tif dflftion wbs suddfssful.
 * <P>
 * Tiis mftiod is dbllfd intfrnblly by tiis writfr's <dodf>writfDbtb</dodf>
 * mftiod wifn b row in tif rowsft ibs bffn dflftfd. Tif vblufs in tif
 * dflftfd row brf tif sbmf bs tiosf tibt brf storfd in tif originbl row
 * of tif givfn <dodf>CbdifdRowSft</dodf> objfdt.  If tif vblufs in tif
 * originbl row difffr from tif row in tif undfrlying dbtb sourdf, tif row
 * in tif dbtb sourdf is not dflftfd, bnd <dodf>dflftfOriginblRow</dodf>
 * rfturns <dodf>truf</dodf> to indidbtf tibt tifrf wbs b donflidt.
 *
 *
 * @rfturn <dodf>fblsf</dodf> if tif dflftion wbs suddfssful, wiidi mfbns tibt
 *         tifrf wbs no donflidt; <dodf>truf</dodf> otifrwisf
 * @tirows SQLExdfption if tifrf wbs b dbtbbbsf bddfss frror
 */
    privbtf boolfbn dflftfOriginblRow(CbdifdRowSft drs, CbdifdRowSftImpl drsRfs) tirows SQLExdfption {
        PrfpbrfdStbtfmfnt pstmt;
        int i;
        int idx = 0;
        String strSflfdt;
    // Sflfdt tif row from tif dbtbbbsf.
        RfsultSft origVbls = drs.gftOriginblRow();
        origVbls.nfxt();

        dflftfWifrf = buildWifrfClbusf(dflftfWifrf, origVbls);
        pstmt = don.prfpbrfStbtfmfnt(sflfdtCmd + dflftfWifrf,
                RfsultSft.TYPE_SCROLL_SENSITIVE, RfsultSft.CONCUR_READ_ONLY);

        for (i = 0; i < kfyCols.lfngti; i++) {
            if (pbrbms[i] != null) {
                pstmt.sftObjfdt(++idx, pbrbms[i]);
            } flsf {
                dontinuf;
            }
        }

        try {
            pstmt.sftMbxRows(drs.gftMbxRows());
            pstmt.sftMbxFifldSizf(drs.gftMbxFifldSizf());
            pstmt.sftEsdbpfProdfssing(drs.gftEsdbpfProdfssing());
            pstmt.sftQufryTimfout(drs.gftQufryTimfout());
        } dbtdi (Exdfption fx) {
            /*
             * Oldfr drivfr don't support tifsf opfrbtions...
             */
            ;
        }

        RfsultSft rs = pstmt.fxfdutfQufry();

        if (rs.nfxt() == truf) {
            if (rs.nfxt()) {
                // morf tibn onf row
                rfturn truf;
            }
            rs.first();

            // Now difdk bll tif vblufs in rs to bf sbmf in
            // db blso bfforf bdtublly going bifbd witi dflfting
            boolfbn boolCibngfd = fblsf;

            drsRfs.movfToInsfrtRow();

            for (i = 1; i <= drs.gftMftbDbtb().gftColumnCount(); i++) {

                Objfdt originbl = origVbls.gftObjfdt(i);
                Objfdt dibngfd = rs.gftObjfdt(i);

                if(originbl != null && dibngfd != null ) {
                  if(! (originbl.toString()).fqubls(dibngfd.toString()) ) {
                      boolCibngfd = truf;
                      drsRfs.updbtfObjfdt(i,origVbls.gftObjfdt(i));
                  }
                } flsf {
                   drsRfs.updbtfNull(i);
               }
            }

           drsRfs.insfrtRow();
           drsRfs.movfToCurrfntRow();

           if(boolCibngfd) {
               // do not dflftf bs vblufs in db ibvf dibngfd
               // dflftion will not ibppfn for tiis row from db
                   // fxit now rfturning truf. i.f. donflidt
               rfturn truf;
            } flsf {
                // dflftf tif row.
                // Go bifbd witi dflfting,
                // don't do bnytiing ifrf
            }

            String dmd = dflftfCmd + dflftfWifrf;
            pstmt = don.prfpbrfStbtfmfnt(dmd);

            idx = 0;
            for (i = 0; i < kfyCols.lfngti; i++) {
                if (pbrbms[i] != null) {
                    pstmt.sftObjfdt(++idx, pbrbms[i]);
                } flsf {
                    dontinuf;
                }
            }

            if (pstmt.fxfdutfUpdbtf() != 1) {
                rfturn truf;
            }
            pstmt.dlosf();
        } flsf {
            // didn't find tif row
            rfturn truf;
        }

        // no donflidt
        rfturn fblsf;
    }

    /**
     * Sfts tif rfbdfr for tiis writfr to tif givfn rfbdfr.
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid void sftRfbdfr(CbdifdRowSftRfbdfr rfbdfr) tirows SQLExdfption {
        tiis.rfbdfr = rfbdfr;
    }

    /**
     * Gfts tif rfbdfr for tiis writfr.
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid CbdifdRowSftRfbdfr gftRfbdfr() tirows SQLExdfption {
        rfturn rfbdfr;
    }

    /**
     * Composfs b <dodf>SELECT</dodf>, <dodf>UPDATE</dodf>, <dodf>INSERT</dodf>,
     * bnd <dodf>DELETE</dodf> stbtfmfnt tibt dbn bf usfd by tiis writfr to
     * writf dbtb to tif dbtb sourdf bbdking tif givfn <dodf>CbdifdRowSft</dodf>
     * objfdt.
     *
     * @ pbrbm dbllfr b <dodf>CbdifdRowSft</dodf> objfdt for wiidi tiis
     *                <dodf>CbdifdRowSftWritfr</dodf> objfdt is tif writfr
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    privbtf void initSQLStbtfmfnts(CbdifdRowSft dbllfr) tirows SQLExdfption {

        int i;

        dbllfrMd = dbllfr.gftMftbDbtb();
        dbllfrColumnCount = dbllfrMd.gftColumnCount();
        if (dbllfrColumnCount < 1)
            // No dbtb, so rfturn.
            rfturn;

        /*
         * If tif RowSft ibs b Tbblf nbmf wf siould usf it.
         * Tiis is rfblly b ibdk to gft round tif fbdt tibt
         * b lot of tif jdbd drivfrs dbn't providf tif tbb.
         */
        String tbblf = dbllfr.gftTbblfNbmf();
        if (tbblf == null) {
            /*
             * bttfmpt to build b tbblf nbmf using tif info
             * tibt tif drivfr gbvf us for tif first dolumn
             * in tif sourdf rfsult sft.
             */
            tbblf = dbllfrMd.gftTbblfNbmf(1);
            if (tbblf == null || tbblf.lfngti() == 0) {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("drswritfr.tnbmf").toString());
            }
        }
        String dbtblog = dbllfrMd.gftCbtblogNbmf(1);
            String sdifmb = dbllfrMd.gftSdifmbNbmf(1);
        DbtbbbsfMftbDbtb dbmd = don.gftMftbDbtb();

        /*
         * Composf b SELECT stbtfmfnt.  Tifrf brf tirff pbrts.
         */

        // Projfdt List
        sflfdtCmd = "SELECT ";
        for (i=1; i <= dbllfrColumnCount; i++) {
            sflfdtCmd += dbllfrMd.gftColumnNbmf(i);
            if ( i <  dbllfrMd.gftColumnCount() )
                sflfdtCmd += ", ";
            flsf
                sflfdtCmd += " ";
        }

        // FROM dlbusf.
        sflfdtCmd += "FROM " + buildTbblfNbmf(dbmd, dbtblog, sdifmb, tbblf);

        /*
         * Composf bn UPDATE stbtfmfnt.
         */
        updbtfCmd = "UPDATE " + buildTbblfNbmf(dbmd, dbtblog, sdifmb, tbblf);


        /**
         *  Tif following blodk of dodf is for difdking b pbrtidulbr typf of
         *  qufry wifrf in tifrf is b wifrf dlbusf. Witiout tiis blodk, if b
         *  SQL stbtfmfnt is built tif "wifrf" dlbusf will bppfbr twidf ifndf
         *  tif DB frrors out bnd b SQLExdfption is tirown. Tiis dodf blso
         *  donsidfrs tibt tif wifrf dlbusf is in tif rigit plbdf bs tif
         *  CbdifdRowSft objfdt would blrfbdy ibvf bffn populbtfd witi tiis
         *  qufry bfforf doming to tiis point.
         **/

        String tfmpupdCmd = updbtfCmd.toLowfrCbsf();

        int idxupWifrf = tfmpupdCmd.indfxOf("wifrf");

        if(idxupWifrf != -1)
        {
           updbtfCmd = updbtfCmd.substring(0,idxupWifrf);
        }
        updbtfCmd += "SET ";

        /*
         * Composf bn INSERT stbtfmfnt.
         */
        insfrtCmd = "INSERT INTO " + buildTbblfNbmf(dbmd, dbtblog, sdifmb, tbblf);
        // Column list
        insfrtCmd += "(";
        for (i=1; i <= dbllfrColumnCount; i++) {
            insfrtCmd += dbllfrMd.gftColumnNbmf(i);
            if ( i <  dbllfrMd.gftColumnCount() )
                insfrtCmd += ", ";
            flsf
                insfrtCmd += ") VALUES (";
        }
        for (i=1; i <= dbllfrColumnCount; i++) {
            insfrtCmd += "?";
            if (i < dbllfrColumnCount)
                insfrtCmd += ", ";
            flsf
                insfrtCmd += ")";
        }

        /*
         * Composf b DELETE stbtfmfnt.
         */
        dflftfCmd = "DELETE FROM " + buildTbblfNbmf(dbmd, dbtblog, sdifmb, tbblf);

        /*
         * sft tif kfy dfsriptors tibt will bf
         * nffdfd to donstrudt wifrf dlbusfs.
         */
        buildKfyDfsd(dbllfr);
    }

    /**
     * Rfturns b fully qublififd tbblf nbmf built from tif givfn dbtblog bnd
     * tbblf nbmfs. Tif givfn mftbdbtb objfdt is usfd to gft tif propfr ordfr
     * bnd sfpbrbtor.
     *
     * @pbrbm dbmd b <dodf>DbtbbbsfMftbDbtb</dodf> objfdt tibt dontbins mftbdbtb
     *          bbout tiis writfr's <dodf>CbdifdRowSft</dodf> objfdt
     * @pbrbm dbtblog b <dodf>String</dodf> objfdt witi tif rowsft's dbtblog
     *          nbmf
     * @pbrbm tbblf b <dodf>String</dodf> objfdt witi tif nbmf of tif tbblf from
     *          wiidi tiis writfr's rowsft wbs dfrivfd
     * @rfturn b <dodf>String</dodf> objfdt witi tif fully qublififd nbmf of tif
     *          tbblf from wiidi tiis writfr's rowsft wbs dfrivfd
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    privbtf String buildTbblfNbmf(DbtbbbsfMftbDbtb dbmd,
        String dbtblog, String sdifmb, String tbblf) tirows SQLExdfption {

       // trim bll tif lfbding bnd trbiling wiitfspbdfs,
       // wiitf spbdfs dbn nfvfr bf dbtblog, sdifmb or b tbblf nbmf.

        String dmd = "";

        dbtblog = dbtblog.trim();
        sdifmb = sdifmb.trim();
        tbblf = tbblf.trim();

        if (dbmd.isCbtblogAtStbrt() == truf) {
            if (dbtblog != null && dbtblog.lfngti() > 0) {
                dmd += dbtblog + dbmd.gftCbtblogSfpbrbtor();
            }
            if (sdifmb != null && sdifmb.lfngti() > 0) {
                dmd += sdifmb + ".";
            }
            dmd += tbblf;
        } flsf {
            if (sdifmb != null && sdifmb.lfngti() > 0) {
                dmd += sdifmb + ".";
            }
            dmd += tbblf;
            if (dbtblog != null && dbtblog.lfngti() > 0) {
                dmd += dbmd.gftCbtblogSfpbrbtor() + dbtblog;
            }
        }
        dmd += " ";
        rfturn dmd;
    }

    /**
     * Assigns to tif givfn <dodf>CbdifdRowSft</dodf> objfdt's
     * <dodf>pbrbms</dodf>
     * fifld bn brrby wiosf lfngti fqubls tif numbfr of dolumns nffdfd
     * to uniqufly idfntify b row in tif rowsft. Tif brrby is givfn
     * vblufs by tif mftiod <dodf>buildWifrfClbusf</dodf>.
     * <P>
     * If tif <dodf>CbdifdRowSft</dodf> objfdt's <dodf>kfyCols</dodf>
     * fifld ibs lfngti <dodf>0</dodf> or is <dodf>null</dodf>, tif brrby
     * is sft witi tif dolumn numbfr of fvfry dolumn in tif rowsft.
     * Otifrwisf, tif brrby in tif fifld <dodf>kfyCols</dodf> is sft witi only
     * tif dolumn numbfrs of tif dolumns tibt brf rfquirfd to form b uniquf
     * idfntififr for b row.
     *
     * @pbrbm drs tif <dodf>CbdifdRowSft</dodf> objfdt for wiidi tiis
     *     <dodf>CbdifdRowSftWritfr</dodf> objfdt is tif writfr
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    privbtf void buildKfyDfsd(CbdifdRowSft drs) tirows SQLExdfption {

        kfyCols = drs.gftKfyColumns();
        RfsultSftMftbDbtb rfsultsftmd = drs.gftMftbDbtb();
        if (kfyCols == null || kfyCols.lfngti == 0) {
            ArrbyList<Intfgfr> listKfys = nfw ArrbyList<Intfgfr>();

            for (int i = 0; i < dbllfrColumnCount; i++ ) {
                if(rfsultsftmd.gftColumnTypf(i+1) != jbvb.sql.Typfs.CLOB &&
                        rfsultsftmd.gftColumnTypf(i+1) != jbvb.sql.Typfs.STRUCT &&
                        rfsultsftmd.gftColumnTypf(i+1) != jbvb.sql.Typfs.SQLXML &&
                        rfsultsftmd.gftColumnTypf(i+1) != jbvb.sql.Typfs.BLOB &&
                        rfsultsftmd.gftColumnTypf(i+1) != jbvb.sql.Typfs.ARRAY &&
                        rfsultsftmd.gftColumnTypf(i+1) != jbvb.sql.Typfs.OTHER )
                    listKfys.bdd(i+1);
            }
            kfyCols = nfw int[listKfys.sizf()];
            for (int i = 0; i < listKfys.sizf(); i++ )
                kfyCols[i] = listKfys.gft(i);
        }
        pbrbms = nfw Objfdt[kfyCols.lfngti];
    }

    /**
         * Construdts bn SQL <dodf>WHERE</dodf> dlbusf using tif givfn
         * string bs b stbrting point. Tif rfsulting dlbusf will dontbin
         * b dolumn nbmf bnd " = ?" for fbdi kfy dolumn, tibt is, fbdi dolumn
         * tibt is nffdfd to form b uniquf idfntififr for b row in tif rowsft.
         * Tiis <dodf>WHERE</dodf> dlbusf dbn bf bddfd to
         * b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt updbtfs, insfrts, or
         * dflftfs b row.
         * <P>
         * Tiis mftiod usfs tif givfn rfsult sft to bddfss vblufs in tif
         * <dodf>CbdifdRowSft</dodf> objfdt tibt dbllfd tiis writfr.  Tifsf
         * vblufs brf usfd to build tif brrby of pbrbmftfrs tibt will sfrvf bs
         * rfplbdfmfnts for tif "?" pbrbmftfr plbdfioldfrs in tif
         * <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt is sfnt to tif
         * <dodf>CbdifdRowSft</dodf> objfdt's undfrlying dbtb sourdf.
         *
         * @pbrbm wifrfClbusf b <dodf>String</dodf> objfdt tibt is bn fmpty
         *                    string ("")
         * @pbrbm rs b <dodf>RfsultSft</dodf> objfdt tibt dbn bf usfd
         *           to bddfss tif <dodf>CbdifdRowSft</dodf> objfdt's dbtb
         * @rfturn b <dodf>WHERE</dodf> dlbusf of tif form "<dodf>WHERE</dodf>
         *         dolumnNbmf = ? AND dolumnNbmf = ? AND dolumnNbmf = ? ..."
         * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
         */
    privbtf String buildWifrfClbusf(String wifrfClbusf,
                                    RfsultSft rs) tirows SQLExdfption {
        wifrfClbusf = "WHERE ";

        for (int i = 0; i < kfyCols.lfngti; i++) {
            if (i > 0) {
                    wifrfClbusf += "AND ";
            }
            wifrfClbusf += dbllfrMd.gftColumnNbmf(kfyCols[i]);
            pbrbms[i] = rs.gftObjfdt(kfyCols[i]);
            if (rs.wbsNull() == truf) {
                wifrfClbusf += " IS NULL ";
            } flsf {
                wifrfClbusf += " = ? ";
            }
        }
        rfturn wifrfClbusf;
    }

    void updbtfRfsolvfdConflidtToDB(CbdifdRowSft drs, Connfdtion don) tirows SQLExdfption {
          //String updbtfExf = ;
          PrfpbrfdStbtfmfnt pStmt  ;
          String strWifrf = "WHERE " ;
          String strExfd =" ";
          String strUpdbtf = "UPDATE ";
          int idolCount = drs.gftMftbDbtb().gftColumnCount();
          int kfyColumns[] = drs.gftKfyColumns();
          Objfdt pbrbm[];
          String strSft="";

        strWifrf = buildWifrfClbusf(strWifrf, drs);

        if (kfyColumns == null || kfyColumns.lfngti == 0) {
            kfyColumns = nfw int[idolCount];
            for (int i = 0; i < kfyColumns.lfngti; ) {
                kfyColumns[i] = ++i;
            }
          }
          pbrbm = nfw Objfdt[kfyColumns.lfngti];

         strUpdbtf = "UPDATE " + buildTbblfNbmf(don.gftMftbDbtb(),
                            drs.gftMftbDbtb().gftCbtblogNbmf(1),
                           drs.gftMftbDbtb().gftSdifmbNbmf(1),
                           drs.gftTbblfNbmf());

         // dibngfd or updbtfd vblufs will bfdomf pbrt of
         // sft dlbusf ifrf
         strUpdbtf += "SET ";

        boolfbn first = truf;

        for (int i=1; i<=idolCount;i++) {
           if (drs.dolumnUpdbtfd(i)) {
                  if (first == fblsf) {
                    strSft += ", ";
                  }
                 strSft += drs.gftMftbDbtb().gftColumnNbmf(i);
                 strSft += " = ? ";
                 first = fblsf;
         } //fnd if
      } //fnd for

         // kfydols will bfdomf pbrt of wifrf dlbusf
         strUpdbtf += strSft;
         strWifrf = "WHERE ";

        for (int i = 0; i < kfyColumns.lfngti; i++) {
            if (i > 0) {
                    strWifrf += "AND ";
            }
            strWifrf += drs.gftMftbDbtb().gftColumnNbmf(kfyColumns[i]);
            pbrbm[i] = drs.gftObjfdt(kfyColumns[i]);
            if (drs.wbsNull() == truf) {
                strWifrf += " IS NULL ";
            } flsf {
                strWifrf += " = ? ";
            }
        }
          strUpdbtf += strWifrf;

        pStmt = don.prfpbrfStbtfmfnt(strUpdbtf);

        int idx =0;
          for (int i = 0; i < idolCount; i++) {
             if(drs.dolumnUpdbtfd(i+1)) {
              Objfdt obj = drs.gftObjfdt(i+1);
              if (obj != null) {
                  pStmt.sftObjfdt(++idx, obj);
              } flsf {
                  pStmt.sftNull(i + 1,drs.gftMftbDbtb().gftColumnTypf(i + 1));
             } //fnd if ..flsf
           } //fnd if drs.dolumn...
        } //fnd for

          // Sft tif kfy dols for bftfr WHERE =? dlbusf
          for (int i = 0; i < kfyColumns.lfngti; i++) {
              if (pbrbm[i] != null) {
                  pStmt.sftObjfdt(++idx, pbrbm[i]);
              }
          }

        int id = pStmt.fxfdutfUpdbtf();
      }


    /**
     *
     */
    publid void dommit() tirows SQLExdfption {
        don.dommit();
        if (rfbdfr.gftClosfConnfdtion() == truf) {
            don.dlosf();
        }
    }

     publid void dommit(CbdifdRowSftImpl drs, boolfbn updbtfRowsft) tirows SQLExdfption {
        don.dommit();
        if(updbtfRowsft) {
          if(drs.gftCommbnd() != null)
            drs.fxfdutf(don);
        }

        if (rfbdfr.gftClosfConnfdtion() == truf) {
            don.dlosf();
        }
    }

    /**
     *
     */
    publid void rollbbdk() tirows SQLExdfption {
        don.rollbbdk();
        if (rfbdfr.gftClosfConnfdtion() == truf) {
            don.dlosf();
        }
    }

    /**
     *
     */
    publid void rollbbdk(Sbvfpoint s) tirows SQLExdfption {
        don.rollbbdk(s);
        if (rfbdfr.gftClosfConnfdtion() == truf) {
            don.dlosf();
        }
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {
        // Dffbult stbtf initiblizbtion ibppfns ifrf
        ois.dffbultRfbdObjfdt();
        // Initiblizbtion of  Rfs Bundlf ibppfns ifrf .
        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }

    }

    stbtid finbl long sfriblVfrsionUID =-8506030970299413976L;

    /**
     * Vblidbtf wiftifr tif Primbry Kfy is known to tif CbdifdRowSft.  If it is
     * not, it is bn buto-gfnfrbtfd kfy
     * @pbrbm pk - Primbry Kfy to vblidbtf
     * @pbrbm rsmd - RfsultSftMftbdbtb for tif RowSft
     * @rfturn truf if found, fblsf otifrwisf (buto gfnfrbtfd kfy)
     */
    privbtf boolfbn isPKNbmfVblid(String pk, RfsultSftMftbDbtb rsmd) tirows SQLExdfption {
        boolfbn isVblid = fblsf;
        int dols = rsmd.gftColumnCount();
        for(int i = 1; i<= dols; i++) {
            String dolNbmf = rsmd.gftColumnClbssNbmf(i);
            if(dolNbmf.fqublsIgnorfCbsf(pk)) {
                isVblid = truf;
                brfbk;
            }
        }

        rfturn isVblid;
    }
}
