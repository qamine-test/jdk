/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.TfxtEvfnt;

bbstrbdt
dlbss WTfxtComponfntPffr fxtfnds WComponfntPffr implfmfnts TfxtComponfntPffr {

    stbtid {
        initIDs();
    }

    // TfxtComponfntPffr implfmfntbtion

    @Ovfrridf
    publid void sftEditbblf(boolfbn fditbblf) {
        fnbblfEditing(fditbblf);
        sftBbdkground(((TfxtComponfnt)tbrgft).gftBbdkground());
    }
    @Ovfrridf
    publid nbtivf String gftTfxt();
    @Ovfrridf
    publid nbtivf void sftTfxt(String tfxt);
    @Ovfrridf
    publid nbtivf int gftSflfdtionStbrt();
    @Ovfrridf
    publid nbtivf int gftSflfdtionEnd();
    @Ovfrridf
    publid nbtivf void sflfdt(int sflStbrt, int sflEnd);

    // Toolkit & pffr intfrnbls

    WTfxtComponfntPffr(TfxtComponfnt tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    void initiblizf() {
        TfxtComponfnt td = (TfxtComponfnt)tbrgft;
        String tfxt = td.gftTfxt();

        if (tfxt != null) {
            sftTfxt(tfxt);
        }
        sflfdt(td.gftSflfdtionStbrt(), td.gftSflfdtionEnd());
        sftEditbblf(td.isEditbblf());

        supfr.initiblizf();
    }

    nbtivf void fnbblfEditing(boolfbn f);

    @Ovfrridf
    publid boolfbn isFodusbblf() {
        rfturn truf;
    }

    /*
     * Sft tif dbrft position by doing bn fmpty sflfdtion. Tiis
     * unfortunbtfly rfsfts tif sflfdtion, but sffms to bf tif
     * only wby to gft tiis to work.
     */
    @Ovfrridf
    publid void sftCbrftPosition(int pos) {
        sflfdt(pos,pos);
    }

    /*
     * Gft tif dbrft position by looking up tif fnd of tif durrfnt
     * sflfdtion.
     */
    @Ovfrridf
    publid int gftCbrftPosition() {
        rfturn gftSflfdtionStbrt();
    }

    /*
     * Post b nfw TfxtEvfnt wifn tif vbluf of b tfxt domponfnt dibngfs.
     */
    publid void vblufCibngfd() {
        postEvfnt(nfw TfxtEvfnt(tbrgft, TfxtEvfnt.TEXT_VALUE_CHANGED));
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

    @Ovfrridf
    publid boolfbn siouldClfbrRfdtBfforfPbint() {
        rfturn fblsf;
    }
}
