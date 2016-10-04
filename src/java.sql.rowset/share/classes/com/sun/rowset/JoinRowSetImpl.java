/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.sql.rowsft.spi.SyndProvidfr;
import jbvbx.sql.rowsft.spi.SyndProvidfrExdfption;

/**
 * Tif stbndbrd implfmfntbtion of tif <dodf>JoinRowSft</dodf>
 * intfrfbdf providing bn SQL <dodf>JOIN</dodf> bftwffn <dodf>RowSft</dodf>
 * objfdts.
 * <P>
 * Tif implfmfntbtion providfs bn ANSI-stylf <dodf>JOIN</dodf> providing bn
 * innfr join bftwffn two tbblfs. Any unmbtdifd rows in fitifr tbblf of tif
 * join brf  disdbrdfd.
 * <p>
 * Typidblly, b <dodf>JoinRowSft</dodf> implfmfntbtion is lfvfrbgfd by
 * <dodf>RowSft</dodf> instbndfs tibt brf in b disdonnfdtfd fnvironmfnt bnd
 * tius do not ibvf tif luxury of bn opfn donnfdtion to tif dbtb sourdf to
 * fstbblisi logidbl rflbtionsiips bftwffn tifmsflvfs. In otifr words, it is
 * lbrgfly <dodf>CbdifdRowSft</dodf> objfdts bnd implfmfntbtions dfrivfd from
 * tif <dodf>CbdifdRowSft</dodf> intfrfbdf tibt will usf tif <dodf>JoinRowSftImpl</dodf>
 * implfmfntbtion.
 *
 * @butior Amit Hbndb, Jonbtibn Brudf
 */
