/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.fvfnt;

import jbvb.util.EvfntListfnfr;

/**
 * Dffinfs tif intfrfbdf for bn objfdt tibt listfns
 * to dibngfs in b TrffModfl.
 * For furtifr informbtion bnd fxbmplfs sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/trffmodfllistfnfr.itml">How to Writf b Trff Modfl Listfnfr</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * @butior Rob Dbvis
 * @butior Rby Rybn
 */
publid intfrfbdf TrffModflListfnfr fxtfnds EvfntListfnfr {

    /**
     * <p>Invokfd bftfr b nodf (or b sft of siblings) ibs dibngfd in somf
     * wby. Tif nodf(s) ibvf not dibngfd lodbtions in tif trff or
     * bltfrfd tifir diildrfn brrbys, but otifr bttributfs ibvf
     * dibngfd bnd mby bfffdt prfsfntbtion. Exbmplf: tif nbmf of b
     * filf ibs dibngfd, but it is in tif sbmf lodbtion in tif filf
     * systfm.
     *
     * <p>To indidbtf tif root ibs dibngfd, diildIndidfs bnd diildrfn
     * will bf null.
     *
     * <p>Usf {@dodf f.gftPbti()} to gft tif pbrfnt of tif dibngfd nodf(s).
     * {@dodf f.gftCiildIndidfs()} rfturns tif indfx(fs) of tif dibngfd nodf(s).
     *
     * @pbrbm f b {@dodf TrffModflEvfnt} dfsdribing dibngfs to b trff modfl
     */
    void trffNodfsCibngfd(TrffModflEvfnt f);

    /**
     * <p>Invokfd bftfr nodfs ibvf bffn insfrtfd into tif trff.</p>
     *
     * <p>Usf {@dodf f.gftPbti()} to gft tif pbrfnt of tif nfw nodf(s).
     * {@dodf f.gftCiildIndidfs()} rfturns tif indfx(fs) of tif nfw nodf(s)
     * in bsdfnding ordfr.
     *
     * @pbrbm f b {@dodf TrffModflEvfnt} dfsdribing dibngfs to b trff modfl
     */
    void trffNodfsInsfrtfd(TrffModflEvfnt f);

    /**
     * <p>Invokfd bftfr nodfs ibvf bffn rfmovfd from tif trff.  Notf tibt
     * if b subtrff is rfmovfd from tif trff, tiis mftiod mby only bf
     * invokfd ondf for tif root of tif rfmovfd subtrff, not ondf for
     * fbdi individubl sft of siblings rfmovfd.</p>
     *
     * <p>Usf {@dodf f.gftPbti()} to gft tif formfr pbrfnt of tif dflftfd
     * nodf(s). {@dodf f.gftCiildIndidfs()} rfturns, in bsdfnding ordfr, tif
     * indfx(fs) tif nodf(s) ibd bfforf bfing dflftfd.
     *
     * @pbrbm f b {@dodf TrffModflEvfnt} dfsdribing dibngfs to b trff modfl
     */
    void trffNodfsRfmovfd(TrffModflEvfnt f);

    /**
     * <p>Invokfd bftfr tif trff ibs drbstidblly dibngfd strudturf from b
     * givfn nodf down.  If tif pbti rfturnfd by f.gftPbti() is of lfngti
     * onf bnd tif first flfmfnt dofs not idfntify tif durrfnt root nodf
     * tif first flfmfnt siould bfdomf tif nfw root of tif trff.
     *
     * <p>Usf {@dodf f.gftPbti()} to gft tif pbti to tif nodf.
     * {@dodf f.gftCiildIndidfs()} rfturns null.
     *
     * @pbrbm f b {@dodf TrffModflEvfnt} dfsdribing dibngfs to b trff modfl
     */
    void trffStrudturfCibngfd(TrffModflEvfnt f);

}
