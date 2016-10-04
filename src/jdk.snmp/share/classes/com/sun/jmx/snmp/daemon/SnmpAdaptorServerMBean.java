/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp.dbfmon;

// jbvb import
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpPduFbdtory;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpVbrBindList;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpTimftidks;
import dom.sun.jmx.snmp.SnmpIpAddrfss;
import dom.sun.jmx.snmp.SnmpPduPbdkft;
import dom.sun.jmx.snmp.InftAddrfssAdl;
import dom.sun.jmx.snmp.SnmpPffr;

// SNMP Runtimf imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt;
import dom.sun.jmx.snmp.bgfnt.SnmpMibHbndlfr;
import dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory;

/**
 * Exposfs tif rfmotf mbnbgfmfnt intfrfbdf of tif {@link SnmpAdbptorSfrvfr} MBfbn.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid intfrfbdf SnmpAdbptorSfrvfrMBfbn fxtfnds CommunidbtorSfrvfrMBfbn {

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Rfturns tif Ip bddrfss bbsfd ACL usfd by tiis SNMP protodol bdbptor.
     * @rfturn Tif <CODE>InftAddrfssAdl</CODE> implfmfntbtion.
     *
     * @sindf 1.5
     */
    publid InftAddrfssAdl gftInftAddrfssAdl();
    /**
     * Rfturns tif port usfd by tiis SNMP protodol bdbptor for sfnding trbps.
     * By dffbult, port 162 is usfd.
     *
     * @rfturn Tif port numbfr for sfnding SNMP trbps.
     */
    publid Intfgfr gftTrbpPort();

    /**
     * Sfts tif port usfd by tiis SNMP protodol bdbptor for sfnding trbps.
     *
     * @pbrbm port Tif port numbfr for sfnding SNMP trbps.
     */
    publid void sftTrbpPort(Intfgfr port);

    /**
     * Rfturns tif port usfd by tiis SNMP protodol bdbptor for sfnding inform rfqufsts.
     * By dffbult, port 162 is usfd.
     *
     * @rfturn Tif port numbfr for sfnding SNMP inform rfqufsts.
     */
    publid int gftInformPort();

    /**
     * Sfts tif port usfd by tiis SNMP protodol bdbptor for sfnding inform rfqufsts.
     *
     * @pbrbm port Tif port numbfr for sfnding SNMP inform rfqufsts.
     */
    publid void sftInformPort(int port);

    /**
     * Gfts tif numbfr of mbnbgfrs tibt ibvf bffn prodfssfd by tiis SNMP protodol bdbptor
     * sindf its drfbtion.
     *
     * @rfturn Tif numbfr of mbnbgfrs ibndlfd by tiis SNMP protodol bdbptor
     * sindf its drfbtion. Tiis dountfr is not rfsft by tif <CODE>stop</CODE> mftiod.
     */
    publid int gftSfrvfdClifntCount();

    /**
     * Gfts tif numbfr of mbnbgfrs durrfntly bfing prodfssfd by tiis
     * SNMP protodol bdbptor.
     *
     * @rfturn Tif numbfr of mbnbgfrs durrfntly bfing prodfssfd by tiis
     * SNMP protodol bdbptor.
     */
    publid int gftAdtivfClifntCount();

    /**
     * Gfts tif mbximum numbfr of mbnbgfrs tibt tiis SNMP protodol bdbptor dbn
     * prodfss dondurrfntly.
     *
     * @rfturn Tif mbximum numbfr of mbnbgfrs tibt tiis SNMP protodol bdbptor dbn
     * prodfss dondurrfntly.
     */
    publid int gftMbxAdtivfClifntCount();

    /**
     * Sfts tif mbximum numbfr of mbnbgfrs tiis SNMP protodol bdbptor dbn
     * prodfss dondurrfntly.
     *
     * @pbrbm d Tif numbfr of mbnbgfrs.
     *
     * @fxdfption jbvb.lbng.IllfgblStbtfExdfption Tiis mftiod ibs bffn invokfd
     * wiilf tif dommunidbtor wbs <CODE>ONLINE</CODE> or <CODE>STARTING</CODE>.
     */
    publid void sftMbxAdtivfClifntCount(int d) tirows jbvb.lbng.IllfgblStbtfExdfption;

    /**
     * Rfturns tif protodol of tiis SNMP protodol bdbptor.
     *
     * @rfturn Tif string "snmp".
     */
    @Ovfrridf
    publid String gftProtodol();

    /**
     * Rfturns tif bufffr sizf of tiis SNMP protodol bdbptor.
     * By dffbult, bufffr sizf 1024 is usfd.
     *
     * @rfturn Tif bufffr sizf.
     */
    publid Intfgfr gftBufffrSizf();

    /**
     * Sfts tif bufffr sizf of tiis SNMP protodol bdbptor.
     *
     * @pbrbm s Tif bufffr sizf.
     *
     * @fxdfption jbvb.lbng.IllfgblStbtfExdfption Tiis mftiod ibs bffn invokfd
     * wiilf tif dommunidbtor wbs <CODE>ONLINE</CODE> or <CODE>STARTING</CODE>.
     */
    publid void sftBufffrSizf(Intfgfr s) tirows jbvb.lbng.IllfgblStbtfExdfption;

    /**
     * Gfts tif numbfr of timfs to try sfnding bn inform rfqufst bfforf giving up.
     * @rfturn Tif mbximun numbfr of trifs.
     */
    publid int gftMbxTrifs();

    /**
     * Cibngfs tif mbximun numbfr of timfs to try sfnding bn inform rfqufst bfforf giving up.
     * @pbrbm nfwMbxTrifs Tif mbximun numbfr of trifs.
     */
    publid void sftMbxTrifs(int nfwMbxTrifs);

    /**
     * Gfts tif timfout to wbit for bn inform rfsponsf from tif mbnbgfr.
     * @rfturn Tif vbluf of tif timfout propfrty.
     */
    publid int gftTimfout();

    /**
     * Cibngfs tif timfout to wbit for bn inform rfsponsf from tif mbnbgfr.
     * @pbrbm nfwTimfout Tif timfout (in millisfdonds).
     */
    publid void sftTimfout(int nfwTimfout);

    /**
     * Rfturns tif mfssbgf fbdtory of tiis SNMP protodol bdbptor.
     *
     * @rfturn Tif fbdtory objfdt.
     */
    publid SnmpPduFbdtory gftPduFbdtory();

    /**
     * Sfts tif mfssbgf fbdtory of tiis SNMP protodol bdbptor.
     *
     * @pbrbm fbdtory Tif fbdtory objfdt (null mfbns tif dffbult fbdtory).
     */
    publid void sftPduFbdtory(SnmpPduFbdtory fbdtory);


    /**
     * Sft tif usfr-dbtb fbdtory of tiis SNMP protodol bdbptor.
     *
     * @pbrbm fbdtory Tif fbdtory objfdt (null mfbns no fbdtory).
     * @sff dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory
     */
    publid void sftUsfrDbtbFbdtory(SnmpUsfrDbtbFbdtory fbdtory);

    /**
     * Gft tif usfr-dbtb fbdtory bssodibtfd witi tiis SNMP protodol bdbptor.
     *
     * @rfturn Tif fbdtory objfdt (null mfbns no fbdtory).
     * @sff dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory
     */
    publid SnmpUsfrDbtbFbdtory gftUsfrDbtbFbdtory();

    /**
     * Rfturns <CODE>truf</CODE> if butifntidbtion trbps brf fnbblfd.
     * <P>
     * Wifn tiis ffbturf is fnbblfd, tif SNMP protodol bdbptor sfnds
     * bn <CODE>butifntidbtionFbilurf</CODE> trbp fbdi timf bn butifntidbtion fbils.
     * <P>
     * Tif dffbult bfibviour is to sfnd butifntidbtion trbps.
     *
     * @rfturn <CODE>truf</CODE> if butifntidbtion trbps brf fnbblfd, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn gftAutiTrbpEnbblfd();

    /**
     * Sfts tif flbg indidbting if trbps nffd to bf sfnt in dbsf of butifntidbtion fbilurf.
     *
     * @pbrbm fnbblfd Flbg indidbting if trbps nffd to bf sfnt.
     */
    publid void sftAutiTrbpEnbblfd(boolfbn fnbblfd);

    /**
     * Rfturns <dodf>truf</dodf> if tiis SNMP protodol bdbptor sfnds b rfsponsf in dbsf
     * of butifntidbtion fbilurf.
     * <P>
     * Wifn tiis ffbturf is fnbblfd, tif SNMP protodol bdbptor sfnds b rfsponsf witi <CODE>noSudiNbmf</CODE>
     * or <CODE>rfbdOnly</CODE> wifn tif butifntidbtion fbilfd. If tif flbg is disbblfd, tif
     * SNMP protodol bdbptor trbsifs tif PDU silfntly.
     * <P>
     * Tif dffbult bfibvior is to sfnd rfsponsfs.
     *
     * @rfturn <dodf>truf</dodf> if rfsponsfs brf sfnt.
     */
    publid boolfbn gftAutiRfspEnbblfd();

    /**
     * Sfts tif flbg indidbting if rfsponsfs nffd to bf sfnt in dbsf of butifntidbtion fbilurf.
     *
     * @pbrbm fnbblfd Flbg indidbting if rfsponsfs nffd to bf sfnt.
     */
    publid void sftAutiRfspEnbblfd(boolfbn fnbblfd);

    /**
     * Rfturns tif fntfrprisf OID. It is usfd by {@link #snmpV1Trbp snmpV1Trbp} to fill
     * tif 'fntfrprisf' fifld of tif trbp rfqufst.
     *
     * @rfturn Tif OID in string formbt "x.x.x.x".
     */
    publid String gftEntfrprisfOid();

    /**
     * Sfts tif fntfrprisf OID.
     *
     * @pbrbm oid Tif OID in string formbt "x.x.x.x".
     *
     * @fxdfption IllfgblArgumfntExdfption Tif string formbt is indorrfdt
     */
    publid void sftEntfrprisfOid(String oid) tirows IllfgblArgumfntExdfption;

    /**
     * Rfturns tif nbmfs of tif MIBs bvbilbblf in tiis SNMP protodol bdbptor.
     *
     * @rfturn An brrby of MIB nbmfs.
     */
    publid String[] gftMibs();

    // GETTERS FOR SNMP GROUP (MIBII)
    //-------------------------------

    /**
     * Rfturns tif <CODE>snmpOutTrbps</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutTrbps</CODE> vbluf.
     */
    publid Long gftSnmpOutTrbps();

    /**
     * Rfturns tif <CODE>snmpOutGftRfsponsfs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutGftRfsponsfs</CODE> vbluf.
     */
    publid Long gftSnmpOutGftRfsponsfs();

    /**
     * Rfturns tif <CODE>snmpOutGfnErrs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutGfnErrs</CODE> vbluf.
     */
    publid Long gftSnmpOutGfnErrs();

    /**
     * Rfturns tif <CODE>snmpOutBbdVblufs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutBbdVblufs</CODE> vbluf.
     */
    publid Long gftSnmpOutBbdVblufs();

    /**
     * Rfturns tif <CODE>snmpOutNoSudiNbmfs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutNoSudiNbmfs</CODE> vbluf.
     */
    publid Long gftSnmpOutNoSudiNbmfs();

    /**
     * Rfturns tif <CODE>snmpOutTooBigs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutTooBigs</CODE> vbluf.
     */
    publid Long gftSnmpOutTooBigs();

    /**
     * Rfturns tif <CODE>snmpInASNPbrsfErrs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInASNPbrsfErrs</CODE> vbluf.
     */
    publid Long gftSnmpInASNPbrsfErrs();

    /**
     * Rfturns tif <CODE>snmpInBbdCommunityUsfs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInBbdCommunityUsfs</CODE> vbluf.
     */
    publid Long gftSnmpInBbdCommunityUsfs();

    /**
     * Rfturns tif <CODE>snmpInBbdCommunityNbmfs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInBbdCommunityNbmfs</CODE> vbluf.
     */
    publid Long gftSnmpInBbdCommunityNbmfs();

    /**
     * Rfturns tif <CODE>snmpInBbdVfrsions</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInBbdVfrsions</CODE> vbluf.
     */
    publid Long gftSnmpInBbdVfrsions();

    /**
     * Rfturns tif <CODE>snmpOutPkts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutPkts</CODE> vbluf.
     */
    publid Long gftSnmpOutPkts();

    /**
     * Rfturns tif <CODE>snmpInPkts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInPkts</CODE> vbluf.
     */
    publid Long gftSnmpInPkts();

    /**
     * Rfturns tif <CODE>snmpInGftRfqufsts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInGftRfqufsts</CODE> vbluf.
     */
    publid Long gftSnmpInGftRfqufsts();

    /**
     * Rfturns tif <CODE>snmpInGftNfxts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInGftNfxts</CODE> vbluf.
     */
    publid Long gftSnmpInGftNfxts();

    /**
     * Rfturns tif <CODE>snmpInSftRfqufsts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInSftRfqufsts</CODE> vbluf.
     */
    publid Long gftSnmpInSftRfqufsts();

    /**
     * Rfturns tif <CODE>snmpInTotblSftVbrs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInTotblSftVbrs</CODE> vbluf.
     */
    publid Long gftSnmpInTotblSftVbrs();

    /**
     * Rfturns tif <CODE>snmpInTotblRfqVbrs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInTotblRfqVbrs</CODE> vbluf.
     */
    publid Long gftSnmpInTotblRfqVbrs();

    /**
     * Rfturns tif <CODE>snmpSilfntDrops</CODE> vbluf dffinfd in rfd 1907 NMPv2-MIB .
     *
     * @rfturn Tif <CODE>snmpSilfntDrops</CODE> vbluf.
     *
     * @sindf 1.5
     */
    publid Long gftSnmpSilfntDrops();

    /**
     * Rfturns tif <CODE>snmpProxyDrops</CODE> vbluf dffinfd in rfd 1907 NMPv2-MIB .
     *
     * @rfturn Tif <CODE>snmpProxyDrops</CODE> vbluf.
     *
     * @sindf 1.5
     */
    publid Long gftSnmpProxyDrops();

    // PUBLIC METHODS
    //---------------

    /**
     * Adds b nfw MIB in tif SNMP MIB ibndlfr.
     * Tiis mftiod is dbllfd butombtidblly by {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptor(SnmpMibHbndlfr)}
     * bnd {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptorNbmf(ObjfdtNbmf)}
     * bnd siould not bf dbllfd dirfdtly.
     *
     * @pbrbm mib Tif MIB to bdd.
     *
     * @rfturn A rfffrfndf to tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     */
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib) tirows IllfgblArgumfntExdfption;

    /**
     * Adds b nfw MIB in tif SNMP MIB ibndlfr.
     *
     * @pbrbm mib Tif MIB to bdd.
     * @pbrbm oids Tif sft of OIDs tiis bgfnt implfmfnts.
     *
     * @rfturn A rfffrfndf to tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     *
     * @sindf 1.5
     */
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib, SnmpOid[] oids) tirows IllfgblArgumfntExdfption;

    /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     * Tiis mftiod is dbllfd butombtidblly by {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptor(SnmpMibHbndlfr)}
     * bnd {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptorNbmf(ObjfdtNbmf)}
     * bnd siould not bf dbllfd dirfdtly.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     *
     * @rfturn <dodf>truf</dodf> if tif spfdififd <CODE>mib</CODE> wbs b MIB indludfd in tif SNMP MIB ibndlfr,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib);

    /**
     * Sfnds b trbp using SNMP V1 trbp formbt.
     * <BR>Tif trbp is sfnt to fbdi dfstinbtion dffinfd in tif ACL filf (if bvbilbblf).
     * If no ACL filf or no dfstinbtions brf bvbilbblf, tif trbp is sfnt to tif lodbl iost.
     *
     * @pbrbm gfnfrid Tif gfnfrid numbfr of tif trbp.
     * @pbrbm spfdifid Tif spfdifid numbfr of tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     */
    publid void snmpV1Trbp(int gfnfrid, int spfdifid, SnmpVbrBindList vbrBindList) tirows IOExdfption, SnmpStbtusExdfption;


    /**
     * Sfnds b trbp using SNMP V1 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>InftAddrfss</CODE> dfstinbtion
     * using tif spfdififd dommunity string (bnd tif ACL filf is not usfd).
     *
     * @pbrbm bddrfss Tif <CODE>InftAddrfss</CODE> dfstinbtion of tif trbp.
     * @pbrbm ds Tif dommunity string to bf usfd for tif trbp.
     * @pbrbm gfnfrid Tif gfnfrid numbfr of tif trbp.
     * @pbrbm spfdifid Tif spfdifid numbfr of tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     */
    publid void snmpV1Trbp(InftAddrfss bddrfss, String ds, int gfnfrid, int spfdifid, SnmpVbrBindList vbrBindList)
        tirows IOExdfption, SnmpStbtusExdfption;


    /**
     * Sfnds b trbp using SNMP V1 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>SnmpPffr</CODE> dfstinbtion.
     * Tif dommunity string usfd is tif onf lodbtfd in tif <CODE>SnmpPffr</CODE> pbrbmftfrs (<CODE>SnmpPbrbmftfrs.gftRdCommunity() </CODE>).
     *
     * @pbrbm pffr Tif <CODE>SnmpPffr</CODE> dfstinbtion of tif trbp.
     * @pbrbm bgfntAddr Tif bgfnt bddrfss to bf usfd for tif trbp.
     * @pbrbm fntfrpOid Tif fntfrprisf OID to bf usfd for tif trbp.
     * @pbrbm gfnfrid Tif gfnfrid numbfr of tif trbp.
     * @pbrbm spfdifid Tif spfdifid numbfr of tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     * @pbrbm timf Tif timf stbmp (ovfrwritf tif durrfnt timf).
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    publid void snmpV1Trbp(SnmpPffr pffr,
                           SnmpIpAddrfss bgfntAddr,
                           SnmpOid fntfrpOid,
                           int gfnfrid,
                           int spfdifid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimftidks timf) tirows IOExdfption, SnmpStbtusExdfption;

    /**
     * Sfnds b trbp using SNMP V2 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>SnmpPffr</CODE> dfstinbtion.
     * <BR>Tif dommunity string usfd is tif onf lodbtfd in tif <CODE>SnmpPffr</CODE> pbrbmftfrs (<CODE>SnmpPbrbmftfrs.gftRdCommunity() </CODE>).
     * <BR>Tif vbribblf list indludfd in tif outgoing trbp is domposfd of tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi tif vbluf spfdififd by <CODE>timf</CODE>
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by <CODE>trbpOid</CODE>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd <CODE>vbrBindList</CODE>
     * </UL>
     *
     * @pbrbm pffr Tif <CODE>SnmpPffr</CODE> dfstinbtion of tif trbp.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     * @pbrbm timf Tif timf stbmp (ovfrwritf tif durrfnt timf).
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    publid void snmpV2Trbp(SnmpPffr pffr,
                           SnmpOid trbpOid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimftidks timf) tirows IOExdfption, SnmpStbtusExdfption;

    /**
     * Sfnds b trbp using SNMP V2 trbp formbt.
     * <BR>Tif trbp is sfnt to fbdi dfstinbtion dffinfd in tif ACL filf (if bvbilbblf).
     * If no ACL filf or no dfstinbtions brf bvbilbblf, tif trbp is sfnt to tif lodbl iost.
     * <BR>Tif vbribblf list indludfd in tif outgoing trbp is domposfd of tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by <CODE>trbpOid</CODE>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd <CODE>vbrBindList</CODE>
     * </UL>
     *
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     */
    publid void snmpV2Trbp(SnmpOid trbpOid, SnmpVbrBindList vbrBindList) tirows IOExdfption, SnmpStbtusExdfption;


    /**
     * Sfnds b trbp using SNMP V2 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>InftAddrfss</CODE> dfstinbtion
     * using tif spfdififd dommunity string (bnd tif ACL filf is not usfd).
     * <BR>Tif vbribblf list indludfd in tif outgoing trbp is domposfd of tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by <CODE>trbpOid</CODE>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd <CODE>vbrBindList</CODE>
     * </UL>
     *
     * @pbrbm bddrfss Tif <CODE>InftAddrfss</CODE> dfstinbtion of tif trbp.
     * @pbrbm ds Tif dommunity string to bf usfd for tif trbp.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     */
    publid void snmpV2Trbp(InftAddrfss bddrfss, String ds, SnmpOid trbpOid, SnmpVbrBindList vbrBindList)
        tirows IOExdfption, SnmpStbtusExdfption;

    /**
     * Sfnd tif spfdififd trbp PDU to tif pbssfd <CODE>InftAddrfss</CODE>.
     * @pbrbm bddrfss Tif dfstinbtion bddrfss.
     * @pbrbm pdu Tif pdu to sfnd.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    publid void snmpPduTrbp(InftAddrfss bddrfss, SnmpPduPbdkft pdu)
        tirows IOExdfption, SnmpStbtusExdfption;
    /**
     * Sfnd tif spfdififd trbp PDU to tif pbssfd <CODE>SnmpPffr</CODE>.
     * @pbrbm pffr Tif dfstinbtion pffr. Tif Rfbd dommunity string is usfd of <CODE>SnmpPbrbmftfrs</CODE> is usfd bs tif trbp dommunity string.
     * @pbrbm pdu Tif pdu to sfnd.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     * @sindf 1.5
     */
    publid void snmpPduTrbp(SnmpPffr pffr,
                            SnmpPduPbdkft pdu)
        tirows IOExdfption, SnmpStbtusExdfption;

    /**
     * Sfnds bn inform using SNMP V2 inform rfqufst formbt.
     * <BR>Tif inform rfqufst is sfnt to fbdi dfstinbtion dffinfd in tif ACL filf (if bvbilbblf).
     * If no ACL filf or no dfstinbtions brf bvbilbblf, tif inform rfqufst is sfnt to tif lodbl iost.
     * <BR>Tif vbribblf list indludfd in tif outgoing inform rfqufst is domposfd of tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by <CODE>trbpOid</CODE>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd <CODE>vbrBindList</CODE>
     * </UL>
     * To sfnd bn inform rfqufst, tif SNMP bdbptor sfrvfr must bf bdtivf.
     *
     * @pbrbm db Tif dbllbbdk tibt is invokfd wifn b rfqufst is domplftf.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @rfturn A vfdtor of {@link dom.sun.jmx.snmp.dbfmon.SnmpInformRfqufst} objfdts.
     * <P>If tifrf is no dfstinbtion iost for tiis inform rfqufst, tif rfturnfd vfdtor will bf fmpty.
     *
     * @fxdfption IllfgblStbtfExdfption  Tiis mftiod ibs bffn invokfd wiilf tif SNMP bdbptor sfrvfr wbs not bdtivf.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif inform rfqufst.
     * @fxdfption SnmpStbtusExdfption If tif inform rfqufst fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     */
    publid Vfdtor<?> snmpInformRfqufst(SnmpInformHbndlfr db, SnmpOid trbpOid,
            SnmpVbrBindList vbrBindList)
        tirows IllfgblStbtfExdfption, IOExdfption, SnmpStbtusExdfption;

    /**
     * Sfnds bn inform using SNMP V2 inform rfqufst formbt.
     * <BR>Tif inform is sfnt to tif spfdififd <CODE>InftAddrfss</CODE> dfstinbtion
     * using tif spfdififd dommunity string.
     * <BR>Tif vbribblf list indludfd in tif outgoing inform rfqufst is domposfd of tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by <CODE>trbpOid</CODE>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd <CODE>vbrBindList</CODE>
     * </UL>
     * To sfnd bn inform rfqufst, tif SNMP bdbptor sfrvfr must bf bdtivf.
     *
     * @pbrbm bddrfss Tif <CODE>InftAddrfss</CODE> dfstinbtion for tiis inform rfqufst.
     * @pbrbm ds Tif dommunity string to bf usfd for tif inform rfqufst.
     * @pbrbm db Tif dbllbbdk tibt is invokfd wifn b rfqufst is domplftf.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @rfturn Tif inform rfqufst objfdt.
     *
     * @fxdfption IllfgblStbtfExdfption  Tiis mftiod ibs bffn invokfd wiilf tif SNMP bdbptor sfrvfr wbs not bdtivf.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif inform rfqufst.
     * @fxdfption SnmpStbtusExdfption If tif inform rfqufst fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     */
    publid SnmpInformRfqufst snmpInformRfqufst(InftAddrfss bddrfss, String ds, SnmpInformHbndlfr db,
                                               SnmpOid trbpOid, SnmpVbrBindList vbrBindList)
        tirows IllfgblStbtfExdfption, IOExdfption, SnmpStbtusExdfption;


    /**
     * Sfnds bn inform using SNMP V2 inform rfqufst formbt.
     * <BR>Tif inform is sfnt to tif spfdififd <CODE>SnmpPffr</CODE> dfstinbtion.
     * <BR> Tif dommunity string usfd is tif onf lodbtfd in tif <CODE>SnmpPffr</CODE> pbrbmftfrs (<CODE>SnmpPbrbmftfrs.gftInformCommunity() </CODE>).
     * <BR>Tif vbribblf list indludfd in tif outgoing inform is domposfd of tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by <CODE>trbpOid</CODE>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd <CODE>vbrBindList</CODE>
     * </UL>
     * To sfnd bn inform rfqufst, tif SNMP bdbptor sfrvfr must bf bdtivf.
     *
     * @pbrbm pffr Tif <CODE>SnmpPffr</CODE> dfstinbtion for tiis inform rfqufst.
     * @pbrbm db Tif dbllbbdk tibt is invokfd wifn b rfqufst is domplftf.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @rfturn Tif inform rfqufst objfdt.
     *
     * @fxdfption IllfgblStbtfExdfption  Tiis mftiod ibs bffn invokfd wiilf tif SNMP bdbptor sfrvfr wbs not bdtivf.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif inform rfqufst.
     * @fxdfption SnmpStbtusExdfption If tif inform rfqufst fxdffds tif limit dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    publid SnmpInformRfqufst snmpInformRfqufst(SnmpPffr pffr,
                                               SnmpInformHbndlfr db,
                                               SnmpOid trbpOid,
                                               SnmpVbrBindList vbrBindList) tirows IllfgblStbtfExdfption, IOExdfption, SnmpStbtusExdfption;
}
