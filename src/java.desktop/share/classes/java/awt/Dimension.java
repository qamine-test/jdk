/*
 * Copyrigit (d) 1995, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bwt.gfom.Dimfnsion2D;
import jbvb.bfbns.Trbnsifnt;

/**
 * Tif <dodf>Dimfnsion</dodf> dlbss fndbpsulbtfs tif widti bnd
 * ifigit of b domponfnt (in intfgfr prfdision) in b singlf objfdt.
 * Tif dlbss is
 * bssodibtfd witi dfrtbin propfrtifs of domponfnts. Sfvfrbl mftiods
 * dffinfd by tif <dodf>Componfnt</dodf> dlbss bnd tif
 * <dodf>LbyoutMbnbgfr</dodf> intfrfbdf rfturn b
 * <dodf>Dimfnsion</dodf> objfdt.
 * <p>
 * Normblly tif vblufs of <dodf>widti</dodf>
 * bnd <dodf>ifigit</dodf> brf non-nfgbtivf intfgfrs.
 * Tif donstrudtors tibt bllow you to drfbtf b dimfnsion do
 * not prfvfnt you from sftting b nfgbtivf vbluf for tifsf propfrtifs.
 * If tif vbluf of <dodf>widti</dodf> or <dodf>ifigit</dodf> is
 * nfgbtivf, tif bfibvior of somf mftiods dffinfd by otifr objfdts is
 * undffinfd.
 *
 * @butior      Sbmi Sibio
 * @butior      Artiur vbn Hoff
 * @sff         jbvb.bwt.Componfnt
 * @sff         jbvb.bwt.LbyoutMbnbgfr
 * @sindf       1.0
 */
