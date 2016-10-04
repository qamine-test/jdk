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
 * {@link dom.sun.jmx.snmp.bgfnt.SnmpGfnfridObjfdtSfrvfr} objfdt.
 * </p>
 *
 * <p>
 * All tifsf mftiods brf usublly gfnfrbtfd by <dodf>mibgfn</dodf> wifn
 * run in gfnfrid-mftbdbtb modf.
 * </p>
 *
 * <p><b><i>
 * Tiis intfrfbdf is usfd intfrnblly bftwffn tif gfnfrbtfd Mftbdbtb bnd
 * tif SNMP runtimf bnd you siouldn't nffd to worry bbout it, bfdbusf
 * you will nfvfr ibvf to usf it dirfdtly.
 * </b></i></p>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 **/
publid intfrfbdf SnmpGfnfridMftbSfrvfr {

    /**
     * Construdt bn bttributf vbluf (bs rfturnfd by Attributf::gftVbluf())
     * from bn SnmpVbluf. Tif rfturnfd bttributf vbluf dbn bf usfd to
     * donstrudt bn Attributf objfdt.
     *
     * @pbrbm id Tif OID brd idfntifying tif vbribblf for wiidi tif
     *           vbluf is donstrudtfd.
     * @pbrbm vbluf Tif SnmpVbluf from wiidi tif Attributf::vbluf will bf
     *              donstrudtfd.
     * @rfturn Tif bttributf vbluf built from tif givfn <dodf>vbluf</dodf>.
     * @fxdfption SnmpStbtusExdfption if tif bttributf vbluf dbnnot bf built
     *            from tif givfn SnmpVbluf <dodf>vbluf</dodf>.
     *
     */
    Objfdt buildAttributfVbluf(long id, SnmpVbluf vbluf)
        tirows SnmpStbtusExdfption;

    /**
     * Construdt bn SnmpVbluf from bn Attributf vbluf bs rfturnfd by
     * Attributf::gftVbluf().
     *
     * @pbrbm id Tif OID brd idfntifying tif vbribblf for wiidi tif
     *           vbluf is donstrudtfd.
     * @pbrbm vbluf Tif bttributf vbluf bs rfturnfd by Attributf::gftVbluf().
     *
     * @rfturn Tif SnmpVbluf built from tif givfn <dodf>vbluf</dodf>.
     * @fxdfption SnmpStbtusExdfption if tif SnmpVbluf dbnnot bf built from
     *            tif givfn <dodf>vbluf</dodf>.
     **/
    SnmpVbluf buildSnmpVbluf(long id, Objfdt vbluf)
        tirows SnmpStbtusExdfption;

    /**
     * Rfturn tif nbmf of tif bttributf dorrfsponding to tif
     * SNMP vbribblf idfntififd by tif givfn <dodf>id</dodf>.
     *
     * @pbrbm id Tif OID brd idfntifying tif vbribblf.
     *
     * @rfturn Tif nbmf of tif vbribblf idfntififd by tif givfn
     *         <dodf>id</dodf>.
     *
     * @fxdfption SnmpStbtusExdfption if tif givfn <dodf>id</dodf> dofs not
     *            dorrfspond to b known vbribblf.
     */
    String gftAttributfNbmf(long id)
        tirows SnmpStbtusExdfption;

    /**
     * Cifdk tif bddfss rigits for b SET opfrbtion.
     *
     * @pbrbm x  Tif nfw rfqufstfd vbluf.
     * @pbrbm id Tif OID brd idfntifying tif vbribblf for wiidi tif SET is
     *           rfqufstfd.
     * @pbrbm dbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *           Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *           {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *           for fbdi indoming SNMP rfqufst.
     * @fxdfption SnmpStbtusExdfption if tif SET opfrbtion must bf rfjfdtfd.
     */
    void difdkSftAddfss(SnmpVbluf x, long id, Objfdt dbtb)
        tirows SnmpStbtusExdfption;

    /**
     * Cifdk tif bddfss rigits for b GET opfrbtion.
     *
     * @pbrbm id Tif OID brd idfntifying tif vbribblf for wiidi tif SET is
     *           rfqufstfd.
     * @pbrbm dbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *           Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *           {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *           for fbdi indoming SNMP rfqufst.
     * @fxdfption SnmpStbtusExdfption if tif SET opfrbtion must bf rfjfdtfd.
     */
    void difdkGftAddfss(long id, Objfdt dbtb)
        tirows SnmpStbtusExdfption;

}
