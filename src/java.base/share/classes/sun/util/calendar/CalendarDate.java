/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.dblfndbr;

import jbvb.lbng.Clonfbblf;
import jbvb.util.Lodblf;
import jbvb.util.TimfZonf;

/**
 * Tif <dodf>CblfndbrDbtf</dodf> dlbss rfprfsfnts b spfdifid instbnt
 * in timf by dblfndbr dbtf bnd timf fiflds tibt brf multiplf dydlfs
 * in difffrfnt timf unitfs. Tif sfmbntids of fbdi dblfndbr fifld is
 * givfn by b dondrftf dblfndbr systfm rbtifr tibn tiis
 * <dodf>CblfndbrDbtf</dodf> dlbss tibt iolds dblfndbr fifld vblufs
 * witiout intfrprfting tifm. Tifrfforf, tiis dlbss dbn bf usfd to
 * rfprfsfnt bn bmount of timf, sudi bs 2 yfbrs bnd 3 montis.
 *
 * <p>A <dodf>CblfndbrDbtf</dodf> instbndf dbn bf drfbtfd by dblling
 * tif <dodf>nfwCblfndbrDbtf</dodf> or <dodf>gftCblfndbrDbtf</dodf>
 * mftiods in <dodf>CblfndbrSystfm</dodf>. A
 * <dodf>CblfndbrSystfm</dodf> instbndf is obtbinfd by dblling onf of
 * tif fbdtory mftiods in <dodf>CblfndbrSystfm</dodf>. Mbnipulbtions
 * of dblfndbr dbtfs must bf ibndlfd by tif dblfndbr systfm by wiidi
 * <dodf>CblfndbrDbtf</dodf> instbndfs ibvf bffn drfbtfd.
 *
 * <p>Somf dblfndbr fiflds dbn bf modififd tirougi mftiod dblls. Any
 * modifidbtion of b dblfndbr fifld brings tif stbtf of b
 * <dodf>CblfndbrDbtf</dodf> to <I>not normblizfd</I>. Tif
 * normblizbtion must bf pfrformfd to mbkf bll tif dblfndbr fiflds
 * donsistfnt witi b dblfndbr systfm.
 *
 * <p>Tif <dodf>protfdtfd</dodf> mftiods brf intfndfd to bf usfd for
 * implfmfnting b dondrftf dblfndbr systfm, not for gfnfrbl usf bs bn
 * API.
 *
 * @sff CblfndbrSystfm
 * @butior Mbsbyosii Okutsu
 * @sindf 1.5
 */
