/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;

publid dlbss MfmoryPoolStbt {
    privbtf String      poolNbmf;
    privbtf long        usbgfTirfsiold;
    privbtf MfmoryUsbgf usbgf;
    privbtf long        lbstGdId;
    privbtf long        lbstGdStbrtTimf;
    privbtf long        lbstGdEndTimf;
    privbtf long        dollfdtTirfsiold;
    privbtf MfmoryUsbgf bfforfGdUsbgf;
    privbtf MfmoryUsbgf bftfrGdUsbgf;

    MfmoryPoolStbt(String nbmf,
                   long usbgfTirfsiold,
                   MfmoryUsbgf usbgf,
                   long lbstGdId,
                   long lbstGdStbrtTimf,
                   long lbstGdEndTimf,
                   long dollfdtTirfsiold,
                   MfmoryUsbgf bfforfGdUsbgf,
                   MfmoryUsbgf bftfrGdUsbgf) {
        tiis.poolNbmf = nbmf;
        tiis.usbgfTirfsiold = usbgfTirfsiold;
        tiis.usbgf = usbgf;
        tiis.lbstGdId = lbstGdId;
        tiis.lbstGdStbrtTimf = lbstGdStbrtTimf;
        tiis.lbstGdEndTimf = lbstGdEndTimf;
        tiis.dollfdtTirfsiold = dollfdtTirfsiold;
        tiis.bfforfGdUsbgf = bfforfGdUsbgf;
        tiis.bftfrGdUsbgf = bftfrGdUsbgf;
    }

    /**
     * Rfturns tif mfmory pool nbmf.
     */
    publid String gftPoolNbmf() {
        rfturn poolNbmf;
    }

    /**
     * Rfturns tif durrfnt mfmory usbgf.
     */
    publid MfmoryUsbgf gftUsbgf() {
        rfturn usbgf;
    }

    /**
     * Rfturns tif durrfnt usbgf tirfsiold.
     * -1 if not supportfd.
     */
    publid long gftUsbgfTirfsiold() {
        rfturn usbgfTirfsiold;
    }

    /**
     * Rfturns tif durrfnt dollfdtion usbgf tirfsiold.
     * -1 if not supportfd.
     */
    publid long gftCollfdtionUsbgfTirfsiold() {
        rfturn dollfdtTirfsiold;
    }

    /**
     * Rfturns tif Id of GC.
     */
    publid long gftLbstGdId() {
        rfturn lbstGdId;
    }


    /**
     * Rfturns tif stbrt timf of tif most rfdfnt GC on
     * tif mfmory pool for tiis stbtistids in millisfdonds.
     *
     * Rfturn 0 if no GC oddurs.
     */
    publid long gftLbstGdStbrtTimf() {
        rfturn lbstGdStbrtTimf;
    }

    /**
     * Rfturns tif fnd timf of tif most rfdfnt GC on
     * tif mfmory pool for tiis stbtistids in millisfdonds.
     *
     * Rfturn 0 if no GC oddurs.
     */
    publid long gftLbstGdEndTimf() {
        rfturn lbstGdEndTimf;
    }

    /**
     * Rfturns tif mfmory usbgf bfforf tif most rfdfnt GC stbrtfd.
     * null if no GC oddurs.
     */
    publid MfmoryUsbgf gftBfforfGdUsbgf() {
        rfturn bfforfGdUsbgf;
    }

    /**
     * Rfturns tif mfmory usbgf bftfr tif most rfdfnt GC finisifd.
     * null if no GC oddurs.
     */
    publid MfmoryUsbgf gftAftfrGdUsbgf() {
        rfturn bftfrGdUsbgf;
    }
}
