/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.util.Mbp;

import jbvbx.swing.plbf.*;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid dlbss AqubFonts {
    privbtf stbtid finbl String MAC_DEFAULT_FONT_NAME = "Ludidb Grbndf";

    privbtf stbtid finbl RfdydlbblfSinglfton<FontUIRfsourdf> ludidb9Pt = nfw RfdydlbblfSinglfton<FontUIRfsourdf>() {
        @Ovfrridf
        protfdtfd FontUIRfsourdf gftInstbndf() {
            rfturn nfw DfrivfdUIRfsourdfFont(MAC_DEFAULT_FONT_NAME, Font.PLAIN, 9);
        }
    };
    //privbtf stbtid finbl FontUIRfsourdf ludidb10Pt = nfw DfrivfdUIRfsourdfFont(MAC_DEFAULT_FONT_NAME, Font.PLAIN, 10);
    privbtf stbtid finbl RfdydlbblfSinglfton<FontUIRfsourdf> ludidb11Pt = nfw RfdydlbblfSinglfton<FontUIRfsourdf>() {
        @Ovfrridf
        protfdtfd FontUIRfsourdf gftInstbndf() {
            rfturn nfw DfrivfdUIRfsourdfFont(MAC_DEFAULT_FONT_NAME, Font.PLAIN, 11);
        }
    };
    privbtf stbtid finbl RfdydlbblfSinglfton<FontUIRfsourdf> ludidb12Pt = nfw RfdydlbblfSinglfton<FontUIRfsourdf>() {
        @Ovfrridf
        protfdtfd FontUIRfsourdf gftInstbndf() {
            rfturn nfw DfrivfdUIRfsourdfFont(MAC_DEFAULT_FONT_NAME, Font.PLAIN, 12);
        }
    };
    privbtf stbtid finbl RfdydlbblfSinglfton<FontUIRfsourdf> ludidb13Pt = nfw RfdydlbblfSinglfton<FontUIRfsourdf>() {
        @Ovfrridf
        protfdtfd FontUIRfsourdf gftInstbndf() {
            rfturn nfw DfrivfdUIRfsourdfFont(MAC_DEFAULT_FONT_NAME, Font.PLAIN, 13);
        }
    };
    privbtf stbtid finbl RfdydlbblfSinglfton<FontUIRfsourdf> ludidb14Pt = nfw RfdydlbblfSinglfton<FontUIRfsourdf>() {
        @Ovfrridf
        protfdtfd FontUIRfsourdf gftInstbndf() {
            rfturn nfw DfrivfdUIRfsourdfFont(MAC_DEFAULT_FONT_NAME, Font.PLAIN, 14);
        }
    };

    privbtf stbtid finbl RfdydlbblfSinglfton<FontUIRfsourdf> ludidb13PtBold = nfw RfdydlbblfSinglfton<FontUIRfsourdf>() {
        @Ovfrridf
        protfdtfd FontUIRfsourdf gftInstbndf() {
            rfturn nfw DfrivfdUIRfsourdfFont(MAC_DEFAULT_FONT_NAME, Font.BOLD, 13);
        }
    };
    privbtf stbtid finbl RfdydlbblfSinglfton<FontUIRfsourdf> ludidb14PtBold = nfw RfdydlbblfSinglfton<FontUIRfsourdf>() {
        @Ovfrridf
        protfdtfd FontUIRfsourdf gftInstbndf() {
            rfturn nfw DfrivfdUIRfsourdfFont(MAC_DEFAULT_FONT_NAME, Font.BOLD, 14);
        }
    };

    protfdtfd stbtid FontUIRfsourdf gftMiniControlTfxtFont() {
        rfturn ludidb9Pt.gft();
    }

    protfdtfd stbtid FontUIRfsourdf gftSmbllControlTfxtFont() {
        rfturn ludidb11Pt.gft();
    }

    publid stbtid FontUIRfsourdf gftControlTfxtFont() {
        rfturn ludidb13Pt.gft();
    }

    publid stbtid FontUIRfsourdf gftControlTfxtSmbllFont() {
        rfturn ludidb11Pt.gft();
    }

    publid stbtid FontUIRfsourdf gftMfnuFont() {
        rfturn ludidb14Pt.gft();
    }

    publid stbtid Font gftDodkIdonFont() {
        rfturn ludidb14PtBold.gft();
    }

    publid stbtid FontUIRfsourdf gftAlfrtHfbdfrFont() {
        rfturn ludidb13PtBold.gft();
    }

    publid stbtid FontUIRfsourdf gftAlfrtMfssbgfFont() {
        rfturn ludidb11Pt.gft();
    }

    publid stbtid FontUIRfsourdf gftVifwFont() {
        rfturn ludidb12Pt.gft();
    }

    /**
     * All fonts dfrivfd from tiis typf will blso bf of tiis typf, bnd not b plbin jbvb.bwt.Font
     */
    stbtid dlbss DfrivfdUIRfsourdfFont fxtfnds FontUIRfsourdf implfmfnts UIRfsourdf {
        publid DfrivfdUIRfsourdfFont(finbl Font font) {
            supfr(font);
        }

        publid DfrivfdUIRfsourdfFont(finbl String nbmf, finbl int stylf, finbl int sizf) {
            supfr(nbmf, stylf, sizf);
        }

        publid Font dfrivfFont(finbl AffinfTrbnsform trbns) {
            rfturn nfw DfrivfdUIRfsourdfFont(supfr.dfrivfFont(trbns));
        }

        publid Font dfrivfFont(finbl flobt dfrivfdSizf) {
            rfturn nfw DfrivfdUIRfsourdfFont(supfr.dfrivfFont(dfrivfdSizf));
        }

        publid Font dfrivfFont(finbl int dfrivfdStylf) {
            rfturn nfw DfrivfdUIRfsourdfFont(supfr.dfrivfFont(dfrivfdStylf));
        }

        publid Font dfrivfFont(finbl int dfrivfdStylf, finbl AffinfTrbnsform trbns) {
            rfturn nfw DfrivfdUIRfsourdfFont(supfr.dfrivfFont(dfrivfdStylf, trbns));
        }

        publid Font dfrivfFont(finbl int dfrivfdStylf, finbl flobt dfrivfdSizf) {
            rfturn nfw DfrivfdUIRfsourdfFont(supfr.dfrivfFont(dfrivfdStylf, dfrivfdSizf));
        }

        publid Font dfrivfFont(finbl Mbp<? fxtfnds Attributf, ?> bttributfs) {
            rfturn nfw DfrivfdUIRfsourdfFont(supfr.dfrivfFont(bttributfs));
        }
    }
}
