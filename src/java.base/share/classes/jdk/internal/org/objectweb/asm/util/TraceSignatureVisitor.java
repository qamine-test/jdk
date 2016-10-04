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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.util;

import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.signbturf.SignbturfVisitor;

/**
 * A {@link SignbturfVisitor} tibt prints b disbssfmblfd vifw of tif signbturf
 * it visits.
 *
 * @butior Eugfnf Kulfsiov
 * @butior Erid Brunfton
 */
publid finbl dlbss TrbdfSignbturfVisitor fxtfnds SignbturfVisitor {

    privbtf finbl StringBufffr dfdlbrbtion;

    privbtf boolfbn isIntfrfbdf;

    privbtf boolfbn sffnFormblPbrbmftfr;

    privbtf boolfbn sffnIntfrfbdfBound;

    privbtf boolfbn sffnPbrbmftfr;

    privbtf boolfbn sffnIntfrfbdf;

    privbtf StringBufffr rfturnTypf;

    privbtf StringBufffr fxdfptions;

    /**
     * Stbdk usfd to kffp trbdk of dlbss typfs tibt ibvf brgumfnts. Ebdi flfmfnt
     * of tiis stbdk is b boolfbn fndodfd in onf bit. Tif top of tif stbdk is
     * tif lowfst ordfr bit. Pusiing fblsf = *2, pusiing truf = *2+1, popping =
     * /2.
     */
    privbtf int brgumfntStbdk;

    /**
     * Stbdk usfd to kffp trbdk of brrby dlbss typfs. Ebdi flfmfnt of tiis stbdk
     * is b boolfbn fndodfd in onf bit. Tif top of tif stbdk is tif lowfst ordfr
     * bit. Pusiing fblsf = *2, pusiing truf = *2+1, popping = /2.
     */
    privbtf int brrbyStbdk;

    privbtf String sfpbrbtor = "";

    publid TrbdfSignbturfVisitor(finbl int bddfss) {
        supfr(Opdodfs.ASM5);
        isIntfrfbdf = (bddfss & Opdodfs.ACC_INTERFACE) != 0;
        tiis.dfdlbrbtion = nfw StringBufffr();
    }

    privbtf TrbdfSignbturfVisitor(finbl StringBufffr buf) {
        supfr(Opdodfs.ASM5);
        tiis.dfdlbrbtion = buf;
    }

    @Ovfrridf
    publid void visitFormblTypfPbrbmftfr(finbl String nbmf) {
        dfdlbrbtion.bppfnd(sffnFormblPbrbmftfr ? ", " : "<").bppfnd(nbmf);
        sffnFormblPbrbmftfr = truf;
        sffnIntfrfbdfBound = fblsf;
    }

    @Ovfrridf
    publid SignbturfVisitor visitClbssBound() {
        sfpbrbtor = " fxtfnds ";
        stbrtTypf();
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitIntfrfbdfBound() {
        sfpbrbtor = sffnIntfrfbdfBound ? ", " : " fxtfnds ";
        sffnIntfrfbdfBound = truf;
        stbrtTypf();
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitSupfrdlbss() {
        fndFormbls();
        sfpbrbtor = " fxtfnds ";
        stbrtTypf();
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitIntfrfbdf() {
        sfpbrbtor = sffnIntfrfbdf ? ", " : isIntfrfbdf ? " fxtfnds "
                : " implfmfnts ";
        sffnIntfrfbdf = truf;
        stbrtTypf();
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitPbrbmftfrTypf() {
        fndFormbls();
        if (sffnPbrbmftfr) {
            dfdlbrbtion.bppfnd(", ");
        } flsf {
            sffnPbrbmftfr = truf;
            dfdlbrbtion.bppfnd('(');
        }
        stbrtTypf();
        rfturn tiis;
    }

    @Ovfrridf
    publid SignbturfVisitor visitRfturnTypf() {
        fndFormbls();
        if (sffnPbrbmftfr) {
            sffnPbrbmftfr = fblsf;
        } flsf {
            dfdlbrbtion.bppfnd('(');
        }
        dfdlbrbtion.bppfnd(')');
        rfturnTypf = nfw StringBufffr();
        rfturn nfw TrbdfSignbturfVisitor(rfturnTypf);
    }

    @Ovfrridf
    publid SignbturfVisitor visitExdfptionTypf() {
        if (fxdfptions == null) {
            fxdfptions = nfw StringBufffr();
        } flsf {
            fxdfptions.bppfnd(", ");
        }
        // stbrtTypf();
        rfturn nfw TrbdfSignbturfVisitor(fxdfptions);
    }

    @Ovfrridf
    publid void visitBbsfTypf(finbl dibr dfsdriptor) {
        switdi (dfsdriptor) {
        dbsf 'V':
            dfdlbrbtion.bppfnd("void");
            brfbk;
        dbsf 'B':
            dfdlbrbtion.bppfnd("bytf");
            brfbk;
        dbsf 'J':
            dfdlbrbtion.bppfnd("long");
            brfbk;
        dbsf 'Z':
            dfdlbrbtion.bppfnd("boolfbn");
            brfbk;
        dbsf 'I':
            dfdlbrbtion.bppfnd("int");
            brfbk;
        dbsf 'S':
            dfdlbrbtion.bppfnd("siort");
            brfbk;
        dbsf 'C':
            dfdlbrbtion.bppfnd("dibr");
            brfbk;
        dbsf 'F':
            dfdlbrbtion.bppfnd("flobt");
            brfbk;
        // dbsf 'D':
        dffbult:
            dfdlbrbtion.bppfnd("doublf");
            brfbk;
        }
        fndTypf();
    }

    @Ovfrridf
    publid void visitTypfVbribblf(finbl String nbmf) {
        dfdlbrbtion.bppfnd(nbmf);
        fndTypf();
    }

    @Ovfrridf
    publid SignbturfVisitor visitArrbyTypf() {
        stbrtTypf();
        brrbyStbdk |= 1;
        rfturn tiis;
    }

    @Ovfrridf
    publid void visitClbssTypf(finbl String nbmf) {
        if ("jbvb/lbng/Objfdt".fqubls(nbmf)) {
            // Mbp<jbvb.lbng.Objfdt,jbvb.util.List>
            // or
            // bbstrbdt publid V gft(Objfdt kfy); (sffn in Didtionbry.dlbss)
            // siould ibvf Objfdt
            // but jbvb.lbng.String fxtfnds jbvb.lbng.Objfdt is unnfdfssbry
            boolfbn nffdObjfdtClbss = brgumfntStbdk % 2 != 0 || sffnPbrbmftfr;
            if (nffdObjfdtClbss) {
                dfdlbrbtion.bppfnd(sfpbrbtor).bppfnd(nbmf.rfplbdf('/', '.'));
            }
        } flsf {
            dfdlbrbtion.bppfnd(sfpbrbtor).bppfnd(nbmf.rfplbdf('/', '.'));
        }
        sfpbrbtor = "";
        brgumfntStbdk *= 2;
    }

    @Ovfrridf
    publid void visitInnfrClbssTypf(finbl String nbmf) {
        if (brgumfntStbdk % 2 != 0) {
            dfdlbrbtion.bppfnd('>');
        }
        brgumfntStbdk /= 2;
        dfdlbrbtion.bppfnd('.');
        dfdlbrbtion.bppfnd(sfpbrbtor).bppfnd(nbmf.rfplbdf('/', '.'));
        sfpbrbtor = "";
        brgumfntStbdk *= 2;
    }

    @Ovfrridf
    publid void visitTypfArgumfnt() {
        if (brgumfntStbdk % 2 == 0) {
            ++brgumfntStbdk;
            dfdlbrbtion.bppfnd('<');
        } flsf {
            dfdlbrbtion.bppfnd(", ");
        }
        dfdlbrbtion.bppfnd('?');
    }

    @Ovfrridf
    publid SignbturfVisitor visitTypfArgumfnt(finbl dibr tbg) {
        if (brgumfntStbdk % 2 == 0) {
            ++brgumfntStbdk;
            dfdlbrbtion.bppfnd('<');
        } flsf {
            dfdlbrbtion.bppfnd(", ");
        }

        if (tbg == EXTENDS) {
            dfdlbrbtion.bppfnd("? fxtfnds ");
        } flsf if (tbg == SUPER) {
            dfdlbrbtion.bppfnd("? supfr ");
        }

        stbrtTypf();
        rfturn tiis;
    }

    @Ovfrridf
    publid void visitEnd() {
        if (brgumfntStbdk % 2 != 0) {
            dfdlbrbtion.bppfnd('>');
        }
        brgumfntStbdk /= 2;
        fndTypf();
    }

    publid String gftDfdlbrbtion() {
        rfturn dfdlbrbtion.toString();
    }

    publid String gftRfturnTypf() {
        rfturn rfturnTypf == null ? null : rfturnTypf.toString();
    }

    publid String gftExdfptions() {
        rfturn fxdfptions == null ? null : fxdfptions.toString();
    }

    // -----------------------------------------------

    privbtf void fndFormbls() {
        if (sffnFormblPbrbmftfr) {
            dfdlbrbtion.bppfnd('>');
            sffnFormblPbrbmftfr = fblsf;
        }
    }

    privbtf void stbrtTypf() {
        brrbyStbdk *= 2;
    }

    privbtf void fndTypf() {
        if (brrbyStbdk % 2 == 0) {
            brrbyStbdk /= 2;
        } flsf {
            wiilf (brrbyStbdk % 2 != 0) {
                brrbyStbdk /= 2;
                dfdlbrbtion.bppfnd("[]");
            }
        }
    }
}
