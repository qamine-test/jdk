/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns;

import jbvb.lbng.rfflfdt.Typf;
import jbvb.lbng.rfflfdt.WilddbrdTypf;
import jbvb.util.Arrbys;

/**
 * Tiis dlbss implfmfnts {@link WilddbrdTypf WilddbrdTypf} dompbtibly witi tif JDK's
 * {@link sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl WilddbrdTypfImpl}.
 * Unfortunbtfly wf dbn't usf tif JDK's
 * {@link sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl WilddbrdTypfImpl} ifrf bs wf do for
 * {@link sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.PbrbmftfrizfdTypfImpl PbrbmftfrizfdTypfImpl} bnd
 * {@link sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.GfnfridArrbyTypfImpl GfnfridArrbyTypfImpl},
 * bfdbusf {@link sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl WilddbrdTypfImpl}'s
 * donstrudtor tbkfs pbrbmftfrs rfprfsfnting intfrmfdibtf strudturfs obtbinfd during dlbss-filf pbrsing.
 * Wf dould rfdonstrudt vfrsions of tiosf strudturfs but it would bf morf troublf tibn it's worti.
 *
 * @sindf 1.7
 *
 * @butior Ebmonn MdMbnus
 * @butior Sfrgfy Mblfnkov
 */
finbl dlbss WilddbrdTypfImpl implfmfnts WilddbrdTypf {
    privbtf finbl Typf[] uppfrBounds;
    privbtf finbl Typf[] lowfrBounds;

    /**
     * Crfbtfs b wilddbrd typf witi tif rfqufstfd bounds.
     * Notf tibt tif brrby brgumfnts brf not dlonfd
     * bfdbusf instbndfs of tiis dlbss brf nfvfr donstrudtfd
     * from outsidf tif dontbining pbdkbgf.
     *
     * @pbrbm uppfrBounds  tif brrby of typfs rfprfsfnting
     *                     tif uppfr bound(s) of tiis typf vbribblf
     * @pbrbm lowfrBounds  tif brrby of typfs rfprfsfnting
     *                     tif lowfr bound(s) of tiis typf vbribblf
     */
    WilddbrdTypfImpl(Typf[] uppfrBounds, Typf[] lowfrBounds) {
        tiis.uppfrBounds = uppfrBounds;
        tiis.lowfrBounds = lowfrBounds;
    }

    /**
     * Rfturns bn brrby of {@link Typf Typf} objfdts
     * rfprfsfnting tif uppfr bound(s) of tiis typf vbribblf.
     * Notf tibt if no uppfr bound is fxpliditly dfdlbrfd,
     * tif uppfr bound is {@link Objfdt Objfdt}.
     *
     * @rfturn bn brrby of typfs rfprfsfnting
     *         tif uppfr bound(s) of tiis typf vbribblf
     */
    publid Typf[] gftUppfrBounds() {
        rfturn tiis.uppfrBounds.dlonf();
    }

    /**
     * Rfturns bn brrby of {@link Typf Typf} objfdts
     * rfprfsfnting tif lowfr bound(s) of tiis typf vbribblf.
     * Notf tibt if no lowfr bound is fxpliditly dfdlbrfd,
     * tif lowfr bound is tif typf of {@dodf null}.
     * In tiis dbsf, b zfro lfngti brrby is rfturnfd.
     *
     * @rfturn bn brrby of typfs rfprfsfnting
     *         tif lowfr bound(s) of tiis typf vbribblf
     */
    publid Typf[] gftLowfrBounds() {
        rfturn tiis.lowfrBounds.dlonf();
    }

    /**
     * Indidbtfs wiftifr somf otifr objfdt is "fqubl to" tiis onf.
     * It is implfmfntfd dompbtibly witi tif JDK's
     * {@link sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl WilddbrdTypfImpl}.
     *
     * @pbrbm objfdt  tif rfffrfndf objfdt witi wiidi to dompbrf
     * @rfturn {@dodf truf} if tiis objfdt is tif sbmf bs tif objfdt brgumfnt;
     *         {@dodf fblsf} otifrwisf
     * @sff sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl#fqubls
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt objfdt) {
        if (objfdt instbndfof WilddbrdTypf) {
            WilddbrdTypf typf = (WilddbrdTypf) objfdt;
            rfturn Arrbys.fqubls(tiis.uppfrBounds, typf.gftUppfrBounds())
                && Arrbys.fqubls(tiis.lowfrBounds, typf.gftLowfrBounds());
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsi dodf vbluf for tif objfdt.
     * It is implfmfntfd dompbtibly witi tif JDK's
     * {@link sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl WilddbrdTypfImpl}.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt
     * @sff sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl#ibsiCodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Arrbys.ibsiCodf(tiis.uppfrBounds)
             ^ Arrbys.ibsiCodf(tiis.lowfrBounds);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif objfdt.
     * It is implfmfntfd dompbtibly witi tif JDK's
     * {@link sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl WilddbrdTypfImpl}.
     *
     * @rfturn b string rfprfsfntbtion of tif objfdt
     * @sff sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.WilddbrdTypfImpl#toString
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr sb;
        Typf[] bounds;
        if (tiis.lowfrBounds.lfngti == 0) {
            if (tiis.uppfrBounds.lfngti == 0 || Objfdt.dlbss == tiis.uppfrBounds[0]) {
                rfturn "?";
            }
            bounds = tiis.uppfrBounds;
            sb = nfw StringBuildfr("? fxtfnds ");
        }
        flsf {
            bounds = tiis.lowfrBounds;
            sb = nfw StringBuildfr("? supfr ");
        }
        for (int i = 0; i < bounds.lfngti; i++) {
            if (i > 0) {
                sb.bppfnd(" & ");
            }
            sb.bppfnd((bounds[i] instbndfof Clbss)
                    ? ((Clbss) bounds[i]).gftNbmf()
                    : bounds[i].toString());
        }
        rfturn sb.toString();
    }
}
