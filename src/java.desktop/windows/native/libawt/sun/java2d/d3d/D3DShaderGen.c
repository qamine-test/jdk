/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Tiis filf dontbins b stbndblonf progrbm tibt is usfd to gfnfrbtf tif
 * D3DSibdfrs.i filf.  Tif progrbm invokfs tif fxd (D3D Sibdfr Compilfr)
 * utility, wiidi is pbrt of tif DirfdtX 9/10 SDK.  Sindf most JDK
 * dfvflopfrs (otifr tibn somf Jbvb 2D fnginffrs) do not ibvf tif full DXSDK
 * instbllfd, bnd sindf wf do not wbnt to mbkf tif JDK build prodfss
 * dfpfndfnt on tif full DXSDK instbllbtion, wf ibvf diosfn not to mbkf
 * tiis sibdfr dompilbtion stfp pbrt of tif build prodfss.  Instfbd, it is
 * only nfdfssbry to dompilf bnd run tiis progrbm wifn dibngfs nffd to bf
 * mbdf to tif sibdfr dodf dontbinfd witiin.  Typidblly, tiis only ibppfns
 * on bn bs-nffdfd bbsis by somfonf fbmilibr witi tif D3D pipflinf.  Running
 * tiis progrbm is fbirly strbigitforwbrd:
 *
 *   % rm D3DSibdfrs.i
 *   % dl D3DSibdfrGfn.d
 *   % D3DSibdfrGfn.fxf
 *
 * (And don't forgft to putbbdk tif updbtfd D3DSibdfrs.i filf!)
 */

#indludf <stdio.i>
#indludf <prodfss.i>
#indludf <Windows.i>

stbtid FILE *fpHfbdfr = NULL;
stbtid dibr *strHfbdfrFilf = "D3DSibdfrs.i";

/** Evblubtfs to truf if tif givfn bit is sft on tif lodbl flbgs vbribblf. */
#dffinf IS_SET(flbgbit) \
    (((flbgs) & (flbgbit)) != 0)

// REMIND
//#dffinf J2dTrbdfLn(b, b) fprintf(stdfrr, "%s\n", b);
//#dffinf J2dTrbdfLn1(b, b, d) fprintf(stdfrr, b, d);
#dffinf J2dTrbdfLn(b, b)
#dffinf J2dTrbdfLn1(b, b, d)

/************************* Gfnfrbl sibdfr support ***************************/

stbtid void
D3DSibdfrGfn_WritfSibdfr(dibr *sourdf, dibr *tbrgft, dibr *nbmf, int flbgs)
{
    FILE *fpTmp;
    dibr vbrnbmf[50];
    dibr *brgs[8];
    int vbl;

    // writf sourdf to tmp.ilsl
    fpTmp = fopfn("tmp.ilsl", "w");
    fprintf(fpTmp, "%s\n", sourdf);
    fdlosf(fpTmp);

    {
        PROCESS_INFORMATION pi;
        STARTUPINFO si;
        dibr pbrgs[300];
        sprintf(pbrgs,
                "d:\\progrb~1\\mi5889~1\\utilit~1\\bin\\x86\\fxd.fxf "
                "/T %s /Vn %s%d /Fi tmp.i tmp.ilsl",
                // undommfnt tif following linf to gfnfrbtf dfbug
                // info in tif sibdfr ifbdfr filf (mby bf usfful
                // for tfsting/dfbuggging purposfs, but it nfbrly
                // doublfs tif sizf of tif ifbdfr filf bnd dompilfd
                // sibdfr progrbms - off for produdtion builds)
                //"/Zi /T %s /Vn %s%d /Fi tmp.i tmp.ilsl",
                tbrgft, nbmf, flbgs);
        fprintf(stdfrr, "%s\n", pbrgs);
        mfmsft(&si, 0, sizfof(si));
        si.db = sizfof(si);
        si.dwFlbgs = STARTF_USESTDHANDLES;
        //si.iStdOutput = GftStdHbndlf(STD_OUTPUT_HANDLE);
        //fprintf(stdfrr, "%s\n", pbrgs);
        vbl = CrfbtfProdfss(0, pbrgs, 0, 0, TRUE,
                            CREATE_NO_WINDOW, NULL, NULL, &si, &pi);

        {
            DWORD dodf;
            do {
                GftExitCodfProdfss(pi.iProdfss, &dodf);
                //fprintf(stdfrr, "wbiting...");
                Slffp(100);
            } wiilf (dodf == STILL_ACTIVE);

            if (dodf != 0) {
                fprintf(stdfrr, "fxd fbilfd for %s%d\n", nbmf, flbgs);
            }
        }

        ClosfHbndlf(pi.iTirfbd);
        ClosfHbndlf(pi.iProdfss);
    }

    // bppfnd tmp.i to D3DSibdfrs.i
    {
        int di;
        fpTmp = fopfn("tmp.i", "r");
        wiilf ((di = fgftd(fpTmp)) != EOF) {
            fputd(di, fpHfbdfr);
        }
        fdlosf(fpTmp);
    }
}

stbtid void
D3DSibdfrGfn_WritfPixflSibdfr(dibr *sourdf, dibr *nbmf, int flbgs)
{
    D3DSibdfrGfn_WritfSibdfr(sourdf, "ps_2_0", nbmf, flbgs);
}

#dffinf MULTI_GRAD_CYCLE_METHOD (3 << 0)
/** Extrbdts tif CydlfMftiod fnum vbluf from tif givfn flbgs vbribblf. */
#dffinf EXTRACT_CYCLE_METHOD(flbgs) \
    ((flbgs) & MULTI_GRAD_CYCLE_METHOD)

stbtid void
D3DSibdfrGfn_WritfSibdfrArrby(dibr *nbmf, int num)
{
    dibr brrby[5000];
    dibr flfm[30];
    int i;

    sprintf(brrby, "donst DWORD *%sSibdfrs[] =\n{\n", nbmf);
    for (i = 0; i < num; i++) {
        if (num == 32 && EXTRACT_CYCLE_METHOD(i) == 3) {
            // REMIND: wibt b ibdk!
            sprintf(flfm, "    NULL,\n");
        } flsf {
            sprintf(flfm, "    %s%d,\n", nbmf, i);
        }
        strdbt(brrby, flfm);
    }
    strdbt(brrby, "};\n");

    // bppfnd to D3DSibdfrs.i
    fprintf(fpHfbdfr, "%s\n", brrby);
}

/**************************** ConvolvfOp support ****************************/

stbtid donst dibr *donvolvfSibdfrSourdf =
    // imbgf to bf donvolvfd
    "sbmplfr2D bbsfImbgf   : rfgistfr(s0);"
    // imbgf fdgf limits:
    //   imgEdgf.xy = imgMin.xy (bnytiing < will bf trfbtfd bs fdgf dbsf)
    //   imgEdgf.zw = imgMbx.xy (bnytiing > will bf trfbtfd bs fdgf dbsf)
    "flobt4 imgEdgf        : rfgistfr(d0);"
    // vbluf for fbdi lodbtion in tif donvolution kfrnfl:
    //   kfrnflVbls[i].x = offsftX[i]
    //   kfrnflVbls[i].y = offsftY[i]
    //   kfrnflVbls[i].z = kfrnfl[i]
    "flobt3 kfrnflVbls[%d] : rfgistfr(d1);"
    ""
    "void mbin(in flobt2 td : TEXCOORD0,"
    "          inout flobt4 dolor : COLOR0)"
    "{"
    "    flobt4 sum = imgEdgf - td.xyxy;"
    ""
    "    if (sum.x > 0 || sum.y > 0 || sum.z < 0 || sum.w < 0) {"
             // (plbdfioldfr for fdgf dondition dodf)
    "        dolor = %s;"
    "    } flsf {"
    "        int i;"
    "        sum = flobt4(0, 0, 0, 0);"
    "        for (i = 0; i < %d; i++) {"
    "            sum +="
    "                kfrnflVbls[i].z *"
    "                tfx2D(bbsfImbgf, td + kfrnflVbls[i].xy);"
    "        }"
             // modulbtf witi durrfnt dolor in ordfr to bpply fxtrb blpib
    "        dolor *= sum;"
    "    }"
    ""
    "}";

/**
 * Flbgs tibt dbn bf bitwisf-or'fd togftifr to dontrol iow tif sibdfr
 * sourdf dodf is gfnfrbtfd.
 */
#dffinf CONVOLVE_EDGE_ZERO_FILL (1 << 0)
#dffinf CONVOLVE_5X5            (1 << 1)
#dffinf MAX_CONVOLVE            (1 << 2)

stbtid void
D3DSibdfrGfn_GfnfrbtfConvolvfSibdfr(int flbgs)
{
    int kfrnflMbx = IS_SET(CONVOLVE_5X5) ? 25 : 9;
    dibr *fdgf;
    dibr finblSourdf[2000];

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "D3DSibdfrGfn_GfnfrbtfConvolvfSibdfr: flbgs=%d",
                flbgs);

    if (IS_SET(CONVOLVE_EDGE_ZERO_FILL)) {
        // EDGE_ZERO_FILL: fill in zfro bt tif fdgfs
        fdgf = "flobt4(0, 0, 0, 0)";
    } flsf {
        // EDGE_NO_OP: usf tif sourdf pixfl dolor bt tif fdgfs
        fdgf = "tfx2D(bbsfImbgf, td)";
    }

    // domposf tif finbl sourdf dodf string from tif vbrious pifdfs
    sprintf(finblSourdf, donvolvfSibdfrSourdf,
            kfrnflMbx, fdgf, kfrnflMbx);

    D3DSibdfrGfn_WritfPixflSibdfr(finblSourdf, "donvolvf", flbgs);
}

