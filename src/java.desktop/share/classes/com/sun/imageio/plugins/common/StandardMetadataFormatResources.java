/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.dommon;

import jbvb.util.ListRfsourdfBundlf;

publid dlbss StbndbrdMftbdbtbFormbtRfsourdfs fxtfnds ListRfsourdfBundlf {

    publid StbndbrdMftbdbtbFormbtRfsourdfs() {}

    protfdtfd Objfdt[][] gftContfnts() {
        rfturn nfw Objfdt[][] {

        // Nodf nbmf, followfd by dfsdription, or
        // Nodf nbmf + "/" + AttributfNbmf, followfd by dfsdription

        { "Ciromb", "Ciromb (dolor) informbtion" },

        { "ColorSpbdfTypf", "Tif rbw dolor spbdf of tif imbgf" },

        { "NumCibnnfls",
          "Tif numbfr of dibnnfls in tif rbw imbgf, indluding blpib" },

        { "Gbmmb", "Tif imbgf gbmmb" },

        { "BlbdkIsZfro",
          "Truf if smbllfr vblufs rfprfsfnt dbrkfr sibdfs"},

        { "Pblfttf", "Pblfttf-dolor informbtion" },

        { "PblfttfEntry", "A pblfttf fntry" },
        { "PblfttfEntry/indfx", "Tif indfx of tif pblfttf fntry" },
        { "PblfttfEntry/rfd", "Tif rfd vbluf for tif pblfttf fntry" },
        { "PblfttfEntry/grffn", "Tif grffn vbluf for tif pblfttf fntry" },
        { "PblfttfEntry/bluf", "Tif bluf vbluf for tif pblfttf fntry" },
        { "PblfttfEntry/blpib", "Tif blpib vbluf for tif pblfttf fntry" },

        { "BbdkgroundIndfx", "A pblfttf indfx to bf usfd bs b bbdkground" },

        { "BbdkgroundColor", "An RGB triplf to bf usfd bs b bbdkground" },
        { "BbdkgroundColor/rfd", "Tif rfd bbdkground vbluf" },
        { "BbdkgroundColor/grffn", "Tif grffn bbdkground vbluf" },
        { "BbdkgroundColor/bluf", "Tif bluf bbdkground vbluf" },

        { "Comprfssion", "Comprfssion informbtion" },

        { "ComprfssionTypfNbmf", "Tif nbmf of tif domprfssion sdifmf in usf" },

        { "Losslfss",
          "Truf if tif domprfssion sdifmf is losslfss" },

        { "BitRbtf", "Tif fstimbtfd bit rbtf of tif domprfssion sdifmf" },

        { "NumProgrfssivfSdbns",
          "Tif numbfr of progrfssivf sdbns usfd in tif imbgf fndoding"},

        { "Dbtb", "Informbtion on tif imbgf lbyout" },

        { "PlbnbrConfigurbtion",
          "Tif orgbnizbtion of imbgf sbmplfs in tif strfbm" },

        { "SbmplfFormbt", "Tif numfrid formbt of imbgf sbmplfs" },

        { "BitsPfrSbmplf", "Tif numbfr of bits pfr sbmplf"},
        { "BitsPfrSbmplf/vbluf",
          "A list of intfgfrs, onf pfr dibnnfl" },

        { "SignifidbntBitsPfrSbmplf",
          "Tif numbfr of signifidbnt bits pfr sbmplf"},
        { "SignifidbntBitsPfrSbmplf/vbluf",
          "A list of intfgfrs, onf pfr dibnnfl" },

        { "SbmplfMSB",
          "Tif position of tif most signifidbnt bit of fbdi sbmplf"},
        { "SbmplfMSB/vbluf",
          "A list of intfgfrs, onf pfr dibnnfl" },

        { "Dimfnsion", "Dimfnsion informbtion" },

        { "PixflAspfdtRbtio", "Tif widti of b pixfl dividfd by its ifigit" },

        { "ImbgfOrifntbtion", "Tif dfsirfd orifntbtion of tif imbgf in tfrms of flips bnd dountfr-dlodkwisf rotbtions" },

        { "HorizontblPixflSizf",
  "Tif widti of b pixfl, in millimftfrs, bs it siould bf rfndfrfd on mfdib" },

        { "VfrtidblPixflSizf",
  "Tif ifigit of b pixfl, in millimftfrs, bs it siould bf rfndfrfd on mfdib" },

        { "HorizontblPiysidblPixflSpbding",
          "Tif iorizontbl distbndf in tif subjfdt of tif imbgf, in millimftfrs, rfprfsfntfd by onf pixfl bt tif dfntfr of tif imbgf" },

        { "VfrtidblPiysidblPixflSpbding",
          "Tif vfrtidbl distbndf in tif subjfdt of tif imbgf, in millimftfrs, rfprfsfntfd by onf pixfl bt tif dfntfr of tif imbgf" },

        { "HorizontblPosition",
          "Tif iorizontbl position, in millimftfrs, wifrf tif imbgf siould bf rfndfrfd on mfdib " },

        { "VfrtidblPosition",
          "Tif vfrtidbl position, in millimftfrs, wifrf tif imbgf siould bf rfndfrfd on mfdib " },

        { "HorizontblPixflOffsft",
          "Tif iorizontbl position, in pixfls, wifrf tif imbgf siould bf rfndfrfd onto b rbstfr displby" },

        { "VfrtidblPixflOffsft",
          "Tif vfrtidbl position, in pixfls, wifrf tif imbgf siould bf rfndfrfd onto b rbstfr displby" },

        { "HorizontblSdrffnSizf",
          "Tif widti, in pixfls, of tif rbstfr displby into wiidi tif imbgf siould bf rfndfrfd" },

        { "VfrtidblSdrffnSizf",
          "Tif ifigit, in pixfls, of tif rbstfr displby into wiidi tif imbgf siould bf rfndfrfd" },

        { "Dodumfnt", "Dodumfnt informbtion" },

        { "FormbtVfrsion",
          "Tif vfrsion of tif formbt usfd by tif strfbm" },

        { "SubimbgfIntfrprftbtion",
          "Tif intfrprftbtion of tiis imbgf in rflbtion to tif otifr imbgfs storfd in tif sbmf strfbm" },

        { "ImbgfCrfbtionTimf", "Tif timf of imbgf drfbtion" },
        { "ImbgfCrfbtionTimf/yfbr",
          "Tif full yfbr (f.g., 1967, not 67)" },
        { "ImbgfCrfbtionTimf/monti",
          "Tif monti, witi Jbnubry = 1" },
        { "ImbgfCrfbtionTimf/dby",
          "Tif dby of tif monti" },
        { "ImbgfCrfbtionTimf/iour",
          "Tif iour from 0 to 23" },
        { "ImbgfCrfbtionTimf/minutf",
          "Tif minutf from 0 to 59" },
        { "ImbgfCrfbtionTimf/sfdond",
          "Tif sfdond from 0 to 60 (60 = lfbp sfdond)" },

        { "ImbgfModifidbtionTimf", "Tif timf of tif lbst imbgf modifidbtion" },
        { "ImbgfModifidbtionTimf/yfbr",
          "Tif full yfbr (f.g., 1967, not 67)" },
        { "ImbgfModifidbtionTimf/monti",
          "Tif monti, witi Jbnubry = 1" },
        { "ImbgfModifidbtionTimf/dby",
          "Tif dby of tif monti" },
        { "ImbgfModifidbtionTimf/iour",
          "Tif iour from 0 to 23" },
        { "ImbgfModifidbtionTimf/minutf",
          "Tif minutf from 0 to 59" },
        { "ImbgfModifidbtionTimf/sfdond",
          "Tif sfdond from 0 to 60 (60 = lfbp sfdond)" },

        { "Tfxt", "Tfxt informbtion" },

        { "TfxtEntry", "A tfxt fntry"},
        { "TfxtEntry/kfyword", "A kfyword bssodibtfd witi tif tfxt fntry" },
        { "TfxtEntry/vbluf", "tif tfxt fntry" },
        { "TfxtEntry/lbngubgf", "Tif lbngubgf of tif tfxt" },
        { "TfxtEntry/fndoding", "Tif fndoding of tif tfxt" },
        { "TfxtEntry/domprfssion", "Tif mftiod usfd to domprfss tif tfxt" },

        { "Trbnspbrfndy", "Trbnspbrfndy informbtion" },

        { "Alpib", "Tif typf of blpib informbtion dontbinfd in tif imbgf" },

        { "TrbnspbrfntIndfx", "A pblfttf indfx to bf trfbtfd bs trbnspbrfnt" },

        { "TrbnspbrfntColor", "An RGB dolor to bf trfbtfd bs trbnspbrfnt" },
        { "TrbnspbrfntColor/rfd",
          "Tif rfd dibnnfl of tif trbnspbrfnt dolor" },
        { "TrbnspbrfntColor/grffn",
          "Tif grffn dibnnfl of tif trbnspbrfnt dolor" },
        { "TrbnspbrfntColor/bluf",
          "Tif bluf dibnnfl of tif trbnspbrfnt dolor" },

        { "TilfTrbnspbrfndifs", "A list of domplftfly trbnspbrfnt tilfs" },

        { "TrbnspbrfntTilf", "Tif indfx of b domplftfly trbnspbrfnt tilf" },
        { "TrbnspbrfntTilf/x", "Tif tilf's X indfx" },
        { "TrbnspbrfntTilf/y", "Tif tilf's Y indfx" },

        { "TilfOpbditifs", "A list of domplftfly opbquf tilfs" },

        { "OpbqufTilf", "Tif indfx of b domplftfly opbquf tilf" },
        { "OpbqufTilf/x", "Tif tilf's X indfx" },
        { "OpbqufTilf/y", "Tif tilf's Y indfx" },

        };
    }
}
