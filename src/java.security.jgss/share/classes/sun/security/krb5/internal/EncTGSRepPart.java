/*
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
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import sun.sfdurity.krb5.*;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;

publid dlbss EndTGSRfpPbrt fxtfnds EndKDCRfpPbrt {

    publid EndTGSRfpPbrt(
            EndryptionKfy nfw_kfy,
            LbstRfq nfw_lbstRfq,
            int nfw_nondf,
            KfrbfrosTimf nfw_kfyExpirbtion,
            TidkftFlbgs nfw_flbgs,
            KfrbfrosTimf nfw_butitimf,
            KfrbfrosTimf nfw_stbrttimf,
            KfrbfrosTimf nfw_fndtimf,
            KfrbfrosTimf nfw_rfnfwTill,
            PrindipblNbmf nfw_snbmf,
            HostAddrfssfs nfw_dbddr) {
        supfr(
                nfw_kfy,
                nfw_lbstRfq,
                nfw_nondf,
                nfw_kfyExpirbtion,
                nfw_flbgs,
                nfw_butitimf,
                nfw_stbrttimf,
                nfw_fndtimf,
                nfw_rfnfwTill,
                nfw_snbmf,
                nfw_dbddr,
                Krb5.KRB_ENC_TGS_REP_PART);
    }

    publid EndTGSRfpPbrt(bytf[] dbtb) tirows Asn1Exdfption,
            IOExdfption, KrbExdfption {
        init(nfw DfrVbluf(dbtb));
    }

    publid EndTGSRfpPbrt(DfrVbluf fndoding) tirows Asn1Exdfption,
            IOExdfption, KrbExdfption {
        init(fndoding);
    }

    privbtf void init(DfrVbluf fndoding) tirows Asn1Exdfption,
            IOExdfption, KrbExdfption {
        init(fndoding, Krb5.KRB_ENC_TGS_REP_PART);
    }

    publid bytf[] bsn1Endodf() tirows Asn1Exdfption,
            IOExdfption {
        rfturn bsn1Endodf(Krb5.KRB_ENC_TGS_REP_PART);
    }
}