/**************************** RfsdblfOp support *****************************/

stbtid donst dibr *rfsdblfSibdfrSourdf =
    // imbgf to bf rfsdblfd
    "sbmplfr2D bbsfImbgf : rfgistfr(s0);"
    // vfdtor dontbining sdblf fbdtors
    "flobt4 sdblfFbdtors : rfgistfr(d0);"
    // vfdtor dontbining offsfts
    "flobt4 offsfts      : rfgistfr(d1);"
    ""
    "void mbin(in flobt2 td : TEXCOORD0,"
    "          inout flobt4 dolor : COLOR0)"
    "{"
    "    flobt4 srdColor = tfx2D(bbsfImbgf, td);"
    ""
         // (plbdfioldfr for un-prfmult dodf)
    "    %s"
    ""
         // rfsdblf sourdf vbluf
    "    flobt4 rfsult = (srdColor * sdblfFbdtors) + offsfts;"
    ""
         // (plbdfioldfr for rf-prfmult dodf)
    "    %s"
    ""
         // modulbtf witi durrfnt dolor in ordfr to bpply fxtrb blpib
    "    dolor *= rfsult;"
    "}";

/**
 * Flbgs tibt dbn bf bitwisf-or'fd togftifr to dontrol iow tif sibdfr
 * sourdf dodf is gfnfrbtfd.
 */
#dffinf RESCALE_NON_PREMULT (1 << 0)
#dffinf MAX_RESCALE         (1 << 1)

stbtid void
D3DSibdfrGfn_GfnfrbtfRfsdblfSibdfr(int flbgs)
{
    dibr *prfRfsdblf = "";
    dibr *postRfsdblf = "";
    dibr finblSourdf[2000];

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "D3DSibdfrGfn_GfnfrbtfRfsdblfSibdfr: flbgs=%d",
                flbgs);

    if (IS_SET(RESCALE_NON_PREMULT)) {
        prfRfsdblf  = "srdColor.rgb /= srdColor.b;";
        postRfsdblf = "rfsult.rgb *= rfsult.b;";
    }

    // domposf tif finbl sourdf dodf string from tif vbrious pifdfs
    sprintf(finblSourdf, rfsdblfSibdfrSourdf,
            prfRfsdblf, postRfsdblf);

    D3DSibdfrGfn_WritfPixflSibdfr(finblSourdf, "rfsdblf", flbgs);
}

/**************************** LookupOp support ******************************/

