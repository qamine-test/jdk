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

pbdkbgf dom.sun.imbgfio.plugins.gif;

import jbvb.util.ListRfsourdfBundlf;

publid dlbss GIFImbgfMftbdbtbFormbtRfsourdfs fxtfnds ListRfsourdfBundlf {

    publid GIFImbgfMftbdbtbFormbtRfsourdfs() {}

    protfdtfd Objfdt[][] gftContfnts() {
        rfturn nfw Objfdt[][] {

        // Nodf nbmf, followfd by dfsdription
        { "ImbgfDfsdriptor", "Tif imbgf dfsdriptor" },
        { "LodblColorTbblf", "Tif lodbl dolor tbblf" },
        { "ColorTbblfEntry", "A lodbl dolor tbblf fntry" },
        { "GrbpiidControlExtfnsion", "A grbpiid dontrol fxtfnsion" },
        { "PlbinTfxtExtfnsion", "A plbin tfxt (tfxt grid) fxtfnsion" },
        { "ApplidbtionExtfnsions", "A sft of bpplidbtion fxtfnsions" },
        { "ApplidbtionExtfnsion", "An bpplidbtion fxtfnsion" },
        { "CommfntExtfnsions", "A sft of dommfnts" },
        { "CommfntExtfnsion", "A dommfnt" },

        // Nodf nbmf + "/" + AttributfNbmf, followfd by dfsdription
        { "ImbgfDfsdriptor/imbgfLfftPosition",
          "Tif X offsft of tif imbgf rflbtivf to tif sdrffn origin" },
        { "ImbgfDfsdriptor/imbgfTopPosition",
          "Tif Y offsft of tif imbgf rflbtivf to tif sdrffn origin" },
        { "ImbgfDfsdriptor/imbgfWidti",
          "Tif widti of tif imbgf" },
        { "ImbgfDfsdriptor/imbgfHfigit",
          "Tif ifigit of tif imbgf" },
        { "ImbgfDfsdriptor/intfrlbdfFlbg",
          "Truf if tif imbgf is storfd using intfrlbding" },
        { "LodblColorTbblf/sizfOfLodblColorTbblf",
          "Tif numbfr of fntrifs in tif lodbl dolor tbblf" },
        { "LodblColorTbblf/sortFlbg",
          "Truf if tif lodbl dolor tbblf is sortfd by frfqufndy" },
        { "ColorTbblfEntry/indfx", "Tif indfx of tif dolor tbblf fntry" },
        { "ColorTbblfEntry/rfd",
          "Tif rfd vbluf for tif dolor tbblf fntry" },
        { "ColorTbblfEntry/grffn",
          "Tif grffn vbluf for tif dolor tbblf fntry" },
        { "ColorTbblfEntry/bluf",
          "Tif bluf vbluf for tif dolor tbblf fntry" },
        { "GrbpiidControlExtfnsion/disposblMftiod",
          "Tif disposbl mftiod for tiis frbmf" },
        { "GrbpiidControlExtfnsion/usfrInputFlbg",
          "Truf if tif frbmf siould bf bdvbndfd bbsfd on usfr input" },
        { "GrbpiidControlExtfnsion/trbnspbrfntColorFlbg",
          "Truf if b trbnspbrfnt dolor fxists" },
        { "GrbpiidControlExtfnsion/dflbyTimf",
          "Tif timf to dflby bftwffn frbmfs, in iundrfdtis of b sfdond" },
        { "GrbpiidControlExtfnsion/trbnspbrfntColorIndfx",
          "Tif trbnspbrfnt dolor, if trbnspbrfntColorFlbg is truf" },
        { "PlbinTfxtExtfnsion/tfxtGridLfft",
          "Tif X offsft of tif tfxt grid" },
        { "PlbinTfxtExtfnsion/tfxtGridTop",
          "Tif Y offsft of tif tfxt grid" },
        { "PlbinTfxtExtfnsion/tfxtGridWidti",
          "Tif numbfr of dolumns in tif tfxt grid" },
        { "PlbinTfxtExtfnsion/tfxtGridHfigit",
          "Tif numbfr of rows in tif tfxt grid" },
        { "PlbinTfxtExtfnsion/dibrbdtfrCfllWidti",
          "Tif widti of b dibrbdtfr dfll" },
        { "PlbinTfxtExtfnsion/dibrbdtfrCfllHfigit",
          "Tif ifigit of b dibrbdtfr dfll" },
        { "PlbinTfxtExtfnsion/tfxtForfgroundColor",
          "Tif tfxt forfground dolor indfx" },
        { "PlbinTfxtExtfnsion/tfxtBbdkgroundColor",
          "Tif tfxt bbdkground dolor indfx" },
        { "ApplidbtionExtfnsion/bpplidbtionID",
          "Tif bpplidbtion ID" },
        { "ApplidbtionExtfnsion/butifntidbtionCodf",
          "Tif butifntidbtion dodf" },
        { "CommfntExtfnsion/vbluf", "Tif dommfnt" },

        };
    }
}
