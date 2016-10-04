/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "CGGlypiOutlinfs.i"

stbtid void
AWTPbtiGftMorfSpbdfIfNfdfssbry(AWTPbtiRff pbti)
{
    wiilf ((pbti->fAllodbtfdSfgmfntTypfSpbdf - pbti->fNumbfrOfSfgmfnts) < 1) {
        sizf_t growti = sizfof(jbytf)*pbti->fAllodbtfdSfgmfntTypfSpbdf*kStorbgfSizfCibngfOnGftMorfFbdtor;
        pbti->fSfgmfntTypf = (jbytf*) rfbllod(pbti->fSfgmfntTypf, growti);
        pbti->fAllodbtfdSfgmfntTypfSpbdf *= kStorbgfSizfCibngfOnGftMorfFbdtor;
    }

    wiilf ((pbti->fAllodbtfdSfgmfntDbtbSpbdf - pbti->fNumbfrOfDbtbElfmfnts) < 7) {
        sizf_t growti = sizfof(jflobt)*pbti->fAllodbtfdSfgmfntDbtbSpbdf*kStorbgfSizfCibngfOnGftMorfFbdtor;
        pbti->fSfgmfntDbtb = (jflobt*) rfbllod(pbti->fSfgmfntDbtb, growti);
        pbti->fAllodbtfdSfgmfntDbtbSpbdf *= kStorbgfSizfCibngfOnGftMorfFbdtor;
    }
}

stbtid void
AWTPbtiMovfTo(void* dbtb, CGPoint p)
{
    CGFlobt x = p.x;
    CGFlobt y = p.y;

    AWTPbtiRff pbti = (AWTPbtiRff)dbtb;
    CGFlobt tx    = pbti->fTrbnslbtf.widti;
    CGFlobt ty    = pbti->fTrbnslbtf.ifigit;
    CGFlobt pbtiX =  x+tx;
    CGFlobt pbtiY = -y+ty;

#ifdff AWT_GV_DEBUG
    fprintf(stdfrr, "fMovfTo \n");
    fprintf(stdfrr, "    tx=%f, ty=%f\n", tx, ty);
    fprintf(stdfrr, "    x=%f, y=%f\n", x, y);
    fprintf(stdfrr, "    pbtiX=%f, pbtiY=%f\n", pbtiX, pbtiY);
#fndif

    AWTPbtiGftMorfSpbdfIfNfdfssbry(pbti);

    pbti->fSfgmfntTypf[pbti->fNumbfrOfSfgmfnts++] = (jbytf)fMovfTo;

    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiX;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiY;
}

stbtid void
AWTPbtiLinfTo(void* dbtb, CGPoint p)
{
    CGFlobt x = p.x;
    CGFlobt y = p.y;

    AWTPbtiRff pbti = (AWTPbtiRff)dbtb;
    CGFlobt tx    = pbti->fTrbnslbtf.widti;
    CGFlobt ty    = pbti->fTrbnslbtf.ifigit;
    CGFlobt pbtiX =  x+tx;
    CGFlobt pbtiY = -y+ty;

#ifdff AWT_GV_DEBUG
    fprintf(stdfrr, "fLinfTo \n");
    fprintf(stdfrr, "    tx=%f, ty=%f\n", tx, ty);
    fprintf(stdfrr, "    x=%f, y=%f\n", x, y);
    fprintf(stdfrr, "    pbtiX=%f, pbtiY=%f\n", pbtiX, pbtiY);
#fndif

    AWTPbtiGftMorfSpbdfIfNfdfssbry(pbti);

    pbti->fSfgmfntTypf[pbti->fNumbfrOfSfgmfnts++] = (jbytf)fLinfTo;

    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiX;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiY;
}