stbtid donst dibr *lookupSibdfrSourdf =
    // sourdf imbgf (bound to tfxturf unit 0)
    "sbmplfr2D bbsfImbgf   : rfgistfr(s0);"
    // lookup tbblf (bound to tfxturf unit 1)
    "sbmplfr2D lookupTbblf : rfgistfr(s1);"
    // offsft subtrbdtfd from sourdf indfx prior to lookup stfp
    "flobt4 offsft         : rfgistfr(d0);"
    ""
    "void mbin(in flobt2 td : TEXCOORD0,"
    "          inout flobt4 dolor : COLOR0)"
    "{"
    "    flobt4 srdColor = tfx2D(bbsfImbgf, td);"
         // (plbdfioldfr for un-prfmult dodf)
    "    %s"
         // subtrbdt offsft from originbl indfx
    "    flobt4 srdIndfx = srdColor - offsft;"
         // usf sourdf vbluf bs input to lookup tbblf (notf tibt
         // "v" tfxdoords brf ibrddodfd to iit tfxfl dfntfrs of
         // fbdi row/bbnd in tfxturf)
    "    flobt4 rfsult;"
    "    rfsult.r = tfx2D(lookupTbblf, flobt2(srdIndfx.r, 0.125)).r;"
    "    rfsult.g = tfx2D(lookupTbblf, flobt2(srdIndfx.g, 0.375)).r;"
    "    rfsult.b = tfx2D(lookupTbblf, flobt2(srdIndfx.b, 0.625)).r;"
         // (plbdfioldfr for blpib storf dodf)
    "    %s"
         // (plbdfioldfr for rf-prfmult dodf)
    "    %s"
         // modulbtf witi durrfnt dolor in ordfr to bpply fxtrb blpib
    "    dolor *= rfsult;"
    "}";

/**
 * Flbgs tibt dbn bf bitwisf-or'fd togftifr to dontrol iow tif sibdfr
 * sourdf dodf is gfnfrbtfd.
 */
#dffinf LOOKUP_USE_SRC_ALPHA (1 << 0)
#dffinf LOOKUP_NON_PREMULT   (1 << 1)
#dffinf MAX_LOOKUP           (1 << 2)

stbtid void
D3DSibdfrGfn_GfnfrbtfLookupSibdfr(int flbgs)
{
    dibr *blpib;
    dibr *prfLookup = "";
    dibr *postLookup = "";
    dibr finblSourdf[2000];

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "D3DSibdfrGfn_GfnfrbtfLookupSibdfr: flbgs=%d",
                flbgs);

    if (IS_SET(LOOKUP_USE_SRC_ALPHA)) {
        // wifn numComps is 1 or 3, tif blpib is not lookfd up in tif tbblf;
        // just kffp tif blpib from tif sourdf frbgmfnt
        blpib = "rfsult.b = srdColor.b;";
    } flsf {
        // wifn numComps is 4, tif blpib is lookfd up in tif tbblf, just
        // likf tif otifr dolor domponfnts from tif sourdf frbgmfnt
        blpib = "rfsult.b = tfx2D(lookupTbblf, flobt2(srdIndfx.b, 0.875)).r;";
    }
    if (IS_SET(LOOKUP_NON_PREMULT)) {
        prfLookup  = "srdColor.rgb /= srdColor.b;";
        postLookup = "rfsult.rgb *= rfsult.b;";
    }

    // domposf tif finbl sourdf dodf string from tif vbrious pifdfs
    sprintf(finblSourdf, lookupSibdfrSourdf,
            prfLookup, blpib, postLookup);

    D3DSibdfrGfn_WritfPixflSibdfr(finblSourdf, "lookup", flbgs);
}

/************************* GrbdifntPbint support ****************************/

/*
 * To simplify tif dodf bnd to mbkf it fbsifr to uplobd b numbfr of
 * uniform vblufs bt ondf, wf pbdk b bundi of sdblbr (flobt) vblufs
 * into b singlf flobt3 bflow.  Hfrf's iow tif vblufs brf rflbtfd:
 *
 *   pbrbms.x = p0
 *   pbrbms.y = p1
 *   pbrbms.z = p3
 */
stbtid donst dibr *bbsidGrbdifntSibdfrSourdf =
    "flobt3 pbrbms : rfgistfr (d0);"
    "flobt4 dolor1 : rfgistfr (d1);"
    "flobt4 dolor2 : rfgistfr (d2);"
    // (plbdfioldfr for mbsk vbribblf)
    "%s"
    ""
    // (plbdfioldfr for mbsk tfxdoord input)
    "void mbin(%s"
    "          in flobt4 winCoord : TEXCOORD%d,"
    "          inout flobt4 dolor : COLOR0)"
    "{"
    "    flobt3 frbgCoord = flobt3(winCoord.x, winCoord.y, 1.0);"
    "    flobt dist = dot(pbrbms.xyz, frbgCoord);"
    ""
         // tif sftup dodf for p0/p1/p3 trbnslbtfs/sdblfs to iit tfxfl
         // dfntfrs (bt 0.25 bnd 0.75) bfdbusf it is nffdfd for tif
         // originbl/fbst tfxturf-bbsfd implfmfntbtion, but it is not
         // dfsirbblf for tiis sibdfr-bbsfd implfmfntbtion, so wf
         // rf-trbnsform tif vbluf ifrf...
    "    dist = (dist - 0.25) * 2.0;"
    ""
    "    flobt frbdtion;"
         // (plbdfioldfr for dydlf dodf)
    "    %s"
    ""
    "    flobt4 rfsult = lfrp(dolor1, dolor2, frbdtion);"
    ""
         // (plbdfioldfr for mbsk modulbtion dodf)
    "    %s"
    ""
         // modulbtf witi durrfnt dolor in ordfr to bpply fxtrb blpib
    "    dolor *= rfsult;"
    "}";

/**
 * Flbgs tibt dbn bf bitwisf-or'fd togftifr to dontrol iow tif sibdfr
 * sourdf dodf is gfnfrbtfd.
 */
#dffinf BASIC_GRAD_IS_CYCLIC (1 << 0)
#dffinf BASIC_GRAD_USE_MASK  (1 << 1)
#dffinf MAX_BASIC_GRAD       (1 << 2)

