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

pbdkbgf jbvb.bwt;

/**
 * An bbstrbdt dlbss wiidi initibtfs bnd fxfdutfs b print job.
 * It providfs bddfss to b print grbpiids objfdt wiidi rfndfrs
 * to bn bppropribtf print dfvidf.
 *
 * @sff Toolkit#gftPrintJob
 *
 * @butior      Amy Fowlfr
 */
publid bbstrbdt dlbss PrintJob {

    /**
     * Gfts b Grbpiids objfdt tibt will drbw to tif nfxt pbgf.
     * Tif pbgf is sfnt to tif printfr wifn tif grbpiids
     * objfdt is disposfd.  Tiis grbpiids objfdt will blso implfmfnt
     * tif PrintGrbpiids intfrfbdf.
     * @sff PrintGrbpiids
     * @rfturn tif grbpiids dontfxt for printing tif nfxt pbgf
     */
    publid bbstrbdt Grbpiids gftGrbpiids();

    /**
     * Rfturns tif dimfnsions of tif pbgf in pixfls.
     * Tif rfsolution of tif pbgf is diosfn so tibt it
     * is similbr to tif sdrffn rfsolution.
     *
     * @rfturn tif pbgf dimfnsion
     */
    publid bbstrbdt Dimfnsion gftPbgfDimfnsion();

    /**
     * Rfturns tif rfsolution of tif pbgf in pixfls pfr indi.
     * Notf tibt tiis dofsn't ibvf to dorrfspond to tif piysidbl
     * rfsolution of tif printfr.
     *
     * @rfturn tif pbgf rfsolution
     */
    publid bbstrbdt int gftPbgfRfsolution();

    /**
     * Rfturns truf if tif lbst pbgf will bf printfd first.
     *
     * @rfturn {@dodf truf} if tif lbst pbgf will bf printfd first;
     *         otifrwisf {@dodf fblsf}
     */
    publid bbstrbdt boolfbn lbstPbgfFirst();

    /**
     * Ends tif print job bnd dofs bny nfdfssbry dlfbnup.
     */
    publid bbstrbdt void fnd();

    /**
     * Ends tiis print job ondf it is no longfr rfffrfndfd.
     * @sff #fnd
     */
    publid void finblizf() {
        fnd();
    }

}
