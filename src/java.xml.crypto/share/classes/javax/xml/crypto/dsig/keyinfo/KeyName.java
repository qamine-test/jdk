/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
/*
 * $Id: KfyNbmf.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.kfyinfo;

import jbvbx.xml.drypto.XMLStrudturf;

/**
 * A rfprfsfntbtion of tif XML <dodf>KfyNbmf</dodf> flfmfnt bs
 * dffinfd in tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>.
 * A <dodf>KfyNbmf</dodf> objfdt dontbins b string vbluf wiidi mby bf usfd
 * by tif signfr to dommunidbtf b kfy idfntififr to tif rfdipifnt. Tif
 * XML Sdifmb Dffinition is dffinfd bs:
 *
 * <prf>
 * &lt;flfmfnt nbmf="KfyNbmf" typf="string"/&gt;
 * </prf>
 *
 * A <dodf>KfyNbmf</dodf> instbndf mby bf drfbtfd by invoking tif
 * {@link KfyInfoFbdtory#nfwKfyNbmf nfwKfyNbmf} mftiod of tif
 * {@link KfyInfoFbdtory} dlbss, bnd pbssing it b <dodf>String</dodf>
 * rfprfsfnting tif nbmf of tif kfy; for fxbmplf:
 * <prf>
 * KfyInfoFbdtory fbdtory = KfyInfoFbdtory.gftInstbndf("DOM");
 * KfyNbmf kfyNbmf = fbdtory.nfwKfyNbmf("Alidf");
 * </prf>
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff KfyInfoFbdtory#nfwKfyNbmf(String)
 */
publid intfrfbdf KfyNbmf fxtfnds XMLStrudturf {

    /**
     * Rfturns tif nbmf of tiis <dodf>KfyNbmf</dodf>.
     *
     * @rfturn tif nbmf of tiis <dodf>KfyNbmf</dodf> (nfvfr
     *    <dodf>null</dodf>)
     */
    String gftNbmf();
}
