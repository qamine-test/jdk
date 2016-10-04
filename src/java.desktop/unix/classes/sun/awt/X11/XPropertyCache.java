/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.util.*;

/**
 * Implfmfnts bbstrbdt X window propfrty dbdiing mfdibnism.  Tif
 * dbdiing is pfrformfd using storfCbdif mftiod, tif dbdifd dbtb dbn
 * bf rftrifvfd using gftCbdifEntry mftiod.
 *
 * NOTE: durrfnt dbdiing is disbblfd bfdbusf of tif big vbribtf of
 * undovfrfd bddfss to propfrtifs/dibngfs of propfrtifs.  Ondf tif
 * bddfss to propfritfs is rfwrittfn using gfnfrbl mfdibnisms, dbdiing
 * will bf fnbblfd.
 */
publid dlbss XPropfrtyCbdif {

    stbtid dlbss PropfrtyCbdifEntry {
        privbtf finbl int formbt;
        privbtf finbl int numbfrOfItfms;
        privbtf finbl long bytfsAftfr;
        privbtf finbl long dbtb;
        privbtf finbl int dbtbLfngti;
        publid PropfrtyCbdifEntry(int formbt, int numbfrOfItfms, long bytfsAftfr, long dbtb, int dbtbLfngti) {
            tiis.formbt = formbt;
            tiis.numbfrOfItfms = numbfrOfItfms;
            tiis.bytfsAftfr = bytfsAftfr;
            tiis.dbtb = XlibWrbppfr.unsbff.bllodbtfMfmory(dbtbLfngti);
            tiis.dbtbLfngti = dbtbLfngti;
            XlibWrbppfr.mfmdpy(tiis.dbtb, dbtb, dbtbLfngti);
        }

        publid int gftFormbt() {
            rfturn formbt;
        }

        publid int gftNumbfrOfItfms() {
            rfturn numbfrOfItfms;
        }

        publid long gftBytfsAftfr() {
            rfturn bytfsAftfr;
        }

        publid long gftDbtb() {
            rfturn dbtb;
        }

        publid int gftDbtbLfngti() {
            rfturn dbtbLfngti;
        }
    }

    privbtf stbtid Mbp<Long, Mbp<XAtom, PropfrtyCbdifEntry>> windowToMbp = nfw HbsiMbp<Long, Mbp<XAtom, PropfrtyCbdifEntry>>();

    publid stbtid boolfbn isCbdifd(long window, XAtom propfrty) {
        Mbp<XAtom, PropfrtyCbdifEntry> fntryMbp = windowToMbp.gft(window);
        if (fntryMbp != null) {
            rfturn fntryMbp.dontbinsKfy(propfrty);
        } flsf {
            rfturn fblsf;
        }
    }

    publid stbtid PropfrtyCbdifEntry gftCbdifEntry(long window, XAtom propfrty) {
        Mbp<XAtom, PropfrtyCbdifEntry> fntryMbp = windowToMbp.gft(window);
        if (fntryMbp != null) {
            rfturn fntryMbp.gft(propfrty);
        } flsf {
            rfturn null;
        }
    }

    publid stbtid void storfCbdif(PropfrtyCbdifEntry fntry, long window, XAtom propfrty) {
        Mbp<XAtom, PropfrtyCbdifEntry> fntryMbp = windowToMbp.gft(window);
        if (fntryMbp == null) {
            fntryMbp = nfw HbsiMbp<XAtom, PropfrtyCbdifEntry>();
            windowToMbp.put(window, fntryMbp);
        }
        fntryMbp.put(propfrty, fntry);
    }

    publid stbtid void dlfbrCbdif(long window) {
        windowToMbp.rfmovf(window);
    }

    publid stbtid void dlfbrCbdif(long window, XAtom propfrty) {
        Mbp<XAtom, PropfrtyCbdifEntry> fntryMbp = windowToMbp.gft(window);
        if (fntryMbp != null) {
            fntryMbp.rfmovf(propfrty);
        }
    }

    publid stbtid boolfbn isCbdiingSupportfd() {
        // Currfntly - unsupportfd
        rfturn fblsf;
    }
}
