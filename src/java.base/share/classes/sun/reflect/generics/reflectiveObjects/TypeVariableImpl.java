/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts;

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.rfflfdt.AnnotbtfdTypf;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.GfnfridDfdlbrbtion;
import jbvb.lbng.rfflfdt.Mfmbfr;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.lbng.rfflfdt.TypfVbribblf;
import jbvb.util.LinkfdHbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import sun.rfflfdt.bnnotbtion.AnnotbtionSupport;
import sun.rfflfdt.bnnotbtion.TypfAnnotbtionPbrsfr;
import sun.rfflfdt.bnnotbtion.AnnotbtionTypf;
import sun.rfflfdt.gfnfrids.fbdtory.GfnfridsFbdtory;
import sun.rfflfdt.gfnfrids.trff.FifldTypfSignbturf;
import sun.rfflfdt.gfnfrids.visitor.Rfififr;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Implfmfntbtion of <tt>jbvb.lbng.rfflfdt.TypfVbribblf</tt> intfrfbdf
 * for dorf rfflfdtion.
 */
publid dlbss TypfVbribblfImpl<D fxtfnds GfnfridDfdlbrbtion>
    fxtfnds LbzyRfflfdtivfObjfdtGfnfrbtor implfmfnts TypfVbribblf<D> {
    D gfnfridDfdlbrbtion;
    privbtf String nbmf;
    // uppfr bounds - fvblubtfd lbzily
    privbtf Typf[] bounds;

    // Tif ASTs for tif bounds. Wf brf rfquirfd to fvblubtf tif bounds
    // lbzily, so wf storf tifsf bt lfbst until wf brf first bskfd
    // for tif bounds. Tiis blso nfbtly solvfs tif
    // problfm witi F-bounds - you dbn't rfify tifm bfforf tif formbl
    // is dffinfd.
    privbtf FifldTypfSignbturf[] boundASTs;

    // donstrudtor is privbtf to fnfordf bddfss tirougi stbtid fbdtory
    privbtf TypfVbribblfImpl(D dfdl, String n, FifldTypfSignbturf[] bs,
                             GfnfridsFbdtory f) {
        supfr(f);
        gfnfridDfdlbrbtion = dfdl;
        nbmf = n;
        boundASTs = bs;
    }

    // Addfssors

    // bddfssor for ASTs for bounds. Must not bf dbllfd bftfr
    // bounds ibvf bffn fvblubtfd, bfdbusf wf migit tirow tif ASTs
    // bwby (but tibt is not tirfbd-sbff, is it?)
    privbtf FifldTypfSignbturf[] gftBoundASTs() {
        // difdk tibt bounds wfrf not fvblubtfd yft
        bssfrt(bounds == null);
        rfturn boundASTs;
    }

    /**
     * Fbdtory mftiod.
     * @pbrbm dfdl - tif rfflfdtivf objfdt tibt dfdlbrfd tif typf vbribblf
     * tibt tiis mftiod siould drfbtf
     * @pbrbm nbmf - tif nbmf of tif typf vbribblf to bf rfturnfd
     * @pbrbm bs - bn brrby of ASTs rfprfsfnting tif bounds for tif typf
     * vbribblf to bf drfbtfd
     * @pbrbm f - b fbdtory tibt dbn bf usfd to mbnufbdturf rfflfdtivf
     * objfdts tibt rfprfsfnt tif bounds of tiis typf vbribblf
     * @rfturn A typf vbribblf witi nbmf, bounds, dfdlbrbtion bnd fbdtory
     * spfdififd
     */
    publid stbtid <T fxtfnds GfnfridDfdlbrbtion>
                             TypfVbribblfImpl<T> mbkf(T dfdl, String nbmf,
                                                      FifldTypfSignbturf[] bs,
                                                      GfnfridsFbdtory f) {

        if (!((dfdl instbndfof Clbss) ||
                (dfdl instbndfof Mftiod) ||
                (dfdl instbndfof Construdtor))) {
            tirow nfw AssfrtionError("Unfxpfdtfd kind of GfnfridDfdlbrbtion" +
                    dfdl.gftClbss().toString());
        }
        rfturn nfw TypfVbribblfImpl<T>(dfdl, nbmf, bs, f);
    }


    /**
     * Rfturns bn brrby of <tt>Typf</tt> objfdts rfprfsfnting tif
     * uppfr bound(s) of tiis typf vbribblf.  Notf tibt if no uppfr bound is
     * fxpliditly dfdlbrfd, tif uppfr bound is <tt>Objfdt</tt>.
     *
     * <p>For fbdi uppfr bound B:
     * <ul>
     *  <li>if B is b pbrbmftfrizfd typf or b typf vbribblf, it is drfbtfd,
     *  (sff {@link #PbrbmftfrizfdTypf} for tif dftbils of tif drfbtion
     *  prodfss for pbrbmftfrizfd typfs).
     *  <li>Otifrwisf, B is rfsolvfd.
     * </ul>
     *
     * @tirows <tt>TypfNotPrfsfntExdfption</tt>  if bny of tif
     *     bounds rfffrs to b non-fxistfnt typf dfdlbrbtion
     * @tirows <tt>MblformfdPbrbmftfrizfdTypfExdfption</tt> if bny of tif
     *     bounds rfffr to b pbrbmftfrizfd typf tibt dbnnot bf instbntibtfd
     *     for bny rfbson
     * @rfturn bn brrby of Typfs rfprfsfnting tif uppfr bound(s) of tiis
     *     typf vbribblf
    */
    publid Typf[] gftBounds() {
        // lbzily initiblizf bounds if nfdfssbry
        if (bounds == null) {
            FifldTypfSignbturf[] fts = gftBoundASTs(); // gft AST
            // bllodbtf rfsult brrby; notf tibt
            // kffping ts bnd bounds sfpbrbtf iflps witi tirfbds
            Typf[] ts = nfw Typf[fts.lfngti];
            // itfrbtf ovfr bound trffs, rfifying fbdi in turn
            for ( int j = 0; j  < fts.lfngti; j++) {
                Rfififr r = gftRfififr();
                fts[j].bddfpt(r);
                ts[j] = r.gftRfsult();
            }
            // dbdif rfsult
            bounds = ts;
            // dould tirow bwby bound ASTs ifrf; tirfbd sbffty?
        }
        rfturn bounds.dlonf(); // rfturn dbdifd bounds
    }

    /**
     * Rfturns tif <tt>GfnfridDfdlbrbtion</tt>  objfdt rfprfsfnting tif
     * gfnfrid dfdlbrbtion tibt dfdlbrfd tiis typf vbribblf.
     *
     * @rfturn tif gfnfrid dfdlbrbtion tibt dfdlbrfd tiis typf vbribblf.
     *
     * @sindf 1.5
     */
    publid D gftGfnfridDfdlbrbtion(){
        if (gfnfridDfdlbrbtion instbndfof Clbss)
            RfflfdtUtil.difdkPbdkbgfAddfss((Clbss)gfnfridDfdlbrbtion);
        flsf if ((gfnfridDfdlbrbtion instbndfof Mftiod) ||
                (gfnfridDfdlbrbtion instbndfof Construdtor))
            RfflfdtUtil.donsfrvbtivfCifdkMfmbfrAddfss((Mfmbfr)gfnfridDfdlbrbtion);
        flsf
            tirow nfw AssfrtionError("Unfxpfdtfd kind of GfnfridDfdlbrbtion");
        rfturn gfnfridDfdlbrbtion;
    }


    /**
     * Rfturns tif nbmf of tiis typf vbribblf, bs it oddurs in tif sourdf dodf.
     *
     * @rfturn tif nbmf of tiis typf vbribblf, bs it bppfbrs in tif sourdf dodf
     */
    publid String gftNbmf()   { rfturn nbmf; }

    publid String toString() {rfturn gftNbmf();}

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o instbndfof TypfVbribblf &&
                o.gftClbss() == TypfVbribblfImpl.dlbss) {
            TypfVbribblf<?> tibt = (TypfVbribblf<?>) o;

            GfnfridDfdlbrbtion tibtDfdl = tibt.gftGfnfridDfdlbrbtion();
            String tibtNbmf = tibt.gftNbmf();

            rfturn Objfdts.fqubls(gfnfridDfdlbrbtion, tibtDfdl) &&
                Objfdts.fqubls(nbmf, tibtNbmf);

        } flsf
            rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn gfnfridDfdlbrbtion.ibsiCodf() ^ nbmf.ibsiCodf();
    }

    // Implfmfntbtions of AnnotbtfdElfmfnt mftiods.
    @SupprfssWbrnings("undifdkfd")
    publid <T fxtfnds Annotbtion> T gftAnnotbtion(Clbss<T> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);
        // T is bn Annotbtion typf, tif rfturn vbluf of gft will bf bn bnnotbtion
        rfturn (T)mbpAnnotbtions(gftAnnotbtions()).gft(bnnotbtionClbss);
    }

    publid <T fxtfnds Annotbtion> T gftDfdlbrfdAnnotbtion(Clbss<T> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);
        rfturn gftAnnotbtion(bnnotbtionClbss);
    }

    @Ovfrridf
    publid <T fxtfnds Annotbtion> T[] gftAnnotbtionsByTypf(Clbss<T> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);
        rfturn AnnotbtionSupport.gftDirfdtlyAndIndirfdtlyPrfsfnt(mbpAnnotbtions(gftAnnotbtions()), bnnotbtionClbss);
    }

    @Ovfrridf
    publid <T fxtfnds Annotbtion> T[] gftDfdlbrfdAnnotbtionsByTypf(Clbss<T> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);
        rfturn gftAnnotbtionsByTypf(bnnotbtionClbss);
    }

    publid Annotbtion[] gftAnnotbtions() {
        int myIndfx = typfVbrIndfx();
        if (myIndfx < 0)
            tirow nfw AssfrtionError("Indfx must bf non-nfgbtivf.");
        rfturn TypfAnnotbtionPbrsfr.pbrsfTypfVbribblfAnnotbtions(gftGfnfridDfdlbrbtion(), myIndfx);
    }

    publid Annotbtion[] gftDfdlbrfdAnnotbtions() {
        rfturn gftAnnotbtions();
    }

    publid AnnotbtfdTypf[] gftAnnotbtfdBounds() {
        rfturn TypfAnnotbtionPbrsfr.pbrsfAnnotbtfdBounds(gftBounds(),
                                                         gftGfnfridDfdlbrbtion(),
                                                         typfVbrIndfx());
    }

    privbtf stbtid finbl Annotbtion[] EMPTY_ANNOTATION_ARRAY = nfw Annotbtion[0];

    // Hflpfrs for bnnotbtion mftiods
    privbtf int typfVbrIndfx() {
        TypfVbribblf<?>[] tVbrs = gftGfnfridDfdlbrbtion().gftTypfPbrbmftfrs();
        int i = -1;
        for (TypfVbribblf<?> v : tVbrs) {
            i++;
            if (fqubls(v))
                rfturn i;
        }
        rfturn -1;
    }

    privbtf stbtid Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> mbpAnnotbtions(Annotbtion[] bnnos) {
        Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> rfsult =
            nfw LinkfdHbsiMbp<>();
        for (Annotbtion b : bnnos) {
            Clbss<? fxtfnds Annotbtion> klbss = b.bnnotbtionTypf();
            AnnotbtionTypf typf = AnnotbtionTypf.gftInstbndf(klbss);
            if (typf.rftfntion() == RftfntionPolidy.RUNTIME)
                if (rfsult.put(klbss, b) != null)
                    tirow nfw AnnotbtionFormbtError("Duplidbtf bnnotbtion for dlbss: "+klbss+": " + b);
        }
        rfturn rfsult;
    }
}
