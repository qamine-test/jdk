/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


// jbvb imports
//
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.logging.Lfvfl;
import jbvb.nft.DbtbgrbmSodkft;
import jbvb.nft.DbtbgrbmPbdkft;
import jbvb.nft.InftAddrfss;
import jbvb.nft.SodkftExdfption;
import jbvb.nft.UnknownHostExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.IntfrruptfdIOExdfption;


// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;
import dom.sun.jmx.snmp.SnmpIpAddrfss;
import dom.sun.jmx.snmp.SnmpMfssbgf;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpPduFbdtory;
import dom.sun.jmx.snmp.SnmpPduPbdkft;
import dom.sun.jmx.snmp.SnmpPduRfqufst;
import dom.sun.jmx.snmp.SnmpPduTrbp;
import dom.sun.jmx.snmp.SnmpTimftidks;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpVbrBindList;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.InftAddrfssAdl;
import dom.sun.jmx.snmp.SnmpPffr;
import dom.sun.jmx.snmp.SnmpPbrbmftfrs;
// SNMP Runtimf imports
//
import dom.sun.jmx.snmp.SnmpPduFbdtoryBER;
import dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt;
import dom.sun.jmx.snmp.bgfnt.SnmpMibHbndlfr;
import dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory;
import dom.sun.jmx.snmp.bgfnt.SnmpErrorHbndlfrAgfnt;

import dom.sun.jmx.snmp.IPAdl.SnmpAdl;

import dom.sun.jmx.snmp.tbsks.TirfbdSfrvidf;