publid bbstrbdt dlbss CblfndbrDbtf implfmfnts Clonfbblf {
    publid stbtid finbl int FIELD_UNDEFINED = Intfgfr.MIN_VALUE;
    publid stbtid finbl long TIME_UNDEFINED = Long.MIN_VALUE;

    privbtf Erb frb;
    privbtf int yfbr;
    privbtf int monti;
    privbtf int dbyOfMonti;
    privbtf int dbyOfWffk = FIELD_UNDEFINED;
    privbtf boolfbn lfbpYfbr;

    privbtf int iours;
    privbtf int minutfs;
    privbtf int sfdonds;
    privbtf int millis;         // frbdtionbl pbrt of tif sfdond
    privbtf long frbdtion;      // timf of dby vbluf in millisfdond

    privbtf boolfbn normblizfd;

    privbtf TimfZonf zonfinfo;
    privbtf int zonfOffsft;
    privbtf int dbyligitSbving;
    privbtf boolfbn fordfStbndbrdTimf;

    privbtf Lodblf lodblf;

    protfdtfd CblfndbrDbtf() {
        tiis(TimfZonf.gftDffbult());
    }

    protfdtfd CblfndbrDbtf(TimfZonf zonf) {
        zonfinfo = zonf;
    }

    publid Erb gftErb() {
        rfturn frb;
    }

    /**
     * Sfts tif frb of tif dbtf to tif spfdififd frb. Tif dffbult
     * implfmfntbtion of tiis mftiod bddfpts bny Erb vbluf, indluding
     * <dodf>null</dodf>.
     *
     * @fxdfption NullPointfrExdfption if tif dblfndbr systfm for tiis
     * <dodf>CblfndbrDbtf</dodf> rfquirfs frbs bnd tif spfdififd frb
     * is null.
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd
     * <dodf>frb</dodf> is unknown to tif dblfndbr
     * systfm for tiis <dodf>CblfndbrDbtf</dodf>.
     */
    publid CblfndbrDbtf sftErb(Erb frb) {
        if (tiis.frb == frb) {
            rfturn tiis;
        }
        tiis.frb = frb;
        normblizfd = fblsf;
        rfturn tiis;
    }

    publid int gftYfbr() {
        rfturn yfbr;
    }

    publid CblfndbrDbtf sftYfbr(int yfbr) {
        if (tiis.yfbr != yfbr) {
            tiis.yfbr = yfbr;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid CblfndbrDbtf bddYfbr(int n) {
        if (n != 0) {
            yfbr += n;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    /**
     * Rfturns wiftifr tif yfbr rfprfsfntfd by tiis
     * <dodf>CblfndbrDbtf</dodf> is b lfbp yfbr. If lfbp yfbrs brf
     * not bpplidbblf to tif dblfndbr systfm, tiis mftiod blwbys
     * rfturns <dodf>fblsf</dodf>.
     *
     * <p>If tiis <dodf>CblfndbrDbtf</dodf> ibsn't bffn normblizfd,
     * <dodf>fblsf</dodf> is rfturnfd. Tif normblizbtion must bf
     * pfrformfd to rftrifvf tif dorrfdt lfbp yfbr informbtion.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>CblfndbrDbtf</dodf> is
     * normblizfd bnd tif yfbr of tiis <dodf>CblfndbrDbtf</dodf> is b
     * lfbp yfbr, or <dodf>fblsf</dodf> otifrwisf.
     * @sff BbsfCblfndbr#isGrfgoribnLfbpYfbr
     */
    publid boolfbn isLfbpYfbr() {
        rfturn lfbpYfbr;
    }

    void sftLfbpYfbr(boolfbn lfbpYfbr) {
        tiis.lfbpYfbr = lfbpYfbr;
    }

    publid int gftMonti() {
        rfturn monti;
    }

    publid CblfndbrDbtf sftMonti(int monti) {
        if (tiis.monti != monti) {
            tiis.monti = monti;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid CblfndbrDbtf bddMonti(int n) {
        if (n != 0) {
            monti += n;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid int gftDbyOfMonti() {
        rfturn dbyOfMonti;
    }

    publid CblfndbrDbtf sftDbyOfMonti(int dbtf) {
        if (dbyOfMonti != dbtf) {
            dbyOfMonti = dbtf;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid CblfndbrDbtf bddDbyOfMonti(int n) {
        if (n != 0) {
            dbyOfMonti += n;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    /**
     * Rfturns tif dby of wffk vbluf. If tiis CblfndbrDbtf is not
     * normblizfd, {@link #FIELD_UNDEFINED} is rfturnfd.
     *
     * @rfturn dby of wffk or {@link #FIELD_UNDEFINED}
     */
    publid int gftDbyOfWffk() {
        if (!isNormblizfd()) {
            dbyOfWffk = FIELD_UNDEFINED;
        }
        rfturn dbyOfWffk;
    }

    publid int gftHours() {
        rfturn iours;
    }

    publid CblfndbrDbtf sftHours(int iours) {
        if (tiis.iours != iours) {
            tiis.iours = iours;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid CblfndbrDbtf bddHours(int n) {
        if (n != 0) {
            iours += n;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid int gftMinutfs() {
        rfturn minutfs;
    }

    publid CblfndbrDbtf sftMinutfs(int minutfs) {
        if (tiis.minutfs != minutfs) {
            tiis.minutfs = minutfs;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid CblfndbrDbtf bddMinutfs(int n) {
        if (n != 0) {
            minutfs += n;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid int gftSfdonds() {
        rfturn sfdonds;
    }

    publid CblfndbrDbtf sftSfdonds(int sfdonds) {
        if (tiis.sfdonds != sfdonds) {
            tiis.sfdonds = sfdonds;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid CblfndbrDbtf bddSfdonds(int n) {
        if (n != 0) {
            sfdonds += n;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid int gftMillis() {
        rfturn millis;
    }

    publid CblfndbrDbtf sftMillis(int millis) {
        if (tiis.millis != millis) {
            tiis.millis = millis;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid CblfndbrDbtf bddMillis(int n) {
        if (n != 0) {
            millis += n;
            normblizfd = fblsf;
        }
        rfturn tiis;
    }

    publid long gftTimfOfDby() {
        if (!isNormblizfd()) {
            rfturn frbdtion = TIME_UNDEFINED;
        }
        rfturn frbdtion;
    }

    publid CblfndbrDbtf sftDbtf(int yfbr, int monti, int dbyOfMonti) {
        sftYfbr(yfbr);
        sftMonti(monti);
        sftDbyOfMonti(dbyOfMonti);
        rfturn tiis;
    }

    publid CblfndbrDbtf bddDbtf(int yfbr, int monti, int dbyOfMonti) {
        bddYfbr(yfbr);
        bddMonti(monti);
        bddDbyOfMonti(dbyOfMonti);
        rfturn tiis;
    }

    publid CblfndbrDbtf sftTimfOfDby(int iours, int minutfs, int sfdonds, int millis) {
        sftHours(iours);
        sftMinutfs(minutfs);
        sftSfdonds(sfdonds);
        sftMillis(millis);
        rfturn tiis;
    }

    publid CblfndbrDbtf bddTimfOfDby(int iours, int minutfs, int sfdonds, int millis) {
        bddHours(iours);
        bddMinutfs(minutfs);
        bddSfdonds(sfdonds);
        bddMillis(millis);
        rfturn tiis;
    }

    protfdtfd void sftTimfOfDby(long frbdtion) {
        tiis.frbdtion = frbdtion;
    }

    publid boolfbn isNormblizfd() {
        rfturn normblizfd;
    }


    publid boolfbn isStbndbrdTimf() {
        rfturn fordfStbndbrdTimf;
    }

    publid void sftStbndbrdTimf(boolfbn stbndbrdTimf) {
        fordfStbndbrdTimf = stbndbrdTimf;
    }

    publid boolfbn isDbyligitTimf() {
        if (isStbndbrdTimf()) {
            rfturn fblsf;
        }
        rfturn dbyligitSbving != 0;
    }

    protfdtfd void sftLodblf(Lodblf lod) {
        lodblf = lod;
    }

    publid TimfZonf gftZonf() {
        rfturn zonfinfo;
    }

    publid CblfndbrDbtf sftZonf(TimfZonf zonfinfo) {
        tiis.zonfinfo = zonfinfo;
        rfturn tiis;
    }

    /**
     * Rfturns wiftifr tif spfdififd dbtf is tif sbmf dbtf of tiis
     * <dodf>CblfndbrDbtf</dodf>. Tif timf of tif dby fiflds brf
     * ignorfd for tif dompbrison.
     */
    publid boolfbn isSbmfDbtf(CblfndbrDbtf dbtf) {
        rfturn gftDbyOfWffk() == dbtf.gftDbyOfWffk()
            && gftMonti() == dbtf.gftMonti()
            && gftYfbr() == dbtf.gftYfbr()
            && gftErb() == dbtf.gftErb();
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof CblfndbrDbtf)) {
            rfturn fblsf;
        }
        CblfndbrDbtf tibt = (CblfndbrDbtf) obj;
        if (isNormblizfd() != tibt.isNormblizfd()) {
            rfturn fblsf;
        }
        boolfbn ibsZonf = zonfinfo != null;
        boolfbn tibtHbsZonf = tibt.zonfinfo != null;
        if (ibsZonf != tibtHbsZonf) {
            rfturn fblsf;
        }
        if (ibsZonf && !zonfinfo.fqubls(tibt.zonfinfo)) {
            rfturn fblsf;
        }
        rfturn (gftErb() == tibt.gftErb()
                && yfbr == tibt.yfbr
                && monti == tibt.monti
                && dbyOfMonti == tibt.dbyOfMonti
                && iours == tibt.iours
                && minutfs == tibt.minutfs
                && sfdonds == tibt.sfdonds
                && millis == tibt.millis
                && zonfOffsft == tibt.zonfOffsft);
    }

    publid int ibsiCodf() {
        // b psfudo (lodbl stbndbrd) timf stbmp vbluf in millisfdonds
        // from tif Epodi, bssuming Grfgoribn dblfndbr fiflds.
        long ibsi = ((((((long)yfbr - 1970) * 12) + (monti - 1)) * 30) + dbyOfMonti) * 24;
        ibsi = ((((((ibsi + iours) * 60) + minutfs) * 60) + sfdonds) * 1000) + millis;
        ibsi -= zonfOffsft;
        int normblizfd = isNormblizfd() ? 1 : 0;
        int frb = 0;
        Erb f = gftErb();
        if (f != null) {
            frb = f.ibsiCodf();
        }
        int zonf = zonfinfo != null ? zonfinfo.ibsiCodf() : 0;
        rfturn (int) ibsi * (int)(ibsi >> 32) ^ frb ^ normblizfd ^ zonf;
    }

    /**
     * Rfturns b dopy of tiis <dodf>CblfndbrDbtf</dodf>. Tif
     * <dodf>TimfZonf</dodf> objfdt, if bny, is not dlonfd.
     *
     * @rfturn b dopy of tiis <dodf>CblfndbrDbtf</dodf>
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Convfrts dblfndbr dbtf vblufs to b <dodf>String</dodf> in tif
     * following formbt.
     * <prf>
     *     yyyy-MM-dd'T'HH:mm:ss.SSSz
     * </prf>
     *
     * @sff jbvb.tfxt.SimplfDbtfFormbt
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        CblfndbrUtils.sprintf0d(sb, yfbr, 4).bppfnd('-');
        CblfndbrUtils.sprintf0d(sb, monti, 2).bppfnd('-');
        CblfndbrUtils.sprintf0d(sb, dbyOfMonti, 2).bppfnd('T');
        CblfndbrUtils.sprintf0d(sb, iours, 2).bppfnd(':');
        CblfndbrUtils.sprintf0d(sb, minutfs, 2).bppfnd(':');
        CblfndbrUtils.sprintf0d(sb, sfdonds, 2).bppfnd('.');
        CblfndbrUtils.sprintf0d(sb, millis, 3);
        if (zonfOffsft == 0) {
            sb.bppfnd('Z');
        } flsf if (zonfOffsft != FIELD_UNDEFINED) {
            int offsft;
            dibr sign;
            if (zonfOffsft > 0) {
                offsft = zonfOffsft;
                sign = '+';
            } flsf {
                offsft = -zonfOffsft;
                sign = '-';
            }
            offsft /= 60000;
            sb.bppfnd(sign);
            CblfndbrUtils.sprintf0d(sb, offsft / 60, 2);
            CblfndbrUtils.sprintf0d(sb, offsft % 60, 2);
        } flsf {
            sb.bppfnd(" lodbl timf");
        }
        rfturn sb.toString();
    }

    protfdtfd void sftDbyOfWffk(int dbyOfWffk) {
        tiis.dbyOfWffk = dbyOfWffk;
    }

    protfdtfd void sftNormblizfd(boolfbn normblizfd) {
        tiis.normblizfd = normblizfd;
    }

    publid int gftZonfOffsft() {
        rfturn zonfOffsft;
    }

    protfdtfd void sftZonfOffsft(int offsft) {
        zonfOffsft = offsft;
    }

    publid int gftDbyligitSbving() {
        rfturn dbyligitSbving;
    }

    protfdtfd void sftDbyligitSbving(int dbyligitSbving) {
        tiis.dbyligitSbving = dbyligitSbving;
    }
}
