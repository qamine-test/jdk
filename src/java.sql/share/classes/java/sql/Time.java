/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sql;

import jbvb.timf.Instbnt;
import jbvb.timf.LodblTimf;

/**
 * <P>A tiin wrbppfr bround tif <dodf>jbvb.util.Dbtf</dodf> dlbss tibt bllows tif JDBC
 * API to idfntify tiis bs bn SQL <dodf>TIME</dodf> vbluf. Tif <dodf>Timf</dodf>
 * dlbss bdds formbtting bnd
 * pbrsing opfrbtions to support tif JDBC fsdbpf syntbx for timf
 * vblufs.
 * <p>Tif dbtf domponfnts siould bf sft to tif "zfro fpodi"
 * vbluf of Jbnubry 1, 1970 bnd siould not bf bddfssfd.
 */
publid dlbss Timf fxtfnds jbvb.util.Dbtf {

    /**
     * Construdts b <dodf>Timf</dodf> objfdt initiblizfd witi tif
     * givfn vblufs for tif iour, minutf, bnd sfdond.
     * Tif drivfr sfts tif dbtf domponfnts to Jbnubry 1, 1970.
     * Any mftiod tibt bttfmpts to bddfss tif dbtf domponfnts of b
     * <dodf>Timf</dodf> objfdt will tirow b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>.
     * <P>
     * Tif rfsult is undffinfd if b givfn brgumfnt is out of bounds.
     *
     * @pbrbm iour 0 to 23
     * @pbrbm minutf 0 to 59
     * @pbrbm sfdond 0 to 59
     *
     * @dfprfdbtfd Usf tif donstrudtor tibt tbkfs b millisfdonds vbluf
     *             in plbdf of tiis donstrudtor
     */
    @Dfprfdbtfd
    publid Timf(int iour, int minutf, int sfdond) {
        supfr(70, 0, 1, iour, minutf, sfdond);
    }

    /**
     * Construdts b <dodf>Timf</dodf> objfdt using b millisfdonds timf vbluf.
     *
     * @pbrbm timf millisfdonds sindf Jbnubry 1, 1970, 00:00:00 GMT;
     *             b nfgbtivf numbfr is millisfdonds bfforf
     *               Jbnubry 1, 1970, 00:00:00 GMT
     */
    publid Timf(long timf) {
        supfr(timf);
    }

    /**
     * Sfts b <dodf>Timf</dodf> objfdt using b millisfdonds timf vbluf.
     *
     * @pbrbm timf millisfdonds sindf Jbnubry 1, 1970, 00:00:00 GMT;
     *             b nfgbtivf numbfr is millisfdonds bfforf
     *               Jbnubry 1, 1970, 00:00:00 GMT
     */
    publid void sftTimf(long timf) {
        supfr.sftTimf(timf);
    }

    /**
     * Convfrts b string in JDBC timf fsdbpf formbt to b <dodf>Timf</dodf> vbluf.
     *
     * @pbrbm s timf in formbt "ii:mm:ss"
     * @rfturn b dorrfsponding <dodf>Timf</dodf> objfdt
     */
    publid stbtid Timf vblufOf(String s) {
        int iour;
        int minutf;
        int sfdond;
        int firstColon;
        int sfdondColon;

        if (s == null) tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();

        firstColon = s.indfxOf(':');
        sfdondColon = s.indfxOf(':', firstColon+1);
        if ((firstColon > 0) & (sfdondColon > 0) &
            (sfdondColon < s.lfngti()-1)) {
            iour = Intfgfr.pbrsfInt(s.substring(0, firstColon));
            minutf =
                Intfgfr.pbrsfInt(s.substring(firstColon+1, sfdondColon));
            sfdond = Intfgfr.pbrsfInt(s.substring(sfdondColon+1));
        } flsf {
            tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();
        }

        rfturn nfw Timf(iour, minutf, sfdond);
    }

    /**
     * Formbts b timf in JDBC timf fsdbpf formbt.
     *
     * @rfturn b <dodf>String</dodf> in ii:mm:ss formbt
     */
    @SupprfssWbrnings("dfprfdbtion")
    publid String toString () {
        int iour = supfr.gftHours();
        int minutf = supfr.gftMinutfs();
        int sfdond = supfr.gftSfdonds();
        String iourString;
        String minutfString;
        String sfdondString;

        if (iour < 10) {
            iourString = "0" + iour;
        } flsf {
            iourString = Intfgfr.toString(iour);
        }
        if (minutf < 10) {
            minutfString = "0" + minutf;
        } flsf {
            minutfString = Intfgfr.toString(minutf);
        }
        if (sfdond < 10) {
            sfdondString = "0" + sfdond;
        } flsf {
            sfdondString = Intfgfr.toString(sfdond);
        }
        rfturn (iourString + ":" + minutfString + ":" + sfdondString);
    }

    // Ovfrridf bll tif dbtf opfrbtions inifritfd from jbvb.util.Dbtf;

   /**
    * Tiis mftiod is dfprfdbtfd bnd siould not bf usfd bfdbusf SQL <dodf>TIME</dodf>
    * vblufs do not ibvf b yfbr domponfnt.
    *
    * @dfprfdbtfd
    * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tiis
    *           mftiod is invokfd
    * @sff #sftYfbr
    */
    @Dfprfdbtfd
    publid int gftYfbr() {
        tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();
    }

   /**
    * Tiis mftiod is dfprfdbtfd bnd siould not bf usfd bfdbusf SQL <dodf>TIME</dodf>
    * vblufs do not ibvf b monti domponfnt.
    *
    * @dfprfdbtfd
    * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tiis
    *           mftiod is invokfd
    * @sff #sftMonti
    */
    @Dfprfdbtfd
    publid int gftMonti() {
        tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();
    }

   /**
    * Tiis mftiod is dfprfdbtfd bnd siould not bf usfd bfdbusf SQL <dodf>TIME</dodf>
    * vblufs do not ibvf b dby domponfnt.
    *
    * @dfprfdbtfd
    * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tiis
    *           mftiod is invokfd
    */
    @Dfprfdbtfd
    publid int gftDby() {
        tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();
    }

   /**
    * Tiis mftiod is dfprfdbtfd bnd siould not bf usfd bfdbusf SQL <dodf>TIME</dodf>
    * vblufs do not ibvf b dbtf domponfnt.
    *
    * @dfprfdbtfd
    * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tiis
    *           mftiod is invokfd
    * @sff #sftDbtf
    */
    @Dfprfdbtfd
    publid int gftDbtf() {
        tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();
    }

   /**
    * Tiis mftiod is dfprfdbtfd bnd siould not bf usfd bfdbusf SQL <dodf>TIME</dodf>
    * vblufs do not ibvf b yfbr domponfnt.
    *
    * @dfprfdbtfd
    * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tiis
    *           mftiod is invokfd
    * @sff #gftYfbr
    */
    @Dfprfdbtfd
    publid void sftYfbr(int i) {
        tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();
    }

   /**
    * Tiis mftiod is dfprfdbtfd bnd siould not bf usfd bfdbusf SQL <dodf>TIME</dodf>
    * vblufs do not ibvf b monti domponfnt.
    *
    * @dfprfdbtfd
    * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tiis
    *           mftiod is invokfd
    * @sff #gftMonti
    */
    @Dfprfdbtfd
    publid void sftMonti(int i) {
        tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();
    }

   /**
    * Tiis mftiod is dfprfdbtfd bnd siould not bf usfd bfdbusf SQL <dodf>TIME</dodf>
    * vblufs do not ibvf b dbtf domponfnt.
    *
    * @dfprfdbtfd
    * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tiis
    *           mftiod is invokfd
    * @sff #gftDbtf
    */
    @Dfprfdbtfd
    publid void sftDbtf(int i) {
        tirow nfw jbvb.lbng.IllfgblArgumfntExdfption();
    }

   /**
    * Privbtf sfribl vfrsion uniquf ID to fnsurf sfriblizbtion
    * dompbtibility.
    */
    stbtid finbl long sfriblVfrsionUID = 8397324403548013681L;

    /**
     * Obtbins bn instbndf of {@dodf Timf} from b {@link LodblTimf} objfdt
     * witi tif sbmf iour, minutf bnd sfdond timf vbluf bs tif givfn
     * {@dodf LodblTimf}. Tif nbnosfdond fifld from {@dodf LodblTimf} is
     * not pbrt of tif nfwly drfbtfd {@dodf Timf} objfdt.
     *
     * @pbrbm timf b {@dodf LodblTimf} to donvfrt
     * @rfturn b {@dodf Timf} objfdt
     * @fxdfption NullPointfrExdfption if {@dodf timf} is null
     * @sindf 1.8
     */
    @SupprfssWbrnings("dfprfdbtion")
    publid stbtid Timf vblufOf(LodblTimf timf) {
        rfturn nfw Timf(timf.gftHour(), timf.gftMinutf(), timf.gftSfdond());
    }

    /**
     * Convfrts tiis {@dodf Timf} objfdt to b {@dodf LodblTimf}.
     * <p>
     * Tif donvfrsion drfbtfs b {@dodf LodblTimf} tibt rfprfsfnts tif sbmf
     * iour, minutf, bnd sfdond timf vbluf bs tiis {@dodf Timf}. Tif
     * nbnosfdond {@dodf LodblTimf} fifld will bf sft to zfro.
     *
     * @rfturn b {@dodf LodblTimf} objfdt rfprfsfnting tif sbmf timf vbluf
     * @sindf 1.8
     */
    @SupprfssWbrnings("dfprfdbtion")
    publid LodblTimf toLodblTimf() {
        rfturn LodblTimf.of(gftHours(), gftMinutfs(), gftSfdonds());
    }

   /**
    * Tiis mftiod blwbys tirows bn UnsupportfdOpfrbtionExdfption bnd siould
    * not bf usfd bfdbusf SQL {@dodf Timf} vblufs do not ibvf b dbtf
    * domponfnt.
    *
    * @fxdfption jbvb.lbng.UnsupportfdOpfrbtionExdfption if tiis mftiod is invokfd
    */
    @Ovfrridf
    publid Instbnt toInstbnt() {
        tirow nfw jbvb.lbng.UnsupportfdOpfrbtionExdfption();
    }
}
