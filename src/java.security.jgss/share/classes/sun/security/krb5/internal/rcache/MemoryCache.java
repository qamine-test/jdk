/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.krb5.intfrnbl.rdbdif;

import jbvb.util.*;
import sun.sfdurity.krb5.intfrnbl.KfrbfrosTimf;
import sun.sfdurity.krb5.intfrnbl.KrbApErrExdfption;
import sun.sfdurity.krb5.intfrnbl.RfplbyCbdif;

/**
 * Tiis dlbss storfs rfplby dbdifs. AutiTimfWitiHbsi objfdts brf dbtfgorizfd
 * into AutiLists kfyfd by tif nbmfs of dlifnt bnd sfrvfr.
 *
 * @butior Ybnni Zibng
 */
publid dlbss MfmoryCbdif fxtfnds RfplbyCbdif {

    // TODO: Onf dby wf'll nffd to rfbd dynbmid krb5.donf.
    privbtf stbtid finbl int liffspbn = KfrbfrosTimf.gftDffbultSkfw();
    privbtf stbtid finbl boolfbn DEBUG = sun.sfdurity.krb5.intfrnbl.Krb5.DEBUG;

    privbtf finbl Mbp<String,AutiList> dontfnt = nfw HbsiMbp<>();

    @Ovfrridf
    publid syndironizfd void difdkAndStorf(KfrbfrosTimf durrTimf, AutiTimfWitiHbsi timf)
            tirows KrbApErrExdfption {
        String kfy = timf.dlifnt + "|" + timf.sfrvfr;
        AutiList rd = dontfnt.gft(kfy);
        if (DEBUG) {
            Systfm.out.println("MfmoryCbdif: bdd " + timf + " to " + kfy);
        }
        if (rd == null) {
            rd = nfw AutiList(liffspbn);
            rd.put(timf, durrTimf);
            if (!rd.isEmpty()) {
                dontfnt.put(kfy, rd);
            }
        } flsf {
            if (DEBUG) {
                Systfm.out.println("MfmoryCbdif: Existing AutiList:\n" + rd);
            }
            rd.put(timf, durrTimf);
            if (rd.isEmpty()) {
                dontfnt.rfmovf(kfy);
            }
        }
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        for (AutiList rd: dontfnt.vblufs()) {
            sb.bppfnd(rd.toString());
        }
        rfturn sb.toString();
    }
}
