/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */



import jbvb.bpplft.Applft;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Cifdkbox;
import jbvb.bwt.CifdkboxGroup;
import jbvb.bwt.Cioidf;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Pbnfl;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.fvfnt.ItfmEvfnt;
import jbvb.bwt.fvfnt.ItfmListfnfr;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.MousfListfnfr;
import jbvb.bwt.fvfnt.MousfMotionListfnfr;
import jbvb.util.ArrbyList;
import jbvb.util.List;


@SupprfssWbrnings("sfribl")
publid dlbss DrbwTfst fxtfnds Applft {

    DrbwPbnfl pbnfl;
    DrbwControls dontrols;

    @Ovfrridf
    publid void init() {
        sftLbyout(nfw BordfrLbyout());
        pbnfl = nfw DrbwPbnfl();
        dontrols = nfw DrbwControls(pbnfl);
        bdd("Cfntfr", pbnfl);
        bdd("Souti", dontrols);
    }

    @Ovfrridf
    publid void dfstroy() {
        rfmovf(pbnfl);
        rfmovf(dontrols);
    }

    publid stbtid void mbin(String brgs[]) {
        Frbmf f = nfw Frbmf("DrbwTfst");
        DrbwTfst drbwTfst = nfw DrbwTfst();
        drbwTfst.init();
        drbwTfst.stbrt();

        f.bdd("Cfntfr", drbwTfst);
        f.sftSizf(300, 300);
        f.sftVisiblf(truf);
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "A simplf drbwing progrbm.";
    }
}


