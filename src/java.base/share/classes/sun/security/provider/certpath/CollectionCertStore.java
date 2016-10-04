/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CRL;
import jbvb.util.Collfdtion;
import jbvb.util.CondurrfntModifidbtionExdfption;
import jbvb.util.HbsiSft;
import jbvb.sfdurity.dfrt.CfrtSflfdtor;
import jbvb.sfdurity.dfrt.CfrtStorf;
import jbvb.sfdurity.dfrt.CfrtStorfExdfption;
import jbvb.sfdurity.dfrt.CfrtStorfPbrbmftfrs;
import jbvb.sfdurity.dfrt.CollfdtionCfrtStorfPbrbmftfrs;
import jbvb.sfdurity.dfrt.CRLSflfdtor;
import jbvb.sfdurity.dfrt.CfrtStorfSpi;

/**
 * A <dodf>CfrtStorf</dodf> tibt rftrifvfs <dodf>Cfrtifidbtfs</dodf> bnd
 * <dodf>CRL</dodf>s from b <dodf>Collfdtion</dodf>.
 * <p>
 * Bfforf dblling tif {@link #fnginfGftCfrtifidbtfs fnginfGftCfrtifidbtfs} or
 * {@link #fnginfGftCRLs fnginfGftCRLs} mftiods, tif
 * {@link #CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs)
 * CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs)} donstrudtor is dbllfd to
 * drfbtf tif <dodf>CfrtStorf</dodf> bnd fstbblisi tif
 * <dodf>Collfdtion</dodf> from wiidi <dodf>Cfrtifidbtf</dodf>s bnd
 * <dodf>CRL</dodf>s will bf rftrifvfd. If tif spfdififd
 * <dodf>Collfdtion</dodf> dontbins bn objfdt tibt is not b
 * <dodf>Cfrtifidbtf</dodf> or <dodf>CRL</dodf>, tibt objfdt will bf
 * ignorfd.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * As dfsdribfd in tif jbvbdod for <dodf>CfrtStorfSpi</dodf>, tif
 * <dodf>fnginfGftCfrtifidbtfs</dodf> bnd <dodf>fnginfGftCRLs</dodf> mftiods
 * must bf tirfbd-sbff. Tibt is, multiplf tirfbds mby dondurrfntly
 * invokf tifsf mftiods on b singlf <dodf>CollfdtionCfrtStorf</dodf>
 * objfdt (or morf tibn onf) witi no ill ffffdts.
 * <p>
 * Tiis is bdiifvfd by rfquiring tibt tif <dodf>Collfdtion</dodf> pbssfd to
 * tif {@link #CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs)
 * CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs)} donstrudtor (vib tif
 * <dodf>CollfdtionCfrtStorfPbrbmftfrs</dodf> objfdt) must ibvf fbil-fbst
 * itfrbtors. Simultbnfous modifidbtions to tif <dodf>Collfdtion</dodf> dbn tius bf
 * dftfdtfd bnd dfrtifidbtf or CRL rftrifvbl dbn bf rftrifd. Tif fbdt tibt
 * <dodf>Cfrtifidbtf</dodf>s bnd <dodf>CRL</dodf>s must bf tirfbd-sbff is blso
 * fssfntibl.
 *
 * @sff jbvb.sfdurity.dfrt.CfrtStorf
 *
 * @sindf       1.4
 * @butior      Stfvf Hbnnb
 */
