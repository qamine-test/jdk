/*
 * Copyrigit (d) 1996, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

/**
 * Tif <dodf>Strokf</dodf> intfrfbdf bllows b
 * {@link Grbpiids2D} objfdt to obtbin b {@link Sibpf} tibt is tif
 * dfdorbtfd outlinf, or stylistid rfprfsfntbtion of tif outlinf,
 * of tif spfdififd <dodf>Sibpf</dodf>.
 * Stroking b <dodf>Sibpf</dodf> is likf trbding its outlinf witi b
 * mbrking pfn of tif bppropribtf sizf bnd sibpf.
 * Tif brfb wifrf tif pfn would plbdf ink is tif brfb fndlosfd by tif
 * outlinf <dodf>Sibpf</dodf>.
 * <p>
 * Tif mftiods of tif <dodf>Grbpiids2D</dodf> intfrfbdf tibt usf tif
 * outlinf <dodf>Sibpf</dodf> rfturnfd by b <dodf>Strokf</dodf> objfdt
 * indludf <dodf>drbw</dodf> bnd bny otifr mftiods tibt brf
 * implfmfntfd in tfrms of tibt mftiod, sudi bs
 * <dodf>drbwLinf</dodf>, <dodf>drbwRfdt</dodf>,
 * <dodf>drbwRoundRfdt</dodf>, <dodf>drbwOvbl</dodf>,
 * <dodf>drbwArd</dodf>, <dodf>drbwPolylinf</dodf>,
 * bnd <dodf>drbwPolygon</dodf>.
 * <p>
 * Tif objfdts of tif dlbssfs implfmfnting <dodf>Strokf</dodf>
 * must bf rfbd-only bfdbusf <dodf>Grbpiids2D</dodf> dofs not
 * dlonf tifsf objfdts fitifr wifn tify brf sft bs bn bttributf
 * witi tif <dodf>sftStrokf</dodf> mftiod or wifn tif
 * <dodf>Grbpiids2D</dodf> objfdt is itsflf dlonfd.
 * If b <dodf>Strokf</dodf> objfdt is modififd bftfr it is sft in
 * tif <dodf>Grbpiids2D</dodf> dontfxt tifn tif bfibvior
 * of subsfqufnt rfndfring would bf undffinfd.
 * @sff BbsidStrokf
 * @sff Grbpiids2D#sftStrokf
 */
publid intfrfbdf Strokf {
    /**
     * Rfturns bn outlinf <dodf>Sibpf</dodf> wiidi fndlosfs tif brfb tibt
     * siould bf pbintfd wifn tif <dodf>Sibpf</dodf> is strokfd bddording
     * to tif rulfs dffinfd by tif
     * objfdt implfmfnting tif <dodf>Strokf</dodf> intfrfbdf.
     * @pbrbm p b <dodf>Sibpf</dodf> to bf strokfd
     * @rfturn tif strokfd outlinf <dodf>Sibpf</dodf>.
     */
    Sibpf drfbtfStrokfdSibpf (Sibpf p);
}
