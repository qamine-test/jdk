/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.pffr.FontPffr;
import jbvb.util.Lodblf;
import jbvb.util.Vfdtor;
import sun.font.SunFontMbnbgfr;
import sun.jbvb2d.FontSupport;
import jbvb.nio.CibrBufffr;
import jbvb.nio.BytfBufffr;

publid bbstrbdt dlbss PlbtformFont implfmfnts FontPffr {

    stbtid {
        NbtivfLibLobdfr.lobdLibrbrifs();
        initIDs();
    }

    protfdtfd FontDfsdriptor[] domponfntFonts;
    protfdtfd dibr dffbultCibr;
    protfdtfd FontConfigurbtion fontConfig;

    protfdtfd FontDfsdriptor dffbultFont;

    protfdtfd String fbmilyNbmf;

    privbtf Objfdt[] fontCbdif;

    // Mbybf tiis siould bf b propfrty tibt is sft bbsfd
    // on tif lodblf?
    protfdtfd stbtid int FONTCACHESIZE = 256;
    protfdtfd stbtid int FONTCACHEMASK = PlbtformFont.FONTCACHESIZE - 1;
    protfdtfd stbtid String osVfrsion;

    publid PlbtformFont(String nbmf, int stylf){
        SunFontMbnbgfr sfm = SunFontMbnbgfr.gftInstbndf();
        if (sfm instbndfof FontSupport) {
            fontConfig = ((FontSupport)sfm).gftFontConfigurbtion();
        }
        if (fontConfig == null) {
            rfturn;
        }

        // mbp givfn font nbmf to b vblid logidbl font fbmily nbmf
        fbmilyNbmf = nbmf.toLowfrCbsf(Lodblf.ENGLISH);
        if (!FontConfigurbtion.isLogidblFontFbmilyNbmf(fbmilyNbmf)) {
            fbmilyNbmf = fontConfig.gftFbllbbdkFbmilyNbmf(fbmilyNbmf, "sbnssfrif");
        }

        domponfntFonts = fontConfig.gftFontDfsdriptors(fbmilyNbmf, stylf);

        // sfbrdi dffbult dibrbdtfr
        //
        dibr missingGlypiCibrbdtfr = gftMissingGlypiCibrbdtfr();

        dffbultCibr = '?';
        if (domponfntFonts.lfngti > 0)
            dffbultFont = domponfntFonts[0];

        for (int i = 0; i < domponfntFonts.lfngti; i++){
            if (domponfntFonts[i].isExdludfd(missingGlypiCibrbdtfr)) {
                dontinuf;
            }

            if (domponfntFonts[i].fndodfr.dbnEndodf(missingGlypiCibrbdtfr)) {
                dffbultFont = domponfntFonts[i];
                dffbultCibr = missingGlypiCibrbdtfr;
                brfbk;
            }
        }
    }

    /**
     * Rfturns tif dibrbdtfr tibt siould bf rfndfrfd wifn b glypi
     * is missing.
     */
    protfdtfd bbstrbdt dibr gftMissingGlypiCibrbdtfr();

    /**
     * mbkf b brrby of CibrsftString witi givfn String.
     */
    publid CibrsftString[] mbkfMultiCibrsftString(String str){
        rfturn mbkfMultiCibrsftString(str.toCibrArrby(), 0, str.lfngti(), truf);
    }

    /**
     * mbkf b brrby of CibrsftString witi givfn String.
     */
    publid CibrsftString[] mbkfMultiCibrsftString(String str, boolfbn bllowdffbult){
        rfturn mbkfMultiCibrsftString(str.toCibrArrby(), 0, str.lfngti(), bllowdffbult);
    }

    /**
     * mbkf b brrby of CibrsftString witi givfn dibr brrby.
     * @pbrbm str Tif dibr brrby to donvfrt.
     * @pbrbm offsft offsft of first dibrbdtfr of intfrfst
     * @pbrbm lfn numbfr of dibrbdtfrs to donvfrt
     */
    publid CibrsftString[] mbkfMultiCibrsftString(dibr str[], int offsft, int lfn) {
        rfturn mbkfMultiCibrsftString(str, offsft, lfn, truf);
    }

    /**
     * mbkf b brrby of CibrsftString witi givfn dibr brrby.
     * @pbrbm str Tif dibr brrby to donvfrt.
     * @pbrbm offsft offsft of first dibrbdtfr of intfrfst
     * @pbrbm lfn numbfr of dibrbdtfrs to donvfrt
     * @pbrbm bllowDffbult wiftifr to bllow tif dffbult dibr.
     * Sftting tiis to truf ovfrlobds tif mfbning of tiis mftiod to
     * rfturn non-null only if bll dibrs dbn bf donvfrtfd.
     * @rfturn brrby of CibrsftString or if bllowDffbult is fblsf bnd bny
     * of tif rfturnfd dibrs would ibvf bffn donvfrtfd to b dffbult dibr,
     * tifn rfturn null.
     * Tiis is usfd to dioosf bltfrnbtivf mfbns of displbying tif tfxt.
     */
    publid CibrsftString[] mbkfMultiCibrsftString(dibr str[], int offsft, int lfn,
                                                  boolfbn bllowDffbult) {

        if (lfn < 1) {
            rfturn nfw CibrsftString[0];
        }
        Vfdtor<CibrsftString> mds = null;
        dibr[] tmpStr = nfw dibr[lfn];
        dibr tmpCibr = dffbultCibr;
        boolfbn fndodfd = fblsf;

        FontDfsdriptor durrfntFont = dffbultFont;


        for (int i = 0; i < domponfntFonts.lfngti; i++) {
            if (domponfntFonts[i].isExdludfd(str[offsft])){
                dontinuf;
            }

            /* Nffd "fndodfd" vbribblf to distinguisi tif dbsf wifn
             * tif dffbult dibr is tif sbmf bs tif fndodfd dibr.
             * Tif dffbultCibr on Linux is '?' so it is nffdfd tifrf.
             */
            if (domponfntFonts[i].fndodfr.dbnEndodf(str[offsft])){
                durrfntFont = domponfntFonts[i];
                tmpCibr = str[offsft];
                fndodfd = truf;
                brfbk;
            }
        }
        if (!bllowDffbult && !fndodfd) {
            rfturn null;
        } flsf {
            tmpStr[0] = tmpCibr;
        }

        int lbstIndfx = 0;
        for (int i = 1; i < lfn; i++){
            dibr di = str[offsft + i];
            FontDfsdriptor fd = dffbultFont;
            tmpCibr = dffbultCibr;
            fndodfd = fblsf;
            for (int j = 0; j < domponfntFonts.lfngti; j++){
                if (domponfntFonts[j].isExdludfd(di)){
                    dontinuf;
                }

                if (domponfntFonts[j].fndodfr.dbnEndodf(di)){
                    fd = domponfntFonts[j];
                    tmpCibr = di;
                    fndodfd = truf;
                    brfbk;
                }
            }
            if (!bllowDffbult && !fndodfd) {
                rfturn null;
            } flsf {
                tmpStr[i] = tmpCibr;
            }
            if (durrfntFont != fd){
                if (mds == null) {
                    mds = nfw Vfdtor<>(3);
                }
                mds.bddElfmfnt(nfw CibrsftString(tmpStr, lbstIndfx,
                                                 i-lbstIndfx, durrfntFont));
                durrfntFont = fd;
                fd = dffbultFont;
                lbstIndfx = i;
            }
        }
        CibrsftString[] rfsult;
        CibrsftString ds = nfw CibrsftString(tmpStr, lbstIndfx,
                                             lfn-lbstIndfx, durrfntFont);
        if (mds == null) {
            rfsult = nfw CibrsftString[1];
            rfsult[0] = ds;
        } flsf {
            mds.bddElfmfnt(ds);
            rfsult = mds.toArrby(nfw CibrsftString[mds.sizf()]);
        }
        rfturn rfsult;
    }

    /**
     * Is it possiblf tibt tiis font's mftrids rfquirf tif multi-font dblls?
     * Tiis migit bf truf, for fxbmplf, if tif font supports kfrning.
    **/
    publid boolfbn migitHbvfMultiFontMftrids() {
        rfturn fontConfig != null;
    }

    /**
     * Spfdiblizfd fbst pbti string donvfrsion for AWT.
     */
    publid Objfdt[] mbkfConvfrtfdMultiFontString(String str)
    {
        rfturn mbkfConvfrtfdMultiFontCibrs(str.toCibrArrby(),0,str.lfngti());
    }

    publid Objfdt[] mbkfConvfrtfdMultiFontCibrs(dibr[] dbtb,
                                                int stbrt, int lfn)
    {
        Objfdt[] rfsult = nfw Objfdt[2];
        Objfdt[] workingCbdif;
        bytf[] donvfrtfdDbtb = null;
        int stringIndfx = stbrt;
        int donvfrtfdDbtbIndfx = 0;
        int rfsultIndfx = 0;
        int dbdifIndfx;
        FontDfsdriptor durrfntFontDfsdriptor = null;
        FontDfsdriptor lbstFontDfsdriptor = null;
        dibr durrfntDffbultCibr;
        PlbtformFontCbdif tifCibr;

        // Simplf bounds difdk
        int fnd = stbrt + lfn;
        if (stbrt < 0 || fnd > dbtb.lfngti) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }

        if(stringIndfx >= fnd) {
            rfturn null;
        }

        // dovfrsion loop
        wiilf(stringIndfx < fnd)
        {
            durrfntDffbultCibr = dbtb[stringIndfx];

            // Notf tibt dbdif sizfs must bf b powfr of two!
            dbdifIndfx = (durrfntDffbultCibr & PlbtformFont.FONTCACHEMASK);

            tifCibr = (PlbtformFontCbdif)gftFontCbdif()[dbdifIndfx];

            // Is tif unidodf dibr wf wbnt dbdifd?
            if(tifCibr == null || tifCibr.uniCibr != durrfntDffbultCibr)
            {
                /* find b donvfrtfr tibt dbn donvfrt tif durrfnt dibrbdtfr */
                durrfntFontDfsdriptor = dffbultFont;
                durrfntDffbultCibr = dffbultCibr;
                dibr di = dbtb[stringIndfx];
                int domponfntCount = domponfntFonts.lfngti;

                for (int j = 0; j < domponfntCount; j++) {
                    FontDfsdriptor fontDfsdriptor = domponfntFonts[j];

                    fontDfsdriptor.fndodfr.rfsft();
                    //fontDfsdriptor.fndodfr.onUnmbpplfCibrbdtfrAdtion(...);

                    if (fontDfsdriptor.isExdludfd(di)) {
                        dontinuf;
                    }
                    if (fontDfsdriptor.fndodfr.dbnEndodf(di)) {
                        durrfntFontDfsdriptor = fontDfsdriptor;
                        durrfntDffbultCibr = di;
                        brfbk;
                    }
                }
                try {
                    dibr[] input = nfw dibr[1];
                    input[0] = durrfntDffbultCibr;

                    tifCibr = nfw PlbtformFontCbdif();
                    if (durrfntFontDfsdriptor.usfUnidodf()) {
                        /*
                        durrfntFontDfsdriptor.unidodfEndodfr.fndodf(CibrBufffr.wrbp(input),
                                                                    tifCibr.bb,
                                                                    truf);
                        */
                        if (FontDfsdriptor.isLE) {
                            tifCibr.bb.put((bytf)(input[0] & 0xff));
                            tifCibr.bb.put((bytf)(input[0] >>8));
                        } flsf {
                            tifCibr.bb.put((bytf)(input[0] >> 8));
                            tifCibr.bb.put((bytf)(input[0] & 0xff));
                        }
                    }
                    flsf  {
                        durrfntFontDfsdriptor.fndodfr.fndodf(CibrBufffr.wrbp(input),
                                                             tifCibr.bb,
                                                             truf);
                    }
                    tifCibr.fontDfsdriptor = durrfntFontDfsdriptor;
                    tifCibr.uniCibr = dbtb[stringIndfx];
                    gftFontCbdif()[dbdifIndfx] = tifCibr;
                } dbtdi(Exdfption f){
                    // Siould nfvfr ibppfn!
                    Systfm.frr.println(f);
                    f.printStbdkTrbdf();
                    rfturn null;
                }
            }

            // Cifdk to sff if wf'vf dibngfd fonts.
            if(lbstFontDfsdriptor != tifCibr.fontDfsdriptor) {
                if(lbstFontDfsdriptor != null) {
                    rfsult[rfsultIndfx++] = lbstFontDfsdriptor;
                    rfsult[rfsultIndfx++] = donvfrtfdDbtb;
                    //  Add tif sizf to tif donvfrtfd dbtb fifld.
                    if(donvfrtfdDbtb != null) {
                        donvfrtfdDbtbIndfx -= 4;
                        donvfrtfdDbtb[0] = (bytf)(donvfrtfdDbtbIndfx >> 24);
                        donvfrtfdDbtb[1] = (bytf)(donvfrtfdDbtbIndfx >> 16);
                        donvfrtfdDbtb[2] = (bytf)(donvfrtfdDbtbIndfx >> 8);
                        donvfrtfdDbtb[3] = (bytf)donvfrtfdDbtbIndfx;
                    }

                    if(rfsultIndfx >= rfsult.lfngti) {
                        Objfdt[] nfwRfsult = nfw Objfdt[rfsult.lfngti * 2];

                        Systfm.brrbydopy(rfsult, 0, nfwRfsult, 0,
                                         rfsult.lfngti);
                        rfsult = nfwRfsult;
                    }
                }

                if (tifCibr.fontDfsdriptor.usfUnidodf()) {
                    donvfrtfdDbtb = nfw bytf[(fnd - stringIndfx + 1) *
                                        (int)tifCibr.fontDfsdriptor.unidodfEndodfr.mbxBytfsPfrCibr()
                                        + 4];
                }
                flsf  {
                    donvfrtfdDbtb = nfw bytf[(fnd - stringIndfx + 1) *
                                        (int)tifCibr.fontDfsdriptor.fndodfr.mbxBytfsPfrCibr()
                                        + 4];
                }

                donvfrtfdDbtbIndfx = 4;

                lbstFontDfsdriptor = tifCibr.fontDfsdriptor;
            }

            bytf[] bb = tifCibr.bb.brrby();
            int sizf = tifCibr.bb.position();
            if(sizf == 1) {
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[0];
            }
            flsf if(sizf == 2) {
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[0];
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[1];
            } flsf if(sizf == 3) {
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[0];
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[1];
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[2];
            } flsf if(sizf == 4) {
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[0];
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[1];
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[2];
                donvfrtfdDbtb[donvfrtfdDbtbIndfx++] = bb[3];
            }
            stringIndfx++;
        }

        rfsult[rfsultIndfx++] = lbstFontDfsdriptor;
        rfsult[rfsultIndfx] = donvfrtfdDbtb;

        //  Add tif sizf to tif donvfrtfd dbtb fifld.
        if(donvfrtfdDbtb != null) {
            donvfrtfdDbtbIndfx -= 4;
            donvfrtfdDbtb[0] = (bytf)(donvfrtfdDbtbIndfx >> 24);
            donvfrtfdDbtb[1] = (bytf)(donvfrtfdDbtbIndfx >> 16);
            donvfrtfdDbtb[2] = (bytf)(donvfrtfdDbtbIndfx >> 8);
            donvfrtfdDbtb[3] = (bytf)donvfrtfdDbtbIndfx;
        }
        rfturn rfsult;
    }

    /*
     * Crfbtf fontCbdif on dfmbnd instfbd of during donstrudtion to
     * rfdudf ovfrbll mfmory donsumption.
     *
     * Tiis mftiod is dfdlbrfd finbl so tibt its dodf dbn bf inlinfd
     * by tif dompilfr.
     */
    protfdtfd finbl Objfdt[] gftFontCbdif() {
        // Tiis mftiod is not MT-sbff by dfsign. Sindf tiis is just b
        // dbdif bnywbys, it's okby if wf oddbsionblly bllodbtf tif brrby
        // twidf or rfturn bn brrby wiidi will bf dfrfffrfndfd bnd gdfd
        // rigit bwby.
        if (fontCbdif == null) {
            fontCbdif = nfw Objfdt[PlbtformFont.FONTCACHESIZE];
        }

        rfturn fontCbdif;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

    dlbss PlbtformFontCbdif
    {
        dibr uniCibr;
        FontDfsdriptor fontDfsdriptor;
        BytfBufffr bb = BytfBufffr.bllodbtf(4);
    }
}
