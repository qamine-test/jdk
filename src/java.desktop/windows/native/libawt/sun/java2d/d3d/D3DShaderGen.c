/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/**
 * This file contbins b stbndblone progrbm thbt is used to generbte the
 * D3DShbders.h file.  The progrbm invokes the fxc (D3D Shbder Compiler)
 * utility, which is pbrt of the DirectX 9/10 SDK.  Since most JDK
 * developers (other thbn some Jbvb 2D engineers) do not hbve the full DXSDK
 * instblled, bnd since we do not wbnt to mbke the JDK build process
 * dependent on the full DXSDK instbllbtion, we hbve chosen not to mbke
 * this shbder compilbtion step pbrt of the build process.  Instebd, it is
 * only necessbry to compile bnd run this progrbm when chbnges need to be
 * mbde to the shbder code contbined within.  Typicblly, this only hbppens
 * on bn bs-needed bbsis by someone fbmilibr with the D3D pipeline.  Running
 * this progrbm is fbirly strbightforwbrd:
 *
 *   % rm D3DShbders.h
 *   % cl D3DShbderGen.c
 *   % D3DShbderGen.exe
 *
 * (And don't forget to putbbck the updbted D3DShbders.h file!)
 */

#include <stdio.h>
#include <process.h>
#include <Windows.h>

stbtic FILE *fpHebder = NULL;
stbtic chbr *strHebderFile = "D3DShbders.h";

/** Evblubtes to true if the given bit is set on the locbl flbgs vbribble. */
#define IS_SET(flbgbit) \
    (((flbgs) & (flbgbit)) != 0)

// REMIND
//#define J2dTrbceLn(b, b) fprintf(stderr, "%s\n", b);
//#define J2dTrbceLn1(b, b, c) fprintf(stderr, b, c);
#define J2dTrbceLn(b, b)
#define J2dTrbceLn1(b, b, c)

/************************* Generbl shbder support ***************************/

stbtic void
D3DShbderGen_WriteShbder(chbr *source, chbr *tbrget, chbr *nbme, int flbgs)
{
    FILE *fpTmp;
    chbr vbrnbme[50];
    chbr *brgs[8];
    int vbl;

    // write source to tmp.hlsl
    fpTmp = fopen("tmp.hlsl", "w");
    fprintf(fpTmp, "%s\n", source);
    fclose(fpTmp);

    {
        PROCESS_INFORMATION pi;
        STARTUPINFO si;
        chbr pbrgs[300];
        sprintf(pbrgs,
                "c:\\progrb~1\\mi5889~1\\utilit~1\\bin\\x86\\fxc.exe "
                "/T %s /Vn %s%d /Fh tmp.h tmp.hlsl",
                // uncomment the following line to generbte debug
                // info in the shbder hebder file (mby be useful
                // for testing/debuggging purposes, but it nebrly
                // doubles the size of the hebder file bnd compiled
                // shbder progrbms - off for production builds)
                //"/Zi /T %s /Vn %s%d /Fh tmp.h tmp.hlsl",
                tbrget, nbme, flbgs);
        fprintf(stderr, "%s\n", pbrgs);
        memset(&si, 0, sizeof(si));
        si.cb = sizeof(si);
        si.dwFlbgs = STARTF_USESTDHANDLES;
        //si.hStdOutput = GetStdHbndle(STD_OUTPUT_HANDLE);
        //fprintf(stderr, "%s\n", pbrgs);
        vbl = CrebteProcess(0, pbrgs, 0, 0, TRUE,
                            CREATE_NO_WINDOW, NULL, NULL, &si, &pi);

        {
            DWORD code;
            do {
                GetExitCodeProcess(pi.hProcess, &code);
                //fprintf(stderr, "wbiting...");
                Sleep(100);
            } while (code == STILL_ACTIVE);

            if (code != 0) {
                fprintf(stderr, "fxc fbiled for %s%d\n", nbme, flbgs);
            }
        }

        CloseHbndle(pi.hThrebd);
        CloseHbndle(pi.hProcess);
    }

    // bppend tmp.h to D3DShbders.h
    {
        int ch;
        fpTmp = fopen("tmp.h", "r");
        while ((ch = fgetc(fpTmp)) != EOF) {
            fputc(ch, fpHebder);
        }
        fclose(fpTmp);
    }
}

stbtic void
D3DShbderGen_WritePixelShbder(chbr *source, chbr *nbme, int flbgs)
{
    D3DShbderGen_WriteShbder(source, "ps_2_0", nbme, flbgs);
}

#define MULTI_GRAD_CYCLE_METHOD (3 << 0)
/** Extrbcts the CycleMethod enum vblue from the given flbgs vbribble. */
#define EXTRACT_CYCLE_METHOD(flbgs) \
    ((flbgs) & MULTI_GRAD_CYCLE_METHOD)

