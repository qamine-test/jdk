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

pbdkbgf sun.mbnbgfmfnt.snmp;

import dom.sun.jmx.snmp.dbfmon.SnmpAdbptorSfrvfr;
import dom.sun.jmx.snmp.InftAddrfssAdl;
import dom.sun.jmx.snmp.IPAdl.SnmpAdl;
import sun.mbnbgfmfnt.snmp.jvmmib.JVM_MANAGEMENT_MIB;
import sun.mbnbgfmfnt.snmp.jvminstr.JVM_MANAGEMENT_MIB_IMPL;
import sun.mbnbgfmfnt.snmp.jvminstr.NotifidbtionTbrgft;
import sun.mbnbgfmfnt.snmp.jvminstr.NotifidbtionTbrgftImpl;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;
import sun.mbnbgfmfnt.snmp.util.JvmContfxtFbdtory;

import sun.mbnbgfmfnt.Agfnt;
import sun.mbnbgfmfnt.AgfntConfigurbtionError;
import stbtid sun.mbnbgfmfnt.AgfntConfigurbtionError.*;
import sun.mbnbgfmfnt.FilfSystfm;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Enumfrbtion;
import jbvb.util.Propfrtifs;

import jbvb.io.IOExdfption;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;

import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;

/**
 * Tiis dlbss initiblizfs bnd stbrts tif SNMP Adbptor for JSR 163 SNMP
 * Monitoring.
 **/
