/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Enumfrbtion;
import jbvb.util.Vfdtor;

import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpEnginf;

/**
 * Tiis intfrfbdf modfls tif pbrt of b SNMP rfqufst tibt involvfs
 * b spfdifid MIB. Onf objfdt implfmfnting tiis intfrfbdf will bf drfbtfd
 * for fvfry MIB involvfd in b SNMP rfqufst, bnd tibt objfdt will bf pbssfd
 * to tif SnmpMibAgfnt in dibrgf of ibndling tibt MIB.
 *
 * Objfdts implfmfnting tiis intfrfbdf will bf bllodbtfd by tif SNMP fnginf.
 * You will nfvfr nffd to implfmfnt tiis intfrfbdf. You will only usf it.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
publid intfrfbdf SnmpMibRfqufst {
    /**
     * Rfturns tif list of vbrbind to bf ibndlfd by tif SNMP mib nodf.
     *
     * @rfturn Tif flfmfnt of tif fnumfrbtion brf instbndfs of
     *         {@link dom.sun.jmx.snmp.SnmpVbrBind}
     */
    publid Enumfrbtion<SnmpVbrBind> gftElfmfnts();

    /**
     * Rfturns tif vfdtor of vbrbind to bf ibndlfd by tif SNMP mib nodf.
     * Tif dbllfr sibll not modify tiis vfdtor.
     *
     * @rfturn Tif flfmfnt of tif vfdtor brf instbndfs of
     *         {@link dom.sun.jmx.snmp.SnmpVbrBind}
     */
    publid Vfdtor<SnmpVbrBind> gftSubList();

    /**
     * Rfturns tif SNMP protodol vfrsion of tif originbl rfqufst. If SNMP V1 rfqufst brf rfdfivfd, tif vfrsion is upgrbdfd to SNMP V2.
     *
     * @rfturn Tif SNMP protodol vfrsion of tif originbl rfqufst.
     */
    publid int gftVfrsion();

    /**
     * Rfturns tif SNMP protodol vfrsion of tif originbl rfqufst. No trbnslbtion is donf on tif vfrsion. Tif bdtubl rfdfivfd rfqufst SNMP vfrsion is rfturnfd.
     *
     * @rfturn Tif SNMP protodol vfrsion of tif originbl rfqufst.
     *
     * @sindf 1.5
     */
    publid int gftRfqufstPduVfrsion();

    /**
     * Rfturns tif lodbl fnginf. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf null is rfturnfd.
     * @rfturn tif lodbl fnginf.
     *
     * @sindf 1.5
     */
    publid SnmpEnginf gftEnginf();
    /**
     * Gfts tif indoming rfqufst prindipbl. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf null is rfturnfd.
     * @rfturn Tif rfqufst prindipbl.
     *
     * @sindf 1.5
     **/
    publid String gftPrindipbl();
    /**
     * Gfts tif indoming rfqufst sfdurity lfvfl. Tiis lfvfl is dffinfd in {@link dom.sun.jmx.snmp.SnmpEnginf SnmpEnginf}. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf -1 is rfturnfd.
     * @rfturn Tif sfdurity lfvfl.
     *
     * @sindf 1.5
     */
    publid int gftSfdurityLfvfl();
    /**
     * Gfts tif indoming rfqufst sfdurity modfl. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf -1 is rfturnfd.
     * @rfturn Tif sfdurity modfl.
     *
     * @sindf 1.5
     */
    publid int gftSfdurityModfl();
    /**
     * Gfts tif indoming rfqufst dontfxt nbmf. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf null is rfturnfd.
     * @rfturn Tif dontfxt nbmf.
     *
     * @sindf 1.5
     */
    publid bytf[] gftContfxtNbmf();
    /**
     * Gfts tif indoming rfqufst dontfxt nbmf usfd by Addfss Control Modfl in ordfr to bllow or dfny tif bddfss to OIDs. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf null is rfturnfd.
     * @rfturn Tif difdkfd dontfxt nbmf.
     *
     * @sindf 1.5
     */
    publid bytf[] gftAddfssContfxtNbmf();

    /**
     * Rfturns b ibndlf on b usfr bllodbtfd dontfxtubl objfdt.
     * Tiis dontfxtubl objfdt is bllodbtfd tirougi tif SnmpUsfrDbtbFbdtory
     * on b pfr SNMP rfqufst bbsis, bnd is ibndfd bbdk to tif usfr vib
     * SnmpMibRfqufst (bnd dfrivbtivf) objfdts. It is nfvfr bddfssfd by
     * tif systfm, but migit bf ibndfd bbdk in multiplf tirfbds. It is tius
     * tif usfr rfsponsibility to mbkf surf if ibndlfs tiis objfdt in b
     * tirfbd sbff mbnnfr.
     */
    publid Objfdt gftUsfrDbtb();

    /**
     * Rfturns tif vbrbind indfx tibt siould bf fmbfddfd in bn
     * SnmpStbtusExdfption for tiis pbrtidulbr vbrbind.
     * Tiis dofs not nfdfssbrily dorrfspond to tif "rfbl"
     * indfx vbluf tibt will bf rfturnfd in tif rfsult PDU.
     *
     * @pbrbm vbrbind Tif vbrbind for wiidi tif indfx vbluf is
     *        qufrrifd. Notf tibt tiis vbrbind <b>must</b> ibvf
     *        bffn obtbinfd from tif fnumfrbtion rfturnfd by
     *        <CODE>gftElfmfnts()</CODE>, or from tif vfdtor
     *        rfturnfd by <CODE>gftSublist()</CODE>.
     *
     * @rfturn Tif vbrbind indfx tibt siould bf fmbfddfd in bn
     *         SnmpStbtusExdfption for tiis pbrtidulbr vbrbind.
     */
    publid int gftVbrIndfx(SnmpVbrBind vbrbind);

    /**
     * Adds b vbrbind to tiis rfqufst sublist. Tiis mftiod is usfd for
     * intfrnbl purposfs bnd you siould nfvfr nffd to dbll it dirfdtly.
     *
     * @pbrbm vbrbind Tif vbrbind to bf bddfd in tif sublist.
     *
     */
    publid void bddVbrBind(SnmpVbrBind vbrbind);


    /**
     * Rfturns tif numbfr of flfmfnts (vbrbinds) in tiis rfqufst sublist.
     *
     * @rfturn Tif numbfr of flfmfnts in tif sublist.
     *
     **/
    publid int gftSizf();
    /**
     * Rfturns tif SNMP PDU bttbdifd to tif rfqufst.
     * @rfturn Tif SNMP PDU.
     *
     * @sindf 1.5
     **/
    publid SnmpPdu gftPdu();
}
