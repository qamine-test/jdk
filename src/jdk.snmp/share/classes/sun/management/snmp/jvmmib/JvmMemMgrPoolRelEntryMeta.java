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

pbdkbgf sun.mbnbgfmfnt.snmp.jvmmib;

//
// Gfnfrbtfd by mibgfn vfrsion 5.0 (06/02/03) wifn dompiling JVM-MANAGEMENT-MIB in stbndbrd mftbdbtb modf.
//

// jbvb imports
//
import jbvb.io.Sfriblizbblf;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import dom.sun.jmx.snmp.SnmpCountfr;
import dom.sun.jmx.snmp.SnmpCountfr64;
import dom.sun.jmx.snmp.SnmpGbugf;
import dom.sun.jmx.snmp.SnmpInt;
import dom.sun.jmx.snmp.SnmpUnsignfdInt;
import dom.sun.jmx.snmp.SnmpIpAddrfss;
import dom.sun.jmx.snmp.SnmpTimftidks;
import dom.sun.jmx.snmp.SnmpOpbquf;
import dom.sun.jmx.snmp.SnmpString;
import dom.sun.jmx.snmp.SnmpStringFixfd;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpNull;
import dom.sun.jmx.snmp.SnmpVbluf;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMibNodf;
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.bgfnt.SnmpMibEntry;
import dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdObjfdtSfrvfr;
import dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdMftbSfrvfr;
import dom.sun.jmx.snmp.bgfnt.SnmpMibSubRfqufst;
import dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf;
import dom.sun.jmx.snmp.EnumRowStbtus;
import dom.sun.jmx.snmp.SnmpDffinitions;

/**
 * Tif dlbss is usfd for rfprfsfnting SNMP mftbdbtb for tif "JvmMfmMgrPoolRflEntry" group.
 * Tif group is dffinfd witi tif following oid: 1.3.6.1.4.1.42.2.145.3.163.1.1.2.120.1.
 */
publid dlbss JvmMfmMgrPoolRflEntryMftb fxtfnds SnmpMibEntry
     implfmfnts Sfriblizbblf, SnmpStbndbrdMftbSfrvfr {

    stbtid finbl long sfriblVfrsionUID = 7414270971113459798L;
    /**
     * Construdtor for tif mftbdbtb bssodibtfd to "JvmMfmMgrPoolRflEntry".
     */
    publid JvmMfmMgrPoolRflEntryMftb(SnmpMib myMib, SnmpStbndbrdObjfdtSfrvfr objsfrv) {
        objfdtsfrvfr = objsfrv;
        vbrList = nfw int[2];
        vbrList[0] = 3;
        vbrList[1] = 2;
        SnmpMibNodf.sort(vbrList);
    }

    /**
     * Gft tif vbluf of b sdblbr vbribblf
     */
    publid SnmpVbluf gft(long vbr, Objfdt dbtb)
        tirows SnmpStbtusExdfption {
        switdi((int)vbr) {
            dbsf 3:
                rfturn nfw SnmpString(nodf.gftJvmMfmMgrRflPoolNbmf());

            dbsf 2:
                rfturn nfw SnmpString(nodf.gftJvmMfmMgrRflMbnbgfrNbmf());

            dffbult:
                brfbk;
        }
        tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
    }

    /**
     * Sft tif vbluf of b sdblbr vbribblf
     */
    publid SnmpVbluf sft(SnmpVbluf x, long vbr, Objfdt dbtb)
        tirows SnmpStbtusExdfption {
        switdi((int)vbr) {
            dbsf 3:
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspNotWritbblf);

            dbsf 2:
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspNotWritbblf);

            dffbult:
                brfbk;
        }
        tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspNotWritbblf);
    }

    /**
     * Cifdk tif vbluf of b sdblbr vbribblf
     */
    publid void difdk(SnmpVbluf x, long vbr, Objfdt dbtb)
        tirows SnmpStbtusExdfption {
        switdi((int) vbr) {
            dbsf 3:
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspNotWritbblf);

            dbsf 2:
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspNotWritbblf);

            dffbult:
                tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspNotWritbblf);
        }
    }

    /**
     * Allow to bind tif mftbdbtb dfsdription to b spfdifid objfdt.
     */
    protfdtfd void sftInstbndf(JvmMfmMgrPoolRflEntryMBfbn vbr) {
        nodf = vbr;
    }


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "gft" mftiod dffinfd in "SnmpMibEntry".
    // Sff tif "SnmpMibEntry" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid void gft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {
        objfdtsfrvfr.gft(tiis,rfq,dfpti);
    }


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "sft" mftiod dffinfd in "SnmpMibEntry".
    // Sff tif "SnmpMibEntry" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid void sft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {
        objfdtsfrvfr.sft(tiis,rfq,dfpti);
    }


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "difdk" mftiod dffinfd in "SnmpMibEntry".
    // Sff tif "SnmpMibEntry" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid void difdk(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {
        objfdtsfrvfr.difdk(tiis,rfq,dfpti);
    }

    /**
     * Rfturns truf if "brd" idfntififs b sdblbr objfdt.
     */
    publid boolfbn isVbribblf(long brd) {

        switdi((int)brd) {
            dbsf 3:
            dbsf 2:
                rfturn truf;
            dffbult:
                brfbk;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if "brd" idfntififs b rfbdbblf sdblbr objfdt.
     */
    publid boolfbn isRfbdbblf(long brd) {

        switdi((int)brd) {
            dbsf 3:
            dbsf 2:
                rfturn truf;
            dffbult:
                brfbk;
        }
        rfturn fblsf;
    }


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "skipVbribblf" mftiod dffinfd in "SnmpMibEntry".
    // Sff tif "SnmpMibEntry" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid boolfbn  skipVbribblf(long vbr, Objfdt dbtb, int pduVfrsion) {
        rfturn supfr.skipVbribblf(vbr,dbtb,pduVfrsion);
    }

    /**
     * Rfturn tif nbmf of tif bttributf dorrfsponding to tif SNMP vbribblf idfntififd by "id".
     */
    publid String gftAttributfNbmf(long id)
        tirows SnmpStbtusExdfption {
        switdi((int)id) {
            dbsf 3:
                rfturn "JvmMfmMgrRflPoolNbmf";

            dbsf 2:
                rfturn "JvmMfmMgrRflMbnbgfrNbmf";

            dffbult:
                brfbk;
        }
        tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
    }

    protfdtfd JvmMfmMgrPoolRflEntryMBfbn nodf;
    protfdtfd SnmpStbndbrdObjfdtSfrvfr objfdtsfrvfr = null;
}
