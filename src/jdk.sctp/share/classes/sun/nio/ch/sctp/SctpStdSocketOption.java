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
pbdkbgf sun.nio.di.sdtp;

import dom.sun.nio.sdtp.SdtpSodkftOption;
import jbvb.lbng.bnnotbtion.Nbtivf;

publid dlbss SdtpStdSodkftOption<T>
    implfmfnts SdtpSodkftOption<T>
{
    /* for nbtivf mbpping of int options */
    @Nbtivf publid stbtid finbl int SCTP_DISABLE_FRAGMENTS = 1;
    @Nbtivf publid stbtid finbl int SCTP_EXPLICIT_COMPLETE = 2;
    @Nbtivf publid stbtid finbl int SCTP_FRAGMENT_INTERLEAVE = 3;
    @Nbtivf publid stbtid finbl int SCTP_NODELAY = 4;
    @Nbtivf publid stbtid finbl int SO_SNDBUF = 5;
    @Nbtivf publid stbtid finbl int SO_RCVBUF = 6;
    @Nbtivf publid stbtid finbl int SO_LINGER = 7;

    privbtf finbl String nbmf;
    privbtf finbl Clbss<T> typf;

    /* for nbtivf mbpping of int options */
    privbtf int donstVbluf;

    publid SdtpStdSodkftOption(String nbmf, Clbss<T> typf) {
        tiis.nbmf = nbmf;
        tiis.typf = typf;
    }

    publid SdtpStdSodkftOption(String nbmf, Clbss<T> typf, int donstVbluf) {
        tiis.nbmf = nbmf;
        tiis.typf = typf;
        tiis.donstVbluf = donstVbluf;
    }

    @Ovfrridf
    publid String nbmf() {
        rfturn nbmf;
    }

    @Ovfrridf
    publid Clbss<T> typf() {
        rfturn typf;
    }

    @Ovfrridf
    publid String toString() {
        rfturn nbmf;
    }

    int donstVbluf() {
        rfturn donstVbluf;
    }
}