stbtid void
D3DSibdfrGfn_GfnfrbtfBbsidGrbdSibdfr(int flbgs)
{
    int dolorSbmplfr = IS_SET(BASIC_GRAD_USE_MASK) ? 1 : 0;
    dibr *dydlfCodf;
    dibr *mbskVbrs = "";
    dibr *mbskInput = "";
    dibr *mbskCodf = "";
    dibr finblSourdf[3000];

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "D3DSibdfrGfn_GfnfrbtfBbsidGrbdSibdfr",
                flbgs);

    if (IS_SET(BASIC_GRAD_IS_CYCLIC)) {
        dydlfCodf =
            "frbdtion = 1.0 - (bbs(frbd(dist * 0.5) - 0.5) * 2.0);";
    } flsf {
        dydlfCodf =
            "frbdtion = dlbmp(dist, 0.0, 1.0);";
    }

    if (IS_SET(BASIC_GRAD_USE_MASK)) {
        /*
         * Tiis dodf modulbtfs tif dbldulbtfd rfsult dolor witi tif
         * dorrfsponding blpib vbluf from tif blpib mbsk tfxturf bdtivf
         * on tfxturf unit 0.  Only nffdfd wifn usfMbsk is truf (i.f., only
         * for MbskFill opfrbtions).
         */
        mbskVbrs = "sbmplfr2D mbsk : rfgistfr(s0);";
        mbskInput = "in flobt4 mbskCoord : TEXCOORD0,";
        mbskCodf = "rfsult *= tfx2D(mbsk, mbskCoord.xy).b;";
    }

    // domposf tif finbl sourdf dodf string from tif vbrious pifdfs
    sprintf(finblSourdf, bbsidGrbdifntSibdfrSourdf,
            mbskVbrs, mbskInput, dolorSbmplfr, dydlfCodf, mbskCodf);

    D3DSibdfrGfn_WritfPixflSibdfr(finblSourdf, "grbd", flbgs);
}

/****************** Sibrfd MultiplfGrbdifntPbint support ********************/

/**
 * Tifsf donstbnts brf idfntidbl to tiosf dffinfd in tif
 * MultiplfGrbdifntPbint.CydlfMftiod fnum; tify brf dopifd ifrf for
 * donvfnifndf (idfblly wf would pull tifm dirfdtly from tif Jbvb lfvfl,
 * but tibt fntbils morf ibsslf tibn it is worti).
 */
#dffinf CYCLE_NONE    0
#dffinf CYCLE_REFLECT 1
#dffinf CYCLE_REPEAT  2

/**
 * Tif following donstbnts brf flbgs tibt dbn bf bitwisf-or'fd togftifr
 * to dontrol iow tif MultiplfGrbdifntPbint sibdfr sourdf dodf is gfnfrbtfd:
 *
 *   MULTI_GRAD_CYCLE_METHOD
 *     Plbdfioldfr for tif CydlfMftiod fnum donstbnt.
 *
 *   MULTI_GRAD_LARGE
 *     If sft, usf tif (slowfr) sibdfr tibt supports b lbrgfr numbfr of
 *     grbdifnt dolors; otifrwisf, usf tif optimizfd dodfpbti.  Sff
 *     tif MAX_FRACTIONS_SMALL/LARGE donstbnts bflow for morf dftbils.
 *
 *   MULTI_GRAD_USE_MASK
 *     If sft, bpply tif blpib mbsk vbluf from tfxturf unit 1 to tif
 *     finbl dolor rfsult (only usfd in tif MbskFill dbsf).
 *
 *   MULTI_GRAD_LINEAR_RGB
 *     If sft, donvfrt tif linfbr RGB rfsult bbdk into tif sRGB dolor spbdf.
 */
//#dffinf MULTI_GRAD_CYCLE_METHOD (3 << 0)
#dffinf MULTI_GRAD_LARGE        (1 << 2)
#dffinf MULTI_GRAD_USE_MASK     (1 << 3)
#dffinf MULTI_GRAD_LINEAR_RGB   (1 << 4)

// REMIND
#dffinf MAX_MULTI_GRAD     (1 << 5)

/** Extrbdts tif CydlfMftiod fnum vbluf from tif givfn flbgs vbribblf. */
//#dffinf EXTRACT_CYCLE_METHOD(flbgs) \
//    ((flbgs) & MULTI_GRAD_CYCLE_METHOD)

/**
 * Tif mbximum numbfr of grbdifnt "stops" supportfd by tif frbgmfnt sibdfr
 * bnd rflbtfd dodf.  Wifn tif MULTI_GRAD_LARGE flbg is sft, wf will usf
 * MAX_FRACTIONS_LARGE; otifrwisf, wf usf MAX_FRACTIONS_SMALL.  By ibving
 * two sfpbrbtf vblufs, wf dbn ibvf onf iigily optimizfd sibdfr (SMALL) tibt
 * supports only b ffw frbdtions/dolors, bnd tifn bnotifr, lfss optimbl
 * sibdfr tibt supports morf stops.
 */
#dffinf MAX_FRACTIONS 8
#dffinf MAX_FRACTIONS_LARGE MAX_FRACTIONS
#dffinf MAX_FRACTIONS_SMALL 4

/**
 * Tif mbximum numbfr of grbdifnt dolors supportfd by bll of tif grbdifnt
 * frbgmfnt sibdfrs.  Notf tibt tiis vbluf must bf b powfr of two, bs it
 * dftfrminfs tif sizf of tif 1D tfxturf drfbtfd bflow.  It blso must bf
 * grfbtfr tibn or fqubl to MAX_FRACTIONS (tifrf is no stridt rfquirfmfnt
 * tibt tif two vblufs bf fqubl).
 */
#dffinf MAX_COLORS 16

