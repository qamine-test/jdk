/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.io.ObjfdtOutput;
import jbvb.io.ObjfdtInput;

/**
 * Only tif idfntity of tif dlbss of bn Extfrnblizbblf instbndf is
 * writtfn in tif sfriblizbtion strfbm bnd it is tif rfsponsibility
 * of tif dlbss to sbvf bnd rfstorf tif dontfnts of its instbndfs.
 *
 * Tif writfExtfrnbl bnd rfbdExtfrnbl mftiods of tif Extfrnblizbblf
 * intfrfbdf brf implfmfntfd by b dlbss to givf tif dlbss domplftf
 * dontrol ovfr tif formbt bnd dontfnts of tif strfbm for bn objfdt
 * bnd its supfrtypfs. Tifsf mftiods must fxpliditly
 * doordinbtf witi tif supfrtypf to sbvf its stbtf. Tifsf mftiods supfrsfdf
 * dustomizfd implfmfntbtions of writfObjfdt bnd rfbdObjfdt mftiods.<br>
 *
 * Objfdt Sfriblizbtion usfs tif Sfriblizbblf bnd Extfrnblizbblf
 * intfrfbdfs.  Objfdt pfrsistfndf mfdibnisms dbn usf tifm bs wfll.  Ebdi
 * objfdt to bf storfd is tfstfd for tif Extfrnblizbblf intfrfbdf. If
 * tif objfdt supports Extfrnblizbblf, tif writfExtfrnbl mftiod is dbllfd. If tif
 * objfdt dofs not support Extfrnblizbblf bnd dofs implfmfnt
 * Sfriblizbblf, tif objfdt is sbvfd using
 * ObjfdtOutputStrfbm. <br> Wifn bn Extfrnblizbblf objfdt is
 * rfdonstrudtfd, bn instbndf is drfbtfd using tif publid no-brg
 * donstrudtor, tifn tif rfbdExtfrnbl mftiod dbllfd.  Sfriblizbblf
 * objfdts brf rfstorfd by rfbding tifm from bn ObjfdtInputStrfbm.<br>
 *
 * An Extfrnblizbblf instbndf dbn dfsignbtf b substitution objfdt vib
 * tif writfRfplbdf bnd rfbdRfsolvf mftiods dodumfntfd in tif Sfriblizbblf
 * intfrfbdf.<br>
 *
 * @butior  unbsdribfd
 * @sff jbvb.io.ObjfdtOutputStrfbm
 * @sff jbvb.io.ObjfdtInputStrfbm
 * @sff jbvb.io.ObjfdtOutput
 * @sff jbvb.io.ObjfdtInput
 * @sff jbvb.io.Sfriblizbblf
 * @sindf   1.1
 */
publid intfrfbdf Extfrnblizbblf fxtfnds jbvb.io.Sfriblizbblf {
    /**
     * Tif objfdt implfmfnts tif writfExtfrnbl mftiod to sbvf its dontfnts
     * by dblling tif mftiods of DbtbOutput for its primitivf vblufs or
     * dblling tif writfObjfdt mftiod of ObjfdtOutput for objfdts, strings,
     * bnd brrbys.
     *
     * @sfriblDbtb Ovfrriding mftiods siould usf tiis tbg to dfsdribf
     *             tif dbtb lbyout of tiis Extfrnblizbblf objfdt.
     *             List tif sfqufndf of flfmfnt typfs bnd, if possiblf,
     *             rflbtf tif flfmfnt to b publid/protfdtfd fifld bnd/or
     *             mftiod of tiis Extfrnblizbblf dlbss.
     *
     * @pbrbm out tif strfbm to writf tif objfdt to
     * @fxdfption IOExdfption Indludfs bny I/O fxdfptions tibt mby oddur
     */
    void writfExtfrnbl(ObjfdtOutput out) tirows IOExdfption;

    /**
     * Tif objfdt implfmfnts tif rfbdExtfrnbl mftiod to rfstorf its
     * dontfnts by dblling tif mftiods of DbtbInput for primitivf
     * typfs bnd rfbdObjfdt for objfdts, strings bnd brrbys.  Tif
     * rfbdExtfrnbl mftiod must rfbd tif vblufs in tif sbmf sfqufndf
     * bnd witi tif sbmf typfs bs wfrf writtfn by writfExtfrnbl.
     *
     * @pbrbm in tif strfbm to rfbd dbtb from in ordfr to rfstorf tif objfdt
     * @fxdfption IOExdfption if I/O frrors oddur
     * @fxdfption ClbssNotFoundExdfption If tif dlbss for bn objfdt bfing
     *              rfstorfd dbnnot bf found.
     */
    void rfbdExtfrnbl(ObjfdtInput in) tirows IOExdfption, ClbssNotFoundExdfption;
}
