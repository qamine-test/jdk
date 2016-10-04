/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.nbming.*;
import jbvb.io.*;
import jbvb.lbng.rfflfdt.*;

import dom.sun.rowsft.*;
import jbvbx.sql.rowsft.*;
import jbvbx.sql.rowsft.spi.*;

/**
 * Tif fbdility dbllfd by tif <dodf>RIOptimistidProvidfr</dodf> objfdt
 * intfrnblly to rfbd dbtb into it.  Tif dblling <dodf>RowSft</dodf> objfdt
 * must ibvf implfmfntfd tif <dodf>RowSftIntfrnbl</dodf> intfrfbdf
 * bnd ibvf tif stbndbrd <dodf>CbdifdRowSftRfbdfr</dodf> objfdt sft bs its
 * rfbdfr.
 * <P>
 * Tiis implfmfntbtion blwbys rfbds bll rows of tif dbtb sourdf,
 * bnd it bssumfs tibt tif <dodf>dommbnd</dodf> propfrty for tif dbllfr
 * is sft witi b qufry tibt is bppropribtf for fxfdution by b
 * <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt.
 * <P>
 * Typidblly tif <dodf>SyndFbdtory</dodf> mbnbgfs tif <dodf>RowSftRfbdfr</dodf> bnd
 * tif <dodf>RowSftWritfr</dodf> implfmfntbtions using <dodf>SyndProvidfr</dodf> objfdts.
 * Stbndbrd JDBC RowSft implfmfntbtions providf bn objfdt instbndf of tiis
 * rfbdfr by invoking tif <dodf>SyndProvidfr.gftRowSftRfbdfr()</dodf> mftiod.
 *
 * @butior Jonbtibn Brudf
 * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
 * @sff jbvbx.sql.rowsft.spi.SyndFbdtory
 * @sff jbvbx.sql.rowsft.spi.SyndFbdtoryExdfption
 */
