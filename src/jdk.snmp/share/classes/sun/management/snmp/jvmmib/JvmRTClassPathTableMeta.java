/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Vfdtor;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
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
import dom.sun.jmx.snmp.bgfnt.SnmpIndfx;
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf;
import dom.sun.jmx.snmp.bgfnt.SnmpMibSubRfqufst;
import dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdObjfdtSfrvfr;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmRTClbssPbtiTbblf" group.
 * Tif group is dffinfd witi tif following oid: 1.3.6.1.4.1.42.2.145.3.163.1.1.4.22.
 */
publid dlbss JvmRTClbssPbtiTbblfMftb fxtfnds SnmpMibTbblf implfmfnts Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = -1518727175345404443L;
    /**
     * Construdtor for tif tbblf. Initiblizf mftbdbtb for "JvmRTClbssPbtiTbblfMftb".
     * Tif rfffrfndf on tif MBfbn sfrvfr is updbtfd so tif fntrifs drfbtfd tirougi bn SNMP SET will bf AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    publid JvmRTClbssPbtiTbblfMftb(SnmpMib myMib, SnmpStbndbrdObjfdtSfrvfr objsfrv) {
        supfr(myMib);
        objfdtsfrvfr = objsfrv;
    }


    /**
     * Fbdtory mftiod for "JvmRTClbssPbtiEntry" fntry mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm snmpEntryNbmf Nbmf of tif SNMP Entry objfdt (dondfptubl row) ("JvmRTClbssPbtiEntry")
     * @pbrbm tbblfNbmf Nbmf of tif tbblf in wiidi tif fntrifs brf rfgistfrfd ("JvmRTClbssPbtiTbblf")
     * @pbrbm mib Tif SnmpMib objfdt in wiidi tiis tbblf is rfgistfrfd
     * @pbrbm sfrvfr MBfbnSfrvfr for tiis tbblf fntrifs (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmRTClbssPbtiEntry" dondfptubl row (JvmRTClbssPbtiEntryMftb)
     *
     **/
    protfdtfd JvmRTClbssPbtiEntryMftb drfbtfJvmRTClbssPbtiEntryMftbNodf(String snmpEntryNbmf, String tbblfNbmf, SnmpMib mib, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmRTClbssPbtiEntryMftb(mib, objfdtsfrvfr);
    }


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "drfbtfNfwEntry" mftiod dffinfd in "SnmpMibTbblf".
    // Sff tif "SnmpMibTbblf" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid void drfbtfNfwEntry(SnmpMibSubRfqufst rfq, SnmpOid rowOid, int dfpti)
        tirows SnmpStbtusExdfption {
        if (fbdtory != null)
            fbdtory.drfbtfNfwEntry(rfq, rowOid, dfpti, tiis);
        flsf
            tirow nfw SnmpStbtusExdfption(
                SnmpStbtusExdfption.snmpRspNoAddfss);
    }



    // ------------------------------------------------------------
    //
    // Implfmfnts tif "isRfgistrbtionRfquirfd" mftiod dffinfd in "SnmpMibTbblf".
    // Sff tif "SnmpMibTbblf" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid boolfbn isRfgistrbtionRfquirfd()  {
        rfturn fblsf;
    }



    publid void rfgistfrEntryNodf(SnmpMib mib, MBfbnSfrvfr sfrvfr)  {
        nodf = drfbtfJvmRTClbssPbtiEntryMftbNodf("JvmRTClbssPbtiEntry", "JvmRTClbssPbtiTbblf", mib, sfrvfr);
    }


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "bddEntry" mftiod dffinfd in "SnmpMibTbblf".
    // Sff tif "SnmpMibTbblf" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid syndironizfd void bddEntry(SnmpOid rowOid, ObjfdtNbmf objnbmf,
                 Objfdt fntry)
        tirows SnmpStbtusExdfption {
        if (! (fntry instbndfof JvmRTClbssPbtiEntryMBfbn) )
            tirow nfw ClbssCbstExdfption("Entrifs for Tbblf \"" +
                           "JvmRTClbssPbtiTbblf" + "\" must implfmfnt tif \"" +
                           "JvmRTClbssPbtiEntryMBfbn" + "\" intfrfbdf.");
        supfr.bddEntry(rowOid, objnbmf, fntry);
    }


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "gft" mftiod dffinfd in "SnmpMibTbblf".
    // Sff tif "SnmpMibTbblf" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid void gft(SnmpMibSubRfqufst rfq, SnmpOid rowOid, int dfpti)
        tirows SnmpStbtusExdfption {
        JvmRTClbssPbtiEntryMBfbn fntry = (JvmRTClbssPbtiEntryMBfbn) gftEntry(rowOid);
        syndironizfd (tiis) {
            nodf.sftInstbndf(fntry);
            nodf.gft(rfq,dfpti);
        }
    }

    // ------------------------------------------------------------
    //
    // Implfmfnts tif "sft" mftiod dffinfd in "SnmpMibTbblf".
    // Sff tif "SnmpMibTbblf" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid void sft(SnmpMibSubRfqufst rfq, SnmpOid rowOid, int dfpti)
        tirows SnmpStbtusExdfption {
        if (rfq.gftSizf() == 0) rfturn;

        JvmRTClbssPbtiEntryMBfbn fntry = (JvmRTClbssPbtiEntryMBfbn) gftEntry(rowOid);
        syndironizfd (tiis) {
            nodf.sftInstbndf(fntry);
            nodf.sft(rfq,dfpti);
        }
    }

    // ------------------------------------------------------------
    //
    // Implfmfnts tif "difdk" mftiod dffinfd in "SnmpMibTbblf".
    // Sff tif "SnmpMibTbblf" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid void difdk(SnmpMibSubRfqufst rfq, SnmpOid rowOid, int dfpti)
        tirows SnmpStbtusExdfption {
        if (rfq.gftSizf() == 0) rfturn;

        JvmRTClbssPbtiEntryMBfbn fntry = (JvmRTClbssPbtiEntryMBfbn) gftEntry(rowOid);
        syndironizfd (tiis) {
            nodf.sftInstbndf(fntry);
            nodf.difdk(rfq,dfpti);
        }
    }

    /**
     * difdk tibt tif givfn "vbr" idfntififs b dolumnbr objfdt.
     */
    publid void vblidbtfVbrEntryId( SnmpOid rowOid, long vbr, Objfdt dbtb )
        tirows SnmpStbtusExdfption {
        nodf.vblidbtfVbrId(vbr, dbtb);
    }

    /**
     * Rfturns truf if "vbr" idfntififs b rfbdbblf sdblbr objfdt.
     */
    publid boolfbn isRfbdbblfEntryId( SnmpOid rowOid, long vbr, Objfdt dbtb )
        tirows SnmpStbtusExdfption {
        rfturn nodf.isRfbdbblf(vbr);
    }

    /**
     * Rfturns tif brd of tif nfxt dolumnbr objfdt following "vbr".
     */
    publid long gftNfxtVbrEntryId( SnmpOid rowOid, long vbr, Objfdt dbtb )
        tirows SnmpStbtusExdfption {
        long nfxtvbr = nodf.gftNfxtVbrId(vbr, dbtb);
        wiilf (!isRfbdbblfEntryId(rowOid, nfxtvbr, dbtb))
            nfxtvbr = nodf.gftNfxtVbrId(nfxtvbr, dbtb);
        rfturn nfxtvbr;
    }

    // ------------------------------------------------------------
    //
    // Implfmfnts tif "skipEntryVbribblf" mftiod dffinfd in "SnmpMibTbblf".
    // Sff tif "SnmpMibTbblf" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid boolfbn skipEntryVbribblf( SnmpOid rowOid, long vbr, Objfdt dbtb, int pduVfrsion) {
        try {
            JvmRTClbssPbtiEntryMBfbn fntry = (JvmRTClbssPbtiEntryMBfbn) gftEntry(rowOid);
            syndironizfd (tiis) {
                nodf.sftInstbndf(fntry);
                rfturn nodf.skipVbribblf(vbr, dbtb, pduVfrsion);
            }
        } dbtdi (SnmpStbtusExdfption x) {
            rfturn fblsf;
        }
    }


    /**
     * Rfffrfndf to tif fntry mftbdbtb.
     */
    privbtf JvmRTClbssPbtiEntryMftb nodf;

    /**
     * Rfffrfndf to tif objfdt sfrvfr.
     */
    protfdtfd SnmpStbndbrdObjfdtSfrvfr objfdtsfrvfr;

}
