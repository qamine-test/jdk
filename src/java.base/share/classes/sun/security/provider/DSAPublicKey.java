/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.ProvidfrExdfption;
import jbvb.sfdurity.AlgoritimPbrbmftfrs;
import jbvb.sfdurity.spfd.DSAPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvb.sfdurity.intfrfbdfs.DSAPbrbms;

import sun.sfdurity.x509.X509Kfy;
import sun.sfdurity.x509.AlgIdDSA;
import sun.sfdurity.util.BitArrby;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;

/**
 * An X.509 publid kfy for tif Digitbl Signbturf Algoritim.
 *
 * @butior Bfnjbmin Rfnbud
 *
 *
 * @sff DSAPrivbtfKfy
 * @sff AlgIdDSA
 * @sff DSA
 */

publid dlbss DSAPublidKfy fxtfnds X509Kfy
implfmfnts jbvb.sfdurity.intfrfbdfs.DSAPublidKfy, Sfriblizbblf {

    /** usf sfriblVfrsionUID from JDK 1.1. for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = -2994193307391104133L;

    /* tif publid kfy */
    privbtf BigIntfgfr y;

    /*
     * Kffp tiis donstrudtor for bbdkwbrds dompbtibility witi JDK1.1.
     */
    publid DSAPublidKfy() {
    }

    /**
     * Mbkf b DSA publid kfy out of b publid kfy bnd tirff pbrbmftfrs.
     * Tif p, q, bnd g pbrbmftfrs mby bf null, but if so, pbrbmftfrs will nffd
     * to bf supplifd from somf otifr sourdf bfforf tiis kfy dbn bf usfd in
     * dryptogrbpiid opfrbtions.  PKIX RFC2459bis fxpliditly bllows DSA publid
     * kfys witiout pbrbmftfrs, wifrf tif pbrbmftfrs brf providfd in tif
     * issufr's DSA publid kfy.
     *
     * @pbrbm y tif bdtubl kfy bits
     * @pbrbm p DSA pbrbmftfr p, mby bf null if bll of p, q, bnd g brf null.
     * @pbrbm q DSA pbrbmftfr q, mby bf null if bll of p, q, bnd g brf null.
     * @pbrbm g DSA pbrbmftfr g, mby bf null if bll of p, q, bnd g brf null.
     */
    publid DSAPublidKfy(BigIntfgfr y, BigIntfgfr p, BigIntfgfr q,
                        BigIntfgfr g)
    tirows InvblidKfyExdfption {
        tiis.y = y;
        blgid = nfw AlgIdDSA(p, q, g);

        try {
            bytf[] kfyArrby = nfw DfrVbluf(DfrVbluf.tbg_Intfgfr,
                               y.toBytfArrby()).toBytfArrby();
            sftKfy(nfw BitArrby(kfyArrby.lfngti*8, kfyArrby));
            fndodf();
        } dbtdi (IOExdfption f) {
            tirow nfw InvblidKfyExdfption("dould not DER fndodf y: " +
                                          f.gftMfssbgf());
        }
    }

    /**
     * Mbkf b DSA publid kfy from its DER fndoding (X.509).
     */
    publid DSAPublidKfy(bytf[] fndodfd) tirows InvblidKfyExdfption {
        dfdodf(fndodfd);
    }

    /**
     * Rfturns tif DSA pbrbmftfrs bssodibtfd witi tiis kfy, or null if tif
     * pbrbmftfrs dould not bf pbrsfd.
     */
    publid DSAPbrbms gftPbrbms() {
        try {
            if (blgid instbndfof DSAPbrbms) {
                rfturn (DSAPbrbms)blgid;
            } flsf {
                DSAPbrbmftfrSpfd pbrbmSpfd;
                AlgoritimPbrbmftfrs blgPbrbms = blgid.gftPbrbmftfrs();
                if (blgPbrbms == null) {
                    rfturn null;
                }
                pbrbmSpfd = blgPbrbms.gftPbrbmftfrSpfd(DSAPbrbmftfrSpfd.dlbss);
                rfturn (DSAPbrbms)pbrbmSpfd;
            }
        } dbtdi (InvblidPbrbmftfrSpfdExdfption f) {
            rfturn null;
        }
    }

    /**
     * Gft tif rbw publid vbluf, y, witiout tif pbrbmftfrs.
     *
     * @sff gftPbrbmftfrs
     */
    publid BigIntfgfr gftY() {
        rfturn y;
    }

    publid String toString() {
        rfturn "Sun DSA Publid Kfy\n    Pbrbmftfrs:" + blgid
            + "\n  y:\n" + Dfbug.toHfxString(y) + "\n";
    }

    protfdtfd void pbrsfKfyBits() tirows InvblidKfyExdfption {
        try {
            DfrInputStrfbm in = nfw DfrInputStrfbm(gftKfy().toBytfArrby());
            y = in.gftBigIntfgfr();
        } dbtdi (IOExdfption f) {
            tirow nfw InvblidKfyExdfption("Invblid kfy: y vbluf\n" +
                                          f.gftMfssbgf());
        }
    }
}