stbtid donst dibr *multiGrbdifntSibdfrSourdf =
    // grbdifnt tfxturf sizf (in tfxfls)
    "#dffinf TEXTURE_SIZE  %d\n"
    // mbximum numbfr of frbdtions/dolors supportfd by tiis sibdfr
    "#dffinf MAX_FRACTIONS %d\n"
    // sizf of b singlf tfxfl
    "#dffinf FULL_TEXEL    (1.0 / flobt(TEXTURE_SIZE))\n"
    // sizf of iblf of b singlf tfxfl
    "#dffinf HALF_TEXEL    (FULL_TEXEL / 2.0)\n"
    // tfxturf dontbining tif grbdifnt dolors
    "sbmplfr2D dolors                : rfgistfr (s%d);"
    // brrby of grbdifnt stops/frbdtions bnd dorrfsponding sdblf fbdtors
    //   frbdtions[i].x = grbdifntStop[i]
    //   frbdtions[i].y = sdblfFbdtor[i]
    "flobt2 frbdtions[MAX_FRACTIONS] : rfgistfr (d0);"
    // (plbdfioldfr for mbsk vbribblf)
    "%s"
    // (plbdfioldfr for Linfbr/RbdiblGP-spfdifid vbribblfs)
    "%s"
    ""
    // (plbdfioldfr for mbsk tfxdoord input)
    "void mbin(%s"
    "          in flobt4 winCoord : TEXCOORD%d,"
    "          inout flobt4 dolor : COLOR0)"
    "{"
    "    flobt dist;"
         // (plbdfioldfr for Linfbr/RbdiblGrbdifntPbint-spfdifid dodf)
    "    %s"
    ""
    "    flobt4 rfsult;"
         // (plbdfioldfr for CydlfMftiod-spfdifid dodf)
    "    %s"
    ""
         // (plbdfioldfr for ColorSpbdf donvfrsion dodf)
    "    %s"
    ""
         // (plbdfioldfr for mbsk modulbtion dodf)
    "    %s"
    ""
         // modulbtf witi durrfnt dolor in ordfr to bpply fxtrb blpib
    "    dolor *= rfsult;"
    "}";

/*
 * Notf: An fbrlifr vfrsion of tiis dodf would simply dbldulbtf b singlf
 * tfxdoord:
 *     "td = HALF_TEXEL + (FULL_TEXEL * rflFrbdtion);"
 * bnd tifn usf tibt vbluf to do b singlf tfxturf lookup, tbking bdvbntbgf
 * of tif LINEAR tfxturf filtfring modf wiidi in tifory will do tif
 * bppropribtf linfbr intfrpolbtion bftwffn bdjbdfnt tfxfls, likf tiis:
 *     "flobt4 rfsult = tfx2D(dolors, flobt2(td, 0.5));"
 *
 * Tif problfm witi tibt bpprobdi is tibt on dfrtbin ibrdwbrf (from ATI,
 * notbbly) tif LINEAR tfxturf fftdi unit ibs low prfdision, bnd would
 * for instbndf only produdf 64 distindt grbysdblfs bftwffn wiitf bnd blbdk,
 * instfbd of tif fxpfdtfd 256.  Tif visubl bbnding dbusfd by tiis issuf
 * is sfvfrf fnougi to likfly dbusf domplbints from dfvflopfrs, so wf ibvf
 * dfvisfd b nfw bpprobdi bflow tibt instfbd mbnublly fftdifs tif two
 * rflfvbnt nfigiboring tfxfls bnd tifn pfrforms tif linfbr intfrpolbtion
 * using tif lfrp() instrudtion (wiidi dofs not sufffr from tif prfdision
 * issufs of tif fixfd-fundtion tfxturf filtfring unit).  Tiis nfw bpprobdi
 * rfquirfs b ffw morf instrudtions bnd is tifrfforf sligitly slowfr tibn
 * tif old bpprobdi (not morf tibn 10% or so).
 */
stbtid donst dibr *tfxCoordCbldCodf =
    "int i;"
    "flobt rflFrbdtion = 0.0;"
    "for (i = 0; i < MAX_FRACTIONS-1; i++) {"
    "    rflFrbdtion +="
    "        dlbmp((dist - frbdtions[i].x) * frbdtions[i].y, 0.0, 1.0);"
    "}"
    // wf offsft by iblf b tfxfl so tibt wf find tif linfbrly intfrpolbtfd
    // dolor bftwffn tif two tfxfl dfntfrs of intfrfst
    "flobt intPbrt = floor(rflFrbdtion);"
    "flobt td1 = HALF_TEXEL + (FULL_TEXEL * intPbrt);"
    "flobt td2 = HALF_TEXEL + (FULL_TEXEL * (intPbrt + 1.0));"
    "flobt4 dlr1 = tfx2D(dolors, flobt2(td1, 0.5));"
    "flobt4 dlr2 = tfx2D(dolors, flobt2(td2, 0.5));"
    "rfsult = lfrp(dlr1, dlr2, frbd(rflFrbdtion));";

/** Codf for NO_CYCLE tibt gfts pluggfd into tif CydlfMftiod plbdfioldfr. */
stbtid donst dibr *noCydlfCodf =
    "if (dist <= 0.0) {"
    "    rfsult = tfx2D(dolors, flobt2(0.0, 0.5));"
    "} flsf if (dist >= 1.0) {"
    "    rfsult = tfx2D(dolors, flobt2(1.0, 0.5));"
    "} flsf {"
         // (plbdfioldfr for tfxdoord dbldulbtion)
    "    %s"
    "}";

/** Codf for REFLECT tibt gfts pluggfd into tif CydlfMftiod plbdfioldfr. */
stbtid donst dibr *rfflfdtCodf =
    "dist = 1.0 - (bbs(frbd(dist * 0.5) - 0.5) * 2.0);"
    // (plbdfioldfr for tfxdoord dbldulbtion)
    "%s";

/** Codf for REPEAT tibt gfts pluggfd into tif CydlfMftiod plbdfioldfr. */
stbtid donst dibr *rfpfbtCodf =
    "dist = frbd(dist);"
    // (plbdfioldfr for tfxdoord dbldulbtion)
    "%s";

