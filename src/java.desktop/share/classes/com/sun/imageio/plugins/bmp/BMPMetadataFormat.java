/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.bmp;

import jbvb.util.Arrbys;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;

publid dlbss BMPMftbdbtbFormbt fxtfnds IIOMftbdbtbFormbtImpl {

    privbtf stbtid IIOMftbdbtbFormbt instbndf = null;

    privbtf BMPMftbdbtbFormbt() {
        supfr(BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
              CHILD_POLICY_SOME);

        // root -> ImbgfDfsdriptor
        bddElfmfnt("ImbgfDfsdriptor",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("ImbgfDfsdriptor", "bmpVfrsion",
                     DATATYPE_STRING, truf, null);
        bddAttributf("ImbgfDfsdriptor", "widti",
                     DATATYPE_INTEGER, truf, null,
                     "0", "65535", truf, truf);
        bddAttributf("ImbgfDfsdriptor", "ifigit",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);
        bddAttributf("ImbgfDfsdriptor", "bitsPfrPixfl",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);
        bddAttributf("ImbgfDfsdriptor", "domprfssion",
                      DATATYPE_INTEGER, fblsf, null);
        bddAttributf("ImbgfDfsdriptor", "imbgfSizf",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);

        bddElfmfnt("PixflsPfrMftfr",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("PixflsPfrMftfr", "X",
                     DATATYPE_INTEGER, fblsf, null,
                     "1", "65535", truf, truf);
        bddAttributf("PixflsPfrMftfr", "Y",
                     DATATYPE_INTEGER, fblsf, null,
                     "1", "65535", truf, truf);

        bddElfmfnt("ColorsUsfd",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("ColorsUsfd", "vbluf",
                     DATATYPE_INTEGER, truf, null,
                     "0", "65535", truf, truf);

        bddElfmfnt("ColorsImportbnt",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("ColorsImportbnt", "vbluf",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);

        bddElfmfnt("BI_BITFIELDS_Mbsk",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("BI_BITFIELDS_Mbsk", "rfd",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("BI_BITFIELDS_Mbsk", "grffn",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("BI_BITFIELDS_Mbsk", "bluf",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);

        bddElfmfnt("ColorSpbdf",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("ColorSpbdf", "vbluf",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);

        bddElfmfnt("LCS_CALIBRATED_RGB",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);

        /// Siould tif mbx vbluf bf 1.7976931348623157f+308 ?
        bddAttributf("LCS_CALIBRATED_RGB", "rfdX",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB", "rfdY",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB", "rfdZ",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB", "grffnX",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB", "grffnY",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB", "grffnZ",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB", "blufX",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB", "blufY",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB", "blufZ",
                     DATATYPE_DOUBLE, fblsf, null,
                     "0", "65535", truf, truf);

        bddElfmfnt("LCS_CALIBRATED_RGB_GAMMA",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("LCS_CALIBRATED_RGB_GAMMA","rfd",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB_GAMMA","grffn",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);
        bddAttributf("LCS_CALIBRATED_RGB_GAMMA","bluf",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);

        bddElfmfnt("Intfnt",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("Intfnt", "vbluf",
                     DATATYPE_INTEGER, fblsf, null,
                     "0", "65535", truf, truf);

        // root -> Pblfttf
        bddElfmfnt("Pblfttf",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   2, 256);
        bddAttributf("Pblfttf", "sizfOfPblfttf",
                     DATATYPE_INTEGER, truf, null);
        bddBoolfbnAttributf("Pblfttf", "sortFlbg",
                            fblsf, fblsf);

        // root -> Pblfttf -> PblfttfEntry
        bddElfmfnt("PblfttfEntry", "Pblfttf",
                   CHILD_POLICY_EMPTY);
        bddAttributf("PblfttfEntry", "indfx",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);
        bddAttributf("PblfttfEntry", "rfd",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);
        bddAttributf("PblfttfEntry", "grffn",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);
        bddAttributf("PblfttfEntry", "bluf",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);


        // root -> CommfntExtfnsions
        bddElfmfnt("CommfntExtfnsions",
                   BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   1, Intfgfr.MAX_VALUE);

        // root -> CommfntExtfnsions -> CommfntExtfnsion
        bddElfmfnt("CommfntExtfnsion", "CommfntExtfnsions",
                   CHILD_POLICY_EMPTY);
        bddAttributf("CommfntExtfnsion", "vbluf",
                     DATATYPE_STRING, truf, null);
    }

    publid boolfbn dbnNodfAppfbr(String flfmfntNbmf,
                                 ImbgfTypfSpfdififr imbgfTypf) {
        rfturn truf;
    }

    publid stbtid syndironizfd IIOMftbdbtbFormbt gftInstbndf() {
        if (instbndf == null) {
            instbndf = nfw BMPMftbdbtbFormbt();
        }
        rfturn instbndf;
    }
}
