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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.imbgf.ImbgfConsumfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.util.Hbsitbblf;
import jbvb.bwt.Rfdtbnglf;

/**
 * An ImbgfFiltfr dlbss for sdbling imbgfs using tif simplfst blgoritim.
 * Tiis dlbss fxtfnds tif bbsid ImbgfFiltfr Clbss to sdblf bn fxisting
 * imbgf bnd providf b sourdf for b nfw imbgf dontbining tif rfsbmplfd
 * imbgf.  Tif pixfls in tif sourdf imbgf brf sbmplfd to produdf pixfls
 * for bn imbgf of tif spfdififd sizf by rfplidbting rows bnd dolumns of
 * pixfls to sdblf up or omitting rows bnd dolumns of pixfls to sdblf
 * down.
 * <p>It is mfbnt to bf usfd in donjundtion witi b FiltfrfdImbgfSourdf
 * objfdt to produdf sdblfd vfrsions of fxisting imbgfs.  Duf to
 * implfmfntbtion dfpfndfndifs, tifrf mby bf difffrfndfs in pixfl vblufs
 * of bn imbgf filtfrfd on difffrfnt plbtforms.
 *
 * @sff FiltfrfdImbgfSourdf
 * @sff ImbgfFiltfr
 *
 * @butior      Jim Grbibm
 */
