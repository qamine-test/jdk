/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// jmx imports
//
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//


import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmMgrPoolRflEntryMBfbn;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmMfmMgrPoolRflEntry" group.
 */
publid dlbss JvmMfmMgrPoolRflEntryImpl
    implfmfnts JvmMfmMgrPoolRflEntryMBfbn {

    /**
     * Vbribblf for storing tif vbluf of "JvmMfmMbnbgfrIndfx".
     *
     * "An indfx opbqufly domputfd by tif bgfnt bnd wiidi uniqufly
     * idfntififs b Mfmory Mbnbgfr."
     *
     */
    finbl protfdtfd int JvmMfmMbnbgfrIndfx;

    /**
     * Vbribblf for storing tif vbluf of "JvmMfmPoolIndfx".
     *
     * "An indfx vbluf opbqufly domputfd by tif bgfnt wiidi uniqufly
     * idfntififs b row in tif jvmMfmPoolTbblf.
     * "
     *
     */
    finbl protfdtfd int JvmMfmPoolIndfx;
    finbl protfdtfd String mmmNbmf;
    finbl protfdtfd String mpmNbmf;

    /**
     * Construdtor for tif "JvmMfmMgrPoolRflEntry" group.
     */
    publid JvmMfmMgrPoolRflEntryImpl(String mmmNbmf,
                                     String mpmNbmf,
                                     int mmbrd, int mpbrd) {
        JvmMfmMbnbgfrIndfx = mmbrd;
        JvmMfmPoolIndfx    = mpbrd;

        tiis.mmmNbmf = mmmNbmf;
        tiis.mpmNbmf = mpmNbmf;
    }

    /**
     * Gfttfr for tif "JvmMfmMgrRflPoolNbmf" vbribblf.
     */
    publid String gftJvmMfmMgrRflPoolNbmf() tirows SnmpStbtusExdfption {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjfdtNbmfTC(mpmNbmf);
    }

    /**
     * Gfttfr for tif "JvmMfmMgrRflMbnbgfrNbmf" vbribblf.
     */
    publid String gftJvmMfmMgrRflMbnbgfrNbmf() tirows SnmpStbtusExdfption {
        rfturn JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjfdtNbmfTC(mmmNbmf);
    }

    /**
     * Gfttfr for tif "JvmMfmMbnbgfrIndfx" vbribblf.
     */
    publid Intfgfr gftJvmMfmMbnbgfrIndfx() tirows SnmpStbtusExdfption {
        rfturn JvmMfmMbnbgfrIndfx;
    }

    /**
     * Gfttfr for tif "JvmMfmPoolIndfx" vbribblf.
     */
    publid Intfgfr gftJvmMfmPoolIndfx() tirows SnmpStbtusExdfption {
        rfturn JvmMfmPoolIndfx;
    }

}
