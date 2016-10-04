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

// jmx imports
//
import dom.sun.jmx.snmp.SnmpVbluf;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

/**
 * <p>
 * Tiis intfrfbdf dffinfs tif mftiods tibt must bf implfmfntfd by bn
 * SNMP mftbdbtb objfdt tibt nffds to intfrbdt witi bn
 * {@link dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdObjfdtSfrvfr} objfdt.
 * </p>
 * <p>
 * All tifsf mftiods brf usublly gfnfrbtfd by <dodf>mibgfn</dodf> wifn
 * run in stbndbrd-mftbdbtb modf (dffbult).
 * </p>
 * <p><b><i>
 * Tiis intfrfbdf is usfd intfrnblly bftwffn tif gfnfrbtfd Mftbdbtb bnd
 * tif SNMP runtimf bnd you siouldn't nffd to worry bbout it, bfdbusf
 * you will nfvfr ibvf to usf it dirfdtly.
 * </b></i></p>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 **/
publid intfrfbdf SnmpStbndbrdMftbSfrvfr {
    /**
     * Rfturns tif vbluf of tif sdblbr objfdt idfntififd by tif givfn
     * OID brd.
     *
     * @pbrbm brd OID brd of tif qufrrifd sdblbr objfdt.
     *
     * @rfturn Tif <CODE>SnmpVbluf</CODE> of tif sdblbr objfdt idfntififd
     *         by <CODE>brd</CODE>.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @fxdfption SnmpStbtusExdfption If tif brd is not vblid, or if
     *    bddfss is dfnifd.
     *
     **/
    publid SnmpVbluf gft(long brd, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption ;

    /**
     * Sfts tif vbluf of tif sdblbr objfdt idfntififd by tif givfn
     * OID brd.
     *
     * @pbrbm x Nfw vbluf for tif sdblbr objfdt idfntififd by
     *    <CODE>brd</CODE>
     *
     * @pbrbm brd OID brd of tif sdblbr objfdt wiosf vbluf is sft.
     *
     * @rfturn Tif nfw <CODE>SnmpVbluf</CODE> of tif sdblbr objfdt
     *    idfntififd by <CODE>brd</CODE>.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @fxdfption SnmpStbtusExdfption If tif brd is not vblid, or if
     *    bddfss is dfnifd.
     *
     **/
    publid SnmpVbluf sft(SnmpVbluf x, long brd, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption ;

    /**
     * Cifdks tibt tif nfw dfsirfd vbluf of tif sdblbr objfdt idfntififd
     * by tif givfn OID brd is vblid.
     *
     * @pbrbm x Nfw vbluf for tif sdblbr objfdt idfntififd by
     *    <CODE>brd</CODE>
     *
     * @pbrbm brd OID brd of tif sdblbr objfdt wiosf vbluf is sft.
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @fxdfption SnmpStbtusExdfption If tif brd is not vblid, or if
     *    bddfss is dfnifd, or if tif nfw dfsirfd vbluf is not vblid.
     *
     **/
    publid void difdk(SnmpVbluf x, long brd, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption ;

}
