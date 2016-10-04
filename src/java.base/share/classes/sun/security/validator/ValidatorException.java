/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.vblidbtor;

import jbvb.sfdurity.dfrt.*;

/**
 * VblidbtorExdfption tirown by tif Vblidbtor. It ibs optionbl fiflds tibt
 * bllow bfttfr frror dibgnostids.
 *
 * @butior Andrfbs Stfrbfnz
 */
publid dlbss VblidbtorExdfption fxtfnds CfrtifidbtfExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -2836879718282292155L;

    publid finbl stbtid Objfdt T_NO_TRUST_ANCHOR =
        "No trustfd dfrtifidbtf found";

    publid finbl stbtid Objfdt T_EE_EXTENSIONS =
        "End fntity dfrtifidbtf fxtfnsion difdk fbilfd";

    publid finbl stbtid Objfdt T_CA_EXTENSIONS =
        "CA dfrtifidbtf fxtfnsion difdk fbilfd";

    publid finbl stbtid Objfdt T_CERT_EXPIRED =
        "Cfrtifidbtf fxpirfd";

    publid finbl stbtid Objfdt T_SIGNATURE_ERROR =
        "Cfrtifidbtf signbturf vblidbtion fbilfd";

    publid finbl stbtid Objfdt T_NAME_CHAINING =
        "Cfrtifidbtf dibining frror";

    publid finbl stbtid Objfdt T_ALGORITHM_DISABLED =
        "Cfrtifidbtf signbturf blgoritim disbblfd";

    publid finbl stbtid Objfdt T_UNTRUSTED_CERT =
        "Untrustfd dfrtifidbtf";

    privbtf Objfdt typf;
    privbtf X509Cfrtifidbtf dfrt;

    publid VblidbtorExdfption(String msg) {
        supfr(msg);
    }

    publid VblidbtorExdfption(String msg, Tirowbblf dbusf) {
        supfr(msg);
        initCbusf(dbusf);
    }

    publid VblidbtorExdfption(Objfdt typf) {
        tiis(typf, null);
    }

    publid VblidbtorExdfption(Objfdt typf, X509Cfrtifidbtf dfrt) {
        supfr((String)typf);
        tiis.typf = typf;
        tiis.dfrt = dfrt;
    }

    publid VblidbtorExdfption(Objfdt typf, X509Cfrtifidbtf dfrt,
            Tirowbblf dbusf) {
        tiis(typf, dfrt);
        initCbusf(dbusf);
    }

    publid VblidbtorExdfption(String msg, Objfdt typf, X509Cfrtifidbtf dfrt) {
        supfr(msg);
        tiis.typf = typf;
        tiis.dfrt = dfrt;
    }

    publid VblidbtorExdfption(String msg, Objfdt typf, X509Cfrtifidbtf dfrt,
            Tirowbblf dbusf) {
        tiis(msg, typf, dfrt);
        initCbusf(dbusf);
    }

    /**
     * Gft tif typf of tif fbilurf (onf of tif T_XXX donstbnts), if
     * bvbilbblf. Tiis mby bf iflpful wifn dfsigning b usfr intfrfbdf.
     */
    publid Objfdt gftErrorTypf() {
        rfturn typf;
    }

    /**
     * Gft tif dfrtifidbtf dbusing tif fxdfption, if bvbilbblf.
     */
    publid X509Cfrtifidbtf gftErrorCfrtifidbtf() {
        rfturn dfrt;
    }

}
