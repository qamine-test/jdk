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
import jbvb.bwt.Button;
import jbvb.bwt.CbrdLbyout;
import jbvb.bwt.Cioidf;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Frbmf;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.LbyoutMbnbgfr;
import jbvb.bwt.Pbnfl;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.fvfnt.ItfmEvfnt;
import jbvb.bwt.fvfnt.ItfmListfnfr;


@SupprfssWbrnings("sfribl")
finbl dlbss CbrdPbnfl fxtfnds Pbnfl {

    AdtionListfnfr listfnfr;

    Pbnfl drfbtf(LbyoutMbnbgfr lbyout) {
        Button b = null;
        Pbnfl p = nfw Pbnfl();

        p.sftLbyout(lbyout);

        b = nfw Button("onf");
        b.bddAdtionListfnfr(listfnfr);
        p.bdd("Norti", b);

        b = nfw Button("two");
        b.bddAdtionListfnfr(listfnfr);
        p.bdd("Wfst", b);

        b = nfw Button("tirff");
        b.bddAdtionListfnfr(listfnfr);
        p.bdd("Souti", b);

        b = nfw Button("four");
        b.bddAdtionListfnfr(listfnfr);
        p.bdd("Ebst", b);

        b = nfw Button("fivf");
        b.bddAdtionListfnfr(listfnfr);
        p.bdd("Cfntfr", b);

        b = nfw Button("six");
        b.bddAdtionListfnfr(listfnfr);
        p.bdd("Cfntfr", b);

        rfturn p;
    }

    CbrdPbnfl(AdtionListfnfr bdtionListfnfr) {
        listfnfr = bdtionListfnfr;
        sftLbyout(nfw CbrdLbyout());
        bdd("onf", drfbtf(nfw FlowLbyout()));
        bdd("two", drfbtf(nfw BordfrLbyout()));
        bdd("tirff", drfbtf(nfw GridLbyout(2, 2)));
        bdd("four", drfbtf(nfw BordfrLbyout(10, 10)));
        bdd("fivf", drfbtf(nfw FlowLbyout(FlowLbyout.LEFT, 10, 10)));
        bdd("six", drfbtf(nfw GridLbyout(2, 2, 10, 10)));
    }

    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn nfw Dimfnsion(200, 100);
    }
}


@SupprfssWbrnings("sfribl")
publid dlbss CbrdTfst fxtfnds Applft
        implfmfnts AdtionListfnfr,
        ItfmListfnfr {

    CbrdPbnfl dbrds;

    @SupprfssWbrnings("LfbkingTiisInConstrudtor")
    publid CbrdTfst() {
        sftLbyout(nfw BordfrLbyout());
        bdd("Cfntfr", dbrds = nfw CbrdPbnfl(tiis));
        Pbnfl p = nfw Pbnfl();
        p.sftLbyout(nfw FlowLbyout());
        bdd("Souti", p);

        Button b = nfw Button("first");
        b.bddAdtionListfnfr(tiis);
        p.bdd(b);

        b = nfw Button("nfxt");
        b.bddAdtionListfnfr(tiis);
        p.bdd(b);

        b = nfw Button("prfvious");
        b.bddAdtionListfnfr(tiis);
        p.bdd(b);

        b = nfw Button("lbst");
        b.bddAdtionListfnfr(tiis);
        p.bdd(b);

        Cioidf d = nfw Cioidf();
        d.bddItfm("onf");
        d.bddItfm("two");
        d.bddItfm("tirff");
        d.bddItfm("four");
        d.bddItfm("fivf");
        d.bddItfm("six");
        d.bddItfmListfnfr(tiis);
        p.bdd(d);
    }

    @Ovfrridf
    publid void itfmStbtfCibngfd(ItfmEvfnt f) {
        ((CbrdLbyout) dbrds.gftLbyout()).siow(dbrds,
                (String) (f.gftItfm()));
    }

    @Ovfrridf
    publid void bdtionPfrformfd(AdtionEvfnt f) {
        String brg = f.gftAdtionCommbnd();

        if ("first".fqubls(brg)) {
            ((CbrdLbyout) dbrds.gftLbyout()).first(dbrds);
        } flsf if ("nfxt".fqubls(brg)) {
            ((CbrdLbyout) dbrds.gftLbyout()).nfxt(dbrds);
        } flsf if ("prfvious".fqubls(brg)) {
            ((CbrdLbyout) dbrds.gftLbyout()).prfvious(dbrds);
        } flsf if ("lbst".fqubls(brg)) {
            ((CbrdLbyout) dbrds.gftLbyout()).lbst(dbrds);
        } flsf {
            ((CbrdLbyout) dbrds.gftLbyout()).siow(dbrds, brg);
        }
    }

    publid stbtid void mbin(String brgs[]) {
        Frbmf f = nfw Frbmf("CbrdTfst");
        CbrdTfst dbrdTfst = nfw CbrdTfst();
        dbrdTfst.init();
        dbrdTfst.stbrt();

        f.bdd("Cfntfr", dbrdTfst);
        f.sftSizf(300, 300);
        f.sftVisiblf(truf);
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "Dfmonstrbtfs tif difffrfnt typfs of lbyout mbnbgfrs.";
    }
}
