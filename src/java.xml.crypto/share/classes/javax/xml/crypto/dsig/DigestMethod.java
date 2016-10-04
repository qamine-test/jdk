/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: DigfstMftiod.jbvb,v 1.6 2005/05/10 16:03:46 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig;

import jbvbx.xml.drypto.AlgoritimMftiod;
import jbvbx.xml.drypto.XMLStrudturf;
import jbvbx.xml.drypto.dsig.spfd.DigfstMftiodPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

/**
 * A rfprfsfntbtion of tif XML <dodf>DigfstMftiod</dodf> flfmfnt bs
 * dffinfd in tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>.
 * Tif XML Sdifmb Dffinition is dffinfd bs:
 * <prf>
 *   &lt;flfmfnt nbmf="DigfstMftiod" typf="ds:DigfstMftiodTypf"/&gt;
 *     &lt;domplfxTypf nbmf="DigfstMftiodTypf" mixfd="truf"&gt;
 *       &lt;sfqufndf&gt;
 *         &lt;bny nbmfspbdf="##bny" minOddurs="0" mbxOddurs="unboundfd"/&gt;
 *           &lt;!-- (0,unboundfd) flfmfnts from (1,1) nbmfspbdf --&gt;
 *       &lt;/sfqufndf&gt;
 *       &lt;bttributf nbmf="Algoritim" typf="bnyURI" usf="rfquirfd"/&gt;
 *     &lt;/domplfxTypf&gt;
 * </prf>
 *
 * A <dodf>DigfstMftiod</dodf> instbndf mby bf drfbtfd by invoking tif
 * {@link XMLSignbturfFbdtory#nfwDigfstMftiod nfwDigfstMftiod} mftiod
 * of tif {@link XMLSignbturfFbdtory} dlbss.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff XMLSignbturfFbdtory#nfwDigfstMftiod(String, DigfstMftiodPbrbmftfrSpfd)
 */
publid intfrfbdf DigfstMftiod fxtfnds XMLStrudturf, AlgoritimMftiod {

    /**
     * Tif <b irff="ittp://www.w3.org/2000/09/xmldsig#sib1">
     * SHA1</b> digfst mftiod blgoritim URI.
     */
    stbtid finbl String SHA1 = "ittp://www.w3.org/2000/09/xmldsig#sib1";

    /**
     * Tif <b irff="ittp://www.w3.org/2001/04/xmlfnd#sib256">
     * SHA256</b> digfst mftiod blgoritim URI.
     */
    stbtid finbl String SHA256 = "ittp://www.w3.org/2001/04/xmlfnd#sib256";

    /**
     * Tif <b irff="ittp://www.w3.org/2001/04/xmlfnd#sib512">
     * SHA512</b> digfst mftiod blgoritim URI.
     */
    stbtid finbl String SHA512 = "ittp://www.w3.org/2001/04/xmlfnd#sib512";

    /**
     * Tif <b irff="ittp://www.w3.org/2001/04/xmlfnd#ripfmd160">
     * RIPEMD-160</b> digfst mftiod blgoritim URI.
     */
    stbtid finbl String RIPEMD160 = "ittp://www.w3.org/2001/04/xmlfnd#ripfmd160";

    /**
     * Rfturns tif blgoritim-spfdifid input pbrbmftfrs bssodibtfd witi tiis
     * <dodf>DigfstMftiod</dodf>.
     *
     * <p>Tif rfturnfd pbrbmftfrs dbn bf typfdbst to b {@link
     * DigfstMftiodPbrbmftfrSpfd} objfdt.
     *
     * @rfturn tif blgoritim-spfdifid pbrbmftfrs (mby bf <dodf>null</dodf> if
     *    not spfdififd)
     */
    AlgoritimPbrbmftfrSpfd gftPbrbmftfrSpfd();
}