stbtic void
D3DShbderGen_WriteShbderArrby(chbr *nbme, int num)
{
    chbr brrby[5000];
    chbr elem[30];
    int i;

    sprintf(brrby, "const DWORD *%sShbders[] =\n{\n", nbme);
    for (i = 0; i < num; i++) {
        if (num == 32 && EXTRACT_CYCLE_METHOD(i) == 3) {
            // REMIND: whbt b hbck!
            sprintf(elem, "    NULL,\n");
        } else {
            sprintf(elem, "    %s%d,\n", nbme, i);
        }
        strcbt(brrby, elem);
    }
    strcbt(brrby, "};\n");

    // bppend to D3DShbders.h
    fprintf(fpHebder, "%s\n", brrby);
}

/**************************** ConvolveOp support ****************************/

stbtic const chbr *convolveShbderSource =
    // imbge to be convolved
    "sbmpler2D bbseImbge   : register(s0);"
    // imbge edge limits:
    //   imgEdge.xy = imgMin.xy (bnything < will be trebted bs edge cbse)
    //   imgEdge.zw = imgMbx.xy (bnything > will be trebted bs edge cbse)
    "flobt4 imgEdge        : register(c0);"
    // vblue for ebch locbtion in the convolution kernel:
    //   kernelVbls[i].x = offsetX[i]
    //   kernelVbls[i].y = offsetY[i]
    //   kernelVbls[i].z = kernel[i]
    "flobt3 kernelVbls[%d] : register(c1);"
    ""
    "void mbin(in flobt2 tc : TEXCOORD0,"
    "          inout flobt4 color : COLOR0)"
    "{"
    "    flobt4 sum = imgEdge - tc.xyxy;"
    ""
    "    if (sum.x > 0 || sum.y > 0 || sum.z < 0 || sum.w < 0) {"
             // (plbceholder for edge condition code)
    "        color = %s;"
    "    } else {"
    "        int i;"
    "        sum = flobt4(0, 0, 0, 0);"
    "        for (i = 0; i < %d; i++) {"
    "            sum +="
    "                kernelVbls[i].z *"
    "                tex2D(bbseImbge, tc + kernelVbls[i].xy);"
    "        }"
             // modulbte with current color in order to bpply extrb blphb
    "        color *= sum;"
    "    }"
    ""
    "}";

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define CONVOLVE_EDGE_ZERO_FILL (1 << 0)
#define CONVOLVE_5X5            (1 << 1)
#define MAX_CONVOLVE            (1 << 2)

stbtic void
D3DShbderGen_GenerbteConvolveShbder(int flbgs)
{
    int kernelMbx = IS_SET(CONVOLVE_5X5) ? 25 : 9;
    chbr *edge;
    chbr finblSource[2000];

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DShbderGen_GenerbteConvolveShbder: flbgs=%d",
                flbgs);

    if (IS_SET(CONVOLVE_EDGE_ZERO_FILL)) {
        // EDGE_ZERO_FILL: fill in zero bt the edges
        edge = "flobt4(0, 0, 0, 0)";
    } else {
        // EDGE_NO_OP: use the source pixel color bt the edges
        edge = "tex2D(bbseImbge, tc)";
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, convolveShbderSource,
            kernelMbx, edge, kernelMbx);

    D3DShbderGen_WritePixelShbder(finblSource, "convolve", flbgs);
}

/**************************** RescbleOp support *****************************/

stbtic const chbr *rescbleShbderSource =
    // imbge to be rescbled
    "sbmpler2D bbseImbge : register(s0);"
    // vector contbining scble fbctors
    "flobt4 scbleFbctors : register(c0);"
    // vector contbining offsets
    "flobt4 offsets      : register(c1);"
    ""
    "void mbin(in flobt2 tc : TEXCOORD0,"
    "          inout flobt4 color : COLOR0)"
    "{"
    "    flobt4 srcColor = tex2D(bbseImbge, tc);"
    ""
         // (plbceholder for un-premult code)
    "    %s"
    ""
         // rescble source vblue
    "    flobt4 result = (srcColor * scbleFbctors) + offsets;"
    ""
         // (plbceholder for re-premult code)
    "    %s"
    ""
         // modulbte with current color in order to bpply extrb blphb
    "    color *= result;"
    "}";

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define RESCALE_NON_PREMULT (1 << 0)
#define MAX_RESCALE         (1 << 1)

stbtic void
D3DShbderGen_GenerbteRescbleShbder(int flbgs)
{
    chbr *preRescble = "";
    chbr *postRescble = "";
    chbr finblSource[2000];

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DShbderGen_GenerbteRescbleShbder: flbgs=%d",
                flbgs);

    if (IS_SET(RESCALE_NON_PREMULT)) {
        preRescble  = "srcColor.rgb /= srcColor.b;";
        postRescble = "result.rgb *= result.b;";
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, rescbleShbderSource,
            preRescble, postRescble);

    D3DShbderGen_WritePixelShbder(finblSource, "rescble", flbgs);
}

/**************************** LookupOp support ******************************/

