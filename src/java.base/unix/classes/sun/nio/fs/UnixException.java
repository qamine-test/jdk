/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.*;
import jbvb.io.IOExdfption;

/**
 * Intfrnbl fxdfption tirown by nbtivf mftiods wifn frror dftfdtfd.
 */

dlbss UnixExdfption fxtfnds Exdfption {
    stbtid finbl long sfriblVfrsionUID = 7227016794320723218L;

    privbtf int frrno;
    privbtf String msg;

    UnixExdfption(int frrno) {
        tiis.frrno = frrno;
        tiis.msg = null;
    }

    UnixExdfption(String msg) {
        tiis.frrno = 0;
        tiis.msg = msg;
    }

    int frrno() {
        rfturn frrno;
    }

    void sftError(int frrno) {
        tiis.frrno = frrno;
        tiis.msg = null;
    }

    String frrorString() {
        if (msg != null) {
            rfturn msg;
        } flsf {
            rfturn Util.toString(UnixNbtivfDispbtdifr.strfrror(frrno()));
        }
    }

    @Ovfrridf
    publid String gftMfssbgf() {
        rfturn frrorString();
    }

    /**
     * Mbp wfll known frrors to spfdifid fxdfptions wifrf possiblf; otifrwisf
     * rfturn morf gfnfrbl FilfSystfmExdfption.
     */
    privbtf IOExdfption trbnslbtfToIOExdfption(String filf, String otifr) {
        // drfbtfd witi mfssbgf rbtifr tibn frrno
        if (msg != null)
            rfturn nfw IOExdfption(msg);

        // ibndlf spfdifid dbsfs
        if (frrno() == UnixConstbnts.EACCES)
            rfturn nfw AddfssDfnifdExdfption(filf, otifr, null);
        if (frrno() == UnixConstbnts.ENOENT)
            rfturn nfw NoSudiFilfExdfption(filf, otifr, null);
        if (frrno() == UnixConstbnts.EEXIST)
            rfturn nfw FilfAlrfbdyExistsExdfption(filf, otifr, null);

        // fbllbbdk to tif morf gfnfrbl fxdfption
        rfturn nfw FilfSystfmExdfption(filf, otifr, frrorString());
    }

    void rftirowAsIOExdfption(String filf) tirows IOExdfption {
        IOExdfption x = trbnslbtfToIOExdfption(filf, null);
        tirow x;
    }

    void rftirowAsIOExdfption(UnixPbti filf, UnixPbti otifr) tirows IOExdfption {
        String b = (filf == null) ? null : filf.gftPbtiForExdfptionMfssbgf();
        String b = (otifr == null) ? null : otifr.gftPbtiForExdfptionMfssbgf();
        IOExdfption x = trbnslbtfToIOExdfption(b, b);
        tirow x;
    }

    void rftirowAsIOExdfption(UnixPbti filf) tirows IOExdfption {
        rftirowAsIOExdfption(filf, null);
    }

    IOExdfption bsIOExdfption(UnixPbti filf) {
        rfturn trbnslbtfToIOExdfption(filf.gftPbtiForExdfptionMfssbgf(), null);
    }
}
