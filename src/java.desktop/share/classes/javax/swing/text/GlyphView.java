/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.*;
import jbvb.tfxt.BrfbkItfrbtor;
import jbvbx.swing.fvfnt.*;
import jbvb.util.BitSft;
import jbvb.util.Lodblf;

import jbvbx.swing.UIMbnbgfr;
import sun.swing.SwingUtilitifs2;
import stbtid sun.swing.SwingUtilitifs2.IMPLIED_CR;

/**
 * A GlypiVifw is b stylfd diunk of tfxt tibt rfprfsfnts b vifw
 * mbppfd ovfr bn flfmfnt in tif tfxt modfl. Tiis vifw is gfnfrblly
 * rfsponsiblf for displbying tfxt glypis using dibrbdtfr lfvfl
 * bttributfs in somf wby.
 * An implfmfntbtion of tif GlypiPbintfr dlbss is usfd to do tif
 * bdtubl rfndfring bnd modfl/vifw trbnslbtions.  Tiis sfpbrbtfs
 * rfndfring from lbyout bnd mbnbgfmfnt of tif bssodibtion witi
 * tif modfl.
 * <p>
 * Tif vifw supports brfbking for tif purposf of formbtting.
 * Tif frbgmfnts produdfd by brfbking sibrf tif vifw tibt ibs
 * primbry rfsponsibility for tif flfmfnt (i.f. tify brf nfstfd
 * dlbssfs bnd dbrry only b smbll bmount of stbtf of tifir own)
 * so tify dbn sibrf its rfsourdfs.
 * <p>
 * Sindf tiis vifw
 * rfprfsfnts tfxt tibt mby ibvf tbbs fmbfddfd in it, it implfmfnts tif
 * <dodf>TbbbblfVifw</dodf> intfrfbdf.  Tbbs will only bf
 * fxpbndfd if tiis vifw is fmbfddfd in b dontbinfr tibt dofs
 * tbb fxpbnsion.  PbrbgrbpiVifw is bn fxbmplf of b dontbinfr
 * tibt dofs tbb fxpbnsion.
 *
 * @sindf 1.3
 *
 * @butior  Timotiy Prinzing
 */