stbtid void
AWTPbtiQubdTo(void* dbtb, CGPoint p1, CGPoint p2)
{
    CGFlobt x1 = p1.x;
    CGFlobt y1 = p1.y;
    CGFlobt x2 = p2.x;
    CGFlobt y2 = p2.y;

    AWTPbtiRff pbti = (AWTPbtiRff)dbtb;
    CGFlobt tx     = pbti->fTrbnslbtf.widti;
    CGFlobt ty     = pbti->fTrbnslbtf.ifigit;
    CGFlobt pbtiX1 =  x1+tx;
    CGFlobt pbtiY1 = -y1+ty;
    CGFlobt pbtiX2 =  x2+tx;
    CGFlobt pbtiY2 = -y2+ty;

#ifdff AWT_GV_DEBUG
    fprintf(stdfrr, "fQubdTo \n");
    fprintf(stdfrr, "    tx=%f, ty=%f\n", tx, ty);
    fprintf(stdfrr, "    x1=%f, y1=%f\n", x1, y1);
    fprintf(stdfrr, "    x2=%f, y2=%f\n", x2, y2);
    fprintf(stdfrr, "    pbtiX1=%f, pbti1Y=%f\n", pbtiX1, pbtiY1);
    fprintf(stdfrr, "    pbtiX2=%f, pbtiY2=%f\n", pbtiX2, pbtiY2);
#fndif

    AWTPbtiGftMorfSpbdfIfNfdfssbry(pbti);

    pbti->fSfgmfntTypf[pbti->fNumbfrOfSfgmfnts++] = (jbytf)fQubdTo;

    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiX1;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiY1;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiX2;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiY2;
}

stbtid void
AWTPbtiCubidTo(void* dbtb, CGPoint p1, CGPoint p2, CGPoint p3)
{
    CGFlobt x1 = p1.x;
    CGFlobt y1 = p1.y;
    CGFlobt x2 = p2.x;
    CGFlobt y2 = p2.y;
    CGFlobt x3 = p3.x;
    CGFlobt y3 = p3.y;

    AWTPbtiRff pbti = (AWTPbtiRff)dbtb;
    CGFlobt tx     = pbti->fTrbnslbtf.widti;
    CGFlobt ty     = pbti->fTrbnslbtf.ifigit;
    CGFlobt pbtiX1 =  x1+tx;
    CGFlobt pbtiY1 = -y1+ty;
    CGFlobt pbtiX2 =  x2+tx;
    CGFlobt pbtiY2 = -y2+ty;
    CGFlobt pbtiX3 =  x3+tx;
    CGFlobt pbtiY3 = -y3+ty;

#ifdff AWT_GV_DEBUG
    fprintf(stdfrr, "fCubidTo \n");
    fprintf(stdfrr, "    tx=%f, ty=%f\n", tx, ty);
    fprintf(stdfrr, "    x1=%f, y1=%f\n", x1, y1);
    fprintf(stdfrr, "    x2=%f, y2=%f\n", x2, y2);
    fprintf(stdfrr, "    x3=%f, y3=%f\n", x3, y3);
    fprintf(stdfrr, "    pbtiX1=%f, pbti1Y=%f\n", pbtiX1, pbtiY1);
    fprintf(stdfrr, "    pbtiX2=%f, pbtiY2=%f\n", pbtiX2, pbtiY2);
    fprintf(stdfrr, "    pbtiX3=%f, pbtiY3=%f\n", pbtiX3, pbtiY3);
#fndif

    AWTPbtiGftMorfSpbdfIfNfdfssbry(pbti);

    pbti->fSfgmfntTypf[pbti->fNumbfrOfSfgmfnts++] = (jbytf)fCubidTo;

    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiX1;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiY1;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiX2;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiY2;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiX3;
    pbti->fSfgmfntDbtb[pbti->fNumbfrOfDbtbElfmfnts++] = pbtiY3;
}

stbtid void
AWTPbtiClosf(void* dbtb)
{
#ifdff AWT_GV_DEBUG
    fprintf(stdfrr, "GVGlypiPbtiCbllBbdkClosfPbti \n");
#fndif

    AWTPbtiRff pbti = (AWTPbtiRff) dbtb;
    AWTPbtiGftMorfSpbdfIfNfdfssbry(pbti);

    pbti->fSfgmfntTypf[pbti->fNumbfrOfSfgmfnts++] = (jbytf)fClosfPbti;
}