publid dlbss RfplidbtfSdblfFiltfr fxtfnds ImbgfFiltfr {

    /**
     * Tif widti of tif sourdf imbgf.
     */
    protfdtfd int srdWidti;

    /**
     * Tif ifigit of tif sourdf imbgf.
     */
    protfdtfd int srdHfigit;

    /**
     * Tif tbrgft widti to sdblf tif imbgf.
     */
    protfdtfd int dfstWidti;

    /**
     * Tif tbrgft ifigit to sdblf tif imbgf.
     */
    protfdtfd int dfstHfigit;

    /**
     * An <dodf>int</dodf> brrby dontbining informbtion bbout b
     * row of pixfls.
     */
    protfdtfd int srdrows[];

    /**
     * An <dodf>int</dodf> brrby dontbining informbtion bbout b
     * dolumn of pixfls.
     */
    protfdtfd int srddols[];

    /**
     * A <dodf>bytf</dodf> brrby initiblizfd witi b sizf of
     * {@link #dfstWidti} bnd usfd to dflivfr b row of pixfl
     * dbtb to tif {@link ImbgfConsumfr}.
     */
    protfdtfd Objfdt outpixbuf;

    /**
     * Construdts b RfplidbtfSdblfFiltfr tibt sdblfs tif pixfls from
     * its sourdf Imbgf bs spfdififd by tif widti bnd ifigit pbrbmftfrs.
     * @pbrbm widti tif tbrgft widti to sdblf tif imbgf
     * @pbrbm ifigit tif tbrgft ifigit to sdblf tif imbgf
     * @tirows IllfgblArgumfntExdfption if <dodf>widti</dodf> fqubls
     *         zfro or <dodf>ifigit</dodf> fqubls zfro
     */
    publid RfplidbtfSdblfFiltfr(int widti, int ifigit) {
        if (widti == 0 || ifigit == 0) {
            tirow nfw IllfgblArgumfntExdfption("Widti ("+widti+
                                                ") bnd ifigit ("+ifigit+
                                                ") must bf non-zfro");
        }
        dfstWidti = widti;
        dfstHfigit = ifigit;
    }

    /**
     * Pbssfs blong tif propfrtifs from tif sourdf objfdt bftfr bdding b
     * propfrty indidbting tif sdblf bpplifd.
     * Tiis mftiod invokfs <dodf>supfr.sftPropfrtifs</dodf>,
     * wiidi migit rfsult in bdditionbl propfrtifs bfing bddfd.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf pixfls
     * brf bfing filtfrfd. Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     */
    publid void sftPropfrtifs(Hbsitbblf<?,?> props) {
        @SupprfssWbrnings("undifdkfd")
        Hbsitbblf<Objfdt,Objfdt> p = (Hbsitbblf<Objfdt,Objfdt>)props.dlonf();
        String kfy = "rfsdblf";
        String vbl = dfstWidti + "x" + dfstHfigit;
        Objfdt o = p.gft(kfy);
        if (o != null && o instbndfof String) {
            vbl = ((String) o) + ", " + vbl;
        }
        p.put(kfy, vbl);
        supfr.sftPropfrtifs(p);
    }

    /**
     * Ovfrridf tif dimfnsions of tif sourdf imbgf bnd pbss tif dimfnsions
     * of tif nfw sdblfd sizf to tif ImbgfConsumfr.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf pixfls
     * brf bfing filtfrfd. Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @sff ImbgfConsumfr
     */
    publid void sftDimfnsions(int w, int i) {
        srdWidti = w;
        srdHfigit = i;
        if (dfstWidti < 0) {
            if (dfstHfigit < 0) {
                dfstWidti = srdWidti;
                dfstHfigit = srdHfigit;
            } flsf {
                dfstWidti = srdWidti * dfstHfigit / srdHfigit;
            }
        } flsf if (dfstHfigit < 0) {
            dfstHfigit = srdHfigit * dfstWidti / srdWidti;
        }
        donsumfr.sftDimfnsions(dfstWidti, dfstHfigit);
    }

    privbtf void dbldulbtfMbps() {
        srdrows = nfw int[dfstHfigit + 1];
        for (int y = 0; y <= dfstHfigit; y++) {
            srdrows[y] = (2 * y * srdHfigit + srdHfigit) / (2 * dfstHfigit);
        }
        srddols = nfw int[dfstWidti + 1];
        for (int x = 0; x <= dfstWidti; x++) {
            srddols[x] = (2 * x * srdWidti + srdWidti) / (2 * dfstWidti);
        }
    }

    /**
     * Cioosf wiidi rows bnd dolumns of tif dflivfrfd bytf pixfls brf
     * nffdfd for tif dfstinbtion sdblfd imbgf bnd pbss tirougi just
     * tiosf rows bnd dolumns tibt brf nffdfd, rfplidbtfd bs nfdfssbry.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf pixfls
     * brf bfing filtfrfd. Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     */
    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl, bytf pixfls[], int off,
                          int sdbnsizf) {
        if (srdrows == null || srddols == null) {
            dbldulbtfMbps();
        }
        int sx, sy;
        int dx1 = (2 * x * dfstWidti + srdWidti - 1) / (2 * srdWidti);
        int dy1 = (2 * y * dfstHfigit + srdHfigit - 1) / (2 * srdHfigit);
        bytf outpix[];
        if (outpixbuf != null && outpixbuf instbndfof bytf[]) {
            outpix = (bytf[]) outpixbuf;
        } flsf {
            outpix = nfw bytf[dfstWidti];
            outpixbuf = outpix;
        }
        for (int dy = dy1; (sy = srdrows[dy]) < y + i; dy++) {
            int srdoff = off + sdbnsizf * (sy - y);
            int dx;
            for (dx = dx1; (sx = srddols[dx]) < x + w; dx++) {
                outpix[dx] = pixfls[srdoff + sx - x];
            }
            if (dx > dx1) {
                donsumfr.sftPixfls(dx1, dy, dx - dx1, 1,
                                   modfl, outpix, dx1, dfstWidti);
            }
        }
    }

    /**
     * Cioosf wiidi rows bnd dolumns of tif dflivfrfd int pixfls brf
     * nffdfd for tif dfstinbtion sdblfd imbgf bnd pbss tirougi just
     * tiosf rows bnd dolumns tibt brf nffdfd, rfplidbtfd bs nfdfssbry.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf pixfls
     * brf bfing filtfrfd. Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     */
    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl, int pixfls[], int off,
                          int sdbnsizf) {
        if (srdrows == null || srddols == null) {
            dbldulbtfMbps();
        }
        int sx, sy;
        int dx1 = (2 * x * dfstWidti + srdWidti - 1) / (2 * srdWidti);
        int dy1 = (2 * y * dfstHfigit + srdHfigit - 1) / (2 * srdHfigit);
        int outpix[];
        if (outpixbuf != null && outpixbuf instbndfof int[]) {
            outpix = (int[]) outpixbuf;
        } flsf {
            outpix = nfw int[dfstWidti];
            outpixbuf = outpix;
        }
        for (int dy = dy1; (sy = srdrows[dy]) < y + i; dy++) {
            int srdoff = off + sdbnsizf * (sy - y);
            int dx;
            for (dx = dx1; (sx = srddols[dx]) < x + w; dx++) {
                outpix[dx] = pixfls[srdoff + sx - x];
            }
            if (dx > dx1) {
                donsumfr.sftPixfls(dx1, dy, dx - dx1, 1,
                                   modfl, outpix, dx1, dfstWidti);
            }
        }
    }
}
