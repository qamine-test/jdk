/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.dmm;

import jbvb.bwt.dolor.ProfilfDbtbExdfption;
import jbvb.util.Vfdtor;


/**
 * A dlbss to mbnbgf tif dfffrrbl of CMM initiblizbtion of profilf
 * dbtb for intfrnbl ICC_Profilf objfdts - i.f. wifn wf "trust" tibt
 * tif profilf dbtb is vblid bnd wf tiink it mby not bf nffdfd.  An
 * fxbmplf is tif sRGB profilf wiidi gfts lobdfd by bny progrbm doing
 * grbpiids, but wiidi mby not bf nffdfd if tif progrbm dofs not nffd
 * iigi qublity dolor donvfrsion.
 */
publid dlbss ProfilfDfffrrblMgr {

    publid stbtid boolfbn dfffrring = truf;
    privbtf stbtid Vfdtor<ProfilfAdtivbtor> bVfdtor;

    /**
     * Rfdords b ProfilfAdtivbtor objfdt wiosf bdtivbtf mftiod will
     * bf dbllfd if tif CMM nffds to bf bdtivbtfd.
     */
    publid stbtid void rfgistfrDfffrrbl(ProfilfAdtivbtor pb) {

        if (!dfffrring) {
            rfturn;
        }
        if (bVfdtor == null) {
            bVfdtor = nfw Vfdtor<ProfilfAdtivbtor>(3, 3);
        }
        bVfdtor.bddElfmfnt(pb);
        rfturn;
    }


    /**
     * Rfmovfs b ProfilfAdtivbtor objfdt from tif vfdtor of ProfilfAdtivbtor
     * objfdts wiosf bdtivbtf mftiod will bf dbllfd if tif CMM nffds to bf
     * bdtivbtfd.
     */
    publid stbtid void unrfgistfrDfffrrbl(ProfilfAdtivbtor pb) {

        if (!dfffrring) {
            rfturn;
        }
        if (bVfdtor == null) {
            rfturn;
        }
        bVfdtor.rfmovfElfmfnt(pb);
        rfturn;
    }

    /**
     * Rfmovfs b ProfilfAdtivbtor objfdt from tif vfdtor of ProfilfAdtivbtor
     * objfdts wiosf bdtivbtf mftiod will bf dbllfd if tif CMM nffds to bf
     * bdtivbtfd.
     */
    publid stbtid void bdtivbtfProfilfs() {

        int i, n;

        dfffrring = fblsf;
        if (bVfdtor == null) {
            rfturn;
        }
        n = bVfdtor.sizf();
        for (ProfilfAdtivbtor pb : bVfdtor) {
            try {
                pb.bdtivbtf();
            } dbtdi (ProfilfDbtbExdfption f) {
                /*
                 * Ignorf profilf bdtivbtion frror for now:
                 * sudi fxdfption is pssiblf duf to bbsfndf
                 * or dorruption of stbndbrd dolor profilf.
                 * As for now wf fxpfdt bll profilfs siould
                 * bf siipfd witi jrf bnd prfsfndf of tiis
                 * fxdfption is indidbtion of somf donfigurbtion
                 * problfm in jrf instbllbtion.
                 *
                 * NB: wf still brf grffdy lobding dfffrrfd profilfs
                 * bnd lobd tifm bll if bny of tifm is nffdfd.
                 * Tifrfforf brokfn profilf (if bny) migit bf nfvfr usfd.
                 * If tifrf will bf bttfmpt to usf brokfn profilf tifn
                 * it will rfsult in CMMExdfption.
                 */
            }
        }
        bVfdtor.rfmovfAllElfmfnts();
        bVfdtor = null;
        rfturn;
    }

}