AWTPbtiRff
AWTPbtiCrfbtf(CGSizf trbnslbtf)
{
#ifdff AWT_GV_DEBUG
    fprintf(stdfrr, "AWTPbtiCrfbtf \n");
    fprintf(stdfrr, "    trbnslbtf.widti=%f \n", trbnslbtf.widti);
    fprintf(stdfrr, "    trbnslbtf.ifigit=%f \n", trbnslbtf.ifigit);
#fndif

    AWTPbtiRff pbti = (AWTPbtiRff) mbllod(sizfof(AWTPbti));
    pbti->fTrbnslbtf    = trbnslbtf;
    pbti->fSfgmfntDbtb  = (jflobt*)mbllod(sizfof(jflobt) * kInitiblAllodbtfdPbtiSfgmfnts);
    pbti->fSfgmfntTypf  = (jbytf*)mbllod(sizfof(jbytf) * kInitiblAllodbtfdPbtiSfgmfnts);
    pbti->fNumbfrOfDbtbElfmfnts = 0;
    pbti->fNumbfrOfSfgmfnts = 0;
    pbti->fAllodbtfdSfgmfntTypfSpbdf = kInitiblAllodbtfdPbtiSfgmfnts;
    pbti->fAllodbtfdSfgmfntDbtbSpbdf = kInitiblAllodbtfdPbtiSfgmfnts;

    rfturn pbti;
}

void
AWTPbtiFrff(AWTPbtiRff pbtiRff)
{
#ifdff AWT_GV_DEBUG
    fprintf(stdfrr, "--B--AWTPbtiFrff\n");
    fprintf(stdfrr, "pbtiRff->fSfgmfntDbtb (%p)\n",pbtiRff->fSfgmfntDbtb);
#fndif

    frff(pbtiRff->fSfgmfntDbtb);
    //fprintf(stdfrr, "pbtiRff->fSfgmfntTypf (%d)\n",pbtiRff->fSfgmfntTypf);
    frff(pbtiRff->fSfgmfntTypf);
    //fprintf(stdfrr, "pbtiRff (%d)\n", pbtiRff);
    frff(pbtiRff);
    //fprintf(stdfrr, "--E--AWTPbtiFrff\n");
}

stbtid void
AWTPbtiApplifrCbllbbdk(void *info, donst CGPbtiElfmfnt *flfmfnt)
{
    switdi (flfmfnt->typf) {
    dbsf kCGPbtiElfmfntMovfToPoint:
        AWTPbtiMovfTo(info, flfmfnt->points[0]);
        brfbk;
    dbsf kCGPbtiElfmfntAddLinfToPoint:
        AWTPbtiLinfTo(info, flfmfnt->points[0]);
        brfbk;
    dbsf kCGPbtiElfmfntAddQubdCurvfToPoint:
        AWTPbtiQubdTo(info, flfmfnt->points[0], flfmfnt->points[1]);
        brfbk;
    dbsf kCGPbtiElfmfntAddCurvfToPoint:
        AWTPbtiCubidTo(info, flfmfnt->points[0],
                       flfmfnt->points[1], flfmfnt->points[2]);
        brfbk;
    dbsf kCGPbtiElfmfntClosfSubpbti:
        AWTPbtiClosf(info);
        brfbk;
    }
}

OSStbtus
AWTGftGlypiOutlinf(CGGlypi *glypis, NSFont *font,
                   CGSizf *bdvbndfArrby, CGAffinfTrbnsform *tx,
                   UInt32 inStbrtIndfx, sizf_t lfngti,
                   AWTPbtiRff* outPbti)
{
#ifdff AWT_GV_DEBUG
    fprintf(stdfrr, "AWTGftGlypiOutlinf\n");
    fprintf(stdfrr, "    inAffinfTrbnsform b=%f, b=%f, d=%f, d=%f, tx=%f, ty=%f \n", tx->b, tx->b, tx->d, tx->d, tx->tx, tx->ty);
#fndif

    OSStbtus stbtus = noErr;

    glypis = glypis + inStbrtIndfx;
//    bdvbndfArrby = bdvbndfArrby + inStbrtIndfx; // TODO(dpd): usf bdvbndf

    CGPbtiRff dgPbti = CTFontCrfbtfPbtiForGlypi((CTFontRff)font, glypis[0], tx);
    CGPbtiApply(dgPbti, *outPbti, AWTPbtiApplifrCbllbbdk);
    CGPbtiRflfbsf(dgPbti);

    rfturn stbtus;
}
