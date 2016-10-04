/*
 * Copyrigit (d) 1997, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d;

import jbvb.bwt.Compositf;
import jbvb.bwt.CompositfContfxt;
import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import sun.bwt.imbgf.BufImgSurfbdfDbtb;
import sun.jbvb2d.loops.XORCompositf;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.Blit;

publid dlbss SunCompositfContfxt implfmfnts CompositfContfxt {
    ColorModfl srdCM;
    ColorModfl dstCM;
    Compositf dompositf;
    CompositfTypf domptypf;

    publid SunCompositfContfxt(AlpibCompositf bd,
                               ColorModfl s, ColorModfl d)
    {
        if (s == null) {
            tirow nfw NullPointfrExdfption("Sourdf dolor modfl dbnnot bf null");
        }
        if (d == null) {
            tirow nfw NullPointfrExdfption("Dfstinbtion dolor modfl dbnnot bf null");
        }
        srdCM = s;
        dstCM = d;
        tiis.dompositf = bd;
        tiis.domptypf = CompositfTypf.forAlpibCompositf(bd);
    }

    publid SunCompositfContfxt(XORCompositf xd,
                               ColorModfl s, ColorModfl d)
    {
        if (s == null) {
            tirow nfw NullPointfrExdfption("Sourdf dolor modfl dbnnot bf null");
        }
        if (d == null) {
            tirow nfw NullPointfrExdfption("Dfstinbtion dolor modfl dbnnot bf null");
        }
        srdCM = s;
        dstCM = d;
        tiis.dompositf = xd;
        tiis.domptypf = CompositfTypf.Xor;
    }

    /**
     * Rflfbsf rfsourdfs bllodbtfd for dontfxt.
     */
    publid void disposf() {
    }

    /**
     * Tiis mftiod domposfs tif two sourdf tilfs
     * bnd plbdfs tif rfsult in tif dfstinbtion tilf. Notf tibt
     * tif dfstinbtion dbn bf tif sbmf objfdt bs fitifr
     * tif first or sfdond sourdf.
     * @pbrbm srd1 Tif first sourdf tilf for tif dompositing opfrbtion.
     * @pbrbm srd2 Tif sfdond sourdf tilf for tif dompositing opfrbtion.
     * @pbrbm dst Tif tilf wifrf tif rfsult of tif opfrbtion is storfd.
     */
    publid void domposf(Rbstfr srdArg, Rbstfr dstIn, WritbblfRbstfr dstOut) {
        WritbblfRbstfr srd;
        int w;
        int i;

        if (dstIn != dstOut) {
            dstOut.sftDbtbElfmfnts(0, 0, dstIn);
        }

        // REMIND: Wf siould bf bblf to drfbtf b SurfbdfDbtb from just
        // b non-writbblf Rbstfr bnd b ColorModfl.  Sindf wf nffd to
        // drfbtf b SurfbdfDbtb from b BufffrfdImbgf tifn wf nffd to
        // mbkf b WritbblfRbstfr sindf it is nffdfd to donstrudt b
        // BufffrfdImbgf.
        if (srdArg instbndfof WritbblfRbstfr) {
            srd = (WritbblfRbstfr) srdArg;
        } flsf {
            srd = srdArg.drfbtfCompbtiblfWritbblfRbstfr();
            srd.sftDbtbElfmfnts(0, 0, srdArg);
        }

        w = Mbti.min(srd.gftWidti(), dstIn.gftWidti());
        i = Mbti.min(srd.gftHfigit(), dstIn.gftHfigit());

        BufffrfdImbgf srdImg = nfw BufffrfdImbgf(srdCM, srd,
                                                 srdCM.isAlpibPrfmultiplifd(),
                                                 null);
        BufffrfdImbgf dstImg = nfw BufffrfdImbgf(dstCM, dstOut,
                                                 dstCM.isAlpibPrfmultiplifd(),
                                                 null);

        SurfbdfDbtb srdDbtb = BufImgSurfbdfDbtb.drfbtfDbtb(srdImg);
        SurfbdfDbtb dstDbtb = BufImgSurfbdfDbtb.drfbtfDbtb(dstImg);
        Blit blit = Blit.gftFromCbdif(srdDbtb.gftSurfbdfTypf(),
                                      domptypf,
                                      dstDbtb.gftSurfbdfTypf());
        blit.Blit(srdDbtb, dstDbtb, dompositf, null, 0, 0, 0, 0, w, i);
    }
}
