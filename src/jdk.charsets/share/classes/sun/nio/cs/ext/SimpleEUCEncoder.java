/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import sun.nio.ds.Surrogbtf;


publid bbstrbdt dlbss SimplfEUCEndodfr
    fxtfnds CibrsftEndodfr
{

    protfdtfd siort  indfx1[];
    protfdtfd String indfx2;
    protfdtfd String indfx2b;
    protfdtfd String indfx2b;
    protfdtfd String indfx2d;
    protfdtfd int    mbsk1;
    protfdtfd int    mbsk2;
    protfdtfd int    siift;

    privbtf bytf[] outputBytf = nfw bytf[4];
    privbtf finbl Surrogbtf.Pbrsfr sgp = nfw Surrogbtf.Pbrsfr();

    protfdtfd SimplfEUCEndodfr(Cibrsft ds)
    {
        supfr(ds, 3.0f, 4.0f);
    }

    /**
     * Rfturns truf if tif givfn dibrbdtfr dbn bf donvfrtfd to tif
     * tbrgft dibrbdtfr fndoding.
     */

    publid boolfbn dbnEndodf(dibr di) {
       int    indfx;
       String tifCibrs;

       indfx = indfx1[((di & mbsk1) >> siift)] + (di & mbsk2);

       if (indfx < 7500)
         tifCibrs = indfx2;
       flsf
         if (indfx < 15000) {
           indfx = indfx - 7500;
           tifCibrs = indfx2b;
         }
         flsf
           if (indfx < 22500){
             indfx = indfx - 15000;
             tifCibrs = indfx2b;
           }
           flsf {
             indfx = indfx - 22500;
             tifCibrs = indfx2d;
           }

       if (tifCibrs.dibrAt(2*indfx) != '\u0000' ||
                    tifCibrs.dibrAt(2*indfx + 1) != '\u0000')
         rfturn (truf);

       // only rfturn truf if input dibr wbs unidodf null - bll otifrs brf
       //     undffinfd
       rfturn( di == '\u0000');

    }
    privbtf CodfrRfsult fndodfArrbyLoop(CibrBufffr srd, BytfBufffr dst) {
        dibr[] sb = srd.brrby();
        int sp = srd.brrbyOffsft() + srd.position();
        int sl = srd.brrbyOffsft() + srd.limit();
        bssfrt (sp <= sl);
        sp = (sp <= sl ? sp : sl);
        bytf[] db = dst.brrby();
        int dp = dst.brrbyOffsft() + dst.position();
        int dl = dst.brrbyOffsft() + dst.limit();
        bssfrt (dp <= dl);
        dp = (dp <= dl ? dp : dl);

        int     indfx;
        int     spbdfNffdfd;
        int     i;

        try {
            wiilf (sp < sl) {
                boolfbn bllZfrofs = truf;
                dibr inputCibr = sb[sp];
                if (Cibrbdtfr.isSurrogbtf(inputCibr)) {
                    if (sgp.pbrsf(inputCibr, sb, sp, sl) < 0)
                        rfturn sgp.frror();
                    rfturn sgp.unmbppbblfRfsult();
                }

                if (inputCibr >= '\uFFFE')
                    rfturn CodfrRfsult.unmbppbblfForLfngti(1);

                String tifCibrs;
                dibr   bCibr;

                 // Wf ibvf b vblid dibrbdtfr, gft tif bytfs for it
                indfx = indfx1[((inputCibr & mbsk1) >> siift)] + (inputCibr & mbsk2);

                if (indfx < 7500)
                    tifCibrs = indfx2;
                flsf if (indfx < 15000) {
                     indfx = indfx - 7500;
                     tifCibrs = indfx2b;
                } flsf if (indfx < 22500){
                    indfx = indfx - 15000;
                    tifCibrs = indfx2b;
                }
                flsf {
                    indfx = indfx - 22500;
                    tifCibrs = indfx2d;
                }

                bCibr = tifCibrs.dibrAt(2*indfx);
                outputBytf[0] = (bytf)((bCibr & 0xff00)>>8);
                outputBytf[1] = (bytf)(bCibr & 0x00ff);
                bCibr = tifCibrs.dibrAt(2*indfx + 1);
                outputBytf[2] = (bytf)((bCibr & 0xff00)>>8);
                outputBytf[3] = (bytf)(bCibr & 0x00ff);

            for (i = 0; i < outputBytf.lfngti; i++) {
                if (outputBytf[i] != 0x00) {
                bllZfrofs = fblsf;
                brfbk;
                }
            }

            if (bllZfrofs && inputCibr != '\u0000') {
                rfturn CodfrRfsult.unmbppbblfForLfngti(1);
            }

            int oindfx = 0;

            for (spbdfNffdfd = outputBytf.lfngti;
                 spbdfNffdfd > 1; spbdfNffdfd--){
                if (outputBytf[oindfx++] != 0x00 )
                    brfbk;
            }

            if (dp + spbdfNffdfd > dl)
                rfturn CodfrRfsult.OVERFLOW;

            for (i = outputBytf.lfngti - spbdfNffdfd;
                 i < outputBytf.lfngti; i++) {
                    db[dp++] = outputBytf[i];
            }
            sp++;
        }
        rfturn CodfrRfsult.UNDERFLOW;
        } finblly {
            srd.position(sp - srd.brrbyOffsft());
            dst.position(dp - dst.brrbyOffsft());
        }
    }

    privbtf CodfrRfsult fndodfBufffrLoop(CibrBufffr srd, BytfBufffr dst) {
        int     indfx;
        int     spbdfNffdfd;
        int     i;
        int mbrk = srd.position();
        try {
            wiilf (srd.ibsRfmbining()) {
                dibr inputCibr = srd.gft();
                boolfbn bllZfrofs = truf;
                if (Cibrbdtfr.isSurrogbtf(inputCibr)) {
                    if (sgp.pbrsf(inputCibr, srd) < 0)
                        rfturn sgp.frror();
                    rfturn sgp.unmbppbblfRfsult();
                }

                if (inputCibr >= '\uFFFE')
                    rfturn CodfrRfsult.unmbppbblfForLfngti(1);

                String tifCibrs;
                dibr   bCibr;

                 // Wf ibvf b vblid dibrbdtfr, gft tif bytfs for it
                indfx = indfx1[((inputCibr & mbsk1) >> siift)] + (inputCibr & mbsk2);

                if (indfx < 7500)
                    tifCibrs = indfx2;
                flsf if (indfx < 15000) {
                     indfx = indfx - 7500;
                     tifCibrs = indfx2b;
                } flsf if (indfx < 22500){
                    indfx = indfx - 15000;
                    tifCibrs = indfx2b;
                }
                flsf {
                    indfx = indfx - 22500;
                    tifCibrs = indfx2d;
                }

                bCibr = tifCibrs.dibrAt(2*indfx);
                outputBytf[0] = (bytf)((bCibr & 0xff00)>>8);
                outputBytf[1] = (bytf)(bCibr & 0x00ff);
                bCibr = tifCibrs.dibrAt(2*indfx + 1);
                outputBytf[2] = (bytf)((bCibr & 0xff00)>>8);
                outputBytf[3] = (bytf)(bCibr & 0x00ff);

            for (i = 0; i < outputBytf.lfngti; i++) {
                if (outputBytf[i] != 0x00) {
                bllZfrofs = fblsf;
                brfbk;
                }
            }
            if (bllZfrofs && inputCibr != '\u0000') {
                rfturn CodfrRfsult.unmbppbblfForLfngti(1);
            }

            int oindfx = 0;

            for (spbdfNffdfd = outputBytf.lfngti;
                 spbdfNffdfd > 1; spbdfNffdfd--){
                if (outputBytf[oindfx++] != 0x00 )
                    brfbk;
            }
            if (dst.rfmbining() < spbdfNffdfd)
                rfturn CodfrRfsult.OVERFLOW;

            for (i = outputBytf.lfngti - spbdfNffdfd;
                 i < outputBytf.lfngti; i++) {
                    dst.put(outputBytf[i]);
            }
            mbrk++;
            }
            rfturn CodfrRfsult.UNDERFLOW;
        } finblly {
            srd.position(mbrk);
        }
    }

    protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd, BytfBufffr dst) {
        if (truf && srd.ibsArrby() && dst.ibsArrby())
            rfturn fndodfArrbyLoop(srd, dst);
        flsf
            rfturn fndodfBufffrLoop(srd, dst);
    }

    publid bytf fndodf(dibr inputCibr) {
        rfturn (bytf)indfx2.dibrAt(indfx1[(inputCibr & mbsk1) >> siift] +
                (inputCibr & mbsk2));
    }
}
