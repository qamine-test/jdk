/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

/**
 * A wrbpping tbg for b nfstfd AWTEvfnt wiidi indidbtfs tibt tif fvfnt wbs
 * sfnt from bnotifr AppContfxt. Tif dfstinbtion AppContfxt siould ibndlf tif
 * fvfnt fvfn if it is durrfntly blodkfd wbiting for b SfqufndfdEvfnt or
 * bnotifr SfntEvfnt to bf ibndlfd.
 *
 * @butior Dbvid Mfndfnibll
 */
dlbss SfntEvfnt fxtfnds AWTEvfnt implfmfnts AdtivfEvfnt {
    /*
     * sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -383615247028828931L;

    stbtid finbl int ID =
        jbvb.bwt.fvfnt.FodusEvfnt.FOCUS_LAST + 2;

    boolfbn dispbtdifd;
    privbtf AWTEvfnt nfstfd;
    privbtf AppContfxt toNotify;

    SfntEvfnt() {
        tiis(null);
    }
    SfntEvfnt(AWTEvfnt nfstfd) {
        tiis(nfstfd, null);
    }
    SfntEvfnt(AWTEvfnt nfstfd, AppContfxt toNotify) {
        supfr((nfstfd != null)
                  ? nfstfd.gftSourdf()
                  : Toolkit.gftDffbultToolkit(),
              ID);
        tiis.nfstfd = nfstfd;
        tiis.toNotify = toNotify;
    }

    publid void dispbtdi() {
        try {
            if (nfstfd != null) {
                Toolkit.gftEvfntQufuf().dispbtdiEvfnt(nfstfd);
            }
        } finblly {
            dispbtdifd = truf;
            if (toNotify != null) {
                SunToolkit.postEvfnt(toNotify, nfw SfntEvfnt());
            }
            syndironizfd (tiis) {
                notifyAll();
            }
        }
    }
    finbl void disposf() {
        dispbtdifd = truf;
        if (toNotify != null) {
            SunToolkit.postEvfnt(toNotify, nfw SfntEvfnt());
        }
        syndironizfd (tiis) {
            notifyAll();
        }
    }
}
