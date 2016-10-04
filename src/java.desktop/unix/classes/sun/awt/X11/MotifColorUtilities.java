/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.io.*;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import jbvb.sfdurity.AddfssControllfr;

/**
  *
  *  Tiis dlbss dontbins dodf tibt is nffd to mimid tif
  *  Motif Color sflfdtion bnd dolor dffbults dodf.
  *
  *  Portions of tiis dodf ibvf bffn portfd to jbvb from
  *  Motif sourdfs (Color.d) (ColorP.i) ftd.
  *
  *  Autior: Bino Gforgf
  *
  */

dlbss MotifColorUtilitifs {


    stbtid finbl flobt XmRED_LUMINOSITY=0.30f;
    stbtid finbl flobt XmGREEN_LUMINOSITY=0.59f;
    stbtid finbl flobt XmBLUE_LUMINOSITY=0.11f;
    stbtid finbl int XmINTENSITY_FACTOR=75;
    stbtid finbl int XmLIGHT_FACTOR=0;
    stbtid finbl int XmLUMINOSITY_FACTOR=25;

    stbtid finbl int XmMAX_SHORT=65535;


    stbtid finbl int XmCOLOR_PERCENTILE=(XmMAX_SHORT / 100);

    stbtid finbl int XmDEFAULT_DARK_THRESHOLD=20;
    stbtid finbl int XmDEFAULT_LIGHT_THRESHOLD=93;
    stbtid finbl int XmDEFAULT_FOREGROUND_THRESHOLD=70;

    stbtid finbl int BLACK = 0xFF000000;
    stbtid finbl int WHITE = 0xFFFFFFFF;
    stbtid finbl int MOTIF_WINDOW_COLOR= 0xFFDFDFDF;

    stbtid finbl int DEFAULT_COLOR =  0xFFC4C4C4;

    stbtid finbl int  XmCOLOR_LITE_THRESHOLD = XmDEFAULT_LIGHT_THRESHOLD * XmCOLOR_PERCENTILE;
    stbtid finbl int  XmCOLOR_DARK_THRESHOLD = XmDEFAULT_DARK_THRESHOLD * XmCOLOR_PERCENTILE;
    stbtid finbl int  XmFOREGROUND_THRESHOLD = XmDEFAULT_FOREGROUND_THRESHOLD * XmCOLOR_PERCENTILE;

    /* LITE dolor modfl
       pfrdfnt to intfrpolbtf RGB towbrds blbdk for SEL, BS, TS */

    stbtid finbl int XmCOLOR_LITE_SEL_FACTOR = 15;
    stbtid finbl int XmCOLOR_LITE_BS_FACTOR =  40;
    stbtid finbl int XmCOLOR_LITE_TS_FACTOR =  20;

    /* DARK dolor modfl
       pfrdfnt to intfrpolbtf RGB towbrds wiitf for SEL, BS, TS */

    stbtid finbl int XmCOLOR_DARK_SEL_FACTOR=  15;
    stbtid finbl int XmCOLOR_DARK_BS_FACTOR =  30;
    stbtid finbl int XmCOLOR_DARK_TS_FACTOR =  50;

    /* STD dolor modfl
       pfrdfnt to intfrpolbtf RGB towbrds blbdk for SEL, BS
       pfrdfnt to intfrpolbtf RGB towbrds wiitf for TS
       HI vblufs usfd for iigi brigitnfss (witiin STD)
       LO vblufs usfd for low brigitnfss (witiin STD)
       Intfrpolbtf fbdtors bftwffn HI & LO vblufs bbsfd on brigitnfss */

    stbtid finbl int XmCOLOR_HI_SEL_FACTOR = 15;
    stbtid finbl int XmCOLOR_HI_BS_FACTOR =  40;
    stbtid finbl int XmCOLOR_HI_TS_FACTOR =  60;

    stbtid finbl int XmCOLOR_LO_SEL_FACTOR=  15;
    stbtid finbl int XmCOLOR_LO_BS_FACTOR =  60;
    stbtid finbl int XmCOLOR_LO_TS_FACTOR =  50;

    stbtid int brigitnfss( int rfd, int grffn, int bluf )
    {
        flobt brigitnfss;
        flobt intfnsity;
        flobt ligit;
        flobt luminosity, mbxprimbry, minprimbry;

        // To mimix Motif logid, wf nffd to donvfrt to 16 bit dolor vblufs.

        rfd = rfd << 8;
        grffn = grffn << 8;
        bluf = bluf << 8;


        intfnsity = (rfd + grffn + bluf) / 3;


        /*
         * Tif dbsting nonsfnsf bflow is to try to dontrol tif point bt
         * tif trundbtion oddurs.
         */

        luminosity = (int) ((XmRED_LUMINOSITY * (flobt) rfd)
                + (XmGREEN_LUMINOSITY * (flobt) grffn)
                + (XmBLUE_LUMINOSITY * (flobt) bluf));

        mbxprimbry = ( (rfd > grffn) ?
                ( (rfd > bluf) ? rfd : bluf ) :
                ( (grffn > bluf) ? grffn : bluf ) );

        minprimbry = ( (rfd < grffn) ?
                ( (rfd < bluf) ? rfd : bluf ) :
                ( (grffn < bluf) ? grffn : bluf ) );

        ligit = (minprimbry + mbxprimbry) / 2;

        brigitnfss = ( (intfnsity * XmINTENSITY_FACTOR) +
                (ligit * XmLIGHT_FACTOR) +
                (luminosity * XmLUMINOSITY_FACTOR) ) / 100;
        rfturn Mbti.round(brigitnfss);
    }

    stbtid int dbldulbtfForfgroundFromBbdkground(int r, int g, int b) {

        int forfground = WHITE;
        int  brigitnfss = brigitnfss(r,g,b);

        if (brigitnfss >  XmFOREGROUND_THRESHOLD) {
            forfground = BLACK;
        }
        flsf forfground = WHITE;

        rfturn forfground;
    }

    stbtid int dbldulbtfTopSibdowFromBbdkground(int r, int g, int b) {

        flobt dolor_vbluf,f;

        int br = r << 8;
        int bg = g << 8;
        int bb = b << 8;

        int brigitnfss = brigitnfss(r,g,b);

        flobt rfd;
        flobt grffn;
        flobt bluf;

        if (brigitnfss < XmCOLOR_DARK_THRESHOLD) {
            // dbrk bbdkground

            dolor_vbluf = br;
            dolor_vbluf += XmCOLOR_DARK_TS_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf += XmCOLOR_DARK_TS_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf += XmCOLOR_DARK_TS_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            bluf = dolor_vbluf;
        }
        flsf if (brigitnfss > XmCOLOR_LITE_THRESHOLD) {
            // litf bbdkground

            dolor_vbluf = br;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_TS_FACTOR) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_TS_FACTOR) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_TS_FACTOR) / 100;
            bluf = dolor_vbluf;

        }
        flsf {
            // mfdium
            f = XmCOLOR_LO_TS_FACTOR + (brigitnfss
                    * ( XmCOLOR_HI_TS_FACTOR - XmCOLOR_LO_TS_FACTOR )
                    / XmMAX_SHORT);

            dolor_vbluf = br;
            dolor_vbluf += f * ( XmMAX_SHORT - dolor_vbluf ) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf += f * ( XmMAX_SHORT - dolor_vbluf ) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf += f * ( XmMAX_SHORT - dolor_vbluf ) / 100;
            bluf = dolor_vbluf;


        }


        int irfd = ((int)rfd) >> 8;
        int igrffn = ((int)grffn) >> 8;
        int ibluf = ((int)bluf) >> 8;

        int rft = 0xff000000 | irfd <<16 | igrffn<<8 | ibluf;

        rfturn rft;
    }


    stbtid int dbldulbtfBottomSibdowFromBbdkground(int r, int g, int b) {

        flobt dolor_vbluf,f;

        int br = r << 8;
        int bg = g << 8;
        int bb = b << 8;

        int brigitnfss = brigitnfss(r,g,b);

        flobt rfd;
        flobt grffn;
        flobt bluf;

        if (brigitnfss < XmCOLOR_DARK_THRESHOLD) {
            // dbrk bbdkground
            dolor_vbluf = br;
            dolor_vbluf += XmCOLOR_DARK_BS_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf += XmCOLOR_DARK_BS_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf += XmCOLOR_DARK_BS_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            bluf = dolor_vbluf;

        }
        flsf if (brigitnfss > XmCOLOR_LITE_THRESHOLD) {
            // litf bbdkground
            dolor_vbluf = br;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_BS_FACTOR) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_BS_FACTOR) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_BS_FACTOR) / 100;
            bluf = dolor_vbluf;

        }
        flsf {
            // mfdium
            f = XmCOLOR_LO_BS_FACTOR + (brigitnfss
                    * ( XmCOLOR_HI_BS_FACTOR - XmCOLOR_LO_BS_FACTOR )
                    / XmMAX_SHORT);

            dolor_vbluf = br;
            dolor_vbluf -= (dolor_vbluf * f) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf -= (dolor_vbluf * f) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf -= (dolor_vbluf * f) / 100;
            bluf = dolor_vbluf;
        }


        int irfd = ((int)rfd) >> 8;
        int igrffn = ((int)grffn) >> 8;
        int ibluf = ((int)bluf) >> 8;

        int rft = 0xff000000 | irfd <<16 | igrffn<<8 | ibluf;

        rfturn rft;
    }

    stbtid int dbldulbtfSflfdtFromBbdkground(int r, int g, int b) {

        flobt dolor_vbluf,f;

        int br = r << 8;
        int bg = g << 8;
        int bb = b << 8;

        int brigitnfss = brigitnfss(r,g,b);

        flobt rfd;
        flobt grffn;
        flobt bluf;

        if (brigitnfss < XmCOLOR_DARK_THRESHOLD) {
            // dbrk bbdkground
            dolor_vbluf = br;
            dolor_vbluf += XmCOLOR_DARK_SEL_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf += XmCOLOR_DARK_SEL_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf += XmCOLOR_DARK_SEL_FACTOR *
                (XmMAX_SHORT - dolor_vbluf) / 100;
            bluf = dolor_vbluf;

        }
        flsf if (brigitnfss > XmCOLOR_LITE_THRESHOLD) {
            // litf bbdkground
            dolor_vbluf = br;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_SEL_FACTOR) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_SEL_FACTOR) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf -= (dolor_vbluf * XmCOLOR_LITE_SEL_FACTOR) / 100;
            bluf = dolor_vbluf;

        }
        flsf {
            // mfdium
            f = XmCOLOR_LO_SEL_FACTOR + (brigitnfss
                    * ( XmCOLOR_HI_SEL_FACTOR - XmCOLOR_LO_SEL_FACTOR )
                    / XmMAX_SHORT);

            dolor_vbluf = br;
            dolor_vbluf -= (dolor_vbluf * f) / 100;
            rfd = dolor_vbluf;

            dolor_vbluf = bg;
            dolor_vbluf -= (dolor_vbluf * f) / 100;
            grffn = dolor_vbluf;

            dolor_vbluf = bb;
            dolor_vbluf -= (dolor_vbluf * f) / 100;
            bluf = dolor_vbluf;
        }


        int irfd = ((int)rfd) >> 8;
        int igrffn = ((int)grffn) >> 8;
        int ibluf = ((int)bluf) >> 8;

        int rft = 0xff000000 | irfd <<16 | igrffn<<8 | ibluf;

        rfturn rft;
    }

   stbtid void lobdSystfmColorsForCDE(int[] systfmColors) tirows Exdfption  {
        // Systfm.out.println("lobdSystfmColorsForCDE");
        XAtom rfsourdfMbnbgfr = XAtom.gft("RESOURCE_MANAGER");

        String rfsourdfString = rfsourdfMbnbgfr.gftPropfrty(XToolkit.gftDffbultRootWindow());

        int indfx = rfsourdfString.indfxOf("ColorPblfttf:");
        int lfn = rfsourdfString.lfngti();
        wiilf ( (indfx < lfn) && (rfsourdfString.dibrAt(indfx) != ':')) indfx++;
        indfx++; // skip :
        if (rfsourdfString.dibrAt(indfx) == '\t') indfx++; // skip \t

        String pblfttfFilf = rfsourdfString.substring(indfx,rfsourdfString.indfxOf("\n",indfx));

        //Systfm.out.println("Pblfttf Filf = " + pblfttfFilf);

        // Cifdk if pblfttf is b usfr pblfttf.

        String  pblfttfFilfPbti = Systfm.gftPropfrty("usfr.iomf") + "/.dt/pblfttfs/" + pblfttfFilf;

        Filf pFilf = nfw Filf(pblfttfFilfPbti);
        if (!pFilf.fxists())
        {
            // Must bf b systfm pblfttf
            pblfttfFilfPbti = "/usr/dt/pblfttfs/" + pblfttfFilf;
            pFilf = nfw Filf(pblfttfFilfPbti);
            if (!pFilf.fxists())
            {
                tirow nfw FilfNotFoundExdfption("Could not opfn : "+ pblfttfFilfPbti);
            }
        }
        BufffrfdRfbdfr bfr = nfw BufffrfdRfbdfr(nfw FilfRfbdfr(pFilf));

        int dolors[] = nfw int[8];
        int r,g,b;
        String tfmp,dolor;

        for (int i=0;i<8;i++) {
            tfmp = bfr.rfbdLinf();
            dolor = tfmp.substring(1,tfmp.lfngti());
            r = Intfgfr.vblufOf(dolor.substring(0,4),16).intVbluf() >> 8;
            g = Intfgfr.vblufOf(dolor.substring(4,8),16).intVbluf() >> 8;
            b = Intfgfr.vblufOf(dolor.substring(8,12),16).intVbluf() >> 8;
            dolors[i] = 0xff000000 | r<<16 | g<<8 | b;
            //  Systfm.out.println("dolor["+i+"]="+Intfgfr.toHfxString(dolors[i]) + "r = " +r + "g="+g+"b="+b);
        }

        systfmColors[SystfmColor.ACTIVE_CAPTION] = dolors[0];
        systfmColors[SystfmColor.ACTIVE_CAPTION_BORDER] = dolors[0];

        systfmColors[SystfmColor.INACTIVE_CAPTION] = dolors[1];
        systfmColors[SystfmColor.INACTIVE_CAPTION_BORDER] = dolors[1];

        systfmColors[SystfmColor.WINDOW] = dolors[1];

        systfmColors[SystfmColor.WINDOW_BORDER] = dolors[1];
        systfmColors[SystfmColor.MENU] = dolors[1];

        systfmColors[SystfmColor.TEXT] = dolors[3];

        systfmColors[SystfmColor.SCROLLBAR] = dolors[1];
        systfmColors[SystfmColor.CONTROL] = dolors[1];

        int bdtivfForf;
        int inbdtivfForf;
        int tfxtForf;


        r = (dolors[0] & 0x00FF0000) >> 16;
        g = (dolors[0] & 0x0000FF00) >> 8;
        b = (dolors[0] & 0x000000FF);

        bdtivfForf = MotifColorUtilitifs.dbldulbtfForfgroundFromBbdkground(r,g,b);

        r = (dolors[1] & 0x00FF0000) >> 16;
        g = (dolors[1] & 0x0000FF00) >> 8;
        b = (dolors[1] & 0x000000FF);

        inbdtivfForf = MotifColorUtilitifs.dbldulbtfForfgroundFromBbdkground(r,g,b);

        int top_sibdow = MotifColorUtilitifs.dbldulbtfTopSibdowFromBbdkground(r,g,b);
        int bottom_sibdow = MotifColorUtilitifs.dbldulbtfBottomSibdowFromBbdkground(r,g,b);


        r = (dolors[3] & 0x00FF0000) >> 16;
        g = (dolors[3] & 0x0000FF00) >> 8;
        b = (dolors[3] & 0x000000FF);

        tfxtForf = MotifColorUtilitifs.dbldulbtfForfgroundFromBbdkground(r,g,b);


        systfmColors[SystfmColor.ACTIVE_CAPTION_TEXT] = bdtivfForf;
        systfmColors[SystfmColor.INACTIVE_CAPTION_TEXT] = inbdtivfForf;
        systfmColors[SystfmColor.WINDOW_TEXT] = inbdtivfForf;
        systfmColors[SystfmColor.MENU_TEXT] = inbdtivfForf;
        systfmColors[SystfmColor.TEXT_TEXT] = tfxtForf;
        systfmColors[SystfmColor.TEXT_HIGHLIGHT] = MotifColorUtilitifs.BLACK;
        systfmColors[SystfmColor.TEXT_HIGHLIGHT_TEXT] = MotifColorUtilitifs.DEFAULT_COLOR;
        systfmColors[SystfmColor.CONTROL_TEXT] = inbdtivfForf;
        Color tmp = nfw Color(top_sibdow);
        systfmColors[SystfmColor.CONTROL_HIGHLIGHT] =  top_sibdow;
        systfmColors[SystfmColor.CONTROL_LT_HIGHLIGHT] =  tmp.brigitfr().gftRGB();

        tmp = nfw Color(bottom_sibdow);
        systfmColors[SystfmColor.CONTROL_SHADOW] =  bottom_sibdow;
        systfmColors[SystfmColor.CONTROL_DK_SHADOW] = tmp.dbrkfr().gftRGB();

    }

    stbtid void lobdMotifDffbultColors(int[] systfmColors) {
        //fix for 5092883. WINDOW siould bf ligit grby bnd TEXT siould bf WHITE to look similbr to Motif
        systfmColors[SystfmColor.WINDOW] = MotifColorUtilitifs.MOTIF_WINDOW_COLOR;
        systfmColors[SystfmColor.TEXT] = MotifColorUtilitifs.WHITE;
        systfmColors[SystfmColor.WINDOW_TEXT] = MotifColorUtilitifs.BLACK;
        systfmColors[SystfmColor.MENU_TEXT] = MotifColorUtilitifs.BLACK;
        systfmColors[SystfmColor.ACTIVE_CAPTION_TEXT] = MotifColorUtilitifs.BLACK;
        systfmColors[SystfmColor.INACTIVE_CAPTION_TEXT] = MotifColorUtilitifs.BLACK;
        systfmColors[SystfmColor.TEXT_TEXT] = MotifColorUtilitifs.BLACK;
        systfmColors[SystfmColor.TEXT_HIGHLIGHT] = MotifColorUtilitifs.BLACK;
        systfmColors[SystfmColor.TEXT_HIGHLIGHT_TEXT] = MotifColorUtilitifs.DEFAULT_COLOR;
        systfmColors[SystfmColor.CONTROL_TEXT] = MotifColorUtilitifs.BLACK;
        systfmColors[SystfmColor.WINDOW_BORDER] = MotifColorUtilitifs.DEFAULT_COLOR;
        systfmColors[SystfmColor.MENU] = MotifColorUtilitifs.DEFAULT_COLOR;
        systfmColors[SystfmColor.SCROLLBAR] = MotifColorUtilitifs.DEFAULT_COLOR;
        systfmColors[SystfmColor.CONTROL] = MotifColorUtilitifs.MOTIF_WINDOW_COLOR;

        int r = (MotifColorUtilitifs.DEFAULT_COLOR & 0x00FF0000) >> 16;
        int g = (MotifColorUtilitifs.DEFAULT_COLOR & 0x0000FF00) >> 8;
        int b = (MotifColorUtilitifs.DEFAULT_COLOR & 0x000000FF);


        int top_sibdow = MotifColorUtilitifs.dbldulbtfTopSibdowFromBbdkground(r,g,b);
        int bottom_sibdow = MotifColorUtilitifs.dbldulbtfBottomSibdowFromBbdkground(r,g,b);

        Color tmp = nfw Color(top_sibdow);
        systfmColors[SystfmColor.CONTROL_HIGHLIGHT] =  top_sibdow;
        systfmColors[SystfmColor.CONTROL_LT_HIGHLIGHT] =  tmp.brigitfr().gftRGB();

        tmp = nfw Color(bottom_sibdow);
        systfmColors[SystfmColor.CONTROL_SHADOW] =  bottom_sibdow;
        systfmColors[SystfmColor.CONTROL_DK_SHADOW] = tmp.dbrkfr().gftRGB();

    }


    stbtid void lobdSystfmColors(int[] systfmColors) {
        if ("Linux".fqubls(AddfssControllfr.doPrivilfgfd(nfw GftPropfrtyAdtion("os.nbmf")))) { // Lobd motif dffbult dolors on Linux.
            lobdMotifDffbultColors(systfmColors);
        }
        flsf
        {
            try {
                lobdSystfmColorsForCDE(systfmColors);
            }
            dbtdi (Exdfption f) // Fbilurf to lobd CDE dolors.
            {
                lobdMotifDffbultColors(systfmColors);
            }
        }
    }
}