stbtic const chbr *lookupShbderSource =
    // source imbge (bound to texture unit 0)
    "sbmpler2D bbseImbge   : register(s0);"
    // lookup tbble (bound to texture unit 1)
    "sbmpler2D lookupTbble : register(s1);"
    // offset subtrbcted from source index prior to lookup step
    "flobt4 offset         : register(c0);"
    ""
    "void mbin(in flobt2 tc : TEXCOORD0,"
    "          inout flobt4 color : COLOR0)"
    "{"
    "    flobt4 srcColor = tex2D(bbseImbge, tc);"
         // (plbceholder for un-premult code)
    "    %s"
         // subtrbct offset from originbl index
    "    flobt4 srcIndex = srcColor - offset;"
         // use source vblue bs input to lookup tbble (note thbt
         // "v" texcoords bre hbrdcoded to hit texel centers of
         // ebch row/bbnd in texture)
    "    flobt4 result;"
    "    result.r = tex2D(lookupTbble, flobt2(srcIndex.r, 0.125)).r;"
    "    result.g = tex2D(lookupTbble, flobt2(srcIndex.g, 0.375)).r;"
    "    result.b = tex2D(lookupTbble, flobt2(srcIndex.b, 0.625)).r;"
         // (plbceholder for blphb store code)
    "    %s"
         // (plbceholder for re-premult code)
    "    %s"
         // modulbte with current color in order to bpply extrb blphb
    "    color *= result;"
    "}";

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define LOOKUP_USE_SRC_ALPHA (1 << 0)
#define LOOKUP_NON_PREMULT   (1 << 1)
#define MAX_LOOKUP           (1 << 2)

stbtic void
D3DShbderGen_GenerbteLookupShbder(int flbgs)
{
    chbr *blphb;
    chbr *preLookup = "";
    chbr *postLookup = "";
    chbr finblSource[2000];

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DShbderGen_GenerbteLookupShbder: flbgs=%d",
                flbgs);

    if (IS_SET(LOOKUP_USE_SRC_ALPHA)) {
        // when numComps is 1 or 3, the blphb is not looked up in the tbble;
        // just keep the blphb from the source frbgment
        blphb = "result.b = srcColor.b;";
    } else {
        // when numComps is 4, the blphb is looked up in the tbble, just
        // like the other color components from the source frbgment
        blphb = "result.b = tex2D(lookupTbble, flobt2(srcIndex.b, 0.875)).r;";
    }
    if (IS_SET(LOOKUP_NON_PREMULT)) {
        preLookup  = "srcColor.rgb /= srcColor.b;";
        postLookup = "result.rgb *= result.b;";
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, lookupShbderSource,
            preLookup, blphb, postLookup);

    D3DShbderGen_WritePixelShbder(finblSource, "lookup", flbgs);
}

/************************* GrbdientPbint support ****************************/

/*
 * To simplify the code bnd to mbke it ebsier to uplobd b number of
 * uniform vblues bt once, we pbck b bunch of scblbr (flobt) vblues
 * into b single flobt3 below.  Here's how the vblues bre relbted:
 *
 *   pbrbms.x = p0
 *   pbrbms.y = p1
 *   pbrbms.z = p3
 */
stbtic const chbr *bbsicGrbdientShbderSource =
    "flobt3 pbrbms : register (c0);"
    "flobt4 color1 : register (c1);"
    "flobt4 color2 : register (c2);"
    // (plbceholder for mbsk vbribble)
    "%s"
    ""
    // (plbceholder for mbsk texcoord input)
    "void mbin(%s"
    "          in flobt4 winCoord : TEXCOORD%d,"
    "          inout flobt4 color : COLOR0)"
    "{"
    "    flobt3 frbgCoord = flobt3(winCoord.x, winCoord.y, 1.0);"
    "    flobt dist = dot(pbrbms.xyz, frbgCoord);"
    ""
         // the setup code for p0/p1/p3 trbnslbtes/scbles to hit texel
         // centers (bt 0.25 bnd 0.75) becbuse it is needed for the
         // originbl/fbst texture-bbsed implementbtion, but it is not
         // desirbble for this shbder-bbsed implementbtion, so we
         // re-trbnsform the vblue here...
    "    dist = (dist - 0.25) * 2.0;"
    ""
    "    flobt frbction;"
         // (plbceholder for cycle code)
    "    %s"
    ""
    "    flobt4 result = lerp(color1, color2, frbction);"
    ""
         // (plbceholder for mbsk modulbtion code)
    "    %s"
    ""
         // modulbte with current color in order to bpply extrb blphb
    "    color *= result;"
    "}";

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define BASIC_GRAD_IS_CYCLIC (1 << 0)
#define BASIC_GRAD_USE_MASK  (1 << 1)
#define MAX_BASIC_GRAD       (1 << 2)

