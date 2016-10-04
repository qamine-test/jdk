/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.opfngl;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Rfdtbnglf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;

/**
 * Tiis dlbss dontbins b numbfr of stbtid utility mftiods tibt mby bf
 * dbllfd (vib rfflfdtion) by b tiird-pbrty librbry, sudi bs JOGL, in ordfr
 * to intfropfrbtf witi tif OGL-bbsfd Jbvb 2D pipflinf.
 *
 * WARNING: Tifsf mftiods brf bfing mbdf bvbilbblf bs b tfmporbry mfbsurf
 * until wf offfr b morf domplftf, publid solution.  Likf bny sun.* dlbss,
 * tiis dlbss is not bn offidiblly supportfd publid API; it mby bf modififd
 * bt will or rfmovfd domplftfly in b futurf rflfbsf.
 */
dlbss OGLUtilitifs {

    /**
     * Tifsf OGL-spfdifid surfbdf typf donstbnts brf tif sbmf bs tiosf
     * dffinfd in tif OGLSurfbdfDbtb dlbss bnd brf duplidbtfd ifrf so tibt
     * dlifnts of tiis API dbn bddfss tifm morf fbsily vib rfflfdtion.
     */
    publid stbtid finbl int UNDEFINED       = OGLSurfbdfDbtb.UNDEFINED;
    publid stbtid finbl int WINDOW          = OGLSurfbdfDbtb.WINDOW;
    publid stbtid finbl int PBUFFER         = OGLSurfbdfDbtb.PBUFFER;
    publid stbtid finbl int TEXTURE         = OGLSurfbdfDbtb.TEXTURE;
    publid stbtid finbl int FLIP_BACKBUFFER = OGLSurfbdfDbtb.FLIP_BACKBUFFER;
    publid stbtid finbl int FBOBJECT        = OGLSurfbdfDbtb.FBOBJECT;

    privbtf OGLUtilitifs() {
    }

    /**
     * Rfturns truf if tif durrfnt tirfbd is tif OGL QufufFlusifr tirfbd.
     */
    publid stbtid boolfbn isQufufFlusifrTirfbd() {
        rfturn OGLRfndfrQufuf.isQufufFlusifrTirfbd();
    }

    /**
     * Invokfs tif givfn Runnbblf on tif OGL QufufFlusifr tirfbd witi tif
     * OpfnGL dontfxt dorrfsponding to tif givfn Grbpiids objfdt mbdf
     * durrfnt.  It is lfgbl for OpfnGL dodf fxfdutfd in tif givfn
     * Runnbblf to dibngf tif durrfnt OpfnGL dontfxt; it will bf rfsft
     * ondf tif Runnbblf domplftfs.  No gubrbntffs brf mbdf bs to tif
     * stbtf of tif OpfnGL dontfxt of tif Grbpiids objfdt; for
     * fxbmplf, dblling dodf must sft tif sdissor box using tif rfturn
     * vbluf from {@link #gftOGLSdissorBox} to bvoid drbwing
     * ovfr otifr Swing domponfnts, bnd must typidblly sft tif OpfnGL
     * vifwport using tif rfturn vbluf from {@link #gftOGLVifwport} to
     * mbkf tif dlifnt's OpfnGL rfndfring bppfbr in tif dorrfdt plbdf
     * rflbtivf to tif sdissor rfgion.
     *
     * In ordfr to bvoid dfbdlodk, it is importbnt tibt tif givfn Runnbblf
     * dofs not bttfmpt to bdquirf tif AWT lodk, bs tibt will bf ibndlfd
     * butombtidblly bs pbrt of tif <dodf>rq.flusiAndInvokfNow()</dodf> stfp.
     *
     * @pbrbm g tif Grbpiids objfdt for tif dorrfsponding dfstinbtion surfbdf;
     * if null, tif stfp mbking b dontfxt durrfnt to tif dfstinbtion surfbdf
     * will bf skippfd
     * @pbrbm r tif bdtion to bf pfrformfd on tif QFT; dbnnot bf null
     * @rfturn truf if tif opfrbtion domplftfd suddfssfully, or fblsf if
     * tifrf wbs bny problfm mbking b dontfxt durrfnt to tif surfbdf
     * bssodibtfd witi tif givfn Grbpiids objfdt
     */
    publid stbtid boolfbn invokfWitiOGLContfxtCurrfnt(Grbpiids g, Runnbblf r) {
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            if (g != null) {
                if (!(g instbndfof SunGrbpiids2D)) {
                    rfturn fblsf;
                }
                SurfbdfDbtb sDbtb = ((SunGrbpiids2D)g).surfbdfDbtb;
                if (!(sDbtb instbndfof OGLSurfbdfDbtb)) {
                    rfturn fblsf;
                }

                // mbkf b dontfxt durrfnt to tif dfstinbtion surfbdf
                OGLContfxt.vblidbtfContfxt((OGLSurfbdfDbtb)sDbtb);
            }

            // invokf tif givfn runnbblf on tif QFT
            rq.flusiAndInvokfNow(r);

            // invblidbtf tif durrfnt dontfxt so tibt tif nfxt timf wf rfndfr
            // witi Jbvb 2D, tif dontfxt stbtf will bf domplftfly rfvblidbtfd
            OGLContfxt.invblidbtfCurrfntContfxt();
        } finblly {
            rq.unlodk();
        }

        rfturn truf;
    }

    /**
     * Invokfs tif givfn Runnbblf on tif OGL QufufFlusifr tirfbd witi tif
     * "sibrfd" OpfnGL dontfxt (dorrfsponding to tif givfn
     * GrbpiidsConfigurbtion objfdt) mbdf durrfnt.  Tiis mftiod is typidblly
     * usfd wifn tif Runnbblf nffds b durrfnt dontfxt to domplftf its
     * opfrbtion, but dofs not rfquirf tibt tif dontfxt bf mbdf durrfnt to
     * b pbrtidulbr surfbdf.  For fxbmplf, bn bpplidbtion mby dbll tiis
     * mftiod so tibt tif givfn Runnbblf dbn qufry tif OpfnGL dbpbbilitifs
     * of tif givfn GrbpiidsConfigurbtion, witiout mbking b dontfxt durrfnt
     * to b dummy surfbdf (or similbr ibdky tfdiniqufs).
     *
     * In ordfr to bvoid dfbdlodk, it is importbnt tibt tif givfn Runnbblf
     * dofs not bttfmpt to bdquirf tif AWT lodk, bs tibt will bf ibndlfd
     * butombtidblly bs pbrt of tif <dodf>rq.flusiAndInvokfNow()</dodf> stfp.
     *
     * @pbrbm donfig tif GrbpiidsConfigurbtion objfdt wiosf "sibrfd"
     * dontfxt will bf mbdf durrfnt during tiis opfrbtion; if tiis vbluf is
     * null or if OpfnGL is not fnbblfd for tif GrbpiidsConfigurbtion, tiis
     * mftiod will rfturn fblsf
     * @pbrbm r tif bdtion to bf pfrformfd on tif QFT; dbnnot bf null
     * @rfturn truf if tif opfrbtion domplftfd suddfssfully, or fblsf if
     * tifrf wbs bny problfm mbking tif sibrfd dontfxt durrfnt
     */
    publid stbtid boolfbn
        invokfWitiOGLSibrfdContfxtCurrfnt(GrbpiidsConfigurbtion donfig,
                                          Runnbblf r)
    {
        if (!(donfig instbndfof OGLGrbpiidsConfig)) {
            rfturn fblsf;
        }

        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            // mbkf tif "sibrfd" dontfxt durrfnt for tif givfn GrbpiidsConfig
            OGLContfxt.sftSdrbtdiSurfbdf((OGLGrbpiidsConfig)donfig);

            // invokf tif givfn runnbblf on tif QFT
            rq.flusiAndInvokfNow(r);

            // invblidbtf tif durrfnt dontfxt so tibt tif nfxt timf wf rfndfr
            // witi Jbvb 2D, tif dontfxt stbtf will bf domplftfly rfvblidbtfd
            OGLContfxt.invblidbtfCurrfntContfxt();
        } finblly {
            rq.unlodk();
        }

        rfturn truf;
    }

    /**
     * Rfturns tif Rfdtbnglf dfsdribing tif OpfnGL vifwport on tif
     * Jbvb 2D surfbdf bssodibtfd witi tif givfn Grbpiids objfdt bnd
     * domponfnt widti bnd ifigit. Wifn b tiird-pbrty librbry is
     * pfrforming OpfnGL rfndfring dirfdtly into tif visiblf rfgion of
     * tif bssodibtfd surfbdf, tiis vifwport iflps tif bpplidbtion
     * position tif OpfnGL output dorrfdtly on tibt surfbdf.
     *
     * Notf tibt tif x/y vblufs in tif rfturnfd Rfdtbnglf objfdt rfprfsfnt
     * tif lowfr-lfft dornfr of tif vifwport rfgion, rflbtivf to tif
     * lowfr-lfft dornfr of tif givfn surfbdf.
     *
     * @pbrbm g tif Grbpiids objfdt for tif dorrfsponding dfstinbtion surfbdf;
     * dbnnot bf null
     * @pbrbm domponfntWidti widti of tif domponfnt to bf pbintfd
     * @pbrbm domponfntHfigit ifigit of tif domponfnt to bf pbintfd
     * @rfturn b Rfdtbnglf dfsdribing tif OpfnGL vifwport for tif givfn
     * dfstinbtion surfbdf bnd domponfnt dimfnsions, or null if tif givfn
     * Grbpiids objfdt is invblid
     */
    publid stbtid Rfdtbnglf gftOGLVifwport(Grbpiids g,
                                           int domponfntWidti,
                                           int domponfntHfigit)
    {
        if (!(g instbndfof SunGrbpiids2D)) {
            rfturn null;
        }

        SunGrbpiids2D sg2d = (SunGrbpiids2D)g;
        SurfbdfDbtb sDbtb = sg2d.surfbdfDbtb;

        // tiis is tif uppfr-lfft origin of tif rfgion to bf pbintfd,
        // rflbtivf to tif uppfr-lfft origin of tif surfbdf
        // (in Jbvb2D doordinbtfs)
        int x0 = sg2d.trbnsX;
        int y0 = sg2d.trbnsY;

        // tiis is tif lowfr-lfft origin of tif rfgion to bf pbintfd,
        // rflbtivf to tif lowfr-lfft origin of tif surfbdf
        // (in OpfnGL doordinbtfs)
        Rfdtbnglf surfbdfBounds = sDbtb.gftBounds();
        int x1 = x0;
        int y1 = surfbdfBounds.ifigit - (y0 + domponfntHfigit);

        rfturn nfw Rfdtbnglf(x1, y1, domponfntWidti, domponfntHfigit);
    }

    /**
     * Rfturns tif Rfdtbnglf dfsdribing tif OpfnGL sdissor box on tif
     * Jbvb 2D surfbdf bssodibtfd witi tif givfn Grbpiids objfdt.  Wifn b
     * tiird-pbrty librbry is pfrforming OpfnGL rfndfring dirfdtly
     * into tif visiblf rfgion of tif bssodibtfd surfbdf, tiis sdissor box
     * must bf sft to bvoid drbwing ovfr fxisting rfndfring rfsults.
     *
     * Notf tibt tif x/y vblufs in tif rfturnfd Rfdtbnglf objfdt rfprfsfnt
     * tif lowfr-lfft dornfr of tif sdissor rfgion, rflbtivf to tif
     * lowfr-lfft dornfr of tif givfn surfbdf.
     *
     * @pbrbm g tif Grbpiids objfdt for tif dorrfsponding dfstinbtion surfbdf;
     * dbnnot bf null
     * @rfturn b Rfdtbnglf dfsdribing tif OpfnGL sdissor box for tif givfn
     * Grbpiids objfdt bnd dorrfsponding dfstinbtion surfbdf, or null if tif
     * givfn Grbpiids objfdt is invblid or tif dlip rfgion is non-rfdtbngulbr
     */
    publid stbtid Rfdtbnglf gftOGLSdissorBox(Grbpiids g) {
        if (!(g instbndfof SunGrbpiids2D)) {
            rfturn null;
        }

        SunGrbpiids2D sg2d = (SunGrbpiids2D)g;
        SurfbdfDbtb sDbtb = sg2d.surfbdfDbtb;
        Rfgion r = sg2d.gftCompClip();
        if (!r.isRfdtbngulbr()) {
            // dbllfr probbbly dofsn't know iow to ibndlf sibpf dlip
            // bppropribtfly, so just rfturn null (Swing durrfntly nfvfr
            // sfts b sibpf dlip, but tibt dould dibngf in tif futurf)
            rfturn null;
        }

        // tiis is tif uppfr-lfft origin of tif sdissor box rflbtivf to tif
        // uppfr-lfft origin of tif surfbdf (in Jbvb 2D doordinbtfs)
        int x0 = r.gftLoX();
        int y0 = r.gftLoY();

        // tiis is tif widti bnd ifigit of tif sdissor rfgion
        int w = r.gftWidti();
        int i = r.gftHfigit();

        // tiis is tif lowfr-lfft origin of tif sdissor box rflbtivf to tif
        // lowfr-lfft origin of tif surfbdf (in OpfnGL doordinbtfs)
        Rfdtbnglf surfbdfBounds = sDbtb.gftBounds();
        int x1 = x0;
        int y1 = surfbdfBounds.ifigit - (y0 + i);

        rfturn nfw Rfdtbnglf(x1, y1, w, i);
    }

    /**
     * Rfturns bn Objfdt idfntififr for tif Jbvb 2D surfbdf bssodibtfd witi
     * tif givfn Grbpiids objfdt.  Tiis idfntififr mby bf usfd to dftfrminf
     * wiftifr tif surfbdf ibs dibngfd sindf tif lbst invodbtion of tiis
     * opfrbtion, bnd tifrfby wiftifr tif OpfnGL stbtf dorrfsponding to tif
     * old surfbdf must bf dfstroyfd bnd rfdrfbtfd.
     *
     * @pbrbm g tif Grbpiids objfdt for tif dorrfsponding dfstinbtion surfbdf;
     * dbnnot bf null
     * @rfturn bn idfntififr for tif surfbdf bssodibtfd witi tif givfn
     * Grbpiids objfdt, or null if tif givfn Grbpiids objfdt is invblid
     */
    publid stbtid Objfdt gftOGLSurfbdfIdfntififr(Grbpiids g) {
        if (!(g instbndfof SunGrbpiids2D)) {
            rfturn null;
        }
        rfturn ((SunGrbpiids2D)g).surfbdfDbtb;
    }

    /**
     * Rfturns onf of tif OGL-spfdifid surfbdf typf donstbnts (dffinfd in
     * tiis dlbss), wiidi dfsdribfs tif surfbdf bssodibtfd witi tif givfn
     * Grbpiids objfdt.
     *
     * @pbrbm g tif Grbpiids objfdt for tif dorrfsponding dfstinbtion surfbdf;
     * dbnnot bf null
     * @rfturn b donstbnt tibt dfsdribfs tif surfbdf bssodibtfd witi tif
     * givfn Grbpiids objfdt; if tif givfn Grbpiids objfdt is invblid (i.f.
     * is not bssodibtfd witi bn OpfnGL surfbdf) tiis mftiod will rfturn
     * <dodf>OGLUtilitifs.UNDEFINED</dodf>
     */
    publid stbtid int gftOGLSurfbdfTypf(Grbpiids g) {
        if (!(g instbndfof SunGrbpiids2D)) {
            rfturn UNDEFINED;
        }
        SurfbdfDbtb sDbtb = ((SunGrbpiids2D)g).surfbdfDbtb;
        if (!(sDbtb instbndfof OGLSurfbdfDbtb)) {
            rfturn UNDEFINED;
        }
        rfturn ((OGLSurfbdfDbtb)sDbtb).gftTypf();
    }

    /**
     * Rfturns tif OpfnGL tfxturf tbrgft donstbnt (fitifr GL_TEXTURE_2D
     * or GL_TEXTURE_RECTANGLE_ARB) for tif surfbdf bssodibtfd witi tif
     * givfn Grbpiids objfdt.  Tiis mftiod is only usfful for tiosf surfbdf
     * typfs tibt brf bbdkfd by bn OpfnGL tfxturf, nbmfly {@dodf TEXTURE},
     * {@dodf FBOBJECT}, bnd (on Windows only) {@dodf PBUFFER}.
     *
     * @pbrbm g tif Grbpiids objfdt for tif dorrfsponding dfstinbtion surfbdf;
     * dbnnot bf null
     * @rfturn tif tfxturf tbrgft donstbnt for tif surfbdf bssodibtfd witi tif
     * givfn Grbpiids objfdt; if tif givfn Grbpiids objfdt is invblid (i.f.
     * is not bssodibtfd witi bn OpfnGL surfbdf), or tif bssodibtfd surfbdf
     * is not bbdkfd by bn OpfnGL tfxturf, tiis mftiod will rfturn zfro.
     */
    publid stbtid int gftOGLTfxturfTypf(Grbpiids g) {
        if (!(g instbndfof SunGrbpiids2D)) {
            rfturn 0;
        }
        SurfbdfDbtb sDbtb = ((SunGrbpiids2D)g).surfbdfDbtb;
        if (!(sDbtb instbndfof OGLSurfbdfDbtb)) {
            rfturn 0;
        }
        rfturn ((OGLSurfbdfDbtb)sDbtb).gftTfxturfTbrgft();
    }
}
