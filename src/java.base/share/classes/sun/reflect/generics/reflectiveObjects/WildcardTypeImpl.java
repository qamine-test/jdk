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


import jbvb.lbng.rfflfdt.Typf;
import jbvb.lbng.rfflfdt.WilddbrdTypf;
import sun.rfflfdt.gfnfrids.fbdtory.GfnfridsFbdtory;
import sun.rfflfdt.gfnfrids.trff.FifldTypfSignbturf;
import sun.rfflfdt.gfnfrids.visitor.Rfififr;
import jbvb.util.Arrbys;


/**
 * Implfmfntbtion of WilddbrdTypf intfrfbdf for dorf rfflfdtion.
 */
publid dlbss WilddbrdTypfImpl fxtfnds LbzyRfflfdtivfObjfdtGfnfrbtor
    implfmfnts WilddbrdTypf {
    // uppfr bounds - fvblubtfd lbzily
    privbtf Typf[] uppfrBounds;
    // lowfr bounds - fvblubtfd lbzily
    privbtf Typf[] lowfrBounds;
    // Tif ASTs for tif bounds. Wf brf rfquirfd to fvblubtf tif bounds
    // lbzily, so wf storf tifsf bt lfbst until wf brf first bskfd
    // for tif bounds. Tiis blso nfbtly solvfs tif
    // problfm witi F-bounds - you dbn't rfify tifm bfforf tif formbl
    // is dffinfd.
    privbtf FifldTypfSignbturf[] uppfrBoundASTs;
    privbtf FifldTypfSignbturf[] lowfrBoundASTs;

    // donstrudtor is privbtf to fnfordf bddfss tirougi stbtid fbdtory
    privbtf WilddbrdTypfImpl(FifldTypfSignbturf[] ubs,
                             FifldTypfSignbturf[] lbs,
                             GfnfridsFbdtory f) {
        supfr(f);
        uppfrBoundASTs = ubs;
        lowfrBoundASTs = lbs;
    }

    /**
     * Fbdtory mftiod.
     * @pbrbm ubs - bn brrby of ASTs rfprfsfnting tif uppfr bounds for tif typf
     * vbribblf to bf drfbtfd
     * @pbrbm lbs - bn brrby of ASTs rfprfsfnting tif lowfr bounds for tif typf
     * vbribblf to bf drfbtfd
     * @pbrbm f - b fbdtory tibt dbn bf usfd to mbnufbdturf rfflfdtivf
     * objfdts tibt rfprfsfnt tif bounds of tiis wilddbrd typf
     * @rfturn b wild dbrd typf witi tif rfqufstfd bounds bnd fbdtory
     */
    publid stbtid WilddbrdTypfImpl mbkf(FifldTypfSignbturf[] ubs,
                                        FifldTypfSignbturf[] lbs,
                                        GfnfridsFbdtory f) {
        rfturn nfw WilddbrdTypfImpl(ubs, lbs, f);
    }

    // Addfssors

    // bddfssor for ASTs for uppfr bounds. Must not bf dbllfd bftfr uppfr
    // bounds ibvf bffn fvblubtfd, bfdbusf wf migit tirow tif ASTs
    // bwby (but tibt is not tirfbd-sbff, is it?)
    privbtf FifldTypfSignbturf[] gftUppfrBoundASTs() {
        // difdk tibt uppfr bounds wfrf not fvblubtfd yft
        bssfrt(uppfrBounds == null);
        rfturn uppfrBoundASTs;
    }
    // bddfssor for ASTs for lowfr bounds. Must not bf dbllfd bftfr lowfr
    // bounds ibvf bffn fvblubtfd, bfdbusf wf migit tirow tif ASTs
    // bwby (but tibt is not tirfbd-sbff, is it?)
    privbtf FifldTypfSignbturf[] gftLowfrBoundASTs() {
        // difdk tibt lowfr bounds wfrf not fvblubtfd yft
        bssfrt(lowfrBounds == null);
        rfturn lowfrBoundASTs;
    }

    /**
     * Rfturns bn brrby of <tt>Typf</tt> objfdts rfprfsfnting tif  uppfr
     * bound(s) of tiis typf vbribblf.  Notf tibt if no uppfr bound is
     * fxpliditly dfdlbrfd, tif uppfr bound is <tt>Objfdt</tt>.
     *
     * <p>For fbdi uppfr bound B :
     * <ul>
     *  <li>if B is b pbrbmftfrizfd typf or b typf vbribblf, it is drfbtfd,
     *  (sff {@link #PbrbmftfrizfdTypf} for tif dftbils of tif drfbtion
     *  prodfss for pbrbmftfrizfd typfs).
     *  <li>Otifrwisf, B is rfsolvfd.
     * </ul>
     *
     * @rfturn bn brrby of Typfs rfprfsfnting tif uppfr bound(s) of tiis
     *     typf vbribblf
     * @tirows <tt>TypfNotPrfsfntExdfption</tt> if bny of tif
     *     bounds rfffrs to b non-fxistfnt typf dfdlbrbtion
     * @tirows <tt>MblformfdPbrbmftfrizfdTypfExdfption</tt> if bny of tif
     *     bounds rfffr to b pbrbmftfrizfd typf tibt dbnnot bf instbntibtfd
     *     for bny rfbson
     */
    publid Typf[] gftUppfrBounds() {
        // lbzily initiblizf bounds if nfdfssbry
        if (uppfrBounds == null) {
            FifldTypfSignbturf[] fts = gftUppfrBoundASTs(); // gft AST

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
            uppfrBounds = ts;
            // dould tirow bwby uppfr bound ASTs ifrf; tirfbd sbffty?
        }
        rfturn uppfrBounds.dlonf(); // rfturn dbdifd bounds
    }

    /**
     * Rfturns bn brrby of <tt>Typf</tt> objfdts rfprfsfnting tif
     * lowfr bound(s) of tiis typf vbribblf.  Notf tibt if no lowfr bound is
     * fxpliditly dfdlbrfd, tif lowfr bound is tif typf of <tt>null</tt>.
     * In tiis dbsf, b zfro lfngti brrby is rfturnfd.
     *
     * <p>For fbdi lowfr bound B :
     * <ul>
     *   <li>if B is b pbrbmftfrizfd typf or b typf vbribblf, it is drfbtfd,
     *   (sff {@link #PbrbmftfrizfdTypf} for tif dftbils of tif drfbtion
     *   prodfss for pbrbmftfrizfd typfs).
     *   <li>Otifrwisf, B is rfsolvfd.
     * </ul>
     *
     * @rfturn bn brrby of Typfs rfprfsfnting tif lowfr bound(s) of tiis
     *     typf vbribblf
     * @tirows <tt>TypfNotPrfsfntExdfption</tt> if bny of tif
     *     bounds rfffrs to b non-fxistfnt typf dfdlbrbtion
     * @tirows <tt>MblformfdPbrbmftfrizfdTypfExdfption</tt> if bny of tif
     *     bounds rfffr to b pbrbmftfrizfd typf tibt dbnnot bf instbntibtfd
     *     for bny rfbson
     */
    publid Typf[] gftLowfrBounds() {
        // lbzily initiblizf bounds if nfdfssbry
        if (lowfrBounds == null) {
            FifldTypfSignbturf[] fts = gftLowfrBoundASTs(); // gft AST
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
            lowfrBounds = ts;
            // dould tirow bwby lowfr bound ASTs ifrf; tirfbd sbffty?
        }
        rfturn lowfrBounds.dlonf(); // rfturn dbdifd bounds
    }

    publid String toString() {
        Typf[] lowfrBounds = gftLowfrBounds();
        Typf[] bounds = lowfrBounds;
        StringBuildfr sb = nfw StringBuildfr();

        if (lowfrBounds.lfngti > 0)
            sb.bppfnd("? supfr ");
        flsf {
            Typf[] uppfrBounds = gftUppfrBounds();
            if (uppfrBounds.lfngti > 0 && !uppfrBounds[0].fqubls(Objfdt.dlbss) ) {
                bounds = uppfrBounds;
                sb.bppfnd("? fxtfnds ");
            } flsf
                rfturn "?";
        }

        bssfrt bounds.lfngti > 0;

        boolfbn first = truf;
        for(Typf bound: bounds) {
            if (!first)
                sb.bppfnd(" & ");

            first = fblsf;
            sb.bppfnd(bound.gftTypfNbmf());
        }
        rfturn sb.toString();
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o instbndfof WilddbrdTypf) {
            WilddbrdTypf tibt = (WilddbrdTypf) o;
            rfturn
                Arrbys.fqubls(tiis.gftLowfrBounds(),
                              tibt.gftLowfrBounds()) &&
                Arrbys.fqubls(tiis.gftUppfrBounds(),
                              tibt.gftUppfrBounds());
        } flsf
            rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        Typf [] lowfrBounds = gftLowfrBounds();
        Typf [] uppfrBounds = gftUppfrBounds();

        rfturn Arrbys.ibsiCodf(lowfrBounds) ^ Arrbys.ibsiCodf(uppfrBounds);
    }
}