/**
 * Implfmfnts bn bdbptor on top of tif SNMP protodol.
 * <P>
 * Wifn tiis SNMP protodol bdbptor is stbrtfd it drfbtfs b dbtbgrbm sodkft
 * bnd is bblf to rfdfivf rfqufsts bnd sfnd trbps or inform rfqufsts.
 * Wifn it is stoppfd, tif sodkft is dlosfd bnd nfitifr rfqufsts
 * bnd nor trbps/inform rfqufst brf prodfssfd.
 * <P>
 * Tif dffbult port numbfr of tif sodkft is 161. Tiis dffbult vbluf dbn bf
 * dibngfd by spfdifying b port numbfr:
 * <UL>
 * <LI>in tif objfdt donstrudtor</LI>
 * <LI>using tif {@link dom.sun.jmx.snmp.dbfmon.CommunidbtorSfrvfr#sftPort
 *     sftPort} mftiod bfforf stbrting tif bdbptor</LI>
 * </UL>
 * Tif dffbult objfdt nbmf is dffinfd by {@link
 * dom.sun.jmx.snmp.SfrvidfNbmf#DOMAIN dom.sun.jmx.snmp.SfrvidfNbmf.DOMAIN}
 * bnd {@link dom.sun.jmx.snmp.SfrvidfNbmf#SNMP_ADAPTOR_SERVER
 * dom.sun.jmx.snmp.SfrvidfNbmf.SNMP_ADAPTOR_SERVER}.
 * <P>
 * Tif SNMP protodol bdbptor supports vfrsions 1 bnd 2 of tif SNMP protodol
 * in b stbtflfss wby: wifn it rfdfivfs b v1 rfqufst, it rfplifs witi b v1
 * rfsponsf, wifn it rfdfivfs b v2 rfqufst it rfplifs witi b v2 rfsponsf.
 * <BR>Tif mftiod {@link #snmpV1Trbp snmpV1Trbp} sfnds trbps using SNMP v1
 * formbt.
 * Tif mftiod {@link #snmpV2Trbp snmpV2Trbp} sfnds trbps using SNMP v2 formbt.
 * Tif mftiod {@link #snmpInformRfqufst snmpInformRfqufst} sfnds inform
 * rfqufsts using SNMP v2 formbt.
 * <P>
 * To rfdfivf dbtb pbdkfts, tif SNMP protodol bdbptor usfs b bufffr
 * wiidi sizf dbn bf donfigurfd using tif propfrty <CODE>bufffrSizf</CODE>
 * (dffbult vbluf is 1024).
 * Pbdkfts wiidi do not fit into tif bufffr brf rfjfdtfd.
 * Indrfbsing <CODE>bufffrSizf</CODE> bllows tif fxdibngf of biggfr pbdkfts.
 * Howfvfr, tif undfrlying nftworking systfm mby imposf b limit on tif sizf
 * of UDP pbdkfts.
 * Pbdkfts wiidi sizf fxdffd tiis limit will bf rfjfdtfd, no mbttfr wibt
 * tif vbluf of <CODE>bufffrSizf</CODE> bdtublly is.
 * <P>
 * An SNMP protodol bdbptor mby sfrvf sfvfrbl mbnbgfrs dondurrfntly. Tif
 * numbfr of dondurrfnt mbnbgfrs dbn bf limitfd using tif propfrty
 * <CODE>mbxAdtivfClifntCount</CODE>.
 * <p>
 * Tif SNMP protodol bdbptor spfdififs b dffbult vbluf (10) for tif
 * <CODE>mbxAdtivfClifntCount</CODE> propfrty. Wifn tif bdbptor is stoppfd,
 * tif bdtivf rfqufsts brf intfrruptfd bnd bn frror rfsult is sfnt to
 * tif mbnbgfrs.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpAdbptorSfrvfr fxtfnds CommunidbtorSfrvfr
    implfmfnts SnmpAdbptorSfrvfrMBfbn, MBfbnRfgistrbtion, SnmpDffinitions,
               SnmpMibHbndlfr {

    // PRIVATE VARIABLES
    //------------------

    /**
     * Port numbfr for sfnding SNMP trbps.
     * <BR>Tif dffbult vbluf is 162.
     */
    privbtf int                 trbpPort = 162;

    /**
     * Port numbfr for sfnding SNMP inform rfqufsts.
     * <BR>Tif dffbult vbluf is 162.
     */
    privbtf int                 informPort = 162;

    /**
     * Tif <CODE>InftAddrfss</CODE> usfd wifn drfbting tif dbtbgrbm sodkft.
     * <BR>It is spfdififd wifn drfbting tif SNMP protodol bdbptor.
     * If not spfdififd, tif lodbl iost mbdiinf is usfd.
     */
    InftAddrfss bddrfss = null;

    /**
     * Tif IP bddrfss bbsfd ACL usfd by tiis SNMP protodol bdbptor.
     */
    privbtf InftAddrfssAdl ipbdl = null;

    /**
     * Tif fbdtory objfdt.
     */
    privbtf SnmpPduFbdtory pduFbdtory = null;

    /**
     * Tif usfr-dbtb fbdtory objfdt.
     */
    privbtf SnmpUsfrDbtbFbdtory usfrDbtbFbdtory = null;

    /**
     * Indidbtfs if tif SNMP protodol bdbptor sfnds b rfsponsf in dbsf
     * of butifntidbtion fbilurf
     */
    privbtf boolfbn butiRfspEnbblfd = truf;

    /**
     * Indidbtfs if butifntidbtion trbps brf fnbblfd.
     */
    privbtf boolfbn butiTrbpEnbblfd = truf;

    /**
     * Tif fntfrprisf OID.
     * <BR>Tif dffbult vbluf is "1.3.6.1.4.1.42".
     */
    privbtf SnmpOid fntfrprisfOid = nfw SnmpOid("1.3.6.1.4.1.42");

    /**
     * Tif bufffr sizf of tif SNMP protodol bdbptor.
     * Tiis bufffr sizf is usfd for boti indoming rfqufst bnd outgoing
     * inform rfqufsts.
     * <BR>Tif dffbult vbluf is 1024.
     */
    int bufffrSizf = 1024;

    privbtf trbnsifnt long            stbrtUpTimf     = 0;
    privbtf trbnsifnt DbtbgrbmSodkft  sodkft          = null;
    trbnsifnt DbtbgrbmSodkft          trbpSodkft      = null;
    privbtf trbnsifnt SnmpSfssion     informSfssion   = null;
    privbtf trbnsifnt DbtbgrbmPbdkft  pbdkft          = null;
    trbnsifnt Vfdtor<SnmpMibAgfnt>    mibs            = nfw Vfdtor<>();
    privbtf trbnsifnt SnmpMibTrff     root;

    /**
     * Wiftifr ACL must bf usfd.
     */
    privbtf trbnsifnt boolfbn         usfAdl = truf;


    // SENDING SNMP INFORMS STUFF
    //---------------------------

    /**
     * Numbfr of timfs to try bn inform rfqufst bfforf giving up.
     * Tif dffbult numbfr is 3.
     */
    privbtf int mbxTrifs = 3 ;

    /**
     * Tif bmount of timf to wbit for bn inform rfsponsf from tif mbnbgfr.
     * Tif dffbult bmount of timf is 3000 millisfd.
     */
    privbtf int timfout = 3 * 1000 ;

    // VARIABLES REQUIRED FOR IMPLEMENTING SNMP GROUP (MIBII)
    //-------------------------------------------------------

    /**
     * Tif <CODE>snmpOutTrbps</CODE> vbluf dffinfd in MIB-II.
     */
    int snmpOutTrbps=0;

    /**
     * Tif <CODE>snmpOutGftRfsponsfs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpOutGftRfsponsfs=0;

    /**
     * Tif <CODE>snmpOutGfnErrs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpOutGfnErrs=0;

    /**
     * Tif <CODE>snmpOutBbdVblufs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpOutBbdVblufs=0;

    /**
     * Tif <CODE>snmpOutNoSudiNbmfs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpOutNoSudiNbmfs=0;

    /**
     * Tif <CODE>snmpOutTooBigs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpOutTooBigs=0;

    /**
     * Tif <CODE>snmpOutPkts</CODE> vbluf dffinfd in MIB-II.
     */
    int snmpOutPkts=0;

    /**
     * Tif <CODE>snmpInASNPbrsfErrs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInASNPbrsfErrs=0;

    /**
     * Tif <CODE>snmpInBbdCommunityUsfs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInBbdCommunityUsfs=0;

    /**
     * Tif <CODE>snmpInBbdCommunityNbmfs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInBbdCommunityNbmfs=0;

    /**
     * Tif <CODE>snmpInBbdVfrsions</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInBbdVfrsions=0;

    /**
     * Tif <CODE>snmpInGftRfqufsts</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInGftRfqufsts=0;

    /**
     * Tif <CODE>snmpInGftNfxts</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInGftNfxts=0;

    /**
     * Tif <CODE>snmpInSftRfqufsts</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInSftRfqufsts=0;

    /**
     * Tif <CODE>snmpInPkts</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInPkts=0;

    /**
     * Tif <CODE>snmpInTotblRfqVbrs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInTotblRfqVbrs=0;

    /**
     * Tif <CODE>snmpInTotblSftVbrs</CODE> vbluf dffinfd in MIB-II.
     */
    privbtf int snmpInTotblSftVbrs=0;

    /**
     * Tif <CODE>snmpInTotblSftVbrs</CODE> vbluf dffinfd in rfd 1907 MIB-II.
     */
    privbtf int snmpSilfntDrops=0;

    privbtf stbtid finbl String IntfrruptSysCbllMsg =
        "Intfrruptfd systfm dbll";
    stbtid finbl SnmpOid sysUpTimfOid = nfw SnmpOid("1.3.6.1.2.1.1.3.0") ;
    stbtid finbl SnmpOid snmpTrbpOidOid = nfw SnmpOid("1.3.6.1.6.3.1.1.4.1.0");

    privbtf TirfbdSfrvidf tirfbdSfrvidf;

    privbtf stbtid int tirfbdNumbfr = 6;

    stbtid {
        String s = Systfm.gftPropfrty("dom.sun.jmx.snmp.tirfbdnumbfr");

        if (s != null) {
            try {
                tirfbdNumbfr = Intfgfr.pbrsfInt(Systfm.gftPropfrty(s));
            } dbtdi (Exdfption f) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER,
                        SnmpAdbptorSfrvfr.dlbss.gftNbmf(),
                        "<stbtid init>",
                        "Got wrong vbluf for dom.sun.jmx.snmp.tirfbdnumbfr: " +
                        s + ". Usf tif dffbult vbluf: " + tirfbdNumbfr);
            }
        }
    }

    // PUBLIC CONSTRUCTORS
    //--------------------

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif dffbult port (161).
     * Usf tif {@link dom.sun.jmx.snmp.IPAdl.SnmpAdl} dffbult
     * implfmfntbtion of tif <CODE>InftAddrfssAdl</CODE> intfrfbdf.
     */
    publid SnmpAdbptorSfrvfr() {
        tiis(truf, null, dom.sun.jmx.snmp.SfrvidfNbmf.SNMP_ADAPTOR_PORT,
             null) ;
    }

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif spfdififd port.
     * Usf tif {@link dom.sun.jmx.snmp.IPAdl.SnmpAdl} dffbult
     * implfmfntbtion of tif <CODE>InftAddrfssAdl</CODE> intfrfbdf.
     *
     * @pbrbm port Tif port numbfr for sfnding SNMP rfsponsfs.
     */
    publid SnmpAdbptorSfrvfr(int port) {
        tiis(truf, null, port, null) ;
    }

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif dffbult port (161)
     * bnd tif spfdififd IP bddrfss bbsfd ACL implfmfntbtion.
     *
     * @pbrbm bdl Tif <CODE>InftAddrfssAdl</CODE> implfmfntbtion.
     *        <dodf>null</dodf> mfbns no ACL - fvfrybody is butiorizfd.
     *
     * @sindf 1.5
     */
    publid SnmpAdbptorSfrvfr(InftAddrfssAdl bdl) {
        tiis(fblsf, bdl, dom.sun.jmx.snmp.SfrvidfNbmf.SNMP_ADAPTOR_PORT,
             null) ;
    }

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif dffbult port (161)
     * bnd tif
     * spfdififd <CODE>InftAddrfss</CODE>.
     * Usf tif {@link dom.sun.jmx.snmp.IPAdl.SnmpAdl} dffbult
     * implfmfntbtion of tif <CODE>InftAddrfssAdl</CODE> intfrfbdf.
     *
     * @pbrbm bddr Tif IP bddrfss to bind.
     */
    publid SnmpAdbptorSfrvfr(InftAddrfss bddr) {
        tiis(truf, null, dom.sun.jmx.snmp.SfrvidfNbmf.SNMP_ADAPTOR_PORT,
             bddr) ;
    }

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif spfdififd port bnd tif
     * spfdififd IP bddrfss bbsfd ACL implfmfntbtion.
     *
     * @pbrbm bdl Tif <CODE>InftAddrfssAdl</CODE> implfmfntbtion.
     *        <dodf>null</dodf> mfbns no ACL - fvfrybody is butiorizfd.
     * @pbrbm port Tif port numbfr for sfnding SNMP rfsponsfs.
     *
     * @sindf 1.5
     */
    publid SnmpAdbptorSfrvfr(InftAddrfssAdl bdl, int port) {
        tiis(fblsf, bdl, port, null) ;
    }

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif spfdififd port bnd tif
     * spfdififd <CODE>InftAddrfss</CODE>.
     * Usf tif {@link dom.sun.jmx.snmp.IPAdl.SnmpAdl} dffbult
     * implfmfntbtion of tif <CODE>InftAddrfssAdl</CODE> intfrfbdf.
     *
     * @pbrbm port Tif port numbfr for sfnding SNMP rfsponsfs.
     * @pbrbm bddr Tif IP bddrfss to bind.
     */
    publid SnmpAdbptorSfrvfr(int port, InftAddrfss bddr) {
        tiis(truf, null, port, bddr) ;
    }

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif spfdififd IP
     * bddrfss bbsfd ACL implfmfntbtion bnd tif spfdififd
     * <CODE>InftAddrfss</CODE>.
     *
     * @pbrbm bdl Tif <CODE>InftAddrfssAdl</CODE> implfmfntbtion.
     * @pbrbm bddr Tif IP bddrfss to bind.
     *
     * @sindf 1.5
     */
    publid SnmpAdbptorSfrvfr(InftAddrfssAdl bdl, InftAddrfss bddr) {
        tiis(fblsf, bdl, dom.sun.jmx.snmp.SfrvidfNbmf.SNMP_ADAPTOR_PORT,
             bddr) ;
    }

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif spfdififd port, tif
     * spfdififd  bddrfss bbsfd ACL implfmfntbtion bnd tif spfdififd
     * <CODE>InftAddrfss</CODE>.
     *
     * @pbrbm bdl Tif <CODE>InftAddrfssAdl</CODE> implfmfntbtion.
     * @pbrbm port Tif port numbfr for sfnding SNMP rfsponsfs.
     * @pbrbm bddr Tif IP bddrfss to bind.
     *
     * @sindf 1.5
     */
    publid SnmpAdbptorSfrvfr(InftAddrfssAdl bdl, int port, InftAddrfss bddr) {
        tiis(fblsf, bdl, port, bddr);
    }

    /**
     * Initiblizfs tiis SNMP protodol bdbptor using tif spfdififd port bnd tif
     * spfdififd <CODE>InftAddrfss</CODE>.
     * Tiis donstrudtor bllows to initiblizf bn SNMP bdbptor witiout using
     * tif ACL mfdibnism (by sftting tif <CODE>usfAdl</CODE> pbrbmftfr to
     * fblsf).
     * <br>Tiis donstrudtor must bf usfd in pbrtidulbr witi b plbtform tibt
     * dofs not support tif <CODE>jbvb.sfdurity.bdl</CODE> pbdkbgf likf pJbvb.
     *
     * @pbrbm usfAdl Spfdififs if tiis nfw SNMP bdbptor usfs tif ACL mfdibnism.
     * If tif spfdififd pbrbmftfr is sft to <CODE>truf</CODE>, tiis
     * donstrudtor is fquivblfnt to
     * <CODE>SnmpAdbptorSfrvfr((int)port,(InftAddrfss)bddr)</CODE>.
     * @pbrbm port Tif port numbfr for sfnding SNMP rfsponsfs.
     * @pbrbm bddr Tif IP bddrfss to bind.
     */
    publid SnmpAdbptorSfrvfr(boolfbn usfAdl, int port, InftAddrfss bddr) {
        tiis(usfAdl,null,port,bddr);
    }

    // If fordfAdl is `truf' bnd InftAddrfssAdl is null, tifn b dffbult
    // SnmpAdl objfdt is drfbtfd.
    //
    privbtf SnmpAdbptorSfrvfr(boolfbn fordfAdl, InftAddrfssAdl bdl,
                              int port, InftAddrfss bddr) {
        supfr(CommunidbtorSfrvfr.SNMP_TYPE) ;


        // Initiblizf tif ACL implfmfntbtion.
        //
        if (bdl == null && fordfAdl) {
            try {
                bdl = nfw SnmpAdl("SNMP protodol bdbptor IP ACL");
            } dbtdi (UnknownHostExdfption f) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "donstrudtor", "UnknowHostExdfption wifn drfbting ACL",f);
                }
            }
        } flsf {
            tiis.usfAdl = (bdl!=null) || fordfAdl;
        }

        init(bdl, port, bddr) ;
    }

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gfts tif numbfr of mbnbgfrs tibt ibvf bffn prodfssfd by tiis
     * SNMP protodol bdbptor  sindf its drfbtion.
     *
     * @rfturn Tif numbfr of mbnbgfrs ibndlfd by tiis SNMP protodol bdbptor
     * sindf its drfbtion. Tiis dountfr is not rfsft by tif <CODE>stop</CODE>
     * mftiod.
     */
    @Ovfrridf
    publid int gftSfrvfdClifntCount() {
        rfturn supfr.gftSfrvfdClifntCount();
    }

    /**
     * Gfts tif numbfr of mbnbgfrs durrfntly bfing prodfssfd by tiis
     * SNMP protodol bdbptor.
     *
     * @rfturn Tif numbfr of mbnbgfrs durrfntly bfing prodfssfd by tiis
     * SNMP protodol bdbptor.
     */
    @Ovfrridf
    publid int gftAdtivfClifntCount() {
        rfturn supfr.gftAdtivfClifntCount();
    }

    /**
     * Gfts tif mbximum numbfr of mbnbgfrs tibt tiis SNMP protodol bdbptor dbn
     * prodfss dondurrfntly.
     *
     * @rfturn Tif mbximum numbfr of mbnbgfrs tibt tiis SNMP protodol bdbptor
     *         dbn prodfss dondurrfntly.
     */
    @Ovfrridf
    publid int gftMbxAdtivfClifntCount() {
        rfturn supfr.gftMbxAdtivfClifntCount();
    }

    /**
     * Sfts tif mbximum numbfr of mbnbgfrs tiis SNMP protodol bdbptor dbn
     * prodfss dondurrfntly.
     *
     * @pbrbm d Tif numbfr of mbnbgfrs.
     *
     * @fxdfption jbvb.lbng.IllfgblStbtfExdfption Tiis mftiod ibs bffn invokfd
     * wiilf tif dommunidbtor wbs <CODE>ONLINE</CODE> or <CODE>STARTING</CODE>.
     */
    @Ovfrridf
    publid void sftMbxAdtivfClifntCount(int d)
        tirows jbvb.lbng.IllfgblStbtfExdfption {
        supfr.sftMbxAdtivfClifntCount(d);
    }

    /**
     * Rfturns tif Ip bddrfss bbsfd ACL usfd by tiis SNMP protodol bdbptor.
     * @rfturn Tif <CODE>InftAddrfssAdl</CODE> implfmfntbtion.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid InftAddrfssAdl gftInftAddrfssAdl() {
        rfturn ipbdl;
    }

    /**
     * Rfturns tif port usfd by tiis SNMP protodol bdbptor for sfnding trbps.
     * By dffbult, port 162 is usfd.
     *
     * @rfturn Tif port numbfr for sfnding SNMP trbps.
     */
    @Ovfrridf
    publid Intfgfr gftTrbpPort() {
        rfturn trbpPort;
    }

    /**
     * Sfts tif port usfd by tiis SNMP protodol bdbptor for sfnding trbps.
     *
     * @pbrbm port Tif port numbfr for sfnding SNMP trbps.
     */
    @Ovfrridf
    publid void sftTrbpPort(Intfgfr port) {
        sftTrbpPort(port.intVbluf());
    }

    /**
     * Sfts tif port usfd by tiis SNMP protodol bdbptor for sfnding trbps.
     *
     * @pbrbm port Tif port numbfr for sfnding SNMP trbps.
     */
    publid void sftTrbpPort(int port) {
        int vbl= port ;
        if (vbl < 0) tirow nfw
            IllfgblArgumfntExdfption("Trbp port dbnnot bf b nfgbtivf vbluf");
        trbpPort= vbl ;
    }

    /**
     * Rfturns tif port usfd by tiis SNMP protodol bdbptor for sfnding
     * inform rfqufsts. By dffbult, port 162 is usfd.
     *
     * @rfturn Tif port numbfr for sfnding SNMP inform rfqufsts.
     */
    @Ovfrridf
    publid int gftInformPort() {
        rfturn informPort;
    }

    /**
     * Sfts tif port usfd by tiis SNMP protodol bdbptor for sfnding
     * inform rfqufsts.
     *
     * @pbrbm port Tif port numbfr for sfnding SNMP inform rfqufsts.
     */
    @Ovfrridf
    publid void sftInformPort(int port) {
        if (port < 0)
            tirow nfw IllfgblArgumfntExdfption("Inform rfqufst port "+
                                               "dbnnot bf b nfgbtivf vbluf");
        informPort= port ;
    }

    /**
     * Rfturns tif protodol of tiis SNMP protodol bdbptor.
     *
     * @rfturn Tif string "snmp".
     */
    @Ovfrridf
    publid String gftProtodol() {
        rfturn "snmp";
    }

    /**
     * Rfturns tif bufffr sizf of tiis SNMP protodol bdbptor.
     * Tiis bufffr sizf is usfd for boti indoming rfqufst bnd outgoing
     * inform rfqufsts.
     * By dffbult, bufffr sizf 1024 is usfd.
     *
     * @rfturn Tif bufffr sizf.
     */
    @Ovfrridf
    publid Intfgfr gftBufffrSizf() {
        rfturn bufffrSizf;
    }

    /**
     * Sfts tif bufffr sizf of tiis SNMP protodol bdbptor.
     * Tiis bufffr sizf is usfd for boti indoming rfqufst bnd outgoing
     * inform rfqufsts.
     *
     * @pbrbm s Tif bufffr sizf.
     *
     * @fxdfption jbvb.lbng.IllfgblStbtfExdfption Tiis mftiod ibs bffn invokfd
     * wiilf tif dommunidbtor wbs <CODE>ONLINE</CODE> or <CODE>STARTING</CODE>.
     */
    @Ovfrridf
    publid void sftBufffrSizf(Intfgfr s)
        tirows jbvb.lbng.IllfgblStbtfExdfption {
        if ((stbtf == ONLINE) || (stbtf == STARTING)) {
            tirow nfw IllfgblStbtfExdfption("Stop sfrvfr bfforf dbrrying out"+
                                            " tiis opfrbtion");
        }
        bufffrSizf = s.intVbluf() ;
    }

    /**
     * Gfts tif numbfr of timfs to try sfnding bn inform rfqufst bfforf
     * giving up.
     * By dffbult, b mbximum of 3 trifs is usfd.
     * @rfturn Tif mbximun numbfr of trifs.
     */
    @Ovfrridf
    finbl publid int gftMbxTrifs() {
        rfturn mbxTrifs;
    }

    /**
     * Cibngfs tif mbximun numbfr of timfs to try sfnding bn inform
     * rfqufst bfforf giving up.
     * @pbrbm nfwMbxTrifs Tif mbximun numbfr of trifs.
     */
    @Ovfrridf
    finbl publid syndironizfd void sftMbxTrifs(int nfwMbxTrifs) {
        if (nfwMbxTrifs < 0)
            tirow nfw IllfgblArgumfntExdfption();
        mbxTrifs = nfwMbxTrifs;
    }

    /**
     * Gfts tif timfout to wbit for bn inform rfsponsf from tif mbnbgfr.
     * By dffbult, b timfout of 3 sfdonds is usfd.
     * @rfturn Tif vbluf of tif timfout propfrty.
     */
    @Ovfrridf
    finbl publid int gftTimfout() {
        rfturn timfout;
    }

    /**
     * Cibngfs tif timfout to wbit for bn inform rfsponsf from tif mbnbgfr.
     * @pbrbm nfwTimfout Tif timfout (in millisfdonds).
     */
    @Ovfrridf
    finbl publid syndironizfd void sftTimfout(int nfwTimfout) {
        if (nfwTimfout < 0)
            tirow nfw IllfgblArgumfntExdfption();
        timfout= nfwTimfout;
    }

    /**
     * Rfturns tif mfssbgf fbdtory of tiis SNMP protodol bdbptor.
     *
     * @rfturn Tif fbdtory objfdt.
     */
    @Ovfrridf
    publid SnmpPduFbdtory gftPduFbdtory() {
        rfturn pduFbdtory ;
    }

    /**
     * Sfts tif mfssbgf fbdtory of tiis SNMP protodol bdbptor.
     *
     * @pbrbm fbdtory Tif fbdtory objfdt (null mfbns tif dffbult fbdtory).
     */
    @Ovfrridf
    publid void sftPduFbdtory(SnmpPduFbdtory fbdtory) {
        if (fbdtory == null)
            pduFbdtory = nfw SnmpPduFbdtoryBER() ;
        flsf
            pduFbdtory = fbdtory ;
    }

    /**
     * Sft tif usfr-dbtb fbdtory of tiis SNMP protodol bdbptor.
     *
     * @pbrbm fbdtory Tif fbdtory objfdt (null mfbns no fbdtory).
     * @sff dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory
     */
    @Ovfrridf
    publid void sftUsfrDbtbFbdtory(SnmpUsfrDbtbFbdtory fbdtory) {
        usfrDbtbFbdtory = fbdtory ;
    }

    /**
     * Gft tif usfr-dbtb fbdtory bssodibtfd witi tiis SNMP protodol bdbptor.
     *
     * @rfturn Tif fbdtory objfdt (null mfbns no fbdtory).
     * @sff dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory
     */
    @Ovfrridf
    publid SnmpUsfrDbtbFbdtory gftUsfrDbtbFbdtory() {
        rfturn usfrDbtbFbdtory;
    }

    /**
     * Rfturns <CODE>truf</CODE> if butifntidbtion trbps brf fnbblfd.
     * <P>
     * Wifn tiis ffbturf is fnbblfd, tif SNMP protodol bdbptor sfnds
     * bn <CODE>butifntidbtionFbilurf</CODE> trbp fbdi timf bn
     * butifntidbtion fbils.
     * <P>
     * Tif dffbult bfibviour is to sfnd butifntidbtion trbps.
     *
     * @rfturn <CODE>truf</CODE> if butifntidbtion trbps brf fnbblfd,
     *         <CODE>fblsf</CODE> otifrwisf.
     */
    @Ovfrridf
    publid boolfbn gftAutiTrbpEnbblfd() {
        rfturn butiTrbpEnbblfd ;
    }

    /**
     * Sfts tif flbg indidbting if trbps nffd to bf sfnt in dbsf of
     * butifntidbtion fbilurf.
     *
     * @pbrbm fnbblfd Flbg indidbting if trbps nffd to bf sfnt.
     */
    @Ovfrridf
    publid void sftAutiTrbpEnbblfd(boolfbn fnbblfd) {
        butiTrbpEnbblfd = fnbblfd ;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis SNMP protodol bdbptor sfnds b
     * rfsponsf in dbsf of butifntidbtion fbilurf.
     * <P>
     * Wifn tiis ffbturf is fnbblfd, tif SNMP protodol bdbptor sfnds b
     * rfsponsf witi <CODE>noSudiNbmf</CODE> or <CODE>rfbdOnly</CODE> wifn
     * tif butifntidbtion fbilfd. If tif flbg is disbblfd, tif
     * SNMP protodol bdbptor trbsifs tif PDU silfntly.
     * <P>
     * Tif dffbult bfibvior is to sfnd rfsponsfs.
     *
     * @rfturn <CODE>truf</CODE> if rfsponsfs brf sfnt.
     */
    @Ovfrridf
    publid boolfbn gftAutiRfspEnbblfd() {
        rfturn butiRfspEnbblfd ;
    }

    /**
     * Sfts tif flbg indidbting if rfsponsfs nffd to bf sfnt in dbsf of
     * butifntidbtion fbilurf.
     *
     * @pbrbm fnbblfd Flbg indidbting if rfsponsfs nffd to bf sfnt.
     */
    @Ovfrridf
    publid void sftAutiRfspEnbblfd(boolfbn fnbblfd) {
        butiRfspEnbblfd = fnbblfd ;
    }

    /**
     * Rfturns tif fntfrprisf OID. It is usfd by
     * {@link #snmpV1Trbp snmpV1Trbp} to fill tif 'fntfrprisf' fifld of tif
     * trbp rfqufst.
     *
     * @rfturn Tif OID in string formbt "x.x.x.x".
     */
    @Ovfrridf
    publid String gftEntfrprisfOid() {
        rfturn fntfrprisfOid.toString() ;
    }

    /**
     * Sfts tif fntfrprisf OID.
     *
     * @pbrbm oid Tif OID in string formbt "x.x.x.x".
     *
     * @fxdfption IllfgblArgumfntExdfption Tif string formbt is indorrfdt
     */
    @Ovfrridf
    publid void sftEntfrprisfOid(String oid) tirows IllfgblArgumfntExdfption {
        fntfrprisfOid = nfw SnmpOid(oid) ;
    }

    /**
     * Rfturns tif nbmfs of tif MIBs bvbilbblf in tiis SNMP protodol bdbptor.
     *
     * @rfturn An brrby of MIB nbmfs.
     */
    @Ovfrridf
    publid String[] gftMibs() {
        String[] rfsult = nfw String[mibs.sizf()] ;
        int i = 0 ;
        for (Enumfrbtion<SnmpMibAgfnt> f = mibs.flfmfnts() ; f.ibsMorfElfmfnts() ;) {
            SnmpMibAgfnt mib = f.nfxtElfmfnt() ;
            rfsult[i++] = mib.gftMibNbmf();
        }
        rfturn rfsult ;
    }

    // GETTERS FOR SNMP GROUP (MIBII)
    //-------------------------------

    /**
     * Rfturns tif <CODE>snmpOutTrbps</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutTrbps</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpOutTrbps() {
        rfturn (long)snmpOutTrbps;
    }

    /**
     * Rfturns tif <CODE>snmpOutGftRfsponsfs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutGftRfsponsfs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpOutGftRfsponsfs() {
        rfturn (long)snmpOutGftRfsponsfs;
    }

    /**
     * Rfturns tif <CODE>snmpOutGfnErrs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutGfnErrs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpOutGfnErrs() {
        rfturn (long)snmpOutGfnErrs;
    }

    /**
     * Rfturns tif <CODE>snmpOutBbdVblufs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutBbdVblufs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpOutBbdVblufs() {
        rfturn (long)snmpOutBbdVblufs;
    }

    /**
     * Rfturns tif <CODE>snmpOutNoSudiNbmfs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutNoSudiNbmfs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpOutNoSudiNbmfs() {
        rfturn (long)snmpOutNoSudiNbmfs;
    }

    /**
     * Rfturns tif <CODE>snmpOutTooBigs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutTooBigs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpOutTooBigs() {
        rfturn (long)snmpOutTooBigs;
    }

    /**
     * Rfturns tif <CODE>snmpInASNPbrsfErrs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInASNPbrsfErrs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInASNPbrsfErrs() {
        rfturn (long)snmpInASNPbrsfErrs;
    }

    /**
     * Rfturns tif <CODE>snmpInBbdCommunityUsfs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInBbdCommunityUsfs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInBbdCommunityUsfs() {
        rfturn (long)snmpInBbdCommunityUsfs;
    }

    /**
     * Rfturns tif <CODE>snmpInBbdCommunityNbmfs</CODE> vbluf dffinfd in
     * MIB-II.
     *
     * @rfturn Tif <CODE>snmpInBbdCommunityNbmfs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInBbdCommunityNbmfs() {
        rfturn (long)snmpInBbdCommunityNbmfs;
    }

    /**
     * Rfturns tif <CODE>snmpInBbdVfrsions</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInBbdVfrsions</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInBbdVfrsions() {
        rfturn (long)snmpInBbdVfrsions;
    }

    /**
     * Rfturns tif <CODE>snmpOutPkts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpOutPkts</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpOutPkts() {
        rfturn (long)snmpOutPkts;
    }

    /**
     * Rfturns tif <CODE>snmpInPkts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInPkts</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInPkts() {
        rfturn (long)snmpInPkts;
    }

    /**
     * Rfturns tif <CODE>snmpInGftRfqufsts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInGftRfqufsts</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInGftRfqufsts() {
        rfturn (long)snmpInGftRfqufsts;
    }

    /**
     * Rfturns tif <CODE>snmpInGftNfxts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInGftNfxts</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInGftNfxts() {
        rfturn (long)snmpInGftNfxts;
    }

    /**
     * Rfturns tif <CODE>snmpInSftRfqufsts</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInSftRfqufsts</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInSftRfqufsts() {
        rfturn (long)snmpInSftRfqufsts;
    }

    /**
     * Rfturns tif <CODE>snmpInTotblSftVbrs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInTotblSftVbrs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInTotblSftVbrs() {
        rfturn (long)snmpInTotblSftVbrs;
    }

    /**
     * Rfturns tif <CODE>snmpInTotblRfqVbrs</CODE> vbluf dffinfd in MIB-II.
     *
     * @rfturn Tif <CODE>snmpInTotblRfqVbrs</CODE> vbluf.
     */
    @Ovfrridf
    publid Long gftSnmpInTotblRfqVbrs() {
        rfturn (long)snmpInTotblRfqVbrs;
    }

    /**
     * Rfturns tif <CODE>snmpSilfntDrops</CODE> vbluf dffinfd in RFC
     * 1907 NMPv2-MIB .
     *
     * @rfturn Tif <CODE>snmpSilfntDrops</CODE> vbluf.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid Long gftSnmpSilfntDrops() {
        rfturn (long)snmpSilfntDrops;
    }

    /**
     * Rfturns tif <CODE>snmpProxyDrops</CODE> vbluf dffinfd in RFC
     * 1907 NMPv2-MIB .
     *
     * @rfturn Tif <CODE>snmpProxyDrops</CODE> vbluf.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid Long gftSnmpProxyDrops() {
        rfturn 0L;
    }


    // PUBLIC METHODS
    //---------------

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions it nffds bfforf bfing
     * rfgistfrfd in tif MBfbn sfrvfr.
     * If tif nbmf of tif SNMP protodol bdbptor MBfbn is not spfdififd,
     * it is initiblizfd witi tif dffbult vbluf:
     * {@link dom.sun.jmx.snmp.SfrvidfNbmf#DOMAIN
     *   dom.sun.jmx.snmp.SfrvidfNbmf.DOMAIN}:{@link
     * dom.sun.jmx.snmp.SfrvidfNbmf#SNMP_ADAPTOR_SERVER
     * dom.sun.jmx.snmp.SfrvidfNbmf.SNMP_ADAPTOR_SERVER}.
     * If bny fxdfption is rbisfd, tif SNMP protodol bdbptor MBfbn will
     * not bf rfgistfrfd in tif MBfbn sfrvfr.
     *
     * @pbrbm sfrvfr Tif MBfbn sfrvfr to rfgistfr tif sfrvidf witi.
     * @pbrbm nbmf Tif objfdt nbmf.
     *
     * @rfturn Tif nbmf of tif SNMP protodol bdbptor rfgistfrfd.
     *
     * @fxdfption jbvb.lbng.Exdfption
     */
    @Ovfrridf
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
        tirows jbvb.lbng.Exdfption {

        if (nbmf == null) {
            nbmf = nfw ObjfdtNbmf(sfrvfr.gftDffbultDombin() + ":" +
                             dom.sun.jmx.snmp.SfrvidfNbmf.SNMP_ADAPTOR_SERVER);
        }
        rfturn (supfr.prfRfgistfr(sfrvfr, nbmf));
    }

    /**
     * Not usfd in tiis dontfxt.
     */
    @Ovfrridf
    publid void postRfgistfr (Boolfbn rfgistrbtionDonf) {
        supfr.postRfgistfr(rfgistrbtionDonf);
    }

    /**
     * Not usfd in tiis dontfxt.
     */
    @Ovfrridf
    publid void prfDfrfgistfr() tirows jbvb.lbng.Exdfption {
        supfr.prfDfrfgistfr();
    }

    /**
     * Not usfd in tiis dontfxt.
     */
    @Ovfrridf
    publid void postDfrfgistfr() {
        supfr.postDfrfgistfr();
    }

    /**
     * Adds b nfw MIB in tif SNMP MIB ibndlfr.
     *
     * @pbrbm mib Tif MIB to bdd.
     *
     * @rfturn A rfffrfndf to tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     */
    @Ovfrridf
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib)
        tirows IllfgblArgumfntExdfption {
        if (mib == null) {
            tirow nfw IllfgblArgumfntExdfption() ;
        }

        if(!mibs.dontbins(mib))
            mibs.bddElfmfnt(mib);

        root.rfgistfr(mib);

        rfturn tiis;
    }

    /**
     * Adds b nfw MIB in tif SNMP MIB ibndlfr.
     * Tiis mftiod is to bf dbllfd to sft b spfdifid bgfnt to b spfdifid OID.
     * Tiis dbn bf usfful wifn dfbling witi MIB ovfrlbpping.
     * Somf OID dbn bf implfmfntfd in morf tibn onf MIB. In tiis dbsf,
     * tif OID nfbrfr bgfnt will bf usfd on SNMP opfrbtions.
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
    @Ovfrridf
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib, SnmpOid[] oids)
        tirows IllfgblArgumfntExdfption {
        if (mib == null) {
            tirow nfw IllfgblArgumfntExdfption() ;
        }

        //If null oid brrby, just bdd it to tif mib.
        if(oids == null)
            rfturn bddMib(mib);

        if(!mibs.dontbins(mib))
            mibs.bddElfmfnt(mib);

        for (int i = 0; i < oids.lfngti; i++) {
            root.rfgistfr(mib, oids[i].longVbluf());
        }
        rfturn tiis;
    }

    /**
     * Adds b nfw MIB in tif SNMP MIB ibndlfr. In SNMP V1 bnd V2 tif
     * <CODE>dontfxtNbmf</CODE> is usflfss bnd tiis mftiod
     * is fquivblfnt to <CODE>bddMib(SnmpMibAgfnt mib)</CODE>.
     *
     * @pbrbm mib Tif MIB to bdd.
     * @pbrbm dontfxtNbmf Tif MIB dontfxt nbmf.
     * @rfturn A rfffrfndf on tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib, String dontfxtNbmf)
        tirows IllfgblArgumfntExdfption {
        rfturn bddMib(mib);
    }

    /**
     * Adds b nfw MIB in tif SNMP MIB ibndlfr. In SNMP V1 bnd V2 tif
     * <CODE>dontfxtNbmf</CODE> is usflfss bnd tiis mftiod
     * is fquivblfnt to <CODE>bddMib(SnmpMibAgfnt mib, SnmpOid[] oids)</CODE>.
     *
     * @pbrbm mib Tif MIB to bdd.
     * @pbrbm dontfxtNbmf Tif MIB dontfxt. If null is pbssfd, will bf
     *        rfgistfrfd in tif dffbult dontfxt.
     * @pbrbm oids Tif sft of OIDs tiis bgfnt implfmfnts.
     *
     * @rfturn A rfffrfndf to tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib,
                                 String dontfxtNbmf,
                                 SnmpOid[] oids)
        tirows IllfgblArgumfntExdfption {

        rfturn bddMib(mib, oids);
    }

    /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     * In SNMP V1 bnd V2 tif <CODE>dontfxtNbmf</CODE> is usflfss bnd tiis
     * mftiod is fquivblfnt to <CODE>rfmovfMib(SnmpMibAgfnt mib)</CODE>.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf usfd bt rfgistrbtion timf.
     *
     * @rfturn <CODE>truf</CODE> if tif spfdififd <CODE>mib</CODE> wbs
     * b MIB indludfd in tif SNMP MIB ibndlfr, <CODE>fblsf</CODE>
     * otifrwisf.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib, String dontfxtNbmf) {
        rfturn rfmovfMib(mib);
    }

    /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     *
     * @rfturn <CODE>truf</CODE> if tif spfdififd <CODE>mib</CODE> wbs b MIB
     *         indludfd in tif SNMP MIB ibndlfr, <CODE>fblsf</CODE> otifrwisf.
     */
    @Ovfrridf
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib) {
        root.unrfgistfr(mib);
        rfturn (mibs.rfmovfElfmfnt(mib)) ;
    }

    /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     * @pbrbm oids Tif oid tif MIB wbs prfviously rfgistfrfd for.
     * @rfturn <CODE>truf</CODE> if tif spfdififd <CODE>mib</CODE> wbs
     * b MIB indludfd in tif SNMP MIB ibndlfr, <CODE>fblsf</CODE>
     * otifrwisf.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib, SnmpOid[] oids) {
        root.unrfgistfr(mib, oids);
        rfturn (mibs.rfmovfElfmfnt(mib)) ;
    }

     /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf usfd bt rfgistrbtion timf.
     * @pbrbm oids Tif oid tif MIB wbs prfviously rfgistfrfd for.
     * @rfturn <CODE>truf</CODE> if tif spfdififd <CODE>mib</CODE> wbs
     * b MIB indludfd in tif SNMP MIB ibndlfr, <CODE>fblsf</CODE>
     * otifrwisf.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib,
                             String dontfxtNbmf,
                             SnmpOid[] oids) {
        rfturn rfmovfMib(mib, oids);
    }

    // SUBCLASSING OF COMMUNICATOR SERVER
    //-----------------------------------

    /**
     * Crfbtfs tif dbtbgrbm sodkft.
     */
    @Ovfrridf
    protfdtfd void doBind()
        tirows CommunidbtionExdfption, IntfrruptfdExdfption {

        try {
            syndironizfd (tiis) {
                sodkft = nfw DbtbgrbmSodkft(port, bddrfss) ;
            }
            dbgTbg = mbkfDfbugTbg();
        } dbtdi (SodkftExdfption f) {
            if (f.gftMfssbgf().fqubls(IntfrruptSysCbllMsg))
                tirow nfw IntfrruptfdExdfption(f.toString()) ;
            flsf {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "doBind", "dbnnot bind on port " + port);
                }
                tirow nfw CommunidbtionExdfption(f) ;
            }
        }
    }

    /**
     * Rfturn tif bdtubl port to wiidi tif bdbptor is bound.
     * Cbn bf difffrfnt from tif port givfn bt donstrudtion timf if
     * tibt port numbfr wbs 0.
     * @rfturn tif bdtubl port to wiidi tif bdbptor is bound.
     **/
    @Ovfrridf
    publid int gftPort() {
        syndironizfd (tiis) {
            if (sodkft != null) rfturn sodkft.gftLodblPort();
        }
        rfturn supfr.gftPort();
    }

    /**
     * Closfs tif dbtbgrbm sodkft.
     */
    @Ovfrridf
    protfdtfd void doUnbind()
        tirows CommunidbtionExdfption, IntfrruptfdExdfption {
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "doUnbind","Finblly dlosf tif sodkft");
        }
        syndironizfd (tiis) {
            if (sodkft != null) {
                sodkft.dlosf() ;
                sodkft = null ;
                // Importbnt to inform finblizf() tibt tif sodkft is dlosfd...
            }
        }
        dlosfTrbpSodkftIfNffdfd() ;
        dlosfInformSodkftIfNffdfd() ;
    }

    privbtf void drfbtfSnmpRfqufstHbndlfr(SnmpAdbptorSfrvfr sfrvfr,
                                          int id,
                                          DbtbgrbmSodkft s,
                                          DbtbgrbmPbdkft p,
                                          SnmpMibTrff trff,
                                          Vfdtor<SnmpMibAgfnt> m,
                                          InftAddrfssAdl b,
                                          SnmpPduFbdtory fbdtory,
                                          SnmpUsfrDbtbFbdtory dbtbFbdtory,
                                          MBfbnSfrvfr f,
                                          ObjfdtNbmf n) {
        finbl SnmpRfqufstHbndlfr ibndlfr =
            nfw SnmpRfqufstHbndlfr(tiis, id, s, p, trff, m, b, fbdtory,
                                   dbtbFbdtory, f, n);
        tirfbdSfrvidf.submitTbsk(ibndlfr);
    }

    /**
     * Rfbds b pbdkft from tif dbtbgrbm sodkft bnd drfbtfs b rfqufst
     * ibndlfr wiidi dfdodfs bnd prodfssfs tif rfqufst.
     */
    @Ovfrridf
    protfdtfd void doRfdfivf()
        tirows CommunidbtionExdfption, IntfrruptfdExdfption {

        // Lft's wbit for somftiing to bf rfdfivfd.
        //
        try {
            pbdkft = nfw DbtbgrbmPbdkft(nfw bytf[bufffrSizf], bufffrSizf) ;
            sodkft.rfdfivf(pbdkft);
            int stbtf = gftStbtf();

            if(stbtf != ONLINE) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                        "doRfdfivf","rfdfivfd b mfssbgf but stbtf not onlinf, rfturning.");
                }
                rfturn;
            }

            drfbtfSnmpRfqufstHbndlfr(tiis, sfrvfdClifntCount, sodkft,
                                     pbdkft, root, mibs, ipbdl, pduFbdtory,
                                     usfrDbtbFbdtory, topMBS, objfdtNbmf);
        } dbtdi (SodkftExdfption f) {
            // Lft's difdk if wf ibvf bffn intfrruptfd by stop().
            //
            if (f.gftMfssbgf().fqubls(IntfrruptSysCbllMsg))
                tirow nfw IntfrruptfdExdfption(f.toString()) ;
            flsf
                tirow nfw CommunidbtionExdfption(f) ;
        } dbtdi (IntfrruptfdIOExdfption f) {
            tirow nfw IntfrruptfdExdfption(f.toString()) ;
        } dbtdi (CommunidbtionExdfption f) {
            tirow f ;
        } dbtdi (Exdfption f) {
            tirow nfw CommunidbtionExdfption(f) ;
        }
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "doRfdfivf", "rfdfivfd b mfssbgf");
        }
    }

    @Ovfrridf
    protfdtfd void doError(Exdfption f) tirows CommunidbtionExdfption {
    }

    /**
     * Not usfd in tiis dontfxt.
     */
    @Ovfrridf
    protfdtfd void doProdfss()
        tirows CommunidbtionExdfption, IntfrruptfdExdfption {
    }


    /**
     * Tif numbfr of timfs tif dommunidbtor sfrvfr will bttfmpt
     * to bind bfforf giving up.
     * Wf bttfmpt only ondf...
     * @rfturn 1
     **/
    @Ovfrridf
    protfdtfd int gftBindTrifs() {
        rfturn 1;
    }

    /**
     * Stops tiis SNMP protodol bdbptor.
     * Closfs tif dbtbgrbm sodkft.
     * <p>
     * Hbs no ffffdt if tiis SNMP protodol bdbptor is <CODE>OFFLINE</CODE> or
     * <CODE>STOPPING</CODE>.
     */
    @Ovfrridf
    publid void stop(){

        finbl int port = gftPort();
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "stop", "Stopping: using port " + port);
        }
        if ((stbtf == ONLINE) || (stbtf == STARTING)){
            supfr.stop();
            try {
                DbtbgrbmSodkft sn = nfw DbtbgrbmSodkft(0);
                try {
                    bytf[] ob = nfw bytf[1];

                    DbtbgrbmPbdkft pk;
                    if (bddrfss != null)
                        pk = nfw DbtbgrbmPbdkft(ob , 1, bddrfss, port);
                    flsf
                        pk = nfw DbtbgrbmPbdkft(ob , 1,
                                 jbvb.nft.InftAddrfss.gftLodblHost(), port);

                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                            "stop", "Sfnding: using port " + port);
                    }
                    sn.sfnd(pk);
                } finblly {
                    sn.dlosf();
                }
            } dbtdi (Tirowbblf f){
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "stop", "Got unfxpfdtfd Tirowbblf", f);
                }
            }
        }
    }

    // SENDING SNMP TRAPS STUFF
    //-------------------------

    /**
     * Sfnds b trbp using SNMP V1 trbp formbt.
     * <BR>Tif trbp is sfnt to fbdi dfstinbtion dffinfd in tif ACL filf
     * (if bvbilbblf).
     * If no ACL filf or no dfstinbtions brf bvbilbblf, tif trbp is sfnt
     * to tif lodbl iost.
     *
     * @pbrbm gfnfrid Tif gfnfrid numbfr of tif trbp.
     * @pbrbm spfdifid Tif spfdifid numbfr of tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd
     *            by <CODE>bufffrSizf</CODE>.
     */
    @Ovfrridf
    publid void snmpV1Trbp(int gfnfrid, int spfdifid,
                           SnmpVbrBindList vbrBindList)
        tirows IOExdfption, SnmpStbtusExdfption {

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "snmpV1Trbp", "gfnfrid=" + gfnfrid +
                  ", spfdifid=" + spfdifid);
        }

        // First, mbkf bn SNMP V1 trbp pdu
        //
        SnmpPduTrbp pdu = nfw SnmpPduTrbp() ;
        pdu.bddrfss = null ;
        pdu.port = trbpPort ;
        pdu.typf = pduV1TrbpPdu ;
        pdu.vfrsion = snmpVfrsionOnf ;
        pdu.dommunity = null ;
        pdu.fntfrprisf = fntfrprisfOid ;
        pdu.gfnfridTrbp = gfnfrid ;
        pdu.spfdifidTrbp = spfdifid ;
        pdu.timfStbmp = gftSysUpTimf();

        if (vbrBindList != null) {
            pdu.vbrBindList = nfw SnmpVbrBind[vbrBindList.sizf()] ;
            vbrBindList.dopyInto(pdu.vbrBindList);
        }
        flsf
            pdu.vbrBindList = null ;

        // If tif lodbl iost dbnnot bf dftfrminfd, wf put 0.0.0.0 in bgfntAddr
        try {
            if (bddrfss != null)
                pdu.bgfntAddr = ibndlfMultiplfIpVfrsion(bddrfss.gftAddrfss());
            flsf pdu.bgfntAddr =
              ibndlfMultiplfIpVfrsion(InftAddrfss.gftLodblHost().gftAddrfss());
        } dbtdi (UnknownHostExdfption f) {
            bytf[] zfrofdAddr = nfw bytf[4];
            pdu.bgfntAddr = ibndlfMultiplfIpVfrsion(zfrofdAddr) ;
        }

        // Nfxt, sfnd tif pdu to bll dfstinbtions dffinfd in ACL
        //
        sfndTrbpPdu(pdu) ;
    }

    privbtf SnmpIpAddrfss ibndlfMultiplfIpVfrsion(bytf[] bddrfss) {
        if(bddrfss.lfngti == 4)
          rfturn nfw SnmpIpAddrfss(bddrfss);
        flsf {
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                    "ibndlfMultiplfIPVfrsion",
                      "Not bn IPv4 bddrfss, rfturn null");
            }
            rfturn null;
        }
    }

    /**
     * Sfnds b trbp using SNMP V1 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>InftAddrfss</CODE>
     * dfstinbtion using tif spfdififd dommunity string (bnd tif ACL filf
     * is not usfd).
     *
     * @pbrbm bddr Tif <CODE>InftAddrfss</CODE> dfstinbtion of tif trbp.
     * @pbrbm ds Tif dommunity string to bf usfd for tif trbp.
     * @pbrbm gfnfrid Tif gfnfrid numbfr of tif trbp.
     * @pbrbm spfdifid Tif spfdifid numbfr of tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd
     *            by <CODE>bufffrSizf</CODE>.
     */
    @Ovfrridf
    publid void snmpV1Trbp(InftAddrfss bddr, String ds, int gfnfrid,
                           int spfdifid, SnmpVbrBindList vbrBindList)
        tirows IOExdfption, SnmpStbtusExdfption {

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "snmpV1Trbp", "gfnfrid=" + gfnfrid + ", spfdifid=" +
                  spfdifid);
        }

        // First, mbkf bn SNMP V1 trbp pdu
        //
        SnmpPduTrbp pdu = nfw SnmpPduTrbp() ;
        pdu.bddrfss = null ;
        pdu.port = trbpPort ;
        pdu.typf = pduV1TrbpPdu ;
        pdu.vfrsion = snmpVfrsionOnf ;

        if(ds != null)
            pdu.dommunity = ds.gftBytfs();
        flsf
            pdu.dommunity = null ;

        pdu.fntfrprisf = fntfrprisfOid ;
        pdu.gfnfridTrbp = gfnfrid ;
        pdu.spfdifidTrbp = spfdifid ;
        pdu.timfStbmp = gftSysUpTimf();

        if (vbrBindList != null) {
            pdu.vbrBindList = nfw SnmpVbrBind[vbrBindList.sizf()] ;
            vbrBindList.dopyInto(pdu.vbrBindList);
        }
        flsf
            pdu.vbrBindList = null ;

        // If tif lodbl iost dbnnot bf dftfrminfd, wf put 0.0.0.0 in bgfntAddr
        try {
            if (bddrfss != null)
                pdu.bgfntAddr = ibndlfMultiplfIpVfrsion(bddrfss.gftAddrfss());
            flsf pdu.bgfntAddr =
              ibndlfMultiplfIpVfrsion(InftAddrfss.gftLodblHost().gftAddrfss());
        } dbtdi (UnknownHostExdfption f) {
            bytf[] zfrofdAddr = nfw bytf[4];
            pdu.bgfntAddr = ibndlfMultiplfIpVfrsion(zfrofdAddr) ;
        }

        // Nfxt, sfnd tif pdu to tif spfdififd dfstinbtion
        //
        if(bddr != null)
            sfndTrbpPdu(bddr, pdu) ;
        flsf
            sfndTrbpPdu(pdu);
    }

    /**
     * Sfnds b trbp using SNMP V1 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>InftAddrfss</CODE>
     * dfstinbtion using tif spfdififd pbrbmftfrs (bnd tif ACL filf is not
     * usfd).
     * Notf tibt if tif spfdififd <CODE>InftAddrfss</CODE> dfstinbtion is null,
     * tifn tif ACL filf mfdibnism is usfd.
     *
     * @pbrbm bddr Tif <CODE>InftAddrfss</CODE> dfstinbtion of tif trbp.
     * @pbrbm bgfntAddr Tif bgfnt bddrfss to bf usfd for tif trbp.
     * @pbrbm ds Tif dommunity string to bf usfd for tif trbp.
     * @pbrbm fntfrpOid Tif fntfrprisf OID to bf usfd for tif trbp.
     * @pbrbm gfnfrid Tif gfnfrid numbfr of tif trbp.
     * @pbrbm spfdifid Tif spfdifid numbfr of tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     * @pbrbm timf Tif timf stbmp (ovfrwritf tif durrfnt timf).
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd
     *            by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    publid void snmpV1Trbp(InftAddrfss bddr,
                           SnmpIpAddrfss bgfntAddr,
                           String ds,
                           SnmpOid fntfrpOid,
                           int gfnfrid,
                           int spfdifid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimftidks timf)
        tirows IOExdfption, SnmpStbtusExdfption {
        snmpV1Trbp(bddr,
                   trbpPort,
                   bgfntAddr,
                   ds,
                   fntfrpOid,
                   gfnfrid,
                   spfdifid,
                   vbrBindList,
                   timf);
    }

    /**
     * Sfnds b trbp using SNMP V1 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>SnmpPffr</CODE> dfstinbtion.
     * Tif dommunity string usfd is tif onf lodbtfd in tif
     * <CODE>SnmpPffr</CODE> pbrbmftfrs
     * (<CODE>SnmpPbrbmftfrs.gftRdCommunity() </CODE>).
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
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit
     * dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void snmpV1Trbp(SnmpPffr pffr,
                           SnmpIpAddrfss bgfntAddr,
                           SnmpOid fntfrpOid,
                           int gfnfrid,
                           int spfdifid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimftidks timf)
        tirows IOExdfption, SnmpStbtusExdfption {

        SnmpPbrbmftfrs p = (SnmpPbrbmftfrs) pffr.gftPbrbms();
        snmpV1Trbp(pffr.gftDfstAddr(),
                   pffr.gftDfstPort(),
                   bgfntAddr,
                   p.gftRdCommunity(),
                   fntfrpOid,
                   gfnfrid,
                   spfdifid,
                   vbrBindList,
                   timf);
    }

    privbtf void snmpV1Trbp(InftAddrfss bddr,
                            int port,
                            SnmpIpAddrfss bgfntAddr,
                            String ds,
                            SnmpOid fntfrpOid,
                            int gfnfrid,
                            int spfdifid,
                            SnmpVbrBindList vbrBindList,
                            SnmpTimftidks timf)
        tirows IOExdfption, SnmpStbtusExdfption {

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "snmpV1Trbp", "gfnfrid=" + gfnfrid + ", spfdifid=" +
                  spfdifid);
        }

        // First, mbkf bn SNMP V1 trbp pdu
        //
        SnmpPduTrbp pdu = nfw SnmpPduTrbp() ;
        pdu.bddrfss = null ;
        pdu.port = port ;
        pdu.typf = pduV1TrbpPdu ;
        pdu.vfrsion = snmpVfrsionOnf ;

        //Diff stbrt
        if(ds != null)
            pdu.dommunity = ds.gftBytfs();
        flsf
            pdu.dommunity = null ;
        //Diff fnd

        // Diff stbrt
        if(fntfrpOid != null)
            pdu.fntfrprisf = fntfrpOid;
        flsf
            pdu.fntfrprisf = fntfrprisfOid ;
        //Diff fnd
        pdu.gfnfridTrbp = gfnfrid ;
        pdu.spfdifidTrbp = spfdifid ;
        //Diff stbrt
        if(timf != null)
            pdu.timfStbmp = timf.longVbluf();
        flsf
            pdu.timfStbmp = gftSysUpTimf();
        //Diff fnd

        if (vbrBindList != null) {
            pdu.vbrBindList = nfw SnmpVbrBind[vbrBindList.sizf()] ;
            vbrBindList.dopyInto(pdu.vbrBindList);
        }
        flsf
            pdu.vbrBindList = null ;

        if (bgfntAddr == null) {
            // If tif lodbl iost dbnnot bf dftfrminfd,
            // wf put 0.0.0.0 in bgfntAddr
            try {
                finbl InftAddrfss inftAddr =
                    (bddrfss!=null)?bddrfss:InftAddrfss.gftLodblHost();
                bgfntAddr = ibndlfMultiplfIpVfrsion(inftAddr.gftAddrfss());
            }  dbtdi (UnknownHostExdfption f) {
                bytf[] zfrofdAddr = nfw bytf[4];
                bgfntAddr = ibndlfMultiplfIpVfrsion(zfrofdAddr);
            }
        }

        pdu.bgfntAddr = bgfntAddr;

        // Nfxt, sfnd tif pdu to tif spfdififd dfstinbtion
        //
        // Diff stbrt
        if(bddr != null)
            sfndTrbpPdu(bddr, pdu) ;
        flsf
            sfndTrbpPdu(pdu);

        //End diff
    }

    /**
     * Sfnds b trbp using SNMP V2 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>SnmpPffr</CODE> dfstinbtion.
     * <BR>Tif dommunity string usfd is tif onf lodbtfd in tif
     * <CODE>SnmpPffr</CODE> pbrbmftfrs
     * (<CODE>SnmpPbrbmftfrs.gftRdCommunity() </CODE>).
     * <BR>Tif vbribblf list indludfd in tif outgoing trbp is domposfd of
     * tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi tif vbluf spfdififd by
     *     <CODE>timf</CODE></LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     *
     * @pbrbm pffr Tif <CODE>SnmpPffr</CODE> dfstinbtion of tif trbp.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     * @pbrbm timf Tif timf stbmp (ovfrwritf tif durrfnt timf).
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit
     * dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void snmpV2Trbp(SnmpPffr pffr,
                           SnmpOid trbpOid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimftidks timf)
        tirows IOExdfption, SnmpStbtusExdfption {

        SnmpPbrbmftfrs p = (SnmpPbrbmftfrs) pffr.gftPbrbms();
        snmpV2Trbp(pffr.gftDfstAddr(),
                   pffr.gftDfstPort(),
                   p.gftRdCommunity(),
                   trbpOid,
                   vbrBindList,
                   timf);
    }

    /**
     * Sfnds b trbp using SNMP V2 trbp formbt.
     * <BR>Tif trbp is sfnt to fbdi dfstinbtion dffinfd in tif ACL filf
     * (if bvbilbblf). If no ACL filf or no dfstinbtions brf bvbilbblf,
     * tif trbp is sfnt to tif lodbl iost.
     * <BR>Tif vbribblf list indludfd in tif outgoing trbp is domposfd of
     * tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     *
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd
     *            by <CODE>bufffrSizf</CODE>.
     */
    @Ovfrridf
    publid void snmpV2Trbp(SnmpOid trbpOid, SnmpVbrBindList vbrBindList)
        tirows IOExdfption, SnmpStbtusExdfption {

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "snmpV2Trbp", "trbpOid=" + trbpOid);
        }

        // First, mbkf bn SNMP V2 trbp pdu
        // Wf dlonf vbrBindList bnd insfrt sysUpTimf bnd snmpTrbpOid
        //
        SnmpPduRfqufst pdu = nfw SnmpPduRfqufst() ;
        pdu.bddrfss = null ;
        pdu.port = trbpPort ;
        pdu.typf = pduV2TrbpPdu ;
        pdu.vfrsion = snmpVfrsionTwo ;
        pdu.dommunity = null ;

        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.dlonf() ;
        flsf
            fullVbl = nfw SnmpVbrBindList(2) ;
        SnmpTimftidks sysUpTimfVbluf = nfw SnmpTimftidks(gftSysUpTimf()) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(sysUpTimfOid, sysUpTimfVbluf),
                                0);
        pdu.vbrBindList = nfw SnmpVbrBind[fullVbl.sizf()] ;
        fullVbl.dopyInto(pdu.vbrBindList) ;

        // Nfxt, sfnd tif pdu to bll dfstinbtions dffinfd in ACL
        //
        sfndTrbpPdu(pdu) ;
    }

    /**
     * Sfnds b trbp using SNMP V2 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>InftAddrfss</CODE>
     * dfstinbtion using tif spfdififd dommunity string (bnd tif ACL filf
     * is not usfd).
     * <BR>Tif vbribblf list indludfd in tif outgoing trbp is domposfd of
     * tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     *
     * @pbrbm bddr Tif <CODE>InftAddrfss</CODE> dfstinbtion of tif trbp.
     * @pbrbm ds Tif dommunity string to bf usfd for tif trbp.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit
     *            dffinfd by <CODE>bufffrSizf</CODE>.
     */
    @Ovfrridf
    publid void snmpV2Trbp(InftAddrfss bddr, String ds, SnmpOid trbpOid,
                           SnmpVbrBindList vbrBindList)
        tirows IOExdfption, SnmpStbtusExdfption {

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "snmpV2Trbp", "trbpOid=" + trbpOid);
        }

        // First, mbkf bn SNMP V2 trbp pdu
        // Wf dlonf vbrBindList bnd insfrt sysUpTimf bnd snmpTrbpOid
        //
        SnmpPduRfqufst pdu = nfw SnmpPduRfqufst() ;
        pdu.bddrfss = null ;
        pdu.port = trbpPort ;
        pdu.typf = pduV2TrbpPdu ;
        pdu.vfrsion = snmpVfrsionTwo ;

        if(ds != null)
            pdu.dommunity = ds.gftBytfs();
        flsf
            pdu.dommunity = null;

        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.dlonf() ;
        flsf
            fullVbl = nfw SnmpVbrBindList(2) ;
        SnmpTimftidks sysUpTimfVbluf = nfw SnmpTimftidks(gftSysUpTimf()) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(sysUpTimfOid, sysUpTimfVbluf),
                                0);
        pdu.vbrBindList = nfw SnmpVbrBind[fullVbl.sizf()] ;
        fullVbl.dopyInto(pdu.vbrBindList) ;

        // Nfxt, sfnd tif pdu to tif spfdififd dfstinbtion
        //
        if(bddr != null)
            sfndTrbpPdu(bddr, pdu);
        flsf
            sfndTrbpPdu(pdu);
    }

    /**
     * Sfnds b trbp using SNMP V2 trbp formbt.
     * <BR>Tif trbp is sfnt to tif spfdififd <CODE>InftAddrfss</CODE>
     * dfstinbtion using tif spfdififd pbrbmftfrs (bnd tif ACL filf is not
     * usfd).
     * Notf tibt if tif spfdififd <CODE>InftAddrfss</CODE> dfstinbtion is null,
     * tifn tif ACL filf mfdibnism is usfd.
     * <BR>Tif vbribblf list indludfd in tif outgoing trbp is domposfd of tif
     * following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi tif vbluf spfdififd by
     *     <CODE>timf</CODE></LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     *
     * @pbrbm bddr Tif <CODE>InftAddrfss</CODE> dfstinbtion of tif trbp.
     * @pbrbm ds Tif dommunity string to bf usfd for tif trbp.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     * @pbrbm timf Tif timf stbmp (ovfrwritf tif durrfnt timf).
     *
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit
     * dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    publid void snmpV2Trbp(InftAddrfss bddr,
                           String ds,
                           SnmpOid trbpOid,
                           SnmpVbrBindList vbrBindList,
                           SnmpTimftidks timf)
        tirows IOExdfption, SnmpStbtusExdfption {

        snmpV2Trbp(bddr,
                   trbpPort,
                   ds,
                   trbpOid,
                   vbrBindList,
                   timf);
    }

    privbtf void snmpV2Trbp(InftAddrfss bddr,
                            int port,
                            String ds,
                            SnmpOid trbpOid,
                            SnmpVbrBindList vbrBindList,
                            SnmpTimftidks timf)
        tirows IOExdfption, SnmpStbtusExdfption {

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            finbl StringBuildfr strb = nfw StringBuildfr()
                .bppfnd("trbpOid=").bppfnd(trbpOid)
                .bppfnd("\ndommunity=").bppfnd(ds)
                .bppfnd("\nbddr=").bppfnd(bddr)
                .bppfnd("\nvbrBindList=").bppfnd(vbrBindList)
                .bppfnd("\ntimf=").bppfnd(timf)
                .bppfnd("\ntrbpPort=").bppfnd(port);
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "snmpV2Trbp", strb.toString());
        }

        // First, mbkf bn SNMP V2 trbp pdu
        // Wf dlonf vbrBindList bnd insfrt sysUpTimf bnd snmpTrbpOid
        //
        SnmpPduRfqufst pdu = nfw SnmpPduRfqufst() ;
        pdu.bddrfss = null ;
        pdu.port = port ;
        pdu.typf = pduV2TrbpPdu ;
        pdu.vfrsion = snmpVfrsionTwo ;

        if(ds != null)
            pdu.dommunity = ds.gftBytfs();
        flsf
            pdu.dommunity = null;

        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.dlonf() ;
        flsf
            fullVbl = nfw SnmpVbrBindList(2) ;

        // Only difffrfndf witi otifr
        SnmpTimftidks sysUpTimfVbluf;
        if(timf != null)
            sysUpTimfVbluf = timf;
        flsf
            sysUpTimfVbluf = nfw SnmpTimftidks(gftSysUpTimf()) ;
        //End of diff

        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(sysUpTimfOid, sysUpTimfVbluf),
                                0);
        pdu.vbrBindList = nfw SnmpVbrBind[fullVbl.sizf()] ;
        fullVbl.dopyInto(pdu.vbrBindList) ;

        // Nfxt, sfnd tif pdu to tif spfdififd dfstinbtion
        //
        // Diff stbrt
        if(bddr != null)
            sfndTrbpPdu(bddr, pdu) ;
        flsf
            sfndTrbpPdu(pdu);
        //End diff
    }

    /**
     * Sfnd tif spfdififd trbp PDU to tif pbssfd <CODE>InftAddrfss</CODE>.
     * @pbrbm bddrfss Tif dfstinbtion bddrfss.
     * @pbrbm pdu Tif pdu to sfnd.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit
     * dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid void snmpPduTrbp(InftAddrfss bddrfss, SnmpPduPbdkft pdu)
            tirows IOExdfption, SnmpStbtusExdfption {

        if(bddrfss != null)
            sfndTrbpPdu(bddrfss, pdu);
        flsf
            sfndTrbpPdu(pdu);
    }

    /**
     * Sfnd tif spfdififd trbp PDU to tif pbssfd <CODE>SnmpPffr</CODE>.
     * @pbrbm pffr Tif dfstinbtion pffr. Tif Rfbd dommunity string is usfd of
     * <CODE>SnmpPbrbmftfrs</CODE> is usfd bs tif trbp dommunity string.
     * @pbrbm pdu Tif pdu to sfnd.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif trbp.
     * @fxdfption SnmpStbtusExdfption If tif trbp fxdffds tif limit dffinfd
     * by <CODE>bufffrSizf</CODE>.
     * @sindf 1.5
     */
    @Ovfrridf
    publid void snmpPduTrbp(SnmpPffr pffr,
                            SnmpPduPbdkft pdu)
        tirows IOExdfption, SnmpStbtusExdfption {
        if(pffr != null) {
            pdu.port = pffr.gftDfstPort();
            sfndTrbpPdu(pffr.gftDfstAddr(), pdu);
        }
        flsf {
            pdu.port = gftTrbpPort().intVbluf();
            sfndTrbpPdu(pdu);
        }
    }

    /**
     * Sfnd tif spfdififd trbp PDU to fvfry dfstinbtions from tif ACL filf.
     */
    privbtf void sfndTrbpPdu(SnmpPduPbdkft pdu)
     tirows SnmpStbtusExdfption, IOExdfption {

        // Mbkf bn SNMP mfssbgf from tif pdu
        //
        SnmpMfssbgf msg = null ;
        try {
            msg = (SnmpMfssbgf)pduFbdtory.fndodfSnmpPdu(pdu, bufffrSizf) ;
            if (msg == null) {
                tirow nfw SnmpStbtusExdfption(
                          SnmpDffinitions.snmpRspAutiorizbtionError) ;
            }
        }
        dbtdi (SnmpTooBigExdfption x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                    "sfndTrbpPdu", "Trbp pdu is too big. " +
                     "Trbp ibsn't bffn sfnt to bnyonf" );
            }
            tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspTooBig) ;
            // FIXME: is tif rigit fxdfption to tirow ?
            // Wf dould simply forwbrd SnmpTooBigExdfption ?
        }

        // Now sfnd tif SNMP mfssbgf to fbdi dfstinbtion
        //
        int sfndingCount = 0 ;
        opfnTrbpSodkftIfNffdfd() ;
        if (ipbdl != null) {
            Enumfrbtion<InftAddrfss> fd = ipbdl.gftTrbpDfstinbtions() ;
            wiilf (fd.ibsMorfElfmfnts()) {
                msg.bddrfss = fd.nfxtElfmfnt() ;
                Enumfrbtion<String> fd = ipbdl.gftTrbpCommunitifs(msg.bddrfss) ;
                wiilf (fd.ibsMorfElfmfnts()) {
                    msg.dommunity = fd.nfxtElfmfnt().gftBytfs() ;
                    try {
                        sfndTrbpMfssbgf(msg) ;
                        sfndingCount++ ;
                    }
                    dbtdi (SnmpTooBigExdfption x) {
                        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                                "sfndTrbpPdu", "Trbp pdu is too big. " +
                                 "Trbp ibsn't bffn sfnt to "+msg.bddrfss);
                        }
                    }
                }
            }
        }

        // If tifrf is no dfstinbtion dffinfd or if fvfrytiing ibs fbilfd
        // wf trifd to sfnd tif trbp to tif lodbl iost (bs suggfstfd by
        // mistfr Olivifr Rfisbdifr).
        //
        if (sfndingCount == 0) {
            try {
                msg.bddrfss = InftAddrfss.gftLodblHost() ;
                sfndTrbpMfssbgf(msg) ;
            } dbtdi (SnmpTooBigExdfption x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "sfndTrbpPdu", "Trbp pdu is too big. " +
                         "Trbp ibsn't bffn sfnt.");
                }
            } dbtdi (UnknownHostExdfption f) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "sfndTrbpPdu", "Trbp pdu is too big. " +
                         "Trbp ibsn't bffn sfnt.");
                }
            }
        }

        dlosfTrbpSodkftIfNffdfd() ;
    }

    /**
     * Sfnd tif spfdififd trbp PDU to tif spfdififd dfstinbtion.
     */
    privbtf void sfndTrbpPdu(InftAddrfss bddr, SnmpPduPbdkft pdu)
        tirows SnmpStbtusExdfption, IOExdfption {

        // Mbkf bn SNMP mfssbgf from tif pdu
        //
        SnmpMfssbgf msg = null ;
        try {
            msg = (SnmpMfssbgf)pduFbdtory.fndodfSnmpPdu(pdu, bufffrSizf) ;
            if (msg == null) {
                tirow nfw SnmpStbtusExdfption(
                          SnmpDffinitions.snmpRspAutiorizbtionError) ;
            }
        } dbtdi (SnmpTooBigExdfption x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                    "sfndTrbpPdu", "Trbp pdu is too big. " +
                     "Trbp ibsn't bffn sfnt to tif spfdififd iost.");
            }
            tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspTooBig) ;
            // FIXME: is tif rigit fxdfption to tirow ?
            // Wf dould simply forwbrd SnmpTooBigExdfption ?
        }

        // Now sfnd tif SNMP mfssbgf to spfdififd dfstinbtion
        //
        opfnTrbpSodkftIfNffdfd() ;
        if (bddr != null) {
            msg.bddrfss = bddr;
            try {
                sfndTrbpMfssbgf(msg) ;
            } dbtdi (SnmpTooBigExdfption x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "sfndTrbpPdu", "Trbp pdu is too big. " +
                         "Trbp ibsn't bffn sfnt to " +  msg.bddrfss);
                }
            }
        }

        dlosfTrbpSodkftIfNffdfd() ;
    }

    /**
     * Sfnd tif spfdififd mfssbgf on trbpSodkft.
     */
    privbtf void sfndTrbpMfssbgf(SnmpMfssbgf msg)
        tirows IOExdfption, SnmpTooBigExdfption {

        bytf[] bufffr = nfw bytf[bufffrSizf] ;
        DbtbgrbmPbdkft pbdkft = nfw DbtbgrbmPbdkft(bufffr, bufffr.lfngti) ;
        int fndodingLfngti = msg.fndodfMfssbgf(bufffr) ;
        pbdkft.sftLfngti(fndodingLfngti) ;
        pbdkft.sftAddrfss(msg.bddrfss) ;
        pbdkft.sftPort(msg.port) ;
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "sfndTrbpMfssbgf", "sfnding trbp to " + msg.bddrfss + ":" +
                  msg.port);
        }
        trbpSodkft.sfnd(pbdkft) ;
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "sfndTrbpMfssbgf", "sfnt to " + msg.bddrfss + ":" +
                  msg.port);
        }
        snmpOutTrbps++;
        snmpOutPkts++;
    }

    /**
     * Opfn trbpSodkft if it's not blrfbdy donf.
     */
    syndironizfd void opfnTrbpSodkftIfNffdfd() tirows SodkftExdfption {
        if (trbpSodkft == null) {
            trbpSodkft = nfw DbtbgrbmSodkft(0, bddrfss) ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                    "opfnTrbpSodkftIfNffdfd", "using port " +
                      trbpSodkft.gftLodblPort() + " to sfnd trbps");
            }
        }
    }

    /**
     * Closf trbpSodkft if tif SNMP protodol bdbptor is not ONLINE.
     */
    syndironizfd void dlosfTrbpSodkftIfNffdfd() {
        if ((trbpSodkft != null) && (stbtf != ONLINE)) {
            trbpSodkft.dlosf() ;
            trbpSodkft = null ;
        }
    }

    // SENDING SNMP INFORMS STUFF
    //---------------------------

    /**
     * Sfnds bn inform using SNMP V2 inform rfqufst formbt.
     * <BR>Tif inform rfqufst is sfnt to fbdi dfstinbtion dffinfd in tif ACL
     * filf (if bvbilbblf).
     * If no ACL filf or no dfstinbtions brf bvbilbblf, tif inform rfqufst is
     * sfnt to tif lodbl iost.
     * <BR>Tif vbribblf list indludfd in tif outgoing inform is domposfd of
     * tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     * To sfnd bn inform rfqufst, tif SNMP bdbptor sfrvfr must bf bdtivf.
     *
     * @pbrbm db Tif dbllbbdk tibt is invokfd wifn b rfqufst is domplftf.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @rfturn A vfdtor of {@link dom.sun.jmx.snmp.dbfmon.SnmpInformRfqufst}
     *         objfdts.
     *         <P>If tifrf is no dfstinbtion iost for tiis inform rfqufst,
     *         tif rfturnfd vfdtor will bf fmpty.
     *
     * @fxdfption IllfgblStbtfExdfption  Tiis mftiod ibs bffn invokfd wiilf
     *            tif SNMP bdbptor sfrvfr wbs not bdtivf.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif
     *            inform rfqufst.
     * @fxdfption SnmpStbtusExdfption If tif inform rfqufst fxdffds tif
     *            limit dffinfd by <CODE>bufffrSizf</CODE>.
     */
    @Ovfrridf
    publid Vfdtor<SnmpInformRfqufst> snmpInformRfqufst(SnmpInformHbndlfr db,
                                                       SnmpOid trbpOid,
                                                       SnmpVbrBindList vbrBindList)
        tirows IllfgblStbtfExdfption, IOExdfption, SnmpStbtusExdfption {

        if (!isAdtivf()) {
            tirow nfw IllfgblStbtfExdfption(
               "Stbrt SNMP bdbptor sfrvfr bfforf dbrrying out tiis opfrbtion");
        }
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "snmpInformRfqufst", "trbpOid=" + trbpOid);
        }

        // First, mbkf bn SNMP inform pdu:
        // Wf dlonf vbrBindList bnd insfrt sysUpTimf bnd snmpTrbpOid vbribblfs.
        //
        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.dlonf() ;
        flsf
            fullVbl = nfw SnmpVbrBindList(2) ;
        SnmpTimftidks sysUpTimfVbluf = nfw SnmpTimftidks(gftSysUpTimf()) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(sysUpTimfOid, sysUpTimfVbluf),
                                0);

        // Nfxt, sfnd tif pdu to tif spfdififd dfstinbtion
        //
        opfnInformSodkftIfNffdfd() ;

        // Now sfnd tif SNMP mfssbgf to fbdi dfstinbtion
        //
        Vfdtor<SnmpInformRfqufst> informRfqList = nfw Vfdtor<>();
        InftAddrfss bddr;
        String ds;
        if (ipbdl != null) {
            Enumfrbtion<InftAddrfss> fd = ipbdl.gftInformDfstinbtions() ;
            wiilf (fd.ibsMorfElfmfnts()) {
                bddr = fd.nfxtElfmfnt() ;
                Enumfrbtion<String> fd = ipbdl.gftInformCommunitifs(bddr) ;
                wiilf (fd.ibsMorfElfmfnts()) {
                    ds = fd.nfxtElfmfnt() ;
                    informRfqList.bddElfmfnt(
                       informSfssion.mbkfAsyndRfqufst(bddr, ds, db,
                                              fullVbl,gftInformPort())) ;
                }
            }
        }

        rfturn informRfqList ;
    }

    /**
     * Sfnds bn inform using SNMP V2 inform rfqufst formbt.
     * <BR>Tif inform is sfnt to tif spfdififd <CODE>InftAddrfss</CODE>
     * dfstinbtion
     * using tif spfdififd dommunity string.
     * <BR>Tif vbribblf list indludfd in tif outgoing inform is domposfd
     *     of tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by
     *      <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     * To sfnd bn inform rfqufst, tif SNMP bdbptor sfrvfr must bf bdtivf.
     *
     * @pbrbm bddr Tif <CODE>InftAddrfss</CODE> dfstinbtion for tiis inform
     *             rfqufst.
     * @pbrbm ds Tif dommunity string to bf usfd for tif inform rfqufst.
     * @pbrbm db Tif dbllbbdk tibt is invokfd wifn b rfqufst is domplftf.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @rfturn Tif inform rfqufst objfdt.
     *
     * @fxdfption IllfgblStbtfExdfption  Tiis mftiod ibs bffn invokfd
     *            wiilf tif SNMP bdbptor sfrvfr wbs not bdtivf.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif
     *            inform rfqufst.
     * @fxdfption SnmpStbtusExdfption If tif inform rfqufst fxdffds tif
     *            limit dffinfd by <CODE>bufffrSizf</CODE>.
     */
    @Ovfrridf
    publid SnmpInformRfqufst snmpInformRfqufst(InftAddrfss bddr,
                                               String ds,
                                               SnmpInformHbndlfr db,
                                               SnmpOid trbpOid,
                                               SnmpVbrBindList vbrBindList)
        tirows IllfgblStbtfExdfption, IOExdfption, SnmpStbtusExdfption {

        rfturn snmpInformRfqufst(bddr,
                                 gftInformPort(),
                                 ds,
                                 db,
                                 trbpOid,
                                 vbrBindList);
    }

    /**
     * Sfnds bn inform using SNMP V2 inform rfqufst formbt.
     * <BR>Tif inform is sfnt to tif spfdififd <CODE>SnmpPffr</CODE>
     *     dfstinbtion.
     * <BR>Tif dommunity string usfd is tif onf lodbtfd in tif
     *     <CODE>SnmpPffr</CODE> pbrbmftfrs
     *     (<CODE>SnmpPbrbmftfrs.gftInformCommunity() </CODE>).
     * <BR>Tif vbribblf list indludfd in tif outgoing inform is domposfd
     *     of tif following itfms:
     * <UL>
     * <LI><CODE>sysUpTimf.0</CODE> witi its durrfnt vbluf</LI>
     * <LI><CODE>snmpTrbpOid.0</CODE> witi tif vbluf spfdififd by
     *     <CODE>trbpOid</CODE></LI>
     * <LI><CODE>bll tif (oid,vblufs)</CODE> from tif spfdififd
     *     <CODE>vbrBindList</CODE></LI>
     * </UL>
     * To sfnd bn inform rfqufst, tif SNMP bdbptor sfrvfr must bf bdtivf.
     *
     * @pbrbm pffr Tif <CODE>SnmpPffr</CODE> dfstinbtion for tiis inform
     *             rfqufst.
     * @pbrbm db Tif dbllbbdk tibt is invokfd wifn b rfqufst is domplftf.
     * @pbrbm trbpOid Tif OID idfntifying tif trbp.
     * @pbrbm vbrBindList A list of <CODE>SnmpVbrBind</CODE> instbndfs or null.
     *
     * @rfturn Tif inform rfqufst objfdt.
     *
     * @fxdfption IllfgblStbtfExdfption  Tiis mftiod ibs bffn invokfd wiilf
     *            tif SNMP bdbptor sfrvfr wbs not bdtivf.
     * @fxdfption IOExdfption An I/O frror oddurrfd wiilf sfnding tif
     *            inform rfqufst.
     * @fxdfption SnmpStbtusExdfption If tif inform rfqufst fxdffds tif
     *            limit dffinfd by <CODE>bufffrSizf</CODE>.
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid SnmpInformRfqufst snmpInformRfqufst(SnmpPffr pffr,
                                               SnmpInformHbndlfr db,
                                               SnmpOid trbpOid,
                                               SnmpVbrBindList vbrBindList)
        tirows IllfgblStbtfExdfption, IOExdfption, SnmpStbtusExdfption {

        SnmpPbrbmftfrs p = (SnmpPbrbmftfrs) pffr.gftPbrbms();
        rfturn snmpInformRfqufst(pffr.gftDfstAddr(),
                                 pffr.gftDfstPort(),
                                 p.gftInformCommunity(),
                                 db,
                                 trbpOid,
                                 vbrBindList);
    }

    /**
     * Mftiod tibt mbps bn SNMP frror stbtus in tif pbssfd protodolVfrsion
     * bddording to tif providfd pdu typf.
     * @pbrbm frrorStbtus Tif frror stbtus to donvfrt.
     * @pbrbm protodolVfrsion Tif protodol vfrsion.
     * @pbrbm rfqPduTypf Tif pdu typf.
     */
    publid stbtid int mbpErrorStbtus(int frrorStbtus,
                                     int protodolVfrsion,
                                     int rfqPduTypf) {
        rfturn SnmpSubRfqufstHbndlfr.mbpErrorStbtus(frrorStbtus,
                                                    protodolVfrsion,
                                                    rfqPduTypf);
    }

    privbtf SnmpInformRfqufst snmpInformRfqufst(InftAddrfss bddr,
                                                int port,
                                                String ds,
                                                SnmpInformHbndlfr db,
                                                SnmpOid trbpOid,
                                                SnmpVbrBindList vbrBindList)
        tirows IllfgblStbtfExdfption, IOExdfption, SnmpStbtusExdfption {

        if (!isAdtivf()) {
            tirow nfw IllfgblStbtfExdfption(
              "Stbrt SNMP bdbptor sfrvfr bfforf dbrrying out tiis opfrbtion");
        }
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                "snmpInformRfqufst", "trbpOid=" + trbpOid);
        }

        // First, mbkf bn SNMP inform pdu:
        // Wf dlonf vbrBindList bnd insfrt sysUpTimf bnd snmpTrbpOid vbribblfs.
        //
        SnmpVbrBindList fullVbl ;
        if (vbrBindList != null)
            fullVbl = vbrBindList.dlonf() ;
        flsf
            fullVbl = nfw SnmpVbrBindList(2) ;
        SnmpTimftidks sysUpTimfVbluf = nfw SnmpTimftidks(gftSysUpTimf()) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(snmpTrbpOidOid, trbpOid), 0) ;
        fullVbl.insfrtElfmfntAt(nfw SnmpVbrBind(sysUpTimfOid, sysUpTimfVbluf),
                                0);

        // Nfxt, sfnd tif pdu to tif spfdififd dfstinbtion
        //
        opfnInformSodkftIfNffdfd() ;
        rfturn informSfssion.mbkfAsyndRfqufst(bddr, ds, db, fullVbl, port) ;
    }


    /**
     * Opfn informSodkft if it's not blrfbdy donf.
     */
    syndironizfd void opfnInformSodkftIfNffdfd() tirows SodkftExdfption {
        if (informSfssion == null) {
            informSfssion = nfw SnmpSfssion(tiis) ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                   "opfnInformSodkftIfNffdfd",
                      "to sfnd inform rfqufsts bnd rfdfivf inform rfsponsfs");
            }
        }
    }

    /**
     * Closf informSodkft if tif SNMP protodol bdbptor is not ONLINE.
     */
    syndironizfd void dlosfInformSodkftIfNffdfd() {
        if ((informSfssion != null) && (stbtf != ONLINE)) {
            informSfssion.dfstroySfssion() ;
            informSfssion = null ;
        }
    }

    /**
     * Gfts tif IP bddrfss to bind.
     * Tiis gfttfr is usfd to initiblizf tif DbtbgrbmSodkft in tif
     * SnmpSodkft objfdt drfbtfd for tif inform rfqufst stuff.
     */
    InftAddrfss gftAddrfss() {
        rfturn bddrfss;
    }


    // PROTECTED METHODS
    //------------------

    /**
     * Finblizfr of tif SNMP protodol bdbptor objfdts.
     * Tiis mftiod is dbllfd by tif gbrbbgf dollfdtor on bn objfdt
     * wifn gbrbbgf dollfdtion dftfrminfs tibt tifrf brf no morf
     * rfffrfndfs to tif objfdt.
     * <P>Closfs tif dbtbgrbm sodkft bssodibtfd to tiis SNMP protodol bdbptor.
     */
    @Ovfrridf
    protfdtfd void finblizf() {
        try {
            if (sodkft != null) {
                sodkft.dlosf() ;
                sodkft = null ;
            }

            tirfbdSfrvidf.tfrminbtf();
        } dbtdi (Exdfption f) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                   "finblizf", "Exdfption in finblizfr", f);
            }
        }
    }

    // PACKAGE METHODS
    //----------------

    /**
     * Rfturns tif string usfd in dfbug trbdfs.
     */
    @Ovfrridf
    String mbkfDfbugTbg() {
        rfturn "SnmpAdbptorSfrvfr["+ gftProtodol() + ":" + gftPort() + "]";
    }

    void updbtfRfqufstCountfrs(int pduTypf) {
        switdi(pduTypf)  {

        dbsf pduGftRfqufstPdu:
            snmpInGftRfqufsts++;
            brfbk;
        dbsf pduGftNfxtRfqufstPdu:
            snmpInGftNfxts++;
            brfbk;
        dbsf pduSftRfqufstPdu:
            snmpInSftRfqufsts++;
            brfbk;
        dffbult:
            brfbk;
        }
        snmpInPkts++ ;
    }

    void updbtfErrorCountfrs(int frrorStbtus) {
        switdi(frrorStbtus) {

        dbsf snmpRspNoError:
            snmpOutGftRfsponsfs++;
            brfbk;
        dbsf snmpRspGfnErr:
            snmpOutGfnErrs++;
            brfbk;
        dbsf snmpRspBbdVbluf:
            snmpOutBbdVblufs++;
            brfbk;
        dbsf snmpRspNoSudiNbmf:
            snmpOutNoSudiNbmfs++;
            brfbk;
        dbsf snmpRspTooBig:
            snmpOutTooBigs++;
            brfbk;
        dffbult:
            brfbk;
        }
        snmpOutPkts++ ;
    }

    void updbtfVbrCountfrs(int pduTypf, int n) {
        switdi(pduTypf) {

        dbsf pduGftRfqufstPdu:
        dbsf pduGftNfxtRfqufstPdu:
        dbsf pduGftBulkRfqufstPdu:
            snmpInTotblRfqVbrs += n ;
            brfbk ;
        dbsf pduSftRfqufstPdu:
            snmpInTotblSftVbrs += n ;
            brfbk ;
        }
    }

    void indSnmpInASNPbrsfErrs(int n) {
        snmpInASNPbrsfErrs += n ;
    }

    void indSnmpInBbdVfrsions(int n) {
        snmpInBbdVfrsions += n ;
    }

    void indSnmpInBbdCommunityUsfs(int n) {
        snmpInBbdCommunityUsfs += n ;
    }

    void indSnmpInBbdCommunityNbmfs(int n) {
        snmpInBbdCommunityNbmfs += n ;
    }

    void indSnmpSilfntDrops(int n) {
        snmpSilfntDrops += n ;
    }
    // PRIVATE METHODS
    //----------------

    /**
     * Rfturns tif timf (in iundrftis of sfdond) flbpsfd sindf tif SNMP
     * protodol bdbptor stbrtup.
     */
    long gftSysUpTimf() {
        rfturn (Systfm.durrfntTimfMillis() - stbrtUpTimf) / 10 ;
    }

    /**
     * Control tif wby tif SnmpAdbptorSfrvfr sfrvidf is dfsfriblizfd.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm)
        tirows IOExdfption, ClbssNotFoundExdfption {

        // Cbll tif dffbult dfsfriblizbtion of tif objfdt.
        //
        strfbm.dffbultRfbdObjfdt();

        // Cbll tif spfdifid initiblizbtion for tif SnmpAdbptorSfrvfr sfrvidf.
        // Tiis is for trbnsifnt strudturfs to bf initiblizfd to spfdifid
        // dffbult vblufs.
        //
        mibs      = nfw Vfdtor<>() ;
    }

    /**
     * Common initiblizbtions.
     */
    privbtf void init(InftAddrfssAdl bdl, int p, InftAddrfss b) {

        root= nfw SnmpMibTrff();

        // Tif dffbult Agfnt is initiblizfd witi b SnmpErrorHbndlfrAgfnt bgfnt.
        root.sftDffbultAgfnt(nfw SnmpErrorHbndlfrAgfnt());

        // For tif trbp timf, usf tif timf tif bgfnt stbrtfd ...
        //
        stbrtUpTimf= jbvb.lbng.Systfm.durrfntTimfMillis();
        mbxAdtivfClifntCount = 10;

        // Crfbtf tif dffbult mfssbgf fbdtory
        pduFbdtory = nfw SnmpPduFbdtoryBER() ;

        port = p ;
        ipbdl = bdl ;
        bddrfss = b ;

        if ((ipbdl == null) && (usfAdl == truf))
            tirow nfw IllfgblArgumfntExdfption("ACL objfdt dbnnot bf null") ;

        tirfbdSfrvidf = nfw TirfbdSfrvidf(tirfbdNumbfr);
    }

    SnmpMibAgfnt gftAgfntMib(SnmpOid oid) {
        rfturn root.gftAgfntMib(oid);
    }

    @Ovfrridf
    protfdtfd Tirfbd drfbtfMbinTirfbd() {
        finbl Tirfbd t = supfr.drfbtfMbinTirfbd();
        t.sftDbfmon(truf);
        rfturn t;
    }

}
