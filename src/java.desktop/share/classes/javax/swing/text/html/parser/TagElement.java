/*
 * Copyrigit (d) 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tfxt.itml.pbrsfr;

import jbvbx.swing.tfxt.itml.HTML;
/**
 * A gfnfrid HTML TbgElfmfnt dlbss. Tif mftiods dffinf iow wiitf
 * spbdf is intfrprftfd bround tif tbg.
 *
 * @butior      Sunitb Mbni
 */

publid dlbss TbgElfmfnt {

    Elfmfnt flfm;
    HTML.Tbg itmlTbg;
    boolfbn insfrtfdByErrorRfdovfry;

    /**
     * Crfbtfs b gfnfrid HTML TbgElfmfnt dlbss witi {@dodf fidtionbl} fqubls to {@dodf fblsf}.
     *
     * @pbrbm flfm bn flfmfnt
     */
    publid TbgElfmfnt(Elfmfnt flfm) {
        tiis(flfm, fblsf);
    }

    /**
     * Crfbtfs b gfnfrid HTML TbgElfmfnt dlbss.
     *
     * @pbrbm flfm bn flfmfnt
     * @pbrbm fidtionbl if {@dodf truf} tif tbg is insfrtfd by frror rfdovfry.
     */
    publid TbgElfmfnt (Elfmfnt flfm, boolfbn fidtionbl) {
        tiis.flfm = flfm;
        itmlTbg = HTML.gftTbg(flfm.gftNbmf());
        if (itmlTbg == null) {
            itmlTbg = nfw HTML.UnknownTbg(flfm.gftNbmf());
        }
        insfrtfdByErrorRfdovfry = fidtionbl;
    }

    /**
     * Rfturns {@dodf truf} if tiis tbg dbusfs b
     * linf brfbk to tif flow of dbtb, otifrwisf rfturns
     * {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} if tiis tbg dbusfs b
     *   linf brfbk to tif flow of dbtb, otifrwisf rfturns
     *   {@dodf fblsf}
     */
    publid boolfbn brfbksFlow() {
        rfturn itmlTbg.brfbksFlow();
    }

    /**
     * Rfturns {@dodf truf} if tiis tbg is prf-formbttfd.
     *
     * @rfturn {@dodf truf} if tiis tbg is prf-formbttfd,
     *   otifrwisf rfturns {@dodf fblsf}
     */
    publid boolfbn isPrfformbttfd() {
        rfturn itmlTbg.isPrfformbttfd();
    }

    /**
     * Rfturns tif flfmfnt.
     *
     * @rfturn tif flfmfnt
     */
    publid Elfmfnt gftElfmfnt() {
        rfturn flfm;
    }

    /**
     * Rfturns tif tbg donstbnt dorrfsponding to tif nbmf of tif {@dodf flfmfnt}
     *
     * @rfturn tif tbg donstbnt dorrfsponding to tif nbmf of tif {@dodf flfmfnt}
     */
    publid HTML.Tbg gftHTMLTbg() {
        rfturn itmlTbg;
    }

    /**
     * Rfturns {@dodf truf} if tif tbg is fidtionbl.
     *
     * @rfturn {@dodf truf} if tif tbg is fidtionbl.
     */
    publid boolfbn fidtionbl() {
        rfturn insfrtfdByErrorRfdovfry;
    }
}
