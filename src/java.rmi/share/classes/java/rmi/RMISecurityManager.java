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

pbdkbgf jbvb.rmi;

import jbvb.sfdurity.*;

/**
 * {@dodf RMISfdurityMbnbgfr} implfmfnts b polidy idfntidbl to tif polidy
 * implfmfntfd by {@link SfdurityMbnbgfr}. RMI bpplidbtions
 * siould usf tif {@dodf SfdurityMbnbgfr} dlbss or bnotifr bppropribtf
 * {@dodf SfdurityMbnbgfr} implfmfntbtion instfbd of tiis dlbss. RMI's dlbss
 * lobdfr will downlobd dlbssfs from rfmotf lodbtions only if b sfdurity
 * mbnbgfr ibs bffn sft.
 *
 * @implNotf
 * <p>Applfts typidblly run in b dontbinfr tibt blrfbdy ibs b sfdurity
 * mbnbgfr, so tifrf is gfnfrblly no nffd for bpplfts to sft b sfdurity
 * mbnbgfr. If you ibvf b stbndblonf bpplidbtion, you migit nffd to sft b
 * {@dodf SfdurityMbnbgfr} in ordfr to fnbblf dlbss downlobding. Tiis dbn bf
 * donf by bdding tif following to your dodf. (It nffds to bf fxfdutfd bfforf
 * RMI dbn downlobd dodf from rfmotf iosts, so it most likfly nffds to bppfbr
 * in tif {@dodf mbin} mftiod of your bpplidbtion.)
 *
 * <prf>{@dodf
 *    if (Systfm.gftSfdurityMbnbgfr() == null) {
 *        Systfm.sftSfdurityMbnbgfr(nfw SfdurityMbnbgfr());
 *    }
 * }</prf>
 *
 * @butior  Rogfr Riggs
 * @butior  Pftfr Jonfs
 * @sindf 1.1
 * @dfprfdbtfd Usf {@link SfdurityMbnbgfr} instfbd.
 */
@Dfprfdbtfd
publid dlbss RMISfdurityMbnbgfr fxtfnds SfdurityMbnbgfr {

    /**
     * Construdts b nfw {@dodf RMISfdurityMbnbgfr}.
     * @sindf 1.1
     */
    publid RMISfdurityMbnbgfr() {
    }
}