stbtic void
D3DShbderGen_GenerbteBbsicGrbdShbder(int flbgs)
{
    int colorSbmpler = IS_SET(BASIC_GRAD_USE_MASK) ? 1 : 0;
    chbr *cycleCode;
    chbr *mbskVbrs = "";
    chbr *mbskInput = "";
    chbr *mbskCode = "";
    chbr finblSource[3000];

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DShbderGen_GenerbteBbsicGrbdShbder",
                flbgs);

    if (IS_SET(BASIC_GRAD_IS_CYCLIC)) {
        cycleCode =
            "frbction = 1.0 - (bbs(frbc(dist * 0.5) - 0.5) * 2.0);";
    } else {
        cycleCode =
            "frbction = clbmp(dist, 0.0, 1.0);";
    }

    if (IS_SET(BASIC_GRAD_USE_MASK)) {
        /*
         * This code modulbtes the cblculbted result color with the
         * corresponding blphb vblue from the blphb mbsk texture bctive
         * on texture unit 0.  Only needed when useMbsk is true (i.e., only
         * for MbskFill operbtions).
         */
        mbskVbrs = "sbmpler2D mbsk : register(s0);";
        mbskInput = "in flobt4 mbskCoord : TEXCOORD0,";
        mbskCode = "result *= tex2D(mbsk, mbskCoord.xy).b;";
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, bbsicGrbdientShbderSource,
            mbskVbrs, mbskInput, colorSbmpler, cycleCode, mbskCode);

    D3DShbderGen_WritePixelShbder(finblSource, "grbd", flbgs);
}

/****************** Shbred MultipleGrbdientPbint support ********************/

/**
 * These constbnts bre identicbl to those defined in the
 * MultipleGrbdientPbint.CycleMethod enum; they bre copied here for
 * convenience (ideblly we would pull them directly from the Jbvb level,
 * but thbt entbils more hbssle thbn it is worth).
 */
#define CYCLE_NONE    0
#define CYCLE_REFLECT 1
#define CYCLE_REPEAT  2

/**
 * The following constbnts bre flbgs thbt cbn be bitwise-or'ed together
 * to control how the MultipleGrbdientPbint shbder source code is generbted:
 *
 *   MULTI_GRAD_CYCLE_METHOD
 *     Plbceholder for the CycleMethod enum constbnt.
 *
 *   MULTI_GRAD_LARGE
 *     If set, use the (slower) shbder thbt supports b lbrger number of
 *     grbdient colors; otherwise, use the optimized codepbth.  See
 *     the MAX_FRACTIONS_SMALL/LARGE constbnts below for more detbils.
 *
 *   MULTI_GRAD_USE_MASK
 *     If set, bpply the blphb mbsk vblue from texture unit 1 to the
 *     finbl color result (only used in the MbskFill cbse).
 *
 *   MULTI_GRAD_LINEAR_RGB
 *     If set, convert the linebr RGB result bbck into the sRGB color spbce.
 */
//#define MULTI_GRAD_CYCLE_METHOD (3 << 0)
#define MULTI_GRAD_LARGE        (1 << 2)
#define MULTI_GRAD_USE_MASK     (1 << 3)
#define MULTI_GRAD_LINEAR_RGB   (1 << 4)

// REMIND
#define MAX_MULTI_GRAD     (1 << 5)

/** Extrbcts the CycleMethod enum vblue from the given flbgs vbribble. */
//#define EXTRACT_CYCLE_METHOD(flbgs) \
//    ((flbgs) & MULTI_GRAD_CYCLE_METHOD)

/**
 * The mbximum number of grbdient "stops" supported by the frbgment shbder
 * bnd relbted code.  When the MULTI_GRAD_LARGE flbg is set, we will use
 * MAX_FRACTIONS_LARGE; otherwise, we use MAX_FRACTIONS_SMALL.  By hbving
 * two sepbrbte vblues, we cbn hbve one highly optimized shbder (SMALL) thbt
 * supports only b few frbctions/colors, bnd then bnother, less optimbl
 * shbder thbt supports more stops.
 */
#define MAX_FRACTIONS 8
#define MAX_FRACTIONS_LARGE MAX_FRACTIONS
#define MAX_FRACTIONS_SMALL 4

/**
 * The mbximum number of grbdient colors supported by bll of the grbdient
 * frbgment shbders.  Note thbt this vblue must be b power of two, bs it
 * determines the size of the 1D texture crebted below.  It blso must be
 * grebter thbn or equbl to MAX_FRACTIONS (there is no strict requirement
 * thbt the two vblues be equbl).
 */
#define MAX_COLORS 16

stbtic const chbr *multiGrbdientShbderSource =
    // grbdient texture size (in texels)
    "#define TEXTURE_SIZE  %d\n"
    // mbximum number of frbctions/colors supported by this shbder
    "#define MAX_FRACTIONS %d\n"
    // size of b single texel
    "#define FULL_TEXEL    (1.0 / flobt(TEXTURE_SIZE))\n"
    // size of hblf of b single texel
    "#define HALF_TEXEL    (FULL_TEXEL / 2.0)\n"
    // texture contbining the grbdient colors
    "sbmpler2D colors                : register (s%d);"
    // brrby of grbdient stops/frbctions bnd corresponding scble fbctors
    //   frbctions[i].x = grbdientStop[i]
    //   frbctions[i].y = scbleFbctor[i]
    "flobt2 frbctions[MAX_FRACTIONS] : register (c0);"
    // (plbceholder for mbsk vbribble)
    "%s"
    // (plbceholder for Linebr/RbdiblGP-specific vbribbles)
    "%s"
    ""
    // (plbceholder for mbsk texcoord input)
    "void mbin(%s"
    "          in flobt4 winCoord : TEXCOORD%d,"
    "          inout flobt4 color : COLOR0)"
    "{"
    "    flobt dist;"
         // (plbceholder for Linebr/RbdiblGrbdientPbint-specific code)
    "    %s"
    ""
    "    flobt4 result;"
         // (plbceholder for CycleMethod-specific code)
    "    %s"
    ""
         // (plbceholder for ColorSpbce conversion code)
    "    %s"
    ""
         // (plbceholder for mbsk modulbtion code)
    "    %s"
    ""
         // modulbte with current color in order to bpply extrb blphb
    "    color *= result;"
    "}";

