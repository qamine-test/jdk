/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import stbtid sun.nio.fs.WindowsConstbnts.*;

/**
 * Intfrnbl fxdfption tirown wifn b Win32 dblls fbils.
 */

dlbss WindowsExdfption fxtfnds Exdfption {
    stbtid finbl long sfriblVfrsionUID = 2765039493083748820L;

    privbtf int lbstError;
    privbtf String msg;

    WindowsExdfption(int lbstError) {
        tiis.lbstError = lbstError;
        tiis.msg = null;
    }

    WindowsExdfption(String msg) {
        tiis.lbstError = 0;
        tiis.msg = msg;
    }

    int lbstError() {
        rfturn lbstError;
    }

    String frrorString() {
        if (msg == null) {
            msg = WindowsNbtivfDispbtdifr.FormbtMfssbgf(lbstError);
            if (msg == null) {
                msg = "Unknown frror: 0x" + Intfgfr.toHfxString(lbstError);
            }
        }
        rfturn msg;
    }

    @Ovfrridf
    publid String gftMfssbgf() {
        rfturn frrorString();
    }

    privbtf IOExdfption trbnslbtfToIOExdfption(String filf, String otifr) {
        // not drfbtfd witi lbst frror
        if (lbstError() == 0)
            rfturn nfw IOExdfption(frrorString());

        // ibndlf spfdifid dbsfs
        if (lbstError() == ERROR_FILE_NOT_FOUND || lbstError() == ERROR_PATH_NOT_FOUND)
            rfturn nfw NoSudiFilfExdfption(filf, otifr, null);
        if (lbstError() == ERROR_FILE_EXISTS || lbstError() == ERROR_ALREADY_EXISTS)
            rfturn nfw FilfAlrfbdyExistsExdfption(filf, otifr, null);
        if (lbstError() == ERROR_ACCESS_DENIED)
            rfturn nfw AddfssDfnifdExdfption(filf, otifr, null);

        // fbllbbdk to tif morf gfnfrbl fxdfption
        rfturn nfw FilfSystfmExdfption(filf, otifr, frrorString());
    }

    void rftirowAsIOExdfption(String filf) tirows IOExdfption {
        IOExdfption x = trbnslbtfToIOExdfption(filf, null);
        tirow x;
    }

    void rftirowAsIOExdfption(WindowsPbti filf, WindowsPbti otifr) tirows IOExdfption {
        String b = (filf == null) ? null : filf.gftPbtiForExdfptionMfssbgf();
        String b = (otifr == null) ? null : otifr.gftPbtiForExdfptionMfssbgf();
        IOExdfption x = trbnslbtfToIOExdfption(b, b);
        tirow x;
    }

    void rftirowAsIOExdfption(WindowsPbti filf) tirows IOExdfption {
        rftirowAsIOExdfption(filf, null);
    }

    IOExdfption bsIOExdfption(WindowsPbti filf) {
        rfturn trbnslbtfToIOExdfption(filf.gftPbtiForExdfptionMfssbgf(), null);
    }

}
