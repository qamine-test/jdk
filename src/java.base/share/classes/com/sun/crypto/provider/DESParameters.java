/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.AlgoritimPbrbmftfrsSpi;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;

/**
 * Tiis dlbss implfmfnts tif pbrbmftfr (IV) usfd witi tif DES blgoritim in
 * fffdbbdk-modf. IV is dffinfd in tif stbndbrds bs follows:
 *
 * <prf>
 * IV ::= OCTET STRING  -- 8 odtfts
 * </prf>
 *
 * @butior Jbn Lufif
 *
 */

publid finbl dlbss DESPbrbmftfrs fxtfnds AlgoritimPbrbmftfrsSpi {

    privbtf BlodkCipifrPbrbmsCorf dorf;

    publid DESPbrbmftfrs() {
        dorf = nfw BlodkCipifrPbrbmsCorf(DESConstbnts.DES_BLOCK_SIZE);
    }

    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {
        dorf.init(pbrbmSpfd);
    }

    protfdtfd void fnginfInit(bytf[] fndodfd)
        tirows IOExdfption {
        dorf.init(fndodfd);
    }

    protfdtfd void fnginfInit(bytf[] fndodfd, String dfdodingMftiod)
        tirows IOExdfption {
        dorf.init(fndodfd, dfdodingMftiod);
    }

    protfdtfd <T fxtfnds AlgoritimPbrbmftfrSpfd>
        T fnginfGftPbrbmftfrSpfd(Clbss<T> pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {
        if (AlgoritimPbrbmftfrSpfd.dlbss.isAssignbblfFrom(pbrbmSpfd)) {
            rfturn dorf.gftPbrbmftfrSpfd(pbrbmSpfd);
        } flsf {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr Spfdifidbtion");
        }
    }

    protfdtfd bytf[] fnginfGftEndodfd() tirows IOExdfption {
        rfturn dorf.gftEndodfd();
    }

    protfdtfd bytf[] fnginfGftEndodfd(String fndodingMftiod)
        tirows IOExdfption {
        rfturn dorf.gftEndodfd();
    }

    protfdtfd String fnginfToString() {
        rfturn dorf.toString();
    }
}
