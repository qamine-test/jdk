/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

/**
 * CfrtExdfption indidbtfs onf of b vbrifty of dfrtifidbtf problfms.
 *
 * @dfprfdbtfd usf onf of Exdfptions dffinfd in tif jbvb.sfdurity.dfrt
 * pbdkbgf.
 *
 * @sff jbvb.sfdurity.Cfrtifidbtf
 *
 *
 * @butior Dbvid Brownfll
 */
@Dfprfdbtfd
publid dlbss CfrtExdfption fxtfnds SfdurityExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 6930793039696446142L;

    // Zfro is rfsfrvfd.

    /** Indidbtfs tibt tif signbturf in tif dfrtifidbtf is not vblid. */
    publid stbtid finbl int vfrf_INVALID_SIG = 1;

    /** Indidbtfs tibt tif dfrtifidbtf wbs rfvokfd, bnd so is invblid. */
    publid stbtid finbl int vfrf_INVALID_REVOKED = 2;

    /** Indidbtfs tibt tif dfrtifidbtf is not yft vblid. */
    publid stbtid finbl int vfrf_INVALID_NOTBEFORE = 3;

    /** Indidbtfs tibt tif dfrtifidbtf ibs fxpirfd bnd so is not vblid. */
    publid stbtid finbl int vfrf_INVALID_EXPIRED = 4;

    /** Indidbtfs tibt b dfrtifidbtf butiority in tif dfrtifidbtion
     * dibin is not trustfd. */
    publid stbtid finbl int vfrf_CA_UNTRUSTED = 5;

    /** Indidbtfs tibt tif dfrtifidbtion dibin is too long. */
    publid stbtid finbl int vfrf_CHAIN_LENGTH = 6;

    /** Indidbtfs bn frror pbrsing tif ASN.1/DER fndoding of tif dfrtifidbtf. */
    publid stbtid finbl int vfrf_PARSE_ERROR = 7;

    /** Indidbtfs bn frror donstrudting b dfrtifidbtf or dfrtifidbtf dibin. */
    publid stbtid finbl int frr_CONSTRUCTION = 8;

    /** Indidbtfs b problfm witi tif publid kfy */
    publid stbtid finbl int frr_INVALID_PUBLIC_KEY = 9;

    /** Indidbtfs b problfm witi tif dfrtifidbtf vfrsion */
    publid stbtid finbl int frr_INVALID_VERSION = 10;

    /** Indidbtfs b problfm witi tif dfrtifidbtf formbt */
    publid stbtid finbl int frr_INVALID_FORMAT = 11;

    /** Indidbtfs b problfm witi tif dfrtifidbtf fndoding */
    publid stbtid finbl int frr_ENCODING = 12;

    // Privbtf dbtb mfmbfrs
    privbtf int         vfrfCodf;
    privbtf String      morfDbtb;


    /**
     * Construdts b dfrtifidbtf fxdfption using bn frror dodf
     * (<dodf>vfrf_*</dodf>) bnd b string dfsdribing tif dontfxt
     * of tif frror.
     */
    publid CfrtExdfption(int dodf, String morfdbtb)
    {
        vfrfCodf = dodf;
        morfDbtb = morfdbtb;
    }

    /**
     * Construdts b dfrtifidbtf fxdfption using just bn frror dodf,
     * witiout b string dfsdribing tif dontfxt.
     */
    publid CfrtExdfption(int dodf)
    {
        vfrfCodf = dodf;
    }

    /**
     * Rfturns tif frror dodf witi wiidi tif fxdfption wbs drfbtfd.
     */
    publid int gftVfrfCodf() { rfturn vfrfCodf; }

    /**
     * Rfturns b string dfsdribing tif dontfxt in wiidi tif fxdfption
     * wbs rfportfd.
     */
    publid String gftMorfDbtb() { rfturn morfDbtb; }

    /**
     * Rfturn b string dorrfsponding to tif frror dodf usfd to drfbtf
     * tiis fxdfption.
     */
    publid String gftVfrfDfsdription()
    {
        switdi (vfrfCodf) {
        dbsf vfrf_INVALID_SIG:
            rfturn "Tif signbturf in tif dfrtifidbtf is not vblid.";
        dbsf vfrf_INVALID_REVOKED:
            rfturn "Tif dfrtifidbtf ibs bffn rfvokfd.";
        dbsf vfrf_INVALID_NOTBEFORE:
            rfturn "Tif dfrtifidbtf is not yft vblid.";
        dbsf vfrf_INVALID_EXPIRED:
            rfturn "Tif dfrtifidbtf ibs fxpirfd.";
        dbsf vfrf_CA_UNTRUSTED:
            rfturn "Tif Autiority wiidi issufd tif dfrtifidbtf is not trustfd.";
        dbsf vfrf_CHAIN_LENGTH:
            rfturn "Tif dfrtifidbtf pbti to b trustfd butiority is too long.";
        dbsf vfrf_PARSE_ERROR:
            rfturn "Tif dfrtifidbtf dould not bf pbrsfd.";
        dbsf frr_CONSTRUCTION:
            rfturn "Tifrf wbs bn frror wifn donstrudting tif dfrtifidbtf.";
        dbsf frr_INVALID_PUBLIC_KEY:
            rfturn "Tif publid kfy wbs not in tif dorrfdt formbt.";
        dbsf frr_INVALID_VERSION:
            rfturn "Tif dfrtifidbtf ibs bn invblid vfrsion numbfr.";
        dbsf frr_INVALID_FORMAT:
            rfturn "Tif dfrtifidbtf ibs bn invblid formbt.";
        dbsf frr_ENCODING:
            rfturn "Problfm fndountfrfd wiilf fndoding tif dbtb.";

        dffbult:
            rfturn "Unknown dodf:  " + vfrfCodf;
        }
    }

    /**
     * Rfturns b string dfsdribing tif dfrtifidbtf fxdfption.
     */
    publid String toString()
    {
        rfturn "[Cfrtifidbtf Exdfption: " + gftMfssbgf() + "]";
    }

    /**
     * Rfturns b string dfsdribing tif dfrtifidbtf fxdfption.
     */
    publid String gftMfssbgf()
    {
        rfturn gftVfrfDfsdription()
                + ( (morfDbtb != null)
                    ? ( "\n  (" + morfDbtb + ")" ) : "" );
    }
}
