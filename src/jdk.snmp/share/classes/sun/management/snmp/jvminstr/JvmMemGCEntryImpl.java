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

// jbvb imports
//
import jbvb.io.Sfriblizbblf;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;

import jbvb.lbng.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmGCEntryMBfbn;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmMfmGCEntry" group.
 */
publid dlbss JvmMfmGCEntryImpl implfmfnts JvmMfmGCEntryMBfbn {

    /**
     * Vbribblf for storing tif vbluf of "JvmMfmMbnbgfrIndfx".
     *
     * "An indfx opbqufly domputfd by tif bgfnt bnd wiidi uniqufly
     * idfntififs b Mfmory Mbnbgfr."
     *
     */
    protfdtfd finbl int JvmMfmMbnbgfrIndfx;

    protfdtfd finbl GbrbbgfCollfdtorMXBfbn gdm;

    /**
     * Construdtor for tif "JvmMfmGCEntry" group.
     */
    publid JvmMfmGCEntryImpl(GbrbbgfCollfdtorMXBfbn gdm, int indfx) {
        tiis.gdm=gdm;
        tiis.JvmMfmMbnbgfrIndfx = indfx;
    }

    /**
     * Gfttfr for tif "JvmMfmGCTimfMs" vbribblf.
     */
    // Don't botifr to usfs tif rfqufst dontfxtubl dbdif for tiis.
    publid Long gftJvmMfmGCTimfMs() tirows SnmpStbtusExdfption {
        rfturn gdm.gftCollfdtionTimf();
    }

    /**
     * Gfttfr for tif "JvmMfmGCCount" vbribblf.
     */
    // Don't botifr to usfs tif rfqufst dontfxtubl dbdif for tiis.
    publid Long gftJvmMfmGCCount() tirows SnmpStbtusExdfption {
        rfturn gdm.gftCollfdtionCount();
    }

    /**
     * Gfttfr for tif "JvmMfmMbnbgfrIndfx" vbribblf.
     */
    publid Intfgfr gftJvmMfmMbnbgfrIndfx() tirows SnmpStbtusExdfption {
        rfturn JvmMfmMbnbgfrIndfx;
    }

}