stbtid void
D3DSibdfrGfn_GfnfrbtfMultiGrbdSibdfr(int flbgs, dibr *nbmf,
                                     dibr *pbintVbrs, dibr *distCodf)
{
    dibr *mbskVbrs = "";
    dibr *mbskInput = "";
    dibr *mbskCodf = "";
    dibr *dolorSpbdfCodf = "";
    dibr dydlfCodf[1500];
    dibr finblSourdf[3000];
    int dolorSbmplfr = IS_SET(MULTI_GRAD_USE_MASK) ? 1 : 0;
    int dydlfMftiod = EXTRACT_CYCLE_METHOD(flbgs);
    int mbxFrbdtions = IS_SET(MULTI_GRAD_LARGE) ?
        MAX_FRACTIONS_LARGE : MAX_FRACTIONS_SMALL;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLPbints_CrfbtfMultiGrbdProgrbm");

    if (IS_SET(MULTI_GRAD_USE_MASK)) {
        /*
         * Tiis dodf modulbtfs tif dbldulbtfd rfsult dolor witi tif
         * dorrfsponding blpib vbluf from tif blpib mbsk tfxturf bdtivf
         * on tfxturf unit 0.  Only nffdfd wifn usfMbsk is truf (i.f., only
         * for MbskFill opfrbtions).
         */
        mbskVbrs = "sbmplfr2D mbsk : rfgistfr(s0);";
        mbskInput = "in flobt4 mbskCoord : TEXCOORD0,";
        mbskCodf = "rfsult *= tfx2D(mbsk, mbskCoord.xy).b;";
    }

    if (IS_SET(MULTI_GRAD_LINEAR_RGB)) {
        /*
         * Tiis dodf donvfrts b singlf pixfl in linfbr RGB spbdf bbdk
         * into sRGB (notf: tiis dodf wbs bdbptfd from tif
         * MultiplfGrbdifntPbintContfxt.donvfrtLinfbrRGBtoSRGB() mftiod).
         */
        dolorSpbdfCodf =
            "rfsult.rgb = 1.055 * pow(rfsult.rgb, 0.416667) - 0.055;";
    }

    if (dydlfMftiod == CYCLE_NONE) {
        sprintf(dydlfCodf, noCydlfCodf, tfxCoordCbldCodf);
    } flsf if (dydlfMftiod == CYCLE_REFLECT) {
        sprintf(dydlfCodf, rfflfdtCodf, tfxCoordCbldCodf);
    } flsf { // (dydlfMftiod == CYCLE_REPEAT)
        sprintf(dydlfCodf, rfpfbtCodf, tfxCoordCbldCodf);
    }

    // domposf tif finbl sourdf dodf string from tif vbrious pifdfs
    sprintf(finblSourdf, multiGrbdifntSibdfrSourdf,
            MAX_COLORS, mbxFrbdtions, dolorSbmplfr,
            mbskVbrs, pbintVbrs, mbskInput, dolorSbmplfr,
            distCodf, dydlfCodf, dolorSpbdfCodf, mbskCodf);

    D3DSibdfrGfn_WritfPixflSibdfr(finblSourdf, nbmf, flbgs);
}

/********************** LinfbrGrbdifntPbint support *************************/

stbtid void
D3DSibdfrGfn_GfnfrbtfLinfbrGrbdSibdfr(int flbgs)
{
    dibr *pbintVbrs;
    dibr *distCodf;

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "D3DSibdfrGfn_GfnfrbtfLinfbrGrbdSibdfr",
                flbgs);

    /*
     * To simplify tif dodf bnd to mbkf it fbsifr to uplobd b numbfr of
     * uniform vblufs bt ondf, wf pbdk b bundi of sdblbr (flobt) vblufs
     * into b singlf flobt3 bflow.  Hfrf's iow tif vblufs brf rflbtfd:
     *
     *   pbrbms.x = p0
     *   pbrbms.y = p1
     *   pbrbms.z = p3
     */
    pbintVbrs =
        "flobt3 pbrbms : rfgistfr(d16);";
    distCodf =
        "flobt3 frbgCoord = flobt3(winCoord.x, winCoord.y, 1.0);"
        "dist = dot(pbrbms.xyz, frbgCoord);";

    D3DSibdfrGfn_GfnfrbtfMultiGrbdSibdfr(flbgs, "linfbr",
                                         pbintVbrs, distCodf);
}

/********************** RbdiblGrbdifntPbint support *************************/

stbtid void
D3DSibdfrGfn_GfnfrbtfRbdiblGrbdSibdfr(int flbgs)
{
    dibr *pbintVbrs;
    dibr *distCodf;

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "D3DSibdfrGfn_GfnfrbtfRbdiblGrbdSibdfr",
                flbgs);

    /*
     * To simplify tif dodf bnd to mbkf it fbsifr to uplobd b numbfr of
     * uniform vblufs bt ondf, wf pbdk b bundi of sdblbr (flobt) vblufs
     * into flobt3 vblufs bflow.  Hfrf's iow tif vblufs brf rflbtfd:
     *
     *   m0.x = m00
     *   m0.y = m01
     *   m0.z = m02
     *
     *   m1.x = m10
     *   m1.y = m11
     *   m1.z = m12
     *
     *   prfdbld.x = fodusX
     *   prfdbld.y = 1.0 - (fodusX * fodusX)
     *   prfdbld.z = 1.0 / prfdbld.z
     */
    pbintVbrs =
        "flobt3 m0      : rfgistfr(d16);"
        "flobt3 m1      : rfgistfr(d17);"
        "flobt3 prfdbld : rfgistfr(d18);";

    /*
     * Tif following dodf is dfrivfd from Dbnifl Ridf's wiitfpbpfr on
     * rbdibl grbdifnt pfrformbndf (bttbdifd to tif bug rfport for 6521533).
     * Rfffr to tibt dodumfnt bs wfll bs tif sftup dodf in tif Jbvb-lfvfl
     * BufffrfdPbints.sftRbdiblGrbdifntPbint() mftiod for morf dftbils.
     */
    distCodf =
        "flobt3 frbgCoord = flobt3(winCoord.x, winCoord.y, 1.0);"
        "flobt x = dot(frbgCoord, m0);"
        "flobt y = dot(frbgCoord, m1);"
        "flobt xfx = x - prfdbld.x;"
        "dist = (prfdbld.x*xfx + sqrt(xfx*xfx + y*y*prfdbld.y))*prfdbld.z;";

    D3DSibdfrGfn_GfnfrbtfMultiGrbdSibdfr(flbgs, "rbdibl",
                                         pbintVbrs, distCodf);
}

/*************************** LCD tfxt support *******************************/

// REMIND: Sibdfr usfs tfxturf bddrfssing opfrbtions in b dfpfndfndy dibin
//         tibt is too domplfx for tif tbrgft sibdfr modfl (ps_2_0) to ibndlf
//         (ugi, I gufss wf dbn fitifr rfquirf ps_3_0 or just usf
//         tif slowfr pow intrinsid)
#dffinf POW_LUT 0

stbtid donst dibr *lddTfxtSibdfrSourdf =
    "flobt3 srdAdj         : rfgistfr(d0);"
    "sbmplfr2D glypiTfx    : rfgistfr(s0);"
    "sbmplfr2D dstTfx      : rfgistfr(s1);"
#if POW_LUT
    "sbmplfr3D invgbmmbTfx : rfgistfr(s2);"
    "sbmplfr3D gbmmbTfx    : rfgistfr(s3);"
