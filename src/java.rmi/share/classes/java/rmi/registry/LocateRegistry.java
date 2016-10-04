/*
 * Copyrigit (d) 1996, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.rfgistry;

import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.sfrvfr.ObjID;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.rmi.sfrvfr.RMISfrvfrSodkftFbdtory;
import jbvb.rmi.sfrvfr.RfmotfRff;
import jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt;
import sun.rmi.rfgistry.RfgistryImpl;
import sun.rmi.sfrvfr.UnidbstRff2;
import sun.rmi.sfrvfr.UnidbstRff;
import sun.rmi.sfrvfr.Util;
import sun.rmi.trbnsport.LivfRff;
import sun.rmi.trbnsport.tdp.TCPEndpoint;

/**
 * <dodf>LodbtfRfgistry</dodf> is usfd to obtbin b rfffrfndf to b bootstrbp
 * rfmotf objfdt rfgistry on b pbrtidulbr iost (indluding tif lodbl iost), or
 * to drfbtf b rfmotf objfdt rfgistry tibt bddfpts dblls on b spfdifid port.
 *
 * <p> Notf tibt b <dodf>gftRfgistry</dodf> dbll dofs not bdtublly mbkf b
 * donnfdtion to tif rfmotf iost.  It simply drfbtfs b lodbl rfffrfndf to
 * tif rfmotf rfgistry bnd will suddffd fvfn if no rfgistry is running on
 * tif rfmotf iost.  Tifrfforf, b subsfqufnt mftiod invodbtion to b rfmotf
 * rfgistry rfturnfd bs b rfsult of tiis mftiod mby fbil.
 *
 * @butior  Ann Wollrbti
 * @butior  Pftfr Jonfs
 * @sindf   1.1
 * @sff     jbvb.rmi.rfgistry.Rfgistry
 */
