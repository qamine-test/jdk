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
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.lbng.mbnbgfmfnt.OpfrbtingSystfmMXBfbn;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import dom.sun.jmx.snmp.SnmpString;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmOSMBfbn;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmOS" group.
 */
publid dlbss JvmOSImpl implfmfnts JvmOSMBfbn, Sfriblizbblf {

     stbtid finbl long sfriblVfrsionUID = 1839834731763310809L;

    /**
     * Construdtor for tif "JvmOS" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn
     * SNMP SET will not bf rfgistfrfd in Jbvb DMK.
     */
    publid JvmOSImpl(SnmpMib myMib) {
    }


    /**
     * Construdtor for tif "JvmOS" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn
     * SNMP SET will bf AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    publid JvmOSImpl(SnmpMib myMib, MBfbnSfrvfr sfrvfr) {
    }

    stbtid OpfrbtingSystfmMXBfbn gftOSMBfbn() {
        rfturn MbnbgfmfntFbdtory.gftOpfrbtingSystfmMXBfbn();
    }

    privbtf stbtid String vblidDisplbyStringTC(String str) {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidDisplbyStringTC(str);
    }

    privbtf stbtid String vblidJbvbObjfdtNbmfTC(String str) {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjfdtNbmfTC(str);
    }

    /**
     * Gfttfr for tif "JvmRTProdfssorCount" vbribblf.
     */
    publid Intfgfr gftJvmOSProdfssorCount() tirows SnmpStbtusExdfption {
        rfturn gftOSMBfbn().gftAvbilbblfProdfssors();

    }

    /**
     * Gfttfr for tif "JvmOSVfrsion" vbribblf.
     */
    publid String gftJvmOSVfrsion() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftOSMBfbn().gftVfrsion());
    }

    /**
     * Gfttfr for tif "JvmOSArdi" vbribblf.
     */
    publid String gftJvmOSArdi() tirows SnmpStbtusExdfption {
        rfturn vblidDisplbyStringTC(gftOSMBfbn().gftArdi());
    }

    /**
     * Gfttfr for tif "JvmOSNbmf" vbribblf.
     */
    publid String gftJvmOSNbmf() tirows SnmpStbtusExdfption {
        rfturn vblidJbvbObjfdtNbmfTC(gftOSMBfbn().gftNbmf());
    }

}