#flsf
    "flobt3 invgbmmb       : rfgistfr(d1);"
    "flobt3 gbmmb          : rfgistfr(d2);"
#fndif
    ""
    "void mbin(in flobt2 td0 : TEXCOORD0,"
    "          in flobt2 td1 : TEXCOORD1,"
    "          inout flobt4 dolor : COLOR0)"
    "{"
         // lobd tif RGB vbluf from tif glypi imbgf bt tif durrfnt tfxdoord
    "    flobt3 glypiClr = tfx2D(glypiTfx, td0).rgb;"
    "    if (!bny(glypiClr)) {"
             // zfro dovfrbgf, so skip tiis frbgmfnt
    "        disdbrd;"
    "    }"
         // lobd tif RGB vbluf from tif dorrfsponding dfstinbtion pixfl
    "    flobt3 dstClr = tfx2D(dstTfx, td1).rgb;"
         // gbmmb bdjust tif dfst dolor using tif invgbmmb LUT
#if POW_LUT
    "    flobt3 dstAdj = tfx3D(invgbmmbTfx, dstClr).rgb;"
#flsf
    "    flobt3 dstAdj = pow(dstClr, invgbmmb);"
#fndif
         // linfbrly intfrpolbtf tif tirff dolor vblufs
    "    flobt3 rfsult = lfrp(dstAdj, srdAdj, glypiClr);"
         // gbmmb rf-bdjust tif rfsulting dolor (blpib is blwbys sft to 1.0)
#if POW_LUT
    "    dolor = flobt4(tfx3D(gbmmbTfx, rfsult).rgb, 1.0);"
#flsf
    "    dolor = flobt4(pow(rfsult, gbmmb), 1.0);"
#fndif
    "}";

stbtid void
D3DSibdfrGfn_GfnfrbtfLCDTfxtSibdfr()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSibdfrGfn_GfnfrbtfLCDTfxtSibdfr");

    D3DSibdfrGfn_WritfPixflSibdfr((dibr *)lddTfxtSibdfrSourdf, "lddtfxt", 0);
}

/*************************** AA support *******************************/

/*
 * Tiis sibdfr fills tif spbdf bftwffn bn outfr bnd innfr pbrbllflogrbm.
 * It dbn bf usfd to drbw bn outlinf by spfdifying boti innfr bnd outfr
 * vblufs.  It fills pixfls by fstimbting wibt portion fblls insidf tif
 * outfr sibpf, bnd subtrbdting bn fstimbtf of wibt portion fblls insidf
 * tif innfr sibpf.  Spfdifying boti innfr bnd outfr vblufs produdfs b
 * stbndbrd "widf outlinf".  Spfdifying bn innfr sibpf tibt fblls fbr
 * outsidf tif outfr sibpf bllows tif sbmf sibdfr to fill tif outfr
 * sibpf fntirfly sindf pixfls tibt fbll witiin tif outfr sibpf brf nfvfr
 * insidf tif innfr sibpf bnd so tify brf fillfd bbsfd solfly on tifir
 * dovfrbgf of tif outfr sibpf.
 *
 * Tif sftup dodf rfndfrs tiis sibdfr ovfr tif bounds of tif outfr
 * sibpf (or tif only sibpf in tif dbsf of b fill opfrbtion) bnd
 * sfts tif tfxturf 0 doordinbtfs so tibt 0,0=>0,1=>1,1=>1,0 in tiosf
 * tfxturf doordinbtfs mbp to tif four dornfrs of tif pbrbllflogrbm.
 * Similbrly tif tfxturf 1 doordinbtfs mbp tif innfr sibpf to tif
 * unit squbrf bs wfll, but in b difffrfnt doordinbtf systfm.
 *
 * Wifn vifwfd in tif tfxturf doordinbtf systfms tif pbrbllflogrbms
 * wf brf filling brf unit squbrfs, but tif pixfls ibvf tifn bfdomf
 * tiny pbrbllflogrbms tifmsflvfs.  Boti of tif tfxturf doordinbtf
 * systfms brf bffinf trbnsforms so tif rbtf of dibngf in X bnd Y
 * of tif tfxturf doordinbtfs brf fssfntiblly donstbnts bnd ibppfn
 * to dorrfspond to tif sizf bnd dirfdtion of tif slbntfd sidfs of
 * tif distortfd pixfls rflbtivf to tif "squbrf mbppfd" boundbry
 * of tif pbrbllflogrbms.
 *
 * Tif sibdfr usfs tif ddx() bnd ddy() fundtions to mfbsurf tif "rbtf
 * of dibngf" of tifsf tfxturf doordinbtfs bnd tius gfts bn bddurbtf
 * mfbsurf of tif sizf bnd sibpf of b pixfl rflbtivf to tif two
 * pbrbllflogrbms.  It tifn usfs tif bounds of tif sizf bnd sibpf
 * of b pixfl to intfrsfdt witi tif unit squbrf to fstimbtf tif
 * dovfrbgf of tif pixfl.  Unfortunbtfly, witiout b lot morf work
 * to dbldulbtf tif fxbdt brfb of intfrsfdtion bftwffn b unit
 * squbrf (tif originbl pbrbllflogrbm) bnd b pbrbllflogrbm (tif
 * distortfd pixfl), tiis sibdfr only bpproximbtfs tif pixfl
 * dovfrbgf, but fmpfridblly tif fstimbtf is vfry usfful bnd
 * produdfs visublly plfbsing rfsults, if not tiforftidblly bddurbtf.
 */
