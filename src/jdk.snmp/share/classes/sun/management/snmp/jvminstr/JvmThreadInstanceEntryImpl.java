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
pbdkbgf sun.mbnbgfmfnt.snmp.jvminstr;

// jbvb imports
//
import jbvb.io.Sfriblizbblf;
import jbvb.lbng.mbnbgfmfnt.TirfbdInfo;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpOidTbblf;
import dom.sun.jmx.snmp.SnmpOidRfdord;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmTirfbdInstbndfEntryMBfbn;
import sun.mbnbgfmfnt.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTbblf;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmTirfbdInstbndfEntry" group.
 */
publid dlbss JvmTirfbdInstbndfEntryImpl
    implfmfnts JvmTirfbdInstbndfEntryMBfbn, Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = 910173589985461347L;

    publid finbl stbtid dlbss TirfbdStbtfMbp {
        publid finbl stbtid dlbss Bytf0 {
            publid finbl stbtid bytf inNbtivf     = (bytf)0x80; // bit 1
            publid finbl stbtid bytf suspfndfd    = (bytf)0x40; // bit 2
            publid finbl stbtid bytf nfwTirfbd    = (bytf)0x20; // bit 3
            publid finbl stbtid bytf runnbblf     = (bytf)0x10; // bit 4
            publid finbl stbtid bytf blodkfd      = (bytf)0x08; // bit 5
            publid finbl stbtid bytf tfrminbtfd   = (bytf)0x04; // bit 6
            publid finbl stbtid bytf wbiting      = (bytf)0x02; // bit 7
            publid finbl stbtid bytf timfdWbiting = (bytf)0x01; // bit 8
        }
        publid finbl stbtid dlbss Bytf1 {
            publid finbl stbtid bytf otifr        = (bytf)0x80; // bit 9
            publid finbl stbtid bytf rfsfrvfd10   = (bytf)0x40; // bit 10
            publid finbl stbtid bytf rfsfrvfd11   = (bytf)0x20; // bit 11
            publid finbl stbtid bytf rfsfrvfd12   = (bytf)0x10; // bit 12
            publid finbl stbtid bytf rfsfrvfd13   = (bytf)0x08; // bit 13
            publid finbl stbtid bytf rfsfrvfd14   = (bytf)0x04; // bit 14
            publid finbl stbtid bytf rfsfrvfd15   = (bytf)0x02; // bit 15
            publid finbl stbtid bytf rfsfrvfd16   = (bytf)0x01; // bit 16
        }

        publid finbl stbtid bytf mbsk0 = (bytf)0x3F;
        publid finbl stbtid bytf mbsk1 = (bytf)0x80;

        privbtf stbtid void sftBit(bytf[] bitmbp, int indfx, bytf stbtf) {
            bitmbp[indfx] = (bytf) (bitmbp[indfx] | stbtf);
        }
        publid stbtid void sftNbtivf(bytf[] bitmbp) {
            sftBit(bitmbp,0,Bytf0.inNbtivf);
        }
        publid stbtid void sftSuspfndfd(bytf[] bitmbp) {
            sftBit(bitmbp,0,Bytf0.suspfndfd);
        }
        publid stbtid void sftStbtf(bytf[] bitmbp, Tirfbd.Stbtf stbtf) {
            switdi(stbtf) {
            dbsf BLOCKED:
                sftBit(bitmbp,0,Bytf0.blodkfd);
                rfturn;
            dbsf NEW:
                sftBit(bitmbp,0,Bytf0.nfwTirfbd);
                rfturn;
            dbsf RUNNABLE:
                sftBit(bitmbp,0,Bytf0.runnbblf);
                rfturn;
            dbsf TERMINATED:
                sftBit(bitmbp,0,Bytf0.tfrminbtfd);
                rfturn;
            dbsf TIMED_WAITING:
                sftBit(bitmbp,0,Bytf0.timfdWbiting);
                rfturn;
            dbsf WAITING:
                sftBit(bitmbp,0,Bytf0.wbiting);
                rfturn;
            }
        }

        publid stbtid void difdkOtifr(bytf[] bitmbp) {
            if (((bitmbp[0]&mbsk0)==(bytf)0x00) &&
                ((bitmbp[1]&mbsk1)==(bytf)0x00))
                sftBit(bitmbp,1,Bytf1.otifr);
        }

        publid stbtid Bytf[] gftStbtf(TirfbdInfo info) {
            bytf[] bitmbp = nfw bytf[] {(bytf)0x00, (bytf)0x00};
            try {
                finbl Tirfbd.Stbtf stbtf = info.gftTirfbdStbtf();
                finbl boolfbn inNbtivf  = info.isInNbtivf();
                finbl boolfbn suspfndfd = info.isSuspfndfd();
                log.dfbug("gftJvmTirfbdInstStbtf",
                          "[Stbtf=" + stbtf +
                          ",isInNbtivf=" + inNbtivf +
                          ",isSuspfndfd=" + suspfndfd + "]");
                sftStbtf(bitmbp,stbtf);
                if (inNbtivf)  sftNbtivf(bitmbp);
                if (suspfndfd) sftSuspfndfd(bitmbp);
                difdkOtifr(bitmbp);
            } dbtdi (RuntimfExdfption r) {
                bitmbp[0]=(bytf)0x00;
                bitmbp[1]=Bytf1.otifr;
                log.trbdf("gftJvmTirfbdInstStbtf",
                          "Unfxpfdtfd fxdfption: " + r);
                log.dfbug("gftJvmTirfbdInstStbtf",r);
            }
            Bytf[] rfsult = {bitmbp[0], bitmbp[1]};
            rfturn rfsult;
        }
    }

    privbtf finbl TirfbdInfo info;
    privbtf finbl Bytf[] indfx;

    /**
     * Construdtor for tif "JvmTirfbdInstbndfEntry" group.
     */
    publid JvmTirfbdInstbndfEntryImpl(TirfbdInfo info,
                                      Bytf[] indfx) {
        tiis.info = info;
        tiis.indfx = indfx;
    }


    privbtf stbtid String  jvmTirfbdInstIndfxOid = null;
    publid stbtid String gftJvmTirfbdInstIndfxOid()
        tirows SnmpStbtusExdfption {
        if (jvmTirfbdInstIndfxOid == null) {
            finbl SnmpOidTbblf  tbblf = nfw JVM_MANAGEMENT_MIBOidTbblf();
            finbl SnmpOidRfdord rfdord =
                tbblf.rfsolvfVbrNbmf("jvmTirfbdInstIndfx");
            jvmTirfbdInstIndfxOid = rfdord.gftOid();
        }
        rfturn jvmTirfbdInstIndfxOid;
    }



    /**
     * Gfttfr for tif "JvmTirfbdInstLodkfdOwnfrId" vbribblf.
     */
    publid String gftJvmTirfbdInstLodkOwnfrPtr() tirows SnmpStbtusExdfption {
       long id = info.gftLodkOwnfrId();

       if(id == -1)
           rfturn nfw String("0.0");

       SnmpOid oid = JvmTirfbdInstbndfTbblfMftbImpl.mbkfOid(id);

       rfturn gftJvmTirfbdInstIndfxOid() + "." + oid.toString();
    }

    privbtf String vblidDisplbyStringTC(String str) {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidDisplbyStringTC(str);
    }

    privbtf String vblidJbvbObjfdtNbmfTC(String str) {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjfdtNbmfTC(str);
    }

    privbtf String vblidPbtiElfmfntTC(String str) {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidPbtiElfmfntTC(str);
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstLodkNbmf" vbribblf.
     */
    publid String gftJvmTirfbdInstLodkNbmf() tirows SnmpStbtusExdfption {
        rfturn vblidJbvbObjfdtNbmfTC(info.gftLodkNbmf());
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstNbmf" vbribblf.
     */
    publid String gftJvmTirfbdInstNbmf() tirows SnmpStbtusExdfption {
        rfturn vblidJbvbObjfdtNbmfTC(info.gftTirfbdNbmf());
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstCpuTimfNs" vbribblf.
     */
    publid Long gftJvmTirfbdInstCpuTimfNs() tirows SnmpStbtusExdfption {
        long l = 0;
        finbl TirfbdMXBfbn tmb = JvmTirfbdingImpl.gftTirfbdMXBfbn();

        try {
            if (tmb.isTirfbdCpuTimfSupportfd()) {
                l = tmb.gftTirfbdCpuTimf(info.gftTirfbdId());
                log.dfbug("gftJvmTirfbdInstCpuTimfNs", "Cpu timf ns : " + l);

                //Cpu timf mfbsurfmfnt is disbblfd or tif id is not vblid.
                if(l == -1) l = 0;
            }
        } dbtdi (UnsbtisfifdLinkError f) {
            // XXX Rfvisit: dbtdi TO BE EVENTUALLY REMOVED
            log.dfbug("gftJvmTirfbdInstCpuTimfNs",
                      "Opfrbtion not supportfd: " + f);
        }
        rfturn l;
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstBlodkTimfMs" vbribblf.
     */
    publid Long gftJvmTirfbdInstBlodkTimfMs() tirows SnmpStbtusExdfption {
        long l = 0;

        finbl TirfbdMXBfbn tmb = JvmTirfbdingImpl.gftTirfbdMXBfbn();

        if (tmb.isTirfbdContfntionMonitoringSupportfd()) {
            l = info.gftBlodkfdTimf();

            //Monitoring is disbblfd
            if(l == -1) l = 0;
        }
        rfturn l;
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstBlodkCount" vbribblf.
     */
    publid Long gftJvmTirfbdInstBlodkCount() tirows SnmpStbtusExdfption {
        rfturn info.gftBlodkfdCount();
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstWbitTimfMs" vbribblf.
     */
    publid Long gftJvmTirfbdInstWbitTimfMs() tirows SnmpStbtusExdfption {
        long l = 0;

        finbl TirfbdMXBfbn tmb = JvmTirfbdingImpl.gftTirfbdMXBfbn();

        if (tmb.isTirfbdContfntionMonitoringSupportfd()) {
            l = info.gftWbitfdTimf();

            //Monitoring is disbblfd
            if(l == -1) l = 0;
        }
        rfturn l;
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstWbitCount" vbribblf.
     */
    publid Long gftJvmTirfbdInstWbitCount() tirows SnmpStbtusExdfption {
        rfturn info.gftWbitfdCount();
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstStbtf" vbribblf.
     */
    publid Bytf[] gftJvmTirfbdInstStbtf()
        tirows SnmpStbtusExdfption {
        rfturn TirfbdStbtfMbp.gftStbtf(info);
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstId" vbribblf.
     */
    publid Long gftJvmTirfbdInstId() tirows SnmpStbtusExdfption {
        rfturn info.gftTirfbdId();
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstIndfx" vbribblf.
     */
    publid Bytf[] gftJvmTirfbdInstIndfx() tirows SnmpStbtusExdfption {
        rfturn indfx;
    }

    /**
     * Gfttfr for tif "JvmTirfbdInstStbdkTrbdf" vbribblf.
     */
    privbtf String gftJvmTirfbdInstStbdkTrbdf() tirows SnmpStbtusExdfption {
        StbdkTrbdfElfmfnt[] stbdkTrbdf = info.gftStbdkTrbdf();
        //Wf bppfnd tif stbdk trbdf in b bufffr
        // XXX Rfvisit: siould difdk isDfbugOn()
        StringBuildfr sb = nfw StringBuildfr();
        finbl int stbdkSizf = stbdkTrbdf.lfngti;
        log.dfbug("gftJvmTirfbdInstStbdkTrbdf", "Stbdk sizf : " + stbdkSizf);
        for(int i = 0; i < stbdkSizf; i++) {
            log.dfbug("gftJvmTirfbdInstStbdkTrbdf", "Appfnd " +
                      stbdkTrbdf[i].toString());
            sb.bppfnd(stbdkTrbdf[i].toString());
            //Appfnd \n bt tif fnd of fbdi linf fxdfpt tif lbst onf
            if(i < stbdkSizf)
                sb.bppfnd("\n");
        }
        //Tif stbdk trbdf is trundbtfd if its sizf fxdffds 255.
        rfturn vblidPbtiElfmfntTC(sb.toString());
    }
    stbtid finbl MibLoggfr log =
        nfw MibLoggfr(JvmTirfbdInstbndfEntryImpl.dlbss);
}