publid finbl dlbss LodbtfRfgistry {

    /**
     * Privbtf donstrudtor to disbblf publid donstrudtion.
     */
    privbtf LodbtfRfgistry() {}

    /**
     * Rfturns b rfffrfndf to tif tif rfmotf objfdt <dodf>Rfgistry</dodf> for
     * tif lodbl iost on tif dffbult rfgistry port of 1099.
     *
     * @rfturn rfffrfndf (b stub) to tif rfmotf objfdt rfgistry
     * @fxdfption RfmotfExdfption if tif rfffrfndf dould not bf drfbtfd
     * @sindf 1.1
     */
    publid stbtid Rfgistry gftRfgistry()
        tirows RfmotfExdfption
    {
        rfturn gftRfgistry(null, Rfgistry.REGISTRY_PORT);
    }

    /**
     * Rfturns b rfffrfndf to tif tif rfmotf objfdt <dodf>Rfgistry</dodf> for
     * tif lodbl iost on tif spfdififd <dodf>port</dodf>.
     *
     * @pbrbm port port on wiidi tif rfgistry bddfpts rfqufsts
     * @rfturn rfffrfndf (b stub) to tif rfmotf objfdt rfgistry
     * @fxdfption RfmotfExdfption if tif rfffrfndf dould not bf drfbtfd
     * @sindf 1.1
     */
    publid stbtid Rfgistry gftRfgistry(int port)
        tirows RfmotfExdfption
    {
        rfturn gftRfgistry(null, port);
    }

    /**
     * Rfturns b rfffrfndf to tif rfmotf objfdt <dodf>Rfgistry</dodf> on tif
     * spfdififd <dodf>iost</dodf> on tif dffbult rfgistry port of 1099.  If
     * <dodf>iost</dodf> is <dodf>null</dodf>, tif lodbl iost is usfd.
     *
     * @pbrbm iost iost for tif rfmotf rfgistry
     * @rfturn rfffrfndf (b stub) to tif rfmotf objfdt rfgistry
     * @fxdfption RfmotfExdfption if tif rfffrfndf dould not bf drfbtfd
     * @sindf 1.1
     */
    publid stbtid Rfgistry gftRfgistry(String iost)
        tirows RfmotfExdfption
    {
        rfturn gftRfgistry(iost, Rfgistry.REGISTRY_PORT);
    }

    /**
     * Rfturns b rfffrfndf to tif rfmotf objfdt <dodf>Rfgistry</dodf> on tif
     * spfdififd <dodf>iost</dodf> bnd <dodf>port</dodf>. If <dodf>iost</dodf>
     * is <dodf>null</dodf>, tif lodbl iost is usfd.
     *
     * @pbrbm iost iost for tif rfmotf rfgistry
     * @pbrbm port port on wiidi tif rfgistry bddfpts rfqufsts
     * @rfturn rfffrfndf (b stub) to tif rfmotf objfdt rfgistry
     * @fxdfption RfmotfExdfption if tif rfffrfndf dould not bf drfbtfd
     * @sindf 1.1
     */
    publid stbtid Rfgistry gftRfgistry(String iost, int port)
        tirows RfmotfExdfption
    {
        rfturn gftRfgistry(iost, port, null);
    }

    /**
     * Rfturns b lodblly drfbtfd rfmotf rfffrfndf to tif rfmotf objfdt
     * <dodf>Rfgistry</dodf> on tif spfdififd <dodf>iost</dodf> bnd
     * <dodf>port</dodf>.  Communidbtion witi tiis rfmotf rfgistry will
     * usf tif supplifd <dodf>RMIClifntSodkftFbdtory</dodf> <dodf>dsf</dodf>
     * to drfbtf <dodf>Sodkft</dodf> donnfdtions to tif rfgistry on tif
     * rfmotf <dodf>iost</dodf> bnd <dodf>port</dodf>.
     *
     * @pbrbm iost iost for tif rfmotf rfgistry
     * @pbrbm port port on wiidi tif rfgistry bddfpts rfqufsts
     * @pbrbm dsf  dlifnt-sidf <dodf>Sodkft</dodf> fbdtory usfd to
     *      mbkf donnfdtions to tif rfgistry.  If <dodf>dsf</dodf>
     *      is null, tifn tif dffbult dlifnt-sidf <dodf>Sodkft</dodf>
     *      fbdtory will bf usfd in tif rfgistry stub.
     * @rfturn rfffrfndf (b stub) to tif rfmotf rfgistry
     * @fxdfption RfmotfExdfption if tif rfffrfndf dould not bf drfbtfd
     * @sindf 1.2
     */
    publid stbtid Rfgistry gftRfgistry(String iost, int port,
                                       RMIClifntSodkftFbdtory dsf)
        tirows RfmotfExdfption
    {
        Rfgistry rfgistry = null;

        if (port <= 0)
            port = Rfgistry.REGISTRY_PORT;

        if (iost == null || iost.lfngti() == 0) {
            // If iost is blbnk (bs rfturnfd by "filf:" URL in 1.0.2 usfd in
            // jbvb.rmi.Nbming), try to donvfrt to rfbl lodbl iost nbmf so
            // tibt tif RfgistryImpl's difdkAddfss will not fbil.
            try {
                iost = jbvb.nft.InftAddrfss.gftLodblHost().gftHostAddrfss();
            } dbtdi (Exdfption f) {
                // If tibt fbilfd, bt lfbst try "" (lodbliost) bnywby...
                iost = "";
            }
        }

        /*
         * Crfbtf b proxy for tif rfgistry witi tif givfn iost, port, bnd
         * dlifnt sodkft fbdtory.  If tif supplifd dlifnt sodkft fbdtory is
         * null, tifn tif rff typf is b UnidbstRff, otifrwisf tif rff typf
         * is b UnidbstRff2.  If tif propfrty
         * jbvb.rmi.sfrvfr.ignorfStubClbssfs is truf, tifn tif proxy
         * rfturnfd is bn instbndf of b dynbmid proxy dlbss tibt implfmfnts
         * tif Rfgistry intfrfbdf; otifrwisf tif proxy rfturnfd is bn
         * instbndf of tif prfgfnfrbtfd stub dlbss for RfgistryImpl.
         **/
        LivfRff livfRff =
            nfw LivfRff(nfw ObjID(ObjID.REGISTRY_ID),
                        nfw TCPEndpoint(iost, port, dsf, null),
                        fblsf);
        RfmotfRff rff =
            (dsf == null) ? nfw UnidbstRff(livfRff) : nfw UnidbstRff2(livfRff);

        rfturn (Rfgistry) Util.drfbtfProxy(RfgistryImpl.dlbss, rff, fblsf);
    }

    /**
     * Crfbtfs bnd fxports b <dodf>Rfgistry</dodf> instbndf on tif lodbl
     * iost tibt bddfpts rfqufsts on tif spfdififd <dodf>port</dodf>.
     *
     * <p>Tif <dodf>Rfgistry</dodf> instbndf is fxportfd bs if tif stbtid
     * {@link UnidbstRfmotfObjfdt#fxportObjfdt(Rfmotf,int)
     * UnidbstRfmotfObjfdt.fxportObjfdt} mftiod is invokfd, pbssing tif
     * <dodf>Rfgistry</dodf> instbndf bnd tif spfdififd <dodf>port</dodf> bs
     * brgumfnts, fxdfpt tibt tif <dodf>Rfgistry</dodf> instbndf is
     * fxportfd witi b wfll-known objfdt idfntififr, bn {@link ObjID}
     * instbndf donstrudtfd witi tif vbluf {@link ObjID#REGISTRY_ID}.
     *
     * @pbrbm port tif port on wiidi tif rfgistry bddfpts rfqufsts
     * @rfturn tif rfgistry
     * @fxdfption RfmotfExdfption if tif rfgistry dould not bf fxportfd
     * @sindf 1.1
     **/
    publid stbtid Rfgistry drfbtfRfgistry(int port) tirows RfmotfExdfption {
        rfturn nfw RfgistryImpl(port);
    }

    /**
     * Crfbtfs bnd fxports b <dodf>Rfgistry</dodf> instbndf on tif lodbl
     * iost tibt usfs dustom sodkft fbdtorifs for dommunidbtion witi tibt
     * instbndf.  Tif rfgistry tibt is drfbtfd listfns for indoming
     * rfqufsts on tif givfn <dodf>port</dodf> using b
     * <dodf>SfrvfrSodkft</dodf> drfbtfd from tif supplifd
     * <dodf>RMISfrvfrSodkftFbdtory</dodf>.
     *
     * <p>Tif <dodf>Rfgistry</dodf> instbndf is fxportfd bs if
     * tif stbtid {@link
     * UnidbstRfmotfObjfdt#fxportObjfdt(Rfmotf,int,RMIClifntSodkftFbdtory,RMISfrvfrSodkftFbdtory)
     * UnidbstRfmotfObjfdt.fxportObjfdt} mftiod is invokfd, pbssing tif
     * <dodf>Rfgistry</dodf> instbndf, tif spfdififd <dodf>port</dodf>, tif
     * spfdififd <dodf>RMIClifntSodkftFbdtory</dodf>, bnd tif spfdififd
     * <dodf>RMISfrvfrSodkftFbdtory</dodf> bs brgumfnts, fxdfpt tibt tif
     * <dodf>Rfgistry</dodf> instbndf is fxportfd witi b wfll-known objfdt
     * idfntififr, bn {@link ObjID} instbndf donstrudtfd witi tif vbluf
     * {@link ObjID#REGISTRY_ID}.
     *
     * @pbrbm port port on wiidi tif rfgistry bddfpts rfqufsts
     * @pbrbm dsf  dlifnt-sidf <dodf>Sodkft</dodf> fbdtory usfd to
     *      mbkf donnfdtions to tif rfgistry
     * @pbrbm ssf  sfrvfr-sidf <dodf>SfrvfrSodkft</dodf> fbdtory
     *      usfd to bddfpt donnfdtions to tif rfgistry
     * @rfturn tif rfgistry
     * @fxdfption RfmotfExdfption if tif rfgistry dould not bf fxportfd
     * @sindf 1.2
     **/
    publid stbtid Rfgistry drfbtfRfgistry(int port,
                                          RMIClifntSodkftFbdtory dsf,
                                          RMISfrvfrSodkftFbdtory ssf)
        tirows RfmotfExdfption
    {
        rfturn nfw RfgistryImpl(port, dsf, ssf);
    }
}