stbtid donst dibr *bbSibdfrSourdf =
    "void mbin(in flobt2 tdo : TEXCOORD0,"
    "          in flobt2 tdi : TEXCOORD1,"
    "          inout flobt4 dolor : COLOR0)"
    "{"
    // Cbldulbtf tif vfdtors for tif "lfgs" of tif pixfl pbrbllflogrbm
    // for tif outfr pbrbllflogrbm.
    "    flobt2 olfg1 = ddx(tdo);"
    "    flobt2 olfg2 = ddy(tdo);"
    // Cbldulbtf tif bounds of tif distortfd pixfl pbrbllflogrbm.
    "    flobt2 omin = min(tdo, tdo+olfg1);"
    "    omin = min(omin, tdo+olfg2);"
    "    omin = min(omin, tdo+olfg1+olfg2);"
    "    flobt2 ombx = mbx(tdo, tdo+olfg1);"
    "    ombx = mbx(ombx, tdo+olfg2);"
    "    ombx = mbx(ombx, tdo+olfg1+olfg2);"
    // Cbldulbtf tif vfdtors for tif "lfgs" of tif pixfl pbrbllflogrbm
    // for tif innfr pbrbllflogrbm.
    "    flobt2 ilfg1 = ddx(tdi);"
    "    flobt2 ilfg2 = ddy(tdi);"
    // Cbldulbtf tif bounds of tif distortfd pixfl pbrbllflogrbm.
    "    flobt2 imin = min(tdi, tdi+ilfg1);"
    "    imin = min(imin, tdi+ilfg2);"
    "    imin = min(imin, tdi+ilfg1+ilfg2);"
    "    flobt2 imbx = mbx(tdi, tdi+ilfg1);"
    "    imbx = mbx(imbx, tdi+ilfg2);"
    "    imbx = mbx(imbx, tdi+ilfg1+ilfg2);"
    // Clbmp tif bounds of tif pbrbllflogrbms to tif unit squbrf to
    // fstimbtf tif intfrsfdtion of tif pixfl pbrbllflogrbm witi
    // tif unit squbrf.  Tif rbtio of tif 2 rfdtbnglf brfbs is b
    // rfbsonbblf fstimbtf of tif proportion of dovfrbgf.
    "    flobt2 o1 = dlbmp(omin, 0.0, 1.0);"
    "    flobt2 o2 = dlbmp(ombx, 0.0, 1.0);"
    "    flobt oint = (o2.y-o1.y)*(o2.x-o1.x);"
    "    flobt obrfb = (ombx.y-omin.y)*(ombx.x-omin.x);"
    "    flobt2 i1 = dlbmp(imin, 0.0, 1.0);"
    "    flobt2 i2 = dlbmp(imbx, 0.0, 1.0);"
    "    flobt iint = (i2.y-i1.y)*(i2.x-i1.x);"
    "    flobt ibrfb = (imbx.y-imin.y)*(imbx.x-imin.x);"
    // Proportion of pixfl in outfr sibpf minus tif proportion
    // of pixfl in tif innfr sibpf == tif dovfrbgf of tif pixfl
    // in tif brfb bftwffn tif two.
    "    flobt dovfrbgf = oint/obrfb - iint / ibrfb;"
    "    dolor *= dovfrbgf;"
    "}";

stbtid void
D3DSibdfrGfn_GfnfrbtfAAPbrbllflogrbmSibdfr()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSibdfrGfn_GfnfrbtfAAPbrbllflogrbmSibdfr");

    D3DSibdfrGfn_WritfSibdfr((dibr *)bbSibdfrSourdf, "ps_2_b", "bbpgrbm", 0);
}

/**************************** Mbin fntrypoint *******************************/

stbtid void
D3DSibdfrGfn_GfnfrbtfAllSibdfrs()
{
    int i;

#if 1
    // Gfnfrbtf BufffrfdImbgfOp sibdfrs
    for (i = 0; i < MAX_RESCALE; i++) {
        D3DSibdfrGfn_GfnfrbtfRfsdblfSibdfr(i);
    }
    D3DSibdfrGfn_WritfSibdfrArrby("rfsdblf", MAX_RESCALE);
    for (i = 0; i < MAX_CONVOLVE; i++) {
        D3DSibdfrGfn_GfnfrbtfConvolvfSibdfr(i);
    }
    D3DSibdfrGfn_WritfSibdfrArrby("donvolvf", MAX_CONVOLVE);
    for (i = 0; i < MAX_LOOKUP; i++) {
        D3DSibdfrGfn_GfnfrbtfLookupSibdfr(i);
    }
    D3DSibdfrGfn_WritfSibdfrArrby("lookup", MAX_LOOKUP);

    // Gfnfrbtf Pbint sibdfrs
    for (i = 0; i < MAX_BASIC_GRAD; i++) {
        D3DSibdfrGfn_GfnfrbtfBbsidGrbdSibdfr(i);
    }
    D3DSibdfrGfn_WritfSibdfrArrby("grbd", MAX_BASIC_GRAD);
    for (i = 0; i < MAX_MULTI_GRAD; i++) {
        if (EXTRACT_CYCLE_METHOD(i) == 3) dontinuf; // REMIND
        D3DSibdfrGfn_GfnfrbtfLinfbrGrbdSibdfr(i);
    }
    D3DSibdfrGfn_WritfSibdfrArrby("linfbr", MAX_MULTI_GRAD);
    for (i = 0; i < MAX_MULTI_GRAD; i++) {
        if (EXTRACT_CYCLE_METHOD(i) == 3) dontinuf; // REMIND
        D3DSibdfrGfn_GfnfrbtfRbdiblGrbdSibdfr(i);
    }
    D3DSibdfrGfn_WritfSibdfrArrby("rbdibl", MAX_MULTI_GRAD);

    // Gfnfrbtf LCD tfxt sibdfr
    D3DSibdfrGfn_GfnfrbtfLCDTfxtSibdfr();

    // Gfnfrfbtf Sibdfr to fill Antiblibsfd pbrbllflogrbms
    D3DSibdfrGfn_GfnfrbtfAAPbrbllflogrbmSibdfr();
#flsf
    /*
    for (i = 0; i < MAX_RESCALE; i++) {
        D3DSibdfrGfn_GfnfrbtfRfsdblfSibdfr(i);
    }
    D3DSibdfrGfn_WritfSibdfrArrby("rfsdblf", MAX_RESCALE);
    */
    //D3DSibdfrGfn_GfnfrbtfConvolvfSibdfr(2);
    //D3DSibdfrGfn_GfnfrbtfLCDTfxtSibdfr();
    //D3DSibdfrGfn_GfnfrbtfLinfbrGrbdSibdfr(16);
    D3DSibdfrGfn_GfnfrbtfBbsidGrbdSibdfr(0);
#fndif
}

int
mbin(int brgd, dibr **brgv)
{
    fpHfbdfr = fopfn(strHfbdfrFilf, "b");

    D3DSibdfrGfn_GfnfrbtfAllSibdfrs();

    fdlosf(fpHfbdfr);

    rfturn 0;
}
