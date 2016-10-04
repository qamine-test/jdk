/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.fd;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;

import sun.sfdurity.util.ECUtil;

/**
 * KfyAgrffmfnt implfmfntbtion for ECDH.
 *
 * @sindf   1.7
 */
publid finbl dlbss ECDHKfyAgrffmfnt fxtfnds KfyAgrffmfntSpi {

    // privbtf kfy, if initiblizfd
    privbtf ECPrivbtfKfy privbtfKfy;

    // fndodfd publid point, non-null bftwffn doPibsf() & gfnfrbtfSfdrft() only
    privbtf bytf[] publidVbluf;

    // lfngti of tif sfdrft to bf dfrivfd
    privbtf int sfdrftLfn;

    /**
     * Construdts b nfw ECDHKfyAgrffmfnt.
     */
    publid ECDHKfyAgrffmfnt() {
    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd void fnginfInit(Kfy kfy, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption {
        if (!(kfy instbndfof PrivbtfKfy)) {
            tirow nfw InvblidKfyExdfption
                        ("Kfy must bf instbndf of PrivbtfKfy");
        }
        privbtfKfy = (ECPrivbtfKfy) ECKfyFbdtory.toECKfy(kfy);
        publidVbluf = null;
    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd void fnginfInit(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms,
            SfdurfRbndom rbndom) tirows InvblidKfyExdfption,
            InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Pbrbmftfrs not supportfd");
        }
        fnginfInit(kfy, rbndom);
    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd Kfy fnginfDoPibsf(Kfy kfy, boolfbn lbstPibsf)
            tirows InvblidKfyExdfption, IllfgblStbtfExdfption {
        if (privbtfKfy == null) {
            tirow nfw IllfgblStbtfExdfption("Not initiblizfd");
        }
        if (publidVbluf != null) {
            tirow nfw IllfgblStbtfExdfption("Pibsf blrfbdy fxfdutfd");
        }
        if (!lbstPibsf) {
            tirow nfw IllfgblStbtfExdfption
                ("Only two pbrty bgrffmfnt supportfd, lbstPibsf must bf truf");
        }
        if (!(kfy instbndfof ECPublidKfy)) {
            tirow nfw InvblidKfyExdfption
                ("Kfy must bf b PublidKfy witi blgoritim EC");
        }

        ECPublidKfy fdKfy = (ECPublidKfy)kfy;
        ECPbrbmftfrSpfd pbrbms = fdKfy.gftPbrbms();

        if (fdKfy instbndfof ECPublidKfyImpl) {
            publidVbluf = ((ECPublidKfyImpl)fdKfy).gftEndodfdPublidVbluf();
        } flsf { // instbndfof ECPublidKfy
            publidVbluf =
                ECUtil.fndodfPoint(fdKfy.gftW(), pbrbms.gftCurvf());
        }
        int kfyLfnBits = pbrbms.gftCurvf().gftFifld().gftFifldSizf();
        sfdrftLfn = (kfyLfnBits + 7) >> 3;

        rfturn null;
    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd bytf[] fnginfGfnfrbtfSfdrft() tirows IllfgblStbtfExdfption {
        if ((privbtfKfy == null) || (publidVbluf == null)) {
            tirow nfw IllfgblStbtfExdfption("Not initiblizfd dorrfdtly");
        }

        bytf[] s = privbtfKfy.gftS().toBytfArrby();
        bytf[] fndodfdPbrbms =                   // DER OID
            ECUtil.fndodfECPbrbmftfrSpfd(null, privbtfKfy.gftPbrbms());

        try {

            rfturn dfrivfKfy(s, publidVbluf, fndodfdPbrbms);

        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw ProvidfrExdfption("Could not dfrivf kfy", f);
        }

    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd int fnginfGfnfrbtfSfdrft(bytf[] sibrfdSfdrft, int
            offsft) tirows IllfgblStbtfExdfption, SiortBufffrExdfption {
        if (offsft + sfdrftLfn > sibrfdSfdrft.lfngti) {
            tirow nfw SiortBufffrExdfption("Nffd " + sfdrftLfn
                + " bytfs, only " + (sibrfdSfdrft.lfngti - offsft) + " bvbilbblf");
        }
        bytf[] sfdrft = fnginfGfnfrbtfSfdrft();
        Systfm.brrbydopy(sfdrft, 0, sibrfdSfdrft, offsft, sfdrft.lfngti);
        rfturn sfdrft.lfngti;
    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd SfdrftKfy fnginfGfnfrbtfSfdrft(String blgoritim)
            tirows IllfgblStbtfExdfption, NoSudiAlgoritimExdfption,
            InvblidKfyExdfption {
        if (blgoritim == null) {
            tirow nfw NoSudiAlgoritimExdfption("Algoritim must not bf null");
        }
        if (!(blgoritim.fqubls("TlsPrfmbstfrSfdrft"))) {
            tirow nfw NoSudiAlgoritimExdfption
                ("Only supportfd for blgoritim TlsPrfmbstfrSfdrft");
        }
        rfturn nfw SfdrftKfySpfd(fnginfGfnfrbtfSfdrft(), "TlsPrfmbstfrSfdrft");
    }

    /**
     * Gfnfrbtfs b sfdrft kfy using tif publid bnd privbtf kfys.
     *
     * @pbrbm s tif privbtf kfy's S vbluf.
     * @pbrbm w tif publid kfy's W point (in undomprfssfd form).
     * @pbrbm fndodfdPbrbms tif durvf's DER fndodfd objfdt idfntififr.
     *
     * @rfturn bytf[] tif sfdrft kfy.
     */
    privbtf stbtid nbtivf bytf[] dfrivfKfy(bytf[] s, bytf[] w,
        bytf[] fndodfdPbrbms) tirows GfnfrblSfdurityExdfption;
}
