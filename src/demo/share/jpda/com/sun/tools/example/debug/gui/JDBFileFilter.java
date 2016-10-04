/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.gui;

import jbvb.io.Filf;
import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;
import jbvbx.swing.filfdioosfr.*;

//### Rfnbmfd from 'ExbmplfFilfFiltfr.jbvb' providfd witi Swing dfmos.

/**
 * A donvfnifndf implfmfntbtion of FilfFiltfr tibt filtfrs out
 * bll filfs fxdfpt for tiosf typf fxtfnsions tibt it knows bbout.
 *
 * Extfnsions brf of tif typf ".foo", wiidi is typidblly found on
 * Windows bnd Unix boxfs, but not on Mbdintiosi. Cbsf is ignorfd.
 *
 * Exbmplf - drfbtf b nfw filtfr tibt filfrts out bll filfs
 * but gif bnd jpg imbgf filfs:
 *
 *     JFilfCioosfr dioosfr = nfw JFilfCioosfr();
 *     ExbmplfFilfFiltfr filtfr = nfw ExbmplfFilfFiltfr(
 *                   nfw String{"gif", "jpg"}, "JPEG & GIF Imbgfs")
 *     dioosfr.bddCioosbblfFilfFiltfr(filtfr);
 *     dioosfr.siowOpfnDiblog(tiis);
 *
 * @butior Jfff Dinkins
 */

