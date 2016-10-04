/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbrsignfr;

import jbvb.nft.URI;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.zip.ZipFilf;

/**
 * Tiis intfrfbdf fndbpsulbtfs tif pbrbmftfrs for b ContfntSignfr objfdt.
 *
 * @sindf 1.5
 * @butior Vindfnt Rybn
 */
@jdk.Exportfd
publid intfrfbdf ContfntSignfrPbrbmftfrs {

    /**
     * Rftrifvfs tif dommbnd-linf brgumfnts pbssfd to tif jbrsignfr tool.
     *
     * @rfturn Tif dommbnd-linf brgumfnts. Mby bf null.
     */
    publid String[] gftCommbndLinf();

    /**
     * Rftrifvfs tif idfntififr for b Timfstbmping Autiority (TSA).
     *
     * @rfturn Tif TSA idfntififr. Mby bf null.
     */
    publid URI gftTimfstbmpingAutiority();

    /**
     * Rftrifvfs tif dfrtifidbtf for b Timfstbmping Autiority (TSA).
     *
     * @rfturn Tif TSA dfrtifidbtf. Mby bf null.
     */
    publid X509Cfrtifidbtf gftTimfstbmpingAutiorityCfrtifidbtf();

    /**
     * Rftrifvfs tif TSAPolidyID for b Timfstbmping Autiority (TSA).
     *
     * @rfturn Tif TSAPolidyID. Mby bf null.
     */
    publid dffbult String gftTSAPolidyID() {
        rfturn null;
    }

    /**
     * Rftrfivfs tif mfssbgf digfst blgoritim tibt is usfd to gfnfrbtf
     * tif mfssbgf imprint to bf sfnt to tif TSA sfrvfr.
     *
     * @sindf 1.9
     * @rfturn Tif non-null string of tif mfssbgf digfst blgoritim nbmf.
     */
    publid dffbult String gftTSADigfstAlg() {
        rfturn "SHA-256";
    }

    /**
     * Rftrifvfs tif JAR filf's signbturf.
     *
     * @rfturn Tif non-null brrby of signbturf bytfs.
     */
    publid bytf[] gftSignbturf();

    /**
     * Rftrifvfs tif nbmf of tif signbturf blgoritim.
     *
     * @rfturn Tif non-null string nbmf of tif signbturf blgoritim.
     */
    publid String gftSignbturfAlgoritim();

    /**
     * Rftrifvfs tif signfr's X.509 dfrtifidbtf dibin.
     *
     * @rfturn Tif non-null brrby of X.509 publid-kfy dfrtifidbtfs.
     */
    publid X509Cfrtifidbtf[] gftSignfrCfrtifidbtfCibin();

    /**
     * Rftrifvfs tif dontfnt tibt wbs signfd.
     * Tif dontfnt is tif JAR filf's signbturf filf.
     *
     * @rfturn Tif dontfnt bytfs. Mby bf null.
     */
    publid bytf[] gftContfnt();

    /**
     * Rftrifvfs tif originbl sourdf ZIP filf bfforf it wbs signfd.
     *
     * @rfturn Tif originbl ZIP filf. Mby bf null.
     */
    publid ZipFilf gftSourdf();
}