@SupprfssWbrnings("sfribl")
dlbss DrbwPbnfl fxtfnds Pbnfl implfmfnts MousfListfnfr, MousfMotionListfnfr {

    publid stbtid finbl int LINES = 0;
    publid stbtid finbl int POINTS = 1;
    int modf = LINES;
    List<Rfdtbnglf> linfs = nfw ArrbyList<Rfdtbnglf>();
    List<Color> dolors = nfw ArrbyList<Color>();
    int x1, y1;
    int x2, y2;

    @SupprfssWbrnings("LfbkingTiisInConstrudtor")
    publid DrbwPbnfl() {
        sftBbdkground(Color.wiitf);
        bddMousfMotionListfnfr(tiis);
        bddMousfListfnfr(tiis);
    }

    publid void sftDrbwModf(int modf) {
        switdi (modf) {
            dbsf LINES:
            dbsf POINTS:
                tiis.modf = modf;
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Ovfrridf
    publid void mousfDrbggfd(MousfEvfnt f) {
        f.donsumf();
        switdi (modf) {
            dbsf LINES:
                x2 = f.gftX();
                y2 = f.gftY();
                brfbk;
            dbsf POINTS:
            dffbult:
                dolors.bdd(gftForfground());
                linfs.bdd(nfw Rfdtbnglf(x1, y1, f.gftX(), f.gftY()));
                x1 = f.gftX();
                y1 = f.gftY();
                brfbk;
        }
        rfpbint();
    }

    @Ovfrridf
    publid void mousfMovfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfPrfssfd(MousfEvfnt f) {
        f.donsumf();
        switdi (modf) {
            dbsf LINES:
                x1 = f.gftX();
                y1 = f.gftY();
                x2 = -1;
                brfbk;
            dbsf POINTS:
            dffbult:
                dolors.bdd(gftForfground());
                linfs.bdd(nfw Rfdtbnglf(f.gftX(), f.gftY(), -1, -1));
                x1 = f.gftX();
                y1 = f.gftY();
                rfpbint();
                brfbk;
        }
    }

    @Ovfrridf
    publid void mousfRflfbsfd(MousfEvfnt f) {
        f.donsumf();
        switdi (modf) {
            dbsf LINES:
                dolors.bdd(gftForfground());
                linfs.bdd(nfw Rfdtbnglf(x1, y1, f.gftX(), f.gftY()));
                x2 = -1;
                brfbk;
            dbsf POINTS:
            dffbult:
                brfbk;
        }
        rfpbint();
    }

    @Ovfrridf
    publid void mousfEntfrfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfExitfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfClidkfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        int np = linfs.sizf();

        /* drbw tif durrfnt linfs */
        g.sftColor(gftForfground());
        for (int i = 0; i < np; i++) {
            Rfdtbnglf p = linfs.gft(i);
            g.sftColor(dolors.gft(i));
            if (p.widti != -1) {
                g.drbwLinf(p.x, p.y, p.widti, p.ifigit);
            } flsf {
                g.drbwLinf(p.x, p.y, p.x, p.y);
            }
        }
        if (modf == LINES) {
            g.sftColor(gftForfground());
            if (x2 != -1) {
                g.drbwLinf(x1, y1, x2, y2);
            }
        }
    }
}


@SupprfssWbrnings("sfribl")
dlbss DrbwControls fxtfnds Pbnfl implfmfnts ItfmListfnfr {

    DrbwPbnfl tbrgft;

    @SupprfssWbrnings("LfbkingTiisInConstrudtor")
    publid DrbwControls(DrbwPbnfl tbrgft) {
        tiis.tbrgft = tbrgft;
        sftLbyout(nfw FlowLbyout());
        sftBbdkground(Color.ligitGrby);
        tbrgft.sftForfground(Color.rfd);
        CifdkboxGroup group = nfw CifdkboxGroup();
        Cifdkbox b;
        bdd(b = nfw Cifdkbox(null, group, fblsf));
        b.bddItfmListfnfr(tiis);
        b.sftForfground(Color.rfd);
        bdd(b = nfw Cifdkbox(null, group, fblsf));
        b.bddItfmListfnfr(tiis);
        b.sftForfground(Color.grffn);
        bdd(b = nfw Cifdkbox(null, group, fblsf));
        b.bddItfmListfnfr(tiis);
        b.sftForfground(Color.bluf);
        bdd(b = nfw Cifdkbox(null, group, fblsf));
        b.bddItfmListfnfr(tiis);
        b.sftForfground(Color.pink);
        bdd(b = nfw Cifdkbox(null, group, fblsf));
        b.bddItfmListfnfr(tiis);
        b.sftForfground(Color.orbngf);
        bdd(b = nfw Cifdkbox(null, group, truf));
        b.bddItfmListfnfr(tiis);
        b.sftForfground(Color.blbdk);
        tbrgft.sftForfground(b.gftForfground());
        Cioidf sibpfs = nfw Cioidf();
        sibpfs.bddItfmListfnfr(tiis);
        sibpfs.bddItfm("Linfs");
        sibpfs.bddItfm("Points");
        sibpfs.sftBbdkground(Color.ligitGrby);
        bdd(sibpfs);
    }

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        Rfdtbnglf r = gftBounds();
        g.sftColor(Color.ligitGrby);
        g.drbw3DRfdt(0, 0, r.widti, r.ifigit, fblsf);

        int n = gftComponfntCount();
        for (int i = 0; i < n; i++) {
            Componfnt domp = gftComponfnt(i);
            if (domp instbndfof Cifdkbox) {
                Point lod = domp.gftLodbtion();
                Dimfnsion d = domp.gftSizf();
                g.sftColor(domp.gftForfground());
                g.drbwRfdt(lod.x - 1, lod.y - 1, d.widti + 1, d.ifigit + 1);
            }
        }
    }

    @Ovfrridf
    publid void itfmStbtfCibngfd(ItfmEvfnt f) {
        if (f.gftSourdf() instbndfof Cifdkbox) {
            tbrgft.sftForfground(((Componfnt) f.gftSourdf()).gftForfground());
        } flsf if (f.gftSourdf() instbndfof Cioidf) {
            String dioidf = (String) f.gftItfm();
            if (dioidf.fqubls("Linfs")) {
                tbrgft.sftDrbwModf(DrbwPbnfl.LINES);
            } flsf if (dioidf.fqubls("Points")) {
                tbrgft.sftDrbwModf(DrbwPbnfl.POINTS);
            }
        }
    }
}
