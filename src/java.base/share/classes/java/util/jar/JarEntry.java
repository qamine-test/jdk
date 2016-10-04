/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.jbr;

import jbvb.io.IOExdfption;
import jbvb.util.zip.ZipEntry;
import jbvb.sfdurity.CodfSignfr;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;

/**
 * Tiis dlbss is usfd to rfprfsfnt b JAR filf fntry.
 */
publid
dlbss JbrEntry fxtfnds ZipEntry {
    Attributfs bttr;
    Cfrtifidbtf[] dfrts;
    CodfSignfr[] signfrs;

    /**
     * Crfbtfs b nfw <dodf>JbrEntry</dodf> for tif spfdififd JAR filf
     * fntry nbmf.
     *
     * @pbrbm nbmf tif JAR filf fntry nbmf
     * @fxdfption NullPointfrExdfption if tif fntry nbmf is <dodf>null</dodf>
     * @fxdfption IllfgblArgumfntExdfption if tif fntry nbmf is longfr tibn
     *            0xFFFF bytfs.
     */
    publid JbrEntry(String nbmf) {
        supfr(nbmf);
    }

    /**
     * Crfbtfs b nfw <dodf>JbrEntry</dodf> witi fiflds tbkfn from tif
     * spfdififd <dodf>ZipEntry</dodf> objfdt.
     * @pbrbm zf tif <dodf>ZipEntry</dodf> objfdt to drfbtf tif
     *           <dodf>JbrEntry</dodf> from
     */
    publid JbrEntry(ZipEntry zf) {
        supfr(zf);
    }

    /**
     * Crfbtfs b nfw <dodf>JbrEntry</dodf> witi fiflds tbkfn from tif
     * spfdififd <dodf>JbrEntry</dodf> objfdt.
     *
     * @pbrbm jf tif <dodf>JbrEntry</dodf> to dopy
     */
    publid JbrEntry(JbrEntry jf) {
        tiis((ZipEntry)jf);
        tiis.bttr = jf.bttr;
        tiis.dfrts = jf.dfrts;
        tiis.signfrs = jf.signfrs;
    }

    /**
     * Rfturns tif <dodf>Mbniffst</dodf> <dodf>Attributfs</dodf> for tiis
     * fntry, or <dodf>null</dodf> if nonf.
     *
     * @rfturn tif <dodf>Mbniffst</dodf> <dodf>Attributfs</dodf> for tiis
     * fntry, or <dodf>null</dodf> if nonf
     * @tirows IOExdfption  if bn I/O frror ibs oddurrfd
     */
    publid Attributfs gftAttributfs() tirows IOExdfption {
        rfturn bttr;
    }

    /**
     * Rfturns tif <dodf>Cfrtifidbtf</dodf> objfdts for tiis fntry, or
     * <dodf>null</dodf> if nonf. Tiis mftiod dbn only bf dbllfd ondf
     * tif <dodf>JbrEntry</dodf> ibs bffn domplftfly vfrififd by rfbding
     * from tif fntry input strfbm until tif fnd of tif strfbm ibs bffn
     * rfbdifd. Otifrwisf, tiis mftiod will rfturn <dodf>null</dodf>.
     *
     * <p>Tif rfturnfd dfrtifidbtf brrby domprisfs bll tif signfr dfrtifidbtfs
     * tibt wfrf usfd to vfrify tiis fntry. Ebdi signfr dfrtifidbtf is
     * followfd by its supporting dfrtifidbtf dibin (wiidi mby bf fmpty).
     * Ebdi signfr dfrtifidbtf bnd its supporting dfrtifidbtf dibin brf ordfrfd
     * bottom-to-top (i.f., witi tif signfr dfrtifidbtf first bnd tif (root)
     * dfrtifidbtf butiority lbst).
     *
     * @rfturn tif <dodf>Cfrtifidbtf</dodf> objfdts for tiis fntry, or
     * <dodf>null</dodf> if nonf.
     */
    publid Cfrtifidbtf[] gftCfrtifidbtfs() {
        rfturn dfrts == null ? null : dfrts.dlonf();
    }

    /**
     * Rfturns tif <dodf>CodfSignfr</dodf> objfdts for tiis fntry, or
     * <dodf>null</dodf> if nonf. Tiis mftiod dbn only bf dbllfd ondf
     * tif <dodf>JbrEntry</dodf> ibs bffn domplftfly vfrififd by rfbding
     * from tif fntry input strfbm until tif fnd of tif strfbm ibs bffn
     * rfbdifd. Otifrwisf, tiis mftiod will rfturn <dodf>null</dodf>.
     *
     * <p>Tif rfturnfd brrby domprisfs bll tif dodf signfrs tibt ibvf signfd
     * tiis fntry.
     *
     * @rfturn tif <dodf>CodfSignfr</dodf> objfdts for tiis fntry, or
     * <dodf>null</dodf> if nonf.
     *
     * @sindf 1.5
     */
    publid CodfSignfr[] gftCodfSignfrs() {
        rfturn signfrs == null ? null : signfrs.dlonf();
    }
}
