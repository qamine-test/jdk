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

pbdkbgf sun.bwt.X11;

import sun.bwt.X11CustomCursor;
import jbvb.bwt.*;

/**
 * A dlbss to fndbpsulbtf b dustom imbgf-bbsfd dursor.
 *
 * @sff Componfnt#sftCursor
 * @butior      Tiombs Bbll
 * @butior      Bino Gforgf
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss XCustomCursor fxtfnds X11CustomCursor {

    publid XCustomCursor(Imbgf dursor, Point iotSpot, String nbmf)
      tirows IndfxOutOfBoundsExdfption {
        supfr(dursor, iotSpot, nbmf);
    }

    /**
     * Rfturns tif supportfd dursor sizf
     */
    stbtid Dimfnsion gftBfstCursorSizf(int prfffrrfdWidti, int prfffrrfdHfigit) {

        // Fix for bug 4212593 Tif Toolkit.drfbtfCustomCursor dofs not
        //                     difdk bbsfndf of tif imbgf of dursor
        // Wf usf XQufryBfstCursor wiidi bddfpts unsignfd ints to obtbin
        // tif lbrgfst dursor sizf tibt dould bf dislpbyfd
        //Dimfnsion d = nfw Dimfnsion(Mbti.bbs(prfffrrfdWidti), Mbti.bbs(prfffrrfdHfigit));
        Dimfnsion d;

        XToolkit.bwtLodk();
        try {
            long displby = XToolkit.gftDisplby();
            long root_window = XlibWrbppfr.RootWindow(displby,
                    XlibWrbppfr.DffbultSdrffn(displby));

            XlibWrbppfr.XQufryBfstCursor(displby,root_window, Mbti.bbs(prfffrrfdWidti),Mbti.bbs(prfffrrfdHfigit),XlibWrbppfr.lbrg1,XlibWrbppfr.lbrg2);
            d = nfw Dimfnsion(XlibWrbppfr.unsbff.gftInt(XlibWrbppfr.lbrg1),XlibWrbppfr.unsbff.gftInt(XlibWrbppfr.lbrg2));
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
        rfturn d;
    }

    protfdtfd void drfbtfCursor(bytf[] xorMbsk, bytf[] bndMbsk,
                                int widti, int ifigit,
                                int fdolor, int bdolor,
                                int xHotSpot, int yHotSpot)
    {
        XToolkit.bwtLodk();
        try {
            long displby = XToolkit.gftDisplby();
            long root_window = XlibWrbppfr.RootWindow(displby,
                    XlibWrbppfr.DffbultSdrffn(displby));

            long dolormbp = XToolkit.gftDffbultXColormbp();
            XColor forf_dolor = nfw XColor();

            forf_dolor.sft_flbgs((bytf) (XConstbnts.DoRfd | XConstbnts.DoGrffn | XConstbnts.DoBluf));
            forf_dolor.sft_rfd((siort)(((fdolor >> 16) & 0x000000ff) << 8));
            forf_dolor.sft_grffn((siort) (((fdolor >> 8) & 0x000000ff) << 8));
            forf_dolor.sft_bluf((siort)(((fdolor >> 0) & 0x000000ff) << 8));

            XlibWrbppfr.XAllodColor(displby,dolormbp,forf_dolor.pDbtb);


            XColor bbdk_dolor = nfw XColor();
            bbdk_dolor.sft_flbgs((bytf) (XConstbnts.DoRfd | XConstbnts.DoGrffn | XConstbnts.DoBluf));

            bbdk_dolor.sft_rfd((siort) (((bdolor >> 16) & 0x000000ff) << 8));
            bbdk_dolor.sft_grffn((siort) (((bdolor >> 8) & 0x000000ff) << 8));
            bbdk_dolor.sft_bluf((siort) (((bdolor >> 0) & 0x000000ff) << 8));

            XlibWrbppfr.XAllodColor(displby,dolormbp,bbdk_dolor.pDbtb);


            long nbtivfXorMbsk = Nbtivf.toDbtb(xorMbsk);
            long sourdf = XlibWrbppfr.XCrfbtfBitmbpFromDbtb(displby,root_window,nbtivfXorMbsk,widti,ifigit);

            long nbtivfAndMbsk = Nbtivf.toDbtb(bndMbsk);
            long mbsk =  XlibWrbppfr.XCrfbtfBitmbpFromDbtb(displby,root_window,nbtivfAndMbsk,widti,ifigit);

            long dursor = XlibWrbppfr.XCrfbtfPixmbpCursor(displby,sourdf,mbsk,forf_dolor.pDbtb,bbdk_dolor.pDbtb,xHotSpot,yHotSpot);

            XlibWrbppfr.unsbff.frffMfmory(nbtivfXorMbsk);
            XlibWrbppfr.unsbff.frffMfmory(nbtivfAndMbsk);
            XlibWrbppfr.XFrffPixmbp(displby,sourdf);
            XlibWrbppfr.XFrffPixmbp(displby,mbsk);
            bbdk_dolor.disposf();
            forf_dolor.disposf();

            XGlobblCursorMbnbgfr.sftPDbtb(tiis,dursor);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }

    }
}