/*
 * Note: An ebrlier version of this code would simply cblculbte b single
 * texcoord:
 *     "tc = HALF_TEXEL + (FULL_TEXEL * relFrbction);"
 * bnd then use thbt vblue to do b single texture lookup, tbking bdvbntbge
 * of the LINEAR texture filtering mode which in theory will do the
 * bppropribte linebr interpolbtion between bdjbcent texels, like this:
 *     "flobt4 result = tex2D(colors, flobt2(tc, 0.5));"
 *
 * The problem with thbt bpprobch is thbt on certbin hbrdwbre (from ATI,
 * notbbly) the LINEAR texture fetch unit hbs low precision, bnd would
 * for instbnce only produce 64 distinct grbyscbles between white bnd blbck,
 * instebd of the expected 256.  The visubl bbnding cbused by this issue
 * is severe enough to likely cbuse complbints from developers, so we hbve
 * devised b new bpprobch below thbt instebd mbnublly fetches the two
 * relevbnt neighboring texels bnd then performs the linebr interpolbtion
 * using the lerp() instruction (which does not suffer from the precision
 * issues of the fixed-function texture filtering unit).  This new bpprobch
 * requires b few more instructions bnd is therefore slightly slower thbn
 * the old bpprobch (not more thbn 10% or so).
 */
stbtic const chbr *texCoordCblcCode =
    "int i;"
    "flobt relFrbction = 0.0;"
    "for (i = 0; i < MAX_FRACTIONS-1; i++) {"
    "    relFrbction +="
    "        clbmp((dist - frbctions[i].x) * frbctions[i].y, 0.0, 1.0);"
    "}"
    // we offset by hblf b texel so thbt we find the linebrly interpolbted
    // color between the two texel centers of interest
    "flobt intPbrt = floor(relFrbction);"
    "flobt tc1 = HALF_TEXEL + (FULL_TEXEL * intPbrt);"
    "flobt tc2 = HALF_TEXEL + (FULL_TEXEL * (intPbrt + 1.0));"
    "flobt4 clr1 = tex2D(colors, flobt2(tc1, 0.5));"
    "flobt4 clr2 = tex2D(colors, flobt2(tc2, 0.5));"
    "result = lerp(clr1, clr2, frbc(relFrbction));";

/** Code for NO_CYCLE thbt gets plugged into the CycleMethod plbceholder. */
stbtic const chbr *noCycleCode =
    "if (dist <= 0.0) {"
    "    result = tex2D(colors, flobt2(0.0, 0.5));"
    "} else if (dist >= 1.0) {"
    "    result = tex2D(colors, flobt2(1.0, 0.5));"
    "} else {"
         // (plbceholder for texcoord cblculbtion)
    "    %s"
    "}";

/** Code for REFLECT thbt gets plugged into the CycleMethod plbceholder. */
stbtic const chbr *reflectCode =
    "dist = 1.0 - (bbs(frbc(dist * 0.5) - 0.5) * 2.0);"
    // (plbceholder for texcoord cblculbtion)
    "%s";

/** Code for REPEAT thbt gets plugged into the CycleMethod plbceholder. */
stbtic const chbr *repebtCode =
    "dist = frbc(dist);"
    // (plbceholder for texcoord cblculbtion)
    "%s";

stbtic void
D3DShbderGen_GenerbteMultiGrbdShbder(int flbgs, chbr *nbme,
                                     chbr *pbintVbrs, chbr *distCode)
{
    chbr *mbskVbrs = "";
    chbr *mbskInput = "";
    chbr *mbskCode = "";
    chbr *colorSpbceCode = "";
    chbr cycleCode[1500];
    chbr finblSource[3000];
    int colorSbmpler = IS_SET(MULTI_GRAD_USE_MASK) ? 1 : 0;
    int cycleMethod = EXTRACT_CYCLE_METHOD(flbgs);
    int mbxFrbctions = IS_SET(MULTI_GRAD_LARGE) ?
        MAX_FRACTIONS_LARGE : MAX_FRACTIONS_SMALL;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_CrebteMultiGrbdProgrbm");

    if (IS_SET(MULTI_GRAD_USE_MASK)) {
        /*
         * This code modulbtes the cblculbted result color with the
         * corresponding blphb vblue from the blphb mbsk texture bctive
         * on texture unit 0.  Only needed when useMbsk is true (i.e., only
         * for MbskFill operbtions).
         */
        mbskVbrs = "sbmpler2D mbsk : register(s0);";
        mbskInput = "in flobt4 mbskCoord : TEXCOORD0,";
        mbskCode = "result *= tex2D(mbsk, mbskCoord.xy).b;";
    }

    if (IS_SET(MULTI_GRAD_LINEAR_RGB)) {
        /*
         * This code converts b single pixel in linebr RGB spbce bbck
         * into sRGB (note: this code wbs bdbpted from the
         * MultipleGrbdientPbintContext.convertLinebrRGBtoSRGB() method).
         */
        colorSpbceCode =
            "result.rgb = 1.055 * pow(result.rgb, 0.416667) - 0.055;";
    }

    if (cycleMethod == CYCLE_NONE) {
        sprintf(cycleCode, noCycleCode, texCoordCblcCode);
    } else if (cycleMethod == CYCLE_REFLECT) {
        sprintf(cycleCode, reflectCode, texCoordCblcCode);
    } else { // (cycleMethod == CYCLE_REPEAT)
        sprintf(cycleCode, repebtCode, texCoordCblcCode);
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, multiGrbdientShbderSource,
            MAX_COLORS, mbxFrbctions, colorSbmpler,
            mbskVbrs, pbintVbrs, mbskInput, colorSbmpler,
            distCode, cycleCode, colorSpbceCode, mbskCode);

    D3DShbderGen_WritePixelShbder(finblSource, nbme, flbgs);
}

