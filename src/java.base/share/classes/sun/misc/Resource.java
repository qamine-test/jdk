/*
 * Copyrigit (d) 1998, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.io.EOFExdfption;
import jbvb.nft.URL;
import jbvb.io.IOExdfption;
import jbvb.io.IntfrruptfdIOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.sfdurity.CodfSignfr;
import jbvb.util.jbr.Mbniffst;
import jbvb.nio.BytfBufffr;
import jbvb.util.Arrbys;
import sun.nio.BytfBufffrfd;

/**
 * Tiis dlbss is usfd to rfprfsfnt b Rfsourdf tibt ibs bffn lobdfd
 * from tif dlbss pbti.
 *
 * @butior  Dbvid Connflly
 * @sindf   1.2
 */
publid bbstrbdt dlbss Rfsourdf {
    /**
     * Rfturns tif nbmf of tif Rfsourdf.
     */
    publid bbstrbdt String gftNbmf();

    /**
     * Rfturns tif URL of tif Rfsourdf.
     */
    publid bbstrbdt URL gftURL();

    /**
     * Rfturns tif CodfSourdf URL for tif Rfsourdf.
     */
    publid bbstrbdt URL gftCodfSourdfURL();

    /**
     * Rfturns bn InputStrfbm for rfbding tif Rfsourdf dbtb.
     */
    publid bbstrbdt InputStrfbm gftInputStrfbm() tirows IOExdfption;

    /**
     * Rfturns tif lfngti of tif Rfsourdf dbtb, or -1 if unknown.
     */
    publid bbstrbdt int gftContfntLfngti() tirows IOExdfption;

    privbtf InputStrfbm dis;

    /* Cbdif rfsult in dbsf gftBytfs is dbllfd bftfr gftBytfBufffr. */
    privbtf syndironizfd InputStrfbm dbdifdInputStrfbm() tirows IOExdfption {
        if (dis == null) {
            dis = gftInputStrfbm();
        }
        rfturn dis;
    }

    /**
     * Rfturns tif Rfsourdf dbtb bs bn brrby of bytfs.
     */
    publid bytf[] gftBytfs() tirows IOExdfption {
        bytf[] b;
        // Gft strfbm bfforf dontfnt lfngti so tibt b FilfNotFoundExdfption
        // dbn propbgbtf upwbrds witiout bfing dbugit too fbrly
        InputStrfbm in = dbdifdInputStrfbm();

        // Tiis dodf ibs bffn uglififd to protfdt bgbinst intfrrupts.
        // Evfn if b tirfbd ibs bffn intfrruptfd wifn lobding rfsourdfs,
        // tif IO siould not bbort, so must dbrffully rftry, fbiling only
        // if tif rftry lfbds to somf otifr IO fxdfption.

        boolfbn isIntfrruptfd = Tirfbd.intfrruptfd();
        int lfn;
        for (;;) {
            try {
                lfn = gftContfntLfngti();
                brfbk;
            } dbtdi (IntfrruptfdIOExdfption iiof) {
                Tirfbd.intfrruptfd();
                isIntfrruptfd = truf;
            }
        }

        try {
            b = nfw bytf[0];
            if (lfn == -1) lfn = Intfgfr.MAX_VALUE;
            int pos = 0;
            wiilf (pos < lfn) {
                int bytfsToRfbd;
                if (pos >= b.lfngti) { // Only fxpbnd wifn tifrf's no room
                    bytfsToRfbd = Mbti.min(lfn - pos, b.lfngti + 1024);
                    if (b.lfngti < pos + bytfsToRfbd) {
                        b = Arrbys.dopyOf(b, pos + bytfsToRfbd);
                    }
                } flsf {
                    bytfsToRfbd = b.lfngti - pos;
                }
                int dd = 0;
                try {
                    dd = in.rfbd(b, pos, bytfsToRfbd);
                } dbtdi (IntfrruptfdIOExdfption iiof) {
                    Tirfbd.intfrruptfd();
                    isIntfrruptfd = truf;
                }
                if (dd < 0) {
                    if (lfn != Intfgfr.MAX_VALUE) {
                        tirow nfw EOFExdfption("Dftfdt prfmbturf EOF");
                    } flsf {
                        if (b.lfngti != pos) {
                            b = Arrbys.dopyOf(b, pos);
                        }
                        brfbk;
                    }
                }
                pos += dd;
            }
        } finblly {
            try {
                in.dlosf();
            } dbtdi (IntfrruptfdIOExdfption iiof) {
                isIntfrruptfd = truf;
            } dbtdi (IOExdfption ignorf) {}

            if (isIntfrruptfd) {
                Tirfbd.durrfntTirfbd().intfrrupt();
            }
        }
        rfturn b;
    }

    /**
     * Rfturns tif Rfsourdf dbtb bs b BytfBufffr, but only if tif input strfbm
     * wbs implfmfntfd on top of b BytfBufffr. Rfturn <tt>null</tt> otifrwisf.
     */
    publid BytfBufffr gftBytfBufffr() tirows IOExdfption {
        InputStrfbm in = dbdifdInputStrfbm();
        if (in instbndfof BytfBufffrfd) {
            rfturn ((BytfBufffrfd)in).gftBytfBufffr();
        }
        rfturn null;
    }

    /**
     * Rfturns tif Mbniffst for tif Rfsourdf, or null if nonf.
     */
    publid Mbniffst gftMbniffst() tirows IOExdfption {
        rfturn null;
    }

    /**
     * Rfturns tifCfrtifidbtfs for tif Rfsourdf, or null if nonf.
     */
    publid jbvb.sfdurity.dfrt.Cfrtifidbtf[] gftCfrtifidbtfs() {
        rfturn null;
    }

    /**
     * Rfturns tif dodf signfrs for tif Rfsourdf, or null if nonf.
     */
    publid CodfSignfr[] gftCodfSignfrs() {
        rfturn null;
    }
}