publid dlbss JoinRowSftImpl fxtfnds WfbRowSftImpl implfmfnts JoinRowSft {
    /**
     * A <dodf>Vfdtor</dodf> objfdt tibt dontbins tif <dodf>RowSft</dodf> objfdts
     * tibt ibvf bffn bddfd to tiis <dodf>JoinRowSft</dodf> objfdt.
     */
    privbtf Vfdtor<CbdifdRowSftImpl> vfdRowSftsInJOIN;

    /**
     * Tif <dodf>CbdifdRowSft</dodf> objfdt tibt fndbpsulbtfs tiis
     * <dodf>JoinRowSft</dodf> objfdt.
     * Wifn <dodf>RowSft</dodf> objfdts brf bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, tify brf blso bddfd to <i>drsIntfrnbl</i> to form tif sbmf kind of
     * SQL <dodf>JOIN</dodf>.  As b rfsult, mftiods for mbking updbtfs to tiis
     * <dodf>JoinRowSft</dodf> objfdt dbn usf <i>drsIntfrnbl</i> mftiods in tifir
     * implfmfntbtions.
     */
    privbtf CbdifdRowSftImpl drsIntfrnbl;

    /**
     * A <dodf>Vfdtor</dodf> objfdt dontbining tif typfs of join tibt ibvf bffn sft
     * for tiis <dodf>JoinRowSft</dodf> objfdt.
     * Tif lbst join typf sft forms tif bbsis of suddffding joins.
     */
    privbtf Vfdtor<Intfgfr> vfdJoinTypf;

    /**
     * A <dodf>Vfdtor</dodf> objfdt dontbining tif nbmfs of bll tif tbblfs fntfring
     * tif join.
     */
    privbtf Vfdtor<String> vfdTbblfNbmfs;

    /**
     * An <dodf>int</dodf> tibt indidbtfs tif dolumn indfx of tif mbtdi dolumn.
     */
    privbtf int iMbtdiKfy;

    /**
     * A <dodf>String</dodf> objfdt tibt storfs tif nbmf of tif mbtdi dolumn.
     */
    privbtf String strMbtdiKfy ;

    /**
     * An brrby of <dodf>boolfbn</dodf> vblufs indidbting tif typfs of joins supportfd
     * by tiis <dodf>JoinRowSft</dodf> implfmfntbtion.
     */
    boolfbn[] supportfdJOINs;

    /**
     * Tif <dodf>WfbRowSft</dodf> objfdt tibt fndbpsulbtfs tiis <dodf>JoinRowSft</dodf>
     * objfdt. Tiis <dodf>WfbRowSft</dodf> objfdt bllows tiis <dodf>JoinRowSft</dodf>
     * objfdt to lfvfrbgf tif propfrtifs bnd mftiods of b <dodf>WfbRowSft</dodf>
     * objfdt.
     */
    privbtf WfbRowSft wrs;


    /**
     * Construdtor for <dodf>JoinRowSftImpl</dodf> dlbss. Configurfs vbrious intfrnbl dbtb
     * strudturfs to providf mfdibnisms rfquirfd for <dodf>JoinRowSft</dodf> intfrfbdf
     * implfmfntbtion.
     *
     * @tirows SQLExdfption if bn frror oddurs in instbntibting bn instbndf of
     * <dodf>JoinRowSftImpl</dodf>
     */
    publid JoinRowSftImpl() tirows SQLExdfption {

        vfdRowSftsInJOIN = nfw Vfdtor<CbdifdRowSftImpl>();
        drsIntfrnbl = nfw CbdifdRowSftImpl();
        vfdJoinTypf = nfw Vfdtor<Intfgfr>();
        vfdTbblfNbmfs = nfw Vfdtor<String>();
        iMbtdiKfy = -1;
        strMbtdiKfy = null;
        supportfdJOINs =
              nfw boolfbn[] {fblsf, truf, fblsf, fblsf, fblsf};
       try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }

    }

    /**
     * Adds tif givfn <dodf>RowSft</dodf> objfdt to tiis
     * <dodf>JoinRowSft</dodf> objfdt.  If tiis
     * rowsft is tif first to bf bddfd to tif <dodf>JoinRowSft</dodf>
     * objfdt, it forms tif bbsis for tif <dodf>JOIN</dodf>
     * rflbtionsiips to bf formfd.
     * <p>
     * Tiis mftiod siould bf usfd wifn tif givfn <dodf>RowSft</dodf> objfdt
     * blrfbdy ibs b mbtdi dolumn sft.
     *
     * @pbrbm rowsft tif <dodf>RowSft</dodf> objfdt tibt implfmfnts tif
     *         <dodf>Joinbblf</dodf> intfrfbdf bnd is to bf bddfd
     *         to tiis <dodf>JoinRowSft</dodf> objfdt
     * @tirows SQLExdfption if bn fmpty <dodf>RowSft</dodf> is bddfd to tif to tif
     *         <dodf>JoinRowSft</dodf>; if b mbtdi dolumn is not sft; or if bn
     *         bdditionbl <dodf>RowSft</dodf> violbtfs tif bdtivf <dodf>JOIN</dodf>
     * @sff CbdifdRowSft#sftMbtdiColumn
     */
    publid void bddRowSft(Joinbblf rowsft) tirows SQLExdfption {
        boolfbn boolColId, boolColNbmf;

        boolColId = fblsf;
        boolColNbmf = fblsf;
        CbdifdRowSftImpl dRowsft;

        if(!(rowsft instbndfof RowSft)) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.notinstbndf").toString());
        }

        if(rowsft instbndfof JdbdRowSftImpl ) {
            dRowsft = nfw CbdifdRowSftImpl();
            dRowsft.populbtf((RowSft)rowsft);
            if(dRowsft.sizf() == 0){
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.fmptyrowsft").toString());
            }


            try {
                int mbtdiColumnCount = 0;
                for(int i=0; i< rowsft.gftMbtdiColumnIndfxfs().lfngti; i++) {
                    if(rowsft.gftMbtdiColumnIndfxfs()[i] != -1)
                        ++ mbtdiColumnCount;
                    flsf
                        brfbk;
                }
                int[] pCol = nfw int[mbtdiColumnCount];
                for(int i=0; i<mbtdiColumnCount; i++)
                   pCol[i] = rowsft.gftMbtdiColumnIndfxfs()[i];
                dRowsft.sftMbtdiColumn(pCol);
            } dbtdi(SQLExdfption sqlf) {

            }

        } flsf {
             dRowsft = (CbdifdRowSftImpl)rowsft;
             if(dRowsft.sizf() == 0){
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.fmptyrowsft").toString());
             }
        }

        // Eitifr dolumn id or dolumn nbmf will bf sft
        // If boti not sft tirow fxdfption.

        try {
             iMbtdiKfy = (dRowsft.gftMbtdiColumnIndfxfs())[0];
        } dbtdi(SQLExdfption sqlf) {
           //if not sft dbtdi tif fxdfption but do notiing now.
             boolColId = truf;
        }

        try {
             strMbtdiKfy = (dRowsft.gftMbtdiColumnNbmfs())[0];
        } dbtdi(SQLExdfption sqlf) {
           //if not sft dbtdi tif fxdfption but do notiing now.
           boolColNbmf = truf;
        }

        if(boolColId && boolColNbmf) {
           // nfitifr sfttfr mftiods ibvf bffn usfd to sft
           tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.mbtdinotsft").toString());
        } flsf {
           //if(boolColId || boolColNbmf)
           // fitifr of tif sfttfr mftiods ibvf bffn sft.
           if(boolColId){
              //
              ArrbyList<Intfgfr> indidfs = nfw ArrbyList<>();
              for(int i=0;i<dRowsft.gftMbtdiColumnNbmfs().lfngti;i++) {
                  if( (strMbtdiKfy = (dRowsft.gftMbtdiColumnNbmfs())[i]) != null) {
                      iMbtdiKfy = dRowsft.findColumn(strMbtdiKfy);
                      indidfs.bdd(iMbtdiKfy);
                  }
                  flsf
                      brfbk;
              }
              int[] indfxfs = nfw int[indidfs.sizf()];
              for(int i=0; i<indidfs.sizf();i++)
                  indfxfs[i] = indidfs.gft(i);
              dRowsft.sftMbtdiColumn(indfxfs);
              // Sft tif mbtdi dolumn ifrf bfdbusf join will bf
              // bbsfd on dolumnId,
              // (nfstfd for loop in initJOIN() difdks for fqublity
              //  bbsfd on dolumnIndfx)
           } flsf {
              //do notiing, iMbtdiKfy is sft.
           }
           // Now boti iMbtdiKfy bnd strMbtdiKfy ibvf bffn sft pointing
           // to tif sbmf dolumn
        }

        // Till first rowsft sftJoinTypf mby not bf sft bfdbusf
        // dffbult typf is JoinRowSft.INNER_JOIN wiidi siould
        // bf sft bnd for subsfqufnt bdditions of rowsft, if not sft
        // kffp on bdding join typf bs JoinRowSft.INNER_JOIN
        // to vfdJoinTypf.

        initJOIN(dRowsft);
    }

    /**
     * Adds tif givfn <dodf>RowSft</dodf> objfdt to tif <dodf>JOIN</dodf> rflbtion
     * bnd sfts tif dfsignbtfd dolumn bs tif mbtdi dolumn.
     * If tif givfn <dodf>RowSft</dodf>
     * objfdt is tif first to bf bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, it forms tif bbsis of tif <dodf>JOIN</dodf> rflbtionsiip to bf formfd
     * wifn otifr <dodf>RowSft</dodf> objfdts brf bddfd .
     * <P>
     * Tiis mftiod siould bf usfd wifn tif givfn <dodf>RowSft</dodf> objfdt
     * dofs not blrfbdy ibvf b mbtdi dolumn sft.
     *
     * @pbrbm rowsft b <dodf>RowSft</dodf> objfdt to bf bddfd to
     *         tif <dodf>JOIN</dodf> rflbtion; must implfmfnt tif <dodf>Joinbblf</dodf>
     *         intfrfbdf
     * @pbrbm dolumnIdx bn <dodf>int</dodf> giving tif indfx of tif dolumn to bf sft bs
     *         tif mbtdi dolumn
     * @tirows SQLExdfption if (1) bn fmpty <dodf>RowSft</dodf> objfdt is bddfd to tiis
     *         <dodf>JoinRowSft</dodf> objfdt, (2) b mbtdi dolumn ibs not bffn sft,
     *         or (3) tif <dodf>RowSft</dodf> objfdt bfing bddfd violbtfs tif bdtivf
     *         <dodf>JOIN</dodf>
     * @sff CbdifdRowSft#unsftMbtdiColumn
     */
    publid void bddRowSft(RowSft rowsft, int dolumnIdx) tirows SQLExdfption {
        //pbssing tif rowsft bs wfll bs tif dolumnIdx to form tif joinrowsft.

        ((CbdifdRowSftImpl)rowsft).sftMbtdiColumn(dolumnIdx);

        bddRowSft((Joinbblf)rowsft);
    }

    /**
     * Adds tif givfn <dodf>RowSft</dodf> objfdt to tif <dodf>JOIN</dodf> rflbtionsiip
     * bnd sfts tif dfsignbtfd dolumn bs tif mbtdi dolumn. If tif givfn
     * <dodf>RowSft</dodf>
     * objfdt is tif first to bf bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, it forms tif bbsis of tif <dodf>JOIN</dodf> rflbtionsiip to bf formfd
     * wifn otifr <dodf>RowSft</dodf> objfdts brf bddfd .
     * <P>
     * Tiis mftiod siould bf usfd wifn tif givfn <dodf>RowSft</dodf> objfdt
     * dofs not blrfbdy ibvf b mbtdi dolumn sft.
     *
     * @pbrbm rowsft b <dodf>RowSft</dodf> objfdt to bf bddfd to
     *         tif <dodf>JOIN</dodf> rflbtion
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif nbmf of tif dolumn
     *        to bf sft bs tif mbtdi dolumn; must implfmfnt tif <dodf>Joinbblf</dodf>
     *        intfrfbdf
     * @tirows SQLExdfption if (1) bn fmpty <dodf>RowSft</dodf> objfdt is bddfd to tiis
     *         <dodf>JoinRowSft</dodf> objfdt, (2) b mbtdi dolumn ibs not bffn sft,
     *         or (3) tif <dodf>RowSft</dodf> objfdt bfing bddfd violbtfs tif bdtivf
     *         <dodf>JOIN</dodf>
     */
    publid void bddRowSft(RowSft rowsft, String dolumnNbmf) tirows SQLExdfption {
        //pbssing tif rowsft bs wfll bs tif dolumnIdx to form tif joinrowsft.
        ((CbdifdRowSftImpl)rowsft).sftMbtdiColumn(dolumnNbmf);
        bddRowSft((Joinbblf)rowsft);
    }

    /**
     * Adds tif givfn <dodf>RowSft</dodf> objfdts to tif <dodf>JOIN</dodf> rflbtionsiip
     * bnd sfts tif dfsignbtfd dolumns bs tif mbtdi dolumns. If tif first
     * <dodf>RowSft</dodf> objfdt in tif brrby of <dodf>RowSft</dodf> objfdts
     * is tif first to bf bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, it forms tif bbsis of tif <dodf>JOIN</dodf> rflbtionsiip to bf formfd
     * wifn otifr <dodf>RowSft</dodf> objfdts brf bddfd.
     * <P>
     * Tif first <dodf>int</dodf>
     * in <i>dolumnIdx</i> is usfd to sft tif mbtdi dolumn for tif first
     * <dodf>RowSft</dodf> objfdt in <i>rowsft</i>, tif sfdond <dodf>int</dodf>
     * in <i>dolumnIdx</i> is usfd to sft tif mbtdi dolumn for tif sfdond
     * <dodf>RowSft</dodf> objfdt in <i>rowsft</i>, bnd so on.
     * <P>
     * Tiis mftiod siould bf usfd wifn tif givfn <dodf>RowSft</dodf> objfdts
     * do not blrfbdy ibvf mbtdi dolumns sft.
     *
     * @pbrbm rowsft bn brrby of <dodf>RowSft</dodf> objfdts to bf bddfd to
     *         tif <dodf>JOIN</dodf> rflbtion; fbdi <dodf>RowSft</dodf> objfdt must
     *         implfmfnt tif <dodf>Joinbblf</dodf> intfrfbdf
     * @pbrbm dolumnIdx bn brrby of <dodf>int</dodf> vblufs dfsignbting tif dolumns
     *        to bf sft bs tif
     *        mbtdi dolumns for tif <dodf>RowSft</dodf> objfdts in <i>rowsft</i>
     * @tirows SQLExdfption if tif numbfr of <dodf>RowSft</dodf> objfdts in
     *         <i>rowsft</i> is not fqubl to tif numbfr of <dodf>int</dodf> vblufs
     *         in <i>dolumnIdx</i>
     */
    publid void bddRowSft(RowSft[] rowsft,
                          int[] dolumnIdx) tirows SQLExdfption {
    //vblidbtf if lfngti of rowsft brrby is sbmf bs lfngti of int brrby.
     if(rowsft.lfngti != dolumnIdx.lfngti) {
        tirow nfw SQLExdfption
             (rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.numnotfqubl").toString());
     } flsf {
        for(int i=0; i< rowsft.lfngti; i++) {
           ((CbdifdRowSftImpl)rowsft[i]).sftMbtdiColumn(dolumnIdx[i]);
           bddRowSft((Joinbblf)rowsft[i]);
        } //fnd for
     } //fnd if

   }


    /**
     * Adds tif givfn <dodf>RowSft</dodf> objfdts to tif <dodf>JOIN</dodf> rflbtionsiip
     * bnd sfts tif dfsignbtfd dolumns bs tif mbtdi dolumns. If tif first
     * <dodf>RowSft</dodf> objfdt in tif brrby of <dodf>RowSft</dodf> objfdts
     * is tif first to bf bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, it forms tif bbsis of tif <dodf>JOIN</dodf> rflbtionsiip to bf formfd
     * wifn otifr <dodf>RowSft</dodf> objfdts brf bddfd.
     * <P>
     * Tif first <dodf>String</dodf> objfdt
     * in <i>dolumnNbmf</i> is usfd to sft tif mbtdi dolumn for tif first
     * <dodf>RowSft</dodf> objfdt in <i>rowsft</i>, tif sfdond <dodf>String</dodf>
     * objfdt in <i>dolumnNbmf</i> is usfd to sft tif mbtdi dolumn for tif sfdond
     * <dodf>RowSft</dodf> objfdt in <i>rowsft</i>, bnd so on.
     * <P>
     * Tiis mftiod siould bf usfd wifn tif givfn <dodf>RowSft</dodf> objfdts
     * do not blrfbdy ibvf mbtdi dolumns sft.
     *
     * @pbrbm rowsft bn brrby of <dodf>RowSft</dodf> objfdts to bf bddfd to
     *         tif <dodf>JOIN</dodf> rflbtion; fbdi <dodf>RowSft</dodf> objfdt must
     *         implfmfnt tif <dodf>Joinbblf</dodf> intfrfbdf
     * @pbrbm dolumnNbmf bn brrby of <dodf>String</dodf> objfdts dfsignbting tif dolumns
     *        to bf sft bs tif
     *        mbtdi dolumns for tif <dodf>RowSft</dodf> objfdts in <i>rowsft</i>
     * @tirows SQLExdfption if tif numbfr of <dodf>RowSft</dodf> objfdts in
     *         <i>rowsft</i> is not fqubl to tif numbfr of <dodf>String</dodf> objfdts
     *         in <i>dolumnNbmf</i>, bn fmpty <dodf>JdbdRowSft</dodf> is bddfd to tif
     *         <dodf>JoinRowSft</dodf>, if b mbtdi dolumn is not sft,
     *         or onf or tif <dodf>RowSft</dodf> objfdts in <i>rowsft</i> violbtfs tif
     *         bdtivf <dodf>JOIN</dodf>
     */
    publid void bddRowSft(RowSft[] rowsft,
                          String[] dolumnNbmf) tirows SQLExdfption {
    //vblidbtf if lfngti of rowsft brrby is sbmf bs lfngti of int brrby.

     if(rowsft.lfngti != dolumnNbmf.lfngti) {
        tirow nfw SQLExdfption
                 (rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.numnotfqubl").toString());
     } flsf {
        for(int i=0; i< rowsft.lfngti; i++) {
           ((CbdifdRowSftImpl)rowsft[i]).sftMbtdiColumn(dolumnNbmf[i]);
           bddRowSft((Joinbblf)rowsft[i]);
        } //fnd for
     } //fnd if

    }

    /**
     * Rfturns b Collfdtion of tif <dodf>RowSft</dodf> objfdt instbndfs
     * durrfntly rfsiding witi tif instbndf of tif <dodf>JoinRowSft</dodf>
     * objfdt instbndf. Tiis siould rfturn tif 'n' numbfr of RowSft dontbinfd
     * witiin tif JOIN bnd mbintbin bny updbtfs tibt ibvf oddourfd wiilf in
     * tiis union.
     *
     * @rfturn A <dodf>Collfdtion</dodf> of tif bddfd <dodf>RowSft</dodf>
     * objfdt instbndfs
     * @tirows SQLExdfption if bn frror oddours gfnfrbting b dollfdtion
     * of tif originbting RowSfts dontbinfd witiin tif JOIN.
     */
    @SupprfssWbrnings("rbwtypfs")
    publid Collfdtion gftRowSfts() tirows SQLExdfption {
        rfturn vfdRowSftsInJOIN;
    }

    /**
     * Rfturns b string brrby of tif RowSft nbmfs durrfntly rfsiding
     * witi tif <dodf>JoinRowSft</dodf> objfdt instbndf.
     *
     * @rfturn b string brrby of tif RowSft nbmfs
     * @tirows SQLExdfption if bn frror oddours rftrifving tif RowSft nbmfs
     * @sff CbdifdRowSft#sftTbblfNbmf
     */
    publid String[] gftRowSftNbmfs() tirows SQLExdfption {
        Objfdt [] brr = vfdTbblfNbmfs.toArrby();
        String []strArr = nfw String[brr.lfngti];

        for( int i = 0;i < brr.lfngti; i++) {
           strArr[i] = brr[i].toString();
        }

        rfturn strArr;
    }

    /**
     * Crfbtfs b sfpbrbtf <dodf>CbdifdRowSft</dodf> objfdt tibt dontbins tif dbtb
     * in tiis <dodf>JoinRowSft</dodf> objfdt.
     * <P>
     * If bny updbtfs or modifidbtions ibvf bffn bpplifd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, tif <dodf>CbdifdRowSft</dodf> objfdt rfturnfd by tiis mftiod will
     * not bf bblf to pfrsist
     * tif dibngfs bbdk to tif originbting rows bnd tbblfs in tif
     * dbtb sourdf bfdbusf tif dbtb mby bf from difffrfnt tbblfs. Tif
     * <dodf>CbdifdRowSft</dodf> instbndf rfturnfd siould not
     * dontbin modifidbtion dbtb, sudi bs wiftifr b row ibs bffn updbtfd or wibt tif
     * originbl vblufs brf.  Also, tif <dodf>CbdifdRowSft</dodf> objfdt siould dlfbr
     * its  propfrtifs pfrtbining to
     * its originbting SQL stbtfmfnt. An bpplidbtion siould rfsft tif
     * SQL stbtfmfnt using tif <dodf>RowSft.sftCommbnd</dodf> mftiod.
     * <p>
     * To pfrsist dibngfs bbdk to tif dbtb sourdf, tif <dodf>JoinRowSft</dodf> objfdt
     * dblls tif mftiod <dodf>bddfptCibngfs</dodf>. Implfmfntbtions
     * dbn lfvfrbgf tif intfrnbl dbtb bnd updbtf trbdking in tifir
     * implfmfntbtions to intfrbdt witi tif <dodf>SyndProvidfr</dodf> to pfrsist bny
     * dibngfs.
     *
     * @rfturn b <dodf>CbdifdRowSft</dodf> objfdt dontbining tif dontfnts of tiis
     *         <dodf>JoinRowSft</dodf> objfdt
     * @tirows SQLExdfption if bn frror oddurs bssfmbling tif <dodf>CbdifdRowSft</dodf>
     *         objfdt
     * @sff jbvbx.sql.RowSft
     * @sff jbvbx.sql.rowsft.CbdifdRowSft
     * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
     */
    publid CbdifdRowSft toCbdifdRowSft() tirows SQLExdfption {
        rfturn drsIntfrnbl;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>JoinRowSft</dodf> objfdt supports
     * bn SQL <dodf>CROSS_JOIN</dodf> bnd <dodf>fblsf</dodf> if it dofs not.
     *
     * @rfturn <dodf>truf</dodf> if tif CROSS_JOIN is supportfd; <dodf>fblsf</dodf>
     *         otifrwisf
     */
    publid boolfbn supportsCrossJoin() {
        rfturn supportfdJOINs[JoinRowSft.CROSS_JOIN];
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>JoinRowSft</dodf> objfdt supports
     * bn SQL <dodf>INNER_JOIN</dodf> bnd <dodf>fblsf</dodf> if it dofs not.
     *
     * @rfturn truf is tif INNER_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsInnfrJoin() {
        rfturn supportfdJOINs[JoinRowSft.INNER_JOIN];
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>JoinRowSft</dodf> objfdt supports
     * bn SQL <dodf>LEFT_OUTER_JOIN</dodf> bnd <dodf>fblsf</dodf> if it dofs not.
     *
     * @rfturn truf is tif LEFT_OUTER_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsLfftOutfrJoin() {
        rfturn supportfdJOINs[JoinRowSft.LEFT_OUTER_JOIN];
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>JoinRowSft</dodf> objfdt supports
     * bn SQL <dodf>RIGHT_OUTER_JOIN</dodf> bnd <dodf>fblsf</dodf> if it dofs not.
     *
     * @rfturn truf is tif RIGHT_OUTER_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsRigitOutfrJoin() {
        rfturn supportfdJOINs[JoinRowSft.RIGHT_OUTER_JOIN];
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>JoinRowSft</dodf> objfdt supports
     * bn SQL <dodf>FULL_JOIN</dodf> bnd <dodf>fblsf</dodf> if it dofs not.
     *
     * @rfturn truf is tif FULL_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsFullJoin() {
        rfturn supportfdJOINs[JoinRowSft.FULL_JOIN];

    }

    /**
     * Sfts tif typf of SQL <dodf>JOIN</dodf> tibt tiis <dodf>JoinRowSft</dodf>
     * objfdt will usf. Tiis mftiod
     * bllows bn bpplidbtion to bdjust tif typf of <dodf>JOIN</dodf> imposfd
     * on tbblfs dontbinfd witiin tiis <dodf>JoinRowSft</dodf> objfdt bnd to do it
     * on tif fly. Tif lbst <dodf>JOIN</dodf> typf sft dftfrminfs tif typf of
     * <dodf>JOIN</dodf> to bf pfrformfd.
     * <P>
     * Implfmfntbtions siould tirow bn <dodf>SQLExdfption</dodf> if tify do
     * not support tif givfn <dodf>JOIN</dodf> typf.
     *
     * @pbrbm typf onf of tif stbndbrd <dodf>JoinRowSft</dodf> donstbnts
     *        indidbting tif typf of <dodf>JOIN</dodf>.  Must bf onf of tif
     *        following:
     *            <dodf>JoinRowSft.CROSS_JOIN</dodf>
     *            <dodf>JoinRowSft.INNER_JOIN</dodf>
     *            <dodf>JoinRowSft.LEFT_OUTER_JOIN</dodf>
     *            <dodf>JoinRowSft.RIGHT_OUTER_JOIN</dodf>, or
     *            <dodf>JoinRowSft.FULL_JOIN</dodf>
     * @tirows SQLExdfption if bn unsupportfd <dodf>JOIN</dodf> typf is sft
     */
    publid void sftJoinTypf(int typf) tirows SQLExdfption {
        // Tif join wiidi govfrns tif join of two rowsfts is tif lbst
        // join sft, using sftJoinTypf

       if (typf >= JoinRowSft.CROSS_JOIN && typf <= JoinRowSft.FULL_JOIN) {
           if (typf != JoinRowSft.INNER_JOIN) {
               // Tiis 'if' will bf rfmovfd bftfr bll joins brf implfmfntfd.
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.notsupportfd").toString());
           } flsf {
              Intfgfr Intgr = Intfgfr.vblufOf(JoinRowSft.INNER_JOIN);
              vfdJoinTypf.bdd(Intgr);
           }
       } flsf {
          tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.notdffinfd").toString());
       }  //fnd if
    }


    /**
     * Tiis difdks for b mbtdi dolumn for
     * wiftifr it fxists or not.
     *
     * @pbrbm <dodf>CbdifdRowSft</dodf> objfdt wiosf mbtdi dolumn nffds to bf difdkfd.
     * @tirows SQLExdfption if MbtdiColumn is not sft.
     */
    privbtf boolfbn difdkforMbtdiColumn(Joinbblf rs) tirows SQLExdfption {
        int[] i = rs.gftMbtdiColumnIndfxfs();
        if (i.lfngti <= 0) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Intfrnbl initiblizbtion of <dodf>JoinRowSft</dodf>.
     */
    privbtf void initJOIN(CbdifdRowSft rowsft) tirows SQLExdfption {
        try {

            CbdifdRowSftImpl dRowsft = (CbdifdRowSftImpl)rowsft;
            // Crfbtf b nfw CbdifdRowSft objfdt lodbl to tiis fundtion.
            CbdifdRowSftImpl drsTfmp = nfw CbdifdRowSftImpl();
            RowSftMftbDbtbImpl rsmd = nfw RowSftMftbDbtbImpl();

            /* Tif following 'if blodk' sffms to bf blwbys going truf.
               dommfnting tiis out for prfsfnt

            if (!supportfdJOINs[1]) {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.notsupportfd").toString());
            }

            */

            if (vfdRowSftsInJOIN.isEmpty() ) {

                // implifs first dRowsft to bf bddfd to tif Join
                // simply bdd tiis bs b CbdifdRowSft.
                // Also bdd it to tif dlbss vbribblf of typf vfdtor
                // do not nffd to difdk "typf" of Join but it siould bf sft.
                drsIntfrnbl = (CbdifdRowSftImpl)rowsft.drfbtfCopy();
                drsIntfrnbl.sftMftbDbtb((RowSftMftbDbtbImpl)dRowsft.gftMftbDbtb());
                // mftbdbtb will blso sft tif MbtdiColumn.

                vfdRowSftsInJOIN.bdd(dRowsft);

            } flsf {
                // At tiis point wf brf rfbdy to bdd bnotifr rowsft to 'tiis' objfdt
                // Cifdk tif sizf of vfdJoinTypf bnd vfdRowSftsInJoin

                // If notiing is bfing sft, intfrnblly dbll sftJoinTypf()
                // to sft to JoinRowSft.INNER_JOIN.

                // For two rowsfts onf (vblid) fntry siould bf tifrf in vfdJoinTypf
                // For tirff rowsfts two (vblid) fntrifs siould bf tifrf in vfdJoinTypf

                // Mbintbin vfdRowSftsInJoin = vfdJoinTypf + 1


                if( (vfdRowSftsInJOIN.sizf() - vfdJoinTypf.sizf() ) == 2 ) {
                   // wf brf going to bdd nfxt rowsft bnd sftJoinTypf ibs not bffn sft
                   // rfdfntly, so sft it to sftJoinTypf() to JoinRowSft.INNER_JOIN.
                   // tif dffbult join typf

                        sftJoinTypf(JoinRowSft.INNER_JOIN);
                } flsf if( (vfdRowSftsInJOIN.sizf() - vfdJoinTypf.sizf() ) == 1  ) {
                   // do notiing sftjoinTypf() ibs bffn sft by progrbmmfr
                }

                // Add tif tbblf nbmfs to tif dlbss vbribblf of typf vfdtor.
                vfdTbblfNbmfs.bdd(drsIntfrnbl.gftTbblfNbmf());
                vfdTbblfNbmfs.bdd(dRowsft.gftTbblfNbmf());
                // Now wf ibvf two rowsfts drsIntfrnbl bnd dRowsft wiidi nffd
                // to bf INNER JOIN'ED to form b nfw rowsft
                // Compbrf tbblf1.MbtdiColumn1.vbluf1 == { tbblf2.MbtdiColumn2.vbluf1
                //                              ... upto tbblf2.MbtdiColumn2.vblufN }
                //     ...
                // Compbrf tbblf1.MbtdiColumn1.vblufM == { tbblf2.MbtdiColumn2.vbluf1
                //                              ... upto tbblf2.MbtdiColumn2.vblufN }
                //
                // Assuming first rowsft ibs M rows bnd sfdond N rows.

                int rowCount2 = dRowsft.sizf();
                int rowCount1 = drsIntfrnbl.sizf();

                // totbl dolumns in tif nfw CbdifdRowSft will bf sum of boti -1
                // (dommon dolumn)
                int mbtdiColumnCount = 0;
                for(int i=0; i< drsIntfrnbl.gftMbtdiColumnIndfxfs().lfngti; i++) {
                    if(drsIntfrnbl.gftMbtdiColumnIndfxfs()[i] != -1)
                        ++ mbtdiColumnCount;
                    flsf
                        brfbk;
                }

                rsmd.sftColumnCount
                    (drsIntfrnbl.gftMftbDbtb().gftColumnCount() +
                     dRowsft.gftMftbDbtb().gftColumnCount() - mbtdiColumnCount);

                drsTfmp.sftMftbDbtb(rsmd);
                drsIntfrnbl.bfforfFirst();
                dRowsft.bfforfFirst();
                for (int i = 1 ; i <= rowCount1 ; i++) {
                  if(drsIntfrnbl.isAftfrLbst() ) {
                    brfbk;
                  }
                  if(drsIntfrnbl.nfxt()) {
                    dRowsft.bfforfFirst();
                    for(int j = 1 ; j <= rowCount2 ; j++) {
                         if( dRowsft.isAftfrLbst()) {
                            brfbk;
                         }
                         if(dRowsft.nfxt()) {
                             boolfbn mbtdi = truf;
                             for(int k=0; k<mbtdiColumnCount; k++) {
                                 if (!drsIntfrnbl.gftObjfdt( drsIntfrnbl.gftMbtdiColumnIndfxfs()[k]).fqubls
                                         (dRowsft.gftObjfdt(dRowsft.gftMbtdiColumnIndfxfs()[k]))) {
                                     mbtdi = fblsf;
                                     brfbk;
                                 }
                             }
                             if (mbtdi) {

                                int p;
                                int dold = 0;   // rfsft tiis vbribblf fvfrytimf you loop
                                // rf drfbtf b JoinRowSft in drsTfmp objfdt
                                drsTfmp.movfToInsfrtRow();

                                // drfbtf b nfw rowsft drsTfmp witi dbtb from first rowsft
                            for( p=1;
                                p<=drsIntfrnbl.gftMftbDbtb().gftColumnCount();p++) {

                                mbtdi = fblsf;
                                for(int k=0; k<mbtdiColumnCount; k++) {
                                 if (p == drsIntfrnbl.gftMbtdiColumnIndfxfs()[k] ) {
                                     mbtdi = truf;
                                     brfbk;
                                 }
                                }
                                    if ( !mbtdi ) {

                                    drsTfmp.updbtfObjfdt(++dold, drsIntfrnbl.gftObjfdt(p));
                                    // dolumn typf blso nffds to bf pbssfd.

                                    rsmd.sftColumnNbmf
                                        (dold, drsIntfrnbl.gftMftbDbtb().gftColumnNbmf(p));
                                    rsmd.sftTbblfNbmf(dold, drsIntfrnbl.gftTbblfNbmf());

                                    rsmd.sftColumnTypf(p, drsIntfrnbl.gftMftbDbtb().gftColumnTypf(p));
                                    rsmd.sftAutoIndrfmfnt(p, drsIntfrnbl.gftMftbDbtb().isAutoIndrfmfnt(p));
                                    rsmd.sftCbsfSfnsitivf(p, drsIntfrnbl.gftMftbDbtb().isCbsfSfnsitivf(p));
                                    rsmd.sftCbtblogNbmf(p, drsIntfrnbl.gftMftbDbtb().gftCbtblogNbmf(p));
                                    rsmd.sftColumnDisplbySizf(p, drsIntfrnbl.gftMftbDbtb().gftColumnDisplbySizf(p));
                                    rsmd.sftColumnLbbfl(p, drsIntfrnbl.gftMftbDbtb().gftColumnLbbfl(p));
                                    rsmd.sftColumnTypf(p, drsIntfrnbl.gftMftbDbtb().gftColumnTypf(p));
                                    rsmd.sftColumnTypfNbmf(p, drsIntfrnbl.gftMftbDbtb().gftColumnTypfNbmf(p));
                                    rsmd.sftCurrfndy(p,drsIntfrnbl.gftMftbDbtb().isCurrfndy(p) );
                                    rsmd.sftNullbblf(p, drsIntfrnbl.gftMftbDbtb().isNullbblf(p));
                                    rsmd.sftPrfdision(p, drsIntfrnbl.gftMftbDbtb().gftPrfdision(p));
                                    rsmd.sftSdblf(p, drsIntfrnbl.gftMftbDbtb().gftSdblf(p));
                                    rsmd.sftSdifmbNbmf(p, drsIntfrnbl.gftMftbDbtb().gftSdifmbNbmf(p));
                                    rsmd.sftSfbrdibblf(p, drsIntfrnbl.gftMftbDbtb().isSfbrdibblf(p));
                                    rsmd.sftSignfd(p, drsIntfrnbl.gftMftbDbtb().isSignfd(p));

                                } flsf {
                                    // will ibppfn only ondf, for tibt  mfrgfd dolumn pbss
                                    // tif typfs bs OBJECT, if typfs not fqubl

                                    drsTfmp.updbtfObjfdt(++dold, drsIntfrnbl.gftObjfdt(p));

                                    rsmd.sftColumnNbmf(dold, drsIntfrnbl.gftMftbDbtb().gftColumnNbmf(p));
                                    rsmd.sftTbblfNbmf
                                        (dold, drsIntfrnbl.gftTbblfNbmf()+
                                         "#"+
                                         dRowsft.gftTbblfNbmf());


                                    rsmd.sftColumnTypf(p, drsIntfrnbl.gftMftbDbtb().gftColumnTypf(p));
                                    rsmd.sftAutoIndrfmfnt(p, drsIntfrnbl.gftMftbDbtb().isAutoIndrfmfnt(p));
                                    rsmd.sftCbsfSfnsitivf(p, drsIntfrnbl.gftMftbDbtb().isCbsfSfnsitivf(p));
                                    rsmd.sftCbtblogNbmf(p, drsIntfrnbl.gftMftbDbtb().gftCbtblogNbmf(p));
                                    rsmd.sftColumnDisplbySizf(p, drsIntfrnbl.gftMftbDbtb().gftColumnDisplbySizf(p));
                                    rsmd.sftColumnLbbfl(p, drsIntfrnbl.gftMftbDbtb().gftColumnLbbfl(p));
                                    rsmd.sftColumnTypf(p, drsIntfrnbl.gftMftbDbtb().gftColumnTypf(p));
                                    rsmd.sftColumnTypfNbmf(p, drsIntfrnbl.gftMftbDbtb().gftColumnTypfNbmf(p));
                                    rsmd.sftCurrfndy(p,drsIntfrnbl.gftMftbDbtb().isCurrfndy(p) );
                                    rsmd.sftNullbblf(p, drsIntfrnbl.gftMftbDbtb().isNullbblf(p));
                                    rsmd.sftPrfdision(p, drsIntfrnbl.gftMftbDbtb().gftPrfdision(p));
                                    rsmd.sftSdblf(p, drsIntfrnbl.gftMftbDbtb().gftSdblf(p));
                                    rsmd.sftSdifmbNbmf(p, drsIntfrnbl.gftMftbDbtb().gftSdifmbNbmf(p));
                                    rsmd.sftSfbrdibblf(p, drsIntfrnbl.gftMftbDbtb().isSfbrdibblf(p));
                                    rsmd.sftSignfd(p, drsIntfrnbl.gftMftbDbtb().isSignfd(p));

                                    //don't do ++dold in tif bbovf stbtfmfnt
                                } //fnd if
                            } //fnd for


                            // bppfnd tif rowsft drsTfmp, witi dbtb from sfdond rowsft
                            for(int q=1;
                                q<= dRowsft.gftMftbDbtb().gftColumnCount();q++) {

                                mbtdi = fblsf;
                                for(int k=0; k<mbtdiColumnCount; k++) {
                                 if (q == dRowsft.gftMbtdiColumnIndfxfs()[k] ) {
                                     mbtdi = truf;
                                     brfbk;
                                 }
                                }
                                    if ( !mbtdi ) {

                                    drsTfmp.updbtfObjfdt(++dold, dRowsft.gftObjfdt(q));

                                    rsmd.sftColumnNbmf
                                        (dold, dRowsft.gftMftbDbtb().gftColumnNbmf(q));
                                    rsmd.sftTbblfNbmf(dold, dRowsft.gftTbblfNbmf());

                                    /**
                                      * Tiis will ibppfn for b spfdibl dbsf sdfnbrio. Tif vbluf of 'p'
                                      * will blwbys bf onf morf tibn tif numbfr of dolumns in tif first
                                      * rowsft in tif join. So, for b vbluf of 'q' wiidi is tif numbfr of
                                      * dolumns in tif sfdond rowsft tibt pbrtidipbtfs in tif join.
                                      * So dfdrfmfnt vbluf of 'p' by 1 flsf `p+q-1` will bf out of rbngf.
                                      **/

                                    //if((p+q-1) > ((drsIntfrnbl.gftMftbDbtb().gftColumnCount()) +
                                      //            (dRowsft.gftMftbDbtb().gftColumnCount())     - 1)) {
                                      // --p;
                                    //}
                                    rsmd.sftColumnTypf(p+q-1, dRowsft.gftMftbDbtb().gftColumnTypf(q));
                                    rsmd.sftAutoIndrfmfnt(p+q-1, dRowsft.gftMftbDbtb().isAutoIndrfmfnt(q));
                                    rsmd.sftCbsfSfnsitivf(p+q-1, dRowsft.gftMftbDbtb().isCbsfSfnsitivf(q));
                                    rsmd.sftCbtblogNbmf(p+q-1, dRowsft.gftMftbDbtb().gftCbtblogNbmf(q));
                                    rsmd.sftColumnDisplbySizf(p+q-1, dRowsft.gftMftbDbtb().gftColumnDisplbySizf(q));
                                    rsmd.sftColumnLbbfl(p+q-1, dRowsft.gftMftbDbtb().gftColumnLbbfl(q));
                                    rsmd.sftColumnTypf(p+q-1, dRowsft.gftMftbDbtb().gftColumnTypf(q));
                                    rsmd.sftColumnTypfNbmf(p+q-1, dRowsft.gftMftbDbtb().gftColumnTypfNbmf(q));
                                    rsmd.sftCurrfndy(p+q-1,dRowsft.gftMftbDbtb().isCurrfndy(q) );
                                    rsmd.sftNullbblf(p+q-1, dRowsft.gftMftbDbtb().isNullbblf(q));
                                    rsmd.sftPrfdision(p+q-1, dRowsft.gftMftbDbtb().gftPrfdision(q));
                                    rsmd.sftSdblf(p+q-1, dRowsft.gftMftbDbtb().gftSdblf(q));
                                    rsmd.sftSdifmbNbmf(p+q-1, dRowsft.gftMftbDbtb().gftSdifmbNbmf(q));
                                    rsmd.sftSfbrdibblf(p+q-1, dRowsft.gftMftbDbtb().isSfbrdibblf(q));
                                    rsmd.sftSignfd(p+q-1, dRowsft.gftMftbDbtb().isSignfd(q));
                                }
                                flsf {
                                    --p;
                                }
                            }
                            drsTfmp.insfrtRow();
                            drsTfmp.movfToCurrfntRow();

                        } flsf {
                            // sindf not fqub12
                            // so do notiing
                        } //fnd if
                         // bool1 = dRowsft.nfxt();
                         }

                    } // fnd innfr for
                     //bool2 = drsIntfrnbl.nfxt();
                   }

                } //fnd outfr for
                drsTfmp.sftMftbDbtb(rsmd);
                drsTfmp.sftOriginbl();

                // Now tif join is donf.
               // Mbkf drsIntfrnbl = drsTfmp, to bf rfbdy for nfxt mfrgf, if bt bll.

                int[] pCol = nfw int[mbtdiColumnCount];
                for(int i=0; i<mbtdiColumnCount; i++)
                   pCol[i] = drsIntfrnbl.gftMbtdiColumnIndfxfs()[i];

                drsIntfrnbl = (CbdifdRowSftImpl)drsTfmp.drfbtfCopy();

                // Bfdbusf wf bdd tif first rowsft bs drsIntfrnbl to tif
                // mfrgfd rowsft, so pCol will point to tif Mbtdi dolumn.
                // until rfsft, bm not surf wf siould sft tiis or not(?)
                // if tiis is not sft nfxt innfr join won't ibppfn
                // if wf fxpliditly do not sft b sft MbtdiColumn of
                // tif nfw drsIntfrnbl.

                drsIntfrnbl.sftMbtdiColumn(pCol);
                // Add tif mfrgfd rowsft to tif dlbss vbribblf of typf vfdtor.
                drsIntfrnbl.sftMftbDbtb(rsmd);
                vfdRowSftsInJOIN.bdd(dRowsft);
            } //fnd if
        } dbtdi(SQLExdfption sqlf) {
            // %%% Exdfption siould not dump ifrf:
            sqlf.printStbdkTrbdf();
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.initfrror").toString() + sqlf);
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("joinrowsftimpl.gfnfridfrr").toString() + f);
        }
    }

    /**
     * Rfturn b SQL-likf dfsdription of tif <dodf>WHERE</dodf> dlbusf bfing usfd
     * in b <dodf>JoinRowSft</dodf> objfdt instbndf. An implfmfntbtion dbn dfsdribf
     * tif <dodf>WHERE</dodf> dlbusf of tif SQL <dodf>JOIN</dodf> by supplying b <dodf>SQL</dodf>
     * strings dfsdription of <dodf>JOIN</dodf> or providf b tfxtubl dfsdription to bssist
     * bpplidbtions using b <dodf>JoinRowSft</dodf>.
     *
     * @rfturn wifrfClbusf b tfxtubl or SQL dfsdripition of tif logidbl
     * <dodf>WHERE</dodf> dlubsf usfd in tif <dodf>JoinRowSft</dodf> instbndf
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting b rfprfsfntbtion
     * of tif <dodf>WHERE</dodf> dlbusf.
     */
    publid String gftWifrfClbusf() tirows SQLExdfption {

       String strWifrfClbusf = "Sflfdt ";
       String wifrfClbusf;
       String tbbNbmf= "";
       String strTbbNbmf = "";
       int sz,dols;
       int j;
       CbdifdRowSftImpl drs;

       // gft bll tif dolumn(s) nbmfs from fbdi rowsft.
       // bppfnd tifm witi tifir tbblfnbmfs i.f. tbblfNbmf.dolumnNbmf
       // Sflfdt tbblfNbmf1.dolumnNbmf1,..., tbblfNbmfX.dolumnNbmfY
       // from tbblfNbmf1,...tbblfNbmfX wifrf
       // tbblfNbmf1.(rowsft1.gftMbtdiColumnNbmf()) ==
       // tbblfNbmf2.(rowsft2.gftMbtdiColumnNbmf()) + "bnd" +
       // tbblfNbmfX.(rowsftX.gftMbtdiColumnNbmf()) ==
       // tbblfNbmfZ.(rowsftZ.gftMbtdiColumnNbmf()));

       sz = vfdRowSftsInJOIN.sizf();
       for(int i=0;i<sz; i++) {
          drs = vfdRowSftsInJOIN.gft(i);
          dols = drs.gftMftbDbtb().gftColumnCount();
          tbbNbmf = tbbNbmf.dondbt(drs.gftTbblfNbmf());
          strTbbNbmf = strTbbNbmf.dondbt(tbbNbmf+", ");
          j = 1;
          wiilf(j<dols) {

            strWifrfClbusf = strWifrfClbusf.dondbt
                (tbbNbmf+"."+drs.gftMftbDbtb().gftColumnNbmf(j++));
            strWifrfClbusf = strWifrfClbusf.dondbt(", ");
          } //fnd wiilf
        } //fnd for


        // now rfmovf tif lbst ","
        strWifrfClbusf = strWifrfClbusf.substring
             (0, strWifrfClbusf.lbstIndfxOf(','));

        // Add from dlbusf
        strWifrfClbusf = strWifrfClbusf.dondbt(" from ");

        // Add tif tbblf nbmfs.
        strWifrfClbusf = strWifrfClbusf.dondbt(strTbbNbmf);

        //Rfmovf tif lbst ","
        strWifrfClbusf = strWifrfClbusf.substring
             (0, strWifrfClbusf.lbstIndfxOf(','));

        // Add tif wifrf dlbusf
        strWifrfClbusf = strWifrfClbusf.dondbt(" wifrf ");

        // Gft tif mbtdi dolumns
        // rowsft1.gftMbtdiColumnNbmf() == rowsft2.gftMbtdiColumnNbmf()
         for(int i=0;i<sz; i++) {
             strWifrfClbusf = strWifrfClbusf.dondbt(
               vfdRowSftsInJOIN.gft(i).gftMbtdiColumnNbmfs()[0]);
             if(i%2!=0) {
               strWifrfClbusf = strWifrfClbusf.dondbt("=");
             }  flsf {
               strWifrfClbusf = strWifrfClbusf.dondbt(" bnd");
             }
          strWifrfClbusf = strWifrfClbusf.dondbt(" ");
         }

        rfturn strWifrfClbusf;
    }


    /**
     * Movfs tif dursor down onf row from its durrfnt position bnd
     * rfturns <dodf>truf</dodf> if tif nfw dursor position is b
     * vblid row.
     * Tif dursor for b nfw <dodf>RfsultSft</dodf> objfdt is initiblly
     * positionfd bfforf tif first row. Tif first dbll to tif mftiod
     * <dodf>nfxt</dodf> movfs tif dursor to tif first row, mbking it
     * tif durrfnt row; tif sfdond dbll mbkfs tif sfdond row tif
     * durrfnt row, bnd so on.
     *
     * <P>If bn input strfbm from tif prfvious row is opfn, it is
     * impliditly dlosfd. Tif <dodf>RfsultSft</dodf> objfdt's wbrning
     * dibin is dlfbrfd wifn b nfw row is rfbd.
     *
     * @rfturn <dodf>truf</dodf> if tif nfw durrfnt row is vblid;
     *         <dodf>fblsf</dodf> if tifrf brf no morf rows
     * @tirows SQLExdfption if bn frror oddurs or
     *            tif dursor is not positionfd in tif rowsft, bfforf
     *            tif first row, or bftfr tif lbst row
     */
    publid boolfbn nfxt() tirows SQLExdfption {
        rfturn drsIntfrnbl.nfxt();
    }


    /**
     * Rflfbsfs tif durrfnt dontfnts of tiis rowsft, disdbrding  outstbnding
     * updbtfs.  Tif rowsft dontbins no rows bftfr tif mftiod
     * <dodf>rflfbsf</dodf> is dbllfd. Tiis mftiod sfnds b
     * <dodf>RowSftCibngfdEvfnt</dodf> objfdt to bll rfgistfrfd listfnfrs prior
     * to rfturning.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void dlosf() tirows SQLExdfption {
        drsIntfrnbl.dlosf();
    }


    /**
     * Rfports wiftifr tif lbst dolumn rfbd wbs SQL <dodf>NULL</dodf>.
     * Notf tibt you must first dbll tif mftiod <dodf>gftXXX</dodf>
     * on b dolumn to try to rfbd its vbluf bnd tifn dbll tif mftiod
     * <dodf>wbsNull</dodf> to dftfrminf wiftifr tif vbluf wbs
     * SQL <dodf>NULL</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif vbluf in tif lbst dolumn rfbd
     *         wbs SQL <dodf>NULL</dodf>; <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn wbsNull() tirows SQLExdfption {
        rfturn drsIntfrnbl.wbsNull();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>String</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds or
     *            tif dursor is not on b vblid row
     */
    publid String gftString(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftString(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>boolfbn</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>fblsf</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid boolfbn gftBoolfbn(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBoolfbn(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>bytf</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid bytf gftBytf(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBytf(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
             * <dodf>siort</dodf> vbluf.
             *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid siort gftSiort(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftSiort(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>siort</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid int gftInt(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftInt(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>long</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid long gftLong(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftLong(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>flobt</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid flobt gftFlobt(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftFlobt(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>doublf</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid doublf gftDoublf(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftDoublf(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     * <P>
     * Tiis mftiod is dfprfdbtfd; usf tif vfrsion of <dodf>gftBigDfdimbl</dodf>
     * tibt dofs not tbkf b sdblf pbrbmftfr bnd rfturns b vbluf witi full
     * prfdision.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point in tif
     *        vbluf rfturnfd
     * @rfturn tif dolumn vbluf witi tif spfdififd numbfr of digits to tif rigit
     *         of tif dfdimbl point; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid BigDfdimbl gftBigDfdimbl(int dolumnIndfx, int sdblf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBigDfdimbl(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>bytf brrby</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tif tif vbluf to bf
     *            rftrifvfd is not binbry
     */
    publid bytf[] gftBytfs(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBytfs(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.sql.Dbtf gftDbtf(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftDbtf(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timf</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.sql.Timf gftTimf(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftTimf(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftTimfstbmp(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.io.InputStrfbm gftAsdiiStrfbm(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftAsdiiStrfbm(dolumnIndfx);
    }

    /**
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of Unidodf dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf LONGVARCHAR vblufs.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into Unidodf.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. . Also, b
     * strfbm mby rfturn 0 for bvbilbblf() wiftifr tifrf is dbtb
     * bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of two bytf Unidodf dibrbdtfrs.  If tif vbluf is SQL NULL
     * tifn tif rfsult is null.
     * @tirows SQLExdfption if bn frror oddurs
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid jbvb.io.InputStrfbm gftUnidodfStrfbm(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftUnidodfStrfbm(dolumnIndfx);
    }

    /**
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of unintfrprftfd bytfs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf LONGVARBINARY vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn 0 for bvbilbblf() wiftifr tifrf is dbtb
     * bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of unintfrprftfd bytfs.  If tif vbluf is SQL NULL
     * tifn tif rfsult is null.
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBinbryStrfbm(dolumnIndfx);
    }

    // ColumnNbmf mftiods

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>String</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid String gftString(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftString(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>boolfbn</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>fblsf</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid boolfbn gftBoolfbn(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBoolfbn(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>bytf</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid bytf gftBytf(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBytf(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>siort</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid siort gftSiort(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftSiort(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs bn <dodf>int</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid int gftInt(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftInt(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>long</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid long gftLong(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftLong(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>flobt</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid flobt gftFlobt(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftFlobt(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>doublf</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid doublf gftDoublf(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftDoublf(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     * @dfprfdbtfd usf tif mftiod <dodf>gftBigDfdimbl(String dolumnNbmf)</dodf>
     *             instfbd
     */
    @Dfprfdbtfd
    publid BigDfdimbl gftBigDfdimbl(String dolumnNbmf, int sdblf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBigDfdimbl(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b bytf brrby.
     * Tif bytfs rfprfsfnt tif rbw vblufs rfturnfd by tif drivfr.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid bytf[] gftBytfs(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBytfs(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid jbvb.sql.Dbtf gftDbtf(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftDbtf(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Timf</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid jbvb.sql.Timf gftTimf(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftTimf(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftTimfstbmp(dolumnNbmf);
    }

    /**
     * Tiis mftiod is not supportfd, bnd it will tirow bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> if it is dbllfd.
     * <P>
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of ASCII dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf LONGVARCHAR vblufs.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into ASCII formbt.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of onf-bytf ASCII dibrbdtfrs.  If tif vbluf is SQL
     *         <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is dbllfd
     */
    publid jbvb.io.InputStrfbm gftAsdiiStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftAsdiiStrfbm(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.io.InputStrfbm</dodf> objfdt.
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of Unidodf dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.
     * Tif JDBC drivfr will do bny nfdfssbry donvfrsion from tif dbtbbbsf
     * formbt into Unidodf.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of two-bytf Unidodf dibrbdtfrs.  If tif vbluf is
     *         SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     * @dfprfdbtfd usf tif mftiod <dodf>gftCibrbdtfrStrfbm</dodf> instfbd
     */
    @Dfprfdbtfd
    publid jbvb.io.InputStrfbm gftUnidodfStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftUnidodfStrfbm(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.io.InputStrfbm</dodf> objfdt.
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of unintfrprftfd bytfs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf> vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b gft mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of unintfrprftfd bytfs.  If tif vbluf is SQL
     *         <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBinbryStrfbm(dolumnNbmf);
    }

    /* Tif first wbrning rfportfd by dblls on tiis <dodf>JoinRowSftImpl</dodf>
     * objfdt is rfturnfd. Subsfqufnt <dodf>JoinRowSftImpl</dodf> wbrnings will
     * bf dibinfd to tiis <dodf>SQLWbrning</dodf>.
     *
     * <P>Tif wbrning dibin is butombtidblly dlfbrfd fbdi timf b nfw
     * row is rfbd.
     *
     * <P><B>Notf:</B> Tiis wbrning dibin only dovfrs wbrnings dbusfd
     * by <dodf>RfsultSft</dodf> mftiods.  Any wbrning dbusfd by stbtfmfnt
     * mftiods (sudi bs rfbding OUT pbrbmftfrs) will bf dibinfd on tif
     * <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * @rfturn tif first SQLWbrning or null
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is dbllfd
     */
    publid SQLWbrning gftWbrnings() {
        rfturn drsIntfrnbl.gftWbrnings();
    }

    /**
     * Tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf> if dbllfd.
     * <P>
     * Aftfr b dbll to tiis mftiod, tif <dodf>gftWbrnings</dodf> mftiod
     * rfturns <dodf>null</dodf> until b nfw wbrning is rfportfd for tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is dbllfd
     */
     publid void dlfbrWbrnings() {
        drsIntfrnbl.dlfbrWbrnings();
    }

    /**
     * Rftrifvfs tif nbmf of tif SQL dursor usfd by tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt.
     *
     * <P>In SQL, b rfsult tbblf is rftrifvfd tirougi b dursor tibt is
     * nbmfd. Tif durrfnt row of b rfsult dbn bf updbtfd or dflftfd
     * using b positionfd updbtf/dflftf stbtfmfnt tibt rfffrfndfs tif
     * dursor nbmf. To insurf tibt tif dursor ibs tif propfr isolbtion
     * lfvfl to support bn updbtf opfrbtion, tif dursor's <dodf>SELECT</dodf>
     * stbtfmfnt siould bf of tif form 'sflfdt for updbtf'. If tif 'for updbtf'
     * dlbusf is omittfd, positionfd updbtfs mby fbil.
     *
     * <P>JDBC supports tiis SQL ffbturf by providing tif nbmf of tif
     * SQL dursor usfd by b <dodf>RfsultSft</dodf> objfdt. Tif durrfnt row
     * of b rfsult sft is blso tif durrfnt row of tiis SQL dursor.
     *
     * <P><B>Notf:</B> If positionfd updbtfs brf not supportfd, bn
     * <dodf>SQLExdfption</dodf> is tirown.
     *
     * @rfturn tif SQL dursor nbmf for tiis <dodf>JoinRowSftImpl</dodf> objfdt's
     *         dursor
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid String gftCursorNbmf() tirows SQLExdfption {
        rfturn drsIntfrnbl.gftCursorNbmf();
    }

    /**
     * Rftrifvfs tif <dodf>RfsultSftMftbDbtb</dodf> objfdt tibt dontbins
     * informbtion bbout tiis <dodf>CbdifdRowsSft</dodf> objfdt. Tif
     * informbtion indludfs tif numbfr of dolumns, tif dbtb typf for fbdi
     * dolumn, bnd otifr propfrtifs for fbdi dolumn.
     *
     * @rfturn tif <dodf>RfsultSftMftbDbtb</dodf> objfdt tibt dfsdribfs tiis
     *         <dodf>JoinRowSftImpl</dodf> objfdt's dolumns
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid RfsultSftMftbDbtb gftMftbDbtb() tirows SQLExdfption {
        rfturn drsIntfrnbl.gftMftbDbtb();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs bn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tif typf of tif <dodf>Objfdt</dodf> will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC
     * spfdifidbtion.
     * <P>
     * Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     * <P>
     * Tiis implfmfntbtion of tif mftiod <dodf>gftObjfdt</dodf> fxtfnds its
     * bfibvior so tibt it gfts tif bttributfs of bn SQL strudturfd typf bs
     * bs bn brrby of <dodf>Objfdt</dodf> vblufs.  Tiis mftiod blso dustom
     * mbps SQL usfr-dffinfd typfs to dlbssfs in tif Jbvb progrbmming lbngubgf.
     * Wifn tif spfdififd dolumn dontbins
     * b strudturfd or distindt vbluf, tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to tif mftiod <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tifrf is b problfm gftting
     *            tif <dodf>Clbss</dodf> objfdt for b dustom mbpping
     * @sindf 1.2
     */
    publid Objfdt gftObjfdt(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftObjfdt(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs bn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tif typf of tif <dodf>Objfdt</dodf> will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC
     * spfdifidbtion.
     * <P>
     * Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     * <P>
     * Tiis implfmfntbtion of tif mftiod <dodf>gftObjfdt</dodf> fxtfnds its
     * bfibvior so tibt it gfts tif bttributfs of bn SQL strudturfd typf bs
     * bs bn brrby of <dodf>Objfdt</dodf> vblufs.  Tiis mftiod blso dustom
     * mbps SQL usfr-dffinfd typfs to dlbssfs
     * in tif Jbvb progrbmming lbngubgf. Wifn tif spfdififd dolumn dontbins
     * b strudturfd or distindt vbluf, tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to tif mftiod <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *         is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *         bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt siowing tif mbpping
     *         from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming
     *         lbngubgf
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is
     *         <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi
     *         onf of tiis rowsft's dolumn nbmfs, (2) tif dursor is not
     *         on b vblid row, or (3) tifrf is b problfm gftting
     *         tif <dodf>Clbss</dodf> objfdt for b dustom mbpping
     */
    publid Objfdt gftObjfdt(int dolumnIndfx,
                            jbvb.util.Mbp<String,Clbss<?>> mbp)
    tirows SQLExdfption {
        rfturn drsIntfrnbl.gftObjfdt(dolumnIndfx, mbp);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs bn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tif typf of tif <dodf>Objfdt</dodf> will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC
     * spfdifidbtion.
     * <P>
     * Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     * <P>
     * Tiis implfmfntbtion of tif mftiod <dodf>gftObjfdt</dodf> fxtfnds its
     * bfibvior so tibt it gfts tif bttributfs of bn SQL strudturfd typf bs
     * bs bn brrby of <dodf>Objfdt</dodf> vblufs.  Tiis mftiod blso dustom
     * mbps SQL usfr-dffinfd typfs to dlbssfs
     * in tif Jbvb progrbmming lbngubgf. Wifn tif spfdififd dolumn dontbins
     * b strudturfd or distindt vbluf, tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to tif mftiod <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf;
     *        if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is
     *        <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi
     *        onf of tiis rowsft's dolumn nbmfs, (2) tif dursor is not
     *        on b vblid row, or (3) tifrf is b problfm gftting
     *        tif <dodf>Clbss</dodf> objfdt for b dustom mbpping
     */
    publid Objfdt gftObjfdt(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftObjfdt(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs bn <dodf>Objfdt</dodf> in
     * tif Jbvb progrbmming lbnugbgf, using tif givfn
     * <dodf>jbvb.util.Mbp</dodf> objfdt to dustom mbp tif vbluf if
     * bppropribtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt siowing tif mbpping
     *            from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming
     *            lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting tif SQL vbluf
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds or
     *            tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row
     */
    publid Objfdt gftObjfdt(String dolumnNbmf,
                            jbvb.util.Mbp<String,Clbss<?>> mbp)
        tirows SQLExdfption {
        rfturn drsIntfrnbl.gftObjfdt(dolumnNbmf, mbp);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b Jbvb dibrbdtfr strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b <dodf>jbvb.io.Rfbdfr</dodf> objfdt.  If tif vbluf is
     *         SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tifrf is b typf mismbtdi
     */
    publid jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftCibrbdtfrStrfbm(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of two-bytf Unidodf dibrbdtfrs.  If tif vbluf is
     *         SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tifrf is b typf mismbtdi
     */
    publid jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftCibrbdtfrStrfbm(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b <dodf>jbvb.mbti.BigDfdimbl</dodf> vbluf witi full prfdision;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid BigDfdimbl gftBigDfdimbl(int dolumnIndfx) tirows SQLExdfption {
       rfturn drsIntfrnbl.gftBigDfdimbl(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>jbvb.mbti.BigDfdimbl</dodf> vbluf witi full prfdision;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid BigDfdimbl gftBigDfdimbl(String dolumnNbmf) tirows SQLExdfption {
       rfturn drsIntfrnbl.gftBigDfdimbl(dolumnNbmf);
    }

    /**
     * Rfturns tif numbfr of rows in tiis <dodf>JoinRowSftImpl</dodf> objfdt.
     *
     * @rfturn numbfr of rows in tif rowsft
     */
    publid int sizf() {
        rfturn drsIntfrnbl.sizf();
    }

    /**
     * Indidbtfs wiftifr tif dursor is bfforf tif first row in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bfforf tif first row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isBfforfFirst() tirows SQLExdfption {
        rfturn drsIntfrnbl.isBfforfFirst();
    }

    /**
     * Indidbtfs wiftifr tif dursor is bftfr tif lbst row in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bftfr tif lbst row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isAftfrLbst() tirows SQLExdfption {
        rfturn drsIntfrnbl.isAftfrLbst();
    }

    /**
     * Indidbtfs wiftifr tif dursor is on tif first row in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif first row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isFirst() tirows SQLExdfption {
        rfturn drsIntfrnbl.isFirst();
    }

    /**
     * Indidbtfs wiftifr tif dursor is on tif lbst row in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt.
     * <P>
     * Notf: Cblling tif mftiod <dodf>isLbst</dodf> mby bf fxpfnsivf
     * bfdbusf tif JDBC drivfr migit nffd to fftdi bifbd onf row in ordfr
     * to dftfrminf wiftifr tif durrfnt row is tif lbst row in tiis rowsft.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif lbst row;
     *         <dodf>fblsf</dodf> otifrwisf or if tiis rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isLbst() tirows SQLExdfption {
        rfturn drsIntfrnbl.isLbst();
    }

    /**
     * Movfs tiis <dodf>JoinRowSftImpl</dodf> objfdt's dursor to tif front of
     * tif rowsft, just bfforf tif first row. Tiis mftiod ibs no ffffdt if
     * tiis rowsft dontbins no rows.
     *
     * @tirows SQLExdfption if bn frror oddurs or tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid void bfforfFirst() tirows SQLExdfption {
        drsIntfrnbl.bfforfFirst();
    }

    /**
     * Movfs tiis <dodf>JoinRowSftImpl</dodf> objfdt's dursor to tif fnd of
     * tif rowsft, just bftfr tif lbst row. Tiis mftiod ibs no ffffdt if
     * tiis rowsft dontbins no rows.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void bftfrLbst() tirows SQLExdfption {
        drsIntfrnbl.bftfrLbst();
    }

    /**
     * Movfs tiis <dodf>JoinRowSftImpl</dodf> objfdt's dursor to tif first row
     * bnd rfturns <dodf>truf</dodf> if tif opfrbtion wbs suddfssful.  Tiis
     * mftiod blso notififs rfgistfrfd listfnfrs tibt tif dursor ibs movfd.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     *         <dodf>fblsf</dodf> otifrwisf or if tifrf brf no rows in tiis
     *         <dodf>JoinRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn first() tirows SQLExdfption {
        rfturn drsIntfrnbl.first();
    }


    /**
     * Movfs tiis <dodf>JoinRowSftImpl</dodf> objfdt's dursor to tif lbst row
     * bnd rfturns <dodf>truf</dodf> if tif opfrbtion wbs suddfssful.  Tiis
     * mftiod blso notififs rfgistfrfd listfnfrs tibt tif dursor ibs movfd.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     *         <dodf>fblsf</dodf> otifrwisf or if tifrf brf no rows in tiis
     *         <dodf>JoinRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn lbst() tirows SQLExdfption {
        rfturn drsIntfrnbl.lbst();
    }

    /**
     * Rfturns tif numbfr of tif durrfnt row in tiis <dodf>JoinRowSftImpl</dodf>
     * objfdt. Tif first row is numbfr 1, tif sfdond numbfr 2, bnd so on.
     *
     * @rfturn tif numbfr of tif durrfnt row;  <dodf>0</dodf> if tifrf is no
     *         durrfnt row
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid int gftRow() tirows SQLExdfption {
        rfturn drsIntfrnbl.gftRow();
    }

    /**
     * Movfs tiis <dodf>JoinRowSftImpl</dodf> objfdt's dursor to tif row numbfr
     * spfdififd.
     *
     * <p>If tif numbfr is positivf, tif dursor movfs to bn bbsolutf row witi
     * rfspfdt to tif bfginning of tif rowsft.  Tif first row is row 1, tif sfdond
     * is row 2, bnd so on.  For fxbmplf, tif following dommbnd, in wiidi
     * <dodf>drs</dodf> is b <dodf>JoinRowSftImpl</dodf> objfdt, movfs tif dursor
     * to tif fourti row, stbrting from tif bfginning of tif rowsft.
     * <PRE><dodf>
     *
     *    drs.bbsolutf(4);
     *
     * </dodf> </PRE>
     * <P>
     * If tif numbfr is nfgbtivf, tif dursor movfs to bn bbsolutf row position
     * witi rfspfdt to tif fnd of tif rowsft.  For fxbmplf, dblling
     * <dodf>bbsolutf(-1)</dodf> positions tif dursor on tif lbst row,
     * <dodf>bbsolutf(-2)</dodf> movfs it on tif nfxt-to-lbst row, bnd so on.
     * If tif <dodf>JoinRowSftImpl</dodf> objfdt <dodf>drs</dodf> ibs fivf rows,
     * tif following dommbnd movfs tif dursor to tif fourti-to-lbst row, wiidi
     * in tif dbsf of b  rowsft witi fivf rows, is blso tif sfdond row, dounting
     * from tif bfginning.
     * <PRE><dodf>
     *
     *    drs.bbsolutf(-4);
     *
     * </dodf> </PRE>
     *
     * If tif numbfr spfdififd is lbrgfr tibn tif numbfr of rows, tif dursor
     * will movf to tif position bftfr tif lbst row. If tif numbfr spfdififd
     * would movf tif dursor onf or morf rows bfforf tif first row, tif dursor
     * movfs to tif position bfforf tif first row.
     * <P>
     * Notf: Cblling <dodf>bbsolutf(1)</dodf> is tif sbmf bs dblling tif
     * mftiod <dodf>first()</dodf>.  Cblling <dodf>bbsolutf(-1)</dodf> is tif
     * sbmf bs dblling <dodf>lbst()</dodf>.
     *
     * @pbrbm row b positivf numbfr to indidbtf tif row, stbrting row numbfring from
     *        tif first row, wiidi is <dodf>1</dodf>; b nfgbtivf numbfr to indidbtf
     *        tif row, stbrting row numbfring from tif lbst row, wiidi is
     *        <dodf>-1</dodf>; must not bf <dodf>0</dodf>
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif rowsft; <dodf>fblsf</dodf>
     *         otifrwisf
     * @tirows SQLExdfption if tif givfn dursor position is <dodf>0</dodf> or tif
     *            typf of tiis rowsft is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn bbsolutf(int row) tirows SQLExdfption {
        rfturn drsIntfrnbl.bbsolutf(row);
    }

    /**
     * Movfs tif dursor tif spfdififd numbfr of rows from tif durrfnt
     * position, witi b positivf numbfr moving it forwbrd bnd b
     * nfgbtivf numbfr moving it bbdkwbrd.
     * <P>
     * If tif numbfr is positivf, tif dursor movfs tif spfdififd numbfr of
     * rows towbrd tif fnd of tif rowsft, stbrting bt tif durrfnt row.
     * For fxbmplf, tif following dommbnd, in wiidi
     * <dodf>drs</dodf> is b <dodf>JoinRowSftImpl</dodf> objfdt witi 100 rows,
     * movfs tif dursor forwbrd four rows from tif durrfnt row.  If tif
     * durrfnt row is 50, tif dursor would movf to row 54.
     * <PRE><dodf>
     *
     *    drs.rflbtivf(4);
     *
     * </dodf> </PRE>
     * <P>
     * If tif numbfr is nfgbtivf, tif dursor movfs bbdk towbrd tif bfginning
     * tif spfdififd numbfr of rows, stbrting bt tif durrfnt row.
     * For fxbmplf, dblling tif mftiod
     * <dodf>bbsolutf(-1)</dodf> positions tif dursor on tif lbst row,
     * <dodf>bbsolutf(-2)</dodf> movfs it on tif nfxt-to-lbst row, bnd so on.
     * If tif <dodf>JoinRowSftImpl</dodf> objfdt <dodf>drs</dodf> ibs fivf rows,
     * tif following dommbnd movfs tif dursor to tif fourti-to-lbst row, wiidi
     * in tif dbsf of b  rowsft witi fivf rows, is blso tif sfdond row
     * from tif bfginning.
     * <PRE><dodf>
     *
     *    drs.bbsolutf(-4);
     *
     * </dodf> </PRE>
     *
     * If tif numbfr spfdififd is lbrgfr tibn tif numbfr of rows, tif dursor
     * will movf to tif position bftfr tif lbst row. If tif numbfr spfdififd
     * would movf tif dursor onf or morf rows bfforf tif first row, tif dursor
     * movfs to tif position bfforf tif first row. In boti dbsfs, tiis mftiod
     * tirows bn <dodf>SQLExdfption</dodf>.
     * <P>
     * Notf: Cblling <dodf>bbsolutf(1)</dodf> is tif sbmf bs dblling tif
     * mftiod <dodf>first()</dodf>.  Cblling <dodf>bbsolutf(-1)</dodf> is tif
     * sbmf bs dblling <dodf>lbst()</dodf>.  Cblling <dodf>rflbtivf(0)</dodf>
     * is vblid, but it dofs not dibngf tif dursor position.
     *
     * @pbrbm rows bn <dodf>int</dodf> indidbting tif numbfr of rows to movf
     *             tif dursor, stbrting bt tif durrfnt row; b positivf numbfr
     *             movfs tif dursor forwbrd; b nfgbtivf numbfr movfs tif dursor
     *             bbdkwbrd; must not movf tif dursor pbst tif vblid
     *             rows
     * @rfturn <dodf>truf</dodf> if tif dursor is on b row in tiis
     *         <dodf>JoinRowSftImpl</dodf> objfdt; <dodf>fblsf</dodf>
     *         otifrwisf
     * @tirows SQLExdfption if tifrf brf no rows in tiis rowsft, tif dursor is
     *         positionfd fitifr bfforf tif first row or bftfr tif lbst row, or
     *         tif rowsft is typf <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn rflbtivf(int rows) tirows SQLExdfption {
        rfturn drsIntfrnbl.rflbtivf(rows);
    }

    /**
     * Movfs tiis <dodf>JoinRowSftImpl</dodf> objfdt's dursor to tif
     * prfvious row bnd rfturns <dodf>truf</dodf> if tif dursor is on
     * b vblid row or <dodf>fblsf</dodf> if it is not.
     * Tiis mftiod blso notififs bll listfnfrs rfgistfrfd witi tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt tibt its dursor ibs movfd.
     * <P>
     * Notf: dblling tif mftiod <dodf>prfvious()</dodf> is not tif sbmf
     * bs dblling tif mftiod <dodf>rflbtivf(-1)</dodf>.  Tiis is truf
     * bfdbusf it is possiblf to dbll <dodf>prfvious()</dodf> from tif insfrt
     * row, from bftfr tif lbst row, or from tif durrfnt row, wifrfbs
     * <dodf>rflbtivf</dodf> mby only bf dbllfd from tif durrfnt row.
     * <P>
     * Tif mftiod <dodf>prfvious</dodf> mby usfd in b <dodf>wiilf</dodf>
     * loop to itfrbtf tirougi b rowsft stbrting bftfr tif lbst row
     * bnd moving towbrd tif bfginning. Tif loop fnds wifn <dodf>prfvious</dodf>
     * rfturns <dodf>fblsf</dodf>, mfbning tibt tifrf brf no morf rows.
     * For fxbmplf, tif following dodf frbgmfnt rftrifvfs bll tif dbtb in
     * tif <dodf>JoinRowSftImpl</dodf> objfdt <dodf>drs</dodf>, wiidi ibs
     * tirff dolumns.  Notf tibt tif dursor must initiblly bf positionfd
     * bftfr tif lbst row so tibt tif first dbll to tif mftiod
     * <dodf>prfvious</dodf> plbdfs tif dursor on tif lbst linf.
     * <PRE> <dodf>
     *
     *     drs.bftfrLbst();
     *     wiilf (prfvious()) {
     *         String nbmf = drs.gftString(1);
     *         int bgf = drs.gftInt(2);
     *         siort ssn = drs.gftSiort(3);
     *         Systfm.out.println(nbmf + "   " + bgf + "   " + ssn);
     *     }
     *
     * </dodf> </PRE>
     * Tiis mftiod tirows bn <dodf>SQLExdfption</dodf> if tif dursor is not
     * on b row in tif rowsft, bfforf tif first row, or bftfr tif lbst row.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     *         <dodf>fblsf</dodf> if it is bfforf tif first row or bftfr tif
     *         lbst row
     * @tirows SQLExdfption if tif dursor is not on b vblid position or tif
     *           typf of tiis rowsft is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn prfvious() tirows SQLExdfption {
        rfturn drsIntfrnbl.prfvious();
    }

    /**
     * Rfturns tif indfx of tif dolumn wiosf nbmf is <i>dolumnNbmf</i>.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif nbmf of tif
     *        dolumn for wiidi tif indfx will bf rfturnfd; tif nbmf must
     *        mbtdi tif SQL nbmf of b dolumn in tiis <dodf>JoinRowSft</dodf>
     *        objfdt, ignoring dbsf
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of tif
     *         dolumn nbmfs for tiis <dodf>JoinRowSft</dodf> objfdt
     */
    publid int findColumn(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.findColumn(dolumnNbmf);
    }

    /**
     * Indidbtfs wiftifr tif durrfnt row of tiis <dodf>JoinRowSftImpl</dodf>
     * objfdt ibs bffn updbtfd.  Tif vbluf rfturnfd
     * dfpfnds on wiftifr tiis rowsft dbn dftfdt updbtfs: <dodf>fblsf</dodf>
     * will blwbys bf rfturnfd if it dofs not dftfdt updbtfs.
     *
     * @rfturn <dodf>truf</dodf> if tif row ibs bffn visibly updbtfd
     *         by tif ownfr or bnotifr bnd updbtfs brf dftfdtfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *            on b vblid row
     *
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn rowUpdbtfd() tirows SQLExdfption {
        rfturn drsIntfrnbl.rowUpdbtfd();
    }

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn of tif durrfnt row of
     * tiis <dodf>JoinRowSftImpl</dodf> objfdt ibs bffn updbtfd. Tif
     * vbluf rfturnfd dfpfnds on wiftifr tiis rowsft dbn dftdtfd updbtfs:
     * <dodf>fblsf</dodf> will blwbys bf rfturnfd if it dofs not dftfdt updbtfs.
     *
     * @rfturn <dodf>truf</dodf> if tif dolumn updbtfd
     *          <dodf>fblsf</dodf> otifrwsf
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *          on b vblid row
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn dolumnUpdbtfd(int indfxColumn) tirows SQLExdfption {
        rfturn drsIntfrnbl.dolumnUpdbtfd(indfxColumn);
    }

    /**
     * Indidbtfs wiftifr tif durrfnt row ibs bffn insfrtfd.  Tif vbluf rfturnfd
     * dfpfnds on wiftifr or not tif rowsft dbn dftfdt visiblf insfrts.
     *
     * @rfturn <dodf>truf</dodf> if b row ibs bffn insfrtfd bnd insfrts brf dftfdtfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *            not on b vblid row
     *
     * @sff DbtbbbsfMftbDbtb#insfrtsArfDftfdtfd
     */
    publid boolfbn rowInsfrtfd() tirows SQLExdfption {
        rfturn drsIntfrnbl.rowInsfrtfd();
    }

    /**
     * Indidbtfs wiftifr tif durrfnt row ibs bffn dflftfd.  A dflftfd row
     * mby lfbvf b visiblf "iolf" in b rowsft.  Tiis mftiod dbn bf usfd to
     * dftfdt sudi iolfs if tif rowsft dbn dftfdt dflftions. Tiis mftiod
     * will blwbys rfturn <dodf>fblsf</dodf> if tiis rowsft dbnnot dftfdt
     * dflftions.
     *
     * @rfturn <dodf>truf</dodf> if (1)tif durrfnt row is blbnk, indidbting tibt
     *         tif row ibs bffn dflftfd, bnd (2)dflftions brf dftfdtfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if tif dursor is on b vblid row in tiis rowsft
     * @sff DbtbbbsfMftbDbtb#dflftfsArfDftfdtfd
     */
    publid boolfbn rowDflftfd() tirows SQLExdfption {
        rfturn drsIntfrnbl.rowDflftfd();
    }

    /**
     * Sfts tif dfsignbtfd nullbblf dolumn in tif durrfnt row or tif
     * insfrt row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi
     * <dodf>null</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft; iowfvfr, bnotifr mftiod must bf dbllfd to domplftf
     * tif updbtf prodfss. If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to mbrk tif row bs updbtfd
     * bnd to notify listfnfrs tibt tif row ibs dibngfd.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd to insfrt tif nfw row into tiis rowsft bnd to notify
     * listfnfrs tibt b row ibs dibngfd.
     * <P>
     * In ordfr to propbgbtf updbtfs in tiis rowsft to tif undfrlying
     * dbtb sourdf, bn bpplidbtion must dbll tif mftiod bddfptCibngfs
     * bftfr it dblls fitifr <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfNull(int dolumnIndfx) tirows SQLExdfption {
        drsIntfrnbl.updbtfNull(dolumnIndfx);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>boolfbn</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBoolfbn(int dolumnIndfx, boolfbn x) tirows SQLExdfption {
        drsIntfrnbl.updbtfBoolfbn(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>bytf</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBytf(int dolumnIndfx, bytf x) tirows SQLExdfption {
        drsIntfrnbl.updbtfBytf(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>siort</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfSiort(int dolumnIndfx, siort x) tirows SQLExdfption {
        drsIntfrnbl.updbtfSiort(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>int</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfInt(int dolumnIndfx, int x) tirows SQLExdfption {
        drsIntfrnbl.updbtfInt(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>long</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfLong(int dolumnIndfx, long x) tirows SQLExdfption {
        drsIntfrnbl.updbtfLong(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>flobt</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfFlobt(int dolumnIndfx, flobt x) tirows SQLExdfption {
        drsIntfrnbl.updbtfFlobt(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfDoublf(int dolumnIndfx, doublf x) tirows SQLExdfption {
        drsIntfrnbl.updbtfDoublf(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBigDfdimbl(int dolumnIndfx, BigDfdimbl x) tirows SQLExdfption {
        drsIntfrnbl.updbtfBigDfdimbl(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>String</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to mbrk tif row bs updbtfd.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd to insfrt tif nfw row into tiis rowsft bnd mbrk it
     * bs insfrtfd. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     * <P>
     * Tif mftiod <dodf>bddfptCibngfs</dodf> must bf dbllfd if tif
     * updbtfd vblufs brf to bf writtfn bbdk to tif undfrlying dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfString(int dolumnIndfx, String x) tirows SQLExdfption {
        drsIntfrnbl.updbtfString(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>bytf</dodf> brrby.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBytfs(int dolumnIndfx, bytf x[]) tirows SQLExdfption {
        drsIntfrnbl.updbtfBytfs(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Dbtf</dodf> objfdt.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif typf of tif dfsignbtfd dolumn is not
     *            bn SQL <dodf>DATE</dodf> or <dodf>TIMESTAMP</dodf>, or
     *            (4) tiis rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfDbtf(int dolumnIndfx, jbvb.sql.Dbtf x) tirows SQLExdfption {
        drsIntfrnbl.updbtfDbtf(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Timf</dodf> objfdt.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif typf of tif dfsignbtfd dolumn is not
     *            bn SQL <dodf>TIME</dodf> or <dodf>TIMESTAMP</dodf>, or
     *            (4) tiis rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfTimf(int dolumnIndfx, jbvb.sql.Timf x) tirows SQLExdfption {
        drsIntfrnbl.updbtfTimf(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Timfstbmp</dodf> objfdt.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif typf of tif dfsignbtfd dolumn is not
     *            bn SQL <dodf>DATE</dodf>, <dodf>TIME</dodf>, or
     *            <dodf>TIMESTAMP</dodf>, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfTimfstbmp(int dolumnIndfx, jbvb.sql.Timfstbmp x) tirows SQLExdfption {
        drsIntfrnbl.updbtfTimfstbmp(dolumnIndfx, x);
    }

    /*
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * ASCII strfbm vbluf.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif numbfr of onf-bytf ASCII dibrbdtfrs in tif strfbm
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is invokfd
     */
    publid void updbtfAsdiiStrfbm(int dolumnIndfx, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        drsIntfrnbl.updbtfAsdiiStrfbm(dolumnIndfx, x, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.io.InputStrfbm</dodf> objfdt.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf; must bf b <dodf>jbvb.io.InputStrfbm</dodf>
     *          dontbining <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>, or
     *          <dodf>LONGVARBINARY</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in bytfs
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif dbtb in tif strfbm is not binbry, or
     *            (4) tiis rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBinbryStrfbm(int dolumnIndfx, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        drsIntfrnbl.updbtfBinbryStrfbm(dolumnIndfx, x, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf; must bf b <dodf>jbvb.io.Rfbdfr</dodf>
     *          dontbining <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>,
     *          <dodf>LONGVARBINARY</dodf>, <dodf>CHAR</dodf>, <dodf>VARCHAR</dodf>,
     *          or <dodf>LONGVARCHAR</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in dibrbdtfrs
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif dbtb in tif strfbm is not b binbry or
     *            dibrbdtfr typf, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfCibrbdtfrStrfbm(int dolumnIndfx, jbvb.io.Rfbdfr x, int lfngti) tirows SQLExdfption {
        drsIntfrnbl.updbtfCibrbdtfrStrfbm(dolumnIndfx, x, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Objfdt</dodf> vbluf.  Tif <dodf>sdblf</dodf> pbrbmftfr indidbtfs
     * tif numbfr of digits to tif rigit of tif dfdimbl point bnd is ignorfd
     * if tif nfw dolumn vbluf is not b typf tibt will bf mbppfd to bn SQL
     * <dodf>DECIMAL</dodf> or <dodf>NUMERIC</dodf> vbluf.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point (for
     *              <dodf>DECIMAL</dodf> bnd <dodf>NUMERIC</dodf> typfs only)
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfObjfdt(int dolumnIndfx, Objfdt x, int sdblf) tirows SQLExdfption {
        drsIntfrnbl.updbtfObjfdt(dolumnIndfx, x, sdblf);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfObjfdt(int dolumnIndfx, Objfdt x) tirows SQLExdfption {
        drsIntfrnbl.updbtfObjfdt(dolumnIndfx, x);
    }

    // dolumnNbmf updbtfs

    /**
     * Sfts tif dfsignbtfd nullbblf dolumn in tif durrfnt row or tif
     * insfrt row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi
     * <dodf>null</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfNull(String dolumnNbmf) tirows SQLExdfption {
        drsIntfrnbl.updbtfNull(dolumnNbmf);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>boolfbn</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBoolfbn(String dolumnNbmf, boolfbn x) tirows SQLExdfption {
        drsIntfrnbl.updbtfBoolfbn(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>bytf</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBytf(String dolumnNbmf, bytf x) tirows SQLExdfption {
        drsIntfrnbl.updbtfBytf(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>siort</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfSiort(String dolumnNbmf, siort x) tirows SQLExdfption {
        drsIntfrnbl.updbtfSiort(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>int</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfInt(String dolumnNbmf, int x) tirows SQLExdfption {
        drsIntfrnbl.updbtfInt(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>long</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfLong(String dolumnNbmf, long x) tirows SQLExdfption {
        drsIntfrnbl.updbtfLong(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>flobt</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfFlobt(String dolumnNbmf, flobt x) tirows SQLExdfption {
        drsIntfrnbl.updbtfFlobt(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfDoublf(String dolumnNbmf, doublf x) tirows SQLExdfption {
        drsIntfrnbl.updbtfDoublf(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBigDfdimbl(String dolumnNbmf, BigDfdimbl x) tirows SQLExdfption {
        drsIntfrnbl.updbtfBigDfdimbl(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>String</dodf> objfdt.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfString(String dolumnNbmf, String x) tirows SQLExdfption {
        drsIntfrnbl.updbtfString(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>bytf</dodf> brrby.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBytfs(String dolumnNbmf, bytf x[]) tirows SQLExdfption {
        drsIntfrnbl.updbtfBytfs(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Dbtf</dodf> objfdt.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif typf
     *            of tif dfsignbtfd dolumn is not bn SQL <dodf>DATE</dodf> or
     *            <dodf>TIMESTAMP</dodf>, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfDbtf(String dolumnNbmf, jbvb.sql.Dbtf x) tirows SQLExdfption {
        drsIntfrnbl.updbtfDbtf(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Timf</dodf> objfdt.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif typf
     *            of tif dfsignbtfd dolumn is not bn SQL <dodf>TIME</dodf> or
     *            <dodf>TIMESTAMP</dodf>, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfTimf(String dolumnNbmf, jbvb.sql.Timf x) tirows SQLExdfption {
        drsIntfrnbl.updbtfTimf(dolumnNbmf, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Timfstbmp</dodf> objfdt.
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds or
     *            tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif typf
     *            of tif dfsignbtfd dolumn is not bn SQL <dodf>DATE</dodf>,
     *            <dodf>TIME</dodf>, or <dodf>TIMESTAMP</dodf>, or (4) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfTimfstbmp(String dolumnNbmf, jbvb.sql.Timfstbmp x) tirows SQLExdfption {
        drsIntfrnbl.updbtfTimfstbmp(dolumnNbmf, x);
    }

    /**
     * Unsupportfd; tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>
     * if dbllfd.
     * <P>
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * ASCII strfbm vbluf.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif numbfr of onf-bytf ASCII dibrbdtfrs in tif strfbm
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is invokfd
     */
    publid void updbtfAsdiiStrfbm(String dolumnNbmf, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        drsIntfrnbl.updbtfAsdiiStrfbm(dolumnNbmf, x, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.io.InputStrfbm</dodf> objfdt.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf; must bf b <dodf>jbvb.io.InputStrfbm</dodf>
     *          dontbining <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>, or
     *          <dodf>LONGVARBINARY</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in bytfs
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif dbtb
     *            in tif strfbm is not binbry, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBinbryStrfbm(String dolumnNbmf, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        drsIntfrnbl.updbtfBinbryStrfbm(dolumnNbmf, x, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf; must bf b <dodf>jbvb.io.Rfbdfr</dodf>
     *          dontbining <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>,
     *          <dodf>LONGVARBINARY</dodf>, <dodf>CHAR</dodf>, <dodf>VARCHAR</dodf>,
     *          or <dodf>LONGVARCHAR</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in dibrbdtfrs
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif dbtb
     *            in tif strfbm is not b binbry or dibrbdtfr typf, or (4) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfCibrbdtfrStrfbm(String dolumnNbmf, jbvb.io.Rfbdfr x, int lfngti) tirows SQLExdfption {
        drsIntfrnbl.updbtfCibrbdtfrStrfbm(dolumnNbmf, x, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Objfdt</dodf> vbluf.  Tif <dodf>sdblf</dodf> pbrbmftfr
     * indidbtfs tif numbfr of digits to tif rigit of tif dfdimbl point
     * bnd is ignorfd if tif nfw dolumn vbluf is not b typf tibt will bf
     *  mbppfd to bn SQL <dodf>DECIMAL</dodf> or <dodf>NUMERIC</dodf> vbluf.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point (for
     *              <dodf>DECIMAL</dodf> bnd <dodf>NUMERIC</dodf> typfs only)
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds or
     *            tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfObjfdt(String dolumnNbmf, Objfdt x, int sdblf) tirows SQLExdfption {
        drsIntfrnbl.updbtfObjfdt(dolumnNbmf, x, sdblf);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
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
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfObjfdt(String dolumnNbmf, Objfdt x) tirows SQLExdfption {
        drsIntfrnbl.updbtfObjfdt(dolumnNbmf, x);
    }

    /**
     * Insfrts tif dontfnts of tiis <dodf>JoinRowSftImpl</dodf> objfdt's insfrt
     * row into tiis rowsft immfdibtfly following tif durrfnt row.
     * If tif durrfnt row is tif
     * position bftfr tif lbst row or bfforf tif first row, tif nfw row will
     * bf insfrtfd bt tif fnd of tif rowsft.  Tiis mftiod blso notififs
     * listfnfrs rfgistfrfd witi tiis rowsft tibt tif row ibs dibngfd.
     * <P>
     * Tif dursor must bf on tif insfrt row wifn tiis mftiod is dbllfd.
     *
     * @tirows SQLExdfption if (1) tif dursor is not on tif insfrt row,
     *            (2) onf or morf of tif non-nullbblf dolumns in tif insfrt
     *            row ibs not bffn givfn b vbluf, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void insfrtRow() tirows SQLExdfption {
        drsIntfrnbl.insfrtRow();
    }

    /**
     * Mbrks tif durrfnt row of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs
     * updbtfd bnd notififs listfnfrs rfgistfrfd witi tiis rowsft tibt tif
     * row ibs dibngfd.
     * <P>
     * Tiis mftiod  dbnnot bf dbllfd wifn tif dursor is on tif insfrt row, bnd
     * it siould bf dbllfd bfforf tif dursor movfs to bnotifr row.  If it is
     * dbllfd bftfr tif dursor movfs to bnotifr row, tiis mftiod ibs no ffffdt,
     * bnd tif updbtfs mbdf bfforf tif dursor movfd will bf lost.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRow() tirows SQLExdfption {
        drsIntfrnbl.updbtfRow();
    }

    /**
     * Dflftfs tif durrfnt row from tiis <dodf>JoinRowSftImpl</dodf> objfdt bnd
     * notififs listfnfrs rfgistfrfd witi tiis rowsft tibt b row ibs dibngfd.
     * Tiis mftiod dbnnot bf dbllfd wifn tif dursor is on tif insfrt row.
     * <P>
     * Tiis mftiod mbrks tif durrfnt row bs dflftfd, but it dofs not dflftf
     * tif row from tif undfrlying dbtb sourdf.  Tif mftiod
     * <dodf>bddfptCibngfs</dodf> must bf dbllfd to dflftf tif row in
     * tif dbtb sourdf.
     *
     * @tirows SQLExdfption if (1) tiis mftiod is dbllfd wifn tif dursor
     *            is on tif insfrt row, bfforf tif first row, or bftfr tif
     *            lbst row or (2) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void dflftfRow() tirows SQLExdfption {
        drsIntfrnbl.dflftfRow();
    }

    /**
     * Sfts tif durrfnt row witi its originbl vbluf bnd mbrks tif row bs
     * not updbtfd, tius undoing bny dibngfs mbdf to tif row sindf tif
     * lbst dbll to tif mftiods <dodf>updbtfRow</dodf> or <dodf>dflftfRow</dodf>.
     * Tiis mftiod siould bf dbllfd only wifn tif dursor is on b row in
     * tiis rowsft.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row, bfforf tif
     *            first row, or bftfr tif lbst row
     */
    publid void rffrfsiRow() tirows SQLExdfption {
        drsIntfrnbl.rffrfsiRow();
    }

    /**
     * Rolls bbdk bny updbtfs mbdf to tif durrfnt row of tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bnd notififs listfnfrs tibt
     * b row ibs dibngfd.  To ibvf bn ffffdt, tiis mftiod
     * must bf dbllfd bftfr bn <dodf>updbtfXXX</dodf> mftiod ibs bffn
     * dbllfd bnd bfforf tif mftiod <dodf>updbtfRow</dodf> ibs bffn dbllfd.
     * If no updbtfs ibvf bffn mbdf or tif mftiod <dodf>updbtfRow</dodf>
     * ibs blrfbdy bffn dbllfd, tiis mftiod ibs no ffffdt.
     * <P>
     * Aftfr <dodf>updbtfRow</dodf> is dbllfd it is tif
     * <dodf>dbndflRowUpdbtfs</dodf> ibs no bfffdt on tif nfwly
     * insfrtfd vblufs. Tif mftiod <dodf>dbndflRowInsfrt</dodf> dbn
     * bf usfd to rfmovf bny rows insfrtfd into tif RowSft.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row, bfforf tif
     *            first row, or bftfr tif lbst row
     */
    publid void dbndflRowUpdbtfs() tirows SQLExdfption {
        drsIntfrnbl.dbndflRowUpdbtfs();
    }

    /**
     * Movfs tif dursor for tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * to tif insfrt row.  Tif durrfnt row in tif rowsft is rfmfmbfrfd
     * wiilf tif dursor is on tif insfrt row.
     * <P>
     * Tif insfrt row is b spfdibl row bssodibtfd witi bn updbtbblf
     * rowsft.  It is fssfntiblly b bufffr wifrf b nfw row mby
     * bf donstrudtfd by dblling tif bppropribtf <dodf>updbtfXXX</dodf>
     * mftiods to bssign b vbluf to fbdi dolumn in tif row.  A domplftf
     * row must bf donstrudtfd; tibt is, fvfry dolumn tibt is not nullbblf
     * must bf bssignfd b vbluf.  In ordfr for tif nfw row to bfdomf pbrt
     * of tiis rowsft, tif mftiod <dodf>insfrtRow</dodf> must bf dbllfd
     * bfforf tif dursor is movfd bbdk to tif rowsft.
     * <P>
     * Only dfrtbin mftiods mby bf invokfd wiilf tif dursor is on tif insfrt
     * row; mbny mftiods tirow bn fxdfption if tify brf dbllfd wiilf tif
     * dursor is tifrf.  In bddition to tif <dodf>updbtfXXX</dodf>
     * bnd <dodf>insfrtRow</dodf> mftiods, only tif <dodf>gftXXX</dodf> mftiods
     * mby bf dbllfd wifn tif dursor is on tif insfrt row.  A <dodf>gftXXX</dodf>
     * mftiod siould bf dbllfd on b dolumn only bftfr bn <dodf>updbtfXXX</dodf>
     * mftiod ibs bffn dbllfd on tibt dolumn; otifrwisf, tif vbluf rfturnfd is
     * undftfrminfd.
     *
     * @tirows SQLExdfption if tiis <dodf>JoinRowSftImpl</dodf> objfdt is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void movfToInsfrtRow() tirows SQLExdfption {
        drsIntfrnbl.movfToInsfrtRow();
    }

    /**
     * Movfs tif dursor for tiis <dodf>JoinRowSftImpl</dodf> objfdt to
     * tif durrfnt row.  Tif durrfnt row is tif row tif dursor wbs on
     * wifn tif mftiod <dodf>movfToInsfrtRow</dodf> wbs dbllfd.
     * <P>
     * Cblling tiis mftiod ibs no ffffdt unlfss it is dbllfd wiilf tif
     * dursor is on tif insfrt row.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void movfToCurrfntRow() tirows SQLExdfption {
        drsIntfrnbl.movfToCurrfntRow();
    }

    /**
     * Rfturns <dodf>null</dodf>.
     *
     * @rfturn <dodf>null</dodf>
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid Stbtfmfnt gftStbtfmfnt() tirows SQLExdfption {
        rfturn drsIntfrnbl.gftStbtfmfnt();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>Rff</dodf> objfdt
     * in tif Jbvb progrbmming lbnugbgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting bn SQL<dodf> REF</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>REF</dodf> vbluf
     */
    publid Rff gftRff(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftRff(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>Blob</dodf> objfdt
     * in tif Jbvb progrbmming lbnugbgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting bn SQL <dodf>BLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>BLOB</dodf> vbluf
     */
    publid Blob gftBlob(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBlob(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>Clob</dodf> objfdt
     * in tif Jbvb progrbmming lbnugbgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting bn SQL <dodf>CLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>CLOB</dodf> vbluf
     */
    publid Clob gftClob(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftClob(dolumnIndfx);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt
     * in tif Jbvb progrbmming lbnugbgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting bn SQL
     *         <dodf>ARRAY</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>ARRAY</dodf> vbluf
     */
     publid Arrby gftArrby(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftArrby(dolumnIndfx);
    }

    // ColumnNbmf

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>Rff</dodf> objfdt
     * in tif Jbvb progrbmming lbnugbgf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting bn SQL<dodf> REF</dodf> vbluf
     * @tirows SQLExdfption  if (1) tif givfn dolumn nbmf is not tif nbmf
     *         of b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *         tiis rowsft's rows or its insfrt row, or (3) tif dolumn vbluf
     *         is not bn SQL <dodf>REF</dodf> vbluf
     */
    publid Rff gftRff(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftRff(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>Blob</dodf> objfdt
     * in tif Jbvb progrbmming lbnugbgf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting bn SQL
     *        <dodf>BLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *        b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *        tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *        dolumn dofs not storf bn SQL <dodf>BLOB</dodf> vbluf
     */
    publid Blob gftBlob(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftBlob(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>Clob</dodf> objfdt
     * in tif Jbvb progrbmming lbnugbgf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting bn SQL
     *         <dodf>CLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>CLOB</dodf> vbluf
     */
    publid Clob gftClob(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftClob(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>JoinRowSftImpl</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt
     * in tif Jbvb progrbmming lbnugbgf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting bn SQL
     *        <dodf>ARRAY</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *        b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *        tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *        dolumn dofs not storf bn SQL <dodf>ARRAY</dodf> vbluf
     */
    publid Arrby gftArrby(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftArrby(dolumnNbmf);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>DATE</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Dbtf gftDbtf(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftDbtf(dolumnIndfx, dbl);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>DATE</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Dbtf gftDbtf(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftDbtf(dolumnNbmf, dbl);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>TIME</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Timf gftTimf(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftTimf(dolumnIndfx, dbl);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>TIME</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Timf gftTimf(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftTimf(dolumnNbmf, dbl);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timfstbmp</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>TIME</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftTimfstbmp(dolumnIndfx, dbl);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt, using tif givfn
     * <dodf>Cblfndbr</dodf> objfdt to donstrudt bn bppropribtf
     * millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>DATE</dodf>,
     *            <dodf>TIME</dodf>, or <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftTimfstbmp(dolumnNbmf, dbl);
    }

   /**
    * Sfts tif mftbdbtb for tiis <dodf>JoinRowSftImpl</dodf> objfdt
    * witi tif givfn <dodf>RowSftMftbDbtb</dodf> objfdt.
    *
    * @pbrbm md b <dodf>RowSftMftbDbtb</dodf> objfdt instbndf dontbining
    *            mftbdbtb bbout tif dolumsn in tif rowsft
    * @tirows SQLExdfption if invblid mftb dbtb is supplifd to tif
    *            rowsft
    */
    publid void sftMftbDbtb(RowSftMftbDbtb md) tirows SQLExdfption {
        drsIntfrnbl.sftMftbDbtb(md);
    }

    publid RfsultSft gftOriginbl() tirows SQLExdfption {
        rfturn drsIntfrnbl.gftOriginbl();
    }

   /**
    * Rfturns b rfsult sft dontbining tif originbl vbluf of tif rowsft.
    * Tif dursor is positionfd bfforf tif first row in tif rfsult sft.
    * Only rows dontbinfd in tif rfsult sft rfturnfd by gftOriginbl()
    * brf sbid to ibvf bn originbl vbluf.
    *
    * @rfturn tif originbl rfsult sft of tif rowsft
    * @tirows SQLExdfption if bn frror oddurs produdf tif
    *           <dodf>RfsultSft</dodf> objfdt
    */
    publid RfsultSft gftOriginblRow() tirows SQLExdfption {
        rfturn drsIntfrnbl.gftOriginblRow();
    }

   /**
    * Rfturns b rfsult sft dontbining tif originbl vbluf of tif durrfnt
    * row only.
    *
    * @tirows SQLExdfption if tifrf is no durrfnt row
    * @sff #sftOriginblRow
    */
    publid void sftOriginblRow() tirows SQLExdfption {
        drsIntfrnbl.sftOriginblRow();
    }

   /**
    * Rfturns tif dolumns tibt mbkf b kfy to uniqufly idfntify b
    * row in tiis <dodf>JoinRowSftImpl</dodf> objfdt.
    *
    * @rfturn bn brrby of dolumn numbfr tibt donstitfs b primbry
    *           kfy for tiis rowsft. Tiis brrby siould bf fmpty
    *           if no dolumns is rfprfsfntitivf of b primbry kfy
    * @tirows SQLExdfption if tif rowsft is fmpty or no dolumns
    *           brf dfsignbtfd bs primbry kfys
    * @sff #sftKfyColumns
    */
    publid int[] gftKfyColumns() tirows SQLExdfption {
        rfturn drsIntfrnbl.gftKfyColumns();
    }

    /**
     * Sfts tiis <dodf>JoinRowSftImpl</dodf> objfdt's
     * <dodf>kfyCols</dodf> fifld witi tif givfn brrby of dolumn
     * numbfrs, wiidi forms b kfy for uniqufly idfntifying b row
     * in tiis rowsft.
     *
     * @pbrbm dols bn brrby of <dodf>int</dodf> indidbting tif
     *        dolumns tibt form b primbry kfy for tiis
     *        <dodf>JoinRowSftImpl</dodf> objfdt; fvfry
     *        flfmfnt in tif brrby must bf grfbtfr tibn
     *        <dodf>0</dodf> bnd lfss tibn or fqubl to tif numbfr
     *        of dolumns in tiis rowsft
     * @tirows SQLExdfption if bny of tif numbfrs in tif
     *            givfn brrby is not vblid for tiis rowsft
     * @sff #gftKfyColumns
     */
    publid void sftKfyColumns(int[] dols) tirows SQLExdfption {
        drsIntfrnbl.sftKfyColumns(dols);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Rff</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Eitifr of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm rff tif <dodf>jbvb.sql.Rff</dodf> objfdt tibt will bf sft bs
     *         tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRff(int dolumnIndfx, jbvb.sql.Rff rff) tirows SQLExdfption {
        drsIntfrnbl.updbtfRff(dolumnIndfx, rff);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Rff</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Eitifr of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif nbmf of tif dolumn
     *        to bf updbtfd; must mbtdi onf of tif dolumn nbmfs in tiis
     *        <dodf>JoinRowSftImpl</dodf> objfdt
     * @pbrbm rff tif <dodf>jbvb.sql.Rff</dodf> objfdt tibt will bf sft bs
     *         tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not vblid,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRff(String dolumnNbmf, jbvb.sql.Rff rff) tirows SQLExdfption {
        drsIntfrnbl.updbtfRff(dolumnNbmf, rff);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Clob</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Eitifr of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm d tif <dodf>jbvb.sql.Clob</dodf> objfdt tibt will bf sft bs
     *         tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfClob(int dolumnIndfx, Clob d) tirows SQLExdfption {
        drsIntfrnbl.updbtfClob(dolumnIndfx, d);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Clob</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Eitifr of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif nbmf of tif dolumn
     *        to bf updbtfd; must mbtdi onf of tif dolumn nbmfs in tiis
     *        <dodf>JoinRowSftImpl</dodf> objfdt
     * @pbrbm d tif <dodf>jbvb.sql.Clob</dodf> objfdt tibt will bf sft bs
     *         tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not vblid,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfClob(String dolumnNbmf, Clob d) tirows SQLExdfption {
        drsIntfrnbl.updbtfClob(dolumnNbmf, d);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Blob</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Eitifr of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm b tif <dodf>jbvb.sql.Blob</dodf> objfdt tibt will bf sft bs
     *         tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBlob(int dolumnIndfx, Blob b) tirows SQLExdfption {
         drsIntfrnbl.updbtfBlob(dolumnIndfx, b);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Blob</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Eitifr of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif nbmf of tif dolumn
     *        to bf updbtfd; must mbtdi onf of tif dolumn nbmfs in tiis
     *        <dodf>JoinRowSftImpl</dodf> objfdt
     * @pbrbm b tif <dodf>jbvb.sql.Blob</dodf> objfdt tibt will bf sft bs
     *         tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not vblid,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBlob(String dolumnNbmf, Blob b) tirows SQLExdfption {
         drsIntfrnbl.updbtfBlob(dolumnNbmf, b);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Arrby</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Eitifr of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm b tif <dodf>jbvb.sql.Arrby</dodf> objfdt tibt will bf sft bs
     *         tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfArrby(int dolumnIndfx, Arrby b) tirows SQLExdfption {
         drsIntfrnbl.updbtfArrby(dolumnIndfx, b);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>JoinRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Arrby</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Eitifr of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif nbmf of tif dolumn
     *        to bf updbtfd; must mbtdi onf of tif dolumn nbmfs in tiis
     *        <dodf>JoinRowSftImpl</dodf> objfdt
     * @pbrbm b tif <dodf>jbvb.sql.Arrby</dodf> objfdt tibt will bf sft bs
     *         tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not vblid,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfArrby(String dolumnNbmf, Arrby b) tirows SQLExdfption {
         drsIntfrnbl.updbtfArrby(dolumnNbmf, b);
    }

    /**
     * Populbtfs tiis <dodf>JoinRowSftImpl</dodf> objfdt witi dbtb.
     * Tiis form of tif mftiod usfs tif rowsft's usfr, pbssword, bnd url or
     * dbtb sourdf nbmf propfrtifs to drfbtf b dbtbbbsf
     * donnfdtion.  If propfrtifs tibt brf nffdfd
     * ibvf not bffn sft, tiis mftiod will tirow bn fxdfption.
     * <P>
     * Anotifr form of tiis mftiod usfs bn fxisting JDBC <dodf>Connfdtion</dodf>
     * objfdt instfbd of drfbting b nfw onf; tifrfforf, it ignorfs tif
     * propfrtifs usfd for fstbblisiing b nfw donnfdtion.
     * <P>
     * Tif qufry spfdififd by tif dommbnd propfrty is fxfdutfd to drfbtf b
     * <dodf>RfsultSft</dodf> objfdt from wiidi to rftrifvf dbtb.
     * Tif durrfnt dontfnts of tif rowsft brf disdbrdfd, bnd tif
     * rowsft's mftbdbtb is blso (rf)sft.  If tifrf brf outstbnding updbtfs,
     * tify brf blso ignorfd.
     * <P>
     * Tif mftiod <dodf>fxfdutf</dodf> dlosfs bny dbtbbbsf donnfdtions tibt it
     * drfbtfs.
     *
     * @tirows SQLExdfption if bn frror oddurs or tif
     *                         nfdfssbry propfrtifs ibvf not bffn sft
     */
    publid void fxfdutf() tirows SQLExdfption {
        drsIntfrnbl.fxfdutf();
    }

    /**
     * Populbtfs tiis <dodf>JoinRowSftImpl</dodf> objfdt witi dbtb,
     * using tif givfn donnfdtion to produdf tif rfsult sft from
     * wiidi dbtb will bf rfbd.  A sfdond form of tiis mftiod,
     * wiidi tbkfs no brgumfnts, usfs tif vblufs from tiis rowsft's
     * usfr, pbssword, bnd fitifr url or dbtb sourdf propfrtifs to
     * drfbtf b nfw dbtbbbsf donnfdtion. Tif form of <dodf>fxfdutf</dodf>
     * tibt is givfn b donnfdtion ignorfs tifsf propfrtifs.
     *
     *  @pbrbm donn A stbndbrd JDBC <dodf>Connfdtion</dodf> objfdt witi vblid
     *           propfrtifs tibt tif <dodf>JoinRowSft</dodf> implfmfntbtion
     *           dbn pbss to b syndironizbtion providfr to fstbblisi b
     *           donnfdtion to tif dbtbsourdf
     * @tirows SQLExdfption if bn invblid <dodf>Connfdtion</dodf> is supplifd
     *           or bn frror oddurs in fstbblisiing tif donnfdtion to tif
     *           dbtb sourf
     * @sff jbvb.sql.Connfdtion
     */
    publid void fxfdutf(Connfdtion donn) tirows SQLExdfption {
        drsIntfrnbl.fxfdutf(donn);
    }

    /**
     * Providf intfrfbdf dovfrbgf for gftURL(int) in RfsultSft->RowSft
     */
    publid jbvb.nft.URL gftURL(int dolumnIndfx) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftURL(dolumnIndfx);
    }

    /**
     * Providf intfrfbdf dovfrbgf for gftURL(String) in RfsultSft->RowSft
     */
    publid jbvb.nft.URL gftURL(String dolumnNbmf) tirows SQLExdfption {
        rfturn drsIntfrnbl.gftURL(dolumnNbmf);
    }

   /**
    * Crfbtfs b nfw <dodf>WfbRowSft</dodf> objfdt, populbtfs it witi tif
    * dbtb in tif givfn <dodf>RfsultSft</dodf> objfdt, bnd writfs it
    * to tif givfn <dodf>jbvb.io.Writfr</dodf> objfdt in XML formbt.
    *
    * @tirows SQLExdfption if bn frror oddurs writing out tif rowsft
    *          dontfnts to XML
    */
    publid void writfXml(RfsultSft rs, jbvb.io.Writfr writfr)
        tirows SQLExdfption {
             wrs = nfw WfbRowSftImpl();
             wrs.populbtf(rs);
             wrs.writfXml(writfr);
    }

    /**
     * Writfs tiis <dodf>JoinRowSft</dodf> objfdt to tif givfn
     * <dodf>jbvb.io.Writfr</dodf> objfdt in XML formbt. In
     * bddition to tif rowsft's dbtb, its propfrtifs bnd mftbdbtb
     * brf blso indludfd.
     *
     * @tirows SQLExdfption if bn frror oddurs writing out tif rowsft
     *          dontfnts to XML
     */
    publid void writfXml(jbvb.io.Writfr writfr) tirows SQLExdfption {
        drfbtfWfbRowSft().writfXml(writfr);
}

    /**
     * Rfbds tiis <dodf>JoinRowSft</dodf> objfdt in its XML formbt.
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid void rfbdXml(jbvb.io.Rfbdfr rfbdfr) tirows SQLExdfption {
        wrs = nfw WfbRowSftImpl();
        wrs.rfbdXml(rfbdfr);
        drsIntfrnbl = (CbdifdRowSftImpl)wrs;
    }

    // Strfbm bbsfd mftiods
    /**
     * Rfbds b strfbm bbsfd XML input to populbtf bn <dodf>WfbRowSft</dodf>
     *
     * @tirows SQLExdfption if b dbtb sourdf bddfss oddurs
     * @tirows IOExdfption if b IO fxdfption oddurs
     */
    publid void rfbdXml(jbvb.io.InputStrfbm iStrfbm) tirows SQLExdfption, IOExdfption {
         wrs = nfw WfbRowSftImpl();
         wrs.rfbdXml(iStrfbm);
         drsIntfrnbl = (CbdifdRowSftImpl)wrs;
    }

    /**
     * Crfbtfs bn bn output strfbm of tif intfrnbl stbtf bnd dontfnts of b
     * <dodf>WfbRowSft</dodf> for XML prodffssing
     *
     * @tirows SQLExdfption if b dbtbsourdf bddfss oddurs
     * @tirows IOExdfption if bn IO fxdfption oddurs
     */
    publid void writfXml(jbvb.io.OutputStrfbm oStrfbm) tirows SQLExdfption, IOExdfption {
         drfbtfWfbRowSft().writfXml(oStrfbm);
    }

    /**
     * Crfbtfs b nfw <dodf>WfbRowSft</dodf> objfdt, populbtfs it witi
     * tif dontfnts of tif <dodf>RfsultSft</dodf> bnd drfbtfs bn output
     * strfbms tif intfrnbl stbtf bnd dontfnts of tif rowsft for XML prodfssing.
     *
     * @tirows SQLExdfption if b dbtbsourdf bddfss oddurs
     * @tirows IOExdfption if bn IO fxdfption oddurs
     */
    publid void writfXml(RfsultSft rs, jbvb.io.OutputStrfbm oStrfbm) tirows SQLExdfption, IOExdfption {
             wrs = nfw WfbRowSftImpl();
             wrs.populbtf(rs);
             wrs.writfXml(oStrfbm);
    }

    /**
     * %%% Jbvbdod dommfnts to bf bddfd ifrf
     */
    privbtf WfbRowSft drfbtfWfbRowSft() tirows SQLExdfption {
       if(wrs != null) {
           // difdk if it ibs blrfbdy bffn initiblizfd.
           rfturn wrs;
       } flsf {
         wrs = nfw WfbRowSftImpl();
          drsIntfrnbl.bfforfFirst();
          wrs.populbtf(drsIntfrnbl);
          rfturn wrs;
       }
    }

    /**
     * Rfturns tif lbst sft SQL <dodf>JOIN</dodf> typf in tiis JoinRowSftImpl
     * objfdt
     *
     * @rfturn joinTypf Onf of tif stbndbrd JoinRowSft stbtid fifld JOIN typfs
     * @tirows SQLExdfption if bn frror oddurs dftfrmining tif durrfnt join typf
     */
    publid int gftJoinTypf() tirows SQLExdfption {
        if (vfdJoinTypf == null) {
            // Dffbult JoinRowSft typf
            tiis.sftJoinTypf(JoinRowSft.INNER_JOIN);
        }
        Intfgfr i = vfdJoinTypf.gft(vfdJoinTypf.sizf()-1);
        rfturn i.intVbluf();
    }

    /**
    * Tif listfnfr will bf notififd wifnfvfr bn fvfnt oddurs on tiis <dodf>JoinRowSft</dodf>
    * objfdt.
    * <P>
    * A listfnfr migit, for fxbmplf, bf b tbblf or grbpi tibt nffds to
    * bf updbtfd in ordfr to bddurbtfly rfflfdt tif durrfnt stbtf of
    * tif <dodf>RowSft</dodf> objfdt.
    * <p>
    * <b>Notf</b>: if tif <dodf>RowSftListfnfr</dodf> objfdt is
    * <dodf>null</dodf>, tiis mftiod silfntly disdbrds tif <dodf>null</dodf>
    * vbluf bnd dofs not bdd b null rfffrfndf to tif sft of listfnfrs.
    * <p>
    * <b>Notf</b>: if tif listfnfr is blrfbdy sft, bnd tif nfw <dodf>RowSftListfrnfr</dodf>
    * instbndf is bddfd to tif sft of listfnfrs blrfbdy rfgistfrfd to rfdfivf
    * fvfnt notifidbtions from tiis <dodf>RowSft</dodf>.
    *
    * @pbrbm listfnfr bn objfdt tibt ibs implfmfntfd tif
    *     <dodf>jbvbx.sql.RowSftListfnfr</dodf> intfrfbdf bnd wbnts to bf notififd
    *     of bny fvfnts tibt oddur on tiis <dodf>JoinRowSft</dodf> objfdt; Mby bf
    *     null.
    * @sff #rfmovfRowSftListfnfr
    */
    publid void bddRowSftListfnfr(RowSftListfnfr listfnfr) {
        drsIntfrnbl.bddRowSftListfnfr(listfnfr);
    }

    /**
    * Rfmovfs tif dfsignbtfd objfdt from tiis <dodf>JoinRowSft</dodf> objfdt's list of listfnfrs.
    * If tif givfn brgumfnt is not b rfgistfrfd listfnfr, tiis mftiod
    * dofs notiing.
    *
    *  <b>Notf</b>: if tif <dodf>RowSftListfnfr</dodf> objfdt is
    * <dodf>null</dodf>, tiis mftiod silfntly disdbrds tif <dodf>null</dodf>
    * vbluf.
    *
    * @pbrbm listfnfr b <dodf>RowSftListfnfr</dodf> objfdt tibt is on tif list
    *        of listfnfrs for tiis <dodf>JoinRowSft</dodf> objfdt
    * @sff #bddRowSftListfnfr
    */
     publid void rfmovfRowSftListfnfr(RowSftListfnfr listfnfr) {
        drsIntfrnbl.rfmovfRowSftListfnfr(listfnfr);
    }

    /**
     * Convfrts tiis <dodf>JoinRowSftImpl</dodf> objfdt to b dollfdtion
     * of tbblfs. Tif sbmplf implfmfntbtion utilitizfs tif <dodf>TrffMbp</dodf>
     * dollfdtion typf.
     * Tiis dlbss gubrbntffs tibt tif mbp will bf in bsdfnding kfy ordfr,
     * sortfd bddording to tif nbturbl ordfr for tif kfy's dlbss.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt donsisting of tbblfs,
     *         fbdi of wiidi is b dopy of b row in tiis
     *         <dodf>JoinRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dollfdtion
     * @sff #toCollfdtion(int)
     * @sff #toCollfdtion(String)
     * @sff jbvb.util.TrffMbp
     */
     publid Collfdtion<?> toCollfdtion() tirows SQLExdfption {
        rfturn drsIntfrnbl.toCollfdtion();
    }

    /**
     * Rfturns tif spfdififd dolumn of tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * bs b <dodf>Collfdtion</dodf> objfdt.  Tiis mftiod mbkfs b dopy of tif
     * dolumn's dbtb bnd utilitizfs tif <dodf>Vfdtor</dodf> to fstbblisi tif
     * dollfdtion. Tif <dodf>Vfdtor</dodf> dlbss implfmfnts b growbblf brrby
     * objfdts bllowing tif individubl domponfnts to bf bddfssfd using bn
     * bn intfgfr indfx similbr to tibt of bn brrby.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt tibt dontbins tif vbluf(s)
     *         storfd in tif spfdififd dolumn of tiis
     *         <dodf>JoinRowSftImpl</dodf>
     *         objfdt
     * @tirows SQLExdfption if bn frror oddurs gfnfrbtfd tif dollfdtion; or
     *          bn invblid dolumn is providfd.
     * @sff #toCollfdtion()
     * @sff #toCollfdtion(String)
     * @sff jbvb.util.Vfdtor
     */
    publid Collfdtion<?> toCollfdtion(int dolumn) tirows SQLExdfption {
        rfturn drsIntfrnbl.toCollfdtion(dolumn);
    }

    /**
     * Rfturns tif spfdififd dolumn of tiis <dodf>JoinRowSftImpl</dodf> objfdt
     * bs b <dodf>Collfdtion</dodf> objfdt.  Tiis mftiod mbkfs b dopy of tif
     * dolumn's dbtb bnd utilitizfs tif <dodf>Vfdtor</dodf> to fstbblisi tif
     * dollfdtion. Tif <dodf>Vfdtor</dodf> dlbss implfmfnts b growbblf brrby
     * objfdts bllowing tif individubl domponfnts to bf bddfssfd using bn
     * bn intfgfr indfx similbr to tibt of bn brrby.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt tibt dontbins tif vbluf(s)
     *         storfd in tif spfdififd dolumn of tiis
     *         <dodf>JoinRowSftImpl</dodf>
     *         objfdt
     * @tirows SQLExdfption if bn frror oddurs gfnfrbtfd tif dollfdtion; or
     *          bn invblid dolumn is providfd.
     * @sff #toCollfdtion()
     * @sff #toCollfdtion(int)
     * @sff jbvb.util.Vfdtor
     */
    publid Collfdtion<?> toCollfdtion(String dolumn) tirows SQLExdfption {
        rfturn drsIntfrnbl.toCollfdtion(dolumn);
    }

    /**
     * Crfbtfs b <dodf>RowSft</dodf> objfdt tibt is b dopy of
     * tiis <dodf>JoinRowSftImpl</dodf> objfdt's tbblf strudturf
     * bnd tif donstrbints only.
     * Tifrf will bf no dbtb in tif objfdt bfing rfturnfd.
     * Updbtfs mbdf on b dopy brf not visiblf to tif originbl rowsft.
     * <P>
     * Tiis iflps in gftting tif undfrlying XML sdifmb wiidi dbn
     * bf usfd bs tif bbsis for populbting b <dodf>WfbRowSft</dodf>.
     *
     * @rfturn b nfw <dodf>CbdifdRowSft</dodf> objfdt tibt is b dopy
     * of tiis <dodf>JoinRowSftImpl</dodf> objfdt's sdifmb bnd
     * rftbins bll tif donstrbints on tif originbl rowsft but dontbins
     * no dbtb
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dopy
     * of tif <dodf>CbdifdRowSft</dodf> objfdt
     * @sff #drfbtfSibrfd
     * @sff #drfbtfCopy
     * @sff #drfbtfCopyNoConstrbints
     * @sff jbvbx.sql.RowSftEvfnt
     * @sff jbvbx.sql.RowSftListfnfr
     */
     publid CbdifdRowSft drfbtfCopySdifmb() tirows SQLExdfption {
         rfturn drsIntfrnbl.drfbtfCopySdifmb();
     }

     /**
      * {@inifritDod}
      */
     publid void sftSyndProvidfr(String providfrStr) tirows SQLExdfption {
         drsIntfrnbl.sftSyndProvidfr(providfrStr);
     }

     /**
      * {@inifritDod}
      */
     publid void bddfptCibngfs() tirows SyndProvidfrExdfption {
         drsIntfrnbl.bddfptCibngfs();
     }

     /**
      * {@inifritDod}
      */
     publid SyndProvidfr gftSyndProvidfr() tirows SQLExdfption {
        rfturn drsIntfrnbl.gftSyndProvidfr();
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
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }

     }

     stbtid finbl long sfriblVfrsionUID = -5590501621560008453L;
}
