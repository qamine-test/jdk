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
import jbvb.io.*;
import jbvb.mbti.*;
import jbvb.util.*;
import jbvb.tfxt.*;

import jbvbx.sql.rowsft.*;
import jbvbx.sql.rowsft.spi.*;
import jbvbx.sql.rowsft.sfribl.*;
import dom.sun.rowsft.intfrnbl.*;
import dom.sun.rowsft.providfrs.*;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Tif stbndbrd implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
 *
 * Sff intfrfbdf dffinition for full bfibvior bnd implfmfntbtion rfquirfmfnts.
 * Tiis rfffrfndf implfmfntbtion ibs mbdf provision for b onf-to-onf writf bbdk
 * fbdility bnd it is durrfmtly bf possiblf to dibngf tif pfristfndf providfr
 * during tif liff-timf of bny CbdifdRowSftImpl.
 *
 * @butior Jonbtibn Brudf, Amit Hbndb
 */

publid dlbss CbdifdRowSftImpl fxtfnds BbsfRowSft implfmfnts RowSft, RowSftIntfrnbl, Sfriblizbblf, Clonfbblf, CbdifdRowSft {

    /**
     * Tif <dodf>SyndProvidfr</dodf> usfd by tif CbdifdRowSft
     */
    privbtf SyndProvidfr providfr;

    /**
     * Tif <dodf>RowSftRfbdfrImpl</dodf> objfdt tibt is tif rfbdfr
     * for tiis rowsft.  Tif mftiod <dodf>fxfdutf</dodf> usfs tiis
     * rfbdfr bs pbrt of its implfmfntbtion.
     * @sfribl
     */
    privbtf RowSftRfbdfr rowSftRfbdfr;

    /**
     * Tif <dodf>RowSftWritfrImpl</dodf> objfdt tibt is tif writfr
     * for tiis rowsft.  Tif mftiod <dodf>bddfptCibngfs</dodf> usfs
     * tiis writfr bs pbrt of its implfmfntbtion.
     * @sfribl
     */
    privbtf RowSftWritfr rowSftWritfr;

    /**
     * Tif <dodf>Connfdtion</dodf> objfdt tibt donnfdts witi tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt's durrfnt undfrlying dbtb sourdf.
     */
    privbtf trbnsifnt Connfdtion donn;

    /**
     * Tif <dodf>RfsultSftMftbDbtb</dodf> objfdt tibt dontbins informbtion
     * bbout tif dolumns in tif <dodf>RfsultSft</dodf> objfdt tibt is tif
     * durrfnt sourdf of dbtb for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.
     */
    privbtf trbnsifnt RfsultSftMftbDbtb RSMD;

    /**
     * Tif <dodf>RowSftMftbDbtb</dodf> objfdt tibt dontbins informbtion bbout
     * tif dolumns in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.
     * @sfribl
     */
    privbtf RowSftMftbDbtbImpl RowSftMD;

    // Propfrtifs of tiis RowSft

    /**
     * An brrby dontbining tif dolumns in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt tibt form b uniquf idfntififr for b row. Tiis brrby
     * is usfd by tif writfr.
     * @sfribl
     */
    privbtf int kfyCols[];

    /**
     * Tif nbmf of tif tbblf in tif undfrlying dbtbbbsf to wiidi updbtfs
     * siould bf writtfn.  Tiis nbmf is nffdfd bfdbusf most drivfrs
     * do not rfturn tiis informbtion in b <dodf>RfsultSftMftbDbtb</dodf>
     * objfdt.
     * @sfribl
     */
    privbtf String tbblfNbmf;

    /**
     * A <dodf>Vfdtor</dodf> objfdt dontbining tif <dodf>Row</dodf>
     * objfdts tibt domprisf  tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.
     * @sfribl
     */
    privbtf Vfdtor<Objfdt> rvi;

    /**
     * Tif durrfnt position of tif dursor in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt.
     * @sfribl
     */
    privbtf int dursorPos;

    /**
     * Tif durrfnt position of tif dursor in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt not dounting rows tibt ibvf bffn dflftfd, if bny.
     * <P>
     * For fxbmplf, supposf tibt tif dursor is on tif lbst row of b rowsft
     * tibt stbrtfd witi fivf rows bnd subsfqufntly ibd tif sfdond bnd tiird
     * rows dflftfd. Tif <dodf>bbsolutfPos</dodf> would bf <dodf>3</dodf>,
     * wifrfbs tif <dodf>dursorPos</dodf> would bf <dodf>5</dodf>.
     * @sfribl
     */
    privbtf int bbsolutfPos;

    /**
     * Tif numbfr of dflftfd rows durrfntly in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt.
     * @sfribl
     */
    privbtf int numDflftfd;

    /**
     * Tif totbl numbfr of rows durrfntly in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt.
     * @sfribl
     */
    privbtf int numRows;

    /**
     * A spfdibl row usfd for donstrudting b nfw row. A nfw
     * row is donstrudtfd by using <dodf>RfsultSft.updbtfXXX</dodf>
     * mftiods to insfrt dolumn vblufs into tif insfrt row.
     * @sfribl
     */
    privbtf InsfrtRow insfrtRow;

    /**
     * A <dodf>boolfbn</dodf> indidbting wiftifr tif dursor is
     * durrfntly on tif insfrt row.
     * @sfribl
     */
    privbtf boolfbn onInsfrtRow;

    /**
     * Tif fifld tibt tfmporbrily iolds tif lbst position of tif
     * dursor bfforf it movfd to tif insfrt row, tius prfsfrving
     * tif numbfr of tif durrfnt row to wiidi tif dursor mby rfturn.
     * @sfribl
     */
    privbtf int durrfntRow;

    /**
     * A <dodf>boolfbn</dodf> indidbting wiftifr tif lbst vbluf
     * rfturnfd wbs bn SQL <dodf>NULL</dodf>.
     * @sfribl
     */
    privbtf boolfbn lbstVblufNull;

    /**
     * A <dodf>SQLWbrning</dodf> wiidi logs on tif wbrnings
     */
    privbtf SQLWbrning sqlwbrn;

    /**
     * Usfd to trbdk mbtdi dolumn for JoinRowSft donsumption
     */
    privbtf String strMbtdiColumn ="";

    /**
     * Usfd to trbdk mbtdi dolumn for JoinRowSft donsumption
     */
    privbtf int iMbtdiColumn = -1;

    /**
     * A <dodf>RowSftWbrning</dodf> wiidi logs on tif wbrnings
     */
    privbtf RowSftWbrning rowsftWbrning;

    /**
     * Tif dffbult SyndProvidfr for tif RI CbdifdRowSftImpl
     */
    privbtf String DEFAULT_SYNC_PROVIDER = "dom.sun.rowsft.providfrs.RIOptimistidProvidfr";

    /**
     * Tif boolfbn vbribblf indidbting lodbtorsUpdbtfVbluf
     */
    privbtf boolfbn dbmslodbtorsUpdbtfCopy;

    /**
     * Tif <dodf>RfsultSft</dodf> objfdt tibt is usfd to mbintbin tif dbtb wifn
     * b RfsultSft bnd stbrt position brf pbssfd bs pbrbmftfrs to tif populbtf fundtion
     */
    privbtf trbnsifnt RfsultSft rfsultSft;

    /**
     * Tif intfgfr vbluf indidbting tif fnd position in tif RfsultSftwifrf tif pidking
     * up of rows for populbting b CbdifdRowSft objfdt wbs lfft off.
     */
    privbtf int fndPos;

    /**
     * Tif intfgfr vbluf indidbting tif fnd position in tif RfsultSftwifrf tif pidking
     * up of rows for populbting b CbdifdRowSft objfdt wbs lfft off.
     */
    privbtf int prfvEndPos;

    /**
     * Tif intfgfr vbluf indidbting tif position in tif RfsultSft, to populbtf tif
     * CbdifdRowSft objfdt.
     */
    privbtf int stbrtPos;

    /**
     * Tif intfgfr vbluf indidbting tif position from wifrf tif pbgf prior to tiis
     * wbs populbtfd.
     */
    privbtf int stbrtPrfv;

    /**
     * Tif intfgfr vbluf indidbting sizf of tif pbgf.
     */
    privbtf int pbgfSizf;

    /**
     * Tif intfgfr vbluf indidbting numbfr of rows tibt ibvf bffn prodfssfd so fbr.
     * Usfd for difdking wiftifr mbxRows ibs bffn rfbdifd or not.
     */
    privbtf int mbxRowsrfbdifd;
    /**
     * Tif boolfbn vbluf wifn truf signififs tibt pbgfs brf still to follow bnd b
     * fblsf vbluf indidbtfs tibt tiis is tif lbst pbgf.
     */
    privbtf boolfbn pbgfnotfnd = truf;

    /**
     * Tif boolfbn vbluf indidbting wiftifr tiis is tif first pbgf or not.
     */
    privbtf boolfbn onFirstPbgf;

    /**
     * Tif boolfbn vbluf indidbting wiftifr tiis is tif lbst pbgf or not.
     */
    privbtf boolfbn onLbstPbgf;

    /**
     * Tif intfgfr vbluf indidbting iow mbny timfs tif populbtf fundtion ibs bffn dbllfd.
     */
    privbtf int populbtfdblldount;

    /**
     * Tif intfgfr vbluf indidbting tif totbl numbfr of rows to bf prodfssfd in tif
     * RfsultSft objfdt pbssfd to tif populbtf fundtion.
     */
    privbtf int totblRows;

    /**
     * Tif boolfbn vbluf indidbting iow tif CbifdRowSft objfdt ibs bffn populbtfd for
     * pbging purposf. Truf indidbtfs tibt donnfdtion pbrbmftfr is pbssfd.
     */
    privbtf boolfbn dbllWitiCon;

    /**
     * CbdifdRowSft rfbdfr objfdt to rfbd tif dbtb from tif RfsultSft wifn b donnfdtion
     * pbrbmftfr is pbssfd to populbtf tif CbdifdRowSft objfdt for pbging.
     */
    privbtf CbdifdRowSftRfbdfr drsRfbdfr;

    /**
     * Tif Vfdtor iolding tif Mbtdi Columns
     */
    privbtf Vfdtor<Intfgfr> iMbtdiColumns;

    /**
     * Tif Vfdtor tibt will iold tif Mbtdi Column nbmfs.
     */
    privbtf Vfdtor<String> strMbtdiColumns;

    /**
     * Triggfr tibt indidbtfs wiftifr tif bdtivf SyndProvidfr is fxposfs tif
     * bdditionbl TrbnsbdtionblWritfr mftiod
     */
    privbtf boolfbn tXWritfr = fblsf;

    /**
     * Tif fifld objfdt for b trbnsbdtionbl RowSft writfr
     */
    privbtf TrbnsbdtionblWritfr tWritfr = null;

    protfdtfd trbnsifnt JdbdRowSftRfsourdfBundlf rfsBundlf;

    privbtf boolfbn updbtfOnInsfrt;



    /**
     * Construdts b nfw dffbult <dodf>CbdifdRowSftImpl</dodf> objfdt witi
     * tif dbpbdity to iold 100 rows. Tiis nfw objfdt ibs no mftbdbtb
     * bnd ibs tif following dffbult vblufs:
     * <prf>
     *     onInsfrtRow = fblsf
     *     insfrtRow = null
     *     dursorPos = 0
     *     numRows = 0
     *     siowDflftfd = fblsf
     *     qufryTimfout = 0
     *     mbxRows = 0
     *     mbxFifldSizf = 0
     *     rowSftTypf = RfsultSft.TYPE_SCROLL_INSENSITIVE
     *     dondurrfndy = RfsultSft.CONCUR_UPDATABLE
     *     rfbdOnly = fblsf
     *     isolbtion = Connfdtion.TRANSACTION_READ_COMMITTED
     *     fsdbpfProdfssing = truf
     *     onInsfrtRow = fblsf
     *     insfrtRow = null
     *     dursorPos = 0
     *     bbsolutfPos = 0
     *     numRows = 0
     * </prf>
     * A <dodf>CbdifdRowSftImpl</dodf> objfdt is donfigurfd to usf tif dffbult
     * <dodf>RIOptimistidProvidfr</dodf> implfmfntbtion to providf donnfdtivity
     * bnd syndironizbtion dbpbbilitifs to tif sft dbtb sourdf.
     * <P>
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid CbdifdRowSftImpl() tirows SQLExdfption {

        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }

        // sft tif Rfbdfr, tiis mbybf ovfrriddfn lbttfr
        providfr =
        SyndFbdtory.gftInstbndf(DEFAULT_SYNC_PROVIDER);

        if (!(providfr instbndfof RIOptimistidProvidfr)) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invblidp").toString());
        }

        rowSftRfbdfr = (CbdifdRowSftRfbdfr)providfr.gftRowSftRfbdfr();
        rowSftWritfr = (CbdifdRowSftWritfr)providfr.gftRowSftWritfr();

        // bllodbtf tif pbrbmftfrs dollfdtion
        initPbrbms();

        initContbinfr();

        // sft up somf dffbult vblufs
        initPropfrtifs();

        // insfrt row sftup
        onInsfrtRow = fblsf;
        insfrtRow = null;

        // sft tif wbrninings
        sqlwbrn = nfw SQLWbrning();
        rowsftWbrning = nfw RowSftWbrning();

    }

    /**
     * Providfs b <dodf>CbdifdRowSftImpl</dodf> instbndf witi tif sbmf dffbult propfrtifs bs
     * bs tif zfro pbrbmftfr donstrudtor.
     * <prf>
     *     onInsfrtRow = fblsf
     *     insfrtRow = null
     *     dursorPos = 0
     *     numRows = 0
     *     siowDflftfd = fblsf
     *     qufryTimfout = 0
     *     mbxRows = 0
     *     mbxFifldSizf = 0
     *     rowSftTypf = RfsultSft.TYPE_SCROLL_INSENSITIVE
     *     dondurrfndy = RfsultSft.CONCUR_UPDATABLE
     *     rfbdOnly = fblsf
     *     isolbtion = Connfdtion.TRANSACTION_READ_COMMITTED
     *     fsdbpfProdfssing = truf
     *     onInsfrtRow = fblsf
     *     insfrtRow = null
     *     dursorPos = 0
     *     bbsolutfPos = 0
     *     numRows = 0
     * </prf>
     *
     * Howfvfr, bpplidbtions will ibvf tif mfbns to spfdify bt runtimf tif
     * dfsirfd <dodf>SyndProvidfr</dodf> objfdt.
     * <p>
     * For fxbmplf, drfbting b <dodf>CbdifdRowSftImpl</dodf> objfdt bs follows fnsurfs
     * tibt b it is fstbblisifd witi tif <dodf>dom.foo.providfr.Impl</dodf> syndironizbtion
     * implfmfntbtion providing tif syndironizbtion mfdibnism for tiis disdonnfdtfd
     * <dodf>RowSft</dodf> objfdt.
     * <prf>
     *     Hbsitbblf fnv = nfw Hbsitbblf();
     *     fnv.put(jbvbx.sql.rowsft.spi.SyndFbdtory.ROWSET_PROVIDER_NAME,
     *         "dom.foo.providfr.Impl");
     *     CbdifdRowSftImpl drs = nfw CbdifdRowSft(fnv);
     * </prf>
     * <p>
     * Cblling tiis donstrudtor witi b <dodf>null</dodf> pbrbmftfr will
     * dbusf tif <dodf>SyndFbdtory</dodf> to providf tif rfffrfndf
     * optimistid providfr <dodf>dom.sun.rowsft.providfrs.RIOptimistidProvidfr</dodf>.
     * <p>
     * In bddition, tif following propfrtifs dbn bf bssodibtfd witi tif
     * providfr to bssist in dftfrmining tif dioidf of tif syndironizbton
     * providfr sudi bs:
     * <ul>
     * <li><dodf>ROWSET_SYNC_PROVIDER</dodf> - tif propfrty spfdifying tif tif
     * <dodf>SyndProvidfr</dodf> dlbss nbmf to bf instbntibtfd by tif
     * <dodf>SyndFbdttory</dodf>
     * <li><dodf>ROWSET_SYNC_VENDOR</dodf> - tif propfrty spfdifying tif softwbrf
     * vfndor bssodibtfd witi b <dodf>SyndProvidfr</dodf> implfmfntbtion.
     * <li><dodf>ROWSET_SYNC_PROVIDER_VER</dodf> - tif propfrty spfdifying tif
     * vfrsion of tif <dodf>SyndProvidfr</dodf> implfmfntbtion providfd by tif
     * softwbrf vfndor.
     * </ul>
     * Morf spfdifid dftbilfs brf bvbilbblf in tif <dodf>SyndFbdtory</dodf>
     * bnd <dodf>SyndProvidfr</dodf> spfdifidibtions lbtfr in tiis dodumfnt.
     * <p>
     * @pbrbm fnv b <dodf>Hbsitbblf</dodf> objfdt witi b list of dfsirfd
     *        syndironizbtion providfrs
     * @tirows SQLExdfption if tif rfqufstfd providfr dbnnot bf found by tif
     * syndironizbtion fbdtory
     * @sff SyndProvidfr
     */
    publid CbdifdRowSftImpl(@SupprfssWbrnings("rbwtypfs") Hbsitbblf fnv) tirows SQLExdfption {


        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }

        if (fnv == null) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.nullibsi").toString());
        }

        String providfrNbmf = (String)fnv.gft(
        jbvbx.sql.rowsft.spi.SyndFbdtory.ROWSET_SYNC_PROVIDER);

        // sft tif Rfbdfr, tiis mbybf ovfrriddfn lbttfr
        providfr =
        SyndFbdtory.gftInstbndf(providfrNbmf);

        rowSftRfbdfr = providfr.gftRowSftRfbdfr();
        rowSftWritfr = providfr.gftRowSftWritfr();

        initPbrbms(); // bllodbtf tif pbrbmftfrs dollfdtion
        initContbinfr();
        initPropfrtifs(); // sft up somf dffbult vblufs
    }

    /**
     * Sfts tif <dodf>rvi</dodf> fifld to b nfw <dodf>Vfdtor</dodf>
     * objfdt witi b dbpbdity of 100 bnd sfts tif
     * <dodf>dursorPos</dodf> bnd <dodf>numRows</dodf> fiflds to zfro.
     */
    privbtf void initContbinfr() {

        rvi = nfw Vfdtor<Objfdt>(100);
        dursorPos = 0;
        bbsolutfPos = 0;
        numRows = 0;
        numDflftfd = 0;
    }

    /**
     * Sfts tif propfrtifs for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt to
     * tifir dffbult vblufs. Tiis mftiod is dbllfd intfrnblly by tif
     * dffbult donstrudtor.
     */

    privbtf void initPropfrtifs() tirows SQLExdfption {

        if(rfsBundlf == null) {
            try {
               rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
            } dbtdi(IOExdfption iof) {
                tirow nfw RuntimfExdfption(iof);
            }
        }
        sftSiowDflftfd(fblsf);
        sftQufryTimfout(0);
        sftMbxRows(0);
        sftMbxFifldSizf(0);
        sftTypf(RfsultSft.TYPE_SCROLL_INSENSITIVE);
        sftCondurrfndy(RfsultSft.CONCUR_UPDATABLE);
        if((rvi.sizf() > 0) && (isRfbdOnly() == fblsf))
            sftRfbdOnly(fblsf);
        flsf
            sftRfbdOnly(truf);
        sftTrbnsbdtionIsolbtion(Connfdtion.TRANSACTION_READ_COMMITTED);
        sftEsdbpfProdfssing(truf);
        //sftTypfMbp(null);
        difdkTrbnsbdtionblWritfr();

        //Instbntibting tif vfdtor for MbtdiColumns

        iMbtdiColumns = nfw Vfdtor<Intfgfr>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtdiColumns.bdd(i, -1);
        }

        strMbtdiColumns = nfw Vfdtor<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtdiColumns.bdd(j,null);
        }
    }

    /**
     * Dftfrminf wiftifr tif SyndProvidfr's writfr implfmfnts tif
     * <dodf>TrbnsbdtionblWritfr<dodf> intfrfbdf
     */
    privbtf void difdkTrbnsbdtionblWritfr() {
        if (rowSftWritfr != null) {
            Clbss<?> d = rowSftWritfr.gftClbss();
            if (d != null) {
                Clbss<?>[] tifIntfrfbdfs = d.gftIntfrfbdfs();
                for (int i = 0; i < tifIntfrfbdfs.lfngti; i++) {
                    if ((tifIntfrfbdfs[i].gftNbmf()).indfxOf("TrbnsbdtionblWritfr") > 0) {
                        tXWritfr = truf;
                        fstbblisiTrbnsbdtionblWritfr();
                    }
                }
            }
        }
    }

    /**
     * Sfts bn privbtf fifld to bll trbnsbdtion bounddbrifs to bf sft
     */
    privbtf void fstbblisiTrbnsbdtionblWritfr() {
        tWritfr = (TrbnsbdtionblWritfr)providfr.gftRowSftWritfr();
    }

    //-----------------------------------------------------------------------
    // Propfrtifs
    //-----------------------------------------------------------------------

    /**
     * Sfts tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dommbnd propfrty
     * to tif givfn <dodf>String</dodf> objfdt bnd dlfbrs tif pbrbmftfrs,
     * if bny, tibt wfrf sft for tif prfvious dommbnd.
     * <P>
     * Tif dommbnd propfrty mby not bf nffdfd
     * if tif rowsft is produdfd by b dbtb sourdf, sudi bs b sprfbdsifft,
     * tibt dofs not support dommbnds. Tius, tiis propfrty is optionbl
     * bnd mby bf <dodf>null</dodf>.
     *
     * @pbrbm dmd b <dodf>String</dodf> objfdt dontbining bn SQL qufry
     *            tibt will bf sft bs tif dommbnd; mby bf <dodf>null</dodf>
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void sftCommbnd(String dmd) tirows SQLExdfption {

        supfr.sftCommbnd(dmd);

        if(!buildTbblfNbmf(dmd).fqubls("")) {
            tiis.sftTbblfNbmf(buildTbblfNbmf(dmd));
        }
    }


    //---------------------------------------------------------------------
    // Rfbding bnd writing dbtb
    //---------------------------------------------------------------------

    /**
     * Populbtfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi dbtb from
     * tif givfn <dodf>RfsultSft</dodf> objfdt.  Tiis
     * mftiod is bn bltfrnbtivf to tif mftiod <dodf>fxfdutf</dodf>
     * for filling tif rowsft witi dbtb.  Tif mftiod <dodf>populbtf</dodf>
     * dofs not rfquirf tibt tif propfrtifs nffdfd by tif mftiod
     * <dodf>fxfdutf</dodf>, sudi bs tif <dodf>dommbnd</dodf> propfrty,
     * bf sft. Tiis is truf bfdbusf tif mftiod <dodf>populbtf</dodf>
     * is givfn tif <dodf>RfsultSft</dodf> objfdt from
     * wiidi to gft dbtb bnd tius dofs not nffd to usf tif propfrtifs
     * rfquirfd for sftting up b donnfdtion bnd fxfduting tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt's dommbnd.
     * <P>
     * Aftfr populbting tiis rowsft witi dbtb, tif mftiod
     * <dodf>populbtf</dodf> sfts tif rowsft's mftbdbtb bnd
     * tifn sfnds b <dodf>RowSftCibngfdEvfnt</dodf> objfdt
     * to bll rfgistfrfd listfnfrs prior to rfturning.
     *
     * @pbrbm dbtb tif <dodf>RfsultSft</dodf> objfdt dontbining tif dbtb
     *             to bf rfbd into tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if bn frror oddurs; or tif mbx row sftting is
     *          violbtfd wiilf populbting tif RowSft
     * @sff #fxfdutf
     */

     publid void populbtf(RfsultSft dbtb) tirows SQLExdfption {
        int rowsFftdifd;
        Row durrfntRow;
        int numCols;
        int i;
        Mbp<String, Clbss<?>> mbp = gftTypfMbp();
        Objfdt obj;
        int mRows;

        if (dbtb == null) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.populbtf").toString());
        }
        tiis.rfsultSft = dbtb;

        // gft tif mftb dbtb for tiis RfsultSft
        RSMD = dbtb.gftMftbDbtb();

        // sft up tif mftbdbtb
        RowSftMD = nfw RowSftMftbDbtbImpl();
        initMftbDbtb(RowSftMD, RSMD);

        // rflfbsf tif mftb-dbtb so tibt brfn't tfmptfd to usf it.
        RSMD = null;
        numCols = RowSftMD.gftColumnCount();
        mRows = tiis.gftMbxRows();
        rowsFftdifd = 0;
        durrfntRow = null;

        wiilf ( dbtb.nfxt()) {

            durrfntRow = nfw Row(numCols);

            if ( rowsFftdifd > mRows && mRows > 0) {
                rowsftWbrning.sftNfxtWbrning(nfw RowSftWbrning("Populbting rows "
                + "sftting ibs fxdffdfd mbx row sftting"));
            }
            for ( i = 1; i <= numCols; i++) {
                /*
                 * difdk if tif usfr ibs sft b mbp. If no mbp
                 * is sft tifn usf plbin gftObjfdt. Tiis lfts
                 * us work witi drivfrs tibt do not support
                 * gftObjfdt witi b mbp in fbirly sfnsiblf wby
                 */
                if (mbp == null || mbp.isEmpty()) {
                    obj = dbtb.gftObjfdt(i);
                } flsf {
                    obj = dbtb.gftObjfdt(i, mbp);
                }
                /*
                 * tif following blodk difdks for tif vbrious
                 * typfs tibt wf ibvf to sfriblizf in ordfr to
                 * storf - rigit now only strudts ibvf bffn tfstfd
                 */
                if (obj instbndfof Strudt) {
                    obj = nfw SfriblStrudt((Strudt)obj, mbp);
                } flsf if (obj instbndfof SQLDbtb) {
                    obj = nfw SfriblStrudt((SQLDbtb)obj, mbp);
                } flsf if (obj instbndfof Blob) {
                    obj = nfw SfriblBlob((Blob)obj);
                } flsf if (obj instbndfof Clob) {
                    obj = nfw SfriblClob((Clob)obj);
                } flsf if (obj instbndfof jbvb.sql.Arrby) {
                    if(mbp != null)
                        obj = nfw SfriblArrby((jbvb.sql.Arrby)obj, mbp);
                    flsf
                        obj = nfw SfriblArrby((jbvb.sql.Arrby)obj);
                }

                durrfntRow.initColumnObjfdt(i, obj);
            }
            rowsFftdifd++;
            rvi.bdd(durrfntRow);
        }

        numRows = rowsFftdifd ;
        // Also rowsFftdifd siould bf fqubl to rvi.sizf()

        // notify bny listfnfrs tibt tif rowsft ibs dibngfd
        notifyRowSftCibngfd();


    }

    /**
     * Initiblizfs tif givfn <dodf>RowSftMftbDbtb</dodf> objfdt witi tif vblufs
     * in tif givfn <dodf>RfsultSftMftbDbtb</dodf> objfdt.
     *
     * @pbrbm md tif <dodf>RowSftMftbDbtb</dodf> objfdt for tiis
     *           <dodf>CbdifdRowSftImpl</dodf> objfdt, wiidi will bf sft witi
     *           vblufs from rsmd
     * @pbrbm rsmd tif <dodf>RfsultSftMftbDbtb</dodf> objfdt from wiidi nfw
     *             vblufs for md will bf rfbd
     * @tirows SQLExdfption if bn frror oddurs
     */
    privbtf void initMftbDbtb(RowSftMftbDbtbImpl md, RfsultSftMftbDbtb rsmd) tirows SQLExdfption {
        int numCols = rsmd.gftColumnCount();

        md.sftColumnCount(numCols);
        for (int dol=1; dol <= numCols; dol++) {
            md.sftAutoIndrfmfnt(dol, rsmd.isAutoIndrfmfnt(dol));
            if(rsmd.isAutoIndrfmfnt(dol))
                updbtfOnInsfrt = truf;
            md.sftCbsfSfnsitivf(dol, rsmd.isCbsfSfnsitivf(dol));
            md.sftCurrfndy(dol, rsmd.isCurrfndy(dol));
            md.sftNullbblf(dol, rsmd.isNullbblf(dol));
            md.sftSignfd(dol, rsmd.isSignfd(dol));
            md.sftSfbrdibblf(dol, rsmd.isSfbrdibblf(dol));
             /*
             * Tif PostgrfSQL drivfrs somftimfs rfturn nfgbtivf dolumnDisplbySizf,
             * wiidi dbusfs bn fxdfption to bf tirown.  Cifdk for it.
             */
            int sizf = rsmd.gftColumnDisplbySizf(dol);
            if (sizf < 0) {
                sizf = 0;
            }
            md.sftColumnDisplbySizf(dol, sizf);
            md.sftColumnLbbfl(dol, rsmd.gftColumnLbbfl(dol));
            md.sftColumnNbmf(dol, rsmd.gftColumnNbmf(dol));
            md.sftSdifmbNbmf(dol, rsmd.gftSdifmbNbmf(dol));
            /*
             * Drivfrs rfturn somf strbngf vblufs for prfdision, for non-numfrid dbtb, indluding rfports of
             * non-intfgfr vblufs; mbybf wf siould difdk typf, & sft to 0 for non-numfrid typfs.
             */
            int prfdision = rsmd.gftPrfdision(dol);
            if (prfdision < 0) {
                prfdision = 0;
            }
            md.sftPrfdision(dol, prfdision);

            /*
             * It sffms, from b bug rfport, tibt b drivfr dbn somftimfs rfturn b nfgbtivf
             * vbluf for sdblf.  jbvbx.sql.rowsft.RowSftMftbDbtbImpl will tirow bn fxdfption
             * if wf bttfmpt to sft b nfgbtivf vbluf.  As sudi, wf'll difdk for tiis dbsf.
             */
            int sdblf = rsmd.gftSdblf(dol);
            if (sdblf < 0) {
                sdblf = 0;
            }
            md.sftSdblf(dol, sdblf);
            md.sftTbblfNbmf(dol, rsmd.gftTbblfNbmf(dol));
            md.sftCbtblogNbmf(dol, rsmd.gftCbtblogNbmf(dol));
            md.sftColumnTypf(dol, rsmd.gftColumnTypf(dol));
            md.sftColumnTypfNbmf(dol, rsmd.gftColumnTypfNbmf(dol));
        }

        if( donn != null){
           // JDBC 4.0 mbndbtfs bs dofs tif Jbvb EE spfd tibt bll DbtbBbsfMftbDbtb mftiods
           // must bf implfmfntfd, tifrfforf, tif prfvious fix for 5055528 is bfing bbdkfd out
            dbmslodbtorsUpdbtfCopy = donn.gftMftbDbtb().lodbtorsUpdbtfCopy();
        }
    }

    /**
     * Populbtfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi dbtb,
     * using tif givfn donnfdtion to produdf tif rfsult sft from
     * wiidi dbtb will bf rfbd.  A sfdond form of tiis mftiod,
     * wiidi tbkfs no brgumfnts, usfs tif vblufs from tiis rowsft's
     * usfr, pbssword, bnd fitifr url or dbtb sourdf propfrtifs to
     * drfbtf b nfw dbtbbbsf donnfdtion. Tif form of <dodf>fxfdutf</dodf>
     * tibt is givfn b donnfdtion ignorfs tifsf propfrtifs.
     *
     * @pbrbm donn A stbndbrd JDBC <dodf>Connfdtion</dodf> objfdt tibt tiis
     * <dodf>CbdifdRowSft</dodf> objfdt dbn pbss to b syndironizbtion providfr
     * to fstbblisi b donnfdtion to tif dbtb sourdf
     * @tirows SQLExdfption if bn invblid <dodf>Connfdtion</dodf> is supplifd
     *           or bn frror oddurs in fstbblisiing tif donnfdtion to tif
     *           dbtb sourdf
     * @sff #populbtf
     * @sff jbvb.sql.Connfdtion
     */
    publid void fxfdutf(Connfdtion donn) tirows SQLExdfption {
        // storf tif donnfdtion so tif rfbdfr dbn find it.
        sftConnfdtion(donn);

        if(gftPbgfSizf() != 0){
            drsRfbdfr = (CbdifdRowSftRfbdfr)providfr.gftRowSftRfbdfr();
            drsRfbdfr.sftStbrtPosition(1);
            dbllWitiCon = truf;
            drsRfbdfr.rfbdDbtb((RowSftIntfrnbl)tiis);
        }

        // Now dbll tif durrfnt rfbdfr's rfbdDbtb mftiod
        flsf {
           rowSftRfbdfr.rfbdDbtb((RowSftIntfrnbl)tiis);
        }
        RowSftMD = (RowSftMftbDbtbImpl)tiis.gftMftbDbtb();

        if(donn != null){
            // JDBC 4.0 mbndbtfs bs dofs tif Jbvb EE spfd tibt bll DbtbBbsfMftbDbtb mftiods
            // must bf implfmfntfd, tifrfforf, tif prfvious fix for 5055528 is bfing bbdkfd out
            dbmslodbtorsUpdbtfCopy = donn.gftMftbDbtb().lodbtorsUpdbtfCopy();
        }

    }

    /**
     * Sfts tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's donnfdtion propfrty
     * to tif givfn <dodf>Connfdtion</dodf> objfdt.  Tiis mftiod is dbllfd
     * intfrnblly by tif vfrsion of tif mftiod <dodf>fxfdutf</dodf> tibt tbkfs b
     * <dodf>Connfdtion</dodf> objfdt bs bn brgumfnt. Tif rfbdfr for tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt dbn rftrifvf tif donnfdtion storfd
     * in tif rowsft's donnfdtion propfrty by dblling its
     * <dodf>gftConnfdtion</dodf> mftiod.
     *
     * @pbrbm donnfdtion tif <dodf>Connfdtion</dodf> objfdt tibt wbs pbssfd in
     *                   to tif mftiod <dodf>fxfdutf</dodf> bnd is to bf storfd
     *                   in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's donnfdtion
     *                   propfrty
     */
    privbtf void sftConnfdtion (Connfdtion donnfdtion) {
        donn = donnfdtion;
    }


    /**
     * Propbgbtfs bll row updbtf, insfrt, bnd dflftf dibngfs to tif
     * undfrlying dbtb sourdf bbdking tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt.
     * <P>
     * <b>Notf</b>In tif rfffrfndf implfmfntbtion bn optimistid dondurrfndy implfmfntbtion
     * is providfd bs b sbmplf implfmfntbtion of b tif <dodf>SyndProvidfr</dodf>
     * bbstrbdt dlbss.
     * <P>
     * Tiis mftiod fbils if bny of tif updbtfs dbnnot bf propbgbtfd bbdk
     * to tif dbtb sourdf.  Wifn it fbils, tif dbllfr dbn bssumf tibt
     * nonf of tif updbtfs brf rfflfdtfd in tif dbtb sourdf.
     * Wifn bn fxdfption is tirown, tif durrfnt row
     * is sft to tif first "updbtfd" row tibt rfsultfd in bn fxdfption
     * unlfss tif row tibt dbusfd tif fxdfption is b "dflftfd" row.
     * In tibt dbsf, wifn dflftfd rows brf not siown, wiidi is usublly truf,
     * tif durrfnt row is not bfffdtfd.
     * <P>
     * If no <dodf>SyndProvidfr</dodf> is donfigurfd, tif rfffrfndf implfmfntbtion
     * lfvfrbgfs tif <dodf>RIOptimistidProvidfr</dodf> bvbilbblf wiidi providfs tif
     * dffbult bnd rfffrfndf syndironizbtion dbpbbilitifs for disdonnfdtfd
     * <dodf>RowSfts</dodf>.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or tif undfrlying
     *          rfffrfndf syndironizbtion providfr fbils to dommit tif updbtfs
     *          to tif dbtbsourdf
     * @tirows SyndProvidfrExdfption if bn intfrnbl frror oddurs witiin tif
     *          <dodf>SyndProvidfr</dodf> instbndf during fitifr during tif
     *          prodfss or bt bny timf wifn tif <dodf>SyndProvidfr</dodf>
     *          instbndf toudifs tif dbtb sourdf.
     * @sff #bddfptCibngfs(jbvb.sql.Connfdtion)
     * @sff jbvbx.sql.RowSftWritfr
     * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
     */
    publid void bddfptCibngfs() tirows SyndProvidfrExdfption {
        if (onInsfrtRow == truf) {
            tirow nfw SyndProvidfrExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invblidop").toString());
        }

        int sbvfCursorPos = dursorPos;
        boolfbn suddfss = fblsf;
        boolfbn donflidt = fblsf;

        try {
            if (rowSftWritfr != null) {
                sbvfCursorPos = dursorPos;
                donflidt = rowSftWritfr.writfDbtb((RowSftIntfrnbl)tiis);
                dursorPos = sbvfCursorPos;
            }

            if (tXWritfr) {
                // do dommit/rollbbdk's ifrf
                if (!donflidt) {
                    tWritfr = (TrbnsbdtionblWritfr)rowSftWritfr;
                    tWritfr.rollbbdk();
                    suddfss = fblsf;
                } flsf {
                    tWritfr = (TrbnsbdtionblWritfr)rowSftWritfr;
                    if (tWritfr instbndfof CbdifdRowSftWritfr) {
                        ((CbdifdRowSftWritfr)tWritfr).dommit(tiis, updbtfOnInsfrt);
                    } flsf {
                        tWritfr.dommit();
                    }

                    suddfss = truf;
                }
            }

            if (suddfss == truf) {
                sftOriginbl();
            } flsf if (!(suddfss) ) {
                tirow nfw SyndProvidfrExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.bddfbilfd").toString());
            }

        } dbtdi (SyndProvidfrExdfption spf) {
               tirow spf;
        } dbtdi (SQLExdfption f) {
            f.printStbdkTrbdf();
            tirow nfw SyndProvidfrExdfption(f.gftMfssbgf());
        } dbtdi (SfdurityExdfption f) {
            tirow nfw SyndProvidfrExdfption(f.gftMfssbgf());
        }
    }

    /**
     * Propbgbtfs bll row updbtf, insfrt, bnd dflftf dibngfs to tif
     * dbtb sourdf bbdking tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * using tif givfn <dodf>Connfdtion</dodf> objfdt.
     * <P>
     * Tif rfffrfndf implfmfntbtion <dodf>RIOptimistidProvidfr</dodf>
     * modififs its syndironizbtion to b writf bbdk fundtion givfn
     * tif updbtfd donnfdtion
     * Tif rfffrfndf implfmfntbtion modififs its syndironizbtion bfibviour
     * vib tif <dodf>SyndProvidfr</dodf> to fnsurf tif syndironizbtion
     * oddurs bddording to tif updbtfd JDBC <dodf>Connfdtion</dodf>
     * propfrtifs.
     *
     * @pbrbm don b stbndbrd JDBC <dodf>Connfdtion</dodf> objfdt
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or tif undfrlying
     *                   syndironizbtion providfr fbils to dommit tif updbtfs
     *                   bbdk to tif dbtb sourdf
     * @sff #bddfptCibngfs
     * @sff jbvbx.sql.RowSftWritfr
     * @sff jbvbx.sql.rowsft.spi.SyndFbdtory
     * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
     */
    publid void bddfptCibngfs(Connfdtion don) tirows SyndProvidfrExdfption{
      sftConnfdtion(don);
      bddfptCibngfs();
    }

    /**
     * Rfstorfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt to its originbl stbtf,
     * tibt is, its stbtf bfforf tif lbst sft of dibngfs.
     * <P>
     * Bfforf rfturning, tiis mftiod movfs tif dursor bfforf tif first row
     * bnd sfnds b <dodf>rowSftCibngfd</dodf> fvfnt to bll rfgistfrfd
     * listfnfrs.
     * @tirows SQLExdfption if bn frror is oddurs rolling bbdk tif RowSft
     *           stbtf to tif dffinifd originbl vbluf.
     * @sff jbvbx.sql.RowSftListfnfr#rowSftCibngfd
     */
    publid void rfstorfOriginbl() tirows SQLExdfption {
        Row durrfntRow;
        for (Itfrbtor<?> i = rvi.itfrbtor(); i.ibsNfxt();) {
            durrfntRow = (Row)i.nfxt();
            if (durrfntRow.gftInsfrtfd() == truf) {
                i.rfmovf();
                --numRows;
            } flsf {
                if (durrfntRow.gftDflftfd() == truf) {
                    durrfntRow.dlfbrDflftfd();
                }
                if (durrfntRow.gftUpdbtfd() == truf) {
                    durrfntRow.dlfbrUpdbtfd();
                }
            }
        }
        // movf to bfforf tif first
        dursorPos = 0;

        // notify bny listfnfrs
        notifyRowSftCibngfd();
    }

    /**
     * Rflfbsfs tif durrfnt dontfnts of tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt bnd sfnds b <dodf>rowSftCibngfd</dodf> fvfnt objfdt to bll
     * rfgistfrfd listfnfrs.
     *
     * @tirows SQLExdfption if bn frror oddurs flusiing tif dontfnts of
     *           RowSft.
     * @sff jbvbx.sql.RowSftListfnfr#rowSftCibngfd
     */
    publid void rflfbsf() tirows SQLExdfption {
        initContbinfr();
        notifyRowSftCibngfd();
    }

    /**
     * Cbndfls dflftion of tif durrfnt row bnd notififs listfnfrs tibt
     * b row ibs dibngfd.
     * <P>
     * Notf:  Tiis mftiod dbn bf ignorfd if dflftfd rows brf not bfing siown,
     * wiidi is tif normbl dbsf.
     *
     * @tirows SQLExdfption if tif dursor is not on b vblid row
     */
    publid void undoDflftf() tirows SQLExdfption {
        if (gftSiowDflftfd() == fblsf) {
            rfturn;
        }
        // mbkf surf wf brf on b row
        difdkCursor();

        // don't wbnt tiis to ibppfn...
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddp").toString());
        }

        Row durrfntRow = (Row)gftCurrfntRow();
        if (durrfntRow.gftDflftfd() == truf) {
            durrfntRow.dlfbrDflftfd();
            --numDflftfd;
            notifyRowCibngfd();
        }
    }

    /**
     * Immfdibtfly rfmovfs tif durrfnt row from tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt if tif row ibs bffn insfrtfd, bnd
     * blso notififs listfnfrs tif b row ibs dibngfd.  An fxdfption is tirown
     * if tif row is not b row tibt ibs bffn insfrtfd or tif dursor is bfforf
     * tif first row, bftfr tif lbst row, or on tif insfrt row.
     * <P>
     * Tiis opfrbtion dbnnot bf undonf.
     *
     * @tirows SQLExdfption if bn frror oddurs,
     *                         tif dursor is not on b vblid row,
     *                         or tif row ibs not bffn insfrtfd
     */
    publid void undoInsfrt() tirows SQLExdfption {
        // mbkf surf wf brf on b row
        difdkCursor();

        // don't wbnt tiis to ibppfn...
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddp").toString());
        }

        Row durrfntRow = (Row)gftCurrfntRow();
        if (durrfntRow.gftInsfrtfd() == truf) {
            rvi.rfmovf(dursorPos-1);
            --numRows;
            notifyRowCibngfd();
        } flsf {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.illfgblop").toString());
        }
    }

    /**
     * Immfdibtfly rfvfrsfs tif lbst updbtf opfrbtion if tif
     * row ibs bffn modififd. Tiis mftiod dbn bf
     * dbllfd to rfvfrsf updbtfs on b bll dolumns until bll updbtfs in b row ibvf
     * bffn rollfd bbdk to tifir originbting stbtf sindf tif lbst syndironizbtion
     * (<dodf>bddfptCibngfs</dodf>) or populbtion. Tiis mftiod mby blso bf dbllfd
     * wiilf pfrforming updbtfs to tif insfrt row.
     * <P>
     * <dodf>undoUpdbtf</dodf mby bf dbllfd bt bny timf during tif liff-timf of b
     * rowsft, iowfvfr bftfr b syndironizbtion ibs oddurs tiis mftiod ibs no
     * bfffdt until furtifr modifidbtion to tif RowSft dbtb oddurs.
     *
     * @tirows SQLExdfption if dursor is bfforf tif first row, bftfr tif lbst
     *     row in rowsft.
     * @sff #undoDflftf
     * @sff #undoInsfrt
     * @sff jbvb.sql.RfsultSft#dbndflRowUpdbtfs
     */
    publid void undoUpdbtf() tirows SQLExdfption {
        // if on insfrt row, dbndfl tif insfrt row
        // mbkf tif insfrt row flbg,
        // dursorPos bbdk to tif durrfnt row
        movfToCurrfntRow();

        // flsf if not on insfrt row
        // dbll undoUpdbtf or undoInsfrt
        undoDflftf();

        undoInsfrt();

    }

    //--------------------------------------------------------------------
    // Vifws
    //--------------------------------------------------------------------

    /**
     * Rfturns b nfw <dodf>RowSft</dodf> objfdt bbdkfd by tif sbmf dbtb bs
     * tibt of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bnd sibring b sft of dursors
     * witi it. Tiis bllows dursors to intfrbtf ovfr b sibrfd sft of rows, providing
     * multiplf vifws of tif undfrlying dbtb.
     *
     * @rfturn b <dodf>RowSft</dodf> objfdt tibt is b dopy of tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt bnd sibrfs b sft of dursors witi it
     * @tirows SQLExdfption if bn frror oddurs or dloning is
     *                         not supportfd
     * @sff jbvbx.sql.RowSftEvfnt
     * @sff jbvbx.sql.RowSftListfnfr
     */
    publid RowSft drfbtfSibrfd() tirows SQLExdfption {
        RowSft dlonf;
        try {
            dlonf = (RowSft)dlonf();
        } dbtdi (ClonfNotSupportfdExdfption fx) {
            tirow nfw SQLExdfption(fx.gftMfssbgf());
        }
        rfturn dlonf;
    }

    /**
     * Rfturns b nfw <dodf>RowSft</dodf> objfdt dontbining by tif sbmf dbtb
     * bs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.  Tiis mftiod
     * difffrs from tif mftiod <dodf>drfbtfCopy</dodf> in tibt it tirows b
     * <dodf>ClonfNotSupportfdExdfption</dodf> objfdt instfbd of bn
     * <dodf>SQLExdfption</dodf> objfdt, bs tif mftiod <dodf>drfbtfSibrfd</dodf>
     * dofs.  Tiis <dodf>dlonf</dodf>
     * mftiod is dbllfd intfrnblly by tif mftiod <dodf>drfbtfSibrfd</dodf>,
     * wiidi dbtdifs tif <dodf>ClonfNotSupportfdExdfption</dodf> objfdt
     * bnd in turn tirows b nfw <dodf>SQLExdfption</dodf> objfdt.
     *
     * @rfturn b dopy of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows ClonfNotSupportfdExdfption if bn frror oddurs wifn
     * bttfmpting to dlonf tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @sff #drfbtfSibrfd
     */
    protfdtfd Objfdt dlonf() tirows ClonfNotSupportfdExdfption  {
        rfturn (supfr.dlonf());
    }

    /**
     * Crfbtfs b <dodf>RowSft</dodf> objfdt tibt is b dffp dopy of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dbtb, indluding
     * donstrbints.  Updbtfs mbdf
     * on b dopy brf not visiblf to tif originbl rowsft;
     * b dopy of b rowsft is domplftfly indfpfndfnt from tif originbl.
     * <P>
     * Mbking b dopy sbvfs tif dost of drfbting bn idfntidbl rowsft
     * from first prindiplfs, wiidi dbn bf quitf fxpfnsivf.
     * For fxbmplf, it dbn fliminbtf tif nffd to qufry b
     * rfmotf dbtbbbsf sfrvfr.
     * @rfturn b nfw <dodf>CbdifdRowSft</dodf> objfdt tibt is b dffp dopy
     *           of tiis <dodf>CbdifdRowSft</dodf> objfdt bnd is
     *           domplftfly indfpfndfnt from tiis <dodf>CbdifdRowSftImpl</dodf>
     *           objfdt.
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dopy of tiis
     *           of tif <dodf>CbdifdRowSftImpl</dodf>
     * @sff #drfbtfSibrfd
     * @sff jbvbx.sql.RowSftEvfnt
     * @sff jbvbx.sql.RowSftListfnfr
     */
    publid CbdifdRowSft drfbtfCopy() tirows SQLExdfption {
        ObjfdtOutputStrfbm out;
        BytfArrbyOutputStrfbm bOut = nfw BytfArrbyOutputStrfbm();
        try {
            out = nfw ObjfdtOutputStrfbm(bOut);
            out.writfObjfdt(tiis);
        } dbtdi (IOExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dlonffbil").toString() , fx.gftMfssbgf()));
        }

        ObjfdtInputStrfbm in;

        try {
            BytfArrbyInputStrfbm bIn = nfw BytfArrbyInputStrfbm(bOut.toBytfArrby());
            in = nfw ObjfdtInputStrfbm(bIn);
        } dbtdi (StrfbmCorruptfdExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dlonffbil").toString() , fx.gftMfssbgf()));
        } dbtdi (IOExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dlonffbil").toString() , fx.gftMfssbgf()));
        }

        try {
            //rfturn ((CbdifdRowSft)(in.rfbdObjfdt()));
            CbdifdRowSftImpl drsTfmp = (CbdifdRowSftImpl)in.rfbdObjfdt();
            drsTfmp.rfsBundlf = tiis.rfsBundlf;
            rfturn ((CbdifdRowSft)drsTfmp);

        } dbtdi (ClbssNotFoundExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dlonffbil").toString() , fx.gftMfssbgf()));
        } dbtdi (OptionblDbtbExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dlonffbil").toString() , fx.gftMfssbgf()));
        } dbtdi (IOExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dlonffbil").toString() , fx.gftMfssbgf()));
        }
    }

    /**
     * Crfbtfs b <dodf>RowSft</dodf> objfdt tibt is b dopy of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's tbblf strudturf
     * bnd tif donstrbints only.
     * Tifrf will bf no dbtb in tif objfdt bfing rfturnfd.
     * Updbtfs mbdf on b dopy brf not visiblf to tif originbl rowsft.
     * <P>
     * Tiis iflps in gftting tif undfrlying XML sdifmb wiidi dbn
     * bf usfd bs tif bbsis for populbting b <dodf>WfbRowSft</dodf>.
     *
     * @rfturn b nfw <dodf>CbdifdRowSft</dodf> objfdt tibt is b dopy
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's sdifmb bnd
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
        // Copy fvfrytiing fxdfpt dbtb i.f bll donstrbints

        // Storf tif numbfr of rows of "tiis"
        // bnd mbkf numRows fqubls zfro.
        // bnd mbkf dbtb blso zfro.
        int nRows = numRows;
        numRows = 0;

        CbdifdRowSft drs = tiis.drfbtfCopy();

        // rfsft tiis objfdt bbdk to numbfr of rows.
        numRows = nRows;

        rfturn drs;
    }

    /**
     * Crfbtfs b <dodf>CbdifdRowSft</dodf> objfdt tibt is b dopy of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dbtb only.
     * All donstrbints sft in tiis objfdt will not bf tifrf
     * in tif rfturning objfdt.  Updbtfs mbdf
     * on b dopy brf not visiblf to tif originbl rowsft.
     *
     * @rfturn b nfw <dodf>CbdifdRowSft</dodf> objfdt tibt is b dffp dopy
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bnd is
     * domplftfly indfpfndfnt from tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dopy of tif
     * of tif <dodf>CbdifdRowSft</dodf>
     * @sff #drfbtfSibrfd
     * @sff #drfbtfCopy
     * @sff #drfbtfCopySdifmb
     * @sff jbvbx.sql.RowSftEvfnt
     * @sff jbvbx.sql.RowSftListfnfr
     */
    publid CbdifdRowSft drfbtfCopyNoConstrbints() tirows SQLExdfption {
        // Copy tif wiolf dbtb ONLY witiout bny donstrbints.
        CbdifdRowSftImpl drs;
        drs = (CbdifdRowSftImpl)tiis.drfbtfCopy();

        drs.initPropfrtifs();
        try {
            drs.unsftMbtdiColumn(drs.gftMbtdiColumnIndfxfs());
        } dbtdi(SQLExdfption sqlf) {
            //do notiing, if tif sftMbtdiColumn is not sft.
        }

        try {
            drs.unsftMbtdiColumn(drs.gftMbtdiColumnNbmfs());
        } dbtdi(SQLExdfption sqlf) {
            //do notiing, if tif sftMbtdiColumn is not sft.
        }

        rfturn drs;
    }

    /**
     * Convfrts tiis <dodf>CbdifdRowSftImpl</dodf> objfdt to b dollfdtion
     * of tbblfs. Tif sbmplf implfmfntbtion utilitizfs tif <dodf>TrffMbp</dodf>
     * dollfdtion typf.
     * Tiis dlbss gubrbntffs tibt tif mbp will bf in bsdfnding kfy ordfr,
     * sortfd bddording to tif nbturbl ordfr for tif kfy's dlbss.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt donsisting of tbblfs,
     *         fbdi of wiidi is b dopy of b row in tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dollfdtion
     * @sff #toCollfdtion(int)
     * @sff #toCollfdtion(String)
     * @sff jbvb.util.TrffMbp
     */
    publid Collfdtion<?> toCollfdtion() tirows SQLExdfption {

        TrffMbp<Intfgfr, Objfdt> tMbp = nfw TrffMbp<>();

        for (int i = 0; i<numRows; i++) {
            tMbp.put(i, rvi.gft(i));
        }

        rfturn (tMbp.vblufs());
    }

    /**
     * Rfturns tif spfdififd dolumn of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * bs b <dodf>Collfdtion</dodf> objfdt.  Tiis mftiod mbkfs b dopy of tif
     * dolumn's dbtb bnd utilitizfs tif <dodf>Vfdtor</dodf> to fstbblisi tif
     * dollfdtion. Tif <dodf>Vfdtor</dodf> dlbss implfmfnts b growbblf brrby
     * objfdts bllowing tif individubl domponfnts to bf bddfssfd using bn
     * bn intfgfr indfx similbr to tibt of bn brrby.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt tibt dontbins tif vbluf(s)
     *         storfd in tif spfdififd dolumn of tiis
     *         <dodf>CbdifdRowSftImpl</dodf>
     *         objfdt
     * @tirows SQLExdfption if bn frror oddurs gfnfrbtfd tif dollfdtion; or
     *          bn invblid dolumn is providfd.
     * @sff #toCollfdtion()
     * @sff #toCollfdtion(String)
     * @sff jbvb.util.Vfdtor
     */
    publid Collfdtion<?> toCollfdtion(int dolumn) tirows SQLExdfption {

        int nRows = numRows;
        Vfdtor<Objfdt> vfd = nfw Vfdtor<>(nRows);

        // drfbtf b dopy
        CbdifdRowSftImpl drsTfmp;
        drsTfmp = (CbdifdRowSftImpl) tiis.drfbtfCopy();

        wiilf(nRows!=0) {
            drsTfmp.nfxt();
            vfd.bdd(drsTfmp.gftObjfdt(dolumn));
            nRows--;
        }

        rfturn (Collfdtion)vfd;
    }

    /**
     * Rfturns tif spfdififd dolumn of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * bs b <dodf>Collfdtion</dodf> objfdt.  Tiis mftiod mbkfs b dopy of tif
     * dolumn's dbtb bnd utilitizfs tif <dodf>Vfdtor</dodf> to fstbblisi tif
     * dollfdtion. Tif <dodf>Vfdtor</dodf> dlbss implfmfnts b growbblf brrby
     * objfdts bllowing tif individubl domponfnts to bf bddfssfd using bn
     * bn intfgfr indfx similbr to tibt of bn brrby.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt tibt dontbins tif vbluf(s)
     *         storfd in tif spfdififd dolumn of tiis
     *         <dodf>CbdifdRowSftImpl</dodf>
     *         objfdt
     * @tirows SQLExdfption if bn frror oddurs gfnfrbtfd tif dollfdtion; or
     *          bn invblid dolumn is providfd.
     * @sff #toCollfdtion()
     * @sff #toCollfdtion(int)
     * @sff jbvb.util.Vfdtor
     */
    publid Collfdtion<?> toCollfdtion(String dolumn) tirows SQLExdfption {
        rfturn toCollfdtion(gftColIdxByNbmf(dolumn));
    }

    //--------------------------------------------------------------------
    // Advbndfd ffbturfs
    //--------------------------------------------------------------------


    /**
     * Rfturns tif <dodf>SyndProvidfr</dodf> implfmfntbtion bfing usfd
     * witi tiis <dodf>CbdifdRowSftImpl</dodf> implfmfntbtion rowsft.
     *
     * @rfturn tif SyndProvidfr usfd by tif rowsft. If not providfr wbs
     *          sft wifn tif rowsft wbs instbntibtfd, tif rfffrfndf
     *          implfmfntbtion (dffbult) providfr is rfturnfd.
     * @tirows SQLExdfption if frror oddurs wiilf rfturn tif
     *          <dodf>SyndProvidfr</dodf> instbndf.
     */
    publid SyndProvidfr gftSyndProvidfr() tirows SQLExdfption {
        rfturn providfr;
    }

    /**
     * Sfts tif bdtivf <dodf>SyndProvidfr</dodf> bnd bttfmpts to lobd
     * lobd tif nfw providfr using tif <dodf>SyndFbdtory</dodf> SPI.
     *
     * @tirows SQLExdfption if bn frror oddurs wiilf rfsftting tif
     *          <dodf>SyndProvidfr</dodf>.
     */
    publid void sftSyndProvidfr(String providfrStr) tirows SQLExdfption {
        providfr =
        SyndFbdtory.gftInstbndf(providfrStr);

        rowSftRfbdfr = providfr.gftRowSftRfbdfr();
        rowSftWritfr = providfr.gftRowSftWritfr();
    }


    //-----------------
    // mftiods inifritfd from RowSft
    //-----------------






    //---------------------------------------------------------------------
    // Rfbding bnd writing dbtb
    //---------------------------------------------------------------------

    /**
     * Populbtfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi dbtb.
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
        fxfdutf(null);
    }



    //-----------------------------------
    // Mftiods inifritfd from RfsultSft
    //-----------------------------------

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
        /*
         * mbkf surf tiings look sbnf. Tif dursor must bf
         * positionfd in tif rowsft or bfforf first (0) or
         * bftfr lbst (numRows + 1)
         */
        if (dursorPos < 0 || dursorPos >= numRows + 1) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddp").toString());
        }
        // now movf bnd notify
        boolfbn rft = tiis.intfrnblNfxt();
        notifyCursorMovfd();

        rfturn rft;
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif nfxt
     * row bnd rfturns <dodf>truf</dodf> if tif dursor is still in tif rowsft;
     * rfturns <dodf>fblsf</dodf> if tif dursor ibs movfd to tif position bftfr
     * tif lbst row.
     * <P>
     * Tiis mftiod ibndlfs tif dbsfs wifrf tif dursor movfs to b row tibt
     * ibs bffn dflftfd.
     * If tiis rowsft siows dflftfd rows bnd tif dursor movfs to b row
     * tibt ibs bffn dflftfd, tiis mftiod movfs tif dursor to tif nfxt
     * row until tif dursor is on b row tibt ibs not bffn dflftfd.
     * <P>
     * Tif mftiod <dodf>intfrnblNfxt</dodf> is dbllfd by mftiods sudi bs
     * <dodf>nfxt</dodf>, <dodf>bbsolutf</dodf>, bnd <dodf>rflbtivf</dodf>,
     * bnd, bs its nbmf implifs, is only dbllfd intfrnblly.
     * <p>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row in tiis
     *         rowsft; <dodf>fblsf</dodf> if it is bftfr tif lbst row
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd boolfbn intfrnblNfxt() tirows SQLExdfption {
        boolfbn rft = fblsf;

        do {
            if (dursorPos < numRows) {
                ++dursorPos;
                rft = truf;
            } flsf if (dursorPos == numRows) {
                // indrfmfnt to bftfr lbst
                ++dursorPos;
                rft = fblsf;
                brfbk;
            }
        } wiilf ((gftSiowDflftfd() == fblsf) && (rowDflftfd() == truf));

        /* fbdi dbll to intfrnblNfxt mby indrfmfnt dursorPos multiplf
         * timfs iowfvfr, tif bbsolutfPos only indrfmfnts ondf pfr dbll.
         */
        if (rft == truf)
            bbsolutfPos++;
        flsf
            bbsolutfPos = 0;

        rfturn rft;
    }

    /**
     * Closfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdy bnd rflfbsfs bny rfsourdfs
     * it wbs using.
     *
     * @tirows SQLExdfption if bn frror oddurs wifn rflfbsing bny rfsourdfs in usf
     * by tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     */
    publid void dlosf() tirows SQLExdfption {

        // dlosf bll dbtb strudturfs iolding
        // tif disdonnfdtfd rowsft

        dursorPos = 0;
        bbsolutfPos = 0;
        numRows = 0;
        numDflftfd = 0;

        // sft bll insfrt(s), updbtf(s) & dflftf(s),
        // if bt bll, to tifir initibl vblufs.
        initPropfrtifs();

        // dlfbr tif vfdtor of it's prfsfnt dontfnts
        rvi.dlfbr();

        // tiis will mbkf it fligiblf for gd
        // rvi = null;
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
        rfturn lbstVblufNull;
    }

    /**
     * Sfts tif fifld <dodf>lbstVblufNull</dodf> to tif givfn
     * <dodf>boolfbn</dodf> vbluf.
     *
     * @pbrbm vbluf <dodf>truf</dodf> to indidbtf tibt tif vbluf of
     *        tif lbst dolumn rfbd wbs SQL <dodf>NULL</dodf>;
     *        <dodf>fblsf</dodf> to indidbtf tibt it wbs not
     */
    privbtf void sftLbstVblufNull(boolfbn vbluf) {
        lbstVblufNull = vbluf;
    }

    // Mftiods for bddfssing rfsults by dolumn indfx

    /**
     * Cifdks to sff wiftifr tif givfn indfx is b vblid dolumn numbfr
     * in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bnd tirows
     * bn <dodf>SQLExdfption</dodf> if it is not. Tif indfx is out of bounds
     * if it is lfss tibn <dodf>1</dodf> or grfbtfr tibn tif numbfr of
     * dolumns in tiis rowsft.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly by tif <dodf>gftXXX</dodf> bnd
     * <dodf>updbtfXXX</dodf> mftiods.
     *
     * @pbrbm idx tif numbfr of b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf>
     *            objfdt; must bf bftwffn <dodf>1</dodf> bnd tif numbfr of
     *            rows in tiis rowsft
     * @tirows SQLExdfption if tif givfn indfx is out of bounds
     */
    privbtf void difdkIndfx(int idx) tirows SQLExdfption {
        if (idx < 1 || idx > RowSftMD.gftColumnCount()) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddol").toString());
        }
    }

    /**
     * Cifdks to sff wiftifr tif dursor for tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt is on b row in tif rowsft bnd tirows bn
     * <dodf>SQLExdfption</dodf> if it is not.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly by <dodf>gftXXX</dodf> mftiods, by
     * <dodf>updbtfXXX</dodf> mftiods, bnd by mftiods tibt updbtf, insfrt,
     * or dflftf b row or tibt dbndfl b row updbtf, insfrt, or dflftf.
     *
     * @tirows SQLExdfption if tif dursor for tiis <dodf>CbdifdRowSftImpl</dodf>
     *         objfdt is not on b vblid row
     */
    privbtf void difdkCursor() tirows SQLExdfption {
        if (isAftfrLbst() == truf || isBfforfFirst() == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddp").toString());
        }
    }

    /**
     * Rfturns tif dolumn numbfr of tif dolumn witi tif givfn nbmf in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.  Tiis mftiod tirows bn
     * <dodf>SQLExdfption</dodf> if tif givfn nbmf is not tif nbmf of
     * onf of tif dolumns in tiis rowsft.
     *
     * @pbrbm nbmf b <dodf>String</dodf> objfdt tibt is tif nbmf of b dolumn in
     *              tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if tif givfn nbmf dofs not mbtdi tif nbmf of onf of
     *         tif dolumns in tiis rowsft
     */
    privbtf int gftColIdxByNbmf(String nbmf) tirows SQLExdfption {
        RowSftMD = (RowSftMftbDbtbImpl)tiis.gftMftbDbtb();
        int dols = RowSftMD.gftColumnCount();

        for (int i=1; i <= dols; ++i) {
            String dolNbmf = RowSftMD.gftColumnNbmf(i);
            if (dolNbmf != null)
                if (nbmf.fqublsIgnorfCbsf(dolNbmf))
                    rfturn (i);
                flsf
                    dontinuf;
        }
        tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbldolnm").toString());

    }

    /**
     * Rfturns tif insfrt row or tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf>objfdt.
     *
     * @rfturn tif <dodf>Row</dodf> objfdt on wiidi tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdts's dursor is positionfd
     */
    protfdtfd BbsfRow gftCurrfntRow() {
        if (onInsfrtRow == truf) {
            rfturn (BbsfRow)insfrtRow;
        } flsf {
            rfturn (BbsfRow)(rvi.gft(dursorPos - 1));
        }
    }

    /**
     * Rfmovfs tif row on wiidi tif dursor is positionfd.
     * <p>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @tirows SQLExdfption if tif dursor is positionfd on tif insfrt
     *            row
     */
    protfdtfd void rfmovfCurrfntRow() {
        ((Row)gftCurrfntRow()).sftDflftfd();
        rvi.rfmovf(dursorPos - 1);
        --numRows;
    }


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>String</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, BIGINT, REAL,
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, <b>CHAR</b>, <b>VARCHAR</b></dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     */
    publid String gftString(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        rfturn vbluf.toString();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>boolfbn</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf bs b <dodf>boolfbn</dodf> in tif Jbvb progbmming lbngubgf;
     *        if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>fblsf</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>BOOLEAN</dodf> vbluf
     * @sff #gftBoolfbn(String)
     */
    publid boolfbn gftBoolfbn(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn fblsf;
        }

        // difdk for Boolfbn...
        if (vbluf instbndfof Boolfbn) {
            rfturn ((Boolfbn)vbluf).boolfbnVbluf();
        }

        // donvfrt to b Doublf bnd dompbrf to zfro
        try {
            rfturn Doublf.dompbrf(Doublf.pbrsfDoublf(vbluf.toString()), 0) != 0;
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.boolfbil").toString(),
                  nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>bytf</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf bs b <dodf>bytf</dodf> in tif Jbvb progrbmming
     * lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf><b>TINYINT</b>, SMALLINT, INTEGER, BIGINT, REAL,
     *            FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     *            or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     *            dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftBytf(String)
     */
    publid bytf gftBytf(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn (bytf)0;
        }
        try {
            rfturn ((Bytf.vblufOf(vbluf.toString())).bytfVbluf());
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.bytffbil").toString(),
                  nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>siort</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, <b>SMALLINT</b>, INTEGER, BIGINT, REAL
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     * @sff #gftSiort(String)
     */
    publid siort gftSiort(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn (siort)0;
        }

        try {
            rfturn ((Siort.vblufOf(vbluf.toString().trim())).siortVbluf());
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.siortfbil").toString(),
                  nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn
     * <dodf>int</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, <b>INTEGER</b>, BIGINT, REAL
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     */
    publid int gftInt(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn 0;
        }

        try {
            rfturn ((Intfgfr.vblufOf(vbluf.toString().trim())).intVbluf());
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.intfbil").toString(),
                  nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>long</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, <b>BIGINT</b>, REAL
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     * @sff #gftLong(String)
     */
    publid long gftLong(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn (long)0;
        }
        try {
            rfturn ((Long.vblufOf(vbluf.toString().trim())).longVbluf());
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.longfbil").toString(),
                  nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>flobt</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, BIGINT, <b>REAL</b>,
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     * @sff #gftFlobt(String)
     */
    publid flobt gftFlobt(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn (flobt)0;
        }
        try {
            rfturn ((nfw Flobt(vbluf.toString())).flobtVbluf());
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.flobtfbil").toString(),
                  nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>doublf</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, BIGINT, REAL,
     * <b>FLOAT</b>, <b>DOUBLE</b>, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     * @sff #gftDoublf(String)
     *
     */
    publid doublf gftDoublf(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn (doublf)0;
        }
        try {
            rfturn ((nfw Doublf(vbluf.toString().trim())).doublfVbluf());
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.doublffbil").toString(),
                  nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
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
        Objfdt vbluf;
        BigDfdimbl bDfdimbl, rftVbl;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn (nfw BigDfdimbl(0));
        }

        bDfdimbl = tiis.gftBigDfdimbl(dolumnIndfx);

        rftVbl = bDfdimbl.sftSdblf(sdblf);

        rfturn rftVbl;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>bytf</dodf> brrby vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf bs b <dodf>bytf</dodf> brrby in tif Jbvb progrbmming
     * lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * rfsult is <dodf>null</dodf>
     *
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf><b>BINARY</b>, <b>VARBINARY</b> or
     * LONGVARBINARY</dodf> vbluf.
     * Tif bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftBytfs(String)
     */
    publid bytf[] gftBytfs(int dolumnIndfx) tirows SQLExdfption {
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (isBinbry(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        rfturn (bytf[])(gftCurrfntRow().gftColumnObjfdt(dolumnIndfx));
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Dbtb</dodf> objfdt; if
     *        tif vbluf is SQL <dodf>NULL</dodf>, tif
     *        rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.sql.Dbtf gftDbtf(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        /*
         * Tif objfdt doming bbdk from tif db dould bf
         * b dbtf, b timfstbmp, or b dibr fifld vbrifty.
         * If it's b dbtf typf rfturn it, b timfstbmp
         * wf turn into b long bnd tifn into b dbtf,
         * dibr strings wf try to pbrsf. Yudk.
         */
        switdi (RowSftMD.gftColumnTypf(dolumnIndfx)) {
            dbsf jbvb.sql.Typfs.DATE: {
                long sfd = ((jbvb.sql.Dbtf)vbluf).gftTimf();
                rfturn nfw jbvb.sql.Dbtf(sfd);
            }
            dbsf jbvb.sql.Typfs.TIMESTAMP: {
                long sfd = ((jbvb.sql.Timfstbmp)vbluf).gftTimf();
                rfturn nfw jbvb.sql.Dbtf(sfd);
            }
            dbsf jbvb.sql.Typfs.CHAR:
            dbsf jbvb.sql.Typfs.VARCHAR:
            dbsf jbvb.sql.Typfs.LONGVARCHAR: {
                try {
                    DbtfFormbt df = DbtfFormbt.gftDbtfInstbndf();
                    rfturn ((jbvb.sql.Dbtf)(df.pbrsf(vbluf.toString())));
                } dbtdi (PbrsfExdfption fx) {
                    tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dbtffbil").toString(),
                        nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
                }
            }
            dffbult: {
                tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dbtffbil").toString(),
                        nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
            }
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timf</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *         tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.sql.Timf gftTimf(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        /*
         * Tif objfdt doming bbdk from tif db dould bf
         * b dbtf, b timfstbmp, or b dibr fifld vbrifty.
         * If it's b dbtf typf rfturn it, b timfstbmp
         * wf turn into b long bnd tifn into b dbtf,
         * dibr strings wf try to pbrsf. Yudk.
         */
        switdi (RowSftMD.gftColumnTypf(dolumnIndfx)) {
            dbsf jbvb.sql.Typfs.TIME: {
                rfturn (jbvb.sql.Timf)vbluf;
            }
            dbsf jbvb.sql.Typfs.TIMESTAMP: {
                long sfd = ((jbvb.sql.Timfstbmp)vbluf).gftTimf();
                rfturn nfw jbvb.sql.Timf(sfd);
            }
            dbsf jbvb.sql.Typfs.CHAR:
            dbsf jbvb.sql.Typfs.VARCHAR:
            dbsf jbvb.sql.Typfs.LONGVARCHAR: {
                try {
                    DbtfFormbt tf = DbtfFormbt.gftTimfInstbndf();
                    rfturn ((jbvb.sql.Timf)(tf.pbrsf(vbluf.toString())));
                } dbtdi (PbrsfExdfption fx) {
                    tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.timffbil").toString(),
                        nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
                }
            }
            dffbult: {
                tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.timffbil").toString(),
                        nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
            }
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
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
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        /*
         * Tif objfdt doming bbdk from tif db dould bf
         * b dbtf, b timfstbmp, or b dibr fifld vbrifty.
         * If it's b dbtf typf rfturn it; b timfstbmp
         * wf turn into b long bnd tifn into b dbtf;
         * dibr strings wf try to pbrsf. Yudk.
         */
        switdi (RowSftMD.gftColumnTypf(dolumnIndfx)) {
            dbsf jbvb.sql.Typfs.TIMESTAMP: {
                rfturn (jbvb.sql.Timfstbmp)vbluf;
            }
            dbsf jbvb.sql.Typfs.TIME: {
                long sfd = ((jbvb.sql.Timf)vbluf).gftTimf();
                rfturn nfw jbvb.sql.Timfstbmp(sfd);
            }
            dbsf jbvb.sql.Typfs.DATE: {
                long sfd = ((jbvb.sql.Dbtf)vbluf).gftTimf();
                rfturn nfw jbvb.sql.Timfstbmp(sfd);
            }
            dbsf jbvb.sql.Typfs.CHAR:
            dbsf jbvb.sql.Typfs.VARCHAR:
            dbsf jbvb.sql.Typfs.LONGVARCHAR: {
                try {
                    DbtfFormbt tf = DbtfFormbt.gftTimfInstbndf();
                    rfturn ((jbvb.sql.Timfstbmp)(tf.pbrsf(vbluf.toString())));
                } dbtdi (PbrsfExdfption fx) {
                    tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.timffbil").toString(),
                        nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
                }
            }
            dffbult: {
                tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.timffbil").toString(),
                        nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
            }
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.io.InputStrfbm</dodf>
     * objfdt.
     *
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of ASCII dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.  Tif JDBC
     * drivfr will do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into ASCII.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. . Also, b
     * strfbm mby rfturn <dodf>0</dodf> for <dodf>CbdifdRowSftImpl.bvbilbblf()</dodf>
     * wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of onf-bytf ASCII dibrbdtfrs.  If tif vbluf is SQL
     *         <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>CHAR, VARCHAR</dodf>, <dodf><b>LONGVARCHAR</b></dodf>
     * <dodf>BINARY, VARBINARY</dodf> or <dodf>LONGVARBINARY</dodf> vbluf. Tif
     * bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typfs tibt tiis mftiod is
     * usfd to rftrifvf.
     * @sff #gftAsdiiStrfbm(String)
     */
    publid jbvb.io.InputStrfbm gftAsdiiStrfbm(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // blwbys frff bn old strfbm
        bsdiiStrfbm = null;

        // sbnity difdk
        difdkIndfx(dolumnIndfx);
        //mbkf surf tif dursor is on b vlid row
        difdkCursor();

        vbluf =  gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);
        if (vbluf == null) {
            lbstVblufNull = truf;
            rfturn null;
        }

        try {
            if (isString(RowSftMD.gftColumnTypf(dolumnIndfx))) {
                bsdiiStrfbm = nfw BytfArrbyInputStrfbm(((String)vbluf).gftBytfs("ASCII"));
            } flsf {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
            }
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption fx) {
            tirow nfw SQLExdfption(fx.gftMfssbgf());
        }

        rfturn bsdiiStrfbm;
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
        // blwbys frff bn old strfbm
        unidodfStrfbm = null;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (isBinbry(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf &&
        isString(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        Objfdt vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);
        if (vbluf == null) {
            lbstVblufNull = truf;
            rfturn null;
        }

        unidodfStrfbm = nfw StringBufffrInputStrfbm(vbluf.toString());

        rfturn unidodfStrfbm;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.io.InputStrfbm</dodf>
     * objfdt.
     * <P>
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of unintfrprftfd bytfs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf> vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> for
     * <dodf>CbdifdRowSftImpl.bvbilbblf()</dodf> wiftifr tifrf is dbtb
     * bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     * is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     * bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of unintfrprftfd bytfs.  If tif vbluf is SQL <dodf>NULL</dodf>
     * tifn tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>BINARY, VARBINARY</dodf> or <dodf><b>LONGVARBINARY</b></dodf>
     * Tif bold typf indidbtfs tif SQL typf tibt tiis mftiod is rfdommfnfd
     * to rftrifvf.
     * @sff #gftBinbryStrfbm(String)
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm(int dolumnIndfx) tirows SQLExdfption {

        // blwbys frff bn old strfbm
        binbryStrfbm = null;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (isBinbry(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        Objfdt vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);
        if (vbluf == null) {
            lbstVblufNull = truf;
            rfturn null;
        }

        binbryStrfbm = nfw BytfArrbyInputStrfbm((bytf[])vbluf);

        rfturn binbryStrfbm;

    }


    // Mftiods for bddfssing rfsults by dolumn nbmf

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>String</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, REAL, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, <b>CHAR</b>,
     * <b>VARCHAR</b></dodf> or <dodf>LONGVARCHAR<</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf.
     */
    publid String gftString(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftString(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>boolfbn</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf bs b <dodf>boolfbn</dodf> in tif Jbvb progrbmming
     *        lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *        tif rfsult is <dodf>fblsf</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>BOOLEAN</dodf> vbluf
     * @sff #gftBoolfbn(int)
     */
    publid boolfbn gftBoolfbn(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBoolfbn(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>bytf</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf bs b <dodf>bytf</dodf> in tif Jbvb progrbmming
     * lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf><B>TINYINT</B>, SMALLINT, INTEGER,
     * BIGINT, REAL, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif
     * bold typf dfsignbtfs tif rfdommfndfd rfturn typf
     */
    publid bytf gftBytf(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBytf(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>siort</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, <b>SMALLINT</b>, INTEGER
     * BIGINT, REAL, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftSiort(int)
     */
    publid siort gftSiort(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftSiort(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs bn <dodf>int</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf
     * of b dolumn in tiis rowsft,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, <b>INTEGER</b>, BIGINT, REAL
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     */
    publid int gftInt(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftInt(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>long</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * <b>BIGINT</b>, REAL, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftLong(int)
     */
    publid long gftLong(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftLong(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>flobt</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, <b>REAL</b>, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftFlobt(String)
     */
    publid flobt gftFlobt(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftFlobt(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * bs b <dodf>doublf</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, REAL, <b>FLOAT</b>, <b>DOUBLE</b>, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typfs.
     * @sff #gftDoublf(int)
     */
    publid doublf gftDoublf(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftDoublf(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point
     * @rfturn b jbvb.mbti.BugDfdimbl objfdt witi <dodf><i>sdblf</i></dodf>
     * numbfr of digits to tif rigit of tif dfdimbl point.
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, REAL, FLOAT, DOUBLE, <b>DECIMAL</b>, <b>NUMERIC</b>, BIT CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf tibt tiis mftiod is usfd to
     * rftrifvf.
     * @dfprfdbtfd Usf tif <dodf>gftBigDfdimbl(String dolumnNbmf)</dodf>
     *             mftiod instfbd
     */
    @Dfprfdbtfd
    publid BigDfdimbl gftBigDfdimbl(String dolumnNbmf, int sdblf) tirows SQLExdfption {
        rfturn gftBigDfdimbl(gftColIdxByNbmf(dolumnNbmf), sdblf);
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>bytf</dodf> brrby.
     * Tif bytfs rfprfsfnt tif rbw vblufs rfturnfd by tif drivfr.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf bs b <dodf>bytf</dodf> brrby in tif Jbvb progrbmming
     * lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf><b>BINARY</b>, <b>VARBINARY</b>
     * </dodf> or <dodf>LONGVARBINARY</dodf> vblufs
     * Tif bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftBytfs(int)
     */
    publid bytf[] gftBytfs(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBytfs(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>DATE</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Dbtf gftDbtf(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftDbtf(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Timf</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid jbvb.sql.Timf gftTimf(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftTimf(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftTimfstbmp(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.io.InputStrfbm</dodf>
     * objfdt.
     *
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of ASCII dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs. Tif
     * <dodf>SyndProvidfr</dodf> will rfly on tif JDBC drivfr to do bny nfdfssbry
     * donvfrsion from tif dbtbbbsf formbt into ASCII formbt.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of onf-bytf ASCII dibrbdtfrs.  If tif vbluf is SQL
     *         <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>CHAR, VARCHAR</dodf>, <dodf><b>LONGVARCHAR</b></dodf>
     * <dodf>BINARY, VARBINARY</dodf> or <dodf>LONGVARBINARY</dodf> vbluf. Tif
     * bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typfs tibt tiis mftiod is
     * usfd to rftrifvf.
     * @sff #gftAsdiiStrfbm(int)
     */
    publid jbvb.io.InputStrfbm gftAsdiiStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftAsdiiStrfbm(gftColIdxByNbmf(dolumnNbmf));

    }

    /**
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
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
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
        rfturn gftUnidodfStrfbm(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.io.InputStrfbm</dodf>
     * objfdt.
     * <P>
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of unintfrprftfd bytfs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf> vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> for <dodf>CbdifdRowSftImpl.bvbilbblf()</dodf>
     * wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of unintfrprftfd bytfs.  If tif vbluf is SQL
     *         <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is unknown,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>BINARY, VARBINARY</dodf> or <dodf><b>LONGVARBINARY</b></dodf>
     * Tif bold typf indidbtfs tif SQL typf tibt tiis mftiod is rfdommfnfd
     * to rftrifvf.
     * @sff #gftBinbryStrfbm(int)
     *
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBinbryStrfbm(gftColIdxByNbmf(dolumnNbmf));
    }


    // Advbndfd ffbturfs:

    /**
     * Tif first wbrning rfportfd by dblls on tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt is rfturnfd. Subsfqufnt <dodf>CbdifdRowSftImpl</dodf> wbrnings will
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
     */
    publid SQLWbrning gftWbrnings() {
        rfturn sqlwbrn;
    }

    /**
     * Clfbrs bll tif wbrnings rfporftfd for tif <dodf>CbdifdRowSftImpl</dodf>
     * objfdt. Aftfr b dbll to tiis mftiod, tif <dodf>gftWbrnings</dodf> mftiod
     * rfturns <dodf>null</dodf> until b nfw wbrning is rfportfd for tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     */
    publid void dlfbrWbrnings() {
        sqlwbrn = null;
    }

    /**
     * Rftrifvfs tif nbmf of tif SQL dursor usfd by tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * <P>In SQL, b rfsult tbblf is rftrifvfd tirougi b dursor tibt is
     * nbmfd. Tif durrfnt row of b <dodf>RfsultSft</dodf> dbn bf updbtfd or dflftfd
     * using b positionfd updbtf/dflftf stbtfmfnt tibt rfffrfndfs tif
     * dursor nbmf. To fnsurf tibt tif dursor ibs tif propfr isolbtion
     * lfvfl to support bn updbtf opfrbtion, tif dursor's <dodf>SELECT</dodf>
     * stbtfmfnt siould bf of tif form <dodf>sflfdt for updbtf</dodf>.
     * If tif <dodf>for updbtf</dodf> dlbusf
     * is omittfd, positionfd updbtfs mby fbil.
     *
     * <P>JDBC supports tiis SQL ffbturf by providing tif nbmf of tif
     * SQL dursor usfd by b <dodf>RfsultSft</dodf> objfdt. Tif durrfnt row
     * of b rfsult sft is blso tif durrfnt row of tiis SQL dursor.
     *
     * <P><B>Notf:</B> If positionfd updbtfs brf not supportfd, bn
     * <dodf>SQLExdfption</dodf> is tirown.
     *
     * @rfturn tif SQL dursor nbmf for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's
     *         dursor
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid String gftCursorNbmf() tirows SQLExdfption {
        tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.posupdbtf").toString());
    }

    /**
     * Rftrifvfs b <dodf>RfsultSftMftbDbtb</dodf> objfdt instbndf tibt
     * dontbins informbtion bbout tif <dodf>CbdifdRowSft</dodf> objfdt.
     * Howfvfr, bpplidbtions siould dbst tif rfturnfd objfdt to b
     * <dodf>RowSftMftbDbtb</dodf> intfrfbdf implfmfntbtion. In tif
     * rfffrfndf implfmfntbtion, tiis dbst dbn bf donf on tif
     * <dodf>RowSftMftbDbtbImpl</dodf> dlbss.
     * <P>
     * For fxbmplf:
     * <prf>
     * CbdifdRowSft drs = nfw CbdifdRowSftImpl();
     * RowSftMftbDbtbImpl mftbDbtb =
     *     (RowSftMftbDbtbImpl)drs.gftMftbDbtb();
     * // Sft tif numbfr of dolumns in tif RowSft objfdt for
     * // wiidi tiis RowSftMftbDbtbImpl objfdt wbs drfbtfd to tif
     * // givfn numbfr.
     * mftbDbtb.sftColumnCount(3);
     * drs.sftMftbDbtb(mftbDbtb);
     * </prf>
     *
     * @rfturn tif <dodf>RfsultSftMftbDbtb</dodf> objfdt tibt dfsdribfs tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt's dolumns
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif RowSft
     * mftb dbtb; or if tif <dodf>CbdifdRowSftImpl</dodf> is fmpty.
     * @sff jbvbx.sql.RowSftMftbDbtb
     */
    publid RfsultSftMftbDbtb gftMftbDbtb() tirows SQLExdfption {
        rfturn (RfsultSftMftbDbtb)RowSftMD;
    }


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tif typf of tif <dodf>Objfdt</dodf> will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC 3.0
     * spfdifidbtion.
     * <P>
     * Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     * <P>
     * Tiis implfmfntbtion of tif mftiod <dodf>gftObjfdt</dodf> fxtfnds its
     * bfibvior so tibt it gfts tif bttributfs of bn SQL strudturfd typf
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
     * @sff #gftObjfdt(String)
     */
    publid Objfdt gftObjfdt(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;
        Mbp<String, Clbss<?>> mbp;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }
        if (vbluf instbndfof Strudt) {
            Strudt s = (Strudt)vbluf;
            mbp = gftTypfMbp();
            // look up tif dlbss in tif mbp
            Clbss<?> d = mbp.gft(s.gftSQLTypfNbmf());
            if (d != null) {
                // drfbtf nfw instbndf of tif dlbss
                SQLDbtb obj = null;
                try {
                    obj = (SQLDbtb) RfflfdtUtil.nfwInstbndf(d);
                } dbtdi(Exdfption fx) {
                    tirow nfw SQLExdfption("Unbblf to Instbntibtf: ", fx);
                }
                // gft tif bttributfs from tif strudt
                Objfdt bttribs[] = s.gftAttributfs(mbp);
                // drfbtf tif SQLInput "strfbm"
                SQLInputImpl sqlInput = nfw SQLInputImpl(bttribs, mbp);
                // rfbd tif vblufs...
                obj.rfbdSQL(sqlInput, s.gftSQLTypfNbmf());
                rfturn (Objfdt)obj;
            }
        }
        rfturn vbluf;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tif typf of tif <dodf>Objfdt</dodf> will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC 3.0
     * spfdifidbtion.
     * <P>
     * Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     * <P>
     * Tiis implfmfntbtion of tif mftiod <dodf>gftObjfdt</dodf> fxtfnds its
     * bfibvior so tibt it gfts tif bttributfs of bn SQL strudturfd typf
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
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs, (2) tif dursor is not
     *            on b vblid row, or (3) tifrf is b problfm gftting
     *            tif <dodf>Clbss</dodf> objfdt for b dustom mbpping
     * @sff #gftObjfdt(int)
     */
    publid Objfdt gftObjfdt(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftObjfdt(gftColIdxByNbmf(dolumnNbmf));
    }

    //----------------------------------------------------------------

    /**
     * Mbps tif givfn dolumn nbmf for onf of tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt's dolumns to its dolumn numbfr.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn tif dolumn indfx of tif givfn dolumn nbmf
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf
     *            of tiis rowsft's dolumn nbmfs
     */
    publid int findColumn(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftColIdxByNbmf(dolumnNbmf);
    }


    //--------------------------JDBC 2.0-----------------------------------

    //---------------------------------------------------------------------
    // Gfttfr's bnd Sfttfr's
    //---------------------------------------------------------------------

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
     * bs b strfbm of two-bytf unidodf dibrbdtfrs in b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.  If tif vbluf is
     * SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>CHAR, VARCHAR, <b>LONGVARCHAR</b>, BINARY, VARBINARY</dodf> or
     * <dodf>LONGVARBINARY</dodf> vbluf.
     * Tif bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftCibrbdtfrStrfbm(String)
     */
    publid jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(int dolumnIndfx) tirows SQLExdfption{

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (isBinbry(RowSftMD.gftColumnTypf(dolumnIndfx))) {
            Objfdt vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);
            if (vbluf == null) {
                lbstVblufNull = truf;
                rfturn null;
            }
            dibrStrfbm = nfw InputStrfbmRfbdfr
            (nfw BytfArrbyInputStrfbm((bytf[])vbluf));
        } flsf if (isString(RowSftMD.gftColumnTypf(dolumnIndfx))) {
            Objfdt vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);
            if (vbluf == null) {
                lbstVblufNull = truf;
                rfturn null;
            }
            dibrStrfbm = nfw StringRfbdfr(vbluf.toString());
        } flsf {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        rfturn dibrStrfbm;
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
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of two-bytf Unidodf dibrbdtfrs.  If tif vbluf is
     *         SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>CHAR, VARCHAR, <b>LONGVARCHAR</b>,
     * BINARY, VARYBINARY</dodf> or <dodf>LONGVARBINARY</dodf> vbluf.
     * Tif bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typf.
     */
    publid jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftCibrbdtfrStrfbm(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b <dodf>jbvb.mbti.BigDfdimbl</dodf> vbluf witi full prfdision;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, BIGINT, REAL,
     * FLOAT, DOUBLE, <b>DECIMAL</b>, <b>NUMERIC</b>, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typfs tibt tiis mftiod is usfd to rftrifvf.
     * @sff #gftBigDfdimbl(String)
     */
    publid BigDfdimbl gftBigDfdimbl(int dolumnIndfx) tirows SQLExdfption {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }
        try {
            rfturn (nfw BigDfdimbl(vbluf.toString().trim()));
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.doublffbil").toString(),
                nfw Objfdt[] {vbluf.toString().trim(), dolumnIndfx}));
        }
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>jbvb.mbti.BigDfdimbl</dodf> vbluf witi full prfdision;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, REAL, FLOAT, DOUBLE, <b>DECIMAL</b>, <b>NUMERIC</b>, BIT CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf tibt tiis mftiod is usfd to
     * rftrifvf
     * @sff #gftBigDfdimbl(int)
     */
    publid BigDfdimbl gftBigDfdimbl(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftBigDfdimbl(gftColIdxByNbmf(dolumnNbmf));
    }

    //---------------------------------------------------------------------
    // Trbvfrsbl/Positioning
    //---------------------------------------------------------------------

    /**
     * Rfturns tif numbfr of rows in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn numbfr of rows in tif rowsft
     */
    publid int sizf() {
        rfturn numRows;
    }

    /**
     * Indidbtfs wiftifr tif dursor is bfforf tif first row in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bfforf tif first row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isBfforfFirst() tirows SQLExdfption {
        if (dursorPos == 0 && numRows > 0) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Indidbtfs wiftifr tif dursor is bftfr tif lbst row in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bftfr tif lbst row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isAftfrLbst() tirows SQLExdfption {
        if (dursorPos == numRows+1 && numRows > 0) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Indidbtfs wiftifr tif dursor is on tif first row in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif first row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isFirst() tirows SQLExdfption {
        // tiis bfdomfs nbsty bfdbusf of dflftfs.
        int sbvfCursorPos = dursorPos;
        int sbvfAbsolutfCursorPos = bbsolutfPos;
        intfrnblFirst();
        if (dursorPos == sbvfCursorPos) {
            rfturn truf;
        } flsf {
            dursorPos = sbvfCursorPos;
            bbsolutfPos = sbvfAbsolutfCursorPos;
            rfturn fblsf;
        }
    }

    /**
     * Indidbtfs wiftifr tif dursor is on tif lbst row in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
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
        int sbvfCursorPos = dursorPos;
        int sbvfAbsolutfCursorPos = bbsolutfPos;
        boolfbn sbvfSiowDflftfd = gftSiowDflftfd();
        sftSiowDflftfd(truf);
        intfrnblLbst();
        if (dursorPos == sbvfCursorPos) {
            sftSiowDflftfd(sbvfSiowDflftfd);
            rfturn truf;
        } flsf {
            sftSiowDflftfd(sbvfSiowDflftfd);
            dursorPos = sbvfCursorPos;
            bbsolutfPos = sbvfAbsolutfCursorPos;
            rfturn fblsf;
        }
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif front of
     * tif rowsft, just bfforf tif first row. Tiis mftiod ibs no ffffdt if
     * tiis rowsft dontbins no rows.
     *
     * @tirows SQLExdfption if bn frror oddurs or tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid void bfforfFirst() tirows SQLExdfption {
       if (gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.bfforffirst").toString());
        }
        dursorPos = 0;
        bbsolutfPos = 0;
        notifyCursorMovfd();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif fnd of
     * tif rowsft, just bftfr tif lbst row. Tiis mftiod ibs no ffffdt if
     * tiis rowsft dontbins no rows.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void bftfrLbst() tirows SQLExdfption {
        if (numRows > 0) {
            dursorPos = numRows + 1;
            bbsolutfPos = 0;
            notifyCursorMovfd();
        }
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif first row
     * bnd rfturns <dodf>truf</dodf> if tif opfrbtion wbs suddfssful.  Tiis
     * mftiod blso notififs rfgistfrfd listfnfrs tibt tif dursor ibs movfd.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     *         <dodf>fblsf</dodf> otifrwisf or if tifrf brf no rows in tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn first() tirows SQLExdfption {
        if(gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.first").toString());
        }

        // movf bnd notify
        boolfbn rft = tiis.intfrnblFirst();
        notifyCursorMovfd();

        rfturn rft;
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif first
     * row bnd rfturns <dodf>truf</dodf> if tif opfrbtion is suddfssful.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly by tif mftiods <dodf>first</dodf>,
     * <dodf>isFirst</dodf>, bnd <dodf>bbsolutf</dodf>.
     * It in turn dblls tif mftiod <dodf>intfrnblNfxt</dodf> in ordfr to
     * ibndlf tif dbsf wifrf tif first row is b dflftfd row tibt is not visiblf.
     * <p>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor movfd to tif first row;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd boolfbn intfrnblFirst() tirows SQLExdfption {
        boolfbn rft = fblsf;

        if (numRows > 0) {
            dursorPos = 1;
            if ((gftSiowDflftfd() == fblsf) && (rowDflftfd() == truf)) {
                rft = intfrnblNfxt();
            } flsf {
                rft = truf;
            }
        }

        if (rft == truf)
            bbsolutfPos = 1;
        flsf
            bbsolutfPos = 0;

        rfturn rft;
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif lbst row
     * bnd rfturns <dodf>truf</dodf> if tif opfrbtion wbs suddfssful.  Tiis
     * mftiod blso notififs rfgistfrfd listfnfrs tibt tif dursor ibs movfd.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     *         <dodf>fblsf</dodf> otifrwisf or if tifrf brf no rows in tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn lbst() tirows SQLExdfption {
        if (gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.lbst").toString());
        }

        // movf bnd notify
        boolfbn rft = tiis.intfrnblLbst();
        notifyCursorMovfd();

        rfturn rft;
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif lbst
     * row bnd rfturns <dodf>truf</dodf> if tif opfrbtion is suddfssful.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly by tif mftiod <dodf>lbst</dodf>
     * wifn rows ibvf bffn dflftfd bnd tif dflftions brf not visiblf.
     * Tif mftiod <dodf>intfrnblLbst</dodf> ibndlfs tif dbsf wifrf tif
     * lbst row is b dflftfd row tibt is not visiblf by in turn dblling
     * tif mftiod <dodf>intfrnblPrfvious</dodf>.
     * <p>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor movfd to tif lbst row;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd boolfbn intfrnblLbst() tirows SQLExdfption {
        boolfbn rft = fblsf;

        if (numRows > 0) {
            dursorPos = numRows;
            if ((gftSiowDflftfd() == fblsf) && (rowDflftfd() == truf)) {
                rft = intfrnblPrfvious();
            } flsf {
                rft = truf;
            }
        }
        if (rft == truf)
            bbsolutfPos = numRows - numDflftfd;
        flsf
            bbsolutfPos = 0;
        rfturn rft;
    }

    /**
     * Rfturns tif numbfr of tif durrfnt row in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt. Tif first row is numbfr 1, tif sfdond numbfr 2, bnd so on.
     *
     * @rfturn tif numbfr of tif durrfnt row;  <dodf>0</dodf> if tifrf is no
     *         durrfnt row
     * @tirows SQLExdfption if bn frror oddurs; or if tif <dodf>CbdifRowSftImpl</dodf>
     *         is fmpty
     */
    publid int gftRow() tirows SQLExdfption {
        // brf wf on b vblid row? Vblid rows brf bftwffn first bnd lbst
        if (numRows > 0 &&
        dursorPos > 0 &&
        dursorPos < (numRows + 1) &&
        (gftSiowDflftfd() == fblsf && rowDflftfd() == fblsf)) {
            rfturn bbsolutfPos;
        } flsf if (gftSiowDflftfd() == truf) {
            rfturn dursorPos;
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif row numbfr
     * spfdififd.
     *
     * <p>If tif numbfr is positivf, tif dursor movfs to bn bbsolutf row witi
     * rfspfdt to tif bfginning of tif rowsft.  Tif first row is row 1, tif sfdond
     * is row 2, bnd so on.  For fxbmplf, tif following dommbnd, in wiidi
     * <dodf>drs</dodf> is b <dodf>CbdifdRowSftImpl</dodf> objfdt, movfs tif dursor
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
     * If tif <dodf>CbdifdRowSftImpl</dodf> objfdt <dodf>drs</dodf> ibs fivf rows,
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
     *        <dodf>-1</dodf>; it must not bf <dodf>0</dodf>
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif rowsft; <dodf>fblsf</dodf>
     *         otifrwisf
     * @tirows SQLExdfption if tif givfn dursor position is <dodf>0</dodf> or tif
     *            typf of tiis rowsft is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn bbsolutf( int row ) tirows SQLExdfption {
        if (row == 0 || gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.bbsolutf").toString());
        }

        if (row > 0) { // wf brf moving fowbrd
            if (row > numRows) {
                // ffll off tif fnd
                bftfrLbst();
                rfturn fblsf;
            } flsf {
                if (bbsolutfPos <= 0)
                    intfrnblFirst();
            }
        } flsf { // wf brf moving bbdkwbrd
            if (dursorPos + row < 0) {
                // ffll off tif front
                bfforfFirst();
                rfturn fblsf;
            } flsf {
                if (bbsolutfPos >= 0)
                    intfrnblLbst();
            }
        }

        // Now movf towbrds tif bbsolutf row tibt wf'rf looking for
        wiilf (bbsolutfPos != row) {
            if (bbsolutfPos < row) {
                if (!intfrnblNfxt())
                    brfbk;
            }
            flsf {
                if (!intfrnblPrfvious())
                    brfbk;
            }
        }

        notifyCursorMovfd();

        if (isAftfrLbst() || isBfforfFirst()) {
            rfturn fblsf;
        } flsf {
            rfturn truf;
        }
    }

    /**
     * Movfs tif dursor tif spfdififd numbfr of rows from tif durrfnt
     * position, witi b positivf numbfr moving it forwbrd bnd b
     * nfgbtivf numbfr moving it bbdkwbrd.
     * <P>
     * If tif numbfr is positivf, tif dursor movfs tif spfdififd numbfr of
     * rows towbrd tif fnd of tif rowsft, stbrting bt tif durrfnt row.
     * For fxbmplf, tif following dommbnd, in wiidi
     * <dodf>drs</dodf> is b <dodf>CbdifdRowSftImpl</dodf> objfdt witi 100 rows,
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
     * If tif <dodf>CbdifdRowSftImpl</dodf> objfdt <dodf>drs</dodf> ibs fivf rows,
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
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt; <dodf>fblsf</dodf>
     *         otifrwisf
     * @tirows SQLExdfption if tifrf brf no rows in tiis rowsft, tif dursor is
     *         positionfd fitifr bfforf tif first row or bftfr tif lbst row, or
     *         tif rowsft is typf <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn rflbtivf(int rows) tirows SQLExdfption {
        if (numRows == 0 || isBfforfFirst() ||
        isAftfrLbst() || gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.rflbtivf").toString());
        }

        if (rows == 0) {
            rfturn truf;
        }

        if (rows > 0) { // wf brf moving forwbrd
            if (dursorPos + rows > numRows) {
                // ffll off tif fnd
                bftfrLbst();
            } flsf {
                for (int i=0; i < rows; i++) {
                    if (!intfrnblNfxt())
                        brfbk;
                }
            }
        } flsf { // wf brf moving bbdkwbrd
            if (dursorPos + rows < 0) {
                // ffll off tif front
                bfforfFirst();
            } flsf {
                for (int i=rows; i < 0; i++) {
                    if (!intfrnblPrfvious())
                        brfbk;
                }
            }
        }
        notifyCursorMovfd();

        if (isAftfrLbst() || isBfforfFirst()) {
            rfturn fblsf;
        } flsf {
            rfturn truf;
        }
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif
     * prfvious row bnd rfturns <dodf>truf</dodf> if tif dursor is on
     * b vblid row or <dodf>fblsf</dodf> if it is not.
     * Tiis mftiod blso notififs bll listfnfrs rfgistfrfd witi tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt tibt its dursor ibs movfd.
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
     * tif <dodf>CbdifdRowSftImpl</dodf> objfdt <dodf>drs</dodf>, wiidi ibs
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
        if (gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.lbst").toString());
        }
        /*
         * mbkf surf tiings look sbnf. Tif dursor must bf
         * positionfd in tif rowsft or bfforf first (0) or
         * bftfr lbst (numRows + 1)
         */
        if (dursorPos < 0 || dursorPos > numRows + 1) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddp").toString());
        }
        // movf bnd notify
        boolfbn rft = tiis.intfrnblPrfvious();
        notifyCursorMovfd();

        rfturn rft;
    }

    /**
     * Movfs tif dursor to tif prfvious row in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt, skipping pbst dflftfd rows tibt brf not visiblf; rfturns
     * <dodf>truf</dodf> if tif dursor is on b row in tiis rowsft bnd
     * <dodf>fblsf</dodf> wifn tif dursor gofs bfforf tif first row.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly by tif mftiod <dodf>prfvious</dodf>.
     * <P>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b row in tiis rowsft;
     *         <dodf>fblsf</dodf> wifn tif dursor rfbdifs tif position bfforf
     *         tif first row
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd boolfbn intfrnblPrfvious() tirows SQLExdfption {
        boolfbn rft = fblsf;

        do {
            if (dursorPos > 1) {
                --dursorPos;
                rft = truf;
            } flsf if (dursorPos == 1) {
                // dfdrfmfnt to bfforf first
                --dursorPos;
                rft = fblsf;
                brfbk;
            }
        } wiilf ((gftSiowDflftfd() == fblsf) && (rowDflftfd() == truf));

        /*
         * Ebdi dbll to intfrnblPrfvious mby movf tif dursor
         * ovfr multiplf rows, tif bbsolutf position movfs onf onf row
         */
        if (rft == truf)
            --bbsolutfPos;
        flsf
            bbsolutfPos = 0;

        rfturn rft;
    }


    //---------------------------------------------------------------------
    // Updbtfs
    //---------------------------------------------------------------------

    /**
     * Indidbtfs wiftifr tif durrfnt row of tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt ibs bffn updbtfd.  Tif vbluf rfturnfd
     * dfpfnds on wiftifr tiis rowsft dbn dftfdt updbtfs: <dodf>fblsf</dodf>
     * will blwbys bf rfturnfd if it dofs not dftfdt updbtfs.
     *
     * @rfturn <dodf>truf</dodf> if tif row ibs bffn visibly updbtfd
     *         by tif ownfr or bnotifr bnd updbtfs brf dftfdtfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *            not on b vblid row
     *
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn rowUpdbtfd() tirows SQLExdfption {
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invblidop").toString());
        }
        rfturn(((Row)gftCurrfntRow()).gftUpdbtfd());
    }

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn of tif durrfnt row of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt ibs bffn updbtfd. Tif
     * vbluf rfturnfd dfpfnds on wiftifr tiis rowsft dbn dftdtfd updbtfs:
     * <dodf>fblsf</dodf> will blwbys bf rfturnfd if it dofs not dftfdt updbtfs.
     *
     * @pbrbm idx tif indfx idfntififr of tif dolumn tibt mby bf ibvf bffn updbtfd.
     * @rfturn <dodf>truf</dodf> is tif dfsignbtfd dolumn ibs bffn updbtfd
     * bnd tif rowsft dftfdts updbtfs; <dodf>fblsf</dodf> if tif rowsft ibs not
     * bffn updbtfd or tif rowsft dofs not dftfdt updbtfs
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *          on b vblid row
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn dolumnUpdbtfd(int idx) tirows SQLExdfption {
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invblidop").toString());
        }
        rfturn (((Row)gftCurrfntRow()).gftColUpdbtfd(idx - 1));
    }

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn of tif durrfnt row of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt ibs bffn updbtfd. Tif
     * vbluf rfturnfd dfpfnds on wiftifr tiis rowsft dbn dftdtfd updbtfs:
     * <dodf>fblsf</dodf> will blwbys bf rfturnfd if it dofs not dftfdt updbtfs.
     *
     * @pbrbm dolumnNbmf tif <dodf>String</dodf> dolumn nbmf dolumn tibt mby bf ibvf
     * bffn updbtfd.
     * @rfturn <dodf>truf</dodf> is tif dfsignbtfd dolumn ibs bffn updbtfd
     * bnd tif rowsft dftfdts updbtfs; <dodf>fblsf</dodf> if tif rowsft ibs not
     * bffn updbtfd or tif rowsft dofs not dftfdt updbtfs
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *          on b vblid row
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn dolumnUpdbtfd(String dolumnNbmf) tirows SQLExdfption {
        rfturn dolumnUpdbtfd(gftColIdxByNbmf(dolumnNbmf));
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
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invblidop").toString());
        }
        rfturn(((Row)gftCurrfntRow()).gftInsfrtfd());
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
        // mbkf surf tif dursor is on b vblid row

        if (isAftfrLbst() == truf ||
        isBfforfFirst() == truf ||
        onInsfrtRow == truf) {

            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddp").toString());
        }
        rfturn(((Row)gftCurrfntRow()).gftDflftfd());
    }

    /**
     * Indidbtfs wiftifr tif givfn SQL dbtb typf is b numbfrid typf.
     *
     * @pbrbm typf onf of tif donstbnts from <dodf>jbvb.sql.Typfs</dodf>
     * @rfturn <dodf>truf</dodf> if tif givfn typf is <dodf>NUMERIC</dodf>,'
     *         <dodf>DECIMAL</dodf>, <dodf>BIT</dodf>, <dodf>TINYINT</dodf>,
     *         <dodf>SMALLINT</dodf>, <dodf>INTEGER</dodf>, <dodf>BIGINT</dodf>,
     *         <dodf>REAL</dodf>, <dodf>DOUBLE</dodf>, or <dodf>FLOAT</dodf>;
     *         <dodf>fblsf</dodf> otifrwisf
     */
    privbtf boolfbn isNumfrid(int typf) {
        switdi (typf) {
            dbsf jbvb.sql.Typfs.NUMERIC:
            dbsf jbvb.sql.Typfs.DECIMAL:
            dbsf jbvb.sql.Typfs.BIT:
            dbsf jbvb.sql.Typfs.TINYINT:
            dbsf jbvb.sql.Typfs.SMALLINT:
            dbsf jbvb.sql.Typfs.INTEGER:
            dbsf jbvb.sql.Typfs.BIGINT:
            dbsf jbvb.sql.Typfs.REAL:
            dbsf jbvb.sql.Typfs.DOUBLE:
            dbsf jbvb.sql.Typfs.FLOAT:
                rfturn truf;
            dffbult:
                rfturn fblsf;
        }
    }

    /**
     * Indidbtfs wiftifr tif givfn SQL dbtb typf is b string typf.
     *
     * @pbrbm typf onf of tif donstbnts from <dodf>jbvb.sql.Typfs</dodf>
     * @rfturn <dodf>truf</dodf> if tif givfn typf is <dodf>CHAR</dodf>,'
     *         <dodf>VARCHAR</dodf>, or <dodf>LONGVARCHAR</dodf>;
     *         <dodf>fblsf</dodf> otifrwisf
     */
    privbtf boolfbn isString(int typf) {
        switdi (typf) {
            dbsf jbvb.sql.Typfs.CHAR:
            dbsf jbvb.sql.Typfs.VARCHAR:
            dbsf jbvb.sql.Typfs.LONGVARCHAR:
                rfturn truf;
            dffbult:
                rfturn fblsf;
        }
    }

    /**
     * Indidbtfs wiftifr tif givfn SQL dbtb typf is b binbry typf.
     *
     * @pbrbm typf onf of tif donstbnts from <dodf>jbvb.sql.Typfs</dodf>
     * @rfturn <dodf>truf</dodf> if tif givfn typf is <dodf>BINARY</dodf>,'
     *         <dodf>VARBINARY</dodf>, or <dodf>LONGVARBINARY</dodf>;
     *         <dodf>fblsf</dodf> otifrwisf
     */
    privbtf boolfbn isBinbry(int typf) {
        switdi (typf) {
            dbsf jbvb.sql.Typfs.BINARY:
            dbsf jbvb.sql.Typfs.VARBINARY:
            dbsf jbvb.sql.Typfs.LONGVARBINARY:
                rfturn truf;
            dffbult:
                rfturn fblsf;
        }
    }

    /**
     * Indidbtfs wiftifr tif givfn SQL dbtb typf is b tfmporbl typf.
     * Tiis mftiod is dbllfd intfrnblly by tif donvfrsion mftiods
     * <dodf>donvfrtNumfrid</dodf> bnd <dodf>donvfrtTfmporbl</dodf>.
     *
     * @pbrbm typf onf of tif donstbnts from <dodf>jbvb.sql.Typfs</dodf>
     * @rfturn <dodf>truf</dodf> if tif givfn typf is <dodf>DATE</dodf>,
     *         <dodf>TIME</dodf>, or <dodf>TIMESTAMP</dodf>;
     *         <dodf>fblsf</dodf> otifrwisf
     */
    privbtf boolfbn isTfmporbl(int typf) {
        switdi (typf) {
            dbsf jbvb.sql.Typfs.DATE:
            dbsf jbvb.sql.Typfs.TIME:
            dbsf jbvb.sql.Typfs.TIMESTAMP:
                rfturn truf;
            dffbult:
                rfturn fblsf;
        }
    }

    /**
     * Indidbtfs wiftifr tif givfn SQL dbtb typf is b boolfbn typf.
     * Tiis mftiod is dbllfd intfrnblly by tif donvfrsion mftiods
     * <dodf>donvfrtNumfrid</dodf> bnd <dodf>donvfrtBoolfbn</dodf>.
     *
     * @pbrbm typf onf of tif donstbnts from <dodf>jbvb.sql.Typfs</dodf>
     * @rfturn <dodf>truf</dodf> if tif givfn typf is <dodf>BIT</dodf>,
     *         , or <dodf>BOOLEAN</dodf>;
     *         <dodf>fblsf</dodf> otifrwisf
     */
    privbtf boolfbn isBoolfbn(int typf) {
        switdi (typf) {
            dbsf jbvb.sql.Typfs.BIT:
            dbsf jbvb.sql.Typfs.BOOLEAN:
                rfturn truf;
            dffbult:
                rfturn fblsf;
        }
    }


    /**
     * Convfrts tif givfn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * to tif stbndbrd mbpping for tif spfdififd SQL tbrgft dbtb typf.
     * Tif donvfrsion must bf to b string or numfrid typf, but tifrf brf no
     * rfstridtions on tif typf to bf donvfrtfd.  If tif sourdf typf bnd tbrgft
     * typf brf tif sbmf, tif givfn objfdt is simply rfturnfd.
     *
     * @pbrbm srdObj tif <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     *               tibt is to bf donvfrtfd to tif tbrgft typf
     * @pbrbm srdTypf tif dbtb typf tibt is tif stbndbrd mbpping in SQL of tif
     *                objfdt to bf donvfrtfd; must bf onf of tif donstbnts in
     *                <dodf>jbvb.sql.Typfs</dodf>
     * @pbrbm trgTypf tif SQL dbtb typf to wiidi to donvfrt tif givfn objfdt;
     *                must bf onf of tif following donstbnts in
     *                <dodf>jbvb.sql.Typfs</dodf>: <dodf>NUMERIC</dodf>,
     *         <dodf>DECIMAL</dodf>, <dodf>BIT</dodf>, <dodf>TINYINT</dodf>,
     *         <dodf>SMALLINT</dodf>, <dodf>INTEGER</dodf>, <dodf>BIGINT</dodf>,
     *         <dodf>REAL</dodf>, <dodf>DOUBLE</dodf>, <dodf>FLOAT</dodf>,
     *         <dodf>VARCHAR</dodf>, <dodf>LONGVARCHAR</dodf>, or <dodf>CHAR</dodf>
     * @rfturn bn <dodf>Objfdt</dodf> vbluf.tibt is
     *         tif stbndbrd objfdt mbpping for tif tbrgft SQL typf
     * @tirows SQLExdfption if tif givfn tbrgft typf is not onf of tif string or
     *         numfrid typfs in <dodf>jbvb.sql.Typfs</dodf>
     */
    privbtf Objfdt donvfrtNumfrid(Objfdt srdObj, int srdTypf,
    int trgTypf) tirows SQLExdfption {

        if (srdTypf == trgTypf) {
            rfturn srdObj;
        }

        if (isNumfrid(trgTypf) == fblsf && isString(trgTypf) == fblsf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString() + trgTypf);
        }

        try {
            switdi (trgTypf) {
                dbsf jbvb.sql.Typfs.BIT:
                    Intfgfr i = Intfgfr.vblufOf(srdObj.toString().trim());
                    rfturn i.fqubls(0) ?
                    Boolfbn.vblufOf(fblsf) :
                        Boolfbn.vblufOf(truf);
                dbsf jbvb.sql.Typfs.TINYINT:
                    rfturn Bytf.vblufOf(srdObj.toString().trim());
                dbsf jbvb.sql.Typfs.SMALLINT:
                    rfturn Siort.vblufOf(srdObj.toString().trim());
                dbsf jbvb.sql.Typfs.INTEGER:
                    rfturn Intfgfr.vblufOf(srdObj.toString().trim());
                dbsf jbvb.sql.Typfs.BIGINT:
                    rfturn Long.vblufOf(srdObj.toString().trim());
                dbsf jbvb.sql.Typfs.NUMERIC:
                dbsf jbvb.sql.Typfs.DECIMAL:
                    rfturn nfw BigDfdimbl(srdObj.toString().trim());
                dbsf jbvb.sql.Typfs.REAL:
                dbsf jbvb.sql.Typfs.FLOAT:
                    rfturn nfw Flobt(srdObj.toString().trim());
                dbsf jbvb.sql.Typfs.DOUBLE:
                    rfturn nfw Doublf(srdObj.toString().trim());
                dbsf jbvb.sql.Typfs.CHAR:
                dbsf jbvb.sql.Typfs.VARCHAR:
                dbsf jbvb.sql.Typfs.LONGVARCHAR:
                    rfturn srdObj.toString();
                dffbult:
                    tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString()+ trgTypf);
            }
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString() + trgTypf);
        }
    }

    /**
     * Convfrts tif givfn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * to tif stbndbrd objfdt mbpping for tif spfdififd SQL tbrgft dbtb typf.
     * Tif donvfrsion must bf to b string or tfmporbl typf, bnd tifrf brf blso
     * rfstridtions on tif typf to bf donvfrtfd.
     * <P>
     * <TABLE ALIGN="CENTER" BORDER CELLPADDING=10 BORDERCOLOR="#0000FF"
     * <CAPTION ALIGN="CENTER"><B>Pbrbmftfrs bnd Rfturn Vblufs</B></CAPTION>
     * <TR>
     *   <TD><B>Sourdf SQL Typf</B>
     *   <TD><B>Tbrgft SQL Typf</B>
     *   <TD><B>Objfdt Rfturnfd</B>
     * </TR>
     * <TR>
     *   <TD><dodf>TIMESTAMP</dodf>
     *   <TD><dodf>DATE</dodf>
     *   <TD><dodf>jbvb.sql.Dbtf</dodf>
     * </TR>
     * <TR>
     *   <TD><dodf>TIMESTAMP</dodf>
     *   <TD><dodf>TIME</dodf>
     *   <TD><dodf>jbvb.sql.Timf</dodf>
     * </TR>
     * <TR>
     *   <TD><dodf>TIME</dodf>
     *   <TD><dodf>TIMESTAMP</dodf>
     *   <TD><dodf>jbvb.sql.Timfstbmp</dodf>
     * </TR>
     * <TR>
     *   <TD><dodf>DATE</dodf>, <dodf>TIME</dodf>, or <dodf>TIMESTAMP</dodf>
     *   <TD><dodf>CHAR</dodf>, <dodf>VARCHAR</dodf>, or <dodf>LONGVARCHAR</dodf>
     *   <TD><dodf>jbvb.lbng.String</dodf>
     * </TR>
     * </TABLE>
     * <P>
     * If tif sourdf typf bnd tbrgft typf brf tif sbmf,
     * tif givfn objfdt is simply rfturnfd.
     *
     * @pbrbm srdObj tif <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     *               tibt is to bf donvfrtfd to tif tbrgft typf
     * @pbrbm srdTypf tif dbtb typf tibt is tif stbndbrd mbpping in SQL of tif
     *                objfdt to bf donvfrtfd; must bf onf of tif donstbnts in
     *                <dodf>jbvb.sql.Typfs</dodf>
     * @pbrbm trgTypf tif SQL dbtb typf to wiidi to donvfrt tif givfn objfdt;
     *                must bf onf of tif following donstbnts in
     *                <dodf>jbvb.sql.Typfs</dodf>: <dodf>DATE</dodf>,
     *         <dodf>TIME</dodf>, <dodf>TIMESTAMP</dodf>, <dodf>CHAR</dodf>,
     *         <dodf>VARCHAR</dodf>, or <dodf>LONGVARCHAR</dodf>
     * @rfturn bn <dodf>Objfdt</dodf> vbluf.tibt is
     *         tif stbndbrd objfdt mbpping for tif tbrgft SQL typf
     * @tirows SQLExdfption if tif givfn tbrgft typf is not onf of tif string or
     *         tfmporbl typfs in <dodf>jbvb.sql.Typfs</dodf>
     */
    privbtf Objfdt donvfrtTfmporbl(Objfdt srdObj,
    int srdTypf, int trgTypf) tirows SQLExdfption {

        if (srdTypf == trgTypf) {
            rfturn srdObj;
        }

        if (isNumfrid(trgTypf) == truf ||
        (isString(trgTypf) == fblsf && isTfmporbl(trgTypf) == fblsf)) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        try {
            switdi (trgTypf) {
                dbsf jbvb.sql.Typfs.DATE:
                    if (srdTypf == jbvb.sql.Typfs.TIMESTAMP) {
                        rfturn nfw jbvb.sql.Dbtf(((jbvb.sql.Timfstbmp)srdObj).gftTimf());
                    } flsf {
                        tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
                    }
                dbsf jbvb.sql.Typfs.TIMESTAMP:
                    if (srdTypf == jbvb.sql.Typfs.TIME) {
                        rfturn nfw Timfstbmp(((jbvb.sql.Timf)srdObj).gftTimf());
                    } flsf {
                        rfturn nfw Timfstbmp(((jbvb.sql.Dbtf)srdObj).gftTimf());
                    }
                dbsf jbvb.sql.Typfs.TIME:
                    if (srdTypf == jbvb.sql.Typfs.TIMESTAMP) {
                        rfturn nfw Timf(((jbvb.sql.Timfstbmp)srdObj).gftTimf());
                    } flsf {
                        tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
                    }
                dbsf jbvb.sql.Typfs.CHAR:
                dbsf jbvb.sql.Typfs.VARCHAR:
                dbsf jbvb.sql.Typfs.LONGVARCHAR:
                    rfturn srdObj.toString();
                dffbult:
                    tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
            }
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

    }

    /**
     * Convfrts tif givfn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * to tif stbndbrd mbpping for tif spfdififd SQL tbrgft dbtb typf.
     * Tif donvfrsion must bf to b string or numfrid typf, but tifrf brf no
     * rfstridtions on tif typf to bf donvfrtfd.  If tif sourdf typf bnd tbrgft
     * typf brf tif sbmf, tif givfn objfdt is simply rfturnfd.
     *
     * @pbrbm srdObj tif <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     *               tibt is to bf donvfrtfd to tif tbrgft typf
     * @pbrbm srdTypf tif dbtb typf tibt is tif stbndbrd mbpping in SQL of tif
     *                objfdt to bf donvfrtfd; must bf onf of tif donstbnts in
     *                <dodf>jbvb.sql.Typfs</dodf>
     * @pbrbm trgTypf tif SQL dbtb typf to wiidi to donvfrt tif givfn objfdt;
     *                must bf onf of tif following donstbnts in
     *                <dodf>jbvb.sql.Typfs</dodf>: <dodf>BIT</dodf>,
     *         or <dodf>BOOLEAN</dodf>
     * @rfturn bn <dodf>Objfdt</dodf> vbluf.tibt is
     *         tif stbndbrd objfdt mbpping for tif tbrgft SQL typf
     * @tirows SQLExdfption if tif givfn tbrgft typf is not onf of tif Boolfbn
     *         typfs in <dodf>jbvb.sql.Typfs</dodf>
     */
    privbtf Objfdt donvfrtBoolfbn(Objfdt srdObj, int srdTypf,
    int trgTypf) tirows SQLExdfption {

        if (srdTypf == trgTypf) {
            rfturn srdObj;
        }

        if (isNumfrid(trgTypf) == truf ||
        (isString(trgTypf) == fblsf && isBoolfbn(trgTypf) == fblsf)) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }


        try {
            switdi (trgTypf) {
                dbsf jbvb.sql.Typfs.BIT:
                    Intfgfr i = Intfgfr.vblufOf(srdObj.toString().trim());
                    rfturn i.fqubls(0) ?
                    Boolfbn.vblufOf(fblsf) :
                        Boolfbn.vblufOf(truf);
                dbsf jbvb.sql.Typfs.BOOLEAN:
                    rfturn Boolfbn.vblufOf(srdObj.toString().trim());
                dffbult:
                    tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString()+ trgTypf);
            }
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString() + trgTypf);
        }
    }

    /**
     * Sfts tif dfsignbtfd nullbblf dolumn in tif durrfnt row or tif
     * insfrt row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi
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
     * dbtb sourdf, bn bpplidbtion must dbll tif mftiod {@link #bddfptCibngfs}
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        BbsfRow row = gftCurrfntRow();
        row.sftColumnObjfdt(dolumnIndfx, null);

    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();
        Objfdt obj = donvfrtBoolfbn(Boolfbn.vblufOf(x),
        jbvb.sql.Typfs.BIT,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        Objfdt obj = donvfrtNumfrid(Bytf.vblufOf(x),
        jbvb.sql.Typfs.TINYINT,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        Objfdt obj = donvfrtNumfrid(Siort.vblufOf(x),
        jbvb.sql.Typfs.SMALLINT,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();
        Objfdt obj = donvfrtNumfrid(x,
        jbvb.sql.Typfs.INTEGER,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        Objfdt obj = donvfrtNumfrid(Long.vblufOf(x),
        jbvb.sql.Typfs.BIGINT,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);

    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        Objfdt obj = donvfrtNumfrid(Flobt.vblufOf(x),
        jbvb.sql.Typfs.REAL,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();
        Objfdt obj = donvfrtNumfrid(Doublf.vblufOf(x),
        jbvb.sql.Typfs.DOUBLE,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        Objfdt obj = donvfrtNumfrid(x,
        jbvb.sql.Typfs.NUMERIC,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (isBinbry(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        Objfdt obj = donvfrtTfmporbl(x,
        jbvb.sql.Typfs.DATE,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        Objfdt obj = donvfrtTfmporbl(x,
        jbvb.sql.Typfs.TIME,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        Objfdt obj = donvfrtTfmporbl(x,
        jbvb.sql.Typfs.TIMESTAMP,
        RowSftMD.gftColumnTypf(dolumnIndfx));

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, obj);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
     * @tirows SQLExdfption if tiis mftiod is invokfd
     */
    publid void updbtfAsdiiStrfbm(int dolumnIndfx, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        // sbnity Cifdk
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();


        if (isString(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf &&
        isBinbry(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        bytf buf[] = nfw bytf[lfngti];
        try {
            int dibrsRfbd = 0;
            do {
                dibrsRfbd += x.rfbd(buf, dibrsRfbd, lfngti - dibrsRfbd);
            } wiilf (dibrsRfbd != lfngti);
            //Cibngfd tif dondition difdk to difdk for lfngti instfbd of -1
        } dbtdi (jbvb.io.IOExdfption fx) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.bsdiistrfbm").toString());
        }
        String str = nfw String(buf);

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, str);

    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
    publid void updbtfBinbryStrfbm(int dolumnIndfx, jbvb.io.InputStrfbm x,int lfngti) tirows SQLExdfption {
        // sbnity Cifdk
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (isBinbry(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        bytf buf[] = nfw bytf[lfngti];
        try {
            int bytfsRfbd = 0;
            do {
                bytfsRfbd += x.rfbd(buf, bytfsRfbd, lfngti - bytfsRfbd);
            } wiilf (bytfsRfbd != -1);
        } dbtdi (jbvb.io.IOExdfption fx) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.binstrfbm").toString());
        }

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, buf);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity Cifdk
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (isString(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf &&
        isBinbry(RowSftMD.gftColumnTypf(dolumnIndfx)) == fblsf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        dibr buf[] = nfw dibr[lfngti];
        try {
            int dibrsRfbd = 0;
            do {
                dibrsRfbd += x.rfbd(buf, dibrsRfbd, lfngti - dibrsRfbd);
            } wiilf (dibrsRfbd != lfngti);
            //Cibngfd tif dondition difdking to difdk for lfngti instfbd of -1
        } dbtdi (jbvb.io.IOExdfption fx) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.binstrfbm").toString());
        }
        String str = nfw String(buf);

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, str);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        int typf = RowSftMD.gftColumnTypf(dolumnIndfx);
        if (typf == Typfs.DECIMAL || typf == Typfs.NUMERIC) {
            ((jbvb.mbti.BigDfdimbl)x).sftSdblf(sdblf);
        }
        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, x);
    }

    /**
     * Sfts tif dfsignbtfd nullbblf dolumn in tif durrfnt row or tif
     * insfrt row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi
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
        updbtfNull(gftColIdxByNbmf(dolumnNbmf));
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfBoolfbn(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfBytf(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfSiort(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfInt(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfLong(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfFlobt(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfDoublf(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfBigDfdimbl(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfString(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfBytfs(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfDbtf(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfTimf(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfTimfstbmp(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
     */
    publid void updbtfAsdiiStrfbm(String dolumnNbmf,
    jbvb.io.InputStrfbm x,
    int lfngti) tirows SQLExdfption {
        updbtfAsdiiStrfbm(gftColIdxByNbmf(dolumnNbmf), x, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfBinbryStrfbm(gftColIdxByNbmf(dolumnNbmf), x, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
     * @pbrbm rfbdfr tif nfw dolumn vbluf; must bf b
     * <dodf>jbvb.io.Rfbdfr</dodf> dontbining <dodf>BINARY</dodf>,
     * <dodf>VARBINARY</dodf>, <dodf>LONGVARBINARY</dodf>, <dodf>CHAR</dodf>,
     * <dodf>VARCHAR</dodf>, or <dodf>LONGVARCHAR</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in dibrbdtfrs
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif dbtb
     *            in tif strfbm is not b binbry or dibrbdtfr typf, or (4) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfCibrbdtfrStrfbm(String dolumnNbmf,
    jbvb.io.Rfbdfr rfbdfr,
    int lfngti) tirows SQLExdfption {
        updbtfCibrbdtfrStrfbm(gftColIdxByNbmf(dolumnNbmf), rfbdfr, lfngti);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfObjfdt(String dolumnNbmf, Objfdt x, int sdblf) tirows SQLExdfption {
        updbtfObjfdt(gftColIdxByNbmf(dolumnNbmf), x, sdblf);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfObjfdt(gftColIdxByNbmf(dolumnNbmf), x);
    }

    /**
     * Insfrts tif dontfnts of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's insfrt
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
        int pos;

        if (onInsfrtRow == fblsf ||
            insfrtRow.isComplftfRow(RowSftMD) == fblsf) {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.fbilfdins").toString());
        }
        // Addfd tif sftting of pbrbmftfrs tibt brf pbssfd
        // to sftXXX mftiods bftfr bn fmpty CRS Objfdt is
        // drfbtfd tirougi RowSftMftbDbtb objfdt
        Objfdt [] toInsfrt = gftPbrbms();

        for(int i = 0;i < toInsfrt.lfngti; i++) {
          insfrtRow.sftColumnObjfdt(i+1,toInsfrt[i]);
        }

        Row insRow = nfw Row(RowSftMD.gftColumnCount(),
        insfrtRow.gftOrigRow());
        insRow.sftInsfrtfd();
        /*
         * Tif nfw row is insfrtfd into tif RowSft
         * immfdibtfly following tif durrfnt row.
         *
         * If wf brf bftfrlbst tifn tif rows brf
         * insfrtfd bt tif fnd.
         */
        if (durrfntRow >= numRows || durrfntRow < 0) {
            pos = numRows;
        } flsf {
            pos = durrfntRow;
        }

        rvi.bdd(pos, insRow);
        ++numRows;
        // notify tif listfnfrs tibt tif row dibngfd.
        notifyRowCibngfd();
    }

    /**
     * Mbrks tif durrfnt row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs
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
        // mbkf surf wf brfn't on tif insfrt row
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.updbtfins").toString());
        }

        ((Row)gftCurrfntRow()).sftUpdbtfd();

        // notify tif listfnfrs tibt tif row dibngfd.
        notifyRowCibngfd();
    }

    /**
     * Dflftfs tif durrfnt row from tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bnd
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
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        ((Row)gftCurrfntRow()).sftDflftfd();
        ++numDflftfd;

        // notify tif listfnfrs tibt tif row dibngfd.
        notifyRowCibngfd();
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
        // mbkf surf wf brf on b row
        difdkCursor();

        // don't wbnt tiis to ibppfn...
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddp").toString());
        }

        Row durrfntRow = (Row)gftCurrfntRow();
        // just undo bny dibngfs mbdf to tiis row.
        durrfntRow.dlfbrUpdbtfd();

    }

    /**
     * Rolls bbdk bny updbtfs mbdf to tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bnd notififs listfnfrs tibt
     * b row ibs dibngfd.  To ibvf bn ffffdt, tiis mftiod
     * must bf dbllfd bftfr bn <dodf>updbtfXXX</dodf> mftiod ibs bffn
     * dbllfd bnd bfforf tif mftiod <dodf>updbtfRow</dodf> ibs bffn dbllfd.
     * If no updbtfs ibvf bffn mbdf or tif mftiod <dodf>updbtfRow</dodf>
     * ibs blrfbdy bffn dbllfd, tiis mftiod ibs no ffffdt.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row, bfforf tif
     *            first row, or bftfr tif lbst row
     */
    publid void dbndflRowUpdbtfs() tirows SQLExdfption {
        // mbkf surf wf brf on b row
        difdkCursor();

        // don't wbnt tiis to ibppfn...
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddp").toString());
        }

        Row durrfntRow = (Row)gftCurrfntRow();
        if (durrfntRow.gftUpdbtfd() == truf) {
            durrfntRow.dlfbrUpdbtfd();
            notifyRowCibngfd();
        }
    }

    /**
     * Movfs tif dursor for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
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
     * @tirows SQLExdfption if tiis <dodf>CbdifdRowSftImpl</dodf> objfdt is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void movfToInsfrtRow() tirows SQLExdfption {
        if (gftCondurrfndy() == RfsultSft.CONCUR_READ_ONLY) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.movftoins").toString());
        }
        if (insfrtRow == null) {
            if (RowSftMD == null)
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.movftoins1").toString());
            int numCols = RowSftMD.gftColumnCount();
            if (numCols > 0) {
                insfrtRow = nfw InsfrtRow(numCols);
            } flsf {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.movftoins2").toString());
            }
        }
        onInsfrtRow = truf;
        // %%% sftCurrfntRow dbllfd in BbsfRow

        durrfntRow = dursorPos;
        dursorPos = -1;

        insfrtRow.initInsfrtRow();
    }

    /**
     * Movfs tif dursor for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt to
     * tif durrfnt row.  Tif durrfnt row is tif row tif dursor wbs on
     * wifn tif mftiod <dodf>movfToInsfrtRow</dodf> wbs dbllfd.
     * <P>
     * Cblling tiis mftiod ibs no ffffdt unlfss it is dbllfd wiilf tif
     * dursor is on tif insfrt row.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void movfToCurrfntRow() tirows SQLExdfption {
        if (onInsfrtRow == fblsf) {
            rfturn;
        } flsf {
            dursorPos = durrfntRow;
            onInsfrtRow = fblsf;
        }
    }

    /**
     * Rfturns <dodf>null</dodf>.
     *
     * @rfturn <dodf>null</dodf>
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid Stbtfmfnt gftStbtfmfnt() tirows SQLExdfption {
        rfturn null;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn <dodf>Objfdt</dodf> in
     * tif Jbvb progrbmming lbngubgf, using tif givfn
     * <dodf>jbvb.util.Mbp</dodf> objfdt to dustom mbp tif vbluf if
     * bppropribtf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt siowing tif mbpping
     *            from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming
     *            lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting tif SQL vbluf
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds or
     *            tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row
     */
     publid Objfdt gftObjfdt(int dolumnIndfx,
                             jbvb.util.Mbp<String,Clbss<?>> mbp)
         tirows SQLExdfption
     {
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }
        if (vbluf instbndfof Strudt) {
            Strudt s = (Strudt)vbluf;

            // look up tif dlbss in tif mbp
            Clbss<?> d = mbp.gft(s.gftSQLTypfNbmf());
            if (d != null) {
                // drfbtf nfw instbndf of tif dlbss
                SQLDbtb obj = null;
                try {
                    obj = (SQLDbtb) RfflfdtUtil.nfwInstbndf(d);
                } dbtdi(Exdfption fx) {
                    tirow nfw SQLExdfption("Unbblf to Instbntibtf: ", fx);
                }
                // gft tif bttributfs from tif strudt
                Objfdt bttribs[] = s.gftAttributfs(mbp);
                // drfbtf tif SQLInput "strfbm"
                SQLInputImpl sqlInput = nfw SQLInputImpl(bttribs, mbp);
                // rfbd tif vblufs...
                obj.rfbdSQL(sqlInput, s.gftSQLTypfNbmf());
                rfturn (Objfdt)obj;
            }
        }
        rfturn vbluf;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Rff</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting bn SQL<dodf> REF</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>REF</dodf> vbluf
     * @sff #gftRff(String)
     */
    publid Rff gftRff(int dolumnIndfx) tirows SQLExdfption {
        Rff vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (RowSftMD.gftColumnTypf(dolumnIndfx) != jbvb.sql.Typfs.REF) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        sftLbstVblufNull(fblsf);
        vbluf = (Rff)(gftCurrfntRow().gftColumnObjfdt(dolumnIndfx));

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        rfturn vbluf;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Blob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting bn SQL <dodf>BLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>BLOB</dodf> vbluf
     * @sff #gftBlob(String)
     */
    publid Blob gftBlob(int dolumnIndfx) tirows SQLExdfption {
        Blob vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (RowSftMD.gftColumnTypf(dolumnIndfx) != jbvb.sql.Typfs.BLOB) {
            Systfm.out.println(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.typf").toString(), RowSftMD.gftColumnTypf(dolumnIndfx)));
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        sftLbstVblufNull(fblsf);
        vbluf = (Blob)(gftCurrfntRow().gftColumnObjfdt(dolumnIndfx));

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        rfturn vbluf;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Clob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting bn SQL <dodf>CLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>CLOB</dodf> vbluf
     * @sff #gftClob(String)
     */
    publid Clob gftClob(int dolumnIndfx) tirows SQLExdfption {
        Clob vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (RowSftMD.gftColumnTypf(dolumnIndfx) != jbvb.sql.Typfs.CLOB) {
            Systfm.out.println(MfssbgfFormbt.formbt(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.typf").toString(), RowSftMD.gftColumnTypf(dolumnIndfx)));
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        sftLbstVblufNull(fblsf);
        vbluf = (Clob)(gftCurrfntRow().gftColumnObjfdt(dolumnIndfx));

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        rfturn vbluf;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
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
     * @sff #gftArrby(String)
     */
    publid Arrby gftArrby(int dolumnIndfx) tirows SQLExdfption {
        jbvb.sql.Arrby vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (RowSftMD.gftColumnTypf(dolumnIndfx) != jbvb.sql.Typfs.ARRAY) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        sftLbstVblufNull(fblsf);
        vbluf = (jbvb.sql.Arrby)(gftCurrfntRow().gftColumnObjfdt(dolumnIndfx));

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        rfturn vbluf;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn <dodf>Objfdt</dodf> in
     * tif Jbvb progrbmming lbngubgf, using tif givfn
     * <dodf>jbvb.util.Mbp</dodf> objfdt to dustom mbp tif vbluf if
     * bppropribtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt siowing tif mbpping
     *        from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming
     *        lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting tif SQL vbluf
     * @tirows SQLExdfption if tif givfn dolumn nbmf is not tif nbmf of
     *         b dolumn in tiis rowsft or tif dursor is not on onf of
     *         tiis rowsft's rows or its insfrt row
     */
    publid Objfdt gftObjfdt(String dolumnNbmf,
                            jbvb.util.Mbp<String,Clbss<?>> mbp)
    tirows SQLExdfption {
        rfturn gftObjfdt(gftColIdxByNbmf(dolumnNbmf), mbp);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Rff</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting bn SQL<dodf> REF</dodf> vbluf
     * @tirows SQLExdfption  if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dolumn vbluf
     *            is not bn SQL <dodf>REF</dodf> vbluf
     * @sff #gftRff(int)
     */
    publid Rff gftRff(String dolNbmf) tirows SQLExdfption {
        rfturn gftRff(gftColIdxByNbmf(dolNbmf));
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Blob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting bn SQL <dodf>BLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>BLOB</dodf> vbluf
     * @sff #gftBlob(int)
     */
    publid Blob gftBlob(String dolNbmf) tirows SQLExdfption {
        rfturn gftBlob(gftColIdxByNbmf(dolNbmf));
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Clob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting bn SQL
     *         <dodf>CLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>CLOB</dodf> vbluf
     * @sff #gftClob(int)
     */
    publid Clob gftClob(String dolNbmf) tirows SQLExdfption {
        rfturn gftClob(gftColIdxByNbmf(dolNbmf));
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt
     * in tif Jbvb progrbmming lbngugbgf.
     *
     * @pbrbm dolNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting bn SQL
     *         <dodf>ARRAY</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>ARRAY</dodf> vbluf
     * @sff #gftArrby(int)
     */
    publid Arrby gftArrby(String dolNbmf) tirows SQLExdfption {
        rfturn gftArrby(gftColIdxByNbmf(dolNbmf));
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf>
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
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        vbluf = donvfrtTfmporbl(vbluf,
        RowSftMD.gftColumnTypf(dolumnIndfx),
        jbvb.sql.Typfs.DATE);

        // drfbtf b dffbult dblfndbr
        Cblfndbr dffbultCbl = Cblfndbr.gftInstbndf();
        // sft tiis Cblfndbr to tif timf wf ibvf
        dffbultCbl.sftTimf((jbvb.util.Dbtf)vbluf);

        /*
         * Now wf dbn pull tif pifdfs of tif dbtf out
         * of tif dffbult dblfndbr bnd put tifm into
         * tif usfr providfd dblfndbr
         */
        dbl.sft(Cblfndbr.YEAR, dffbultCbl.gft(Cblfndbr.YEAR));
        dbl.sft(Cblfndbr.MONTH, dffbultCbl.gft(Cblfndbr.MONTH));
        dbl.sft(Cblfndbr.DAY_OF_MONTH, dffbultCbl.gft(Cblfndbr.DAY_OF_MONTH));

        /*
         * Tiis looks b littlf odd but it is dorrfdt -
         * Cblfndbr.gftTimf() rfturns b Dbtf...
         */
        rfturn nfw jbvb.sql.Dbtf(dbl.gftTimf().gftTimf());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf>
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
        rfturn gftDbtf(gftColIdxByNbmf(dolumnNbmf), dbl);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf>
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
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        vbluf = donvfrtTfmporbl(vbluf,
        RowSftMD.gftColumnTypf(dolumnIndfx),
        jbvb.sql.Typfs.TIME);

        // drfbtf b dffbult dblfndbr
        Cblfndbr dffbultCbl = Cblfndbr.gftInstbndf();
        // sft tif timf in tif dffbult dblfndbr
        dffbultCbl.sftTimf((jbvb.util.Dbtf)vbluf);

        /*
         * Now wf dbn pull tif pifdfs of tif dbtf out
         * of tif dffbult dblfndbr bnd put tifm into
         * tif usfr providfd dblfndbr
         */
        dbl.sft(Cblfndbr.HOUR_OF_DAY, dffbultCbl.gft(Cblfndbr.HOUR_OF_DAY));
        dbl.sft(Cblfndbr.MINUTE, dffbultCbl.gft(Cblfndbr.MINUTE));
        dbl.sft(Cblfndbr.SECOND, dffbultCbl.gft(Cblfndbr.SECOND));

        rfturn nfw jbvb.sql.Timf(dbl.gftTimf().gftTimf());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf>
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
        rfturn gftTimf(gftColIdxByNbmf(dolumnNbmf), dbl);
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timfstbmp</dodf>
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
        Objfdt vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        sftLbstVblufNull(fblsf);
        vbluf = gftCurrfntRow().gftColumnObjfdt(dolumnIndfx);

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        vbluf = donvfrtTfmporbl(vbluf,
        RowSftMD.gftColumnTypf(dolumnIndfx),
        jbvb.sql.Typfs.TIMESTAMP);

        // drfbtf b dffbult dblfndbr
        Cblfndbr dffbultCbl = Cblfndbr.gftInstbndf();
        // sft tif timf in tif dffbult dblfndbr
        dffbultCbl.sftTimf((jbvb.util.Dbtf)vbluf);

        /*
         * Now wf dbn pull tif pifdfs of tif dbtf out
         * of tif dffbult dblfndbr bnd put tifm into
         * tif usfr providfd dblfndbr
         */
        dbl.sft(Cblfndbr.YEAR, dffbultCbl.gft(Cblfndbr.YEAR));
        dbl.sft(Cblfndbr.MONTH, dffbultCbl.gft(Cblfndbr.MONTH));
        dbl.sft(Cblfndbr.DAY_OF_MONTH, dffbultCbl.gft(Cblfndbr.DAY_OF_MONTH));
        dbl.sft(Cblfndbr.HOUR_OF_DAY, dffbultCbl.gft(Cblfndbr.HOUR_OF_DAY));
        dbl.sft(Cblfndbr.MINUTE, dffbultCbl.gft(Cblfndbr.MINUTE));
        dbl.sft(Cblfndbr.SECOND, dffbultCbl.gft(Cblfndbr.SECOND));

        rfturn nfw jbvb.sql.Timfstbmp(dbl.gftTimf().gftTimf());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
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
        rfturn gftTimfstbmp(gftColIdxByNbmf(dolumnNbmf), dbl);
    }

    /*
     * RowSftIntfrnbl Intfrfbdf
     */

    /**
     * Rftrifvfs tif <dodf>Connfdtion</dodf> objfdt pbssfd to tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.  Tiis donnfdtion mby bf
     * usfd to populbtf tiis rowsft witi dbtb or to writf dbtb bbdk
     * to its undfrlying dbtb sourdf.
     *
     * @rfturn tif <dodf>Connfdtion</dodf> objfdt pbssfd to tiis rowsft;
     *         mby bf <dodf>null</dodf> if tifrf is no donnfdtion
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid Connfdtion gftConnfdtion() tirows SQLExdfption{
        rfturn donn;
    }

    /**
     * Sfts tif mftbdbtb for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * witi tif givfn <dodf>RowSftMftbDbtb</dodf> objfdt.
     *
     * @pbrbm md b <dodf>RowSftMftbDbtb</dodf> objfdt instbndf dontbining
     *            mftbdbtb bbout tif dolumsn in tif rowsft
     * @tirows SQLExdfption if invblid mftb dbtb is supplifd to tif
     *            rowsft
     */
    publid void sftMftbDbtb(RowSftMftbDbtb md) tirows SQLExdfption {
        RowSftMD =(RowSftMftbDbtbImpl) md;
    }

    /**
     * Rfturns b rfsult sft dontbining tif originbl vbluf of tif rowsft. Tif
     * originbl vbluf is tif stbtf of tif <dodf>CbdifdRowSftImpl</dodf> bftfr tif
     * lbst populbtion or syndironizbtion (wiidifvfr oddurrfd most rfdfntly) witi
     * tif dbtb sourdf.
     * <p>
     * Tif dursor is positionfd bfforf tif first row in tif rfsult sft.
     * Only rows dontbinfd in tif rfsult sft rfturnfd by <dodf>gftOriginbl()</dodf>
     * brf sbid to ibvf bn originbl vbluf.
     *
     * @rfturn tif originbl rfsult sft of tif rowsft
     * @tirows SQLExdfption if bn frror oddurs produdf tif
     *           <dodf>RfsultSft</dodf> objfdt
     */
    publid RfsultSft gftOriginbl() tirows SQLExdfption {
        CbdifdRowSftImpl drs = nfw CbdifdRowSftImpl();
        drs.RowSftMD = RowSftMD;
        drs.numRows = numRows;
        drs.dursorPos = 0;

        // mbkf surf wf don't gft somfonf plbying witi tifsf
        // %%% is tiis now nfdfssbry ???
        //drs.sftRfbdfr(null);
        //drs.sftWritfr(null);
        int dolCount = RowSftMD.gftColumnCount();
        Row orig;

        for (Itfrbtor<?> i = rvi.itfrbtor(); i.ibsNfxt();) {
            orig = nfw Row(dolCount, ((Row)i.nfxt()).gftOrigRow());
            drs.rvi.bdd(orig);
        }
        rfturn (RfsultSft)drs;
    }

    /**
     * Rfturns b rfsult sft dontbining tif originbl vbluf of tif durrfnt
     * row only.
     * Tif originbl vbluf is tif stbtf of tif <dodf>CbdifdRowSftImpl</dodf> bftfr
     * tif lbst populbtion or syndironizbtion (wiidifvfr oddurrfd most rfdfntly)
     * witi tif dbtb sourdf.
     *
     * @rfturn tif originbl rfsult sft of tif row
     * @tirows SQLExdfption if tifrf is no durrfnt row
     * @sff #sftOriginblRow
     */
    publid RfsultSft gftOriginblRow() tirows SQLExdfption {
        CbdifdRowSftImpl drs = nfw CbdifdRowSftImpl();
        drs.RowSftMD = RowSftMD;
        drs.numRows = 1;
        drs.dursorPos = 0;
        drs.sftTypfMbp(tiis.gftTypfMbp());

        // mbkf surf wf don't gft somfonf plbying witi tifsf
        // %%% is tiis now nfdfssbry ???
        //drs.sftRfbdfr(null);
        //drs.sftWritfr(null);

        Row orig = nfw Row(RowSftMD.gftColumnCount(),
        gftCurrfntRow().gftOrigRow());

        drs.rvi.bdd(orig);

        rfturn (RfsultSft)drs;

    }

    /**
     * Mbrks tif durrfnt row in tiis rowsft bs bfing bn originbl row.
     *
     * @tirows SQLExdfption if tifrf is no durrfnt row
     * @sff #gftOriginblRow
     */
    publid void sftOriginblRow() tirows SQLExdfption {
        if (onInsfrtRow == truf) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invblidop").toString());
        }

        Row row = (Row)gftCurrfntRow();
        mbkfRowOriginbl(row);

        // tiis dbn ibppfn if dflftfd rows brf bfing siown
        if (row.gftDflftfd() == truf) {
            rfmovfCurrfntRow();
        }
    }

    /**
     * Mbkfs tif givfn row of tiis rowsft tif originbl row by dlfbring bny
     * sfttings tibt mbrk tif row bs ibving bffn insfrtfd, dflftfd, or updbtfd.
     * Tiis mftiod is dbllfd intfrnblly by tif mftiods
     * <dodf>sftOriginblRow</dodf>
     * bnd <dodf>sftOriginbl</dodf>.
     *
     * @pbrbm row tif row to bf mbdf tif originbl row
     */
    privbtf void mbkfRowOriginbl(Row row) {
        if (row.gftInsfrtfd() == truf) {
            row.dlfbrInsfrtfd();
        }

        if (row.gftUpdbtfd() == truf) {
            row.movfCurrfntToOrig();
        }
    }

    /**
     * Mbrks bll rows in tiis rowsft bs bfing originbl rows. Any updbtfs
     * mbdf to tif rows bfdomf tif originbl vblufs for tif rowsft.
     * Cblls to tif mftiod <dodf>sftOriginbl</dodf> donnot bf rfvfrsfd.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void sftOriginbl() tirows SQLExdfption {
        for (Itfrbtor<?> i = rvi.itfrbtor(); i.ibsNfxt();) {
            Row row = (Row)i.nfxt();
            mbkfRowOriginbl(row);
            // rfmovf dflftfd rows from tif dollfdtion.
            if (row.gftDflftfd() == truf) {
                i.rfmovf();
                --numRows;
            }
        }
        numDflftfd = 0;

        // notify bny listfnfrs tibt tif rowsft ibs dibngfd
        notifyRowSftCibngfd();
    }

    /**
     * Rfturns bn idfntififr for tif objfdt (tbblf) tibt wbs usfd to drfbtf tiis
     * rowsft.
     *
     * @rfturn b <dodf>String</dodf> objfdt tibt idfntififs tif tbblf from
     *         wiidi tiis <dodf>CbdifdRowSftImpl</dodf> objfdt wbs dfrivfd
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid String gftTbblfNbmf() tirows SQLExdfption {
        rfturn tbblfNbmf;
    }

    /**
     * Sfts tif idfntififr for tif tbblf from wiidi tiis rowsft wbs dfrivfd
     * to tif givfn tbblf nbmf.
     *
     * @pbrbm tbbNbmf b <dodf>String</dodf> objfdt tibt idfntififs tif
     *          tbblf from wiidi tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     *          wbs dfrivfd
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void sftTbblfNbmf(String tbbNbmf) tirows SQLExdfption {
        if (tbbNbmf == null)
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.tbblfnbmf").toString());
        flsf
            tbblfNbmf = tbbNbmf;
    }

    /**
     * Rfturns tif dolumns tibt mbkf b kfy to uniqufly idfntify b
     * row in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn bn brrby of dolumn numbfrs tibt donstitutfs b primbry
     *           kfy for tiis rowsft. Tiis brrby siould bf fmpty
     *           if no dolumn is rfprfsfntitivf of b primbry kfy
     * @tirows SQLExdfption if tif rowsft is fmpty or no dolumns
     *           brf dfsignbtfd bs primbry kfys
     * @sff #sftKfyColumns
     */
    publid int[] gftKfyColumns() tirows SQLExdfption {
        int[]kfyColumns  = tiis.kfyCols;
        rfturn (kfyColumns == null) ? null : Arrbys.dopyOf(kfyColumns, kfyColumns.lfngti);
    }


    /**
     * Sfts tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's
     * <dodf>kfyCols</dodf> fifld witi tif givfn brrby of dolumn
     * numbfrs, wiidi forms b kfy for uniqufly idfntifying b row
     * in tiis rowsft.
     *
     * @pbrbm kfys bn brrby of <dodf>int</dodf> indidbting tif
     *        dolumns tibt form b primbry kfy for tiis
     *        <dodf>CbdifdRowSftImpl</dodf> objfdt; fvfry
     *        flfmfnt in tif brrby must bf grfbtfr tibn
     *        <dodf>0</dodf> bnd lfss tibn or fqubl to tif numbfr
     *        of dolumns in tiis rowsft
     * @tirows SQLExdfption if bny of tif numbfrs in tif
     *            givfn brrby is not vblid for tiis rowsft
     * @sff #gftKfyColumns
     */
    publid void sftKfyColumns(int [] kfys) tirows SQLExdfption {
        int numCols = 0;
        if (RowSftMD != null) {
            numCols = RowSftMD.gftColumnCount();
            if (kfys.lfngti > numCols)
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.kfydols").toString());
        }
        kfyCols = nfw int[kfys.lfngti];
        for (int i = 0; i < kfys.lfngti; i++) {
            if (RowSftMD != null && (kfys[i] <= 0 ||
            kfys[i] > numCols)) {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invbliddol").toString() +
                kfys[i]);
            }
            kfyCols[i] = kfys[i];
        }
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Rff</dodf> vbluf.
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
     * @pbrbm rff tif nfw dolumn <dodf>jbvb.sql.Rff</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *        (2) tif dursor is not on onf of tiis rowsft's rows or its
     *        insfrt row, or (3) tiis rowsft is
     *        <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRff(int dolumnIndfx, jbvb.sql.Rff rff) tirows SQLExdfption {
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        // SfriblClob will iflp in gftting tif bytf brrby bnd storing it.
        // Wf nffd to bf difdking DbtbbbsfMftbDbtb.lodbtorsUpdbtorCopy()
        // or tirougi RowSftMftbDbtb.lodbtorsUpdbtorCopy()
        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, nfw SfriblRff(rff));
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
     * @pbrbm rff tif nfw dolumn <dodf>jbvb.sql.Rff</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *        nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *        onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *        rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRff(String dolumnNbmf, jbvb.sql.Rff rff) tirows SQLExdfption {
        updbtfRff(gftColIdxByNbmf(dolumnNbmf), rff);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
     *        (2) tif dursor is not on onf of tiis rowsft's rows or its
     *        insfrt row, or (3) tiis rowsft is
     *        <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfClob(int dolumnIndfx, Clob d) tirows SQLExdfption {
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        // SfriblClob will iflp in gftting tif bytf brrby bnd storing it.
        // Wf nffd to bf difdking DbtbbbsfMftbDbtb.lodbtorsUpdbtorCopy()
        // or tirougi RowSftMftbDbtb.lodbtorsUpdbtorCopy()

        if(dbmslodbtorsUpdbtfCopy){
           gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, nfw SfriblClob(d));
        }
        flsf{
           tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotsupp").toString());
        }
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfClob(gftColIdxByNbmf(dolumnNbmf), d);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        // SfriblBlob will iflp in gftting tif bytf brrby bnd storing it.
        // Wf nffd to bf difdking DbtbbbsfMftbDbtb.lodbtorsUpdbtorCopy()
        // or tirougi RowSftMftbDbtb.lodbtorsUpdbtorCopy()

        if(dbmslodbtorsUpdbtfCopy){
           gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, nfw SfriblBlob(b));
        }
        flsf{
           tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotsupp").toString());
        }
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfBlob(gftColIdxByNbmf(dolumnNbmf), b);
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        // SfriblArrby will iflp in gftting tif bytf brrby bnd storing it.
        // Wf nffd to bf difdking DbtbbbsfMftbDbtb.lodbtorsUpdbtorCopy()
        // or tirougi RowSftMftbDbtb.lodbtorsUpdbtorCopy()
        gftCurrfntRow().sftColumnObjfdt(dolumnIndfx, nfw SfriblArrby(b));
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
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
        updbtfArrby(gftColIdxByNbmf(dolumnNbmf), b);
    }


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.nft.URL</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b jbvb.nft.URL objfdt dontbining tif rfsourdf rfffrfndf dfsdribfd by
     * tif URL
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>DATALINK</dodf> vbluf.
     * @sff #gftURL(String)
     */
    publid jbvb.nft.URL gftURL(int dolumnIndfx) tirows SQLExdfption {
        //tirow nfw SQLExdfption("Opfrbtion not supportfd");

        jbvb.nft.URL vbluf;

        // sbnity difdk.
        difdkIndfx(dolumnIndfx);
        // mbkf surf tif dursor is on b vblid row
        difdkCursor();

        if (RowSftMD.gftColumnTypf(dolumnIndfx) != jbvb.sql.Typfs.DATALINK) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.dtypfmismt").toString());
        }

        sftLbstVblufNull(fblsf);
        vbluf = (jbvb.nft.URL)(gftCurrfntRow().gftColumnObjfdt(dolumnIndfx));

        // difdk for SQL NULL
        if (vbluf == null) {
            sftLbstVblufNull(truf);
            rfturn null;
        }

        rfturn vbluf;
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.nft.URL</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b jbvb.nft.URL objfdt dontbining tif rfsourdf rfffrfndf dfsdribfd by
     * tif URL
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf not tif nbmf of b dolumn
     * in tiis rowsft, or
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>DATALINK</dodf> vbluf.
     * @sff #gftURL(int)
     */
    publid jbvb.nft.URL gftURL(String dolumnNbmf) tirows SQLExdfption {
        rfturn gftURL(gftColIdxByNbmf(dolumnNbmf));

    }

    /**
     * Tif first wbrning rfportfd by dblls on tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt is rfturnfd. Subsfqufnt <dodf>CbdifdRowSftImpl</dodf> wbrnings will
     * bf dibinfd to tiis <dodf>SQLWbrning</dodf>. All <dodf>RowSftWbrnings</dodf>
     * wbrnings brf gfnfrbtfd in tif disdonnfdtfd fnvironmfnt bnd rfmbin b
     * sfpfrbtf wbrning dibin to tibt providfd by tif <dodf>gftWbrnings</dodf>
     * mftiod.
     *
     * <P>Tif wbrning dibin is butombtidblly dlfbrfd fbdi timf b nfw
     * row is rfbd.
     *
     * <P><B>Notf:</B> Tiis wbrning dibin only dovfrs wbrnings dbusfd
     * by <dodf>CbdifdRowSft</dodf> (bnd tifir diild intfrfbdf)
     * mftiods. All <dodf>SQLWbrnings</dodf> dbn bf obtbinfd using tif
     * <dodf>gftWbrnings</dodf> mftiod wiidi trbdks wbrnings gfnfrbtfd
     * by tif undfrlying JDBC drivfr.
     * @rfturn tif first SQLWbrning or null
     *
     */
    publid RowSftWbrning gftRowSftWbrnings() {
        try {
            notifyCursorMovfd();
        } dbtdi (SQLExdfption f) {} // mbsk fxdfption
        rfturn rowsftWbrning;
    }


    /**
     * Tif fundtion trifs to isolbtf tif tbblfnbmf wifn only sftCommbnd
     * is sft bnd not sftTbblfnbmf is dbllfd providfd tifrf is only onf tbblf
     * nbmf in tif qufry flsf just lfbvfs tif sftting of tbblf nbmf bs sudi.
     * If sftTbblfnbmf is sft lbtfr it will ovfr ridf tiis tbblf nbmf
     * vbluf so rftrifvfd.
     *
     * @rfturn tif tbblfnbmf if only onf tbblf in qufry flsf rfturn ""
     */
    privbtf String buildTbblfNbmf(String dommbnd) tirows SQLExdfption {

        // If wf ibvf b qufry from onf tbblf,
        // wf sft tif tbblf nbmf impliditly
        // flsf usfr ibs to fxpliditly sft tif tbblf nbmf.

        int indfxFrom, indfxCommb;
        String strTbblfnbmf ="";
        dommbnd = dommbnd.trim();

        // Qufry dbn bf b sflfdt, insfrt or  updbtf

        if(dommbnd.toLowfrCbsf().stbrtsWiti("sflfdt")) {
            // look for "from" kfyword, bftfr tibt look for b
            // dommb bftfr from. If dommb is tifrf don't sft
            // tbblf nbmf flsf isolbtf tbblf nbmf.

            indfxFrom = dommbnd.toLowfrCbsf().indfxOf("from");
            indfxCommb = dommbnd.indfxOf(',', indfxFrom);

            if(indfxCommb == -1) {
                // implifs only onf tbblf
                strTbblfnbmf = (dommbnd.substring(indfxFrom+"from".lfngti(),dommbnd.lfngti())).trim();

                String tbbNbmf = strTbblfnbmf;

                int idxWifrf = tbbNbmf.toLowfrCbsf().indfxOf("wifrf");

                /**
                  * Adding tif bddtionbl difdk for donditions following tif tbblf nbmf.
                  * If b dondition is found trundbtf it.
                  **/

                if(idxWifrf != -1)
                {
                   tbbNbmf = tbbNbmf.substring(0,idxWifrf).trim();
                }

                strTbblfnbmf = tbbNbmf;

            } flsf {
                //strTbblfnbmf="";
            }

        } flsf if(dommbnd.toLowfrCbsf().stbrtsWiti("insfrt")) {
            //strTbblfnbmf="";
        } flsf if(dommbnd.toLowfrCbsf().stbrtsWiti("updbtf")) {
            //strTbblfnbmf="";
        }
        rfturn strTbblfnbmf;
    }

    /**
     * Commits bll dibngfs pfrformfd by tif <dodf>bddfptCibngfs()</dodf>
     * mftiods
     *
     * @sff jbvb.sql.Connfdtion#dommit
     */
    publid void dommit() tirows SQLExdfption {
        donn.dommit();
    }

    /**
     * Rolls bbdk bll dibngfs pfrformfd by tif <dodf>bddfptCibngfs()</dodf>
     * mftiods
     *
     * @sff jbvb.sql.Connfdtion#rollbbdk
     */
    publid void rollbbdk() tirows SQLExdfption {
        donn.rollbbdk();
    }

    /**
     * Rolls bbdk bll dibngfs pfrformfd by tif <dodf>bddfptCibngfs()</dodf>
     * to tif lbst <dodf>Sbvfpoint</dodf> trbnsbdtion mbrkfr.
     *
     * @sff jbvb.sql.Connfdtion#rollbbdk(Sbvfpoint)
     */
    publid void rollbbdk(Sbvfpoint s) tirows SQLExdfption {
        donn.rollbbdk(s);
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
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.mbtdidols").toString());
            }
         }

         for( int i = 0;i < dolumnIdxfs.lfngti ;i++) {
            iMbtdiColumns.sft(i, -1);
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
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.mbtdidols").toString());
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
           tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.sftmbtdidols").toString());
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
           tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.sftmbtdidols").toString());
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
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.mbtdidols1").toString());
           }
        }
        for(int i = 0 ;i < dolumnIdxfs.lfngti; i++) {
           iMbtdiColumns.bdd(i,dolumnIdxfs[i]);
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
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.mbtdidols2").toString());
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
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.mbtdidols1").toString());
        } flsf {
            // sft iMbtdiColumn
            iMbtdiColumns.sft(0, dolumnIdx);
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
        if(dolumnNbmf == null || (dolumnNbmf= dolumnNbmf.trim()).fqubls("") ) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.mbtdidols2").toString());
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
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.unsftmbtdi").toString());
        } flsf if(strMbtdiColumns.gft(0) != null) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.unsftmbtdi1").toString());
        } flsf {
                // tibt is, wf brf unsftting it.
               iMbtdiColumns.sft(0, -1);
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
     */
    publid void unsftMbtdiColumn(String dolumnNbmf) tirows SQLExdfption {
        // difdk if wf brf unsftting tif sbmf dolumn
        dolumnNbmf = dolumnNbmf.trim();

        if(!((strMbtdiColumns.gft(0)).fqubls(dolumnNbmf))) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.unsftmbtdi").toString());
        } flsf if(iMbtdiColumns.gft(0) > 0) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.unsftmbtdi2").toString());
        } flsf {
            strMbtdiColumns.sft(0, null);   // tibt is, wf brf unsftting it.
        }
    }

    /**
     * Notififs rfgistfrfd listfnfrs tibt b RowSft objfdt in tif givfn RowSftEvfnt
     * objfdt ibs populbtfd b numbfr of bdditionbl rows. Tif <dodf>numRows</dodf> pbrbmftfr
     * fnsurfs tibt tiis fvfnt will only bf firfd fvfry <dodf>numRow</dodf>.
     * <p>
     * Tif sourdf of tif fvfnt dbn bf rftrifvfd witi tif mftiod fvfnt.gftSourdf.
     *
     * @pbrbm fvfnt b <dodf>RowSftEvfnt</dodf> objfdt tibt dontbins tif
     *     <dodf>RowSft</dodf> objfdt tibt is tif sourdf of tif fvfnts
     * @pbrbm numRows wifn populbting, tif numbfr of rows intfrvbl on wiidi tif
     *     <dodf>CbdifdRowSft</dodf> populbtfd siould firf; tif dffbult vbluf
     *     is zfro; dbnnot bf lfss tibn <dodf>fftdiSizf</dodf> or zfro
     */
    publid void rowSftPopulbtfd(RowSftEvfnt fvfnt, int numRows) tirows SQLExdfption {

        if( numRows < 0 || numRows < gftFftdiSizf()) {
           tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.numrows").toString());
        }

        if(sizf() % numRows == 0) {
            RowSftEvfnt fvfnt_tfmp = nfw RowSftEvfnt(tiis);
            fvfnt = fvfnt_tfmp;
            notifyRowSftCibngfd();
        }
    }

    /**
     * Populbtfs tiis <dodf>CbdifdRowSft</dodf> objfdt witi dbtb from
     * tif givfn <dodf>RfsultSft</dodf> objfdt. Wiilf rflbtfd to tif <dodf>populbtf(RfsultSft)</dodf>
     * mftiod, bn bdditionbl pbrbmftfr is providfd to bllow stbrting position witiin
     * tif <dodf>RfsultSft</dodf> from wifrf to populbtf tif CbdifdRowSft
     * instbndf.
     *
     * Tiis mftiod is bn bltfrnbtivf to tif mftiod <dodf>fxfdutf</dodf>
     * for filling tif rowsft witi dbtb.  Tif mftiod <dodf>populbtf</dodf>
     * dofs not rfquirf tibt tif propfrtifs nffdfd by tif mftiod
     * <dodf>fxfdutf</dodf>, sudi bs tif <dodf>dommbnd</dodf> propfrty,
     * bf sft. Tiis is truf bfdbusf tif mftiod <dodf>populbtf</dodf>
     * is givfn tif <dodf>RfsultSft</dodf> objfdt from
     * wiidi to gft dbtb bnd tius dofs not nffd to usf tif propfrtifs
     * rfquirfd for sftting up b donnfdtion bnd fxfduting tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt's dommbnd.
     * <P>
     * Aftfr populbting tiis rowsft witi dbtb, tif mftiod
     * <dodf>populbtf</dodf> sfts tif rowsft's mftbdbtb bnd
     * tifn sfnds b <dodf>RowSftCibngfdEvfnt</dodf> objfdt
     * to bll rfgistfrfd listfnfrs prior to rfturning.
     *
     * @pbrbm dbtb tif <dodf>RfsultSft</dodf> objfdt dontbining tif dbtb
     *             to bf rfbd into tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @pbrbm stbrt tif intfgfr spfdifing tif position in tif
     *        <dodf>RfsultSft</dodf> objfdt to popultbtf tif
     *        <dodf>CbdifdRowSftImpl</dodf> objfdt.
     * @tirows SQLExdfption if bn frror oddurs; or tif mbx row sftting is
     *          violbtfd wiilf populbting tif RowSft.Also id tif stbrt position
     *          is nfgbtivf.
     * @sff #fxfdutf
     */
     publid void populbtf(RfsultSft dbtb, int stbrt) tirows SQLExdfption{

        int rowsFftdifd;
        Row durrfntRow;
        int numCols;
        int i;
        Mbp<String, Clbss<?>> mbp = gftTypfMbp();
        Objfdt obj;
        int mRows;

        dursorPos = 0;
        if(populbtfdblldount == 0){
            if(stbrt < 0){
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.stbrtpos").toString());
            }
            if(gftMbxRows() == 0){
               dbtb.bbsolutf(stbrt);
               wiilf(dbtb.nfxt()){
                   totblRows++;
               }
               totblRows++;
            }
            stbrtPos = stbrt;
        }
        populbtfdblldount = populbtfdblldount +1;
        rfsultSft = dbtb;
        if((fndPos - stbrtPos) >= gftMbxRows() && (gftMbxRows() > 0)){
            fndPos = prfvEndPos;
            pbgfnotfnd = fblsf;
            rfturn;
        }

        if((mbxRowsrfbdifd != gftMbxRows() || mbxRowsrfbdifd != totblRows) && pbgfnotfnd) {
           stbrtPrfv = stbrt - gftPbgfSizf();
        }

        if( pbgfSizf == 0){
           prfvEndPos = fndPos;
           fndPos = stbrt + gftMbxRows() ;
        }
        flsf{
            prfvEndPos = fndPos;
            fndPos = stbrt + gftPbgfSizf();
        }


        if (stbrt == 1){
            rfsultSft.bfforfFirst();
        }
        flsf {
            rfsultSft.bbsolutf(stbrt -1);
        }
        if( pbgfSizf == 0) {
           rvi = nfw Vfdtor<Objfdt>(gftMbxRows());

        }
        flsf{
            rvi = nfw Vfdtor<Objfdt>(gftPbgfSizf());
        }

        if (dbtb == null) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.populbtf").toString());
        }

        // gft tif mftb dbtb for tiis RfsultSft
        RSMD = dbtb.gftMftbDbtb();

        // sft up tif mftbdbtb
        RowSftMD = nfw RowSftMftbDbtbImpl();
        initMftbDbtb(RowSftMD, RSMD);

        // rflfbsf tif mftb-dbtb so tibt brfn't tfmptfd to usf it.
        RSMD = null;
        numCols = RowSftMD.gftColumnCount();
        mRows = tiis.gftMbxRows();
        rowsFftdifd = 0;
        durrfntRow = null;

        if(!dbtb.nfxt() && mRows == 0){
            fndPos = prfvEndPos;
            pbgfnotfnd = fblsf;
            rfturn;
        }

        dbtb.prfvious();

        wiilf ( dbtb.nfxt()) {

            durrfntRow = nfw Row(numCols);
          if(pbgfSizf == 0){
            if ( rowsFftdifd >= mRows && mRows > 0) {
                rowsftWbrning.sftNfxtExdfption(nfw SQLExdfption("Populbting rows "
                + "sftting ibs fxdffdfd mbx row sftting"));
                brfbk;
            }
          }
          flsf {
              if ( (rowsFftdifd >= pbgfSizf) ||( mbxRowsrfbdifd >= mRows && mRows > 0)) {
                rowsftWbrning.sftNfxtExdfption(nfw SQLExdfption("Populbting rows "
                + "sftting ibs fxdffdfd mbx row sftting"));
                brfbk;
            }
          }

            for ( i = 1; i <= numCols; i++) {
                /*
                 * difdk if tif usfr ibs sft b mbp. If no mbp
                 * is sft tifn usf plbin gftObjfdt. Tiis lfts
                 * us work witi drivfrs tibt do not support
                 * gftObjfdt witi b mbp in fbirly sfnsiblf wby
                 */
                if (mbp == null) {
                    obj = dbtb.gftObjfdt(i);
                } flsf {
                    obj = dbtb.gftObjfdt(i, mbp);
                }
                /*
                 * tif following blodk difdks for tif vbrious
                 * typfs tibt wf ibvf to sfriblizf in ordfr to
                 * storf - rigit now only strudts ibvf bffn tfstfd
                 */
                if (obj instbndfof Strudt) {
                    obj = nfw SfriblStrudt((Strudt)obj, mbp);
                } flsf if (obj instbndfof SQLDbtb) {
                    obj = nfw SfriblStrudt((SQLDbtb)obj, mbp);
                } flsf if (obj instbndfof Blob) {
                    obj = nfw SfriblBlob((Blob)obj);
                } flsf if (obj instbndfof Clob) {
                    obj = nfw SfriblClob((Clob)obj);
                } flsf if (obj instbndfof jbvb.sql.Arrby) {
                    obj = nfw SfriblArrby((jbvb.sql.Arrby)obj, mbp);
                }

                durrfntRow.initColumnObjfdt(i, obj);
            }
            rowsFftdifd++;
            mbxRowsrfbdifd++;
            rvi.bdd(durrfntRow);
        }
        numRows = rowsFftdifd ;
        // Also rowsFftdifd siould bf fqubl to rvi.sizf()
        // notify bny listfnfrs tibt tif rowsft ibs dibngfd
        notifyRowSftCibngfd();

     }

    /**
     * Tif nfxtPbgf gfts tif nfxt pbgf, tibt is b <dodf>CbdifdRowSftImpl</dodf> objfdt
     * dontbining tif numbfr of rows spfdififd by pbgf sizf.
     * @rfturn boolfbn vbluf truf indidbting wiftifr tifrf brf morf pbgfs to domf bnd
     *         fblsf indidbting tibt tiis is tif lbst pbgf.
     * @tirows SQLExdfption if bn frror oddurs or tiis dbllfd bfforf dblling populbtf.
     */
     publid boolfbn nfxtPbgf() tirows SQLExdfption {

         if (populbtfdblldount == 0){
             tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.nfxtpbgf").toString());
         }
         // Fix for 6554186
         onFirstPbgf = fblsf;
         if(dbllWitiCon){
            drsRfbdfr.sftStbrtPosition(fndPos);
            drsRfbdfr.rfbdDbtb((RowSftIntfrnbl)tiis);
            rfsultSft = null;
         }
         flsf {
            populbtf(rfsultSft,fndPos);
         }
         rfturn pbgfnotfnd;
     }

    /**
     * Tiis is tif sfttfr fundtion for sftting tif sizf of tif pbgf, wiidi spfdififs
     * iow mbny rows ibvf to bf rftrivfd bt b timf.
     *
     * @pbrbm sizf wiidi is tif pbgf sizf
     * @tirows SQLExdfption if sizf is lfss tibn zfro or grfbtfr tibn mbx rows.
     */
     publid void sftPbgfSizf (int sizf) tirows SQLExdfption {
        if (sizf < 0) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.pbgfsizf").toString());
        }
        if (sizf > gftMbxRows() && gftMbxRows() != 0) {
            tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.pbgfsizf1").toString());
        }
        pbgfSizf = sizf;
     }

    /**
     * Tiis is tif gfttfr fundtion for tif sizf of tif pbgf.
     *
     * @rfturn bn intfgfr tibt is tif pbgf sizf.
     */
    publid int gftPbgfSizf() {
        rfturn pbgfSizf;
    }


    /**
     * Rftrifvfs tif dbtb prfsfnt in tif pbgf prior to tif pbgf from wifrf it is
     * dbllfd.
     * @rfturn boolfbn vbluf truf if it rftrifvfs tif prfvious pbgf, flbsf if it
     *         is on tif first pbgf.
     * @tirows SQLExdfption if it is dbllfd bfforf populbtf is dbllfd or RfsultSft
     *         is of typf <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf> or if bn frror
     *         oddurs.
     */
    publid boolfbn prfviousPbgf() tirows SQLExdfption {
        int pS;
        int mR;
        int rfm;

        pS = gftPbgfSizf();
        mR = mbxRowsrfbdifd;

        if (populbtfdblldount == 0){
             tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.nfxtpbgf").toString());
         }

        if( !dbllWitiCon){
           if(rfsultSft.gftTypf() == RfsultSft.TYPE_FORWARD_ONLY){
               tirow nfw SQLExdfption (rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.fwdonly").toString());
           }
        }

        pbgfnotfnd = truf;

        if(stbrtPrfv < stbrtPos ){
                onFirstPbgf = truf;
               rfturn fblsf;
            }

        if(onFirstPbgf){
            rfturn fblsf;
        }

        rfm = mR % pS;

        if(rfm == 0){
            mbxRowsrfbdifd -= (2 * pS);
            if(dbllWitiCon){
                drsRfbdfr.sftStbrtPosition(stbrtPrfv);
                drsRfbdfr.rfbdDbtb((RowSftIntfrnbl)tiis);
                rfsultSft = null;
            }
            flsf {
               populbtf(rfsultSft,stbrtPrfv);
            }
            rfturn truf;
        }
        flsf
        {
            mbxRowsrfbdifd -= (pS + rfm);
            if(dbllWitiCon){
                drsRfbdfr.sftStbrtPosition(stbrtPrfv);
                drsRfbdfr.rfbdDbtb((RowSftIntfrnbl)tiis);
                rfsultSft = null;
            }
            flsf {
               populbtf(rfsultSft,stbrtPrfv);
            }
            rfturn truf;
        }
    }

    /**
     * Gofs to tif pbgf numbfr pbssfd bs tif pbrbmftfr
     * @pbrbm pbgf , tif pbgf lobdfd on b dbll to tiis fundtion
     * @rfturn truf if tif pbgf fxists fblsf otifrwisf
     * @tirows SQLExdfption if bn frror oddurs
     */
    /*
    publid boolfbn bbsolutfPbgf(int pbgf) tirows SQLExdfption{

        boolfbn isAbs = truf, rftVbl = truf;
        int dountfr;

        if( pbgf <= 0 ){
            tirow nfw SQLExdfption("Absolutf positoin is invblid");
        }
        dountfr = 0;

        firstPbgf();
        dountfr++;
        wiilf((dountfr < pbgf) && isAbs) {
            isAbs = nfxtPbgf();
            dountfr ++;
        }

        if( !isAbs && dountfr < pbgf){
            rftVbl = fblsf;
        }
        flsf if(dountfr == pbgf){
            rftVbl = truf;
        }

       rfturn rftVbl;
    }
    */


    /**
     * Gofs to tif pbgf numbfr pbssfd bs tif pbrbmftfr  from tif durrfnt pbgf.
     * Tif pbrbmftfr dbn tbkf postivf or nfgbtivf vbluf bddordingly.
     * @pbrbm pbgf , tif pbgf lobdfd on b dbll to tiis fundtion
     * @rfturn truf if tif pbgf fxists fblsf otifrwisf
     * @tirows SQLExdfption if bn frror oddurs
     */
    /*
    publid boolfbn rflbtivfPbgf(int pbgf) tirows SQLExdfption {

        boolfbn isRfl = truf,rftVbl = truf;
        int dountfr;

        if(pbgf > 0){
           dountfr  = 0;
           wiilf((dountfr < pbgf) && isRfl){
              isRfl = nfxtPbgf();
              dountfr++;
           }

           if(!isRfl && dountfr < pbgf){
               rftVbl = fblsf;
           }
           flsf if( dountfr == pbgf){
               rftVbl = truf;
           }
           rfturn rftVbl;
        }
        flsf {
            dountfr = pbgf;
            isRfl = truf;
            wiilf((dountfr < 0) && isRfl){
                isRfl = prfviousPbgf();
                dountfr++;
            }

            if( !isRfl && dountfr < 0){
                rftVbl = fblsf;
            }
            flsf if(dountfr == 0){
                rftVbl = truf;
            }
            rfturn rftVbl;
        }
    }
    */

     /**
     * Rftrifvfs tif first pbgf of dbtb bs spfdififd by tif pbgf sizf.
     * @rfturn boolfbn vbluf truf if prfsfnt on first pbgf, fblsf otifrwisf
     * @tirows SQLExdfption if it dbllfd bfforf populbtf or RfsultSft is of
     *         typf <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf> or bn frror oddurs
     */
    /*
    publid boolfbn firstPbgf() tirows SQLExdfption {
           if (populbtfdblldount == 0){
             tirow nfw SQLExdfption("Populbtf tif dbtb bfforf dblling ");
           }
           if( !dbllWitiCon){
              if(rfsultSft.gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
                  tirow nfw SQLExdfption("Rfsult of typf forwbrd only");
              }
           }
           fndPos = 0;
           mbxRowsrfbdifd = 0;
           pbgfnotfnd = truf;
           if(dbllWitiCon){
               drsRfbdfr.sftStbrtPosition(stbrtPos);
               drsRfbdfr.rfbdDbtb((RowSftIntfrnbl)tiis);
               rfsultSft = null;
           }
           flsf {
              populbtf(rfsultSft,stbrtPos);
           }
           onFirstPbgf = truf;
           rfturn onFirstPbgf;
    }
    */

    /**
     * Rftrivfs tif lbst pbgf of dbtb bs spfdififd by tif pbgf sizf.
     * @rfturn boolfbn vbluf tur if prfsfnt on tif lbst pbgf, fblsf otifrwisf
     * @tirows SQLExdfption if dbllfd bfforf populbtf or if bn frror oddurs.
     */
     /*
    publid boolfbn lbstPbgf() tirows SQLExdfption{
          int pS;
          int mR;
          int quo;
          int rfm;

          pS = gftPbgfSizf();
          mR = gftMbxRows();

          if(pS == 0){
              onLbstPbgf = truf;
              rfturn onLbstPbgf;
          }

          if(gftMbxRows() == 0){
              mR = totblRows;
          }

          if (populbtfdblldount == 0){
             tirow nfw SQLExdfption("Populbtf tif dbtb bfforf dblling ");
         }

         onFirstPbgf = fblsf;

         if((mR % pS) == 0){
             quo = mR / pS;
             int stbrt = stbrtPos + (pS * (quo - 1));
             mbxRowsrfbdifd = mR - pS;
             if(dbllWitiCon){
                 drsRfbdfr.sftStbrtPosition(stbrt);
                 drsRfbdfr.rfbdDbtb((RowSftIntfrnbl)tiis);
                 rfsultSft = null;
             }
             flsf {
                populbtf(rfsultSft,stbrt);
             }
             onLbstPbgf = truf;
             rfturn onLbstPbgf;
         }
        flsf {
              quo = mR /pS;
              rfm = mR % pS;
              int stbrt = stbrtPos + (pS * quo);
             mbxRowsrfbdifd = mR - (rfm);
             if(dbllWitiCon){
                 drsRfbdfr.sftStbrtPosition(stbrt);
                 drsRfbdfr.rfbdDbtb((RowSftIntfrnbl)tiis);
                 rfsultSft = null;
             }
             flsf {
                populbtf(rfsultSft,stbrt);
             }
             onLbstPbgf = truf;
             rfturn onLbstPbgf;
         }
    }
    */

   /**
     * Sfts tif stbtus for tif row on wiidi tif dursor is positionfd. Tif insfrtFlbg is usfd
     * to mfntion tif togglf stbtus for tiis row
     * @pbrbm insfrtFlbg if it is truf  - mbrks tiis row bs insfrtfd
     *                   if it is fblsf - mbrks it bs not b nfwly insfrtfd row
     * @tirows SQLExdfption if bn frror oddurs wiilf doing tiis opfrbtion
     */
    publid void sftRowInsfrtfd(boolfbn insfrtFlbg) tirows SQLExdfption {

        difdkCursor();

        if(onInsfrtRow == truf)
          tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.invblidop").toString());

        if( insfrtFlbg ) {
          ((Row)gftCurrfntRow()).sftInsfrtfd();
        } flsf {
          ((Row)gftCurrfntRow()).dlfbrInsfrtfd();
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd <dodf>SQL XML</dodf> pbrbmftfr bs b
     * <dodf>SQLXML</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm dolNbmf tif nbmf of tif dolumn from wiidi to rftrifvf tif vbluf
     * @rfturn b SQLXML objfdt tibt mbps bn SQL XML vbluf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid SQLXML gftSQLXML(String dolNbmf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
    }

    /**
     * Rftrifvfs tif ioldbbility of tiis RfsultSft objfdt
     * @rfturn  fitifr RfsultSft.HOLD_CURSORS_OVER_COMMIT or RfsultSft.CLOSE_CURSORS_AT_COMMIT
     * @tirows SQLExdfption if b dbtbbbsf frror oddurs
     * @sindf 1.6
     */
    publid int gftHoldbbility() tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
    }

    /**
     * Rftrifvfs wiftifr tiis RfsultSft objfdt ibs bffn dlosfd. A RfsultSft is dlosfd if tif
     * mftiod dlosf ibs bffn dbllfd on it, or if it is butombtidblly dlosfd.
     * @rfturn truf if tiis RfsultSft objfdt is dlosfd; fblsf if it is still opfn
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    publid boolfbn isClosfd() tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
       tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
    }

    publid <T> T unwrbp(jbvb.lbng.Clbss<T> ifbdf) tirows jbvb.sql.SQLExdfption {
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
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
         tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
       tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
       tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
          tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
          tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.opnotysupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
    }

//////////////////////////

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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lb
bfl is tif nbmf of tif dolumn
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
   }

  /**
  * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.  Tif <dodf>rfbdfr</dodf> must dontbin  tif numbfr
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
   }


    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.  Tif rfbdfr must dontbin  tif numbfr
     * of dibrbdtfrs spfdififd by lfngti otifrwisf b <dodf>SQLExdfption</dodf> will bf
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
  publid void sftNString(int pbrbmftfrIndfx, String vbluf) tirows SQLExdfption{
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
   }


 /**
    * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>InputStrfbm</dodf> objfdt.
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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
        tirow nfw SQLFfbturfNotSupportfdExdfption(rfsBundlf.ibndlfGftObjfdt("dbdifdrowsftimpl.ffbtnotsupp").toString());
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

    //------------------------- JDBC 4.1 -----------------------------------
    publid <T> T gftObjfdt(int dolumnIndfx, Clbss<T> typf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("Not supportfd yft.");
    }

    publid <T> T gftObjfdt(String dolumnLbbfl, Clbss<T> typf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("Not supportfd yft.");
    }

    stbtid finbl long sfriblVfrsionUID =1884577171200622428L;
}