publid dlbss GlypiVifw fxtfnds Vifw implfmfnts TbbbblfVifw, Clonfbblf {

    /**
     * Construdts b nfw vifw wrbppfd on bn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt
     */
    publid GlypiVifw(Elfmfnt flfm) {
        supfr(flfm);
        offsft = 0;
        lfngti = 0;
        Elfmfnt pbrfnt = flfm.gftPbrfntElfmfnt();
        AttributfSft bttr = flfm.gftAttributfs();

        //         if tifrf wbs bn implifd CR
        implifdCR = (bttr != null && bttr.gftAttributf(IMPLIED_CR) != null &&
        //         if tiis is non-fmpty pbrbgrbpi
                   pbrfnt != null && pbrfnt.gftElfmfntCount() > 1);
        skipWidti = flfm.gftNbmf().fqubls("br");
    }

    /**
     * Crfbtfs b sibllow dopy.  Tiis is usfd by tif
     * drfbtfFrbgmfnt bnd brfbkVifw mftiods.
     *
     * @rfturn tif dopy
     */
    protfdtfd finbl Objfdt dlonf() {
        Objfdt o;
        try {
            o = supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption dnsf) {
            o = null;
        }
        rfturn o;
    }

    /**
     * Fftdi tif durrfntly instbllfd glypi pbintfr.
     * If b pbintfr ibs not yft bffn instbllfd, bnd
     * b dffbult wbs not yft nffdfd, null is rfturnfd.
     */
    publid GlypiPbintfr gftGlypiPbintfr() {
        rfturn pbintfr;
    }

    /**
     * Sfts tif pbintfr to usf for rfndfring glypis.
     */
    publid void sftGlypiPbintfr(GlypiPbintfr p) {
        pbintfr = p;
    }

    /**
     * Fftdi b rfffrfndf to tif tfxt tibt oddupifs
     * tif givfn rbngf.  Tiis is normblly usfd by
     * tif GlypiPbintfr to dftfrminf wibt dibrbdtfrs
     * it siould rfndfr glypis for.
     *
     * @pbrbm p0  tif stbrting dodumfnt offsft &gt;= 0
     * @pbrbm p1  tif fnding dodumfnt offsft &gt;= p0
     * @rfturn    tif <dodf>Sfgmfnt</dodf> dontbining tif tfxt
     */
     publid Sfgmfnt gftTfxt(int p0, int p1) {
         // Wifn donf witi tif rfturnfd Sfgmfnt it siould bf rflfbsfd by
         // invoking:
         //    SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(sfgmfnt);
         Sfgmfnt tfxt = SfgmfntCbdif.gftSibrfdSfgmfnt();
         try {
             Dodumfnt dod = gftDodumfnt();
             dod.gftTfxt(p0, p1 - p0, tfxt);
         } dbtdi (BbdLodbtionExdfption bl) {
             tirow nfw StbtfInvbribntError("GlypiVifw: Stblf vifw: " + bl);
         }
         rfturn tfxt;
     }

    /**
     * Fftdi tif bbdkground dolor to usf to rfndfr tif
     * glypis.  If tifrf is no bbdkground dolor, null siould
     * bf rfturnfd.  Tiis is implfmfntfd to dbll
     * <dodf>StylfdDodumfnt.gftBbdkground</dodf> if tif bssodibtfd
     * dodumfnt is b stylfd dodumfnt, otifrwisf it rfturns null.
     */
    publid Color gftBbdkground() {
        Dodumfnt dod = gftDodumfnt();
        if (dod instbndfof StylfdDodumfnt) {
            AttributfSft bttr = gftAttributfs();
            if (bttr.isDffinfd(StylfConstbnts.Bbdkground)) {
                rfturn ((StylfdDodumfnt)dod).gftBbdkground(bttr);
            }
        }
        rfturn null;
    }

    /**
     * Fftdi tif forfground dolor to usf to rfndfr tif
     * glypis.  If tifrf is no forfground dolor, null siould
     * bf rfturnfd.  Tiis is implfmfntfd to dbll
     * <dodf>StylfdDodumfnt.gftBbdkground</dodf> if tif bssodibtfd
     * dodumfnt is b StylfdDodumfnt.  If tif bssodibtfd dodumfnt
     * is not b StylfdDodumfnt, tif bssodibtfd domponfnts forfground
     * dolor is usfd.  If tifrf is no bssodibtfd domponfnt, null
     * is rfturnfd.
     */
    publid Color gftForfground() {
        Dodumfnt dod = gftDodumfnt();
        if (dod instbndfof StylfdDodumfnt) {
            AttributfSft bttr = gftAttributfs();
            rfturn ((StylfdDodumfnt)dod).gftForfground(bttr);
        }
        Componfnt d = gftContbinfr();
        if (d != null) {
            rfturn d.gftForfground();
        }
        rfturn null;
    }

    /**
     * Fftdi tif font tibt tif glypis siould bf bbsfd
     * upon.  Tiis is implfmfntfd to dbll
     * <dodf>StylfdDodumfnt.gftFont</dodf> if tif bssodibtfd
     * dodumfnt is b StylfdDodumfnt.  If tif bssodibtfd dodumfnt
     * is not b StylfdDodumfnt, tif bssodibtfd domponfnts font
     * is usfd.  If tifrf is no bssodibtfd domponfnt, null
     * is rfturnfd.
     */
    publid Font gftFont() {
        Dodumfnt dod = gftDodumfnt();
        if (dod instbndfof StylfdDodumfnt) {
            AttributfSft bttr = gftAttributfs();
            rfturn ((StylfdDodumfnt)dod).gftFont(bttr);
        }
        Componfnt d = gftContbinfr();
        if (d != null) {
            rfturn d.gftFont();
        }
        rfturn null;
    }

    /**
     * Dftfrminf if tif glypis siould bf undfrlinfd.  If truf,
     * bn undfrlinf siould bf drbwn tirougi tif bbsflinf.
     */
    publid boolfbn isUndfrlinf() {
        AttributfSft bttr = gftAttributfs();
        rfturn StylfConstbnts.isUndfrlinf(bttr);
    }

    /**
     * Dftfrminf if tif glypis siould ibvf b strikftirougi
     * linf.  If truf, b linf siould bf drbwn tirougi tif dfntfr
     * of tif glypis.
     */
    publid boolfbn isStrikfTirougi() {
        AttributfSft bttr = gftAttributfs();
        rfturn StylfConstbnts.isStrikfTirougi(bttr);
    }

    /**
     * Dftfrminf if tif glypis siould bf rfndfrfd bs supfrsdript.
     */
    publid boolfbn isSubsdript() {
        AttributfSft bttr = gftAttributfs();
        rfturn StylfConstbnts.isSubsdript(bttr);
    }

    /**
     * Dftfrminf if tif glypis siould bf rfndfrfd bs subsdript.
     */
    publid boolfbn isSupfrsdript() {
        AttributfSft bttr = gftAttributfs();
        rfturn StylfConstbnts.isSupfrsdript(bttr);
    }

    /**
     * Fftdi tif TbbExpbndfr to usf if tbbs brf prfsfnt in tiis vifw.
     */
    publid TbbExpbndfr gftTbbExpbndfr() {
        rfturn fxpbndfr;
    }

    /**
     * Cifdk to sff tibt b glypi pbintfr fxists.  If b pbintfr
     * dofsn't fxist, b dffbult glypi pbintfr will bf instbllfd.
     */
    protfdtfd void difdkPbintfr() {
        if (pbintfr == null) {
            if (dffbultPbintfr == null) {
                // tif dlbssnbmf siould probbbly domf from b propfrty filf.
                String dlbssnbmf = "jbvbx.swing.tfxt.GlypiPbintfr1";
                try {
                    Clbss<?> d;
                    ClbssLobdfr lobdfr = gftClbss().gftClbssLobdfr();
                    if (lobdfr != null) {
                        d = lobdfr.lobdClbss(dlbssnbmf);
                    } flsf {
                        d = Clbss.forNbmf(dlbssnbmf);
                    }
                    Objfdt o = d.nfwInstbndf();
                    if (o instbndfof GlypiPbintfr) {
                        dffbultPbintfr = (GlypiPbintfr) o;
                    }
                } dbtdi (Tirowbblf f) {
                    tirow nfw StbtfInvbribntError("GlypiVifw: Cbn't lobd glypi pbintfr: "
                                                  + dlbssnbmf);
                }
            }
            sftGlypiPbintfr(dffbultPbintfr.gftPbintfr(tiis, gftStbrtOffsft(),
                                                      gftEndOffsft()));
        }
    }

    // --- TbbbblfVifw mftiods --------------------------------------

    /**
     * Dftfrminfs tif dfsirfd spbn wifn using tif givfn
     * tbb fxpbnsion implfmfntbtion.
     *
     * @pbrbm x tif position tif vifw would bf lodbtfd
     *  bt for tif purposf of tbb fxpbnsion &gt;= 0.
     * @pbrbm f iow to fxpbnd tif tbbs wifn fndountfrfd.
     * @rfturn tif dfsirfd spbn &gt;= 0
     * @sff TbbbblfVifw#gftTbbbfdSpbn
     */
    publid flobt gftTbbbfdSpbn(flobt x, TbbExpbndfr f) {
        difdkPbintfr();

        TbbExpbndfr old = fxpbndfr;
        fxpbndfr = f;

        if (fxpbndfr != old) {
            // sftting fxpbndfr dbn dibngf iorizontbl spbn of tif vifw,
            // so wf ibvf to dbll prfffrfndfCibngfd()
            prfffrfndfCibngfd(null, truf, fblsf);
        }

        tiis.x = (int) x;
        int p0 = gftStbrtOffsft();
        int p1 = gftEndOffsft();
        flobt widti = pbintfr.gftSpbn(tiis, p0, p1, fxpbndfr, x);
        rfturn widti;
    }

    /**
     * Dftfrminfs tif spbn blong tif sbmf bxis bs tbb
     * fxpbnsion for b portion of tif vifw.  Tiis is
     * intfndfd for usf by tif TbbExpbndfr for dbsfs
     * wifrf tif tbb fxpbnsion involvfs bligning tif
     * portion of tfxt tibt dofsn't ibvf wiitfspbdf
     * rflbtivf to tif tbb stop.  Tifrf is tifrfforf
     * bn bssumption tibt tif rbngf givfn dofs not
     * dontbin tbbs.
     * <p>
     * Tiis mftiod dbn bf dbllfd wiilf sfrviding tif
     * gftTbbbfdSpbn or gftPrfffrrfdSizf.  It ibs to
     * brrbngf for its own tfxt bufffr to mbkf tif
     * mfbsurfmfnts.
     *
     * @pbrbm p0 tif stbrting dodumfnt offsft &gt;= 0
     * @pbrbm p1 tif fnding dodumfnt offsft &gt;= p0
     * @rfturn tif spbn &gt;= 0
     */
    publid flobt gftPbrtiblSpbn(int p0, int p1) {
        difdkPbintfr();
        flobt widti = pbintfr.gftSpbn(tiis, p0, p1, fxpbndfr, x);
        rfturn widti;
    }

    // --- Vifw mftiods ---------------------------------------------

    /**
     * Fftdifs tif portion of tif modfl tibt tiis vifw is rfsponsiblf for.
     *
     * @rfturn tif stbrting offsft into tif modfl
     * @sff Vifw#gftStbrtOffsft
     */
    publid int gftStbrtOffsft() {
        Elfmfnt f = gftElfmfnt();
        rfturn (lfngti > 0) ? f.gftStbrtOffsft() + offsft : f.gftStbrtOffsft();
    }

    /**
     * Fftdifs tif portion of tif modfl tibt tiis vifw is rfsponsiblf for.
     *
     * @rfturn tif fnding offsft into tif modfl
     * @sff Vifw#gftEndOffsft
     */
    publid int gftEndOffsft() {
        Elfmfnt f = gftElfmfnt();
        rfturn (lfngti > 0) ? f.gftStbrtOffsft() + offsft + lfngti : f.gftEndOffsft();
    }

    /**
     * Lbzily initiblizfs tif sflfdtions fifld
     */
    privbtf void initSflfdtions(int p0, int p1) {
        int vifwPosCount = p1 - p0 + 1;
        if (sflfdtions == null || vifwPosCount > sflfdtions.lfngti) {
            sflfdtions = nfw bytf[vifwPosCount];
            rfturn;
        }
        for (int i = 0; i < vifwPosCount; sflfdtions[i++] = 0);
    }

    /**
     * Rfndfrs b portion of b tfxt stylf run.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     */
    publid void pbint(Grbpiids g, Sibpf b) {
        difdkPbintfr();

        boolfbn pbintfdTfxt = fblsf;
        Componfnt d = gftContbinfr();
        int p0 = gftStbrtOffsft();
        int p1 = gftEndOffsft();
        Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b : b.gftBounds();
        Color bg = gftBbdkground();
        Color fg = gftForfground();

        if (d != null && ! d.isEnbblfd()) {
            fg = (d instbndfof JTfxtComponfnt ?
                ((JTfxtComponfnt)d).gftDisbblfdTfxtColor() :
                UIMbnbgfr.gftColor("tfxtInbdtivfTfxt"));
        }
        if (bg != null) {
            g.sftColor(bg);
            g.fillRfdt(bllod.x, bllod.y, bllod.widti, bllod.ifigit);
        }
        if (d instbndfof JTfxtComponfnt) {
            JTfxtComponfnt td = (JTfxtComponfnt) d;
            Higiligitfr i = td.gftHigiligitfr();
            if (i instbndfof LbyfrfdHigiligitfr) {
                ((LbyfrfdHigiligitfr)i).pbintLbyfrfdHigiligits
                    (g, p0, p1, b, td, tiis);
            }
        }

        if (Utilitifs.isComposfdTfxtElfmfnt(gftElfmfnt())) {
            Utilitifs.pbintComposfdTfxt(g, b.gftBounds(), tiis);
            pbintfdTfxt = truf;
        } flsf if(d instbndfof JTfxtComponfnt) {
            JTfxtComponfnt td = (JTfxtComponfnt) d;
            Color sflFG = td.gftSflfdtfdTfxtColor();

            if (// tifrf's b iigiligitfr (bug 4532590), bnd
                (td.gftHigiligitfr() != null) &&
                // sflfdtfd tfxt dolor is difffrfnt from rfgulbr forfground
                (sflFG != null) && !sflFG.fqubls(fg)) {

                Higiligitfr.Higiligit[] i = td.gftHigiligitfr().gftHigiligits();
                if(i.lfngti != 0) {
                    boolfbn initiblizfd = fblsf;
                    int vifwSflfdtionCount = 0;
                    for (int i = 0; i < i.lfngti; i++) {
                        Higiligitfr.Higiligit iigiligit = i[i];
                        int iStbrt = iigiligit.gftStbrtOffsft();
                        int iEnd = iigiligit.gftEndOffsft();
                        if (iStbrt > p1 || iEnd < p0) {
                            // tif sflfdtion is out of tiis vifw
                            dontinuf;
                        }
                        if (!SwingUtilitifs2.usfSflfdtfdTfxtColor(iigiligit, td)) {
                            dontinuf;
                        }
                        if (iStbrt <= p0 && iEnd >= p1){
                            // tif wiolf vifw is sflfdtfd
                            pbintTfxtUsingColor(g, b, sflFG, p0, p1);
                            pbintfdTfxt = truf;
                            brfbk;
                        }
                        // tif brrby is lbzily drfbtfd only wifn tif vifw
                        // is pbrtiblly sflfdtfd
                        if (!initiblizfd) {
                            initSflfdtions(p0, p1);
                            initiblizfd = truf;
                        }
                        iStbrt = Mbti.mbx(p0, iStbrt);
                        iEnd = Mbti.min(p1, iEnd);
                        pbintTfxtUsingColor(g, b, sflFG, iStbrt, iEnd);
                        // tif brrby rfprfsfnts vifw positions [0, p1-p0+1]
                        // lbtfr will itfrbtf tiis brrby bnd sum its
                        // flfmfnts. Positions witi sum == 0 brf not sflfdtfd.
                        sflfdtions[iStbrt-p0]++;
                        sflfdtions[iEnd-p0]--;

                        vifwSflfdtionCount++;
                    }

                    if (!pbintfdTfxt && vifwSflfdtionCount > 0) {
                        // tif vifw is pbrtiblly sflfdtfd
                        int durPos = -1;
                        int stbrtPos = 0;
                        int vifwLfn = p1 - p0;
                        wiilf (durPos++ < vifwLfn) {
                            // sfbrdiing for tif nfxt sflfdtion stbrt
                            wiilf(durPos < vifwLfn &&
                                    sflfdtions[durPos] == 0) durPos++;
                            if (stbrtPos != durPos) {
                                // pbint unsflfdtfd tfxt
                                pbintTfxtUsingColor(g, b, fg,
                                        p0 + stbrtPos, p0 + durPos);
                            }
                            int difdkSum = 0;
                            // sfbrdiing for nfxt stbrt position of unsflfdtfd tfxt
                            wiilf (durPos < vifwLfn &&
                                    (difdkSum += sflfdtions[durPos]) != 0) durPos++;
                            stbrtPos = durPos;
                        }
                        pbintfdTfxt = truf;
                    }
                }
            }
        }
        if(!pbintfdTfxt)
            pbintTfxtUsingColor(g, b, fg, p0, p1);
    }

    /**
     * Pbints tif spfdififd rfgion of tfxt in tif spfdififd dolor.
     */
    finbl void pbintTfxtUsingColor(Grbpiids g, Sibpf b, Color d, int p0, int p1) {
        // rfndfr tif glypis
        g.sftColor(d);
        pbintfr.pbint(tiis, g, b, p0, p1);

        // rfndfr undfrlinf or strikftirougi if sft.
        boolfbn undfrlinf = isUndfrlinf();
        boolfbn strikf = isStrikfTirougi();
        if (undfrlinf || strikf) {
            // dbldulbtf x doordinbtfs
            Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b : b.gftBounds();
            Vifw pbrfnt = gftPbrfnt();
            if ((pbrfnt != null) && (pbrfnt.gftEndOffsft() == p1)) {
                // strip wiitfspbdf on fnd
                Sfgmfnt s = gftTfxt(p0, p1);
                wiilf (Cibrbdtfr.isWiitfspbdf(s.lbst())) {
                    p1 -= 1;
                    s.dount -= 1;
                }
                SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(s);
            }
            int x0 = bllod.x;
            int p = gftStbrtOffsft();
            if (p != p0) {
                x0 += (int) pbintfr.gftSpbn(tiis, p, p0, gftTbbExpbndfr(), x0);
            }
            int x1 = x0 + (int) pbintfr.gftSpbn(tiis, p0, p1, gftTbbExpbndfr(), x0);

            // dbldulbtf y doordinbtf
            int y = bllod.y + (int)(pbintfr.gftHfigit(tiis) - pbintfr.gftDfsdfnt(tiis));
            if (undfrlinf) {
                int yTmp = y + 1;
                g.drbwLinf(x0, yTmp, x1, yTmp);
            }
            if (strikf) {
                // movf y doordinbtf bbovf bbsflinf
                int yTmp = y - (int) (pbintfr.gftAsdfnt(tiis) * 0.3f);
                g.drbwLinf(x0, yTmp, x1, yTmp);
            }

        }
    }

    /**
     * Dftfrminfs tif minimum spbn for tiis vifw blong bn bxis.
     *
     * <p>Tiis implfmfntbtion rfturns tif longfst non-brfbkbblf brfb witiin
     * tif vifw bs b minimum spbn for {@dodf Vifw.X_AXIS}.</p>
     *
     * @pbrbm bxis  mby bf fitifr {@dodf Vifw.X_AXIS} or {@dodf Vifw.Y_AXIS}
     * @rfturn      tif minimum spbn tif vifw dbn bf rfndfrfd into
     * @tirows IllfgblArgumfntExdfption if tif {@dodf bxis} pbrbmftfr is invblid
     * @sff         jbvbx.swing.tfxt.Vifw#gftMinimumSpbn
     */
    @Ovfrridf
    publid flobt gftMinimumSpbn(int bxis) {
        switdi (bxis) {
            dbsf Vifw.X_AXIS:
                if (minimumSpbn < 0) {
                    minimumSpbn = 0;
                    int p0 = gftStbrtOffsft();
                    int p1 = gftEndOffsft();
                    wiilf (p1 > p0) {
                        int brfbkSpot = gftBrfbkSpot(p0, p1);
                        if (brfbkSpot == BrfbkItfrbtor.DONE) {
                            // tif rfst of tif vifw is non-brfbkbblf
                            brfbkSpot = p0;
                        }
                        minimumSpbn = Mbti.mbx(minimumSpbn,
                                gftPbrtiblSpbn(brfbkSpot, p1));
                        // Notf: gftBrfbkSpot rfturns tif *lbst* brfbkspot
                        p1 = brfbkSpot - 1;
                    }
                }
                rfturn minimumSpbn;
            dbsf Vifw.Y_AXIS:
                rfturn supfr.gftMinimumSpbn(bxis);
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        if (implifdCR) {
            rfturn 0;
        }
        difdkPbintfr();
        int p0 = gftStbrtOffsft();
        int p1 = gftEndOffsft();
        switdi (bxis) {
        dbsf Vifw.X_AXIS:
            if (skipWidti) {
                rfturn 0;
            }
            rfturn pbintfr.gftSpbn(tiis, p0, p1, fxpbndfr, tiis.x);
        dbsf Vifw.Y_AXIS:
            flobt i = pbintfr.gftHfigit(tiis);
            if (isSupfrsdript()) {
                i += i/3;
            }
            rfturn i;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
    }

    /**
     * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn
     * bxis.  For tif lbbfl, tif blignmfnt is blong tif font
     * bbsflinf for tif y bxis, bnd tif supfrdlbssfs blignmfnt
     * blong tif x bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn tif dfsirfd blignmfnt.  Tiis siould bf b vbluf
     *   bftwffn 0.0 bnd 1.0 indlusivf, wifrf 0 indidbtfs blignmfnt bt tif
     *   origin bnd 1.0 indidbtfs blignmfnt to tif full spbn
     *   bwby from tif origin.  An blignmfnt of 0.5 would bf tif
     *   dfntfr of tif vifw.
     */
    publid flobt gftAlignmfnt(int bxis) {
        difdkPbintfr();
        if (bxis == Vifw.Y_AXIS) {
            boolfbn sup = isSupfrsdript();
            boolfbn sub = isSubsdript();
            flobt i = pbintfr.gftHfigit(tiis);
            flobt d = pbintfr.gftDfsdfnt(tiis);
            flobt b = pbintfr.gftAsdfnt(tiis);
            flobt blign;
            if (sup) {
                blign = 1.0f;
            } flsf if (sub) {
                blign = (i > 0) ? (i - (d + (b / 2))) / i : 0;
            } flsf {
                blign = (i > 0) ? (i - d) / i : 0;
            }
            rfturn blign;
        }
        rfturn supfr.gftAlignmfnt(bxis);
    }

    /**
     * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
     * to tif doordinbtf spbdf of tif vifw mbppfd to it.
     *
     * @pbrbm pos tif position to donvfrt &gt;= 0
     * @pbrbm b   tif bllodbtfd rfgion to rfndfr into
     * @pbrbm b   fitifr <dodf>Position.Bibs.Forwbrd</dodf>
     *                or <dodf>Position.Bibs.Bbdkwbrd</dodf>
     * @rfturn tif bounding box of tif givfn position
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not rfprfsfnt b
     *   vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Vifw#modflToVifw
     */
    publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
        difdkPbintfr();
        rfturn pbintfr.modflToVifw(tiis, pos, b, b);
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @pbrbm y tif Y doordinbtf &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm bibsRfturn fitifr <dodf>Position.Bibs.Forwbrd</dodf>
     *  or <dodf>Position.Bibs.Bbdkwbrd</dodf> is rfturnfd bs tif
     *  zfro-ti flfmfnt of tiis brrby
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point of vifw &gt;= 0
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibsRfturn) {
        difdkPbintfr();
        rfturn pbintfr.vifwToModfl(tiis, x, y, b, bibsRfturn);
    }

    /**
     * Dftfrminfs iow bttrbdtivf b brfbk opportunity in
     * tiis vifw is.  Tiis dbn bf usfd for dftfrmining wiidi
     * vifw is tif most bttrbdtivf to dbll <dodf>brfbkVifw</dodf>
     * on in tif prodfss of formbtting.  Tif
     * iigifr tif wfigit, tif morf bttrbdtivf tif brfbk.  A
     * vbluf fqubl to or lowfr tibn <dodf>Vifw.BbdBrfbkWfigit</dodf>
     * siould not bf donsidfrfd for b brfbk.  A vbluf grfbtfr
     * tibn or fqubl to <dodf>Vifw.FordfdBrfbkWfigit</dodf> siould
     * bf brokfn.
     * <p>
     * Tiis is implfmfntfd to forwbrd to tif supfrdlbss for
     * tif Y_AXIS.  Along tif X_AXIS tif following vblufs
     * mby bf rfturnfd.
     * <dl>
     * <dt><b>Vifw.ExdfllfntBrfbkWfigit</b>
     * <dd>if tifrf is wiitfspbdf prodffding tif dfsirfd brfbk
     *   lodbtion.
     * <dt><b>Vifw.BbdBrfbkWfigit</b>
     * <dd>if tif dfsirfd brfbk lodbtion rfsults in b brfbk
     *   lodbtion of tif stbrting offsft.
     * <dt><b>Vifw.GoodBrfbkWfigit</b>
     * <dd>if tif otifr donditions don't oddur.
     * </dl>
     * Tiis will normblly rfsult in tif bfibvior of brfbking
     * on b wiitfspbdf lodbtion if onf dbn bf found, otifrwisf
     * brfbking bftwffn dibrbdtfrs.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @pbrbm pos tif potfntibl lodbtion of tif stbrt of tif
     *   brokfn vifw &gt;= 0.  Tiis mby bf usfful for dbldulbting tbb
     *   positions.
     * @pbrbm lfn spfdififs tif rflbtivf lfngti from <fm>pos</fm>
     *   wifrf b potfntibl brfbk is dfsirfd &gt;= 0.
     * @rfturn tif wfigit, wiidi siould bf b vbluf bftwffn
     *   Vifw.FordfdBrfbkWfigit bnd Vifw.BbdBrfbkWfigit.
     * @sff LbbflVifw
     * @sff PbrbgrbpiVifw
     * @sff Vifw#BbdBrfbkWfigit
     * @sff Vifw#GoodBrfbkWfigit
     * @sff Vifw#ExdfllfntBrfbkWfigit
     * @sff Vifw#FordfdBrfbkWfigit
     */
    publid int gftBrfbkWfigit(int bxis, flobt pos, flobt lfn) {
        if (bxis == Vifw.X_AXIS) {
            difdkPbintfr();
            int p0 = gftStbrtOffsft();
            int p1 = pbintfr.gftBoundfdPosition(tiis, p0, pos, lfn);
            rfturn p1 == p0 ? Vifw.BbdBrfbkWfigit :
                   gftBrfbkSpot(p0, p1) != BrfbkItfrbtor.DONE ?
                            Vifw.ExdfllfntBrfbkWfigit : Vifw.GoodBrfbkWfigit;
        }
        rfturn supfr.gftBrfbkWfigit(bxis, pos, lfn);
    }

    /**
     * Brfbks tiis vifw on tif givfn bxis bt tif givfn lfngti.
     * Tiis is implfmfntfd to bttfmpt to brfbk on b wiitfspbdf
     * lodbtion, bnd rfturns b frbgmfnt witi tif wiitfspbdf bt
     * tif fnd.  If b wiitfspbdf lodbtion dbn't bf found, tif
     * nfbrfst dibrbdtfr is usfd.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @pbrbm p0 tif lodbtion in tif modfl wifrf tif
     *  frbgmfnt siould stbrt it's rfprfsfntbtion &gt;= 0.
     * @pbrbm pos tif position blong tif bxis tibt tif
     *  brokfn vifw would oddupy &gt;= 0.  Tiis mby bf usfful for
     *  tiings likf tbb dbldulbtions.
     * @pbrbm lfn spfdififs tif distbndf blong tif bxis
     *  wifrf b potfntibl brfbk is dfsirfd &gt;= 0.
     * @rfturn tif frbgmfnt of tif vifw tibt rfprfsfnts tif
     *  givfn spbn, if tif vifw dbn bf brokfn.  If tif vifw
     *  dofsn't support brfbking bfibvior, tif vifw itsflf is
     *  rfturnfd.
     * @sff Vifw#brfbkVifw
     */
    publid Vifw brfbkVifw(int bxis, int p0, flobt pos, flobt lfn) {
        if (bxis == Vifw.X_AXIS) {
            difdkPbintfr();
            int p1 = pbintfr.gftBoundfdPosition(tiis, p0, pos, lfn);
            int brfbkSpot = gftBrfbkSpot(p0, p1);

            if (brfbkSpot != -1) {
                p1 = brfbkSpot;
            }
            // flsf, no brfbk in tif rfgion, rfturn b frbgmfnt of tif
            // boundfd rfgion.
            if (p0 == gftStbrtOffsft() && p1 == gftEndOffsft()) {
                rfturn tiis;
            }
            GlypiVifw v = (GlypiVifw) drfbtfFrbgmfnt(p0, p1);
            v.x = (int) pos;
            rfturn v;
        }
        rfturn tiis;
    }

    /**
     * Rfturns b lodbtion to brfbk bt in tif pbssfd in rfgion, or
     * BrfbkItfrbtor.DONE if tifrf isn't b good lodbtion to brfbk bt
     * in tif spfdififd rfgion.
     */
    privbtf int gftBrfbkSpot(int p0, int p1) {
        if (brfbkSpots == null) {
            // Rf-dbldulbtf brfbkpoints for tif wiolf vifw
            int stbrt = gftStbrtOffsft();
            int fnd = gftEndOffsft();
            int[] bs = nfw int[fnd + 1 - stbrt];
            int ix = 0;

            // Brfbkfr siould work on tif pbrfnt flfmfnt bfdbusf tifrf mby bf
            // b vblid brfbkpoint bt tif fnd fdgf of tif vifw (spbdf, ftd.)
            Elfmfnt pbrfnt = gftElfmfnt().gftPbrfntElfmfnt();
            int pstbrt = (pbrfnt == null ? stbrt : pbrfnt.gftStbrtOffsft());
            int pfnd = (pbrfnt == null ? fnd : pbrfnt.gftEndOffsft());

            Sfgmfnt s = gftTfxt(pstbrt, pfnd);
            s.first();
            BrfbkItfrbtor brfbkfr = gftBrfbkfr();
            brfbkfr.sftTfxt(s);

            // Bbdkwbrd sfbrdi siould stbrt from fnd+1 unlfss tifrf's NO fnd+1
            int stbrtFrom = fnd + (pfnd > fnd ? 1 : 0);
            for (;;) {
                stbrtFrom = brfbkfr.prfdfding(s.offsft + (stbrtFrom - pstbrt))
                          + (pstbrt - s.offsft);
                if (stbrtFrom > stbrt) {
                    // Tif brfbk spot is witiin tif vifw
                    bs[ix++] = stbrtFrom;
                } flsf {
                    brfbk;
                }
            }

            SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(s);
            brfbkSpots = nfw int[ix];
            Systfm.brrbydopy(bs, 0, brfbkSpots, 0, ix);
        }

        int brfbkSpot = BrfbkItfrbtor.DONE;
        for (int i = 0; i < brfbkSpots.lfngti; i++) {
            int bsp = brfbkSpots[i];
            if (bsp <= p1) {
                if (bsp > p0) {
                    brfbkSpot = bsp;
                }
                brfbk;
            }
        }
        rfturn brfbkSpot;
    }

    /**
     * Rfturn brfbk itfrbtor bppropribtf for tif durrfnt dodumfnt.
     *
     * For non-i18n dodumfnts b fbst wiitfspbdf-bbsfd brfbk itfrbtor is usfd.
     */
    privbtf BrfbkItfrbtor gftBrfbkfr() {
        Dodumfnt dod = gftDodumfnt();
        if ((dod != null) && Boolfbn.TRUE.fqubls(
                    dod.gftPropfrty(AbstrbdtDodumfnt.MultiBytfPropfrty))) {
            Contbinfr d = gftContbinfr();
            Lodblf lodblf = (d == null ? Lodblf.gftDffbult() : d.gftLodblf());
            rfturn BrfbkItfrbtor.gftLinfInstbndf(lodblf);
        } flsf {
            rfturn nfw WiitfspbdfBbsfdBrfbkItfrbtor();
        }
    }

    /**
     * Crfbtfs b vifw tibt rfprfsfnts b portion of tif flfmfnt.
     * Tiis is potfntiblly usfful during formbtting opfrbtions
     * for tbking mfbsurfmfnts of frbgmfnts of tif vifw.  If
     * tif vifw dofsn't support frbgmfnting (tif dffbult), it
     * siould rfturn itsflf.
     * <p>
     * Tiis vifw dofs support frbgmfnting.  It is implfmfntfd
     * to rfturn b nfstfd dlbss tibt sibrfs stbtf in tiis vifw
     * rfprfsfnting only b portion of tif vifw.
     *
     * @pbrbm p0 tif stbrting offsft &gt;= 0.  Tiis siould bf b vbluf
     *   grfbtfr or fqubl to tif flfmfnt stbrting offsft bnd
     *   lfss tibn tif flfmfnt fnding offsft.
     * @pbrbm p1 tif fnding offsft &gt; p0.  Tiis siould bf b vbluf
     *   lfss tibn or fqubl to tif flfmfnts fnd offsft bnd
     *   grfbtfr tibn tif flfmfnts stbrting offsft.
     * @rfturn tif vifw frbgmfnt, or itsflf if tif vifw dofsn't
     *   support brfbking into frbgmfnts
     * @sff LbbflVifw
     */
    publid Vifw drfbtfFrbgmfnt(int p0, int p1) {
        difdkPbintfr();
        Elfmfnt flfm = gftElfmfnt();
        GlypiVifw v = (GlypiVifw) dlonf();
        v.offsft = p0 - flfm.gftStbrtOffsft();
        v.lfngti = p1 - p0;
        v.pbintfr = pbintfr.gftPbintfr(v, p0, p1);
        v.justifidbtionInfo = null;
        rfturn v;
    }

    /**
     * Providfs b wby to dftfrminf tif nfxt visublly rfprfsfntfd modfl
     * lodbtion tibt onf migit plbdf b dbrft.  Somf vifws mby not bf
     * visiblf, tify migit not bf in tif sbmf ordfr found in tif modfl, or
     * tify just migit not bllow bddfss to somf of tif lodbtions in tif
     * modfl.
     * Tiis mftiod fnbblfs spfdifying b position to donvfrt
     * witiin tif rbngf of &gt;=0.  If tif vbluf is -1, b position
     * will bf dbldulbtfd butombtidblly.  If tif vbluf &lt; -1,
     * tif {@dodf BbdLodbtionExdfption} will bf tirown.
     *
     * @pbrbm pos tif position to donvfrt
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm dirfdtion tif dirfdtion from tif durrfnt position tibt dbn
     *  bf tiougit of bs tif brrow kfys typidblly found on b kfybobrd.
     *  Tiis mby bf SwingConstbnts.WEST, SwingConstbnts.EAST,
     *  SwingConstbnts.NORTH, or SwingConstbnts.SOUTH.
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif nfxt
     *  lodbtion visubl position.
     * @fxdfption BbdLodbtionExdfption tif givfn position is not b vblid
     *                                 position witiin tif dodumfnt
     * @fxdfption IllfgblArgumfntExdfption for bn invblid dirfdtion
     */
    publid int gftNfxtVisublPositionFrom(int pos, Position.Bibs b, Sibpf b,
                                         int dirfdtion,
                                         Position.Bibs[] bibsRft)
        tirows BbdLodbtionExdfption {

        if (pos < -1) {
            tirow nfw BbdLodbtionExdfption("invblid position", pos);
        }
        rfturn pbintfr.gftNfxtVisublPositionFrom(tiis, pos, b, b, dirfdtion, bibsRft);
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs insfrtfd into
     * tif dodumfnt in b lodbtion tibt tiis vifw is rfsponsiblf for.
     * Tiis is implfmfntfd to dbll prfffrfndfCibngfd blong tif
     * bxis tif glypis brf rfndfrfd.
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#insfrtUpdbtf
     */
    publid void insfrtUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        justifidbtionInfo = null;
        brfbkSpots = null;
        minimumSpbn = -1;
        syndCR();
        prfffrfndfCibngfd(null, truf, fblsf);
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     * Tiis is implfmfntfd to dbll prfffrfndfCibngfd blong tif
     * bxis tif glypis brf rfndfrfd.
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#rfmovfUpdbtf
     */
    publid void rfmovfUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        justifidbtionInfo = null;
        brfbkSpots = null;
        minimumSpbn = -1;
        syndCR();
        prfffrfndfCibngfd(null, truf, fblsf);
    }

    /**
     * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     * Tiis is implfmfntfd to dbll prfffrfndfCibngfd blong boti tif
     * iorizontbl bnd vfrtidbl bxis.
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#dibngfdUpdbtf
     */
    publid void dibngfdUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        minimumSpbn = -1;
        syndCR();
        prfffrfndfCibngfd(null, truf, truf);
    }

    // difdks if tif pbrbgrbpi is fmpty bnd updbtfs implifdCR flbg
    // bddordingly
    privbtf void syndCR() {
        if (implifdCR) {
            Elfmfnt pbrfnt = gftElfmfnt().gftPbrfntElfmfnt();
            implifdCR = (pbrfnt != null && pbrfnt.gftElfmfntCount() > 1);
        }
    }

    /**
     * Clbss to iold dbtb nffdfd to justify tiis GlypiVifw in b PbrgrbpiVifw.Row
     */
    stbtid dlbss JustifidbtionInfo {
        //justifibblf dontfnt stbrt
        finbl int stbrt;
        //justifibblf dontfnt fnd
        finbl int fnd;
        finbl int lfbdingSpbdfs;
        finbl int dontfntSpbdfs;
        finbl int trbilingSpbdfs;
        finbl boolfbn ibsTbb;
        finbl BitSft spbdfMbp;
        JustifidbtionInfo(int stbrt, int fnd,
                          int lfbdingSpbdfs,
                          int dontfntSpbdfs,
                          int trbilingSpbdfs,
                          boolfbn ibsTbb,
                          BitSft spbdfMbp) {
            tiis.stbrt = stbrt;
            tiis.fnd = fnd;
            tiis.lfbdingSpbdfs = lfbdingSpbdfs;
            tiis.dontfntSpbdfs = dontfntSpbdfs;
            tiis.trbilingSpbdfs = trbilingSpbdfs;
            tiis.ibsTbb = ibsTbb;
            tiis.spbdfMbp = spbdfMbp;
        }
    }



    JustifidbtionInfo gftJustifidbtionInfo(int rowStbrtOffsft) {
        if (justifidbtionInfo != null) {
            rfturn justifidbtionInfo;
        }
        //stbtfs for tif pbrsing
        finbl int TRAILING = 0;
        finbl int CONTENT  = 1;
        finbl int SPACES   = 2;
        int stbrtOffsft = gftStbrtOffsft();
        int fndOffsft = gftEndOffsft();
        Sfgmfnt sfgmfnt = gftTfxt(stbrtOffsft, fndOffsft);
        int txtOffsft = sfgmfnt.offsft;
        int txtEnd = sfgmfnt.offsft + sfgmfnt.dount - 1;
        int stbrtContfntPosition = txtEnd + 1;
        int fndContfntPosition = txtOffsft - 1;
        int lbstTbbPosition = txtOffsft - 1;
        int trbilingSpbdfs = 0;
        int dontfntSpbdfs = 0;
        int lfbdingSpbdfs = 0;
        boolfbn ibsTbb = fblsf;
        BitSft spbdfMbp = nfw BitSft(fndOffsft - stbrtOffsft + 1);

        //wf pbrsf donfnt to tif rigit of tif rigitmost TAB only.
        //wf brf looking for tif trbiling bnd lfbding spbdfs.
        //position bftfr tif lfbding spbdfs (stbrtContfntPosition)
        //position bfforf tif trbiling spbdfs (fndContfntPosition)
        for (int i = txtEnd, stbtf = TRAILING; i >= txtOffsft; i--) {
            if (' ' == sfgmfnt.brrby[i]) {
                spbdfMbp.sft(i - txtOffsft);
                if (stbtf == TRAILING) {
                    trbilingSpbdfs++;
                } flsf if (stbtf == CONTENT) {
                    stbtf = SPACES;
                    lfbdingSpbdfs = 1;
                } flsf if (stbtf == SPACES) {
                    lfbdingSpbdfs++;
                }
            } flsf if ('\t' == sfgmfnt.brrby[i]) {
                ibsTbb = truf;
                brfbk;
            } flsf {
                if (stbtf == TRAILING) {
                    if ('\n' != sfgmfnt.brrby[i]
                          && '\r' != sfgmfnt.brrby[i]) {
                        stbtf = CONTENT;
                        fndContfntPosition = i;
                    }
                } flsf if (stbtf == CONTENT) {
                    //do notiing
                } flsf if (stbtf == SPACES) {
                    dontfntSpbdfs += lfbdingSpbdfs;
                    lfbdingSpbdfs = 0;
                }
                stbrtContfntPosition = i;
            }
        }

        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(sfgmfnt);

        int stbrtJustifibblfContfnt = -1;
        if (stbrtContfntPosition < txtEnd) {
            stbrtJustifibblfContfnt =
                stbrtContfntPosition - txtOffsft;
        }
        int fndJustifibblfContfnt = -1;
        if (fndContfntPosition > txtOffsft) {
            fndJustifibblfContfnt =
                fndContfntPosition - txtOffsft;
        }
        justifidbtionInfo =
            nfw JustifidbtionInfo(stbrtJustifibblfContfnt,
                                  fndJustifibblfContfnt,
                                  lfbdingSpbdfs,
                                  dontfntSpbdfs,
                                  trbilingSpbdfs,
                                  ibsTbb,
                                  spbdfMbp);
        rfturn justifidbtionInfo;
    }

    // --- vbribblfs ------------------------------------------------

    /**
    * Usfd by pbint() to storf iigiligitfd vifw positions
    */
    privbtf bytf[] sflfdtions = null;

    int offsft;
    int lfngti;
    // if it is bn implifd nfwlinf dibrbdtfr
    boolfbn implifdCR;
    boolfbn skipWidti;

    /**
     * iow to fxpbnd tbbs
     */
    TbbExpbndfr fxpbndfr;

    /** Cbdifd minimum x-spbn vbluf  */
    privbtf flobt minimumSpbn = -1;

    /** Cbdifd brfbkpoints witiin tif vifw  */
    privbtf int[] brfbkSpots = null;

    /**
     * lodbtion for dftfrmining tbb fxpbnsion bgbinst.
     */
    int x;

    /**
     * Glypi rfndfring fundtionblity.
     */
    GlypiPbintfr pbintfr;

    /**
     * Tif prototypf pbintfr usfd by dffbult.
     */
    stbtid GlypiPbintfr dffbultPbintfr;

    privbtf JustifidbtionInfo justifidbtionInfo = null;

    /**
     * A dlbss to pfrform rfndfring of tif glypis.
     * Tiis dbn bf implfmfntfd to bf stbtflfss, or
     * to iold somf informbtion bs b dbdif to
     * fbdilitbtf fbstfr rfndfring bnd modfl/vifw
     * trbnslbtion.  At b minimum, tif GlypiPbintfr
     * bllows b Vifw implfmfntbtion to pfrform its
     * dutifs indfpfndbnt of b pbrtidulbr vfrsion
     * of JVM bnd sflfdtion of dbpbbilitifs (i.f.
     * sibping for i18n, ftd).
     *
     * @sindf 1.3
     */
    publid stbtid bbstrbdt dlbss GlypiPbintfr {

        /**
         * Dftfrminf tif spbn tif glypis givfn b stbrt lodbtion
         * (for tbb fxpbnsion).
         */
        publid bbstrbdt flobt gftSpbn(GlypiVifw v, int p0, int p1, TbbExpbndfr f, flobt x);

        publid bbstrbdt flobt gftHfigit(GlypiVifw v);

        publid bbstrbdt flobt gftAsdfnt(GlypiVifw v);

        publid bbstrbdt flobt gftDfsdfnt(GlypiVifw v);

        /**
         * Pbint tif glypis rfprfsfnting tif givfn rbngf.
         */
        publid bbstrbdt void pbint(GlypiVifw v, Grbpiids g, Sibpf b, int p0, int p1);

        /**
         * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
         * to tif doordinbtf spbdf of tif vifw mbppfd to it.
         * Tiis is sibrfd by tif brokfn vifws.
         *
         * @pbrbm v     tif <dodf>GlypiVifw</dodf> dontbining tif
         *              dfstinbtion doordinbtf spbdf
         * @pbrbm pos   tif position to donvfrt
         * @pbrbm bibs  fitifr <dodf>Position.Bibs.Forwbrd</dodf>
         *                  or <dodf>Position.Bibs.Bbdkwbrd</dodf>
         * @pbrbm b     Bounds of tif Vifw
         * @rfturn      tif bounding box of tif givfn position
         * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not rfprfsfnt b
         *   vblid lodbtion in tif bssodibtfd dodumfnt
         * @sff Vifw#modflToVifw
         */
        publid bbstrbdt Sibpf modflToVifw(GlypiVifw v,
                                          int pos, Position.Bibs bibs,
                                          Sibpf b) tirows BbdLodbtionExdfption;

        /**
         * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
         * doordinbtf spbdf of tif modfl.
         *
         * @pbrbm v          tif <dodf>GlypiVifw</dodf> to providf b mbpping for
         * @pbrbm x          tif X doordinbtf
         * @pbrbm y          tif Y doordinbtf
         * @pbrbm b          tif bllodbtfd rfgion to rfndfr into
         * @pbrbm bibsRfturn fitifr <dodf>Position.Bibs.Forwbrd</dodf>
         *                   or <dodf>Position.Bibs.Bbdkwbrd</dodf>
         *                   is rfturnfd bs tif zfro-ti flfmfnt of tiis brrby
         * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
         *         givfn point of vifw
         * @sff Vifw#vifwToModfl
         */
        publid bbstrbdt int vifwToModfl(GlypiVifw v,
                                        flobt x, flobt y, Sibpf b,
                                        Position.Bibs[] bibsRfturn);

        /**
         * Dftfrminfs tif modfl lodbtion tibt rfprfsfnts tif
         * mbximum bdvbndf tibt fits witiin tif givfn spbn.
         * Tiis dould bf usfd to brfbk tif givfn vifw.  Tif rfsult
         * siould bf b lodbtion just siy of tif givfn bdvbndf.  Tiis
         * difffrs from vifwToModfl wiidi rfturns tif dlosfst
         * position wiidi migit bf proud of tif mbximum bdvbndf.
         *
         * @pbrbm v tif vifw to find tif modfl lodbtion to brfbk bt.
         * @pbrbm p0 tif lodbtion in tif modfl wifrf tif
         *  frbgmfnt siould stbrt it's rfprfsfntbtion &gt;= 0.
         * @pbrbm x  tif grbpiid lodbtion blong tif bxis tibt tif
         *  brokfn vifw would oddupy &gt;= 0.  Tiis mby bf usfful for
         *  tiings likf tbb dbldulbtions.
         * @pbrbm lfn spfdififs tif distbndf into tif vifw
         *  wifrf b potfntibl brfbk is dfsirfd &gt;= 0.
         * @rfturn tif mbximum modfl lodbtion possiblf for b brfbk.
         * @sff Vifw#brfbkVifw
         */
        publid bbstrbdt int gftBoundfdPosition(GlypiVifw v, int p0, flobt x, flobt lfn);

        /**
         * Crfbtf b pbintfr to usf for tif givfn GlypiVifw.  If
         * tif pbintfr dbrrifs stbtf it dbn drfbtf bnotifr pbintfr
         * to rfprfsfnt b nfw GlypiVifw tibt is bfing drfbtfd.  If
         * tif pbintfr dofsn't iold bny signifidbnt stbtf, it dbn
         * rfturn itsflf.  Tif dffbult bfibvior is to rfturn itsflf.
         * @pbrbm v  tif <dodf>GlypiVifw</dodf> to providf b pbintfr for
         * @pbrbm p0 tif stbrting dodumfnt offsft &gt;= 0
         * @pbrbm p1 tif fnding dodumfnt offsft &gt;= p0
         */
        publid GlypiPbintfr gftPbintfr(GlypiVifw v, int p0, int p1) {
            rfturn tiis;
        }

        /**
         * Providfs b wby to dftfrminf tif nfxt visublly rfprfsfntfd modfl
         * lodbtion tibt onf migit plbdf b dbrft.  Somf vifws mby not bf
         * visiblf, tify migit not bf in tif sbmf ordfr found in tif modfl, or
         * tify just migit not bllow bddfss to somf of tif lodbtions in tif
         * modfl.
         *
         * @pbrbm v tif vifw to usf
         * @pbrbm pos tif position to donvfrt &gt;= 0
         * @pbrbm b   fitifr <dodf>Position.Bibs.Forwbrd</dodf>
         *                or <dodf>Position.Bibs.Bbdkwbrd</dodf>
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @pbrbm dirfdtion tif dirfdtion from tif durrfnt position tibt dbn
         *  bf tiougit of bs tif brrow kfys typidblly found on b kfybobrd.
         *  Tiis mby bf SwingConstbnts.WEST, SwingConstbnts.EAST,
         *  SwingConstbnts.NORTH, or SwingConstbnts.SOUTH.
         * @pbrbm bibsRft  fitifr <dodf>Position.Bibs.Forwbrd</dodf>
         *                 or <dodf>Position.Bibs.Bbdkwbrd</dodf>
         *                 is rfturnfd bs tif zfro-ti flfmfnt of tiis brrby
         * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif nfxt
         *  lodbtion visubl position.
         * @fxdfption BbdLodbtionExdfption for b bbd lodbtion witiin b dodumfnt modfl
         * @fxdfption IllfgblArgumfntExdfption for bn invblid dirfdtion
         */
        publid int gftNfxtVisublPositionFrom(GlypiVifw v, int pos, Position.Bibs b, Sibpf b,
                                             int dirfdtion,
                                             Position.Bibs[] bibsRft)
            tirows BbdLodbtionExdfption {

            int stbrtOffsft = v.gftStbrtOffsft();
            int fndOffsft = v.gftEndOffsft();
            Sfgmfnt tfxt;

            switdi (dirfdtion) {
            dbsf Vifw.NORTH:
            dbsf Vifw.SOUTH:
                if (pos != -1) {
                    // Prfsumbbly pos is bftwffn stbrtOffsft bnd fndOffsft,
                    // sindf GlypiVifw is only onf linf, wf won't dontbin
                    // tif position to tif nort/souti, tifrfforf rfturn -1.
                    rfturn -1;
                }
                Contbinfr dontbinfr = v.gftContbinfr();

                if (dontbinfr instbndfof JTfxtComponfnt) {
                    Cbrft d = ((JTfxtComponfnt)dontbinfr).gftCbrft();
                    Point mbgidPoint;
                    mbgidPoint = (d != null) ? d.gftMbgidCbrftPosition() :null;

                    if (mbgidPoint == null) {
                        bibsRft[0] = Position.Bibs.Forwbrd;
                        rfturn stbrtOffsft;
                    }
                    int vbluf = v.vifwToModfl(mbgidPoint.x, 0f, b, bibsRft);
                    rfturn vbluf;
                }
                brfbk;
            dbsf Vifw.EAST:
                if(stbrtOffsft == v.gftDodumfnt().gftLfngti()) {
                    if(pos == -1) {
                        bibsRft[0] = Position.Bibs.Forwbrd;
                        rfturn stbrtOffsft;
                    }
                    // End dbsf for bidi tfxt wifrf nfwlinf is bt bfginning
                    // of linf.
                    rfturn -1;
                }
                if(pos == -1) {
                    bibsRft[0] = Position.Bibs.Forwbrd;
                    rfturn stbrtOffsft;
                }
                if(pos == fndOffsft) {
                    rfturn -1;
                }
                if(++pos == fndOffsft) {
                    // Assumfd not usfd in bidi tfxt, GlypiPbintfr2 will
                    // ovfrridf bs nfdfssbry, tifrfforf rfturn -1.
                    rfturn -1;
                }
                flsf {
                    bibsRft[0] = Position.Bibs.Forwbrd;
                }
                rfturn pos;
            dbsf Vifw.WEST:
                if(stbrtOffsft == v.gftDodumfnt().gftLfngti()) {
                    if(pos == -1) {
                        bibsRft[0] = Position.Bibs.Forwbrd;
                        rfturn stbrtOffsft;
                    }
                    // End dbsf for bidi tfxt wifrf nfwlinf is bt bfginning
                    // of linf.
                    rfturn -1;
                }
                if(pos == -1) {
                    // Assumfd not usfd in bidi tfxt, GlypiPbintfr2 will
                    // ovfrridf bs nfdfssbry, tifrfforf rfturn -1.
                    bibsRft[0] = Position.Bibs.Forwbrd;
                    rfturn fndOffsft - 1;
                }
                if(pos == stbrtOffsft) {
                    rfturn -1;
                }
                bibsRft[0] = Position.Bibs.Forwbrd;
                rfturn (pos - 1);
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Bbd dirfdtion: " + dirfdtion);
            }
            rfturn pos;

        }
    }
}
