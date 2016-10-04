/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Sfriblizbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.logging.Lfvfl;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpVbrBind;

/**
 * A simplf MIB bgfnt tibt implfmfnts SNMP dblls (gft, sft, gftnfxt bnd gftbulk) in b wby tibt only frrors or fxdfptions brf rfturnfd. Evfry dbll donf on tiis bgfnt fbils. Error ibndling is donf bddording to tif mbnbgfr's SNMP protodol vfrsion.
 * <P>It is usfd by <CODE>SnmpAdbptorSfrvfr</CODE> for its dffbult bgfnt bfibvior. Wifn b rfdfivfd Oid dofsn't mbtdi, tiis bgfnt is dbllfd to fill tif rfsult list witi frrors.</P>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 *
 */

publid dlbss SnmpErrorHbndlfrAgfnt fxtfnds SnmpMibAgfnt
        implfmfnts Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 7751082923508885650L;

    publid SnmpErrorHbndlfrAgfnt() {}

    /**
     * Initiblizfs tif MIB (witi no rfgistrbtion of tif MBfbns into tif
     * MBfbn sfrvfr). Dofs notiing.
     *
     * @fxdfption IllfgblAddfssExdfption Tif MIB dbnnot bf initiblizfd.
     */

    @Ovfrridf
    publid void init() tirows IllfgblAddfssExdfption {
    }

    /**
     * Initiblizfs tif MIB but fbdi singlf MBfbn rfprfsfnting tif MIB
     * is insfrtfd into tif MBfbn sfrvfr.
     *
     * @pbrbm sfrvfr Tif MBfbn sfrvfr to rfgistfr tif sfrvidf witi.
     * @pbrbm nbmf Tif objfdt nbmf.
     *
     * @rfturn Tif pbssfd nbmf pbrbmftfr.
     *
     * @fxdfption jbvb.lbng.Exdfption
     */

    @Ovfrridf
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
        tirows Exdfption {
        rfturn nbmf;
    }

    /**
     * Gfts tif root objfdt idfntififr of tif MIB.
     * <P>Tif root objfdt idfntififr is tif objfdt idfntififr uniqufly
     * idfntifying tif MIB.
     *
     * @rfturn Tif rfturnfd oid is null.
     */

    @Ovfrridf
    publid long[] gftRootOid() {
        rfturn null;
    }

    /**
     * Prodfssfs b <CODE>gft</CODE> opfrbtion. It will tirow bn fxdfption for V1 rfqufsts or it will sft fxdfptions witiin tif list for V2 rfqufsts.
     *
     * @pbrbm inRfqufst Tif SnmpMibRfqufst objfdt iolding tif list of vbribblf to bf rftrifvfd.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     */

    @Ovfrridf
    publid void gft(SnmpMibRfqufst inRfqufst) tirows SnmpStbtusExdfption {

        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                SnmpErrorHbndlfrAgfnt.dlbss.gftNbmf(),
                "gft", "Gft in Exdfption");

        if(inRfqufst.gftVfrsion() == SnmpDffinitions.snmpVfrsionOnf)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiNbmf);

        Enumfrbtion<SnmpVbrBind> l = inRfqufst.gftElfmfnts();
        wiilf(l.ibsMorfElfmfnts()) {
            SnmpVbrBind vbrbind = l.nfxtElfmfnt();
            vbrbind.sftNoSudiObjfdt();
        }
    }

    /**
     * Cifdks if b <CODE>sft</CODE> opfrbtion dbn bf pfrformfd.
     * If tif opfrbtion dbn not bf pfrformfd, tif mftiod siould fmit b
     * <CODE>SnmpStbtusExdfption</CODE>.
     *
     * @pbrbm inRfqufst Tif SnmpMibRfqufst objfdt iolding tif list of vbribblfs to
     *            bf sft. Tiis list is domposfd of
     *            <CODE>SnmpVbrBind</CODE> objfdts.
     *
     * @fxdfption SnmpStbtusExdfption Tif <CODE>sft</CODE> opfrbtion
     *    dbnnot bf pfrformfd.
     */

    @Ovfrridf
    publid void difdk(SnmpMibRfqufst inRfqufst) tirows SnmpStbtusExdfption {

        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                SnmpErrorHbndlfrAgfnt.dlbss.gftNbmf(),
                "difdk", "Cifdk in Exdfption");

        tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspNotWritbblf);
    }

    /**
     * Prodfssfs b <CODE>sft</CODE> opfrbtion. Siould nfvfr bf dbllfd (difdk prfviously dbllfd ibving fbilfd).
     *
     * @pbrbm inRfqufst Tif SnmpMibRfqufst objfdt iolding tif list of vbribblf to bf sft.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     */

    @Ovfrridf
    publid void sft(SnmpMibRfqufst inRfqufst) tirows SnmpStbtusExdfption {

        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                SnmpErrorHbndlfrAgfnt.dlbss.gftNbmf(),
                "sft", "Sft in Exdfption, CANNOT bf dbllfd");

        tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspNotWritbblf);
    }

    /**
     * Prodfssfs b <CODE>gftNfxt</CODE> opfrbtion. It will tirow bn fxdfption for V1 rfqufsts or it will sft fxdfptions witiin tif list for V2 rfqufsts..
     *
     * @pbrbm inRfqufst Tif SnmpMibRfqufst objfdt iolding tif list of vbribblfs to bf rftrifvfd.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     */

    @Ovfrridf
    publid void gftNfxt(SnmpMibRfqufst inRfqufst) tirows SnmpStbtusExdfption {

        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                SnmpErrorHbndlfrAgfnt.dlbss.gftNbmf(),
                "gftNfxt", "GftNfxt in Exdfption");

        if(inRfqufst.gftVfrsion() == SnmpDffinitions.snmpVfrsionOnf)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiNbmf);

        Enumfrbtion<SnmpVbrBind> l = inRfqufst.gftElfmfnts();
        wiilf(l.ibsMorfElfmfnts()) {
            SnmpVbrBind vbrbind = l.nfxtElfmfnt();
            vbrbind.sftEndOfMibVifw();
        }
    }

    /**
     * Prodfssfs b <CODE>gftBulk</CODE> opfrbtion. It will tirow bn fxdfption if tif rfqufst is b V1 onf or it will sft fxdfptions witiin tif list for V2 onfs.
     *
     * @pbrbm inRfqufst Tif SnmpMibRfqufst objfdt iolding tif list of vbribblf to bf rftrifvfd.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd during tif opfrbtion.
     */

    @Ovfrridf
    publid void gftBulk(SnmpMibRfqufst inRfqufst, int nonRfpfbt, int mbxRfpfbt)
        tirows SnmpStbtusExdfption {

        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                SnmpErrorHbndlfrAgfnt.dlbss.gftNbmf(),
                "gftBulk", "GftBulk in Exdfption");

        if(inRfqufst.gftVfrsion() == SnmpDffinitions.snmpVfrsionOnf)
            tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspGfnErr, 0);

        Enumfrbtion<SnmpVbrBind> l = inRfqufst.gftElfmfnts();
        wiilf(l.ibsMorfElfmfnts()) {
            SnmpVbrBind vbrbind = l.nfxtElfmfnt();
            vbrbind.sftEndOfMibVifw();
        }
    }

}
