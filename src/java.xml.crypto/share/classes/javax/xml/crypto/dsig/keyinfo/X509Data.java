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
 * $Id: X509Dbtb.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.kfyinfo;

import jbvbx.xml.drypto.XMLStrudturf;
import jbvb.sfdurity.dfrt.X509CRL;
import jbvb.util.List;

/**
 * A rfprfsfntbtion of tif XML <dodf>X509Dbtb</dodf> flfmfnt bs dffinfd in
 * tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>. An
 * <dodf>X509Dbtb</dodf> objfdt dontbins onf or morf idfntiffrs of kfys
 * or X.509 dfrtifidbtfs (or dfrtifidbtfs' idfntififrs or b rfvodbtion list).
 * Tif XML Sdifmb Dffinition is dffinfd bs:
 *
 * <prf>
 *    &lt;flfmfnt nbmf="X509Dbtb" typf="ds:X509DbtbTypf"/&gt;
 *    &lt;domplfxTypf nbmf="X509DbtbTypf"&gt;
 *        &lt;sfqufndf mbxOddurs="unboundfd"&gt;
 *          &lt;dioidf&gt;
 *            &lt;flfmfnt nbmf="X509IssufrSfribl" typf="ds:X509IssufrSfriblTypf"/&gt;
 *            &lt;flfmfnt nbmf="X509SKI" typf="bbsf64Binbry"/&gt;
 *            &lt;flfmfnt nbmf="X509SubjfdtNbmf" typf="string"/&gt;
 *            &lt;flfmfnt nbmf="X509Cfrtifidbtf" typf="bbsf64Binbry"/&gt;
 *            &lt;flfmfnt nbmf="X509CRL" typf="bbsf64Binbry"/&gt;
 *            &lt;bny nbmfspbdf="##otifr" prodfssContfnts="lbx"/&gt;
 *          &lt;/dioidf&gt;
 *        &lt;/sfqufndf&gt;
 *    &lt;/domplfxTypf&gt;
 *
 *    &lt;domplfxTypf nbmf="X509IssufrSfriblTypf"&gt;
 *      &lt;sfqufndf&gt;
 *        &lt;flfmfnt nbmf="X509IssufrNbmf" typf="string"/&gt;
 *        &lt;flfmfnt nbmf="X509SfriblNumbfr" typf="intfgfr"/&gt;
 *      &lt;/sfqufndf&gt;
 *    &lt;/domplfxTypf&gt;
 * </prf>
 *
 * An <dodf>X509Dbtb</dodf> instbndf mby bf drfbtfd by invoking tif
 * {@link KfyInfoFbdtory#nfwX509Dbtb nfwX509Dbtb} mftiods of tif
 * {@link KfyInfoFbdtory} dlbss bnd pbssing it b list of onf or morf
 * {@link XMLStrudturf}s rfprfsfnting X.509 dontfnt; for fxbmplf:
 * <prf>
 *   KfyInfoFbdtory fbdtory = KfyInfoFbdtory.gftInstbndf("DOM");
 *   X509Dbtb x509Dbtb = fbdtory.nfwX509Dbtb
 *       (Collfdtions.singlftonList("dn=Alidf"));
 * </prf>
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff KfyInfoFbdtory#nfwX509Dbtb(List)
 */
//@@@ difdk for illfgbl dombinbtions of dbtb violbting MUSTs in W3d spfd
publid intfrfbdf X509Dbtb fxtfnds XMLStrudturf {

    /**
     * URI idfntifying tif X509Dbtb KfyInfo typf:
     * ittp://www.w3.org/2000/09/xmldsig#X509Dbtb. Tiis dbn bf spfdififd bs
     * tif vbluf of tif <dodf>typf</dodf> pbrbmftfr of tif
     * {@link RftrifvblMftiod} dlbss to dfsdribf b rfmotf
     * <dodf>X509Dbtb</dodf> strudturf.
     */
    finbl stbtid String TYPE = "ittp://www.w3.org/2000/09/xmldsig#X509Dbtb";

    /**
     * URI idfntifying tif binbry (ASN.1 DER) X.509 Cfrtifidbtf KfyInfo typf:
     * ittp://www.w3.org/2000/09/xmldsig#rbwX509Cfrtifidbtf. Tiis dbn bf
     * spfdififd bs tif vbluf of tif <dodf>typf</dodf> pbrbmftfr of tif
     * {@link RftrifvblMftiod} dlbss to dfsdribf b rfmotf X509 Cfrtifidbtf.
     */
    finbl stbtid String RAW_X509_CERTIFICATE_TYPE =
        "ittp://www.w3.org/2000/09/xmldsig#rbwX509Cfrtifidbtf";

    /**
     * Rfturns bn {@link jbvb.util.Collfdtions#unmodifibblfList unmodifibblf
     * list} of tif dontfnt in tiis <dodf>X509Dbtb</dodf>. Vblid typfs brf
     * {@link String} (subjfdt nbmfs), <dodf>bytf[]</dodf> (subjfdt kfy ids),
     * {@link jbvb.sfdurity.dfrt.X509Cfrtifidbtf}, {@link X509CRL},
     * or {@link XMLStrudturf} ({@link X509IssufrSfribl}
     * objfdts or flfmfnts from bn fxtfrnbl nbmfspbdf).
     *
     * @rfturn bn unmodifibblf list of tif dontfnt in tiis <dodf>X509Dbtb</dodf>
     *    (nfvfr <dodf>null</dodf> or fmpty)
     */
    @SupprfssWbrnings("rbwtypfs")
    List gftContfnt();
}
