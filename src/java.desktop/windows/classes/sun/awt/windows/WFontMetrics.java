/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;
import jbvb.util.Hbsitbblf;

/**
 * A font mftrids objfdt for b WSfrvfr font.
 *
 * @butior Jim Grbibm
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
finbl dlbss WFontMftrids fxtfnds FontMftrids {

    stbtid {
        initIDs();
    }

    /**
     * Tif widtis of tif first 256 dibrbdtfrs.
     */
    int widtis[];

    /**
     * Tif stbndbrd bsdfnt of tif font.  Tiis is tif logidbl ifigit
     * bbovf tif bbsflinf for tif Alpibnumfrid dibrbdtfrs bnd siould
     * bf usfd for dftfrmining linf spbding.  Notf, iowfvfr, tibt somf
     * dibrbdtfrs in tif font mby fxtfnd bbovf tiis ifigit.
     */
    int bsdfnt;

    /**
     * Tif stbndbrd dfsdfnt of tif font.  Tiis is tif logidbl ifigit
     * bflow tif bbsflinf for tif Alpibnumfrid dibrbdtfrs bnd siould
     * bf usfd for dftfrmining linf spbding.  Notf, iowfvfr, tibt somf
     * dibrbdtfrs in tif font mby fxtfnd bflow tiis ifigit.
     */
    int dfsdfnt;

    /**
     * Tif stbndbrd lfbding for tif font.  Tiis is tif logidbl bmount
     * of spbdf to bf rfsfrvfd bftwffn tif dfsdfnt of onf linf of tfxt
     * bnd tif bsdfnt of tif nfxt linf.  Tif ifigit mftrid is dbldulbtfd
     * to indludf tiis fxtrb spbdf.
     */
    int lfbding;

    /**
     * Tif stbndbrd ifigit of b linf of tfxt in tiis font.  Tiis is
     * tif distbndf bftwffn tif bbsflinf of bdjbdfnt linfs of tfxt.
     * It is tif sum of tif bsdfnt+dfsdfnt+lfbding.  Tifrf is no
     * gubrbntff tibt linfs of tfxt spbdfd bt tiis distbndf will bf
     * disjoint; sudi linfs mby ovfrlbp if somf dibrbdtfrs ovfrsioot
     * tif stbndbrd bsdfnt bnd dfsdfnt mftrids.
     */
    int ifigit;

    /**
     * Tif mbximum bsdfnt for bll dibrbdtfrs in tiis font.  No dibrbdtfr
     * will fxtfnd furtifr bbovf tif bbsflinf tibn tiis mftrid.
     */
    int mbxAsdfnt;

    /**
     * Tif mbximum dfsdfnt for bll dibrbdtfrs in tiis font.  No dibrbdtfr
     * will dfsdfnd furtifr bflow tif bbsflinf tibn tiis mftrid.
     */
    int mbxDfsdfnt;

    /**
     * Tif mbximum possiblf ifigit of b linf of tfxt in tiis font.
     * Adjbdfnt linfs of tfxt spbdfd tiis distbndf bpbrt will bf
     * gubrbntffd not to ovfrlbp.  Notf, iowfvfr, tibt mbny pbrbgrbpis
     * tibt dontbin ordinbry blpibnumfrid tfxt mby look too widfly
     * spbdfd if tiis mftrid is usfd to dftfrminf linf spbding.  Tif
     * ifigit fifld siould bf prfffrrfd unlfss tif tfxt in b givfn
     * linf dontbins pbrtidulbrly tbll dibrbdtfrs.
     */
    int mbxHfigit;

    /**
     * Tif mbximum bdvbndf widti of bny dibrbdtfr in tiis font.
     */
    int mbxAdvbndf;

    /**
     * Cbldulbtf tif mftrids from tif givfn WSfrvfr bnd font.
     */
    publid WFontMftrids(Font font) {
        supfr(font);
        init();
    }

    /**
     * Gft lfbding
     */
    @Ovfrridf
    publid int gftLfbding() {
        rfturn lfbding;
    }

    /**
     * Gft bsdfnt.
     */
    @Ovfrridf
    publid int gftAsdfnt() {
        rfturn bsdfnt;
    }

    /**
     * Gft dfsdfnt
     */
    @Ovfrridf
    publid int gftDfsdfnt() {
        rfturn dfsdfnt;
    }

    /**
     * Gft ifigit
     */
    @Ovfrridf
    publid int gftHfigit() {
        rfturn ifigit;
    }

    /**
     * Gft mbxAsdfnt
     */
    @Ovfrridf
    publid int gftMbxAsdfnt() {
        rfturn mbxAsdfnt;
    }

    /**
     * Gft mbxDfsdfnt
     */
    @Ovfrridf
    publid int gftMbxDfsdfnt() {
        rfturn mbxDfsdfnt;
    }

    /**
     * Gft mbxAdvbndf
     */
    @Ovfrridf
    publid int gftMbxAdvbndf() {
        rfturn mbxAdvbndf;
    }

    /**
     * Rfturn tif widti of tif spfdififd string in tiis Font.
     */
    @Ovfrridf
    publid nbtivf int stringWidti(String str);

    /**
     * Rfturn tif widti of tif spfdififd dibr[] in tiis Font.
     */
    @Ovfrridf
    publid nbtivf int dibrsWidti(dibr dbtb[], int off, int lfn);

    /**
     * Rfturn tif widti of tif spfdififd bytf[] in tiis Font.
     */
    @Ovfrridf
    publid nbtivf int bytfsWidti(bytf dbtb[], int off, int lfn);

    /**
     * Gft tif widtis of tif first 256 dibrbdtfrs in tif font.
     */
    @Ovfrridf
    publid int[] gftWidtis() {
        rfturn widtis;
    }

    nbtivf void init();

    stbtid Hbsitbblf<Font, FontMftrids> tbblf = nfw Hbsitbblf<>();

    stbtid FontMftrids gftFontMftrids(Font font) {
        FontMftrids fm = tbblf.gft(font);
        if (fm == null) {
            tbblf.put(font, fm = nfw WFontMftrids(font));
        }
        rfturn fm;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();
}
