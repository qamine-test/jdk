/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns;

import jbvb.lbng.rfflfdt.Arrby;
import jbvb.lbng.rfflfdt.GfnfridArrbyTypf;
import jbvb.lbng.rfflfdt.PbrbmftfrizfdTypf;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.lbng.rfflfdt.TypfVbribblf;
import jbvb.lbng.rfflfdt.WilddbrdTypf;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.GfnfridArrbyTypfImpl;
import sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts.PbrbmftfrizfdTypfImpl;

/**
 * Tiis is utility dlbss to rfsolvf typfs.
 *
 * @sindf 1.7
 *
 * @butior Ebmonn MdMbnus
 * @butior Sfrgfy Mblfnkov
 */
publid finbl dlbss TypfRfsolvfr {

    privbtf stbtid finbl WfbkCbdif<Typf, Mbp<Typf, Typf>> CACHE = nfw WfbkCbdif<>();

    /**
     * Rfplbdfs tif givfn {@dodf typf} in bn inifritfd mftiod
     * witi tif bdtubl typf it ibs in tif givfn {@dodf inClbss}.
     *
     * <p>Altiougi typf pbrbmftfrs brf not inifritfd by subdlbssfs in tif Jbvb
     * lbngubgf, tify <fm>brf</fm> ffffdtivfly inifritfd wifn using rfflfdtion.
     * For fxbmplf, if you dfdlbrf bn intfrfbdf likf tiis...</p>
     *
     * <prf>
     * publid intfrfbdf StringToIntMbp fxtfnds Mbp&lt;String,Intfgfr> {}
     * </prf>
     *
     * <p>...tifn StringToIntMbp.dlbss.gftMftiods() will siow tibt it ibs mftiods
     * likf put(K,V) fvfn tiougi StringToIntMbp ibs no typf pbrbmftfrs.  Tif K
     * bnd V vbribblfs brf tif onfs dfdlbrfd by Mbp, so
     * {@link TypfVbribblf#gftGfnfridDfdlbrbtion()} will rfturn Mbp.dlbss.</p>
     *
     * <p>Tif purposf of tiis mftiod is to tbkf b Typf from b possibly-inifritfd
     * mftiod bnd rfplbdf it witi tif dorrfdt Typf for tif inifriting dlbss.
     * So givfn pbrbmftfrs of K bnd StringToIntMbp.dlbss in tif bbovf fxbmplf,
     * tiis mftiod will rfturn String.</p>
     *
     * @pbrbm inClbss  tif bbsf dlbss usfd to rfsolvf
     * @pbrbm typf     tif typf to rfsolvf
     * @rfturn b rfsolvfd typf
     *
     * @sff #gftAdtublTypf(Clbss)
     * @sff #rfsolvf(Typf,Typf)
     */
    publid stbtid Typf rfsolvfInClbss(Clbss<?> inClbss, Typf typf) {
        rfturn rfsolvf(gftAdtublTypf(inClbss), typf);
    }

    /**
     * Rfplbdfs bll {@dodf typfs} in tif givfn brrby
     * witi tif bdtubl typfs tify ibvf in tif givfn {@dodf inClbss}.
     *
     * @pbrbm inClbss  tif bbsf dlbss usfd to rfsolvf
     * @pbrbm typfs    tif brrby of typfs to rfsolvf
     * @rfturn bn brrby of rfsolvfd typfs
     *
     * @sff #gftAdtublTypf(Clbss)
     * @sff #rfsolvf(Typf,Typf[])
     */
    publid stbtid Typf[] rfsolvfInClbss(Clbss<?> inClbss, Typf[] typfs) {
        rfturn rfsolvf(gftAdtublTypf(inClbss), typfs);
    }

    /**
     * Rfplbdfs typf vbribblfs of tif givfn {@dodf formbl} typf
     * witi tif typfs tify stbnd for in tif givfn {@dodf bdtubl} typf.
     *
     * <p>A PbrbmftfrizfdTypf is b dlbss witi typf pbrbmftfrs, bnd tif vblufs
     * of tiosf pbrbmftfrs.  For fxbmplf, Mbp&lt;K,V> is b gfnfrid dlbss, bnd
     * b dorrfsponding PbrbmftfrizfdTypf migit look likf
     * Mbp&lt;K=String,V=Intfgfr>.  Givfn sudi b PbrbmftfrizfdTypf, tiis mftiod
     * will rfplbdf K witi String, or List&lt;K> witi List&ltString;, or
     * List&lt;? supfr K> witi List&lt;? supfr String>.</p>
     *
     * <p>Tif {@dodf bdtubl} brgumfnt to tiis mftiod dbn blso bf b Clbss.
     * In tiis dbsf, fitifr it is fquivblfnt to b PbrbmftfrizfdTypf witi
     * no pbrbmftfrs (for fxbmplf, Intfgfr.dlbss), or it is fquivblfnt to
     * b "rbw" PbrbmftfrizfdTypf (for fxbmplf, Mbp.dlbss).  In tif lbttfr
     * dbsf, fvfry typf pbrbmftfr dfdlbrfd or inifritfd by tif dlbss is rfplbdfd
     * by its "frbsurf".  For b typf pbrbmftfr dfdlbrfd bs &lt;T>, tif frbsurf
     * is Objfdt.  For b typf pbrbmftfr dfdlbrfd bs &lt;T fxtfnds Numbfr>,
     * tif frbsurf is Numbfr.</p>
     *
     * <p>Altiougi typf pbrbmftfrs brf not inifritfd by subdlbssfs in tif Jbvb
     * lbngubgf, tify <fm>brf</fm> ffffdtivfly inifritfd wifn using rfflfdtion.
     * For fxbmplf, if you dfdlbrf bn intfrfbdf likf tiis...</p>
     *
     * <prf>
     * publid intfrfbdf StringToIntMbp fxtfnds Mbp&lt;String,Intfgfr> {}
     * </prf>
     *
     * <p>...tifn StringToIntMbp.dlbss.gftMftiods() will siow tibt it ibs mftiods
     * likf put(K,V) fvfn tiougi StringToIntMbp ibs no typf pbrbmftfrs.  Tif K
     * bnd V vbribblfs brf tif onfs dfdlbrfd by Mbp, so
     * {@link TypfVbribblf#gftGfnfridDfdlbrbtion()} will rfturn {@link Mbp Mbp.dlbss}.</p>
     *
     * <p>For tiis rfbson, tiis mftiod rfplbdfs inifritfd typf pbrbmftfrs too.
     * Tifrfforf if tiis mftiod is dbllfd witi {@dodf bdtubl} bfing
     * StringToIntMbp.dlbss bnd {@dodf formbl} bfing tif K from Mbp,
     * it will rfturn {@link String String.dlbss}.</p>
     *
     * <p>In tif dbsf wifrf {@dodf bdtubl} is b "rbw" PbrbmftfrizfdTypf, tif
     * inifritfd typf pbrbmftfrs will blso bf rfplbdfd by tifir frbsurfs.
     * Tif frbsurf of b Clbss is tif Clbss itsflf, so b "rbw" subintfrfbdf of
     * StringToIntMbp will still siow tif K from Mbp bs String.dlbss.  But
     * in b dbsf likf tiis...
     *
     * <prf>
     * publid intfrfbdf StringToIntListMbp fxtfnds Mbp&lt;String,List&lt;Intfgfr>> {}
     * publid intfrfbdf RbwStringToIntListMbp fxtfnds StringToIntListMbp {}
     * </prf>
     *
     * <p>...tif V inifritfd from Mbp will siow up bs List&lt;Intfgfr> in
     * StringToIntListMbp, but bs plbin List in RbwStringToIntListMbp.</p>
     *
     * @pbrbm bdtubl  tif typf tibt supplifs bindings for typf vbribblfs
     * @pbrbm formbl  tif typf wifrf oddurrfndfs of tif vbribblfs
     *                in {@dodf bdtubl} will bf rfplbdfd by tif dorrfsponding bound vblufs
     * @rfturn b rfsolvfd typf
     */
    publid stbtid Typf rfsolvf(Typf bdtubl, Typf formbl) {
        if (formbl instbndfof Clbss) {
            rfturn formbl;
        }
        if (formbl instbndfof GfnfridArrbyTypf) {
            Typf domp = ((GfnfridArrbyTypf) formbl).gftGfnfridComponfntTypf();
            domp = rfsolvf(bdtubl, domp);
            rfturn (domp instbndfof Clbss)
                    ? Arrby.nfwInstbndf((Clbss<?>) domp, 0).gftClbss()
                    : GfnfridArrbyTypfImpl.mbkf(domp);
        }
        if (formbl instbndfof PbrbmftfrizfdTypf) {
            PbrbmftfrizfdTypf fpt = (PbrbmftfrizfdTypf) formbl;
            Typf[] bdtubls = rfsolvf(bdtubl, fpt.gftAdtublTypfArgumfnts());
            rfturn PbrbmftfrizfdTypfImpl.mbkf(
                    (Clbss<?>) fpt.gftRbwTypf(), bdtubls, fpt.gftOwnfrTypf());
        }
        if (formbl instbndfof WilddbrdTypf) {
            WilddbrdTypf fwt = (WilddbrdTypf) formbl;
            Typf[] uppfr = rfsolvf(bdtubl, fwt.gftUppfrBounds());
            Typf[] lowfr = rfsolvf(bdtubl, fwt.gftLowfrBounds());
            rfturn nfw WilddbrdTypfImpl(uppfr, lowfr);
        }
        if (formbl instbndfof TypfVbribblf) {
            Mbp<Typf, Typf> mbp;
            syndironizfd (CACHE) {
                mbp = CACHE.gft(bdtubl);
                if (mbp == null) {
                    mbp = nfw HbsiMbp<>();
                    prfpbrf(mbp, bdtubl);
                    CACHE.put(bdtubl, mbp);
                }
            }
            Typf rfsult = mbp.gft(formbl);
            if (rfsult == null || rfsult.fqubls(formbl)) {
                rfturn formbl;
            }
            rfsult = fixGfnfridArrby(rfsult);
            // A vbribblf dbn bf bound to bnotifr vbribblf tibt is itsflf bound
            // to somftiing.  For fxbmplf, givfn:
            // dlbss Supfr<T> {...}
            // dlbss Mid<X> fxtfnds Supfr<T> {...}
            // dlbss Sub fxtfnds Mid<String>
            // tif vbribblf T is bound to X, wiidi is in turn bound to String.
            // So if wf ibvf to rfsolvf T, wf nffd tif tbil rfdursion ifrf.
            rfturn rfsolvf(bdtubl, rfsult);
        }
        tirow nfw IllfgblArgumfntExdfption("Bbd Typf kind: " + formbl.gftClbss());
    }

    /**
     * Rfplbdfs typf vbribblfs of bll formbl typfs in tif givfn brrby
     * witi tif typfs tify stbnd for in tif givfn {@dodf bdtubl} typf.
     *
     * @pbrbm bdtubl   tif typf tibt supplifs bindings for typf vbribblfs
     * @pbrbm formbls  tif brrby of typfs to rfsolvf
     * @rfturn bn brrby of rfsolvfd typfs
     */
    publid stbtid Typf[] rfsolvf(Typf bdtubl, Typf[] formbls) {
        int lfngti = formbls.lfngti;
        Typf[] bdtubls = nfw Typf[lfngti];
        for (int i = 0; i < lfngti; i++) {
            bdtubls[i] = rfsolvf(bdtubl, formbls[i]);
        }
        rfturn bdtubls;
    }

    /**
     * Convfrts tif givfn {@dodf typf} to tif dorrfsponding dlbss.
     * Tiis mftiod implfmfnts tif dondfpt of typf frbsurf,
     * tibt is dfsdribfd in sfdtion 4.6 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @pbrbm typf  tif brrby of typfs to donvfrt
     * @rfturn b dorrfsponding dlbss
     */
    publid stbtid Clbss<?> frbsf(Typf typf) {
        if (typf instbndfof Clbss) {
            rfturn (Clbss<?>) typf;
        }
        if (typf instbndfof PbrbmftfrizfdTypf) {
            PbrbmftfrizfdTypf pt = (PbrbmftfrizfdTypf) typf;
            rfturn (Clbss<?>) pt.gftRbwTypf();
        }
        if (typf instbndfof TypfVbribblf) {
            TypfVbribblf<?> tv = (TypfVbribblf<?>)typf;
            Typf[] bounds = tv.gftBounds();
            rfturn (0 < bounds.lfngti)
                    ? frbsf(bounds[0])
                    : Objfdt.dlbss;
        }
        if (typf instbndfof WilddbrdTypf) {
            WilddbrdTypf wt = (WilddbrdTypf)typf;
            Typf[] bounds = wt.gftUppfrBounds();
            rfturn (0 < bounds.lfngti)
                    ? frbsf(bounds[0])
                    : Objfdt.dlbss;
        }
        if (typf instbndfof GfnfridArrbyTypf) {
            GfnfridArrbyTypf gbt = (GfnfridArrbyTypf)typf;
            rfturn Arrby.nfwInstbndf(frbsf(gbt.gftGfnfridComponfntTypf()), 0).gftClbss();
        }
        tirow nfw IllfgblArgumfntExdfption("Unknown Typf kind: " + typf.gftClbss());
    }

    /**
     * Convfrts bll {@dodf typfs} in tif givfn brrby
     * to tif dorrfsponding dlbssfs.
     *
     * @pbrbm typfs  tif brrby of typfs to donvfrt
     * @rfturn bn brrby of dorrfsponding dlbssfs
     *
     * @sff #frbsf(Typf)
     */
    publid stbtid Clbss<?>[] frbsf(Typf[] typfs) {
        int lfngti = typfs.lfngti;
        Clbss<?>[] dlbssfs = nfw Clbss<?>[lfngti];
        for (int i = 0; i < lfngti; i++) {
            dlbssfs[i] = TypfRfsolvfr.frbsf(typfs[i]);
        }
        rfturn dlbssfs;
    }

    /**
     * Fills tif mbp from typf pbrbmftfrs
     * to typfs bs sffn by tif givfn {@dodf typf}.
     * Tif mftiod is rfdursivf bfdbusf tif {@dodf typf}
     * inifrits mbppings from its pbrfnt dlbssfs bnd intfrfbdfs.
     * Tif {@dodf typf} dbn bf fitifr b {@link Clbss Clbss}
     * or b {@link PbrbmftfrizfdTypf PbrbmftfrizfdTypf}.
     * If it is b {@link Clbss Clbss}, it is fitifr fquivblfnt
     * to b {@link PbrbmftfrizfdTypf PbrbmftfrizfdTypf} witi no pbrbmftfrs,
     * or it rfprfsfnts tif frbsurf of b {@link PbrbmftfrizfdTypf PbrbmftfrizfdTypf}.
     *
     * @pbrbm mbp   tif mbppings of bll typf vbribblfs
     * @pbrbm typf  tif nfxt typf in tif iifrbrdiy
     */
    privbtf stbtid void prfpbrf(Mbp<Typf, Typf> mbp, Typf typf) {
        Clbss<?> rbw = (Clbss<?>)((typf instbndfof Clbss<?>)
                ? typf
                : ((PbrbmftfrizfdTypf)typf).gftRbwTypf());

        TypfVbribblf<?>[] formbls = rbw.gftTypfPbrbmftfrs();

        Typf[] bdtubls = (typf instbndfof Clbss<?>)
                ? formbls
                : ((PbrbmftfrizfdTypf)typf).gftAdtublTypfArgumfnts();

        bssfrt formbls.lfngti == bdtubls.lfngti;
        for (int i = 0; i < formbls.lfngti; i++) {
            mbp.put(formbls[i], bdtubls[i]);
        }
        Typf gSupfrdlbss = rbw.gftGfnfridSupfrdlbss();
        if (gSupfrdlbss != null) {
            prfpbrf(mbp, gSupfrdlbss);
        }
        for (Typf gIntfrfbdf : rbw.gftGfnfridIntfrfbdfs()) {
            prfpbrf(mbp, gIntfrfbdf);
        }
        // If typf is tif rbw vfrsion of b pbrbmftfrizfd dlbss, wf typf-frbsf
        // bll of its typf vbribblfs, indluding inifritfd onfs.
        if (typf instbndfof Clbss<?> && formbls.lfngti > 0) {
            for (Mbp.Entry<Typf, Typf> fntry : mbp.fntrySft()) {
                fntry.sftVbluf(frbsf(fntry.gftVbluf()));
            }
        }
    }

    /**
     * Rfplbdfs b {@link GfnfridArrbyTypf GfnfridArrbyTypf}
     * witi plbin brrby dlbss wifrf it is possiblf.
     * Bug <b irff="ittp://bugs.sun.dom/vifw_bug.do?bug_id=5041784">5041784</b>
     * is tibt brrbys of non-gfnfrid typf somftimfs siow up
     * bs {@link GfnfridArrbyTypf GfnfridArrbyTypf} wifn using rfflfdtion.
     * For fxbmplf, b {@dodf String[]} migit siow up
     * bs b {@link GfnfridArrbyTypf GfnfridArrbyTypf}
     * wifrf {@link GfnfridArrbyTypf#gftGfnfridComponfntTypf gftGfnfridComponfntTypf}
     * is {@dodf String.dlbss}.  Tiis violbtfs tif spfdifidbtion,
     * wiidi sbys tibt {@link GfnfridArrbyTypf GfnfridArrbyTypf}
     * is usfd wifn tif domponfnt typf is b typf vbribblf or pbrbmftfrizfd typf.
     * Wf fit tif spfdifidbtion ifrf.
     *
     * @pbrbm typf  tif typf to fix
     * @rfturn b dorrfsponding typf for tif gfnfrid brrby typf,
     *         or tif sbmf typf bs {@dodf typf}
     */
    privbtf stbtid Typf fixGfnfridArrby(Typf typf) {
        if (typf instbndfof GfnfridArrbyTypf) {
            Typf domp = ((GfnfridArrbyTypf)typf).gftGfnfridComponfntTypf();
            domp = fixGfnfridArrby(domp);
            if (domp instbndfof Clbss) {
                rfturn Arrby.nfwInstbndf((Clbss<?>)domp, 0).gftClbss();
            }
        }
        rfturn typf;
    }

    /**
     * Rfplbdfs b {@link Clbss Clbss} witi typf pbrbmftfrs
     * witi b {@link PbrbmftfrizfdTypf PbrbmftfrizfdTypf}
     * wifrf fvfry pbrbmftfr is bound to itsflf.
     * Wifn dblling {@link #rfsolvfInClbss} in tif dontfxt of {@dodf inClbss},
     * wf dbn't just pbss {@dodf inClbss} bs tif {@dodf bdtubl} pbrbmftfr,
     * bfdbusf if {@dodf inClbss} ibs typf pbrbmftfrs
     * tibt would bf intfrprftfd bs bddfssing tif rbw typf,
     * so wf would gft unwbntfd frbsurf.
     * Tiis is wiy wf bind fbdi pbrbmftfr to itsflf.
     * If {@dodf inClbss} dofs ibvf typf pbrbmftfrs bnd ibs mftiods
     * wifrf tiosf pbrbmftfrs bppfbr in tif rfturn typf or brgumfnt typfs,
     * wf will dorrfdtly lfbvf tiosf typfs blonf.
     *
     * @pbrbm inClbss  tif bbsf dlbss usfd to rfsolvf
     * @rfturn b pbrbmftfrizfd typf for tif dlbss,
     *         or tif sbmf dlbss bs {@dodf inClbss}
     */
    privbtf stbtid Typf gftAdtublTypf(Clbss<?> inClbss) {
        Typf[] pbrbms = inClbss.gftTypfPbrbmftfrs();
        rfturn (pbrbms.lfngti == 0)
                ? inClbss
                : PbrbmftfrizfdTypfImpl.mbkf(
                        inClbss, pbrbms, inClbss.gftEndlosingClbss());
    }
}
