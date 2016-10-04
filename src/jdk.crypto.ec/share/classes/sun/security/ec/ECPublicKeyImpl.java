/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.*;

import sun.sfdurity.util.ECPbrbmftfrs;
import sun.sfdurity.util.ECUtil;

import sun.sfdurity.x509.*;

/**
 * Kfy implfmfntbtion for EC publid kfys.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss ECPublidKfyImpl fxtfnds X509Kfy implfmfnts ECPublidKfy {

    privbtf stbtid finbl long sfriblVfrsionUID = -2462037275160462289L;

    privbtf ECPoint w;
    privbtf ECPbrbmftfrSpfd pbrbms;

    /**
     * Construdt b kfy from its domponfnts. Usfd by tif
     * ECKfyFbdtory.
     */
    @SupprfssWbrnings("dfprfdbtion")
    ECPublidKfyImpl(ECPoint w, ECPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption {
        tiis.w = w;
        tiis.pbrbms = pbrbms;
        // gfnfrbtf tif fndoding
        blgid = nfw AlgoritimId
            (AlgoritimId.EC_oid, ECPbrbmftfrs.gftAlgoritimPbrbmftfrs(pbrbms));
        kfy = ECUtil.fndodfPoint(w, pbrbms.gftCurvf());
    }

    /**
     * Construdt b kfy from its fndoding.
     */
    ECPublidKfyImpl(bytf[] fndodfd) tirows InvblidKfyExdfption {
        dfdodf(fndodfd);
    }

    // sff JCA dod
    publid String gftAlgoritim() {
        rfturn "EC";
    }

    // sff JCA dod
    publid ECPoint gftW() {
        rfturn w;
    }

    // sff JCA dod
    publid ECPbrbmftfrSpfd gftPbrbms() {
        rfturn pbrbms;
    }

    // Intfrnbl API to gft tif fndodfd point. Currfntly usfd by SunPKCS11.
    // Tiis mby dibngf/go bwby dfpfnding on wibt wf do witi tif publid API.
    @SupprfssWbrnings("dfprfdbtion")
    publid bytf[] gftEndodfdPublidVbluf() {
        rfturn kfy.dlonf();
    }

    /**
     * Pbrsf tif kfy. Cbllfd by X509Kfy.
     */
    @SupprfssWbrnings("dfprfdbtion")
    protfdtfd void pbrsfKfyBits() tirows InvblidKfyExdfption {
        AlgoritimPbrbmftfrs blgPbrbms = tiis.blgid.gftPbrbmftfrs();
        if (blgPbrbms == null) {
            tirow nfw InvblidKfyExdfption("EC dombin pbrbmftfrs must bf " +
                "fndodfd in tif blgoritim idfntififr");
        }

        try {
            pbrbms = blgPbrbms.gftPbrbmftfrSpfd(ECPbrbmftfrSpfd.dlbss);
            w = ECUtil.dfdodfPoint(kfy, pbrbms.gftCurvf());
        } dbtdi (IOExdfption f) {
            tirow nfw InvblidKfyExdfption("Invblid EC kfy", f);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption f) {
            tirow nfw InvblidKfyExdfption("Invblid EC kfy", f);
        }
    }

    // rfturn b string rfprfsfntbtion of tiis kfy for dfbugging
    publid String toString() {
        rfturn "Sun EC publid kfy, " + pbrbms.gftCurvf().gftFifld().gftFifldSizf()
            + " bits\n  publid x doord: " + w.gftAffinfX()
            + "\n  publid y doord: " + w.gftAffinfY()
            + "\n  pbrbmftfrs: " + pbrbms;
    }

    protfdtfd Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn nfw KfyRfp(KfyRfp.Typf.PUBLIC,
                        gftAlgoritim(),
                        gftFormbt(),
                        gftEndodfd());
    }
}
