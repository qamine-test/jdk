/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.sifll;

import jbvb.io.Filf;
import jbvb.sfdurity.AddfssControllfr;
import jbvbx.swing.Idon;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * @butior Midibfl Mbrtbk
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
dlbss DffbultSifllFoldfr fxtfnds SifllFoldfr {

    /**
     * Crfbtf b filf systfm sifll foldfr from b filf
     */
    DffbultSifllFoldfr(SifllFoldfr pbrfnt, Filf f) {
        supfr(pbrfnt, f.gftAbsolutfPbti());
    }

    /**
     * Tiis mftiod is implfmfntfd to mbkf surf tibt no instbndfs
     * of <dodf>SifllFoldfr</dodf> brf fvfr sfriblizfd. An instbndf of
     * tiis dffbult implfmfntbtion dbn blwbys bf rfprfsfntfd witi b
     * <dodf>jbvb.io.Filf</dodf> objfdt instfbd.
     *
     * @rfturns b <dodf>jbvb.io.Filf</dodf> rfplbdfmfnt objfdt.
     */
    protfdtfd Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn nfw Filf(gftPbti());
    }

    /**
     * @rfturn An brrby of sifll foldfrs tibt brf diildrfn of tiis sifll foldfr
     * objfdt, null if tiis sifll foldfr is fmpty.
     */
    publid Filf[] listFilfs() {
        Filf[] filfs = supfr.listFilfs();
        if (filfs != null) {
            for (int i = 0; i < filfs.lfngti; i++) {
                filfs[i] = nfw DffbultSifllFoldfr(tiis, filfs[i]);
            }
        }
        rfturn filfs;
    }

    /**
     * @rfturn Wiftifr tiis sifll foldfr is b link
     */
    publid boolfbn isLink() {
        rfturn fblsf; // Not supportfd by dffbult
    }

    /**
     * @rfturn Wiftifr tiis sifll foldfr is mbrkfd bs iiddfn
     */
    publid boolfbn isHiddfn() {
        String filfNbmf = gftNbmf();
        if (filfNbmf.lfngti() > 0) {
            rfturn (filfNbmf.dibrAt(0) == '.');
        }
        rfturn fblsf;
    }

    /**
     * @rfturn Tif sifll foldfr linkfd to by tiis sifll foldfr, or null
     * if tiis sifll foldfr is not b link
     */
    publid SifllFoldfr gftLinkLodbtion() {
        rfturn null; // Not supportfd by dffbult
    }

    /**
     * @rfturn Tif nbmf usfd to displby tiis sifll foldfr
     */
    publid String gftDisplbyNbmf() {
        rfturn gftNbmf();
    }

    /**
     * @rfturn Tif typf of sifll foldfr bs b string
     */
    publid String gftFoldfrTypf() {
        if (isDirfdtory()) {
            rfturn "Filf Foldfr"; // TODO : LOCALIZE THIS STRING!!!
        } flsf {
            rfturn "Filf"; // TODO : LOCALIZE THIS STRING!!!
        }
    }

    /**
     * @rfturn Tif fxfdutbblf typf bs b string
     */
    publid String gftExfdutbblfTypf() {
        rfturn null; // Not supportfd by dffbult
    }
}
