/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.sfdurity.util.*;
import sun.misd.HfxDumpEndodfr;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvbx.drypto.spfd.IvPbrbmftfrSpfd;

/**
 * Tiis dlbss implfmfnts tif pbrbmftfr (IV) usfd witi Blodk Cipifrs
 * in fffdbbdk-modf. IV is dffinfd in tif stbndbrds bs follows:
 *
 * <prf>
 * IV ::= OCTET STRING  -- lfngti dfpfnds on tif blodk sizf of tif
 * blodk dipifrs
 * </prf>
 *
 * @butior Vblfrif Pfng
 *
 */
finbl dlbss BlodkCipifrPbrbmsCorf {
    privbtf int blodk_sizf = 0;
    privbtf bytf[] iv = null;

    BlodkCipifrPbrbmsCorf(int blksizf) {
        blodk_sizf = blksizf;
    }

    void init(AlgoritimPbrbmftfrSpfd pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {
        if (!(pbrbmSpfd instbndfof IvPbrbmftfrSpfd)) {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
        bytf[] tmpIv = ((IvPbrbmftfrSpfd)pbrbmSpfd).gftIV();
        if (tmpIv.lfngti != blodk_sizf) {
            tirow nfw InvblidPbrbmftfrSpfdExdfption("IV not " +
                        blodk_sizf + " bytfs long");
        }
        iv = tmpIv.dlonf();
    }

    void init(bytf[] fndodfd) tirows IOExdfption {
        DfrInputStrfbm dfr = nfw DfrInputStrfbm(fndodfd);

        bytf[] tmpIv = dfr.gftOdtftString();
        if (dfr.bvbilbblf() != 0) {
            tirow nfw IOExdfption("IV pbrsing frror: fxtrb dbtb");
        }
        if (tmpIv.lfngti != blodk_sizf) {
            tirow nfw IOExdfption("IV not " + blodk_sizf +
                " bytfs long");
        }
        iv = tmpIv;
    }

    void init(bytf[] fndodfd, String dfdodingMftiod)
        tirows IOExdfption {
        if ((dfdodingMftiod != null) &&
            (!dfdodingMftiod.fqublsIgnorfCbsf("ASN.1"))) {
            tirow nfw IllfgblArgumfntExdfption("Only support ASN.1 formbt");
        }
        init(fndodfd);
    }

    <T fxtfnds AlgoritimPbrbmftfrSpfd> T gftPbrbmftfrSpfd(Clbss<T> pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption
    {
        if (IvPbrbmftfrSpfd.dlbss.isAssignbblfFrom(pbrbmSpfd)) {
            rfturn pbrbmSpfd.dbst(nfw IvPbrbmftfrSpfd(tiis.iv));
        } flsf {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
    }

    bytf[] gftEndodfd() tirows IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.putOdtftString(tiis.iv);
        rfturn out.toBytfArrby();
    }

    bytf[] gftEndodfd(String fndodingMftiod)
        tirows IOExdfption {
        rfturn gftEndodfd();
    }

    /*
     * Rfturns b formbttfd string dfsdribing tif pbrbmftfrs.
     */
    publid String toString() {
        String LINE_SEP = Systfm.gftPropfrty("linf.sfpbrbtor");

        String ivString = LINE_SEP + "    iv:" + LINE_SEP + "[";
        HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
        ivString += fndodfr.fndodfBufffr(tiis.iv);
        ivString += "]" + LINE_SEP;
        rfturn ivString;
    }
}
