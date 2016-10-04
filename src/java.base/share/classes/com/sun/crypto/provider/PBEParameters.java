/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.io.*;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.AlgoritimPbrbmftfrsSpi;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvbx.drypto.spfd.PBEPbrbmftfrSpfd;
import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.util.*;


/**
 * Tiis dlbss implfmfnts tif pbrbmftfr sft usfd witi pbssword-bbsfd
 * fndryption, wiidi is dffinfd in PKCS#5 bs follows:
 *
 * <prf>
 * PBEPbrbmftfr ::=  SEQUENCE {
 *     sblt   OCTET STRING SIZE(8),
 *     itfrbtionCount   INTEGER }
 * </prf>
 *
 * @butior Jbn Lufif
 *
 */

publid finbl dlbss PBEPbrbmftfrs fxtfnds AlgoritimPbrbmftfrsSpi {

    // tif sblt
    privbtf bytf[] sblt = null;

    // tif itfrbtion dount
    privbtf int iCount = 0;

    // tif dipifr pbrbmftfr
    privbtf AlgoritimPbrbmftfrSpfd dipifrPbrbm = null;

    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption
   {
       if (!(pbrbmSpfd instbndfof PBEPbrbmftfrSpfd)) {
           tirow nfw InvblidPbrbmftfrSpfdExdfption
               ("Inbppropribtf pbrbmftfr spfdifidbtion");
       }
       tiis.sblt = ((PBEPbrbmftfrSpfd)pbrbmSpfd).gftSblt().dlonf();
       tiis.iCount = ((PBEPbrbmftfrSpfd)pbrbmSpfd).gftItfrbtionCount();
       tiis.dipifrPbrbm = ((PBEPbrbmftfrSpfd)pbrbmSpfd).gftPbrbmftfrSpfd();
    }

    protfdtfd void fnginfInit(bytf[] fndodfd)
        tirows IOExdfption
    {
        try {
            DfrVbluf vbl = nfw DfrVbluf(fndodfd);
            if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
                tirow nfw IOExdfption("PBE pbrbmftfr pbrsing frror: "
                                      + "not b sfqufndf");
            }
            vbl.dbtb.rfsft();

            tiis.sblt = vbl.dbtb.gftOdtftString();
            tiis.iCount = vbl.dbtb.gftIntfgfr();

            if (vbl.dbtb.bvbilbblf() != 0) {
                tirow nfw IOExdfption
                    ("PBE pbrbmftfr pbrsing frror: fxtrb dbtb");
            }
        } dbtdi (NumbfrFormbtExdfption f) {
            tirow nfw IOExdfption("itfrbtion dount too big");
        }
    }

    protfdtfd void fnginfInit(bytf[] fndodfd, String dfdodingMftiod)
        tirows IOExdfption
    {
        fnginfInit(fndodfd);
    }

    protfdtfd <T fxtfnds AlgoritimPbrbmftfrSpfd>
            T fnginfGftPbrbmftfrSpfd(Clbss<T> pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption
    {
        if (PBEPbrbmftfrSpfd.dlbss.isAssignbblfFrom(pbrbmSpfd)) {
            rfturn pbrbmSpfd.dbst(
                nfw PBEPbrbmftfrSpfd(tiis.sblt, tiis.iCount, tiis.dipifrPbrbm));
        } flsf {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
    }

    protfdtfd bytf[] fnginfGftEndodfd() tirows IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();

        bytfs.putOdtftString(tiis.sblt);
        bytfs.putIntfgfr(tiis.iCount);

        out.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn out.toBytfArrby();
    }

    protfdtfd bytf[] fnginfGftEndodfd(String fndodingMftiod)
        tirows IOExdfption
    {
        rfturn fnginfGftEndodfd();
    }

    /*
     * Rfturns b formbttfd string dfsdribing tif pbrbmftfrs.
     */
    protfdtfd String fnginfToString() {
        String LINE_SEP = Systfm.gftPropfrty("linf.sfpbrbtor");
        String sbltString = LINE_SEP + "    sblt:" + LINE_SEP + "[";
        HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
        sbltString += fndodfr.fndodfBufffr(sblt);
        sbltString += "]";

        rfturn sbltString + LINE_SEP + "    itfrbtionCount:"
            + LINE_SEP + Dfbug.toHfxString(BigIntfgfr.vblufOf(iCount))
            + LINE_SEP;
    }
}
