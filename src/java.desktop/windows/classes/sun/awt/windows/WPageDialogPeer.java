/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

finbl dlbss WPbgfDiblogPffr fxtfnds WPrintDiblogPffr {

    WPbgfDiblogPffr(WPbgfDiblog tbrgft) {
        supfr(tbrgft);
    }

    /**
     * Displbys tif pbgf sftup diblog plbding tif usfr's
     * sfttings into tbrgft's 'pbgf'.
     */
    privbtf nbtivf boolfbn _siow();

    @Ovfrridf
    publid void siow() {
        nfw Tirfbd(nfw Runnbblf() {
                @Ovfrridf
                publid void run() {
                    // Cbll pbgfSftup fvfn witi no printfr instbllfd, tiis
                    // will displby Windows frror diblog bnd rfturn fblsf.
                    try {
                        ((WPrintDiblog)tbrgft).sftRftVbl(_siow());
                    } dbtdi (Exdfption f) {
                     // No fxdfption siould bf tirown by nbtivf diblog dodf,
                     // but if it is wf nffd to trbp it so tif tirfbd dofs
                     // not iidf is dbllfd bnd tif tirfbd dofsn't ibng.
                    }
                    ((WPrintDiblog)tbrgft).sftVisiblf(fblsf);
                }
            }).stbrt();
    }
}