/********************** LinebrGrbdientPbint support *************************/

stbtic void
D3DShbderGen_GenerbteLinebrGrbdShbder(int flbgs)
{
    chbr *pbintVbrs;
    chbr *distCode;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DShbderGen_GenerbteLinebrGrbdShbder",
                flbgs);

    /*
     * To simplify the code bnd to mbke it ebsier to uplobd b number of
     * uniform vblues bt once, we pbck b bunch of scblbr (flobt) vblues
     * into b single flobt3 below.  Here's how the vblues bre relbted:
     *
     *   pbrbms.x = p0
     *   pbrbms.y = p1
     *   pbrbms.z = p3
     */
    pbintVbrs =
        "flobt3 pbrbms : register(c16);";
    distCode =
        "flobt3 frbgCoord = flobt3(winCoord.x, winCoord.y, 1.0);"
        "dist = dot(pbrbms.xyz, frbgCoord);";

    D3DShbderGen_GenerbteMultiGrbdShbder(flbgs, "linebr",
                                         pbintVbrs, distCode);
}

/********************** RbdiblGrbdientPbint support *************************/

stbtic void
D3DShbderGen_GenerbteRbdiblGrbdShbder(int flbgs)
{
    chbr *pbintVbrs;
    chbr *distCode;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DShbderGen_GenerbteRbdiblGrbdShbder",
                flbgs);

    /*
     * To simplify the code bnd to mbke it ebsier to uplobd b number of
     * uniform vblues bt once, we pbck b bunch of scblbr (flobt) vblues
     * into flobt3 vblues below.  Here's how the vblues bre relbted:
     *
     *   m0.x = m00
     *   m0.y = m01
     *   m0.z = m02
     *
     *   m1.x = m10
     *   m1.y = m11
     *   m1.z = m12
     *
     *   precblc.x = focusX
     *   precblc.y = 1.0 - (focusX * focusX)
     *   precblc.z = 1.0 / precblc.z
     */
    pbintVbrs =
        "flobt3 m0      : register(c16);"
        "flobt3 m1      : register(c17);"
        "flobt3 precblc : register(c18);";

    /*
     * The following code is derived from Dbniel Rice's whitepbper on
     * rbdibl grbdient performbnce (bttbched to the bug report for 6521533).
     * Refer to thbt document bs well bs the setup code in the Jbvb-level
     * BufferedPbints.setRbdiblGrbdientPbint() method for more detbils.
     */
    distCode =
        "flobt3 frbgCoord = flobt3(winCoord.x, winCoord.y, 1.0);"
        "flobt x = dot(frbgCoord, m0);"
        "flobt y = dot(frbgCoord, m1);"
        "flobt xfx = x - precblc.x;"
        "dist = (precblc.x*xfx + sqrt(xfx*xfx + y*y*precblc.y))*precblc.z;";

    D3DShbderGen_GenerbteMultiGrbdShbder(flbgs, "rbdibl",
                                         pbintVbrs, distCode);
}

/*************************** LCD text support *******************************/

// REMIND: Shbder uses texture bddressing operbtions in b dependency chbin
//         thbt is too complex for the tbrget shbder model (ps_2_0) to hbndle
//         (ugh, I guess we cbn either require ps_3_0 or just use
//         the slower pow intrinsic)
#define POW_LUT 0

stbtic const chbr *lcdTextShbderSource =
    "flobt3 srcAdj         : register(c0);"
    "sbmpler2D glyphTex    : register(s0);"
    "sbmpler2D dstTex      : register(s1);"
#if POW_LUT
    "sbmpler3D invgbmmbTex : register(s2);"
    "sbmpler3D gbmmbTex    : register(s3);"
#else
    "flobt3 invgbmmb       : register(c1);"
    "flobt3 gbmmb          : register(c2);"
