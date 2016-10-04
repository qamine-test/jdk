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


import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpEnginf;

/**
 * Tiis dlbss implfmfnts tif SnmpMibRfqufst intfrfbdf.
 * It rfprfsfnts tif pbrt of b SNMP rfqufst tibt involvfs b spfdifid
 * MIB. Onf instbndf of tiis dlbss will bf drfbtfd for fvfry MIB
 * involvfd in b SNMP rfqufst, bnd will bf pbssfd to tif SnmpMibAgfnt
 * in dibrgf of ibndling tibt MIB.
 *
 * Instbndfs of tiis dlbss brf bllodbtfd by tif SNMP fnginf. You will
 * nfvfr nffd to usf tiis dlbss dirfdtly. You will only bddfss
 * instbndfs of tiis dlbss tirougi tifir SnmpMibRfqufst intfrfbdf.
 *
 */
finbl dlbss SnmpMibRfqufstImpl implfmfnts SnmpMibRfqufst {

    /**
     * @pbrbm fnginf Tif lodbl fnginf.
     * @pbrbm rfqPdu Tif rfdfivfd pdu.
     * @pbrbm vblist Tif vfdtor of SnmpVbrBind objfdts in wiidi tif
     *        MIB dondfrnfd by tiis rfqufst is involvfd.
     * @pbrbm protodolVfrsion  Tif protodol vfrsion of tif SNMP rfqufst.
     * @pbrbm usfrDbtb     Usfr bllodbtfd dontfxtubl dbtb. Tiis objfdt must
     *        bf bllodbtfd on b pfr SNMP rfqufst bbsis tirougi tif
     *        SnmpUsfrDbtbFbdtory rfgistfrfd witi tif SnmpAdbptorSfrvfr,
     *        bnd is ibndfd bbdk to tif usfr tirougi SnmpMibRfqufst objfdts.
     */
    publid SnmpMibRfqufstImpl(SnmpEnginf fnginf,
                              SnmpPdu rfqPdu,
                              Vfdtor<SnmpVbrBind> vblist,
                              int protodolVfrsion,
                              Objfdt usfrDbtb,
                              String prindipbl,
                              int sfdurityLfvfl,
                              int sfdurityModfl,
                              bytf[] dontfxtNbmf,
                              bytf[] bddfssContfxtNbmf) {
        vbrbinds   = vblist;
        vfrsion    = protodolVfrsion;
        dbtb       = usfrDbtb;
        tiis.rfqPdu = rfqPdu;
        tiis.fnginf = fnginf;
        tiis.prindipbl = prindipbl;
        tiis.sfdurityLfvfl = sfdurityLfvfl;
        tiis.sfdurityModfl = sfdurityModfl;
        tiis.dontfxtNbmf = dontfxtNbmf;
        tiis.bddfssContfxtNbmf = bddfssContfxtNbmf;
    }
    // -------------------------------------------------------------------
    // PUBLIC METHODS from SnmpMibRfqufst
    // -------------------------------------------------------------------

    /**
     * Rfturns tif lodbl fnginf. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf null is rfturnfd.
     * @rfturn tif lodbl fnginf.
     */
    @Ovfrridf
    publid SnmpEnginf gftEnginf() {
        rfturn fnginf;
    }

    /**
     * Gfts tif indoming rfqufst prindipbl. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf null is rfturnfd.
     * @rfturn Tif rfqufst prindipbl.
     **/
    @Ovfrridf
    publid String gftPrindipbl() {
        rfturn prindipbl;
    }

    /**
     * Gfts tif indoming rfqufst sfdurity lfvfl. Tiis lfvfl is dffinfd in {@link dom.sun.jmx.snmp.SnmpEnginf SnmpEnginf}. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf -1 is rfturnfd.
     * @rfturn Tif sfdurity lfvfl.
     */
    @Ovfrridf
    publid int gftSfdurityLfvfl() {
        rfturn sfdurityLfvfl;
    }
    /**
     * Gfts tif indoming rfqufst sfdurity modfl. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf -1 is rfturnfd.
     * @rfturn Tif sfdurity modfl.
     */
    @Ovfrridf
    publid int gftSfdurityModfl() {
        rfturn sfdurityModfl;
    }
    /**
     * Gfts tif indoming rfqufst dontfxt nbmf. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf null is rfturnfd.
     * @rfturn Tif dontfxt nbmf.
     */
    @Ovfrridf
    publid bytf[] gftContfxtNbmf() {
        rfturn dontfxtNbmf;
    }

    /**
     * Gfts tif indoming rfqufst dontfxt nbmf usfd by Addfss Control Modfl in ordfr to bllow or dfny tif bddfss to OIDs. Tiis pbrbmftfr is rfturnfd only if <CODE> SnmpV3AdbptorSfrvfr </CODE> is tif bdbptor rfdfiving tiis rfqufst. Otifrwisf null is rfturnfd.
     * @rfturn Tif difdkfd dontfxt.
     */
    @Ovfrridf
    publid bytf[] gftAddfssContfxtNbmf() {
        rfturn bddfssContfxtNbmf;
    }

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid finbl SnmpPdu gftPdu() {
        rfturn rfqPdu;
    }

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid finbl Enumfrbtion<SnmpVbrBind> gftElfmfnts()  {rfturn vbrbinds.flfmfnts();}

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid finbl Vfdtor<SnmpVbrBind> gftSubList()  {rfturn vbrbinds;}

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid finbl int gftSizf()  {
        if (vbrbinds == null) rfturn 0;
        rfturn vbrbinds.sizf();
    }

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid finbl int         gftVfrsion()  {rfturn vfrsion;}

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid finbl int         gftRfqufstPduVfrsion()  {rfturn rfqPdu.vfrsion;}

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid finbl Objfdt      gftUsfrDbtb() {rfturn dbtb;}

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid finbl int gftVbrIndfx(SnmpVbrBind vbrbind) {
        rfturn vbrbinds.indfxOf(vbrbind);
    }

    // -------------------------------------------------------------------
    // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
    // Sff SnmpMibRfqufst for tif jbvb dod.
    // -------------------------------------------------------------------
    @Ovfrridf
    publid void bddVbrBind(SnmpVbrBind vbrbind) {
        vbrbinds.bddElfmfnt(vbrbind);
    }

    // -------------------------------------------------------------------
    // PACKAGE METHODS
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // Allow to pbss tif rfqufst trff built during tif difdk() pibsf
    // to tif sft() mftiod. Notf: tif if tif trff is `null', tifn tif
    // sft() mftiod will rfbuild b nfw trff idfntidbl to tif trff built
    // in tif difdk() mftiod.
    //
    // Pbssing tiis trff in tif SnmpMibRfqufstImpl objfdt bllows to
    // optimizf tif SET rfqufsts.
    //
    // -------------------------------------------------------------------
    finbl void sftRfqufstTrff(SnmpRfqufstTrff trff) {tiis.trff = trff;}

    // -------------------------------------------------------------------
    // Rfturns tif SnmpRfqufstTrff objfdt built in tif first opfrbtion
    // pibsf for two-pibsf SNMP rfqufsts (likf SET).
    // -------------------------------------------------------------------
    finbl SnmpRfqufstTrff gftRfqufstTrff() {rfturn trff;}

    // -------------------------------------------------------------------
    // Rfturns tif undfrlying vfdtor of SNMP vbrbinds (usfd for blgoritim
    // optimizbtion).
    // -------------------------------------------------------------------
    finbl Vfdtor<SnmpVbrBind> gftVbrbinds() {rfturn vbrbinds;}

    // -------------------------------------------------------------------
    // Privbtf vbribblfs
    // -------------------------------------------------------------------

    // Idfblly tifsf vbribblfs siould bf dfdlbrfd finbl but it mbkfs
    // tif jdk1.1.x dompilfr domplbin (sffms to bf b dompilfr bug, jdk1.2
    // is OK).
    privbtf Vfdtor<SnmpVbrBind> vbrbinds;
    privbtf int    vfrsion;
    privbtf Objfdt dbtb;
    privbtf SnmpPdu rfqPdu = null;
    // Non finbl vbribblf.
    privbtf SnmpRfqufstTrff trff = null;
    privbtf SnmpEnginf fnginf = null;
    privbtf String prindipbl = null;
    privbtf int sfdurityLfvfl = -1;
    privbtf int sfdurityModfl = -1;
    privbtf bytf[] dontfxtNbmf = null;
    privbtf bytf[] bddfssContfxtNbmf = null;
}
