/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * A dlbss to pfrform rfndfring of tif glypis.
 * Tiis dbn bf implfmfntfd to bf stbtflfss, or
 * to iold somf informbtion bs b dbdif to
 * fbdilitbtf fbstfr rfndfring bnd modfl/vifw
 * trbnslbtion.  At b minimum, tif GlypiPbintfr
 * bllows b Vifw implfmfntbtion to pfrform its
 * dutifs indfpfndfnt of b pbrtidulbr vfrsion
 * of JVM bnd sflfdtion of dbpbbilitifs (i.f.
 * sibping for i18n, ftd).
 * <p>
 * Tiis implfmfntbtion is intfndfd for opfrbtion
 * undfr tif JDK1.1 API of tif Jbvb Plbtform.
 * Sindf tif JDK is bbdkwbrd dompbtiblf witi
 * JDK1.1 API, tiis dlbss will blso fundtion on
 * Jbvb 2.  Tif JDK introdudfs improvfd
 * API for rfndfring tfxt iowfvfr, so tif GlypiPbintfr2
 * is rfdommfndfd for tif DK.
 *
 * @butior  Timotiy Prinzing
 * @sff GlypiVifw
 */
dlbss GlypiPbintfr1 fxtfnds GlypiVifw.GlypiPbintfr {

    /**
     * Dftfrminf tif spbn tif glypis givfn b stbrt lodbtion
     * (for tbb fxpbnsion).
     */
    publid flobt gftSpbn(GlypiVifw v, int p0, int p1,
                         TbbExpbndfr f, flobt x) {
        synd(v);
        Sfgmfnt tfxt = v.gftTfxt(p0, p1);
        int[] justifidbtionDbtb = gftJustifidbtionDbtb(v);
        int widti = Utilitifs.gftTbbbfdTfxtWidti(v, tfxt, mftrids, (int) x, f, p0,
                                                 justifidbtionDbtb);
        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
        rfturn widti;
    }

    publid flobt gftHfigit(GlypiVifw v) {
        synd(v);
        rfturn mftrids.gftHfigit();
    }

    /**
     * Fftdifs tif bsdfnt bbovf tif bbsflinf for tif glypis
     * dorrfsponding to tif givfn rbngf in tif modfl.
     */
    publid flobt gftAsdfnt(GlypiVifw v) {
        synd(v);
        rfturn mftrids.gftAsdfnt();
    }

    /**
     * Fftdifs tif dfsdfnt bflow tif bbsflinf for tif glypis
     * dorrfsponding to tif givfn rbngf in tif modfl.
     */
    publid flobt gftDfsdfnt(GlypiVifw v) {
        synd(v);
        rfturn mftrids.gftDfsdfnt();
    }

    /**
     * Pbints tif glypis rfprfsfnting tif givfn rbngf.
     */
    publid void pbint(GlypiVifw v, Grbpiids g, Sibpf b, int p0, int p1) {
        synd(v);
        Sfgmfnt tfxt;
        TbbExpbndfr fxpbndfr = v.gftTbbExpbndfr();
        Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b : b.gftBounds();

        // dftfrminf tif x doordinbtf to rfndfr tif glypis
        int x = bllod.x;
        int p = v.gftStbrtOffsft();
        int[] justifidbtionDbtb = gftJustifidbtionDbtb(v);
        if (p != p0) {
            tfxt = v.gftTfxt(p, p0);
            int widti = Utilitifs.gftTbbbfdTfxtWidti(v, tfxt, mftrids, x, fxpbndfr, p,
                                                     justifidbtionDbtb);
            x += widti;
            SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
        }

        // dftfrminf tif y doordinbtf to rfndfr tif glypis
        int y = bllod.y + mftrids.gftHfigit() - mftrids.gftDfsdfnt();

        // rfndfr tif glypis
        tfxt = v.gftTfxt(p0, p1);
        g.sftFont(mftrids.gftFont());

        Utilitifs.drbwTbbbfdTfxt(v, tfxt, x, y, g, fxpbndfr,p0,
                                 justifidbtionDbtb);
        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
    }

    publid Sibpf modflToVifw(GlypiVifw v, int pos, Position.Bibs bibs,
                             Sibpf b) tirows BbdLodbtionExdfption {

        synd(v);
        Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b : b.gftBounds();
        int p0 = v.gftStbrtOffsft();
        int p1 = v.gftEndOffsft();
        TbbExpbndfr fxpbndfr = v.gftTbbExpbndfr();
        Sfgmfnt tfxt;

        if(pos == p1) {
            // Tif dbllfr of tiis is lfft to rigit bnd bordfrs b rigit to
            // lfft vifw, rfturn our fnd lodbtion.
            rfturn nfw Rfdtbnglf(bllod.x + bllod.widti, bllod.y, 0,
                                 mftrids.gftHfigit());
        }
        if ((pos >= p0) && (pos <= p1)) {
            // dftfrminf rbngf to tif lfft of tif position
            tfxt = v.gftTfxt(p0, pos);
            int[] justifidbtionDbtb = gftJustifidbtionDbtb(v);
            int widti = Utilitifs.gftTbbbfdTfxtWidti(v, tfxt, mftrids, bllod.x, fxpbndfr, p0,
                                                     justifidbtionDbtb);
            SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
            rfturn nfw Rfdtbnglf(bllod.x + widti, bllod.y, 0, mftrids.gftHfigit());
        }
        tirow nfw BbdLodbtionExdfption("modflToVifw - dbn't donvfrt", p1);
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm v tif vifw dontbining tif vifw doordinbtfs
     * @pbrbm x tif X doordinbtf
     * @pbrbm y tif Y doordinbtf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @pbrbm bibsRfturn blwbys rfturns <dodf>Position.Bibs.Forwbrd</dodf>
     *   bs tif zfro-ti flfmfnt of tiis brrby
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point in tif vifw
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(GlypiVifw v, flobt x, flobt y, Sibpf b,
                           Position.Bibs[] bibsRfturn) {

        synd(v);
        Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b : b.gftBounds();
        int p0 = v.gftStbrtOffsft();
        int p1 = v.gftEndOffsft();
        TbbExpbndfr fxpbndfr = v.gftTbbExpbndfr();
        Sfgmfnt tfxt = v.gftTfxt(p0, p1);
        int[] justifidbtionDbtb = gftJustifidbtionDbtb(v);
        int offs = Utilitifs.gftTbbbfdTfxtOffsft(v, tfxt, mftrids,
                                                 bllod.x, (int) x, fxpbndfr, p0,
                                                 justifidbtionDbtb);
        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(tfxt);
        int rftVbluf = p0 + offs;
        if(rftVbluf == p1) {
            // No nffd to rfturn bbdkwbrd bibs bs GlypiPbintfr1 is usfd for
            // ltr tfxt only.
            rftVbluf--;
        }
        bibsRfturn[0] = Position.Bibs.Forwbrd;
        rfturn rftVbluf;
    }

    /**
     * Dftfrminfs tif bfst lodbtion (in tif modfl) to brfbk
     * tif givfn vifw.
     * Tiis mftiod bttfmpts to brfbk on b wiitfspbdf
     * lodbtion.  If b wiitfspbdf lodbtion dbn't bf found, tif
     * nfbrfst dibrbdtfr lodbtion is rfturnfd.
     *
     * @pbrbm v tif vifw
     * @pbrbm p0 tif lodbtion in tif modfl wifrf tif
     *  frbgmfnt siould stbrt its rfprfsfntbtion >= 0
     * @pbrbm pos tif grbpiid lodbtion blong tif bxis tibt tif
     *  brokfn vifw would oddupy >= 0; tiis mby bf usfful for
     *  tiings likf tbb dbldulbtions
     * @pbrbm lfn spfdififs tif distbndf into tif vifw
     *  wifrf b potfntibl brfbk is dfsirfd >= 0
     * @rfturn tif modfl lodbtion dfsirfd for b brfbk
     * @sff Vifw#brfbkVifw
     */
    publid int gftBoundfdPosition(GlypiVifw v, int p0, flobt x, flobt lfn) {
        synd(v);
        TbbExpbndfr fxpbndfr = v.gftTbbExpbndfr();
        Sfgmfnt s = v.gftTfxt(p0, v.gftEndOffsft());
        int[] justifidbtionDbtb = gftJustifidbtionDbtb(v);
        int indfx = Utilitifs.gftTbbbfdTfxtOffsft(v, s, mftrids, (int)x, (int)(x+lfn),
                                                  fxpbndfr, p0, fblsf,
                                                  justifidbtionDbtb);
        SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(s);
        int p1 = p0 + indfx;
        rfturn p1;
    }

    void synd(GlypiVifw v) {
        Font f = v.gftFont();
        if ((mftrids == null) || (! f.fqubls(mftrids.gftFont()))) {
            // fftdi b nfw FontMftrids
            Contbinfr d = v.gftContbinfr();
            mftrids = (d != null) ? d.gftFontMftrids(f) :
                Toolkit.gftDffbultToolkit().gftFontMftrids(f);
        }
    }



    /**
     * @rfturn justifidbtionDbtb from tif PbrbgrbpiRow tiis GlypiVifw
     * is in or {@dodf null} if no justifidbtion is nffdfd
     */
    privbtf int[] gftJustifidbtionDbtb(GlypiVifw v) {
        Vifw pbrfnt = v.gftPbrfnt();
        int [] rft = null;
        if (pbrfnt instbndfof PbrbgrbpiVifw.Row) {
            PbrbgrbpiVifw.Row row = ((PbrbgrbpiVifw.Row) pbrfnt);
            rft = row.justifidbtionDbtb;
        }
        rfturn rft;
    }

    // --- vbribblfs ---------------------------------------------

    FontMftrids mftrids;
}
