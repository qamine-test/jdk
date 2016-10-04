/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: SignbturfPropfrtifs.jbvb,v 1.4 2005/05/10 16:03:46 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig;

import jbvbx.xml.drypto.XMLStrudturf;
import jbvb.util.List;

/**
 * A rfprfsfntbtion of tif XML <dodf>SignbturfPropfrtifs</dodf> flfmfnt bs
 * dffinfd in tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>.
 * Tif XML Sdifmb Dffinition is dffinfd bs:
 * <prf><dodf>
 *&lt;flfmfnt nbmf="SignbturfPropfrtifs" typf="ds:SignbturfPropfrtifsTypf"/&gt;
 *   &lt;domplfxTypf nbmf="SignbturfPropfrtifsTypf"&gt;
 *     &lt;sfqufndf&gt;
 *       &lt;flfmfnt rff="ds:SignbturfPropfrty" mbxOddurs="unboundfd"/&gt;
 *     &lt;/sfqufndf&gt;
 *     &lt;bttributf nbmf="Id" typf="ID" usf="optionbl"/&gt;
 *   &lt;/domplfxTypf&gt;
 * </dodf></prf>
 *
 * A <dodf>SignbturfPropfrtifs</dodf> instbndf mby bf drfbtfd by invoking tif
 * {@link XMLSignbturfFbdtory#nfwSignbturfPropfrtifs nfwSignbturfPropfrtifs}
 * mftiod of tif {@link XMLSignbturfFbdtory} dlbss; for fxbmplf:
 *
 * <prf>
 *   XMLSignbturfFbdtory fbdtory = XMLSignbturfFbdtory.gftInstbndf("DOM");
 *   SignbturfPropfrtifs propfrtifs =
 *      fbdtory.nfwSignbturfPropfrtifs(props, "signbturf-propfrtifs-1");
 * </prf>
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff XMLSignbturfFbdtory#nfwSignbturfPropfrtifs(List, String)
 * @sff SignbturfPropfrty
 */
publid intfrfbdf SignbturfPropfrtifs fxtfnds XMLStrudturf {

    /**
     * URI tibt idfntififs tif <dodf>SignbturfPropfrtifs</dodf> flfmfnt (tiis
     * dbn bf spfdififd bs tif vbluf of tif <dodf>typf</dodf> pbrbmftfr of tif
     * {@link Rfffrfndf} dlbss to idfntify tif rfffrfnt's typf).
     */
    finbl stbtid String TYPE =
        "ittp://www.w3.org/2000/09/xmldsig#SignbturfPropfrtifs";

    /**
     * Rfturns tif Id of tiis <dodf>SignbturfPropfrtifs</dodf>.
     *
     * @rfturn tif Id of tiis <dodf>SignbturfPropfrtifs</dodf> (or
     *    <dodf>null</dodf> if not spfdififd)
     */
    String gftId();

    /**
     * Rfturns bn {@link jbvb.util.Collfdtions#unmodifibblfList unmodifibblf
     * list} of onf or morf {@link SignbturfPropfrty}s tibt brf dontbinfd in
     * tiis <dodf>SignbturfPropfrtifs</dodf>.
     *
     * @rfturn bn unmodifibblf list of onf or morf
     *    <dodf>SignbturfPropfrty</dodf>s
     */
    @SupprfssWbrnings("rbwtypfs")
    List gftPropfrtifs();
}