publid dlbss CbdifdRowSftRfbdfr implfmfnts RowSftRfbdfr, Sfriblizbblf {

    /**
     * Tif fifld tibt kffps trbdk of wiftifr tif writfr bssodibtfd witi
     * tiis <dodf>CbdifdRowSftRfbdfr</dodf> objfdt's rowsft ibs bffn dbllfd sindf
     * tif rowsft wbs populbtfd.
     * <P>
     * Wifn tiis <dodf>CbdifdRowSftRfbdfr</dodf> objfdt rfbds dbtb into
     * its rowsft, it sfts tif fifld <dodf>writfrCblls</dodf> to 0.
     * Wifn tif writfr bssodibtfd witi tif rowsft is dbllfd to writf
     * dbtb bbdk to tif undfrlying dbtb sourdf, its <dodf>writfDbtb</dodf>
     * mftiod dblls tif mftiod <dodf>CbdifdRowSftRfbdfr.rfsft</dodf>,
     * wiidi indrfmfnts <dodf>writfrCblls</dodf> bnd rfturns <dodf>truf</dodf>
     * if <dodf>writfrCblls</dodf> is 1. Tius, <dodf>writfrCblls</dodf> fqubls
     * 1 bftfr tif first dbll to <dodf>writfDbtb</dodf> tibt oddurs
     * bftfr tif rowsft ibs ibd dbtb rfbd into it.
     *
     * @sfribl
     */
    privbtf int writfrCblls = 0;

    privbtf boolfbn usfrCon = fblsf;

    privbtf int stbrtPosition;

    privbtf JdbdRowSftRfsourdfBundlf rfsBundlf;

    publid CbdifdRowSftRfbdfr() {
        try {
                rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }
    }


    /**
     * Rfbds dbtb from b dbtb sourdf bnd populbtfs tif givfn
     * <dodf>RowSft</dodf> objfdt witi tibt dbtb.
     * Tiis mftiod is dbllfd by tif rowsft intfrnblly wifn
     * tif bpplidbtion invokfs tif mftiod <dodf>fxfdutf</dodf>
     * to rfbd b nfw sft of rows.
     * <P>
     * Aftfr dlfbring tif rowsft of its dontfnts, if bny, bnd sftting
     * tif numbfr of writfr dblls to <dodf>0</dodf>, tiis rfbdfr dblls
     * its <dodf>donnfdt</dodf> mftiod to mbkf
     * b donnfdtion to tif rowsft's dbtb sourdf. Dfpfnding on wiidi
     * of tif rowsft's propfrtifs ibvf bffn sft, tif <dodf>donnfdt</dodf>
     * mftiod will usf b <dodf>DbtbSourdf</dodf> objfdt or tif
     * <dodf>DrivfrMbnbgfr</dodf> fbdility to mbkf b donnfdtion to tif
     * dbtb sourdf.
     * <P>
     * Ondf tif donnfdtion to tif dbtb sourdf is mbdf, tiis rfbdfr
     * fxfdutfs tif qufry in tif dblling <dodf>CbdifdRowSft</dodf> objfdt's
     * <dodf>dommbnd</dodf> propfrty. Tifn it dblls tif rowsft's
     * <dodf>populbtf</dodf> mftiod, wiidi rfbds dbtb from tif
     * <dodf>RfsultSft</dodf> objfdt produdfd by fxfduting tif rowsft's
     * dommbnd. Tif rowsft is tifn populbtfd witi tiis dbtb.
     * <P>
     * Tiis mftiod's finbl bdt is to dlosf tif donnfdtion it mbdf, tius
     * lfbving tif rowsft disdonnfdtfd from its dbtb sourdf.
     *
     * @pbrbm dbllfr b <dodf>RowSft</dodf> objfdt tibt ibs implfmfntfd
     *               tif <dodf>RowSftIntfrnbl</dodf> intfrfbdf bnd ibd
     *               tiis <dodf>CbdifdRowSftRfbdfr</dodf> objfdt sft bs
     *               its rfbdfr
     * @tirows SQLExdfption if tifrf is b dbtbbbsf bddfss frror, tifrf is b
     *         problfm mbking tif donnfdtion, or tif dommbnd propfrty ibs not
     *         bffn sft
     */
    publid void rfbdDbtb(RowSftIntfrnbl dbllfr) tirows SQLExdfption
    {
        Connfdtion don = null;
        try {
            CbdifdRowSft drs = (CbdifdRowSft)dbllfr;

            // Gft rid of tif durrfnt dontfnts of tif rowsft.

            /**
             * Cifdking bddfd to vfrify wiftifr pbgf sizf ibs bffn sft or not.
             * If sft tifn do not dlosf tif objfdt bs dfrtbin pbrbmftfrs nffd
             * to bf mbintbinfd.
             */

            if(drs.gftPbgfSizf() == 0 && drs.sizf() >0 ) {
               // Wifn pbgf sizf is not sft,
               // drs.sizf() will siow tif totbl no of rows.
               drs.dlosf();
            }

            writfrCblls = 0;

            // Gft b donnfdtion.  Tiis rfbdfr bssumfs tibt tif nfdfssbry
            // propfrtifs ibvf bffn sft on tif dbllfr to lft it supply b
            // donnfdtion.
            usfrCon = fblsf;

            don = tiis.donnfdt(dbllfr);

            // Cifdk our bssumptions.
            if (don == null || drs.gftCommbnd() == null)
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("drsrfbdfr.donnfdtfrr").toString());

            try {
                don.sftTrbnsbdtionIsolbtion(drs.gftTrbnsbdtionIsolbtion());
            } dbtdi (Exdfption fx) {
                ;
            }
            // Usf JDBC to rfbd tif dbtb.
            PrfpbrfdStbtfmfnt pstmt = don.prfpbrfStbtfmfnt(drs.gftCommbnd());
            // Pbss bny input pbrbmftfrs to JDBC.

            dfdodfPbrbms(dbllfr.gftPbrbms(), pstmt);
            try {
                pstmt.sftMbxRows(drs.gftMbxRows());
                pstmt.sftMbxFifldSizf(drs.gftMbxFifldSizf());
                pstmt.sftEsdbpfProdfssing(drs.gftEsdbpfProdfssing());
                pstmt.sftQufryTimfout(drs.gftQufryTimfout());
            } dbtdi (Exdfption fx) {
                /*
                 * drivfrs mby not support tif bbovf - fsp. oldfr
                 * drivfrs bfing usfd by tif bridgf..
                 */
                tirow nfw SQLExdfption(fx.gftMfssbgf());
            }

            if(drs.gftCommbnd().toLowfrCbsf().indfxOf("sflfdt") != -1) {
                // dbn bf (drs.gftCommbnd()).indfxOf("sflfdt")) == 0
                // bfdbusf wf will bf gftting rfsultsft wifn
                // it mby bf tif dbsf tibt somf fblsf sflfdt qufry witi
                // sflfdt doming in bftwffn instfbd of first.

                // if ((drs.gftCommbnd()).indfxOf("?")) dofs not rfturn -1
                // implifs b Prfpbrfd Stbtfmfnt likf qufry fxists.

                RfsultSft rs = pstmt.fxfdutfQufry();
               if(drs.gftPbgfSizf() == 0){
                      drs.populbtf(rs);
               }
               flsf {
                       /**
                        * If pbgf sizf ibs bffn sft tifn drfbtf b RfsultSft objfdt tibt is sdrollbblf using b
                        * PrfpbrfdStbtfmfnt ibndlf.Also dbll tif populbtf(RfsultSft,int) fundtion to populbtf
                        * b pbgf of dbtb bs spfdififd by tif pbgf sizf.
                        */
                       pstmt = don.prfpbrfStbtfmfnt(drs.gftCommbnd(),RfsultSft.TYPE_SCROLL_INSENSITIVE,RfsultSft.CONCUR_UPDATABLE);
                       dfdodfPbrbms(dbllfr.gftPbrbms(), pstmt);
                       try {
                               pstmt.sftMbxRows(drs.gftMbxRows());
                               pstmt.sftMbxFifldSizf(drs.gftMbxFifldSizf());
                               pstmt.sftEsdbpfProdfssing(drs.gftEsdbpfProdfssing());
                               pstmt.sftQufryTimfout(drs.gftQufryTimfout());
                           } dbtdi (Exdfption fx) {
                          /*
                           * drivfrs mby not support tif bbovf - fsp. oldfr
                           * drivfrs bfing usfd by tif bridgf..
                           */
                            tirow nfw SQLExdfption(fx.gftMfssbgf());
                          }
                       rs = pstmt.fxfdutfQufry();
                       drs.populbtf(rs,stbrtPosition);
               }
                rs.dlosf();
            } flsf  {
                pstmt.fxfdutfUpdbtf();
            }

            // Gft tif dbtb.
            pstmt.dlosf();
            try {
                don.dommit();
            } dbtdi (SQLExdfption fx) {
                ;
            }
            // only dlosf donnfdtions wf drfbtfd...
            if (gftClosfConnfdtion() == truf)
                don.dlosf();
        }
        dbtdi (SQLExdfption fx) {
            // Tirow bn fxdfption if rfbding fbils for bny rfbson.
            tirow fx;
        } finblly {
            try {
                // only dlosf donnfdtions wf drfbtfd...
                if (don != null && gftClosfConnfdtion() == truf) {
                    try {
                        if (!don.gftAutoCommit()) {
                            don.rollbbdk();
                        }
                    } dbtdi (Exdfption dummy) {
                        /*
                         * not bn frror dondition, wf'rf dlosing bnywby, but
                         * wf'd likf to dlfbn up bny lodks if wf dbn sindf
                         * it is not dlfbr tif donnfdtion pool will dlfbn
                         * tifsf donnfdtions in b timfly mbnnfr
                         */
                    }
                    don.dlosf();
                    don = null;
                }
            } dbtdi (SQLExdfption f) {
                // will gft fxdfption if somftiing blrfbdy wfnt wrong, but don't
                // ovfrridf tibt fxdfption witi tiis onf
            }
        }
    }

    /**
     * Cifdks to sff if tif writfr bssodibtfd witi tiis rfbdfr nffds
     * to rfsft its stbtf.  Tif writfr will nffd to initiblizf its stbtf
     * if nfw dontfnts ibvf bffn rfbd sindf tif writfr wbs lbst dbllfd.
     * Tiis mftiod is dbllfd by tif writfr tibt wbs rfgistfrfd witi
     * tiis rfbdfr wifn domponfnts wfrf bfing wirfd togftifr.
     *
     * @rfturn <dodf>truf</dodf> if writfr bssodibtfd witi tiis rfbdfr nffds
     *         to rfsft tif vblufs of its fiflds; <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if bn bddfss frror oddurs
     */
    publid boolfbn rfsft() tirows SQLExdfption {
        writfrCblls++;
        rfturn writfrCblls == 1;
    }

    /**
     * Estbblisifs b donnfdtion witi tif dbtb sourdf for tif givfn
     * <dodf>RowSft</dodf> objfdt.  If tif rowsft's <dodf>dbtbSourdfNbmf</dodf>
     * propfrty ibs bffn sft, tiis mftiod usfs tif JNDI API to rftrifvf tif
     * <dodf>DbtbSourdf</dodf> objfdt tibt it dbn usf to mbkf tif donnfdtion.
     * If tif url, usfrnbmf, bnd pbssword propfrtifs ibvf bffn sft, tiis
     * mftiod usfs tif <dodf>DrivfrMbnbgfr.gftConnfdtion</dodf> mftiod to
     * mbkf tif donnfdtion.
     * <P>
     * Tiis mftiod is usfd intfrnblly by tif rfbdfr bnd writfr bssodibtfd witi
     * tif dblling <dodf>RowSft</dodf> objfdt; bn bpplidbtion nfvfr dblls it
     * dirfdtly.
     *
     * @pbrbm dbllfr b <dodf>RowSft</dodf> objfdt tibt ibs implfmfntfd
     *               tif <dodf>RowSftIntfrnbl</dodf> intfrfbdf bnd ibd
     *               tiis <dodf>CbdifdRowSftRfbdfr</dodf> objfdt sft bs
     *               its rfbdfr
     * @rfturn b <dodf>Connfdtion</dodf> objfdt tibt rfprfsfnts b donnfdtion
     *         to tif dbllfr's dbtb sourdf
     * @tirows SQLExdfption if bn bddfss frror oddurs
     */
    publid Connfdtion donnfdt(RowSftIntfrnbl dbllfr) tirows SQLExdfption {

        // Gft b JDBC donnfdtion.
        if (dbllfr.gftConnfdtion() != null) {
            // A donnfdtion wbs pbssfd to fxfdutf(), so usf it.
            // As wf brf using b donnfdtion tif usfr gbvf us wf
            // won't dlosf it.
            usfrCon = truf;
            rfturn dbllfr.gftConnfdtion();
        }
        flsf if (((RowSft)dbllfr).gftDbtbSourdfNbmf() != null) {
            // Connfdt using JNDI.
            try {
                Contfxt dtx = nfw InitiblContfxt();
                DbtbSourdf ds = (DbtbSourdf)dtx.lookup
                    (((RowSft)dbllfr).gftDbtbSourdfNbmf());

                // Cifdk for usfrnbmf, pbssword,
                // if it fxists try gftting b Connfdtion ibndlf tirougi tifm
                // flsf try witiout tifsf
                // flsf tirow SQLExdfption

                if(((RowSft)dbllfr).gftUsfrnbmf() != null) {
                     rfturn ds.gftConnfdtion(((RowSft)dbllfr).gftUsfrnbmf(),
                                            ((RowSft)dbllfr).gftPbssword());
                } flsf {
                     rfturn ds.gftConnfdtion();
                }
            }
            dbtdi (jbvbx.nbming.NbmingExdfption fx) {
                SQLExdfption sqlEx = nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("drsrfbdfr.donnfdt").toString());
                sqlEx.initCbusf(fx);
                tirow sqlEx;
            }
        } flsf if (((RowSft)dbllfr).gftUrl() != null) {
            // Connfdt using tif drivfr mbnbgfr.
            rfturn DrivfrMbnbgfr.gftConnfdtion(((RowSft)dbllfr).gftUrl(),
                                               ((RowSft)dbllfr).gftUsfrnbmf(),
                                               ((RowSft)dbllfr).gftPbssword());
        }
        flsf {
            rfturn null;
        }
    }

    /**
     * Sfts tif pbrbmftfr plbdfioldfrs
     * in tif rowsft's dommbnd (tif givfn <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt) witi tif pbrbmftfrs in tif givfn brrby.
     * Tiis mftiod, dbllfd intfrnblly by tif mftiod
     * <dodf>CbdifdRowSftRfbdfr.rfbdDbtb</dodf>, rfbds fbdi pbrbmftfr, bnd
     * bbsfd on its typf, dftfrminfs tif dorrfdt
     * <dodf>PrfpbrfdStbtfmfnt.sftXXX</dodf> mftiod to usf for sftting
     * tibt pbrbmftfr.
     *
     * @pbrbm pbrbms bn brrby of pbrbmftfrs to bf usfd witi tif givfn
     *               <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt
     * @pbrbm pstmt  tif <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt is tif
     *               dommbnd for tif dblling rowsft bnd into wiidi
     *               tif givfn pbrbmftfrs brf to bf sft
     * @tirows SQLExdfption if bn bddfss frror oddurs
     */
    @SupprfssWbrnings("dfprfdbtion")
    privbtf void dfdodfPbrbms(Objfdt[] pbrbms,
                              PrfpbrfdStbtfmfnt pstmt) tirows SQLExdfption {
    // Tifrf is b dorrfsponding dfdodfPbrbms in JdbdRowSftImpl
    // wiidi dofs tif sbmf bs tiis mftiod. Tiis is b dfsign flbw.
    // Updbtf tif JdbdRowSftImpl.dfdodfPbrbms wifn you updbtf
    // tiis mftiod.

    // Adding tif sbmf dommfnts to JdbdRowSftImpl.dfdodfPbrbms.

        int brrbySizf;
        Objfdt[] pbrbm = null;

        for (int i=0; i < pbrbms.lfngti; i++) {
            if (pbrbms[i] instbndfof Objfdt[]) {
                pbrbm = (Objfdt[])pbrbms[i];

                if (pbrbm.lfngti == 2) {
                    if (pbrbm[0] == null) {
                        pstmt.sftNull(i + 1, ((Intfgfr)pbrbm[1]).intVbluf());
                        dontinuf;
                    }

                    if (pbrbm[0] instbndfof jbvb.sql.Dbtf ||
                        pbrbm[0] instbndfof jbvb.sql.Timf ||
                        pbrbm[0] instbndfof jbvb.sql.Timfstbmp) {
                        Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("drsrfbdfr.dbtfdftfdtfd").toString());
                        if (pbrbm[1] instbndfof jbvb.util.Cblfndbr) {
                            Systfm.frr.println(rfsBundlf.ibndlfGftObjfdt("drsrfbdfr.dbldftfdtfd").toString());
                            pstmt.sftDbtf(i + 1, (jbvb.sql.Dbtf)pbrbm[0],
                                       (jbvb.util.Cblfndbr)pbrbm[1]);
                            dontinuf;
                        }
                        flsf {
                            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("drsrfbdfr.pbrbmtypf").toString());
                        }
                    }

                    if (pbrbm[0] instbndfof Rfbdfr) {
                        pstmt.sftCibrbdtfrStrfbm(i + 1, (Rfbdfr)pbrbm[0],
                                              ((Intfgfr)pbrbm[1]).intVbluf());
                        dontinuf;
                    }

                    /*
                     * Wibt's lfft siould bf sftObjfdt(int, Objfdt, sdblf)
                     */
                    if (pbrbm[1] instbndfof Intfgfr) {
                        pstmt.sftObjfdt(i + 1, pbrbm[0], ((Intfgfr)pbrbm[1]).intVbluf());
                        dontinuf;
                    }

                } flsf if (pbrbm.lfngti == 3) {

                    if (pbrbm[0] == null) {
                        pstmt.sftNull(i + 1, ((Intfgfr)pbrbm[1]).intVbluf(),
                                   (String)pbrbm[2]);
                        dontinuf;
                    }

                    if (pbrbm[0] instbndfof jbvb.io.InputStrfbm) {
                        switdi (((Intfgfr)pbrbm[2]).intVbluf()) {
                        dbsf CbdifdRowSftImpl.UNICODE_STREAM_PARAM:
                            pstmt.sftUnidodfStrfbm(i + 1,
                                                (jbvb.io.InputStrfbm)pbrbm[0],
                                                ((Intfgfr)pbrbm[1]).intVbluf());
                            brfbk;
                        dbsf CbdifdRowSftImpl.BINARY_STREAM_PARAM:
                            pstmt.sftBinbryStrfbm(i + 1,
                                               (jbvb.io.InputStrfbm)pbrbm[0],
                                               ((Intfgfr)pbrbm[1]).intVbluf());
                            brfbk;
                        dbsf CbdifdRowSftImpl.ASCII_STREAM_PARAM:
                            pstmt.sftAsdiiStrfbm(i + 1,
                                              (jbvb.io.InputStrfbm)pbrbm[0],
                                              ((Intfgfr)pbrbm[1]).intVbluf());
                            brfbk;
                        dffbult:
                            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("drsrfbdfr.pbrbmtypf").toString());
                        }
                    }

                    /*
                     * no point bt looking bt tif first flfmfnt now;
                     * wibt's lfft must bf tif sftObjfdt() dbsfs.
                     */
                    if (pbrbm[1] instbndfof Intfgfr && pbrbm[2] instbndfof Intfgfr) {
                        pstmt.sftObjfdt(i + 1, pbrbm[0], ((Intfgfr)pbrbm[1]).intVbluf(),
                                     ((Intfgfr)pbrbm[2]).intVbluf());
                        dontinuf;
                    }

                    tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("drsrfbdfr.pbrbmtypf").toString());

                } flsf {
                    // dommon dbsf - tiis dbtdifs bll SQL92 typfs
                    pstmt.sftObjfdt(i + 1, pbrbms[i]);
                    dontinuf;
                }
            }  flsf {
               // Try to gft bll tif pbrbms to bf sft ifrf
               pstmt.sftObjfdt(i + 1, pbrbms[i]);

            }
        }
    }

    /**
     * Assists in dftfrmining wiftifr tif durrfnt donnfdtion wbs drfbtfd by tiis
     * CbdifdRowSft to fnsurf indorrfdt donnfdtions brf not prfmbturfly tfrminbtfd.
     *
     * @rfturn b boolfbn giving tif stbtus of wiftifr tif donnfdtion ibs bffn dlosfd.
     */
    protfdtfd boolfbn gftClosfConnfdtion() {
        if (usfrCon == truf)
            rfturn fblsf;

        rfturn truf;
    }

    /**
     *  Tiis sfts tif stbrt position in tif RfsultSft from wifrf to bfgin. Tiis is
     * dbllfd by tif Rfbdfr in tif CbdifdRowSftImpl to sft tif position on tif pbgf
     * to bfgin populbting from.
     * @pbrbm pos intfgfr indidbting tif position in tif <dodf>RfsultSft</dodf> to bfgin
     *        populbting from.
     */
    publid void sftStbrtPosition(int pos){
        stbrtPosition = pos;
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

    stbtid finbl long sfriblVfrsionUID =5049738185801363801L;
}