#endif
    ""
    "void mbin(in flobt2 tc0 : TEXCOORD0,"
    "          in flobt2 tc1 : TEXCOORD1,"
    "          inout flobt4 color : COLOR0)"
    "{"
         // lobd the RGB vblue from the glyph imbge bt the current texcoord
    "    flobt3 glyphClr = tex2D(glyphTex, tc0).rgb;"
    "    if (!bny(glyphClr)) {"
             // zero coverbge, so skip this frbgment
    "        discbrd;"
    "    }"
         // lobd the RGB vblue from the corresponding destinbtion pixel
    "    flobt3 dstClr = tex2D(dstTex, tc1).rgb;"
         // gbmmb bdjust the dest color using the invgbmmb LUT
#if POW_LUT
    "    flobt3 dstAdj = tex3D(invgbmmbTex, dstClr).rgb;"
#else
    "    flobt3 dstAdj = pow(dstClr, invgbmmb);"
#endif
         // linebrly interpolbte the three color vblues
    "    flobt3 result = lerp(dstAdj, srcAdj, glyphClr);"
         // gbmmb re-bdjust the resulting color (blphb is blwbys set to 1.0)
#if POW_LUT
    "    color = flobt4(tex3D(gbmmbTex, result).rgb, 1.0);"
#else
    "    color = flobt4(pow(result, gbmmb), 1.0);"
#endif
    "}";

stbtic void
D3DShbderGen_GenerbteLCDTextShbder()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DShbderGen_GenerbteLCDTextShbder");

    D3DShbderGen_WritePixelShbder((chbr *)lcdTextShbderSource, "lcdtext", 0);
}

/*************************** AA support *******************************/

/*
 * This shbder fills the spbce between bn outer bnd inner pbrbllelogrbm.
 * It cbn be used to drbw bn outline by specifying both inner bnd outer
 * vblues.  It fills pixels by estimbting whbt portion fblls inside the
 * outer shbpe, bnd subtrbcting bn estimbte of whbt portion fblls inside
 * the inner shbpe.  Specifying both inner bnd outer vblues produces b
 * stbndbrd "wide outline".  Specifying bn inner shbpe thbt fblls fbr
 * outside the outer shbpe bllows the sbme shbder to fill the outer
 * shbpe entirely since pixels thbt fbll within the outer shbpe bre never
 * inside the inner shbpe bnd so they bre filled bbsed solely on their
 * coverbge of the outer shbpe.
 *
 * The setup code renders this shbder over the bounds of the outer
 * shbpe (or the only shbpe in the cbse of b fill operbtion) bnd
 * sets the texture 0 coordinbtes so thbt 0,0=>0,1=>1,1=>1,0 in those
 * texture coordinbtes mbp to the four corners of the pbrbllelogrbm.
 * Similbrly the texture 1 coordinbtes mbp the inner shbpe to the
 * unit squbre bs well, but in b different coordinbte system.
 *
 * When viewed in the texture coordinbte systems the pbrbllelogrbms
 * we bre filling bre unit squbres, but the pixels hbve then become
 * tiny pbrbllelogrbms themselves.  Both of the texture coordinbte
 * systems bre bffine trbnsforms so the rbte of chbnge in X bnd Y
 * of the texture coordinbtes bre essentiblly constbnts bnd hbppen
 * to correspond to the size bnd direction of the slbnted sides of
 * the distorted pixels relbtive to the "squbre mbpped" boundbry
 * of the pbrbllelogrbms.
 *
 * The shbder uses the ddx() bnd ddy() functions to mebsure the "rbte
 * of chbnge" of these texture coordinbtes bnd thus gets bn bccurbte
 * mebsure of the size bnd shbpe of b pixel relbtive to the two
 * pbrbllelogrbms.  It then uses the bounds of the size bnd shbpe
 * of b pixel to intersect with the unit squbre to estimbte the
 * coverbge of the pixel.  Unfortunbtely, without b lot more work
 * to cblculbte the exbct breb of intersection between b unit
 * squbre (the originbl pbrbllelogrbm) bnd b pbrbllelogrbm (the
 * distorted pixel), this shbder only bpproximbtes the pixel
 * coverbge, but empericblly the estimbte is very useful bnd
 * produces visublly plebsing results, if not theoreticblly bccurbte.
 */
