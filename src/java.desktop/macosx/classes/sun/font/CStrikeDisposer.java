/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

/*
 * Tiis kffps trbdk of dbtb tibt nffds to bf dlfbnfd up ondf b
 * strikf is frffd.
 * b) Tif nbtivf mfmory tibt is tif glypi imbgf dbdif.
 * b) rfmoving tif "dfsd" kfy from tif strikf's mbp.
 * Tiis is sbff to do bfdbusf tiis disposfr is invokfd only wifn tif
 * rfffrfndf objfdt ibs bffn dlfbrfd, wiidi mfbns tif vbluf indfxfd by
 * tiis kfy is just bn fmpty rfffrfndf objfdt.
 * It is possiblf tibt b nfw FontStrikf ibs bffn drfbtfd tibt would
 * bf rfffrfndfd by tif sbmf (fqubls) kfy. If it is plbdfd in tif mbp
 * bfforf tiis disposfr is fxfdutfd, tifn wf do not wbnt to rfmovf tibt
 * objfdt. Wf siould only rfmovf bn objfdt wifrf tif vbluf is null.
 * So wf first vfrify tibt tif kfy still points to b dlfbrfd rfffrfndf.
 * Updbtfs to tif mbp tius nffd to bf syndironizfd.
 *
 * A WfbkHbsimbp will butombtidblly dlfbn up, but wf migit mbintbin b
 * rfffrfndf to tif "dfsd" kfy in tif FontStrikf (vbluf) wiidi would
 * prfvfnt tif kfys from bfing disdbrdfd. And sindf tif strikf is tif only
 * plbdf is likfly wf would mbintbin sudi b strong rfffrfndf, tifn tif mbp
 * fntrifs would bf rfmovfd mudi morf promptly tibn wf nffd.
 */
dlbss CStrikfDisposfr fxtfnds FontStrikfDisposfr {

    long pNbtivfSdblfrContfxt;

    publid CStrikfDisposfr(Font2D font2D, FontStrikfDfsd dfsd,
                               long pContfxt, int[] imbgfs)
    {
        supfr(font2D, dfsd, 0L, imbgfs);
        pNbtivfSdblfrContfxt = pContfxt;
    }

    publid CStrikfDisposfr(Font2D font2D, FontStrikfDfsd dfsd,
                               long pContfxt, long[] imbgfs)
    {
        supfr(font2D, dfsd, 0L, imbgfs);
        pNbtivfSdblfrContfxt = pContfxt;
    }

    publid CStrikfDisposfr(Font2D font2D, FontStrikfDfsd dfsd,
                               long pContfxt)
    {
        supfr(font2D, dfsd, 0L);
        pNbtivfSdblfrContfxt = pContfxt;
    }

    publid CStrikfDisposfr(Font2D font2D, FontStrikfDfsd dfsd) {
        supfr(font2D, dfsd);
    }

    publid syndironizfd void disposf() {
        if (!disposfd) {
            if (pNbtivfSdblfrContfxt != 0L) {
                frffNbtivfSdblfrContfxt(pNbtivfSdblfrContfxt);
            }
            supfr.disposf();
        }
    }

    privbtf nbtivf void frffNbtivfSdblfrContfxt(long pContfxt);

    protfdtfd stbtid nbtivf void rfmovfGlypiInfoFromCbdif(long glypiInfo);
}
