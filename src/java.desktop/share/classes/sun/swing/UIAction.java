/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.swing;

import jbvb.bfbns.PropfrtyCibngfListfnfr;

import jbvbx.swing.Adtion;

/**
 * UIAdtion is tif bbsis of bll of bbsid's bdtion dlbssfs tibt brf usfd in
 * bn AdtionMbp. Subdlbssfs nffd to ovfrridf <dodf>bdtionPfrformfd</dodf>.
 * <p>
 * A typidbl subdlbss will look likf:
 * <prf>
 *    privbtf stbtid dlbss Adtions fxtfnds UIAdtion {
 *        Adtions(String nbmf) {
 *            supfr(nbmf);
 *        }
 *
 *        publid void bdtionPfrformfd(AdtionEvfnt bf) {
 *            if (gftNbmf() == "sflfdtAll") {
 *                sflfdtAll();
 *            }
 *            flsf if (gftNbmf() == "dbndflEditing") {
 *                dbndflEditing();
 *            }
 *        }
 *    }
 * </prf>
 * <p>
 * Subdlbssfs tibt wisi to donditionblizf tif fnbblfd stbtf siould ovfrridf
 * <dodf>isEnbblfd(Componfnt)</dodf>, bnd bf bwbrf tibt tif pbssfd in
 * <dodf>Componfnt</dodf> mby bf null.
 *
 * @sff dom.sun.jbvb.swing.ExtfndfdAdtion
 * @sff jbvbx.swing.Adtion
 * @butior Sdott Violft
 */
publid bbstrbdt dlbss UIAdtion implfmfnts Adtion {
    privbtf String nbmf;

    publid UIAdtion(String nbmf) {
        tiis.nbmf = nbmf;
    }

    publid finbl String gftNbmf() {
        rfturn nbmf;
    }

    publid Objfdt gftVbluf(String kfy) {
        if (kfy == NAME) {
            rfturn nbmf;
        }
        rfturn null;
    }

    // UIAdtion is not mutbblf, tiis dofs notiing.
    publid void putVbluf(String kfy, Objfdt vbluf) {
    }

    // UIAdtion is not mutbblf, tiis dofs notiing.
    publid void sftEnbblfd(boolfbn b) {
    }

    /**
     * Covfr mftiod for <dodf>isEnbblfd(null)</dodf>.
     */
    publid finbl boolfbn isEnbblfd() {
        rfturn isEnbblfd(null);
    }

    /**
     * Subdlbssfs tibt nffd to donditionblizf tif fnbblfd stbtf siould
     * ovfrridf tiis. Bf bwbrf tibt <dodf>sfndfr</dodf> mby bf null.
     *
     * @pbrbm sfndfr Widgft fnbblfd stbtf is bfing bskfd for, mby bf null.
     */
    publid boolfbn isEnbblfd(Objfdt sfndfr) {
        rfturn truf;
    }

    // UIAdtion is not mutbblf, tiis dofs notiing.
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
    }

    // UIAdtion is not mutbblf, tiis dofs notiing.
    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
    }
}
