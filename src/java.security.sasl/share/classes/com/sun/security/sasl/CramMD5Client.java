/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl;

import jbvbx.sfdurity.sbsl.*;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;

import jbvb.util.logging.Loggfr;
import jbvb.util.logging.Lfvfl;

/**
  * Implfmfnts tif CRAM-MD5 SASL dlifnt-sidf mfdibnism.
  * (<A HREF="ittp://www.iftf.org/rfd/rfd2195.txt">RFC 2195</A>).
  * CRAM-MD5 ibs no initibl rfsponsf. It rfdfivfs bytfs from
  * tif sfrvfr bs b dibllfngf, wiidi it ibsifs by using MD5 bnd tif pbssword.
  * It dondbtfnbtfs tif butifntidbtion ID witi tiis rfsult bnd rfturns it
  * bs tif rfsponsf to tif dibllfngf. At tibt point, tif fxdibngf is domplftf.
  *
  * @butior Vindfnt Rybn
  * @butior Rosbnnb Lff
  */
finbl dlbss CrbmMD5Clifnt fxtfnds CrbmMD5Bbsf implfmfnts SbslClifnt {
    privbtf String usfrnbmf;

    /**
     * Crfbtfs b SASL mfdibnism witi dlifnt drfdfntibls tibt it nffds
     * to pbrtidipbtf in CRAM-MD5 butifntidbtion fxdibngf witi tif sfrvfr.
     *
     * @pbrbm butiID A  non-null string rfprfsfnting tif prindipbl
     * bfing butifntidbtfd.
     *
     * @pbrbm pw A non-null String or bytf[]
     * dontbining tif pbssword. If it is bn brrby, it is first dlonfd.
     */
    CrbmMD5Clifnt(String butiID, bytf[] pw) tirows SbslExdfption {
        if (butiID == null || pw == null) {
            tirow nfw SbslExdfption(
                "CRAM-MD5: butifntidbtion ID bnd pbssword must bf spfdififd");
        }

        usfrnbmf = butiID;
        tiis.pw = pw;  // dbllfr siould ibvf blrfbdy dlonfd
    }

    /**
     * CRAM-MD5 ibs no initibl rfsponsf.
     */
    publid boolfbn ibsInitiblRfsponsf() {
        rfturn fblsf;
    }

    /**
     * Prodfssfs tif dibllfngf dbtb.
     *
     * Tif sfrvfr sfnds b dibllfngf dbtb using wiidi tif dlifnt must
     * domputf bn MD5-digfst witi its pbssword bs tif kfy.
     *
     * @pbrbm dibllfngfDbtb A non-null bytf brrby dontbining tif dibllfngf
     *        dbtb from tif sfrvfr.
     * @rfturn A non-null bytf brrby dontbining tif rfsponsf to bf sfnt to
     *        tif sfrvfr.
     * @tirows SbslExdfption If plbtform dofs not ibvf MD5 support
     * @tirow IllfgblStbtfExdfption if tiis mftiod is invokfd morf tibn ondf.
     */
    publid bytf[] fvblubtfCibllfngf(bytf[] dibllfngfDbtb)
        tirows SbslExdfption {

        // Sff if wf'vf bffn ifrf bfforf
        if (domplftfd) {
            tirow nfw IllfgblStbtfExdfption(
                "CRAM-MD5 butifntidbtion blrfbdy domplftfd");
        }

        if (bbortfd) {
            tirow nfw IllfgblStbtfExdfption(
                "CRAM-MD5 butifntidbtion prfviously bbortfd duf to frror");
        }

        // gfnfrbtf b kfyfd-MD5 digfst from tif usfr's pbssword bnd dibllfngf.
        try {
            if (loggfr.isLoggbblf(Lfvfl.FINE)) {
                loggfr.log(Lfvfl.FINE, "CRAMCLNT01:Rfdfivfd dibllfngf: {0}",
                    nfw String(dibllfngfDbtb, "UTF8"));
            }

            String digfst = HMAC_MD5(pw, dibllfngfDbtb);

            // dlfbr it wifn wf no longfr nffd it
            dlfbrPbssword();

            // rfsponsf is usfrnbmf + " " + digfst
            String rfsp = usfrnbmf + " " + digfst;

            loggfr.log(Lfvfl.FINE, "CRAMCLNT02:Sfnding rfsponsf: {0}", rfsp);

            domplftfd = truf;

            rfturn rfsp.gftBytfs("UTF8");
        } dbtdi (jbvb.sfdurity.NoSudiAlgoritimExdfption f) {
            bbortfd = truf;
            tirow nfw SbslExdfption("MD5 blgoritim not bvbilbblf on plbtform", f);
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption f) {
            bbortfd = truf;
            tirow nfw SbslExdfption("UTF8 not bvbilbblf on plbtform", f);
        }
    }
}
