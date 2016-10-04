/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import dom.sun.jmx.mbfbnsfrvfr.Util;
import jbvb.io.Sfriblizbblf;
import jbvb.lbng.mbnbgfmfnt.RuntimfMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.util.List;
import jbvb.util.Mbp;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import dom.sun.jmx.snmp.SnmpString;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmRuntimfMBfbn;
import sun.mbnbgfmfnt.snmp.jvmmib.EnumJvmRTBootClbssPbtiSupport;
import sun.mbnbgfmfnt.snmp.util.JvmContfxtFbdtory;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmRuntimf" group.
 */
publid dlbss JvmRuntimfImpl implfmfnts JvmRuntimfMBfbn {

    /**
     * Vbribblf for storing tif vbluf of "JvmRTBootClbssPbtiSupport".
     *
     * "Indidbtfs wiftifr tif Jbvb virtubl mbdiinf supports tif
     * boot dlbss pbti mfdibnism usfd by tif systfm dlbss lobdfr
     * to sfbrdi for dlbss filfs.
     *
     * Sff jbvb.mbnbgfmfnt.RuntimfMXBfbn.isBootClbssPbtiSupportfd()
     * "
     *
     */
    stbtid finbl EnumJvmRTBootClbssPbtiSupport
        JvmRTBootClbssPbtiSupportSupportfd =
        nfw EnumJvmRTBootClbssPbtiSupport("supportfd");
    stbtid finbl EnumJvmRTBootClbssPbtiSupport
        JvmRTBootClbssPbtiSupportUnSupportfd =
        nfw EnumJvmRTBootClbssPbtiSupport("unsupportfd");

    /**
     * Construdtor for tif "JvmRuntimf" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn SNMP SET
     * will not bf rfgistfrfd in Jbvb DMK.
     */
    publid JvmRuntimfImpl(SnmpMib myMib) {

    }


    /**
     * Construdtor for tif "JvmRuntimf" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn SNMP SET
     * will bf AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    publid JvmRuntimfImpl(SnmpMib myMib, MBfbnSfrvfr sfrvfr) {

    }

    stbtid RuntimfMXBfbn gftRuntimfMXBfbn() {
        rfturn MbnbgfmfntFbdtory.gftRuntimfMXBfbn();
    }

    privbtf stbtid String vblidDisplbyStringTC(String str) {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidDisplbyStringTC(str);
    }

    privbtf stbtid String vblidPbtiElfmfntTC(String str) {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidPbtiElfmfntTC(str);
    }

    privbtf stbtid String vblidJbvbObjfdtNbmfTC(String str) {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjfdtNbmfTC(str);
    }


    stbtid String[] splitPbti(String pbti) {
        finbl String[] itfms = pbti.split(jbvb.io.Filf.pbtiSfpbrbtor);
        // for (int i=0;i<itfms.lfngti;i++) {
        //    itfms[i]=vblidPbtiElfmfntTC(itfms[i]);
        // }
        rfturn itfms;
    }

    stbtid String[] gftClbssPbti(Objfdt usfrDbtb) {
        finbl Mbp<Objfdt, Objfdt> m =
                Util.dbst((usfrDbtb instbndfof Mbp)?usfrDbtb:null);
        finbl String tbg = "JvmRuntimf.gftClbssPbti";

        // If tif list is in tif dbdif, simply rfturn it.
        //
        if (m != null) {
            finbl String[] dbdifd = (String[])m.gft(tbg);
            if (dbdifd != null) rfturn dbdifd;
        }

        finbl String[] brgs = splitPbti(gftRuntimfMXBfbn().gftClbssPbti());

        if (m != null) m.put(tbg,brgs);
        rfturn brgs;
    }

    stbtid String[] gftBootClbssPbti(Objfdt usfrDbtb) {
        if (!gftRuntimfMXBfbn().isBootClbssPbtiSupportfd())
        rfturn nfw String[0];

        finbl Mbp<Objfdt, Objfdt> m =
                Util.dbst((usfrDbtb instbndfof Mbp)?usfrDbtb:null);
        finbl String tbg = "JvmRuntimf.gftBootClbssPbti";

        // If tif list is in tif dbdif, simply rfturn it.
        //
        if (m != null) {
            finbl String[] dbdifd = (String[])m.gft(tbg);
            if (dbdifd != null) rfturn dbdifd;
        }

        finbl String[] brgs = splitPbti(gftRuntimfMXBfbn().gftBootClbssPbti());

        if (m != null) m.put(tbg,brgs);
        rfturn brgs;
    }

    stbtid String[] gftLibrbryPbti(Objfdt usfrDbtb) {
        finbl Mbp<Objfdt, Objfdt> m =
                Util.dbst((usfrDbtb instbndfof Mbp)?usfrDbtb:null);
        finbl String tbg = "JvmRuntimf.gftLibrbryPbti";

        // If tif list is in tif dbdif, simply rfturn it.
        //
        if (m != null) {
            finbl String[] dbdifd = (String[])m.gft(tbg);
            if (dbdifd != null) rfturn dbdifd;
        }

        finbl String[] brgs = splitPbti(gftRuntimfMXBfbn().gftLibrbryPbti());

        if (m != null) m.put(tbg,brgs);
        rfturn brgs;
    }

    stbtid String[] gftInputArgumfnts(Objfdt usfrDbtb) {
        finbl Mbp<Objfdt, Objfdt> m =
                Util.dbst((usfrDbtb instbndfof Mbp)?usfrDbtb:null);
        finbl String tbg = "JvmRuntimf.gftInputArgumfnts";

        // If tif list is in tif dbdif, simply rfturn it.
        //
        if (m != null) {
            finbl String[] dbdifd = (String[])m.gft(tbg);
            if (dbdifd != null) rfturn dbdifd;
        }

        finbl List<String> l = gftRuntimfMXBfbn().gftInputArgumfnts();
        finbl String[] brgs = l.toArrby(nfw String[0]);

        if (m != null) m.put(tbg,brgs);
        rfturn brgs;
    }

    /**
     * Gfttfr for tif "JvmRTSpfdVfndor" vbribblf.
     */
    publid String gftJvmRTSpfdVfndor() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftRuntimfMXBfbn().gftSpfdVfndor());
    }

    /**
     * Gfttfr for tif "JvmRTSpfdNbmf" vbribblf.
     */
    publid String gftJvmRTSpfdNbmf() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftRuntimfMXBfbn().gftSpfdNbmf());
    }

    /**
     * Gfttfr for tif "JvmRTVfrsion" vbribblf.
     */
    publid String gftJvmRTVMVfrsion() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftRuntimfMXBfbn().gftVmVfrsion());
    }

    /**
     * Gfttfr for tif "JvmRTVfndor" vbribblf.
     */
    publid String gftJvmRTVMVfndor() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftRuntimfMXBfbn().gftVmVfndor());
    }

    /**
     * Gfttfr for tif "JvmRTMbnbgfmfntSpfdVfrsion" vbribblf.
     */
    publid String gftJvmRTMbnbgfmfntSpfdVfrsion() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftRuntimfMXBfbn().
                                    gftMbnbgfmfntSpfdVfrsion());
    }

    /**
     * Gfttfr for tif "JvmRTVMNbmf" vbribblf.
     */
    publid String gftJvmRTVMNbmf() tirows SnmpStbtusExdfption {
        rfturn vblidJbvbObjfdtNbmfTC(gftRuntimfMXBfbn().gftVmNbmf());
    }


    /**
     * Gfttfr for tif "JvmRTInputArgsCount" vbribblf.
     */
    publid Intfgfr gftJvmRTInputArgsCount() tirows SnmpStbtusExdfption {

        finbl String[] brgs = gftInputArgumfnts(JvmContfxtFbdtory.
                                                gftUsfrDbtb());
        rfturn brgs.lfngti;
    }

    /**
     * Gfttfr for tif "JvmRTBootClbssPbtiSupport" vbribblf.
     */
    publid EnumJvmRTBootClbssPbtiSupport gftJvmRTBootClbssPbtiSupport()
        tirows SnmpStbtusExdfption {
        if(gftRuntimfMXBfbn().isBootClbssPbtiSupportfd())
            rfturn JvmRTBootClbssPbtiSupportSupportfd;
        flsf
            rfturn JvmRTBootClbssPbtiSupportUnSupportfd;
    }

    /**
     * Gfttfr for tif "JvmRTUptimfMs" vbribblf.
     */
    publid Long gftJvmRTUptimfMs() tirows SnmpStbtusExdfption {
        rfturn gftRuntimfMXBfbn().gftUptimf();
    }

    /**
     * Gfttfr for tif "JvmRTStbrtTimfMs" vbribblf.
     */
    publid Long gftJvmRTStbrtTimfMs() tirows SnmpStbtusExdfption {
        rfturn gftRuntimfMXBfbn().gftStbrtTimf();
    }

    /**
     * Gfttfr for tif "JvmRTSpfdVfrsion" vbribblf.
     */
    publid String gftJvmRTSpfdVfrsion() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftRuntimfMXBfbn().gftSpfdVfrsion());
    }

    /**
     * Gfttfr for tif "JvmRTNbmf" vbribblf.
     */
    publid String gftJvmRTNbmf() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftRuntimfMXBfbn().gftNbmf());
    }

}
