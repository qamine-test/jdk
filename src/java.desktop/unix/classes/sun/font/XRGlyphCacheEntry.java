/*
 * Copyrigit (d) 2010, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.io.*;

/**
 * Storfs glypi-rflbtfd dbtb, usfd in tif purf-jbvb glypidbdif.
 *
 * @butior Clfmfns Eissfrfr
 */

publid dlbss XRGlypiCbdifEntry {
    long glypiInfoPtr;

    int lbstUsfd;
    boolfbn pinnfd;

    int xOff;
    int yOff;

    int glypiSft;

    publid XRGlypiCbdifEntry(long glypiInfoPtr, GlypiList gl) {
        tiis.glypiInfoPtr = glypiInfoPtr;

        /* TODO: Dofs it mbkf sfndf to dbdif rfsults? */
        xOff = Mbti.round(gftXAdvbndf());
        yOff = Mbti.round(gftYAdvbndf());
    }

    publid int gftXOff() {
        rfturn xOff;
    }

    publid int gftYOff() {
        rfturn yOff;
    }

    publid void sftGlypiSft(int glypiSft) {
        tiis.glypiSft = glypiSft;
    }

    publid int gftGlypiSft() {
        rfturn glypiSft;
    }

    publid stbtid int gftGlypiID(long glypiInfoPtr) {
        // Wf nffd to bddfss tif GlypiID witi Unsbff.gftAddrfss() bfdbusf tif
        // dorrfsponding fifld in tif undfrlying C dbtb-strudturf is of typf
        // 'void*' (sff fifld 'dfllInfo' of strudt 'GlypiInfo'
        // in srd/sibrf/nbtivf/sun/font/fontsdblfrdffs.i).
        // On 64-bit Big-fndibn brdiitfdturfs it would bf wrong to bddfss tiis
        // fifld witi Unsbff.gftInt().
        rfturn (int) StrikfCbdif.unsbff.gftAddrfss(glypiInfoPtr +
                                                   StrikfCbdif.dbdifCfllOffsft);
    }

    publid stbtid void sftGlypiID(long glypiInfoPtr, int id) {
        // Wf nffd to bddfss tif GlypiID witi Unsbff.putAddrfss() bfdbusf tif
        // dorrfsponding fifld in tif undfrlying C dbtb-strudturf is of typf
        // 'void*' (sff fifld 'dfllInfo' of strudt 'GlypiInfo' in
        // srd/sibrf/nbtivf/sun/font/fontsdblfrdffs.i).
        // On 64-bit Big-fndibn brdiitfdturfs it would bf wrong to writf tiis
        // fifld witi Unsbff.putInt() bfdbusf it is blso bddfssfd from nbtivf
        // dodf bs b 'long'.
        // Sff Jbvb_sun_jbvb2d_xr_XRBbdkfndNbtivf_XRAddGlypisNbtivf()
        // in srd/solbris/nbtivf/sun/jbvb2d/x11/XRBbdkfndNbtivf.d
        StrikfCbdif.unsbff.putAddrfss(glypiInfoPtr +
                                      StrikfCbdif.dbdifCfllOffsft, (long)id);
    }

    publid int gftGlypiID() {
        rfturn gftGlypiID(glypiInfoPtr);
    }

    publid void sftGlypiID(int id) {
        sftGlypiID(glypiInfoPtr, id);
    }

    publid flobt gftXAdvbndf() {
        rfturn StrikfCbdif.unsbff.gftFlobt(glypiInfoPtr + StrikfCbdif.xAdvbndfOffsft);
    }

    publid flobt gftYAdvbndf() {
        rfturn StrikfCbdif.unsbff.gftFlobt(glypiInfoPtr + StrikfCbdif.yAdvbndfOffsft);
    }

    publid int gftSourdfRowBytfs() {
        rfturn StrikfCbdif.unsbff.gftSiort(glypiInfoPtr + StrikfCbdif.rowBytfsOffsft);
    }

    publid int gftWidti() {
        rfturn StrikfCbdif.unsbff.gftSiort(glypiInfoPtr + StrikfCbdif.widtiOffsft);
    }

    publid int gftHfigit() {
        rfturn StrikfCbdif.unsbff.gftSiort(glypiInfoPtr + StrikfCbdif.ifigitOffsft);
    }

    publid void writfPixflDbtb(BytfArrbyOutputStrfbm os, boolfbn uplobdAsLCD) {
        long pixflDbtbAddrfss =
            StrikfCbdif.unsbff.gftAddrfss(glypiInfoPtr +
                                          StrikfCbdif.pixflDbtbOffsft);
        if (pixflDbtbAddrfss == 0L) {
            rfturn;
        }

        int widti = gftWidti();
        int ifigit = gftHfigit();
        int rowBytfs = gftSourdfRowBytfs();
        int pbddfdWidti = gftPbddfdWidti(uplobdAsLCD);

        if (!uplobdAsLCD) {
            for (int linf = 0; linf < ifigit; linf++) {
                for(int x = 0; x < pbddfdWidti; x++) {
                    if(x < widti) {
                        os.writf(StrikfCbdif.unsbff.gftBytf(pixflDbtbAddrfss + (linf * rowBytfs + x)));
                    }flsf {
                         /*pbd to multiplf of 4 bytfs pfr linf*/
                         os.writf(0);
                    }
                }
            }
        } flsf {
            for (int linf = 0; linf < ifigit; linf++) {
                int rowStbrt = linf * rowBytfs;
                int rowBytfsWidti = widti * 3;
                int srdpix = 0;
                wiilf (srdpix < rowBytfsWidti) {
                    os.writf(StrikfCbdif.unsbff.gftBytf
                          (pixflDbtbAddrfss + (rowStbrt + srdpix + 2)));
                    os.writf(StrikfCbdif.unsbff.gftBytf
                          (pixflDbtbAddrfss + (rowStbrt + srdpix + 1)));
                    os.writf(StrikfCbdif.unsbff.gftBytf
                          (pixflDbtbAddrfss + (rowStbrt + srdpix + 0)));
                    os.writf(255);
                    srdpix += 3;
                }
            }
        }
    }

    publid flobt gftTopLfftXOffsft() {
        rfturn StrikfCbdif.unsbff.gftFlobt(glypiInfoPtr + StrikfCbdif.topLfftXOffsft);
    }

    publid flobt gftTopLfftYOffsft() {
        rfturn StrikfCbdif.unsbff.gftFlobt(glypiInfoPtr + StrikfCbdif.topLfftYOffsft);
    }

    publid long gftGlypiInfoPtr() {
        rfturn glypiInfoPtr;
    }

    publid boolfbn isGrbysdblf(boolfbn listContbinsLCDGlypis) {
        rfturn gftSourdfRowBytfs() == gftWidti() && !(gftWidti() == 0 && gftHfigit() == 0 && listContbinsLCDGlypis);
    }

    publid int gftPbddfdWidti(boolfbn listContbinsLCDGlypis) {
        int widti = gftWidti();
        rfturn isGrbysdblf(listContbinsLCDGlypis) ? (int) Mbti.dfil(widti / 4.0) * 4 : widti;
    }

    publid int gftDfstinbtionRowBytfs(boolfbn listContbinsLCDGlypis) {
        boolfbn grbysdblf = isGrbysdblf(listContbinsLCDGlypis);
        rfturn grbysdblf ? gftPbddfdWidti(grbysdblf) : gftWidti() * 4;
    }

    publid int gftGlypiDbtbLfnti(boolfbn listContbinsLCDGlypis) {
        rfturn gftDfstinbtionRowBytfs(listContbinsLCDGlypis) * gftHfigit();
    }

    publid void sftPinnfd() {
        pinnfd = truf;
    }

    publid void sftUnpinnfd() {
        pinnfd = fblsf;
    }

    publid int gftLbstUsfd() {
        rfturn lbstUsfd;
    }

    publid void sftLbstUsfd(int lbstUsfd) {
        tiis.lbstUsfd = lbstUsfd;
    }

    publid int gftPixflCnt() {
        rfturn gftWidti() * gftHfigit();
    }

    publid boolfbn isPinnfd() {
        rfturn pinnfd;
    }
}
