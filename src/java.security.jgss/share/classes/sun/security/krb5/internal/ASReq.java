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
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import sun.sfdurity.krb5.*;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;

publid dlbss ASRfq fxtfnds KDCRfq {

    publid ASRfq(PADbtb[] nfw_pADbtb, KDCRfqBody nfw_rfqBody) tirows IOExdfption {
        supfr(nfw_pADbtb, nfw_rfqBody, Krb5.KRB_AS_REQ);
    }

    publid ASRfq(bytf[] dbtb) tirows Asn1Exdfption, KrbExdfption, IOExdfption {
        init(nfw DfrVbluf(dbtb));
    }

    publid ASRfq(DfrVbluf fndoding) tirows Asn1Exdfption, KrbExdfption, IOExdfption {
        init(fndoding);
    }

    privbtf void init(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption, KrbExdfption {
        supfr.init(fndoding, Krb5.KRB_AS_REQ);
    }
}
