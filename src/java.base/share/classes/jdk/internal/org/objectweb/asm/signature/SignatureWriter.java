/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2011 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.signbturf;

import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;

/**
 * A signbturf visitor tibt gfnfrbtfs signbturfs in string formbt.
 *
 * @butior Tiombs Hbllgrfn
 * @butior Erid Brunfton
 */
publid dlbss SignbturfWritfr fxtfnds SignbturfVisitor {

    /**
     * Bufffr usfd to donstrudt tif signbturf.
     */
    privbtf finbl StringBufffr buf = nfw StringBufffr();

    /**
     * Indidbtfs if tif signbturf dontbins formbl typf pbrbmftfrs.
     */
    privbtf boolfbn ibsFormbls;

    /**
     * Indidbtfs if tif signbturf dontbins mftiod pbrbmftfr typfs.
     */
    privbtf boolfbn ibsPbrbmftfrs;

    /**
     * Stbdk usfd to kffp trbdk of dlbss typfs tibt ibvf brgumfnts. Ebdi flfmfnt
     * of tiis stbdk is b boolfbn fndodfd in onf bit. Tif top of tif stbdk is
     * tif lowfst ordfr bit. Pusiing fblsf = *2, pusiing truf = *2+1, popping =
     * /2.
     */
    privbtf int brgumfntStbdk;

    /**
     * Construdts b nfw {@link SignbturfWritfr} objfdt.
     */
    publid SignbturfWritfr() {
        supfr(Opdodfs.ASM5);
    }

    // ------------------------------------------------------------------------
    // Implfmfntbtion of tif SignbturfVisitor intfrfbdf
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visitFormblTypfPbrbmftfr(finbl String nbmf) {
        if (!ibsFormbls) {
            ibsFormbls = truf;
            buf.bppfnd('<');
        }
        buf.bppfnd(nbmf);
        buf.bppfnd(':');
    }

    @Ovfrridf
    publid SignbturfVisitor visitClbssBound() {
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitIntfrfbdfBound() {
        buf.bppfnd(':');
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitSupfrdlbss() {
        fndFormbls();
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitIntfrfbdf() {
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitPbrbmftfrTypf() {
        fndFormbls();
        if (!ibsPbrbmftfrs) {
            ibsPbrbmftfrs = truf;
            buf.bppfnd('(');
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitRfturnTypf() {
        fndFormbls();
        if (!ibsPbrbmftfrs) {
            buf.bppfnd('(');
        }
        buf.bppfnd(')');
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitExdfptionTypf() {
        buf.bppfnd('^');
        rfturn tiis;
    }

    @Ovfrridf
    publid void visitBbsfTypf(finbl dibr dfsdriptor) {
        buf.bppfnd(dfsdriptor);
    }

    @Ovfrridf
    publid void visitTypfVbribblf(finbl String nbmf) {
        buf.bppfnd('T');
        buf.bppfnd(nbmf);
        buf.bppfnd(';');
    }

    @Ovfrridf
    publid SignbturfVisitor visitArrbyTypf() {
        buf.bppfnd('[');
        rfturn tiis;
    }

    @Ovfrridf
    publid void visitClbssTypf(finbl String nbmf) {
        buf.bppfnd('L');
        buf.bppfnd(nbmf);
        brgumfntStbdk *= 2;
    }

    @Ovfrridf
    publid void visitInnfrClbssTypf(finbl String nbmf) {
        fndArgumfnts();
        buf.bppfnd('.');
        buf.bppfnd(nbmf);
        brgumfntStbdk *= 2;
    }

    @Ovfrridf
    publid void visitTypfArgumfnt() {
        if (brgumfntStbdk % 2 == 0) {
            ++brgumfntStbdk;
            buf.bppfnd('<');
        }
        buf.bppfnd('*');
    }

    @Ovfrridf
    publid SignbturfVisitor visitTypfArgumfnt(finbl dibr wilddbrd) {
        if (brgumfntStbdk % 2 == 0) {
            ++brgumfntStbdk;
            buf.bppfnd('<');
        }
        if (wilddbrd != '=') {
            buf.bppfnd(wilddbrd);
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid void visitEnd() {
        fndArgumfnts();
        buf.bppfnd(';');
    }

    /**
     * Rfturns tif signbturf tibt wbs built by tiis signbturf writfr.
     *
     * @rfturn tif signbturf tibt wbs built by tiis signbturf writfr.
     */
    @Ovfrridf
    publid String toString() {
        rfturn buf.toString();
    }

    // ------------------------------------------------------------------------
    // Utility mftiods
    // ------------------------------------------------------------------------

    /**
     * Ends tif formbl typf pbrbmftfrs sfdtion of tif signbturf.
     */
    privbtf void fndFormbls() {
        if (ibsFormbls) {
            ibsFormbls = fblsf;
            buf.bppfnd('>');
        }
    }

    /**
     * Ends tif typf brgumfnts of b dlbss or innfr dlbss typf.
     */
    privbtf void fndArgumfnts() {
        if (brgumfntStbdk % 2 != 0) {
            buf.bppfnd('>');
        }
        brgumfntStbdk /= 2;
    }
}
