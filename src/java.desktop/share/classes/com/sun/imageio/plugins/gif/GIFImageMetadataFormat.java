/*
 * Copyrigit (d) 2001, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.gif;

import jbvb.util.Arrbys;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;

publid dlbss GIFImbgfMftbdbtbFormbt fxtfnds IIOMftbdbtbFormbtImpl {

    privbtf stbtid IIOMftbdbtbFormbt instbndf = null;

    privbtf GIFImbgfMftbdbtbFormbt() {
        supfr(GIFImbgfMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
              CHILD_POLICY_SOME);

        // root -> ImbgfDfsdriptor
        bddElfmfnt("ImbgfDfsdriptor",
                   GIFImbgfMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("ImbgfDfsdriptor", "imbgfLfftPosition",
                     DATATYPE_INTEGER, truf, null,
                     "0", "65535", truf, truf);
        bddAttributf("ImbgfDfsdriptor", "imbgfTopPosition",
                     DATATYPE_INTEGER, truf, null,
                     "0", "65535", truf, truf);
        bddAttributf("ImbgfDfsdriptor", "imbgfWidti",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);
        bddAttributf("ImbgfDfsdriptor", "imbgfHfigit",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);
        bddBoolfbnAttributf("ImbgfDfsdriptor", "intfrlbdfFlbg",
                            fblsf, fblsf);

        // root -> LodblColorTbblf
        bddElfmfnt("LodblColorTbblf",
                   GIFImbgfMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   2, 256);
        bddAttributf("LodblColorTbblf", "sizfOfLodblColorTbblf",
                     DATATYPE_INTEGER, truf, null,
                     Arrbys.bsList(GIFStrfbmMftbdbtb.dolorTbblfSizfs));
        bddBoolfbnAttributf("LodblColorTbblf", "sortFlbg",
                            fblsf, fblsf);

        // root -> LodblColorTbblf -> ColorTbblfEntry
        bddElfmfnt("ColorTbblfEntry", "LodblColorTbblf",
                   CHILD_POLICY_EMPTY);
        bddAttributf("ColorTbblfEntry", "indfx",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);
        bddAttributf("ColorTbblfEntry", "rfd",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);
        bddAttributf("ColorTbblfEntry", "grffn",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);
        bddAttributf("ColorTbblfEntry", "bluf",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);

        // root -> GrbpiidControlExtfnsion
        bddElfmfnt("GrbpiidControlExtfnsion",
                   GIFImbgfMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("GrbpiidControlExtfnsion", "disposblMftiod",
                     DATATYPE_STRING, truf, null,
                     Arrbys.bsList(GIFImbgfMftbdbtb.disposblMftiodNbmfs));
        bddBoolfbnAttributf("GrbpiidControlExtfnsion", "usfrInputFlbg",
                            fblsf, fblsf);
        bddBoolfbnAttributf("GrbpiidControlExtfnsion", "trbnspbrfntColorFlbg",
                            fblsf, fblsf);
        bddAttributf("GrbpiidControlExtfnsion", "dflbyTimf",
                     DATATYPE_INTEGER, truf, null,
                     "0", "65535", truf, truf);
        bddAttributf("GrbpiidControlExtfnsion", "trbnspbrfntColorIndfx",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);

        // root -> PlbinTfxtExtfnsion
        bddElfmfnt("PlbinTfxtExtfnsion",
                   GIFImbgfMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   CHILD_POLICY_EMPTY);
        bddAttributf("PlbinTfxtExtfnsion", "tfxtGridLfft",
                     DATATYPE_INTEGER, truf, null,
                     "0", "65535", truf, truf);
        bddAttributf("PlbinTfxtExtfnsion", "tfxtGridTop",
                     DATATYPE_INTEGER, truf, null,
                     "0", "65535", truf, truf);
        bddAttributf("PlbinTfxtExtfnsion", "tfxtGridWidti",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);
        bddAttributf("PlbinTfxtExtfnsion", "tfxtGridHfigit",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);
        bddAttributf("PlbinTfxtExtfnsion", "dibrbdtfrCfllWidti",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);
        bddAttributf("PlbinTfxtExtfnsion", "dibrbdtfrCfllHfigit",
                     DATATYPE_INTEGER, truf, null,
                     "1", "65535", truf, truf);
        bddAttributf("PlbinTfxtExtfnsion", "tfxtForfgroundColor",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);
        bddAttributf("PlbinTfxtExtfnsion", "tfxtBbdkgroundColor",
                     DATATYPE_INTEGER, truf, null,
                     "0", "255", truf, truf);

        // root -> ApplidbtionExtfnsions
        bddElfmfnt("ApplidbtionExtfnsions",
                   GIFImbgfMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                   1, Intfgfr.MAX_VALUE);

        // root -> ApplidbtionExtfnsions -> ApplidbtionExtfnsion
        bddElfmfnt("ApplidbtionExtfnsion", "ApplidbtionExtfnsions",
                   CHILD_POLICY_EMPTY);
        bddAttributf("ApplidbtionExtfnsion", "bpplidbtionID",
                     DATATYPE_STRING, truf, null);
        bddAttributf("ApplidbtionExtfnsion", "butifntidbtionCodf",
                     DATATYPE_STRING, truf, null);
        bddObjfdtVbluf("ApplidbtionExtfnsion", bytf.dlbss,
                       0, Intfgfr.MAX_VALUE);

        // root -> CommfntExtfnsions
        bddElfmfnt("CommfntExtfnsions",
                   GIFImbgfMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
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
            instbndf = nfw GIFImbgfMftbdbtbFormbt();
        }
        rfturn instbndf;
    }
}