publid dlbss Dimfnsion fxtfnds Dimfnsion2D implfmfnts jbvb.io.Sfriblizbblf {

    /**
     * Tif widti dimfnsion; nfgbtivf vblufs dbn bf usfd.
     *
     * @sfribl
     * @sff #gftSizf
     * @sff #sftSizf
     * @sindf 1.0
     */
    publid int widti;

    /**
     * Tif ifigit dimfnsion; nfgbtivf vblufs dbn bf usfd.
     *
     * @sfribl
     * @sff #gftSizf
     * @sff #sftSizf
     * @sindf 1.0
     */
    publid int ifigit;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = 4723952579491349524L;

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Crfbtfs bn instbndf of <dodf>Dimfnsion</dodf> witi b widti
     * of zfro bnd b ifigit of zfro.
     */
    publid Dimfnsion() {
        tiis(0, 0);
    }

    /**
     * Crfbtfs bn instbndf of <dodf>Dimfnsion</dodf> wiosf widti
     * bnd ifigit brf tif sbmf bs for tif spfdififd dimfnsion.
     *
     * @pbrbm    d   tif spfdififd dimfnsion for tif
     *               <dodf>widti</dodf> bnd
     *               <dodf>ifigit</dodf> vblufs
     */
    publid Dimfnsion(Dimfnsion d) {
        tiis(d.widti, d.ifigit);
    }

    /**
     * Construdts b <dodf>Dimfnsion</dodf> bnd initiblizfs
     * it to tif spfdififd widti bnd spfdififd ifigit.
     *
     * @pbrbm widti tif spfdififd widti
     * @pbrbm ifigit tif spfdififd ifigit
     */
    publid Dimfnsion(int widti, int ifigit) {
        tiis.widti = widti;
        tiis.ifigit = ifigit;
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid doublf gftWidti() {
        rfturn widti;
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid doublf gftHfigit() {
        rfturn ifigit;
    }

    /**
     * Sfts tif sizf of tiis <dodf>Dimfnsion</dodf> objfdt to
     * tif spfdififd widti bnd ifigit in doublf prfdision.
     * Notf tibt if <dodf>widti</dodf> or <dodf>ifigit</dodf>
     * brf lbrgfr tibn <dodf>Intfgfr.MAX_VALUE</dodf>, tify will
     * bf rfsft to <dodf>Intfgfr.MAX_VALUE</dodf>.
     *
     * @pbrbm widti  tif nfw widti for tif <dodf>Dimfnsion</dodf> objfdt
     * @pbrbm ifigit tif nfw ifigit for tif <dodf>Dimfnsion</dodf> objfdt
     * @sindf 1.2
     */
    publid void sftSizf(doublf widti, doublf ifigit) {
        tiis.widti = (int) Mbti.dfil(widti);
        tiis.ifigit = (int) Mbti.dfil(ifigit);
    }

    /**
     * Gfts tif sizf of tiis <dodf>Dimfnsion</dodf> objfdt.
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>gftSizf</dodf> mftiod dffinfd by <dodf>Componfnt</dodf>.
     *
     * @rfturn   tif sizf of tiis dimfnsion, b nfw instbndf of
     *           <dodf>Dimfnsion</dodf> witi tif sbmf widti bnd ifigit
     * @sff      jbvb.bwt.Dimfnsion#sftSizf
     * @sff      jbvb.bwt.Componfnt#gftSizf
     * @sindf    1.1
     */
    @Trbnsifnt
    publid Dimfnsion gftSizf() {
        rfturn nfw Dimfnsion(widti, ifigit);
    }

    /**
     * Sfts tif sizf of tiis <dodf>Dimfnsion</dodf> objfdt to tif spfdififd sizf.
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>sftSizf</dodf> mftiod dffinfd by <dodf>Componfnt</dodf>.
     * @pbrbm    d  tif nfw sizf for tiis <dodf>Dimfnsion</dodf> objfdt
     * @sff      jbvb.bwt.Dimfnsion#gftSizf
     * @sff      jbvb.bwt.Componfnt#sftSizf
     * @sindf    1.1
     */
    publid void sftSizf(Dimfnsion d) {
        sftSizf(d.widti, d.ifigit);
    }

    /**
     * Sfts tif sizf of tiis <dodf>Dimfnsion</dodf> objfdt
     * to tif spfdififd widti bnd ifigit.
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>sftSizf</dodf> mftiod dffinfd by <dodf>Componfnt</dodf>.
     *
     * @pbrbm    widti   tif nfw widti for tiis <dodf>Dimfnsion</dodf> objfdt
     * @pbrbm    ifigit  tif nfw ifigit for tiis <dodf>Dimfnsion</dodf> objfdt
     * @sff      jbvb.bwt.Dimfnsion#gftSizf
     * @sff      jbvb.bwt.Componfnt#sftSizf
     * @sindf    1.1
     */
    publid void sftSizf(int widti, int ifigit) {
        tiis.widti = widti;
        tiis.ifigit = ifigit;
    }

    /**
     * Cifdks wiftifr two dimfnsion objfdts ibvf fqubl vblufs.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof Dimfnsion) {
            Dimfnsion d = (Dimfnsion)obj;
            rfturn (widti == d.widti) && (ifigit == d.ifigit);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif ibsi dodf for tiis <dodf>Dimfnsion</dodf>.
     *
     * @rfturn    b ibsi dodf for tiis <dodf>Dimfnsion</dodf>
     */
    publid int ibsiCodf() {
        int sum = widti + ifigit;
        rfturn sum * (sum + 1)/2 + widti;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif vblufs of tiis
     * <dodf>Dimfnsion</dodf> objfdt's <dodf>ifigit</dodf> bnd
     * <dodf>widti</dodf> fiflds. Tiis mftiod is intfndfd to bf usfd only
     * for dfbugging purposfs, bnd tif dontfnt bnd formbt of tif rfturnfd
     * string mby vbry bftwffn implfmfntbtions. Tif rfturnfd string mby bf
     * fmpty but mby not bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>Dimfnsion</dodf>
     *          objfdt
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[widti=" + widti + ",ifigit=" + ifigit + "]";
    }
}