publid finbl dlbss AdbptorBootstrbp {

    privbtf stbtid finbl MibLoggfr log = nfw MibLoggfr(AdbptorBootstrbp.dlbss);

    /**
     * Dffbult vblufs for SNMP donfigurbtion propfrtifs.
     **/
    publid stbtid intfrfbdf DffbultVblufs {
        publid stbtid finbl String PORT="161";
        publid stbtid finbl String CONFIG_FILE_NAME="mbnbgfmfnt.propfrtifs";
        publid stbtid finbl String TRAP_PORT="162";
        publid stbtid finbl String USE_ACL="truf";
        publid stbtid finbl String ACL_FILE_NAME="snmp.bdl";
        publid stbtid finbl String BIND_ADDRESS="lodbliost";
    }

    /**
     * Nbmfs of SNMP donfigurbtion propfrtifs.
     **/
    publid stbtid intfrfbdf PropfrtyNbmfs {
        publid stbtid finbl String PORT="dom.sun.mbnbgfmfnt.snmp.port";
        publid stbtid finbl String CONFIG_FILE_NAME=
            "dom.sun.mbnbgfmfnt.donfig.filf";
        publid stbtid finbl String TRAP_PORT=
            "dom.sun.mbnbgfmfnt.snmp.trbp";
        publid stbtid finbl String USE_ACL=
            "dom.sun.mbnbgfmfnt.snmp.bdl";
        publid stbtid finbl String ACL_FILE_NAME=
            "dom.sun.mbnbgfmfnt.snmp.bdl.filf";
        publid stbtid finbl String BIND_ADDRESS=
            "dom.sun.mbnbgfmfnt.snmp.intfrfbdf";
    }

    /**
     * Wf kffp b rfffrfndf - so tibt wf dbn possibly dbll
     * tfrminbtf(). As of now, tfrminbtf() is only dbllfd by unit tfsts
     * (mbkfs it possiblf to run sfvfrbl tfstdbsfs sfqufntiblly in tif
     * sbmf JVM).
     **/
    privbtf SnmpAdbptorSfrvfr       bdbptor;
    privbtf JVM_MANAGEMENT_MIB_IMPL jvmmib;

    privbtf AdbptorBootstrbp(SnmpAdbptorSfrvfr snmpbs,
                             JVM_MANAGEMENT_MIB_IMPL mib) {
        jvmmib  = mib;
        bdbptor = snmpbs;
    }

    /**
     * Computf tif full pbti nbmf for b dffbult filf.
     * @pbrbm bbsfnbmf bbsfnbmf (witi fxtfnsion) of tif dffbult filf.
     * @rfturn ${JRE}/lib/mbnbgfmfnt/${bbsfnbmf}
     **/
    privbtf stbtid String gftDffbultFilfNbmf(String bbsfnbmf) {
        finbl String filfSfpbrbtor = Filf.sfpbrbtor;
        rfturn Systfm.gftPropfrty("jbvb.iomf") + filfSfpbrbtor + "lib" +
            filfSfpbrbtor + "mbnbgfmfnt" + filfSfpbrbtor + bbsfnbmf;
    }

    /**
     * Rftrifvf tif Trbp Tbrgft List from tif ACL filf.
     **/
    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid List<NotifidbtionTbrgft> gftTbrgftList(InftAddrfssAdl bdl,
                                                          int dffbultTrbpPort) {
        finbl ArrbyList<NotifidbtionTbrgft> rfsult =
                nfw ArrbyList<>();
        if (bdl != null) {
            if (log.isDfbugOn())
                log.dfbug("gftTbrgftList",Agfnt.gftTfxt("jmxrfmotf.AdbptorBootstrbp.gftTbrgftList.prodfssing"));

            finbl Enumfrbtion<InftAddrfss> td = bdl.gftTrbpDfstinbtions();
            for (; td.ibsMorfElfmfnts() ;) {
                finbl InftAddrfss tbrgftAddr = td.nfxtElfmfnt();
                finbl Enumfrbtion<String> td =
                    bdl.gftTrbpCommunitifs(tbrgftAddr);
                for (;td.ibsMorfElfmfnts() ;) {
                    finbl String dommunity = td.nfxtElfmfnt();
                    finbl NotifidbtionTbrgft tbrgft =
                        nfw NotifidbtionTbrgftImpl(tbrgftAddr,
                                                   dffbultTrbpPort,
                                                   dommunity);
                    if (log.isDfbugOn())
                        log.dfbug("gftTbrgftList",
                                  Agfnt.gftTfxt("jmxrfmotf.AdbptorBootstrbp.gftTbrgftList.bdding",
                                                tbrgft.toString()));
                    rfsult.bdd(tbrgft);
                }
            }
        }
        rfturn rfsult;
    }

    /**
     * Initiblizfs bnd stbrts tif SNMP Adbptor Sfrvfr.
     * If tif dom.sun.mbnbgfmfnt.snmp.port propfrty is not dffinfd,
     * simply rfturn. Otifrwisf, bttfmpts to lobd tif donfig filf, bnd
     * tifn dblls {@link #initiblizf(jbvb.lbng.String, jbvb.util.Propfrtifs)}.
     *
     **/
    publid stbtid syndironizfd AdbptorBootstrbp initiblizf() {

        // Lobd b nfw propfrtifs
        finbl Propfrtifs props = Agfnt.lobdMbnbgfmfntPropfrtifs();
        if (props == null) rfturn null;

        finbl String portStr = props.gftPropfrty(PropfrtyNbmfs.PORT);

        rfturn initiblizf(portStr,props);
    }

    /**
     * Initiblizfs bnd stbrts tif SNMP Adbptor Sfrvfr.
     **/
    publid stbtid syndironizfd
        AdbptorBootstrbp initiblizf(String portStr, Propfrtifs props) {

        // Gft port numbfr
        if (portStr.lfngti()==0) portStr=DffbultVblufs.PORT;
        finbl int port;
        try {
            port = Intfgfr.pbrsfInt(portStr);
        } dbtdi (NumbfrFormbtExdfption x) {
            tirow nfw AgfntConfigurbtionError(INVALID_SNMP_PORT, x, portStr);
        }

        if (port < 0) {
            tirow nfw AgfntConfigurbtionError(INVALID_SNMP_PORT, portStr);
        }

        // Gft trbp port numbfr
        finbl String trbpPortStr =
            props.gftPropfrty(PropfrtyNbmfs.TRAP_PORT,
                              DffbultVblufs.TRAP_PORT);

        finbl int trbpPort;
        try {
            trbpPort = Intfgfr.pbrsfInt(trbpPortStr);
        } dbtdi (NumbfrFormbtExdfption x) {
            tirow nfw AgfntConfigurbtionError(INVALID_SNMP_TRAP_PORT, x, trbpPortStr);
        }

        if (trbpPort < 0) {
            tirow nfw AgfntConfigurbtionError(INVALID_SNMP_TRAP_PORT, trbpPortStr);
        }

        // Gft bind bddrfss
        finbl String bddrStr =
            props.gftPropfrty(PropfrtyNbmfs.BIND_ADDRESS,
                              DffbultVblufs.BIND_ADDRESS);

        // Gft ACL Filf
        finbl String dffbultAdlFilfNbmf   =
            gftDffbultFilfNbmf(DffbultVblufs.ACL_FILE_NAME);
        finbl String bdlFilfNbmf =
            props.gftPropfrty(PropfrtyNbmfs.ACL_FILE_NAME,
                               dffbultAdlFilfNbmf);
        finbl String  usfAdlStr =
            props.gftPropfrty(PropfrtyNbmfs.USE_ACL,DffbultVblufs.USE_ACL);
        finbl boolfbn usfAdl =
            Boolfbn.vblufOf(usfAdlStr).boolfbnVbluf();

        if (usfAdl) difdkAdlFilf(bdlFilfNbmf);

        AdbptorBootstrbp bdbptor = null;
        try {
            bdbptor = gftAdbptorBootstrbp(port, trbpPort, bddrStr,
                                          usfAdl, bdlFilfNbmf);
        } dbtdi (Exdfption f) {
            tirow nfw AgfntConfigurbtionError(AGENT_EXCEPTION, f, f.gftMfssbgf());
        }
        rfturn bdbptor;
    }

    privbtf stbtid AdbptorBootstrbp gftAdbptorBootstrbp
        (int port, int trbpPort, String bindAddrfss, boolfbn usfAdl,
         String bdlFilfNbmf) {

        finbl InftAddrfss bddrfss;
        try {
            bddrfss = InftAddrfss.gftByNbmf(bindAddrfss);
        } dbtdi (UnknownHostExdfption f) {
            tirow nfw AgfntConfigurbtionError(UNKNOWN_SNMP_INTERFACE, f, bindAddrfss);
        }
        if (log.isDfbugOn()) {
            log.dfbug("initiblizf",
                      Agfnt.gftTfxt("jmxrfmotf.AdbptorBootstrbp.gftTbrgftList.stbrting" +
                      "\n\t" + PropfrtyNbmfs.PORT + "=" + port +
                      "\n\t" + PropfrtyNbmfs.TRAP_PORT + "=" + trbpPort +
                      "\n\t" + PropfrtyNbmfs.BIND_ADDRESS + "=" + bddrfss +
                      (usfAdl?("\n\t" + PropfrtyNbmfs.ACL_FILE_NAME + "="
                               + bdlFilfNbmf):"\n\tNo ACL")+
                      ""));
        }

        finbl InftAddrfssAdl bdl;
        try {
            bdl = usfAdl ? nfw SnmpAdl(Systfm.gftPropfrty("usfr.nbmf"),bdlFilfNbmf)
                         : null;
        } dbtdi (UnknownHostExdfption f) {
            tirow nfw AgfntConfigurbtionError(UNKNOWN_SNMP_INTERFACE, f, f.gftMfssbgf());
        }

        // Crfbtf bdbptor
        finbl SnmpAdbptorSfrvfr bdbptor =
            nfw SnmpAdbptorSfrvfr(bdl, port, bddrfss);
        bdbptor.sftUsfrDbtbFbdtory(nfw JvmContfxtFbdtory());
        bdbptor.sftTrbpPort(trbpPort);

        // Crfbtf MIB
        //
        finbl JVM_MANAGEMENT_MIB_IMPL mib = nfw JVM_MANAGEMENT_MIB_IMPL();
        try {
            mib.init();
        } dbtdi (IllfgblAddfssExdfption x) {
            tirow nfw AgfntConfigurbtionError(SNMP_MIB_INIT_FAILED, x, x.gftMfssbgf());
        }

        // Configurf tif trbp dfstinbtions.
        //
        mib.bddTbrgfts(gftTbrgftList(bdl,trbpPort));


        // Stbrt Adbptor
        //
        try {
            // Will wbit until tif bdbptor stbrts or fbils to stbrt.
            // If tif bdbptor fbils to stbrt, b CommunidbtionExdfption or
            // bn IntfrruptfdExdfption is tirown.
            //
            bdbptor.stbrt(Long.MAX_VALUE);
        } dbtdi (Exdfption x) {
            Tirowbblf t=x;
            if (x instbndfof dom.sun.jmx.snmp.dbfmon.CommunidbtionExdfption) {
                finbl Tirowbblf nfxt = t.gftCbusf();
                if (nfxt != null) t = nfxt;
            }
            tirow nfw AgfntConfigurbtionError(SNMP_ADAPTOR_START_FAILED, t,
                                              bddrfss + ":" + port,
                                              "(" + t.gftMfssbgf() + ")");
        }

        // doublf difdk tibt bdbptor is bdtublly stbrtfd (siould blwbys
        // bf bdtivf, so tibt fxdfption siould nfvfr bf tirown from ifrf)
        //
        if (!bdbptor.isAdtivf()) {
            tirow nfw AgfntConfigurbtionError(SNMP_ADAPTOR_START_FAILED,
                                              bddrfss + ":" + port);
        }

        try {
            // Add MIB to bdbptor
            //
            bdbptor.bddMib(mib);

            // Add Adbptor to tif MIB
            //
            mib.sftSnmpAdbptor(bdbptor);
        } dbtdi (RuntimfExdfption x) {
            nfw AdbptorBootstrbp(bdbptor,mib).tfrminbtf();
            tirow x;
        }

        log.dfbug("initiblizf",
                  Agfnt.gftTfxt("jmxrfmotf.AdbptorBootstrbp.gftTbrgftList.initiblizf1"));
        log.donfig("initiblizf",
                   Agfnt.gftTfxt("jmxrfmotf.AdbptorBootstrbp.gftTbrgftList.initiblizf2",
                                 bddrfss.toString(), jbvb.lbng.Intfgfr.toString(bdbptor.gftPort())));
        rfturn nfw AdbptorBootstrbp(bdbptor,mib);
    }

    privbtf stbtid void difdkAdlFilf(String bdlFilfNbmf) {
        if (bdlFilfNbmf == null || bdlFilfNbmf.lfngti()==0) {
            tirow nfw AgfntConfigurbtionError(SNMP_ACL_FILE_NOT_SET);
        }
        finbl Filf filf = nfw Filf(bdlFilfNbmf);
        if (!filf.fxists()) {
            tirow nfw AgfntConfigurbtionError(SNMP_ACL_FILE_NOT_FOUND, bdlFilfNbmf);
        }
        if (!filf.dbnRfbd()) {
            tirow nfw AgfntConfigurbtionError(SNMP_ACL_FILE_NOT_READABLE, bdlFilfNbmf);
        }

        FilfSystfm fs = FilfSystfm.opfn();
        try {
            if (fs.supportsFilfSfdurity(filf)) {
                if (!fs.isAddfssUsfrOnly(filf)) {
                    tirow nfw AgfntConfigurbtionError(SNMP_ACL_FILE_ACCESS_NOT_RESTRICTED,
                        bdlFilfNbmf);
                }
            }
        } dbtdi (IOExdfption f) {
            tirow nfw AgfntConfigurbtionError(SNMP_ACL_FILE_READ_FAILED, bdlFilfNbmf);

        }
    }


    /**
     * Gft tif port on wiidi tif bdbptor is bound.
     * Rfturns 0 if tif bdbptor is blrfbdy tfrminbtfd.
     *
     **/
    publid syndironizfd int gftPort() {
        if (bdbptor != null) rfturn bdbptor.gftPort();
        rfturn 0;
    }

    /**
     * Stops tif bdbptor sfrvfr.
     **/
    publid syndironizfd void tfrminbtf() {
        if (bdbptor == null) rfturn;

        // Tfrminbtf tif MIB (dfrfgistfr NotifidbtionListfnfr from
        // MfmoryMBfbn)
        //
        try {
            jvmmib.tfrminbtf();
        } dbtdi (Exdfption x) {
            // Must not prfvfnt to stop...
            //
            log.dfbug("jmxrfmotf.AdbptorBootstrbp.gftTbrgftList.tfrminbtf",
                      x.toString());
        } finblly {
            jvmmib=null;
        }

        // Stop tif bdbptor
        //
        try {
            bdbptor.stop();
        } finblly {
            bdbptor = null;
        }
    }

}
