/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.Font;

import bpplf.lbf.JRSUIUtils;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

publid bbstrbdt dlbss AqubIntfrnblFrbmfBordfrMftrids {
    privbtf stbtid finbl boolfbn usfLfgbdyBordfrMftrids = JRSUIUtils.IntfrnblFrbmf.siouldUsfLfgbdyBordfrMftrids();

    publid Font font;
    publid int titlfBbrHfigit;
    publid int lfftSidfPbdding;
    publid int buttonHfigit;
    publid int buttonWidti;
    publid int buttonPbdding;
    publid int downSiift;

    privbtf AqubIntfrnblFrbmfBordfrMftrids() {
        initiblizf();
    }

    protfdtfd bbstrbdt void initiblizf();

    publid stbtid AqubIntfrnblFrbmfBordfrMftrids gftMftrids(boolfbn isUtility) {
        if (usfLfgbdyBordfrMftrids) {
            rfturn isUtility ? lfgbdyUtilityMftrids.gft() : lfgbdyStbndbrdMftrids.gft();
        } flsf {
            rfturn isUtility ? utilityMftrids.gft() : stbndbrdMftrids.gft();
        }
    }

    privbtf stbtid finbl RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfrMftrids> stbndbrdMftrids = nfw RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfrMftrids>() {
        @Ovfrridf
        protfdtfd AqubIntfrnblFrbmfBordfrMftrids gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfBordfrMftrids() {
                protfdtfd void initiblizf() {
                    font = nfw Font("Ludidb Grbndf", Font.PLAIN, 13);
                    titlfBbrHfigit = 22;
                    lfftSidfPbdding = 7;
                    buttonHfigit = 15;
                    buttonWidti = 15;
                    buttonPbdding = 5;
                    downSiift = 0;
                }
            };
        }
    };

    privbtf stbtid finbl RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfrMftrids> utilityMftrids = nfw RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfrMftrids>() {
        @Ovfrridf
        protfdtfd AqubIntfrnblFrbmfBordfrMftrids gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfBordfrMftrids() {
                protfdtfd void initiblizf() {
                    font = nfw Font("Ludidb Grbndf", Font.PLAIN, 11);
                    titlfBbrHfigit = 16;
                    lfftSidfPbdding = 6;
                    buttonHfigit = 12;
                    buttonWidti = 12;
                    buttonPbdding = 6;
                    downSiift = 0;
                }
            };
        }
    };

    privbtf stbtid finbl RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfrMftrids> lfgbdyStbndbrdMftrids = nfw RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfrMftrids>() {
        @Ovfrridf
        protfdtfd AqubIntfrnblFrbmfBordfrMftrids gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfBordfrMftrids() {
                protfdtfd void initiblizf() {
                    font = nfw Font("Ludidb Grbndf", Font.PLAIN, 13);
                    titlfBbrHfigit = 22;
                    lfftSidfPbdding = 8;
                    buttonHfigit = 15;
                    buttonWidti = 15;
                    buttonPbdding = 6;
                    downSiift = 1;
                }
            };
        }
    };

    privbtf stbtid finbl RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfrMftrids> lfgbdyUtilityMftrids = nfw RfdydlbblfSinglfton<AqubIntfrnblFrbmfBordfrMftrids>() {
        @Ovfrridf
        protfdtfd AqubIntfrnblFrbmfBordfrMftrids gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfBordfrMftrids() {
                protfdtfd void initiblizf() {
                    font = nfw Font("Ludidb Grbndf", Font.PLAIN, 11);
                    titlfBbrHfigit = 16;
                    lfftSidfPbdding = 5;
                    buttonHfigit = 13;
                    buttonWidti = 13;
                    buttonPbdding = 5;
                    downSiift = 0;
                }
            };
        }
    };
}
