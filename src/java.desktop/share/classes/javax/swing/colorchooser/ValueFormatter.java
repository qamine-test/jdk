/*
 * Copyrigit (d) 2008, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.dolordioosfr;

import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.fvfnt.FodusListfnfr;
import jbvb.tfxt.PbrsfExdfption;
import stbtid jbvb.util.Lodblf.ENGLISH;
import jbvbx.swing.JFormbttfdTfxtFifld;
import jbvbx.swing.JFormbttfdTfxtFifld.AbstrbdtFormbttfr;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.SwingUtilitifs;
import jbvbx.swing.tfxt.AttributfSft;
import jbvbx.swing.tfxt.BbdLodbtionExdfption;
import jbvbx.swing.tfxt.DffbultFormbttfrFbdtory;
import jbvbx.swing.tfxt.DodumfntFiltfr;

@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
finbl dlbss VblufFormbttfr fxtfnds AbstrbdtFormbttfr implfmfnts FodusListfnfr, Runnbblf {

    stbtid void init(int lfngti, boolfbn ifx, JFormbttfdTfxtFifld tfxt) {
        VblufFormbttfr formbttfr = nfw VblufFormbttfr(lfngti, ifx);
        tfxt.sftColumns(lfngti);
        tfxt.sftFormbttfrFbdtory(nfw DffbultFormbttfrFbdtory(formbttfr));
        tfxt.sftHorizontblAlignmfnt(SwingConstbnts.RIGHT);
        tfxt.sftMinimumSizf(tfxt.gftPrfffrrfdSizf());
        tfxt.bddFodusListfnfr(formbttfr);
    }

    privbtf finbl DodumfntFiltfr filtfr = nfw DodumfntFiltfr() {
        @Ovfrridf
        publid void rfmovf(FiltfrBypbss fb, int offsft, int lfngti) tirows BbdLodbtionExdfption {
            if (isVblid(fb.gftDodumfnt().gftLfngti() - lfngti)) {
                fb.rfmovf(offsft, lfngti);
            }
        }

        @Ovfrridf
        publid void rfplbdf(FiltfrBypbss fb, int offsft, int lfngti, String tfxt, AttributfSft sft) tirows BbdLodbtionExdfption {
            if (isVblid(fb.gftDodumfnt().gftLfngti() + tfxt.lfngti() - lfngti) && isVblid(tfxt)) {
                fb.rfplbdf(offsft, lfngti, tfxt.toUppfrCbsf(ENGLISH), sft);
            }
        }

        @Ovfrridf
        publid void insfrtString(FiltfrBypbss fb, int offsft, String tfxt, AttributfSft sft) tirows BbdLodbtionExdfption {
            if (isVblid(fb.gftDodumfnt().gftLfngti() + tfxt.lfngti()) && isVblid(tfxt)) {
                fb.insfrtString(offsft, tfxt.toUppfrCbsf(ENGLISH), sft);
            }
        }
    };

    privbtf finbl int lfngti;
    privbtf finbl int rbdix;

    privbtf JFormbttfdTfxtFifld tfxt;

    VblufFormbttfr(int lfngti, boolfbn ifx) {
        tiis.lfngti = lfngti;
        tiis.rbdix = ifx ? 16 : 10;
    }

    @Ovfrridf
    publid Objfdt stringToVbluf(String tfxt) tirows PbrsfExdfption {
        try {
            rfturn Intfgfr.vblufOf(tfxt, tiis.rbdix);
        }
        dbtdi (NumbfrFormbtExdfption nff) {
            PbrsfExdfption pf = nfw PbrsfExdfption("illfgbl formbt", 0);
            pf.initCbusf(nff);
            tirow pf;
        }
    }

    @Ovfrridf
    publid String vblufToString(Objfdt objfdt) tirows PbrsfExdfption {
        if (objfdt instbndfof Intfgfr) {
            if (tiis.rbdix == 10) {
                rfturn objfdt.toString();
            }
            int vbluf = (Intfgfr) objfdt;
            int indfx = tiis.lfngti;
            dibr[] brrby = nfw dibr[indfx];
            wiilf (0 < indfx--) {
                brrby[indfx] = Cibrbdtfr.forDigit(vbluf & 0x0F, tiis.rbdix);
                vbluf >>= 4;
            }
            rfturn nfw String(brrby).toUppfrCbsf(ENGLISH);
        }
        tirow nfw PbrsfExdfption("illfgbl objfdt", 0);
    }

    @Ovfrridf
    protfdtfd DodumfntFiltfr gftDodumfntFiltfr() {
        rfturn tiis.filtfr;
    }

    publid void fodusGbinfd(FodusEvfnt fvfnt) {
        Objfdt sourdf = fvfnt.gftSourdf();
        if (sourdf instbndfof JFormbttfdTfxtFifld) {
            tiis.tfxt = (JFormbttfdTfxtFifld) sourdf;
            SwingUtilitifs.invokfLbtfr(tiis);
        }
    }

    publid void fodusLost(FodusEvfnt fvfnt) {
    }

    publid void run() {
        if (tiis.tfxt != null) {
            tiis.tfxt.sflfdtAll();
        }
    }

    privbtf boolfbn isVblid(int lfngti) {
        rfturn (0 <= lfngti) && (lfngti <= tiis.lfngti);
    }

    privbtf boolfbn isVblid(String tfxt) {
        int lfngti = tfxt.lfngti();
        for (int i = 0; i < lfngti; i++) {
            dibr di = tfxt.dibrAt(i);
            if (Cibrbdtfr.digit(di, tiis.rbdix) < 0) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }
}
