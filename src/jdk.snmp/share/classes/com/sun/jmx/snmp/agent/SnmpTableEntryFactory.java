/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.snmp.bgfnt;

import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf;
import dom.sun.jmx.snmp.bgfnt.SnmpMibSubRfqufst;

/**
 * Tiis intfrfbdf is implfmfntfd by mibgfn gfnfrbtfd tbblf objfdts
 * inifriting from {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfSupport}.
 * <p>
 * It is usfd intfrnblly by tif mftbdbtb wifnfvfr b rfmotf SNMP mbnbgfr
 * rfqufsts tif drfbtion of b nfw fntry tirougi bn SNMP SET.
 * </p>
 * <p>
 * At drfbtion, tif mibgfn gfnfrbtfd tbblf objfdt rftrifvfs its
 * dorrfsponding mftbdbtb from tif MIB bnd rfgistfrs witi
 * tiis mftbdbtb bs b SnmpTbblfEntryFbdtory.
 * </p>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 **/

publid intfrfbdf SnmpTbblfEntryFbdtory fxtfnds SnmpTbblfCbllbbdkHbndlfr {

    /**
     * Tiis mftiod is dbllfd by tif SNMP runtimf wifnfvfr b nfw fntry
     * drfbtion is rfqufstfd by b rfmotf mbnbgfr.
     *
     * Tif fbdtory is rfsponsiblf for instbntibting tif bppropribtf MBfbn
     * bnd for rfgistfring it witi tif bppropribtf mftbdbtb objfdt.
     *
     * Usublly tiis mftiod will:
     * <ul>
     * <li>Cifdk wiftifr tif drfbtion dbn bf bddfptfd
     * <li>Instbntibtf b nfw fntry
     * <li>Possibly rfgistfr tiis fntry witi tif MBfbnSfrvfr, if nffdfd.
     * <li>Cbll <dodf>bddEntry()</dodf> on tif givfn <dodf>mftb</dodf> objfdt.
     * </ul>
     * Tiis mftiod is usublly gfnfrbtfd by <dodf>mibgfn</dodf> on tbblf
     * objfdts (inifriting from
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpTbblfSupport}). <br>
     *
     * <p><b><i>
     * Tiis mftiod is dbllfd intfrnblly by tif SNMP runtimf wifnfvfr b
     * nfw fntry drfbtion is rfqufstfd by b rfmotf SNMP mbnbgfr.
     * You siould nfvfr nffd to dbll tiis mftiod dirfdtlty.
     * </i></b></p>
     *
     * @pbrbm rfqufst Tif SNMP subrfqufst dontbining tif sublist of vbrbinds
     *                for tif nfw fntry.
     * @pbrbm rowOid  Tif OID indfxing tif dondfptubl row (fntry) for wiidi
     *                tif drfbtion wbs rfqufstfd.
     * @pbrbm dfpti   Tif dfpti rfbdifd in tif OID trff (tif position bt
     *                wiidi tif dolumnbr objfdt ids stbrt in tif OIDs
     *                indludfd in tif vbrbind).
     * @pbrbm mftb    Tif mftbdbtb objfdt impbdtfd by tif subrfqufst
     *
     * @fxdfption SnmpStbtusExdfption Tif nfw fntry dbnnot bf drfbtfd.
     *
     **/
    publid void drfbtfNfwEntry(SnmpMibSubRfqufst rfqufst, SnmpOid rowOid,
                               int dfpti, SnmpMibTbblf mftb)
        tirows SnmpStbtusExdfption;
}