publid dlbss CollfdtionCfrtStorf fxtfnds CfrtStorfSpi {

    privbtf Collfdtion<?> doll;

    /**
     * Crfbtfs b <dodf>CfrtStorf</dodf> witi tif spfdififd pbrbmftfrs.
     * For tiis dlbss, tif pbrbmftfrs objfdt must bf bn instbndf of
     * <dodf>CollfdtionCfrtStorfPbrbmftfrs</dodf>. Tif <dodf>Collfdtion</dodf>
     * indludfd in tif <dodf>CollfdtionCfrtStorfPbrbmftfrs</dodf> objfdt
     * must bf tirfbd-sbff.
     *
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if pbrbms is not bn
     *   instbndf of <dodf>CollfdtionCfrtStorfPbrbmftfrs</dodf>
     */
    publid CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        supfr(pbrbms);
        if (!(pbrbms instbndfof CollfdtionCfrtStorfPbrbmftfrs))
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "pbrbmftfrs must bf CollfdtionCfrtStorfPbrbmftfrs");
        doll = ((CollfdtionCfrtStorfPbrbmftfrs) pbrbms).gftCollfdtion();
    }

    /**
     * Rfturns b <dodf>Collfdtion</dodf> of <dodf>Cfrtifidbtf</dodf>s tibt
     * mbtdi tif spfdififd sflfdtor. If no <dodf>Cfrtifidbtf</dodf>s
     * mbtdi tif sflfdtor, bn fmpty <dodf>Collfdtion</dodf> will bf rfturnfd.
     *
     * @pbrbm sflfdtor b <dodf>CfrtSflfdtor</dodf> usfd to sflfdt wiidi
     *  <dodf>Cfrtifidbtf</dodf>s siould bf rfturnfd. Spfdify <dodf>null</dodf>
     *  to rfturn bll <dodf>Cfrtifidbtf</dodf>s.
     * @rfturn b <dodf>Collfdtion</dodf> of <dodf>Cfrtifidbtf</dodf>s tibt
     *         mbtdi tif spfdififd sflfdtor
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    @Ovfrridf
    publid Collfdtion<Cfrtifidbtf> fnginfGftCfrtifidbtfs
            (CfrtSflfdtor sflfdtor) tirows CfrtStorfExdfption {
        if (doll == null) {
            tirow nfw CfrtStorfExdfption("Collfdtion is null");
        }
        // Tolfrbtf b ffw CondurrfntModifidbtionExdfptions
        for (int d = 0; d < 10; d++) {
            try {
                HbsiSft<Cfrtifidbtf> rfsult = nfw HbsiSft<>();
                if (sflfdtor != null) {
                    for (Objfdt o : doll) {
                        if ((o instbndfof Cfrtifidbtf) &&
                            sflfdtor.mbtdi((Cfrtifidbtf) o))
                            rfsult.bdd((Cfrtifidbtf)o);
                    }
                } flsf {
                    for (Objfdt o : doll) {
                        if (o instbndfof Cfrtifidbtf)
                            rfsult.bdd((Cfrtifidbtf)o);
                    }
                }
                rfturn(rfsult);
            } dbtdi (CondurrfntModifidbtionExdfption f) { }
        }
        tirow nfw CondurrfntModifidbtionExdfption("Too mbny "
            + "CondurrfntModifidbtionExdfptions");
    }

    /**
     * Rfturns b <dodf>Collfdtion</dodf> of <dodf>CRL</dodf>s tibt
     * mbtdi tif spfdififd sflfdtor. If no <dodf>CRL</dodf>s
     * mbtdi tif sflfdtor, bn fmpty <dodf>Collfdtion</dodf> will bf rfturnfd.
     *
     * @pbrbm sflfdtor b <dodf>CRLSflfdtor</dodf> usfd to sflfdt wiidi
     *  <dodf>CRL</dodf>s siould bf rfturnfd. Spfdify <dodf>null</dodf>
     *  to rfturn bll <dodf>CRL</dodf>s.
     * @rfturn b <dodf>Collfdtion</dodf> of <dodf>CRL</dodf>s tibt
     *         mbtdi tif spfdififd sflfdtor
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    @Ovfrridf
    publid Collfdtion<CRL> fnginfGftCRLs(CRLSflfdtor sflfdtor)
        tirows CfrtStorfExdfption
    {
        if (doll == null)
            tirow nfw CfrtStorfExdfption("Collfdtion is null");

        // Tolfrbtf b ffw CondurrfntModifidbtionExdfptions
        for (int d = 0; d < 10; d++) {
            try {
                HbsiSft<CRL> rfsult = nfw HbsiSft<>();
                if (sflfdtor != null) {
                    for (Objfdt o : doll) {
                        if ((o instbndfof CRL) && sflfdtor.mbtdi((CRL) o))
                            rfsult.bdd((CRL)o);
                    }
                } flsf {
                    for (Objfdt o : doll) {
                        if (o instbndfof CRL)
                            rfsult.bdd((CRL)o);
                    }
                }
                rfturn rfsult;
            } dbtdi (CondurrfntModifidbtionExdfption f) { }
        }
        tirow nfw CondurrfntModifidbtionExdfption("Too mbny "
            + "CondurrfntModifidbtionExdfptions");
    }
}