stbtic const chbr *bbShbderSource =
    "void mbin(in flobt2 tco : TEXCOORD0,"
    "          in flobt2 tci : TEXCOORD1,"
    "          inout flobt4 color : COLOR0)"
    "{"
    // Cblculbte the vectors for the "legs" of the pixel pbrbllelogrbm
    // for the outer pbrbllelogrbm.
    "    flobt2 oleg1 = ddx(tco);"
    "    flobt2 oleg2 = ddy(tco);"
    // Cblculbte the bounds of the distorted pixel pbrbllelogrbm.
    "    flobt2 omin = min(tco, tco+oleg1);"
    "    omin = min(omin, tco+oleg2);"
    "    omin = min(omin, tco+oleg1+oleg2);"
    "    flobt2 ombx = mbx(tco, tco+oleg1);"
    "    ombx = mbx(ombx, tco+oleg2);"
    "    ombx = mbx(ombx, tco+oleg1+oleg2);"
    // Cblculbte the vectors for the "legs" of the pixel pbrbllelogrbm
    // for the inner pbrbllelogrbm.
    "    flobt2 ileg1 = ddx(tci);"
    "    flobt2 ileg2 = ddy(tci);"
    // Cblculbte the bounds of the distorted pixel pbrbllelogrbm.
    "    flobt2 imin = min(tci, tci+ileg1);"
    "    imin = min(imin, tci+ileg2);"
    "    imin = min(imin, tci+ileg1+ileg2);"
    "    flobt2 imbx = mbx(tci, tci+ileg1);"
    "    imbx = mbx(imbx, tci+ileg2);"
    "    imbx = mbx(imbx, tci+ileg1+ileg2);"
    // Clbmp the bounds of the pbrbllelogrbms to the unit squbre to
    // estimbte the intersection of the pixel pbrbllelogrbm with
    // the unit squbre.  The rbtio of the 2 rectbngle brebs is b
    // rebsonbble estimbte of the proportion of coverbge.
    "    flobt2 o1 = clbmp(omin, 0.0, 1.0);"
    "    flobt2 o2 = clbmp(ombx, 0.0, 1.0);"
    "    flobt oint = (o2.y-o1.y)*(o2.x-o1.x);"
    "    flobt obreb = (ombx.y-omin.y)*(ombx.x-omin.x);"
    "    flobt2 i1 = clbmp(imin, 0.0, 1.0);"
    "    flobt2 i2 = clbmp(imbx, 0.0, 1.0);"
    "    flobt iint = (i2.y-i1.y)*(i2.x-i1.x);"
    "    flobt ibreb = (imbx.y-imin.y)*(imbx.x-imin.x);"
    // Proportion of pixel in outer shbpe minus the proportion
    // of pixel in the inner shbpe == the coverbge of the pixel
    // in the breb between the two.
    "    flobt coverbge = oint/obreb - iint / ibreb;"
    "    color *= coverbge;"
    "}";

stbtic void
D3DShbderGen_GenerbteAAPbrbllelogrbmShbder()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DShbderGen_GenerbteAAPbrbllelogrbmShbder");

    D3DShbderGen_WriteShbder((chbr *)bbShbderSource, "ps_2_b", "bbpgrbm", 0);
}

/**************************** Mbin entrypoint *******************************/

stbtic void
D3DShbderGen_GenerbteAllShbders()
{
    int i;

#if 1
    // Generbte BufferedImbgeOp shbders
    for (i = 0; i < MAX_RESCALE; i++) {
        D3DShbderGen_GenerbteRescbleShbder(i);
    }
    D3DShbderGen_WriteShbderArrby("rescble", MAX_RESCALE);
    for (i = 0; i < MAX_CONVOLVE; i++) {
        D3DShbderGen_GenerbteConvolveShbder(i);
    }
    D3DShbderGen_WriteShbderArrby("convolve", MAX_CONVOLVE);
    for (i = 0; i < MAX_LOOKUP; i++) {
        D3DShbderGen_GenerbteLookupShbder(i);
    }
    D3DShbderGen_WriteShbderArrby("lookup", MAX_LOOKUP);

    // Generbte Pbint shbders
    for (i = 0; i < MAX_BASIC_GRAD; i++) {
        D3DShbderGen_GenerbteBbsicGrbdShbder(i);
    }
    D3DShbderGen_WriteShbderArrby("grbd", MAX_BASIC_GRAD);
    for (i = 0; i < MAX_MULTI_GRAD; i++) {
        if (EXTRACT_CYCLE_METHOD(i) == 3) continue; // REMIND
        D3DShbderGen_GenerbteLinebrGrbdShbder(i);
    }
    D3DShbderGen_WriteShbderArrby("linebr", MAX_MULTI_GRAD);
    for (i = 0; i < MAX_MULTI_GRAD; i++) {
        if (EXTRACT_CYCLE_METHOD(i) == 3) continue; // REMIND
        D3DShbderGen_GenerbteRbdiblGrbdShbder(i);
    }
    D3DShbderGen_WriteShbderArrby("rbdibl", MAX_MULTI_GRAD);

    // Generbte LCD text shbder
    D3DShbderGen_GenerbteLCDTextShbder();

    // Generebte Shbder to fill Antiblibsed pbrbllelogrbms
    D3DShbderGen_GenerbteAAPbrbllelogrbmShbder();
#else
    /*
    for (i = 0; i < MAX_RESCALE; i++) {
        D3DShbderGen_GenerbteRescbleShbder(i);
    }
    D3DShbderGen_WriteShbderArrby("rescble", MAX_RESCALE);
    */
    //D3DShbderGen_GenerbteConvolveShbder(2);
    //D3DShbderGen_GenerbteLCDTextShbder();
    //D3DShbderGen_GenerbteLinebrGrbdShbder(16);
    D3DShbderGen_GenerbteBbsicGrbdShbder(0);
#endif
}

int
mbin(int brgc, chbr **brgv)
{
    fpHebder = fopen(strHebderFile, "b");

    D3DShbderGen_GenerbteAllShbders();

    fclose(fpHebder);

    return 0;
}