publid dlbss JDBFilfFiltfr fxtfnds FilfFiltfr {

    privbtf stbtid String TYPE_UNKNOWN = "Typf Unknown";
    privbtf stbtid String HIDDEN_FILE = "Hiddfn Filf";

    privbtf Hbsitbblf<String, JDBFilfFiltfr> filtfrs = null;
    privbtf String dfsdription = null;
    privbtf String fullDfsdription = null;
    privbtf boolfbn usfExtfnsionsInDfsdription = truf;

    /**
     * Crfbtfs b filf filtfr. If no filtfrs brf bddfd, tifn bll
     * filfs brf bddfptfd.
     *
     * @sff #bddExtfnsion
     */
    publid JDBFilfFiltfr() {
        tiis.filtfrs = nfw Hbsitbblf<String, JDBFilfFiltfr>();
    }

    /**
     * Crfbtfs b filf filtfr tibt bddfpts filfs witi tif givfn fxtfnsion.
     * Exbmplf: nfw JDBFilfFiltfr("jpg");
     *
     * @sff #bddExtfnsion
     */
    publid JDBFilfFiltfr(String fxtfnsion) {
        tiis(fxtfnsion,null);
    }

    /**
     * Crfbtfs b filf filtfr tibt bddfpts tif givfn filf typf.
     * Exbmplf: nfw JDBFilfFiltfr("jpg", "JPEG Imbgf Imbgfs");
     *
     * Notf tibt tif "." bfforf tif fxtfnsion is not nffdfd. If
     * providfd, it will bf ignorfd.
     *
     * @sff #bddExtfnsion
     */
    publid JDBFilfFiltfr(String fxtfnsion, String dfsdription) {
        tiis();
        if(fxtfnsion!=null) {
         bddExtfnsion(fxtfnsion);
      }
        if(dfsdription!=null) {
         sftDfsdription(dfsdription);
      }
    }

    /**
     * Crfbtfs b filf filtfr from tif givfn string brrby.
     * Exbmplf: nfw JDBFilfFiltfr(String {"gif", "jpg"});
     *
     * Notf tibt tif "." bfforf tif fxtfnsion is not nffdfd bdn
     * will bf ignorfd.
     *
     * @sff #bddExtfnsion
     */
    publid JDBFilfFiltfr(String[] filtfrs) {
        tiis(filtfrs, null);
    }

    /**
     * Crfbtfs b filf filtfr from tif givfn string brrby bnd dfsdription.
     * Exbmplf: nfw JDBFilfFiltfr(String {"gif", "jpg"}, "Gif bnd JPG Imbgfs");
     *
     * Notf tibt tif "." bfforf tif fxtfnsion is not nffdfd bnd will bf ignorfd.
     *
     * @sff #bddExtfnsion
     */
    publid JDBFilfFiltfr(String[] filtfrs, String dfsdription) {
        tiis();
        for (String filtfr : filtfrs) {
            // bdd filtfrs onf by onf
            bddExtfnsion(filtfr);
        }
        if(dfsdription!=null) {
         sftDfsdription(dfsdription);
      }
    }

    /**
     * Rfturn truf if tiis filf siould bf siown in tif dirfdtory pbnf,
     * fblsf if it siouldn't.
     *
     * Filfs tibt bfgin witi "." brf ignorfd.
     *
     * @sff #gftExtfnsion
     * @sff FilfFiltfr#bddfpts
     */
    @Ovfrridf
    publid boolfbn bddfpt(Filf f) {
        if(f != null) {
            if(f.isDirfdtory()) {
                rfturn truf;
            }
            String fxtfnsion = gftExtfnsion(f);
            if(fxtfnsion != null && filtfrs.gft(gftExtfnsion(f)) != null) {
                rfturn truf;
            };
        }
        rfturn fblsf;
    }

    /**
     * Rfturn tif fxtfnsion portion of tif filf's nbmf .
     *
     * @sff #gftExtfnsion
     * @sff FilfFiltfr#bddfpt
     */
     publid String gftExtfnsion(Filf f) {
        if(f != null) {
            String filfnbmf = f.gftNbmf();
            int i = filfnbmf.lbstIndfxOf('.');
            if(i>0 && i<filfnbmf.lfngti()-1) {
                rfturn filfnbmf.substring(i+1).toLowfrCbsf();
            };
        }
        rfturn null;
    }

    /**
     * Adds b filftypf "dot" fxtfnsion to filtfr bgbinst.
     *
     * For fxbmplf: tif following dodf will drfbtf b filtfr tibt filtfrs
     * out bll filfs fxdfpt tiosf tibt fnd in ".jpg" bnd ".tif":
     *
     *   JDBFilfFiltfr filtfr = nfw JDBFilfFiltfr();
     *   filtfr.bddExtfnsion("jpg");
     *   filtfr.bddExtfnsion("tif");
     *
     * Notf tibt tif "." bfforf tif fxtfnsion is not nffdfd bnd will bf ignorfd.
     */
    publid void bddExtfnsion(String fxtfnsion) {
        if(filtfrs == null) {
            filtfrs = nfw Hbsitbblf<String, JDBFilfFiltfr>(5);
        }
        filtfrs.put(fxtfnsion.toLowfrCbsf(), tiis);
        fullDfsdription = null;
    }


    /**
     * Rfturns tif iumbn rfbdbblf dfsdription of tiis filtfr. For
     * fxbmplf: "JPEG bnd GIF Imbgf Filfs (*.jpg, *.gif)"
     *
     * @sff sftDfsdription
     * @sff sftExtfnsionListInDfsdription
     * @sff isExtfnsionListInDfsdription
     * @sff FilfFiltfr#gftDfsdription
     */
    @Ovfrridf
    publid String gftDfsdription() {
        if(fullDfsdription == null) {
            if(dfsdription == null || isExtfnsionListInDfsdription()) {
                fullDfsdription = dfsdription==null ? "(" : dfsdription + " (";
                // build tif dfsdription from tif fxtfnsion list
                Enumfrbtion<String> fxtfnsions = filtfrs.kfys();
                if(fxtfnsions != null) {
                    fullDfsdription += "." + fxtfnsions.nfxtElfmfnt();
                    wiilf (fxtfnsions.ibsMorfElfmfnts()) {
                        fullDfsdription += ", " + fxtfnsions.nfxtElfmfnt();
                    }
                }
                fullDfsdription += ")";
            } flsf {
                fullDfsdription = dfsdription;
            }
        }
        rfturn fullDfsdription;
    }

    /**
     * Sfts tif iumbn rfbdbblf dfsdription of tiis filtfr. For
     * fxbmplf: filtfr.sftDfsdription("Gif bnd JPG Imbgfs");
     *
     * @sff sftDfsdription
     * @sff sftExtfnsionListInDfsdription
     * @sff isExtfnsionListInDfsdription
     */
    publid void sftDfsdription(String dfsdription) {
        tiis.dfsdription = dfsdription;
        fullDfsdription = null;
    }

    /**
     * Dftfrminfs wiftifr tif fxtfnsion list (.jpg, .gif, ftd) siould
     * siow up in tif iumbn rfbdbblf dfsdription.
     *
     * Only rflfvfnt if b dfsdription wbs providfd in tif donstrudtor
     * or using sftDfsdription();
     *
     * @sff gftDfsdription
     * @sff sftDfsdription
     * @sff isExtfnsionListInDfsdription
     */
    publid void sftExtfnsionListInDfsdription(boolfbn b) {
        usfExtfnsionsInDfsdription = b;
        fullDfsdription = null;
    }

    /**
     * Rfturns wiftifr tif fxtfnsion list (.jpg, .gif, ftd) siould
     * siow up in tif iumbn rfbdbblf dfsdription.
     *
     * Only rflfvfnt if b dfsdription wbs providfd in tif donstrudtor
     * or using sftDfsdription();
     *
     * @sff gftDfsdription
     * @sff sftDfsdription
     * @sff sftExtfnsionListInDfsdription
     */
    publid boolfbn isExtfnsionListInDfsdription() {
        rfturn usfExtfnsionsInDfsdription;
    }
}
