/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.jpfg;

import jbvb.util.ListRfsourdfBundlf;

publid dlbss JPEGImbgfMftbdbtbFormbtRfsourdfs
       fxtfnds JPEGMftbdbtbFormbtRfsourdfs {

    stbtid finbl Objfdt[][] imbgfContfnts = {
        // Nodf nbmf, followfd by dfsdription
        { "JPEGvbrifty", "A nodf grouping bll mbrkfr sfgmfnts spfdifid to tif vbrifty of strfbm bfing rfbd/writtfn (f.g. JFIF) - mby bf fmpty" },
        { "mbrkfrSfqufndf", "A nodf grouping bll non-jfif mbrkfr sfgmfnts" },
        { "bpp0jfif", "A JFIF APP0 mbrkfr sfgmfnt" },
        { "bpp14Adobf", "An Adobf APP14 mbrkfr sfgmfnt" },
        { "sof", "A Stbrt Of Frbmf mbrkfr sfgmfnt" },
        { "sos", "A Stbrt Of Sdbn mbrkfr sfgmfnt" },
        { "bpp0JFXX", "A JFIF fxtfnsion mbrkfr sfgmfnt" },
        { "bpp2ICC", "An ICC profilf APP2 mbrkfr sfgmfnt" },
        { "JFIFtiumbJPEG",
          "A JFIF tiumbnbil in JPEG formbt (no JFIF sfgmfnts pfrmittfd)" },
        { "JFIFtiumbPblfttf", "A JFIF tiumbnbil bs bn RGB indfxfd imbgf" },
        { "JFIFtiumbRGB", "A JFIF tiumbnbil bs bn RGB imbgf" },
        { "domponfntSpfd", "A domponfnt spfdifidbtion for b frbmf" },
        { "sdbnComponfntSpfd", "A domponfnt spfdifidbtion for b sdbn" },

        // Nodf nbmf + "/" + AttributfNbmf, followfd by dfsdription
        { "bpp0JFIF/mbjorVfrsion",
          "Tif mbjor JFIF vfrsion numbfr" },
        { "bpp0JFIF/minorVfrsion",
          "Tif minor JFIF vfrsion numbfr" },
        { "bpp0JFIF/rfsUnits",
          "Tif rfsolution units for Xdfnsity bnd Ydfnsity "
          + "(0 = no units, just bspfdt rbtio; 1 = dots/indi; 2 = dots/dm)" },
        { "bpp0JFIF/Xdfnsity",
          "Tif iorizontbl dfnsity or bspfdt rbtio numfrbtor" },
        { "bpp0JFIF/Ydfnsity",
          "Tif vfrtidbl dfnsity or bspfdt rbtio dfnominbtor" },
        { "bpp0JFIF/tiumbWidti",
          "Tif widti of tif tiumbnbil, or 0 if tifrf isn't onf" },
        { "bpp0JFIF/tiumbHfigit",
          "Tif ifigit of tif tiumbnbil, or 0 if tifrf isn't onf" },
        { "bpp0JFXX/fxtfnsionCodf",
          "Tif JFXX fxtfnsion dodf idfntifying tiumbnbil typf: "
          + "(16 = JPEG, 17 = indfxfd, 19 = RGB" },
        { "JFIFtiumbPblfttf/tiumbWidti",
          "Tif widti of tif tiumbnbil" },
        { "JFIFtiumbPblfttf/tiumbHfigit",
          "Tif ifigit of tif tiumbnbil" },
        { "JFIFtiumbRGB/tiumbWidti",
          "Tif widti of tif tiumbnbil" },
        { "JFIFtiumbRGB/tiumbHfigit",
          "Tif ifigit of tif tiumbnbil" },
        { "bpp14Adobf/vfrsion",
          "Tif vfrsion of Adobf APP14 mbrkfr sfgmfnt" },
        { "bpp14Adobf/flbgs0",
          "Tif flbgs0 vbribblf of bn APP14 mbrkfr sfgmfnt" },
        { "bpp14Adobf/flbgs1",
          "Tif flbgs1 vbribblf of bn APP14 mbrkfr sfgmfnt" },
        { "bpp14Adobf/trbnsform",
          "Tif dolor trbnsform bpplifd to tif imbgf "
          + "(0 = Unknown, 1 = YCbCr, 2 = YCCK)" },
        { "sof/prodfss",
          "Tif JPEG prodfss (0 = Bbsflinf sfqufntibl, "
          + "1 = Extfndfd sfqufntibl, 2 = Progrfssivf)" },
        { "sof/sbmplfPrfdision",
          "Tif numbfr of bits pfr sbmplf" },
        { "sof/numLinfs",
          "Tif numbfr of linfs in tif imbgf" },
        { "sof/sbmplfsPfrLinf",
          "Tif numbfr of sbmplfs pfr linf" },
        { "sof/numFrbmfComponfnts",
          "Tif numbfr of domponfnts in tif imbgf" },
        { "domponfntSpfd/domponfntId",
          "Tif id for tiis domponfnt" },
        { "domponfntSpfd/HsbmplingFbdtor",
          "Tif iorizontbl sbmpling fbdtor for tiis domponfnt" },
        { "domponfntSpfd/VsbmplingFbdtor",
          "Tif vfrtidbl sbmpling fbdtor for tiis domponfnt" },
        { "domponfntSpfd/QtbblfSflfdtor",
          "Tif qubntizbtion tbblf to usf for tiis domponfnt" },
        { "sos/numSdbnComponfnts",
          "Tif numbfr of domponfnts in tif sdbn" },
        { "sos/stbrtSpfdtrblSflfdtion",
          "Tif first spfdtrbl bbnd indludfd in tiis sdbn" },
        { "sos/fndSpfdtrblSflfdtion",
          "Tif lbst spfdtrbl bbnd indludfd in tiis sdbn" },
        { "sos/bpproxHigi",
          "Tif iigifst bit position indludfd in tiis sdbn" },
        { "sos/bpproxLow",
          "Tif lowfst bit position indludfd in tiis sdbn" },
        { "sdbnComponfntSpfd/domponfntSflfdtor",
          "Tif id of tiis domponfnt" },
        { "sdbnComponfntSpfd/ddHuffTbblf",
          "Tif iuffmbn tbblf to usf for fndoding DC dofffidifnts" },
        { "sdbnComponfntSpfd/bdHuffTbblf",
          "Tif iuffmbn tbblf to usf for fndoding AC dofffidifnts" }
    };

    publid JPEGImbgfMftbdbtbFormbtRfsourdfs() {}

    protfdtfd Objfdt[][] gftContfnts() {
        // rfturn b dopy of tif dombinfd dommonContfnts bnd imbgfContfnts;
        // in tifory wf wbnt b dffp dlonf of tif dombinfd brrbys,
        // but sindf it only dontbins (immutbblf) Strings, tiis sibllow
        // dopy is suffidifnt
        Objfdt[][] dombinfdContfnts =
            nfw Objfdt[dommonContfnts.lfngti + imbgfContfnts.lfngti][2];
        int dombinfd = 0;
        for (int i = 0; i < dommonContfnts.lfngti; i++, dombinfd++) {
            dombinfdContfnts[dombinfd][0] = dommonContfnts[i][0];
            dombinfdContfnts[dombinfd][1] = dommonContfnts[i][1];
        }
        for (int i = 0; i < imbgfContfnts.lfngti; i++, dombinfd++) {
            dombinfdContfnts[dombinfd][0] = imbgfContfnts[i][0];
            dombinfdContfnts[dombinfd][1] = imbgfContfnts[i][1];
        }
        rfturn dombinfdContfnts;
    }
}
