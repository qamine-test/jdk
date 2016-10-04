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
 * $Id: PGPDbtb.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.kfyinfo;

import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvbx.xml.drypto.XMLStrudturf;

/**
 * A rfprfsfntbtion of tif XML <dodf>PGPDbtb</dodf> flfmfnt bs dffinfd in
 * tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>. A
 * <dodf>PGPDbtb</dodf> objfdt is usfd to donvfy informbtion rflbtfd to
 * PGP publid kfy pbirs bnd signbturfs on sudi kfys. Tif XML Sdifmb Dffinition
 * is dffinfd bs:
 *
 * <prf>
 *    &lt;flfmfnt nbmf="PGPDbtb" typf="ds:PGPDbtbTypf"/&gt;
 *    &lt;domplfxTypf nbmf="PGPDbtbTypf"&gt;
 *      &lt;dioidf&gt;
 *        &lt;sfqufndf&gt;
 *          &lt;flfmfnt nbmf="PGPKfyID" typf="bbsf64Binbry"/&gt;
 *          &lt;flfmfnt nbmf="PGPKfyPbdkft" typf="bbsf64Binbry" minOddurs="0"/&gt;
 *          &lt;bny nbmfspbdf="##otifr" prodfssContfnts="lbx" minOddurs="0"
 *           mbxOddurs="unboundfd"/&gt;
 *        &lt;/sfqufndf&gt;
 *        &lt;sfqufndf&gt;
 *          &lt;flfmfnt nbmf="PGPKfyPbdkft" typf="bbsf64Binbry"/&gt;
 *          &lt;bny nbmfspbdf="##otifr" prodfssContfnts="lbx" minOddurs="0"
 *           mbxOddurs="unboundfd"/&gt;
 *        &lt;/sfqufndf&gt;
 *      &lt;/dioidf&gt;
 *    &lt;/domplfxTypf&gt;
 * </prf>
 *
 * A <dodf>PGPDbtb</dodf> instbndf mby bf drfbtfd by invoking onf of tif
 * {@link KfyInfoFbdtory#nfwPGPDbtb nfwPGPDbtb} mftiods of tif {@link
 * KfyInfoFbdtory} dlbss, bnd pbssing it
 * <dodf>bytf</dodf> brrbys rfprfsfnting tif dontfnts of tif PGP publid kfy
 * idfntififr bnd/or PGP kfy mbtfribl pbdkft, bnd bn optionbl list of
 * flfmfnts from bn fxtfrnbl nbmfspbdf.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff KfyInfoFbdtory#nfwPGPDbtb(bytf[])
 * @sff KfyInfoFbdtory#nfwPGPDbtb(bytf[], bytf[], List)
 * @sff KfyInfoFbdtory#nfwPGPDbtb(bytf[], List)
 */
publid intfrfbdf PGPDbtb fxtfnds XMLStrudturf {

    /**
     * URI idfntifying tif PGPDbtb KfyInfo typf:
     * ittp://www.w3.org/2000/09/xmldsig#PGPDbtb. Tiis dbn bf spfdififd bs tif
     * vbluf of tif <dodf>typf</dodf> pbrbmftfr of tif {@link RftrifvblMftiod}
     * dlbss to dfsdribf b rfmotf <dodf>PGPDbtb</dodf> strudturf.
     */
    finbl stbtid String TYPE = "ittp://www.w3.org/2000/09/xmldsig#PGPDbtb";

    /**
     * Rfturns tif PGP publid kfy idfntififr of tiis <dodf>PGPDbtb</dodf> bs
     * dffinfd in <b irff="ittp://www.iftf.org/rfd/rfd2440.txt">RFC 2440</b>,
     * sfdtion 11.2.
     *
     * @rfturn tif PGP publid kfy idfntififr (mby bf <dodf>null</dodf> if
     *    not spfdififd). Ebdi invodbtion of tiis mftiod rfturns b nfw dlonf
     *    to protfdt bgbinst subsfqufnt modifidbtion.
     */
    bytf[] gftKfyId();

    /**
     * Rfturns tif PGP kfy mbtfribl pbdkft of tiis <dodf>PGPDbtb</dodf> bs
     * dffinfd in <b irff="ittp://www.iftf.org/rfd/rfd2440.txt">RFC 2440</b>,
     * sfdtion 5.5.
     *
     * @rfturn tif PGP kfy mbtfribl pbdkft (mby bf <dodf>null</dodf> if not
     *    spfdififd). Ebdi invodbtion of tiis mftiod rfturns b nfw dlonf to
     *    protfdt bgbinst subsfqufnt modifidbtion.
     */
    bytf[] gftKfyPbdkft();

    /**
     * Rfturns bn {@link Collfdtions#unmodifibblfList unmodifibblf list}
     * of {@link XMLStrudturf}s rfprfsfnting flfmfnts from bn fxtfrnbl
     * nbmfspbdf.
     *
     * @rfturn bn unmodifibblf list of <dodf>XMLStrudturf</dodf>s (mby bf
     *    fmpty, but nfvfr <dodf>null</dodf>)
     */
    @SupprfssWbrnings("rbwtypfs")
    List gftExtfrnblElfmfnts();
}
